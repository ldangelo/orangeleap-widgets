

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
    <script type="text/javascript" src="js/dynamicform.js"></script>
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
		params.widgettype='gifthistory';
		params.customentitytype='undefined';
		
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
			generateForm(form,store,meta);

		    },
		    'load':function(store,records,options) {
			var metaData = store.reader.meta.fields;
			var value = null;
			for (var m=0; m < metaData.length; m++) {
			    if (metaData[m].type != 'boolean') {
     			      value = records[records.length-1].get(metaData[m].name);
			      value = value.replace(/&lt;/g,"<");
			      value = value.replace(/&gt;/g,">");
			      var fld = form.getForm().findField(metaData[m].name);
			      if (fld != null) fld.setValue(value);
			    }			    
			}
			form.render("donorprofileform-div");
		    }}});
	    
	    // need a form here....
	    form = new Ext.FormPanel({
		id: 'form',
		//	    region: 'top',
		border: false,
		frame: true,
		labelWidth: 100,
		monitorValid:true,
		monitorPoll:200,
		width: 600,
		trackResetOnLoad:true,
		items: [],
		buttons: [{
		
		    text: 'Generate',
		    formBind:true,
		    handler: onGenerate,
		    align:'center'
		}
			 ]
	    });
	    
	    var widgetid = getParameter(queryString,'widgetId');
	    if (widgetid == null)
		mydatastore.load({url: 'widgetform.ajax', params: {action:'create',widgettype:'gifthistory',customentitytype:'undefined'}});
	    else
		mydatastore.doRequest({url:'widgetform.ajax',params: {action:'read',widgettype:'gifthistory',customentitytype:'undefined',widgetid:widgetid}});
	});  // Ext.onReady
</script>
    </head>
    <body>
    <div id="donorprofileform-div"></div>
    <div id="placements-div"></div>
    </body>
    </page:applyDecorator>
    