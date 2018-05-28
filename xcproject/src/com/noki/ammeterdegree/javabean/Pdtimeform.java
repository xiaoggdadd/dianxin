package com.noki.ammeterdegree.javabean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pdtimeform {

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pdtimeform p = new Pdtimeform();
		p.getDateform("2001-02-31");
	}*/

	/**
	 * 判断日期格式是否正确
	 * 
	 * @param date
	 *            ：需要判断格式是否正确的时间
	 * @return b:返回Boolean型数据，true--格式正确，false--格式错误
	 */
	public boolean getDateform(String date) {
		boolean b = false;

		// 去除字符串格式的空格，判断日期格式长度是否等于10
		String date1 = date.trim();
		if (date1.length() == 10) {
			// 判断日期格式中是否包含两个“—”
			if (date1.split("-").length == 3) {
				// 截取年月日，判断年月日是否是数字组成
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);
				String day = date1.substring(8, 10);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()
						&& pattern.matcher(day).matches()) {
					// 把年月日，转换成整数形式
					int year1 = Integer.parseInt(year);
					int month1 = Integer.parseInt(month);
					int day1 = Integer.parseInt(day);
					if ((day1 > 0 && day1 < 32) && (month1 > 0 && month1 < 13)) {
						//判断时间是否符合时间规则：1、3、5、7、8、10、12月份--31天;闰年 2月份--29天，其他年份--28天；4、6、9、11--30天
						switch (month1) {
						case 1:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 2:
							if(((year1%4==0)&&(year1%100>0))||(year1%400==0)){
								if (day1 > 0 && day1 < 30) {
									b = true;
								}else{
									b = false;
								}								
							}else{
								if(day1 > 0 && day1 < 29){
									b = true;
								}else{
									b = false;
								}
							}							
							break;
						case 3:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 4:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 5:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 6:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 7:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 8:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 9:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 10:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 11:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 12:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;							
						}
					} else {
						b = false;
					}
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		return b;
	}
	public boolean getMonthform(String date) {
		boolean b = false;

		// 去除字符串格式的空格，判断日期格式长度是否等于10
		String date1 = date.trim();
		if (date1.length() == 7) {
			// 判断日期格式中是否包含两个“—”
			if (date1.split("-").length == 2) {
				// 截取年月日，判断年月日是否是数字组成
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()) {
					// 把年月，转换成整数形式
					int month1 = Integer.parseInt(month);
					if (month1 > 0 && month1 < 13) {
						b = true;
					} else {
						b = false;
					}
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		return b;
	}
	/**
	 * 判断日期格式是否正确
	 * 
	 * @param date
	 *            ：需要判断格式是否正确的时间
	 * @return b:返回Boolean型数据，true--格式正确，false--格式错误
	 */
	public boolean getDateform1(String date) {
		boolean b = false;

		// 去除字符串格式的空格，判断日期格式长度是否等于10
		String date1 = date.trim();
		if (date1.length() == 10) {
			// 判断日期格式中是否包含两个“—”
			if (date1.split("/").length == 3) {
				// 截取年月日，判断年月日是否是数字组成
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);
				String day = date1.substring(8, 10);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()
						&& pattern.matcher(day).matches()) {
					// 把年月日，转换成整数形式
					int year1 = Integer.parseInt(year);
					int month1 = Integer.parseInt(month);
					int day1 = Integer.parseInt(day);
					if ((day1 > 0 && day1 < 32) && (month1 > 0 && month1 < 13)) {
						//判断时间是否符合时间规则：1、3、5、7、8、10、12月份--31天;闰年 2月份--29天，其他年份--28天；4、6、9、11--30天
						switch (month1) {
						case 1:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 2:
							if(((year1%4==0)&&(year1%100>0))||(year1%400==0)){
								if (day1 > 0 && day1 < 30) {
									b = true;
								}else{
									b = false;
								}								
							}else{
								if(day1 > 0 && day1 < 29){
									b = true;
								}else{
									b = false;
								}
							}							
							break;
						case 3:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 4:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 5:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 6:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 7:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 8:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 9:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 10:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;
						case 11:
							if (day1 > 0 && day1 < 31) {
								b = true;
							}
							break;
						case 12:
							if (day1 > 0 && day1 < 32) {
								b = true;
							}
							break;							
						}
					} else {
						b = false;
					}
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		return b;
	}
	public boolean getMonthform1(String date) {
		boolean b = false;

		// 去除字符串格式的空格，判断日期格式长度是否等于10
		String date1 = date.trim();
		if (date1.length() == 7) {
			// 判断日期格式中是否包含两个“—”
			if (date1.split("/").length == 2) {
				// 截取年月日，判断年月日是否是数字组成
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()) {
					// 把年月，转换成整数形式
					int month1 = Integer.parseInt(month);
					if (month1 > 0 && month1 < 13) {
						b = true;
					} else {
						b = false;
					}
				} else {
					b = false;
				}
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		return b;
	}

}
