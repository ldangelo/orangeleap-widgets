<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Donor Profile Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var registrationform = new WidgetForm({widgettype:'customentity',customentitytype:'donor_profile'});
				registrationform.render('donorprofileform-div');
			});
		</script>
    </head>
    <body>
		<h3 class="heading">Donor Profile Widget</h3>
		<div id="donorprofileform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
