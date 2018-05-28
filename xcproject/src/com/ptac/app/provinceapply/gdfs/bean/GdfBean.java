package com.ptac.app.provinceapply.gdfs.bean;

/**
 * @author lijing
 * @see 省申请供电方式审核实体类
 */
public class GdfBean {
	
	private String id;
	private String city;//市
	private String xian;//区县
	private String zdname;//站点名称
	private String oldg;//申请前
	private String newg;//申请后
	private String xiaoqu;//乡镇
	private String zdid;//站点ID
	private String state;//审核状态
	private boolean fjnr;//附件
	private String flgg;//省级审核更改字段,用途:省级分类审核
	private String bftg;//省级部分通过字段,用途:省级分类审核
	private String bfbtg;//省级部分不通过字段,用途:省级分类审核
	
	public String getFlgg() {
		return flgg;
	}
	public void setFlgg(String flgg) {
		this.flgg = flgg;
	}
	public String getBftg() {
		return bftg;
	}
	public void setBftg(String bftg) {
		this.bftg = bftg;
	}
	public String getBfbtg() {
		return bfbtg;
	}
	public void setBfbtg(String bfbtg) {
		this.bfbtg = bfbtg;
	}
	public boolean isFjnr() {
		return fjnr;
	}
	public void setFjnr(boolean fjnr) {
		this.fjnr = fjnr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getOldg() {
		return oldg;
	}
	public void setOldg(String oldg) {
		this.oldg = oldg;
	}
	public String getNewg() {
		return newg;
	}
	public void setNewg(String newg) {
		this.newg = newg;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
