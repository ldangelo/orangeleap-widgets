<%@ include file="include.jsp" %>


<page:applyDecorator name="main">
	<head>
		<title>Edit Pledge Card Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var pledgecardform = new WidgetForm({widgettype:'customentity',customentitytype:'pledge_card'});
				pledgecardform.render('pledgecardform-div');
			});
		</script>
    </head>
    <body>
		<h3 class="heading">Pledge Card Widget</h3>
		<div id="pledgecardform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
