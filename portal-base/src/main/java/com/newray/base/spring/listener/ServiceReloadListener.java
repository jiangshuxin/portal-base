package com.newray.base.spring.listener;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.newray.base.spring.ReloadAware;
import com.newray.base.spring.SpringContextHolder;

/**
 * 服务重启监听器，被观察者
 * @author justin.jiang
 *
 */
public class ServiceReloadListener implements ApplicationListener<ContextRefreshedEvent> {
	private List<ReloadAware> reloadAwares;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!SpringContextHolder.getContext().equals(event.getSource())) return;
		if(reloadAwares ==null || reloadAwares.size() == 0) return ;
		for(ReloadAware reloadAware : this.reloadAwares){
			reloadAware.reload();//通知观察者
		}
	}

	public void setReloadAwares(List<ReloadAware> reloadAwares) {
		this.reloadAwares = reloadAwares;
	}
}
