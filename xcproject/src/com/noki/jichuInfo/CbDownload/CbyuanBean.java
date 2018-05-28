package com.noki.jichuInfo.CbDownload;

public class CbyuanBean {
	
	private String ACCOUNTNAME;
	private String NAME;
	private String ROLENAME;
	//private String ROWNUM;
	//private String JZNAME;
	/*public String getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(String rOWNUM) {
		ROWNUM = rOWNUM;
	}*/
	public String getACCOUNTNAME() {
		return ACCOUNTNAME;
	}
	public void setACCOUNTNAME(String aCCOUNTNAME) {
		ACCOUNTNAME = aCCOUNTNAME;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	
	public String getROLENAME() {
		return ROLENAME;
	}
	public void setROLENAME(String rOLENAME) {
		ROLENAME = rOLENAME;
	}
	
	public CbyuanBean(String aCCOUNTNAME, String nAME, String rOLENAME) {
		super();
		ACCOUNTNAME = aCCOUNTNAME;
		NAME = nAME;
		ROLENAME = rOLENAME;
	}
	public CbyuanBean() {
		super();
	}
	
	/*public String getJZNAME() {
		return JZNAME;
	}
	public void setJZNAME(String jZNAME) {
		JZNAME = jZNAME;
	}*/
	
	
}
