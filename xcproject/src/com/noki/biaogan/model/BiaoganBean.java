package com.noki.biaogan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;


public class BiaoganBean implements Comparator {
	private String ID;
	private String JZID;
	private String JZCODE;
	private String JZNAME;
	private String DBID;
	private String DBBM;
	private String BIAOGANVALUE;
	private String YEARMONTH;
	private String ZLFH;
	private String YDXS;
	private String KTXS;
	private String ZQ;
	
	
	
	

	public BiaoganBean() {
		super();
	}

	public BiaoganBean(String jZID, String jZNAME, String dBBM,
			String bIAOGANVALUE, String yEARMONTH, String zLFH, String yDXS,
			String kTXS) {
		super();
		JZID = jZID;
		JZNAME = jZNAME;
		DBBM = dBBM;
		BIAOGANVALUE = bIAOGANVALUE;
		YEARMONTH = yEARMONTH;
		ZLFH = zLFH;
		YDXS = yDXS;
		KTXS = kTXS;
	}

	public BiaoganBean(String iD, String jZID, String jZCODE, String jZNAME,
			String dBID, String dBBM, String bIAOGANVALUE, String yEARMONTH,
			String zLFH, String yDXS, String kTXS, String zQ) {
		super();
		ID = iD;
		JZID = jZID;
		JZCODE = jZCODE;
		JZNAME = jZNAME;
		DBID = dBID;
		DBBM = dBBM;
		BIAOGANVALUE = bIAOGANVALUE;
		YEARMONTH = yEARMONTH;
		ZLFH = zLFH;
		YDXS = yDXS;
		KTXS = kTXS;
		ZQ = zQ;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getZQ() {
		return ZQ;
	}

	public void setZQ(String zQ) {
		ZQ = zQ;
	}

	public String getZLFH() {
		return ZLFH;
	}

	public void setZLFH(String zLFH) {
		ZLFH = zLFH;
	}

	public String getYDXS() {
		return YDXS;
	}

	public void setYDXS(String yDXS) {
		YDXS = yDXS;
	}

	public String getKTXS() {
		return KTXS;
	}

	public void setKTXS(String kTXS) {
		KTXS = kTXS;
	}

	public String getYEARMONTH() {
		return YEARMONTH;
	}

	public void setYEARMONTH(String yEARMONTH) {
		YEARMONTH = yEARMONTH;
	}

	public String getBIAOGANVALUE() {
		return BIAOGANVALUE;
	}

	public void setBIAOGANVALUE(String bIAOGANVALUE) {
		BIAOGANVALUE = bIAOGANVALUE;
	}

	public String getJZID() {
		return JZID;
	}

	public void setJZID(String jZID) {
		JZID = jZID;
	}

	public String getJZCODE() {
		return JZCODE;
	}

	public void setJZCODE(String jZCODE) {
		JZCODE = jZCODE;
	}

	public String getJZNAME() {
		return JZNAME;
	}

	public void setJZNAME(String jZNAME) {
		JZNAME = jZNAME;
	}

	public String getDBID() {
		return DBID;
	}

	public void setDBID(String dBID) {
		DBID = dBID;
	}

	public String getDBBM() {
		return DBBM;
	}

	public void setDBBM(String dBBM) {
		DBBM = dBBM;
	}

	@Override
	public int compare(Object o1, Object o2) {
		if (o1 instanceof BiaoganBean && o2 instanceof BiaoganBean) {
			BiaoganBean b1 = (BiaoganBean) o1;
			BiaoganBean b2 = (BiaoganBean) o2;
			String b1Name = b1.getYEARMONTH();
			String b2Name = b2.getYEARMONTH();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			java.util.Date b1Date = null;
			try {
				b1Date = sdf.parse(b1Name);
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
			java.util.Date b2Date = null;
			try {
				b2Date = sdf.parse(b2Name);
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
			if (b1Date.getTime() > b2Date.getTime()) {
				return -1;
			} else if (b1Date.getTime() < b2Date.getTime()) {
				return 1;
			} else {
				return 0;
			}
		}else{
			return 0;
		}
	}

}
