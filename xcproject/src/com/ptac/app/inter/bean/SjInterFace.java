package com.ptac.app.inter.bean;

import java.util.Date;

public class SjInterFace {
	private int ID;
	private Date INITIDATE;
	private String INITIATOR;
	private String STATION;
	private String RECEIVE;
	private Date RECEIVEDATE;
	private String ACCOUNTSDATE;

	private int TOTALNUMBER;
	private Long TOTALCOST;
	private Long TOTALELECTRIC;
	private String FILES;
	private String SENDER;
	private Date SENDDATA;
	private String DATAPASSPERSONAL;
	private Date DATAPASSDATE;
	private int ID_SUP;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getINITIDATE() {
		return INITIDATE;
	}

	public void setINITIDATE(Date iNITIDATE) {
		INITIDATE = iNITIDATE;
	}

	public String getINITIATOR() {
		return INITIATOR;
	}

	public void setINITIATOR(String iNITIATOR) {
		INITIATOR = iNITIATOR;
	}

	public String getSTATION() {
		return STATION;
	}

	public void setSTATION(String sTATION) {
		STATION = sTATION;
	}

	public String getRECEIVE() {
		return RECEIVE;
	}

	public void setRECEIVE(String rECEIVE) {
		RECEIVE = rECEIVE;
	}

	public Date getRECEIVEDATE() {
		return RECEIVEDATE;
	}

	public void setRECEIVEDATE(Date rECEIVEDATE) {
		RECEIVEDATE = rECEIVEDATE;
	}

	public String getACCOUNTSDATE() {
		return ACCOUNTSDATE;
	}

	public void setACCOUNTSDATE(String aCCOUNTSDATE) {
		ACCOUNTSDATE = aCCOUNTSDATE;
	}

	public int getTOTALNUMBER() {
		return TOTALNUMBER;
	}

	public void setTOTALNUMBER(int tOTALNUMBER) {
		TOTALNUMBER = tOTALNUMBER;
	}

	
	

	public Long getTOTALCOST() {
		return TOTALCOST;
	}

	public void setTOTALCOST(Long tOTALCOST) {
		TOTALCOST = tOTALCOST;
	}

	public Long getTOTALELECTRIC() {
		return TOTALELECTRIC;
	}

	public void setTOTALELECTRIC(Long tOTALELECTRIC) {
		TOTALELECTRIC = tOTALELECTRIC;
	}

	public String getFILES() {
		return FILES;
	}

	public void setFILES(String fILES) {
		FILES = fILES;
	}

	public String getSENDER() {
		return SENDER;
	}

	public void setSENDER(String sENDER) {
		SENDER = sENDER;
	}

	public Date getSENDDATA() {
		return SENDDATA;
	}

	public void setSENDDATA(Date sENDDATA) {
		SENDDATA = sENDDATA;
	}

	public String getDATAPASSPERSONAL() {
		return DATAPASSPERSONAL;
	}

	public void setDATAPASSPERSONAL(String dATAPASSPERSONAL) {
		DATAPASSPERSONAL = dATAPASSPERSONAL;
	}

	public Date getDATAPASSDATE() {
		return DATAPASSDATE;
	}

	public void setDATAPASSDATE(Date dATAPASSDATE) {
		DATAPASSDATE = dATAPASSDATE;
	}

	public int getID_SUP() {
		return ID_SUP;
	}

	public void setID_SUP(int iDSUP) {
		ID_SUP = iDSUP;
	}

}
