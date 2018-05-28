package com.noki.baobiaohuizong.beans;

public class Bdsyswcjdbb_4 {
	private String COSTTIME;
	private String DATETIME;
	private String SHI;
	private String YSDFJE;	//预算电费金额
	private String ALLMONEY;
	private String YSWCJX;	//预算完成进行
	
	public Bdsyswcjdbb_4() {
		super();
	}

	public Bdsyswcjdbb_4(String cOSTTIME, String dATETIME, String sHI,
			String ySDFJE, String aLLMONEY, String ySWCJX) {
		super();
		COSTTIME = cOSTTIME;
		DATETIME = dATETIME;
		SHI = sHI;
		YSDFJE = ySDFJE;
		ALLMONEY = aLLMONEY;
		YSWCJX = ySWCJX;
	}

	public String getCOSTTIME() {
		return COSTTIME;
	}

	public void setCOSTTIME(String cOSTTIME) {
		COSTTIME = cOSTTIME;
	}

	public String getDATETIME() {
		return DATETIME;
	}

	public void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}

	public String getSHI() {
		return SHI;
	}

	public void setSHI(String sHI) {
		SHI = sHI;
	}

	public String getYSDFJE() {
		return YSDFJE;
	}

	public void setYSDFJE(String ySDFJE) {
		YSDFJE = ySDFJE;
	}

	public String getALLMONEY() {
		return ALLMONEY;
	}

	public void setALLMONEY(String aLLMONEY) {
		ALLMONEY = aLLMONEY;
	}

	public String getYSWCJX() {
		return YSWCJX;
	}

	public void setYSWCJX(String ySWCJX) {
		YSWCJX = ySWCJX;
	}
	
}
