package com.ptac.app.inter.ftp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class ZanGuTimerTask extends TimerTask  {

	@Override
	public void run() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			String enterTime = dateFormat.format(cal.getTime());
			//new FileWriterUtil().KdgcInterface(enterTime);
			//每月3日12:05
			Calendar calr = Calendar.getInstance();
			int day = calr.get(Calendar.DAY_OF_MONTH);
			if(day==1){
				new ZanGuImpDao().updateCityAverageUnitPrice();
			}
			if(day==3){
				System.out.println("每个月3号12:05执行暂估自动保存");
				new ZanGuImpDao().zanGu();//执行暂估静态数据保存
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
