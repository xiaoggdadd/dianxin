package com.noki.ammeterdegree.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class newAmmeterDegreeFormBean {
	private String ad2_bz;
	private String ad2_bz1;
	//基站信息
	private String stationid;
    private String stationname;
    private String provinceid;
    private String cityid;
    private String countryid;
    private String stationtypeid;
    private String isbenchmarkstation;
    private String stationaliasname;
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
	public String getAd2_bz() {
		return ad2_bz;
	}
	public void setAd2_bz(String ad2Bz) {
		ad2_bz = ad2Bz;
	}
	public String getAd2_bz1() {
		return ad2_bz1;
	}
	public void setAd2_bz1(String ad2Bz1) {
		ad2_bz1 = ad2Bz1;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
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
	public String getAmmeterid() {
		return ammeterid;
	}
	public void setAmmeterid(String ammeterid) {
		this.ammeterid = ammeterid;
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
	public String getElectriccurrenttype_ammeter() {
		return electriccurrenttype_ammeter;
	}
	public void setElectriccurrenttype_ammeter(String electriccurrenttypeAmmeter) {
		electriccurrenttype_ammeter = electriccurrenttypeAmmeter;
	}
	public String getUsageofelectypeid_ammeter() {
		return usageofelectypeid_ammeter;
	}
	public void setUsageofelectypeid_ammeter(String usageofelectypeidAmmeter) {
		usageofelectypeid_ammeter = usageofelectypeidAmmeter;
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
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
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
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getPayzq() {
		return payzq;
	}
	public void setPayzq(String payzq) {
		this.payzq = payzq;
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
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeAllInfo(String ammeterid) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select  dag.agname as xian," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 5 )) as shi," +
		    		"(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng, " +
		    		"zd.jzname,zd.id, (select name from indexs where code = zd.jztype and type = 'ZDLX') jztype, " +
		    		"zd.bieming, case when zd.bgsign='1' then '是' else '否' end bgsign,d.dbid, " +
		    		"(select name from indexs where code = d.dbyt and type = 'DBYT') dbyt," +
		    		"d.csds,d.cssytime,d.beilv," +
		    		"(select name from indexs where code = d.ydsb and type = 'YDSB') ydsb," +
		    		"d.dllx,(select name from indexs where code=d.dfzflx and type='dfzflx')as dfzflx from dianbiao d,zhandian zd," +
		    		"d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode and d.dbid='"+ammeterid+"'");
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getAmmeterDegreeInfo:"+sql);
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
	  public synchronized ArrayList selectXS(String dbid){
		  AmmeterDegreeFormBean formBean=new AmmeterDegreeFormBean();
		  String sql="select linelosstype,linelossvalue from dianbiao where dbid='"+dbid+"'";
		  System.out.println("--"+sql.toString());
		  ResultSet rs=null;
		  DataBase db = new DataBase();
		 ArrayList list=new ArrayList();
		  try {
			db.connectDb();
			rs=db.queryAll(sql);
			 Query query=new Query();
		      list = query.query(rs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }
		  return list;
	  }
     
}
