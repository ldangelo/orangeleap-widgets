

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
	<script type="text/javascript">

	Ext.onReady(function() {
	    var fldidx = 0;
            var guid = null;
	    var mydatastore = null;
	    var form = null;
	    var queryString = window.top.location.search.substring(1);

	    function getParameter ( queryString, parameterName ) {
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
	    } 
	    
	    function onSuccess() {
		Ext.Msg.show({
        	    title:'Success'
		    ,msg:'Form submitted successfully'
		    ,modal:true
		    ,icon:Ext.Msg.INFO
		    ,buttons:Ext.Msg.OK
		});
	    }
	    function onFailure(form,action) {
		Ext.Msg.show({
		    title:'Error'
		    ,msg:action.result.message || action.response.responseText
		    ,modal:true
		    ,icon:Ext.Msg.ERROR
		    ,buttons:Ext.Msg.OK
		});
	    }
	    
	    function onGenerate() {
	    	var params = new Object();
		params.action = "update";
		params.widgettype='customentity';
		params.customentitytype='widget_authentication';
		
		for (var f = 0; f < mydatastore.reader.meta.fields.length; f++) 
		{
		params[mydatastore.reader.meta.fields[f].name] = form.findById(mydatastore.reader.meta.fields[f].name).getValue();
		}
		
		mydatastore.load({
		    url:'widgetform.ajax'
		    ,success:onSuccess
		    ,failure:onFailure
		    ,params: params
	       	    ,waitMsg:'Generating Widget...'
		});	
	    }	        
            var proxy = new Ext.data.HttpProxy( {url: 'widgetform.ajax'}
            ); // Ext.data.HttpProxy
	    
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
		listeners: {
		    
    	    	    'metachange':function(store,meta) {
			if (form.items.length > 0) return;  // we are paging through data and the fields already exist
			var fields = meta.fields;
			var col1 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]});
			var col2 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]})
			var panel = new Ext.Panel({ columnWidth:0.5,layout:'column', bodyStyle:'padding:0 18px 0 0',items:[col1,col2]});
			
			for (var f=0;f < fields.length; f++) {
			    if (fields[f].hidden == true) {
				var field = new Ext.form.Hidden();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = mydatastore;
				field.mode = 'local';
				field.border = false;
				
				if (fields[f].readonly == true) 
				   field.readOnly=true;
				
				if (f > fields.length/2)
				    col2.add(field);
				else
				    col1.add(field);
				
			    } else if (fields[f].type == 'text' || fields[f].type == 'date' || fields[f].type == 'integer') {
				var field = new Ext.form.TextField();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = mydatastore;
				field.mode = 'local';
				field.border = false;

				if (fields[f].readonly == true) 
				   field.readOnly=true;

				if (f > fields.length/2)
				    col2.add(field);
				else
				    col1.add(field);
				
			    } else if (fields[f].type == 'comment') {
				var field = new Ext.form.TextArea();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = mydatastore;
				field.mode = 'local';
				field.border = false;
				field.width = 350;
				field.height = 150;

				if (fields[f].readonly == true) 
				   field.readOnly=true;

				if (f > fields.length/2)
				    col2.add(field);
				else
				    col1.add(field);
				
			    } else if (fields[f].type == 'picklist') {
				var field = new Ext.form.TextField();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = mydatastore;
				field.mode = 'local';
				field.border = false;

				if (fields[f].readonly == true) 
				   field.readOnly=true;

				if (f > fields.length/2)
				    col2.add(field);
				else
				    col1.add(field);
				
			    } else if (fields[f].type == 'multi-picklist') {
				var field = new Ext.form.TextField();
				field.id = fields[f].name;
				field.name = fields[f].name;
				field.fieldLabel = fields[f].header;
				field.store = mydatastore;
				field.mode = 'local';
				field.border = false;

				if (fields[f].readonly == true) 
				   field.readOnly=true;

				if (f > fields.length/2)
				    col2.add(field);
				else
				    col1.add(field);
				
			    }
			}
			form.add(panel);
		    },
		    'load':function(store,records,options) {
			var metaData = store.reader.meta.fields;
			var value = null;
			for (var m=0; m < metaData.length; m++) {
			    
			    value = records[records.length-1].get(metaData[m].name);
			    var fld = form.getForm().findField(metaData[m].name);
			    if (fld != null) fld.setValue(value);
			}
			form.render("loginform-div");
		    }}});
	    
	    // need a form here....
	    form = new Ext.FormPanel({
		id: 'form',
		//	    region: 'top',
		border: false,
		frame: true,
		labelWidth: 100,
		monitorValid:true,
		width: 600,
		trackResetOnLoad:true,
		items: [],
		buttons: [{
		    text: 'Generate',
		    handler: onGenerate,
		    align:'center'
		}
			 ]
	    });
	    
	    var widgetid = getParameter(queryString,'widgetId');
	    if (widgetid == null)
		mydatastore.load({url: 'widgetform.ajax', params: {action:'create',widgettype:'customentitytype',customentitytype:'widget_authentication'}});
	    else
		mydatastore.doRequest({url:'widgetform.ajax',params: {action:'read',widgettype:'customentitytype',customentitytype:'widget_authentication',widgetid:widgetid}});
	});  // Ext.onReady
</script>
    </head>
    <body>
    <div id="loginform-div"></div>
    <div id="placements-div"></div>
    </body>
    </page:applyDecorator>
    