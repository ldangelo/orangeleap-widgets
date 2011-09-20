Ext.ns('OrangeLeap');
var $j = jQuery.noConflict();


OrangeLeap.CustomEntity = Ext.extend(Ext.form.FormPanel, {
    widgetid:null,
    guid:null,
    loginurl:null,
    buttonLabel:null,
    mydatastore : null,
    referer:"Unknown",
//    form:null,
    sessionId:null,
    args: null,
    successurl: null,
postToUrl: function(url, params, newWindow)
{
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
    setCookie: function(c_name,value,expiredays)
    {
	var exdate=new Date();
	exdate.setDate(exdate.getDate()+expiredays);
	document.cookie=c_name+ "=" +escape(value)+
	    ((expiredays==null) ? "" : ";expires="+exdate.toUTCString());
    },
    populateArgs: function(form,args)  {
	var referer = args.split('?');
	var parms = referer[1].split('&');

	for (x in parms) {
	    if (x == 'remove') return;

	    var keyval = parms[x].split('=');

	    if (keyval[0] == 'id') {
	    	var f = form.findById("sponsorable_id");
	    	if (f!=null) {
	    		f.setValue(keyval[1]);
	    	}
	    	continue; //skip id's
	    }

	    var f = form.findById(keyval[0]);
	    if (f != null)
		f.setValue(keyval[1]);
	}
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
    onSuccess:function(f,a) {
    	var cfMap = a.result.data.customFieldMap.entry;

    	if (this.successurl == null || this.successurl == '') {
	    var user_message = null;
	    for (var f=0; f < cfMap.length; f++) {
		if (cfMap[f].key == 'user_message') {
		    user_message = cfMap[f].value.value;
		    break;
		}
	    }
	    
	    if (user_message == null) user_message = 'Form submitted successfully';

    		Ext.Msg.show({
    			title:'Success'
    				,msg:user_message
    				,modal:true
    				,icon:Ext.Msg.INFO
    				,buttons:Ext.Msg.OK
    		});
    	} else {
    		var params = new Object();
    		
    		for (var f=0; f < cfMap.length; f++) {
    			params[cfMap[f].key] = cfMap[f].value.value;
    		}
		this.postToUrl(this.successurl,params);    		
    	}
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
            url: 'customEntity.ajax?action=create&guid=' + this.guid + '&sessionId' + this.sessionId
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

	this.sessionId = this.getCookie("sessionId");

	if (this.authenticate == true && this.sessionId == "") {
	    window.location = this.loginurl;
	    return;
	}
	OrangeLeap.CustomEntity.superclass.initComponent.call(this);

	var proxy = new Ext.data.HttpProxy({
	    api: {
		read: 'customEntity.ajax?action=view&guid=' + this.guid + '&sessionId=' + this.sessionId,
		create: 'customEntity.ajax?action=create&guid=' + this.guid+ '&sessionId=' + this.sessionId,
		update: 'customEntity.ajax?action=update&guid=' + this.guid + '&sessionId=' + this.sessionId,
		destroy: 'customEntity.ajax?action=delete&guid=' + this.guid+ '&sessionId=' + this.sessionId
	    },
	    timeout: 120000
	});

	var reader=new Ext.data.JsonReader();

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
		    var fieldsectioncount = 0;
		    var fieldsectionindex = 0;

		    for (var f=0;f < fields.length; f++) {
			if (fields[f].hidden == true) {

			    if (this.form.findById(fields[f].name) == null) {
				var field = new Ext.form.Hidden();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.dataIndex = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = store;
				field.border = false;
			    //if (fieldset == null)
				this.form.superclass().add.call(this.form,field);
			    }
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
			    else {
				if (fieldsectionindex >= fieldsectioncount/2)
				    col2.add(field);
				else
				    col1.add(field);
				fieldsectionindex ++;
			    }
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
			    else {
				if (fieldsectionindex > (fieldsectioncount-1)/2)
				    col2.add(field);
				else
				    col1.add(field);
				fieldsectionindex ++;
			    }
			} else if (fields[f].type == 'section') {
			    if (fieldset != null)
				this.form.superclass().add.call(this.form,fieldset);

			    var col1 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]});
			    var col2 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]})
			    var panel = new Ext.Panel({ columnWidth:0.5,layout:'column', bodyStyle:'padding:0 18px 0 0',items:[col1,col2]});

			    fieldset = new Ext.form.FieldSet({
				id:fields[f].name,
				title:fields[f].header,
				labelAlign:'left',
				items:[panel]
			    });

			    // calculate the number of fields in this section
			    var start = f+1;
			    for (fieldsectioncount = 0; start < fields.length && fields[start].type != 'section'; fieldsectioncount++) start++;
			    fieldsectionindex =0;

			} else if (fields[f].type == 'picklist' || fields[f].type== 'multi-picklist') {
			    var comboConfig = new Ext.form.ComboBox({
				id:fields[f].name + 'combo',
				dataIndex : fields[f].name,
				valueField:'Name',
				triggerAction:'all',
				hiddenName:fields[f].name ,
				displayField:'Description',
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
			    else {
				if (fieldsectionindex > (fieldsectioncount-1)/2)
				    col2.add(comboConfig);
				else
				    col1.add(comboConfig);
				fieldsectionindex ++;
			    }
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

		    var linkConfig = {
		    	xtype: "box",
		    	autoEl: {
		    		tag: 'a',
		    		href: 'http://www.orangeleap.com/',
		    		html: 'Powered by Orange Leap.'
		    	}
		    };
		    this.form.superclass().addButton.call(this.form,btnConfig,this.form.onSubmit,this.form);
		    this.form.superclass().add.call(this.form,linkConfig);

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

			    if (metaData[m].type == 'picklist') {
				if (this.form.findById(metaData[m].name + 'combo') != null)
				    this.form.findById(metaData[m].name + 'combo').setValue(value);
			    } else {
				if (this.form.findById(metaData[m].name) != null)
				    this.form.findById(metaData[m].name).setValue(value);
			    }
			}
		    }
		if (this.form.args != null)
		    this.form.populateArgs(this.form,this.form.args);

		Ext.get('loading').remove();
		Ext.get('loading-mask').fadeOut({remove:true});
	    }}
	});

	OrangeLeapWidget.updateViewCount(this.guid,this.referer);

	this.mydatastore.load();
    },
    showError: function(str) {
	OrangeLeapWidget.updateErrorCount(this.guid,this.referer);

    	Ext.Msg.show({
    	    title: 'ERROR',
    	    msg: str,
    	    icon: Ext.MessageBox.ERROR,
    	    buttons: Ext.Msg.OK
    	});
    }

});
Ext.reg('customentity', OrangeLeap.CustomEntity);