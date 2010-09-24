<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>

        <script type="text/javascript">
        	Ext.onReady(function() {

        		function newStyle() {
        					window.location = 'styleform.htm';
        		}
	var mystyledatasource = null;

        var styleproxy = new Ext.data.HttpProxy( {api: {
            read: 'style.ajax?action=list'
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
				panel.render("styles-div");
		}
		}});


	var stylegrid = new Ext.grid.GridPanel({
	    store: mystyledatastore,
	    columns: [],
	    viewConfig: {
		forceFit: false,
		emptyText: 'No Styles'
	    },
	    stripeRows: true,
	    frame:true,
	    title: "Widget Styles",
	    height: 250,
	    width:655,
	});
    stylegrid.on('rowdblclick', function(grid, rowIndex, event) {

        var record = mystyledatastore.getAt(rowIndex);
        if (record) {
            // open window to view record
            var thisUrl = 'styleform.htm?id=' + record.get('Id');

            window.location = thisUrl;
        }
    });

    var panel = new Ext.Panel({
    	height: 350,
    	width: 660,
    	border: false,
    	items:[ {
    		xtype: 'button',
    		text: 'New Style',
    		height: 50,
    		handler: newStyle
    	}, stylegrid ]
    });


    mystyledatastore.load();

        	});
        </script>
    </head>
    <body>
    <div id="styles-div"></div>

    </body>
    </page:applyDecorator>

