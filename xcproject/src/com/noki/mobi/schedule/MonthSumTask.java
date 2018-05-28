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
 * <p>Title:�ؼ�������� �Զ� ��ͳ�� </p>
 *
 * <p>Description: ������������Զ���ȡ�ƻ�  Timer  </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: ptac</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MonthSumTask extends TimerTask {
    public MonthSumTask() {
    }

    private String nowtime = "";
    private String yaerMonth = "";
    private String nowDay = "";
    private String nowHour = "";
    private String preSday = "";// ����
    private String maxDate = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    public void run() {
    	
    	Calendar cal=Calendar.getInstance();

        cal.set(Calendar.DATE, 1);     //����������Ϊ���µ�һ��

        cal.roll(Calendar.DATE, -1);   //���ڻع�һ�죬Ҳ�������һ��

        int max=cal.get(Calendar.DATE);
        maxDate=String.valueOf(max);
      
        nowtime = CTime.formatDate();
        yaerMonth =CTime.formatWholeDate().toString().substring(0, 7);
        nowDay = nowtime.substring(6, 8);
        nowHour = nowtime.substring(8, 10);
        System.out.println("MonthSum_timer run== "+CTime.formatWholeDate()+"  nowDay="+nowDay+"  maxDate"+maxDate+yaerMonth);
        if ((Integer.parseInt(nowHour) >= 23)&&(nowDay.equals(maxDate))) { //����ĩ���һ���23�㵽24��֮��ִ������ͳ���أ����������ϸ��µĵ��� ��ѣ�
            
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
                   /*  select sum(t.actualdegree)
                     from ammeterdegrees t
                    where t.zdcb = '0'
                      and t.manualauditstatus = '1'
                      and t.endmonth >= '2011-12'
                      and t.endmonth <= '2011-12'
                      and t.ammeterid_fk in
                          (select a.ammeterid from ammeters a where a.ammeteruse = 'dbyt01' and a.stationid in (select id from zhandian z where 1 = 1 and z.shi = '13701'and z.xuni = '0'))*/

                     st.append("select sum(a.actualdegree) as sum from ammeterdegrees a where  a.zdcb = '0' and a.manualauditstatus = '1' and  a.endmonth='"+yaerMonth+"' and a.ammeterid_fk in (select am.ammeterid from ammeters am where am.ammeteruse = 'dbyt01' and am.stationid in (select id from zhandian z where z.xuni = '0' and z.xian='"+rs0.getString(1)+"'))");
                     System.out.println(st.toString()+"����");
                     ResultSet rs = oracledb.queryAll(st.toString()); 
                     while (rs.next()) {
                         
                    	 zdlxsumdl.setTotle(rs.getString("sum")!=null?rs.getString("sum"):"0");
	              	  
                     }
                     rs.close();
                     
                     st1.append("select sum(e.actualpay) as sum  from  electricfees e ");
                     st1.append("where e.MANUALAUDITSTATUS>='1' and e.AMMETERDEGREEID_FK in (select a.AMMETERDEGREEID from ammeterdegrees a where  a.zdcb = '0' and a.manualauditstatus = '1' and  a.endmonth='"+yaerMonth+"' and a.ammeterid_fk in (select am.ammeterid from ammeters am where am.ammeteruse = 'dbyt01' and am.stationid in (select id from zhandian z where z.xuni = '0' and z.xian='"+rs0.getString(1)+"')))");
                     System.out.println(st1.toString()+"���");
                     ResultSet rs2 = oracledb.queryAll(st1.toString()); 
                     
                     while (rs2.next()) {
                    
                    	 zdlxsumdf.setTotle(rs2.getString("sum")!=null?rs2.getString("sum"):"0");
	              	  
                     }
                     rs2.close();
                     
                    System.out.println(zdlxsumdl.getTotle()+"dl*df"+zdlxsumdf.getTotle());
                    st2.append("insert into monthSummary (shi, shicode, xian, xiancode, dl_total, df_total, summarymonth)" +
                    		"values('"+shi+"','"+shiname+"','"+xian+"','"+xianname+"','"+zdlxsumdl.totle+"','"+zdlxsumdf.totle+"','"+yaerMonth+"')");
                    System.out.println(st2.toString()+"@@@@@@@@@@@@@@@@@@@");
                    int result = oracledb.update(st2.toString()); 
                    if(result>0){
                    	System.out.println(xianname+"���µ�������ѻ��ܳɹ���");
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
