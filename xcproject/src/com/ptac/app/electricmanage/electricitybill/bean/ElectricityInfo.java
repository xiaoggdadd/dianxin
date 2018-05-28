package com.ptac.app.electricmanage.electricitybill.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

/**
 * @author lijing
 * @see 电费单添加页面电费信息所需字段
 */
public class ElectricityInfo {
	
	private String dbid;//电表ID

	//电量表
 
	private String ammeterdegree_id;//电量id
	private String lastdegree;//上次电表读数
	private String thisdegree;//本次电表读数
	private String actualdegree;//用电量
	private String lastdatetime;//上次抄表时间+1	
	private String lasttime;//本次抄表时间（最大的本次抄表时间 不加 1）
	private String thisdatetime;//本次抄表时间
	private String floatdegree;//用电量调整	
	private String startmonth;//起始年月
	private String blhdl;//实际用电量(倍率耗电量)
	private String memo;//(电量)备注	
	private String endmonth;//结束年月
    private String entrypensonnel;
    private String entrytime;
    private String dedhdl;//额定耗电量超标比值
    private String csdb;//超省定标比例值
    private String scdf;//上次电费
    private String scdl;//上次电量
    private String lastunitprice;//上次单价
    private String manualauditname;//人工审核人（审核人）
    private String dlinputoperator;//电量表抄表操作员
    private String pjdl;//票据电量
    private String tbtzsq;//特别调整申请
    private String dbydl;//电表用电量2014-10-24 WangYiXiao
	
	//电费表	    		
	private String electricfee_id;//电费id;
    private String kptime;
	private String unitprice;//本次单价
	private String floatpay;//费用调整
	private String memo1;//(电费)备注
	private String accountmonth;//报账月份
	private double actualpay;//实际电费金额
	private String notetypeid;//票据类型	
	private String noteno;//票据编号
	private String notetime;//票据时间
	private String inputoperator;//抄表操作员
	private String paydatetime;//交费时间
	private String payoperator;//交费操作员
	private double pjje;//票据金额
	private int flag;
	private String csds;
	private String cssytime;
	private String liuchenghao;//预支型流程号
	private String yddf;//用电电费
	private String jfzb;//尖峰占比
	private String bfzb;//波峰占比
	private String bpzb;//波平占比
	private String bgzb;//波谷占比
	//2014-7-17
	private String stationtypecode;//站点类型code
	private String propertycode;//站点属性code
	private String dfzflxcode;//电费支付类型code
	private String gdfscode;//供电方式code
	private String zlfh;//直流负荷
	private String jlfh;//交流负荷
	private String beilv;//倍率
	private String changevalue;//变损值
	private String linelosstype;//线损类型
	private String linelossvalue;//线损值
	private String actuallinelossvalue;//实际线损值
	private String scb;//生产标2-14-10-22

	//站点表
	private String edhdl;//额定耗电量
	private String qsdbdl;//全省定标电量
	private String shi;//地市 用于是否是济宁判断
	
	private boolean gldlbz;//管理电量标识
	private String zdname;//站点名称
	
	
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public ElectricityInfo(){
		
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbid() {
		return dbid;
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
	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
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
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public double getActualpay() {
		return actualpay;
	}
	public void setActualpay(double actualpay) {
		this.actualpay = actualpay;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getInputoperator() {
		return inputoperator;
	}
	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
	}
	public String getPayoperator() {
		return payoperator;
	}
	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}
	public double getPjje() {
		return pjje;
	}
	public void setPjje(double pjje) {
		this.pjje = pjje;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
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
	
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
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
	public synchronized ElectricityInfo getChuShiDuShuDegree(String ammeterid) {
		ElectricityInfo bean=new ElectricityInfo();
		  StringBuffer sql = new StringBuffer();
		  sql.append("select db.csds,to_char(db.cssytime,'yyyy-mm-dd') cssytime from dianbiao db where db.dbid='"+ammeterid+"' and db.dbyt = 'dbyt01'");
		  DataBase db = new DataBase();
		  try{
			  System.out.println("获取初始电表读数:");
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
	public void setKptime(String kptime) {
		this.kptime = kptime;
	}
	public String getKptime() {
		return kptime;
	}
	public String getLiuchenghao() {
		return liuchenghao;
	}
	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}
	public void setAmmeterdegree_id(String ammeterdegree_id) {
		this.ammeterdegree_id = ammeterdegree_id;
	}
	public String getAmmeterdegree_id() {
		return ammeterdegree_id;
	}
	public void setElectricfee_id(String electricfee_id) {
		this.electricfee_id = electricfee_id;
	}
	public String getElectricfee_id() {
		return electricfee_id;
	}
	public String getScdf() {
		return scdf;
	}
	public void setScdf(String scdf) {
		this.scdf = scdf;
	}
	public String getScdl() {
		return scdl;
	}
	public void setScdl(String scdl) {
		this.scdl = scdl;
	}
	public String getLastunitprice() {
		return lastunitprice;
	}
	public void setLastunitprice(String lastunitprice) {
		this.lastunitprice = lastunitprice;
	}
	public String getManualauditname() {
		return manualauditname;
	}
	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}
	public String getDlinputoperator() {
		return dlinputoperator;
	}
	public void setDlinputoperator(String dlinputoperator) {
		this.dlinputoperator = dlinputoperator;
	}
	public String getPjdl() {
		return pjdl;
	}
	public void setPjdl(String pjdl) {
		this.pjdl = pjdl;
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
	public String getJfzb() {
		return jfzb;
	}
	public void setJfzb(String jfzb) {
		this.jfzb = jfzb;
	}
	public String getBfzb() {
		return bfzb;
	}
	public void setBfzb(String bfzb) {
		this.bfzb = bfzb;
	}
	public String getBpzb() {
		return bpzb;
	}
	public void setBpzb(String bpzb) {
		this.bpzb = bpzb;
	}
	public String getBgzb() {
		return bgzb;
	}
	public void setBgzb(String bgzb) {
		this.bgzb = bgzb;
	}
	public boolean isGldlbz() {
		return gldlbz;
	}
	public void setGldlbz(boolean gldlbz) {
		this.gldlbz = gldlbz;
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
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getChangevalue() {
		return changevalue;
	}
	public void setChangevalue(String changevalue) {
		this.changevalue = changevalue;
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
	public String getActuallinelossvalue() {
		return actuallinelossvalue;
	}
	public void setActuallinelossvalue(String actuallinelossvalue) {
		this.actuallinelossvalue = actuallinelossvalue;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
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
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}

	
	
}
