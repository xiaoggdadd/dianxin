package com.ptac.app.checksign.cityelectricfeecheck.bean;

/**
 * @author WangYiXiao 2014-1-23
 * @see �м������������ֶ�
 */
public class CityElectricFeeCheck{
	//վ��� ZHANDIAN
	private String zdid;//վ��id
	private String zdcode;//վ��code
	private String jzname;//վ������
	private String property;//վ������
	private String stationtype;//վ������
	private String szdq;//���ڵ���(����+����(С��)
	private String edhdl;//��ĵ�
	private String zlfh;//ֱ������
	private String jlfh;//��������
	private String qsdbdl;//ȫʡ�������
	//���� DIANBIAO
	private String dbid;//���ID
	private String dfzflx;//���֧������
	private String linelosstype;//��������
	private String linelossvalue;//����ֵ
	private String beilv;//����
	//������ AMMETERDEGREES ��Ӧ����ͼ CITYCHECK_DDV
	private String lastdatetime;//�ϴγ���ʱ��
	private String thisdatetime;//���γ���ʱ��
	private String blhdl;//���ʺĵ���(ʵ���õ���)
	private String dedhdl;//�ĵ����ı�־(�����������)
	//��ѱ� ELECTRICFEES ��Ӧ����ͼ CITYCHECK_DDF
	//Ԥ���ѱ�prepayment f ��Ӧ����ͼ citycheck_yff
	private String unitprice;//����ddf
	private String accountmonth;//�����·�ddf,yff
	private String notetypeid;//Ʊ������ddf,yff
	private String ey_id;//���id(electricfee_id number)ddf Ԥ����id(id number)yff
	private String uuid;//���uuid(dfuuid) ddf,Ԥ����uuid yff
	private String actualpay;//ʵ�ʵ�ѽ��(actualpay)ddf,(money)yff
	private String autoauditstatus;//�Զ����״̬(0 δͨ�� 1 ͨ��)ddf
	private String noteno;//Ʊ�ݱ��ddf,yff
	private String manualauditstatus;//�˹����״̬(0δͨ�� -1����δͨ�� 1 �˹�ͨ�� 2 ����ͨ�� -2 �˹���ͨ��)ddf
	private String cityaudit;//�м����( 0δͨ����1ͨ��  -2��ͨ��)ddf,yff
	private String pjje;//Ʊ�ݽ��(2λС��)ddf,yff
	private String manpassmemo;//�˹����ͨ��ԭ��
	private String lastelecfees;//���ڵ��
	private String lastelecdegree;//���ڵ���
	private String lastunitprice;//���ڵ���
	//����
	private String dfbzw;//��ѱ�־λ�����ݿ����ޣ�1:�½ᣬԤ����2 ; 2:��ͬ���忨��Ԥ����1
	private String jszq;//��������   ���� =�����γ���ʱ��-�ϴγ���ʱ�䣩/1000/60/60/24+1;
	private String eddf;//���ѣ���ʽ����ĵ���*���ε���*���ڣ�
	private String qsdbdlcbbl;//ȫʡ�������������������ȫʡ���겻Ϊ�չ�ʽΪ����ʵ���õ���-��ȫʡ�������*���ڣ���/��ȫʡ�������*���ڣ�*100
	private String cityzrzt;//�м��������״̬
	private String cityzrname;//�м����������
	private String cityzrtime;//�м��������ʱ��
	private String quxian;//����
	private String gdtx;//����������
	
	/**
	 * �¼��ֶ�
	 * @author rock
	 * @return
	 */
	private String dbname;//�������
	private String gdfs;//���緽ʽ
	private String dbydl;//����õ���
	private String sccbsj;//�ϴγ���ʱ��
	private String bccbsj;//���γ���ʱ��
	private String bzydl;//�����õ���
	private String ydltz;//�õ�������
	private String dlmemo;//����������ע
	private String danjia;//����
	private String dftz;//��ѵ���
	private String dfmemo;//��ѵ�����ע
	private String csdbdfe;//��ʡ�����Ѷ�
	private String csdbdfbz;//��ʡ�����ѱ�ֵ
	private String pjsj;//Ʊ��ʱ��
	private String jfsj;//�ɷ�ʱ��
	
	private String lrbz;//¼���־����ѵ�¼�뻹��Ԥ����¼�룩
	
	private String tbtzsq;//�ر��������
	private String dlid;//����ID
	
	private String lastyue;//�������
	
	private String lastfloatdegreeandbl;//���ڵ�������*����
	private String lastactualdegree;//���ڵ���õ���
	private String floatdegreeandbl;//��������*����
	
	private String cshengbdld;//��ʡ�������
	private String cshibdld;//���б������
	private String csdbdfjdz;//��ʡ���Ѿ���ֵ
	private String cshidbdfjdz;//���б��Ѿ���ֵ
	private String cshidbdfbl;//���б��ѱ���
	private String glbrjl;//������վ���
	private String bzrj;//�����վ�����
	private String lineandchangeandbl;//�߱������
	
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
