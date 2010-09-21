<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>

        <script type="text/javascript">
        	Ext.onReady(function() {
var form=null;

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

                function onSave() {
                	form.getForm().submit({url:'style.ajax?action=save',waitMsg:'Saving Style...',submitEmptyText:false});
                }


	    var reader = new Ext.data.JsonReader({
                idProperty: 'Id',
                root:'rows',
                totalProperty: 'rows',
	            fields: [
    	            "Id","Style"
        	    ]});


	    // need a form here....
	    form = new Ext.FormPanel({
		id: 'form',
		//	    region: 'top',
                reader: reader,
                border: false,
		frame: true,
		labelWidth: 100,
		monitorValid:true,
		width: 600,
		trackResetOnLoad:true,
		defaultType: 'textarea',
		items: [{
                        fieldLabel: 'Style',
                        id: 'Style',
                        name: 'Style',
                        width:400,
                        height:200
                },
                {
                	id: 'Id',
                	name: 'Id',
                	xtype: 'hidden'
                }],
		buttons: [{
		    text: 'Save',
		    handler: onSave,
		    align:'center'
		}
			 ]
	    });
        form.render('styleform-div');

        form.getForm().load({url:'style.ajax?action=read',method: 'GET',waitMsg: 'Loading'});

        form.on({
        actioncomplete: function(form, action){
            if(action.type == 'load'){
    			form.items.get("Style").setValue(form.items.get("Style").value.replace(/\\n/gi,String.fromCharCode(10)));
    			form.items.get("Style").setValue(form.items.get("Style").value.replace(/&quot;/gi,''));
            }
        }
    });

	});


        </script>
    </head>
    <body>
    <div id="styleform-div"></div>

    </body>
    </page:applyDecorator>

