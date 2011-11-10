<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/adapter/ext/ext-base-debug.js"></script>
	<script type="text/javascript" src="js/extjs/adapter/jquery/ext-jquery-adapter.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/dynamicform.js"></script>
	<script type="text/javascript" src="js/widgetform.js"></script>
	<script type='text/javascript' src='dwr/interface/OrangeLeapWidget.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript" src="js/createWidget.js"></script>
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
