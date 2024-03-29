var $j = jQuery.noConflict();

var gifthistory = {
    sessionId: null,
    widgetId: null,
	replaceTopContent: null,
	
    setCookie: function(c_name,value,expiredays)
    {
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	document.cookie=c_name+ "=" +escape(value)+
	    ((expiredays==null) ? "" : ";expires="+exdate.toUTCString());
    },

    populateWidget: function(constituent,table) {
	for (x in table.fields) {
	    var fieldName = table.fields[x].customTableFieldName;
	    if (fieldName != null) {
		if (fieldName.indexOf(".") != -1) {
		    //
		    // this is a sub object like primaryEmail, etc...
		    var obj = constituent[fieldName.substring(0,fieldName.indexOf("."))];
		    $j("[name=" + table.fields[x].customTableFieldName + "]").val(obj[fieldName.substring(fieldName.indexOf(".") +1, fieldName.length)]);
	    } else {
		$j("[name=" + table.fields[x].customTableFieldName + "]").val(constituent[table.fields[x].customTableFieldName]);
	    }
	    }
	}
    },

    populatePicklist: function(picklistitems,field) {
	for (x in picklistitems) {
	    $j("<option value=" + picklistitems[x].itemName + ">" + picklistitems[x].defaultDisplayValue + "</option>").appendTo("select[name=" + field + "]");
	}
    },

    handleReturn: function(datafromserver) {
        	Ext.Msg.show({
    			title:'Success'
    				,msg:datafromserver
    				,modal:true
    				,icon:Ext.Msg.INFO
    				,buttons:Ext.Msg.OK
    		});
    },


    getCookie: function(c_name)
    {
	if (document.cookie.length>0)
	{
	    c_start=document.cookie.indexOf(c_name + "=");
	    if (c_start!=-1)
	    {
		c_start=c_start + c_name.length+1;
		c_end=document.cookie.indexOf(";",c_start);
		if (c_end==-1) c_end=document.cookie.length;
		return unescape(document.cookie.substring(c_start,c_end));
	    }
	}
	return "";
    },

    generateWidget: function(widgetname,widgetId,authenticate, redirecturl, referer, replaceTopContent) {
		this.sessionId = this.getCookie("sessionId");
	    this.widgetId = widgetId;
	    this.replaceTopContent = replaceTopContent;

		if (authenticate == true && (Ext.isEmpty(this.sessionId) || this.sessionId == '""')) {
			if (replaceTopContent == 'true') {
				top.location.href=redirecturl;
			}
			else {
				window.location.href=redirecturl;
			}
		}
		else {
			OrangeLeapWidget.updateViewCount(widgetId,referer);

			var mydatastore = new Ext.data.JsonStore({
			    url:'giftHistory.json?guid=' + widgetId + '&sessionId=' + this.sessionId,
			    root:'rows',
			    fields:['id',
			        {name: 'donationdate', type: 'date', dateFormat: 'c'},
			        'amount','status','paymentstatus'],
			    sortInfo:{field:'id',direction:'ASC'}
			});

			var giftGrid = new Ext.grid.GridPanel({
			    id:'giftgrid',
			    store: mydatastore,
			    loadMask: true,
				cls: 'widgetForm',
			    columns:	[
					{id:'id', header: 'Gift Id', dataIndex: 'id',sortable:true},
					{id:'donationdate', xtype: 'datecolumn', header: 'Donation Date', dataIndex:'donationdate',sortable:true},
					{id:'amount', xtype: 'numbercolumn', header: 'Gift Amount',dataIndex:'amount',sortable:true},
					{id:'status', header:'Gift Status',dataIndex:'status',sortable:true},
					{id:'paymentstatus', header:'Payment Status',dataIndex:'paymentstatus',sortable:true},
							   {header: "Actions", width: 60, sortable: true, renderer: function() {
						return '<div class="controlBtn"><img src="images/inboxSmall.png" class="make_receipt"></div>';
					}, dataIndex: 'Id'}
				],
			    viewConfig: {
					forceFit: true,
					emptyText: 'No Gift History To Display'
			    },
			    stripeRows:true,
			    frame:false,
			    title:'Gift History',
			    height:350,
			    width:655,
			    bbar: new Ext.Toolbar({
			        items: [
			            {
							xtype : 'box',
							cls: 'logoutLink',
							autoEl : {
								id: 'olLogoutLink',
								tag : 'a',
								href : 'javascript:void(0)',
								html : 'Logout'
							}
			            }
			        ]
			    })
			});
			mydatastore.load({
				callback: function() {
					Ext.get('loading').remove();
					Ext.get('loading-mask').fadeOut({remove:true});
				}
			});

			var that = this;
			giftGrid.on("click", function(e) {
	            var btn = e.getTarget('.controlBtn');
	            if (btn) {
	                $j(btn).addClass('showWait');
	                var t = e.getTarget();
	                var v = this.getView();
	                var rowIdx = v.findRowIndex(t);
	                var record = this.getStore().getAt(rowIdx);
	                var control = t.className.split('_')[1];
	                switch (control) {
	                    case 'receipt':
	                        if (window.console) {
	                            console.log('send receipt for gift - ' + record.id);
	                        }
	                        var callbackproxy = function(dataFromServer) {
					            gifthistory.handleReturn(dataFromServer);
	                            $j(btn).removeClass('showWait');
				            }

				            var callMetaData={callback:callbackproxy};

				            OrangeLeapWidget.sendGiftReceipt(record.id,gifthistory.sessionId,gifthistory.widgetId,callMetaData);

	                        break;
	                    case 'go':
	                        if (window.console) {
	                            console.log('go to this record - ' + record.id);
	                        }
	                        break;
	                }
	            }
	        },giftGrid);

			giftGrid.render(widgetname);
			Ext.get('olLogoutLink').on('click', function() {
				that.logout.call(that);
			});
		}
    },

	logout: function() {
		var logoutUrl = 'logout.json?guid=' + this.widgetId + '&sessionId=' + this.sessionId;
	    document.cookie = 'sessionId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	    if (this.replaceTopContent == 'true') {
			top.location.href = logoutUrl;
	    }
	    else {
			window.location.href = logoutUrl;
	    }
	},

    showError: function() {
	$j("div#globalErrors").show();
    },

    hideError: function() {
	$j("div#globalErrors").hide();
    },

    clearError: function() {
	$j("ul#errors").empty();
    },

    handleError: function(str) {
	$j("<li>" + str + "</li>").appendTo('ul#errors');
    },

    validateForm: function() {
	var isValid=1;

	return isValid;
    }

}