Ext.onReady(function() {
	var widgetTypes = [];

	OrangeLeapWidget.getRoles(function(data) {
		if (data.match("WEBTOOLS_DONORPORTAL")) {
			widgetTypes.push(['registration', 'Registration']);
			widgetTypes.push(['login', 'Login']);
			widgetTypes.push(['donorProfile', 'Donor Profile']);
			widgetTypes.push(['giftHistory', 'Gift History']);
			widgetTypes.push(['pledge', 'Pledge']);
		}
		if (data.match("WEBTOOLS_DONATION")) {
			widgetTypes.push(['donation', 'Donation']);
			widgetTypes.push(['recurringGift', 'Recurring Gift']);
		}
		if (data.match("WEBTOOLS_SPONSORSHIP")) {
			widgetTypes.push(['sponsorable', 'Sponsorable']);
			widgetTypes.push(['sponsorship', 'Sponsorship']);
		}

		createPanels();
	});

    function createPanels() {

		var NewWidgetPanelUi = Ext.extend(Ext.Panel, {
			width: 900,
			height: 400,
			layout: 'column',
			border: false,
			initComponent: function() {
				this.items = [
					{
						xtype: 'panel',
						title: 'My Panel',
						id: 'formpanel',
						width: 900,
						height: 380,
						headerAsText: false,
						border: false,
						header: false
					}
				];
				NewWidgetPanelUi.superclass.initComponent.call(this);
			}
		});
		var widgetCombo = new Ext.form.ComboBox({
			name: 'widgetType',
			id: 'widgetType',
			renderTo: 'widgetOptions',
			store: new Ext.data.ArrayStore({
				fields: [
					'value',
					'desc'
				],
				data: widgetTypes
			}),
			displayField: 'desc',
			valueField: 'value',
			typeAhead: true,
			enableKeyEvents: true,
			mode: 'local',
			forceSelection: true,
			triggerAction: 'all',
			emptyText: ' ',
			width: 250,
			listeners: {
				'change': function(combobox, newValue, oldValue) {
					showWidgetForm(newValue);
				},
				'select': function(combobox, record, index) {
					showWidgetForm(combobox.getValue());
				},
				scope: this
			}
		});

		var widgetpanel = new NewWidgetPanelUi();
		widgetpanel.render("widgetpanel-div");
    }

    function showWidgetForm(newValue) {
		var formpanel = Ext.getCmp("formpanel");
		formpanel.el.mask('Loading...', 'x-mask-loading');
		formpanel.removeAll();

		var widgetFormParams;

		if ('login' == newValue) {
			widgetFormParams = { widgettype:'customentity', customentitytype:'widget_authentication' };
		}
		else if ('donation' == newValue) {
			widgetFormParams = { widgettype:'customentity', customentitytype:'online_donation' };
		}
		else if ('pledge' == newValue) {
			widgetFormParams = { widgettype:'pledges', customentitytype:'undefined' };
		}
		else if ('recurringGift' == newValue) {
			widgetFormParams = { widgettype:'customentity', customentitytype:'online_recurringgift' };
		}
		else if ('registration' == newValue) {
			widgetFormParams = { widgettype:'customentity', customentitytype:'online_registration' };
		}
		else if ('donorProfile' == newValue) {
			widgetFormParams = { widgettype:'customentity', customentitytype:'donor_profile' };
		}
		else if ('giftHistory' == newValue) {
			widgetFormParams = { widgettype:'gifthistory',customentitytype:'undefined' };
		}
		else if ('sponsorship' == newValue) {
			widgetFormParams = { widgettype:'customentity',customentitytype:'online_sponsorship' };
		}
		else if ('sponsorable' == newValue) {
			widgetFormParams = { widgettype:'customentity',customentitytype:'sponsorable' };
		}
		formpanel.add(new WidgetForm(widgetFormParams));
		formpanel.doLayout();
		formpanel.el.unmask();
    }
});