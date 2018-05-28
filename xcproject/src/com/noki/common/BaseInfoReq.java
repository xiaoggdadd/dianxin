package com.noki.common;

public class BaseInfoReq {
	private String MSGID="";              //业务消息ID 非空
	private String PMSGID="";             //PI消息ID 业务端不填写
	private String SENDTIME="";           //发送时间非空、前8位表示日期后6位表示时间
	private String S_PROVINCE="";         //调用省份
	private String S_SYSTEM="";              //调用系统（调用端）
	private String SERVICENAME="";        //接口名称
	private String T_PROVINCE="";         //目标省份
	private String T_SYSTEM="";           //目标系统（服务提供方）
	private String RETRY;                 //重试次数
	public String getMSGID() {
		return MSGID;
	}
	public void setMSGID(String mSGID) {
		MSGID = mSGID;
	}
	public String getPMSGID() {
		return PMSGID;
	}
	public void setPMSGID(String pMSGID) {
		PMSGID = pMSGID;
	}
	public String getSENDTIME() {
		return SENDTIME;
	}
	public void setSENDTIME(String sENDTIME) {
		SENDTIME = sENDTIME;
	}
	public String getS_PROVINCE() {
		return S_PROVINCE;
	}
	public void setS_PROVINCE(String sPROVINCE) {
		S_PROVINCE = sPROVINCE;
	}
	public String getS_SYSTEM() {
		return S_SYSTEM;
	}
	public void setS_SYSTEM(String sSYSTEM) {
		S_SYSTEM = sSYSTEM;
	}
	public String getSERVICENAME() {
		return SERVICENAME;
	}
	public void setSERVICENAME(String sERVICENAME) {
		SERVICENAME = sERVICENAME;
	}
	public String getT_PROVINCE() {
		return T_PROVINCE;
	}
	public void setT_PROVINCE(String tPROVINCE) {
		T_PROVINCE = tPROVINCE;
	}
	public String getT_SYSTEM() {
		return T_SYSTEM;
	}
	public void setT_SYSTEM(String tSYSTEM) {
		T_SYSTEM = tSYSTEM;
	}
	public String getRETRY() {
		return RETRY;
	}
	public void setRETRY(String rETRY) {
		RETRY = rETRY;
	}
}
