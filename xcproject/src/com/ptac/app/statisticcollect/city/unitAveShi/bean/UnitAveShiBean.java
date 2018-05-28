package com.ptac.app.statisticcollect.city.unitAveShi.bean;

/**
 * @author ZengJin 2014-3-30 
 * @see 单价平均与平均单价bean
 */
public class UnitAveShiBean {
	private String shi;//城市
	private String xian;//区县
	private String bzyfstart;//报账月份起
	private String bzyfend;//报账月份止
	private String danjiapj;//单价平均
	private String pingjundj;//平均单价
	private String danjiapjqs;//单价平均趋势
	private String pingjundjqs;//平均单价趋势
	
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getBzyfstart() {
		return bzyfstart;
	}
	public void setBzyfstart(String bzyfstart) {
		this.bzyfstart = bzyfstart;
	}
	public String getBzyfend() {
		return bzyfend;
	}
	public void setBzyfend(String bzyfend) {
		this.bzyfend = bzyfend;
	}
	public String getDanjiapj() {
		return danjiapj;
	}
	public void setDanjiapj(String danjiapj) {
		this.danjiapj = danjiapj;
	}
	public String getPingjundj() {
		return pingjundj;
	}
	public void setPingjundj(String pingjundj) {
		this.pingjundj = pingjundj;
	}
	public String getDanjiapjqs() {
		return danjiapjqs;
	}
	public void setDanjiapjqs(String danjiapjqs) {
		this.danjiapjqs = danjiapjqs;
	}
	public String getPingjundjqs() {
		return pingjundjqs;
	}
	public void setPingjundjqs(String pingjundjqs) {
		this.pingjundjqs = pingjundjqs;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	
}
