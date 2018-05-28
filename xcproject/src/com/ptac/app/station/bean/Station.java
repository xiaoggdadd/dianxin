package com.ptac.app.station.bean;

import java.io.Serializable;

/**
 * <p>
 * ��վ������Ϣ
 * </p>
 * 
 * @author guoli
 * @date 2017.12.1
 * @version 1.0
 * 
 */
public class Station implements Serializable {

	private Integer id;
	/** ���б��� */
	private String shi;
	/** ���ر��� */
	private String xian;
	private String xiaoqu; // ����
	/** ��վ���� */
	private String stationCode;
	/** ��վ���� */
	private String jzName;
	/** ���� */
	private String longitude;
	/** γ�� */
	private String latitude;
	/** �Ƿ��ж������ */
	private String issupervisor;
	/** ���緽ʽ */
	private String power;
	/** ��վ���� */
	private String stationType;
	/** ��վ��Ȩ���� */
	private String property;
	/** �Ƿ��пյ� */
	private String kongtiao;
	/** ��վ�ȼ� */
	private String rank;
	/** ��վ״̬ */
	private String status;
	/** IDC�������� */
	private int IDCRackNum;
	/** �Ƿ������� */
	private String isSharingRent;
	/** ��������Ӫ��Ԫ */
	private String ascriptionUnit;
	/** �������� */
	private int sharedNum;
	/** �������� */
	private String sharedName;
	/** ��ͬ���� */
	private String contractType;
	/** �����վ������������ */
	private int currentNum;
	/** ��վ��˵����������� */
	private String currentType;
	/** ���� */
	private String accountPeriod;
	/** ֧������ */
	private String branchBureau;
	/** �Ƿ������ҵ�õ����� */
	private String isIdstlElecApply;
	/** �Ƿ������ҵ�õ� */
	private String isApplyIdstlElec;
	/** �Ƿ��Ǵ�ҵ�õ� */
	private String isIdstlElec;
	/** �Ƿ����ֱ�ӽ��� */
	private String isPowerDirectTransaction;
	/** ��ҵ�߷��� */
	private double idstlElecTopPrice;
	/** ��ҵƽ�ε�� */
	private double idstlElecNormalPrice;
	/** ��ҵ�͹ȵ�� */
	private double idstlElecBottomPrice;
	/** һ�㹤�̼�������� */
	private double geneOtherBusiElecPrice;
	/** ��ѹ����� */
	private String transformerCd;
	/** ��ѹ������ */
	private double transformerCapacity;
	/** �������� */
	private String powerElement;

	//
	private String StationName; //
	private String StationFullCode; //B******/Y******
	private String collectMode; // ���ݲɼ���ʽ �칫1
	private String StationFullName; // ��վȫ��(**��**��**�� **)
	private String approveStatus = ""; // ����״̬
	private String zdcode= ""; 
	private String accountname;
	
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getZdcode() {
		return zdcode;
	}

	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getJzName() {
		return jzName;
	}

	public void setJzName(String jzName) {
		this.jzName = jzName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIssupervisor() {
		return issupervisor;
	}

	public void setIssupervisor(String issupervisor) {
		this.issupervisor = issupervisor;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKongtiao() {
		return kongtiao;
	}

	public void setKongtiao(String kongtiao) {
		this.kongtiao = kongtiao;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIDCRackNum() {
		return IDCRackNum;
	}

	public void setIDCRackNum(int iDCRackNum) {
		IDCRackNum = iDCRackNum;
	}

	public String getIsSharingRent() {
		return isSharingRent;
	}

	public void setIsSharingRent(String isSharingRent) {
		this.isSharingRent = isSharingRent;
	}

	public String getAscriptionUnit() {
		return ascriptionUnit;
	}

	public void setAscriptionUnit(String ascriptionUnit) {
		this.ascriptionUnit = ascriptionUnit;
	}

	public int getSharedNum() {
		return sharedNum;
	}

	public void setSharedNum(int sharedNum) {
		this.sharedNum = sharedNum;
	}

	public String getSharedName() {
		return sharedName;
	}

	public void setSharedName(String sharedName) {
		this.sharedName = sharedName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public String getCurrentType() {
		return currentType;
	}

	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getBranchBureau() {
		return branchBureau;
	}

	public void setBranchBureau(String branchBureau) {
		this.branchBureau = branchBureau;
	}

	public String getIsIdstlElecApply() {
		return isIdstlElecApply;
	}

	public void setIsIdstlElecApply(String isIdstlElecApply) {
		this.isIdstlElecApply = isIdstlElecApply;
	}

	public String getIsApplyIdstlElec() {
		return isApplyIdstlElec;
	}

	public void setIsApplyIdstlElec(String isApplyIdstlElec) {
		this.isApplyIdstlElec = isApplyIdstlElec;
	}

	public String getIsIdstlElec() {
		return isIdstlElec;
	}

	public void setIsIdstlElec(String isIdstlElec) {
		this.isIdstlElec = isIdstlElec;
	}

	public String getIsPowerDirectTransaction() {
		return isPowerDirectTransaction;
	}

	public void setIsPowerDirectTransaction(String isPowerDirectTransaction) {
		this.isPowerDirectTransaction = isPowerDirectTransaction;
	}

	public double getIdstlElecTopPrice() {
		return idstlElecTopPrice;
	}

	public void setIdstlElecTopPrice(double idstlElecTopPrice) {
		this.idstlElecTopPrice = idstlElecTopPrice;
	}

	public double getIdstlElecNormalPrice() {
		return idstlElecNormalPrice;
	}

	public void setIdstlElecNormalPrice(double idstlElecNormalPrice) {
		this.idstlElecNormalPrice = idstlElecNormalPrice;
	}

	public double getIdstlElecBottomPrice() {
		return idstlElecBottomPrice;
	}

	public void setIdstlElecBottomPrice(double idstlElecBottomPrice) {
		this.idstlElecBottomPrice = idstlElecBottomPrice;
	}

	public double getGeneOtherBusiElecPrice() {
		return geneOtherBusiElecPrice;
	}

	public void setGeneOtherBusiElecPrice(double geneOtherBusiElecPrice) {
		this.geneOtherBusiElecPrice = geneOtherBusiElecPrice;
	}

	public String getTransformerCd() {
		return transformerCd;
	}

	public void setTransformerCd(String transformerCd) {
		this.transformerCd = transformerCd;
	}

	public double getTransformerCapacity() {
		return transformerCapacity;
	}

	public void setTransformerCapacity(double transformerCapacity) {
		this.transformerCapacity = transformerCapacity;
	}

	public String getPowerElement() {
		return powerElement;
	}

	public void setPowerElement(String powerElement) {
		this.powerElement = powerElement;
	}

	public String getStationFullCode() {
		return StationFullCode;
	}

	public void setStationFullCode(String stationFullCode) {
		StationFullCode = stationFullCode;
	}

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}

	public String getCollectMode() {
		return collectMode;
	}

	public void setCollectMode(String collectMode) {
		this.collectMode = collectMode;
	}

	public String getStationFullName() {
		return StationFullName;
	}

	public void setStationFullName(String stationFullName) {
		StationFullName = stationFullName;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	
	

}
