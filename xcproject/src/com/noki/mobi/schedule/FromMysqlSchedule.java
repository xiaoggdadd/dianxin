package com.noki.mobi.schedule;

import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

/**
 * <p>Title:��MYsql���ݿ��ȡ������Ϣ�ļƻ� </p>
 *
 * <p>Description: ��Ŀ��������TimerTask ��MysqlTask����</p>
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
      // schedule(TimerTask task, long delay, long period)�����趨ָ������task��ָ���ӳ�delay����й̶��ӳ�peroid��ִ�С�
       
   }

   public void contextDestroyed(ServletContextEvent event){
       timer.cancel();
   }

}
