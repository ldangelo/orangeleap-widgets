<%@ include file="include.jsp" %>

<page:applyDecorator name="main">
	<head>
		<title>Current Styles - <spring:message code="appName"/></title>
		<pack:script enabled='true'>
			<src>/js/listplacements.js</src>
		</pack:script>

		<script type="text/javascript">
			Ext.onReady(function() {

            Ext.QuickTips.init();
			var mystyledatasource = null;

			var styleproxy = new Ext.data.HttpProxy( {api: {
				read: 'style.ajax?action=list'
			}}); // Ext.data.HttpProxy

			var stylereader = new Ext.data.JsonReader({
				totalProperty: 'totalRows',
				successProperty: 'success',
				idProperty: 'id',
				root: 'rows',
				messageProperty: 'message'
			});

			mystyledatastore = new Ext.data.Store({
				metaData:true,
				reader:stylereader,
				proxy:styleproxy,
				autoSave:false,
				listeners: {

					'metachange':function(store,meta) {
					   var fields = meta.fields;
						// has the right definition

						var columns = [];
						for (var f=0;f < fields.length; f++) {
							var thisWidth = 275;
							if (fields[f].name == 'Inactive') {
								thisWidth = 50;
							}
							else if (fields[f].name == 'Id') {
								thisWidth = 150;
							}
							columns.push({
								header: fields[f].header,
								dataIndex: fields[f].name,
								sortable:true,
								width: thisWidth,
								renderer: function(value, metaData, record, rowIndex, colIndex, store) {
									var title = '';
									var dataIndex = this.dataIndex ? this.dataIndex : (this.scope ? this.scope.dataIndex : null);
									if (dataIndex && record.fields.map && record.fields.map[dataIndex]) {
										if (record.fields.map[dataIndex].header) {
											title = record.fields.map[dataIndex].header;
										}
									}
									return '<span ext:qtitle="' + title + '"ext:qwidth="400" ext:qtip="' + value + '" ext:qclass="constrainText">' + value + '</span>';
								}
							});
						}

						stylegrid.reconfigure(store,new Ext.grid.ColumnModel(columns));
					},
					'load':function(store,records,options) {
							panel.render("styles-div");
					}
				}});


			var stylegrid = new Ext.grid.GridPanel({
				store: mystyledatastore,
				columns: [],
				viewConfig: {
					emptyText: 'No Styles'
				},
				stripeRows: true,
				height: 250,
				width:900,
				tbar: {
					items: [
						{
							xtype: 'button',
							'text': 'Add New Style',
							'tooltip': 'Add New Style',
							'iconCls': 'add',
							'id': 'addNewButton',
							'handler': function() {
								window.location.href = 'styleform.htm';
							}
						}
					]
				}
			});
			stylegrid.on('rowdblclick', function(grid, rowIndex, event) {

				var record = mystyledatastore.getAt(rowIndex);
				if (record) {
					// open window to view record
					var thisUrl = 'styleform.htm?id=' + record.get('Id');

					window.location.href = thisUrl;
				}
			});

			var panel = new Ext.Panel({
				height: 350,
				width: 900,
				border: false,
				items:[ stylegrid ]
			});

			mystyledatastore.load();

			});
		</script>
    </head>
    <body>
		<h3 class="heading">Widget Styles</h3>
		<div id="styles-div"></div>
	</body>
</page:applyDecorator>

