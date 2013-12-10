package com.newray.base.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.SearchUtil;
import com.googlecode.genericdao.search.Sort;
import com.newray.base.crud.service.GeneralCRUDService;
import com.newray.base.web.command.ListPageCommand;
import com.newray.base.web.command.QueryCommand;
import com.newray.base.web.list.annotation.DataGrid;
import com.newray.base.web.list.search.LogicOperFacade;
import com.newray.base.web.router.MVCRouter;
import com.newray.base.web.router.MVCRouterService;

/**
 * list页面通用控制器
 * @author justin
 *
 */
@Controller
@Transactional
public class ListPageAction {
	protected static final Logger logger = Logger.getLogger(ListPageAction.class);
	
	private GeneralCRUDService generalCRUDService;
	
	private LogicOperFacade logicOperFacade;
	
	private MVCRouterService mVCRouterService;
	
	public static final String CLASS_KEY = "_class";
	public static final String ROUTER_KEY = "_routerKey";
	public static final String PARAMS_KEY = "_paramsKey";
	
	/**
	 * list页面通用请求后缀
	 */
	public static final String LIST_ACTION_SUFFIX = "List.htm";
	
	/**
	 * list页面通用查询请求后缀
	 */
	public static final String LIST_QUERY_ACTION_SUFFIX = "/listcommonQuery.json";
	
	/**
	 * list页面通用页面后缀
	 */
	public static final String URL_SUFFIX = ".htm";
	
	/**
	 * 根据访问路径跳转到特定的list页面，该页面会初始化展示的元数据。<p>
	 * 在webContext.xml中，参见MVC list/info Router的配置，routerKey是入口，背后关联其他属性
	 * @param req
	 * @param res
	 * @param mm
	 * @return
	 * @throws ClassNotFoundException
	 */
	@RequestMapping({ "/**/*"+LIST_ACTION_SUFFIX })//**表示跨越多级目录
	public String toListPage(HttpServletRequest req, HttpServletResponse res,ModelMap mm) throws ClassNotFoundException{
		String routerKeyAll = genRouterKeyAll(req);
		MVCRouter router = mVCRouterService.getRouter().get(routerKeyAll);
		Assert.notNull(router, String.format("传入的参数%s，找不到对应MVC路由", routerKeyAll));
		if(StringUtils.isEmpty(router.getModelClass())) throw new IllegalArgumentException("ModelClass不能为空");
		
		Class<?> clazz = Class.forName(router.getModelClass());
		
		mm.put(PARAMS_KEY, req.getParameterMap());
		mm.put(ROUTER_KEY, routerKeyAll);
		mm.put(CLASS_KEY, clazz);
		
		if(StringUtils.isNotEmpty(router.getJspPath())){
			return router.getJspPath();
		}else{
			return genDefaultJspPath(routerKeyAll);
		}
	}

	/**
	 * 生成默认的jsp路径
	 * @param routerKeyAll
	 * @return
	 */
	private String genDefaultJspPath(String routerKeyAll) {
		if(routerKeyAll.indexOf(URL_SUFFIX) > 0){
			routerKeyAll = StringUtils.replaceOnce(routerKeyAll, URL_SUFFIX, "");
		}
		return routerKeyAll;
	}

	/**
	 * 生成完整的routerKey
	 * @param req
	 * @param routerKey
	 * @return
	 */
	private String genRouterKeyAll(HttpServletRequest req) {
		String reqUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		return StringUtils.replaceOnce(reqUri, contextPath, "");
	}
	
	@RequestMapping({ "/**"+LIST_QUERY_ACTION_SUFFIX })
	public Object commonListQuery(HttpServletRequest req, HttpServletResponse res,ListPageCommand listPageCommand) throws ServletRequestBindingException, ClassNotFoundException{
		String reqUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String routerKey = StringUtils.replaceOnce(StringUtils.replaceOnce(reqUri, contextPath, ""),LIST_QUERY_ACTION_SUFFIX,"");
		if(StringUtils.isEmpty(routerKey)) return null;
		int page = ServletRequestUtils.getIntParameter(req, "page");
		int rows = ServletRequestUtils.getIntParameter(req, "rows");
		String sortStr = ServletRequestUtils.getStringParameter(req, "sort");
		String order = ServletRequestUtils.getStringParameter(req, "order");
		
		//获取router
		Map<String,MVCRouter> routerMap = mVCRouterService.getRouter();
		Assert.notNull(routerMap, "can't find MVCRouter");
		Assert.isTrue(routerMap.containsKey(routerKey), "can't find key in MVCRouter");
		MVCRouter mVCRouter = routerMap.get(routerKey);
		
		//组装sort
		List<Sort> sorts = generateSorter(sortStr,order,mVCRouter);
		
		//组装分页Search对象
		Search is = new Search();
		if(mVCRouter.getListPageCommand() != null) {
			generateFilter(is,mVCRouter.getListPageCommand());//list页面初始化参数
		}
		generateFilter(is,listPageCommand);//用户查询参数
		
		is.setPage(page-1);
		is.setMaxResults(rows);
		is.setFirstResult(SearchUtil.calcFirstResult(is));
		is.setSorts(sorts);
		is.setSearchClass(Class.forName(mVCRouter.getModelClass()));
		
		SearchResult<?> searchResult = this.generalCRUDService.searchAndCount(is);
		
		//查询结束后调用ListEditor
		if(mVCRouter.getListEditor() != null){
			mVCRouter.getListEditor().modify(searchResult);
		}
		
		List<?> list = searchResult.getResult();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", searchResult.getTotalCount());
		map.put("rows", list);
		return map;
	}

	/**
	 * 排序顺序：用户点击列表标题排序>webContext配置排序>model配置排序
	 * @param sortStr
	 * @param order
	 * @param listPageCommand
	 * @return
	 * @throws ClassNotFoundException 
	 */
	private List<Sort> generateSorter(String sortStr, String order,
			MVCRouter mVCRouter) throws ClassNotFoundException {
		List<Sort> sorts = new ArrayList<Sort>();
		
		//用户点击列表标题排序
		if(!StringUtils.isEmpty(sortStr)){
			Sort sort = new Sort(true,extractSortStyle(sortStr));
			sort.setDesc(order.equalsIgnoreCase(QueryCommand.ORDER_BY_DESC));
			sorts.add(sort);
		}
		
		//webContext配置排序
		if(mVCRouter.getListPageCommand() != null){
			for(QueryCommand qc : mVCRouter.getListPageCommand().getQueryCommand()){
				if(qc.isOrderBy()){
					Sort sort = new Sort(true,extractSortStyle(qc.getFieldName()));
					sort.setDesc(qc.getOrderDirection().equalsIgnoreCase(QueryCommand.ORDER_BY_DESC));
					sorts.add(sort);
				}
			}
		}
		
		//model配置排序
		Class<?> clazz = Class.forName(mVCRouter.getModelClass());
		DataGrid dataGrid = clazz.getAnnotation(DataGrid.class);
		String sortName = AnnotationUtils.getValue(dataGrid, "sortName").toString();
		String sortOrder = AnnotationUtils.getValue(dataGrid, "sortOrder").toString();
		if(StringUtils.isNotEmpty(sortName) && StringUtils.isNotEmpty(sortOrder)){
			Sort sort = new Sort(true,extractSortStyle(sortName));
			sort.setDesc(sortOrder.equalsIgnoreCase(QueryCommand.ORDER_BY_DESC));
			sorts.add(sort);
		}
		
		return sorts;
	}
	
	/**
	 * 提取sort的字符串样式
	 * @param sortField
	 * @return
	 */
	private String extractSortStyle(String sortField){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(sortField);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 创建filter
	 * @param is
	 * @param listPageCommand
	 */
	private void generateFilter(Search is, ListPageCommand listPageCommand) {
		QueryCommand[] scs = listPageCommand.getQueryCommand();
		if(scs == null || scs.length == 0) return ;
		for(QueryCommand sc : scs){
			if(StringUtils.isEmpty(sc.getOperation()) || StringUtils.isEmpty(sc.getFieldName()) ||
					StringUtils.isEmpty(sc.getQueryText1())) continue;//认为text2不是必输
			List<String> list = new ArrayList<String>();
			list.add(sc.getQueryText1());
			list.add(sc.getQueryText2());
			Filter[] filters = logicOperFacade.buildFilter(sc.getOperation(), sc.getFieldName(), list.toArray());
			is.addFilters(filters);
		}
	}

	@Autowired
	public void setGenericCRUDService(GeneralCRUDService generalCRUDService) {
		this.generalCRUDService = generalCRUDService;
	}

	@Autowired
	public void setLogicOperFacade(LogicOperFacade logicOperFacade) {
		this.logicOperFacade = logicOperFacade;
	}
	
	@Autowired
	public void setmVCRouterService(MVCRouterService mVCRouterService) {
		this.mVCRouterService = mVCRouterService;
	}
}
