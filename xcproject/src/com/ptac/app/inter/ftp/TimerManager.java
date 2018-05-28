package com.ptac.app.inter.ftp;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 
 * @author Administrator
 * 
 */
public class TimerManager {
	Timer timer = null;
	private static final long PERIOD_DAY = 1000 * 60 * 60 * 24;

	public TimerManager() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		timer = new Timer();
		NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();
		timer.schedule(task, date, PERIOD_DAY);
	}

	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public void dropTime() {
		timer.cancel();
	}

}
