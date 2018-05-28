package com.ptac.app.provinceapply.gbsq.bean;

/**
 * @author lijing
 * @see 省申请改标审核实体类
 */
public class GbsqBean {
	
	private String id;
	private String city;//市
	private String xian;//区县
	private String zdname;//站点名称
	private String oldq;//申请前
	private String newq;//申请后
	private String zlfh;//直流负荷
	private String jlfh;//交流负荷
	private String edhdl;//额定耗电量
	private String xiaoqu;//乡镇
	private String zdid;//站点ID
	private String state;//审核状态
	private Boolean fjnr;//是否存在附件
	
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
	public String getOldq() {
		return oldq;
	}
	public void setOldq(String oldq) {
		this.oldq = oldq;
	}
	public String getNewq() {
		return newq;
	}
	public void setNewq(String newq) {
		this.newq = newq;
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
	public Boolean getFjnr() {
		return fjnr;
	}
	public void setFjnr(Boolean fjnr) {
		this.fjnr = fjnr;
	}

	
}
