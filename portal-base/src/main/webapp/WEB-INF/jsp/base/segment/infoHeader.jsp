<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.lang.annotation.Annotation"%>
<%@page import="org.springframework.core.annotation.AnnotationUtils"%>
<%@page import="com.newray.base.web.list.annotation.*"%>
<%@page import="com.newray.base.web.controller.InfoPageAction"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.util.*"%>
<%@page import="java.beans.PropertyDescriptor"%>
<%@page import="org.springframework.beans.BeanUtils"%>

<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader_EasyUI.jsp" %>
<script src="${ctx}/js/jquery-easyui-1.3/newray/framework/info.js" type="text/javascript"></script>

<%
	Class<?> _clazz = (Class<?>)request.getAttribute(InfoPageAction.CLASS_KEY);
	String _daoName = (String)request.getAttribute(InfoPageAction.ROUTER_KEY);
%>	

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