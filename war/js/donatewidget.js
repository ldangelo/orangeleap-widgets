   Ext.onReady(function() {
      Ext.QuickTips.init();
      Ext.form.Field.prototype.msgTarget = 'side';

      var form_donate = new Ext.form.FormPanel({frame: true,title: 'Donate Now!',autoHeight: true, width: 512, bodyStyle : {padding: '10px'}});
      
      var panel_name = new Ext.Panel({frame: true, title: 'Name',autoHeight: true});
      
      var field_firstName = new Ext.form.TextField({fieldLabel: 'First Name', name:'firstName', width:140});
      var field_lastName = new Ext.form.TextField({fieldLabel: 'Last Name', name:'lastName', width:140});

      panel_name.add(field_firstName,field_lastName);
      
      var field_emailAddress = new Ext.form.TextField({fieldLabel: 'Email ', name:'emailAddress', width:140});
      var field_billingAddressLine1 = new Ext.form.TextField({fieldLabel: 'Line 1', name:'billingAddressLine1', width:140});
      var field_billingAddressLine2 = new Ext.form.TextField({fieldLabel: 'Line 2', name:'billingAddressLine2', width:140});
      var field_billingAddressLine3 = new Ext.form.TextField({fieldLabel: 'Line 3', name:'billingAddressLine3', width:140});
      var field_billingAddressCity = new Ext.form.TextField({fieldLabel: 'City', name:'billingAddressLine3', width:140});
      var field_billingAddressState = new Ext.form.TextField({fieldLabel: 'State', name:'billingAddressLine3', width:140});
      var field_billingAddressZip = new Ext.form.TextField({fieldLabel: 'Zip Code', name:'billingAddressLine3', width:140});

      var field_ccNumber = new Ext.form.TextField({fieldLabel: 'Credit Card', name:'ccNumber', width:140});
      
   // create reusable renderer
      Ext.util.Format.comboRenderer = function(combo){
          return function(value){
              var record = combo.findRecord(combo.valueField, value);
              return record ? record.get(combo.displayField) : combo.valueNotFoundText;
          }
      }

      var combo_ccExpMonth = new Ext.form.ComboBox({typeAhead: true,triggerAction:'all',lazyRender:true,mode:'local',
    	  store: new Ext.data.SimpleStore({
    		  id: 0,
    		  field: ['expId','displayText'],data:[
    		                                       [1, '01'],
    		                                       [2, '02'],
       		                                       [3, '03'],
       		                                       [4, '04'],
       		                                       [5, '05'],
       		                                       [6, '06'],
       		                                       [7, '07'],
       		                                       [8, '08'],
       		                                       [9, '09'],
       		                                       [10, '10'],
       		                                       [11, '11'],
       		                                       [12, '12']]
       		                                       }),
       	   valueField: 'expId',
      	   displayField: 'displayText'
      });
      
      var field_ccExpYear = new Ext.form.TextField({fieldLabel: 'Expiration Year', name:'ccExpYear', width:4});
  	
      form_donate.add({legend: 'Donate Now!'},panel_name,field_emailAddress,field_billingAddressLine1,field_billingAddressLine2,field_billingAddressLine3,field_billingAddressCity,field_billingAddressState,field_billingAddressZip,field_ccNumber,combo_ccExpMonth,field_ccExpYear);
  	
  	form_donate.render('donate-form');
  });
