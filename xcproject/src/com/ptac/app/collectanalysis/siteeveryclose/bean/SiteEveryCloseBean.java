package com.ptac.app.collectanalysis.siteeveryclose.bean;

/**站点每月关闭情况统计bean
 * @author WangYiXiao 2014-12-30
 *
 */
public class SiteEveryCloseBean {
	private String cityid;//地市编码
	private String cityname;//地市
	private String siteproperty;//站点属性
	private String sitetype;//站点类型
	private String sitepropertycode;//站点属性编码
	private String sitetypecode;//站点类型编码
	private String starttime;//开始月份
	private String endtime;//结束月份
	private String applyclosecount;//申请关闭总数
	private String closecount;//关闭总数
	private String powerrate;//报账电费
	private String electricityquantity;//报账电量
	private String cycle;//报账周期
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getSiteproperty() {
		return siteproperty;
	}
	public void setSiteproperty(String siteproperty) {
		this.siteproperty = siteproperty;
	}
	public String getSitetype() {
		return sitetype;
	}
	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getApplyclosecount() {
		return applyclosecount;
	}
	public void setApplyclosecount(String applyclosecount) {
		this.applyclosecount = applyclosecount;
	}
	public String getClosecount() {
		return closecount;
	}
	public void setClosecount(String closecount) {
		this.closecount = closecount;
	}
	public String getPowerrate() {
		return powerrate;
	}
	public void setPowerrate(String powerrate) {
		this.powerrate = powerrate;
	}
	public String getElectricityquantity() {
		return electricityquantity;
	}
	public void setElectricityquantity(String electricityquantity) {
		this.electricityquantity = electricityquantity;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getSitepropertycode() {
		return sitepropertycode;
	}
	public void setSitepropertycode(String sitepropertycode) {
		this.sitepropertycode = sitepropertycode;
	}
	public String getSitetypecode() {
		return sitetypecode;
	}
	public void setSitetypecode(String sitetypecode) {
		this.sitetypecode = sitetypecode;
	}

	
}
