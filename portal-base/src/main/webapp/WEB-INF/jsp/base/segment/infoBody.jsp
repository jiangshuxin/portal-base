<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.lang.annotation.Annotation"%>
<%@page import="org.springframework.core.annotation.AnnotationUtils"%>
<%@page import="com.newray.base.web.list.annotation.*"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.*"%>
<%@page import="java.beans.PropertyDescriptor"%>
<%@page import="org.springframework.beans.BeanUtils"%>
<%
	Class<?> _clazz = (Class<?>)request.getAttribute(InfoPageAction.CLASS_KEY);
	String _daoName = (String)request.getAttribute(InfoPageAction.ROUTER_KEY);
%>			
	<div id="info_container">
		<table id="_info_data_region"></table>
	</div>
		
	<input type="hidden" id="_pageContext" value="${pageContext.request.contextPath}">
	<input type="hidden" id="_fields" value="<%=getFields(_clazz)%>">
	<input type="hidden" id="_dao_prefix" value="<%=_daoName%>">
	
	<table>
<%
	PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(_clazz);
	pds = com.newray.base.util.ReflectUtil.filter(pds, ColumnUI.class);
	pds = makeOrder(pds,new com.newray.base.web.list.comparator.AnnoOrderComparator(ColumnUI.class)).toArray(new PropertyDescriptor[]{});
	for(int i=0;i<pds.length;i++){
		PropertyDescriptor pd = pds[i];
		Method readMethod = pd.getReadMethod();
		if(readMethod.isAnnotationPresent(ColumnUI.class)){
			ColumnUI col = readMethod.getAnnotation(ColumnUI.class);
	%>
		<tr>
			<td><%=pd.getName() %></td>
		</tr>
		<%} %>
	<%} %>
  <tr>
    <th>Column 1 Heading</th>
    <th>Column 2 Heading</th>
  </tr>
  <tr>
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
  </tr>
</table>


<script>
	$(function(){
		var pageContext = $('#_pageContext').val();
		//var fields = eval("("+$('#_fields').val()+")");
		var fields = $('#_fields').val();
		alert(fields);
		var dao_prefix = $('#_dao_prefix').val();
		$.each(fields, function(i, item){
			alert(item);
			alert("nammi@item.title="+item.title+";item.field="+item.field);
			$('#_info_data_region').append(
				"<tr>"+
				"<td>"+item.title+"</td>"+
				"<td>"+"<input type='text' id='"+item.field+"'></td>"+
				"</tr>"		
			);
		});
		//for(var i=0; i<)
		
	});
</script>
<%!//作为info页面的公共部分进行抽取
	public String getFields(Class<?> clazz) throws NoSuchFieldException{
		StringBuilder sb = new StringBuilder();
		sb.append("[[");
		
		boolean flag = false;
		
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
		pds = com.newray.base.util.ReflectUtil.filter(pds, ColumnUI.class);
		pds = makeOrder(pds,new com.newray.base.web.list.comparator.AnnoOrderComparator(ColumnUI.class)).toArray(new PropertyDescriptor[]{});
		for(int i=0;i<pds.length;i++){
			PropertyDescriptor pd = pds[i];
			Method readMethod = pd.getReadMethod();
			if(readMethod.isAnnotationPresent(ColumnUI.class)){
				ColumnUI col = readMethod.getAnnotation(ColumnUI.class);
				if(col.frozen()) continue;
				if(flag) sb.append(",");
				sb.append("{");
				appendAttrByPropName(col,sb,"field",true,pd);
				appendAttr(col,sb,"title",true);
				/* appendAttr(col,sb,"width",true);
				appendAttr(col,sb,"sortable",true);
				appendAttr(col,sb,"resizable",true);
				appendAttr(col,sb,"hidden",true);
				appendAttr(col,sb,"rowspan",true);
				appendAttr(col,sb,"colspan",true);
				//appendAttr(col,sb,"align",true);
				appendAttr(col,sb,"checkbox",false); */
				sb.append("}");
				flag = true;
			}
		}
		sb.append("]]");
		System.out.println("nammi@sb="+sb.toString());
		return sb.toString();
	}

	
	private SortedSet<PropertyDescriptor> makeOrder(PropertyDescriptor[] pds,Comparator<PropertyDescriptor> comps){
		SortedSet<PropertyDescriptor> set = new TreeSet<PropertyDescriptor>(comps);
		for(PropertyDescriptor pd : pds){
			set.add(pd);
		}
		return set;
	}
	
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
	
%>