<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader.jsp" %>
<body>
	<form:form modelAttribute="form" action="${ctx }/login_form.htm" method="post" target="_self">
		<table>
			<tr>
				<td>用户名：</td>
				<td><form:input path="userName" /></td>
				<td><form:errors path="userName"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password"/></td>
			</tr>
			<tr>
				<td colspan="1"><input type="submit"></td>
			</tr>
		</table>
		<input id="token" name="token" type="hidden" value="<%=System.nanoTime() %>"/>
	</form:form>
</body>

<script>
	
</script>
</html>
