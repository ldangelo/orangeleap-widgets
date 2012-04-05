

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/adapter/jquery/ext-jquery-adapter.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
	<script type="text/javascript">

	Ext.onReady(function() {
        var guid = null;
	var mydatastore = null;
        
        var proxy = new Ext.data.HttpProxy( {api: {
            read: 'loginwidget.ajax?action=read&widgettype=customentity&customentitytype=sponsorable&guid=' + guid,
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
	    title: "Sponsorable Widgets",
	    height: 250,
	    width:655,
	    listeners: {
	    'rowclick': function(placementgrid,rowIndex, e) {
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
    <div id="placements-div"></div>
    </body>
</page:applyDecorator>
    