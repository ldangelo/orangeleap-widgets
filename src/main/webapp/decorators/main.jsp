<%--
% This is the main decorator for all SOMECOMPANY INTRANET pages.
% It includes standard caching, style sheet, header, footer and copyright notice.
--%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ include file="/includes/cache.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
 <title><decorator:title default="Donor Widgets Portal" /></title>
 <decorator:head />
 <%@ include file="/includes/style.jsp" %>
 </head>
 <body bgcolor="#FFFFFF">
 <script type="text/javascript">window.status = "Loading: <decorator:title default="INTRANET" />...";</script>
 <%@ include file="/includes/header.jsp"%>
 <table class="mainBodyTable" border="0" cellspacing="0" cellpadding="0">
 <tr>
 <td height="20" nowrap> </td>
 </tr>
 <tr>
 <td width="1%" nowrap> </td>
 <!-- <td width="16%" valign="top" nowrap>
 <script type="text/javascript">window.status = "Loading: Navigation...";</script>
 <%@ include file="/includes/navigation.jsp" %>
 </td>-->
 <td width="2%" nowrap> </td>
 <td valign="top">
 <br>
 <script type="text/javascript">window.status = "Loading: Document body...";</script>
 <div class="docBody"><decorator:body /></div>
 </td>
 <td width="1%" nowrap> </td>
 </tr>
 </table>
 <br>
 <%@ include file="/includes/footer.jsp" %>

 <script type="text/javascript">window.status = "Done";</script>
 </body>
 </html>