package com.ptac.app.relationquery.bean;

/**
 * @author lijing
 * @see 外租站点与主站点关联查询Model
 */
public class RelationQueryBean {

	private String city;//城市
	private String xian;//区县（汉字）
	private String town;//区县编码
	private String zdname;//站点名称
	private String id;//站点ID
	private String zdlx;//站点类型
	private String jzname;//主站点名称
	private String zdid;//主站点ID
	private String stationtype;//主站点类型
	private String xmh;//关联ID号
	private String wzhs;//外租回收站点数量
	private String ygl;//已关联主站点个数
	private String wgl;//未关联站点个数
	
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getXmh() {
		return xmh;
	}
	public void setXmh(String xmh) {
		this.xmh = xmh;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getWzhs() {
		return wzhs;
	}
	public void setWzhs(String wzhs) {
		this.wzhs = wzhs;
	}
	public String getYgl() {
		return ygl;
	}
	public void setYgl(String ygl) {
		this.ygl = ygl;
	}
	public String getWgl() {
		return wgl;
	}
	public void setWgl(String wgl) {
		this.wgl = wgl;
	}
}
