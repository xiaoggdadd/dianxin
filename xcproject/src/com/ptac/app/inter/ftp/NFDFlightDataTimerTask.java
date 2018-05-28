package com.ptac.app.inter.ftp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ptac.app.inter.dao.CheckValiate;

public class NFDFlightDataTimerTask extends TimerTask {
	Logger logger = Logger.getLogger(NFDFlightDataTimerTask.class);

	@Override
	public void run() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			int day = cal.get(Calendar.DAY_OF_MONTH);//
			if (day == 1) {//如果是某个月的一号
				cal.add(Calendar.MONTH, -1);
				String enterTime = dateFormat.format(cal.getTime());
				String month = new StringBuffer(enterTime).deleteCharAt(4).toString();
				if(CheckValiate.getInstance().isKdValidate(month)){
					new FileWriterUtil().KdgcInterface(enterTime);
					logger.info("科大接口数据生成:" + enterTime + "，" + date.toString());
				}
			}
			if (day == 3) {//
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();
				Calendar c3 = Calendar.getInstance();
				c1.add(Calendar.MONTH, -1);
				c1.add(Calendar.DATE, 3);
				c2.add(Calendar.MONTH, 0);
				c2.add(Calendar.DATE, 3);
				c3.add(Calendar.MONTH, -1);
				DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat format3 = new SimpleDateFormat("yyyyMM");
				String btime = format2.format(c1.getTime());
				String etime = format2.format(c2.getTime());
				String month = format3.format(c3.getTime());
				if (CheckValiate.getInstance().isSjValidate(month)) {
				//if(true) {
					new FileWriterSJ().SjInterface(month, btime, etime);
					logger.info("审计接口生成,账期:" + month + ",开始时间：" + btime
							+ ",结束时间：" + etime);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
