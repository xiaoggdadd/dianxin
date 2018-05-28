package com.ptac.app.electricmanage.electricitybill.bean;
/**
 * @author lijing
 * @see 电费单添加页面基本信息所需字段
 * @UPDATE WangYiXiao 2014-4-16 新增字段  自有变压器类型，供电方式，尖峰占比，尖峰值，波峰占比，波峰值，波平占比，波平值，波谷占比，波谷值，电业局单价
 */
public class BasicInfo {

	private String shi;//市
	private String xian;//区县	
	private String xiaoqu;//乡镇	
	private String stationtype;//站点类型NAME
	private String zdlxbm;//站点类型	编码
	private String jzname;//站点名称
	private String area;//地区
	private String gsf;//归属方
	private String jztype;//集团报表类型
	private String fkzq;//结算周期(付款周期)ZDDFINFO表里的FKZQ	
	private String dfzflx;//电费支付类型
	private String beilv;//倍率	
	private String linelosstype;//线损类型
	private String linelossvalue;//线损值
	private String dbid;//电表ID
	private String shifou;//是否标记为胡波站点
	private String edhdl;//额定耗电量
	private String csds;//初始读数
	private String cssytime;//初始使用时间
	private String dianfei;//电费单价
	private String qsdbdl;//站点表的全省定标电量
	private String zlfh;//直流负荷
	private String jlfh;//交流负荷
	private String pue;//PUE值
	private String property;//站点属性
	private String zybyqlx;//自有变压器类型
	private String gdfs;//供电方式 编码
	private String gdfsname;//供电方式 名称
	private String changevalue;//变损电量
	private String jfzb;//尖峰占比
	private String jfz;//尖峰值
	private String bfzb;//波峰占比
	private String bfz;//波峰值
	private String bpzb;//波平占比
	private String bpz;//波平值
	private String bgzb;//波谷占比
	private String bgz;//波谷值
	private String dyjunitprice;//电业局单价
	private String yddf;//用电电费
	private String dbds;//电表修改读数
	private String xgbzw;//电表读数修改标志位
	//2014-7-17
	private String stationtypecode;//站点类型code
	private String propertycode;//站点属性code
	private String dfzflxcode;//电费支付类型code
	private String gdfscode;//供电方式code
	private String linelosstypecode;//线损类型code
	//2014-10-21
	private String scb;//生产标

	
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
