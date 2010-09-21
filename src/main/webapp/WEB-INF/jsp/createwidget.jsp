<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>



<page:applyDecorator name="main">
<head>
    <script type="text/javascript" src="js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/extjs/ext-base-debug.js"></script>
	<script type="text/javascript" src="js/extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="js/dynamicform.js"></script>
	<script type="text/javascript" src="js/widgetform.js"></script>

        <script type="text/javascript">
        	Ext.onReady(function() {
NewWidgetPanelUi = Ext.extend(Ext.Panel, {
    title: 'Create New Widget',
    width: 750,
    height: 400,
    layout: 'column',
    border: false,
    initComponent: function() {
        this.items = [
            {
                xtype: 'panel',
                title: 'My Panel',
                layout: 'form',
                id: 'toppanel',
                width: 129,
                height: 350,
                border: false,
                header: false,
                headerAsText: false,
                animCollapse: false,
                collapseFirst: false,
                items: [
                    {
                        xtype: 'button',
                        text: 'Login',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'customentity',customentitytype:'widget_authentication'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Donation',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'customentity',customentitytype:'online_donation'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Registration',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({width: 600, height: 300, widgettype:'customentity',customentitytype:'donor_profile'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Donor Profile',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'customentity',customentitytype:'donor_profile'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Gift History',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'gifthistory',customentitytype:'unknown'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Sponsorship',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'customentity',customentitytype:'sponsorship'}));
							formpanel.doLayout();
                        }
                    },
                    {
                        xtype: 'button',
                        text: 'Sponsorable',
                        width: 125,
                        handler: function () {
							var formpanel = Ext.getCmp("formpanel");
							formpanel.removeAll();
							formpanel.add(new WidgetForm({widgettype:'customentity',customentitytype:'sponsorable'}));
							formpanel.doLayout();
                        }
                    }
                ]
            },
            {
                xtype: 'panel',
                title: 'My Panel',
                id: 'formpanel',
                width: 600,
                height: 380,
                headerAsText: false,
                border: false,
                header: false
            }
        ];
        NewWidgetPanelUi.superclass.initComponent.call(this);
    }
});

var widgetpanel = new NewWidgetPanelUi();
widgetpanel.render("widgetpanel-div");
        	});
        </script>
</head>
<body>
   <div id="widgetpanel-div"></div>

</body>
</page:applyDecorator>