package com.noki.mobi.schedule;

import java.util.TimerTask;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import java.text.SimpleDateFormat;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.text.DecimalFormat;
import com.noki.util.CTime;
import com.noki.imports.*;
/**
 * <p>Title:县级电量电费 自动 月统计 </p>
 *
 * <p>Description: 建立电量电费自动读取计划  Timer  </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: ptac</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CopyOfMonthSumTask extends TimerTask {
    public CopyOfMonthSumTask() {
    }

    private String nowtime = "";
    private String yaerMonth = "";
    private String nowDay = "";
    private String nowHour = "";
    private String preSday = "";// 日期
    private String maxDate = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    public void run() {
    	
    	Calendar cal=Calendar.getInstance();

        cal.set(Calendar.DATE, 1);     //把日期设置为当月第一天

        cal.roll(Calendar.DATE, -1);   //日期回滚一天，也就是最后一天

        int max=cal.get(Calendar.DATE);
        maxDate=String.valueOf(max);
      
        nowtime = CTime.formatDate();
        yaerMonth =CTime.formatWholeDate().toString().substring(0, 7);
        nowDay = nowtime.substring(6, 8);
        nowHour = nowtime.substring(8, 10);
        System.out.println("MonthSum_timer run== "+CTime.formatWholeDate()+"  nowDay="+nowDay+"  maxDate"+maxDate+yaerMonth);
        if ((Integer.parseInt(nowHour) >= 23)&&(nowDay.equals(maxDate))) { //当月末最后一天的23点到24点之间执行任务（统计县（区）级的上个月的电量 电费）
            
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DATE, -1);
            Date perday = calendar.getTime();
            preSday = sdfk.format(perday);
              
            DataBase oracledb = new DataBase();
            
            try {
            	
                oracledb.getConnection();
                ResultSet rs0 = oracledb.queryAll("select distinct(xian),xianname,shi,shiname from allinfo_view");
                int i=0;
                while(rs0.next()){
                 	 String xian=rs0.getString("xian");
               	     String xianname=rs0.getString("xianname");
                	 String shi=rs0.getString("shi");
                	 String shiname=rs0.getString("shiname");
                	 StringBuffer st = new StringBuffer();
                     StringBuffer st1 = new StringBuffer();
                     StringBuffer st2 = new StringBuffer();
                	 System.out.println(i++);
                	 ZdlxSum zdlxsumdl=new ZdlxSum();
                     ZdlxSum zdlxsumdf=new ZdlxSum();  
                     st.append("select v.jztype,sum(a.actualdegree) as sum from allinfo_view v,ammeterdegrees a where v.xuni='0' and v.dbyt='dbyt01' and v.dbid=a.ammeterid_fk and a.MANUALAUDITSTATUS='1' and  a.endmonth='"+yaerMonth+"' and v.xian='"+rs0.getString(1)+"' group by v.jztype");
                   
                     ResultSet rs = oracledb.queryAll(st.toString()); 
                     System.out.println(st.toString()+"电量");
                     try{
	                     while (rs.next()) {
	                      if ("zdlx01".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
	              		      zdlxsumdl.setZdlx01(rs.getString("sum"));
		              	   }
		              	   if ("zdlx02".equals(rs.getString("jztype"))){
		              		   zdlxsumdl.setZdlx02(rs.getString("sum"));
		              	   }
		              	   if ("zdlx03".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx03(rs.getString("sum"));
		              	   }
		              	   if ("zdlx04".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx04(rs.getString("sum"));
		              	   }
		              	   if ("zdlx05".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx05(rs.getString("sum"));
		              	   }
		              	   if ("zdlx06".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx06(rs.getString("sum"));
		              	   }
		              	   if ("zdlx07".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx07(rs.getString("sum"));
		              	   }
		              	   if ("zdlx08".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx08(rs.getString("sum"));
		              	   }
		              	   if ("zdlx09".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx09(rs.getString("sum"));
		              	   }
		              	   if ("zdlx10".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx10(rs.getString("sum"));
		              	   }
		              	   if ("zdlx11".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx11(rs.getString("sum"));
		              	   }
		              	   if ("zdlx12".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx12(rs.getString("sum"));
		              	   }
		              	   if ("zdlx19".equals(rs.getString("jztype"))&& (!"".equals(rs.getString("sum"))&&(null!=rs.getString("sum")))){
		              		   zdlxsumdl.setZdlx19(rs.getString("sum"));
		              	   }
	                     }
                     }
                     catch(Exception e){
                    	 e.printStackTrace();
                     }
                     st1.append("select v.jztype, sum(e.actualpay) as sum  from allinfo_view v, ammeterdegrees a, electricfees e ");
                     st1.append("where v.xuni='0' and v.dbyt='dbyt01' and v.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeID_FK and e.MANUALAUDITSTATUS>='1' and  a.endmonth='"+yaerMonth+"' and v.xian='"+rs0.getString(1)+"' group by v.jztype");
                     System.out.println(st1.toString()+"电费");
                     ResultSet rs2 = oracledb.queryAll(st1.toString()); 
                     
                     while (rs2.next()) {
                      if ("zdlx01".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
              		      zdlxsumdf.setZdlx01(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx02".equals(rs2.getString("jztype"))){
	              		   zdlxsumdf.setZdlx02(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx03".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx03(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx04".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx04(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx05".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx05(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx06".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx06(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx07".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx07(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx08".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx08(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx09".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx09(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx10".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx10(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx11".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx11(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx12".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx12(rs2.getString("sum"));
	              	   }
	              	   if ("zdlx19".equals(rs2.getString("jztype"))&& (!"".equals(rs2.getString("sum"))&&(null!=rs2.getString("sum")))){
	              		   zdlxsumdf.setZdlx19(rs2.getString("sum"));
	              	   }
                     }
                     rs.close();
                     rs2.close();
                     zdlxsumdl.zdlx01=zdlxsumdl.getZdlx01()!=null?zdlxsumdl.getZdlx01():"0";
                     zdlxsumdl.zdlx02=zdlxsumdl.getZdlx02()!=null?zdlxsumdl.getZdlx02():"0";
                     zdlxsumdl.zdlx03=zdlxsumdl.getZdlx03()!=null?zdlxsumdl.getZdlx03():"0";
                     zdlxsumdl.zdlx04=zdlxsumdl.getZdlx04()!=null?zdlxsumdl.getZdlx04():"0";
                     zdlxsumdl.zdlx05=zdlxsumdl.getZdlx05()!=null?zdlxsumdl.getZdlx05():"0";
                     zdlxsumdl.zdlx06=zdlxsumdl.getZdlx06()!=null?zdlxsumdl.getZdlx06():"0";
                     zdlxsumdl.zdlx07=zdlxsumdl.getZdlx07()!=null?zdlxsumdl.getZdlx07():"0";
                     zdlxsumdl.zdlx08=zdlxsumdl.getZdlx08()!=null?zdlxsumdl.getZdlx08():"0";
                     zdlxsumdl.zdlx09=zdlxsumdl.getZdlx09()!=null?zdlxsumdl.getZdlx09():"0";
                     zdlxsumdl.zdlx10=zdlxsumdl.getZdlx10()!=null?zdlxsumdl.getZdlx10():"0";
                     zdlxsumdl.zdlx11=zdlxsumdl.getZdlx11()!=null?zdlxsumdl.getZdlx11():"0";
                     zdlxsumdl.zdlx12=zdlxsumdl.getZdlx12()!=null?zdlxsumdl.getZdlx12():"0";
                     zdlxsumdl.zdlx19=zdlxsumdl.getZdlx19()!=null?zdlxsumdl.getZdlx19():"0";
                     zdlxsumdf.zdlx01=zdlxsumdf.getZdlx01()!=null?zdlxsumdf.getZdlx01():"0";
                     zdlxsumdf.zdlx02=zdlxsumdf.getZdlx02()!=null?zdlxsumdf.getZdlx02():"0";
                     zdlxsumdf.zdlx03=zdlxsumdf.getZdlx03()!=null?zdlxsumdf.getZdlx03():"0";
                     zdlxsumdf.zdlx04=zdlxsumdf.getZdlx04()!=null?zdlxsumdf.getZdlx04():"0";
                     zdlxsumdf.zdlx05=zdlxsumdf.getZdlx05()!=null?zdlxsumdf.getZdlx05():"0";
                     zdlxsumdf.zdlx06=zdlxsumdf.getZdlx06()!=null?zdlxsumdf.getZdlx06():"0";
                     zdlxsumdf.zdlx07=zdlxsumdf.getZdlx07()!=null?zdlxsumdf.getZdlx07():"0";
                     zdlxsumdf.zdlx08=zdlxsumdf.getZdlx08()!=null?zdlxsumdf.getZdlx08():"0";
                     zdlxsumdf.zdlx09=zdlxsumdf.getZdlx09()!=null?zdlxsumdf.getZdlx09():"0";
                     zdlxsumdf.zdlx10=zdlxsumdf.getZdlx10()!=null?zdlxsumdf.getZdlx10():"0";
                     zdlxsumdf.zdlx11=zdlxsumdf.getZdlx11()!=null?zdlxsumdf.getZdlx11():"0";
                     zdlxsumdf.zdlx12=zdlxsumdf.getZdlx12()!=null?zdlxsumdf.getZdlx12():"0";
                     zdlxsumdf.zdlx19=zdlxsumdf.getZdlx19()!=null?zdlxsumdf.getZdlx19():"0";

                     
                     zdlxsumdl.setTotle(String.valueOf(Integer.parseInt(zdlxsumdl.getZdlx01()!=null?zdlxsumdl.getZdlx01():"0")+Integer.parseInt(zdlxsumdl.getZdlx02()!=null?zdlxsumdl.getZdlx02():"0")
                 			+Integer.parseInt(zdlxsumdl.zdlx03)+Integer.parseInt(zdlxsumdl.getZdlx04()!=null?zdlxsumdl.getZdlx04():"0")+Integer.parseInt(zdlxsumdl.getZdlx05()!=null?zdlxsumdl.getZdlx05():"0")+
                 			Integer.parseInt(zdlxsumdl.getZdlx06()!=null?zdlxsumdl.getZdlx06():"0")+Integer.parseInt(zdlxsumdl.getZdlx07()!=null?zdlxsumdl.getZdlx07():"0")+Integer.parseInt(zdlxsumdl.getZdlx08()!=null?zdlxsumdl.getZdlx08():"0")+
                 			Integer.parseInt(zdlxsumdl.getZdlx09()!=null?zdlxsumdl.getZdlx09():"0")+Integer.parseInt(zdlxsumdl.getZdlx10()!=null?zdlxsumdl.getZdlx10():"0")+Integer.parseInt(zdlxsumdl.getZdlx11()!=null?zdlxsumdl.getZdlx11():"0")+
                 			Integer.parseInt(zdlxsumdl.getZdlx12()!=null?zdlxsumdl.getZdlx12():"0")+Integer.parseInt(zdlxsumdl.getZdlx19()!=null?zdlxsumdl.getZdlx19():"0")));
                     zdlxsumdf.setTotle(String.valueOf(Float.parseFloat(zdlxsumdf.getZdlx01()!=null?zdlxsumdf.getZdlx01():"0")+Float.parseFloat(zdlxsumdf.getZdlx02()!=null?zdlxsumdf.getZdlx02():"0")
                  			+Float.parseFloat(zdlxsumdf.getZdlx03()!=null?zdlxsumdf.getZdlx03():"0")+Float.parseFloat(zdlxsumdf.getZdlx04()!=null?zdlxsumdf.getZdlx04():"0")+Float.parseFloat(zdlxsumdf.getZdlx05()!=null?zdlxsumdf.getZdlx05():"0")+
                  			Float.parseFloat(zdlxsumdf.getZdlx06()!=null?zdlxsumdf.getZdlx06():"0")+Float.parseFloat(zdlxsumdf.getZdlx07()!=null?zdlxsumdf.getZdlx07():"0")+Float.parseFloat(zdlxsumdf.getZdlx08()!=null?zdlxsumdf.getZdlx08():"0")+
                  			Float.parseFloat(zdlxsumdf.getZdlx09()!=null?zdlxsumdf.getZdlx09():"0")+Float.parseFloat(zdlxsumdf.getZdlx10()!=null?zdlxsumdf.getZdlx10():"0")+Float.parseFloat(zdlxsumdf.getZdlx11()!=null?zdlxsumdf.getZdlx11():"0")+
                  			Float.parseFloat(zdlxsumdf.getZdlx12()!=null?zdlxsumdf.getZdlx12():"0")+Float.parseFloat(zdlxsumdf.getZdlx19()!=null?zdlxsumdf.getZdlx19():"0")));
                     System.out.println(zdlxsumdl.getTotle()+"dl*df"+zdlxsumdf.getTotle());
                    st2.append("insert into monthSummary (shi, shicode, xian, xiancode, dl_total, df_total, summarymonth)" +
                    		"values('"+shi+"','"+shiname+"','"+xian+"','"+xianname+"','"+zdlxsumdl.totle+"','"+zdlxsumdf.totle+"','"+yaerMonth+"')");
                    System.out.println(st2.toString()+"@@@@@@@@@@@@@@@@@@@");
                    int result = oracledb.update(st2.toString()); 
                    if(result>0){
                    	System.out.println(xianname+"县月电量。电费汇总成功！");
                    }
                };
              
            } catch (Exception de) {
                de.printStackTrace();
            }finally {
        	   
                try {
                    oracledb.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }

            }
        
        }
    }

}
