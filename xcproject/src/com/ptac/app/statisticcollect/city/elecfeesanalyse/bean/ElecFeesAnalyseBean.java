package com.ptac.app.statisticcollect.city.elecfeesanalyse.bean;

/**
 * 市级电费分析通报bean
 * @author WangYiXiao 2015-3-30
 *
 */
public class ElecFeesAnalyseBean {
	private String shi;//市
	private String city;//区县
	private String lastsameyuef;//报账月份的上一年日期
	private String lastyuef;//报账月份上个月日期
	private String accountmonthf;//报账月份当月日期
	private String tb1;//同比
	private String hb1;//环比
	private String last1toaf;//上年1月份到报账月份累计数据
	private String the1toaf;//一月份到报账月份累计数据
	private String tb2;//同比
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLastsameyuef() {
		return lastsameyuef;
	}
	public void setLastsameyuef(String lastsameyuef) {
		this.lastsameyuef = lastsameyuef;
	}
	public String getLastyuef() {
		return lastyuef;
	}
	public void setLastyuef(String lastyuef) {
		this.lastyuef = lastyuef;
	}
	public String getAccountmonthf() {
		return accountmonthf;
	}
	public void setAccountmonthf(String accountmonthf) {
		this.accountmonthf = accountmonthf;
	}
	public String getTb1() {
		return tb1;
	}
	public void setTb1(String tb1) {
		this.tb1 = tb1;
	}
	public String getHb1() {
		return hb1;
	}
	public void setHb1(String hb1) {
		this.hb1 = hb1;
	}
	public String getLast1toaf() {
		return last1toaf;
	}
	public void setLast1toaf(String last1toaf) {
		this.last1toaf = last1toaf;
	}
	public String getThe1toaf() {
		return the1toaf;
	}
	public void setThe1toaf(String the1toaf) {
		this.the1toaf = the1toaf;
	}
	public String getTb2() {
		return tb2;
	}
	public void setTb2(String tb2) {
		this.tb2 = tb2;
	}
}
