package com.noki.mobi.schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import com.noki.util.CTime;

/**
 * <p>Title: 定时</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class STask extends TimerTask {
    public STask() {
    	
    }

    private String nowtime = "";
    private String nowDay = "";
    private String nowHour = "";
    private String preSday = "";
    private String preSday_1 = "";
    private TempDb tempdb = null;
    private String tabname = "";
    private String dbid2 = "";
    private String ldata = "";
    private String bdata2 = "";
    private String ltime = "";
    private String btime2 = "";
    private String sjdata = "";
    private String zdcb = "";
    private String days="";
    
    private int jgday = 1;
    private String memo="";
    int countDay=0;
    //private String pre2Sday = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    @SuppressWarnings("deprecation")
	public void run() {
        System.out.println("Tab1_STask_timer run==" + CTime.formatWholeDate());
        nowtime = CTime.formatDate();
        nowDay = nowtime.substring(0, 8);
        nowHour = nowtime.substring(8, 10);
        System.out.println("nowHour=="+nowHour);
//        if (Integer.parseInt(nowHour)>=23&&Integer.parseInt(nowHour) < 24) { //23点到24点之间执行任务转移数据库
//        	System.out.println("nowHour=**="+nowHour);
//        	String sql="select * from tab1";
//        	ArrayList list=new ArrayList();
//        	 TempDb tempdb =new TempDb();
//        	
//        	  try {
//        		  tempdb.connectDb();
//                  ResultSet rs2=tempdb.queryAll(sql);
//                  while (rs2.next()) {
//                	  String tempStr="";
//                	  tempStr=rs2.getString(1)+","+rs2.getString(2)+","+rs2.getString(3)+","+rs2.getString(4)+","+rs2.getString(5);
//                	  list.add(tempStr);
//                       
//                  }
//              	
//               }catch (Exception de) {
//                   de.printStackTrace();
//               } 
//               if(list.size()>0){
//            	   DataBase db =new DataBase();
//            	try {
//					db.connectDb();
//				  for(int i=0;i<list.size();i++){
//					
//					 String[] array=list.get(i).toString().split(",");
//	                String insertsql="insert into tab1addendum  values('"+array[0]+"','"+array[1]+"','"+array[2]+"','"+array[3]+"','"+array[4]+"')";
//                     db.update(insertsql);
//                     if(i%200==0){
//                    	 db.close();
//                    	 db.connectDb();
//                     }
//                    System.out.println("--"+i+insertsql);
//              
//            	   }
//				  String delsql=" Truncate table tab1";
//	              	 if(list.size()>0){
//	              	    tempdb.update(delsql);
//	                 }
//	              	 tempdb.close();
//            	} catch (DbException e) {
//					e.printStackTrace();
//				}
//                }
//        	
//        }
//        if (Integer.parseInt(nowHour)>=1 &&Integer.parseInt(nowHour) < 2) { //零点到一点之间执行任务汇总到电量表
//        	DataBase db = null;
//        	db = new DataBase();
//        	
//        	ResultSet rs=null;
//        	List<STaskbean> list=new ArrayList();
//        	
//        	days=sdfk.format(new Date());
//            System.out.println("-days-"+days);
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(calendar.DATE, -(jgday));//间隔时间前推上次读取数据时间
//            Date perday = calendar.getTime();
//            preSday = sdfk.format(perday);
//            
//        	try {
//				db.connectDb();
//				String sql="select substr(M.DBID,1,14),M.DBID,to_char(S.DATES,'yyyy-mm-dd')as datetime,nvl(S.DAYVALUE,'987654321')as dl,S.SL,S.Bdata ,S.Edata from allinfo_view M left join (SELECT substr(MT.STNAME,1,14) as STNAME,to_date(MT.getdatetime,'yyyy-mm-dd HH24:Mi:SS') as DATES,sum(ST.Datavalue-MT.Datavalue) as DAYVALUE,count(*) SL,max(ST.Datavalue) as EDATA,max(MT.Datavalue) as BDATA from tab1view MT   left join tab1view ST   on to_date(ST.getdatetime,'yyyy-mm-dd HH24:Mi:SS')=to_date(MT.getdatetime,'yyyy-mm-dd HH24:Mi:SS')+1 and INSTR(ST.getdatetime,'00:00:00')<>0 and ST.flag<>0 and ST.Stname=MT.Stname  where INSTR(MT.getdatetime,'00:00:00')<>0 and MT.flag<>0  group by substr(MT.STNAME,1,14),to_date(MT.getdatetime,'yyyy-mm-dd HH24:Mi:SS') ) S on substr(M.DBID,1,14)=S.STNAME and to_char(S.DATES,'yyyy-mm-dd')='"+preSday+"'WHERE M.DBYT='dbyt02'  and nvl(S.DAYVALUE,'987654321')<>'987654321'ORDER by substr(M.DBID,1,14),to_char(S.DATES,'yyyy-mm-dd')";
//			    rs=db.queryAll(sql);
//			    System.out.println("-sql---"+sql.toString());
//			    try {
//					while(rs.next()){
//						STaskbean bean=new STaskbean();
//						bean.setDbid(rs.getString(2));
//						bean.setDatetime(rs.getString(3));
//						bean.setDl(rs.getString(4));
//						bean.setLastdegree(rs.getString(6));
//						bean.setThisdegree(rs.getString(7));
//						list.add(bean);
//					}
//					System.out.println("sub--"+list.toString());	
//					
//					for(STaskbean bean1 :list){
//						
//			String sql1="insert into AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,BLHDL,zdcb,memo)values('" +bean1.getDbid() + "','" + bean1.getLastdegree() + "','" + bean1.getThisdegree() +"','" + bean1.getDatetime() + "','" + bean1.getDatetime() + "','" + bean1.getDl()+ "','1','"+memo+"')";
//			System.out.println("sub"+sql1.toString());
//			int k = db.update(sql1.toString());
//					}
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			    
//        	} catch (DbException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally {
//              try {
//              db.close();
//              
//             
//          } catch (DbException de) {
//              de.printStackTrace();
//          }
//
//      }
//        	
//        }
    }

}
