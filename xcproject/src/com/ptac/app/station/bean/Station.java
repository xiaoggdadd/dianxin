package com.ptac.app.station.bean;

import java.io.Serializable;

/**
 * <p>
 * 局站基本信息
 * </p>
 * 
 * @author guoli
 * @date 2017.12.1
 * @version 1.0
 * 
 */
public class Station implements Serializable {

	private Integer id;
	/** 地市编码 */
	private String shi;
	/** 区县编码 */
	private String xian;
	private String xiaoqu; // 乡镇
	/** 局站编码 */
	private String stationCode;
	/** 局站名称 */
	private String jzName;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 是否有动环监控 */
	private String issupervisor;
	/** 供电方式 */
	private String power;
	/** 局站类型 */
	private String stationType;
	/** 局站产权类型 */
	private String property;
	/** 是否有空调 */
	private String kongtiao;
	/** 局站等级 */
	private String rank;
	/** 局站状态 */
	private String status;
	/** IDC机架数量 */
	private int IDCRackNum;
	/** 是否共享外租 */
	private String isSharingRent;
	/** 所归属经营单元 */
	private String ascriptionUnit;
	/** 共享方数量 */
	private int sharedNum;
	/** 共享方名称 */
	private String sharedName;
	/** 合同类型 */
	private String contractType;
	/** 共享局站各方电流数量 */
	private int currentNum;
	/** 局站标杆电量电流类型 */
	private String currentType;
	/** 账期 */
	private String accountPeriod;
	/** 支局名称 */
	private String branchBureau;
	/** 是否满足大工业用电申请 */
	private String isIdstlElecApply;
	/** 是否申请大工业用电 */
	private String isApplyIdstlElec;
	/** 是否是大工业用电 */
	private String isIdstlElec;
	/** 是否电力直接交易 */
	private String isPowerDirectTransaction;
	/** 大工业高峰电价 */
	private double idstlElecTopPrice;
	/** 大工业平段电价 */
	private double idstlElecNormalPrice;
	/** 大工业低谷电价 */
	private double idstlElecBottomPrice;
	/** 一般工商及其他电价 */
	private double geneOtherBusiElecPrice;
	/** 变压器编号 */
	private String transformerCd;
	/** 变压器容量 */
	private double transformerCapacity;
	/** 功率因素 */
	private String powerElement;

	//
	private String StationName; //
	private String StationFullCode; //B******/Y******
	private String collectMode; // 数据采集方式 办公1
	private String StationFullName; // 局站全称(**市**县**区 **)
	private String approveStatus = ""; // 审批状态
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
