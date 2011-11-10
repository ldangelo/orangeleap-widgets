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
                    	columns.push({header: fields[f].header, dataIndex: fields[f].name,sortable:true, width: 275});
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
			emptyText: 'No Styles'
	    },
	    stripeRows: true,
	    height: 250,
	    width:900,
		tbar: {
            items: [
                {
					xtype: 'button',
					'text': 'Add New Style',
					'tooltip': 'Add New Style',
					'iconCls': 'add',
					'id': 'addNewButton',
					'handler': function() {
						window.location.href = 'styleform.htm';
					}
				}
			]
		}
	});
    stylegrid.on('rowdblclick', function(grid, rowIndex, event) {

        var record = mystyledatastore.getAt(rowIndex);
        if (record) {
            // open window to view record
            var thisUrl = 'styleform.htm?id=' + record.get('Id');

            window.location.href = thisUrl;
        }
    });

    var panel = new Ext.Panel({
    	height: 350,
    	width: 900,
    	border: false,
    	items:[ stylegrid ]
    });

    mystyledatastore.load();

        	});
        </script>
    </head>
    <body>
	<h3 class="heading">Widget Styles</h3>
    <div id="styles-div"></div>

    </body>
    </page:applyDecorator>

