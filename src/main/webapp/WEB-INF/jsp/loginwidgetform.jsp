<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Login Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
		</pack:script>
		<script type="text/javascript">
			Ext.onReady(function() {
				var loginform = new WidgetForm({widgettype:'customentity',customentitytype:'widget_authentication'});
				loginform.render('loginform-div');
			});
		</script>
    </head>
    <body>
		<h3 class="heading">Login Widget</h3>
		<div id="loginform-div"></div>
		<div id="placements-div"></div>
    </body>
</page:applyDecorator>
