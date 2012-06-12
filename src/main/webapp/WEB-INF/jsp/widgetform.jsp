<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Edit Widget - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
			<src>/js/dynamicform.js</src>
			<src>/js/editWidget.js</src>
		</pack:script>
	</head>
	<body>
		<div id="widgetform-div"></div>
		<div id="placements-div"></div>
	</body>
</page:applyDecorator>
