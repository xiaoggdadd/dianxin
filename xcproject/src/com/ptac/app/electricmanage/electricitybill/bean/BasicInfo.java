package com.ptac.app.electricmanage.electricitybill.bean;
/**
 * @author lijing
 * @see ��ѵ����ҳ�������Ϣ�����ֶ�
 * @UPDATE WangYiXiao 2014-4-16 �����ֶ�  ���б�ѹ�����ͣ����緽ʽ�����ռ�ȣ����ֵ������ռ�ȣ�����ֵ����ƽռ�ȣ���ƽֵ������ռ�ȣ�����ֵ����ҵ�ֵ���
 */
public class BasicInfo {

	private String shi;//��
	private String xian;//����	
	private String xiaoqu;//����	
	private String stationtype;//վ������NAME
	private String zdlxbm;//վ������	����
	private String jzname;//վ������
	private String area;//����
	private String gsf;//������
	private String jztype;//���ű�������
	private String fkzq;//��������(��������)ZDDFINFO�����FKZQ	
	private String dfzflx;//���֧������
	private String beilv;//����	
	private String linelosstype;//��������
	private String linelossvalue;//����ֵ
	private String dbid;//���ID
	private String shifou;//�Ƿ���Ϊ����վ��
	private String edhdl;//��ĵ���
	private String csds;//��ʼ����
	private String cssytime;//��ʼʹ��ʱ��
	private String dianfei;//��ѵ���
	private String qsdbdl;//վ����ȫʡ�������
	private String zlfh;//ֱ������
	private String jlfh;//��������
	private String pue;//PUEֵ
	private String property;//վ������
	private String zybyqlx;//���б�ѹ������
	private String gdfs;//���緽ʽ ����
	private String gdfsname;//���緽ʽ ����
	private String changevalue;//�������
	private String jfzb;//���ռ��
	private String jfz;//���ֵ
	private String bfzb;//����ռ��
	private String bfz;//����ֵ
	private String bpzb;//��ƽռ��
	private String bpz;//��ƽֵ
	private String bgzb;//����ռ��
	private String bgz;//����ֵ
	private String dyjunitprice;//��ҵ�ֵ���
	private String yddf;//�õ���
	private String dbds;//����޸Ķ���
	private String xgbzw;//�������޸ı�־λ
	//2014-7-17
	private String stationtypecode;//վ������code
	private String propertycode;//վ������code
	private String dfzflxcode;//���֧������code
	private String gdfscode;//���緽ʽcode
	private String linelosstypecode;//��������code
	//2014-10-21
	private String scb;//������

	
	public String getGdfsname() {
		return gdfsname;
	}
	public void setGdfsname(String gdfsname) {
		this.gdfsname = gdfsname;
	}
	public String getZdlxbm() {
		return zdlxbm;
	}
	public void setZdlxbm(String zdlxbm) {
		this.zdlxbm = zdlxbm;
	}
	public String getPue() {
		return pue;
	}
	public void setPue(String pue) {
		this.pue = pue;
	}
	public String getDbds() {
		return dbds;
	}
	public void setDbds(String dbds) {
		this.dbds = dbds;
	}
	public String getXgbzw() {
		return xgbzw;
	}
	public void setXgbzw(String xgbzw) {
		this.xgbzw = xgbzw;
	}
	public String getDianfei() {
		return dianfei;
	}
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
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
	public String getStationtype() {
		return stationtype;
	}
	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGsf() {
		return gsf;
	}
	public void setGsf(String gsf) {
		this.gsf = gsf;
	}
	public String getJztype() {
		return jztype;
	}
	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	public String getFkzq() {
		return fkzq;
	}
	public void setFkzq(String fkzq) {
		this.fkzq = fkzq;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
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
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbid() {
		return dbid;
	}
	public String getShifou() {
		return shifou;
	}
	public void setShifou(String shifou) {
		this.shifou = shifou;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public void setCsds(String csds) {
		this.csds = csds;
	}
	public String getCsds() {
		return csds;
	}
	public void setCssytime(String cssytime) {
		this.cssytime = cssytime;
	}
	public String getCssytime() {
		return cssytime;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
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
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getZybyqlx() {
		return zybyqlx;
	}
	public void setZybyqlx(String zybyqlx) {
		this.zybyqlx = zybyqlx;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getJfzb() {
		return jfzb;
	}
	public void setJfzb(String jfzb) {
		this.jfzb = jfzb;
	}
	public String getJfz() {
		return jfz;
	}
	public void setJfz(String jfz) {
		this.jfz = jfz;
	}
	public String getBfzb() {
		return bfzb;
	}
	public void setBfzb(String bfzb) {
		this.bfzb = bfzb;
	}
	public String getBfz() {
		return bfz;
	}
	public void setBfz(String bfz) {
		this.bfz = bfz;
	}
	public String getBpzb() {
		return bpzb;
	}
	public void setBpzb(String bpzb) {
		this.bpzb = bpzb;
	}
	public String getBpz() {
		return bpz;
	}
	public void setBpz(String bpz) {
		this.bpz = bpz;
	}
	public String getBgzb() {
		return bgzb;
	}
	public void setBgzb(String bgzb) {
		this.bgzb = bgzb;
	}
	public String getBgz() {
		return bgz;
	}
	public void setBgz(String bgz) {
		this.bgz = bgz;
	}
	public String getDyjunitprice() {
		return dyjunitprice;
	}
	public void setDyjunitprice(String dyjunitprice) {
		this.dyjunitprice = dyjunitprice;
	}
	public String getYddf() {
		return yddf;
	}
	public void setYddf(String yddf) {
		this.yddf = yddf;
	}
	public String getChangevalue() {
		return changevalue;
	}
	public void setChangevalue(String changevalue) {
		this.changevalue = changevalue;
	}
	public String getStationtypecode() {
		return stationtypecode;
	}
	public void setStationtypecode(String stationtypecode) {
		this.stationtypecode = stationtypecode;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public String getDfzflxcode() {
		return dfzflxcode;
	}
	public void setDfzflxcode(String dfzflxcode) {
		this.dfzflxcode = dfzflxcode;
	}
	public String getGdfscode() {
		return gdfscode;
	}
	public void setGdfscode(String gdfscode) {
		this.gdfscode = gdfscode;
	}
	public String getLinelosstypecode() {
		return linelosstypecode;
	}
	public void setLinelosstypecode(String linelosstypecode) {
		this.linelosstypecode = linelosstypecode;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	
	
}
