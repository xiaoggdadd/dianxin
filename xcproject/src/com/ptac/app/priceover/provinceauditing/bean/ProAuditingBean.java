package com.ptac.app.priceover.provinceauditing.bean;

/**
 * @author lijing
 * @see 单价超标市审核实体类
 */
public class ProAuditingBean {

	private String id;
	private String city;//市
	private String zdname;//站点名称
	private String pld;//偏离度
	private String bzdj;//标准单价
	private String sjdj;//实际单价
	private String bzdf;//报账电费
	private String bzdl;//报账电量
	private String zdsx;//站点属性
	private String zdlx;//站点类型
	private String gdfs;//供电方式
	private String zdid;//站点ID
	private String xian;//区县
	private String xiaoqu;//乡镇
	private String state;//省级审核状态
	private String accountmonth;//报账月份
	private String yzydlx;//业主用电类型
	private String yzhdjcdj;//业主获电基础单价
	private String hzdj;//核准单价
	//private String sbzdj;//省标准单价
	private String jfdlzb;//尖峰电量(占比)
	private String pdlzb;//平电量(占比)
	private String zgdjcdj;//直供电基础单价
	private String gfdlzb;//高峰电量(占比)
	private String gdlzb;//谷电量(占比)
	private String byqrl;//变压器容量
	private String beilv;//倍率
	private String ydsx;//用电属性(生产/非生产)
	private String sdb;//省定标
	private String xbsl;//线变损率
	private String xbsdl;//线变损电量(线变损率*省定标)
	private String glfsfxs;//管理费上浮系数(%)
	private String msfxs;//忙时上浮系数(%)
	
	public String getYzydlx() {
		return yzydlx;
	}
	public void setYzydlx(String yzydlx) {
		this.yzydlx = yzydlx;
	}
	public String getYzhdjcdj() {
		return yzhdjcdj;
	}
	public void setYzhdjcdj(String yzhdjcdj) {
		this.yzhdjcdj = yzhdjcdj;
	}
	public String getHzdj() {
		return hzdj;
	}
	public void setHzdj(String hzdj) {
		this.hzdj = hzdj;
	}
//	public String getSbzdj() {
//		return sbzdj;
//	}
//	public void setSbzdj(String sbzdj) {
//		this.sbzdj = sbzdj;
//	}
	public String getJfdlzb() {
		return jfdlzb;
	}
	public void setJfdlzb(String jfdlzb) {
		this.jfdlzb = jfdlzb;
	}
	public String getPdlzb() {
		return pdlzb;
	}
	public void setPdlzb(String pdlzb) {
		this.pdlzb = pdlzb;
	}
	public String getZgdjcdj() {
		return zgdjcdj;
	}
	public void setZgdjcdj(String zgdjcdj) {
		this.zgdjcdj = zgdjcdj;
	}
	public String getGfdlzb() {
		return gfdlzb;
	}
	public void setGfdlzb(String gfdlzb) {
		this.gfdlzb = gfdlzb;
	}
	public String getGdlzb() {
		return gdlzb;
	}
	public void setGdlzb(String gdlzb) {
		this.gdlzb = gdlzb;
	}
	public String getByqrl() {
		return byqrl;
	}
	public void setByqrl(String byqrl) {
		this.byqrl = byqrl;
	}
	public String getYdsx() {
		return ydsx;
	}
	public void setYdsx(String ydsx) {
		this.ydsx = ydsx;
	}
	public String getSdb() {
		return sdb;
	}
	public void setSdb(String sdb) {
		this.sdb = sdb;
	}
	public String getXbsl() {
		return xbsl;
	}
	public void setXbsl(String xbsl) {
		this.xbsl = xbsl;
	}
	public String getXbsdl() {
		return xbsdl;
	}
	public void setXbsdl(String xbsdl) {
		this.xbsdl = xbsdl;
	}
	public String getGlfsfxs() {
		return glfsfxs;
	}
	public void setGlfsfxs(String glfsfxs) {
		this.glfsfxs = glfsfxs;
	}
	public String getMsfxs() {
		return msfxs;
	}
	public void setMsfxs(String msfxs) {
		this.msfxs = msfxs;
	}
	public String getPld() {
		return pld;
	}
	public void setPld(String pld) {
		this.pld = pld;
	}
	public String getBzdj() {
		return bzdj;
	}
	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}
	public String getSjdj() {
		return sjdj;
	}
	public void setSjdj(String sjdj) {
		this.sjdj = sjdj;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
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
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
