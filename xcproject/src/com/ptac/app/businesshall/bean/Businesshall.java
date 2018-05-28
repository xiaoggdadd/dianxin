package com.ptac.app.businesshall.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Ӫҵ��ʵ��
 * 
 * @author guoli
 * 
 */
public class Businesshall implements Serializable {

	private Integer id;
	private String company; // ��˾
	private String branchCompany;// ���طֹ�˾
	private String entityCode;  //ʵ����
	private String entityName;  //ʵ������
	private String entityType;  //ʵ������
	private String entitySmallType;  //ʵ��������
	private String address;  //����ص�
	private String practicalUse;  //ʵ����;
	private String ownership;  //����Ȩ
	private String rightOfUse;  //ʹ��Ȩ
	private String manageDepartment;  //������
	private String personLiable;  //������
	private double builtArea;  //�������
	private String buildingStructure;  //�����ṹ
	private short isAttachedEntity;  //�Ƿ���ʵ��
	private String baseStation;  //��Ӧĸվ��
	private short status;  //״̬
	private String organization;  //ʹ�õ�λ
	private String certificateNumber;  //����֤��
	private String houseValue;  //���ݼ�ֵ
	private String sharedOrganization;  //����λ����
	private int numberOfEmployees;  //Ա������
	private String electricCurrent;  //����
	private String creater;  //������
	private String peripheralSystemCode;  //��Χϵͳ����
	private String sharedProperty;  //��������
	private String environment;  //����
	private short approveStatus;  //���״̬
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
