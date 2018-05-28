package com.ptac.app.inter.ftp;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerZanGu {
	Timer timer = null;

	// ʱ����
	private static final long PERIOD_DAY = 1000 * 60 * 60 * 24;

	public TimerZanGu() {
		Calendar calendar = Calendar.getInstance();
		//ÿ��12:05
		//calendar.set(Calendar.DAY_OF_MONTH, 3);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 5);
		calendar.set(Calendar.SECOND, 0);

		Date date = calendar.getTime(); // ��һ��ִ�ж�ʱ�����ʱ��

		// �����һ��ִ�ж�ʱ�����ʱ�� С�� ��ǰ��ʱ��
		// ��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		timer = new Timer();

		ZanGuTimerTask task = new ZanGuTimerTask();
		// ����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
		timer.schedule(task, date, PERIOD_DAY);
	}

	// ���ӻ��������
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
