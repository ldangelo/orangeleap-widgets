Ext.ns('OrangeLeap');
var $j = jQuery.noConflict();


OrangeLeap.CustomEntity = Ext.extend(Ext.form.FormPanel, {
    widgetid:null,
    guid:null,
    loginurl:null,
    buttonLabel:null,
    mydatastore : null,
//    form:null,
    constituentid:null,

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
    
    getCookie:function(c_name)
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
    onSuccess:function() {
	Ext.Msg.show({
            title:'Success'
            ,msg:'Form submitted successfully'
            ,modal:true
            ,icon:Ext.Msg.INFO
            ,buttons:Ext.Msg.OK
	});
    },
    
    onFailure: function(form,action) {
	Ext.Msg.show({
             title:'Error'
            ,msg:action.result.message || action.response.responseText
            ,modal:true
            ,icon:Ext.Msg.ERROR
            ,buttons:Ext.Msg.OK
	});
    },
    
    onSubmit:function() {
	this.getForm().submit({
            url: 'customEntity.ajax?action=create&guid=' + this.guid + '&constituentid=' + this.constituentid
            ,scope:this
            ,success:this.onSuccess
            ,failure:this.onFailure
            ,params:{cmd:'save'}
            ,waitMsg:'Saving...'
        });
    },
    constructor:function(config) {
	config = config || {};
	config.listeners = config.listeners || {}
	Ext.applyIf(config.listeners, {
	});
	OrangeLeap.CustomEntity.superclass.constructor.call(this,config);
    },
    initComponent:function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'under';
	
	if (this.guid == null)
	    this.showError("Guid id undefinded");
	if (this.authenticate == null)
	    this.showError("Authenticate is undefined");
	if (this.loginurl == null)
	    this.showError("Login URL is undefined");
	if (this.buttonLabel == null)
	    this.showError("Button Label is undefined");
	
	this.constituentid = this.getCookie("constituentId");
	
	if (this.authenticate == true && this.constituentid == "") {
	    window.location = this.loginurl;
	}
	OrangeLeap.CustomEntity.superclass.initComponent.call(this);

	var proxy = new Ext.data.HttpProxy({
	    api: {
		read: 'customEntity.ajax?action=view&guid=' + this.guid + '&constituentid=' + this.constituentid,
		create: 'customEntity.ajax?action=create&guid=' + this.guid+ '&constituentid=' + this.constituentid,
		update: 'customEntity.ajax?action=update&guid=' + this.guid + '&constituentid=' + this.constituentid,
		destroy: 'customEntity.ajax?action=delete&guid=' + this.guid+ '&constituentid=' + this.constituentid
	    }
	});
	
	var reader = new Ext.data.JsonReader({
	    totalProperty: 'totalRows',
	    successProperty: 'success',
	    idProperty: 'id',
	    root: 'rows',
	    messageProperty: 'message'
	});
	
	var writer = new Ext.data.JsonWriter({
	    encode:true,
	    writeAllFields:false
	});
	
	this.mydatastore = new Ext.data.Store({
	    form:this,
	    metaData:true,
	    reader:reader,
	    writer:writer,
	    proxy:proxy,
	    autoSave:false,
	    listeners: {
		'metachange':function(store,meta) {
		    var fields = meta.fields;
		    var fieldset = null;
		    for (var f=0;f < fields.length; f++) {
			if (fields[f].hidden == true) {
			    
			    var field = new Ext.form.Hidden();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.dataIndex = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = store;
			    field.border = false;
			    if (fieldset == null) 
				this.form.superclass().add.call(this.form,field);
			    else
				fieldset.add(field);
			    
			} else if (fields[f].type == 'text' || fields[f].type == 'date' || fields[f].type == 'integer' || fields[f].type == 'number') {
			    var field = new Ext.form.TextField();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.dataIndex = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = store;
			    field.border = false;
			    field.value='';
			    if (fields[f].required == true) {
				field.allowBlank=false,
				field.blankText="Enter a " + fields[f].header;
			    }
			    if (fieldset == null)
				this.form.superclass().add.call(this.form,field);
			    else
				fieldset.add(field);
			} else if (fields[f].type == 'comments') {
			    var field = new Ext.form.TextArea();
			    field.id = fields[f].name;
			    field.name = fields[f].name;
			    field.dataIndex = fields[f].name;
			    field.fieldLabel = fields[f].header;
			    field.store = store;
			    field.border = false;
			    field.width = 350;
			    field.height = 150;
			    if (fields[f].required == true) {
				field.allowBlank=false,
				field.blankText="Enter a " + fields[f].header;
			    }
			    if (fieldset == null)
				this.form.superclass().add.call(this.form,field);
			    else
				fieldset.add(field);
			} else if (fields[f].type == 'section') {
			    if (fieldset != null)
				this.form.superclass().add.call(this.form,fieldset);
			    fieldset = new Ext.form.FieldSet({
				id:fields[f].name,
				title:fields[f].header,
				labelAlign:'left',
				items:[]
			    });
			} else if (fields[f].type == 'picklist') {
			    var comboConfig = new Ext.form.ComboBox({
				id:fields[f].name + 'combo',
				dataIndex : fields[f].name,
				valueField:'Name',
				triggerAction:'all',
				hiddenName:fields[f].name ,
				displayField:'Name',
				forceSelection:true,
				emptyText: 'Select ' + fields[f].header + '...',
				store:new Ext.data.JsonStore({
				    id:'Name',
				    root:'rows',
				    totalProperty:'totalRows',
				    fields: [
					{name:'Name',type:'string'},
					{name:'Description',type:'string'}
				    ],
				    url:'picklistItems.json',
				    baseParams: {
					guid: this.form.guid,
					picklistname: fields[f].picklistId
				    }
				}),
				fieldLabel:fields[f].header
			    });
			    if (fields[f].required == true) {
				comboConfig.allowBlank=false,
				comboConfig.blankText="Enter a " + fields[f].header;
			    }
			    if (fieldset == null) 
				this.form.superclass().add.call(this.form,field);
			    else
				fieldset.add(comboConfig);
			}
		    }
		    
		    if (fieldset != null)
			this.form.superclass().add.call(this.form,fieldset);

		    var btnConfig = { 
			text: this.form.buttonLabel,
			handler: this.form.onSubmit,
			align: 'center',
			formBind:true
		    };

		    this.form.superclass().addButton.call(this.form,btnConfig,this.form.onSubmit,this.form);
		    
		    var element = Ext.query('script[src$=required-field.js]')[0];
		    var renderElement = element.parentNode;
		    this.form.superclass().render.call(this.form,this.form.widgetid);
		},
	    'load': function(store,records,options) {
		    var metaData = store.reader.meta.fields;
		    var value = null;
		    for (var m=0; m < metaData.length; m++) {

			if (metaData[m].type != 'section') {
			    value = records[0].get(metaData[m].name);
			    
			    if (metaData[m].type == 'picklist') 
				this.form.findById(metaData[m].name + 'combo').setValue(value);
			    else
				this.form.findById(metaData[m].name).setValue(value);
			}
		    }
		    Ext.get('loading').remove();
		    Ext.get('loading-mask').fadeOut({remove:true});
	    }}
	});
	
	
	Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
	    this.showError(res.response.Text);
	});
	
	this.mydatastore.load();
    },
    showError: function(str) {
    	Ext.Msg.show({
    	    title: 'ERROR',
    	    msg: str,
    	    icon: Ext.MessageBox.ERROR,
    	    buttons: Ext.Msg.OK
    	});
    }
    
});
Ext.reg('customentity', OrangeLeap.CustomEntity);