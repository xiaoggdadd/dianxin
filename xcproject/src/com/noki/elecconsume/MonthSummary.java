package com.noki.elecconsume;
import com.noki.database.DbException;
import java.text.SimpleDateFormat;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import com.noki.util.CTime;
import com.noki.imports.*;
 // <p>Title:县级电量电费月统计 
public class MonthSummary {
    public MonthSummary() {
    }
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    public boolean Summary(String xianStr,String summaryMonthStr) {
    	    boolean flag=false;
            System.out.println("县级电量电费月统计 Month== "+CTime.formatWholeDate()+"--"+xianStr);
           
            DataBase oracledb = new DataBase();
            
            try {
            	
                oracledb.getConnection();
                ResultSet rs0 = oracledb.queryAll("select xian,xianname,shi,shiname from allinfo_view where xian="+xianStr+" ");
                int i=0;
                if(rs0.next()){
                 	 String xian=rs0.getString("xian");
               	     String xianname=rs0.getString("xianname");
                	 String shi=rs0.getString("shi");
                	 String shiname=rs0.getString("shiname");
                	 StringBuffer st = new StringBuffer();
                     StringBuffer st1 = new StringBuffer();
                     StringBuffer st2 = new StringBuffer();
                	 //System.out.println(i++);
                	 ZdlxSum zdlxsumdl=new ZdlxSum();
                     ZdlxSum zdlxsumdf=new ZdlxSum();  

                     st.append("select sum(a.blhdl) as sum from ammeterdegrees a where  a.zdcb = '0' and a.manualauditstatus = '1' and  a.endmonth='"+summaryMonthStr+"' and a.ammeterid_fk in (select am.ammeterid from ammeters am where am.ammeteruse = 'dbyt01' and am.stationid in (select z.id from zhandian z where z.xuni = '0' and z.xian='"+xian+"'))");
                     System.out.println("电量"+st.toString());
                     ResultSet rs = oracledb.queryAll(st.toString()); 
                     while (rs.next()) {
                         //更新电量 all
                    	 zdlxsumdl.setTotle(rs.getString("sum")!=null?rs.getString("sum"):"0");
	              	  
                     }
                     rs.close();
                     
                     st1.append("select sum(e.actualpay) as sum  from  electricfees e ");
                     st1.append("where e.MANUALAUDITSTATUS='2' and e.AMMETERDEGREEID_FK in (select a.AMMETERDEGREEID from ammeterdegrees a where  a.zdcb = '0' and a.manualauditstatus = '1' and  a.endmonth='"+summaryMonthStr+"' and a.ammeterid_fk in (select am.ammeterid from ammeters am where am.ammeteruse = 'dbyt01' and am.stationid in (select z.id from zhandian z where z.xuni = '0' and z.xian='"+xian+"')))");
                     System.out.println("电费"+st1.toString());
                     ResultSet rs2 = oracledb.queryAll(st1.toString()); 
                     
                     while (rs2.next()) {
                    //更新电费all
                    	 zdlxsumdf.setTotle(rs2.getString("sum")!=null?rs2.getString("sum"):"0");
	              	  
                     }
                     rs2.close();
                     
                    System.out.println(zdlxsumdl.getTotle()+"dl*df"+zdlxsumdf.getTotle());
                    String sql="select * from Monthsummary m where m.xian='"+xian+"' and m.summarymonth='"+summaryMonthStr+"'";
                    int result = oracledb.update(sql.toString()); 
                    if(result>0){
                    	sql="update Monthsummary  set dl_total='"+zdlxsumdl.totle+"',df_total='"+zdlxsumdf.totle+"' " +
                    		"where xian='"+xian+"' and summarymonth='"+summaryMonthStr+"'";
                    	 System.out.println("@1"+sql.toString());
                    	result =oracledb.update(sql);
                    }else{
                    	
                    	sql="insert into monthSummary (shi, shicode, xian, xiancode, dl_total, df_total, summarymonth) values('"+shi+"','"+shiname+"','"+xian+"','"+xianname+"','"+zdlxsumdl.totle+"','"+zdlxsumdf.totle+"','"+summaryMonthStr+"')";
                    	 System.out.println("@2"+sql.toString());
                    	result =oracledb.update(sql);
                    }
//                    st2.append("if EXISTS (select * from Monthsummary m where m.xian='"+xian+"' and m.summarymonth='"+summaryMonthStr+"' )  " +
//                    		"update Monthsummary  set dl_total='"+zdlxsumdl.totle+"',df_total='"+zdlxsumdf.totle+"' " +
//                    		"where xian='"+xian+"' and summarymonth='"+summaryMonthStr+"' else  " +
//                    		"insert into monthSummary (shi, shicode, xian, xiancode, dl_total, df_total, summarymonth) values('"+shi+"','"+shiname+"','"+xian+"','"+xianname+"','"+zdlxsumdl.totle+"','"+zdlxsumdf.totle+"','"+summaryMonthStr+"');");
                    System.out.println("@@@@@@@@@@@@@@@@@@@"+sql.toString());
                   // int result = oracledb.update(st2.toString()); 
                    //int result =1;
                    if(result>0){
                    	flag=true;
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
         return flag;
        }
    
 }
