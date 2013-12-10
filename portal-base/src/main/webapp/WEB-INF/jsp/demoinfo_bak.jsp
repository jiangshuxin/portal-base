<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader.jsp" %>
<%@ page import="com.newray.base.web.controller.InfoPageAction" %>
<script type="text/javascript" src="${ctx}/js/jquery-easyui-1.3/newray/framework/info.js"></script>
<html>
<head>
<meta http-equv="Content-Type" content="text/html; charset=UTF-8">
<title>demoinfo</title>
</head>
<body>
	<div style="float:left">
		<div style="float:left">
			<h2>特殊字段类型</h2>
			<table>
				<tr>
					<td>必输项：</td>
					<td><input class="easyui-validatebox" required="true" missingMessage="必输项"></input></td>
				</tr>
				<tr>
					<td>金额：</td>
					<td><input class="easyui-numberbox" groupSeparator="," decimalSeparator="." precision="2"></input></td>
				</tr>
				<tr>
					<td>数值微调器：</td>
					<td><input id="numberspinner" class="easyui-numberspinner" min="0" max="10" required="true">(numberspinner)</input>
						<a href="#" class="easyui-linkbutton" onclick="disableNum()">Disable</a>
						<a href="#" class="easyui-linkbutton" onclick="enableNum()">Enable</a>
					</td>
				</tr>
				<tr>
					<td>进度条1：</td>
					<td>
						<a href="#" class="easyui-linkbutton" onclick="showProgress()">showProgressDialogue</a>
					</td>
				</tr>
				<tr>
					<td>进度条2：</td>
					<td>
						<a href="#" class="easyui-linkbutton" onclick="testProgress()">TestProgressbar</a>
						<div id="p" class="easyui-progressbar" style="width:400px;"></div>
					</td>
				</tr>				
				<tr>
					<td>日期：</td>
					<td><input id="date" class="easyui-datebox"></input></td>
				</tr>
				<tr>
					<td>时间：</td>
					<td><input id="datetime" class="easyui-datetimebox"></input></td>
				</tr>
				<tr>
					<td>单选下拉框：</td>
					<td><select id="simplecom" class="easyui-combobox" required="true">
							<option value="bj">Beijing</option>
							<option value="sh">Shanghai</option>
							<option value="tj">Tianjin</option>
							<option value="cq">Chongqing</option>
						</select>
					<a href="#" class="easyui-linkbutton" onclick="disableCom()">Disable</a>
					<a href="#" class="easyui-linkbutton" onclick="enableCom()">Enable</a></td>
				</tr>
				<tr>
					<td>多选下拉框：</td>
					<td>
						<!-- 
						<input class="easyui-combobox" name="language" url="${ctx}/js/jquery-easyui-1.3/demo/combobox_data.json"
								valueField="id" textField="text" multiple="true" panelHeight="auto">
						</input>
						 -->
					</td>
				</tr>			
			</table>
		</div>
		<div style="float:left">
			<h2>输入校验</h2>
			<table>
				<tr>
					<td>仅限数字：</td>
					<td><input class="easyui-validatebox" validType="intorfloat" required="false" missingMessage="必填项" invalidMessage="请输入数字，并确保格式正确"></input></td>
				</tr>
				<tr>
					<td>数字范围限制：</td>
					<td><input class="easyui-numberbox" min="5.5" max="20" precision="2" missingMessage="填写5.5~20之间的数字"></input></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input class="easyui-validatebox" validType="username" missingMessage="字母开头，其后允许字母、数字、下划线，6~16个字符" invalidMessage="用户名不合法"></input>(<font color="red" size="2px">字母开头，其后允许字母、数字、下划线，6~16个字符</font>)</td>
				</tr>	
				<tr>
					<td>字符串长度：</td>
					<td><input class="easyui-validatebox" validType="length[0,10]" missingMessage="填写少于10个字符" invalidMessage="不能超过10个字符"></input></td>
				</tr>				
				<tr>
					<td>仅限中文：</td>
					<td><input class="easyui-validatebox" validType="chinese" invalidMessage="仅限中文"></input></td>
				</tr>
				<tr>
					<td>仅限手机号：</td>
					<td><input class="easyui-validatebox" validType="mobile" required="false" missingMessage="必填项" invalidMessage="请输入有效的手机号"></input></td>
				</tr>
				<tr>
					<td>仅限电话号码：</td>
					<td><input class="easyui-validatebox" validType="phone" required="false" missingMessage="必填项" invalidMessage="请输入有效的电话号码"></input></td>
				</tr>
				<tr>
					<td>仅限身份证：</td>
					<td><input class="easyui-validatebox" validType="idcard"  required="false" missingMessage="请输入身份证号" invalidMessage="身份证格式不正确"></input></td>
				</tr>					
				<tr>
					<td>仅限邮箱地址：</td>
					<td><input class="easyui-validatebox" validType="email" required="false" invalidMessage="请输入有效邮箱"></input></td>
				</tr>
				<tr>
					<td>仅限URL：</td>
					<td><input class="easyui-validatebox" validType="url" required="false" invalidMessage="请输入有效网址"></input></td>
				</tr>
				<tr>
					<td>IP地址：</td>
					<td><input class="easyui-validatebox" validType="url" required="false" invalidMessage="请输入有效IP地址"></input></td>
				</tr>
				<tr>
					<td>车牌号：</td>
					<td><input class="easyui-validatebox" validType="carno" required="false" invalidMessage="请输入有效车牌号"></input></td>
				</tr>
				<tr>
					<td>发动机号：</td>
					<td><input class="easyui-validatebox" validType="carenergin" required="false" invalidMessage="请输入有效发动机号"></input></td>
				</tr>				
			</table>
		</div>
		<div style="float:left">
			<h2>查看记录</h2>
			<div>
				<input type="button" value="新增记录" onclick="addRecord()">
				<input type="button" value="查看记录" onclick="viewRecord()">
			</div>
			<div id="recordDiv">
				<form:form id="recordForm"  modelAttribute="form" action="${ctx}/info/{daoName}/saveInfoForm.htm" method="post" >
					<table id="recordTable">
						<tr>
							<td><label for="sex">性别:</label></td>
							<td><input class="easyui-validatebox" required="true" missingMessage="必输项" name="sex"></input></td>
						</tr>
						<tr>
							<td><label for="isMarried">已婚:</label></td>
							<td><input type="text" name="isMarried"></input></td>
						</tr>
						<tr>
							<td><label for="birthDate">出生日期:</label></td>
							<td><input type="text" name="birthDate"></input></td>
						<tr>
							<td><label for="height">身高(M):</label></td>
							<td><textarea name="height" style="height: 60px;"></textarea></td>
						<tr>
							<td><label for="inputDate">填写日期:</label></td>
							<td><textarea name="inputDate" style="height: 60px;"></textarea></td>
						</tr>
					</table>
					<!-- <input type="button" value="保存" onclick="info_form_submit(this.form)"> -->
					<input type="submit" value="保存">
					<input type="button" value="关闭" onclick="info_form_close()">
				</form:form>
			</div>
		</div>
	</div>
	<%
		Class<?> _clazz = (Class<?>) request.getAttribute(InfoPageAction.CLASS_KEY);
		String _daoName = (String) request.getAttribute(InfoPageAction.ROUTER_KEY);
	%>
	<input type="hidden" id="_pageContext" value="${pageContext.request.contextPath}">
	<input type="hidden" id="_dao_prefix" value="<%=_daoName%>">
	<%-- <input type="hidden" id="_queryCond" value="<%=buildQueryJSON(_clazz)%>"> --%>
</body>
</html>
