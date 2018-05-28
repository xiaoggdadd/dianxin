package com.noki.common.oss.model;

import java.util.Date;

public class OSSAccount {
	private int id;				//����ID
	private String areaName;	//��������
	private int areaId;			//����ID
	private String orgName;		//��λ
	private int staffId;		//ά����ԱID
	private String staffName;	//����
	private String username;	//ά����Աoss�˺�
	private String passWord;	//����
	private String mobileTel;	//�ֻ�
	private String email;		//����
	private String officeTel;	//�칫�绰
	private String jobName;		//ְλ
	private String sex;			//�Ա�
	private String nation;		//����
	private Date birthday;		//��������
	private String idCard;		//ʡ��֤����
	private String staffLevel;	//ְ��
	private String workArea;	//��������
	private Date beginworkdate;	//�μӹ���ʱ��
	private String nowworkdate;	//�ָڹ���ʱ��
	private String workType;	//ά��רҵ
	private String homeAddress;//��ͥסַ
	private String eduLevel;	//�Ļ��̶�
	private Date getedudate;	//��ҵʱ��
	private int state;			//��Ա״̬
	private int isDw;			//�Ƿ��ά(���ֵ����˺�/��ά��Ա�˺�)
	private Date updateDate;	//����ʱ��
	private Date createDate;	//����ʱ��
	private String isonworktext;//�ڸڷ�
	private String areaCode;	//����CODE
	private int orgId;			//��λID
	private String orgCode;		//��λCODE
	//�޲�
	public OSSAccount() {
		super();
	}
	//�вΣ���ID�У�
	public OSSAccount(int id, String areaName, int areaId, String orgName,
			int staffId, String staffName, String username, String passWord,
			String mobileTel, String email, String officeTel, String jobName,
			String sex, String nation, Date birthday, String idCard,
			String staffLevel, String workArea, Date beginworkdate,
			String nowworkdate, String workType, String homeAddress,
			String eduLevel, Date getedudate, int state, int isDw,
			Date updateDate, Date createDate, String isonworktext,String areaCode,int orgId,String orgCode) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.areaId = areaId;
		this.orgName = orgName;
		this.staffId = staffId;
		this.staffName = staffName;
		this.username = username;
		this.passWord = passWord;
		this.mobileTel = mobileTel;
		this.email = email;
		this.officeTel = officeTel;
		this.jobName = jobName;
		this.sex = sex;
		this.nation = nation;
		this.birthday = birthday;
		this.idCard = idCard;
		this.staffLevel = staffLevel;
		this.workArea = workArea;
		this.beginworkdate = beginworkdate;
		this.nowworkdate = nowworkdate;
		this.workType = workType;
		this.homeAddress = homeAddress;
		this.eduLevel = eduLevel;
		this.getedudate = getedudate;
		this.state = state;
		this.isDw = isDw;
		this.updateDate = updateDate;
		this.createDate = createDate;
		this.isonworktext = isonworktext;
		this.areaCode = areaCode;
		this.orgId = orgId;
		this.orgCode = orgCode;
	}

	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(String staffLevel) {
		this.staffLevel = staffLevel;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public Date getBeginworkdate() {
		return beginworkdate;
	}

	public void setBeginworkdate(Date beginworkdate) {
		this.beginworkdate = beginworkdate;
	}

	public String getNowworkdate() {
		return nowworkdate;
	}

	public void setNowworkdate(String nowworkdate) {
		this.nowworkdate = nowworkdate;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public Date getGetedudate() {
		return getedudate;
	}

	public void setGetedudate(Date getedudate) {
		this.getedudate = getedudate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIsDw() {
		return isDw;
	}

	public void setIsDw(int isDw) {
		this.isDw = isDw;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsonworktext() {
		return isonworktext;
	}

	public void setIsonworktext(String isonworktext) {
		this.isonworktext = isonworktext;
	}
	
}
