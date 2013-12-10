$(function(){
	//alert($('#recordDiv').is(":hidden"));
	if($('#recordDiv').is(":visible")==true){
		$('#recordDiv').hide();
		$('#recordForm').css('display', 'none');
	}
});

/**
	*扩展easyui的validator插件rules，支持更多验证类型
	*author by qfang
	*2013-01-08
	*/
$.extend($.fn.validatebox.defaults.rules, {
	username:{//验证用户名
		validator:function(value){
			return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
		},
		message:"用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）"
	},
	chinese:{//验证中文
		validator:function(value){
			return /^[\u0391-\uFFE5]+$/i.test(value);
		},
		message:"请输入中文"
	},
	intorfloat:{//验证整数或小数
		validator:function(value){
			return /^\d+(\.\d+)?$/i.test(value); //稍作调整，小数部分"\d"后需要"+",否则限制只能一位小数
		},
		message:"请输入数字，并确保格式正确"
	},
	int:{//验证整数
		validator:function(value){
			return /^[+]?[1-9]+\d*$/i.test(value); //稍作调整，小数部分"\d"后需要"+",否则限制只能一位小数
		},
		message:"请输入数字，并确保格式正确"
	},
	mobile:{//验证手机号码
		validator:function(value){
			return /^(13|15|18)\d{9}$/i.test(value);
		},
		message:"手机号码格式不正确"
	},
	phone:{//验证电话号码(有问题:3761135验证不通过)
		validator:function(value){
			return /^((\d{3}\-\d{8})|(\d{4}\-\d{7}))$/i.test(value);
		},
		message:"请输入有效的电话号码"
	},
	idcard:{//验证身份证(有问题：310113198101036220验证不通过)
		validator:function(value){
			//当前年份
			var curYear = new Date().getFullYear();
			curYear = parseInt(curYear);
			var startYear = curYear-150;	//此处预设最大年龄为150
			//var regStr = eval("/^\\d{6}[19,20]\\d{2}(0[1,9]|1[0,2])(0[1,9]|[1,2]\\d|3[0.1])\\d{3}(\\d|[A-Z])$/i");
			//return regStr.test(value);
			return /\d{6}[19,20]\d{2}(0[1-9]|1[0-2])([0[1-9]|[1,2]\d|3[0,1])\d{3}(\d|[A-Z])/i.test(value);
			//return /^\d{6}[startYear-curYear](0[1,9]|1[0,2])(0[1,9]|[10,31])\d{3}(\d|[A-Z])$/i.test(value);
		},
		message:"请输入有效的身份证"
	},
	ip:{//验证IP地址
		validator:function(value){
			return /d+.d+.d+.d+/i.test(value);
		},
		message:"请输入有效的IP地址"
	},
	carno:{//验证车牌号
		validator:function(value){
			return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/i.test(value);
		},
		message:"请输入有效的车牌号"
	},
	carenergin:{//验证发动机号
		validator:function(value){
			return /^[a-zA-Z0-9]{16}$/i.test(value);
		},
		message:"请输入有效的发动机号"
	},
});

/**
*下拉框的可用、不可用
*/
function disableCom(){
	$('#simplecom').combobox("disable");
}
function enableCom(){
	$('#simplecom').combobox("enable");
}

/**
*展现进度条
*/
function showProgress(){
	var win = $.messager.progress({
		title:'Please waiting',
		msg:'Loading data...'
	});
	setTimeout(function(){
		$.messager.progress('close');
	}, 3200);	//3200毫秒，恰好能看到进度条到100%立即关闭
}
function testProgress(){
	var value = $('#p').progressbar('getValue');
	if(value < 100){
		value += Math.floor(Math.random()*10);
		$('#p').progressbar('setValue', value);
		setTimeout(arguments.callee, 200);
	}
}

/**
*数值微调器的可用、不可用
*/
function disableNum(){
	$('#numberspinner').numberspinner("disable");
}
function enableNum(){
	$('#numberspinner').numberspinner("enable");
}

/**
 * info页面新增记录、查看记录
 * 
 */
function addRecord(){
	alert('toadd');
	$('#recordDiv').show();
	$('#recordForm').css('display', 'block');
}
function viewRecord(){
	//alert('toview');
	$('#recordDiv').show();
	//$('recordForm').css('display', 'block');
	$('#recordForm').form('load', 'info/info_form.json');
}
function info_form_submit(form){
	alert('tosubmit');
	var jsonObject = {}; 
	//alert($("#recordTable :input").length);
	var inputs = $("#recordTable :input");
	var len = $("#recordTable :input").length;
	for(var i = 0; i < len; i++) { 
		e = inputs[i];
		//alert($("#recordTable :input")[i]);
		//alert($("#recordTable :input")[i].attr("name"));
		//alert(e.attr("name"));
		//jsonObject['+e.name+'] = ;
	}
	var pageContext = $('#_pageContext').val();
	var dao_prefix = $('#_dao_prefix').val();
	$('#recordForm').attr("action", pageContext+"/info/"+dao_prefix+"/saveInfoForm.htm").submit();
	//$('#recordForm').attr("action", "saveInfoForm.htm").submit();
	//return;
	//return jsonObject;
}
function info_form_close(){
	alert('toclose');
	$('#recordDiv').hide();
}