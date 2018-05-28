package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * @Update WangYiXiao 2014-5-9 市级审核时间
 *
 */
public class ZhurenPanduanBean {

	
	public String getCountyauditstatus() {
		return countyauditstatus;
	}
	public void setCountyauditstatus(String countyauditstatus) {
		this.countyauditstatus = countyauditstatus;
	}
	public String getCityzrauditstatus() {
		return cityzrauditstatus;
	}
	public void setCityzrauditstatus(String cityzrauditstatus) {
		this.cityzrauditstatus = cityzrauditstatus;
	}
	public String getCountyauditname() {
		return countyauditname;
	}
	public void setCountyauditname(String countyauditname) {
		this.countyauditname = countyauditname;
	}
	public String getCountyaudittime() {
		return countyaudittime;
	}
	public void setCountyaudittime(String countyaudittime) {
		this.countyaudittime = countyaudittime;
	}
	public String getCityzrauditname() {
		return cityzrauditname;
	}
	public void setCityzrauditname(String cityzrauditname) {
		this.cityzrauditname = cityzrauditname;
	}
	public String getCityzraudittime() {
		return cityzraudittime;
	}
	public void setCityzraudittime(String cityzraudittime) {
		this.cityzraudittime = cityzraudittime;
	}
	public String getCsdb() {
		return csdb;
	}
	public void setCsdb(String csdb) {
		this.csdb = csdb;
	}
	public String getCITYAUDIT() {
		return CITYAUDIT;
	}
	public void setCITYAUDIT(String cITYAUDIT) {
		CITYAUDIT = cITYAUDIT;
	}
	public String getJszq() {
		return jszq;
	}
	public void setJszq(String jszq) {
		this.jszq = jszq;
	}
	
	public String getCityaudittime() {
		return cityaudittime;
	}
	public void setCityaudittime(String cityaudittime) {
		this.cityaudittime = cityaudittime;
	}

	private String countyauditstatus;//区县主人审核状态标志位
	private String cityzrauditstatus;//市主任审核状态标志位
	private String countyauditname;//区县主任审核人
	private String countyaudittime;//区县主任审核时间
	private String cityzrauditname;//市主任审核人
	private String cityzraudittime;//市主任审核时间
	private String csdb;//超省定标的比例值
	private String CITYAUDIT;//市级审核
	private String jszq;//结算周期
	private String cityaudittime;//市级审核时间
}
