var $j = jQuery.noConflict();

var authentication = {

    include: function(filename)
    {
	var head = document.getElementsByTagName('head')[0];

	script = document.createElement('script');
	script.src = filename;
	script.type = 'text/javascript';

	head.appendChild(script)
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

    generateWidget: function(widgetid, redirecturl) {
	constituentid = this.getCookie("constituentId");

	if (constituentid != "") window.location.redirecturl;

        var loginform = new Ext.form.FormPanel({
	    id:'loginform',
	    frame:true,
	    title: 'Authenticate',
	    width:350,
	    defaults: {width: 230},
	    defaultType: 'textfield',
	    items: [{
		fieldLabel: 'User Name',
		name: 'username',
		allowBlank:false
	    },{
		fieldLabel: 'Password',
		name:'password',
		allowBlank:false
	    }],
	    buttons: [{
		text: 'Login',
		handler: function(b,e) {
		    //
		    // call authenticate through DWR
		    var callbackproxy = function(dataFromServer) {
			authentication.handleReturn(dataFromServer,widgetid,redirecturl);
		    }
		    
		    var callMetaData = {callback:callbackproxy};
		    
		    OrangeLeapWidget.authenticate(widgetid,$j("input[name=username]").val(),$j("input[name=password]").val(),callMetaData);
		}
            }]
	    
        });

	loginform.render("widget");
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