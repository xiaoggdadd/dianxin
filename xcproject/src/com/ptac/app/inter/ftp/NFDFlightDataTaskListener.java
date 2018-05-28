package com.ptac.app.inter.ftp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class NFDFlightDataTaskListener implements ServletContextListener {
	Logger logger = Logger.getLogger(NFDFlightDataTaskListener.class);

	private TimerManager timerManager = null;
	private TimerZanGu timerZanGu = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println(timerManager+"    wode");
		timerManager.dropTime();
		logger.info("科大   审计 接口监听删除4");
		timerZanGu.dropTime();
		logger.info("暂估 监听移除");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println(timerManager+"    wode");
		timerManager = new TimerManager();
		logger.info("科大 审计 接口监听启动");
		timerZanGu = new TimerZanGu();
		logger.info("暂估 监听启动");
	}

}
