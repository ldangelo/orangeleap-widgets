function generateForm(form,store,meta) {
			if (form.items.length > 1) return;  // we are paging through data and the fields already exist
			var fields = meta.fields;
			var col1 = new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]}); 
			var col2 = null;

			if (fields.length > 4)
			    col2 =  new Ext.Panel({columnWidth: '.50',layout:'form',defaults:{anchor:'100%'},bodyStyle:'padding:0 18px 0 0',items:[]})
			 
			var panel = null;

			if (col2 != null) {
				panel = new Ext.Panel({ columnWidth:0.5,layout:'column', bodyStyle:'padding:0 18px 0 0',items:[col1,col2]});
			}
			else {
				panel = new Ext.Panel({bodyStyle:'padding:0 18px 0 0',items:[col1]});
			}

			for (var f=0;f < fields.length; f++) {
			    if (fields[f].hidden) {
					var field = new Ext.form.Hidden();
					field.id = fields[f].name;
					field.name = fields[f].name;
					field.fieldLabel = fields[f].header;
					field.store = store;
					field.mode = 'local';
					field.border = false;

					if (fields[f].readonly == true)
					   field.readOnly=true;

					if (col2 != null && f > fields.length/2)
						col2.add(field);
					else
						col1.add(field);

			    }
			    else if (fields[f].type == 'text' || fields[f].type == 'date' || fields[f].type == 'integer') {
					var field = new Ext.form.TextField();
					field.id = fields[f].name;
					field.name = fields[f].name;
					field.fieldLabel = fields[f].header;
					field.store = store;
					field.mode = 'local';
					field.border = false;

					if (fields[f].readonly == true)
					   field.readOnly=true;

					if (fields[f].required == true) {
						field.allowBlank=false,
						field.blankText="Enter a " + fields[f].header;
					}

					if (col2 != null && f > fields.length/2)
						col2.add(field);
					else
						col1.add(field);
				
			    }
			    else if (fields[f].type == 'comment') {
					var field = new Ext.form.TextArea();
					field.id = fields[f].name;
					field.name = fields[f].name;
					field.fieldLabel = fields[f].header;
					field.store = store;
					field.mode = 'local';
					field.border = false;
					field.width = 350;
					field.height = 150;

					if (fields[f].readonly == true)
					   field.readOnly=true;

					if (fields[f].required == true) {
						field.allowBlank=false,
						field.blankText="Enter a " + fields[f].header;
					}

					if (col2 != null && f > fields.length/2)
						col2.add(field);
					else
						col1.add(field);

			    }
			    else if (fields[f].type == 'picklist') {
					var comboConfig = new Ext.form.ComboBox({
					id:fields[f].name,
					dataIndex : fields[f].name,
					valueField:'Name',
					triggerAction:'all',
					hiddenName:fields[f].name + 'hidden' ,
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
						proxy: new Ext.data.HttpProxy({
							api: {
								read: 'picklistItems.json'
							},
							timeout: 120000
						}),
						baseParams: {
							guid: "",
							picklistname: fields[f].name
						}
					}),
					fieldLabel:fields[f].header
					});
					if (fields[f].required == true) {
					comboConfig.allowBlank=false,
					comboConfig.blankText="Enter a " + fields[f].header;
					}
					if (col2 != null && f > fields.length/2)
						col2.add(comboConfig);
					else
						col1.add(comboConfig);

			    }
			    else if (fields[f].type == 'multi-picklist') {
					var field = new Ext.form.TextField();
					field.id = fields[f].name;
					field.name = fields[f].name;
					field.fieldLabel = fields[f].header;
					field.store = store;
					field.mode = 'local';
					field.border = false;

					if (fields[f].readonly == true)
					   field.readOnly=true;

					if (fields[f].required == true) {
					field.allowBlank=false,
					field.blankText="Enter a " + fields[f].header;
					}

					if (col2 != null && f > fields.length/2)
						col2.add(field);
					else
						col1.add(field);

			    }
			    else if (fields[f].type == 'boolean') {
			        if (fields[f].element == 'radio') {
						var field = new Ext.form.RadioGroup({
                        	id: fields[f].name,
                        	name: fields[f].name,
                        	readOnly: fields[f].readonly,
							items: [
								{ boxLabel: fields[f].trueOption, name: fields[f].name, inputValue: 'true' },
								{ boxLabel: fields[f].falseOption, name: fields[f].name, inputValue: 'false', checked: true },
							]
						});
						if (col2 != null && f > fields.length/2) {
							col2.add(field);
						}
						else {
							col1.add(field);
						}
			        }
			        else {
						// add a checkbox....
						var field = new Ext.form.Checkbox();
						field.id = fields[f].name;
						field.name = fields[f].name;
						field.boxLabel = fields[f].header;
						field.store = store;
						field.mode = 'local';
						field.border = false;

						if (fields[f].readonly == true)
						   field.readOnly=true;

						if (fields[f].required == true) {
							field.checked=true;
							field.disabled=true;
						}
						if (col2 != null && f > fields.length/2)
							col2.add(field);
						else
							col1.add(field);
			        }
				}
			}
			form.add(panel);
}