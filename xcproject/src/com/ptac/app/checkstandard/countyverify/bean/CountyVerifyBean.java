package com.ptac.app.checkstandard.countyverify.bean;

/**
 * @author lijing
 * @see 区县签收及核实信息实体类
 */
public class CountyVerifyBean {

	private String city;//市
	private String zdname;//站点名称
	private String xczb;//相差比例
	private String scb;//生产标(度)
	private String jyscb;//建议生产标(度)
	private String zlfh;//直流负荷(度)
	private String jlfh;//交流负荷(度)
	private String bdb;//本地标(度)
	private String zdsx;//站点属性
	private String zdid;//站点ID
	private String xian;//区县
	private String xiaoqu;//乡镇
	private String state;//状态
	private String id;
	private String lasttime;//上次抄表时间
	private String thistime;//本次抄表时间
	private String lastdegree;//上次电表读数
	private String thisdegree;//本次电表读数
	private String beilv;//倍率
	private String dbydl;//电表用电量
	private String bzdf;//报账金额
	private String bzdl;//报账电量
	private String accountmonth;//报账月份
	private String citystate;//市级主任审核状态
	
	private String zlfhold;//直流负荷
	private String jlfhold;//交流负荷
	private String edhdlold;//本地标
	private String scbold;//生产标
	private String zlfhnew;//核实后直流
	private String jlfhnew;//核实后交流
	private String edhdlnew;//核实后本地标
	private String scbnew;//核实后生产标
	
	public String getZlfhold() {
		return zlfhold;
	}
	public void setZlfhold(String zlfhold) {
		this.zlfhold = zlfhold;
	}
	public String getJlfhold() {
		return jlfhold;
	}
	public void setJlfhold(String jlfhold) {
		this.jlfhold = jlfhold;
	}
	public String getEdhdlold() {
		return edhdlold;
	}
	public void setEdhdlold(String edhdlold) {
		this.edhdlold = edhdlold;
	}
	public String getScbold() {
		return scbold;
	}
	public void setScbold(String scbold) {
		this.scbold = scbold;
	}
	public String getZlfhnew() {
		return zlfhnew;
	}
	public void setZlfhnew(String zlfhnew) {
		this.zlfhnew = zlfhnew;
	}
	public String getJlfhnew() {
		return jlfhnew;
	}
	public void setJlfhnew(String jlfhnew) {
		this.jlfhnew = jlfhnew;
	}
	public String getEdhdlnew() {
		return edhdlnew;
	}
	public void setEdhdlnew(String edhdlnew) {
		this.edhdlnew = edhdlnew;
	}
	public String getScbnew() {
		return scbnew;
	}
	public void setScbnew(String scbnew) {
		this.scbnew = scbnew;
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
	public String getCitystate() {
		return citystate;
	}
	public void setCitystate(String citystate) {
		this.citystate = citystate;
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
	public String getXczb() {
		return xczb;
	}
	public void setXczb(String xczb) {
		this.xczb = xczb;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getJyscb() {
		return jyscb;
	}
	public void setJyscb(String jyscb) {
		this.jyscb = jyscb;
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
	public String getBdb() {
		return bdb;
	}
	public void setBdb(String bdb) {
		this.bdb = bdb;
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
