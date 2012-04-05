<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Current Widgets - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/listWidgets.js</src>
		</pack:script>
	</head>
	<body>
		<h3 class="heading">Current Widgets</h3>
		<div id='grid-example'></div>
		<h3 class="heading" style="display:none" id="widgetPlacementTitle">Widget Placement</h3>
		<div id="placements-div"></div>
	</body>
</page:applyDecorator>