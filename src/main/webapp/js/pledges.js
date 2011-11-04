var $j = jQuery.noConflict();

var pledges = {
postToUrl: function(url, params, newWindow)
{
    var form = $('<form>');
    form.attr('action', url);
    form.attr('method', 'POST');
    if(newWindow){ form.attr('target', '_blank'); }

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
	    document.location = redirecturl;
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

    generateWidget: function(widgetname,widgetid,authenticate, redirecturl, referer, donationurl) {
	var sessionId = this.getCookie("sessionId");

	if (authenticate == true && sessionId == "") {
        window.open(redirecturl, "_blank");
//	    window.location=redirecturl;
	    return;
	}


	OrangeLeapWidget.updateViewCount(widgetid,referer);

	var mydatastore = new Ext.data.JsonStore({
	    url:'/donorwidgets/pledges.json?guid=' + widgetid + '&sessionId=' + sessionId,
	    root:'rows',
	    fields:['id','donationdate','recurring','amount','projectCode','motivationCode','status'],
	    sortInfo:{field:'id',direction:'ASC'}
	});



	var pledgesGrid = new Ext.grid.GridPanel({
	    id:'pledgegrid',
	    store:mydatastore,
	    columns:	[
	    {id:'id', header: 'Pledge Id', dataIndex: 'id',sortable:true},
	    {id:'donationdate',xtype: 'datecolumn', header: 'Pledge Date', dataIndex:'donationdate',sortable:true},
            {id: 'recurring',header: 'Recurring',dataIndex: 'recurring'},
	    {id:'amount',xtype: 'numbercolumn', header: 'Pledge Amount',dataIndex:'amount',sortable:true},
	    {id:'status',header:'Pledge Status',dataIndex:'status',sortable:true},
	    {id:'projectCode', header: 'Project Code', dataIndex: 'projectCode', sortable:true},
	    {id:'motivationCode', header: 'Motivation Code', dataIndex: 'motivationCode', sortable: true},

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
	    width:655
	});
	mydatastore.load();
        Ext.get('loading').remove();
	Ext.get('loading-mask').fadeOut({remove:true});

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
                    console.log('make a payment - ' + record.id);
                    var params = {};
                    window.open(donationurl + "?pledge_id=" + record.id +"&gift_amount=" + record.data.amount + "&gift_designation=" + record.data.projectCode + "&gift_motivation=" + record.data.motivationCode);
                    //pledges.postToUrl('http://localhost/~ldangelo/donation.html',params);
                    break;
                case 'go':
                    console.log('go to this record - ' + record.id);
                    break;
            }
        }
    },pledgesGrid);

	pledgesGrid.render(widgetname);
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