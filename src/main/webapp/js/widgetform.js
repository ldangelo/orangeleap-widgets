WidgetForm = Ext.extend(Ext.form.FormPanel, {
	fldidx:  0,
	guid: null,
	widgettype: null,
	customentitytype: null,
    mydatastore: null,
    queryString: window.top.location.search.substring(1),
	id: 'form',
	border: false,
	frame: true,
	labelWidth: 100,
	buttonAlign: 'center',
	monitorValid: true,
	width: 900,
	trackResetOnLoad: true,
	getParameter: function (queryString, parameterName) {
		// Add "=" to the parameter name (i.e. parameterName=value)
		var parameterName = parameterName + "=";
		if ( queryString.length > 0 ) {
		    // Find the beginning of the string
		    begin = queryString.indexOf ( parameterName );
		    // If the parameter name is not found, skip it, otherwise return the value
		    if ( begin != -1 ) {
				// Add the length (integer) to the beginning
				begin += parameterName.length;
				// Multiple parameters are separated by the "&" sign
				end = queryString.indexOf ( "&" , begin );
				if ( end == -1 ) {
					end = queryString.length
				}
				// Return the string
				return unescape ( queryString.substring ( begin, end ) );
		    }
		    // Return "null" if no parameter has been found
		    return "null";
		}
	},
	checkSave: function(records, options, success) {
		if (success) {
			Ext.Msg.show({
	                title:'Success'
			    ,msg:'Your changes were successfully saved.'
			    ,modal:true
			    ,icon:Ext.Msg.INFO
			    ,buttons:Ext.Msg.OK
			});
		}
		else {
			Ext.Msg.show({
			    title:'Error'
			    ,msg:'Your changes could NOT be saved due to an error.  Please try again.'
			    ,modal:true
			    ,icon:Ext.Msg.ERROR
			    ,buttons:Ext.Msg.OK
			});
		}
	},
	onGenerate: function() {
		var params = new Object();
		params.action = "update";
		params.widgettype= mydatastore.widgetForm.widgettype;
		params.customentitytype=mydatastore.widgetForm.customentitytype;

		for (var f = 0; f < mydatastore.reader.meta.fields.length; f++) {
			var anElem = mydatastore.widgetForm.findById(mydatastore.reader.meta.fields[f].name);
			var aValue;
			if (anElem.getXType() == 'radiogroup') {
				var aRadioElem = anElem.getValue();
				aValue = aRadioElem && (aRadioElem.getRawValue() === 'true' || aRadioElem.getRawValue() === true);
			}
			else {
				aValue = anElem.getValue();
			}
			params[mydatastore.reader.meta.fields[f].name] = aValue;
		}

		mydatastore.load({
			url: 'widgetform.ajax'
			,callback: mydatastore.widgetForm.checkSave
			,params: params
			,waitMsg: 'Generating Widget...'
		});
	},
	initComponent: function() {
		this.items = [ new Ext.form.ComboBox({
			id: 'styleId',
			fieldLabel: 'Style',
			hiddenName: 'styleIdHidden',
			triggerAction: 'all',
			emptyText: 'Select Style...',
			displayField: 'StyleName',
			valueField: 'Id',
			lazyInit : false,
			forceSelection: true,
            selectOnFocus: true,
			mode : 'local',
			store : new Ext.data.JsonStore({
				fields : [ 'Id', 'Style', 'StyleName'],
				data: []
			})
		}),
			new Ext.form.ComboBox({
			id: 'javascriptId',
			fieldLabel: 'Javascript',
			hiddenName: 'javascriptIdHidden',
			triggerAction: 'all',
			emptyText: 'Select Javascript...',
			displayField: 'JavascriptName',
			valueField: 'javascriptId',
			lazyInit : false,
			forceSelection: true,
	        selectOnFocus: true,
			mode : 'local',
			store : new Ext.data.JsonStore({
				fields : [ 'javascriptId', 'Javascript', 'JavascriptName'],
				data: []
			})			
		})			
		];

		this.buttons =  [{
			text: 'Save',
			handler: this.onGenerate,
			align:'center',
			formBind: true
		}];
		WidgetForm.superclass.initComponent.call(this);

		var proxy = new Ext.data.HttpProxy({
			url: 'widgetform.ajax',
            listeners: {
                exception: function(proxy, type, action, options, response, arg) {
					if (response.getResponseHeader('errorMsg')) {
				        Ext.MessageBox.show({
				            'title': 'ERROR',
				            'icon': Ext.MessageBox.ERROR,
				            'buttons': Ext.MessageBox.OK,
				            'minWidth': 600,
				            'msg': response.getResponseHeader('errorMsg')
				        });
					}
                }
            }
		}); // Ext.data.HttpProxy

		var reader = new Ext.data.JsonReader({
			totalProperty: 'totalRows',
			successProperty: 'success',
			idProperty: 'id',
			root: 'rows',
			messageProperty: 'message'
		});

		mydatastore = new Ext.data.JsonStore({
			metaData:true,
			reader:reader,
			proxy:proxy,
			autoSave:false,
			widgetForm: this,
			listeners: {
				'exception': function(proxy, type, action, exception) {
					Ext.Msg.show({
						title:'Error'
							,msg:exception.reader.jsonData.message
							,modal:true
							,icon:Ext.Msg.ERROR
							,buttons:Ext.Msg.OK					
					});
                	Ext.fly('widgetWrapper').unmask();
				},
				'metachange':function(store,meta) {
					Ext.QuickTips.init();
					generateForm(store.widgetForm, store, meta);
				},
				'beforeload': function(store, options) {
					var wrapperElem = Ext.fly('widgetWrapper');
					if (wrapperElem) {
                	    wrapperElem.mask('Loading...', 'x-mask-loading');
					}
				},
				'load':function(store,records,options) {
					if (store.reader.meta.styles) {
						Ext.getCmp('styleId').store.loadData(store.reader.meta.styles);
					}
					if (store.reader.meta.javascripts) {
						Ext.getCmp('javascriptId').store.loadData(store.reader.meta.javascripts);
					}

					var metaData = store.reader.meta.fields;
					var value = null;
					for (var m=0; m < metaData.length; m++) {
						if (metaData[m].name == 'replaceTopContents') {
							value = records[records.length-1].get(metaData[m].name);
							var fld = store.widgetForm.getForm().findField(metaData[m].name);
							if (fld != null) {
								fld.setValue(value);
							}
						}
						else if (metaData[m].type == 'style') {
							value = records[records.length-1].get(metaData[m].name);
							if ( ! value) {
								value = '0';
							}
							Ext.getCmp('styleId').setValue(value);
						}
						else if (metaData[m].type == 'javascript') {
							value = records[records.length-1].get(metaData[m].name);
							if ( ! value) {
								value = '0';
							}
							Ext.getCmp('javascriptId').setValue(value);
						}
						else if (metaData[m].type == 'boolean') {
							value = records[records.length-1].get(metaData[m].name);
							var fld = store.widgetForm.getForm().findField(metaData[m].name);
							if (fld != null) {
								fld.setValue(value);
							}
						}
						else {
							value = records[records.length-1].get(metaData[m].name);
							value = value.replace(/&lt;/g,"<");
							value = value.replace(/&gt;/g,">");
							var fld = store.widgetForm.getForm().findField(metaData[m].name);
							if (fld != null) {
								fld.setValue(value);
							}
						}
					}
					store.widgetForm.doLayout();
					var wrapperElem = Ext.fly('widgetWrapper');
					if (wrapperElem) {
                	    wrapperElem.unmask();
					}
				}
			}
		});

		var guid = this.getParameter(this.queryString,'guid');
		if (guid == null) {
			mydatastore.load({
				url: 'widgetform.ajax',
				params: {
					action: 'create',
					widgettype: mydatastore.widgetForm.widgettype,
					customentitytype: mydatastore.widgetForm.customentitytype
				}
			});
		}
		else {
			mydatastore.load({
				url: 'widgetform.ajax',
				params: {
					action: 'read',
					widgettype: mydatastore.widgetForm.widgettype,
					customentitytype: mydatastore.widgetForm.customentitytype,
					guid: guid
				}
			});
			//
			// hide the 'Generate' button to keep someone from regenerating the code....
			//this.buttons[0].hide();
		}
	}
});