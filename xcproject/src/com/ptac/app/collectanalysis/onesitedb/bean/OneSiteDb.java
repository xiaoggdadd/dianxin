package com.ptac.app.collectanalysis.onesitedb.bean;

/** 一站多表bean
 * @author WangYiXiao 2014-12-31
 *
 */
public class OneSiteDb {
	private String city;//地市
	private String zds;//站点数
	private String yzyb;//一站一表
	private String yzdb;//一站多表
	private String fhsyzdb;//非外借回收一站多表
	private String yzlb;//一站零表
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZds() {
		return zds;
	}
	public void setZds(String zds) {
		this.zds = zds;
	}
	public String getYzyb() {
		return yzyb;
	}
	public void setYzyb(String yzyb) {
		this.yzyb = yzyb;
	}
	public String getYzdb() {
		return yzdb;
	}
	public void setYzdb(String yzdb) {
		this.yzdb = yzdb;
	}
	public String getFhsyzdb() {
		return fhsyzdb;
	}
	public void setFhsyzdb(String fhsyzdb) {
		this.fhsyzdb = fhsyzdb;
	}
	public String getYzlb() {
		return yzlb;
	}
	public void setYzlb(String yzlb) {
		this.yzlb = yzlb;
	}
	
}
