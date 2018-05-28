package com.noki.common.oss.timer;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerTaskListener implements ServletContextListener {

	private Timer timer = null;
	
	public void contextDestroyed(ServletContextEvent sce) {
		
		if(timer != null){
			timer.cancel();
		}
		sce.getServletContext().log("定时器结束");
	}

	public void contextInitialized(ServletContextEvent sce) {
		if(timer == null){
			timer = new Timer();
		}
		sce.getServletContext().log("定时器开启");
		timer.schedule(new TimerTaskHander(), 10000, 1000 * 60 *60);
	}
	 
}
