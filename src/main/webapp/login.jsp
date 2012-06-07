<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><spring:message code="appName"/> - <spring:message code="login"/></title>
		<link href="<c:url value='/css/login.css' />" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" type="image/ico" href="images/favicon.ico" />
		<script type="text/javascript" src="<c:url value='/js/jquery/jquery-1.3.2.min.js' />"></script>
		<script>
			$(function() {
				$('#j_username').focus();

            	if (localStorage) {
            		if (localStorage.getItem('olRememberChecked') === 'false') {
                        $('#rememberUserName').get(0).checked = false;
            		}
                    if (localStorage.getItem('olCachedUserName')) {
                        $('#j_username').val(localStorage.getItem('olCachedUserName'));
                        $('#j_password').focus();
                    }
                    else {
                        $('#j_username').focus();
                    }
            		$('#j_username').bind('change', function(event) {
                    	if ($('#rememberUserName').get(0).checked) {
                    	    localStorage.setItem('olCachedUserName', $(this).val());
                    	}
                    	else {
                    	    localStorage.removeItem('olCachedUserName');
                    	}
            		});
            		$('#rememberUserName').bind('click', function(event) {
            			if ($(this).get(0).checked) {
                    	    localStorage.setItem('olCachedUserName', $('#j_username').val());
                    	    localStorage.setItem('olRememberChecked', 'true');
            			}
            			else {
                    	    localStorage.removeItem('olCachedUserName');
                    	    localStorage.setItem('olRememberChecked', 'false');
            			}
            		});
            	}
            	else {
            		$('#userNameRow, #rememberUserName').hide();
                    $('#username').focus();
            	}
            });
		</script>
	</head>
	<body>
		<div class="loginPane">
			<div class="loginContent">
			<img src="images/donate_nowTrimmed.png" />
				<h1 class="loginHeader"><spring:message code="pleaseSignIn"/></h1>

				<c:if test="${not empty param.login_error}">
					<p style="color:red;padding:0;margin:0;"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
				</c:if>

				<form id="f" name="f" action="<c:url value="/loginProcess" />" method="post">
					<table class="loginInfo">
						<tr>
							<td style="text-align:right"><label for="j_username"><spring:message code="userName"/></label></td>
							<td><input size="30" class="loginField" type="text" name="j_username" id="j_username"  <c:if test="${not empty param.login_error}">value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"</c:if> /></td>
						</tr>
						<tr>
							<td style="text-align:right"><label for="j_password"><spring:message code="password"/></label></td>
							<td><input size="30" class="loginField" type="password" name="j_password" id="j_password" /></td>
						</tr>
                        <tr id="userNameRow">
                            <td colspan="2">
                                <input type="checkbox" value="true" name="rememberUserName" id="rememberUserName" checked="true"/> <spring:message code="rememberUserName"/>
                            </td>
                        </tr>
						<tr>
							<td class="loginButton" colspan="2">
								<input class="loginField" type="hidden" name="sitename" id="sitename" />
								<input class="loginField" type="hidden" name="j_username" id="j_username" />
								<input class="loginButton" name="submit" id="submit" type="submit" value="Sign In"/>
							</td>
						</tr>
						<%--
						<tr>
							<td>&nbsp;</td>
							<td><a href="#">Forgot your password?</a></td>
						</tr>
						--%>
					</table>
				</form>
				<c:if test="${not empty build.version}"><spring:message code="release"/>: </c:if><c:out value="${build.version}"/> <!-- ${build.time}  -->
			</div>
		</div>
	</body>
</html>