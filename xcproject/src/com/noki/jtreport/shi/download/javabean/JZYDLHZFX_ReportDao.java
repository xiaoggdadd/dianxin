package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 基站用电量汇总分析表.xls(表二)
 * @author Administrator
 *
 */
public class JZYDLHZFX_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
		// TODO Auto-generated method stub
		String power=getAccount().getShi();
		WritableSheet sheet=book.getSheet(0);
		String mon=getMonth();
		String accountmonth=getAccountmonth();
		String str="";
		try {
			//获取查询时间
			//Calendar ca=Calendar.getInstance();
			//int year=ca.get(Calendar.YEAR);
			//int month=ca.get(Calendar.MONTH)+1;
			
		/**	
		 * String month1="";
			if(month-3<=0){
				year=year-1;
				month=month-3+12;
			}else{
				month=month-1;
			}
			if(month<10){
				month1="0"+month;
			}else{
				month1=""+month;
			}
		 String date=year+"-"+month1;// 本月
		 String date1=year+"-"+"0"+1;//本年第一个月
		 String date2=year-1+"-"+month1;//同比本月
		 **/
			if("".equals(mon)||null==mon){
				mon=accountmonth;
			}
			int year=Integer.parseInt(mon.substring(0, 4));
			String month=mon.substring(5, 7);
			String date=mon;
			String date1=year+"-"+"0"+1;
			String date2=year-1+"-"+month;
			int time=Integer.parseInt(month);
			
			System.out.println(date+"*"+date1+"."+date2+"@"+mon.substring(0,7));
			//城市
			String shiName="";
			String shiNameql="select distinct shiname from Allinfo_View where shi='"+power+"' ";    
			ResultSet shinameResult=stmt.executeQuery(shiNameql);
		    if(shinameResult.next()){
		    	shiName=shinameResult.getString(1);
		    }
		    sheet.addCell(new Label(0,3,shiName));            
		    //物理基站个数
			String wlZhanshusql="select count(distinct zdcode)  from zhandian  where jztype='zdlx08' and shi='"+power+"'";   
			ResultSet  rs1=stmt.executeQuery(wlZhanshusql);
			String wl="";
			if(rs1.next()){
				wl=rs1.getString(1);
			}
			sheet.addCell(new Label(1,3,wl));
			//逻辑站数
			String ljzssql="select sum(k.ljzs)  from zhandian z,zhandiankz k where k.zdid=z.id and z.jztype='zdlx08' and shi='"+power+"'";
			String ljzs="";
			ResultSet rs2=stmt.executeQuery(ljzssql);
			if(rs2.next()){
				ljzs=rs2.getString(1);
			}
			sheet.addCell(new Label(2,3,ljzs));
			//载频个数
			String zpgssql="select sum(k.zpsl)  from zhandian z,zhandiankz k where k.zdid=z.id and z.jztype='zdlx08' and shi='"+power+"'";
			String zpgs="";
			ResultSet rs3=stmt.executeQuery(zpgssql);
			if(rs3.next()){
				zpgs=rs3.getString(1);
			}
			sheet.addCell(new Label(3,3,zpgs));
		 DecimalFormat format=new 	DecimalFormat("0.0000");
		 //本年累计
		 String benniansql="select sum(a.blhdl) from ZHANDIAN t, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and t.xuni = '0' and d.dbyt = 'dbyt01' " +
		 		"and d.zdid = t.id and a.manualauditstatus>='1' and t.shi = '"+power+"' and to_char(a.endmonth,'yyyy-mm')>='"+date1+"' and to_char(a.endmonth,'yyyy-mm')<='"+date+"'";
		 String bennian="";
		 ResultSet rs4=stmt.executeQuery(benniansql);
		 if(rs4.next()){
			 bennian=rs4.getString(1);
		 }
		 if(bennian==null)
		 {
			 bennian=0+"";
		 }
		 String benn=format.format(Double.parseDouble(bennian)/10000);
		 sheet.addCell(new Label(4,3,benn));
		 //同比耗电量
		 String tongbisql="select sum(a.blhdl) from ZHANDIAN t, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and t.xuni = '0' and d.dbyt = 'dbyt01' " +
		 		"and d.zdid = t.id and a.manualauditstatus>='1' and t.shi = '"+power+"' and to_char(a.endmonth,'yyyy-mm')='"+date2+"'";
		 String tongbi="";
		 ResultSet rs5=stmt.executeQuery(tongbisql);
		 if(rs5.next()){
			 tongbi=rs5.getString(1);
		 }
		 if(tongbi==null){
			 tongbi=0+"";
		 }
		 String tong=format.format(Double.parseDouble(tongbi)/10000);
		 sheet.addCell(new Label(5,3,tong));
		 //本月用电量
		 String benyuesql="select sum(a.blhdl) from ZHANDIAN t, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and t.xuni = '0' and d.dbyt = 'dbyt01' " +
		 		"and d.zdid = t.id and a.manualauditstatus>='1' and t.shi = '"+power+"' and to_char(a.endmonth,'yyyy-mm')='"+date+"'";
		 String benyue="";
		 ResultSet rs6=stmt.executeQuery(benyuesql);
		 if(rs6.next()){
			 benyue=rs6.getString(1);
		 }
		 if(benyue==null){
			 benyue=0+"";
		 }
		 String beny=format.format(Double.parseDouble(benyue)/10000);
		 sheet.addCell(new Label(6,3,beny));
		 //本年累计电费
		 
		 String benniandfsql="select sum(e.actualpay) from ZHANDIAN t,electricfees e, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and a.ammeterdegreeid=e.ammeterdegreeid_fk " +
		 		"and t.xuni = '0' and d.dbyt = 'dbyt01' and d.zdid = t.id and e.manualauditstatus>='1' and t.shi = '"+power+"'  and to_char(a.endmonth,'yyyy-mm')>='"+date1+"' and to_char(a.endmonth,'yyyy-mm')<='"+date+"'";
		 String benniandf="";
		 System.out.println(benniandfsql.toString());
		 ResultSet rs7=stmt.executeQuery(benniandfsql);
		 if(rs7.next()){
			 benniandf=rs7.getString(1);
		 }
		 if(benniandf==null)
		 {
			 benniandf=0.0+"";
		 }
		 String benndf=format.format(Double.parseDouble(benniandf)/10000);
		 sheet.addCell(new Label(7,3,benndf));
		 //同比电费
		 String tongbidfsql="select sum(e.actualpay) from ZHANDIAN t,electricfees e, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and a.ammeterdegreeid=e.ammeterdegreeid_fk " +
		 		"and t.xuni = '0' and d.dbyt = 'dbyt01' and d.zdid = t.id and e.manualauditstatus>='1' and t.shi = '"+power+"'  and to_char(a.endmonth,'yyyy-mm')='"+date2+"'";
		 String tongbidf="";
		 ResultSet rs8=stmt.executeQuery(tongbidfsql);
		 System.out.println("--"+tongbidfsql.toString());
		 if(rs8.next()){
			 tongbidf=rs8.getString(1);
		 }
		 if(tongbidf==null){
			 tongbidf=0.0+"";
		 }
		 String tongdf=format.format(Double.parseDouble(tongbidf)/10000);
		 sheet.addCell(new Label(8,3,tongdf));
		 //本月电费
		 String benyuedfsql="select sum(e.actualpay) from ZHANDIAN t,electricfees e, ammeterdegrees a, dianbiao d " +
		 		"where t.jztype = 'zdlx08' and a.ammeterid_fk = d.dbid and a.ammeterdegreeid=e.ammeterdegreeid_fk " +
		 		"and t.xuni = '0' and d.dbyt = 'dbyt01' and d.zdid = t.id  and e.manualauditstatus>='1' and t.shi = '"+power+"'  and to_char(a.endmonth,'yyyy-mm')='"+date+"'";
		 String benyuedf="";
		 ResultSet rs9=stmt.executeQuery(benyuedfsql);
		 if(rs9.next()){
			 benyuedf=rs9.getString(1);
		 }
		 if(benyuedf==null){
			 benyuedf=0.0+"";
		 }
		 String benydf=format.format(Double.parseDouble(benyuedf)/10000);
		 sheet.addCell(new Label(9,3,benydf));
		 //本年累计每载频电量
		 DecimalFormat mat=new DecimalFormat("0");
		 String bennianzaipin=mat.format(Double.parseDouble(bennian)/Double.parseDouble(zpgs)/time);
		 
		 sheet.addCell(new Label(10,3,bennianzaipin));
		 //同比每载频电量
		 String tongbizaipin=mat.format(Double.parseDouble(tongbi)/Double.parseDouble(zpgs));
		 
		 sheet.addCell(new Label(11,3,tongbizaipin));
		 //本月每载频电量
		 String benyuezaipin="";
		
		 benyuezaipin=mat.format(Double.parseDouble(benyue)/Double.parseDouble(zpgs));
		 sheet.addCell(new Label(12,3,benyuezaipin));
		 //本年累计每载频电费
		 DecimalFormat mat1=new DecimalFormat("0.00");
		 String bennianzpdf=mat1.format(Double.parseDouble(benniandf)/Double.parseDouble(zpgs)/time);
		 
		 sheet.addCell(new Label(13,3,bennianzpdf));
		 //同比每载频电费
		 String tongbizpdf=mat1.format(Double.parseDouble(tongbidf)/Double.parseDouble(zpgs));
		 
		 sheet.addCell(new Label(14,3,tongbizpdf));
		 //本月每载频电费
		 
		 String benyuezpdf="";
		 benyuezpdf=mat1.format(Double.parseDouble(benyuedf)/Double.parseDouble(zpgs));
		 sheet.addCell(new Label(15,3,benyuezpdf));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
