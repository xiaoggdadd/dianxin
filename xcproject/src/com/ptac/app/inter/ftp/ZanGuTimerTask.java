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
			//ÿ��3��12:05
			Calendar calr = Calendar.getInstance();
			int day = calr.get(Calendar.DAY_OF_MONTH);
			if(day==1){
				new ZanGuImpDao().updateCityAverageUnitPrice();
			}
			if(day==3){
				System.out.println("ÿ����3��12:05ִ���ݹ��Զ�����");
				new ZanGuImpDao().zanGu();//ִ���ݹ���̬���ݱ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
