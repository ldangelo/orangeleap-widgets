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
        form.getForm().submit({url:'site.ajax?action=save',waitMsg:'Saving Settings...',submitEmptyText:false, success: onSuccess, failure: onFailure });
    }


	var reader = new Ext.data.JsonReader({
        idProperty: 'siteName',
        root:'rows',
        totalProperty: 'rows',
        fields: [
            "siteName","orangeLeapUserId","orangeLeapPassword"
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
		height: 250,
		trackResetOnLoad:true,
		defaultType: 'textarea',
		items: [{
			fieldLabel: 'Site Name',
			id: 'siteName',
                width:950,
				xtype: 'textfield',
				maxLength: 128,
				hidden: true
			},
	        {
                fieldLabel: 'Orange Leap User ID',
                id: 'orangeLeapUserId',
                xtype: 'textfield',
                width:950,
                maxLength: 255
            },
	        {
                fieldLabel: 'Orange Leap Password',
                id: 'orangeLeapPassword',
                xtype: 'textfield',
                width:950,
                maxLength: 255,
                inputType: 'password'
	        }
        ],
		buttons: [{
		    text: 'Save',
		    handler: onSave,
		    align:'center'
		}]
    });

    form.render('siteform-div');

    form.getForm().load({
        url:'site.ajax?action=read',
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
});
