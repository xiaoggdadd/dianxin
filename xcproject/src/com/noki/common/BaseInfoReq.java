package com.noki.common;

public class BaseInfoReq {
	private String MSGID="";              //ҵ����ϢID �ǿ�
	private String PMSGID="";             //PI��ϢID ҵ��˲���д
	private String SENDTIME="";           //����ʱ��ǿա�ǰ8λ��ʾ���ں�6λ��ʾʱ��
	private String S_PROVINCE="";         //����ʡ��
	private String S_SYSTEM="";              //����ϵͳ�����öˣ�
	private String SERVICENAME="";        //�ӿ�����
	private String T_PROVINCE="";         //Ŀ��ʡ��
	private String T_SYSTEM="";           //Ŀ��ϵͳ�������ṩ����
	private String RETRY;                 //���Դ���
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
