package com.noki.jichuInfo.yspz;

/**
 * Ԥ������BEAN
 * @author Administrator
 * Ԥ�����ù���bean
 */
public class YSPZBEAN {
	/**
	 * //Ψһ����
	 */
	private int yid;
	/**
	 * //����
	 */
	private String SHI;
	/**
	 * //�·�
	 */
	private String MONTH;
	/**
	 * //�����Ԫ
	 */
	private double MONEY;
	/**
	 * //״̬ 0����   1ɾ��
	 */
	private int STATE;
	/**
	 * //¼��ʱ��
	 */
	private String INSERTTIME;
	/**
	 * //�޸�ʱ��
	 */
	private String UPDATETIME;
	/***
	 * //¼����
	 */
	private String ENTRYPERSON;
	
	
	/**
	 * �޲�
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
