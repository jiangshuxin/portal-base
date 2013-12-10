package com.newray.base.web.list.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.newray.base.web.list.constant.Align;

@Target({TYPE})
@Retention(RUNTIME)
public @interface DataGrid {
	/**
	 * 面板元素id
	 * @return
	 */
	String id() default "DataGrid";
	/**
	 * DataGrid标题
	 * @return
	 */
	String title() default "";
	/**
	 * 图标名称
	 * @return
	 */
	String iconCls() default "";
	/**
	 * 左偏移
	 * @return
	 */
	int left() default 0;
	/**
	 * 上偏移
	 * @return
	 */
	int top() default 0;
	/**
	 * 宽度
	 * @return
	 */
	int width() default 0;
	/**
	 * 高度
	 * @return
	 */
	int height() default 0;
	/**
	 * 是否自适应外围容器
	 * @return
	 */
	boolean fit() default false;
	/**
	 * 是否设置边框
	 * @return
	 */
	boolean border() default true;
	
	/**
	 * 防止因字段过多，水平方向出现滚动条
	 * @return
	 */
	boolean fitColumns() default true;
	/**
	 * Defines if set the row height based on the contents of that row. Set to false can improve loading performance. 
	 * @return
	 */
	boolean autoRowHeight() default true;
	/**
	 * 斑马线
	 * @return
	 */
	boolean striped() default true;
	/**
	 * 允许换行
	 * @return
	 */
	boolean nowrap() default true;
	/**
	 * 主键字段
	 * @return
	 */
	String idField() default "";
	/**
	 * 是否显示行号
	 * @return
	 */
	boolean rownumbers() default true;
	/**
	 * 是否单选
	 * @return
	 */
	boolean singleSelect() default true;
	/**
	 * 初始化时显示页数
	 * @return
	 */
	int pageNumber() default 1;
	/**
	 * 每页展示多少条目
	 * @return
	 */
	int pageSize() default 10;
	/**
	 * 排序名称
	 * @return
	 */
	String sortName() default "";
	/**
	 * 排序顺序
	 * @return
	 */
	String sortOrder() default "desc";
	/**
	 * 是否远程排序
	 * @return
	 */
	boolean remoteSort() default true;
	/**
	 * 对齐方式
	 * @return
	 */
	String align() default Align.CENTER;
}
