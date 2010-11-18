var $j = jQuery.noConflict();

var authentication = {
    failureurl: null,
    loginform:null,


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

    handleReturn: function(sessionId,widgetid,successurl,failureurl) {
	if (sessionId == null) {
	    this.handleError(widgetid,"Authentication Failed!");
	} else {
	    if (Ext.get("remember").dom.value == "on")
		this.setCookie("sessionId",sessionId,5);
	    else
		this.setCookie("sessionId",sessionId);

	    if (successurl != null) {
		//document.location = successurl;
		window.open(successurl,"_top");
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

    generateWidget: function(widgetid, successurl, failureurl) {
	sessionId = this.getCookie("sessionId");

	if (sessionId != "") window.location.successurl;

	this.failureurl = failureurl;

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'under';

        this.loginform = new Ext.form.FormPanel({
	    id:'loginform',
	    frame:true,
	    title: 'Authenticate',
	    width:350,
	    defaults: {width: 230},
	    defaultType: 'textfield',
	    monitorValid:true,
	    items: [{
		fieldLabel: 'User Name',
		name: 'username',
		allowBlank:false,
		minLength: 6,
		maxLength: 12,
		blankText: 'Enter your User Name Please.',
		minLengthText: 'User Name must be at least 6 characters'
	    },{
		inputType:'password',
		fieldLabel: 'Password',
		name:'password',
		allowBlank:false,
		minLength: 6,
		maxLength: 12,
		blankText: 'Enter your Password Please.',
		minLengthText: 'Password must be at least 6 characters'

	    },
		    {id: 'remember',
		     xtype:'checkbox',
		     boxLabel: 'Remember Me'
		    },
		    {
		    	xtype: "box",
		    	autoEl: {
		    		tag: 'a',
		    		href: 'http://www.orangeleap.com/',
		    		html: 'Powered by Orange Leap.'
		    	}
		    }],
	    buttons: [{
		text: 'Login',
		formBind:true,
		handler: function(b,e) {

		    //
		    // call authenticate through DWR
		    var callbackproxy = function(dataFromServer) {
			authentication.handleReturn(dataFromServer,widgetid,successurl,failureurl);
		    }

		    var callMetaData = {callback:callbackproxy};

		    OrangeLeapWidget.authenticate(widgetid,$j("input[name=username]").val(),$j("input[name=password]").val(),callMetaData);
		}
            }]

        });


	var element = Ext.query('script[src$=required-field.js]')[0];
	var renderElement = element.parentNode;
	this.loginform.render("widget");

	OrangeLeapWidget.updateViewCount(widgetid,document.location.href);
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

    errorHandlerFinished: function() {
	    if (authentication.failureurl != null)
		document.location = authentication.failureurl;
    },
    handleError: function(widgetid, str) {
	// update widget's error count
	OrangeLeapWidget.updateErrorCount(widgetid,document.location.href);

	// display the error
	Ext.Msg.show({
	    title: 'ERROR',
	    msg: str,
	    icon: Ext.MessageBox.ERROR,
	    buttons: Ext.Msg.OK,
	    modal: true,
	    fn: this.errorHandlerFinished
	});
    },

    validateForm: function() {
	var isValid=1;

	return isValid;
    }

}
