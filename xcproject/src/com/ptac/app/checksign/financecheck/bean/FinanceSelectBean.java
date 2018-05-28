package com.ptac.app.checksign.financecheck.bean;

/**
 * 
 * 用于存放查询条件的JavaBean(用来传递查询条件；用来做查询之后的查询条件同步)
 * @author rock
 *
 */
public class FinanceSelectBean {
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
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
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	public String getLch() {
		return lch;
	}
	public void setLch(String lch) {
		this.lch = lch;
	}
	public String getBzyf() {
		return bzyf;
	}
	public void setBzyf(String bzyf) {
		this.bzyf = bzyf;
	}
	public String getLrrq() {
		return lrrq;
	}
	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}
	public String getZdqyzt() {
		return zdqyzt;
	}
	public void setZdqyzt(String zdqyzt) {
		this.zdqyzt = zdqyzt;
	}
	public String getDbqyzt() {
		return dbqyzt;
	}
	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}
	public String getCwshzt() {
		return cwshzt;
	}
	public void setCwshzt(String cwshzt) {
		this.cwshzt = cwshzt;
	}
	public String getPjlx() {
		return pjlx;
	}
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	
	@Override
	public String toString() {
		return "FinanceSelectBean [bzyf=" + bzyf + ", cwshzt=" + cwshzt
				+ ", dbqyzt=" + dbqyzt + ", lch=" + lch + ", loginId="
				+ loginId + ", lrrq=" + lrrq + ", pjlx=" + pjlx + ", shi="
				+ shi + ", xian=" + xian + ", xiaoqu=" + xiaoqu + ", zdlx="
				+ zdlx + ", zdmc=" + zdmc + ", zdqyzt=" + zdqyzt + ", zdsx="
				+ zdsx + "]";
	}
	
	private String loginId;
	private String  shi ;//市
	private String  xian;//县
	private String   xiaoqu;//小区
	private String   zdmc;//站点名称
	private String   zdlx;//站点类型
	private String   zdsx;//站点属性
	private String   lch;//流程号
	private String   bzyf;//保障月份
	private String   lrrq;//录入日期
	private String   zdqyzt;//站点启用状态
	private String   dbqyzt;//电表启用状态
	private String   cwshzt;//财务审核状态
	private String   pjlx;//票据类型
}
