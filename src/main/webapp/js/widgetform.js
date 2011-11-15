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
	onSuccess: function() {
		Ext.Msg.show({
        	    title:'Success'
		    ,msg:'Form submitted successfully'
		    ,modal:true
		    ,icon:Ext.Msg.INFO
		    ,buttons:Ext.Msg.OK
		});
	},
	onFailure:function(form,action) {
		Ext.Msg.show({
		    title:'Error'
		    ,msg:action.result.message || action.response.responseText
		    ,modal:true
		    ,icon:Ext.Msg.ERROR
		    ,buttons:Ext.Msg.OK
		});
	},
	onGenerate: function() {
		var params = new Object();
		params.action = "update";
		params.widgettype= mydatastore.widgetForm.widgettype;
		params.customentitytype=mydatastore.widgetForm.customentitytype;

		for (var f = 0; f < mydatastore.reader.meta.fields.length; f++) {
		    params[mydatastore.reader.meta.fields[f].name] = mydatastore.widgetForm.findById(mydatastore.reader.meta.fields[f].name).getValue();
		}

		mydatastore.load({
			url: 'widgetform.ajax'
			,success: mydatastore.widgetForm.onSuccess
			,failure: mydatastore.widgetForm.onFailure
			,params: params
			,waitMsg: 'Generating Widget...'
		});
	},
	initComponent: function() {
		this.items = [ new Ext.form.ComboBox({
			id:'styleId',
			fieldLabel: 'Style',
			hiddenName: 'styleIdHidden',
			triggerAction: 'all',
			emptyText: 'Select Style...',
			displayField:'StyleName',
			valueField: 'Id',
			store:new Ext.data.JsonStore({
				id:'Id',
				root:'rows',
				totalProperty:'totalRows',
				fields: [
					{name:'Id',type:'string'},
					{name:'Style',type:'string'},
					{name: 'StyleName',type: 'string'}
				],
				url:'style.ajax',
				baseParams: {
					action: "list"
				}
			})
		})];

		this.buttons =  [{
			text: 'Generate',
			handler: this.onGenerate,
			align:'center',
			formBind: true
		}];
		WidgetForm.superclass.initComponent.call(this);

		var proxy = new Ext.data.HttpProxy( {url: 'widgetform.ajax'}); // Ext.data.HttpProxy

		var reader = new Ext.data.JsonReader({
			totalProperty: 'totalRows',
			successProperty: 'success',
			idProperty: 'id',
			root: 'rows',
			messageProperty: 'message'
		});

		mydatastore = new Ext.data.Store({
			metaData:true,
			reader:reader,
			proxy:proxy,
			autoSave:false,
			widgetForm: this,
			listeners: {
				'metachange':function(store,meta) {
					generateForm(store.widgetForm, store, meta);
				},
				'load':function(store,records,options) {
					var metaData = store.reader.meta.fields;
					var value = null;
					for (var m=0; m < metaData.length; m++) {
						if (metaData[m].type != 'boolean' && metaData[m].type != 'style') {
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
			this.buttons[0].hide();
		}
	}
});