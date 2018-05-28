package com.noki.common.oss;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ת��������
 * @author guol
 *
 */
public class DateUtils {

	 public static final String minDate="1900-01-01";
	/**
	   * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
	   * 
	   * @param strDate
	   * @return
	   */
	public static Date toDateLong(String strDate) {
	    if(strDate == null || strDate.isEmpty()) strDate = minDate;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	}
	/**
	   * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd HH:mm:ss
	   * 
	   * @param dateDate
	   * @return
	   */
	public static String toStringLong(java.util.Date dateDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(dateDate);
	    return dateString;
	}
	/**
	   * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd
	   * 
	   * @param dateDate
	   * @param k
	   * @return
	   */
	public static String toString(java.util.Date dateDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(dateDate);
	    return dateString;
	}
	/**
	   * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd 
	   * 
	   * @param strDate
	   * @return
	   */
	public static Date toDate(String strDate) {
		if(strDate == null || strDate.isEmpty()) strDate = minDate;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	}
	/**
	   * �õ�����ʱ��
	   * 
	   * @return
	   */
	public static Date getNow() {
	    Date currentTime = new Date();
	    return currentTime;
	}
}
