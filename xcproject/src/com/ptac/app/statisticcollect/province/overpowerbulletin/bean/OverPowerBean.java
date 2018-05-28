package com.ptac.app.statisticcollect.province.overpowerbulletin.bean;

public class OverPowerBean {
	
	private String city;//地市
	private String zbzdl;//总报账电量(万度) 
	private String cbdl;//超标电量(万度) 
	private String cbzb;//超标电量占比(%) 
	private String zbzdlsum;//总报账电量合计
	private String cbdlsum;//超标电量合计
	private String cbzbsum;//超标电量占比合计
	
	public String getZbzdlsum() {
		return zbzdlsum;
	}
	public void setZbzdlsum(String zbzdlsum) {
		this.zbzdlsum = zbzdlsum;
	}
	public String getCbdlsum() {
		return cbdlsum;
	}
	public void setCbdlsum(String cbdlsum) {
		this.cbdlsum = cbdlsum;
	}
	public String getCbzbsum() {
		return cbzbsum;
	}
	public void setCbzbsum(String cbzbsum) {
		this.cbzbsum = cbzbsum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZbzdl() {
		return zbzdl;
	}
	public void setZbzdl(String zbzdl) {
		this.zbzdl = zbzdl;
	}
	public String getCbdl() {
		return cbdl;
	}
	public void setCbdl(String cbdl) {
		this.cbdl = cbdl;
	}
	public String getCbzb() {
		return cbzb;
	}
	public void setCbzb(String cbzb) {
		this.cbzb = cbzb;
	}
}
