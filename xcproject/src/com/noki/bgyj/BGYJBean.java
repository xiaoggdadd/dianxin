package com.noki.bgyj;
/**
 * Demo BGYJBean
 * 
 * @author 修改人：gaochunlei
 * @date 2017-12-28
 */
public class BGYJBean {
	/**
	 * 标杆预警ID
	 */
	private String ID;
	/**
	 * 站点ID
	 */
	private String ZDID;
	/**
	 * 当次抄表时间
	 */
	private String DCCBSJ;
	/**
	 * 上次抄表时间
	 */
	private String SCCBSJ;
	/**
	 * 当次抄表电量
	 */
	private String DCCBDL;
	/**
	 * 上次抄表电量
	 */
	private String SCCBDL;
	/**
	 * 标杆值
	 */
	private String BGZ;
	/**
	 * 状态
	 */	
	private String STATE;
	/**
	 * 站点名称=基站名称（站点表(ZHANDIAN)中的JZname）
	 */
	private String ZDMC;
	/**
	 * 站点编码
	 */
	private String ZDBM;
	/**
	 * 电表编码
	 */
	private String DBBM;
	/**
	 * 电表ID
	 */
	private String DBID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getZDID() {
		return ZDID;
	}
	public void setZDID(String zDID) {
		ZDID = zDID;
	}
	public String getDCCBSJ() {
		return DCCBSJ;
	}
	public void setDCCBSJ(String dCCBSJ) {
		DCCBSJ = dCCBSJ;
	}
	public String getSCCBSJ() {
		return SCCBSJ;
	}
	public void setSCCBSJ(String sCCBSJ) {
		SCCBSJ = sCCBSJ;
	}
	public String getDCCBDL() {
		return DCCBDL;
	}
	public void setDCCBDL(String dCCBDL) {
		DCCBDL = dCCBDL;
	}
	public String getSCCBDL() {
		return SCCBDL;
	}
	public void setSCCBDL(String sCCBDL) {
		SCCBDL = sCCBDL;
	}
	public String getBGZ() {
		return BGZ;
	}
	public void setBGZ(String bGZ) {
		BGZ = bGZ;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getZDMC() {
		return ZDMC;
	}
	public void setZDMC(String zDMC) {
		ZDMC = zDMC;
	}
	public String getZDBM() {
		return ZDBM;
	}
	public void setZDBM(String zDBM) {
		ZDBM = zDBM;
	}
	public String getDBBM() {
		return DBBM;
	}
	public void setDBBM(String dBBM) {
		DBBM = dBBM;
	}
	public String getDBID() {
		return DBID;
	}
	public void setDBID(String dBID) {
		DBID = dBID;
	}
	
}
