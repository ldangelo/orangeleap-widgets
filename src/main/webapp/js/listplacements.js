	var myplacementdatasource = null;

        var placementproxy = new Ext.data.HttpProxy( {api: {
            read: 'placements.ajax?action=read'
        }}); // Ext.data.HttpProxy

	var placementreader = new Ext.data.JsonReader({
	    totalProperty: 'totalRows',
	    successProperty: 'success',
	    idProperty: 'id',
	    root: 'rows',
	    messageProperty: 'message'
	});
	
	myplacementdatastore = new Ext.data.Store({
	    metaData:true,
	    reader:placementreader,
	    proxy:placementproxy,
	    autoSave:false,
	    listeners: {

     	    	   	'metachange':function(store,meta) {
               var fields = meta.fields;
                    // has the right definition

                    var columns = [];
		    for (var f=0;f < fields.length; f++) {
                    	columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true});
		    }			
		 
                    placementgrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
		},
		'load':function(store,records,options) {
				placementgrid.render("placements-div");
}
		}});

	var placementgrid = new Ext.grid.GridPanel({
	    store: myplacementdatastore,
	    columns: [],
	    viewConfig: {
		forceFit: false,
		emptyText: 'No Widgets'
	    },
	    stripeRows: true,
	    frame:true,
	    title: "Login Widget Placement",
	    height: 250,
	    width:655,
	});
        

