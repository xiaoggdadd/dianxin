package com.ptac.app.statisticcollect.province.accountwithout.bean;

public class AccountWithoutBean {
	
	private String city;//地市
	private String zdcount;//启用站点数(个)
	private String countl;//长期(6个月以上)未报账站点数(个)
	private String countlzb;//未报账站占比(%) 
	private String counts;//长期(12个月以上)未报账站点数(个)
	private String countszb;//未报账站占比(%)
	private String zdcountsum;//启用站点数合计
	private String countlsum;//长期(6个月以上)未报账站点数合计
	private String countlzbsum;//未报账站占比合计
	private String countssum;//长期(12个月以上)未报账站点数合计
	private String countszbsum;//未报账站占比合计
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZdcount() {
		return zdcount;
	}
	public void setZdcount(String zdcount) {
		this.zdcount = zdcount;
	}
	public String getCountl() {
		return countl;
	}
	public void setCountl(String countl) {
		this.countl = countl;
	}
	public String getCountlzb() {
		return countlzb;
	}
	public void setCountlzb(String countlzb) {
		this.countlzb = countlzb;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public String getCountszb() {
		return countszb;
	}
	public void setCountszb(String countszb) {
		this.countszb = countszb;
	}
	public String getZdcountsum() {
		return zdcountsum;
	}
	public void setZdcountsum(String zdcountsum) {
		this.zdcountsum = zdcountsum;
	}
	public String getCountlsum() {
		return countlsum;
	}
	public void setCountlsum(String countlsum) {
		this.countlsum = countlsum;
	}
	public String getCountlzbsum() {
		return countlzbsum;
	}
	public void setCountlzbsum(String countlzbsum) {
		this.countlzbsum = countlzbsum;
	}
	public String getCountssum() {
		return countssum;
	}
	public void setCountssum(String countssum) {
		this.countssum = countssum;
	}
	public String getCountszbsum() {
		return countszbsum;
	}
	public void setCountszbsum(String countszbsum) {
		this.countszbsum = countszbsum;
	}
}
