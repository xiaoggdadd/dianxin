package com.noki.common.oss.timer;

import java.util.Timer;
import java.util.TimerTask;

import com.noki.common.oss.service.OneOSSCustomerService;

public class TimerTaskHander extends TimerTask {
	private OneOSSCustomerService oneOSSCustomerService;
	@Override
	public void run() {
		try {
			oneOSSCustomerService = new OneOSSCustomerService();
			oneOSSCustomerService.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTaskHander(), 0, 5000);
	}
   
}
