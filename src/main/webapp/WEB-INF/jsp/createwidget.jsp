<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Create Widget - <spring:message code="appName"/></title>
		<script type='text/javascript' src='dwr/interface/OrangeLeapWidget.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<pack:script enabled='false'>
			<src>/js/dynamicform.js</src>
			<src>/js/widgetform.js</src>
			<src>/js/createWidget.js</src>
		</pack:script>
		<style type="text/css">
			#widgetOptions { margin-bottom: 15px; }
			#widgetWrapper { background-color: #eee; padding: 10px; }
		</style>
	</head>
	<body>
		<h3 class="heading">Create New Widget</h3>
		<div id="widgetWrapper">
			<div id="widgetOptions"></div>
			<div id="widgetpanel-div"></div>
		</div>
	</body>
</page:applyDecorator>
