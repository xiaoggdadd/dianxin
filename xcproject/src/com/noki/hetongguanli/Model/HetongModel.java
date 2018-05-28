package com.noki.hetongguanli.Model;

public class HetongModel {
	int ID;
	String STARTTIME;
	String ENDTIME;
	String PARTYA;
	String PARTYB;
	String CONTRACTNAME;
	String PROJECTAMONNT;
	String CONTRACTDETAIL;

	public String getCONTRACTDETAIL() {
		return CONTRACTDETAIL;
	}

	public void setCONTRACTDETAIL(String cONTRACTDETAIL) {
		CONTRACTDETAIL = cONTRACTDETAIL;
	}

	public String getSTARTTIME() {
		return STARTTIME;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public String getPARTYA() {
		return PARTYA;
	}

	public void setPARTYA(String pARTYA) {
		PARTYA = pARTYA;
	}

	public String getPARTYB() {
		return PARTYB;
	}

	public void setPARTYB(String pARTYB) {
		PARTYB = pARTYB;
	}

	public String getCONTRACTNAME() {
		return CONTRACTNAME;
	}

	public void setCONTRACTNAME(String cONTRACTNAME) {
		CONTRACTNAME = cONTRACTNAME;
	}

	public String getPROJECTAMONNT() {
		return PROJECTAMONNT;
	}

	public void setPROJECTAMONNT(String pROJECTAMONNT) {
		PROJECTAMONNT = pROJECTAMONNT;
	}

}
