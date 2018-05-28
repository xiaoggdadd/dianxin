package com.ptac.app.electricmanage.electricitybill.tool;

import java.util.Calendar;
 public class DateUtil {
	public long[][] getYue(String begintime,String endtime){
		int beginyear = Integer.parseInt(begintime.substring(0, 4));
		int endyear = Integer.parseInt(endtime.substring(0, 4));
		int beginyue = Integer.parseInt(begintime.substring(5, 7));
		int endyue = Integer.parseInt(endtime.substring(5, 7));
		int begindate = Integer.parseInt(begintime.substring(8, 10));
		int enddate= Integer.parseInt(endtime.substring(8, 10));
		
		int yue = endyear*12 + endyue-(beginyear*12 + beginyue)+1;
		long[][] a = new long[yue][2];
		if(yue == 1){//yue = 1 也就是 在同月
			a[0][0] = beginyue;//当前月份
			a[0][1] = enddate - begindate + 1;//当前月所占天数
		}else{//多个月(至少两个月)
			Calendar bcd = Calendar.getInstance();
			Calendar ecd = Calendar.getInstance();
			for(int i = 0;i < yue;i++){
				int last = yue-1;//最后一个月
				a[i][0] = beginyue;//当前月份
				bcd.clear();
				ecd.clear();
				if(i==0){//第一个月
					bcd.set(beginyear, beginyue-1, begindate);//开始时间
					ecd.set(beginyear, beginyue, 1);//下个月的第一天
					ecd.add(Calendar.DAY_OF_MONTH, -1);//本月的最后一天
					long bt = bcd.getTimeInMillis();
					long et = ecd.getTimeInMillis();
					a[i][1] = (et-bt)/(1000*3600*24)+1;
				}else if(i==last){//最后一个月
					bcd.set(beginyear, beginyue-1, 1);//本月第一天
					ecd.set(endyear, endyue-1, enddate);//结束时间
					long bt = bcd.getTimeInMillis();
					long et = ecd.getTimeInMillis();
					a[i][1] = (et-bt)/(1000*3600*24)+1;
				}else{//之间的月份
					bcd.set(beginyear, beginyue-1, 1);//本月第一天
					int max = bcd.getActualMaximum(Calendar.DAY_OF_MONTH);
					a[i][1] = max;
				}
				beginyear = (beginyue+1) ==13?(beginyear+1):beginyear;
				beginyue = (beginyue+1) ==13?1:(beginyue+1);
			}
		}
		return a;
	}
}
 
