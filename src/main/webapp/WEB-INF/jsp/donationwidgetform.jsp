

<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/adapter/jquery/ext-jquery-adapter.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/listplacements.js"></script>
	    <script type="text/javascript" src="js/dynamicform.js"></script>
	    <script type="text/javascript" src="js/widgetform.js"></script>
	<script type="text/javascript">
	Ext.onReady(function() {
		var registrationform = new WidgetForm({widgettype:'customentity',customentitytype:'online_donation'});
		registrationform.render('donationform-div');
	});  // Ext.onReady
</script>
    </head>
    <body>
	<h3 class="heading">Donation Widget</h3>
    <div id="donationform-div"></div>
    <div id="placements-div"></div>
    </body>
    </page:applyDecorator>
