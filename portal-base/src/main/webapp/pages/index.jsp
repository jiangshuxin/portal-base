<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader.jsp" %>
<body>
	<a href="${pageContext.request.contextPath}/login_page.htm">登陆</a>
	
	<a href="${pageContext.request.contextPath}/demoList.htm">List</a>
	<a href="${pageContext.request.contextPath}/demoList.htm?password=123456">带单个参数的List</a>
	
	<a href="${pageContext.request.contextPath}/user_infoPage.htm">Info</a>
	
	<button onclick="testAjax();">AjaxSpring</button>
	<button onclick="testMi();">MethodInvoker</button>
	
	<form action="" id="myForm">
		<input name="name" value="sxjiang">
		<select name="sex">
			<option value="1">male</option>
			<option value="0">female</option>
		</select>
	</form>
</body>

<script type="text/javascript">
	function testAjax(){
		var obj = {"name":"pengpeng","person.name":"sxjiang"};
		jQuery.ajax({
			url : '${pageContext.request.contextPath}/index.json',
			contentType : "application/json",//application/xml
			processData : true,//contentType为xml时，些值为false
			dataType : "json",//json--返回json数据类型；xml--返回xml
			cache : false,
			data : obj,
			success : function(data) {
				alert(data);
				for(var k in data){
					alert(k);
				}
			},
			error : function(e) {
				document.write(e);
			}
		});
	}
	
	function testMi(){
		//var result = CommonUtil.methodInvoke('Local_Demo',{"userName":"jiang","password":"12345"},'login','com.newray.base.demo.command.LoginCommand');//'userName=jiang&password=12345'
		//var tt = ["1","2"];
		//alert(tt[1]);
		//var params = {"userName":"jiang","password":"12345"};//,{"name":"peng","age":"15"}
		//var result = CommonUtil.methodInvoke('Local_Demo',['sxjiang','peng'],'getSomeUsers');
		//var result = CommonUtil.methodInvoke('Local_Demo','','login');
		//var result = CommonUtil.methodInvoke('Local_Demo','userName=sxjiang&password=123','getSomeUsers');
		//var result = CommonUtil.methodInvoke('Local_Demo',['sxjiang','peng',{"userName":"jiang","password":"12345"}],'getSomeUsers');
		//var result = CommonUtil.methodInvoke('Local_Demo',['sxjiang'],'getSomeUsers',['java.lang.String']);
		//var result = CommonUtil.methodInvoke('Local_Demo',['sxjiang','peng'],'getSomeUsers',['java.lang.String','java.lang.String']);
		//var result = CommonUtil.methodInvoke('Local_Demo','sxjiang&peng','getSomeUsers',['java.lang.String','java.lang.Integer']);//error
		//var result = CommonUtil.methodInvoke('Local_Demo','sxjiang','getSomeUsers');
		//var result = CommonUtil.methodInvoke('Local_Demo');
		
		var result = CommonUtil.methodInvoke('Local_Dict','YesNo');
		
		for(var k in result){
			alert(result[k]);
		}
	}
</script>
</html>