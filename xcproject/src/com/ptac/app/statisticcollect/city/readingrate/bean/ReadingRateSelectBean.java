package com.ptac.app.statisticcollect.city.readingrate.bean;

/**
 * 抄表率的查询条件Bean
 * @author rock
 *
 */
public class ReadingRateSelectBean {
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	
	
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public ReadingRateSelectBean(){
		
	}
	public ReadingRateSelectBean(String shi,String xian,String startmonth,String endmonth,String zdlx,String zdsx,String gdfs){
		this.shi = shi;
		this.xian=xian;
		this.startmonth = startmonth;
		this.endmonth = endmonth;
		this.zdlx = zdlx;
		this.zdsx = zdsx;
		this.gdfs = gdfs;
	}
	
	private String shi ="";
	private String xian ="";
	private String startmonth ="";
	private String endmonth ="";
	private String zdlx ="";
	private String zdsx ="";
	private String gdfs = "";

}
