package com.noki.jichuInfo.CbDownload;

public class KbzBean {
	private String COSTSTATE;//状态
	private String SHINAME;//分公司名称
	private String DBCODE;//,费用对象编码,
	private String DBNAME;//费用对象名称,
	private String FPNAME;//票据类型,
	private String ZDCODE;//实体编码,
	private String ZDNAME;//实体名称,
	private String COSTTYPENAME;//费用类型,
	private String COSTTIME;//报账期间,
	private String COSTUSERNAME;//报账人,
	private String STARTTIME;//开始日期,
	private String ENDTIME;//结束日期,
	private String COSTNUM;//报账单号,
	private String BZNAME;//报账类型,
	private String YWTYPE;//业务类型,
	private String APPNUM;//SAP凭证账号
	public String getCOSTSTATE() {
		return COSTSTATE;
	}
	public void setCOSTSTATE(String cOSTSTATE) {
		COSTSTATE = cOSTSTATE;
	}
	public String getSHINAME() {
		return SHINAME;
	}
	public void setSHINAME(String sHINAME) {
		SHINAME = sHINAME;
	}
	public String getDBCODE() {
		return DBCODE;
	}
	public void setDBCODE(String dBCODE) {
		DBCODE = dBCODE;
	}
	public String getDBNAME() {
		return DBNAME;
	}
	public void setDBNAME(String dBNAME) {
		DBNAME = dBNAME;
	}
	public String getFPNAME() {
		return FPNAME;
	}
	public void setFPNAME(String fPNAME) {
		FPNAME = fPNAME;
	}
	public String getZDCODE() {
		return ZDCODE;
	}
	public void setZDCODE(String zDCODE) {
		ZDCODE = zDCODE;
	}
	public String getZDNAME() {
		return ZDNAME;
	}
	public void setZDNAME(String zDNAME) {
		ZDNAME = zDNAME;
	}
	public String getCOSTTYPENAME() {
		return COSTTYPENAME;
	}
	public void setCOSTTYPENAME(String cOSTTYPENAME) {
		COSTTYPENAME = cOSTTYPENAME;
	}
	public String getCOSTTIME() {
		return COSTTIME;
	}
	public void setCOSTTIME(String cOSTTIME) {
		COSTTIME = cOSTTIME;
	}
	public String getCOSTUSERNAME() {
		return COSTUSERNAME;
	}
	public void setCOSTUSERNAME(String cOSTUSERNAME) {
		COSTUSERNAME = cOSTUSERNAME;
	}
	public String getSTARTTIME() {
		return STARTTIME;
	}
	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}
	public String getENDTIME() {
		return ENDTIME;
	}
	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}
	public String getCOSTNUM() {
		return COSTNUM;
	}
	public void setCOSTNUM(String cOSTNUM) {
		COSTNUM = cOSTNUM;
	}
	public String getBZNAME() {
		return BZNAME;
	}
	public void setBZNAME(String bZNAME) {
		BZNAME = bZNAME;
	}
	public String getYWTYPE() {
		return YWTYPE;
	}
	public void setYWTYPE(String yWTYPE) {
		YWTYPE = yWTYPE;
	}
	public String getAPPNUM() {
		return APPNUM;
	}
	public void setAPPNUM(String aPPNUM) {
		APPNUM = aPPNUM;
	}
	public KbzBean(String cOSTSTATE, String sHINAME, String dBCODE,
			String dBNAME, String fPNAME, String zDCODE, String zDNAME,
			String cOSTTYPENAME, String cOSTTIME, String cOSTUSERNAME,
			String sTARTTIME, String eNDTIME, String cOSTNUM, String bZNAME,
			String yWTYPE, String aPPNUM) {
		super();
		COSTSTATE = cOSTSTATE;
		SHINAME = sHINAME;
		DBCODE = dBCODE;
		DBNAME = dBNAME;
		FPNAME = fPNAME;
		ZDCODE = zDCODE;
		ZDNAME = zDNAME;
		COSTTYPENAME = cOSTTYPENAME;
		COSTTIME = cOSTTIME;
		COSTUSERNAME = cOSTUSERNAME;
		STARTTIME = sTARTTIME;
		ENDTIME = eNDTIME;
		COSTNUM = cOSTNUM;
		BZNAME = bZNAME;
		YWTYPE = yWTYPE;
		APPNUM = aPPNUM;
	}
	public KbzBean() {
		super();
	}
	
	
	 
}
