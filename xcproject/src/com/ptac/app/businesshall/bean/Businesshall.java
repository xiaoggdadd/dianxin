package com.ptac.app.businesshall.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 营业厅实体
 * 
 * @author guoli
 * 
 */
public class Businesshall implements Serializable {

	private Integer id;
	private String company; // 公司
	private String branchCompany;// 区县分公司
	private String entityCode;  //实体编号
	private String entityName;  //实体名称
	private String entityType;  //实体类型
	private String entitySmallType;  //实体子类型
	private String address;  //坐落地点
	private String practicalUse;  //实际用途
	private String ownership;  //所有权
	private String rightOfUse;  //使用权
	private String manageDepartment;  //管理部门
	private String personLiable;  //责任人
	private double builtArea;  //建筑面积
	private String buildingStructure;  //建筑结构
	private short isAttachedEntity;  //是否附属实体
	private String baseStation;  //对应母站房
	private short status;  //状态
	private String organization;  //使用单位
	private String certificateNumber;  //房产证号
	private String houseValue;  //房屋价值
	private String sharedOrganization;  //共享单位名称
	private int numberOfEmployees;  //员工人数
	private String electricCurrent;  //电流
	private String creater;  //创建人
	private String peripheralSystemCode;  //外围系统编码
	private String sharedProperty;  //共享属性
	private String environment;  //环境
	private short approveStatus;  //审核状态
	private Date createDate;  //

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBranchCompany() {
		return branchCompany;
	}

	public void setBranchCompany(String branchCompany) {
		this.branchCompany = branchCompany;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntitySmallType() {
		return entitySmallType;
	}

	public void setEntitySmallType(String entitySmallType) {
		this.entitySmallType = entitySmallType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPracticalUse() {
		return practicalUse;
	}

	public void setPracticalUse(String practicalUse) {
		this.practicalUse = practicalUse;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getRightOfUse() {
		return rightOfUse;
	}

	public void setRightOfUse(String rightOfUse) {
		this.rightOfUse = rightOfUse;
	}

	public String getManageDepartment() {
		return manageDepartment;
	}

	public void setManageDepartment(String manageDepartment) {
		this.manageDepartment = manageDepartment;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public double getBuiltArea() {
		return builtArea;
	}

	public void setBuiltArea(double builtArea) {
		this.builtArea = builtArea;
	}

	public String getBuildingStructure() {
		return buildingStructure;
	}

	public void setBuildingStructure(String buildingStructure) {
		this.buildingStructure = buildingStructure;
	}

	public short isAttachedEntity() {
		return isAttachedEntity;
	}

	public void setAttachedEntity(short isAttachedEntity) {
		this.isAttachedEntity = isAttachedEntity;
	}

	public String getBaseStation() {
		return baseStation;
	}

	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(String houseValue) {
		this.houseValue = houseValue;
	}

	public String getSharedOrganization() {
		return sharedOrganization;
	}

	public void setSharedOrganization(String sharedOrganization) {
		this.sharedOrganization = sharedOrganization;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public String getElectricCurrent() {
		return electricCurrent;
	}

	public void setElectricCurrent(String electricCurrent) {
		this.electricCurrent = electricCurrent;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getPeripheralSystemCode() {
		return peripheralSystemCode;
	}

	public void setPeripheralSystemCode(String peripheralSystemCode) {
		this.peripheralSystemCode = peripheralSystemCode;
	}

	public String getSharedProperty() {
		return sharedProperty;
	}

	public void setSharedProperty(String sharedProperty) {
		this.sharedProperty = sharedProperty;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public short getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(short approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
