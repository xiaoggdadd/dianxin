package com.ptac.app.checksign.cityelectricfeecheck.bean;

/**
 * @author WangYiXiao 2014-1-23
 * @see 市级电费审核所需字段
 */
public class CityElectricFeeCheck{
	//站点表 ZHANDIAN
	private String zdid;//站点id
	private String zdcode;//站点code
	private String jzname;//站点名称
	private String property;//站点属性
	private String stationtype;//站点类型
	private String szdq;//所在地区(区县+乡镇(小区)
	private String edhdl;//额定耗电
	private String zlfh;//直流负荷
	private String jlfh;//交流负荷
	private String qsdbdl;//全省定标电量
	//电表表 DIANBIAO
	private String dbid;//电表ID
	private String dfzflx;//电费支付类型
	private String linelosstype;//线损类型
	private String linelossvalue;//线损值
	private String beilv;//倍率
	//电量表 AMMETERDEGREES 对应的视图 CITYCHECK_DDV
	private String lastdatetime;//上次抄表时间
	private String thisdatetime;//本次抄表时间
	private String blhdl;//倍率耗电量(实际用电量)
	private String dedhdl;//耗电量的标志(电量超标比例)
	//电费表 ELECTRICFEES 对应的视图 CITYCHECK_DDF
	//预付费表：prepayment f 对应的视图 citycheck_yff
	private String unitprice;//单价ddf
	private String accountmonth;//报账月份ddf,yff
	private String notetypeid;//票据类型ddf,yff
	private String ey_id;//电费id(electricfee_id number)ddf 预付费id(id number)yff
	private String uuid;//电费uuid(dfuuid) ddf,预付费uuid yff
	private String actualpay;//实际电费金额(actualpay)ddf,(money)yff
	private String autoauditstatus;//自动审核状态(0 未通过 1 通过)ddf
	private String noteno;//票据编号ddf,yff
	private String manualauditstatus;//人工审核状态(0未通过 -1财务未通过 1 人工通过 2 财务通过 -2 人工不通过)ddf
	private String cityaudit;//市级审核( 0未通过，1通过  -2不通过)ddf,yff
	private String pjje;//票据金额(2位小数)ddf,yff
	private String manpassmemo;//人工审核通过原因
	private String lastelecfees;//上期电费
	private String lastelecdegree;//上期电量
	private String lastunitprice;//上期单价
	//其他
	private String dfbzw;//电费标志位（数据库中无）1:月结，预付费2 ; 2:合同、插卡、预付费1
	private String jszq;//结算周期   周期 =（本次抄表时间-上次抄表时间）/1000/60/60/24+1;
	private String eddf;//额定电费，公式：额定耗电量*本次单价*周期；
	private String qsdbdlcbbl;//全省定标电量超标比例：如果全省定标不为空公式为：（实际用电量-（全省定标电量*周期））/（全省定标电量*周期）*100
	private String cityzrzt;//市级主任审核状态
	private String cityzrname;//市级主任审核人
	private String cityzrtime;//市级主任审核时间
	private String quxian;//区县
	private String gdtx;//工单提醒列
	
	/**
	 * 新加字段
	 * @author rock
	 * @return
	 */
	private String dbname;//电表名称
	private String gdfs;//供电方式
	private String dbydl;//电表用电量
	private String sccbsj;//上次抄表时间
	private String bccbsj;//本次抄表时间
	private String bzydl;//报账用电量
	private String ydltz;//用电量调整
	private String dlmemo;//电量调整备注
	private String danjia;//单价
	private String dftz;//电费调整
	private String dfmemo;//电费调整备注
	private String csdbdfe;//超省定标电费额
	private String csdbdfbz;//超省定标电费比值
	private String pjsj;//票据时间
	private String jfsj;//缴费时间
	
	private String lrbz;//录入标志（电费单录入还是预付费录入）
	
	private String tbtzsq;//特别调整申请
	private String dlid;//电量ID
	
	private String lastyue;//上期余额
	
	private String lastfloatdegreeandbl;//上期电量调整*倍率
	private String lastactualdegree;//上期电表用电量
	private String floatdegreeandbl;//电量调整*倍率
	
	private String cshengbdld;//超省标电量度
	private String cshibdld;//超市标电量度
	private String csdbdfjdz;//超省标电费绝对值
	private String cshidbdfjdz;//超市标电费绝对值
	private String cshidbdfbl;//超市标电费比例
	private String glbrjl;//管理表日均量
	private String bzrj;//报账日均电量
	private String lineandchangeandbl;//线变损电量
	
	public String getLastyue() {
		return lastyue;
	}
	public void setLastyue(String lastyue) {
		this.lastyue = lastyue;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getNoteno() {
		return noteno;
	}
	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}
	public String getCityzrzt() {
		return cityzrzt;
	}
	public void setCityzrzt(String cityzrzt) {
		this.cityzrzt = cityzrzt;
	}
	public String getCityzrname() {
		return cityzrname;
	}
	public void setCityzrname(String cityzrname) {
		this.cityzrname = cityzrname;
	}
	public String getCityzrtime() {
		return cityzrtime;
	}
	public void setCityzrtime(String cityzrtime) {
		this.cityzrtime = cityzrtime;
	}
	public String getPjsj() {
		return pjsj;
	}
	public void setPjsj(String pjsj) {
		this.pjsj = pjsj;
	}
	public String getJfsj() {
		return jfsj;
	}
	public void setJfsj(String jfsj) {
		this.jfsj = jfsj;
	}
	public String getLrbz() {
		return lrbz;
	}
	public void setLrbz(String lrbz) {
		this.lrbz = lrbz;
	}
	public String getDlid() {
		return dlid;
	}
	public void setDlid(String dlid) {
		this.dlid = dlid;
	}
	public String getTbtzsq() {
		return tbtzsq;
	}
	public void setTbtzsq(String tbtzsq) {
		this.tbtzsq = tbtzsq;
	}
	
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getZdcode() {
		return zdcode;
	}
	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getSzdq() {
		return szdq;
	}
	public void setSzdq(String szdq) {
		this.szdq = szdq;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getZlfh() {
		return zlfh;
	}
	public void setZlfh(String zlfh) {
		this.zlfh = zlfh;
	}
	public String getJlfh() {
		return jlfh;
	}
	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getLinelosstype() {
		return linelosstype;
	}
	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}
	public String getLinelossvalue() {
		return linelossvalue;
	}
	public void setLinelossvalue(String linelossvalue) {
		this.linelossvalue = linelossvalue;
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
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getDedhdl() {
		return dedhdl;
	}
	public void setDedhdl(String dedhdl) {
		this.dedhdl = dedhdl;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getEy_id() {
		return ey_id;
	}
	public void setEy_id(String eyId) {
		ey_id = eyId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getAutoauditstatus() {
		return autoauditstatus;
	}
	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
	}
	public String getManualauditstatus() {
		return manualauditstatus;
	}
	public void setManualauditstatus(String manualauditstatus) {
		this.manualauditstatus = manualauditstatus;
	}
	public String getCityaudit() {
		return cityaudit;
	}
	public void setCityaudit(String cityaudit) {
		this.cityaudit = cityaudit;
	}
	public String getPjje() {
		return pjje;
	}
	public void setPjje(String pjje) {
		this.pjje = pjje;
	}
	public String getDfbzw() {
		return dfbzw;
	}
	public void setDfbzw(String dfbzw) {
		this.dfbzw = dfbzw;
	}
	public String getJszq() {
		return jszq;
	}
	public void setJszq(String jszq) {
		this.jszq = jszq;
	}
	public String getEddf() {
		return eddf;
	}
	public void setEddf(String eddf) {
		this.eddf = eddf;
	}
	public String getQsdbdlcbbl() {
		return qsdbdlcbbl;
	}
	public void setQsdbdlcbbl(String qsdbdlcbbl) {
		this.qsdbdlcbbl = qsdbdlcbbl;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getDbydl() {
		return dbydl;
	}
	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}
	public String getSccbsj() {
		return sccbsj;
	}
	public void setSccbsj(String sccbsj) {
		this.sccbsj = sccbsj;
	}
	public String getBccbsj() {
		return bccbsj;
	}
	public void setBccbsj(String bccbsj) {
		this.bccbsj = bccbsj;
	}
	public String getBzydl() {
		return bzydl;
	}
	public void setBzydl(String bzydl) {
		this.bzydl = bzydl;
	}
	public String getYdltz() {
		return ydltz;
	}
	public void setYdltz(String ydltz) {
		this.ydltz = ydltz;
	}
	public String getDlmemo() {
		return dlmemo;
	}
	public void setDlmemo(String dlmemo) {
		this.dlmemo = dlmemo;
	}
	public String getDanjia() {
		return danjia;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public String getDftz() {
		return dftz;
	}
	public void setDftz(String dftz) {
		this.dftz = dftz;
	}
	public String getDfmemo() {
		return dfmemo;
	}
	public void setDfmemo(String dfmemo) {
		this.dfmemo = dfmemo;
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
	public String getQuxian() {
		return quxian;
	}
	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}
	public String getManpassmemo() {
		return manpassmemo;
	}
	public void setManpassmemo(String manpassmemo) {
		this.manpassmemo = manpassmemo;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
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
	public String getLastfloatdegreeandbl() {
		return lastfloatdegreeandbl;
	}
	public void setLastfloatdegreeandbl(String lastfloatdegreeandbl) {
		this.lastfloatdegreeandbl = lastfloatdegreeandbl;
	}
	public String getLastactualdegree() {
		return lastactualdegree;
	}
	public void setLastactualdegree(String lastactualdegree) {
		this.lastactualdegree = lastactualdegree;
	}
	public String getFloatdegreeandbl() {
		return floatdegreeandbl;
	}
	public void setFloatdegreeandbl(String floatdegreeandbl) {
		this.floatdegreeandbl = floatdegreeandbl;
	}
	public String getCshengbdld() {
		return cshengbdld;
	}
	public void setCshengbdld(String cshengbdld) {
		this.cshengbdld = cshengbdld;
	}
	public String getCshibdld() {
		return cshibdld;
	}
	public void setCshibdld(String cshibdld) {
		this.cshibdld = cshibdld;
	}
	public String getCsdbdfjdz() {
		return csdbdfjdz;
	}
	public void setCsdbdfjdz(String csdbdfjdz) {
		this.csdbdfjdz = csdbdfjdz;
	}
	public String getCshidbdfjdz() {
		return cshidbdfjdz;
	}
	public void setCshidbdfjdz(String cshidbdfjdz) {
		this.cshidbdfjdz = cshidbdfjdz;
	}
	public String getCshidbdfbl() {
		return cshidbdfbl;
	}
	public void setCshidbdfbl(String cshidbdfbl) {
		this.cshidbdfbl = cshidbdfbl;
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
