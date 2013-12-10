<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ 
page import="java.util.*" %><%@ 
page import="org.apache.commons.lang.*" %><%@ 
taglib prefix="s" uri="http://www.springframework.org/tags"%><%@ 
taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ 
taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script src="${ctx}/js/jquery-easyui-1.3/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common/mi.js" type="text/javascript"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>
<script type="text/javascript">
	<%//定义全局变量%>
	var CONTEXT_PATH = '${ctx}';
</script>