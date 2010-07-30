

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript">

	Ext.onReady(function() {
        var guid = null;
	var mydatastore = null;
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
//                    col = new Ext.grid.Column({
//                        align: 'left',
//                        header: fields[f].header,
//                        width: 100,
//                        sortable: true,
//		 grid:grid,
//                        dataIndex: fields[f].name
//                    });

                    columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true});
}
		 
                    placementgrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
		},
		'load':function(store,records,options) {
				placementgrid.render("loginplacements-div");
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

        
        var proxy = new Ext.data.HttpProxy( {api: {
            read: 'loginwidget.ajax?action=read&guid=' + guid,
            create: 'loginwidget.ajax?action=create',
            update: 'loginwidget.ajax?action=update&guid=' + guid,
            destroy: 'loginwidget.ajax?action=save&guid=' + guid
        }}); // Ext.data.HttpProxy

	var reader = new Ext.data.JsonReader({
	    totalProperty: 'totalRows',
	    successProperty: 'success',
	    idProperty: 'id',
	    root: 'rows',
	    messageProperty: 'message'
	});
	
	mydatastore = new Ext.data.Store({
	    metaData:true,
	    reader:reader,
	    proxy:proxy,
	    autoSave:false,
	    listeners: {

  	    	   	'metachange':function(store,meta) {
               var fields = meta.fields;
                    // has the right definition

                    var columns = [];
for (var f=0;f < fields.length; f++) {
//                    col = new Ext.grid.Column({
//                        align: 'left',
//                        header: fields[f].header,
//                        width: 100,
//                        sortable: true,
//		 grid:grid,
//                        dataIndex: fields[f].name
//                    });

                    columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true});
}
		 
                    grid.reconfigure(store,new Ext.grid.ColumnModel(columns));
		},
		'load':function(store,records,options) {
				grid.render("logingrid-div");
}
		}});

	var grid = new Ext.grid.GridPanel({
	    store: mydatastore,
	    columns: [],
	    viewConfig: {
		forceFit: false,
		emptyText: 'No Widgets'
	    },
	    stripeRows: true,
	    frame:true,
	    title: "Login Widgets",
	    height: 250,
	    width:655,
	    listeners: {
	    'rowdblclick': function(placementgrid,rowIndex, e) {
	    //
	    // need to load the placements
	    var row = this.store.data.items[rowIndex].data;

	    myplacementdatastore.load({params: {guid: row.widgetGuid}})
	    }	    
	    }

	});

		mydatastore.load();
	});  // Ext.onReady
	</script>
    </head>
    <body>
    <div id="logingrid-div"></div>
    <div id="loginplacements-div"></div>
    </body>
</page:applyDecorator>
    