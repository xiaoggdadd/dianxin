package com.ptac.app.calibstat.movefixedelec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public String[] getCompare(String begintime,String endtime)throws ParseException{
		String[] month = new String[2];
		String  format = "yyyy-MM";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date b = sdf.parse(begintime);
			Date e = sdf.parse(endtime);
			Calendar cb = Calendar.getInstance();
			Calendar ce = Calendar.getInstance();
			cb.setTime(b);
			ce.setTime(e);
			ce.add(Calendar.MONTH, -2);
			Date ccb = cb.getTime();
			Date cce = ce.getTime();
			if(ce.after(cb)){
				month[0] = sdf.format(ccb);
				month[1] = sdf.format(cce);	
			}else{
				month[0] = sdf.format(cce);
				month[1] = sdf.format(ccb);
			}
		return month;
	}
}
