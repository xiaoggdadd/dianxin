package com.noki.chaobiaorenyuan.bean;

public class WenJian {
	private String ID;			//文件表主键
	private String SCR_ID;		//上传人ID
	private String SCR_NAME;	//上传人名称
	private String SC_TIME;		//上传时间
	private String LUJING;		//文件保存路径
	private String YUAN_NAME;	//文件原名称
	private String XIAN_NAME;	//文件现名称
	private String LIUCHENG_ID;	//文件对应流程ID
	private String LIUCHENG_NAME;//文件对应流程名称
	private String DIANBIAO_ID;	//文件对应电表ID
	private String DIANBIAO_NAME;//文件对应电表名称
	private String CREATE_USERID;//创建人ID
	private String CREATE_DATE;	//创建时间
	private String UPDATE_USERID;//修改人ID
	private String UPDATE_DATE;	//修改时间
	private String REMARK;		//备注
	
	public WenJian() {
		super();
	}

	public WenJian(String sCR_ID, String sCR_NAME, String sC_TIME,
			String lUJING, String yUAN_NAME, String xIAN_NAME,
			String dIANBIAO_ID, String dIANBIAO_NAME) {
		super();
		SCR_ID = sCR_ID;
		SCR_NAME = sCR_NAME;
		SC_TIME = sC_TIME;
		LUJING = lUJING;
		YUAN_NAME = yUAN_NAME;
		XIAN_NAME = xIAN_NAME;
		DIANBIAO_ID = dIANBIAO_ID;
		DIANBIAO_NAME = dIANBIAO_NAME;
	}

	public WenJian(String sCR_ID, String sCR_NAME, String sC_TIME,
			String lUJING, String yUAN_NAME, String xIAN_NAME,
			String lIUCHENG_ID, String lIUCHENG_NAME, String dIANBIAO_ID,
			String dIANBIAO_NAME) {
		super();
		SCR_ID = sCR_ID;
		SCR_NAME = sCR_NAME;
		SC_TIME = sC_TIME;
		LUJING = lUJING;
		YUAN_NAME = yUAN_NAME;
		XIAN_NAME = xIAN_NAME;
		LIUCHENG_ID = lIUCHENG_ID;
		LIUCHENG_NAME = lIUCHENG_NAME;
		DIANBIAO_ID = dIANBIAO_ID;
		DIANBIAO_NAME = dIANBIAO_NAME;
	}

	public WenJian(String iD, String sCR_ID, String sCR_NAME, String sC_TIME,
			String lUJING, String yUAN_NAME, String xIAN_NAME,
			String lIUCHENG_ID, String lIUCHENG_NAME, String dIANBIAO_ID,
			String dIANBIAO_NAME, String cREATE_USERID, String cREATE_DATE,
			String uPDATE_USERID, String uPDATE_DATE, String rEMARK) {
		super();
		ID = iD;
		SCR_ID = sCR_ID;
		SCR_NAME = sCR_NAME;
		SC_TIME = sC_TIME;
		LUJING = lUJING;
		YUAN_NAME = yUAN_NAME;
		XIAN_NAME = xIAN_NAME;
		LIUCHENG_ID = lIUCHENG_ID;
		LIUCHENG_NAME = lIUCHENG_NAME;
		DIANBIAO_ID = dIANBIAO_ID;
		DIANBIAO_NAME = dIANBIAO_NAME;
		CREATE_USERID = cREATE_USERID;
		CREATE_DATE = cREATE_DATE;
		UPDATE_USERID = uPDATE_USERID;
		UPDATE_DATE = uPDATE_DATE;
		REMARK = rEMARK;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSCR_ID() {
		return SCR_ID;
	}

	public void setSCR_ID(String sCR_ID) {
		SCR_ID = sCR_ID;
	}

	public String getSCR_NAME() {
		return SCR_NAME;
	}

	public void setSCR_NAME(String sCR_NAME) {
		SCR_NAME = sCR_NAME;
	}

	public String getSC_TIME() {
		return SC_TIME;
	}

	public void setSC_TIME(String sC_TIME) {
		SC_TIME = sC_TIME;
	}

	public String getLUJING() {
		return LUJING;
	}

	public void setLUJING(String lUJING) {
		LUJING = lUJING;
	}

	public String getYUAN_NAME() {
		return YUAN_NAME;
	}

	public void setYUAN_NAME(String yUAN_NAME) {
		YUAN_NAME = yUAN_NAME;
	}

	public String getXIAN_NAME() {
		return XIAN_NAME;
	}

	public void setXIAN_NAME(String xIAN_NAME) {
		XIAN_NAME = xIAN_NAME;
	}

	public String getLIUCHENG_ID() {
		return LIUCHENG_ID;
	}

	public void setLIUCHENG_ID(String lIUCHENG_ID) {
		LIUCHENG_ID = lIUCHENG_ID;
	}

	public String getLIUCHENG_NAME() {
		return LIUCHENG_NAME;
	}

	public void setLIUCHENG_NAME(String lIUCHENG_NAME) {
		LIUCHENG_NAME = lIUCHENG_NAME;
	}

	public String getDIANBIAO_ID() {
		return DIANBIAO_ID;
	}

	public void setDIANBIAO_ID(String dIANBIAO_ID) {
		DIANBIAO_ID = dIANBIAO_ID;
	}

	public String getDIANBIAO_NAME() {
		return DIANBIAO_NAME;
	}

	public void setDIANBIAO_NAME(String dIANBIAO_NAME) {
		DIANBIAO_NAME = dIANBIAO_NAME;
	}

	public String getCREATE_USERID() {
		return CREATE_USERID;
	}

	public void setCREATE_USERID(String cREATE_USERID) {
		CREATE_USERID = cREATE_USERID;
	}

	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public String getUPDATE_USERID() {
		return UPDATE_USERID;
	}

	public void setUPDATE_USERID(String uPDATE_USERID) {
		UPDATE_USERID = uPDATE_USERID;
	}

	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(String uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
}
