package com.noki.ammeterdegree.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillCountBean;


/**
 * Ammeterdegrees entity. @author MyEclipse Persistence Tools
 */

public class AmmeterDegreeFormBean  implements java.io.Serializable {
	//分摊电量
    private String scdl;
    private String bgdl;
    private String yydl;
    private String xxhdl;
    private String jstzdl;
    private String dddfdl;//代垫电量
    private String qsdbdl;//全省定标电量
   
    //后加信息
    private String csds;
    private String cssytime;
    private String linelossvalue;
    private String linelosstype;
    private String shifou;
    private String bccbsj;
   
    private String gdfs;
	 

	 

	//分摊电量
	private String scdf;//网络运营线电费（生产）
    private String bgdf;//信息化支撑线电费
    private String yydf;//市场营销线电费（营业）
    private String xxhdf;//信息化支撑线电费
    private String jstzdf;//建设投资线电费
    private String dddfdf;//代垫电费
    
    //2014-10-28 WangYiXiao
    private String property;//站点属性
    private String shicode;//市编码
	//站点信息
    private String ycq;//原产权单位
    private String jzname;//站点名称
    private String jzcode;//站点编码
    private String wlzdbm;//物理站点编码
    private String zfdh;//支付单号物业系统
    private String gdfname;//供电方名称
    private String dbbm;//电表户号(电表编码)
    private String zzlx;//站址类型
	private String longitude;
	private String latitude;
	private String Gdfs;

	public String getYcq() {
		return ycq;
	}

	public void setYcq(String ycq) {
		this.ycq = ycq;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getJzcode() {
		return jzcode;
	}

	public void setJzcode(String jzcode) {
		this.jzcode = jzcode;
	}

	public String getWlzdbm() {
		return wlzdbm;
	}

	public void setWlzdbm(String wlzdbm) {
		this.wlzdbm = wlzdbm;
	}

	public String getZfdh() {
		return zfdh;
	}

	public void setZfdh(String zfdh) {
		this.zfdh = zfdh;
	}

	public String getGdfname() {
		return gdfname;
	}

	public void setGdfname(String gdfname) {
		this.gdfname = gdfname;
	}

	public String getDbbm() {
		return dbbm;
	}

	public void setDbbm(String dbbm) {
		this.dbbm = dbbm;
	}

	public String getZzlx() {
		return zzlx;
	}

	public void setZzlx(String zzlx) {
		this.zzlx = zzlx;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getShicode() {
		return shicode;
	}

	public void setShicode(String shicode) {
		this.shicode = shicode;
	}

	public String getQsdbdl() {
		return qsdbdl;
	}

	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}

	public String getBgdf() {
		return bgdf;
	}

	public void setBgdf(String bgdf) {
		this.bgdf = bgdf;
	}

	public String getYydf() {
		return yydf;
	}

	public void setYydf(String yydf) {
		this.yydf = yydf;
	}

	public String getXxhdf() {
		return xxhdf;
	}

	public void setXxhdf(String xxhdf) {
		this.xxhdf = xxhdf;
	}

	public String getJstzdf() {
		return jstzdf;
	}

	public void setJstzdf(String jstzdf) {
		this.jstzdf = jstzdf;
	}

	public String getDddfdl() {
		return dddfdl;
	}

	public void setDddfdl(String dddfdl) {
		this.dddfdl = dddfdl;
	}

	public String getDddfdf() {
		return dddfdf;
	}

	public void setDddfdf(String dddfdf) {
		this.dddfdf = dddfdf;
	}

	public String getBccbsj() {
		return bccbsj;
	}

	public void setBccbsj(String bccbsj) {
		this.bccbsj = bccbsj;
	}

	public String getShifou() {
		return shifou;
	}

	public void setShifou(String shifou) {
		this.shifou = shifou;
	}

	public String getScdl() {
		return scdl;
	}

	public void setScdl(String scdl) {
		this.scdl = scdl;
	}

	public String getBgdl() {
		return bgdl;
	}

	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}

	public String getYydl() {
		return yydl;
	}

	public void setYydl(String yydl) {
		this.yydl = yydl;
	}

	public String getXxhdl() {
		return xxhdl;
	}

	public void setXxhdl(String xxhdl) {
		this.xxhdl = xxhdl;
	}

	public String getJstzdl() {
		return jstzdl;
	}

	public void setJstzdl(String jstzdl) {
		this.jstzdl = jstzdl;
	}

	public String getCsds() {
		return csds;
	}

	public void setCsds(String csds) {
		this.csds = csds;
	}

	public String getCssytime() {
		return cssytime;
	}

	public void setCssytime(String cssytime) {
		this.cssytime = cssytime;
	}

	public String getLinelossvalue() {
		return linelossvalue;
	}

	public void setLinelossvalue(String linelossvalue) {
		this.linelossvalue = linelossvalue;
	}

	public String getLinelosstype() {
		return linelosstype;
	}

	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}

	private String actualpay;
	public String getActualpay() {
		return actualpay;
	}

	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}

	private String fkzq;
	public String getFkzq() {
		return fkzq;
	}
    
	public void setFkzq(String fkzq) {
		this.fkzq = fkzq;
	}
    private String lastdatetime1;
	public String getLastdatetime1() {
		return lastdatetime1;
	}

	public void setLastdatetime1(String lastdatetime1) {
		this.lastdatetime1 = lastdatetime1;
	}

	private String lrsj;
	private String bcsj;
	private String diqu;
    private String bcdbds;
	private String beilv;
	private String xslx;
	private String xsz;
	private String scdbds;
	private String ydl;
	private String sccbsj;
	private String sjydl;
	private String sjdfje;
	private String scydl;
	private String sclrsj;
	private String zlfh;
	private String jlfh;

	public String getDiqu() {
		return diqu;
	}
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}

	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}

	public String getXslx() {
		return xslx;
	}

	public void setXslx(String xslx) {
		this.xslx = xslx;
	}

	public String getXsz() {
		return xsz;
	}

	public void setXsz(String xsz) {
		this.xsz = xsz;
	}

	public String getScdbds() {
		return scdbds;
	}

	public void setScdbds(String scdbds) {
		this.scdbds = scdbds;
	}

	public String getYdl() {
		return ydl;
	}
    
	public void setYdl(String ydl) {
		this.ydl = ydl;
	}

	public String getSccbsj() {
		return sccbsj;
	}

	public void setSccbsj(String sccbsj) {
		this.sccbsj = sccbsj;
	}

	public String getSjydl() {
		return sjydl;
	}

	public void setSjydl(String sjydl) {
		this.sjydl = sjydl;
	}

	public String getSjdfje() {
		return sjdfje;
	}

	public void setSjdfje(String sjdfje) {
		this.sjdfje = sjdfje;
	}

	public String getScydl() {
		return scydl;
	}

	public void setScydl(String scydl) {
		this.scydl = scydl;
	}

	public String getScdf() {
		return scdf;
	}

	public void setScdf(String scdf) {
		this.scdf = scdf;
	}

	public String getSclrsj() {
		return sclrsj;
	}

	public void setSclrsj(String sclrsj) {
		this.sclrsj = sclrsj;
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

	public String getPue() {
		return pue;
	}

	public void setPue(String pue) {
		this.pue = pue;
	}
	

	private String pue;
    // Fields  
	private String ad2_bz;
	private String ad2_bz1;
	//基站信息
	
	private String shi;
	private String xian;
	private String xiaoqu;
	private String stationid;
    private String stationname;
    private String stationtype;
    private String provinceid;
    private String cityid;
    private String countryid;
    private String stationtypeid;
    private String isbenchmarkstation;
    private String stationaliasname;
  

	private String gsf;
    private String jztype;
    private String dianfei;
    //电表信息
	private String ammeterid;
	private String initialdegree;
	private String initialdate;
	private String electriccurrenttype_ammeter;
	private String usageofelectypeid_ammeter;
	private String ammeteruse;
	private String multiplyingpower;
	private String dfzflx;
	
	//电量信息
     private String ammeterdegreeid;
     private String ammeteridFk;
     private String electriccurrenttype;
     private String usageofelectypeid;
     private String lastdegree;
     private String thisdegree;
     private String lastdatetime;
     private String thisdatetime;
     private String floatdegree;
     private String actualdegree;
     private String recorder;
     private String collectdatetime;
     private String inputoperator;
     private String inputdatetime;
     private String datasource;
     private String autoauditstatus;
     private String autoauditdescription;
     private String manualauditstatus;
     private String manualauditname;
     private String manualauditdatetime;
     private String financialoperator;
     private String financialdatetime;
     private String memo;
     private String startmonth;
     private String endmonth;
     private String blhdl;
     private String payzq;
     private String entrypensonnel;
     private String entrytime;
     private String sumdegree;
     private String dbili;
    private String shuoshuzhuanye;
    private String startdegree;
    private String startdate;
    private String edhdl;
    private double pjje;
    private String itemvalue;
    private String accountmonth;//报账月份
    private String memodf;//电费备注
    private String notetypeid;//票据类型
    private String noteno;//票据编号
    private String notetime;//票据时间
    private String paydatetime;//交费时间
    private String payoperator;//交费操作员
    private String floatpay;//费用调整
    
    private String lastdl;//上期电量
    private String lastdf;//上期电费
    private String scdj;//上期单价
    private String lastlch;//上期预付费流程号
    private String lastyue;//上期余额
    //2014-10-28
    private String hbzq;//合并周期
    private String bzz;//标准值
    private String scbbl;//超省标
    private String scb;//生产标
    private String dbydl;//电表用电量
    private String stationtypecode;//站点类型code
    private String propertycode;//站点属性code
    private String gdfscode;//供电方式code
    private String dfzflxcode;//电费支付类型code
    
    
    public String getScdj() {
		return scdj;
	}

	public void setScdj(String scdj) {
		this.scdj = scdj;
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

	public String getGdfscode() {
		return gdfscode;
	}

	public void setGdfscode(String gdfscode) {
		this.gdfscode = gdfscode;
	}

	public String getDfzflxcode() {
		return dfzflxcode;
	}

	public void setDfzflxcode(String dfzflxcode) {
		this.dfzflxcode = dfzflxcode;
	}

	public String getHbzq() {
		return hbzq;
	}

	public void setHbzq(String hbzq) {
		this.hbzq = hbzq;
	}

	public String getBzz() {
		return bzz;
	}

	public void setBzz(String bzz) {
		this.bzz = bzz;
	}

	public String getScbbl() {
		return scbbl;
	}

	public void setScbbl(String scbbl) {
		this.scbbl = scbbl;
	}

	public String getScb() {
		return scb;
	}

	public void setScb(String scb) {
		this.scb = scb;
	}

	public String getDbydl() {
		return dbydl;
	}

	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}

	public String getLastdl() {
		return lastdl;
	}

	public void setLastdl(String lastdl) {
		this.lastdl = lastdl;
	}

	public String getLastdf() {
		return lastdf;
	}

	public void setLastdf(String lastdf) {
		this.lastdf = lastdf;
	}

	public String getLastlch() {
		return lastlch;
	}

	public void setLastlch(String lastlch) {
		this.lastlch = lastlch;
	}

	public String getLastyue() {
		return lastyue;
	}

	public void setLastyue(String lastyue) {
		this.lastyue = lastyue;
	}
    
     public String getFloatpay() {
		return floatpay;
	}

	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}

	public String getPaydatetime() {
		return paydatetime;
	}

	public void setPaydatetime(String paydatetime) {
		this.paydatetime = paydatetime;
	}

	public String getPayoperator() {
		return payoperator;
	}

	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}

	public String getNotetypeid() {
		return notetypeid;
	}

	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}

	public String getNoteno() {
		return noteno;
	}

	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}

	public String getNotetime() {
		return notetime;
	}

	public void setNotetime(String notetime) {
		this.notetime = notetime;
	}

	public String getMemodf() {
		return memodf;
	}

	public void setMemodf(String memodf) {
		this.memodf = memodf;
	}

	public String getAccountmonth() {
		return accountmonth;
	}

	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}

	public String getItemvalue() {
		return itemvalue;
	}

	public void setItemvalue(String itemvalue) {
		this.itemvalue = itemvalue;
	}

	public double getPjje() {
		return pjje;
	}

	public void setPjje(double pjje) {
		this.pjje = pjje;
	}

	public String getStartdegree() {
		return startdegree;
	}

	public void setStartdegree(String startdegree) {
		this.startdegree = startdegree;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getShuoshuzhuanye() {
		return shuoshuzhuanye;
	}

	public void setShuoshuzhuanye(String shuoshuzhuanye) {
		this.shuoshuzhuanye = shuoshuzhuanye;
	}

	public String getDbili() {
		return dbili;
	}

	public void setDbili(String dbili) {
		this.dbili = dbili;
	}

	public String getSumdegree() {
		return sumdegree;
	}

	public void setSumdegree(String sumdegree) {
		this.sumdegree = sumdegree;
	}

	public String getDianfei() {
 		return dianfei;
 	}

 	public void setDianfei(String dianfei) {
 		this.dianfei = dianfei;
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

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
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

	public String getBlhdl() {
		return blhdl;
	}

	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}

	public AmmeterDegreeFormBean() {
     }
    // Property accessors

	public String getAmmeterdegreeid() {
		return ammeterdegreeid;
	}

	public void setAmmeterdegreeid(String ammeterdegreeid) {
		this.ammeterdegreeid = ammeterdegreeid;
	}

	public String getAmmeteridFk() {
		return ammeteridFk;
	}

	public void setAmmeteridFk(String ammeteridFk) {
		this.ammeteridFk = ammeteridFk;
	}

	public String getElectriccurrenttype() {
		return electriccurrenttype;
	}

	public void setElectriccurrenttype(String electriccurrenttype) {
		this.electriccurrenttype = electriccurrenttype;
	}

	public String getUsageofelectypeid() {
		return usageofelectypeid;
	}

	public void setUsageofelectypeid(String usageofelectypeid) {
		this.usageofelectypeid = usageofelectypeid;
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

	public String getActualdegree() {
		return actualdegree;
	}

	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getCollectdatetime() {
		return collectdatetime;
	}

	public void setCollectdatetime(String collectdatetime) {
		this.collectdatetime = collectdatetime;
	}

	public String getInputoperator() {
		return inputoperator;
	}

	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
	}

	public String getInputdatetime() {
		return inputdatetime;
	}

	public void setInputdatetime(String inputdatetime) {
		this.inputdatetime = inputdatetime;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getAutoauditstatus() {
		return autoauditstatus;
	}

	public void setAutoauditstatus(String autoauditstatus) {
		this.autoauditstatus = autoauditstatus;
	}

	public String getAutoauditdescription() {
		return autoauditdescription;
	}

	public void setAutoauditdescription(String autoauditdescription) {
		this.autoauditdescription = autoauditdescription;
	}

	public String getManualauditstatus() {
		return manualauditstatus;
	}

	public void setManualauditstatus(String manualauditstatus) {
		this.manualauditstatus = manualauditstatus;
	}

	public String getManualauditname() {
		return manualauditname;
	}

	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}

	public String getManualauditdatetime() {
		return manualauditdatetime;
	}

	public void setManualauditdatetime(String manualauditdatetime) {
		this.manualauditdatetime = manualauditdatetime;
	}

	public String getFinancialoperator() {
		return financialoperator;
	}

	public void setFinancialoperator(String financialoperator) {
		this.financialoperator = financialoperator;
	}

	public String getFinancialdatetime() {
		return financialdatetime;
	}

	public void setFinancialdatetime(String financialdatetime) {
		this.financialdatetime = financialdatetime;
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

	
	/**
	   * 电量明细与修改界面调用此方法
	   * @param accountId String
	   * @return AccountFormBeangetAmmeterDegreeAllInfo
	   */
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeInfo(String degreeid) {
		AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select ammeterdegreeid,actualdegree,ammeterid_Fk,autoauditdescription,autoauditstatus,TO_CHAR(collectdatetime,'yyyy-mm-dd') collectdatetime," +
	    		"datasource,electriccurrenttype,TO_CHAR(endmonth,'yyyy-mm') endmonth,TO_CHAR(financialdatetime,'yyyy-mm-dd') financialdatetime,financialoperator,floatdegree,TO_CHAR(inputdatetime,'yyyy-mm-dd') inputdatetime,inputoperator," +
	    		"TO_CHAR(lastdatetime,'yyyy-mm-dd') lastdatetime,lastdegree,TO_CHAR(manualauditdatetime,'yyyy-mm-dd') manualauditdatetime,manualauditname,manualauditstatus,memo,recorder,TO_CHAR(startmonth,'yyyy-mm') startmonth,TO_CHAR(thisdatetime,'yyyy-mm-dd') thisdatetime," +
	    		"thisdegree,usageofelectypeid,blhdl,autoauditstatus,autoauditdescription from dianduview where AMMETERDEGREEID="+degreeid);
	    
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("getAmmeterDegreeInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	bean.setAmmeterdegreeid(rs.getString("ammeterdegreeid") != null ? rs.getString("ammeterdegreeid") : "");
	    	bean.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");
	    	bean.setAmmeteridFk(rs.getString("ammeterid_Fk") != null ? rs.getString("ammeterid_Fk") : "");
	    	bean.setAutoauditdescription(rs.getString("autoauditdescription") != null ? rs.getString("autoauditdescription") : "");
	    	bean.setAutoauditstatus(rs.getString("autoauditstatus") != null ? rs.getString("autoauditstatus") : "");
	    	bean.setCollectdatetime(rs.getString("collectdatetime") != null ? rs.getString("collectdatetime") : "");
	    	bean.setDatasource(rs.getString("datasource") != null ? rs.getString("datasource") : "");
	    	bean.setElectriccurrenttype(rs.getString("electriccurrenttype") != null ? rs.getString("electriccurrenttype") : "");
	    	bean.setEndmonth(rs.getString("endmonth") != null ? rs.getString("endmonth") : "");
	    	bean.setFinancialdatetime(rs.getString("financialdatetime") != null ? rs.getString("financialdatetime") : "");
	    	bean.setFinancialoperator(rs.getString("financialoperator") != null ? rs.getString("financialoperator") : "");
	    	bean.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
	    	bean.setInputdatetime(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");
	    	bean.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");
	    	bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
	    	bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
	    	bean.setManualauditdatetime(rs.getString("manualauditdatetime") != null ? rs.getString("manualauditdatetime") : "");
	    	bean.setManualauditname(rs.getString("manualauditname") != null ? rs.getString("manualauditname") : "");
	    	bean.setManualauditstatus(rs.getString("manualauditstatus") != null ? rs.getString("manualauditstatus") : "");
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	bean.setRecorder(rs.getString("recorder") != null ? rs.getString("recorder") : "");
	    	bean.setStartmonth(rs.getString("startmonth") != null ? rs.getString("startmonth") : "");
	    	bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs.getString("thisdatetime") : "");
	    	bean.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
	    	bean.setUsageofelectypeid(rs.getString("usageofelectypeid") != null ? rs.getString("usageofelectypeid") : "");
	    	bean.setBlhdl(rs.getString("blhdl") != null ? rs.getString("blhdl") : "");//倍率耗电量
	    	bean.setAutoauditstatus(rs.getString("autoauditstatus") != null ? rs.getString("autoauditstatus") : "");//自动审核状态
	    	bean.setAutoauditdescription(rs.getString("autoauditdescription") != null ? rs.getString("autoauditdescription") : "");//自动审核描述
	        //bean.setMemo(rs.getString(14) != null ? rs.getString(14) : "");
	      }
	      rs.close();
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
	  //获取县code    更改
	  public synchronized String getXian(String degreeid) {
		    StringBuffer sql = new StringBuffer();
		    sql.append("select z.xian from zhandian z,dianbiao d, ammeterdegrees a where a.AMMETERDEGREEID=? and a.ammeterid_fk=d.dbid and d.zdid=z.id");
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getXian:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll003(sql.toString(), degreeid);
		      if (rs.next()) {
		    	  result=rs.getString(1) != null ? rs.getString(1) : "";
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		    	
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return result;
		  }
	  //获取所有的站点
	  public synchronized ArrayList getStation(String loginId) {
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    sql.append("SELECT JZNAME,ZDCODE FROM ZHANDIAN Z WHERE XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"') ORDER BY JZNAME");
		   // String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getzhandian:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      if (rs.next()) {
		    	  Query query = new Query();
					list = query.query(rs);
		    	
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }
	  //标志位
	  public synchronized String getShifou(String zdcode,String loginId) {
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    sql.append("SELECT z.bgsign FROM ZHANDIAN Z WHERE z.zdcode= " +zdcode+
		    		" AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"')");
		   
		    sql.append(" ORDER BY JZNAME");
		    //sql.append("SELECT JZNAME,d.dbid,d.dbname FROM ZHANDIAN Z, DIANBIAO D WHERE  Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.QYZT='1' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1'  AND (D.DFZFLX = 'dfzflx01' OR D.DFZFLX = 'dfzflx03') AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"')AND JZNAME LIKE '%"+zdmc+"%'  ORDER BY JZNAME");
		    
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("模糊查询站点:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while(rs.next()){
		    	  result=rs.getString(1);
		      }
		      rs.close();
		      db.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return result;
		  }
	  //模糊查询站点
	  public synchronized ArrayList getStationMhchexk(String loginId,String mc,String str) {
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    String zdmc=mc;   
		    if(zdmc.contains("?")){
		    	System.out.println(zdmc.contains("?"));
		    	StringBuffer zdmc1=new StringBuffer(zdmc);
		    	zdmc=zdmc1.substring(0, zdmc1.lastIndexOf("?"));
		    }
		    sql.append("SELECT JZNAME,d.dbid,d.dbname,z.zdcode FROM ZHANDIAN Z, DIANBIAO D WHERE  " +
		    		"Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.QYZT='1' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1' "+str+" " +
		    		" AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"')");
		 
		    if(!"请输入".equals(mc)){
		    	 sql.append(" AND JZNAME LIKE '%"+zdmc+"%'");
		    }
		   
		    sql.append(" ORDER BY JZNAME");
		    //sql.append("SELECT JZNAME,d.dbid,d.dbname FROM ZHANDIAN Z, DIANBIAO D WHERE  Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.QYZT='1' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1'  AND (D.DFZFLX = 'dfzflx01' OR D.DFZFLX = 'dfzflx03') AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginId+"')AND JZNAME LIKE '%"+zdmc+"%'  ORDER BY JZNAME");
		    
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("模糊查询站点:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      
		    	 Query query = new Query();
				list = query.query(rs);
		    	
		   
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }
	  //获取电表用途
	  public synchronized String getDBYT(String degreeid) {
		    StringBuffer sql = new StringBuffer();
		    sql.append("select d.dbyt ammeteruse from zhandian z,dianbiao d, ammeterdegrees a where a.AMMETERDEGREEID=? and a.ammeterid_fk=d.dbid and d.zdid=z.id");
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("dbyt:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll003(sql.toString(), degreeid);
		      if (rs.next()) {
		    	  result=rs.getString(1) != null ? rs.getString(1) : "";
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return result;
		  }
	  //得到了部分电费信息
	  public synchronized AmmeterDegreeFormBean getAmmeterAllinfo(String code) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("  select db.beilv,amm.THISDEGREE bcdbds,amm.thisdatetime bcsj,amm.actualdegree ydl,amm.BLHDL sjhdl from dianbiao db,ammeterdegrees amm,electricfees el where db.dbid='"+code+"' and db.dbid=amm.ammeterid_fk(+) and amm.ammeterdegreeid=el.ammeterdegreeid_fk(+)"
            );
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getAmmeterDegreeInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      System.out.println("=====================ok");
		      while (rs.next()) {
		    	bean.setBeilv(rs.getString("beilv") != null ? rs.getString("beilv") : "");
		    	bean.setBcdbds(rs.getString("bcdbds") != null ? rs.getString("bcdbds") : "");
		    	bean.setBcsj(rs.getString("bcsj") != null ? rs.getString("bcsj") : "");
		    	bean.setYdl(rs.getString("ydl") != null ? rs.getString("ydl") : "");
		    	bean.setSjydl(rs.getString("sjhdl") != null ? rs.getString("sjhdl") : "");
		    	bean.setSjdfje(rs.getString("sjdfje") != null ? rs.getString("sjdfje") : "");
		    	bean.setLrsj(rs.getString("lrsj") != null ? rs.getString("lrsj") : "");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  
	  public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getBcsj() {
		return bcsj;
	}

	public void setBcsj(String bcsj) {
		this.bcsj = bcsj;
	}

	public String getBcdbds() {
		return bcdbds;
	}

	public void setBcdbds(String bcdbds) {
		this.bcdbds = bcdbds;
	}

	/**
	   * 
	   * @param accountId String
	   * @return AccountFormBean------------------
	   */
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeAllInfo1(String code) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //查询配置表 电量上下浮动的 百分比
		    String sql1="SELECT T.ITEMVALUE FROM PERMISSION_CONFIGURATION T WHERE T.ITEMNAME='AuditDegree6'";
		    sql.append(
		    		"select (select a.agname from d_area_grade a where a.agcode=zd.shi) ||" +
		    		"(select a.agname from d_area_grade a where a.agcode=zd.xian) ||" +
		    		"(select a.agname from d_area_grade a where a.agcode=zd.xiaoqu) diqu,d.bgsign bb," +
		    		" (select name from indexs where code = zd.stationtype and type = 'stationtype') stationtype," +
		    		"(select name from indexs where code = zd.gsf and type = 'gsf') gsf,"+
                    "(select name from indexs where code = zd.jztype and type = 'ZDLX') jztype," +		    		
		             "(select ind.name from indexs ind where ind.code = d.LINELOSSTYPE and type = 'xslx') xslx,"+
		            "d.linelossvalue xsz,"+
		            "zd.dianfei,"+
		            "zd.Zlfh zlfh,"+
		            "zd.jlfh jlfh,zd.edhdl edhdl,"+
		            "zd.pue pue,"+
		            "(select ind.name from indexs ind where ind.code = zdinfo.fkzq and type = 'FKZQ') fkzq,"+
		    		
		    		"zd.bieming, case when zd.bgsign='1' then '是' else '否' end bgsign,d.dbid, " +
		    		"(select name from indexs where code = d.dbyt and type = 'DBYT') dbyt," +
		    		"d.csds,d.cssytime,d.beilv," +
		    		"(select name from indexs where code = d.ydsb and type = 'YDSB') ydsb," +
		    		"d.dllx,(select name from indexs where code=d.dfzflx and type='dfzflx')as dfzflx,zd.qsdbdl," +
		    		" RTNAME(ZD.ZDCQ) AS YCQ,ZD.JZNAME,ZD.JZCODE,ZD.WLZDBM,ZD.GDFNAME,D.DBBM,RTNAME(ZD.ZZLX) AS ZZLX " +
		    		"from ZDDFINFO  zdinfo, dianbiao d,zhandian zd," +
		    		" d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode " +
		    		"  and zd.id =zdinfo.zdid(+) and d.dbid = '"+code+"' and d.dbyt='dbyt01'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getAmmeterDegreeInfo----:"+sql);
		      db.connectDb();
		      ResultSet rs = null,rs1=null;
		      rs1=db.queryAll(sql1.toString());
		      while(rs1.next()){
			    	bean.setItemvalue(rs1.getString("itemvalue") != null ? rs1.getString("itemvalue") : "");
		      }
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	bean.setAmmeterid(rs.getString("dbid") != null ? rs.getString("dbid") : "");
		        bean.setDiqu(rs.getString("diqu") != null ? rs.getString("diqu") : "");		        
		        bean.setShifou(rs.getString(2) != null ? rs.getString(2) : "");
		        bean.setFkzq(rs.getString("fkzq") != null ? rs.getString("fkzq") : "");
		        bean.setLinelosstype(rs.getString("xslx") != null ? rs.getString("xslx") : "");
		        bean.setLinelossvalue(rs.getString("xsz") != null ? rs.getString("xsz") : "");
		        bean.setUsageofelectypeid_ammeter(rs.getString("ydsb") != null ? rs.getString("ydsb") : "");
		        bean.setMultiplyingpower(rs.getString("beilv") != null ? rs.getString("beilv") : "");
		        bean.setElectriccurrenttype_ammeter(rs.getString("dllx") != null ? rs.getString("dllx") : "");
		        bean.setAmmeteruse(rs.getString("dbyt") != null ? rs.getString("dbyt") : "");
		        bean.setDfzflx(rs.getString("dfzflx")!=null?rs.getString("dfzflx"):"");
		        bean.setStationtype(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
		        bean.setGsf(rs.getString("gsf")!=null?rs.getString("gsf"):"");
		        bean.setJztype(rs.getString("jztype")!=null?rs.getString("jztype"):"");
		        bean.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx") : "");		        
		        bean.setDianfei(rs.getString("dianfei") != null ? rs.getString("dianfei") : "");			        
		        bean.setXsz(rs.getString("xsz") != null ? rs.getString("xsz") : "");
		        bean.setZlfh(rs.getString("zlfh") != null ? rs.getString("zlfh") : ""); 
		        bean.setJlfh(rs.getString("jlfh") != null ? rs.getString("jlfh") : ""); 
		        bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		        bean.setCssytime(rs.getString("cssytime")!=null ? rs.getString("cssytime") : "");
		        bean.setEdhdl(rs.getString("edhdl")!=null ? rs.getString("edhdl") :"");	
		        bean.setQsdbdl(rs.getString("qsdbdl")!=null?rs.getString("qsdbdl"):"");
		        //----新增字段
		        bean.setYcq(rs.getString("ycq")!=null?rs.getString("ycq"):"");
		        bean.setJzname(rs.getString("jzname")!=null?rs.getString("jzname"):"");
		        bean.setJzcode(rs.getString("jzcode")!=null?rs.getString("jzcode"):"");
		        bean.setWlzdbm(rs.getString("wlzdbm")!=null?rs.getString("wlzdbm"):"");
		        bean.setZfdh("");
		        bean.setGdfname(rs.getString("gdfname")!=null?rs.getString("gdfname"):"");
		        bean.setDbbm(rs.getString("dbbm")!=null?rs.getString("dbbm"):"");
		        bean.setZzlx(rs.getString("zzlx")!=null?rs.getString("zzlx"):"");
		        
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //电量列表预付费管理合同单--添加带出来的信息
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeAllInfo(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select distinct dag.agname as xian,d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 5 )) as shi," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng, " +
		    		"zd.jzname,zd.id, (select name from indexs where code = zd.jztype and type = 'ZDLX') jztype, " +
		    		"zd.bieming, case when zd.bgsign='1' then '是' else '否' end bgsign,d.dbid, " +
		    		"(select name from indexs where code = d.dbyt and type = 'DBYT') dbyt," +
		    		"d.csds,d.beilv,pr.sumdegree, pr.startdegree,to_char(pr.startdate,'yyyy-mm-dd') startdate,zd.edhdl,zd.qsdbdl," +
		    		"(select name from indexs where code = d.ydsb and type = 'YDSB') ydsb,d.danjia," +
		    		"d.dllx,(select name from indexs where code=d.dfzflx and type='dfzflx')as dfzflx," +
		    		"zd.property,zd.zlfh,zd.jlfh,zd.scb,zd.shi shicode,d.dfzflx dfzflxa,zd.stationtype,zd.gdfs " +
		    		"from prepayment pr,dianbiao d,zhandian zd," +
		    		"d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode and zd.qyzt='1' and pr.cityaudit='1' and d.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("电量列表预付费管理合同单--添加预付费带出来的信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		    	bean.setStationid(rs.getString("id")!= null ? rs.getString("id") : "");
		        bean.setProvinceid(rs.getString("sheng") != null ? rs.getString("sheng") : "");
		        bean.setCityid(rs.getString("shi") != null ? rs.getString("shi") : "");
		        bean.setCountryid(rs.getString("xian") != null ? rs.getString("xian") : "");
		        bean.setStationname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
		        bean.setStationaliasname(rs.getString("bieming") != null ? rs.getString("bieming") : "");
		        bean.setStationtypeid(rs.getString("jztype") != null ? rs.getString("jztype") : "");
		        bean.setIsbenchmarkstation(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
		        bean.setAmmeterid(rs.getString("dbid") != null ? rs.getString("dbid") : "");
		        bean.setInitialdegree(rs.getString("csds") != null ? rs.getString("csds") : "");
		        bean.setInitialdate(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		        bean.setUsageofelectypeid_ammeter(rs.getString("ydsb") != null ? rs.getString("ydsb") : "");
		        bean.setMultiplyingpower(rs.getString("beilv") != null ? rs.getString("beilv") : "");
		        bean.setElectriccurrenttype_ammeter(rs.getString("dllx") != null ? rs.getString("dllx") : "");
		        bean.setAmmeteruse(rs.getString("dbyt") != null ? rs.getString("dbyt") : "");
		        bean.setDfzflx(rs.getString("dfzflx")!=null?rs.getString("dfzflx"):"");

		        bean.setDianfei(rs.getString("danjia")!=null?rs.getString("danjia"):"");
		        bean.setSumdegree(rs.getString("sumdegree")!=null?rs.getString("sumdegree"):"");
		        bean.setStartdegree(rs.getString("startdegree")!=null?rs.getString("startdegree"):"");
		        bean.setStartdate(rs.getString("startdate")!=null?rs.getString("startdate"):"");
		        bean.setEdhdl(rs.getString("edhdl")!=null?rs.getString("edhdl"):"");
		        bean.setQsdbdl(rs.getString("qsdbdl")!=null?rs.getString("qsdbdl"):"");
		        bean.setShicode(rs.getString("shicode")!=null?rs.getString("shicode"):"");
		        bean.setProperty(rs.getString("property")!=null?rs.getString("property"):"");
		        bean.setScb(rs.getString("scb")!=null?rs.getString("scb"):"");
		        bean.setZlfh(rs.getString("zlfh")!=null?rs.getString("zlfh"):"");
		        bean.setJlfh(rs.getString("jlfh")!=null?rs.getString("jlfh"):"");
		        bean.setDfzflxcode(rs.getString("dfzflxa")!=null?rs.getString("dfzflxa"):"");
		        bean.setStationtypecode(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
		        bean.setGdfscode(rs.getString("gdfs")!=null?rs.getString("gdfs"):"");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //电量列表预付费管理合同单--添加带出来的信息------ @WangYiXiao 2014-11-13 电量列表,预付费,合同单都调用这个新的方法以防其他用到的地方出错
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeAllInfoa(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select distinct dag.agname as xian,d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 5 )) as shi," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng, " +
		    		"zd.jzname,zd.id, (select name from indexs where code = zd.jztype and type = 'ZDLX') jztype, " +
		    		"zd.bieming, case when zd.bgsign='1' then '是' else '否' end bgsign,d.dbid, " +
		    		"(select name from indexs where code = d.dbyt and type = 'DBYT') dbyt," +
		    		"d.csds,d.beilv,zd.edhdl,zd.qsdbdl," +
		    		"(select name from indexs where code = d.ydsb and type = 'YDSB') ydsb,d.danjia," +
		    		"d.dllx,(select name from indexs where code=d.dfzflx and type='dfzflx')as dfzflx," +
		    		"zd.property,zd.zlfh,zd.jlfh,zd.scb,zd.shi shicode,d.dfzflx dfzflxa,zd.stationtype,zd.gdfs " +
		    		"from dianbiao d,zhandian zd," +
		    		"d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode and zd.qyzt='1' and d.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("电量列表预付费管理合同单--添加预付费带出来的信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		    	bean.setStationid(rs.getString("id")!= null ? rs.getString("id") : "");
		        bean.setProvinceid(rs.getString("sheng") != null ? rs.getString("sheng") : "");
		        bean.setCityid(rs.getString("shi") != null ? rs.getString("shi") : "");
		        bean.setCountryid(rs.getString("xian") != null ? rs.getString("xian") : "");
		        bean.setStationname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
		        bean.setStationaliasname(rs.getString("bieming") != null ? rs.getString("bieming") : "");
		        bean.setStationtypeid(rs.getString("jztype") != null ? rs.getString("jztype") : "");
		        bean.setIsbenchmarkstation(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
		        bean.setAmmeterid(rs.getString("dbid") != null ? rs.getString("dbid") : "");
		        bean.setInitialdegree(rs.getString("csds") != null ? rs.getString("csds") : "");
		        bean.setInitialdate(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		        bean.setUsageofelectypeid_ammeter(rs.getString("ydsb") != null ? rs.getString("ydsb") : "");
		        bean.setMultiplyingpower(rs.getString("beilv") != null ? rs.getString("beilv") : "");
		        bean.setElectriccurrenttype_ammeter(rs.getString("dllx") != null ? rs.getString("dllx") : "");
		        bean.setAmmeteruse(rs.getString("dbyt") != null ? rs.getString("dbyt") : "");
		        bean.setDfzflx(rs.getString("dfzflx")!=null?rs.getString("dfzflx"):"");

		        bean.setDianfei(rs.getString("danjia")!=null?rs.getString("danjia"):"");
//		        bean.setSumdegree(rs.getString("sumdegree")!=null?rs.getString("sumdegree"):"");
//		        bean.setStartdegree(rs.getString("startdegree")!=null?rs.getString("startdegree"):"");
//		        bean.setStartdate(rs.getString("startdate")!=null?rs.getString("startdate"):"");
		        bean.setEdhdl(rs.getString("edhdl")!=null?rs.getString("edhdl"):"");
		        bean.setQsdbdl(rs.getString("qsdbdl")!=null?rs.getString("qsdbdl"):"");
		        bean.setShicode(rs.getString("shicode")!=null?rs.getString("shicode"):"");
		        bean.setProperty(rs.getString("property")!=null?rs.getString("property"):"");
		        bean.setScb(rs.getString("scb")!=null?rs.getString("scb"):"");
		        bean.setZlfh(rs.getString("zlfh")!=null?rs.getString("zlfh"):"");
		        bean.setJlfh(rs.getString("jlfh")!=null?rs.getString("jlfh"):"");
		        bean.setDfzflxcode(rs.getString("dfzflxa")!=null?rs.getString("dfzflxa"):"");
		        bean.setStationtypecode(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
		        bean.setGdfscode(rs.getString("gdfs")!=null?rs.getString("gdfs"):"");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  public synchronized AmmeterDegreeFormBean getEndmonthOne(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		  String sql="";
		    DataBase db = new DataBase();
		    try {
		    	sql=" SELECT TO_CHAR(MAX(PR.ENDMONTH),'yyyy-mm') ENDMONTH FROM YUFUFEIVIEW PR, DIANBIAO D, ZHANDIAN ZD "+
                " WHERE ZD.ID = D.ZDID  AND D.DBID = PR.PREPAYMENT_AMMETERID AND PR.FINANCEAUDIT = '2' AND ZD.QYZT = '1' AND D.DBQYZT = '1' AND D.DFZFLX = 'dfzflx02'  AND D.DBID = '"+ammeterid+"'";
		    	System.out.println("电量列表预付费管理合同单--添加预付费带出来的起始年月:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		    rs=db.queryAll(sql);
		      while (rs.next()) {
                      bean.setEndmonth(rs.getString("ENDMONTH"));
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //分摊专业占比查询
	  public synchronized ArrayList getStationMhchexkt(String ammeterid) {
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		  
		    sql.append("SELECT SB.SHUOSHUZHUANYE, SUM(SB.DBILI * SB.XJBILI / 100)dbili FROM SBGL SB WHERE DIANBIAOID='"+ammeterid+"' GROUP BY SB.SHUOSHUZHUANYE");
		   // String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("分摊专业占比查询:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		    	 Query query = new Query();
				list = query.query(rs);		   
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }
	  
	  //分摊专业占比详细查询
	  public synchronized ArrayList getStationMhchexktxjbl(String ammeterid){
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		  
		    sql.append("SELECT SB.SHUOSHUZHUANYE,SB.DBILI,SB.QCBCODE,SB.KJKMCODE,SB.ZYMXCODE,SB.XJBILI FROM SBGL SB WHERE DIANBIAOID='"+ammeterid+"'");
		    //String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("分摊专业占比详细查询:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      
		    	 Query query = new Query();
				list = query.query(rs);
		    	
		   
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }
	  //
	  
	  //合同单获取初始电表读数
	  public synchronized AmmeterDegreeFormBean getChuShiDuShuDegree(String ammeterid) {
		  AmmeterDegreeFormBean bean=new AmmeterDegreeFormBean();
		  StringBuffer sql = new StringBuffer();
		  sql.append("select db.csds,db.cssytime from dianbiao db where db.dbid='"+ammeterid+"' and db.dbyt = 'dbyt01'");
		  DataBase db = new DataBase();
		  try{
			  System.out.println("合同单获取初始电表读数:"+sql);
			  db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      if(rs.next()){
		    	  bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		    	  bean.setCssytime(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		      }
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
	  //管理列表用 初始
	  
	  public synchronized AmmeterDegreeFormBean getChuShiDuShuDegreeGL(String ammeterid) {
		  AmmeterDegreeFormBean bean=new AmmeterDegreeFormBean();
		  StringBuffer sql = new StringBuffer();
		  sql.append("select db.csds,db.cssytime from dianbiao db where db.dbid='"+ammeterid+"' and db.dbyt = 'dbyt03'");
		  DataBase db = new DataBase();
		  try{
			  System.out.println("电量列表--添加电量带出数据:"+sql);
			  db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      if(rs.next()){
		    	  bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		    	  bean.setCssytime(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		      }
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
    //电量列表管理列表使用 电表读数
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegreeGl(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("select to_char(t1.lastdatetime,'yyyy-mm-dd') lastdatetime,t1.ammeterid_fk ,d.thisdegree lastdegree from (select  max(t.thisdatetime) lastdatetime, t.ammeterid_fk  from dianduview t where  t.manualauditstatus='1'and AMMETERID_FK = '"+ammeterid+"' group by t.ammeterid_fk)t1,dianduview d where t1.ammeterid_fk=d.ammeterid_fk and t1.lastdatetime=d.thisdatetime");
		    //sql.append("select max(to_number(t.thisdegree)) lastdegree,max(t.thisdatetime) lastdatetime from ammeterdegrees t where t.manualauditstatus>='1' and AMMETERID_FK='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("电量列表--添加电量带出来的信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      
		      if(rs.next()==true){
		      
		    	//站点信息
		        bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
		        bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
		       
		       
		      }
		      else{
		    	  AmmeterDegreeFormBean beancs = new AmmeterDegreeFormBean();
		    	  beancs= bean.getChuShiDuShuDegreeGL(ammeterid);
		    	    bean.setLastdegree(beancs.getCsds() != null ? beancs.getCsds() : "");
			        bean.setLastdatetime(beancs.getCssytime() != null ? beancs.getCssytime() : "");
			       
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //获取初始电表读数
	  public synchronized AmmeterDegreeFormBean getChuShiDuShuDegreeYF(String ammeterid) {
		  AmmeterDegreeFormBean bean=new AmmeterDegreeFormBean();
		  StringBuffer sql = new StringBuffer();
		  sql.append("SELECT y.csds,to_char(y.cssytime,'yyyy-mm-dd') cssytime  FROM dianbiao y  WHERE  y.dbid = '"+ammeterid+"'");
		  DataBase db = new DataBase();
		  try{
			  System.out.println("getChuShiDuShuDegree:"+sql);
			  db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      if(rs.next()){
		    	  bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		    	  bean.setCssytime(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		      }
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }

	  //添加预付费--带出信息查询
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegreeYf(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("select to_char(s.lastdatetime,'yyyy-mm-dd') as lastdatetime ,y.stopdegree as lastdegree " +
		    		" from (select max(t.enddate) lastdatetime, " +
		    		"t.prepayment_ammeterid from yufufeiview t where t.PREPAYMENT_AMMETERID = '"+ammeterid+"' and  t.cityaudit='1'"+
		    		" group by t.prepayment_ammeterid )s ,yufufeiview y where y.prepayment_ammeterid=s.prepayment_ammeterid and s.lastdatetime=y.enddate");
		    //sql.append("select max(to_number(t.thisdegree)) lastdegree,max(t.thisdatetime) lastdatetime from ammeterdegrees t where t.manualauditstatus>='1' and AMMETERID_FK='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("添加预付费--带出信息查询:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      
		      if(rs.next()){
		      
		    	//站点信息
		        bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
		        bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
		
		      }
		      else{
		    	  AmmeterDegreeFormBean beancs = new AmmeterDegreeFormBean();
		    	  beancs= bean.getChuShiDuShuDegreeYF(ammeterid);
		    	    bean.setLastdegree(beancs.getCsds() != null ? beancs.getCsds() : "");
			        bean.setLastdatetime(beancs.getCssytime() != null ? beancs.getCssytime() : "");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  
	  //添加预付费--带出信息查询(上期流程号、上期电量、上期电费、上期余额)
	  public synchronized AmmeterDegreeFormBean getLast(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		   
		    sql.append("select s.dianliang,s.liuchenghao,s.money,"+
		    		" round(((select nvl(sum(yf.money),0) from yufufeiview yf where yf.financeaudit = '2' and yf.prepayment_ammeterid = '"+ammeterid+"')-(select nvl(sum(df.actualpay),0) from dianfeidan df where df.MANUALAUDITSTATUS = '2'and  df.ammeterid_fk = '"+ammeterid+"')),2) as ye "+
		    		" from (select to_char(max(t.enddate),'yyyy-mm-dd') lastdatetime,t.dianliang,t.liuchenghao,t.money," +
		    		" t.prepayment_ammeterid from yufufeiview t where t.PREPAYMENT_AMMETERID = '"+ammeterid+"' and t.financeaudit = '2' "+
		    		" group by t.prepayment_ammeterid, t.dianliang,t.liuchenghao,t.money) s,zhandian z,dianbiao d " +
		    		"where z.id = d.zdid and d.dbid = s.prepayment_ammeterid(+) and d.dbid = '"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	  System.out.println("添加预付费--带出上期信息查询don:"+sql);
			      db.connectDb();
			      ResultSet rs = null;
			      
			      rs = db.queryAll(sql.toString());
			      
			      if(rs.next()){
			      
			        bean.setLastdl(rs.getString("dianliang") != null ? rs.getString("dianliang") : "");
			        bean.setLastlch(rs.getString("liuchenghao") != null ? rs.getString("liuchenghao") : "");
			        bean.setLastdf(rs.getString("money") != null ? rs.getString("money") : "");
			        bean.setLastyue(rs.getString("ye") != null ? rs.getString("ye") : "");
			        System.out.println(rs.getString("ye")+"   fsfsfffffffffffffffffffffffffff");
			      }else{
				        bean.setLastdl("");
				        bean.setLastdf("");
				        bean.setLastyue("");
				        bean.setLastlch("");
			      }
			      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  
	  
	//合同单获得最近的电表读数
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegree(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("select to_char(s.thisdatetime,'yyyy-mm-dd') as lastdatetime,s.thisdegree as lastdegree " +
		    		",s.actualdegree ydl,to_char(s.thisdatetime,'yyyy-mm-dd') sccbsj,e.actualpay actualpay" +
		    		",to_char(e.entrytime,'yyyy-mm-dd') entrytime" +
		    		",zd.pue pue" +
		    		",zd.jlfh jlfh" +
		    		",zd.zlfh zlfh" +
		    		" from(select max(t.thisdatetime) lastdatetime,t.ammeterid_fk  from dianduview t,dianfeiview d where t.ammeterdegreeid=d.ammeterdegreeid_fk and d.manualauditstatus='1'and AMMETERID_FK = '"+ammeterid+"' group by t.ammeterid_fk) a,dianduview s,dianfeiview e,dianbiao db,zhandian zd where s.ammeterid_fk=a.ammeterid_fk and zd.qyzt='1' and s.thisdatetime=a.lastdatetime and s.ammeterdegreeid = e.ammeterdegreeid_fk and s.ammeterid_fk = db.dbid  and db.zdid = zd.id");
		    //sql.append("select max(to_number(t.thisdegree)) lastdegree,max(t.thisdatetime) lastdatetime from ammeterdegrees t where t.manualauditstatus>='1' and AMMETERID_FK='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("合同单获得最近的电表读数:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      
		      if(rs.next()==true){
		      
		    	//站点信息
		        bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
		        bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
		        bean.setYdl(rs.getString("ydl") != null ? rs.getString("ydl") : "");
		        bean.setSccbsj(rs.getString("sccbsj") != null ? rs.getString("sccbsj") : "");
		        bean.setActualpay(rs.getString("actualpay") != null ? rs.getString("actualpay") : "");
		       
		        bean.setZlfh(rs.getString("zlfh") != null ? rs.getString("zlfh") : "");		        
		        bean.setJlfh(rs.getString("jlfh") != null ? rs.getString("jlfh") : "");
		        bean.setPue(rs.getString("pue") != null ? rs.getString("pue") : "");
		        bean.setEntrytime(rs.getString("entrytime") != null ? rs.getString("entrytime") : "");    
		       
		      }
		      else{
		    	  AmmeterDegreeFormBean beancs = new AmmeterDegreeFormBean();
		    	  beancs= bean.getChuShiDuShuDegree(ammeterid);
		    	    bean.setLastdegree(beancs.getCsds() != null ? beancs.getCsds() : "");
			        bean.setLastdatetime(beancs.getCssytime() != null ? beancs.getCssytime() : "");
			        bean.setYdl("");
			        bean.setSccbsj("");
			        bean.setActualpay("");
			       
			        bean.setZlfh("");		        
			        bean.setJlfh("");
			        bean.setPue("");
			        bean.setEntrytime("");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  
	  //获得最近的电表读数
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegree1(String ammeterid) throws ParseException {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("SELECT TO_CHAR(S.THISDATETIME,'yyyy-mm-dd') AS LASTDATETIME,S.THISDEGREE AS LASTDEGREE " +
		    		",S.ACTUALDEGREE YDL,TO_CHAR(S.LASTDATETIME,'yyyy-mm-dd') SCCBSJ,E.ACTUALPAY ACTUALPAY" +
		    		",TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd') ENTRYTIME" +
		    		",E.PJJE "+
		    		",ZD.PUE PUE" +
		    		",ZD.JLFH JLFH" +
		    		",ZD.ZLFH ZLFH" +
		    		" FROM(SELECT  TO_CHAR(MAX(T.THISDATETIME),'YYYY-MM-DD') LASTDATETIME,T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK AND D.MANUALAUDITSTATUS>='1'AND AMMETERID_FK = '"+ammeterid+"' GROUP BY T.AMMETERID_FK) A,DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD WHERE S.AMMETERID_FK=A.AMMETERID_FK AND TO_CHAR(S.THISDATETIME,'yyyy-mm-dd')=A.LASTDATETIME AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID");
		    //sql.append("select max(to_number(t.thisdegree)) lastdegree,max(t.thisdatetime) lastdatetime from ammeterdegrees t where t.manualauditstatus>='1' and AMMETERID_FK='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getLastAmmeterDegree1:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      
		      if(rs.next()==true){
		      
		    	//站点信息
		        bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
		        bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "");
		        bean.setYdl(rs.getString("ydl") != null ? rs.getString("ydl") : "");
		        bean.setSccbsj(rs.getString("sccbsj") != null ? rs.getString("sccbsj") : "");
		        bean.setActualpay(rs.getString("actualpay") != null ? rs.getString("actualpay") : "");
		        bean.setPjje(rs.getDouble("pjje"));
//		        bean.setZlfh(rs.getString("zlfh") != null ? rs.getString("zlfh") : "");		        
//		        bean.setJlfh(rs.getString("jlfh") != null ? rs.getString("jlfh") : "");
		        bean.setPue(rs.getString("pue") != null ? rs.getString("pue") : "");
		        bean.setEntrytime(rs.getString("entrytime") != null ? rs.getString("entrytime") : "");
		    
		        
		       
		      }
		      else{
		    	  AmmeterDegreeFormBean beancs = new AmmeterDegreeFormBean();
		    	  beancs= bean.getChuShiDuShuDegree(ammeterid);
		    	    bean.setLastdegree(beancs.getCsds() != null ? beancs.getCsds() : "");
		    	    String lastadtime=beancs.getCssytime();
		    	    if(!"".equals(lastadtime)&&lastadtime!=null){
		    	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	                    String str=lastadtime;
	                    Date dt=sdf.parse(str);
	                    Calendar rightNow = Calendar.getInstance();
	                    rightNow.setTime(dt);
	                    //rightNow.add(Calendar.YEAR,-1);//日期减1年
	                    //rightNow.add(Calendar.MONTH,3);//日期加3个月
	                    rightNow.add(Calendar.DAY_OF_YEAR,-1);//日期加1天
	                    Date dt1=rightNow.getTime();
	                    lastadtime = sdf.format(dt1);
	                    bean.setLastdatetime(lastadtime);
		    	    }
		    	    else{
                     
			        bean.setLastdatetime(beancs.getCssytime() != null ? beancs.getCssytime() : "");}
			        bean.setYdl("");
			        bean.setSccbsj("");
			        bean.setActualpay("");
//			       
//			        bean.setZlfh("");		        
//			        bean.setJlfh("");
			        bean.setPue("");
			        bean.setEntrytime("");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //获取管理电表的最近一次的抄表读数
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegree2(String zdcode) throws ParseException {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    DataBase db = new DataBase();
		    String dbid="";
		    //to_number(t.thisdegree) 转换成数字
		    String sql1="SELECT DBID FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBYT='dbyt03' AND Z.ZDCODE='"+zdcode+"'";
		    try {
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql1.toString());
		      if(rs.next()==true){
		         dbid=rs.getString("dbid");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    sql.append("SELECT AM.THISDEGREE,TO_CHAR(DD.THISDATETIME,'yyyy-mm-dd'),AM.AMMETERID_FK FROM ZHANDIAN Z,  DIANBIAO D, AMMETERDEGREES AM, "+
		    			"(SELECT TO_CHAR(MAX(T.THISDATETIME), 'YYYY-MM-DD') THISDATETIME,T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.AMMETERID_FK ='"+dbid+"'  GROUP BY AMMETERID_FK) DD"+
		    			" WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID  AND D.DBYT = 'dbyt03'");
		   
		    try {
		    	System.out.println("获取最近日期电表读数getLastAmmeterDegree2:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      if(rs.next()==true){
		        bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs.getString("thisdatetime") : "");
		        bean.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
		      }
		    
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	//获得最近的日耗电量
	  public synchronized long getLastDayAmmeterDegree(String ammeterid,String lasttime) {
		  //PrepaymentFormBean bean = new PrepaymentFormBean();
		    long daydegree =0;
		    StringBuffer sql = new StringBuffer();
		    //sql.append("select  case when  (to_date(ad.thisdatetime,'YYYY-MM-DD')-to_date(ad.lastdatetime,'YYYY-MM-DD')) != '0'then  ad.actualdegree/(to_date(ad.thisdatetime,'YYYY-MM-DD')-to_date(ad.lastdatetime,'YYYY-MM-DD')) else 1 end daydegree" +
		    		//" from ammeterdegrees ad where ad.thisdatetime = (select max(ad.thisdatetime)  from ammeterdegrees ad where ad.manualauditstatus='1' and ad.Autoauditstatus='1'and ad.ammeterid_fk='"+ammeterid+"')");
		    //sql.append("SELECT CASE WHEN (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')) != '0' THEN AD.blhdl / (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')) ELSE   1 END DAYDEGREE" +
		    	//	" FROM dianduview AD,(SELECT MAX(dD.THISDATETIME)AS THISDATETIME ,dd.ammeterdegreeid  FROM dianduview dd,dianfeiview e  WHERE  e.manualauditstatus='1' AND e.ammeterdegreeid_fk=dd.ammeterdegreeid AND dD.AMMETERID_FK = '"+ammeterid+"'   GROUP  BY dd.ammeterdegreeid)aad " +
		    	//	"WHERE AD.THISDATETIME =aad.THISDATETIME AND aad.ammeterdegreeid=ad.ammeterdegreeid");
		    sql.append("SELECT CASE WHEN (AD.THISDATETIME - AD.LASTDATETIME) != 0 THEN AD.blhdl / (AD.THISDATETIME - AD.LASTDATETIME+1) ELSE   1 END DAYDEGREE" +
		    		" FROM dianduview AD " +
		    		"WHERE TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') ='"+lasttime+"' AND AD.AMMETERID_FK='"+ammeterid+"'");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getLastDayAmmeterDegree:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		    	 // daydegree = rs.getLong("daydegree");
		    	  daydegree=rs.getLong(1);
		    	  //System.out.println("daydegree:"+daydegree);	     
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return daydegree;
		  }
	  //修改调用
	  public synchronized AmmeterDegreeFormBean getLastAmmeterModfiyDegree(String ammeterid,String degreeid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select max(thisdegree) lastdegree from (select t.thisdegree,row_number() over(order by t.thisdegree desc) m from ammeterdegrees t where t.manualauditstatus='1' and  t.Autoauditstatus='1' and AMMETERID_FK='"+ammeterid+"') where m>(select num from (select rownum num, mmm.* from (select t.ammeterdegreeid, thisdegree from ammeterdegrees t where t.manualauditstatus='1' and  t.Autoauditstatus='1'and AMMETERID_FK = '"+ammeterid+"' order by t.thisdegree desc) mmm) mm where mm.ammeterdegreeid = '"+degreeid+"')" );
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getLastAmmeterModfiyDegree:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//站点信息
		        bean.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "0");		        		     
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //电量列表--添加电量获取电表初始值
	  public synchronized AmmeterDegreeFormBean getStartAmmeterModfiyDegree(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select distinct t.csds,to_char(t.cssytime,'yyyy-mm-dd') cssytime from dianbiao t where t.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("电量列表--添加电量获取电表初始值:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		        bean.setInitialdegree(rs.getString("csds") != null ? rs.getString("csds") : "0");		        		     
		        bean.setInitialdate(rs.getString("cssytime") != null ? rs.getString("cssytime") : "0");		        		     
			      
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	//获得最近的日耗电量
	  public synchronized long getLastDayAmmeterModfiyDegree(String ammeterid,String degreeid) {
		  //PrepaymentFormBean bean = new PrepaymentFormBean();
		    long daydegree = 0;
		    StringBuffer sql = new StringBuffer();
		    sql.append("select  case when  (to_date(ad.thisdatetime,'YYYY-MM-DD')-to_date(ad.lastdatetime,'YYYY-MM-DD')) != '0'then  ad.actualdegree/(to_date(ad.thisdatetime,'YYYY-MM-DD')-to_date(ad.lastdatetime,'YYYY-MM-DD')) else 0 end daydegree" +
		    		" from ammeterdegrees ad where ad.thisdatetime = (select max(thisdatetime) from (select t.thisdatetime,row_number() over(order by t.thisdatetime desc) m from ammeterdegrees t where t.manualauditstatus = '1' and t.Autoauditstatus = '1' and AMMETERID_FK='"+ammeterid+"') where m>(select num from (select rownum num, mmm.* from (select t.ammeterdegreeid, thisdegree from ammeterdegrees t where t.manualauditstatus = '1' and t.Autoauditstatus = '1' and AMMETERID_FK = '"+ammeterid+"' order by t.thisdegree desc) mmm) mm where mm.ammeterdegreeid = '"+degreeid+"'))");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getLastDayAmmeterModfiyDegree:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		    	  daydegree = rs.getLong("daydegree");
		    	  System.out.println("daydegree:"+daydegree);	     
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return daydegree;
		  }
	  
//	public synchronized BargainBillCountBean getCount(String ammeterid) {
//		BargainBillCountBean bbc = null;
//		    String sql = "select zd.edhdl,zd.qsdbdl,d.danjia " +
//		    		"from dianbiao d,zhandian zd " +
//		    		" where d.zdid=zd.id and zd.qyzt='1' and d.dbid=? group by zd.edhdl,zd.qsdbdl,d.danjia";
//		    
//		    DataBase db = new DataBase();
//		    Connection conn = null;
//		    PreparedStatement ps = null;
//		    ResultSet rs = null;
//		    try {
//		    	System.out.println("电量列表预付费管理合同单--添加预付费带出来的全省定标电量、额定耗电量信息:"+sql);
//		    	System.out.println("查询的ID:"+ammeterid);
//		      conn = db.getConnection();
//		      ps = conn.prepareStatement(sql);
//		      
//		      ps.setString(1, ammeterid);
//		      
//		      rs = ps.executeQuery();
//		      while (rs.next()) {
//		    	  String ed = rs.getString(1)!=null?rs.getString(1):"";
//		    	  String qs = rs.getString(2)!=null?rs.getString(2):"";
//		    	  String dianfei = rs.getString(3)!=null?rs.getString(3):"0";
//		    	  if("".equals(dianfei)||" ".equals(dianfei)){
//		    		  dianfei="0.00";
//		    	  }
//		    	  
//		    	   bbc = new BargainBillCountBean(qs,ed,dianfei);
//		    	   break;
//		      }
//		      
//		    }
//		    catch (Exception de) {
//		      de.printStackTrace();
//		    }
//		    finally {
//		    	db.free(rs, ps, conn);
//		    }
//		    return bbc;
//		  }
	  
	//获得查询日期内的日耗电量（王代军）
	public synchronized ArrayList getCheckDayAmmeterDegree(String ammeterid,String beginTime,String endTime) {
		  List list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		   // AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,ACTUALDEGREE
		    
		    sql.append("select thisdatetime ,actualdegree from ammeterdegrees  where ammeterid_fk='"+ammeterid+"' and thisdatetime>='"+beginTime+"' and thisdatetime<='"+endTime+"' order by thisdatetime");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getDegree:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	 String temp=rs.getString("thisdatetime").substring(5, 10).replace('-', '月')+"日,"+rs.getString("actualdegree");
		    	  if(!",".equals(temp)){
		    		  System.out.println(temp);
		    	   list.add(temp); 
		    	   }
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		    	se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return (ArrayList) list;
		  }
	  //获得付款周期
	  public String getPrepayZq(String ammeterid) {
			//AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    String payzq= "0";
		    sql.append("select zf.id,zf.fkzq,ind.name from zddfinfo zf,indexs ind,dianbiao db  where zf.fkzq=ind.code and ind.type='FKZQ' and db.zdid=zf.zdid and db.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("获得付款周期:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		
		      while (rs.next()) {
		    	//付款周期
		        payzq=rs.getString("name") != null ? rs.getString("name") : "";	
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return payzq;
		  }
	public String getAmmeterid() {
		return ammeterid;
	}

	public void setAmmeterid(String ammeterid) {
		this.ammeterid = ammeterid;
	}

	public String getElectriccurrenttype_ammeter() {
		return electriccurrenttype_ammeter;
	}

	public void setElectriccurrenttype_ammeter(String electriccurrenttype_ammeter) {
		this.electriccurrenttype_ammeter = electriccurrenttype_ammeter;
	}

	public String getUsageofelectypeid_ammeter() {
		return usageofelectypeid_ammeter;
	}

	public void setUsageofelectypeid_ammeter(String usageofelectypeid_ammeter) {
		this.usageofelectypeid_ammeter = usageofelectypeid_ammeter;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getStationtypeid() {
		return stationtypeid;
	}

	public void setStationtypeid(String stationtypeid) {
		this.stationtypeid = stationtypeid;
	}

	public String getIsbenchmarkstation() {
		return isbenchmarkstation;
	}

	public void setIsbenchmarkstation(String isbenchmarkstation) {
		this.isbenchmarkstation = isbenchmarkstation;
	}

	public String getStationaliasname() {
		return stationaliasname;
	}

	public void setStationaliasname(String stationaliasname) {
		this.stationaliasname = stationaliasname;
	}

	public String getAd2_bz() {
		return ad2_bz;
	}

	public void setAd2_bz(String ad2_bz) {
		this.ad2_bz = ad2_bz;
	}

	public String getInitialdegree() {
		return initialdegree;
	}

	public void setInitialdegree(String initialdegree) {
		this.initialdegree = initialdegree;
	}

	public String getInitialdate() {
		return initialdate;
	}

	public void setInitialdate(String initialdate) {
		this.initialdate = initialdate;
	}

	public String getPayzq() {
		return payzq;
	}

	public void setPayzq(String payzq) {
		this.payzq = payzq;
	}

	public String getAmmeteruse() {
		return ammeteruse;
	}

	public void setAmmeteruse(String ammeteruse) {
		this.ammeteruse = ammeteruse;
	}

	public String getMultiplyingpower() {
		return multiplyingpower;
	}

	public void setMultiplyingpower(String multiplyingpower) {
		this.multiplyingpower = multiplyingpower;
	}

	public String getAd2_bz1() {
		return ad2_bz1;
	}

	public void setAd2_bz1(String ad2_bz1) {
		this.ad2_bz1 = ad2_bz1;
	}


	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStationid() {
		return stationid;
	}


	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}

	public String getDfzflx() {
		return dfzflx;
	}

	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}

	public String getEntrypensonnel() {
		return entrypensonnel;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}

	public String getEdhdl() {
		return edhdl;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getGdfs() {
		return gdfs;
	}

	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}

}