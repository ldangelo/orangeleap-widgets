<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Gift History Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var gifthistoryform = new WidgetForm({widgettype:'gifthistory',customentitytype:'undefined'});
				gifthistoryform.render('gifthistory-div');
			});
		</script>
	</head>
	<body>
		<h3 class="heading">Gift History Widget</h3>
		<div id="gifthistory-div"></div>
		<div id="placements-div"></div>
	</body>
</page:applyDecorator>
