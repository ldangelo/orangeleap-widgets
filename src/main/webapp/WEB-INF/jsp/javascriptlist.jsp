<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Current Javascripts - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listJavascripts.js</src>
		</pack:script>
    </head>
    <body>
		<h3 class="heading">Widget Javascripts</h3>
		<div id="javascripts-div"></div>
	</body>
</page:applyDecorator>

