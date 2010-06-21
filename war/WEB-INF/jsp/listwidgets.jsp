<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
	<script type="text/javascript" src="js/jquery/jquery-1.3.2-min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all.js"></script>
	<script type="text/javascript">
	
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
		
Ext.onReady(function() {
	var myData = [
		['1','1','General Presidents Fund','Donate Now','donateWidget.htm?id=1'],
		['2','2','General Presidents Fund','God Tube','donateWidget.htm?id=2'],		
		['3','3','General Presidents Fund','Google','donateWidget.htm?id=3']
		];		

	var proxy=new Ext.data.HttpProxy(    {url:'/donatenow/listWidgets.json'});

		  var reader=new Ext.data.JsonReader(
			{
				totalProperty: 'totalRows',
				root: 'rows',
				id: 'widgetid'
			},[
				{name: 'widgetid', mapping: 'widgetid'},
				{name: 'guid'},
				{name: 'type'},
				{name: 'errorcount'},
				{name: 'viewcount'}
			]
		)

		var store=new Ext.data.Store(    {
		  proxy:proxy,
		  reader:reader
	   });

	store.load();


	
	//
	// create the grid
	var grid = new Ext.grid.GridPanel({
		store: store,
		columns: [
			{id:'widgetid',width: 80,header:'Widget Id',dataIndex:'widgetid'},
			{id:'guid',width: 250,header:'Widget GUID',dataIndex:'guid'},		
			{id:'type',width: 80,header:'Widget Type',dataIndex:'type'},
			{id:'errorcount',width: 80,header:'Error Count',dataIndex:'errorcount', renderer: add_thousands_separator },			
			{id:'viewcount',width:80,header:'View Count',dataIndex:'viewcount',renderer: add_thousands_separator }				
		],
	       viewConfig: {
            forceFit: false,
            emptyText: 'No Widgets Found'
        },
		stripeRows: true,
		title: 'Current Widgets',
		height: 350,
		width: 900
	});

    grid.on('rowdblclick', function(grid, rowIndex, event) {

        var record = store.getAt(rowIndex);
        if (record) {
            // open window to view record
            var thisUrl = 'donatewidget.htm?id=' + record.get('widgetid');

            window.location = thisUrl;
        }
    });
	
	grid.render('grid-example');
});
	</script>
</head>
<body>
	<div id='grid-example'/>
</body>
</page:applyDecorator>