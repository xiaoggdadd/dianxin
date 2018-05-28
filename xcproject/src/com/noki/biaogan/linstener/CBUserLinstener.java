package com.noki.biaogan.linstener;

import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.noki.app.DlOcrServer;

public class CBUserLinstener implements ServletContextListener {
	private DlOcrServer dlThread;  
	public void contextDestroyed(ServletContextEvent e) {  
        if (dlThread != null && dlThread.isInterrupted()) {  
        	dlThread.interrupt();  
        }  
    }  
  
    public void contextInitialized(ServletContextEvent e) {  
        String str = null;  
        if (str == null && dlThread == null) {  
        	String uuid = UUID.randomUUID().toString(); 
        	dlThread = new DlOcrServer(uuid,"");  
        	dlThread.start(); // servlet 上下文初始化时启动 socket  
        }  
    }  
}
