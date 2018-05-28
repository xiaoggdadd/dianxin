package com.noki.zhandian;
//缴费详细
public class FeiyongBean {
	private String time;//报账日期
	private String begin;//开始时间
	private String end;//本月结束时间
	private double begins;//开始电表读数
	private double ends;//结束电表读数
	private double dians;//电量总额
	private double jinez;//电费总额
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public double getBegins() {
		return begins;
	}
	public void setBegins(double begins) {
		this.begins = begins;
	}
	public double getEnds() {
		return ends;
	}
	public void setEnds(double ends) {
		this.ends = ends;
	}
	public double getDians() {
		return dians;
	}
	public void setDians(double dians) {
		this.dians = dians;
	}
	public double getJinez() {
		return jinez;
	}
	public void setJinez(double jinez) {
		this.jinez = jinez;
	}
	
}
