package com.noki.daoruelectricfees.vo;

/**
 * ��ص���ʵ��
 * 
 * @author guoli
 * 
 */
public class ElectricCurrentVo {

	private String stationNo;
	private String value;
	private String sbdl; //������豸����
	private String daynum;//����õ����� 

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
