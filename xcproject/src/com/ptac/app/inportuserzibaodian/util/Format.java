package com.ptac.app.inportuserzibaodian.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijing
 * @see ��Ҫ������ת�������ݸ�ʽ
 */
public  class  Format {
	/**
	 * @author lijing
	 * @param str
	 *            String ��String���͵Ĳ���ת����double����
	 * @return ����һ��double���͵�ֵ
	 */
	public static synchronized double str_d(String str) {
		if (null == str || "null".equals(str) || str.equals("")
				|| "o".equals(str) || " ".equals(str))
			str = "0";
		double d = 0;
		d = Double.parseDouble(str);
		return d;
	}

	/**
	 * @param str
	 *            (String)
	 * @return (String)
	 * @see ʹ���ݱ���1λС��
	 * @author ZengJin 2014-1-16
	 */
	public static synchronized String s1(String str) {
		if (null == str || "null".equals(str) || str.equals("")
				|| "o".equals(str) || " ".equals(str))
			str = "0";
		DecimalFormat df = new DecimalFormat("0.0");
		df.setRoundingMode(RoundingMode.HALF_UP); 
		String d1 = df.format(Double.parseDouble(str));
		return d1;
	}

	/**
	 * @param d
	 *            (double)
	 * @return (double)
	 * @see ʹ���ݱ���1λС��
	 * @author ZengJin 2014-1-16
	 */
	public static synchronized double d1(double d) {
		BigDecimal df = new BigDecimal(String.valueOf(d));
		double df1 = df.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df1;
	}

	/**
	 * @param str
	 *            (String)
	 * @return (String)
	 * @see ʹ���ݱ���2λС��
	 * @author ZengJin 2014-1-16
	 */
	public static synchronized String str2(String str) {
		if (null == str || "null".equals(str) || str.equals("")
				|| "o".equals(str) || " ".equals(str) ||"��".equals(str)|| "-��".equals(str))
			str = "0";
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);  
		String str1 = df.format(Double.parseDouble(str));
		return str1;
	}

	/**
	 * @param d
	 *            (double)
	 * @return (double)
	 * @see ʹ���ݱ���2λС��
	 * @author ZengJin 2014-1-16
	 */
	public static synchronized double d2(double d) {
		BigDecimal df = new BigDecimal(String.valueOf(d));
		double df1 = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df1;
	}

	/**
	 * @author lijing
	 * @param str
	 *            (String)
	 * @see ��String���͵Ĳ���������λС��
	 * @return ����һ��String���͵�ֵ
	 * @modify �޸Ĵ������� ZengJin 2014-01-16
	 */
	public static synchronized String str4(String str) {
		if (null == str || "null".equals(str) || str.equals("")
				|| "o".equals(str) || " ".equals(str))
			str = "0";
		DecimalFormat df = new DecimalFormat("0.0000");
		df.setRoundingMode(RoundingMode.HALF_UP);  
		String str1 = df.format(Double.parseDouble(str));
		return str1;
	}

	/**
	 * @param d
	 *            (double)
	 * @return (double)
	 * @see ʹ���ݱ���4λС��
	 * @author ZengJin 2014-1-16
	 */
	public static synchronized double d4(double d) {
		BigDecimal df = new BigDecimal(String.valueOf(d));
		double df1 = df.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df1;
	}

	/** * �ж��ַ����Ƿ������� */
	public static synchronized boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/** * �ж��ַ����Ƿ��Ǹ����� */
	public static synchronized boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/** * �ж��ַ����Ƿ������� */
	public static synchronized boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}

	/** �ж� ʱ��Ϊ  yyyy-MM-dd
	 * @param value
	 * @return
	 */
	public static synchronized boolean isTime01(String value) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/** �ж�ʱ�� Ϊ yyyy/MM/dd 
	 * @param value
	 * @return
	 */
	public static synchronized boolean isTime02(String value){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/** ʱ���ж�
	 * @param value
	 * @return
	 */
	public static synchronized boolean isTime(String value){
		if(isTime01(value)||isTime02(value)){
			return true;
		}else{
			return false;
		}
	}
	/** �·��ж� yyyy-MM
	 * @param value
	 * @return
	 */
	public static synchronized boolean isMonth01(String value){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/** �·��ж� yyyy/MM
	 * @param value
	 * @return
	 */
	public static synchronized boolean isMonth02(String value){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**�·� �ж�
	 * @param value
	 * @return
	 */
	public static synchronized boolean isMonth(String value){
		if(isMonth01(value)||isMonth02(value)){
			return true;
		}else{
			return false;
		}
	}
	/** ʱ��ת�� 
	 * @param value
	 * @return
	 */
	public static synchronized Date String2Time(String value){
		Date data = new Date();
		SimpleDateFormat s01 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s02 = new SimpleDateFormat("yyyy/MM/dd");
		try{
		if(isTime01(value)){
			data = s01.parse(value);
		}
		if(isTime02(value)){
			data = s02.parse(value);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return data;
	}
	/** �·�ʱ��ת��ת�� 
	 * @param value
	 * @return
	 */
	public static synchronized Date String2Month(String value){
		Date data = new Date();
		SimpleDateFormat s01 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat s02 = new SimpleDateFormat("yyyy/MM");
		try{
		if(isMonth01(value)){
			data = s01.parse(value);
		}
		if(isMonth02(value)){
			data = s02.parse(value);
		}
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return data;
	}
	/** ��yyyy/mm ���͵�ʱ��ת���� yyyy-mm��
	 * @param month
	 * @return
	 */
	public static synchronized String getMonth(String month){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM"); 
		String temp = "";
		try {
			Date bdate = formatter.parse(month);
			temp = format1.format(bdate);
			return temp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	/**��yyyy/mm/dd ���͵�ʱ��ת���� yyyy-mm-dd��
	 * @param time
	 * @return
	 */
	public static synchronized String getTime(String time){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
		String temp = "";
		try {
			Date bdate = formatter.parse(time);
			temp = format1.format(bdate);
			return temp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
