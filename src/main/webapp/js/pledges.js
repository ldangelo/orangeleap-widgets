var $j = jQuery.noConflict();

var pledges = {
		replaceTopContent: null,		
postToUrl: function(url, params, replaceTopWindow)
{
    var form = $('<form>');
    form.attr('action', url);
    form.attr('method', 'POST');
    if(replaceTopWindow){ form.attr('target', '_top'); }

    var addParam = function(paramName, paramValue){
        var input = $('<input type="hidden">');
        input.attr({ 'id':     paramName,
                     'name':   paramName,
                     'value':  paramValue });
        form.append(input);
    };

    // Params is an Array.
    if(params instanceof Array){
        for(var i=0; i<params.length; i++){
            addParam(i, params[i]);
        }
    }

    // Params is an Associative array or Object.
    if(params instanceof Object){
        for(var key in params){
            addParam(key, params[key]);
        }
    }

    // Submit the form, then remove it from the page
    form.appendTo(document.body);
    form.submit();
    form.remove();
},
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

    handleReturn: function(constituentid,widetid,redirecturl) {
	if (constituentid == -1) {
	    this.handlerError("Authentication Failed!");
	} else {
	    this.setCookie("constituentId",constituentid);
	    if (this.replaceTopContent == 'true') {
			Ext.getBody().mask('Loading...', 'x-mask-loading');
	    	top.location.href = redirecturl;
	    }
	    else {
			Ext.getBody().mask('Loading...', 'x-mask-loading');
	    	window.location.href = redirecturl;
	    }
	}
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

    generateWidget: function(widgetname,widgetId,authenticate, redirecturl, referer, donationurl, replaceTopContent) {
	this.sessionId = this.getCookie("sessionId");
	this.replaceTopContent = replaceTopContent;
	this.widgetId = widgetId;

	var that = this;
	
	
	if (authenticate == true && this.sessionId == "") {
		if (replaceTopContent == 'true') {
			Ext.getBody().mask('Loading...', 'x-mask-loading');
			top.location.href=redirecturl;
		}
		else {
			Ext.getBody().mask('Loading...', 'x-mask-loading');
			window.location.href=redirecturl;
		}

		return;
	}


	OrangeLeapWidget.updateViewCount(widgetId,referer);

	var mydatastore = new Ext.data.JsonStore({
	    url:'pledges.json?guid=' + widgetId + '&sessionId=' + that.sessionId,
	    root:'rows',
	    fields:['id',
	    	{name: 'donationdate', type: 'date', dateFormat: 'c'},
	    	'recurring',
	    	{name: 'startdate', type: 'date', dateFormat: 'c'},
	    	{name: 'enddate', type: 'date', dateFormat: 'c'},
	    	'frequency','originalamount','amountpaid','amountremaining','status','projectCode','projectCodeDescription','motivationCode','motivationCodeDescription'],
	    sortInfo:{field:'id',direction:'ASC'}
	});



	var pledgesGrid = new Ext.grid.GridPanel({
	    id:'pledgegrid',
	    store:mydatastore,
		cls: 'widgetForm',
	    columns:	[
	    {id:'id', header: 'Pledge Id', dataIndex: 'id',sortable:true},
	    {id:'donationdate',xtype: 'datecolumn', header: 'Pledge Date', dataIndex:'donationdate',sortable:true},
	    {id:'startdate',xtype: 'datecolumn', header: 'Start Date', dataIndex:'startdate',sortable:true},
	    {id:'enddate',xtype: 'datecolumn', header: 'End Date', dataIndex:'enddate',sortable:true},
        {id:'frequency',header:'Frequency',dataIndex:'frequency',sortable:true},
		{id:'status',header:'Status',dataIndex:'status',sortable:true},
		{id:'originalamount',xtype: 'numbercolumn', header: 'Original Amount',dataIndex:'originalamount',sortable:true},
		{id:'amountpaid',xtype: 'numbercolumn', header: 'Amount Paid',dataIndex:'amountpaid',sortable:true},
	    {id:'amountremaining',xtype: 'numbercolumn', header: 'Amount Remaining',dataIndex:'amountremaining',sortable:true},
	    
	    

        {header: "Actions", width: 60, sortable: false, renderer: function() {
            return '<div class="controlBtn"><img src="images/money.png" class="make_payment"></div>';
        }, dataIndex: 'Id'}],
	    viewConfig: {
		forceFit: true,
		emptyText: 'No Pledges To Display'
	    },
	    stripeRows:true,
	    frame:false,
	    title:'Pledges',
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

    pledgesGrid.on("click", function(e) {
         var btn = e.getTarget('.controlBtn');
        if (btn) {
            var t = e.getTarget();
            var v = this.getView();
            var rowIdx = v.findRowIndex(t);
            var record = this.getStore().getAt(rowIdx);
            var control = t.className.split('_')[1];
            switch (control) {
                case 'payment':
                    if (window.console) {
                        console.log('make a payment - ' + record.id);
                    }
                    var params = {};
                    if (replaceTopContent == 'true') {
						Ext.getBody().mask('Loading...', 'x-mask-loading');
						if(record.data.recurring){
							top.location.href= donationurl + "?pledge_id=" + record.id +"&transaction_firstDistributionLineAmount=" + record.data.originalamount + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode;
						}
						else {
							top.location.href= donationurl + "?pledge_id=" + record.id + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode;
						}
                    }
                    else {
						Ext.getBody().mask('Loading...', 'x-mask-loading');
                    	window.location.href= donationurl + "?pledge_id=" + record.id +"&transaction_firstDistributionLineAmount=" + record.data.amount + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode;
						if(record.data.recurring){
							window.location.href= donationurl + "?pledge_id=" + record.id +"&transaction_firstDistributionLineAmount=" + record.data.originalamount + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode;
						}
						else {
							window.location.href= donationurl + "?pledge_id=" + record.id + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode;
						}
                    }
                    //pledges.postToUrl('http://localhost/~ldangelo/donation.html',params);
                    break;
                case 'go':
                    if (window.console) {
                        console.log('go to this record - ' + record.id);
                    }
                    break;
            }
        }
    },pledgesGrid);

		var that = this;
		pledgesGrid.render(widgetname);
		Ext.get('olLogoutLink').on('click', function() {
			that.logout.call(that);
		});
    },

	logout: function() {
		var logoutUrl = 'logout.json?guid=' + this.widgetId + '&sessionId=' + this.sessionId;
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