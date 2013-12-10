<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.*"%>
<%@page import="java.lang.annotation.Annotation"%>
<%@page import="org.springframework.core.annotation.*"%>
<%@page import="com.newray.base.web.list.annotation.*"%>
<%@page import="com.newray.base.web.controller.ListPageAction"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.*"%>
<%@page import="java.beans.PropertyDescriptor"%>
<%@page import="org.springframework.beans.*"%>

<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader_EasyUI.jsp" %>
<script type="text/javascript">
	<%//定义List页面全局变量%>
	var LIST_QUERY_ACTION_SUFFIX = '<%=ListPageAction.LIST_QUERY_ACTION_SUFFIX%>';
</script>
<script src="${ctx}/js/jquery-easyui-1.3/newray/framework/list.js" type="text/javascript"></script>

<%
	Class<?> _clazz = (Class<?>)request.getAttribute(ListPageAction.CLASS_KEY);
	String _routerKey = (String)request.getAttribute(ListPageAction.ROUTER_KEY);
	Map<String,String[]> paramMap = (Map<String,String[]>)request.getAttribute(ListPageAction.PARAMS_KEY);
%>	

<%!//作为list页面的公共部分进行抽取
	public String getColumns(Class<?> clazz) throws NoSuchFieldException{
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
				appendAttr(col,sb,"width",true);
				appendAttr(col,sb,"sortable",true);
				appendAttr(col,sb,"resizable",true);
				appendAttr(col,sb,"hidden",true);
				appendAttr(col,sb,"rowspan",true);
				appendAttr(col,sb,"colspan",true);
				appendAttr(col,sb,"align",true);
				appendAttr(col,sb,"code",true);
				appendAttr(col,sb,"checkbox",false);
				sb.append("}");
				flag = true;
			}
		}
		sb.append("]]");
		return sb.toString();
	}

	public String getFrozenColumns(Class<?> clazz) throws NoSuchFieldException{
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
				if(!col.frozen()) continue;
				if(flag) sb.append(",");
				sb.append("{");
				appendAttrByPropName(col,sb,"field",true,pd);
				appendAttr(col,sb,"title",true);
				appendAttr(col,sb,"width",true);
				appendAttr(col,sb,"sortable",true);
				appendAttr(col,sb,"resizable",true);
				appendAttr(col,sb,"hidden",true);
				appendAttr(col,sb,"rowspan",true);
				appendAttr(col,sb,"colspan",true);
				appendAttr(col,sb,"align",true);
				appendAttr(col,sb,"code",true);
				appendAttr(col,sb,"checkbox",false);
				sb.append("}");
				flag = true;
			}
		}
		sb.append("]]");
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
	
	/**
	*形如：
	* key1:value1,
	* key2:value2,
	* key3:value3
	*注意最后没有括号
	**/
	public String appendAllAttrs(Annotation ann) throws NoSuchFieldException{
		StringBuilder sb = new StringBuilder();
		Map<String,Object> map = AnnotationUtils.getAnnotationAttributes(ann);
		Set<String> keys = map.keySet();
		int index = 0;
		for(String key : keys){
			//model层的排序配置不放到页面端
			if("sortName".equalsIgnoreCase(key) || "sortOrder".equalsIgnoreCase(key))continue;
			sb.append(key);
			sb.append(":");
			if(map.get(key) instanceof String){
				sb.append("'");
				sb.append(AnnotationUtils.getValue(ann, key));
				sb.append("'");
			}else sb.append(AnnotationUtils.getValue(ann, key));
			
			if(index++ != keys.size() - 1) sb.append(",");
		}
		return sb.toString();
	}
	
	/**
	*尝试获取ann的attr属性，如果为空则使用pd的属性名
	**/
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
	
	/* public String buildQueryJSON(Class<?> clazz){
		StringBuilder sb = new StringBuilder();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
		pds = com.newray.base.util.ReflectUtil.filter(pds, QueryCondition.class);
		
		sb.append("{");
		
		for(int i=0;i<pds.length;i++){
			sb.append("'queryCommand["+i+"].fieldName':'',");
			sb.append("'queryCommand["+i+"].operation':'',");
			sb.append("'queryCommand["+i+"].queryText1':'',");
			sb.append("'queryCommand["+i+"].queryText2':'',");
		}
		
		sb.append("}");
		return sb.toString();
	} */%>