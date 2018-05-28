package com.ptac.app.statisticcollect.city.modifyunitprice.bean;

/**
 * @see 修改单价详情bean
 * @author WangYiXiao 2014-5-27 
 *
 */
public class UnitPriceDetailsBean {
	//序号 城市 区县 乡镇 站点名称 站点属性 站点类型 站点ID，电表ID 修改前单价 修改后单价
	
	private String shi;//城市
	private String xian;//区县
	private String xiaoqu;//乡镇
	private String jzname;//站点名称
	private String property;//站点属性
	private String stationtype;//站点类型
	private String zdid;//站点ID
	private String dbid;//电表ID
	private String previousunitprice;//改前单价
	private String laterunitprice;//改后单价
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
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
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
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getPreviousunitprice() {
		return previousunitprice;
	}
	public void setPreviousunitprice(String previousunitprice) {
		this.previousunitprice = previousunitprice;
	}
	public String getLaterunitprice() {
		return laterunitprice;
	}
	public void setLaterunitprice(String laterunitprice) {
		this.laterunitprice = laterunitprice;
	}
}
