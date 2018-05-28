package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

/**
 * Electricfees entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrepaymentFormBean implements java.io.Serializable {

	// Fields

	private String Id;
	private String stationid;
	private String feetypeid;
	private String ammeterdegreeidFk;
	private String prepaymentammeterid;
	private String money;
	private String startdegree;
	private String startdate;
	private String enddate;		
	private String inputoperator;
	private String inputdatetime;
	private String auditdate;
	private String auditoperator;	
	private String financialoperator;
	private String financialdatetime;
	private String memo;
	private String dayfee;
	private String stopdegree;
	private String sumdegree;
	private String networkdf;
	private String  informatizationdf;
	private String administrativedf;
	private String  marketdf;
	private String builddf;
	private String dddf;
	
   private String htbh;
   private String jzname;
   private String dbname;
   private String szdq;
   private String stationtype;
   private double jszq;//结算周期
   private String qsdbdlbz;//超省标比值
   private String dedhdlbz;//超本地标比值
   private String edhdl;//额定耗电量
   private String qsdbdl;//全省定标电量
   
   private String lastdl;//上期电量
   private String lastdf;//上期电费
   private String lastlch;//上期预付费流程号
   private String lastyue;//上期余额
   
   private String beilv;//倍率
   private String dbydl;//电表用电量
   private String shicode;//市
   private String property;//站点属性
   private String zlfh;//直流负荷
   private String jlfh;//交流负荷
   private String scb;//生产标
   private String dfzflxcode;//电费支付类型code
   private String gdfscode;//供电方式code
   private String stationtypecode;//站点类型code
   private String propertycode;//站点属性code
   
   
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
public String getShicode() {
	return shicode;
}
public void setShicode(String shicode) {
	this.shicode = shicode;
}
public String getProperty() {
	return property;
}
public void setProperty(String property) {
	this.property = property;
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
public String getBeilv() {
	return beilv;
}
public void setBeilv(String beilv) {
	this.beilv = beilv;
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
public String getQsdbdlbz() {
	return qsdbdlbz;
}
public void setQsdbdlbz(String qsdbdlbz) {
	this.qsdbdlbz = qsdbdlbz;
}
public String getDedhdlbz() {
	return dedhdlbz;
}
public void setDedhdlbz(String dedhdlbz) {
	this.dedhdlbz = dedhdlbz;
}
public double getJszq() {
	return jszq;
}
public void setJszq(double jszq) {
	this.jszq = jszq;
}
public String getDddf() {
	return dddf;
}
public void setDddf(String dddf) {
	this.dddf = dddf;
}

private String dflx;
	public String getStationtype() {
	return stationtype;
}
public void setStationtype(String stationtype) {
	this.stationtype = stationtype;
}
public String getDflx() {
	return dflx;
}
public void setDflx(String dflx) {
	this.dflx = dflx;
}
	public String getDbname() {
	return dbname;
}
public void setDbname(String dbname) {
	this.dbname = dbname;
}
public String getSzdq() {
	return szdq;
}
public void setSzdq(String szdq) {
	this.szdq = szdq;
}
	public String getJzname() {
	return jzname;
}
public void setJzname(String jzname) {
	this.jzname = jzname;
}
	public String getHtbh() {
	return htbh;
}
public void setHtbh(String htbh) {
	this.htbh = htbh;
}

	private String notetypeid;
	private String noteno;
	private String notetime;
	private String kptime;
	private String payoperator;
	private String paydatetime;
	private String accountmonth;
	
	private String startmonth;
	private String endmonth;
	private String entrypensonnel;
	private String entrytime;
	private String actualdegree;//实际用电量
	private double pjje;
	
	//站点信息
    private String stationname;
    private String zdcode;
    private String provinceid;
    private String cityid;
    private String countryid;
    private String stationtypeid;
    private String isbenchmarkstation;
    private String stationaliasname;
    private String sysprice;
    private String dianfei;
    private String uuid;
	// Constructors
    private String pjlx;//票据类型
    private String fpje;//补发票金额   
    private String fpsh;//补录发票审核状态
	public String getPjlx() {
		return pjlx;
	}
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	public String getFpje() {
		return fpje;
	}
	public void setFpje(String fpje) {
		this.fpje = fpje;
	}
	public String getFpsh() {
		return fpsh;
	}
	public void setFpsh(String fpsh) {
		this.fpsh = fpsh;
	}
	public String getSumdegree() {
		return sumdegree;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public double getPjje() {
		return pjje;
	}

	public void setPjje(double pjje) {
		this.pjje = pjje;
	}

	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	public String getNetworkdf() {
		return networkdf;
	}
	public void setNetworkdf(String networkdf) {
		this.networkdf = networkdf;
	}
	public String getInformatizationdf() {
		return informatizationdf;
	}
	public void setInformatizationdf(String informatizationdf) {
		this.informatizationdf = informatizationdf;
	}
	public String getAdministrativedf() {
		return administrativedf;
	}
	public void setAdministrativedf(String administrativedf) {
		this.administrativedf = administrativedf;
	}
	public String getMarketdf() {
		return marketdf;
	}
	public void setMarketdf(String marketdf) {
		this.marketdf = marketdf;
	}
	public String getBuilddf() {
		return builddf;
	}
	public void setBuilddf(String builddf) {
		this.builddf = builddf;
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




	/** default constructor */
	public PrepaymentFormBean() {
	}

	
	
	
	/**
	   * 预付费修改
	   * @param accountId String
	   * @return AccountFormBean
	   */
	  public synchronized PrepaymentFormBean getPrepaymentInfo(String prepayId) {
		  PrepaymentFormBean bean = new PrepaymentFormBean();
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT Y.AMMETERDEGREEID_FK,Y.PREPAYMENT_AMMETERID,Y.AUDITDATE,Y.AUDITOPERATOR,TO_CHAR(Y.ENDDATE,'yyyy-mm-dd') ENDDATE,Y.FEETYPEID,TO_CHAR(Y.FINANCIALDATE,'yyyy-mm-dd') FINANCIALDATE,Y.financialoperator," +
	    		"Y.ID,TO_CHAR(Y.inputdatetime,'yyyy-mm-dd') INPUTDATETIME,Y.inputoperator,Y.MEMO,Y.MONEY,TO_CHAR(Y.STARTDATE,'yyyy-mm-dd') STARTDATE,Y.STARTDEGREE,Y.stopdegree,Y.pjje,TO_CHAR(Y.startmonth,'yyyy-mm') startmonth,TO_CHAR(Y.endmonth,'yyyy-mm') ENDMONTH,Y.notetypeid,Y.noteno,TO_CHAR(Y.notetime,'yyyy-mm-dd') notetime," +
	    		"TO_CHAR(Y.kptime,'yyyy-mm-dd') kptime,Y.payoperator,TO_CHAR(Y.paydatetime,'yyyy-mm-dd')paydatetime,TO_CHAR(Y.accountmonth,'yyyy-mm') accountmonth,Y.sumdegree,Y.DANJIA,Y.NETWORKDF,Y.ADMINISTRATIVEDF,Y.MARKETDF,Y.INFORMATIZATIONDF,Y.BUILDDF,Y.DDDF," +
	    		"Y.DIANLIANG,Y.CSDB,Y.DEDHDL,Y.lastdf,Y.lastdl,Y.lastlch,Y.lastyue," +
	    		"z.edhdl edhdla,z.qsdbdl qsdbdla,d.beilv beilva,z.shi shicode,z.property,z.zlfh zlfha,z.jlfh jlfha,z.scb scba,"
	    		+ "d.dfzflx dfzflxa,z.stationtype stationtypea,z.gdfs gdfsa "
	    			+"from yufufeiview y,zhandian z,dianbiao d where z.id = d.zdid and y.PREPAYMENT_AMMETERID = d.dbid and  y.ID="+prepayId);
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("getPrepaymentInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      
	      while (rs.next()) {
	    	bean.setAmmeterdegreeidFk(rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "");
	    	bean.setPrepaymentammeterid(rs.getString("PREPAYMENT_AMMETERID") != null ? rs.getString("PREPAYMENT_AMMETERID") : "");
	    	bean.setAuditdate(rs.getString("AUDITDATE") != null ? rs.getString("AUDITDATE") : "");
	    	bean.setAuditoperator(rs.getString("AUDITOPERATOR") != null ? rs.getString("AUDITOPERATOR") : "");
	    	bean.setEnddate(rs.getString("ENDDATE") != null ? rs.getString("enddate") : "");
	    	bean.setFeetypeid(rs.getString("FEETYPEID") != null ? rs.getString("FEETYPEID") : "");
	    	bean.setFinancialdatetime(rs.getString("FINANCIALDATE") != null ? rs.getString("FINANCIALDATE") : "");
	    	bean.setFinancialoperator(rs.getString("financialoperator") != null ? rs.getString("financialoperator") : "");
	    	bean.setId(rs.getString("ID") != null ? rs.getString("ID") : "");
	    	bean.setInputdatetime(rs.getString("inputdatetime") != null ? rs.getString("inputdatetime") : "");	    
	    	bean.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");	    	
	    	bean.setMoney(rs.getString("MONEY") != null ? rs.getString("MONEY") : "");
	    	bean.setStartdate(rs.getString("STARTDATE") != null ? rs.getString("STARTDATE") : "");
	    	bean.setStartdegree(rs.getString("STARTDEGREE") != null ? rs.getString("STARTDEGREE") : "0.0"); 
	    	bean.setStopdegree(rs.getString("stopdegree") != null ? rs.getString("stopdegree") : "0.0");   
	    	bean.setPjje(rs.getDouble("pjje"));
	    	bean.setStartmonth(rs.getString("startmonth")!=null?rs.getString("startmonth"):"");
	    	bean.setEndmonth(rs.getString("endmonth")!=null?rs.getString("endmonth"):"");

	    	bean.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : ""); 
	    	bean.setNoteno(rs.getString("noteno") != null ? rs.getString("noteno") : "");   
	    	bean.setNotetime(rs.getString("notetime") != null ? rs.getString("notetime") : "");   
	    	bean.setKptime(rs.getString("kptime") != null ? rs.getString("kptime") : "");   
	    	bean.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");   
	    	bean.setPaydatetime(rs.getString("paydatetime") != null ? rs.getString("paydatetime") : "");   
	    	bean.setAccountmonth(rs.getString("accountmonth") != null ? rs.getString("accountmonth") : ""); 
	    	bean.setSumdegree(rs.getString("sumdegree")!=null?rs.getString("sumdegree"):"0.0");
	    	bean.setDianfei(rs.getString("DANJIA") != null ? rs.getString("DANJIA") : "0.00");

	    	bean.setNetworkdf(rs.getString("NETWORKDF")!=null?rs.getString("NETWORKDF"):"0.00");
	    	bean.setAdministrativedf(rs.getString("ADMINISTRATIVEDF")!=null?rs.getString("ADMINISTRATIVEDF"):"0.00");
	    	bean.setMarketdf(rs.getString("MARKETDF")!=null?rs.getString("MARKETDF"):"0.00");
	    	bean.setInformatizationdf(rs.getString("INFORMATIZATIONDF")!=null?rs.getString("INFORMATIZATIONDF"):"0.00");
	    	bean.setBuilddf(rs.getString("BUILDDF")!=null?rs.getString("BUILDDF"):"0.00");
	    	bean.setDddf(rs.getString("DDDF")!=null?rs.getString("DDDF"):"0.00");
	    	bean.setActualdegree(rs.getString("DIANLIANG")!=null?rs.getString("DIANLIANG"):"0.0");
	    	bean.setQsdbdlbz(rs.getString("CSDB")!=null?rs.getString("CSDB"):"0.0");
	    	bean.setDedhdlbz(rs.getString("DEDHDL")!=null?rs.getString("DEDHDL"):"0.0");
	    	bean.setEdhdl(rs.getString("edhdla")!=null?rs.getString("edhdla"):"0.0");
	    	bean.setQsdbdl(rs.getString("qsdbdla")!=null?rs.getString("qsdbdla"):"0.0");
	    	bean.setLastdf(rs.getString("lastdf")!=null?rs.getString("lastdf"):"0.00");
	    	bean.setLastdl(rs.getString("lastdl")!=null?rs.getString("lastdl"):"0.00");
	    	bean.setLastlch(rs.getString("lastlch")!=null?rs.getString("lastlch"):"");
	    	bean.setLastyue(rs.getString("lastyue")!=null?rs.getString("lastyue"):"0.00");
	    	bean.setBeilv(rs.getString("beilva")!=null?rs.getString("beilva"):"");
	    	bean.setProperty(rs.getString("property")!=null?rs.getString("property"):"");
	    	bean.setZlfh(rs.getString("zlfha")!=null?rs.getString("zlfha"):"");
	    	bean.setJlfh(rs.getString("jlfha")!=null?rs.getString("jlfha"):"");
	    	bean.setShicode(rs.getString("shicode")!=null?rs.getString("shicode"):"");
	    	bean.setScb(rs.getString("scba")!=null?rs.getString("scba"):"");
	    	bean.setDfzflxcode(rs.getString("dfzflxa")!=null?rs.getString("dfzflxa"):"");
	    	bean.setStationtypecode(rs.getString("stationtypea")!=null?rs.getString("stationtypea"):"");
	    	bean.setGdfscode(rs.getString("gdfsa")!=null?rs.getString("gdfsa"):"");
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
	  public synchronized ArrayList getStationMhchexkt(String ammeterid) {
		  ArrayList list=new ArrayList();
		    StringBuffer sql = new StringBuffer();
		  
		    sql.append("SELECT SB.SHUOSHUZHUANYE,SB.DBILI FROM SBGL SB WHERE DIANBIAOID='"+ammeterid+"'");
		    String result="";
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getzhandian:"+sql);
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
//	  /**
//	   * 账户明细与修改界面调用此方法
//	   * @param accountId String
//	   * @return AccountFormBean
//	   */
//	  public synchronized PrepaymentFormBean getPrepaymentAllInfo(String degreeid) {
//		  PrepaymentFormBean bean = new PrepaymentFormBean();
//	    StringBuffer sql = new StringBuffer();
//	    sql.append("select am.ammeterid,am.multiplyingpower,am.ammetertype,am.initialdegree,am.initialdate," +
//	    		"amd.floatdegree,amd.actualdegree,amd.ammeterdegreeid,zd.jzname,dag.agname as xian,(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 5 )) as shi,(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng,zd.jztype,zd.bgsign," +
//	    		"zd.bieming  from zhandian zd, ammeters am, ammeterdegrees amd,d_area_grade dag " +
//	    		"where zd.id = am.stationid and am.ammeterid = amd.ammeterid_fk and zd.xian=dag.agcode " +
//	    		" and amd.ammeterdegreeid="+degreeid);
//	    
//	    DataBase db = new DataBase();
//	    try {
//	    	System.out.println("getPrepaymentAllInfo:"+sql);
//	      db.connectDb();
//	      ResultSet rs = null;
//	      rs = db.queryAll(sql.toString());
//	      while (rs.next()) {
//	    	
//	    	//站点 
//	    	bean.setStationname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
//	    	bean.setStationaliasname(rs.getString("bieming") != null ? rs.getString("bieming") : "");
//	    	bean.setStationtypeid(rs.getString("jztype") != null ? rs.getString("jztype") : "");
//	    	bean.setProvinceid(rs.getString("sheng") != null ? rs.getString("sheng") : "");
//	    	bean.setCityid(rs.getString("shi") != null ? rs.getString("shi") : "");
//	    	bean.setCountryid(rs.getString("xian") != null ? rs.getString("xian") : "");
//	    	bean.setIsbenchmarkstation(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
//	    	//电表
//	    	bean.setAmmererid(rs.getString("ammeterid") != null ? rs.getString("ammeterid") : "");
//	    	bean.setAmmerertype(rs.getString("ammetertype") != null ? rs.getString("ammetertype") : "");
//	    	bean.setMultiplyingpower(rs.getString("multiplyingpower") != null ? rs.getString("multiplyingpower") : "");
//	    	bean.setInitialdegree(rs.getString("initialdegree") != null ? rs.getString("initialdegree") : "");
//	    	bean.setInitialdate(rs.getString("initialdate") != null ? rs.getString("initialdate") : "");
//	    	//电量
//	    	bean.setAmmeterdegreeid(rs.getString("ammeterdegreeid") != null ? rs.getString("ammeterdegreeid") : "");
//	    	bean.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
//	    	bean.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");
//	   
//	      }
//	      rs.close();
//	    }
//	    catch (DbException de) {
//	      de.printStackTrace();
//	    }
//	    catch (SQLException se) {
//	      se.printStackTrace();
//	    }
//	    finally {
//	      try {
//	        db.close();
//	      }
//	      catch (DbException de) {
//	        de.printStackTrace();
//	      }
//	    }
//	    return bean;
//	  }
	  
	  //获取终止电表读数信息
	  public synchronized PrepaymentFormBean getStopDegreeAllInfo(String ammeterid) {
		  PrepaymentFormBean bean = new PrepaymentFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select stopdegree from yufufeiview where  PREPAYMENT_AMMETERID='"+ammeterid+"' ");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getStopDegreeInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		     // System.out.println("111111111111111");
		      rs = db.queryAll(sql.toString());
		     // System.out.println("22222222222");
		      while (rs.next()) {
		    	//终止电表读数信息
		    	  System.out.println("111111111111111");
		    	 bean.setStopdegree(rs.getString("stopdegree") != null ? rs.getString("stopdegree") : "");//
		    	 
		    	 System.out.println("终止电表读数"+bean.getStopdegree());
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
	  public synchronized PrepaymentFormBean getStartdegree(String ammeterid) {
		  PrepaymentFormBean bean = new PrepaymentFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select  to_number(p.stopdegree) as stopdegree,max(p.ENDDATE) from yufufeiview p where p.FINANCEAUDIT='2' and PREPAYMENT_AMMETERID='"+ammeterid+"' group by stopdegree ");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getStopDegreeInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		     // System.out.println("111111111111111");
		      rs = db.queryAll(sql.toString());
		     // System.out.println("22222222222");
		      while (rs.next()) {
		    	//终止电表读数信息
		    	  System.out.println("111111111111111");
		    	 bean.setStopdegree(rs.getString("stopdegree") != null ? rs.getString("stopdegree") : "");//
		    	 
		    	 System.out.println("终止电表读数"+bean.getStopdegree());
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
	  
	  
	  
	  
	  
	  
	  
	  
	  
		//获取预付费终止电表读数信息
	  public synchronized PrepaymentFormBean getAmmeterDegreeAllInf(String ammeterid) {
		  PrepaymentFormBean beans = new PrepaymentFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select  pr.stopdegree " +
		    		"from dianbiao d,zhandian zd,prepayment pr," +
		    		"d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode and d.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getStopDegreeInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//预付费终止电表读数信息
		    	beans.setStopdegree(rs.getString("stopdegree")!= null ? rs.getString("stopdegree") : "");
		       
		        
		        
		
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
		    return beans;
		    
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	//获得最近的日平均电费用
	  public synchronized String getLastDayAmmeterDegree(String ammeterid) {
		  //PrepaymentFormBean bean = new PrepaymentFormBean();
		    String daynum = "";
		    StringBuffer sql = new StringBuffer();
		    sql.append("select  round(case when  (ad.thisdatetime-ad.lastdatetime) != '0'then  ef.actualpay/(ad.thisdatetime-ad.lastdatetime) else 0 end) dayfee" +
		    		" from ammeterdegrees ad,electricfees ef where ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ad.thisdatetime = (select max(ad.thisdatetime)  from ammeterdegrees ad,electricfees ef where ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ad.ammeterid_fk='"+ammeterid+"')");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getLastDayAmmeterDegree:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	//基站信息
		    	  daynum = rs.getString("dayfee") != null ? rs.getString("dayfee") : "";
		        		     
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
		    return daynum;
		  }
	    

	public String getId() {
		return Id;
	}




	public void setId(String id) {
		Id = id;
	}




	public String getFeetypeid() {
		return feetypeid;
	}




	public void setFeetypeid(String feetypeid) {
		this.feetypeid = feetypeid;
	}




	public String getAmmeterdegreeidFk() {
		return ammeterdegreeidFk;
	}




	public void setAmmeterdegreeidFk(String ammeterdegreeidFk) {
		this.ammeterdegreeidFk = ammeterdegreeidFk;
	}




	public String getMoney() {
		return money;
	}




	public void setMoney(String money) {
		this.money = money;
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




	public String getEnddate() {
		return enddate;
	}




	public void setEnddate(String enddate) {
		this.enddate = enddate;
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




	public String getAuditdate() {
		return auditdate;
	}




	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}




	public String getAuditoperator() {
		return auditoperator;
	}




	public void setAuditoperator(String auditoperator) {
		this.auditoperator = auditoperator;
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




	public String getPrepaymentammeterid() {
		return prepaymentammeterid;
	}




	public void setPrepaymentammeterid(String prepaymentammeterid) {
		this.prepaymentammeterid = prepaymentammeterid;
	}




	public String getStationid() {
		return stationid;
	}




	public void setStationid(String stationid) {
		this.stationid = stationid;
	}




	public String getDayfee() {
		return dayfee;
	}




	public void setDayfee(String dayfee) {
		this.dayfee = dayfee;
	}
	
	/**
	   * 
	   * @param accountId String
	   * @return AccountFormBean
	   */
	  public synchronized PrepaymentFormBean getPrepaymentAllInfo(String degreeid) {
		  PrepaymentFormBean bean = new PrepaymentFormBean();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select dag.agname xian,(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 5)) shi," +
	    		"(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 3)) sheng, " +
	    		"zd.jzname,(select name from indexs where code = zd.jztype and type = 'ZDLX') jztype," +
	    		"zd.bieming, case when zd.bgsign = '1' then '是' else '否' end bgsign " +
	    		" from zhandian zd, d_area_grade dag where  zd.xian = dag.agcode and zd.id = "+degreeid);
	    
	    DataBase db = new DataBase();
	    try {
	      System.out.println("getPrepaymentInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	
	    	//站点 
	    	bean.setStationname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
	    	bean.setStationaliasname(rs.getString("bieming") != null ? rs.getString("bieming") : "");
	    	bean.setStationtypeid(rs.getString("jztype") != null ? rs.getString("jztype") : "");
	    	bean.setProvinceid(rs.getString("sheng") != null ? rs.getString("sheng") : "");
	    	bean.setCityid(rs.getString("shi") != null ? rs.getString("shi") : "");
	    	bean.setCountryid(rs.getString("xian") != null ? rs.getString("xian") : "");
	    	bean.setIsbenchmarkstation(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
	    	
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




	public String getStationname() {
		return stationname;
	}




	public void setStationname(String stationname) {
		this.stationname = stationname;
	}




	public String getZdcode() {
		return zdcode;
	}




	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
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




	public String getSysprice() {
		return sysprice;
	}




	public void setSysprice(String sysprice) {
		this.sysprice = sysprice;
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




	public String getKptime() {
		return kptime;
	}




	public void setKptime(String kptime) {
		this.kptime = kptime;
	}




	public String getPayoperator() {
		return payoperator;
	}




	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}




	public String getPaydatetime() {
		return paydatetime;
	}




	public void setPaydatetime(String paydatetime) {
		this.paydatetime = paydatetime;
	}




	public String getAccountmonth() {
		return accountmonth;
	}




	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}




	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}




	public String getStartmonth() {
		return startmonth;
	}




	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}




	public String getEndmonth() {
		return endmonth;
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
	
	
	public String getStopdegree() {
		return stopdegree;
	}

	public void setStopdegree(String stopdegree) {
		this.stopdegree = stopdegree;
	}

}