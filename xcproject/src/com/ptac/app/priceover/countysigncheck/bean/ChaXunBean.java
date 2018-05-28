package com.ptac.app.priceover.countysigncheck.bean;

/** 单价超标--区县签收及核实 查询Bean
 * @author WangYiXiao 2015-3-3
 *
 */
public class ChaXunBean {
	private String pid;//超标单价id
	private String city;//城市
	private String zdname;//站点名称
	private String pld;//偏离度
	private String standardprice;//标准单价
	private String actualprice;//实际单价
	private String bzdf;//报账电费
	private String bzdl;//报账电量
	private String accountmonth;//报账月份
	private String property;//站点属性
	private String stationtype;//站点类型
	private String gdfs;//供电方式
	private String zdid;//站点ID
	private String xian;//区县
	private String xiaoqu;//乡镇
	private String countycheck;//区县核实标识
	private String state;//市审核
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getPld() {
		return pld;
	}
	public void setPld(String pld) {
		this.pld = pld;
	}
	public String getStandardprice() {
		return standardprice;
	}
	public void setStandardprice(String standardprice) {
		this.standardprice = standardprice;
	}
	public String getActualprice() {
		return actualprice;
	}
	public void setActualprice(String actualprice) {
		this.actualprice = actualprice;
	}
	public String getBzdf() {
		return bzdf;
	}
	public void setBzdf(String bzdf) {
		this.bzdf = bzdf;
	}
	public String getBzdl() {
		return bzdl;
	}
	public void setBzdl(String bzdl) {
		this.bzdl = bzdl;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getCountycheck() {
		return countycheck;
	}
	public void setCountycheck(String countycheck) {
		this.countycheck = countycheck;
	}

	
}
