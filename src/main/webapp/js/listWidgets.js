function add_thousands_separator(input) {
	var s = input.toString(), l = s.length, o = '';
	while (l > 3) {
		var c = s.substr(l - 3, 3);
		o = ',' + c + o;
		s = s.replace(c, '');
		l -= 3;
	}
	o = s + o;
	return o;
}

function renderNotificationIcon(val,metaData, record, rowIndex, colIndex, store) {
   return "<a class='sprite notifications'></a>";
}

function renderCodeIcon(val,metaData, record, rowIndex, colIndex, store) {
	//
	// find out what 'type' of widget we are sitting on custom entity, etc... and set the href accordingly.

	var url = "";
	//
	// find out what 'type' of widget we are sitting on custom entity, etc... and set the href accordingly...
	if (record.data.type == "customentity") {
		if (record.data.entityname == 'online_donation')
			url = 'donationwidgetform.htm?guid=' + val;
		else if (record.data.entityname == 'widget_authentication')
			url = 'loginwidgetform.htm?guid=' + val;
		else if (record.data.entityname == 'donor_profile')
			url= 'donorprofilewidgetform.htm?guid=' + val;
		else if (record.data.entityname == 'sponsorable')
			url = 'sponsorablewidgetform.htm?guid=' + val;
		else if (record.data.entityname == 'online_sponsorship')
			url = 'sponsorshipwidgetform.htm?guid=' + val;
		else if(record.data.entityname == 'online_registration')
			url = 'registrationwidgetform.htm?guid=' + val;
		else if(record.data.entityname == 'online_recurringgift')
			url = 'recurringgiftwidgetform.htm?guid=' + val;
	} else {
		if (record.data.type == 'gifthistory') {
			url = 'gifthistorywidgetform.htm?guid=' + val;
		} else if (record.data.type == 'pledges') {
			url = 'pledgewidgetform.htm?guid=' + val;
		}
	}
   return "<a class='sprite code' href='" + url +"'></a>";
}

function renderViewIcon(val,metaData, record, rowIndex, colIndex, store) {
	var url = 'widget.ajax?action=view&guid=' + val;

	return "<a class='sprite view' href='" + url + "'></a>";
}

function newForm() {
	window.location = 'createwidget.htm';
}

Ext.onReady(function() {

    Ext.QuickTips.init();

	var proxy = new Ext.data.HttpProxy({
		url:'listWidgets.json'
	});

	var reader = new Ext.data.JsonReader({
		totalProperty: 'totalRows',
		root: 'rows',
		id: 'widgetid',
		fields: [
			{name: 'widgetid', mapping: 'widgetid'},
			{name: 'guid'},
			{name: 'type'},
			{name: 'widgetname'},
			{name: 'widgetDescription'},
			{
				name: 'replaceTopContents',
				renderer: function(value, metaData, record, rowIndex, colIndex, store) {
					if (value == 'true' || value == '1') {
						value = 'Replace Top Window Contents';
					}
					else {
						value = 'Replace Inner Window (IFrame) Contents';
					}
					return value;
				}
			},
			{name: 'entityname'},
			{name: 'errorcount'},
			{name: 'inactive'},
			{name: 'inactive'},
			{name: 'createdBy'},
			{name: 'viewcount'}
		]}
	)

	var store = new Ext.data.Store({
		proxy: proxy,
		reader: reader,
		remoteSort: false,
		listeners: {
			load: function(store, records, options) {
				updateToolbarText();
			}
		}
	});

	var findTitle = function(dataIndex, scope, store) {
        var title = '';
        var dataIndex = dataIndex ? dataIndex : (scope ? scope.dataIndex : null);
        if (dataIndex && store.fields.map && store.fields.map[dataIndex]) {
            if (store.fields.map[dataIndex].header) {
                title = store.fields.map[dataIndex].header;
            }
        }
        return title;
	};

    var createdByCombo = new Ext.form.ComboBox({
       store: new Ext.data.ArrayStore({
            fields: [
                'name',
                'desc'
            ],
            data: [['me', 'Me'], ['everyone', 'All']]
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
					localStorage.setItem('lastListWidgetCreatedBy', comboBox.getValue());
                }
				loadStore();
            }
		}
    });

	//
	// create the grid
	var grid = new Ext.grid.GridPanel({
		store: store,
		columns: [
			{
				id:'widgetid',
				width: 100,
				header:'Id',
				dataIndex:'widgetid',
				sortable: true,
                renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var title = findTitle(this.dataIndex, this.scope, store);
                    return '<span ext:qtitle="' + title + '"ext:qwidth="250" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
                }
			},
			{
				id: 'widgetName',
				width: 200,
				header: 'Widget Name',
				dataIndex: 'widgetname',
				sortable: true,
				renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var title = findTitle(this.dataIndex, this.scope, store);
					return '<span ext:qtitle="' + title + '"ext:qwidth="250" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
				}
			},
			{
				id:'widgetDescription',
				width: 200,
				header:'Description',
				dataIndex:'widgetDescription',
				sortable: true,
				renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var title = findTitle(this.dataIndex, this.scope, store);
					return '<span ext:qtitle="' + title + '"ext:qwidth="250" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
				}
			},
			{
				id:'createdBy',
				width: 150,
				header:'Last Modified By',
				dataIndex:'createdBy',
				sortable: true,
				renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var title = findTitle(this.dataIndex, this.scope, store);
					return '<span ext:qtitle="' + title + '"ext:qwidth="250" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
				}
			},
			{
				id:'inactive',
				width:50,
				header:'Inactive',
				dataIndex:'inactive',
				sortable: true,
				renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var title = findTitle(this.dataIndex, this.scope, store);
					return '<span ext:qtitle="' + title + '"ext:qwidth="250" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
				}
			},
			{id:'view',width:80,header:'View',renderer: renderViewIcon,dataIndex:'guid'},
			{id:'code',width:80,header:'Code',renderer: renderCodeIcon,dataIndex:'guid'}
		],
	    viewConfig: {
            emptyText: 'No Widgets Found'
        },
        loadMask: true,
		stripeRows: true,
		height: 250,
		width: 900,
		tbar: {
            items: [
                { xtype: 'tbtext', text: "View Widgets Last Modified By:" },
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
					'text': 'Add New Widget',
					'tooltip': 'Add New Widget',
					'iconCls': 'add',
					'id': 'addNewButton',
					'handler': function() {
						window.location.href = 'createwidget.htm';
					}
				}
			]
		},
        bbar: new Ext.Toolbar([
            new Ext.Toolbar.TextItem({
                'id': 'widgetsToolbarTextItem',
                'text': ' '
            })
        ]),
	    listeners: {
		    'rowclick': function(placementgrid,rowIndex, e) {
				// need to load the placements
				grid.el.mask('Loading', 'x-mask-loading');
				var row = this.store.data.items[rowIndex].data;

				var callbackFunc = function() {
					grid.el.unmask();
				};
		        Ext.getCmp('placementGrid').store.load({params: {guid: row.guid}, callback: callbackFunc})
	        }
	    }
	});

	var updateToolbarText = function() {
		var count = 0;
		if (store.data && store.data.items) {
			count = store.data.items.length;
		}
		if (count > 1) {
			Ext.getCmp('widgetsToolbarTextItem').setText(String.format('Displaying {0} Widgets', count));
		}
		else if (count == 1) {
			Ext.getCmp('widgetsToolbarTextItem').setText(String.format('Displaying 1 Widget', count));
		}
		else {
			Ext.getCmp('widgetsToolbarTextItem').setText('No Widgets to display');
		}
	};

	var lastFilterByCreatedBy = 'everyone';
	if (localStorage) {
		lastFilterByCreatedBy = localStorage.getItem('lastListWidgetCreatedBy');
		if ( ! lastFilterByCreatedBy || (lastFilterByCreatedBy != 'me' && lastFilterByCreatedBy != 'everyone')) {
			lastFilterByCreatedBy = 'me';
		}
		localStorage.setItem('lastListWidgetCreatedBy', lastFilterByCreatedBy);
	}
	createdByCombo.setValue(lastFilterByCreatedBy);

	var loadStore = function() {
		store.load({
			params: {
				createdBy: createdByCombo.getValue()
			}
		});
	};

	loadStore();

    var panel = new Ext.Panel({
    	height: 350,
    	width: 900,
    	border: false,
    	items:[ grid ]
    });
	panel.render('grid-example');
});
