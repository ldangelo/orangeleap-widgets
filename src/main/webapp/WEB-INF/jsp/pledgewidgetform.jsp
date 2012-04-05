<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Pledge Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var pledgeform = new WidgetForm({widgettype:'pleges',customentitytype:'undefined'});
				pledgeform.render('pledge-div');
			});
		</script>
	</head>
	<body>
		<h3 class="heading">Pledge Widget</h3>
		<div id="pledge-div"></div>
		<div id="placements-div"></div>
	</body>
</page:applyDecorator>