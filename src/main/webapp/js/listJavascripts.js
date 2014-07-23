Ext.onReady(function() {

    Ext.QuickTips.init();
	var myjavascriptdatasource = null;

	var javascriptproxy = new Ext.data.HttpProxy( {api: {
		read: 'javascript.ajax?action=list'
	}}); // Ext.data.HttpProxy

	var javascriptreader = new Ext.data.JsonReader({
		totalProperty: 'totalRows',
		successProperty: 'success',
		idProperty: 'id',
		root: 'rows',
		messageProperty: 'message'
	});

	myjavascriptdatastore = new Ext.data.Store({
		metaData:true,
		reader:javascriptreader,
		proxy:javascriptproxy,
		autoSave:false,
		remoteSort: false,
		listeners: {

			'metachange':function(store,meta) {
			   var fields = meta.fields;
				// has the right definition

				var columns = [];
				for (var f=0;f < fields.length; f++) {
					var thisWidth = 275;
					if (fields[f].name == 'Inactive') {
						thisWidth = 50;
					}
					else if (fields[f].name == 'Id') {
						thisWidth = 100;
					}
					else if (fields[f].name == 'CreatedBy') {
						thisWidth = 150;
					}
					columns.push({
						header: fields[f].header,
						dataIndex: fields[f].name,
						sortable:true,
						width: thisWidth,
						renderer: function(value, metaData, record, rowIndex, colIndex, store) {
							var title = '';
							var dataIndex = this.dataIndex ? this.dataIndex : (this.scope ? this.scope.dataIndex : null);
							if (dataIndex && record.fields.map && record.fields.map[dataIndex]) {
								if (record.fields.map[dataIndex].header) {
									title = record.fields.map[dataIndex].header;
								}
							}
							return '<span ext:qtitle="' + title + '"ext:qwidth="400" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
						}
					});
				}

				javascriptgrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
			},
			'load':function(store,records,options) {
				panel.render("javascripts-div");
				updateToolbarText();
			}
		}});

    var createdByCombo = new Ext.form.ComboBox({
       store: new Ext.data.ArrayStore({
            fields: [
                'name',
                'desc'
            ],
            data: [['me', 'Last Modified By Me'], ['everyone', 'All Javascripts']]
        }),
        displayField: 'desc',
        valueField: 'name',
        typeAhead: false,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        emptyText: ' ',
        selectOnFocus: true,
        minListWidth: 150,
        id: 'createdByCombo',
        stateful: true,
        stateEvents: ['select'],
        listeners: {
            'select': function(comboBox, record, index) {
                if (localStorage) {
					localStorage.setItem('lastListJavascriptCreatedBy', comboBox.getValue());
                }
				loadStore();
            }
		}
    });

	var javascriptgrid = new Ext.grid.GridPanel({
		store: myjavascriptdatastore,
		columns: [],
		viewConfig: {
			emptyText: 'No Javascripts'
		},
		stripeRows: true,
		height: 250,
		width:900,
		loadMask: true,
		tbar: {
			items: [
                { xtype: 'tbtext', text: "View Javascripts:" },
                createdByCombo,
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
	            { xtype: 'tbspacer' },
				{
					xtype: 'button',
					'text': 'Add New Javascript',
					'tooltip': 'Add New Javascript',
					'iconCls': 'add',
					'id': 'addNewButton',
					'handler': function() {
						window.location.href = 'javascriptform.htm';
					}
				}
			]
		},
        bbar: new Ext.Toolbar([
            new Ext.Toolbar.TextItem({
                'id': 'javascriptsToolbarTextItem',
                'text': ' '
            })
        ]),
	});
	javascriptgrid.on('rowdblclick', function(grid, rowIndex, event) {
		javascriptgrid.el.mask('Loading...', 'x-mask-loading');
		var record = myjavascriptdatastore.getAt(rowIndex);
		if (record) {
			// open window to view record
			var thisUrl = 'javascriptform.htm?id=' + record.get('Id');

			window.location.href = thisUrl;
		}
	});

	var panel = new Ext.Panel({
		height: 350,
		width: 900,
		border: false,
		items:[ javascriptgrid ]
	});

	var lastFilterByCreatedBy = 'everyone';
	if (localStorage) {
		lastFilterByCreatedBy = localStorage.getItem('lastListJavascriptCreatedBy');
		if ( ! lastFilterByCreatedBy || (lastFilterByCreatedBy != 'me' && lastFilterByCreatedBy != 'everyone')) {
			lastFilterByCreatedBy = 'me';
		}
		localStorage.setItem('lastListJavascriptCreatedBy', lastFilterByCreatedBy);
	}
	createdByCombo.setValue(lastFilterByCreatedBy);

	var loadStore = function() {
		myjavascriptdatastore.load({
			params: {
				createdBy: createdByCombo.getValue()
			}
		});
	};

	var updateToolbarText = function() {
        var count = 0;
        if (myjavascriptdatastore.data && myjavascriptdatastore.data.items) {
            count = myjavascriptdatastore.data.items.length;
        }
        if (count > 1) {
            Ext.getCmp('javascriptsToolbarTextItem').setText(String.format('Displaying {0} Javascripts', count));
        }
        else if (count == 1) {
            Ext.getCmp('javascriptsToolbarTextItem').setText(String.format('Displaying 1 Javascript', count));
        }
        else {
            Ext.getCmp("javascriptsToolbarTextItem").setText('No Javascripts to display');
        }
    };

	loadStore();
});
