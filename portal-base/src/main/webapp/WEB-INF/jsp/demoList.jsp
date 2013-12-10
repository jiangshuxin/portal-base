<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.newray.base.demo.model.TUserinfo"%>
<%@ include file="/WEB-INF/jsp/base/segment/listHeader.jsp" %>

<script type="text/javascript">
	//功能按钮组
	var toolButtons = [{
		id:'btnadd',
		text:'Add',
		iconCls:'icon-add',
		handler:function(){
			alert('add');
		}
	},{
		id:'btncut',
		text:'Cut',
		iconCls:'icon-cut',
		handler:function(){
			alert('cut');
		}
	},'-',{
		id:'btnsave',
		text:'Save',
		disabled:true,
		iconCls:'icon-save',
		handler:function(){
			alert('save');
		}
	}];
	
	function onLoadSuccess(){
		//alert('success');
		/*var panel = $('#_list_data_region').datagrid('getPanel');
		panel.panel('resize',{
			width:800,
			height:450, 
			left:0,
			top:0,
		});*/
	}
	
	function onClickRow(rowIndex, rowData){
		//alert(rowData.username);
	}
	
	$(function(){
		$('body').layout('panel','center').panel({
			onOpen : function(){
				loadList();
			}
		});
	});
</script>

<body class="easyui-layout">
	<div region="north" border="false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div region="west" split="true" title="West" style="width:150px;padding:10px;">west content</div>
	<div region="east" split="true" collapsed="true" title="East" style="width:100px;padding:10px;">east region</div>
	<div region="south" border="false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<script type="text/javascript">
		//本行以下js代码会执行两遍
	</script>
	<div region="center" title="Main Title">
	
		<%@ include file="/WEB-INF/jsp/base/segment/listBody.jsp" %>
	</div>
	
</body>

</html>
