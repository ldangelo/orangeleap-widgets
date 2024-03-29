Ext.ns('OrangeLeap');
var $j = jQuery.noConflict();

OrangeLeap.CustomEntity = Ext.extend(Ext.form.FormPanel, {
    cls: 'widgetForm',
    buttonAlign: 'center',
    monitorValid : true,
    widgetid : null,
    guid : null,
    loginurl : null,
    buttonLabel : null,
    mydatastore : null,
    referer : "Unknown",
    sessionId : null,
    args : null,
    successurl : null,
    picklistNameItemsMap: {},
    addresses: {},
    phones: {},
    emails: {},
    allowLogout: false,
    allowLogoutReset: false,
    replaceTopContent: null,
    timeout: 120,
    valueDelimiter: '\u00A7',
    parentFields: {},
    helpMsg: null,
    submitButton: null,
    showErrorMessage: function(message) {
	this.helpMsg.setValue("<span style=\"color:red;font-size:20px\">" + message + "</span>");    
	this.helpMsg.show();
    },
    showMessage: function(message) {
	this.helpMsg.setValue ("<span style=\"color:green;font-size:20px\">" + message + "</span>");    
	this.helpMsg.show(); 
    },
    postToUrl : function(url, params, replaceTopWindow) {
			var form = $j('<form>');
			form.attr('action', url);
			form.attr('method', 'POST');
			if (replaceTopWindow) {
				form.attr('target', '_top');
			}

			var addParam = function(paramName, paramValue) {
				var input = $j('<input type="hidden">');
				input.attr({
					'id' : paramName,
					'name' : paramName,
					'value' : paramValue
				});
				form.append(input);
			};

			// Params is an Array.
			if (params instanceof Array) {
				for ( var i = 0; i < params.length; i++) {
					addParam(i, params[i]);
				}
			}

			// Params is an Associative array or Object.
			if (params instanceof Object) {
				for ( var key in params) {
					addParam(key, params[key]);
				}
			}

			// Submit the form, then remove it from the page
			form.appendTo(document.body);
			form.submit();
			form.remove();
		},
		setCookie : function(c_name, value, expiredays) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expiredays);
			document.cookie = c_name + "=" + escape(value) + ( ! expiredays ? "" : ";expires=" + exdate.toUTCString());
		},
		populateArgs : function(form, args) {
			var referer = args.split('?');

			if (referer[1]) {
				var parms = referer[1].split('&');

				for (x = 0; x < parms.length; x++) {
					var keyval = parms[x].split('=');

					if (keyval[0] == 'id') {
						var f = form.findById("sponsorable_id");
						if (f) {
							f.setValue(keyval[1]);
						}
						continue; // skip id's
					}

					var f = form.findById(keyval[0]);
					if (f) {
						f.setValue(keyval[1]);
					}
				}
			}
		},
		populateWidget : function(constituent, table) {
			for (x in table.fields) {
				var fieldName = table.fields[x].customTableFieldName;
				if (fieldName) {
					if (fieldName.indexOf(".") != -1) {
						//
						// this is a sub object like primaryEmail,
						// etc...
						var obj = constituent[fieldName.substring(
								0, fieldName.indexOf("."))];
						$j("[name=" + table.fields[x].customTableFieldName + "]").val(obj[fieldName.substring(fieldName.indexOf(".") + 1, fieldName.length)]);
					}
					else {
						$j("[name=" + table.fields[x].customTableFieldName + "]").val(constituent[table.fields[x].customTableFieldName]);
					}
				}
			}
		},

		getCookie : function(c_name) {
			if (document.cookie.length > 0) {
				c_start = document.cookie.indexOf(c_name + "=");
				if (c_start != -1) {
					c_start = c_start + c_name.length + 1;
					c_end = document.cookie.indexOf(";", c_start);
					if (c_end == -1) {
						c_end = document.cookie.length;
					}
					return unescape(document.cookie.substring(c_start, c_end));
				}
			}
			return "";
		},
		onSuccess : function(f, a) {
		    var cfMap = a.result.data.customFieldMap.entry;

			if ( ! this.successurl || Ext.isEmpty(this.successurl)) {
				var user_message = null;
				for ( var f = 0; f < cfMap.length; f++) {
					if (cfMap[f].key == 'user_message') {
						user_message = cfMap[f].value.value;
						break;
					}
				}
				for ( var f = 0; f < cfMap.length; f++) {
					if (cfMap[f].key == 'customtablerowid') {
						this.mydatastore.getAt(0).set('customtablerow', parseInt(cfMap[f].value.value, 10));
						Ext.getCmp("form").form.findField("customtablerowid").setValue(cfMap[f].value.value);
						break;
					}
				}

				if ( ! user_message) {
					user_message = 'Your changes were successfully saved';
				}
				this.maskAccountNumbers();
			    this.showMessage(user_message);
			    this.submitButton.formBind=false;
			    this.submitButton.disable();
			    this.submitButton.disabled = true;
			}
			else {
				var params = new Object();

				for ( var f = 0; f < cfMap.length; f++) {
					params[cfMap[f].key] = cfMap[f].value.value;
				}

				this.maskAccountNumbers();

			    if (this.replaceTopContent == 'true') {
					this.postToUrl(this.successurl, params,true);
			    }
			    else {
					this.postToUrl(this.successurl, params,false);
			    }
			}
		},

		maskAccountNumbers: function() {
			try {
				var cardField = Ext.getCmp('form').form.findField('creditCardNumber');
				if ( ! cardField) {
					cardField = Ext.getCmp('form').form.findField('payment_info_paymentSource_creditCardNumber');
				}
				if (cardField) {
					cardField.el.dom.type = 'password';
				}
				var achField = Ext.getCmp('form').form.findField('ach_account');
				if ( ! achField) {
					achField = Ext.getCmp('form').form.findField('payment_info_paymentSource_achAccountNumber');
				}
				if (achField) {
					achField.el.dom.type = 'password';
				}
			}
			catch (exception) {}
		},

		onFailure : function(form, action) {
			var message;
			if (action.result || action.response) {
		message = action.result.message || action.response.responseText;
			}
			else if ( ! form.isValid()) {
				message = 'Please correct the errors that are in red';
			}
			else {
				message = 'An unspecified error occurred.  Please try again';
			}
		    this.showErrorMessage(message);
		},

		onSubmit : function() {
		    this.form.submitButton.disable();
			var customEntityForm = Ext.getCmp('form');
			customEntityForm.form.submit({
				url : 'customEntity.ajax?action=create&guid='
						+ customEntityForm.guid
						+ '&sessionId='
						+ customEntityForm.sessionId,
				scope : customEntityForm,
				success : customEntityForm.onSuccess,
				failure : customEntityForm.onFailure,
				params : {
					cmd : 'save',
					referer : customEntityForm.referer
				},
				waitMsg : 'Processing...'
			});
		},

		logout: function() {
			var logoutUrl = 'customEntity.ajax?action=logout&guid=' + this.guid + '&sessionId=' + this.sessionId;
	        document.cookie = 'sessionId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		    if (this.replaceTopContent == 'true') {
				top.location.href = logoutUrl;
		    }
		    else {
				window.location.href = logoutUrl;
		    }
		},

		logoutReset: function() {
	        document.cookie = 'sessionId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	        Ext.getCmp('form').form.reset();
	        location.reload();
		},

		initComponent : function() {
			Ext.QuickTips.init();

			var that = this;

			if ( this.guid == undefined) {
				this.showError("Guid ID is undefined");
			}
			if ( this.authenticate == undefined) {
				this.showError("Authenticate is undefined.");
			}
			if ( this.loginurl == undefined) {
				this.showError("Login URL is undefined.");
			}
			if ( this.buttonLabel == undefined) {
				this.showError("Button Label is undefined.");
			}

			if (this.replaceTopContent == undefined) {
				this.showError("Replace Top Content is undefined.")
			}
			this.sessionId = this.getCookie("sessionId");

			if (this.authenticate == true && (Ext.isEmpty(this.sessionId) || this.sessionId == '""')) {
				if (this.replaceTopContent == 'true') {
					top.location.href = this.loginurl;
				}
				else {
					window.location.href = this.loginurl;
				}
			}
			else {
				Ext.QuickTips.init();

				// invalid markers to sides
				Ext.form.Field.prototype.msgTarget = 'side';

				var config = {
					 defaultType:'textfield'
					,defaults:{anchor:'-24'}
					,monitorValid:true
				};

				Ext.apply(this, Ext.apply(this.initialConfig, config));


				// call parent
				OrangeLeap.CustomEntity.superclass.initComponent.apply(this, arguments);
				var readURL = 'customEntity.ajax?action=view&guid='
						+ this.guid
						+ '&sessionId='
						+ this.sessionId;
				if (this.param_pledge_id && this.param_pledge_id.length > 0) {
					readURL += '&pledge_id=' + this.param_pledge_id;
				}
				if (this.param_transaction_firstDistributionLineAmount && this.param_transaction_firstDistributionLineAmount.length > 0) {
					readURL += '&transaction_firstDistributionLineAmount=' + this.param_transaction_firstDistributionLineAmount;
				}
				if (this.param_gift_designation && this.param_gift_designation.length > 0) {
					readURL += '&gift_designation=' + this.param_gift_designation;
				}
				if (this.param_gift_motivation && this.param_gift_motivation.length > 0) {
					readURL += '&gift_motivation=' + this.param_gift_motivation;
				}
				if (this.param_gift_bank && this.param_gift_bank.length > 0) {
                    readURL += '&gift_bank=' + this.param_gift_bank;
                }
				var proxy = new Ext.data.HttpProxy({
					api : {
						read : readURL,
						create : 'customEntity.ajax?action=create&guid='
								+ this.guid
								+ '&sessionId='
								+ this.sessionId,
						update : 'customEntity.ajax?action=update&guid='
								+ this.guid
								+ '&sessionId='
								+ this.sessionId,
						destroy : 'customEntity.ajax?action=delete&guid='
								+ this.guid
								+ '&sessionId='
								+ this.sessionId
					},
					timeout : 120000
				});

				var reader = new Ext.data.JsonReader(
						{"success": true} );

				var writer = new Ext.data.JsonWriter({
					encode : true,
					writeAllFields : false
				});

				that.checkMultiPicklistValue = function(comboSuperSelect) {
					var aName = comboSuperSelect.getName();
					var value = comboSuperSelect.getValue();

					if ( ! Ext.isEmpty(value)) {
						var tokens = value.split(that.valueDelimiter);
					}


					if (that.parentFields[aName]) {
			    var childFields = that.parentFields[aName];
			    if (childFields) {
				var myForm = Ext.getCmp("form").form;
							for (var x = 0; x < childFields.length; x++) {
								var childObj = childFields[x];

								if ( ! Ext.isEmpty(childObj.parentFieldValue)) {
									if (Ext.isEmpty(value)) {
										myForm.findField(childObj.thisFieldName).hide();
										myForm.findField(childObj.thisFieldName).disable();
									}
									else {
										var show = false;
										for (var z = 0; z < tokens.length; z++) {
											if (tokens[z] == childObj.parentFieldValue) {
												show = true;
												break;
											}
										}
										if (show) {
											myForm.findField(childObj.thisFieldName).show();
											myForm.findField(childObj.thisFieldName).enable();
										}
										else {
											myForm.findField(childObj.thisFieldName).hide();
											myForm.findField(childObj.thisFieldName).disable();
										}
									}
								}
							}
			    }
					}
				};

				var createFieldLabel = function(aField) {
					return (aField.required ? '<span class="required">*</span>' : '') + aField.header;
				};

				this.mydatastore = new Ext.data.Store({
					form : this,
					metaData : true,
					reader : reader,
					writer : writer,
					proxy : proxy,
					autoSave : false,
					listeners : {
						'exception' : function(proxy, type, action, options, response, args) {
							if (window.console) {
					            console.log('EXCEPTION in entity, type = "' + type + '" action = "' + action +
					                    '" options.params = "' + (options && options.params ? Ext.encode(options.params) : '') +
					                    '" response.status = "' + response.status + '" response.statusText = "' + response.statusText + (args ? '" args = "' + Ext.encode(args) : '') + '"');
							}

							if (type == "remote") {
								// remote error: A valid response was returned from the server having successProperty === false.
								// This response might contain an error-message sent from the server.
								// For example, the user may have failed authentication/authorization or a database validation error occurred


								var errorCode = '';
								if (response.responseText) {
									errorCode = ' Error Code: ' + response.responseText;
								}

							    Ext.MessageBox.show({
								title: 'ERROR',
								icon: Ext.MessageBox.ERROR,
								buttons: Ext.MessageBox.OK,
								minWidth: 500,
								msg: 'The request could not be processed due to an error.  Please try again or contact the website administrator for help.' + errorCode
							    });

							}
						    else {
							if (response.statusText == "timeout" && response.readyState != 4) {
					                    Ext.MessageBox.show({
								'title': 'Error',
								'icon': Ext.MessageBox.ERROR,
								'buttons': Ext.MessageBox.OK,
								'msg': 'The request could not be processed because the response timed out.  Please try again or contact the website administrator for help.'
					                    });
							}
							else if (response.statusText != "abort") {
						    // response error: An invalid response from the server was returned: either 404, 500
						    // or the response meta-data does not match that defined in the DataReader
						    var errorCode = '';
						    if (response.status || response.statusText) {
							errorCode = ' Error Code: ' + (response.status ? response.status + ' ' : ' ') + (response.statusText ? response.statusText : '');
							}
					                Ext.MessageBox.show({
					                    title: 'ERROR',
					                    icon: Ext.MessageBox.ERROR,
					                    buttons: Ext.MessageBox.OK,
					                    minWidth: 500,
					                    msg: 'An invalid response was returned by the server.  Please try again or contact the website administrator for help.' + errorCode
					                });
						    }
					}
						},
						'metachange' : function(store, meta) {
							that.parentFields = {};
							var fields = meta.fields;
							var fieldset = null;
							var fieldsectioncount = 0;
							var fieldsectionindex = 0;

							that.picklistNameItemsMap = {};
							if (meta.picklistNameItems) {
								for (var picklistName in meta.picklistNameItems) {
									that.picklistNameItemsMap[picklistName] = meta.picklistNameItems[picklistName];
								}
							}
							if (meta.allowLogout) {
								that.allowLogout = meta.allowLogout;
							}
							if (meta.allowLogoutReset) {
								that.allowLogoutReset = meta.allowLogoutReset;
							}
							that.addresses = {};
							if (meta.addresses) {
								that.addresses = meta.addresses;
							}
							that.phones = {};
							if (meta.phones) {
								that.phones = meta.phones;
							}
							that.emails = {};
							if (meta.emails) {
								that.emails = meta.emails;
							}

						    //
						    // add a message field to use
						    this.form.helpMsg = new Ext.form.DisplayField();
						    this.form.helpMsg.id = "helpMsg";
						    this.form.helpMsg.name = "helpMsg";
//						    this.form.helpMsg.value = "Your help message goes here!";

						    this.form.superclass().add.call(this.form, this.form.helpMsg);

							for ( var f = 0; f < fields.length; f++) {
								if ( ! Ext.isEmpty(fields[f].parentFieldName)) {
									var childFields = that.parentFields[fields[f].parentFieldName];
									if ( ! childFields) {
										childFields = [];
										that.parentFields[fields[f].parentFieldName] = childFields;
									}

									var childObj = { thisFieldName: fields[f].name };

									if ( ! Ext.isEmpty(fields[f].parentFieldValue)) {
										childObj.parentFieldValue = fields[f].parentFieldValue;
								    }
								    childFields.push(childObj);
								}

								if (fields[f].hidden) {
									if ( ! this.form.findById(fields[f].name)) {
										var field = new Ext.form.Hidden();
										field.id = fields[f].name;
										if (fields[f].type == 'picklist') {
											field.id = fields[f].name + 'combo';
										}
										field.name = fields[f].name;
										field.dataIndex = fields[f].name;
										field.fieldLabel = createFieldLabel(fields[f]);
										field.store = store;
										field.border = false;
										this.form.superclass().add.call(this.form, field);
									}
								}
								else if (fields[f].type == 'text'
									 || fields[f].type == 'password'
									 || fields[f].type == 'date'
									 || fields[f].type == 'integer'
									 || fields[f].type == 'number'
									 || fields[f].type == 'money'
									 || fields[f].type == 'checkbox') {
									var field = null;
									if (fields[f].type == 'date') {
										field = new Ext.form.DateField();
									}
									else if (fields[f].type == 'integer' || fields[f].type == 'number') {
										field = new Ext.form.NumberField();
									}
									else if (fields[f].type == "checkbox") {
										field = new Ext.form.Checkbox();
										field.inputValue = "true";
									}
									else {
										field = new Ext.form.TextField();
									}
									field.id = fields[f].name;
									field.name = fields[f].name;
									field.dataIndex = fields[f].name;
									field.fieldLabel = createFieldLabel(fields[f]);
									field.store = store;
									field.border = false;

									if (fields[f].type == 'password') {
										field.inputType = 'password';
									}

									if (fields[f].type == 'money') {
	//										field.maskRe=/[0-9\$\.]/;
	//										field.regex= /\d{1,3},?(\d{3},?)*\d{3}(\.\d{1,3})?|\d{1,3}(\.\d{1,2})?/;
											field.validateOnBlur=true;
											field.minValue=1;
											field.allowNegative=false;
											field.decimalPrecision=2;
											field.decimalSeperator=".";
											field.on('change', function(object, newValue, oldValue){
														newValue = Ext.util.Format.number(newValue,"0.00");
	//													if(newValue == '$NaN.00')
															//object.setValue(oldValue);
	//													else
															object.setValue(newValue);
													});

									}

									if ( ! Ext.isEmpty(fields[f].regEx)) {
										field.regex = new RegExp(fields[f].regEx);
										field.regexText = fields[f].regExExample;
									}

									if (fields[f].required) {
										field.allowBlank = false;
										field.blankText = "Enter a " + fields[f].header;
									}

									if ( ! fieldset) {
										this.form.superclass().add.call(this.form, field);
									}
									else {
										if (fieldsectionindex > (fieldsectioncount - 1) / 2) {
											col2.add(field);
										}
										else {
											col1.add(field);
										}
										if (!fields[f].hidden) {
											fieldsectionindex++;
										}
									}
								}
								else if (fields[f].type == 'comments') {
									var field = new Ext.form.TextArea();
									field.id = fields[f].name;
									field.name = fields[f].name;
									field.dataIndex = fields[f].name;
									field.fieldLabel = createFieldLabel(fields[f]);
									field.store = store;
									field.border = false;
									field.width = 350;
									field.height = 150;
									if (fields[f].required) {
										field.allowBlank = false,
										field.blankText = "Enter a " + fields[f].header;
									}
									if ( ! fieldset) {
										this.form.superclass().add.call(this.form, field);
									}
									else {
										if (fieldsectionindex > (fieldsectioncount - 1) / 2) {
											col2.add(field);
										}
										else {
											col1.add(field);
										}
										if (!fields[f].hidden) {
											fieldsectionindex++;
										}
									}
								}
								else if (fields[f].type == 'section') {
									if (fieldset) {
										this.form.superclass().add.call(this.form, fieldset);
									}
									var col1 = new Ext.Panel({
										columnWidth : '.50',
										layout : 'form',
										defaults : {
											anchor : '100%'
										},
										bodyStyle : 'padding:0 18px 0 0',
										items : []
									});
									var col2 = new Ext.Panel({
										columnWidth : '.50',
										layout : 'form',
										defaults : {
											anchor : '100%'
										},
										bodyStyle : 'padding:0 18px 0 0',
										items : []
									});
									var panel = new Ext.Panel({
										columnWidth : 0.5,
										layout : 'column',
										bodyStyle : 'padding:0 18px 0 0',
										items : [ col1, col2 ]
									});

									fieldset = new Ext.form.FieldSet({
										id : fields[f].name,
										title : fields[f].header,
										labelAlign : 'left',
										items : [ panel ]
									});

									// calculate the number of
									// fields in this section
									var start = f + 1;
									for (fieldsectioncount = 0; start < fields.length && fields[start].type != 'section'; fieldsectioncount++) {
										if (fields[start].type == 'custom-table-reference' || fields[start].type == 'multi-custom-table-reference' || fields[start].hidden) {
											fieldsectioncount--;
										}
									
										start++;										
									}
									fieldsectionindex = 0;

								}
								else if (fields[f].type == 'picklist') {
									var comboEventHandler = function(comboBox) {
										var value = comboBox.getValue();
	                                    var childFields = that.parentFields[comboBox.getName()];
	                                    if (childFields) {
	                                        var myForm = Ext.getCmp("form").form;
											for (var x = 0; x < childFields.length; x++) {
												var childObj = childFields[x];

												if ( ! Ext.isEmpty(childObj.parentFieldValue)) {
													var show = (value == childObj.parentFieldValue);
													if (show) {
														myForm.findField(childObj.thisFieldName).show();
														myForm.findField(childObj.thisFieldName).enable();
													}
													else {
														myForm.findField(childObj.thisFieldName).hide();
														myForm.findField(childObj.thisFieldName).disable();
													}
												}
											}
	                                    }
									};
									var comboConfig = new Ext.form.ComboBox({
									    id : fields[f].name + 'combo',
									    dataIndex : fields[f].name,
									    valueField : 'Name',
									    triggerAction : 'all',
									    hiddenName : fields[f].name,
									    displayField : 'Description',
									    forceSelection: true,
									    selectOnFocus: true,
									    lazyInit : false,
									    mode : 'local',
									    emptyText : 'Select ' + fields[f].header + '...',
									    store : new Ext.data.JsonStore({
										fields : [ 'Name', 'Description'],
										data: that.picklistNameItemsMap[fields[f].picklistId]
									    }),
									    fieldLabel : createFieldLabel(fields[f]),
									    listeners: {
										select: function(comboBox, record, index) {
										    comboEventHandler(comboBox);
										},
										change: function(comboBox, newValue, oldValue) {
										    comboEventHandler(comboBox);
										}
									    }
									});
									var oldFilterFunc = comboConfig.store.filter;
									comboConfig.store.filter = function(field, query) {
										oldFilterFunc.call(this, 'Description', query, false, false); // allow case-insensitive filtering of combobox records
									};

									if (fields[f].required) {
										comboConfig.allowBlank = false;
										comboConfig.blankText = "Enter a " + fields[f].header;
									}
									if ( ! fieldset) {
										this.form.superclass().add.call(this.form, comboConfig);
									}
									else {
										if (fieldsectionindex > (fieldsectioncount - 1) / 2) {
											col2.add(comboConfig);
										}
										else {
											col1.add(comboConfig);
										}
										if (!fields[f].hidden) {
											fieldsectionindex++;
										}
									}
								}
								else if (fields[f].type == 'multi-picklist') {
									var comboConfig = new Ext.ux.form.SuperBoxSelect({
										id : fields[f].name, // + 'combo',
										name: fields[f].name,
										valueDelimiter: that.valueDelimiter,
										triggerAction: 'all',
										queryDelay: 0,
	//									readOnly:true,
	//									renderFieldBtns: false,
	//									supressClearValueRemoveEvents: true,
	//									dataIndex : fields[f].name,
										valueField : 'Name',
	//									hiddenName : fields[f].name,
										displayField : 'Description',
	//									forceSelection : true,
	//									lazyInit : false,
										mode : 'local',
										emptyText : 'Select ' + fields[f].header + '...',
										store : new Ext.data.JsonStore({
											fields : [ 'Name', 'Description'],
											data: that.picklistNameItemsMap[fields[f].picklistId]
										}),
										fieldLabel : createFieldLabel(fields[f]),
										listeners: {
											additem: function(comboSuperSelect, addedValue, record) {
												that.checkMultiPicklistValue(comboSuperSelect);
											},
											removeitem: function(comboSuperSelect, removedValue, record) {
												that.checkMultiPicklistValue(comboSuperSelect);
											}
										}
									});

									if (fields[f].required) {
										comboConfig.allowBlank = false;
										comboConfig.blankText = "Enter a " + fields[f].header;
									}
									if ( ! fieldset) {
										this.form.superclass().add.call(this.form, comboConfig);
									}
									else {
										if (fieldsectionindex > (fieldsectioncount - 1) / 2) {
											col2.add(comboConfig);
										}
										else {
											col1.add(comboConfig);
										}

									    if (!fields[f].hidden) {
											fieldsectionindex++;
										}										
									}
								} else if (fields[f].type == 'entity-reference') {
									var comboEventHandler = function(comboBox) {
										var value = comboBox.getValue();
	                                    var childFields = that.parentFields[comboBox.getName()];
	                                    if (childFields) {
	                                        var myForm = Ext.getCmp("form").form;
											for (var x = 0; x < childFields.length; x++) {
												var childObj = childFields[x];

												if ( ! Ext.isEmpty(childObj.parentFieldValue)) {
													var show = (value == childObj.parentFieldValue);
													if (show) {
														myForm.findField(childObj.thisFieldName).show();
														myForm.findField(childObj.thisFieldName).enable();
													}
													else {
														myForm.findField(childObj.thisFieldName).hide();
														myForm.findField(childObj.thisFieldName).disable();
													}
												}
											}
	                                    }
									};
									
									var dataSource = null;
									if (fields[f].referencedEntityType == 'address') {
										dataSource = that.addresses
									} else if (fields[f].referencedEntityType == 'phone') {
										dataSource = that.phones
									} else if (fields[f].referencedEntityType == 'email') {
										dataSource = that.emails
									} else if (fields[f].referencedEntityType == 'paymentSource') {
										dataSource = that.paymentSources
									}
									
									if (dataSource != null) {
										var comboConfig = new Ext.form.ComboBox({
										    id : fields[f].name + 'combo',
										    dataIndex : fields[f].name,
										    valueField : 'Name',
										    triggerAction : 'all',
										    hiddenName : fields[f].name,
										    displayField : 'Description',
										    forceSelection: true,
										    selectOnFocus: true,
										    lazyInit : false,
										    mode : 'local',
										    emptyText : 'Select ' + fields[f].header + '...',
										    store : new Ext.data.JsonStore({
											fields : [ 'Name', 'Description'],
											data: dataSource
										    }),
										    fieldLabel : createFieldLabel(fields[f]),
										    listeners: {
											select: function(comboBox, record, index) {
											    comboEventHandler(comboBox);
											},
											change: function(comboBox, newValue, oldValue) {
											    comboEventHandler(comboBox);
											}
										    }
										});
										var oldFilterFunc = comboConfig.store.filter;
										comboConfig.store.filter = function(field, query) {
											oldFilterFunc.call(this, 'Description', query, false, false); // allow case-insensitive filtering of combobox records
										};
	
										if (fields[f].required) {
											comboConfig.allowBlank = false;
											comboConfig.blankText = "Enter a " + fields[f].header;
										}
										
									} else {
										// No data source - just create a text box
									    fields[f].type = "text";
										comboConfig = new Ext.form.TextField();
										comboConfig.id = fields[f].name;
										comboConfig.name = fields[f].name;
										comboConfig.dataIndex = fields[f].name;
										comboConfig.fieldLabel = createFieldLabel(fields[f]);
										comboConfig.store = store;
										comboConfig.border = false;
									}
									
									if ( ! fieldset) {
										this.form.superclass().add.call(this.form, comboConfig);
									}
									else {
										if (fieldsectionindex > (fieldsectioncount - 1) / 2) {
											col2.add(comboConfig);
										}
										else {
											col1.add(comboConfig);
										}
										if (!fields[f].hidden) {
											fieldsectionindex++;
										}
									}
								}
							}		
								
							if (fieldset) {
								this.form.superclass().add.call(this.form, fieldset);
							}

							var btnConfig = {
								text : this.form.buttonLabel,
								handler : this.form.onSubmit,
								cls: 'mainButton',
								align : 'center',
								formBind : true,
								scope : this
							};

							var orangeLeapLinkConfig = {
								xtype : "box",
								cls: 'olLink',
								autoEl : {
									tag : 'a',
									href : 'http://www.orangeleap.com/',
									html : 'Powered by Orange Leap'
								}
							};
							this.form.submitButton = this.form.superclass().addButton.call(this.form, btnConfig, this.form.onSubmit, this.form);
							this.form.superclass().add.call(this.form, orangeLeapLinkConfig);

							if (this.form.allowLogout === true) {
								var logoutLinkConfig = {
									xtype : 'box',
									cls: 'logoutLink',
									autoEl : {
										id: 'olLogoutLink',
										tag : 'a',
										href : 'javascript:void(0)',
										html : 'Logout'
									}
								};
								this.form.superclass().add.call(this.form, logoutLinkConfig);
							}
							else if (this.form.allowLogoutReset === true) {
                                var logoutResetLinkConfig = {
                                    xtype : 'box',
                                    cls: 'logoutLink',
                                    autoEl : {
                                        id: 'olLogoutResetLink',
                                        tag : 'a',
                                        href : 'javascript:void(0)',
                                        html : 'Logout'
                                    }
                                };
                                this.form.superclass().add.call(this.form, logoutResetLinkConfig);
                            }

							// apply config
							this.form.superclass().render.call(this.form, this.form.widgetid);
							if (this.form.allowLogout === true) {
								var aForm = this.form;
								Ext.get('olLogoutLink').on('click', function() {
									aForm.logout.call(aForm);
								});
							}
							else if (this.form.allowLogoutReset === true) {
								var aForm = this.form;
								Ext.get('olLogoutResetLink').on('click', function() {
									aForm.logoutReset.call(aForm);
								});
							}
						},
						'load' : function(store, records, options) {
							var metaData = store.reader.meta.fields;
							var value = null;
							for ( var m = 0; m < metaData.length; m++) {
								if (metaData[m].type != 'section') {
									value = records[0].get(metaData[m].name);

									if (value !== '') {
										if (metaData[m].type == 'picklist') {
											if (this.form.findById(metaData[m].name + 'combo')) {
												this.form.findById(metaData[m].name + 'combo').setValue(value);
											}
										} else if (metaData[m].type == 'entity-reference' && !metaData[m].hidden) {
											if (this.form.findById(metaData[m].name + 'combo')) {
												this.form.findById(metaData[m].name + 'combo').setValue(value);
											}
										} 
										else if (metaData[m].type == 'checkbox'){
											this.form.findById(metaData[m].name).setValue(value);
											this.form.findById(metaData[m].name).checked = value;
										}
										else {
											if (this.form.findById(metaData[m].name)) {
												if (typeof(value) === 'string') {
													value = value.replace('&#167;','\u00A7');
												}
												this.form.findById(metaData[m].name).setValue(value);
											}
										}
									}
								}
							}
							if (this.form.args) {
								this.form.populateArgs(this.form, this.form.args);
							}
							if (this.form.parentFields) {
								for (var parentFieldName in this.form.parentFields) {
						            var aParentElem = this.form.form.findField(parentFieldName);
						            if (aParentElem) {
						                var xType = aParentElem.getXType();
						                if (xType == 'combo') {
							                aParentElem.fireEvent('select', aParentElem)
						                }
						                else if (xType == 'superboxselect') {
			                                this.form.checkMultiPicklistValue(aParentElem);
						                }
						            }
								}
							}

							if (this.form.allowLogoutReset === true && Ext.get('olLogoutResetLink')) {
								var firstNameElem = this.form.form.findField('first_name');
								var lastNameElem = this.form.form.findField('last_name');

								if ( ! firstNameElem) {
									firstNameElem = this.form.form.findField('constituent_firstName');
								}
								if ( ! lastNameElem) {
									lastNameElem = this.form.form.findField('constituent_lastName');
								}

								if (firstNameElem || lastNameElem) {
									var firstNameValue = firstNameElem ? firstNameElem.getValue() : '';
									var lastNameValue = lastNameElem ? lastNameElem.getValue() : '';

									if (Ext.isEmpty(firstNameValue) && Ext.isEmpty(lastNameValue)) {
										Ext.get('olLogoutResetLink').hide();
									}
								}
							}

							Ext.get('loading').remove();
							Ext.get('loading-mask').fadeOut({
								remove : true
							});

							// call the afterWidgetDataLoaded function if it exists (would be defined in the custom javascript for the widget)
							if (typeof afterWidgetDataLoaded == 'function') {
								afterWidgetDataLoaded();
							}
						}
					}
				});

				OrangeLeapWidget.updateViewCount(this.guid, this.referer);

				this.mydatastore.load();
			}
		},
		showError : function(str) {
			OrangeLeapWidget.updateErrorCount(this.guid, this.referer);
		    this.showErrorMessage(str);
		}
});
Ext.reg('customentity', OrangeLeap.CustomEntity);
