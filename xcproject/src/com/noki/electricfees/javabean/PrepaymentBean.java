package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

public class PrepaymentBean {
	//预付费管理信息查询
	public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
		
		PrepaymentFormBean prepaymentFormBean=new PrepaymentFormBean();
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			 sql =
			        "select distinct pr.id,pr.prepayment_ammeterid,ft.name,pr.money,pr.ammeterdegreeid_fk,pr.startdegree,pr.stopdegree,to_char(pr.startdate,'yyyy-mm-dd') startdate,zd.jzname," +
			        "(select NAME from indexs where CODE = zd.STATIONTYPE and type='stationtype') as stationtype,pr.dianliang as actualdegree," +
			        "to_char(pr.enddate,'yyyy-mm-dd') enddate,pr.stationid,pr.financeaudit,(select i.name from indexs i where i.code=d.dfzflx and i.type='dfzflx')as dfzflx " +
			        " from indexs ft, yufufeiview pr,zhandian zd,dianbiao d where ft.code = pr.feetypeid and d.dbid=pr.prepayment_ammeterid  and zd.id=d.zdid" +
			        " and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "+
			        " and ft.type = 'FYLX' and d.dfzflx<>'dfzflx02' and (pr.manualauditstatus = '0' or pr.manualauditstatus = '-2' or pr.manualauditstatus is null) "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";
          System.out.println("预付费管理信息查询："+sql.toString());
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
	      db.close();
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
	//预付费管理信息查询查询总条数
	public String getCountt1(String whereStr,String loginId) {
		PrepaymentFormBean prepaymentFormBean=new PrepaymentFormBean();
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    StringBuffer sql = new StringBuffer();
	    String fzzdstr = "";
	    String count="";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			 sql.append("select count(zd.id)"+
			        " from indexs ft, yufufeiview pr,zhandian zd,dianbiao d where ft.code = pr.feetypeid and zd.qyzt='1' and  d.dbid=pr.prepayment_ammeterid  and zd.id=d.zdid" +
			        " and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "+
			        " and ft.type = 'FYLX' and d.dfzflx<>'dfzflx02' and (pr.manualauditstatus = '0' or pr.manualauditstatus = '-2' or pr.manualauditstatus is null) "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")" );
          System.out.println("预付费管理信息查询汇总："+sql.toString());
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
	      while(rs.next()){
				count=rs.getString(1);
			 }
	    }
	    
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	    return count;
	  }
	//预付费管理信息查询总金额
	public String getCountt2(String whereStr,String loginId) {
		System.out.println("PrepaymentBean-getPageData:"+whereStr);
		PrepaymentFormBean prepaymentFormBean=new PrepaymentFormBean();
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    StringBuffer sql = new StringBuffer();
	    String fzzdstr = "";
	    String count="";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			 sql.append("select sum(pr.money)"+
			        " from indexs ft, yufufeiview pr,zhandian zd,dianbiao d where ft.code = pr.feetypeid and zd.qyzt='1' and  d.dbid=pr.prepayment_ammeterid  and zd.id=d.zdid" +
			        " and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "+
			        " and ft.type = 'FYLX' and d.dfzflx<>'dfzflx02' and (pr.manualauditstatus = '0' or pr.manualauditstatus = '-2' or pr.manualauditstatus is null) "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")" );
          System.out.println("预付费管理信息查询汇总："+sql.toString());
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
	      while(rs.next()){
				count=rs.getString(1);
			 }
	    }
	    
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	    return count;
	  }
  private int allPage;
  public void setAllPage(int allpage ){
	  this.allPage=allpage;
	  
  }
  public int getAllPage(){
	  return this.allPage;
  }
  
  //预付费管理信息导出
  
  public synchronized ArrayList getPageData(String whereStr,String loginId) {
		PrepaymentFormBean prepaymentFormBean=new PrepaymentFormBean();
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			 sql =
				 "select distinct pr.id,pr.prepayment_ammeterid,ft.name,pr.money,pr.ammeterdegreeid_fk,pr.startdegree,pr.stopdegree,to_char(pr.startdate,'yyyy-mm-dd') startdate,zd.jzname," +
			        "(select NAME from indexs where CODE = zd.STATIONTYPE and type='stationtype') as stationtype,pr.dianliang as actualdegree," +
			        "to_char(pr.enddate,'yyyy-mm-dd') enddate,pr.stationid,pr.financeaudit,(select i.name from indexs i where i.code=d.dfzflx and i.type='dfzflx')as dfzflx " +
			        " from indexs ft, yufufeiview pr,zhandian zd,dianbiao d where ft.code = pr.feetypeid and zd.qyzt='1' and  d.dbid=pr.prepayment_ammeterid  and zd.id=d.zdid" +
			        " and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "+
			        " and ft.type = 'FYLX' and d.dfzflx<>'dfzflx02' and pr.cityaudit='0' "+whereStr+" "+
				    "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";
        System.out.println("预付费管理信息导出："+sql.toString());
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
		db.close();
    System.out.println("负责站点条件："+cxtj);
		return cxtj.toString();
	}
	
  /**
   * 添加预付费信息
   * 以下是要添加的信息
 * @param d4 
 * @param d3 
 * @param d42 
 * @param d32 
   */
  public synchronized String addPrepayment(PrepaymentFormBean formBean,String bzw,String zdshzt,String zdshms,String qxzr,String city,String cityzr, String d3, String d4, String d32, String d42,String hbzq,String bzz,String scb) {
    //birthday = birthday.length()>0?birthday:null;
    String msg = "保存预付费信息失败！请重试或与管理员联系！";
    MD5 md = new MD5();
    CTime ctime = new CTime();
//    //计算预计增加天数
//    String dayfee =  formBean.getLastDayAmmeterDegree(formBean.getPrepaymentammeterid());
//    int preaddday = 0;
//    if(!dayfee.equals("")){
//    	preaddday = Integer.parseInt(formBean.getMoney())/Integer.parseInt(dayfee);
//    }
    //计算预计增加天数
    String start=formBean.getStartmonth();
    String end=formBean.getEndmonth();
    int startn = Integer.parseInt(start.substring(0, 4));
	int starty = Integer.parseInt(start.substring(5, 7));
	int endn = Integer.parseInt(end.substring(0, 4));
	int endy = Integer.parseInt(end.substring(5, 7));
	int time = (endn - startn) * 12 + endy - starty + 1;
	
//	long uuid = System.currentTimeMillis();
	long uuid1 = System.currentTimeMillis();
	String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
	String uuid = uuid2+Long.toString(uuid1).substring(3);
	
	String df = formBean.getMoney();
	double dfyu = Double.parseDouble(df)%time;
	int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
//	NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF
	//生产
	 double scdlyushu=Double.parseDouble(formBean.getNetworkdf())%time;
	    int scdl = (int) ((Double.parseDouble(formBean.getNetworkdf())-scdlyushu)/time);
	    //办公
	    double admintyushu=Double.parseDouble(formBean.getAdministrativedf())%time;
	    int scdl1 = (int) ((Double.parseDouble(formBean.getAdministrativedf())-admintyushu)/time);
	    //营销
	    double marketyushu=Double.parseDouble(formBean.getMarketdf())%time;
	    int scdl2 = (int) ((Double.parseDouble(formBean.getMarketdf())-marketyushu)/time);
	    //信息化
	    double inforyushu=Double.parseDouble(formBean.getInformatizationdf())%time;
	    int scdl3 = (int) ((Double.parseDouble(formBean.getInformatizationdf())-inforyushu)/time);
	    //建设
	    double buildyushu=Double.parseDouble(formBean.getBuilddf())%time;
	    int scdl4= (int) ((Double.parseDouble(formBean.getBuilddf())-buildyushu)/time);
	    //代垫
	    double dddf=Double.parseDouble(formBean.getDddf())%time;
	    int scdl5= (int) ((Double.parseDouble(formBean.getDddf())-dddf)/time);
	    
	List dflist = new ArrayList();
	 List year_month = new ArrayList();
	 List scdlfentan = new ArrayList();
	 List bgdlfentan = new ArrayList();
	 List yydlfentan = new ArrayList();
	 List xxhdlfentan = new ArrayList();
	 List jstzdlfentan = new ArrayList();
	 List dddffentan        = new ArrayList();
	String[] sqlBatch = new String[time];
	for (int i = 0; i < time; i++) {
		String yue = String.valueOf(starty);
     	if(yue.length()==1)yue = "0"+yue;
     	String year_month_tmp = startn+"-"+yue;
     	starty ++;
     	if(starty>12){
     		starty = 1;
     		startn ++;
     	}
     	year_month.add(year_month_tmp);
		if (i == time - 1){
			dflist.add(dfPermonth + dfyu);
		scdlfentan.add(scdl+scdlyushu);
 		bgdlfentan.add(scdl1+admintyushu);
 		yydlfentan.add(scdl2+marketyushu);
 		xxhdlfentan.add(scdl3+inforyushu);
 		jstzdlfentan.add(scdl4+buildyushu);
 		dddffentan.add(scdl5+dddf);
 		
		}else{
			dflist.add(dfPermonth);
		scdlfentan.add(scdl);
 		bgdlfentan.add(scdl1);
 		yydlfentan.add(scdl2);
 		xxhdlfentan.add(scdl3);
 		jstzdlfentan.add(scdl4);
 		dddffentan.add(scdl5);
		}
	}
	System.out.println("shijian:" + time + "-" + start + "-" + end);
	
	System.out.println("--------------------------"+d32+d42);
	
//	NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF
	String s="sysdate";
	//String autotime = "1".equals(zdshzt)?s:"";
	String qxzrtime = "1".equals(qxzr)?s:"NULL";
	String citytime = "1".equals(city)?s:"NULL";
	String cityzrtime = "1".equals(cityzr)?s:"NULL";
	for (int i = 0; i < time; i++) {
    StringBuffer sql = new StringBuffer();
    
    sql.append("INSERT INTO prepayment(STATIONID,PREPAYMENT_AMMETERID,PJJE,FEETYPEID,MONEY,STARTDEGREE,STARTDATE,ENDDATE,NOTETYPEID,"
    		 +"NOTENO,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,STARTMONTH,ENDMONTH,MEMO,LASTDF,LASTDL,LASTYUE,LASTLCH,UUID,financeaudit,entrypensonnel,"
    		 +"entrytime,stopdegree,sumdegree,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,DANJIA,DIANLIANG,YFFBZW,"
    		 +"JSZQ,CSDB,DEDHDL,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,CITYAUDIT,COUNTYFLAG,CITYFLAG,FLAG,"
    		 +"EDHDL,QSDBDL,CITYAUDITTIME,COUNTYAUDITTIME,CITYZRAUDITTIME,MANUALAUDITSTATUS,BEILV,HBZQ,BZZ,SCB,DBYDL,ZLFH,JLFH,STATIONTYPECODE,PROPERTYCODE,DFZFLXCODE,GDFSCODE)");
    sql.append(" VALUES('" + formBean.getStationid() + "'"); 
    sql.append(",'" + formBean.getPrepaymentammeterid() + "','" +formBean.getPjje()+"','"+
    		          formBean.getFeetypeid() + "','" + dflist.get(i) + "','" + formBean.getStartdegree() +
               "',TO_DATE('" + formBean.getStartdate() + "','yyyy-mm-dd'),TO_DATE('" + formBean.getEnddate() + "','yyyy-mm-dd')" +
               ",'" + formBean.getNotetypeid() + "','" + formBean.getNoteno() + "',TO_DATE('" + formBean.getNotetime() + "','yyyy-mm-dd'),TO_DATE('" +formBean.getKptime()+"','yyyy-mm-dd'),'"
               + formBean.getPayoperator() + "',TO_DATE('" + formBean.getPaydatetime() + "','yyyy-mm-dd'),TO_DATE('" 
               + formBean.getAccountmonth() + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),'"
               + formBean.getMemo() + "','"+ formBean.getLastdf()+"','"+formBean.getLastdl()+"','"+formBean.getLastyue()+"','"+formBean.getLastlch()+"','"
               + uuid+ "','1','"+formBean.getEntrypensonnel()+"',"+s+",'"+formBean.getStopdegree()
               +"','"+formBean.getSumdegree()+"','"+scdlfentan.get(i)+"','"+bgdlfentan.get(i)+"','"+yydlfentan.get(i)+"','"
               +xxhdlfentan.get(i)+"','"+jstzdlfentan.get(i)+"','"+dddffentan.get(i)+"','"+formBean.getDianfei()+"','"
               +formBean.getActualdegree()+"','"+bzw+"','"+formBean.getJszq()+"','"+d42+"','"+d32
               +"','"+zdshzt+"','"+zdshms+"','"+qxzr+"','"+cityzr+"','"+city+"','"+qxzr+"','"+cityzr+"','"+city+"','"+d3+"','"+d4
               +"',"+citytime+","+qxzrtime+","+cityzrtime+",'0','"+formBean.getBeilv()+"',"+hbzq+","+bzz+","+scb+","+formBean.getDbydl()+","+formBean.getZlfh()+","+formBean.getJlfh()
               +",'"+formBean.getStationtypecode()+"','"+formBean.getPropertycode()+"','"+formBean.getDfzflxcode()+"','"+formBean.getGdfscode()+"')");
    
    System.out.println("预付费添加"+sql);
	sqlBatch[i] = sql.toString();
	}
    DataBase db = new DataBase();
    try {
      db.connectDb();
      db.setAutoCommit(false);
      db.updateBatch(sqlBatch);
      msg = "添加预付费信息成功！";
        db.commit();
		db.setAutoCommit(true);
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
   * 删除账户
   * @param accountId String[]
   * @return String
   */
  public synchronized String delPrepayment(String degreeid) {
	    String msg = "删除预付费信息失败！";
	    DataBase db = new DataBase();

	    try {
	      db.connectDb();

	    	 String delete="delete from PREPAYMENT where uuid=(select e.uuid from PREPAYMENT e where e.id='"+degreeid+"') ";

	    
	      System.out.println(delete.toCharArray());
	      //String delNames = getName(accountId, db);
	      msg = "删除预付费信息失败！";
	      db.update(delete);
	      msg = "删除预付费信息成功！";
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
   * 修改预付费信息
 * @param cityzr 
 * @param city 
 * @param qxzr 
 * @param zdshms 
 * @param zdshzt 
 * @param d4 
 * @param d3 
 * @param d42 
 * @param d32 
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyPrepayment(PrepaymentFormBean formBean,String prepayId, String zdshzt, String zdshms, String qxzr, String city, String cityzr, String d3, String d4, String d32, String d42,String hbzq,String bzz,String scb) {
	    String msg = "修改预付费信息失败！";
	    String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
	    DataBase db = new DataBase();
       System.out.println("--"+prepayId);
	    try {
	    	 String delete="delete from PREPAYMENT where uuid=(select e.uuid from PREPAYMENT e where e.id='"+prepayId+"') ";
	    	String start=formBean.getStartmonth();
	        String end=formBean.getEndmonth();
	        System.out.println("***"+start+end);
	        int startn = Integer.parseInt(start.substring(0, 4));
	    	int starty = Integer.parseInt(start.substring(5, 7));
	    	int endn = Integer.parseInt(end.substring(0, 4));
	    	int endy = Integer.parseInt(end.substring(5, 7));
	    	int time = (endn - startn) * 12 + endy - starty + 1;
	    	
//	    	long uuid = System.currentTimeMillis();
			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
			String uuid = uuid2+Long.toString(uuid1).substring(3);	    	
	    	
	    	String df = formBean.getMoney();
	    	double dfyu = Double.parseDouble(df)%time;
	    	int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
//	    	NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF
	    	//生产
	    	 double scdlyushu=Double.parseDouble(formBean.getNetworkdf())%time;
	    	    int scdl = (int) ((Double.parseDouble(formBean.getNetworkdf())-scdlyushu)/time);
	    	    //办公
	    	    double admintyushu=Double.parseDouble(formBean.getAdministrativedf())%time;
	    	    int scdl1 = (int) ((Double.parseDouble(formBean.getAdministrativedf())-admintyushu)/time);
	    	    //营销
	    	    double marketyushu=Double.parseDouble(formBean.getMarketdf())%time;
	    	    int scdl2 = (int) ((Double.parseDouble(formBean.getMarketdf())-marketyushu)/time);
	    	    //信息化
	    	    double inforyushu=Double.parseDouble(formBean.getInformatizationdf())%time;
	    	    int scdl3 = (int) ((Double.parseDouble(formBean.getInformatizationdf())-inforyushu)/time);
	    	    //建设
	    	    double buildyushu=Double.parseDouble(formBean.getBuilddf())%time;
	    	    int scdl4= (int) ((Double.parseDouble(formBean.getBuilddf())-buildyushu)/time);
	    	    //代垫
	    	    double dddf=Double.parseDouble(formBean.getDddf())%time;
	    	    int scdl5= (int) ((Double.parseDouble(formBean.getDddf())-dddf)/time);
	    	
	    	
	    	
	    	List dflist = new ArrayList();
	    	 List year_month = new ArrayList();
	    	 List scdlfentan = new ArrayList();
	    	 List bgdlfentan = new ArrayList();
	    	 List yydlfentan = new ArrayList();
	    	 List xxhdlfentan = new ArrayList();
	    	 List jstzdlfentan = new ArrayList();
	    	 List dddffentan  = new ArrayList();
	    	 
	    	String[] sqlBatch = new String[time];
	    	for (int i = 0; i < time; i++) {
	    		String yue = String.valueOf(starty);
	         	if(yue.length()==1)yue = "0"+yue;
	         	String year_month_tmp = startn+"-"+yue;
	         	starty ++;
	         	if(starty>12){
	         		starty = 1;
	         		startn ++;
	         	}
	         	year_month.add(year_month_tmp);
	    		if (i == time - 1){
	    			dflist.add(dfPermonth + dfyu);
	    			scdlfentan.add(scdl+scdlyushu);
	    	 		bgdlfentan.add(scdl1+admintyushu);
	    	 		yydlfentan.add(scdl2+marketyushu);
	    	 		xxhdlfentan.add(scdl3+inforyushu);
	    	 		jstzdlfentan.add(scdl4+buildyushu);
	    	 		dddffentan.add(scdl5+dddf);
	    			
	    		}else{
	    			dflist.add(dfPermonth);
	    			scdlfentan.add(scdl);
	    	 		bgdlfentan.add(scdl1);
	    	 		yydlfentan.add(scdl2);
	    	 		xxhdlfentan.add(scdl3);
	    	 		jstzdlfentan.add(scdl4);
	    	 		dddffentan.add(scdl5);
	    			
	    			
	    		}
	    	}
	    	System.out.println("shijian:" + time + "-" + start + "-" + end);
	    	 db.connectDb();
	    	 
	    	String qxzrtime = "1".equals(qxzr)?s:"NULL";
	    	String citytime = "1".equals(city)?s:"NULL";
	    	String cityzrtime = "1".equals(cityzr)?s:"NULL";
	    	for (int i = 0; i < time; i++) {
	      StringBuffer sql = new StringBuffer();
	      sql.append("INSERT INTO prepayment(STATIONID,PREPAYMENT_AMMETERID,PJJE,FEETYPEID,MONEY,STARTDEGREE,STOPDEGREE,STARTDATE,ENDDATE,"
	    		  +"NOTETYPEID,NOTENO,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,STARTMONTH,ENDMONTH,MEMO,UUID,financeaudit,"
	    		  +"entrypensonnel,entrytime,sumdegree,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,DANJIA,DIANLIANG,JSZQ,"
	    		  +"CSDB,DEDHDL,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,CITYAUDIT,COUNTYFLAG,CITYFLAG,FLAG,"
	    		  +"EDHDL,QSDBDL,CITYAUDITTIME,COUNTYAUDITTIME,CITYZRAUDITTIME,YFFBZW,MANUALAUDITSTATUS,BEILV,HBZQ,BZZ,SCB,DBYDL,ZLFH,JLFH,PROPERTYCODE,STATIONTYPECODE,DFZFLXCODE,GDFSCODE)");
	      sql.append(" VALUES('" + formBean.getStationid() + "'"); 
	      sql.append(",'" + formBean.getPrepaymentammeterid() + "','" +formBean.getPjje()+"','"+
	      		          formBean.getFeetypeid() + "','" + dflist.get(i) + "','" + formBean.getStartdegree() +
	                 "','"+formBean.getStopdegree()+"',TO_DATE('" + formBean.getStartdate() + "','yyyy-mm-dd'),TO_DATE('" + formBean.getEnddate() + "','yyyy-mm-dd')" +
	                 ",'" + formBean.getNotetypeid() + "','" + formBean.getNoteno() + "',TO_DATE('" + formBean.getNotetime() + "','yyyy-mm-dd'),TO_DATE('" 
	                 + formBean.getKptime() + "','yyyy-mm-dd'),'" + formBean.getPayoperator() + "',TO_DATE('" + formBean.getPaydatetime() + "','yyyy-mm-dd'),TO_DATE('" 
	                 + formBean.getAccountmonth() + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),'" + formBean.getMemo() 
	                 + "','" + uuid+ "','1','"+formBean.getEntrypensonnel()+"',"+s+",'"+formBean.getSumdegree()+"','"+scdlfentan.get(i)
	                 +"','"+bgdlfentan.get(i)+"','"+yydlfentan.get(i)+"','"+xxhdlfentan.get(i)+"','"+jstzdlfentan.get(i)+"','"
	                 +dddffentan.get(i)+"','"+formBean.getDianfei()+"','"+formBean.getActualdegree()+"','"+formBean.getJszq()+"','"
	                 +d42+"','"+d32
	                 +"','"+zdshzt+"','"+zdshms+"','"+qxzr+"','"+cityzr+"','"+city+"','"+qxzr+"','"+cityzr+"','"+city+"','"+d3+"','"+d4
	                 +"',"+citytime+","+qxzrtime+","+cityzrtime+",'1','0','"+formBean.getBeilv()+"',"+hbzq+","+bzz+","+scb+","+formBean.getDbydl()
	                 +","+formBean.getZlfh()+","+formBean.getJlfh()+",'"+formBean.getPropertycode()+"','"+formBean.getStationtypecode()+"','"+formBean.getDfzflxcode()+"','"+formBean.getGdfscode()+"')");
	      
	  	  sqlBatch[i] = sql.toString();

	      System.out.println(sql.toString());
	     
	    	}
	    	 System.out.println(formBean.getEnddate()+delete.toString());
	    	 
	      msg = "修改预付费信息失败！";
	      db.setAutoCommit(false);
	      db.update(delete);
	      db.updateBatch(sqlBatch);
	      msg = "修改预付费信息成功！";
	        db.commit();
			db.setAutoCommit(true);
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
//获取电表初始读数
  public synchronized String getStartDegree(String amid) {
	    StringBuffer sql = new StringBuffer();
	    sql.append(" select t.initialdegree from ammeters t where t.ammeterid='"+amid+"'");
	    String startdegree = "";
	    DataBase db = new DataBase();
	    try {
	    	System.out.println("getStartDegree:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {	    
	    	  startdegree = rs.getString("initialdegree") != null ? rs.getString("initialdegree") : "";
	        		     
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
	    return startdegree;
	  }
    //获得最近的电表读数
    public synchronized String getLastAmmeterDegree(String ammeterid) {
	    StringBuffer sql = new StringBuffer();
	    sql.append("select max(t.thisdegree) lastdegree from ammeterdegrees t where AMMETERID_FK='"+ammeterid+"'");
	    String startdegree = "";
	    DataBase db = new DataBase();
	    try {
	    	//System.out.println("getLastAmmeterDegree:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	  startdegree = rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "";	        		     
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
	    return startdegree;
	  }
    /**
     * 获取预计结束时间信息
     * 以下是要添加的信息
     */
    public synchronized String getPreenddate(PrepaymentFormBean formBean) {

      MD5 md = new MD5();
      CTime ctime = new CTime();
      //计算预计结束时间
      String dayfee =  formBean.getLastDayAmmeterDegree(formBean.getPrepaymentammeterid());
      //System.out.println("%%%%%%%%%%%%%:"+dayfee);
      int preaddday = 0;
      String preenddata = "";
      if(!dayfee.equals("")){
    	String money = formBean.getMoney();
    	if(money == ""){
    		money = "0";
    	}
    	//System.out.println("getPreenddate:"+money+"*****"+dayfee);
      	preaddday = Integer.parseInt(money)/Integer.parseInt(dayfee);
      }
      StringBuffer sql = new StringBuffer();
	    sql.append(" select to_char(to_date('" + formBean.getStartdate() + "', 'YYYY-MM-DD')+"+preaddday+",'YYYY-MM-DD') preenddata from PREPAYMENT t ");
	    String startdegree = "";
	    DataBase db = new DataBase();
	    try {
	    	//System.out.println("getPreenddate:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {	    
	    	  preenddata = rs.getString("preenddata") != null ? rs.getString("preenddata") : "";
	        		     
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
	    
     System.out.println("预计增加天数:"+preaddday);
     System.out.println("预计结束时间:"+preenddata);
      return preenddata;
    }
}
