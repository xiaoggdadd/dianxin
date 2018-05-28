package com.ptac.app.checksign.citymanagercheck.bean;

public class CityMngCheckBean {

	private String zdid;//站点ID
	private String dbid;//电表ID
	private String electricfeeid;//电费id
	private String uuid;//电量里面的uuid
	private String zdname;//站点名称
	private String accountmonth;//报账月份
	private String blhdl;//实际用电量
	private String actualpay;//实际电费
	private String unitprice;//单价
	private String csbl;//超省标比例
	private String csbbl;//超市标比例
	private String qsdbdl;//省定标(省定标：全省定标电量)
	private String edhdl;//市定标(市定标：额定耗电量    )
	private String stationtype;//站点类型
	private String property;//站点属性
	private String cityzrauditstatus;//市级主任审核状态
	private String dbname;//电表名称
	private String lastdegree;//起始电表数
	private String thisdegree;//结束电表数
	private String lastdatetime;//上次抄表时间	
	private String thisdatetime;//本次抄表时间
	private String floatdegree;//电量调整	
	private String memo;//电量调整备注
	private String floatpay;//电费调整
	private String memo1;//电费调整备注
	private String szdq;//所属地区
	private String dfzflx;//电费支付类型
	private String dfbzw;//电费标志位（数据库中没有）1表示:月结,预付费2; 2表示:合同、插卡、预付费1
	
	/**
	 * 查询用，新字段
	 * @author rock
	 */
	private String quxian;//区县
	private String gdfs;//供电方式
	private String jszq;//结算周期
	private String csdbdfe;//超省定标电费额
	private String csdbdfbz;//超省定标电费比值
	private String rgshzt;//人工审核状态
	private String sjshzt;//市级审核状态
	private String cwshzt;//财务审核状态
	private String beilv;//倍率
	private String pjsj;//票据时间
	private String pjbh;//票据编号
	private String pjlx;//票据类型
	private String jfsj;//交费时间
	private String dbydl;//电表用电量
	private String bzydl;//报账用电量
	
	private String lrzt;//录入状态
	
	private String autoauditstatus;//自动审核状态(0未通过  1通过）
	private String manpassmemo;//人工审核通过原因
	
	private String lastelecfees;//上次电费
	private String lastelecdegree;//上次电量
	private String lastunitprice;//上次单价
	private String gdtx;//工单提醒
	
	private String lastyue;//上期余额
	
	private String floatdegreeandbl;//上期电量调整*倍率
	private String lastactualdegree;//上期电表用电量
	private String lastfloatdegreeandbl;//上期电量调整*倍率
	private String glbrjl;//管理表日均量
	private String bzrj;//报账日均电量
	private String lineandchangeandbl;//线变损电量
	
	public String getLastyue() {
		return lastyue;
	}

	public void setLastyue(String lastyue) {
		this.lastyue = lastyue;
	}

	public String getLastelecfees() {
		return lastelecfees;
	}

	public void setLastelecfees(String lastelecfees) {
		this.lastelecfees = lastelecfees;
	}

	public String getLastelecdegree() {
		return lastelecdegree;
	}

	public void setLastelecdegree(String lastelecdegree) {
		this.lastelecdegree = lastelecdegree;
	}

	public String getLastunitprice() {
		return lastunitprice;
	}

	public void setLastunitprice(String lastunitprice) {
		this.lastunitprice = lastunitprice;
	}

	public String getGdtx() {
		return gdtx;
	}

	public void setGdtx(String gdtx) {
		this.gdtx = gdtx;
	}

	public String getAutoauditstatus() {
		return autoauditstatus;
	}

	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
	}

	public String getManpassmemo() {
		return manpassmemo;
	}

	public void setManpassmemo(String manpassmemo) {
		this.manpassmemo = manpassmemo;
	}

	public String getLrzt() {
		return lrzt;
	}

	public void setLrzt(String lrzt) {
		this.lrzt = lrzt;
	}

	//空的构造方法
	public CityMngCheckBean(){
		
	}
	
	public String getElectricfeeid() {
		return electricfeeid;
	}

	public void setElectricfeeid(String electricfeeid) {
		this.electricfeeid = electricfeeid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getCsbl() {
		return csbl;
	}
	public void setCsbl(String csbl) {
		this.csbl = csbl;
	}
	public String getCsbbl() {
		return csbbl;
	}
	public void setCsbbl(String csbbl) {
		this.csbbl = csbbl;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}

	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}

	public String getEdhdl() {
		return edhdl;
	}
	public String getCityzrauditstatus() {
		return cityzrauditstatus;
	}

	public void setCityzrauditstatus(String cityzrauditstatus) {
		this.cityzrauditstatus = cityzrauditstatus;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}

	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getLastdatetime() {
		return lastdatetime;
	}
	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}
	public String getThisdatetime() {
		return thisdatetime;
	}
	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getSzdq() {
		return szdq;
	}
	public void setSzdq(String szdq) {
		this.szdq = szdq;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}

	public String getDfbzw() {
		return dfbzw;
	}

	public void setDfbzw(String dfbzw) {
		this.dfbzw = dfbzw;
	}

	public String getQuxian() {
		return quxian;
	}

	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}

	public String getGdfs() {
		return gdfs;
	}

	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

	public String getJszq() {
		return jszq;
	}

	public void setJszq(String jszq) {
		this.jszq = jszq;
	}

	public String getCsdbdfe() {
		return csdbdfe;
	}

	public void setCsdbdfe(String csdbdfe) {
		this.csdbdfe = csdbdfe;
	}

	public String getCsdbdfbz() {
		return csdbdfbz;
	}

	public void setCsdbdfbz(String csdbdfbz) {
		this.csdbdfbz = csdbdfbz;
	}

	public String getRgshzt() {
		return rgshzt;
	}

	public void setRgshzt(String rgshzt) {
		this.rgshzt = rgshzt;
	}

	public String getSjshzt() {
		return sjshzt;
	}

	public void setSjshzt(String sjshzt) {
		this.sjshzt = sjshzt;
	}

	public String getCwshzt() {
		return cwshzt;
	}

	public void setCwshzt(String cwshzt) {
		this.cwshzt = cwshzt;
	}

	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}

	public String getPjsj() {
		return pjsj;
	}

	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}

	public String getPjbh() {
		return pjbh;
	}

	public void setPjbh(String pjbh) {
		this.pjbh = pjbh;
	}

	public String getPjlx() {
		return pjlx;
	}

	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}

	public String getJfsj() {
		return jfsj;
	}

	public void setJfsj(String jfsj) {
		this.jfsj = jfsj;
	}

	public String getDbydl() {
		return dbydl;
	}

	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}

	public String getBzydl() {
		return bzydl;
	}

	public void setBzydl(String bzydl) {
		this.bzydl = bzydl;
	}

	public String getFloatdegreeandbl() {
		return floatdegreeandbl;
	}

	public void setFloatdegreeandbl(String floatdegreeandbl) {
		this.floatdegreeandbl = floatdegreeandbl;
	}

	public String getLastactualdegree() {
		return lastactualdegree;
	}

	public void setLastactualdegree(String lastactualdegree) {
		this.lastactualdegree = lastactualdegree;
	}

	public String getLastfloatdegreeandbl() {
		return lastfloatdegreeandbl;
	}

	public void setLastfloatdegreeandbl(String lastfloatdegreeandbl) {
		this.lastfloatdegreeandbl = lastfloatdegreeandbl;
	}

	public String getGlbrjl() {
		return glbrjl;
	}

	public void setGlbrjl(String glbrjl) {
		this.glbrjl = glbrjl;
	}

	public String getBzrj() {
		return bzrj;
	}

	public void setBzrj(String bzrj) {
		this.bzrj = bzrj;
	}

	public String getLineandchangeandbl() {
		return lineandchangeandbl;
	}

	public void setLineandchangeandbl(String lineandchangeandbl) {
		this.lineandchangeandbl = lineandchangeandbl;
	}
	
	
	
}
