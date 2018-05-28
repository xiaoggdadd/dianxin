package com.noki.zhandian;

public class ZDFeiyongBean {
	//费用汇总
	private String time;//年月
	private double sjje;//实交金额
	private double sjds;//实交读数
	private int days;//天数
	private String amid;//电量ID
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public double getSjje() {
		return sjje;
	}
	public void setSjje(double sjje) {
		this.sjje = sjje;
	}
	public double getSjds() {
		return sjds;
	}
	public void setSjds(double sjds) {
		this.sjds = sjds;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getAmid() {
		return amid;
	}
	public void setAmid(String amid) {
		this.amid = amid;
	}
	
	
	
}
