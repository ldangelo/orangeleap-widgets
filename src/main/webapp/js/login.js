var $j = jQuery.noConflict();

var authentication = {
	successurl:null,
    loginform:null,
    replaceTopContent:null,
  
	postToUrl: function(url, params, newWindow) {
		var form = $j('<form>');
		form.attr('action', url);
		form.attr('method', 'POST');

		if(newWindow){ form.attr('target', '_blank'); }

		var addParam = function(paramName, paramValue){
			var input = $j('<input type="hidden">');
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

    handleReturn: function(session,widgetid,successurl) {
		if (session == null) {
			this.handleError(widgetid,"Authentication Failed!");
		}
		else {
			if (Ext.get("remember").dom.value == "on")
			this.setCookie("sessionId",session.sessionId,5);
			else
			this.setCookie("sessionId",session.sessionId);

			if (successurl != null) {
			    var params = new Object();
			    params["constituentId"] = session.constituentId;
			    params["accountnumber"]= session.accountNumber;

			    if (this.replaceTopContent=='true')
				this.postToUrl(successurl, params,true);
			    else
				this.postToUrl(successurl, params,false);
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
    testCookies: function() {
    	this.setCookie("test","test",1);
    	
    	if (this.getCookie("test") == "") 
    		return false;
    	else
    		return true;
    },
    generateWidget: function(widgetid, successurl, replaceTopContent,referer) {
		sessionId = this.getCookie("sessionId");

		if (this.testCookies() == false) {
			// display the error
			Ext.Msg.show({
				title: 'ERROR',
				msg: "Cookies must be enabled.",
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK,
				modal: true,
			});
			return;
		}
		this.successurl = successurl;
		this.replaceTopContent = replaceTopContent;
		
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'under';

			this.loginform = new Ext.form.FormPanel({
				id: 'loginform',
				border:false,
				frame: true,
				title: 'Login',
				cls: 'widgetForm',
				buttonAlign: 'center',
				width: 350,
				defaults: {width: 230},
				defaultType: 'textfield',
				labelAlign: 'right',
				monitorValid: true,
				items: [{
					fieldLabel: 'User Name',
					name: 'username',
					allowBlank:false,
					minLength: 6,
					maxLength: 12,
					id: 'enterUserName',
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
						cls: 'olLink',
						autoEl: {
							tag: 'a',
							href: 'http://www.orangeleap.com/',
							html: 'Powered by Orange Leap'
						}
					}
				],
				fbar: [{
					text: 'Login',
					formBind:true,
					cls: 'mainButton',
					handler: function(b,e) {
						Ext.get('enterUserName').addClass('showWait');

						//
						// call authenticate through DWR
						var callbackproxy = function(dataFromServer) {
							authentication.handleReturn(dataFromServer,widgetid,successurl);
							Ext.get('enterUserName').removeClass('showWait');
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
							buttonAlign: 'center',
							monitorValid:true,
							items: [
								{
									fieldLabel: 'User Name',
									name: 'fpusername',
									id: 'forgotPasswordUserName',
									allowBlank: false,
									minLength: 6,
									maxLength: 12,
									blankText: 'Enter your User Name.',
									minLengthText: 'User Name must be at least 6 characters'
								}
							],
							buttons: [{
								text: 'Submit',
								formBind:true,
								handler: function(b,e) {
									Ext.get('forgotPasswordUserName').addClass('showWait');
									var callbackproxy = function(dataFromServer) {
										authentication.forgotPassword(dataFromServer, widgetid);
										Ext.get('forgotPasswordUserName').removeClass('showWait');
									}
									var callMetaData= { callback: callbackproxy };

									OrangeLeapWidget.forgotPassword(widgetid, $j("input[name=fpusername]").val(), callMetaData);
								}
							}]
						});

						var forgotPasswordWin = new Ext.Window({
							layout: 'fit',
							plain: false,
							modal: true,
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
							buttonAlign: 'center',
							bodyStyle: 'padding:10px',
							defaults: {width: 230},
							defaultType: 'textfield',
							monitorValid:true,
							items: [
								{
									fieldLabel: 'User Name',
									name: 'cpusername',
									id: 'changePasswordUserName',
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
									inputType: 'password',
									minLength: 6,
									maxLength: 12,
									blankText: 'Enter your current Password.',
									minLengthText: 'Old Password must be at least 6 characters'
								},
								{
									fieldLabel: 'New Password',
									name: 'newpassword',
									inputType: 'password',
									allowBlank:false,
									minLength: 6,
									maxLength: 12,
									blankText: 'Enter your New Password.',
									minLengthText: 'New Password must be at least 6 characters'
								},
								{
									fieldLabel: 'New Password',
									name: 'newpasswordre',
									inputType: 'password',
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
									Ext.get('changePasswordUserName').addClass('showWait');
									var callbackproxy = function(dataFromServer) {
										authentication.changePassword(dataFromServer,widgetid);
										Ext.get('changePasswordUserName').removeClass('showWait');
									}

									var callMetaData= { callback: callbackproxy };

									OrangeLeapWidget.changePassword(widgetid, $j("input[name=cpusername]").val(), $j("input[name=oldpassword]").val(), $j("input[name=newpassword]").val(), callMetaData);
								}
							}]
						});

						changePasswordWin = new Ext.Window({
							layout: 'fit',
							plain: false,
							modal: true,
							width: 400,
							height: 270,
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
				}
			]
		});

		this.loginform.render("widget");

		OrangeLeapWidget.updateViewCount(widgetid,referer);
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
		OrangeLeapWidget.updateErrorCount(widgetid,this.referer);

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
