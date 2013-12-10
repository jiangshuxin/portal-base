package com.newray.base.web.command;

/**
 * 查询命令集
 * @author justin.jiang
 *
 */
public class QueryCommand {
	/**
	 * 升序
	 */
	public static final String ORDER_BY_ASC = "asc";
	/**
	 * 降序
	 */
	public static final String ORDER_BY_DESC = "desc";
	
	private String fieldName;
	private String operation;
	private String queryText1;
	private String queryText2;
	private boolean orderBy;
	private String orderDirection = ORDER_BY_DESC;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getQueryText1() {
		return queryText1;
	}

	public void setQueryText1(String queryText1) {
		this.queryText1 = queryText1;
	}

	public String getQueryText2() {
		return queryText2;
	}

	public void setQueryText2(String queryText2) {
		this.queryText2 = queryText2;
	}

	public boolean isOrderBy() {
		return orderBy;
	}

	public void setOrderBy(boolean orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
}
