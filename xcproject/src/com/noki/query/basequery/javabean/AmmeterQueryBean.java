package com.noki.query.basequery.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

public class AmmeterQueryBean {
	//基础查询电表查询和导出
	public synchronized List<ElectricFeesFormBean> getPageDatapp(String whereStr,String loginId) {		
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    ResultSet rs = null;
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    StringBuffer s3 = new StringBuffer();
		try {		
	        sql =" SELECT  DB.DBID,JZ.JZNAME,(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME = DB.ENTRYPENSONNEL AND DELSIGN = 1) AS ENTRYPENSONNEL,TO_CHAR(DB.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,JZ.ID,DB.LINELOSSVALUE," +
	          "(SELECT NAME FROM INDEXS WHERE CODE = JZ.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
		      "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAN) AS XIAN,"+
		      "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAOQU) AS XIAOQU," +
	          "(SELECT NAME FROM INDEXS WHERE CODE = DB.SSZY AND TYPE = 'SSZY') PROFESSIONALTYPEID,(SELECT NAME FROM INDEXS WHERE CODE = DB.DBYT AND TYPE = 'DBYT') AMMETERUSE,"+
	          "DB.DLLX DLLX,(SELECT NAME FROM INDEXS WHERE CODE = DB.YDSB AND TYPE = 'YDSB') YDSB," +
	          "(SELECT NAME FROM INDEXS WHERE CODE = DB.DFZFLX AND TYPE = 'dfzflx') DFZFLX,"+
	          "(SELECT NAME FROM INDEXS WHERE CODE = DB.LINELOSSTYPE AND TYPE = 'xslx') LINELOSSTYPE,"+
	          "DB.CSDS,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,DB.BEILV,DB.DBXH,DB.DBNAME,JZ.QYZT,DB.DANJIA,DB.DBQYZT,DB.DBZBDYHH " +
	          " FROM ZHANDIAN JZ, D_AREA_GRADE DAG, DIANBIAO DB WHERE JZ.XIAN = DAG.AGCODE AND DB.ZDID=JZ.ID AND JZ.SHSIGN = '1' "+whereStr+
	          "AND ((JZ.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ORDER BY JZ.JZNAME";	     
	      db.connectDb();
	      //StringBuffer strall = new StringBuffer();
	      //strall.append("select count(*) from zhandian jz, dianbiao db,d_area_grade dag where jz.xian = dag.agcode and jz.qyzt='1' and jz.id = db.zdid and db.dbqyzt='1' "+whereStr+"and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
	     // System.out.println(whereStr);
	      System.out.println("电表查询和导出："+sql.toString());
	      s3.append("select count(*)  from (" + sql + ")");
	      rs = db.queryAll(sql.toString());
	      ResultSet qw = null;
	      qw = db.queryAll(s3.toString());
	      if(qw.next()){
             	this.setAllPage((qw.getInt(1)+14)/15);
             }
	      while(rs.next()){
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
				formbean.setDbid(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setJzname(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setEntrypensonnel(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setEntrytime(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setZdcode(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setLinelossvalue(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setStationtype(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setXian(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setXiaoqu((rs.getString(9)!=null?rs.getString(9):""));				
				formbean.setProfessionaltypeid(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setDbyt(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setDllx(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setYdsb(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setDfzflx(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setLinelosstype(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setCsds(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setCssytime(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setBeilv(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setDbxh(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setDbname(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setZdqyzt(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setUnitprice(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setDbqyzt(rs.getString(23)!=null?rs.getString(23):"");
				formbean.setDbzbdyhh(rs.getString(24)!=null?rs.getString(24):"");
				list.add(formbean);
	      }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	    	 if (rs != null) {
	 	        try {
	 	          rs.close();
	 	        }
	 	        catch (SQLException se) {
	 	          se.printStackTrace();
	 	        }
	 	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
/*	//基础查询电表导出
	public synchronized ArrayList getPageData(String whereStr,String loginId) {
		
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    ResultSet rs = null;
	    DataBase db = new DataBase();
		try {
	        sql = 
		          " select  db.dbid,jz.jzname,(select name from account where accountname = db.entrypensonnel and DELSIGN = 1) as entrypensonnel,db.entrytime,jz.zdcode,db.linelossvalue," +
		          "(select name from indexs where code = jz.stationtype and type='stationtype') as stationtype," +
			        "(case when jz.xian is not null then (select distinct agname from d_area_grade where agcode=jz.xian) else '' end)"+
			        "||(case when jz.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=jz.xiaoqu) else '' end) as szdq," +
		          "(select name from indexs where code = db.sszy and type = 'SSZY') professionaltypeid,(select name from indexs where code = db.dbyt and type = 'DBYT') ammeteruse,db.dllx electriccurrenttype_ammeter,(select name from indexs where code = db.ydsb and type = 'YDSB') usageofelectypeid_ammeter," +
		          "(select name from indexs where code = db.dfzflx and type = 'dfzflx') dfzflx,"+
		          "(select name from indexs where code = db.linelosstype and type = 'xslx') linelosstype,"+
		          "db.csds,db.cssytime,db.beilv,db.dbxh,db.dbname" +
		          " from zhandian jz, d_area_grade dag, dianbiao db where jz.xian = dag.agcode and jz.qyzt='1' and jz.id = db.zdid "+whereStr+
		          "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))) ORDER BY JZ.JZNAME";

	     
	      db.connectDb();
	      System.out.println("电表查询导出："+sql.toString());
	      rs = db.queryAll( sql.toString());
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    }

	    finally {
	    	 if (rs != null) {
		 	        try {
		 	          rs.close();
		 	        }
		 	        catch (SQLException se) {
		 	          se.printStackTrace();
		 	        }
		 	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }*/
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
  //电表查询汇总
  public ElectricFeesFormBean getCountt(String whereStr,String loginId){
	    String count1="";//总数
	    String sumjs="";//结算电表总数
	    String sumcj="";//采集电表总数
	    String sumgl="";//管理电表总数
		DataBase db = new DataBase();
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();			
			StringBuffer strall = new StringBuffer();
		    strall.append(" SELECT COUNT(*) COUNT1,SUM(DECODE(DB.DBYT,'dbyt01',1)) SUMJS,SUM(DECODE(DB.DBYT,'dbyt02',1)) SUMCJ,SUM(DECODE(DB.DBYT,'dbyt03',1)) SUMGL" +		      		
			      " FROM ZHANDIAN JZ, D_AREA_GRADE DAG, DIANBIAO DB WHERE JZ.XIAN = DAG.AGCODE AND JZ.SHSIGN = '1' AND JZ.ID = DB.ZDID "+whereStr+
			      "AND ((JZ.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))");
			System.out.println("电表查询汇总："+strall.toString());
			rs = db.queryAll(strall.toString());         
      		 while(rs.next()){
          		count1 = rs.getString(1)!=null?rs.getString(1):"0";
          		sumjs = rs.getString(2)!=null?rs.getString(2):"0";
          		sumcj = rs.getString(3)!=null?rs.getString(3):"0";
          		sumgl = rs.getString(4)!=null?rs.getString(4):"0";
      		 }
          	bean1.setCount(count1);          	
          	bean1.setJsdb(sumjs);
          	bean1.setCaijizd(sumcj);
          	bean1.setGldb(sumgl);
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		return bean1;
	}
  
  
  
  
  }