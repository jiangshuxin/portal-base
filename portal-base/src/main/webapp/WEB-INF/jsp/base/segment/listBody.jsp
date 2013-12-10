<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="border: 1px solid groove;" id="_queryDiv">
			<table id="_queryTable">
			<%//model配置条件参数展示
				PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(_clazz);
					pds = com.newray.base.util.ReflectUtil.filter(pds, QueryCondition.class);
					pds = makeOrder(pds,new com.newray.base.web.list.comparator.AnnoOrderComparator(QueryCondition.class)).toArray(new PropertyDescriptor[]{});
					int i=0;
					for(;i<pds.length;i++){
						PropertyDescriptor pd = pds[i];
						Method readMethod = pd.getReadMethod();
						QueryCondition qc = readMethod.getAnnotation(QueryCondition.class);
						ColumnUI cui = readMethod.getAnnotation(ColumnUI.class);
			%>
				<tr>
					<td id="FqueryCommand<%=i%>">
					<%//若有代码关联的字段，则fieldName取其指向的字段名称 %>
						<span fieldName="<%=(StringUtils.isEmpty(cui.dictNo()) && StringUtils.isEmpty(cui.dictRefColumn()))?pd.getName():cui.dictRefColumn()%>">
							<%=StringUtils.isEmpty(qc.name())?cui.title():qc.name() %>:
						</span>
					</td>
					<td id="OqueryCommand<%=i%>">
						<select onchange="_queryTextControl(this);" style="width: 87px;height: 19px">
							<option value="equal">等于</option>
							<option value="notEqual">不等于</option>
							<%if(StringUtils.isEmpty(cui.dictNo()) && StringUtils.isEmpty(cui.dictRefColumn())){ %>
								<option value="like">类似于</option>
								<option value="gt">大于</option>
								<option value="gte">大于等于</option>
								<option value="lt">小于</option>
								<option value="lte">小于等于</option>
								<option value="between">在……之间</option>
							<%} %>
						</select>
					</td>
					<td id="QqueryCommand<%=i%>">
						<%if(StringUtils.isNotEmpty(cui.dictNo()) && StringUtils.isNotEmpty(cui.dictRefColumn())){ %>
							<select _dictNo = "<%=cui.dictNo()%>">
								<option value="">----请选择----</option>
							</select>
						<%}else{ %>
							<input type="text">
							<input type="text" style="display: none">
						<%} %>
					</td>
				</tr>
			<%	} %>
			<%//外部条件参数展示
				if(paramMap != null && paramMap.size() > 0){
					for(String key : paramMap.keySet()){
						String[] value = paramMap.get(key);
						%>
							<tr>
								<td id="FqueryCommand<%=i%>">
								<%//若有代码关联的字段，则fieldName取其指向的字段名称 %>
									<span fieldName="<%=key%>">
									</span>
								</td>
								<td id="OqueryCommand<%=i%>">
									<select onchange="_queryTextControl(this);" style="width: 87px;height: 19px">
										<option value="equal">等于</option>
									</select>
								</td>
								<td id="QqueryCommand<%=i++%>">
									<input type="text" value="<%=value.length>0?value[0]:""%>">
								</td>
							</tr>
						<%
					}
				}
			%>
			<tr>
				<td>
					<button onclick="_doQuery();">查询</button>
					<button onclick="_clearQuery();">清空</button>
				</td>
			</tr>
			</table>
			
		</div>
		
		<div id="list_container">
			<table id="_list_data_region"></table>
		</div>
		
	<input type="hidden" id="_frozenColumns" value="<%=getFrozenColumns(_clazz)%>">
	<input type="hidden" id="_columns" value="<%=getColumns(_clazz)%>">
	<input type="hidden" id="_routerKey" value="<%=_routerKey%>">

<script>
	function loadList(){
		
		var frozenColumns = eval("("+$('#_frozenColumns').val()+")");
		var columns = eval("("+$('#_columns').val()+")");
		
		//列表基本属性
		$('#_list_data_region').datagrid({
			frozenColumns:frozenColumns,
			columns:columns,
			pagination:true,
			toolbar:toolButtons,
			
			//列表事件
			onLoadSuccess : function(data){
				if(typeof onLoadSuccess == 'function'){
					onLoadSuccess();
				}
			},
			onLoadError : function(){
				if(typeof onLoadError == 'function'){
					onLoadError();
				}
			},
			onBeforeLoad : function(param){
				var pager = $('#_list_data_region').datagrid('getPager');
				$(pager).pagination({
			        beforePageText: '第',//页数文本框前显示的汉字  
			        afterPageText: '页    共 {pages} 页',  
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
				});
				
				if(typeof onBeforeLoad == 'function'){
					return onBeforeLoad();
				}
				return true;
			},
			
			onClickRow : function(rowIndex, rowData){
				if(typeof onClickRow == 'function'){
					return onClickRow(rowIndex, rowData);
				}
				//alert(rowData.username);
			},
			
			onDblClickRow : function(rowIndex, rowData){
				if(typeof onDblClickRow == 'function'){
					return onDblClickRow(rowIndex, rowData);
				}
				//alert(rowData.username);
			},
			onClickCell : function(rowIndex, field, value){
				if(typeof onClickCell == 'function'){
					return onClickCell(rowIndex, field, value);
				}
				//alert(field);
			},
			onDblClickCell : function(rowIndex, field, value){
				if(typeof onDblClickCell == 'function'){
					return onDblClickCell(rowIndex, field, value);
				}
			},
			onSortColumn : function(sort, order){//sort是字段名称   order有asc和desc
				//alert(order);
				if(typeof onSortColumn == 'function'){
					return onSortColumn(sort, order);
				}
			},
			onResizeColumn : function(field, width){
				if(typeof onResizeColumn == 'function'){
					return onResizeColumn(field, width);
				}
			},
			onSelect : function(rowIndex, rowData){
				if(typeof onSelect == 'function'){
					return onSelect(rowIndex, rowData);
				}
				//alert(rowIndex);
			},
			onUnselect : function(rowIndex, rowData){
				if(typeof onUnselect == 'function'){
					return onUnselect(rowIndex, rowData);
				}
			},
			onSelectAll : function(rows){
				if(typeof onSelectAll == 'function'){
					return onSelectAll(rows);
				}
				/*for(var obj in rows[0]){//row可以.字段名
					alert(obj);
				}*/
				//alert(rows[6].password);
			},
			onUnselectAll : function(rows){
				if(typeof onUnselectAll == 'function'){
					return onUnselectAll(rows);
				}
			},
			onBeforeEdit : function(rowIndex, rowData){
				if(typeof onBeforeEdit == 'function'){
					return onBeforeEdit(rowIndex, rowData);
				}
			},
			onAfterEdit : function(rowIndex, rowData, changes){
				if(typeof onAfterEdit == 'function'){
					return onAfterEdit(rowIndex, rowData, changes);
				}
			},
			onCancelEdit : function(rowIndex, rowData){
				if(typeof onCancelEdit == 'function'){
					return onCancelEdit(rowIndex, rowData);
				}
			},
			onHeaderContextMenu : function(e, field){
				if(typeof onHeaderContextMenu == 'function'){
					return onHeaderContextMenu(e, field);
				}
			},
			onRowContextMenu : function(e, rowIndex, rowData){
				if(typeof onRowContextMenu == 'function'){
					return onRowContextMenu(e, rowIndex, rowData);
				}
			},
			<%
				DataGrid _dataGrid = _clazz.getAnnotation(DataGrid.class);
				String _dataGridStr = appendAllAttrs(_dataGrid);
				out.print(_dataGridStr);
			%>
		});
		
		_doQuery();
		
		<%//若有代码关联的搜索条件%>
		if($('select[_dictNo]').size() > 0){
			$('select[_dictNo]').each(function(){
				var dictNo = $(this).attr('_dictNo');
				var result = CommonUtil.methodInvoke('Local_Dict',dictNo);
				if(result.successFlag == false || result.content.length == 0){
					alert('无法找到字典内容，dictNo='+dictNo);
					return false;
				}
				var dictMap = result.content;
				for(var k in dictMap){
					$(this).children('option:last').after('<option value="'+dictMap[k].id.itemno+'">'+dictMap[k].itemname+'</option>');
				}
			});
		}
	}
</script>