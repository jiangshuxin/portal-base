	function _queryTextControl(self){
		if(self.value == 'between'){
			$(self).parent().next().children('input').show();
		}else if(self.value == 'isNull' || self.value == 'isNotNull'){
			$(self).parent().next().children('input').hide();
		}else{
			$(self).parent().next().children('input:eq(0)').show();
			$(self).parent().next().children('input:eq(1)').hide();
		}
	}

	function _doQuery(){
		var length = $('#_queryTable td').size();
		var queryCond = eval("("+_buildQueryJSON(length)+")");
		var routerKey = $('#_routerKey').val();
		for(var i = 0;i<length;i++){
			queryCond['queryCommand['+i+'].fieldName'] = $('#FqueryCommand'+i).children('span').attr('fieldName');
			queryCond['queryCommand['+i+'].operation'] = $('#OqueryCommand'+i).children('select').val();
			if($('#QqueryCommand'+i+' select').size() > 0){
				queryCond['queryCommand['+i+'].queryText1'] = $('#QqueryCommand'+i+' select').val();
			}else{
				queryCond['queryCommand['+i+'].queryText1'] = $('#QqueryCommand'+i).children('input').eq(0).val();
				queryCond['queryCommand['+i+'].queryText2'] = $('#QqueryCommand'+i).children('input').eq(1).val();
			}
		}
		
		//alert(queryCond['queryCommand['+0+'].fieldName']);
		//queryCond['queryCommand['+0+'].fieldName'] = "test";
		//alert(queryCond['queryCommand['+0+'].fieldName']);
		
		$('#_list_data_region').datagrid({
			url:CONTEXT_PATH+routerKey+LIST_QUERY_ACTION_SUFFIX,
			queryParams:queryCond,
			pageNumber:1,
			pageSize:10,
			sortName:null,//查询后排序参数清空
			sortOrder:'asc',
		});
	}
	
	function _buildQueryJSON(length){
		var str = "";
		str = str.concat("{");
		for(var i = 0;i<length;i++){
			str = str.concat("'queryCommand["+i+"].fieldName':'',");
			str = str.concat("'queryCommand["+i+"].operation':'',");
			str = str.concat("'queryCommand["+i+"].queryText1':'',");
			str = str.concat("'queryCommand["+i+"].queryText2':'',");
		}
		str = str.concat("}");
		return str;
	}
	
	function _clearQuery(){
		$('#_queryTable').find('input').val('');
	}
	
	function _appendQueryCommand(fieldName,fieldNameEcho,queryText1,queryText2,display){
		var index = $('#_queryTable tr').size();
		display = display || 'display: none';
		var html = 
		'<tr style="'+display+'">'+
			'<td id="FqueryCommand'+index+'">'+
				'<span fieldName="'+fieldName+'">'+fieldNameEcho+':'+'</span>'+
			'</td>'+
			'<td id="OqueryCommand'+index+'">'+
				'<select onchange="_queryTextControl(this);" style="width: 87px;height: 19px">'+
					'<option value="equal">等于</option>'+
					'<option value="notEqual">不等于</option>'+
					'<option value="like">类似于</option>'+
					'<option value="gt">大于</option>'+
					'<option value="gte">大于等于</option>'+
					'<option value="lt">小于</option>'+
					'<option value="lte">小于等于</option>'+
					'<option value="between">在……之间</option>'+
				'</select>'+
			'</td>'+
			'<td id="QqueryCommand'+index+'">'+
				'<input type="text" value="'+queryText1+'">'+
				'<input type="text" style="display: none" value="'+queryText2+'">'+
			'</td>'+
		'</tr>';
		
		$('#_queryTable tr').last().after(html);
	}
