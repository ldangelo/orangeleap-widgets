	var mystyledatasource = null;

        var styleproxy = new Ext.data.HttpProxy( {api: {
            read: 'styles.ajax?action=read'
        }}); // Ext.data.HttpProxy

	var stylereader = new Ext.data.JsonReader({
	    totalProperty: 'totalRows',
	    successProperty: 'success',
	    idProperty: 'id',
	    root: 'rows',
	    messageProperty: 'message'
	});

	mystyledatastore = new Ext.data.Store({
	    metaData:true,
	    reader:stylereader,
	    proxy:styleproxy,
	    autoSave:false,
	    listeners: {

     	    	   	'metachange':function(store,meta) {
               var fields = meta.fields;
                    // has the right definition

                    var columns = [];
		    for (var f=0;f < fields.length; f++) {
                    	columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true});
		    }

                    stylegrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
		},
		'load':function(store,records,options) {
				stylegrid.render("styles-div");
}
		}});

	var stylegrid = new Ext.grid.GridPanel({
	    store: mystyledatastore,
	    columns: [],
	    viewConfig: {
		forceFit: false,
		emptyText: 'No Widgets'
	    },
	    stripeRows: true,
	    frame:true,
	    title: "Widget Style",
	    height: 250,
	    width:655,
	});


