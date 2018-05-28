package com.noki.jichuInfo.yspz;

/**
 * 预算配置BEAN
 * @author Administrator
 * 预算配置管理bean
 */
public class YSPZBEAN {
	/**
	 * //唯一主键
	 */
	private int yid;
	/**
	 * //地市
	 */
	private String SHI;
	/**
	 * //月份
	 */
	private String MONTH;
	/**
	 * //金额万元
	 */
	private double MONEY;
	/**
	 * //状态 0可用   1删除
	 */
	private int STATE;
	/**
	 * //录入时间
	 */
	private String INSERTTIME;
	/**
	 * //修改时间
	 */
	private String UPDATETIME;
	/***
	 * //录入人
	 */
	private String ENTRYPERSON;
	
	
	/**
	 * 无参
	 */
	public YSPZBEAN() {
		super();
	}


	/**
	 * 
	 * @param sHI
	 * @param mONTH
	 * @param mONEY
	 * @param sTATE
	 * @param iNSERTTIME
	 * @param uPDATETIME
	 * @param eNTRYPERSON
	 */
	public YSPZBEAN(String sHI, String mONTH, double mONEY, int sTATE,
			String iNSERTTIME, String uPDATETIME, String eNTRYPERSON) {
		super();
		SHI = sHI;
		MONTH = mONTH;
		MONEY = mONEY;
		STATE = sTATE;
		INSERTTIME = iNSERTTIME;
		UPDATETIME = uPDATETIME;
		ENTRYPERSON = eNTRYPERSON;
	}




	/**
	 * 
	 * @param yid
	 * @param sHI
	 * @param mONTH
	 * @param mONEY
	 * @param sTATE
	 * @param iNSERTTIME
	 * @param uPDATETIME
	 * @param eNTRYPERSON
	 */
	public YSPZBEAN(int yid, String sHI, String mONTH, double mONEY,
			int sTATE, String iNSERTTIME, String uPDATETIME,
			String eNTRYPERSON) {
		super();
		this.yid = yid;
		SHI = sHI;
		MONTH = mONTH;
		MONEY = mONEY;
		STATE = sTATE;
		INSERTTIME = iNSERTTIME;
		UPDATETIME = uPDATETIME;
		ENTRYPERSON = eNTRYPERSON;
	}

/**
 * 
 * @return
 */
	public int getYid() {
		return yid;
	}

/**
 * 
 * @param yid
 */
	public void setYid(int yid) {
		this.yid = yid;
	}

/**
 * 
 * @return
 */
	public String getSHI() {
		return SHI;
	}

/**
 * 
 * @param sHI
 */
	public void setSHI(String sHI) {
		SHI = sHI;
	}

/**
 * 
 * @return
 */
	public String getMONTH() {
		return MONTH;
	}

/**
 * 
 * @param mONTH
 */
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}

/**
 * 
 * @return
 */
	public double getMONEY() {
		return MONEY;
	}

/**
 * 
 * @param mONEY
 */
	public void setMONEY(double mONEY) {
		MONEY = mONEY;
	}

/**
 * 
 * @return
 */
	public int getSTATE() {
		return STATE;
	}

/**
 * 
 * @param sTATE
 */
	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}

/**
 * 
 * @return
 */
	public String getINSERTTIME() {
		return INSERTTIME;
	}

/**
 * 
 * @param iNSERTTIME
 */
	public void setINSERTTIME(String iNSERTTIME) {
		INSERTTIME = iNSERTTIME;
	}

/***
 * 
 * @return
 */
	public String getUPDATETIME() {
		return UPDATETIME;
	}

/**
 * 
 * @param uPDATETIME
 */
	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

/***
 * 
 * @return
 */
	public String getENTRYPERSON() {
		return ENTRYPERSON;
	}

/**
 * 
 * @param eNTRYPERSON
 */
	public void setENTRYPERSON(String eNTRYPERSON) {
		ENTRYPERSON = eNTRYPERSON;
	}

	

}
