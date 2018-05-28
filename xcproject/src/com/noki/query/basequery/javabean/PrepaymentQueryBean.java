package com.noki.query.basequery.javabean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class PrepaymentQueryBean {
	//预付费信息查询和导出
	Connection conn = null;
	Statement sta = null;
	public synchronized List<ElectricFeesFormBean> getPageData(int start,String whereStr,String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    //调用负责站点条件函数	 
		try {
			fzzdstr = getFuzeZdid(db,loginId);
	        sql ="SELECT DISTINCT TO_CHAR(PR.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,PR.LIUCHENGHAO,D.DBNAME,PR.ID,ZD.JZNAME,ZD.ID,PR.CITYAUDIT,PR.FINANCEAUDIT,(SELECT NAME FROM INDEXS WHERE CODE=D.DFZFLX AND TYPE='dfzflx')DFZFLX,"+
	        "PR.PREPAYMENT_AMMETERID,(SELECT NAME FROM INDEXS WHERE CODE = PR.FEETYPEID AND TYPE='FYLX') AS NAME,PR.MONEY,PR.AMMETERDEGREEID_FK,PR.STARTDEGREE,"+
	        "PR.STOPDEGREE,TO_CHAR(PR.STARTDATE,'yyyy-mm-dd') STARTDATE,TO_CHAR(PR.ENDDATE,'yyyy-mm-dd') ENDDATE,TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME=PR.ENTRYPENSONNEL AND DELSIGN=1) AS ENTRYPENSONNEL,TO_CHAR(PR.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(PR.ENDMONTH,'yyyy-mm') ENDMONTH,"+
	        "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN), "+
	        "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU) ," + 
	        "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=ZD.GDFS AND I.TYPE='GDFS') AS GDFS,TO_CHAR(PR.KJYF,'yyyy-mm') KJYF,PR.MEMO, " +
	        " PR.MANUALAUDITSTATUS,PR.MANUALAUDITNAME,TO_CHAR(PR.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,PR.COUNTYAUDITSTATUS,PR.COUNTYAUDITNAME,TO_CHAR(PR.COUNTYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') COUNTYAUDITTIME,PR.CITYZRAUDITSTATUS,PR.CITYZRAUDITNAME,TO_CHAR(PR.CITYZRAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYZRAUDITTIME "+
	        "FROM DIANBIAO D,YUFUFEIVIEW PR,ZHANDIAN ZD WHERE  ZD.ID=D.ZDID AND D.DBID = PR.PREPAYMENT_AMMETERID "+whereStr+
	        "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+") ORDER BY ENTRYTIME DESC,JZNAME DESC";
	        System.out.println("预付费查询与导出："+sql.toString());
	        db.connectDb();	 
	        conn = db.getConnection();
	        sta = conn.createStatement();
	        rs = sta.executeQuery(sql.toString());
	        while(rs.next()){
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
				formbean.setAccountmonth(rs.getString(1)!=null?rs.getString(1):"");	
				formbean.setLiuchenghao(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setDbname(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setStationid(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setJzname(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setZdcode(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setCityaudit(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setCw(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setDfzflx(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setPrepayment_ammeterid(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setFeebz(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setMoney(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setAmmeterdegreeidFk(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setStartdegree(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setStopdegree(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setStartdate(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setStopdate(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setEntrytime(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setEntrypensonnel(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setStartmonth(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setEndmonth(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setXian(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setXiaoqu(rs.getString(23)!=null?rs.getString(23):"");
				formbean.setStationtype(rs.getString(24)!=null?rs.getString(24):"");
				formbean.setGdfs(rs.getString(25)!=null?rs.getString(25):"");
				formbean.setKjyf(rs.getString(26)!=null?rs.getString(26):"");
				formbean.setMemo(rs.getString(27)!=null?rs.getString(27):"");
				
				formbean.setManualauditstatus(rs.getString(28)!=null?rs.getString(28):"");
				formbean.setManualauditname(rs.getString(29)!=null?rs.getString(29):"");
				formbean.setManualauditdatetime(rs.getString(30)!=null?rs.getString(30):"");
				formbean.setCountyauditstatus(rs.getString(31)!=null?rs.getString(31):"");
				formbean.setCountyauditname(rs.getString(32)!=null?rs.getString(32):"");
				formbean.setCountyaudittime(rs.getString(33)!=null?rs.getString(33):"");
				formbean.setCityzrauditstatus(rs.getString(34)!=null?rs.getString(34):"");
				formbean.setCityzrauditname(rs.getString(35)!=null?rs.getString(35):"");
				formbean.setCityzraudittime(rs.getString(36)!=null?rs.getString(36):"");
				
				list.add(formbean);
	        }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.free(rs,sta,conn);
	    }
	    return list;
	  }
	//详细信息的弹出页面	
	public synchronized ElectricFeesFormBean getPageDatae(String dbid,String loginId,String dlid) {
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    //调用负责站点条件函数	 
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	        sql ="SELECT PR.NETWORKDF,PR.INFORMATIZATIONDF,PR.ADMINISTRATIVEDF,PR.MARKETDF,PR.BUILDDF,PR.DDDF "+
	        "FROM YUFUFEIVIEW PR WHERE PREPAYMENT_AMMETERID='"+dbid+"' AND PR.ID='"+dlid+"'";	        
		     System.out.println("预付费查询详细信息："+sql.toString());
		     db.connectDb();	 
		     conn = db.getConnection();
		     sta = conn.createStatement();
		     rs = sta.executeQuery(sql.toString());
		     while(rs.next()){
		    	 bean1.setScdff(rs.getString(1)!=null?rs.getString(1):""); 
		    	 bean1.setXxhdf(rs.getString(2)!=null?rs.getString(2):"");
		    	 bean1.setBgdf(rs.getString(3)!=null?rs.getString(3):"");
		    	 bean1.setYydf(rs.getString(4)!=null?rs.getString(4):"");
		    	 bean1.setJstzdf(rs.getString(5)!=null?rs.getString(5):"");
		    	 bean1.setDddfdf(rs.getString(6)!=null?rs.getString(6):"");
		     }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.free(rs,sta,conn);
	    }
	    return bean1;
	  }
	
	//预付费信息条数、未审核、市级审核、财务审核条数
	public synchronized ElectricFeesFormBean getCount(String whereStr,String loginId){
	    String count1 = "";//总数
	    String money = "";//金额总和
	    String shishen = "";//市级审核
	    String caiwu = "";//财务审核
	    String weishen = "";//未审核
	    ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		String fzzdstr="";
		String sql="";
		ResultSet rs = null;
			try {
				fzzdstr = getFuzeZdid(db,loginId);			
				sql="SELECT DISTINCT COUNT(PR.ID) ZONG,SUM(PR.MONEY) MONEY,SUM(DECODE(PR.CITYAUDIT, '1', 1, '2', 1)) SHISHEN,"+
					"SUM(DECODE(PR.FINANCEAUDIT, '2', 1)) CAIWU,SUM(DECODE(PR.CITYAUDIT,'0',1,'',1)) WEISHEN "
		    		 +" FROM DIANBIAO D, YUFUFEIVIEW PR, ZHANDIAN ZD  WHERE ZD.ID = D.ZDID AND D.DBID = PR.PREPAYMENT_AMMETERID "
		    		 +whereStr+"AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";
				System.out.println("预付费查询汇总："+sql.toString());
				db.connectDb();	 
				conn = db.getConnection();
				sta = conn.createStatement();
				rs = sta.executeQuery(sql.toString());
				while(rs.next()){
					count1 = rs.getString(1)!=null?rs.getString(1):"0";
					money = rs.getString(2)!=null?rs.getString(2):"0";
					shishen = rs.getString(3)!=null?rs.getString(3):"0";
					caiwu = rs.getString(4)!=null?rs.getString(4):"0";
					weishen = rs.getString(5)!=null?rs.getString(5):"0";
				}
	          	bean1.setCount(count1);
	          	bean1.setMoney(money);
	          	bean1.setShish(shishen);
	          	bean1.setCw(caiwu);
	          	bean1.setWtg(weishen);
		    }catch (DbException de) {
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
	
	//预付费金额总和统计
	public String getCountGree(String whereStr,String loginId,String str){
		String sql = "";
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		      strall.append("select sum(to_char(money,'fm9999999990.00')) from dianbiao d,yufufeiview pr,zhandian zd where zd.id=d.zdid  and zd.qyzt='1' and d.dbid = pr.prepayment_ammeterid "+whereStr+str+"and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("预付费信息汇总："+strall.toString());
			rs = db.queryAll(strall.toString());
           
            		 while(rs.next()){
					count=rs.getString(1);
            		 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
		}
		catch (DbException de) {
			de.printStackTrace();
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
		return count;
	}
	
	
	//预付费信息导出
	public synchronized ArrayList getPageData(String whereStr,String loginId) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	 
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	        sql =
		        "select distinct d.dbname,zd.jzname,zd.zdcode,pr.CITYAUDIT,pr.FINANCEAUDIT,d.dfzflx,pr.id,pr.prepayment_ammeterid,(select NAME from indexs where CODE = pr.feetypeid and type='FYLX') as NAME,pr.money,pr.ammeterdegreeid_fk,pr.startdegree,pr.stopdegree,pr.startdate,pr.enddate,"+
		        "pr.entrytime,(select name from account where accountname=pr.entrypensonnel and DELSIGN=1) as entrypensonnel,pr.startmonth,pr.endmonth,"+
		        "(case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)"+
		        "||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq," + 
		        "(select NAME from indexs where CODE = zd.STATIONTYPE and type='stationtype') as stationtype " +
		        "from dianbiao d,yufufeiview pr,zhandian zd where zd.id=d.zdid and zd.id = pr.stationid and d.dbid = pr.prepayment_ammeterid "+whereStr+
		        "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") order by entrytime desc,jzname desc";
	        System.out.println("预付费查询导出："+sql.toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs = null;
	    try {
	      db.connectDb();
	     
	      rs = db.queryAll(sql.toString());
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
	  }
	
	//预支充减查询
	public synchronized List<ElectricFeesFormBean> getPageDataz(int start,String whereStr,String loginId) {
		List<ElectricFeesFormBean>  list = new ArrayList<ElectricFeesFormBean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    ResultSet rs = null;
	    DataBase db = new DataBase();
	    //调用负责站点条件函数	 
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	        sql ="SELECT DISTINCT ZD.JZNAME,ZD.ZDCODE,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=D.DFZFLX AND I.TYPE='dfzflx')DFZFLX," +
	        	"PR.PREPAYMENT_AMMETERID,SUM(PR.MONEY) AS MONEY," +
	        	//"SUM(PR.PJJE) AS PJJEYF,
	        	"(select sum(pr.pjje) from yufufeiview pr where d.dbid=pr.prepayment_ammeterid and pr.NOTETYPEID='pjlx03')fpyf," + //预付费发票金额
	        	"(SELECT SUM(df.PJJE) FROM DIANFEIDAN df WHERE D.DBID = df.AMMETERID_FK AND df.MANUALAUDITSTATUS = '2'  and df.notetypeid='pjlx03' ) fpdf," + //电费发票金额
	        	"(select sum(pr.pjje) from yufufeiview pr where d.dbid=pr.prepayment_ammeterid and pr.NOTETYPEID='pjlx05')sjyf," +  //预付费收据金额
	        	"(SELECT SUM(df.PJJE) FROM DIANFEIDAN df WHERE D.DBID = df.AMMETERID_FK AND df.MANUALAUDITSTATUS = '2'  and df.notetypeid='pjlx05' ) sjdf,"+    //电费收据金额
	        	//"(SELECT SUM(PJJE) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS='2' "+whereStr+" GROUP BY DBID) PJJEDF,"+
		        "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
		        "(SELECT SUM(ACTUALPAY) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS='2' "+whereStr+" GROUP BY DBID) ACTUALPAY,"+
		        "(SELECT MAX(THISDATETIME) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS='2' "+whereStr+" GROUP BY DBID) THISDATETIME,"+
		        "(SELECT SUM(ACTUALPAY) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS='2' AND THISDATETIME=(SELECT MAX(THISDATETIME) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS='2' "+whereStr+" GROUP BY DBID)"+whereStr+" GROUP BY DBID) THISACTUALPAY,"+
		        "ROUND((CASE WHEN (SELECT SUM(ACTUALPAY) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK  AND MANUALAUDITSTATUS = '2' "+whereStr+" GROUP BY DBID) IS NULL THEN SUM(PR.MONEY) ELSE (SUM(PR.MONEY)- (SELECT SUM(ACTUALPAY) FROM DIANFEIDAN WHERE D.DBID = AMMETERID_FK AND MANUALAUDITSTATUS = '2' "+whereStr+" GROUP BY DBID)) END),2) AS CE, "+
		        "(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN) ELSE '' END)"+
		        "||(CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU) ELSE '' END) AS SZDQ " + 	        
		        "FROM ZHANDIAN ZD,DIANBIAO D,YUFUFEIVIEW PR WHERE ZD.ID=D.ZDID  AND D.DBID = PR.PREPAYMENT_AMMETERID AND ZD.QYZT='1' AND PR.FINANCEAUDIT = '2' "+whereStr+
		        " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+") GROUP BY JZNAME, ZD.ZDCODE,PR.PREPAYMENT_AMMETERID,"+
		        "PR.AMMETERDEGREEID_FK,STATIONTYPE, D.DBID,ZD.XIAN,ZD.XIAOQU,ZD.SHI,D.DFZFLX";
		      System.out.println("预支充减查询："+sql.toString());
		      db.connectDb();
		      rs = db.queryAlls(sql.toString());
		      while(rs.next()){
		    	  ElectricFeesFormBean formbean=new ElectricFeesFormBean();
		    	  formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
		    	  formbean.setZdcode(rs.getString(2)!=null?rs.getString(2):"");
		    	  formbean.setDfzflx(rs.getString(3)!=null?rs.getString(3):"");//
		    	  formbean.setPrepayment_ammeterid(rs.getString(4)!=null?rs.getString(4):"");
		    	  formbean.setMoney(rs.getString(5)!=null?rs.getString(5):"0");
		    	  formbean.setPjjeyf(rs.getString(6)!=null?rs.getString(6):"0");//预付费发票
		    	  formbean.setPjjedf(rs.getString(7)!=null?rs.getString(7):"0");//电费发票
		    	  formbean.setSjyf(rs.getString(8)!=null?rs.getString(8):"0");//预付费收据
		    	  formbean.setSjdf(rs.getString(9)!=null?rs.getString(9):"0");//电费收据
		    	  formbean.setStationtype(rs.getString(10)!=null?rs.getString(10):"");
		    	  formbean.setActualpay(rs.getString(11)!=null?rs.getString(11):"0");
		    	  formbean.setThisdatetime(rs.getString(12)!=null?rs.getString(12):"");
		    	  formbean.setThisactualpay(rs.getString(13)!=null?rs.getString(13):"0");
		    	  formbean.setCe(rs.getString(14)!=null?rs.getString(14):"0");
		    	  formbean.setSzdq(rs.getString(15)!=null?rs.getString(15):"");
		    	  list.add(formbean);
		      }
		      //Query query=new Query();
		      //list = query.query(rs);
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

	//预支充减查询
	public synchronized ArrayList getlist(String dbid) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    ResultSet rs = null;
	    DataBase db = new DataBase();
	    //调用负责站点条件函数	 
		try {
	        sql ="SELECT DISTINCT ZD.JZNAME,ZD.ZDCODE,PR.PREPAYMENT_AMMETERID,PR.MONEY,PR.ENDMONTH,PR.STARTMONTH,PR.ACCOUNTMONTH,PR.ENTRYTIME,"+
	        "(SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = PR.ENTRYPENSONNEL AND ACC.DELSIGN = 1) AS ENTRYPENSONNEL,"+
	        "PR.AMMETERDEGREEID_FK,(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) "+
	        "|| (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ "+
	        "FROM ZHANDIAN ZD, DIANBIAO D, YUFUFEIVIEW PR WHERE ZD.ID = D.ZDID AND ZD.ID = PR.STATIONID AND D.DBID = PR.PREPAYMENT_AMMETERID AND D.DBID='"+dbid+"'";
	        System.out.println("预付费查询详细信息："+sql.toString());
	        db.connectDb();	 
	        conn = db.getConnection();
	        sta = conn.createStatement();
	        rs = sta.executeQuery(sql.toString());
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	    	db.free(rs,sta,conn);
	    }

	    return list;
	  }	
	
	//预支充减查询
	public synchronized ArrayList getlistcj(String dbid) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    ResultSet rs = null;
	    DataBase db = new DataBase();
	    //调用负责站点条件函数	 
		try {
	        sql ="SELECT DISTINCT ZD.JZNAME,ZD.ZDCODE,PR.AMMETERID_FK AS PREPAYMENT_AMMETERID,PR.ACTUALPAY AS MONEY,PR.ENDMONTH,PR.STARTMONTH,PR.ACCOUNTMONTH,PR.ENTRYTIME,"+
	        "(SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = PR.ENTRYPENSONNEL AND ACC.DELSIGN = 1) AS ENTRYPENSONNEL,"+
	        "(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) "+
	        "|| (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ "+
	        "FROM ZHANDIAN ZD, DIANBIAO D, DIANFEIDAN PR WHERE  ZD.ID = D.ZDID AND D.DBID = PR.AMMETERID_FK AND MANUALAUDITSTATUS = '2' AND D.DBID='"+dbid+"'";
	        System.out.println("充减查询详细信息："+sql.toString());
	        db.connectDb();	 
	        conn = db.getConnection();
	        sta = conn.createStatement();
	        rs = sta.executeQuery(sql.toString());
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (Exception de) {
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
	
	//预付费信息条数
	public String getCountz(String whereStr,String loginId,String str){
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		      strall.append("select count(*) from dianbiao d,yufufeiview pr,zhandian zd where zd.id=d.zdid and zd.id = pr.stationid and zd.qyzt='1' and d.dbid = pr.prepayment_ammeterid "+whereStr+str+"and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("预付费查询汇总："+strall.toString());
			rs = db.queryAll(strall.toString());
           
            		 while(rs.next()){
					count=rs.getString(1);
            		 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
		}
		catch (DbException de) {
			de.printStackTrace();
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
		return count;
	}	
	
  private int allPage;
  public void setAllPage(int allpage ){
	  this.allPage=allpage;
	  
  }
  public int getAllPage(){
	  return this.allPage;
  }
//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;
		String cxtj = new String("");
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery("select begincode,endcode from per_zhandian where accountid='"
					+ loginid
					+ "' and begincode is not null and endcode is not null");
			while (rs.next()) {			
					cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
							+ "' and zdcode <='" + rs.getString(2) + "')";			
			}	
			System.out.println("负责站点条件："+cxtj);
		}
		catch (DbException de) {
			de.printStackTrace();
		} 
		finally {
			db.free(rs,sta,conn);
		}
		return cxtj.toString();
	}
}
