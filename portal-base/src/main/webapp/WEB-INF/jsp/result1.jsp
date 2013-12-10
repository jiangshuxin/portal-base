<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ 
page import="java.util.*"%>
<%@ include file="/WEB-INF/jsp/base/segment/resourceHeader.jsp"%>


<body class="easyui-layout">
	<div region="north" title="" split="true"
		style="height: 100px; padding: 10px;">
		<p>The north content.</p>
		<div style="background:#C9EDCC;" id="menuContainer">
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb1" class="easyui-menubutton" menu="#mm1" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
			<a href="javascript:void(0)" id="mb2" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
		</div>
		<div id="mm1" style="width: 150px;">
			<div iconCls="icon-undo">Undo</div>
			<div iconCls="icon-redo">Redo</div>
			<div class="menu-sep"></div>
			<div>Cut</div>
			<div>Copy</div>
			<div>Paste</div>
			<div class="menu-sep"></div>
			<div>
				<span>Toolbar</span>
				<div style="width: 150px;">
					<div>Address</div>
					<div>Link</div>
					<div>Navigation Toolbar</div>
					<div>Bookmark Toolbar</div>
					<div class="menu-sep"></div>
					<div>New Toolbar...</div>
				</div>
			</div>
			<div iconCls="icon-remove">Delete</div>
			<div>Select All</div>
		</div>
		<div id="mm2" style="width: 100px;">
			<div>Help</div>
			<div>Update</div>
			<div>About</div>
		</div>
	</div>
	<div region="south" title="" split="false"
		style="height: 40px; padding: 10px; background: #efefef;">
	</div>
	<div region="center" title="Main Title" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="Tab1" style="padding: 20px; overflow: hidden;">
				<div style="margin-top: 20px;">
					<h3>jQuery EasyUI framework help you build your web page
						easily.</h3>
					<li>easyui is a collection of user-interface plugin based on
						jQuery.</li>
					<li>using easyui you don't write many javascript code, instead
						you defines user-interface by writing some HTML markup.</li>
					<li>easyui is very easy but powerful.</li>
				</div>
			</div>
			<div title="Tab2" closable="true" style="padding: 20px;">This
				is Tab2 width close button.</div>
			<div title="Tab3" iconCls="icon-reload" closable="true"
				style="overflow: hidden; padding: 5px;">
				<table id="tt2"></table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		alert('${form.userName}');
		var length = $('#menuContainer').width();
		var menuHiddenFlag = false;
		//alert(length);
		var sum = new Number(0);
		var index = new Number(0);
		$('#menuContainer a').each(function(i){
			var width = $(this).width();
			var hasIcon = $(this).attr('iconCls');
			if(typeof hasIcon == 'undefined' || hasIcon == '') sum += width + 28;
			else sum += width + 46;//46  26
			if(sum > length){
				$(this).hide();
				menuHiddenFlag = true;
				if(index == 0) index = i;
			}
		});
		$('#menuContainer a:eq('+index+')').after('<a>show all</a>');
	});
</script>
</html>