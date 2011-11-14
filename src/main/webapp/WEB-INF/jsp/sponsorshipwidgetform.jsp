<%@ include file="include.jsp" %>


<page:applyDecorator name="main">
	<head>
		<title>Edit Sponsorship Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var sponsorshipform = new WidgetForm({widgettype:'customentity',customentitytype:'online_sponsorship'});
				sponsorshipform.render('sponsorshipform-div');
			});
		</script>
    </head>
    <body>
		<h3 class="heading">Sponsorship Widget</h3>
		<div id="sponsorshipform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
