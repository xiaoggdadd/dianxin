package com.ptac.app.statisticcollect.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**年月处理工具类
 * @author WangYiXiao 2015-3-19
 *
 */
public class YearYueUtil {
	private static Logger logger = Logger.getLogger(YearYueUtil.class);
	/**去年同月,去年1月
	 * @return
	 */
	public synchronized String[] lastSameYue(String nianyue){
		String[] str = new String[2]; 
		String last = "",last2="";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(nianyue);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("获取上年同月,字符串解析出错:"+e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -12);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		String m = month>=10?String.valueOf(month):"0"+month;
		last = year+"-"+m;
		last2 = year+"-01";
		str[0] = last;
		str[1] = last2;
		return str;
	}
	
	/**上个月
	 * @return
	 */
	public synchronized String lastYue(String nianyue){
		String last = "";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(nianyue);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("获取月,字符串解析出错:"+e.getMessage());
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		String m = month>=10?String.valueOf(month):"0"+month;
		last = year+"-"+m;
		return last;
	}
	
	/**当年1月
	 * @return
	 */
	public synchronized String OneYue(String nianyue){
		return nianyue.split("-")[0]+"-01";
		
	}
	
	/**平均单价通报 获取起始终止日期
	 * @return
	 */
	public synchronized String[] month(){
		String[] str = new String[2];
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
	    cal1.set(Calendar.MONTH, 0);//当前年的一月份
	    cal.add(Calendar.MONTH,-1);//当前月的前一个月
	    int year1 = cal1.get(Calendar.YEAR);
	    int year2 = cal.get(Calendar.YEAR);
		if(year1>year2){
			cal1.set(Calendar.YEAR,year2);
		}
	    String begin = ft.format(cal1.getTime());
	    String end = ft.format(cal.getTime());
	    str[0] = begin;
	    str[1] = end;
	    return str;
	}
}
