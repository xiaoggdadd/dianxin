package com.ptac.app.datastatistics.electricitybills.bean;

/**
 * @author 李靖
 * @see 电费缴纳明细Bean
 */
public class ElecBillsBean {

	//站点表 ZHANDIAN
	private int id;//站点ID
	private String edhdl;//额定耗电量
	private String stationtype;//站点类型	
	private String bgsign;//是否标杆
	private String shi;//市
	private String xian;//区县	
	private String xiaoqu;//乡镇	
	private String jzname;//站点名称
	private String bieming;//站点别名
	private double zlfh;//直流负荷
	private double jlfh;//交流负荷
	private String gdfs;//供电方式
	private String qyzt;//站点启用状态
	private String zdsx;//站点属性
	private double qsdbdl;//全省定标电量
	//电表表 DIANBIAO
	private String dbid;//电表ID
	private String dbzbdyhh;//自报电用户号	
	private String beilv;//倍率	
	private String dbname;//电表名称	
	private String dfzflx;//电费支付类型
	private String dbqyzt;//电表启用状态
	//电量表 AMMETERDEGREES
	private String lastdatetime;//上次抄表时间	
	private String thisdatetime;//本次抄表时间
	private String floatdegree;//用电量调整	
	private String memo;//(电量)备注	
	private String startmonth;//起始月份		
	private String endmonth;//结束月份
	private String autoauditstatus;//自动审核状态
	private String lastdegree;//起始电表数	
	private String thisdegree;//结束电表数
	private String blhdl;//折算后用电量(倍率耗电量)	
	private String actualdegree;//实际用电量
	
	private String floatdegreeandbl;//电量调整*倍率
	private String lastfloatdegreeandbl;//上期电量调整*倍率
	private String lastactualdegree;//上期电表用电量
	private String glbrjl;//管理表日均量
	private String bzrj;//报账日均电量
	private String lineandchangeandbl;//线变损电量

	//电费表 ELECTRICFEES
	private String electricfeeid;//电费id
	private String cityauditpensonnel;//市级审核员	
	private String cityaudittime;//市级审核时间
	private String entrypensonnel;//录入人员	
	private String entrytime;//录入时间
	private String notetypeid;//票据类型	
	private String unitprice;//本次单价
	private String floatpay;//电费调整
	private String actualpay;//报账电费//实际电费
	private String liuchenghao;//流程号	
	private String financialoperator;//财务审核员(财务操作员)	
	private String accountmonth;//报账月份
	private String memo1;//(电费)备注
	private String noteno;//票据编号	
	private String manualauditstatus;//人工审核状态
	private String manualauditdatetime;//人工审核时间	
	private String manualauditname;//人工审核员	
	private String financialstatus;//财务审核状态
	private String financialdatetime;//财务审核时间	
	private String cityaudit;//市级审核状态	
	private String kjyf;//财务月份(会计月份)
	private String sc;//生产电费
	private String bg;//办公电费
	private String yy;//营业电费
	private String xxhzc;//信息化支撑
	private String jstz;//建设投资
	private String dddf;//代垫电费
	
	private String countyauditstatus;//区县主任审核状态
	private String countyauditname;//区县主任审核人
	private String countyaudittime;//区县主任审核时间
	private String cityzrauditstatus;//市主任审核状态
	private String cityzrauditname;//市主任审核人
	private String cityzraudittime;//市主任审核时间
	//计算获得
	private String cycle;//结算周期	
	private String rated;//额定电费	
	private String subtract;//与额定电费差	
	private String ratio;//与额定电费比值		
	private String estimate;//直流负荷估算电量	
	private double totalelec;//电量合计	
	private double totalmoney;//电费合计
	private double sdbdf;//省定标电费
	private double csdbdfe;//超省定标电费额
	private double dbydl;//电表用电量
	
	public String getCountyauditstatus() {
		return countyauditstatus;
	}
	public void setCountyauditstatus(String countyauditstatus) {
		this.countyauditstatus = countyauditstatus;
	}
	public String getCountyauditname() {
		return countyauditname;
	}
	public void setCountyauditname(String countyauditname) {
		this.countyauditname = countyauditname;
	}
	public String getCountyaudittime() {
		return countyaudittime;
	}
	public void setCountyaudittime(String countyaudittime) {
		this.countyaudittime = countyaudittime;
	}
	public String getCityzrauditstatus() {
		return cityzrauditstatus;
	}
	public void setCityzrauditstatus(String cityzrauditstatus) {
		this.cityzrauditstatus = cityzrauditstatus;
	}
	public String getCityzrauditname() {
		return cityzrauditname;
	}
	public void setCityzrauditname(String cityzrauditname) {
		this.cityzrauditname = cityzrauditname;
	}
	public String getCityzraudittime() {
		return cityzraudittime;
	}
	public void setCityzraudittime(String cityzraudittime) {
		this.cityzraudittime = cityzraudittime;
	}
	
	public double getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(double qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public double getSdbdf() {
		return sdbdf;
	}
	public void setSdbdf(double sdbdf) {
		this.sdbdf = sdbdf;
	}
	public double getCsdbdfe() {
		return csdbdfe;
	}
	public void setCsdbdfe(double csdbdfe) {
		this.csdbdfe = csdbdfe;
	}
	public double getTotalelec() {
		return totalelec;
	}
	public void setTotalelec(double totalelec) {
		this.totalelec = totalelec;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEdhdl() {
		return edhdl;
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
	public String getBgsign() {
		return bgsign;
	}
	public void setBgsign(String bgsign) {
		this.bgsign = bgsign;
	}
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
	public String getBieming() {
		return bieming;
	}
	public void setBieming(String bieming) {
		this.bieming = bieming;
	}
	public double getZlfh() {
		return zlfh;
	}
	public void setZlfh(double zlfh) {
		this.zlfh = zlfh;
	}
	public double getJlfh() {
		return jlfh;
	}
	public void setJlfh(double jlfh) {
		this.jlfh = jlfh;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getQyzt() {
		return qyzt;
	}
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbzbdyhh() {
		return dbzbdyhh;
	}
	public void setDbzbdyhh(String dbzbdyhh) {
		this.dbzbdyhh = dbzbdyhh;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getDbqyzt() {
		return dbqyzt;
	}
	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
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
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getAutoauditstatus() {
		return autoauditstatus;
	}
	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
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
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getCityauditpensonnel() {
		return cityauditpensonnel;
	}
	public void setCityauditpensonnel(String cityauditpensonnel) {
		this.cityauditpensonnel = cityauditpensonnel;
	}
	public String getCityaudittime() {
		return cityaudittime;
	}
	public void setCityaudittime(String cityaudittime) {
		this.cityaudittime = cityaudittime;
	}
	public String getEntrypensonnel() {
		return entrypensonnel;
	}
	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}
	public String getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}

	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getLiuchenghao() {
		return liuchenghao;
	}
	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}
	public String getFinancialoperator() {
		return financialoperator;
	}
	public void setFinancialoperator(String financialoperator) {
		this.financialoperator = financialoperator;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getNoteno() {
		return noteno;
	}
	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}
	public String getManualauditstatus() {
		return manualauditstatus;
	}
	public void setManualauditstatus(String manualauditstatus) {
		this.manualauditstatus = manualauditstatus;
	}
	public String getManualauditdatetime() {
		return manualauditdatetime;
	}
	public void setManualauditdatetime(String manualauditdatetime) {
		this.manualauditdatetime = manualauditdatetime;
	}
	public String getManualauditname() {
		return manualauditname;
	}
	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}
	public String getFinancialdatetime() {
		return financialdatetime;
	}
	public void setFinancialdatetime(String financialdatetime) {
		this.financialdatetime = financialdatetime;
	}
	public String getCityaudit() {
		return cityaudit;
	}
	public void setCityaudit(String cityaudit) {
		this.cityaudit = cityaudit;
	}
	public String getKjyf() {
		return kjyf;
	}
	public void setKjyf(String kjyf) {
		this.kjyf = kjyf;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public String getSubtract() {
		return subtract;
	}
	public void setSubtract(String subtract) {
		this.subtract = subtract;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	public double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(double totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	public void setFinancialstatus(String financialstatus) {
		this.financialstatus = financialstatus;
	}
	public String getFinancialstatus() {
		return financialstatus;
	}
	public void setElectricfeeid(String electricfeeid) {
		this.electricfeeid = electricfeeid;
	}
	public String getElectricfeeid() {
		return electricfeeid;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getXxhzc() {
		return xxhzc;
	}
	public void setXxhzc(String xxhzc) {
		this.xxhzc = xxhzc;
	}
	public String getJstz() {
		return jstz;
	}
	public void setJstz(String jstz) {
		this.jstz = jstz;
	}
	public String getDddf() {
		return dddf;
	}
	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	public void setDbydl(double dbydl) {
		this.dbydl = dbydl;
	}
	public double getDbydl() {
		return dbydl;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	public String getActualdegree() {
		return actualdegree;
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
