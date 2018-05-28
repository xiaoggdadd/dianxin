package com.noki.check.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.elecconsume.MonthSummary;
import com.noki.elecconsume.MonthSummaryBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

public class CheckDegreeBean {
	//人工电量审核查询
	public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
		
		System.out.println("CheckDegreeBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			 sql =
			        "select zd.JZNAME,AMMETERDEGREEID, db.dllx electriccurrenttype_ammeter,db.YDSB," +
			        "AMMETERID_FK,LASTDEGREE,THISDEGREE,to_char(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,to_char(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE," +
			        "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"+
			        "zd.PROPERTY,zd.JZTYPE,"+
			        "ACTUALDEGREE,ad.autoauditstatus,ad.MANUALAUDITSTATUS,ad.entrytime,ad.blhdl,zd.edhdl,zd.qsdbdl dl,zd.dianfei from manualdlcheck_ddv ad,dianbiao db,zhandian zd where ad.ammeterid_fk=db.dbid and db.dbyt='dbyt03' and db.zdid=zd.id and ad.amuuid is null "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";
			 System.out.println("人工电量审核查询:"+sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	
//人工电量审核总条数
	public String getCount(String whereStr,String loginId,String str){
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			  StringBuffer strall = new StringBuffer();
		      strall.append("select count(*) "
		    		  +"from dianduview ad,dianbiao db,zhandian zd where (ad.manualauditstatus = '0' or ad.manualauditstatus is null) and  ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null "+whereStr+str+" "+
					    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("人工电量审核总条数"+strall.toString());
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
	//人工电量审核折算后用电量总和
	public String getCountGree(String whereStr,String loginId,String str){
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		      strall.append("select sum(to_char(blhdl,'fm9999999990.00'))" 
		    		  +"from dianduview ad,dianbiao db,zhandian zd where (ad.manualauditstatus = '0' or ad.manualauditstatus is null) and  ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null "+whereStr+str+" "+
					    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("人工电量审核折算后用电量总和"+strall.toString());
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
	
	
	
//人工电量审核导出
public synchronized ArrayList getPageDatargdl(String whereStr,String loginId) {
		
		System.out.println("AmmeterDegreeBean-getPageData:"+whereStr);
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
			        "select zd.JZNAME,AMMETERDEGREEID, db.dllx electriccurrenttype_ammeter, (select i.name from indexs i where i.code=db.ydsb and i.type='YDSB')as usageofelectypeid_ammeter ," +
			        "AMMETERID_FK,LASTDEGREE,THISDEGREE,to_char(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,to_char(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE," +
			        "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"+
			        "(select name from indexs where code=zd.property and type = 'ZDSX') as property,"+
			        "(select name from indexs where code=zd.jztype and type = 'ZDLX') as jztype,"+
			        "ACTUALDEGREE,ad.autoauditstatus,ad.MANUALAUDITSTATUS,ad.entrytime,ad.blhdl,zd.edhdl,zd.qsdbdl dl,zd.dianfei from dianduview ad,dianbiao db,zhandian zd where  ad.ammeterid_fk=db.dbid and db.dbyt='dbyt03' and db.zdid=zd.id  and ad.amuuid is null "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";  //order by ENTRYTIME desc,JZNAME desc
		} catch (DbException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("人工电量审核导出"+sql.toString());
		
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

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			
		}	
  System.out.println("负责站点条件："+cxtj);
  		if(rs!=null){
  			rs.close();
  		}
  		if(db!=null){
  			db.close();
  		}
		return cxtj.toString();
	}
  /**
   * 审核电量信息
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyCheckDegree(AmmeterDegreeFormBean formBean,String bz) {
	    String msg = "审核电量信息失败！";
	    DataBase db = new DataBase();
   String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
	    try {
	      db.connectDb();
          
	      String sql = "execute UPDATE ammeterdegrees SET MANUALAUDITSTATUS='" + formBean.getManualauditstatus() + "'," +
	      		       "MANUALAUDITDATETIME=" + s + "," +
	      		     "MANUALAUDITNAME='" + formBean.getManualauditname() + "'" +
	      		       " WHERE uuid =(select a.uuid from ammeterdegrees a where a.ammeterdegreeid="+formBean.getAmmeterdegreeid()+")"; //审核电量信息表

	      System.out.println(sql.toString());
	      msg = "审核电量信息失败！";
	      
	      db.update(sql);
	      if(bz.equals("1")){
		     msg = "审核电量信息通过！"; 
		     
		     
		     // if(formBean.getAmmeteruse().equals("dbyt01")){
		      //   MonthSummary summary=new MonthSummary();
		         
		         /**
		          * @author GT
		          * 暂时注销   中间表 功能
		          * **/
		         
		         
//		         if(summary.Summary(formBean.getCityid(), formBean.getEndmonth())){
//		        	 
//		      }
		   //  };
		     
		  }else{
		     msg = "审核电量信息未通过！";
		  }
	      
	    }
	    catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  /**
   * 审核电费信息
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyCheckFees(ElectricFeesFormBean formBean,String chooseIdStr1,String chooseIdStr2,String bz) {
	  String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
	    String msg = "审核电费信息失败！";
	    DataBase db = new DataBase();
        String str = "";
        if(bz.equals("1")){
        	str = "";
        }else if(bz.equals("0")){
        	str ="LIUCHENGHAO='',";
        }
        
        String kjyf = formBean.getCurrentmonth();
        if("null".equals(kjyf) || kjyf == null){
        	Date date = new Date();
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        	String currentmonth = formatter.format(date);
        	kjyf = currentmonth;
        }
       /**
        * @author GT
        * 更改信息  的 updtae 方式  
        * 
        * */
        StringBuffer sql1 = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();
        StringBuffer sql3 = new StringBuffer();
        StringBuffer sql4 = new StringBuffer();
	    try {
	      db.connectDb();
          if(chooseIdStr1.split(",").length>10){
        	  sql1.append("execute");
        	  sql2.append("execute");
        	  sql3.append("execute");
        	  sql4.append("execute");
          }
           sql1.append(" UPDATE AMMETERDEGREES SET MANUALAUDITSTATUS='"+ formBean.getManualauditstatus() + "'," +
		       "MANUALAUDITDATETIME=" + s + "," +	
		       "MANUALAUDITNAME='" + formBean.getManualauditname() + "'" +
		       " WHERE UUID IN ("+chooseIdStr1+chooseIdStr2+")");
           sql2.append(" UPDATE ELECTRICFEES SET MANUALAUDITSTATUS='"+ formBean.getManualauditstatus() + "'," +
                "MANUALAUDITDATETIME=" + s + "," +	
                "MANUALAUDITNAME='" + formBean.getManualauditname() + "'," +str+
                " KJYF=to_date('"+kjyf+"','yyyy-mm')"+
                " WHERE DFUUID IN ("+chooseIdStr1+chooseIdStr2+")");
	       sql3.append(" UPDATE ELECTRICFEES SET MANUALAUDITSTATUS='" + formBean.getManualauditstatus() + "'," +
	    	   "FINANCIALDATETIME=" + s + "," +	
		       "FINANCIALOPERATOR='" + formBean.getFinancialoperator() + "'," +
		       "KJYF=to_date('"+kjyf+"','yyyy-mm')"+
		       " WHERE DFUUID IN ("+chooseIdStr1+")");
	       sql4.append(" UPDATE prepayment SET financeaudit='" + formBean.getManualauditstatus() + "'," +
	    	   "FINANCIALDATE=" + s + "," +	
		       "FINANCIALOPERATOR='" + formBean.getFinancialoperator() + "'," +
		       "KJYF=to_date('"+kjyf+"','yyyy-mm')"+
		       " WHERE UUID IN ("+chooseIdStr2+")");
	      msg = "审核电费信息失败！"; 
	      if(formBean.getManualauditstatus()=="2"||formBean.getManualauditstatus()=="-1"){
	    	  if(chooseIdStr1!=null&&chooseIdStr1!=""){
		    	System.out.println("财务审核电费单信息表:"+sql3.toString()); 
	    		 db.update(sql3.toString());
	    	  }
	    	  if(chooseIdStr2!=null&&chooseIdStr2!=""){
	    		System.out.println("财务审核预付费:"+sql4.toString());
		    	db.update(sql4.toString());
	    	  }
	      }else{
	    	  
	    	  
	    	  /**添加事务处理
	    	   * @author GT
	    	   * */
	    	  System.out.println("审核电量信息表:"+sql1.toString());
	    	  System.out.println("审核电费单:"+sql2.toString());
	    	  
	    	  db.setAutoCommit(false);
	    	  db.update(sql1.toString());
	    	  db.update(sql2.toString());
	    	  db.commit();
	    	  db.setAutoCommit(true);
	    	  
	    	  
	      }
	     
	      if(bz.equals("1")){
	    	 
	    	 
		     msg = "审核电费信息通过！";

		     
		      
		  }else{
		     msg = "审核电费信息未通过！";
		  }
	    } catch (DbException de) {
	      try {
	        db.rollback();
	        db.setAutoCommit(true);
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  /**
   * 市级电费审核
   * @param accountId String[]
   * @return String
   */
  public synchronized String CheckCityFees(ElectricFeesFormBean formBean,String chooseIdStr1,String chooseIdStr2,String bz) {
	    String msg = "审核电费信息失败！";
	    String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
	    DataBase db = new DataBase();
	    String str = "";
        if(bz.equals("1")){
        	str = "";
        }else if(bz.equals("0")){
        	str ="LIUCHENGHAO='',";
        }
        
        /**
         * @author GT
         * 市级审核更改  批量 
         * */
        
        StringBuffer sql1 = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();
        if(chooseIdStr1.split(",").length>10){
      	  sql1.append("execute");
      	  sql2.append("execute");
        }
	    try {
	      db.connectDb();
          sql1.append(" UPDATE ELECTRICFEES SET CITYAUDIT='" + formBean.getCityaudit() + "'," +
	      		       "CITYAUDITTIME=" + s + "," +str+
	      		     "CITYAUDITPENSONNEL='" + formBean.getCityauditpensonnel() + "'" +	
	      		   " WHERE DFUUID IN ("+chooseIdStr1+")");      		      
         sql2.append(" UPDATE prepayment SET CITYAUDIT='" + formBean.getCityaudit() + "'," +
	      		       		"CITYAUDITTIME=" + s + "," +str+
		      		     "CITYAUDITPENSONNEL='" + formBean.getCityauditpensonnel() + "'" +	
                	 	" WHERE UUID IN ("+chooseIdStr2+")"); 
            
	      msg = "审核电费信息失败！";
	      if(chooseIdStr1!=""&&chooseIdStr1!=null){
	    	  System.out.println("市级审核电费单:"+sql1.toString());
	    	  db.update(sql1.toString());
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	
	    	  System.out.println("市级审核预付费:"+sql2.toString());
	    	  db.update(sql2.toString());	    	  
	      }
	      if(bz.equals("1")){
		     msg = "审核电费信息通过！";
		  }else if(bz.equals("0")){
			 msg = "审核电费信息取消通过！";
		  }else{
		     msg = "审核电费信息未通过！";
		  }
	    } catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  
  /**
   * 审核站点信息
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyCheckZhanDian(ZhanDianForm formBean,String bz) {
	    String msg = "审核站点信息失败！";
	    String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
	    DataBase db = new DataBase();

	    try {
	      db.connectDb();
          
	      String sql = " UPDATE ZHANDIAN SET MANUALAUDITSTATUS_STATION='" + formBean.getManualauditstatus_station() + "'," +
	      		       //"MANUALAUDITNAME_STATION='" + formBean.getManualauditname_station() + "'," +
	      		       "MANUALAUDITDATETIME_STATION=" + s + "" +	      		      
	      		       " WHERE ID="+formBean.getId(); //审核站点信息表

	      System.out.println(sql.toString());
	      msg = "审核站点信息失败！";
	      db.update(sql);
	      if(bz.equals("1")){
	    	msg = "审核站点信息通过！";  
	      }else{
	        msg = "审核站点信息未通过！";
	      }
	    }
	    catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  
  /**
   * 显示电表信息列表
   * @param accountId String[]
   * @return String
   */
  public synchronized ArrayList getPageDataAmmeter(int start,String whereStr) {
	  System.out.println("CheckDegreeBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql =
	        "select  db.dbid ammeterid,zd.jzname,zd.sheng,zd.shi,zd.xian," +
	        "db.sszy professionaltypeid,db.dbyt ammeteruse,db.csds initialdegree,db.cssytime initialdate," +
	        "db.beilv multiplyingpower,db.dbxh ammetertype from dbid am,zhandian zd " +
	        "where db.zdid=zd.id "+whereStr;

	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {
	      db.connectDb();
	      StringBuffer strall = new StringBuffer();
	      strall.append("select count(*) from dbid am,zhandian zd where am.zdid=zd.id ");
	      rs = db.queryAll(strall.toString());
	      if(rs.next()){
	    	  this.setAllPage((rs.getInt(1)+14)/15);
	      }
	      NPageBean nbean=new NPageBean();
	      rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException de) {
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
  //人工审核电费(弃用转为ElectricFeesBean的方法)
  public synchronized ArrayList getPageDataFees(int start,String whereStr,String loginId) {
		System.out.println("ElectricFeesBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			sql =
		        "select zd.jzname,zd.zdcode,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS," +
		        "ind.name NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,ef.PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.MANUALAUDITSTATUS " +
		        "from zhandian zd,ammeters am,ammeterdegrees ad,electricfees ef,indexs ind  " +
		        "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk " +
		        "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null) and ind.code=ef.notetypeid and ind.type='PJLX' "+whereStr+" "+
			    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";

		} catch (DbException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    ResultSet rs = null;
	    try {
	      db.connectDb();
	      StringBuffer strall = new StringBuffer();
	      strall.append("select count(*) from zhandian zd,ammeters am,ammeterdegrees ad,electricfees ef,indexs ind  " +
	        "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk " +
	        "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null) and ind.code=ef.notetypeid and ind.type='PJLX' and (ef.MANUALAUDITSTATUS != '1' or ef.MANUALAUDITSTATUS is NUll) "+whereStr+" "+
		    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ");
	      rs = db.queryAll(strall.toString());
	      if(rs.next()){
	    	  this.setAllPage((rs.getInt(1)+14)/15);
	      }
	      NPageBean nbean=new NPageBean();
	      rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException de) {
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
  //人工审核站点 
  @SuppressWarnings("unchecked")
public synchronized ArrayList getPageDataSp(int start,String whereStr,String loginId) {
		
		System.out.println("StationPointQueryBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	   
		String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			sql =
		        " select jz.ID,jz.JZNAME,jz.ZDCODE,jz.MANUALAUDITSTATUS_STATION,(select t.name from indexs t where t.code = jz.property) as property,(select t.name from indexs t where t.code = jz.jztype) as jztype,(select t.name from indexs t where t.code = jz.yflx) as yflx,(select t.name from indexs t where t.code = jz.gdfs) as gdfs,jz.SYDATE,jz.ERPCODE,jz.FZR,jz.jnglmk,jz.area,jz.dianfei," +
		        "(select agname from d_area_grade where agcode = jz.xian) as xian," +
		        "(select agname from d_area_grade where agcode = jz.shi) as shi," +
		        "(select agname from d_area_grade where agcode = jz.sheng) as sheng" +
		        " from zhandian jz,dianbiao d where d.zdid=jz.id and d.dbyt='dbyt01' and jz.shsign='1' "+whereStr+" "+
			    "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";
System.out.println("--"+sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	    ResultSet rs = null;
	    try {
	      db.connectDb();
	      StringBuffer strall = new StringBuffer();
	      strall.append("select count(*) from zhandian jz,dianbiao d where d.zdid=jz.id and d.dbyt='dbyt01' and jz.shsign='1' "+whereStr+" "+
				    "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ");
	      rs = db.queryAll(strall.toString());
	      if(rs.next()){
	    	  this.setAllPage((rs.getInt(1)+14)/15);
	      }
	      NPageBean nbean=new NPageBean();
	      rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException de) {
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
  /**
   * 合同单补录发票审核
   * @param accountId String[]
   * @return String
   */
  public synchronized String Checknewhtsh(String chooseIdStr,String bz) {
	    String msg = "审核合同单补录发票信息失败！";
	    //String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
	    DataBase db = new DataBase();
	    try {
	      db.connectDb();            
          String sql = " UPDATE PREPAYMENT SET FPSH='" + bz +"' WHERE UUID IN ("+chooseIdStr+")";
	      System.out.println("合同单补录发票审核:"+sql.toString());
	      db.update(sql);
	      if(bz.equals("1")){
		     msg = "审核合同单补录发票信息通过！";
		  }else{
		     msg = "审核合同单补录发票信息未通过！";
		  }
	    } catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  
  
}
