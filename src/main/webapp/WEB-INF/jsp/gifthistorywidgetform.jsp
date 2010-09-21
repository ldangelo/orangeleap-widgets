

<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>



<page:applyDecorator name="main">
	<head>
	<script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
	<script type="text/javascript" src="js/dynamicform.js"></script>
	<script type="text/javascript" src="js/widgetform.js"></script>
	<script type="text/javascript">
		Ext.onReady(function() {
			var gifthistoryform = new WidgetForm({widgettype:'gifthistory',customentitytype:'undefined'});
			gifthistoryform.render('gifthistory-div');
		});
	</script>
	</head>
	<body>
	<div id="gifthistory-div"></div>
	<div id="placements-div"></div>
	</body>
</page:applyDecorator>
