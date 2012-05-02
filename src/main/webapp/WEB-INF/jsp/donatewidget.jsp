
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<page:applyDecorator name="main">
	<head>
	<style type="text/css">
	#column1 {
		position: relative;
		left: 0;
	}
	#column2 {
		postion: relative;
		left: 50%;
		}
	form input[type="text"], form input.text
	{
	}
	form
	{
		width: 500px;
	}
	div.header
	{
		width: 500px;
		background-color:#b0c4de;
	}
	
	div.form-row
	{
		width: 500px;
	}
	fieldset
	{
		background-color: #e0ffff;
		border-style:solid;
		border-width:5px;
		width: 500px;
	}	
	</style>	
	<script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all.js"></script>
	<script type="text/javascript">
	var $j = jQuery.noConflict();
	
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

	var proxy=new Ext.data.HttpProxy(    {url:'listPlacements.json?widgetid=' + $j("#id").attr("value")});
		  var reader=new Ext.data.JsonReader(
			{
				totalProperty: 'totalRows',
				root: 'rows',
				id: 'id'
			},[
				{name: 'id', mapping: 'id'},
				{name: 'url'},
				{name: 'giftcount'},
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
			{id:'id',width: 80,header:'Placement Id',dataIndex:'id'},
			{id:'url',width: 250,header:'Placement location',dataIndex:'url'},		
			{id:'giftcount',width: 80,header:'Gift Count',dataIndex:'giftcount', renderer: add_thousands_separator },
			{id:'errorcount',width: 80,header:'Error Count',dataIndex:'errorcount', renderer: add_thousands_separator },			
			{id:'viewcount',width:80,header:'View Count',dataIndex:'viewcount',renderer: add_thousands_separator }				
		],
	       viewConfig: {
            forceFit: false,
            emptyText: 'No Placements Found'
        },
		stripeRows: true,
		title: 'Current Placements',
		height: 350,
		width: 900
	});
	
	grid.render('placement-grid');
	});
	</script>
	</head>

	<body>
	<div>

	<div><c:url var="url" value="/donatewidget.htm" />

	<div class="header"><span>Donate Widget</span></div>

	<form:form action="${url}" commandName="donateWidget">
		<form:hidden path="id" />

		<fieldset>
<table>
		<tr>
		<td align="right"><label for="projectCode"><fmt:message	key="donateWidget.form.projectCode" />:</label></td>
		 <td><form:select	path="projectCode" items="${donateWidget.projectCodeList}" itemValue="itemName" itemLabel="defaultDisplayValue"/></td>
	</tr>

		
	<tr>
			<td align="right"><label for="motivationCode"><fmt:message key="donateWidget.form.motivationCode" />:</label></td>			
		 <td><form:select	path="motivationCode" items="${donateWidget.motivationCodeList}" itemValue="itemName" itemLabel="defaultDisplayValue" /></td>			
</tr>
</table>

		<div><label for="widgetHTML"><fmt:message
			key="donateWidget.form.widgetHTML" />:</label> <form:textarea
			rows="20" cols="75" readonly="true" path="widgetHTML" /></div>


		<div ><input type="submit" class="button"
			value="<fmt:message key="donateWidget.button.save"/>" /></div>

		</fieldset>
	</form:form></div>
	<div id="placement-grid"/>
	</div>
	</body>
</page:applyDecorator>