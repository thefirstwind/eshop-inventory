package com.thefirstwind.eshop.inventory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.thefirstwind.eshop.inventory.thread.RequestProcessorThreadPool;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统初始化监听器
 * @author xingxn
 *
 */
@Slf4j
public class InitListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("=========================系统初始化 开始===================");
		RequestProcessorThreadPool.init();
		log.info("=========================系统初始化 完毕===================");
	}
}
