package com.ptac.app.checksign.manElectricCheck.bean;

public class CountryHeadBean {
	
	private String biaozhi = "";//����������"���"����"Ԥ����"(js��)
	private String sort = "";//����������"���"����"Ԥ����"(��ʾ��)
	private String flag = "";//�Ƿ����쳣�߶1:�ǣ�0����
	private String description = "";//�쳣�߶�������Ϣ
	private String manpassmeno = "";//�˹����ͨ��ԭ��

	//վ��� ZHANDIAN
	private String zdid = "";//վ��id
	private String shi = "";//��
	private String xian = "";//����	
	private String xiaoqu = "";//����	
	private String jzname = "";//վ������
	private String stationtype = "";//վ������	
	private String property = "";//վ������
	private String qyzt = "";//վ������״̬
	//���� DIANBIAO
	private String dbid = "";//���id
	private String dbname = "";//�������
	private String dbqyzt = "";//�������״̬

	
	//������ AMMETERDEGREES
	private String blhdl = "";//ʵ���õ���(���ʺĵ���)
	private String edhdl = "";//��ĵ���(�б�)
	private String qsdbdl = "";//ʡ�������(ʡ��)
	private String lastdegree = "";//��ʼ�����
	private String thisdegree = "";//���������
	private String lastdatetime = "";//�ϴγ���ʱ��
	private String thisdatetime = "";//���γ���ʱ��
	private String floatdegree = "";//��������
	private String ammemo = "";//����������ע
	private String lastelecfees;//�ϴε��
	private String lastelecdegree;//�ϴε���
	private String lastunitprice;//�ϴε���
	//2014-6-27
	private String dedhdl;//��ĵ����������
	private String csdb;//ȫʡ��������������
	
	//��ѱ� ELECTRICFEES
	private String uuid = "";
	private String accountmonth = "";//�����·�
	private String actualpay = "";//ʵ�ʵ��
	private String unitprice = "";//����
	private String countryheadstatus = "";//����������˱�־
	private String floatpay = "";//��ѵ���
	private String efmemo = "";//��ѵ�����ע
	
	private String dfzflx = "";//���֧������
	private String gdfs = "";//���緽ʽ
	private String dbydl = "";//����õ���
	private String bzydl = "";//�����õ���
	private String jszq = "";//��������
	private String csdbdfe = "";//��ʡ�����Ѷ�	
	private String csdbdfbz = "";//��ʡ�����ѱ�ֵ	
	private String manualauditstatus = "";//�˹����״̬	
	private String cityaudit = "";//�м����״̬	
	private String manualauditstatus1 = "";//�������״̬	
	private String beilv = "";//����	
	private String pjsj = "";//Ʊ��ʱ��	
	private String pjbh = "";//Ʊ�ݱ��	
	private String pjlx = "";//Ʊ������	
	private String jfsj = "";//����ʱ��
	private String actualdegree = "";//ʵ���õ���
	private String autoauditstatus = "";//�Զ����״̬
	
	private String gdtx;//��������
	private String floatdegreeandbl;//��������*����
	private String dlid;//����ID
	private String electricfee_id;//���ID
	private String tbtzsq;//�ر��������
	private String yddf;//�õ���
	private String linelosstype;//��������
	private String linelossvalue;//����ֵ
	private String changevalue;//����ֵ
	private String actuallinelossvalue;//ʵ������ֵ
	
	private String lastyue;//�������
	
	private String lastfloatdegreeandbl;//���ڵ�������*����
	private String lastactualdegree;//���ڵ���õ���
	
	private String csdbdfjdz;//��ʡ�����Ѿ���ֵ
	private String cshidbdfjdz;//���ж����Ѿ���ֵ
	private String cshibdfbl;//���б��ѱ���
	private String csbdld;//��ʡ�������
	private String cshibdld;//���б������
	private String glbrjl;//������վ���
	private String bzrj;//�����վ�����
	private String lineandchangeandbl;//�߱������
	
	private String bzz;//��׼ֵ
	
	
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
	private String zflxflag;//֧�����ͱ�־
	
	
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
	private String ssdq = "";//��������
	private String cshibl = "";//���б����
	private String cshengbl = "";//��ʡ�����

	
	
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
