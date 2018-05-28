package com.ptac.app.statisticcollect.province.sitedetailcount.bean;

/**
 * @author lijing
 * @see 报账单审核统计bean
 */
public class SiteDetailCountBean {

	private String code;//编号
	private String city;//城市	
	private String xian;//县		
	//private String zry;//自然月	
	private String bztime;//报账月份
	private String passzdcount;//审核通过站点总数	
	private String xtjsdbs;//系统结算电表数	
	private String bzts;//报账条数	
	private String bzl;//报账率	
	private String zdshts;//自动审核条数
	private String zdshl;//自动审核率	
	private String xglyshts;//县管理员审核条数	
	private String xglyshl;//县管理员审核率	
	private String qxzrshts;//区县主任审核条数
	private String qxzrshl;//区县主任审核率	
	private String sglyshts;//市管理员审核条数	
	private String sglyshl;//市管理员审核率	
	private String szrshts;//市级主任审核条数	
	private String szrshl;//市级主任审核率	
	private String cwshts;//财务审核条数	
	private String cwshl;//财务审核率	
	private String dyts;//打印条数	
	private String dyl;//打印率
	private String diszdsl;//交过费用的站点数量（每个站点按照报账月份去重复）
	
	
	public String getDiszdsl() {
		return diszdsl;
	}
	public void setDiszdsl(String diszdsl) {
		this.diszdsl = diszdsl;
	}
	public SiteDetailCountBean() {
		super();
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
//	public String getZry() {
//		return zry;
//	}
//	public void setZry(String zry) {
//		this.zry = zry;
//	}
	public void setBztime(String bztime) {
		this.bztime = bztime;
	}
	public String getBztime() {
		return bztime;
	}
	public void setPasszdcount(String passzdcount) {
		this.passzdcount = passzdcount;
	}
	public String getPasszdcount() {
		return passzdcount;
	}
	public String getXtjsdbs() {
		return xtjsdbs;
	}
	public void setXtjsdbs(String xtjsdbs) {
		this.xtjsdbs = xtjsdbs;
	}
	public String getBzts() {
		return bzts;
	}
	public void setBzts(String bzts) {
		this.bzts = bzts;
	}
	public String getBzl() {
		return bzl;
	}
	public void setBzl(String bzl) {
		this.bzl = bzl;
	}
	public String getZdshts() {
		return zdshts;
	}
	public void setZdshts(String zdshts) {
		this.zdshts = zdshts;
	}
	public String getZdshl() {
		return zdshl;
	}
	public void setZdshl(String zdshl) {
		this.zdshl = zdshl;
	}
	public String getXglyshts() {
		return xglyshts;
	}
	public void setXglyshts(String xglyshts) {
		this.xglyshts = xglyshts;
	}
	public String getXglyshl() {
		return xglyshl;
	}
	public void setXglyshl(String xglyshl) {
		this.xglyshl = xglyshl;
	}
	public String getQxzrshts() {
		return qxzrshts;
	}
	public void setQxzrshts(String qxzrshts) {
		this.qxzrshts = qxzrshts;
	}
	public String getQxzrshl() {
		return qxzrshl;
	}
	public void setQxzrshl(String qxzrshl) {
		this.qxzrshl = qxzrshl;
	}
	public String getSglyshts() {
		return sglyshts;
	}
	public void setSglyshts(String sglyshts) {
		this.sglyshts = sglyshts;
	}
	public String getSglyshl() {
		return sglyshl;
	}
	public void setSglyshl(String sglyshl) {
		this.sglyshl = sglyshl;
	}
	public String getSzrshts() {
		return szrshts;
	}
	public void setSzrshts(String szrshts) {
		this.szrshts = szrshts;
	}
	public String getSzrshl() {
		return szrshl;
	}
	public void setSzrshl(String szrshl) {
		this.szrshl = szrshl;
	}
	public String getCwshts() {
		return cwshts;
	}
	public void setCwshts(String cwshts) {
		this.cwshts = cwshts;
	}
	public String getCwshl() {
		return cwshl;
	}
	public void setCwshl(String cwshl) {
		this.cwshl = cwshl;
	}
	public String getDyts() {
		return dyts;
	}
	public void setDyts(String dyts) {
		this.dyts = dyts;
	}
	public String getDyl() {
		return dyl;
	}
	public void setDyl(String dyl) {
		this.dyl = dyl;
	}
	
}
