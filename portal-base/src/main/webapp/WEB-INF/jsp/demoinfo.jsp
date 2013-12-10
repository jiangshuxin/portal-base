<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.lang.annotation.Annotation"%>
<%@page import="org.springframework.core.annotation.AnnotationUtils"%>
<%@page import="com.newray.base.web.info.annotation.*"%>
<%@page import="com.newray.base.web.controller.InfoPageAction"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.*"%>
<%@page import="java.beans.PropertyDescriptor"%>
<%@page import="org.springframework.beans.BeanUtils"%>

<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader.jsp" %>
<script src="${ctx}/js/jquery-easyui-1.3/newray/framework/info.js" type="text/javascript"></script>
<%
	Class<?> _clazz = (Class<?>)request.getAttribute(InfoPageAction.CLASS_KEY);
	String _daoName = (String)request.getAttribute(InfoPageAction.ROUTER_KEY);
%>			
<div id="info_container">
	<table id="_info_data_region"></table>
</div>
	
<input type="hidden" id="_pageContext" value="${pageContext.request.contextPath}">
<input type="hidden" id="_dao_prefix" value="<%=_daoName%>">

<form>
	<table>
	<%
	PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(_clazz);
	pds = com.newray.base.util.ReflectUtil.filter(pds, InfoUI.class);
	pds = makeOrder(pds,new com.newray.base.web.list.comparator.AnnoOrderComparator(InfoUI.class)).toArray(new PropertyDescriptor[]{});
	
	//groupId统计List
	List<String> groupList = new ArrayList<String>();
	Map<String,String> groupMap = new HashMap<String,String>();
	
	for(int i=0;i<pds.length;i++){
		PropertyDescriptor pd = pds[i];
		Method readMethod = pd.getReadMethod();
		if(readMethod.isAnnotationPresent(InfoUI.class)){
			InfoUI infoUI = readMethod.getAnnotation(InfoUI.class);
			
			//分组
			String groupId = infoUI.groupId();
			String title = infoUI.title();
			if(groupId!=null && !"".equals(groupId)){
				groupMap.put(title, groupId);
				if(!groupList.contains(groupId)){
					groupList.add(groupId);
				}else{
					
				}
			}
	%>
		<tr>
			<td><%=infoUI.title() %></td>
			<td>
				<input type="text" id="<%=infoUI.id() %>" class="<%=infoUI.validClass() %>" required="<%=infoUI.isRequired() %>"  missingMessage="必输项">
			</td>
		</tr>
		<%} %>
	<%} %>
	</table>
	<%
		//遍历分组生成fieldset
		for(int j=0; j<groupList.size(); j++){
			String groupId = groupList.get(j);
	%>
		<fieldset>
			<legend><%=groupId  %></legend>
			<%
				Set<String> mapSet = groupMap.keySet();
				Iterator<String> itor = mapSet.iterator();
				while(itor.hasNext()){
					String key = itor.next();
					String value = groupMap.get(key);
					if(groupId.equals(value)){
			%>
						<%=key %>:<input type="text">	
			<%	
					}
				}
			%>
		</fieldset>	
	<% }%>
	
</form>


<script>

	$(function(){
		var pageContext = $('#_pageContext').val();
		var fields = eval("("+$('#_fields').val()+")");
		var dao_prefix = $('#_dao_prefix').val();
	});
</script>
<%!//作为info页面的公共部分进行抽取
	private SortedSet<PropertyDescriptor> makeOrder(PropertyDescriptor[] pds,Comparator<PropertyDescriptor> comps){
		SortedSet<PropertyDescriptor> set = new TreeSet<PropertyDescriptor>(comps);
		for(PropertyDescriptor pd : pds){
			set.add(pd);
		}
		return set;
	}
	
	/*
	private void appendAttr(Annotation ann,StringBuilder sb,String attr,boolean needCom) throws NoSuchFieldException{
		sb.append(attr);
		sb.append(":");
		Map<String,Object> map = AnnotationUtils.getAnnotationAttributes(ann);
		if(map.get(attr) instanceof String){
			sb.append("'");
			sb.append(AnnotationUtils.getValue(ann, attr));
			sb.append("'");
		}else sb.append(AnnotationUtils.getValue(ann, attr));
		
		if(needCom) sb.append(",");
	}
	
	private void appendAttrByPropName(Annotation ann,StringBuilder sb,String attr,boolean needCom,PropertyDescriptor pd) throws NoSuchFieldException{
		sb.append(attr);
		sb.append(":");
		Map<String,Object> map = AnnotationUtils.getAnnotationAttributes(ann);
		
		Object val = AnnotationUtils.getValue(ann, attr);
		if(val instanceof String && StringUtils.isEmpty(val.toString())) val = pd.getName();
		
		if(map.get(attr) instanceof String){
			sb.append("'");
			sb.append(val);
			sb.append("'");
		}else sb.append(val);
		
		if(needCom) sb.append(",");
	}
	*/
%>