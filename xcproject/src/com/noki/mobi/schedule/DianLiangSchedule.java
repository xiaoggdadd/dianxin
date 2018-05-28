package com.noki.mobi.schedule;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.dao.NewJzxxDao;

/**
 * <p>Title:�����ƻ� </p>
 *
 * <p>Description: �������������Զ���ȡ�ƻ�  Timer  </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: ptac</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DianLiangSchedule implements ServletContextListener {
    private Timer timer = null;
    public DianLiangSchedule() {
    }

    public void contextInitialized(ServletContextEvent event) {
    	try {
    		 InetAddress addr = InetAddress.getLocalHost();
			 String ip=addr.getHostAddress();
			 System.out.println("����ip"+ip);
			 timer = new Timer();
		     timer.schedule(new STask(), 10000, 1000 * 60 * 60 );
		     timer.schedule(new MonthSumTask(), 10000, 1000 * 60 * 60);
		     timer.schedule(new KtxsDao(), 10000, 1000 * 60*60);
		     if(ip.equals("134.33.107.153")||ip.equals("169.254.5.238")||ip.equals("169.254.95.120")){//192.168.1.153�ָ�Ϊ����134.33.107.153
		    // timer.schedule(new JzxxDao(), 0,1000*60*60);//1000 * 60*60  = 1Сʱ
		     }
		     if(ip.equals("134.33.107.153")){
		    	 timer.schedule(new NewJzxxDao(), 0,1000*60*60);//1000 * 60*60  = 1Сʱ
		     }
		     
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent event) {
        timer.cancel();
    }
}
