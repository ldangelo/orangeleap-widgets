<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Sponsorable Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var sponsorableform = new WidgetForm({widgettype:'customentity',customentitytype:'sponsorable'});
				sponsorableform.render('sponsorableform-div');
			});
		</script>
    </head>
    <body>
		<h3 class="heading">Sponsorable Widget</h3>
		<div id="sponsorableform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
