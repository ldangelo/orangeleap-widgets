<%@ include file="/WEB-INF/jsp/include.jsp" %>

<donorwidgets:auth/>

<div id="banner">
	<ol>
		<li id="greeting">
			Welcome, <c:out value="${donorWidgetsUserName}"/>
		</li>
		<li>
			<a id="sec-changepwd" href="#">Change Password</a>
		</li>
		<li>
			<a href="logout">Logout</a>
		</li>
		<li>
			<a href="#" onclick="clearCache();" >Clear Cache</a>		
		</li>		
	</ol>
</div>
<div id="navmain">
	<ul>
		<li>
			<a href="listwidgets.htm">Widgets</a>
		</li>
	<%--
		<li>
			<a href="reports.htm">Reports</a>
		</li>
	--%>
		<li>
			<a href="stylelist.htm">Styles</a>
		</li>
		<li>
			<a href="site.htm">Settings</a>
		</li>		
	</ul>
</div>

<script>
function clearCache() {
	var url = 'clearCache.json';
	
	$.ajax({
		type: "POST",
		url: url,
		cache: false,
		success: function(data) {
			if (data.indexOf('clearCacheSuccess') != -1) {
				Ext.Msg.show({
					title: 'Success',
					msg: 'Cache successfully cleared.',
					modal: true,
					icon: Ext.Msg.INFO,
					buttons: Ext.Msg.OK 
				});
			} else {
				Ext.MessageBox.show({
					'title': 'Error',
					'icon': Ext.MessageBox.ERROR,
					'buttons': Ext.MessageBox.OK,
					'msg': 'The cache was not successfully cleared. ' + data
				});
			}
		},
		error: function(error){
			Ext.MessageBox.show({
				'title': 'Error',
				'icon': Ext.MessageBox.ERROR,
				'buttons': Ext.MessageBox.OK,
				'msg': 'An error occurred attempting to clear cache.  Please see message log for details.'
			});
		}
	});
}
</script>

