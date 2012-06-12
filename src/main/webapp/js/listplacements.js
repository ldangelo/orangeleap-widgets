Ext.onReady(function() {

	var myplacementdatasource = null;

    var placementproxy = new Ext.data.HttpProxy({
        api: {
            read: 'placements.ajax?action=read'
        }
    });

	var placementreader = new Ext.data.JsonReader({
	    totalProperty: 'totalRows',
	    successProperty: 'success',
	    idProperty: 'id',
	    root: 'rows',
	    messageProperty: 'message'
	});

	var myplacementdatastore = new Ext.data.Store({
	    metaData:true,
	    reader:placementreader,
	    proxy:placementproxy,
	    autoSave:false,
	    listeners: {
			'metachange': function(store,meta) {
                var fields = meta.fields;
                // has the right definition

                var columns = [];
	            for (var f=0;f < fields.length; f++) {
                    columns.push({
                        header: fields[f].header,
                        dataIndex: fields[f].name,
                        sortable:true,
                        width: 150,
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

                placementgrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
			}
		}
	});

	var placementgrid = new Ext.grid.GridPanel({
	    store: myplacementdatastore,
	    renderTo: 'placements-div',
	    id: 'placementGrid',
	    columns: [
	    ],
	    loadMask: true,
	    viewConfig: {
			emptyText: 'This Widget does not have a placement'
	    },
	    stripeRows: true,
	    height: 250,
	    width: 900
	});
});

