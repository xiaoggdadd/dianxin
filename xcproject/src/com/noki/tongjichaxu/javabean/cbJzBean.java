package com.noki.tongjichaxu.javabean;


/**
 * @author xuzehua
 * 2013-05-14
 * 超标基站管理模块bean
 * 
 * */
public class cbJzBean {
	private String shiname;
	private String jzsxcount;
	private String scnhcount;
	private String cbgkcount;
	private String cbzdcount;
    private String dlcbcount;
	private String lxcbcount;
	private String lxzzcbcount;
	private String jsdbcount;
	private String bzdlcount;
	private String szdlcount;
	private String bzpldu;
	private String waqzgcount;
	private String zsx;
	private String pld;
	private String cbjzzg;
	private String zsxcount;
	private String cbdlzz;
	private String xzsgsdldb;
	private String zf;
	
	//新增
	
    private String xian;//小区
    //private String shi;
    private String xiaoqu;//乡镇
    private String zdid;//站点id
    private String jzname;//站点名称
    private String property;//站点属性
    private String stationtype;//站点类型
    
    private String shicode;//市编码
    public String getXiancode() {
		return xiancode;
	}

	public void setXiancode(String xiancode) {
		this.xiancode = xiancode;
	}

	private String xiancode;
    //生产分摊比例
    private String dbid;//电表id
    private String dbname;//电表名称
    private String scft; //生产分摊
    //超标站点
    private String sjydl;
    private String qsdbdl;
    private String cbbl;
    //结算电量超标
   private String jsrjhdl;//结算日均耗电量
    private String jssdb;//结算省定标
    private String jscbbl;//结算超标比例
    private String glrjhdl;
    private String glsdb;
    private String glcbbl;
    //未按期整改
    private String cxyf;
    private String cxrhdl;
    private String cxsdb;
    private String cxbl;
    
    private String ssyyf;//上上个月的月份
    private String ssyrhdl;
    private String ssysdb;
    private String ssybl;
    
    
    public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public String getCbdlzz() {
		return cbdlzz;
	}

	public void setCbdlzz(String cbdlzz) {
		this.cbdlzz = cbdlzz;
	}

	public String getXzsgsdldb() {
		return xzsgsdldb;
	}

	public void setXzsgsdldb(String xzsgsdldb) {
		this.xzsgsdldb = xzsgsdldb;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}    
    public String getCxyf() {
		return cxyf;
	}

	public void setCxyf(String cxyf) {
		this.cxyf = cxyf;
	}

	public String getCxrhdl() {
		return cxrhdl;
	}

	public void setCxrhdl(String cxrhdl) {
		this.cxrhdl = cxrhdl;
	}

	public String getCxsdb() {
		return cxsdb;
	}

	public void setCxsdb(String cxsdb) {
		this.cxsdb = cxsdb;
	}

	public String getCxbl() {
		return cxbl;
	}

	public void setCxbl(String cxbl) {
		this.cxbl = cxbl;
	}

	public String getSsyyf() {
		return ssyyf;
	}

	public void setSsyyf(String ssyyf) {
		this.ssyyf = ssyyf;
	}

	public String getSsyrhdl() {
		return ssyrhdl;
	}

	public void setSsyrhdl(String ssyrhdl) {
		this.ssyrhdl = ssyrhdl;
	}

	public String getSsysdb() {
		return ssysdb;
	}

	public void setSsysdb(String ssysdb) {
		this.ssysdb = ssysdb;
	}

	public String getSsybl() {
		return ssybl;
	}

	public void setSsybl(String ssybl) {
		this.ssybl = ssybl;
	}

	public String getJsrjhdl() {
		return jsrjhdl;
	}

	public void setJsrjhdl(String jsrjhdl) {
		this.jsrjhdl = jsrjhdl;
	}

	public String getJssdb() {
		return jssdb;
	}

	public void setJssdb(String jssdb) {
		this.jssdb = jssdb;
	}

	public String getJscbbl() {
		return jscbbl;
	}

	public void setJscbbl(String jscbbl) {
		this.jscbbl = jscbbl;
	}

	public String getGlrjhdl() {
		return glrjhdl;
	}

	public void setGlrjhdl(String glrjhdl) {
		this.glrjhdl = glrjhdl;
	}

	public String getGlsdb() {
		return glsdb;
	}

	public void setGlsdb(String glsdb) {
		this.glsdb = glsdb;
	}

	public String getGlcbbl() {
		return glcbbl;
	}

	public void setGlcbbl(String glcbbl) {
		this.glcbbl = glcbbl;
	}

	public String getSjydl() {
		return sjydl;
	}

	public void setSjydl(String sjydl) {
		this.sjydl = sjydl;
	}

	public String getQsdbdl() {
		return qsdbdl;
	}

	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}

	public String getCbbl() {
		return cbbl;
	}

	public void setCbbl(String cbbl) {
		this.cbbl = cbbl;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
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

	public String getScft() {
		return scft;
	}

	public void setScft(String scft) {
		this.scft = scft;
	}

	
    
	public String getShicode() {
		return shicode;
	}

	public void setShicode(String shicode) {
		this.shicode = shicode;
	}

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

/*	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}*/

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getZsxcount() {
		return zsxcount;
	}

	public void setZsxcount(String zsxcount) {
		this.zsxcount = zsxcount;
	}

	public String getCbjzzg() {
		return cbjzzg;
	}

	public void setCbjzzg(String cbjzzg) {
		this.cbjzzg = cbjzzg;
	}

	public String getPld() {
		return pld;
	}

	public void setPld(String pld) {
		this.pld = pld;
	}

	public String getZsx() {
		return zsx;
	}

	public void setZsx(String zsx) {
		this.zsx = zsx;
	}

	public String getWaqzgcount() {
		return waqzgcount;
	}

	public void setWaqzgcount(String waqzgcount) {
		this.waqzgcount = waqzgcount;
	}

	public String getBzdlcount() {
		return bzdlcount;
	}

	public void setBzdlcount(String bzdlcount) {
		this.bzdlcount = bzdlcount;
	}

	public String getSzdlcount() {
		return szdlcount;
	}

	public void setSzdlcount(String szdlcount) {
		this.szdlcount = szdlcount;
	}

	public String getBzpldu() {
		return bzpldu;
	}

	public void setBzpldu(String bzpldu) {
		this.bzpldu = bzpldu;
	}

	public String getJsdbcount() {
		return jsdbcount;
	}

	public void setJsdbcount(String jsdbcount) {
		this.jsdbcount = jsdbcount;
	}

	public String getLxzzcbcount() {
		return lxzzcbcount;
	}

	public void setLxzzcbcount(String lxzzcbcount) {
		this.lxzzcbcount = lxzzcbcount;
	}

	public String getLxcbcount() {
		return lxcbcount;
	}

	public void setLxcbcount(String lxcbcount) {
		this.lxcbcount = lxcbcount;
	}

	public String getCbzdcount() {
		return cbzdcount;
	}

	public void setCbzdcount(String cbzdcount) {
		this.cbzdcount = cbzdcount;
	}

	public String getShiname() {
		return shiname;
	}

	public void setShiname(String shiname) {
		this.shiname = shiname;
	}

	public String getJzsxcount() {
		return jzsxcount;
	}

	public void setJzsxcount(String jzsxcount) {
		this.jzsxcount = jzsxcount;
	}

	public String getScnhcount() {
		return scnhcount;
	}

	public void setScnhcount(String scnhcount) {
		this.scnhcount = scnhcount;
	}

	public String getCbgkcount() {
		return cbgkcount;
	}

	public void setCbgkcount(String cbgkcount) {
		this.cbgkcount = cbgkcount;
	}

	public String getDlcbcount() {
		return dlcbcount;
	}

	public void setDlcbcount(String dlcbcount) {
		this.dlcbcount = dlcbcount;
	}
    
}