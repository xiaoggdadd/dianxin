package com.noki.dgyydgl.javabean;
public class DgyydDaocBean {
	private String SHI;//城市
	private String XIAN;//区县
	private String XIAOQU;//乡镇
	private String BYQRL;//变压器容量
	private String GLYS;//功率因素
	private String DJ;//一般工商及其他电价
	private String ZHDJ;//大工业峰谷平综合电价
	private String JC;//价差
	private String ZDDJ;//最大需量40%基本电价
	private String PHDDL;//平衡点电量
	private String RL;//平衡点电量折算容量
	private String ZB;//占变压器比
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
	public String getBYQRL() {
		return BYQRL;
	}
	public void setBYQRL(String bYQRL) {
		BYQRL = bYQRL;
	}
	public String getGLYS() {
		return GLYS;
	}
	public void setGLYS(String gLYS) {
		GLYS = gLYS;
	}
	public String getDJ() {
		return DJ;
	}
	public void setDJ(String dJ) {
		DJ = dJ;
	}
	public String getZHDJ() {
		return ZHDJ;
	}
	public void setZHDJ(String zHDJ) {
		ZHDJ = zHDJ;
	}
	public String getJC() {
		return JC;
	}
	public void setJC(String jC) {
		JC = jC;
	}
	public String getZDDJ() {
		return ZDDJ;
	}
	public void setZDDJ(String zDDJ) {
		ZDDJ = zDDJ;
	}
	public String getPHDDL() {
		return PHDDL;
	}
	public void setPHDDL(String pHDDL) {
		PHDDL = pHDDL;
	}
	public String getRL() {
		return RL;
	}
	public void setRL(String rL) {
		RL = rL;
	}
	public String getZB() {
		return ZB;
	}
	public void setZB(String zB) {
		ZB = zB;
	}
	public DgyydDaocBean(String sHI, String xIAN, String xIAOQU, String bYQRL,
			String gLYS, String dJ, String zHDJ, String jC, String zDDJ,
			String pHDDL, String rL, String zB) {
		super();
		SHI = sHI;
		XIAN = xIAN;
		XIAOQU = xIAOQU;
		BYQRL = bYQRL;
		GLYS = gLYS;
		DJ = dJ;
		ZHDJ = zHDJ;
		JC = jC;
		ZDDJ = zDDJ;
		PHDDL = pHDDL;
		RL = rL;
		ZB = zB;
	}
	public DgyydDaocBean() {
		super();
	}
	
	
	
}
