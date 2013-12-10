/**
 * @author justin.jiang
 *
 */
//post方式模拟页面跳转
function Navigation(url, data, name){
	this.url = url;
	this.data = data || '';
	this.name = name || '_self';
}

Navigation.prototype.openPostWindow = function(){  
    var tempForm = document.createElement("form");
    tempForm.id = "_tempForm1";
    tempForm.method = "post";
    tempForm.action = this.url;
    tempForm.target = this.name;
    
    if(typeof this.data == 'string'){
    	var params = this.data.split('&');
	    for(var i=0;i<params.length;i++){
	    	var param = params[i];
	    	if(param.indexOf('=') > -1){
	    		var key = param.split('=')[0];
	    		var value = param.split('=')[1];
	    		
			    var hideInput = document.createElement("input");
			    hideInput.type = "hidden";
			    hideInput.name = key;
			    hideInput.value = value;
			    tempForm.appendChild(hideInput);
	    	}
	    }
    }else if(typeof this.data == 'object'){
    	for(var key in this.data){
    		var value = this.data[key];
    		
		    var hideInput = document.createElement("input");
		    hideInput.type = "hidden";
		    hideInput.name = key;
		    hideInput.value = value;
		    tempForm.appendChild(hideInput);
    	}
    }
    
    document.body.appendChild(tempForm);
    
    tempForm.submit();
    document.body.removeChild(tempForm);
}

/**
 * 提供两个常用js方法：
 * 1. methodInvoke用于jsp页面直接调用本地或者远程的方法，暂时只支持5个对象参数
 * 2. mergeObj合并两个js对象到一个大对象
 * 
 */
var CommonUtil = {
	methodInvoke : function(id,params,methodName,paramTypes){
		var json_data = {
			"id":id,
			"methodName":typeof methodName == 'undefined'?"":methodName,
		};
		
		if(typeof params == 'object'){
			if(!$.isArray(params)){//对象形式
				for(var k in params){
					//json_data['paramMap[0]'] = this.mergeObj(json_data['paramMap[0]'],$.parseJSON("{\""+k+"\":\""+params[k]+"\"}"));
					this.mergeObjProperty(json_data, 'paramMap[0]', $.parseJSON("{\""+k+"\":\""+params[k]+"\"}"));
				}
			}else{//多个对象，数组形式
				var index = 0;
				for(var j in params){
					var tempObj = params[j];
					if(typeof tempObj == 'object'){
						for(var k in tempObj){
							//json_data['paramMap['+index+']'] = this.mergeObj(json_data['paramMap['+index+']'],$.parseJSON("{\""+k+"\":\""+tempObj[k]+"\"}"));
							this.mergeObjProperty(json_data, 'paramMap['+index+']', $.parseJSON("{\""+k+"\":\""+tempObj[k]+"\"}"));
						}
					}else if(typeof tempObj == 'string'){//基本类型(数组形式)  ['value1','value2']形式
						//json_data['paramMap['+index+']'] = this.mergeObj(json_data['paramMap['+index+']'],$.parseJSON("{\""+index+"\":\""+tempObj+"\"}"));
						this.mergeObjProperty(json_data, 'paramMap['+index+']', $.parseJSON("{\""+index+"\":\""+tempObj+"\"}"));
					}
					index++;
				}
			}
			
		}else if(typeof params == 'string'){//key1=value1&key2=value2的形式，只能用于表示单个对象或多个基本类型
			if(params.indexOf('&') > -1 || params.indexOf('=') > -1){
				var kvArray = params.split('&');
				for(var i in kvArray){
					if(kvArray[i].indexOf('=') > -1){//key=value形式
						var kv = kvArray[i].split('=');
						if(kv.length == 2){
							//json_data['paramMap[0]'] = this.mergeObj(json_data['paramMap[0]'],$.parseJSON("{\""+kv[0]+"\":\""+kv[1]+"\"}"));
							this.mergeObjProperty(json_data, 'paramMap[0]', $.parseJSON("{\""+kv[0]+"\":\""+kv[1]+"\"}"));
						}
					}else{//基本类型(字符串形式)  value1&value2形式
						//json_data['paramMap['+i+']'] = this.mergeObj(json_data['paramMap['+i+']'],$.parseJSON("{\""+i+"\":\""+kvArray[i]+"\"}"));
						this.mergeObjProperty(json_data, 'paramMap['+i+']', $.parseJSON("{\""+i+"\":\""+kvArray[i]+"\"}"));
					}
				}
			}else{//单个基本类型参数
				//json_data['paramMap[0]'] = this.mergeObj(json_data['paramMap[0]'],$.parseJSON("{\"0\":\""+params+"\"}"));
				this.mergeObjProperty(json_data, 'paramMap[0]', $.parseJSON("{\"0\":\""+params+"\"}"));
			}
		}else if(typeof params == 'undefined'){
			this.mergeObjProperty(json_data, 'paramMap[0]', $.parseJSON("{}"));
		}
		
		if($.isArray(paramTypes)){
			for(var k in paramTypes){
				this.mergeObjProperty(json_data, 'paramTypes['+k+']', paramTypes[k]);
			}
		}else if(typeof paramTypes == 'string'){
			this.setObjProperty(json_data, 'paramTypes[0]', paramTypes);
		}else if(typeof paramTypes == 'undefined'){
			this.mergeObjProperty(json_data, 'paramTypes[0]', '');
		}
		
		var responseText = $.ajax({
			url : CONTEXT_PATH + "/common/srvInvoker.json",
			data : json_data,
			dataType : "json",
			contentType : "application/json",
			processData : true,
			cache : false,
			async : false,
		}).responseText;
		
		try {
			return $.parseJSON(responseText).invokerResult;
		} catch (e) {
			document.write(responseText);
		}
	},
	setObjProperty : function(obj,key,value){
		if(typeof value == 'object'){
			var tempObj = $.parseJSON('{"'+key+'":{}}');
			tempObj[''+key+''] = value;
			this.mergeObj(obj, tempObj);
		}else if(typeof value == 'string'){
			var newObjStr = '{"'+key+'":"'+value+'"}';
			this.mergeObj(obj, $.parseJSON(newObjStr));
		}
	},
	getObjProperty : function(obj,key){
		return obj[''+key+''];
	},
	existObjProperty : function(obj,key){
		for(var k in obj){
			if(k == key)return true;
		}
		return false;
	},
	mergeObjProperty : function(obj,key,value){
		if(this.existObjProperty(obj, key)){
			this.mergeObj(this.getObjProperty(obj, key), value);
		}else{
			this.setObjProperty(obj, key, value);
		}
	},
	mergeObj : function(o1,o2){
		for(var key in o2){
		    o1[key]=o2[key];
		}
		return o1;
	}
}

