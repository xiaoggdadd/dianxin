package com.ptac.app.station.bean;

/***
 * ��װ��վ��ѯ����
 * 
 * @author guoli
 * 
 */
public class StationQuery {

	private String accountId;
	private String shi;
	private String xian;
	private String xiaoqu;
	private String stationcode;
	private String stationname;
	private String collectMode;
	private String stationType;		//ʵ������
	private String ascriptionUnit;	//��������Ӫ��λ
	private String power;			//���緽ʽ
	private String approveStatus;   //���״̬
	
	public String getAscriptionUnit() {
		return ascriptionUnit;
	}

	public void setAscriptionUnit(String ascriptionUnit) {
		this.ascriptionUnit = ascriptionUnit;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getStationcode() {
		return stationcode;
	}

	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getCollectMode() {
		return collectMode;
	}

	public void setCollectMode(String collectMode) {
		this.collectMode = collectMode;
	}

}
