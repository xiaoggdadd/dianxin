package com.noki.mobi.schedule;

import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

/**
 * <p>Title:从MYsql数据库读取电量信息的计划 </p>
 *
 * <p>Description: 项目开启建立TimerTask 的MysqlTask任务</p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class FromMysqlSchedule implements ServletContextListener {
    private Timer timer=null;
    public FromMysqlSchedule() {
    }
    public void contextInitialized(ServletContextEvent event){
       timer = new Timer();
      
      // timer.schedule(new MysqlTask(),100,1000*6);
      // schedule(TimerTask task, long delay, long period)方法设定指定任务task在指定延迟delay后进行固定延迟peroid的执行。
       
   }

   public void contextDestroyed(ServletContextEvent event){
       timer.cancel();
   }

}
