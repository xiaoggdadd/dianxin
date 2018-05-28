package com.noki.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.StringTokenizer;

public class CTime {

  private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  private static final SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat sdfy = new SimpleDateFormat("yyyy-MM");
  private static final SimpleDateFormat dfk = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-01");

  /**
   * 此为得到准确的当前的时间，一般得到当前的时间要用这个
   * @return
   */
  public static synchronized String getCurrentDate() {
    String s = "";
    Calendar calend;
    TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
    TimeZone tzm = TimeZone.getDefault();
    System.out.println(tz);
    System.out.println(tzm);
    calend = Calendar.getInstance(tz);
    int year = calend.get(Calendar.YEAR);
    int m = calend.get(Calendar.MONTH) + 1;
    int d = calend.get(Calendar.DATE);
    int h = calend.get(Calendar.HOUR_OF_DAY);
    int mm = calend.get(Calendar.MINUTE);
    int ss = calend.get(Calendar.SECOND);

    return CTime.formatWholeDate(calend.getTime());
  }

  /**
   * 返回长日期格式，包含时间 一般用于当前时间取值
   * @param dt Date 型参数
   * @return
   */

  public static synchronized String formatDate(Date dt) {
    if (dt == null) {
      return "00000000000000";
    }
    return df.format(dt);

  }

  /**
   * 返回当前系统时间长日期格式，包含时间 用于取当前时间值
   *
   * @return
   */
  public static String formatDate() {
    return df.format(new Date());
  }

  /**
   * 返回长日期格式（带减号）,包含时间 ,一般用于当前时间取值
   * @param dt Date 型参数
   * @return
   */
  public static synchronized String formatWholeDate(Date dt) {
    if (dt == null) {
      return "0000-00-00 00:00:00.0";
    }
    return dfk.format(dt);
  }

  /**
   * 返回长日期格式（带减号）,包含时间 ,用于当前时间取值
   * @return
   */
  public static String formatWholeDate() {
    return dfk.format(new Date());
  }

  /**
   * 返回长日期格式，包含时间 用于对字符串的转换
   * @param dt String 型参数
   * @return
   */
  public static synchronized String formatDate(String dt) {
    StringBuffer strbf = new StringBuffer();
    StringTokenizer st = new StringTokenizer(dt.substring(0, 10), "-");
    while (st.hasMoreTokens()) {
      strbf.append(st.nextToken());
    }
    st = new StringTokenizer(dt.substring(11, 19), ":");
    while (st.hasMoreTokens()) {
      strbf.append(st.nextToken());
    }
    return strbf.toString();
  }

  /**
   * 返回短日期格式，不包含时间 一般用于当前时间取值
   * @param dt Date 型参数
   * @return
   */
  public static synchronized String formatShortDate(Date dt) {
    if (dt == null) {
      return "00000000";
    }
    return sdf.format(dt);
  }

  /**
   * 返回当前系统日期短日期格式，不包含时间 用于取当前日期值
   *
   * @return
   */
  public static String formatShortDate() {
    return sdf.format(new Date());
  }

  /**
   * 返回短日期格式（带减号），不包含时间 一般用于当前时间取值
   * @param dt Date 型参数
   * @return
   */
  public static synchronized String formatRealDate(Date dt) {
    if (dt == null) {
      return "0000-00-00";
    }
    return sdfk.format(dt);
  }

  /**
   * 返回短日期格式（带减号），不包含时间 一般用于当前月份取值
   * @param dt Date 型参数
   * @return
   */
  public static synchronized String formatRealMonth(Date dt) {
    if (dt == null) {
      return "0000-00";
    }
    return sdfy.format(dt);
  }

  /**
   * 返回短日期格式（带减号），不包含时间 一般用于当前时间取值
   * @param dt Date 型参数
   * @return
   */
  public static synchronized String formatYearDay(Date dt) {
    if (dt == null) {
      return "0000-00-00";
    }
    return ff.format(dt);
  }

  /**
   * 返回短日期格式，不包含时间 用于对字符串的转换
   * @param dt String 型参数
   * @return
   */
  public static synchronized String formatShortDate(String dt) {
    StringBuffer strbf = new StringBuffer();
    StringTokenizer st = new StringTokenizer(dt.substring(0, 10), "-");
    while (st.hasMoreTokens()) {
      strbf.append(st.nextToken());
    }

    return strbf.toString();
  }

  /**
   * 用于返回中文的方法
   * @param strvalue
   * @return
   */
  public static synchronized String toChinese(String strvalue) {
    try {
      if (strvalue == null) {
        return null;
      }
      else {
        strvalue = new String(strvalue.getBytes("ISO8859_1"), "GB2312");
        return strvalue;
      }
    }
    catch (Exception e) {
      return null;
    }
  }

  public static Date parseDate(String dateString) {
    try {
      if (dateString == null) {
        return new Date();
      }
      return dfk.parse(dateString);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  //返回两个日期间相差的天数
  public static long getQuot(String time1, String time2){
	    long days = 0;
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	     Date date1 = ft.parse( time1 );
	     Date date2 = ft.parse( time2 );
	     days = date2.getTime() - date1.getTime();
	     days = days/1000/60/60/24;
	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	    return days;
	}



}
