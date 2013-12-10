package com.newray.base.web.router;

import java.util.Map;

/**
 * MVC路由服务
 * @author justin.jiang
 *
 */
public class MVCRouterService {
	private Map<String, MVCRouter> router;

	public Map<String, MVCRouter> getRouter() {
		return router;
	}

	public void setRouter(Map<String, MVCRouter> router) {
		this.router = router;
	}
}
