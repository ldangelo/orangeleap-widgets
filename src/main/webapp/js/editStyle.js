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
		    ,msg:'Your changes were successfully saved.'
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
        form.getForm().submit({url:'style.ajax?action=save',waitMsg:'Saving Style...',submitEmptyText:false, success: onSuccess, failure: onFailure });
    }


	var reader = new Ext.data.JsonReader({
        idProperty: 'Id',
        root:'rows',
        totalProperty: 'rows',
        fields: [
            "Id","Style","StyleName","Inactive","Deleted"
        ]
	});


	    // need a form here....
    form = new Ext.FormPanel({
		id: 'form',
		//	    region: 'top',
                reader: reader,
                border: false,
		frame: true,
		labelWidth: 90,
		monitorValid:true,
		width: 990,
		height: 850,
		trackResetOnLoad:true,
		defaultType: 'textarea',
		items: [{
					fieldLabel: 'StyleName',
					id: 'StyleName',
	                width:950,
					xtype: 'textfield',
					maxLength: 128
				},
		        {
                        fieldLabel: 'Style',
                        id: 'Style',
                        name: 'Style',
                        width:950,
                        height:700
                },
                {
                    fieldLabel: 'Inactive',
                    id: 'Inactive',
                    name: 'Inactive',
                    xtype: 'checkbox'
                },
                {
                    fieldLabel: 'Deleted',
	                id: 'Deleted',
	                name: 'Deleted',
	                xtype: 'checkbox'
	            },
	            {
                	id: 'Id',
                	name: 'Id',
                	xtype: 'hidden'
                }
        ],
		buttons: [{
		    text: 'Save',
		    handler: onSave,
		    align:'center'
		}
			 ]
    });

    form.render('styleform-div');

    var id = getParameter(window.top.location.search.substring(1),'id');
    if (id != null) {
        form.getForm().load({url:'style.ajax?action=read&Id='+ id,method: 'GET',waitMsg: 'Loading'});
    }
    else {
        form.getForm().load({url:'style.ajax?action=create',method:'GET',waitMsg: 'Loading'});
    }

    form.on({
        actioncomplete: function(form, action){
            if(action.type == 'load'){
                form.items.get("Style").setValue(form.items.get("Style").value.replace(/\\n/gi,String.fromCharCode(10)));
                form.items.get("Style").setValue(form.items.get("Style").value.replace(/\\&quot;/gi,'"'));
                form.items.get("Style").setValue(form.items.get("Style").value.replace(/&quot;/gi,''));
            }
        }
    });

    $('#showExample').click(function() {
        $(this).hide();
        $('#examples, #hideExample').show();
    });

    $('#hideExample').click(function() {
        $(this).hide();
        $('#examples').hide();
        $('#showExample').show();
    });
});