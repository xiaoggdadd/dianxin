package com.noki.jichuInfo.CbDownload;

public class ShiTiDCBean {
	//private String ID;//id
	private String  SHI;//地市
	private String XIAN;//区县
	private String XIAOQU;//乡镇
	private String JZNAME;//名称

	private String KONGTIAO;//是否有空调
	private String STATIONTYPE;//实体类型
	//private String FULL_STATION_CODE;//实体编码
	private String ZDCODE;//实体编码
	private String POWER_ID;//供电方式
	private String IS_SHARING_RENT;//是否共享外租
	private String  ASCRIPTION_UNIT;//所归属经营单位
	//private String STATION_FULL_NAME;//实体名称
	private String APPROVE_STATUS;//审核状态
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
