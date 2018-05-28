package com.ptac.app.priceover.citysend.bean;

/**
 * @author lijing
 * @see 地市签收及派单实体类
 */
public class CitySendBean {

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
	private String state;//市派单状态
	private String lasttime;//上次抄表时间
	private String thistime;//本次抄表时间
	private String lastdegree;//上次电表读数
	private String thisdegree;//本次电表读数
	private String beilv;//倍率
	private String dbydl;//电表用电量
	private String accountmonth;//报账月份
	private String id;
	
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
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getDbydl() {
		return dbydl;
	}
	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
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
