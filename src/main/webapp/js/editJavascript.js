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
        form.getForm().submit({url:'javascript.ajax?action=save',waitMsg:'Saving Javascript...',submitEmptyText:false, success: onSuccess, failure: onFailure });
    }


	var reader = new Ext.data.JsonReader({
        idProperty: 'Id',
        root:'rows',
        totalProperty: 'rows',
        fields: [
            "Id","Javascript","JavascriptName","Inactive","Deleted"
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
					fieldLabel: 'JavascriptName',
					id: 'JavascriptName',
	                width:950,
					xtype: 'textfield',
					maxLength: 128
				},
		        {
                        fieldLabel: 'Javascript',
                        id: 'Javascript',
                        name: 'Javascript',
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

    form.render('javascriptform-div');

    var id = getParameter(window.top.location.search.substring(1),'id');
    if (id != null) {
        form.getForm().load({
            url:'javascript.ajax?action=read&Id='+ id,
            method: 'GET',
            waitMsg: 'Loading',
            failure: function(form, action) {
                if (action && action.response && action.response.getResponseHeader('errorMsg')) {
			        Ext.MessageBox.show({
			            'title': 'ERROR',
			            'icon': Ext.MessageBox.ERROR,
			            'buttons': Ext.MessageBox.OK,
			            'minWidth': 600,
			            'msg': action.response.getResponseHeader('errorMsg')
			        });
                }
            }
        });
    }
    else {
        form.getForm().load({
            url:'javascript.ajax?action=create',
            method:'GET',
            waitMsg: 'Loading',
			failure: function(form, action) {
	            if (action && action.response && action.response.getResponseHeader('errorMsg')) {
			        Ext.MessageBox.show({
			            'title': 'ERROR',
			            'icon': Ext.MessageBox.ERROR,
			            'buttons': Ext.MessageBox.OK,
			            'minWidth': 600,
			            'msg': action.response.getResponseHeader('errorMsg')
			        });
	            }
			}
        });
    }

    form.on({
        actioncomplete: function(form, action){
            if(action.type == 'load'){
                form.items.get("Javascript").setValue(form.items.get("Javascript").value.replace(/\\n/gi,String.fromCharCode(10)));
                form.items.get("Javascript").setValue(form.items.get("Javascript").value.replace(/\\&quot;/gi,'"'));
                form.items.get("Javascript").setValue(form.items.get("Javascript").value.replace(/&quot;/gi,''));
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
