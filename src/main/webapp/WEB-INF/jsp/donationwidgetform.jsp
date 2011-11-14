<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Donation Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var registrationform = new WidgetForm({widgettype:'customentity',customentitytype:'online_donation'});
				registrationform.render('donationform-div');
			});  // Ext.onReady
		</script>
    </head>
    <body>
		<h3 class="heading">Donation Widget</h3>
		<div id="donationform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
