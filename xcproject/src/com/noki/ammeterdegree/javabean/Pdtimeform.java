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
	 * �ж����ڸ�ʽ�Ƿ���ȷ
	 * 
	 * @param date
	 *            ����Ҫ�жϸ�ʽ�Ƿ���ȷ��ʱ��
	 * @return b:����Boolean�����ݣ�true--��ʽ��ȷ��false--��ʽ����
	 */
	public boolean getDateform(String date) {
		boolean b = false;

		// ȥ���ַ�����ʽ�Ŀո��ж����ڸ�ʽ�����Ƿ����10
		String date1 = date.trim();
		if (date1.length() == 10) {
			// �ж����ڸ�ʽ���Ƿ��������������
			if (date1.split("-").length == 3) {
				// ��ȡ�����գ��ж��������Ƿ����������
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);
				String day = date1.substring(8, 10);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()
						&& pattern.matcher(day).matches()) {
					// �������գ�ת����������ʽ
					int year1 = Integer.parseInt(year);
					int month1 = Integer.parseInt(month);
					int day1 = Integer.parseInt(day);
					if ((day1 > 0 && day1 < 32) && (month1 > 0 && month1 < 13)) {
						//�ж�ʱ���Ƿ����ʱ�����1��3��5��7��8��10��12�·�--31��;���� 2�·�--29�죬�������--28�죻4��6��9��11--30��
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

		// ȥ���ַ�����ʽ�Ŀո��ж����ڸ�ʽ�����Ƿ����10
		String date1 = date.trim();
		if (date1.length() == 7) {
			// �ж����ڸ�ʽ���Ƿ��������������
			if (date1.split("-").length == 2) {
				// ��ȡ�����գ��ж��������Ƿ����������
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()) {
					// �����£�ת����������ʽ
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
	 * �ж����ڸ�ʽ�Ƿ���ȷ
	 * 
	 * @param date
	 *            ����Ҫ�жϸ�ʽ�Ƿ���ȷ��ʱ��
	 * @return b:����Boolean�����ݣ�true--��ʽ��ȷ��false--��ʽ����
	 */
	public boolean getDateform1(String date) {
		boolean b = false;

		// ȥ���ַ�����ʽ�Ŀո��ж����ڸ�ʽ�����Ƿ����10
		String date1 = date.trim();
		if (date1.length() == 10) {
			// �ж����ڸ�ʽ���Ƿ��������������
			if (date1.split("/").length == 3) {
				// ��ȡ�����գ��ж��������Ƿ����������
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);
				String day = date1.substring(8, 10);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()
						&& pattern.matcher(day).matches()) {
					// �������գ�ת����������ʽ
					int year1 = Integer.parseInt(year);
					int month1 = Integer.parseInt(month);
					int day1 = Integer.parseInt(day);
					if ((day1 > 0 && day1 < 32) && (month1 > 0 && month1 < 13)) {
						//�ж�ʱ���Ƿ����ʱ�����1��3��5��7��8��10��12�·�--31��;���� 2�·�--29�죬�������--28�죻4��6��9��11--30��
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

		// ȥ���ַ�����ʽ�Ŀո��ж����ڸ�ʽ�����Ƿ����10
		String date1 = date.trim();
		if (date1.length() == 7) {
			// �ж����ڸ�ʽ���Ƿ��������������
			if (date1.split("/").length == 2) {
				// ��ȡ�����գ��ж��������Ƿ����������
				String year = date1.substring(0, 4);
				String month = date1.substring(5, 7);

				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(year).matches()
						&& pattern.matcher(month).matches()) {
					// �����£�ת����������ʽ
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
