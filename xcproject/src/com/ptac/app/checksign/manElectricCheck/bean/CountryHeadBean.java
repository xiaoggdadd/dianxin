package com.ptac.app.checksign.manElectricCheck.bean;

public class CountryHeadBean {
	
	private String biaozhi = "";//用来区分是"电费"还是"预付费"(js用)
	private String sort = "";//用来区分是"电费"还是"预付费"(显示用)
	private String flag = "";//是否是异常高额，1:是，0：否
	private String description = "";//异常高额描述信息
	private String manpassmeno = "";//人工审核通过原因

	//站点表 ZHANDIAN
	private String zdid = "";//站点id
	private String shi = "";//市
	private String xian = "";//区县	
	private String xiaoqu = "";//乡镇	
	private String jzname = "";//站点名称
	private String stationtype = "";//站点类型	
	private String property = "";//站点属性
	private String qyzt = "";//站点启用状态
	//电表表 DIANBIAO
	private String dbid = "";//电表id
	private String dbname = "";//电表名称
	private String dbqyzt = "";//电表启用状态

	
	//电量表 AMMETERDEGREES
	private String blhdl = "";//实际用电量(倍率耗电量)
	private String edhdl = "";//额定耗电量(市标)
	private String qsdbdl = "";//省定标电量(省标)
	private String lastdegree = "";//起始电表数
	private String thisdegree = "";//结束电表数
	private String lastdatetime = "";//上次抄表时间
	private String thisdatetime = "";//本次抄表时间
	private String floatdegree = "";//电量调整
	private String ammemo = "";//电量调整备注
	private String lastelecfees;//上次电费
	private String lastelecdegree;//上次电量
	private String lastunitprice;//上次单价
	//2014-6-27
	private String dedhdl;//额定耗电量超标比例
	private String csdb;//全省定标电量超标比例
	
	//电费表 ELECTRICFEES
	private String uuid = "";
	private String accountmonth = "";//报账月份
	private String actualpay = "";//实际电费
	private String unitprice = "";//单价
	private String countryheadstatus = "";//区县主任审核标志
	private String floatpay = "";//电费调整
	private String efmemo = "";//电费调整备注
	
	private String dfzflx = "";//电费支付类型
	private String gdfs = "";//供电方式
	private String dbydl = "";//电表用电量
	private String bzydl = "";//报账用电量
	private String jszq = "";//结算周期
	private String csdbdfe = "";//超省定标电费额	
	private String csdbdfbz = "";//超省定标电费比值	
	private String manualauditstatus = "";//人工审核状态	
	private String cityaudit = "";//市级审核状态	
	private String manualauditstatus1 = "";//财务审核状态	
	private String beilv = "";//倍率	
	private String pjsj = "";//票据时间	
	private String pjbh = "";//票据编号	
	private String pjlx = "";//票据类型	
	private String jfsj = "";//交费时间
	private String actualdegree = "";//实际用电量
	private String autoauditstatus = "";//自动审核状态
	
	private String gdtx;//工单提醒
	private String floatdegreeandbl;//电量调整*倍率
	private String dlid;//电量ID
	private String electricfee_id;//电费ID
	private String tbtzsq;//特别调整申请
	private String yddf;//用电电费
	private String linelosstype;//线损类型
	private String linelossvalue;//线损值
	private String changevalue;//变损值
	private String actuallinelossvalue;//实际线损值
	
	private String lastyue;//上期余额
	
	private String lastfloatdegreeandbl;//上期电量调整*倍率
	private String lastactualdegree;//上期电表用电量
	
	private String csdbdfjdz;//超省定标电费绝对值
	private String cshidbdfjdz;//超市定标电费绝对值
	private String cshibdfbl;//超市标电费比例
	private String csbdld;//超省标电量度
	private String cshibdld;//超市标电量度
	private String glbrjl;//管理表日均量
	private String bzrj;//报账日均电量
	private String lineandchangeandbl;//线变损电量
	
	private String bzz;//标准值
	
	
	public String getLastyue() {
		return lastyue;
	}
	public void setLastyue(String lastyue) {
		this.lastyue = lastyue;
	}
	public String getYddf() {
		return yddf;
	}
	public void setYddf(String yddf) {
		this.yddf = yddf;
	}
	public String getTbtzsq() {
		return tbtzsq;
	}
	public void setTbtzsq(String tbtzsq) {
		this.tbtzsq = tbtzsq;
	}
	public String getDlid() {
		return dlid;
	}
	public String getElectricfee_id() {
		return electricfee_id;
	}
	public void setElectricfee_id(String electricfeeId) {
		electricfee_id = electricfeeId;
	}
	public void setDlid(String dlid) {
		this.dlid = dlid;
	}
	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	private String zflxflag;//支付类型标志
	
	
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
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
	public String getBzydl() {
		return bzydl;
	}
	public void setBzydl(String bzydl) {
		this.bzydl = bzydl;
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
	public String getManualauditstatus1() {
		return manualauditstatus1;
	}
	public void setManualauditstatus1(String manualauditstatus1) {
		this.manualauditstatus1 = manualauditstatus1;
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
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
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
	public String getAmmemo() {
		return ammemo;
	}
	public void setAmmemo(String ammemo) {
		this.ammemo = ammemo;
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
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getEfmemo() {
		return efmemo;
	}
	public void setEfmemo(String efmemo) {
		this.efmemo = efmemo;
	}
	public String getSsdq() {
		return ssdq;
	}
	public void setSsdq(String ssdq) {
		this.ssdq = ssdq;
	}
	private String ssdq = "";//所属地区
	private String cshibl = "";//超市标比例
	private String cshengbl = "";//超省标比例

	
	
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
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
	public String getQyzt() {
		return qyzt;
	}
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	public String getDbqyzt() {
		return dbqyzt;
	}
	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}

	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}

	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getCountryheadstatus() {
		return countryheadstatus;
	}
	public void setCountryheadstatus(String countryheadstatus) {
		this.countryheadstatus = countryheadstatus;
	}
	public String getCshibl() {
		return cshibl;
	}
	public void setCshibl(String cshibl) {
		this.cshibl = cshibl;
	}
	public String getCshengbl() {
		return cshengbl;
	}
	public void setCshengbl(String cshengbl) {
		this.cshengbl = cshengbl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDbname() {
		return dbname;
	}
	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}
	public String getBiaozhi() {
		return biaozhi;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setZflxflag(String zflxflag) {
		this.zflxflag = zflxflag;
	}
	public String getZflxflag() {
		return zflxflag;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSort() {
		return sort;
	}
	public void setManpassmeno(String manpassmeno) {
		this.manpassmeno = manpassmeno;
	}
	public String getManpassmeno() {
		return manpassmeno;
	}
	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
	}
	public String getAutoauditstatus() {
		return autoauditstatus;
	}
	public void setGdtx(String gdtx) {
		this.gdtx = gdtx;
	}
	public String getGdtx() {
		return gdtx;
	}
	public String getFloatdegreeandbl() {
		return floatdegreeandbl;
	}
	public void setFloatdegreeandbl(String floatdegreeandbl) {
		this.floatdegreeandbl = floatdegreeandbl;
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
	public String getDedhdl() {
		return dedhdl;
	}
	public void setDedhdl(String dedhdl) {
		this.dedhdl = dedhdl;
	}
	public String getCsdb() {
		return csdb;
	}
	public void setCsdb(String csdb) {
		this.csdb = csdb;
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
	public String getCshibdfbl() {
		return cshibdfbl;
	}
	public void setCshibdfbl(String cshibdfbl) {
		this.cshibdfbl = cshibdfbl;
	}
	public String getCsbdld() {
		return csbdld;
	}
	public void setCsbdld(String csbdld) {
		this.csbdld = csbdld;
	}
	public String getCshibdld() {
		return cshibdld;
	}
	public void setCshibdld(String cshibdld) {
		this.cshibdld = cshibdld;
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
	public String getChangevalue() {
		return changevalue;
	}
	public void setChangevalue(String changevalue) {
		this.changevalue = changevalue;
	}
	public String getActuallinelossvalue() {
		return actuallinelossvalue;
	}
	public void setActuallinelossvalue(String actuallinelossvalue) {
		this.actuallinelossvalue = actuallinelossvalue;
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
	public String getBzz() {
		return bzz;
	}
	public void setBzz(String bzz) {
		this.bzz = bzz;
	}
	
	
}
