package com.ptac.app.checksign.provincecheck.bean;

/**
 * @author lijing
 * @see 省级新增站点审核Bean
 */
public class ProvCheckBean {

	private String city;//城市
	private String zdname;//站点名称
	private String property;//站点属性
	private String stationtype;//站点类型
	private String zlfh;//直流负荷
	private String jlfh;//交流负荷
	private String edhdl;//额定耗电量
	private String gdfs;//供电方式
	private String gxmsg;//共享信息
	private String xian;//区县
	private String town;//乡镇
	private String zdid;//站点ID
	private String provauditstatus;//省级新增站点审核标志--->0：未审核；1：通过；2：不通过
	
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
	public String getZlfh() {
		return zlfh;
	}
	public void setZlfh(String zlfh) {
		this.zlfh = zlfh;
	}
	public String getJlfh() {
		return jlfh;
	}
	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getGxmsg() {
		return gxmsg;
	}
	public void setGxmsg(String gxmsg) {
		this.gxmsg = gxmsg;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getProvauditstatus() {
		return provauditstatus;
	}
	public void setProvauditstatus(String provauditstatus) {
		this.provauditstatus = provauditstatus;
	}
}
