package com.noki.jichuInfo.CbDownload;

public class ShiTiDCBean {
	//private String ID;//id
	private String  SHI;//����
	private String XIAN;//����
	private String XIAOQU;//����
	private String JZNAME;//����

	private String KONGTIAO;//�Ƿ��пյ�
	private String STATIONTYPE;//ʵ������
	//private String FULL_STATION_CODE;//ʵ�����
	private String ZDCODE;//ʵ�����
	private String POWER_ID;//���緽ʽ
	private String IS_SHARING_RENT;//�Ƿ�������
	private String  ASCRIPTION_UNIT;//��������Ӫ��λ
	//private String STATION_FULL_NAME;//ʵ������
	private String APPROVE_STATUS;//���״̬
	public String getSHI() {
		return SHI;
	}
	public void setSHI(String sHI) {
		SHI = sHI;
	}
	public String getXIAN() {
		return XIAN;
	}
	public void setXIAN(String xIAN) {
		XIAN = xIAN;
	}
	public String getXIAOQU() {
		return XIAOQU;
	}
	public void setXIAOQU(String xIAOQU) {
		XIAOQU = xIAOQU;
	}
	public String getJZNAME() {
		return JZNAME;
	}
	public void setJZNAME(String jZNAME) {
		JZNAME = jZNAME;
	}
	public String getKONGTIAO() {
		return KONGTIAO;
	}
	public void setKONGTIAO(String kONGTIAO) {
		KONGTIAO = kONGTIAO;
	}
	public String getSTATIONTYPE() {
		return STATIONTYPE;
	}
	public void setSTATIONTYPE(String sTATIONTYPE) {
		STATIONTYPE = sTATIONTYPE;
	}
	public String getZDCODE() {
		return ZDCODE;
	}
	public void setZDCODE(String zDCODE) {
		ZDCODE = zDCODE;
	}
	public String getPOWER_ID() {
		return POWER_ID;
	}
	public void setPOWER_ID(String pOWERID) {
		POWER_ID = pOWERID;
	}
	public String getIS_SHARING_RENT() {
		return IS_SHARING_RENT;
	}
	public void setIS_SHARING_RENT(String iSSHARINGRENT) {
		IS_SHARING_RENT = iSSHARINGRENT;
	}
	public String getASCRIPTION_UNIT() {
		return ASCRIPTION_UNIT;
	}
	public void setASCRIPTION_UNIT(String aSCRIPTIONUNIT) {
		ASCRIPTION_UNIT = aSCRIPTIONUNIT;
	}
	public String getAPPROVE_STATUS() {
		return APPROVE_STATUS;
	}
	public void setAPPROVE_STATUS(String aPPROVESTATUS) {
		APPROVE_STATUS = aPPROVESTATUS;
	}
	public ShiTiDCBean(String sHI, String xIAN, String xIAOQU, String jZNAME,
			String kONGTIAO, String sTATIONTYPE, String zDCODE, String pOWERID,
			String iSSHARINGRENT, String aSCRIPTIONUNIT, String aPPROVESTATUS) {
		super();
		SHI = sHI;
		XIAN = xIAN;
		XIAOQU = xIAOQU;
		JZNAME = jZNAME;
		KONGTIAO = kONGTIAO;
		STATIONTYPE = sTATIONTYPE;
		ZDCODE = zDCODE;
		POWER_ID = pOWERID;
		IS_SHARING_RENT = iSSHARINGRENT;
		ASCRIPTION_UNIT = aSCRIPTIONUNIT;
		APPROVE_STATUS = aPPROVESTATUS;
	}
	public ShiTiDCBean() {
		super();
	}
	
}
