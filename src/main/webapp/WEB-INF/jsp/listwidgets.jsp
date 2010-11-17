<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
	<script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
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
		} else {
			if (record.data.type == 'gifthistory')
				url = 'gifthistorywidgetform.htm?guid=' + val;
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


	var proxy=new Ext.data.HttpProxy(    {url:'/donorwidgets/listWidgets.json'});

		  var reader=new Ext.data.JsonReader(
			{
				totalProperty: 'totalRows',
				root: 'rows',
				id: 'widgetid',
			fields: [
				{name: 'widgetid', mapping: 'widgetid'},
				{name: 'guid'},
				{name: 'type'},
				{name: 'entityname'},
				{name: 'errorcount'},
				{name: 'viewcount'}
			]}
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
			{id:'widgetid',width: 80,header:'Id',dataIndex:'widgetid'},
			{id:'type',width: 80,header:'Type',dataIndex:'type'},
			{id:'entityname',width: 80,header:'Entity Name',dataIndex:'entityname'},
			{id:'view',width:80,header:'View',renderer: renderViewIcon,dataIndex:'guid'},
			{id:'notifications',width:80,header:'Notifications',renderer: renderNotificationIcon,dataIndex:'guid'},
			{id:'code',width:80,header:'Code',renderer: renderCodeIcon,dataIndex:'guid'}
//			{id:'errorcount',width: 80,header:'Error Count',dataIndex:'errorcount', renderer: add_thousands_separator },
//			{id:'viewcount',width:80,header:'View Count',dataIndex:'viewcount',renderer: add_thousands_separator }
		],
	       viewConfig: {
            forceFit: false,
            emptyText: 'No Widgets Found'
        },
		stripeRows: true,
		frame:true,
		title: 'Current Widgets',
		height: 250,
		width: 655,
	    listeners: {
	    'rowclick': function(placementgrid,rowIndex, e) {
	    //
	    // need to load the placements
	    var row = this.store.data.items[rowIndex].data;

	    myplacementdatastore.load({params: {guid: row.guid}})
	    }
	    }
	});

    var panel = new Ext.Panel({
    	height: 350,
    	width: 660,
    	border: false,
    	items:[ {
    		xtype: 'button',
    		text: 'New Widget',
    		height: 50,
    		handler: newForm
    	}, grid ]
    });
	panel.render('grid-example');
});
	</script>
</head>
<body>
	<div id='grid-example'></div>
	<div id="placements-div"></div>
</body>
</page:applyDecorator>