package com.ptac.app.datastatistics.electricitybills.bean;

/**
 * @author �
 * @see ��ѽ�����ϸBean
 */
public class ElecBillsBean {

	//վ��� ZHANDIAN
	private int id;//վ��ID
	private String edhdl;//��ĵ���
	private String stationtype;//վ������	
	private String bgsign;//�Ƿ���
	private String shi;//��
	private String xian;//����	
	private String xiaoqu;//����	
	private String jzname;//վ������
	private String bieming;//վ�����
	private double zlfh;//ֱ������
	private double jlfh;//��������
	private String gdfs;//���緽ʽ
	private String qyzt;//վ������״̬
	private String zdsx;//վ������
	private double qsdbdl;//ȫʡ�������
	//���� DIANBIAO
	private String dbid;//���ID
	private String dbzbdyhh;//�Ա����û���	
	private String beilv;//����	
	private String dbname;//�������	
	private String dfzflx;//���֧������
	private String dbqyzt;//�������״̬
	//������ AMMETERDEGREES
	private String lastdatetime;//�ϴγ���ʱ��	
	private String thisdatetime;//���γ���ʱ��
	private String floatdegree;//�õ�������	
	private String memo;//(����)��ע	
	private String startmonth;//��ʼ�·�		
	private String endmonth;//�����·�
	private String autoauditstatus;//�Զ����״̬
	private String lastdegree;//��ʼ�����	
	private String thisdegree;//���������
	private String blhdl;//������õ���(���ʺĵ���)	
	private String actualdegree;//ʵ���õ���
	
	private String floatdegreeandbl;//��������*����
	private String lastfloatdegreeandbl;//���ڵ�������*����
	private String lastactualdegree;//���ڵ���õ���
	private String glbrjl;//������վ���
	private String bzrj;//�����վ�����
	private String lineandchangeandbl;//�߱������

	//��ѱ� ELECTRICFEES
	private String electricfeeid;//���id
	private String cityauditpensonnel;//�м����Ա	
	private String cityaudittime;//�м����ʱ��
	private String entrypensonnel;//¼����Ա	
	private String entrytime;//¼��ʱ��
	private String notetypeid;//Ʊ������	
	private String unitprice;//���ε���
	private String floatpay;//��ѵ���
	private String actualpay;//���˵��//ʵ�ʵ��
	private String liuchenghao;//���̺�	
	private String financialoperator;//�������Ա(�������Ա)	
	private String accountmonth;//�����·�
	private String memo1;//(���)��ע
	private String noteno;//Ʊ�ݱ��	
	private String manualauditstatus;//�˹����״̬
	private String manualauditdatetime;//�˹����ʱ��	
	private String manualauditname;//�˹����Ա	
	private String financialstatus;//�������״̬
	private String financialdatetime;//�������ʱ��	
	private String cityaudit;//�м����״̬	
	private String kjyf;//�����·�(����·�)
	private String sc;//�������
	private String bg;//�칫���
	private String yy;//Ӫҵ���
	private String xxhzc;//��Ϣ��֧��
	private String jstz;//����Ͷ��
	private String dddf;//������
	
	private String countyauditstatus;//�����������״̬
	private String countyauditname;//�������������
	private String countyaudittime;//�����������ʱ��
	private String cityzrauditstatus;//���������״̬
	private String cityzrauditname;//�����������
	private String cityzraudittime;//���������ʱ��
	//������
	private String cycle;//��������	
	private String rated;//����	
	private String subtract;//����Ѳ�	
	private String ratio;//����ѱ�ֵ		
	private String estimate;//ֱ�����ɹ������	
	private double totalelec;//�����ϼ�	
	private double totalmoney;//��Ѻϼ�
	private double sdbdf;//ʡ������
	private double csdbdfe;//��ʡ�����Ѷ�
	private double dbydl;//����õ���
	
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
