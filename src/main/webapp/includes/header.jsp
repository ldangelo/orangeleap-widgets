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
	</ul>
</div>
