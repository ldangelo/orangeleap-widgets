var $j = jQuery.noConflict();

var gifthistory = {

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

    generateWidget: function(widgetname,widgetid,authenticate, redirecturl) {
	var sessionId = this.getCookie("sessionId");

	if (authenticate == true && sessionId == "") {
	    window.location=redirecturl;
	    return;
	}


	OrangeLeapWidget.updateViewCount(widgetid,document.location.href);

	var mydatastore = new Ext.data.JsonStore({
	    url:'/donorwidgets/giftHistory.json?guid=' + widgetid + '&sessionId=' + sessionId,
	    root:'rows',
	    fields:['id','donationdate','amount','status','paymentstatus'],
	    sortInfo:{field:'id',direction:'ASC'}
	});



	var giftGrid = new Ext.grid.GridPanel({
	    id:'giftgrid',
	    store:mydatastore,
	    columns:	[
	    {id:'id', header: 'Gift Id', dataIndex: 'id',sortable:false},
	    {id:'donationdate',header: 'Donation Date', dataIndex:'donationdate',sortable:false},
	    {id:'amount',header: 'Gift Amount',dataIndex:'amount',sortable:false},
	    {id:'status',header:'Gift Status',dataIndex:'status',sortable:false},
	    {id:'paymentstatus',header:'Payment Status',dataIndex:'paymentstatus',sortable:false}
	],
	    viewConfig: {
		forceFit: false,
		emptyText: 'No Gift History To Display'
	    },
	    stripeRows:true,
	    frame:true,
	    title:'Gift History',
	    height:350,
	    width:655
	});
	mydatastore.load();
        Ext.get('loading').remove();
	Ext.get('loading-mask').fadeOut({remove:true});

	giftGrid.render(widgetname);
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