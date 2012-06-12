<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Current Styles - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/liststyles.js</src>
		</pack:script>
    </head>
    <body>
		<h3 class="heading">Widget Styles</h3>
		<div id="styles-div"></div>
	</body>
</page:applyDecorator>

