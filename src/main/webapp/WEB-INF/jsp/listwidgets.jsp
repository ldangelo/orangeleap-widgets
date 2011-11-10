<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
	<script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/adapter/jquery/ext-jquery-adapter.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
	<script type="text/javascript" src="js/listWidgets.js"></script>
</head>
<body>
	<h3 class="heading">Current Widgets</h3>
	<div id='grid-example'></div>
	<h3 class="heading" style="display:none" id="widgetPlacementTitle">Widget Placement</h3>
	<div id="placements-div"></div>
</body>
</page:applyDecorator>