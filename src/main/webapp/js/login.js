var $j = jQuery.noConflict();

var authentication = {
	successurl:null,
    loginform:null,

	postToUrl: function(url, params, newWindow) {
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
    setCookie: function(c_name,value,expiredays) {
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
				}
				else {
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

    forgotPassword: function(dataFromServer,widgetid) {
    	Ext.Msg.show({
    		title: "Forgot Password",
    		msg: dataFromServer,
    		icon: Ext.MessageBox.Info,
    		buttons: Ext.Msg.OK,
    		modal: true
    	});
    },

    changePassword: function(dataFromServer,widgetid) {
    	var i = 0;
    	
	// display the error
    	Ext.Msg.show({
    		title: 'Password changed!',
    		msg: dataFromServer,
    		icon: Ext.MessageBox.Info,
    		buttons: Ext.Msg.OK,
    		modal: true
    	});
    	
    },

    handleReturn: function(sessionId,widgetid,successurl) {
		if (sessionId == null) {
			this.handleError(widgetid,"Authentication Failed!");
		}
		else {
			if (Ext.get("remember").dom.value == "on")
			this.setCookie("sessionId",sessionId,5);
			else
			this.setCookie("sessionId",sessionId);

			if (successurl != null) {
				window.open(successurl);
			}
		}
    },


    getCookie: function(c_name) {
		if (document.cookie.length>0) {
			c_start=document.cookie.indexOf(c_name + "=");
			if (c_start!=-1) {
				c_start=c_start + c_name.length+1;
				c_end=document.cookie.indexOf(";",c_start);
				if (c_end==-1) {
					c_end=document.cookie.length;
				}
				return unescape(document.cookie.substring(c_start,c_end));
			}
		}
		return "";
    },

    generateWidget: function(widgetid, successurl) {
		sessionId = this.getCookie("sessionId");

		if (sessionId != "") {
			window.location.href = successurl;
		}

		this.successurl = successurl;

		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'under';

			this.loginform = new Ext.form.FormPanel({
				id:'loginform',
				border:false,
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
					blankText: 'Enter your User Name.',
					minLengthText: 'User Name must be at least 6 characters'
				},{
					inputType:'password',
					fieldLabel: 'Password',
					name:'password',
					allowBlank:false,
					minLength: 6,
					maxLength: 12,
					blankText: 'Enter your Password.',
					minLengthText: 'Password must be at least 6 characters'
				},
					{
						id: 'remember',
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
						authentication.handleReturn(dataFromServer,widgetid,successurl);
						}

						var callMetaData = {callback:callbackproxy};

						OrangeLeapWidget.authenticate(widgetid,$j("input[name=username]").val(),$j("input[name=password]").val(),callMetaData);
					}
				},{
					text: 'Forgot Password',
					formBind: false,
					handler: function(b,e) {
						var forgotpass = new Ext.form.FormPanel({
							id:'loginform',
							border:false,
							frame:true,
							width:350,
							bodyStyle: 'padding:10px',
							defaults: {width: 230},
							defaultType: 'textfield',
							monitorValid:true,
							items: [{
								fieldLabel: 'User Name',
								name: 'fpusername',
								allowBlank:false,
								minLength:6,
								maxLength:12,
								blankText: 'Enter your User Name.',
								minLengthText: 'User Name must be at least 6 characters'
							}
								],
							buttons: [{
								text: 'Submit',
								formBind:true,
								handler: function(b,e) {
									var callbackproxy = function(dataFromServer) {
										authentication.forgotPassword(dataFromServer,widgetid);
									}

									var callMetaData={callback:callbackproxy};

									OrangeLeapWidget.forgotPassword(widgetid,$j("input[name=fpusername]").val(),callMetaData);
								}
							}]
						});

						var forgotPasswordWin = new Ext.Window({
							layout: 'fit',
							plain: false,
							width: 400,
							height: 130,
							id: 'forgotPasswordWin',
							title: 'Forgot Password',
							items: [
								forgotpass
							]
						});
						var otherWin = Ext.getCmp('changePasswordWin');
						if (otherWin && ! otherWin.hidden) {
							otherWin.hide();
						}
						forgotPasswordWin.show();
					}
				},{
					text: 'Change Password',
					formBind: false,
					handler: function(b,e) {
						var changepass = new Ext.form.FormPanel({
							id:'changeform',
							border:false,
							frame:true,
							width:350,
							bodyStyle: 'padding:10px',
							defaults: {width: 230},
							defaultType: 'textfield',
							monitorValid:true,
							items: [{
								fieldLabel: 'User Name',
								name: 'cpusername',
								allowBlank:false,
								minLength:6,
								maxLength:12,
								blankText: 'Enter your User Name.',
								minLengthText: 'User Name must be at least 6 characters'
							},
									{
								fieldLabel: 'Old Password',
								name: 'oldpassword',
								allowBlank:false,
								minLength: 6,
								maxLength: 12,
								blankText: 'Enter your current Password.',
								minLengthText: 'Old Password must be at least 6 characters'
								},
												{
								fieldLabel: 'New Password',
								name: 'newpassword',
								allowBlank:false,
								minLength: 6,
								maxLength: 12,
								blankText: 'Enter your New Password.',
								minLengthText: 'New Password must be at least 6 characters'
								},
									{
								fieldLabel: 'New Password',
								name: 'newpasswordre',
								allowBlank:false,
								minLength: 6,
								maxLength: 12,
								blankText: 'Re-Enter your New Password.',
								minLengthText: 'New Password must be at least 6 characters'
								}

								],
							buttons: [{
							text: 'Submit',
							formBind:true,
							handler: function(b,e) {
								if ($j("input[name=newpassword]").val() != $j("input[name=newpasswordre]").val()) {
										// display the error
									Ext.Msg.show({
										title: 'ERROR',
										msg: "New Passwords Must Be The Same",
										icon: Ext.MessageBox.ERROR,
										buttons: Ext.Msg.OK,
										modal: true
									});

									return;
								}
								var callbackproxy = function(dataFromServer) {
									authentication.changePassword(dataFromServer,widgetid);
								}

								var callMetaData={callback:callbackproxy};

								OrangeLeapWidget.changePassword(widgetid,$j("input[name=cpusername]").val(),$j("input[name=oldpassword]").val(),$j("input[name=newpassword]").val(),callMetaData);
							}
							}]
						});

						changePasswordWin = new Ext.Window({
							layout: 'fit',
							plain: false,
							width: 400,
							height: 275,
							title: 'Change Password',
							id: 'changePasswordWin',
							items: [
								changepass
							]
						});
						var otherWin = Ext.getCmp('forgotPasswordWin');
						if (otherWin && ! otherWin.hidden) {
							otherWin.hide();
						}
						changePasswordWin.show();
					}
				}]

			});


	//	var element = Ext.query('script[src$=required-field.js]')[0];
	//	var renderElement = element.parentNode;
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
