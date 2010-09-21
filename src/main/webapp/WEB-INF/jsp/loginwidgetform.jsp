

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<!--<script type="text/javascript" src="js/extjs/ext-jquery-adapter.js"></script>-->
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
    <script type="text/javascript" src="js/dynamicform.js"></script>
    <script type="text/javascript" src="js/widgetform.js"></script>
	<script type="text/javascript">

	Ext.onReady(function() {
		var loginform = new WidgetForm({widgettype:'customentity',customentitytype:'widget_authentication'});
		loginform.render('loginform-div');
	});  // Ext.onReady
</script>
    </head>
    <body>
    <div id="loginform-div"></div>
    <div id="placements-div"></div>
    </body>
    </page:applyDecorator>
