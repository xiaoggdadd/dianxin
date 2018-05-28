package com.ptac.app.sitemanage.dbstationmanage.bean;

/**多表站点维护Bean
 * @author WangYiXiao 2014-12-16
 *
 */
public class DbStationManageBean {

	private String city;//城市
	private String zdname;//站点名称
	private String zdid;//站点ID
	private String property;//站点属性
	private String stationtype;//站点类型
	private String gdfs;//供电方式
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
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
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
	
}
