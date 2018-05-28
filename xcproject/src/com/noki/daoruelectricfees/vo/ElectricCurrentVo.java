package com.noki.daoruelectricfees.vo;

/**
 * 监控电流实体
 * 
 * @author guoli
 * 
 */
public class ElectricCurrentVo {

	private String stationNo;
	private String value;
	private String sbdl; //监控主设备电量
	private String daynum;//监控用电天数 

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSbdl() {
		return sbdl;
	}

	public void setSbdl(String sbdl) {
		this.sbdl = sbdl;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

}
