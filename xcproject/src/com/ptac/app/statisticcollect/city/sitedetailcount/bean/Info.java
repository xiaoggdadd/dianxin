package com.ptac.app.statisticcollect.city.sitedetailcount.bean;

public class Info {

	private String id;//报账单号
	private String zdname;//站点名称	
	private String blhdl;//报账电量	
	private String actualpay;//报账电费
	private String shr;//审核人	
	private String shtime;//审核时间
	private String passly;//通过理由
	private String shzt;//审核状态
	
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShtime() {
		return shtime;
	}
	public void setShtime(String shtime) {
		this.shtime = shtime;
	}
	public String getPassly() {
		return passly;
	}
	public void setPassly(String passly) {
		this.passly = passly;
	}
					
}
