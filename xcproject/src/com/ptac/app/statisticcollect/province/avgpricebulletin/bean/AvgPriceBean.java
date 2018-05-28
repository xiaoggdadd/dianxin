package com.ptac.app.statisticcollect.province.avgpricebulletin.bean;

public class AvgPriceBean {

	private String city;//地市
	private String jcdj;//基础电价(元/度) 
	private String pjdj;//平均电价(元/度) 
	private String pjdjpld;//平均电价偏离度(%) 
	private String zgddj;//转供电平均电价(元/度) 
	private String zgdpld;//转供电平均电价偏离度(%) 
	
	private String jcdjsum;//基础电价合计
	private String pjdjsum;//平均电价合计
	private String pjdjpldsum;//平均电价偏离度合计 
	private String zgddjsum;//转供电平均电价合计
	private String zgdpldsum;//转供电平均电价偏离度合计
	
	public String getJcdjsum() {
		return jcdjsum;
	}
	public void setJcdjsum(String jcdjsum) {
		this.jcdjsum = jcdjsum;
	}
	public String getPjdjsum() {
		return pjdjsum;
	}
	public void setPjdjsum(String pjdjsum) {
		this.pjdjsum = pjdjsum;
	}
	public String getPjdjpldsum() {
		return pjdjpldsum;
	}
	public void setPjdjpldsum(String pjdjpldsum) {
		this.pjdjpldsum = pjdjpldsum;
	}
	public String getZgddjsum() {
		return zgddjsum;
	}
	public void setZgddjsum(String zgddjsum) {
		this.zgddjsum = zgddjsum;
	}
	public String getZgdpldsum() {
		return zgdpldsum;
	}
	public void setZgdpldsum(String zgdpldsum) {
		this.zgdpldsum = zgdpldsum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getJcdj() {
		return jcdj;
	}
	public void setJcdj(String jcdj) {
		this.jcdj = jcdj;
	}
	public String getPjdj() {
		return pjdj;
	}
	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
	}
	public String getPjdjpld() {
		return pjdjpld;
	}
	public void setPjdjpld(String pjdjpld) {
		this.pjdjpld = pjdjpld;
	}
	public String getZgddj() {
		return zgddj;
	}
	public void setZgddj(String zgddj) {
		this.zgddj = zgddj;
	}
	public String getZgdpld() {
		return zgdpld;
	}
	public void setZgdpld(String zgdpld) {
		this.zgdpld = zgdpld;
	}
}
