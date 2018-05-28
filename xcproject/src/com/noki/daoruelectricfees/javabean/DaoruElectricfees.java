package com.noki.daoruelectricfees.javabean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.daoruelectricfees.vo.ElectricCurrentVo;
import com.noki.daoruelectricfees.vo.ElectricfeesVo;
import com.noki.daoruelectricfees.vo.ElectricmeterVo;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.jizhan.SiteManage;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.CommonBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.noki.util.CTime;

public class DaoruElectricfees {
	private static BigDecimal allmoney;
	private String daoruRen;
	
	public String getDaoruRen() {
		return daoruRen;
	}

	public void setDaoruRen(String daoruRen) {
		this.daoruRen = daoruRen;
	}

	String sqlnote = "";
	  Vector wrongContent = new Vector();
	  public DataBase db;
	  public DaoruElectricfees() {
	    try {
	      db = new DataBase();
	      db.connectDb();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public void closeDb() {
	    try {
	      db.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	  private ResultSet rs = null;
	  //电费单导入
	  public synchronized Vector getWrong(Vector content,CountForm cform,String accountname,String loginId) {
		  String lastdatetime="";
		  Date date1 = null,date2=null;
		  String thisdatetime="",startmonth="",endmonth="",inputdatetime="",accountmonth="",notetime="",kptime="",paydatetime="";
		  String dbid1="";  String blhdl=""; String actualpay="";
		  //System.out.println("电量"+content);
		  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Object[] a=content.toArray();
		  AmmeterDegreeFormBean formBean= null;
		  ElectricFeesFormBean feesformBean=null;
		  DaoruDianLiang bean = new DaoruDianLiang();
		  DaoruDianFei fees = new DaoruDianFei();
		  AutoAuditBean abean = new AutoAuditBean();
		  	 ArrayList fylist = new ArrayList();
		  	 fylist = abean.getPageData(1,"");
		  for(int i=1;i<a.length;i++){
			  Vector<String> row=new Vector<String>();
			  Object[] b=((Vector)a[i]).toArray();
//			  for(int ii=0;ii<b.length;ii++){
//				  System.out.println("-"+b[ii]+"-");
//			  }
			  dbid1=b[3].toString().trim();
			  blhdl=b[9].toString().trim();
			
			  actualpay=b[17].toString().trim();
			  String did="",dianfei="",beilv1="1",linelosstype1="",linelossvalue1="",edhdl="0";
			  String zy1="",zy2="",zy3="",zy4="",zy5="",zy6="";
			 // System.out.println("_-"+dbid1+"--"+b[9].toString().trim()+"--");
			  String  sql1="SELECT aa.ID,aa.DIANFEI,aa.BEILV,aa.LINELOSSTYPE,aa.LINELOSSVALUE,aa.EDHDL," +
			  		"max(aa.ZY1),max(aa.ZY2),max(aa.ZY3),max(aa.ZY4),max(aa.ZY5),max(aa.ZY6) " +
			  		"from (SELECT D.ID, Z.DIANFEI, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI END)AS ZY1," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI END)AS ZY2," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI END)AS ZY3," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI END)AS ZY4," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI END)AS ZY5,  " +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx06' THEN S.DBILI END)AS ZY6  " +
			  		" FROM ZHANDIAN Z, DIANBIAO D,SBGL S   WHERE Z.ID = D.ZDID   AND D.DBYT = 'dbyt01' AND D.DBQYZT='1' AND Z.QYZT='1' " +
			  		"  AND S.DIANBIAOID(+)=D.DBID AND D.DBID=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?))aa " +
			  		"group by  aa.ID,aa.DIANFEI,aa.BEILV,aa.LINELOSSTYPE, aa.LINELOSSVALUE,aa.EDHDL";
			   ResultSet rs=null;
			  // DataBase db=new DataBase();
			   try{
			   //更改中2012-12-3 
			   Properties pro=new Properties();
			   pro.setProperty("1", dbid1);
			   pro.setProperty("2", loginId);
			  // db.connectDb();
			 //  System.out.println(sql1);
			 //  System.out.println(pro.getProperty("1"));
			 //  System.out.println(pro.getProperty("2"));
			   System.out.println("电费单导入查询语句："+sql1);
			   rs=db.queryAll001(sql1,pro);
			   while(rs.next()){
				   did=rs.getString(1);
				   dianfei=rs.getString(2);
				   beilv1=rs.getString(3);
				   linelosstype1=rs.getString(4);
				   linelossvalue1=rs.getString(5);
				   edhdl=rs.getString(6);
				   zy1=rs.getString(7);
				   zy2=rs.getString(8);
				   zy3=rs.getString(9);
				   zy4=rs.getString(10);
				   zy5=rs.getString(11);
				   zy6=rs.getString(12);
			   }
			   closeDb();
			   }catch(Exception e){
				   e.printStackTrace();
			   }finally{
				   if(rs != null){
					   try {
						   rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   }
				   
					   try {
						   db.close();
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			   }
			   
			  if("".equals(did)||null==did){
				  System.out.println("未查到电表"+dbid1);
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[0].toString()+b[2].toString()+"电表"+dbid1);
				  wrongContent.add(row);
				  continue;
			  }
			  String str44=b[4].toString().trim();
			  String str4 = str44.replaceAll(" ", "");

			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  
			//  System.out.println("eeee"+str4+"eeee");
			  if(pattern.matcher(str4).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次电表读数格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str4==""){		
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次电表读数为空"); 
				  wrongContent.add(row);
				  continue;
				  }
			  String str55=b[5].toString().trim();
			  String str5 = str55.replaceAll(" ", "");
			  if(pattern.matcher(str5).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次电表读数格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str5==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次电表读数为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[6].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次抄表时间为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[7].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次抄表时间为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  String str88=b[8].toString().trim();
			  String str8 = str88.replaceAll(" ", "");
			  if(pattern1.matcher(str8).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"用电量调整格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str8.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"用电量调整为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str99=b[9].toString().trim();
			  String str9 = str99.replaceAll(" ", "");
			 // System.out.println("-1-"+str9+"--"+pattern.matcher(str9).matches());
			  if(pattern1.matcher(str9).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				 // System.out.println("-2-"+str9+"--");
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际用电量格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else  if(str9.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				//  System.out.println("-3-"+str9+"--");
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际用电量为空");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[10].toString().trim().equals("")||b[11].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"起始结束月份为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str155=b[15].toString().trim();
			  String str15 = str155.replaceAll(" ", "");
			  if(pattern.matcher(str15).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"单价格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str15.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"单价为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str166=b[16].toString().trim();
			  String str16 = str166.replaceAll(" ", "");
			  if(pattern1.matcher(str16).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"费用调整格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str16.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"费用调整为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str177=b[17].toString().trim();
			  String str17 = str177.replaceAll(" ", "");
			  if(pattern1.matcher(str17).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际电费金额格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str17.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际电费金额为空");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[18].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"报账月份为空");
				  wrongContent.add(row);
				  continue;
			  }
			  
			 Pattern pattern3 = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{2}|[0-9]{2}-[0-9]{2}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{2}");
			 Pattern pattern4 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");
			 String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
	             + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
	             + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
	             + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
			Pattern pattern5 = Pattern.compile(datePattern2); 
			  String str66=b[6].toString().trim();
			  lastdatetime = str66.replaceAll(" ", "");		
			  if((b[6].toString().trim().contains("/")&&pattern4.matcher(lastdatetime).matches()==true)||b[6].toString().trim().contains("-")&&isValidDate(lastdatetime)==true){
				  if(b[6].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date =sdf.parse(lastdatetime);
		        	 lastdatetime=sdf1.format(date);	
				} catch (ParseException e) {  
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  }else{
					  lastdatetime=str66.replaceAll(" ", "");
				  }
		    }else{
		    	 for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次抄表时间为空   或者   格式不正确");
				  wrongContent.add(row);
				  continue;
		    }
			 if(pattern3.matcher(lastdatetime).matches()==true){
				 String lastdate="20"+lastdatetime;
				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date;
					try {
						date = sdf1.parse(lastdate);
						lastdatetime=sdf1.format(date);
						//System.out.println("lastdatetime+=+++"+lastdatetime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	        	 
			 }
			  if(pattern5.matcher(lastdatetime).matches()==false){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次抄表时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
			  String str77=b[7].toString().trim();
			  thisdatetime = str77.replaceAll(" ", "");  
			  if((b[7].toString().trim().contains("/")&&pattern4.matcher(thisdatetime).matches()==true)||b[7].toString().trim().contains("-")&&isValidDate(thisdatetime)==true){
			  if(b[7].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date =sdf.parse(thisdatetime);
		        	 thisdatetime=sdf1.format(date);
		        	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  }else{
					  thisdatetime=str77.replaceAll(" ", "");
				  }
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次抄表时间为空   或者   格式不正确");
				  wrongContent.add(row);
				  continue;
			  }
			  if(pattern3.matcher(thisdatetime).matches()==true){
					 String lastdate="20"+thisdatetime;
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date;
						try {
							date = sdf1.parse(lastdate);
							thisdatetime=sdf1.format(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	        	 
				 }
			  if(pattern5.matcher(thisdatetime).matches()==false){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次抄表时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
			  SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
	     	 try {
					Date ss =sdf4.parse(lastdatetime);
					Date ee =sdf4.parse(thisdatetime);
					
					 if(ss.getTime()>ee.getTime()){
						 for(int j=0;j<b.length;j++){
				       		   row.add(b[j].toString().trim());
				       	      }
						  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"上次抄表时间大于本次抄表时间");
						  wrongContent.add(row);
						  continue;
					 }
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
			  String str100=b[10].toString().trim();
			  startmonth = str100.replaceAll(" ", "");
			  if((pattern2.matcher(startmonth).matches()==true&&(b[10].toString().trim().contains("/")&&b[10].toString().trim().length()<=7))||(pattern2.matcher(startmonth).matches()==true&&(b[10].toString().trim().contains("-")&&b[10].toString().trim().length()<=7))){
		         if(b[10].toString().trim().contains("/")){
				  try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		        	date1 =sdf.parse(startmonth);
		        	 startmonth=sdf1.format(date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         }else{
		        	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		        	 if(b[10].toString().trim().length()<7){
							try {
								Date end=sdf1.parse(b[10].toString().trim());
								startmonth=sdf1.format(end);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
					 }else{
						 startmonth=str100.replaceAll(" ", ""); 
					 }
				  } 	        
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"起始年月为空或者格式不正确");
				  wrongContent.add(row);
				  continue;
			  }	
			  String str111=b[11].toString().trim();
			  endmonth = str111.replaceAll(" ", ""); 
			  if((pattern2.matcher(endmonth).matches()==true&&(b[11].toString().trim().contains("/")&&b[11].toString().trim().length()<=7))||(pattern2.matcher(endmonth).matches()==true&&(b[11].toString().trim().contains("-")&&b[11].toString().trim().length()<=7))){
				  if(b[11].toString().trim().contains("/")){
				  try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		        	 date2 =sdf.parse(endmonth);
		        	 endmonth=sdf1.format(date2);	        	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  }else{
					  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
					 if(b[11].toString().trim().length()<7){
							try {
								Date end=sdf1.parse(endmonth);
								 endmonth=sdf1.format(end);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						 
					 }else{
						 endmonth=str111.replaceAll(" ", "");
					 }
	 
				  } 
				  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		        	 try {
						Date ss =sdf1.parse(startmonth);
						Date ee =sdf1.parse(endmonth);
						
						 if(ss.getTime()>ee.getTime()){
							 for(int j=0;j<b.length;j++){
					       		   row.add(b[j].toString().trim());
					       	      }
							  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"起始月份大于结束月份");
							  wrongContent.add(row);
							  continue;
						 }
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"结束年月为空或者格式不正确");
				  wrongContent.add(row);
				  continue;
			  }	  
			  if(!b[13].toString().trim().equals("")&&b[13].toString().trim()!=null){
				  String str133=b[13].toString().trim();
				  inputdatetime = str133.replaceAll(" ", ""); 
			  if((b[13].toString().trim().contains("/")&&pattern4.matcher(inputdatetime).matches()==true)||b[13].toString().trim().contains("-")&&isValidDate(inputdatetime)==true){
				  if(b[13].toString().trim().length()!=10){
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"抄表时间格式不正确!!");
					  wrongContent.add(row);
					  continue;
				  }
			  if(b[13].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date =sdf.parse(inputdatetime);
		        	 inputdatetime=sdf1.format(date);
		        	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				  }else{
					 
					  inputdatetime=str133.replaceAll(" ", "");
				  } 
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"抄表时间为空");
				  wrongContent.add(row);
				  continue;
			  }
			  if(pattern3.matcher(inputdatetime).matches()==true){
					 String lastdate="20"+inputdatetime;
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date;
						try {
							date = sdf1.parse(lastdate);
							inputdatetime=sdf1.format(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	        	 
				 }
			  if(pattern5.matcher(inputdatetime).matches()==false){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"抄表时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
			  }else{
				  inputdatetime=b[21].toString().trim();
			  }
			
			
			  String str188=b[18].toString().trim();
			  accountmonth = str188.replaceAll(" ", ""); 		 
			  if((pattern2.matcher(accountmonth).matches()==true&&(b[18].toString().trim().contains("/")&&b[18].toString().trim().length()<=7))||(pattern2.matcher(accountmonth).matches()==true&&(b[18].toString().trim().contains("-")&&b[18].toString().trim().length()<=7))){
				  if(b[18].toString().trim().contains("/")){   
				  try {
				        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
				        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
				        	Date date =sdf.parse(accountmonth);
				        	 accountmonth=sdf1.format(date);
				        	
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }else{
						 
					  accountmonth=str188.replaceAll(" ", ""); 
				  } 
						
						 
					  }else{
						  for(int j=0;j<b.length;j++){
				       		   row.add(b[j].toString().trim());
				       	      }
						  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"报账月份为空或者格式不正确");
						  wrongContent.add(row);
						  continue;
					  }
//			  String str177=b[17].toString().trim();
//			  String str17 = str177.replaceAll(" ", "");
//			  if(pattern.matcher(str17).matches()==false){	  
//				  for(int j=0;j<b.length;j++){
//		      		   row.add(b[j].toString().trim());
//		      	      }
//				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际电费金额格式不正确"); 
//				  wrongContent.add(row);
//				  continue;
//			  }else if(str17.equals("")){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"实际电费金额为空");
//				  wrongContent.add(row);
//				  continue;
//			  }
			  String str21=b[21].toString().trim();
			  if("".equals(str21)||null==str21){
				  str21="0";
			  }else if(pattern1.matcher(str21).matches()==false){	  
			  for(int j=0;j<b.length;j++){
	      		   row.add(b[j].toString().trim());
	      	      }
		  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"票据金额格式不正确"); 
			  wrongContent.add(row);
			  continue;
		  }
			  
			  
			  
			  
			  
		  if(!b[22].toString().trim().equals("")&&b[22].toString().trim()!=null){		 
			  String str211=b[22].toString().trim();
			  notetime = str211.replaceAll(" ", ""); 
			  if((b[22].toString().trim().contains("/")&&pattern4.matcher(notetime).matches()==true)||b[22].toString().trim().contains("-")&&isValidDate(notetime)){
				  if(b[22].toString().trim().contains("/")){
			          try {
			        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date =sdf.parse(b[22].toString().trim());
			        	 notetime=sdf1.format(date);
			        	
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					  } else{					 
						  notetime=str211.replaceAll(" ", ""); 
					  }
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"票据时间格式不正确");
				  wrongContent.add(row);
				  continue;
			  }
			  if(pattern3.matcher(notetime).matches()==true){
					 String lastdate="20"+notetime;
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date;
						try {
							date = sdf1.parse(lastdate);
							notetime=sdf1.format(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	        	 
				 }
			  if(pattern5.matcher(notetime).matches()==false){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"票据时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
		  }else{
			  notetime=b[22].toString().trim();
		  }
		 
		  if(!b[23].toString().trim().equals("")&&b[23].toString().trim()!=null){	
			  String str222=b[23].toString().trim();
			  kptime = str222.replaceAll(" ", ""); 
				  if((b[23].toString().trim().contains("/")&&pattern4.matcher(kptime).matches()==true)||b[23].toString().trim().contains("-")&&isValidDate(kptime)){
				  if(b[23].toString().trim().contains("/")){
			          try {
			        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date =sdf.parse(kptime);
			        	 kptime=sdf1.format(date);
			        	
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					  } else{					 
						  kptime=str222.replaceAll(" ", "");
					  }
				  }else{
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"开票时间格式不正确");
					  wrongContent.add(row);
					  continue;
				  }
				  if(pattern3.matcher(kptime).matches()==true){
						 String lastdate="20"+kptime;
						 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				        	Date date;
							try {
								date = sdf1.parse(lastdate);
								kptime=sdf1.format(date);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	        	 
					 }
				  if(pattern5.matcher(kptime).matches()==false){
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"开票时间格式不正确!!");
					  wrongContent.add(row);
					  continue;
				  }
		  }else{
			  kptime=b[23].toString().trim();
		  }
		  
		  if(!b[25].toString().trim().equals("")&&b[25].toString().trim()!=null){
			  String str244=b[25].toString().trim();
			  paydatetime = str244.replaceAll(" ", ""); 
				  if((b[25].toString().trim().contains("/")&&pattern4.matcher(paydatetime).matches()==true)||b[25].toString().trim().contains("-")&&isValidDate(paydatetime)){
				  if(b[25].toString().trim().contains("/")){
			          try {
			        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date =sdf.parse(paydatetime);
			        	 paydatetime=sdf1.format(date);
			        	
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					  } else{					  
						  paydatetime=str244.replaceAll(" ", "");
					  }
				  }else{
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"交费时间格式不正确");
					  wrongContent.add(row);
					  continue;
				  }
				  if(pattern3.matcher(paydatetime).matches()==true){
						 String lastdate="20"+paydatetime;
						 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				        	Date date;
							try {
								date = sdf1.parse(lastdate);
								paydatetime=sdf1.format(date);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	        	 
					 }
				  if(pattern5.matcher(paydatetime).matches()==false){
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"交费时间格式不正确!!");
					  wrongContent.add(row);
					  continue;
				  }
		  }else{
			  paydatetime=b[25].toString().trim();
		  }
		  
	    	     
			     DecimalFormat dmat=new DecimalFormat("0.0000");
			     String danjia1=b[15].toString().trim();
			     if("".equals(dianfei)||null==dianfei)dianfei="1";
			     if("".equals(danjia1)||null==danjia1)danjia1="1";
		         danjia1= dmat.format(Double.parseDouble(danjia1));   
		         String  danjia2= dmat.format(Double.parseDouble(dianfei));
			     //System.out.println("2系统-"+danjia2);
				 if(!danjia1.equals(danjia2)){
			    	  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
				    	  row.add("单价和站点单价不一致!系统单价为："+danjia2);	    	  
				    	  wrongContent.add(row);
				    	  continue;					    	   		      
				// }	
			}

		//比较实际用电量和实际交费金额  
	if(b[9].toString().trim()!=null&&!b[9].toString().trim().equals("")){
		//System.out.println("?????????????????");
		String lastdegrees2=b[4].toString().trim();
		String  thisdegrees2=b[5].toString().trim();
		String  floatdegrees2= b[8].toString().trim();
		String  actualdegrees2= b[9].toString().trim();
		
		String  feiyongt2= b[16].toString().trim();
		String  money2= b[17].toString().trim();
		
		if(lastdegrees2.equals("")||lastdegrees2==null)lastdegrees2="0";
		if(thisdegrees2.equals("")||thisdegrees2==null)thisdegrees2="0";
		if(floatdegrees2.equals("")||floatdegrees2==null)floatdegrees2="0";
		if(actualdegrees2.equals("")||actualdegrees2==null)actualdegrees2="0";
		
		if(feiyongt2.equals("")||feiyongt2==null)feiyongt2="0";
		if(money2.equals("")||money2==null)money2="0";
		
		
	    Double lastdegrees=Double.parseDouble(lastdegrees2);
	    Double  thisdegrees1=Double.parseDouble(thisdegrees2);
	    Double  floatdegrees= Double.parseDouble(floatdegrees2);
	    Double  actualdegrees= Double.parseDouble(actualdegrees2);
	   
	    Double  feiyongt= Double.parseDouble(feiyongt2);
	    Double  money= Double.parseDouble(money2);

		 if("".equals(linelossvalue1)||linelossvalue1==null)linelossvalue1="0";
		 if("".equals(linelosstype1)||linelosstype1==null)linelosstype1="";
		 if("".equals(beilv1)||beilv1==null)beilv1="1";
		 if("".equals(edhdl)||edhdl==null)edhdl="0";
		 
		 Double linelossvalue=Double.parseDouble(linelossvalue1);
		 Double beilv=Double.parseDouble(beilv1);
		 Double danjia=Double.parseDouble(dianfei);
		 //System.out.println("-"+beilv2+"-"+linelosstype+"-"+linelossvalue2+"-");
		
	    Double jsdegree=0.0;
	    if(linelosstype1.equals("线损比例")){
	    	if(linelossvalue==0)  linelossvalue=1.0;
	    	if(beilv==0)  beilv=1.0;
	    	//实际耗电量=倍率*（（本次电量码-起始电量码）*电损比例[或+电损值]+电量调整）
	    	jsdegree=beilv*((thisdegrees1-lastdegrees)*linelossvalue+floatdegrees);
	    	DecimalFormat price=new DecimalFormat("0.0");
	        String  jsdeg= price.format(jsdegree);
	        String  actual= price.format(actualdegrees);
	    	//System.out.println("1:"+jsdeg);
	    	//System.out.println("2:"+actual);
	    	if(actualdegrees>jsdegree+1||actualdegrees<jsdegree-1){
	    		for(int j=0;j<b.length;j++){
	     		   row.add(b[j].toString().trim());
	     	      }
	 	    	  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"的实际用电量与计算的结果不一致!!系统算出结果为："+jsdeg);	    	  
	 	    	  wrongContent.add(row);
	 	    	  continue;	
	    	}
//	    	if(!actual.equals(jsdeg)){
//	    		 
//	    	}
	    }else{
	    	if(linelossvalue==0)  linelossvalue=0.0;
	    	if(beilv==0)  beilv=1.0;
	    	jsdegree=beilv*((thisdegrees1-lastdegrees)+linelossvalue+floatdegrees);
	    	DecimalFormat price=new DecimalFormat("0.0");
	        String  jsdeg= price.format(jsdegree);
	        String  actual= price.format(actualdegrees);
	        if(actualdegrees>jsdegree+1||actualdegrees<jsdegree-1){
	        	 for(int j=0;j<b.length;j++){
	         		   row.add(b[j].toString().trim());
	         	      }
	      	    	  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"的实际用电量与计算的结果不一致!系统算出结果为："+jsdeg);	    	  
	      	    	  wrongContent.add(row);
	      	    	  continue;	
	        }
//	    	if(!jsdeg.equals(actual)){
//	   		
//	     	}
	    }
	   Double jsmoney=jsdegree*danjia+feiyongt;
	   //System.out.println("jsmoney"+jsmoney+"--"+jsdegree+"--"+danjia+"--"+feiyongt);
	   DecimalFormat price=new DecimalFormat("0.00");
	   String  jsmoneys= price.format(jsmoney);
	   String  moneys= price.format(money);
	   //System.out.println("-"+jsmoney+"-"+money+"-");
	    if(money>jsmoney+1||money<jsmoney-1){
	    	 for(int j=0;j<b.length;j++){
	     		   row.add(b[j].toString().trim());
	     	      }
	  	    	  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"的实际电费金额与计算的结果不一致!系统算出结果为："+jsmoneys);	    	  
	  	    	  wrongContent.add(row);
	  	    	  continue;
	    }
//	    if(!jsmoneys.equals(moneys)){
//	    	
//	    }   
	}
		  //判断导入的数据是否重复
    	 DecimalFormat df1 = new DecimalFormat("0.00");
    	 String lastdegrees2 = df1.format(Double.parseDouble(b[4].toString().trim().equals("")?"0":b[4].toString().trim()));
		 String thisdegrees2 = df1.format(Double.parseDouble(b[5].toString().trim().equals("")?"0":b[5].toString().trim()));
		  String sql ="SELECT 'A'FROM dbtemp_09_view T, DIANBIAO DB, DFTEMP_011_VIEW E WHERE DB.DBID = T.AMMETERID_FK AND DB.DBID =? AND T.LASTDEGREE =? AND T.THISDEGREE =? AND TO_CHAR(T.LASTDATETIME,'yyyy-mm-dd') =? AND TO_CHAR(T.THISDATETIME,'yyyy-mm-dd') =? AND T.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND T0_CHAR(E.ACCOUNTMONTH,'yyyy-mm') =?";
	     System.out.println("判断导入的数据是否重复:"+sql);
	     // DataBase db1 = new DataBase();     
	      ArrayList<String> listzd=new ArrayList<String>() ;
	      rs=null;
	      try {  
	    	Properties pro=new Properties();
	    	pro.setProperty("1", dbid1);
	    	pro.setProperty("2", lastdegrees2);
	    	pro.setProperty("3", thisdegrees2);
	    	pro.setProperty("4", lastdatetime);
	    	pro.setProperty("5", thisdatetime);
	    	pro.setProperty("6", accountmonth);
			rs = db.queryAll002(sql,pro);	
			//System.out.println("121212121--"+dbid1+"-"+lastdegrees2+"-"+thisdegrees2+"-"+lastdatetime+"-"+thisdatetime+"-"+accountmonth);
			 if(rs.next()){		
	    	  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	    	  }
	       		System.out.println("1212121不能重复导入21"+rs.toString());
		    	row.add("不能重复导入!");	    	  
		    	wrongContent.add(row);
		    	continue;
			 }}catch (Exception e) {
				 e.printStackTrace();
			 }finally {
			
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				 closeDb();
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
			}
			}
			  //System.out.println("thisdatetime"+lastdatetime+"thisdatetime"+thisdatetime+"startmonth"+startmonth+"endmonth"+endmonth+"inputdatetime"+inputdatetime+"accountmonth"+accountmonth+"notetime"+notetime+"kptime"+kptime+"paydatetime"+paydatetime);
//			  String amuuid = UUID.randomUUID().toString().trim().replace("-","");
			  
			  long uuid1 = System.currentTimeMillis();
			  String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		      String uuid = uuid2+Long.toString(uuid1).substring(3);
		      
			  //分摊电量、电费
			  //获取电表分摊比例		  
//			  AmmeterDegreeFormBean beanm=new AmmeterDegreeFormBean();
//			  ArrayList list = new ArrayList();
//			  list = beanm.getStationMhchexkt(dbid1);		  
			  String shuoshuzhuanye="";
			  String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
			  String blhdl1="",blhdl2="",blhdl3="",blhdl4="",blhdl5="",blhdl6="";
			  String actualpay1="",actualpay2="",actualpay3="",actualpay4="",actualpay5="",actualpay6="";
			  double wucha = 0.0000001;
		      DecimalFormat df = new DecimalFormat("0.00");
				  dbili1=zy1;
				  dbili2=zy2;
			      dbili3=zy3;
			      dbili4=zy4;
			      dbili5=zy5;
			      dbili6=zy6;
		       if(null==dbili1||"".equals(dbili1)){
	        	 dbili1="0.00";
		       }
		       if(null==dbili2||"".equals(dbili2)){
	        	 dbili2="0.00";
		       }
		       if(null==dbili3||"".equals(dbili3)){
	        	 dbili3="0.00";
		       }
		       if(null==dbili4||"".equals(dbili4)){
	        	 dbili4="0.00";
		       }
		       if(null==dbili5||"".equals(dbili5)){
	        	 dbili5="0.00";
		       }
		       if(null==dbili6||"".equals(dbili6)){
		        	 dbili6="0.00";
			  }
		       
		       //分摊电量(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
		       Pattern pthdl = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?"); 
		        pattern.matcher(blhdl).matches();
		       if(!"".equals(blhdl)||blhdl!=null||blhdl!="0"||!"o".equals(blhdl)){
			       //生产
		    	   blhdl1=df.format((new Double(dbili1).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //营业
		    	   blhdl2=df.format((new Double(dbili2).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
			       //办公
		    	   blhdl3=df.format((new Double(dbili3).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //信息化	
		    	   blhdl4=df.format((new Double(dbili4).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           // 建设投资
		    	   blhdl5=df.format((new Double(dbili5).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //代垫电量
		    	   blhdl6=df.format((new Double(dbili6).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		       }else{
		    	   blhdl1="0.00";
		    	   blhdl2="0.00";
		    	   blhdl3="0.00";
		    	   blhdl4="0.00";
		    	   blhdl5="0.00";
		    	   blhdl6="0.00";
		       }

		       //分摊电费(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
			  if(!"".equals(actualpay)||actualpay!=null||actualpay!="0"||!"o".equals(actualpay)){
			       //生产
			       actualpay1=df.format((new Double(dbili1).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           //营业
			       actualpay2=df.format((new Double(dbili2).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
			       //办公
			       actualpay3=df.format((new Double(dbili3).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           //信息化	
			       actualpay4=df.format((new Double(dbili4).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           // 建设投资
			       actualpay5=df.format((new Double(dbili5).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           // 代垫电费
			       actualpay6=df.format((new Double(dbili6).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
			  }else{
				  actualpay1="0.00";
				  actualpay2="0.00";
				  actualpay3="0.00";
				  actualpay4="0.00";
				  actualpay5="0.00";
				  actualpay6="0.00";
			  }  
			  
			  formBean= new AmmeterDegreeFormBean();
			  
			  formBean.setAmmeteridFk(dbid1);
		      formBean.setLastdegree(df.format(Double.parseDouble(b[4].toString().trim().equals("")?"0":b[4].toString().trim())));
		      formBean.setThisdegree(df.format(Double.parseDouble(b[5].toString().trim().equals("")?"0":b[5].toString().trim())));
		      formBean.setLastdatetime(lastdatetime);	     
		      formBean.setThisdatetime(thisdatetime);
		      formBean.setFloatdegree(df.format(Double.parseDouble(b[8].toString().trim().equals("")?"0":b[8].toString().trim())));
		      formBean.setBlhdl(df.format(Double.parseDouble(b[9].toString().trim())));
		      formBean.setStartmonth(startmonth);
		      formBean.setEndmonth(endmonth);
		      formBean.setActualdegree(df.format(Double.parseDouble(b[9].toString().trim())));
		      formBean.setInputoperator(b[12].toString().trim());
		      formBean.setInputdatetime(inputdatetime);	     
		    //  System.out.println("0--0"+formBean.getActualdegree());	      
		      formBean.setMemo(b[14].toString().trim());
		      formBean.setEntrypensonnel(accountname);
		      formBean.setEntrytime(mat.format(new Date()));
		      formBean.setEdhdl(edhdl);
		      formBean.setBeilv(beilv1);
		      //分摊电量
		  		//生产
		      formBean.setScdl(blhdl1);
		      	//办公
		      formBean.setYydl(blhdl2);
		      	//营业
		      formBean.setBgdl(blhdl3);
		      	//信息化 
		      formBean.setXxhdl(blhdl4);
		      	//建设投资
		      formBean.setJstzdl(blhdl5);
		      	//代垫电量
		      formBean.setDddfdl(blhdl6);
		      
		      feesformBean= new ElectricFeesFormBean();
		      
		      String bzw="0";
		    //通过主键获取电量id
		      String msg="";
		      msg=bean.addDegreeFees(formBean, uuid,bzw,fylist);
		      if(msg.equals("添加电量成功!")){
		    	 // System.out.println(b[9]+"添加电量成功");
		      }
		      else{
		    	  System.out.println(b[3]+"添加电量失败");
		    	  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
		    	  row.add(b[0].toString()+b[1].toString()+b[2].toString()+dbid1+"添加电量出错!");
		    	  
		    	  wrongContent.add(row);
		    	  continue;
		    	  
		      }
		     
		      
		      
		     // feesformBean.setAmmeterdegreeid(this.getDLID(amuuid));
		      feesformBean.setUnitprice(b[15].toString().trim());
		    //  System.out.println(b[16].toString().trim()+"---");
		      feesformBean.setFloatpay(df.format(Double.parseDouble(b[16].toString().trim()!=null?b[16].toString().trim():"0")));
		      feesformBean.setActualpay(df.format(Double.parseDouble(b[17].toString().trim()!=null?b[17].toString().trim():"0")));
		      feesformBean.setPjje(Double.parseDouble(str21));
		      
		      String pjlx="";
		      if(b[19].toString().trim().equals("发票")){
		    	  pjlx="pjlx03";
		      }else if(b[19].toString().trim().equals("支票")){
		    	  pjlx="pjlx02";
		      }else if(b[19].toString().trim().equals("电费分割单")){
		    	  pjlx="pjlx04";
		      }else if(b[19].toString().trim().equals("收据")){
		    	  pjlx="pjlx05";
		      }else{
		    	  pjlx=b[19].toString().trim();
		      }
		      System.out.println("1111111111pjlx"+pjlx);
		      feesformBean.setNotetypeid(pjlx.equals("")?"pjlx03":pjlx);
		      //System.out.println(req.getParameter("notetypeid"));
		      feesformBean.setNoteno(b[20].toString().trim());
		      feesformBean.setNotetime(notetime);
		      feesformBean.setPayoperator(b[24].toString().trim());
		      feesformBean.setPaydatetime(paydatetime);
		      feesformBean.setAccountmonth(accountmonth);
		      feesformBean.setMemo(b[26].toString().trim());
		      feesformBean.setAmmererid(dbid1);
		      feesformBean.setKptime(kptime);
		      feesformBean.setEntrypensonnel(accountname);
		      feesformBean.setEntrytime(mat.format(new Date()));
		      feesformBean.setDfuuid(uuid);
		      
		      //分摊电费
		      //生产
		      feesformBean.setScdff(actualpay1);
		      //营业
		      feesformBean.setYydf(actualpay2);
		      //办公
		      feesformBean.setBgdf(actualpay3);
		      //信息化
		      feesformBean.setXxhdf(actualpay4);
		      //建设投资
		      feesformBean.setJstzdl(actualpay5);
		      //代垫电费
		      feesformBean.setDddfdf(actualpay6);
		      
		     // System.out.println(start+"/*****"+end);
		      
		      ElectricFeesBean beana = new ElectricFeesBean();
		      List ammeterdegreeid=new ArrayList();
		      ammeterdegreeid =beana.getAmUuid(uuid);
		      System.out.println("主键生成查询id:"+ammeterdegreeid); 
		      int flag=feesformBean.getFlag(ammeterdegreeid.get(0).toString());
		      System.out.println("flag------>>>:"+flag);
		      feesformBean.setFlag(flag);
		      //取电量表中自动审核标志
		       String autoauditstatus =feesformBean.getZdshbz(ammeterdegreeid.get(0).toString());
		       feesformBean.setAutoauditstatus(autoauditstatus);
		       System.out.println("autoauditstatus----->>>"+autoauditstatus);
		      msg=fees.addFeesDegree(feesformBean,startmonth,endmonth,this.getDLID(uuid),bzw,fylist);
	         if(msg.equals("添加电费成功!")){
	        	// System.out.println(b[9]+"添加电费成功");
		      }
	         else{
	        	   System.out.println(b[9]+"添加电费失败");
	        	   for(int j=0;j<b.length;j++){
	        		   row.add(b[j].toString().trim());
	        	   }
		    	  row.add(b[1].toString()+b[2].toString()+dbid1+"添加电费出错!");
		    	  wrongContent.add(row);
		    	  continue;
		    	  
		      }
	        }
		 // System.out.println(content.size()+"  "+wrongContent.toString()+" "+row.size());
		  cform.setCg((content.size()-1)-wrongContent.size());
		  cform.setBcg(wrongContent.size());
		  cform.setZg((content.size()-1));
		  return wrongContent;

		}
	 
	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String uuid) {
		  String sql ="select ammeterdegreeid from AMMETERDEGREES where uuid='"+uuid+"'";
	     // System.out.println("--电表amuuid返回id--"+sql);
	      DataBase db = new DataBase();
	      ArrayList list=new ArrayList() ;
	      rs=null;
	      try {
			rs = db.queryAll(sql);
		
		      while(rs.next()) {
		    	 // System.out.println(rs.getString("ammeterdegreeid"));
		    	  list.add(rs.getString("ammeterdegreeid"));
		      }
		     
	      } catch (Exception e) {
	  		e.printStackTrace();
	  	 }
	      try {
			db.close();
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return list;
	  
	}

	/*
	   * 
	   * 电费批量导入
	   */
	  public synchronized Vector getWrongFee(Vector content,String idStr) {
		    StringBuffer sql = new StringBuffer();
		    int m = 0, i = 0;
		    ArrayList fylistn = new ArrayList();
		    AmmeterDegreeBean abean= new AmmeterDegreeBean();
		    Vector row=new Vector();
		    System.out.println("电费"+content);
		    return row;
		    /*
		      catch (Exception e) {
		        e.printStackTrace();
		        row.add(e.toString());
		        wrongContent.add(row);
		      }
		     }//m条数>1
		    }
		    System.out.println("wrongContent:"+wrongContent);
		    return wrongContent;*/
		  }
	  
	  

	  private boolean getDBID(String con) {
	      String sql ="select id from dianbiao where dbid='"+con+"'";
	      System.out.println("--电表code返回id--"+sql);
	      DataBase db = new DataBase();
	      boolean flag=false;
	      rs=null;
	      try {
			rs = db.queryAll(sql);
		
		      if (rs.next()) {
		    	  flag=true;
		      }
		     
	      } catch (Exception e) {
	  		e.printStackTrace();
	  	 }
	      try {
	    	 rs.close();
			db.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return flag;
	  }
	  
	  
	  /**
	   * 校验电表合法性
	   * @param dbbm
	   * @return
	   */
	  public boolean isValidDb(String dbbm) throws DbException {
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select count(*) cnt from dianbiao d where d.dbbm='"+dbbm+"'");
		  
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  int cnt = 0;
		  try {
			//db.connectDb();
			rs = db.queryAll(dbSql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		 if(cnt > 0){
			 return true;
		 }else{
			 System.out.println("电表["+dbbm+"] 不存在");
			 return false;
		 }
	  }

	  /**
	   * 是否重复电费记录   <p>同一电表下,若录入的电费单结束日期小于等于现有的电费单结束日期视为重复数据</p>
	   * @param dbid 电表id
	   * @param endtime 结束日期
	   * @return
	 * @throws DbException 
	   */
	  public boolean isRepeatef(ElectricfeesVo efVo) throws DbException {
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select count(1) cnt from electricfees e where e.dianbiaoid='"+efVo.getDbid()+"' and e.endtime >= to_date('"+efVo.getEndtime()+"', 'yyyy-MM-dd hh24:mi:ss') ");
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  int cnt = 0;
		  try {
			  //db.connectDb();
			  rs = db.queryAll(dbSql.toString());
			  while(rs.next()){
				  cnt = rs.getInt(1);
			  }
		  } catch (Exception e1) {
			  e1.printStackTrace();
		  }finally{
			  try {
				  db.close();
			  } catch (DbException e) {
				  e.printStackTrace();
			  }
		  }
		  if(cnt > 0){
			  return true;
		  }else{
			  return false;
		  }
	  }

	  /**
	   * <p>普通表：转供电
	   *    电损金额：总金额输入-（截止度数-起始度数）倍率*单价 </p>
	   * <p>电业表：直供电
	   *    电损度数：</p>
	   * @param meterVo 电表实体
	   * @param vo 电量电费实体
	   * @return
	   */
	  public String getElectricLoss(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  if (isDirectedPowerSupply(meterVo)) {// 直供电
			  BigDecimal Allmoney = new BigDecimal(vo.getAllmoney());
			  BigDecimal sqds = new BigDecimal(vo.getSqds());
			  BigDecimal bqds = new BigDecimal(vo.getBqds());
			  BigDecimal beilv = new BigDecimal(vo.getBeilv());
			  BigDecimal price = new BigDecimal(vo.getPrice());
			  //电损金额
			  BigDecimal dsje = Allmoney.subtract(bqds.subtract(sqds).multiply(beilv)
					  .multiply(price));
			  return dsje.toString();
		  } else {
			  return vo.getDiansun();
		  }
		  
	  }

	  /**
	   * <p>普通表：转供电
	   *    单价：电表的单价</p>
	   * <p>电业表：直供电
	   *    单价：总金额除以（(截止度数-起始度数）倍率+电损度数)</p>
	   * @param meterVo 电表实体
	   * @param vo 电量电费实体
	   * @return
	   */
	  public String getElectricfeesPrice(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  if (isDirectedPowerSupply(meterVo)) {// 直供电
			  BigDecimal Allmoney = new BigDecimal(vo.getAllmoney());
			  BigDecimal sqds = new BigDecimal(vo.getSqds());
			  BigDecimal bqds = new BigDecimal(vo.getBqds());
			  BigDecimal beilv = new BigDecimal(vo.getBeilv());
			  BigDecimal diansun = new BigDecimal(vo.getDiansun());
			  //单价
			  BigDecimal danjia = new BigDecimal(0);
			  try {
				
				  danjia = Allmoney.divide(bqds.subtract(sqds).multiply(beilv)
						  .add(diansun), 4, RoundingMode.HALF_UP);
			} catch (Exception e) {
				System.out.println("单价计算异常 " +e);
				danjia.setScale(4);
			}
			  System.out.println("dianjia="+ danjia);
			  return danjia.toString();
			  
		  } else {
			  return meterVo.getDj();
		  }
		  
	  }

	  /**
	   * 是否直供电： 直供电返回true
	   * @param meterVo 电表实体
	   * @return
	   */
	  public boolean isDirectedPowerSupply(ElectricmeterVo meterVo) {
		  if (meterVo.getGdfs() != null && meterVo.getGdfs().equals("gdfs05")) {// 直供电
			  return true;
		  } else {
			  return false;
		  }
	  }
	  /**
	   * 是否直供电： 直供电返回true
	   * @param dbbm 电表编码
	   * @return
	 * @throws DbException 
	   */
	  public boolean isDirectedPowerSupply(String dbbm) throws DbException {
		  //电表信息
		  ElectricmeterVo meterVo = getDb(dbbm);
		  if (meterVo.getGdfs() != null && meterVo.getGdfs().equals("gdfs05")) {// 直供电
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
	  /**
	   * 电量 =（截止度数-起始读数 ）*倍率
	   * @param meterVo
	   * @param vo
	   * @return
	   */
	  public String getElectric(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  BigDecimal sqds = new BigDecimal(vo.getSqds());
		  BigDecimal bqds = new BigDecimal(vo.getBqds());
		  BigDecimal beilv = new BigDecimal(meterVo.getBeilv());
		  BigDecimal dianliang = bqds.subtract(sqds).multiply(beilv);
		  return dianliang.toString();
	  }
	  /**
	   * 税额计算公式 : 总金额/(1+税率)*税率   保留两位小数
	   * 
	   * @param meterVo 电表实体
	   * @param vo 电量电费实体
	   * @return
	   */
	  public String getTax(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  BigDecimal allmoney = new BigDecimal(vo.getAllmoney());
		  BigDecimal zzsl = new BigDecimal(meterVo.getZzsl());
		  BigDecimal one = new BigDecimal(1);
		  //税额计算
		  BigDecimal tax = new BigDecimal(0);
		  try {
			  tax = allmoney.divide(zzsl.add(one), 6, RoundingMode.HALF_UP)
						.multiply(zzsl).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			System.out.println("税额计算异常 " +e);
			tax.setScale(2);
		}
  
		  return tax.toString();
	  }
	  public Long getElectricdays(ElectricfeesVo efVo) {
		  long day =  CTime.getQuot(efVo.getStarttime(), efVo.getEndtime());
		  return day;
	  }

	  /**
	   * 日均用电量 = 用电量 /用电天数  保留两位小数
	   * @param efVo
	   * @return
	   */
	  public String getAvgElectric(ElectricfeesVo efVo) {
		  BigDecimal dianliang = new BigDecimal(efVo.getDianliang());
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());
		  BigDecimal avg = new BigDecimal(0);
		  try {
			  avg = dianliang.divide(daynum, 2, RoundingMode.HALF_UP);
			
		} catch (Exception e) {
			System.out.println("日均用电量计算异常, " +e);
			avg.setScale(2);
		}
		  System.out.println("avg="+ avg);
		  return avg.toString();
	  }

	  public Long getElectricdays_C(ElectricfeesVo efVo) {
		  long day =  CTime.getQuot(efVo.getStarttime_c(), efVo.getEndtime_c());
		  return day;
	  }

	  public String getElectric_C(ElectricfeesVo vo) {
		  BigDecimal sqds_c = new BigDecimal(vo.getSqds_c());
		  BigDecimal bqds_c = new BigDecimal(vo.getBqds_c());
		  BigDecimal beilv = new BigDecimal(vo.getBeilv());
		  BigDecimal dianliang_c = bqds_c.subtract(sqds_c).multiply(beilv);
		  return dianliang_c.toString();
	  }

	  public String getAvgElectric_C(ElectricfeesVo efVo) {
		  BigDecimal dianliang_c = new BigDecimal(efVo.getDianliang_c());
		  BigDecimal daynum_c = new BigDecimal(efVo.getDaynum_c());
		  BigDecimal avg_c = new BigDecimal(0);
		  try {
			  avg_c = dianliang_c.divide(daynum_c, 2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("计算手机抄表日均电量异常 " +e);
			avg_c.setScale(2);
		}
		  return avg_c.toString();
	  }
	  
	  /**
	   *<p>电量偏离数 = 实际用电量 / 理论用电量</p>
	   *<p>理论用电量： 用电量  + (实际用电天数-用电天数) * 日均用电量</p>
	   *<p>实际用电量: 来源手机抄表用电量</p>
	   *<p>实际用电天数: 来源手机抄表用电天数</p>
	   * @param efVo
	   * @return
	   */
	  public String getElectricdeviateNo(ElectricfeesVo efVo) {
		  BigDecimal dianliang = new BigDecimal(efVo.getDianliang());//用电量
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());//用电天数
		  BigDecimal rjydl =  new BigDecimal(efVo.getRjydl()); //日均用电量
		  BigDecimal daynum_c = new BigDecimal(efVo.getDaynum_c());//实际用电天数
		  BigDecimal dianliang_c =  new BigDecimal(efVo.getDianliang_c()); //实际用电量
		  //计算电量偏离数
		  BigDecimal dlpls = new BigDecimal(0.00);
		  try {
			  dlpls = dianliang_c.divide(
					  dianliang.add(daynum_c.subtract(daynum).multiply(rjydl)), 2,
					  RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("电量偏离数计算异常  " +e);
			dlpls.setScale(2);
		}
		  System.out.println("dlpls="+dlpls);

		  return dlpls.toString();
	  }

	  /**
	   * 日期偏离数 = 结束日期-抄表结束日期-1
	   * @param efVo
	   * @return
	   */
	  public String getDatedeviateNo(ElectricfeesVo efVo) {
		  BigDecimal days = new BigDecimal(CTime.getQuot(efVo.getEndtime_c(), efVo.getEndtime()));
		  BigDecimal one = new BigDecimal(1);
		  BigDecimal rqpls = days.subtract(one);
		  return rqpls.toString();
	  }
	  
	  /**
	   * 
	   * PUE=抄表用电量/[（直流主设备电流*54V?+未监控的交流主设备功率+未监控的直流主设备功率）/0.9 *天数*24小时/1000]
	   * @param efVo
	   * @return
	   */
	  public String getElectricPue(ElectricmeterVo meterVo, ElectricfeesVo efVo) {
		  BigDecimal dianliang = new BigDecimal(efVo.getDianliang());//用电量
		  BigDecimal jiaoliu = new BigDecimal(meterVo.getJiaoliu());//未监控的交流主设备功率
		  BigDecimal zhiliu = new BigDecimal(meterVo.getZhiliu());//未监控的直流主设备功率
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());//用电天数
		  BigDecimal dianliu = new BigDecimal(efVo.getCurrentVo().getValue());//直流主设备电流
		  //计算Pue值
		  BigDecimal pue = new BigDecimal(0);
		  try {
			  pue = dianliang.divide(
						dianliu.multiply(new BigDecimal(54)).add(jiaoliu).add(zhiliu)
								.divide(new BigDecimal(0.9), 6, RoundingMode.HALF_UP)
								.multiply(daynum).multiply(new BigDecimal(24))
								.divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP),
						2, RoundingMode.HALF_UP);
		 } catch (Exception e) {
			  System.out.println("PUE计算异常 " +e);
			  pue.setScale(2);
		 }
		  System.out.println("pue="+pue);
		  return pue.toString();
	  }

	  /**
	   * 标杆电量=(0.5*全市的去年同期月份的平均PUE*主设备功率/0.9*24小时*房屋系数+0.2*本站去年同期日均电量+0.3*本站上次报账日均电量)*本次缴费天数
	   * @param meterVo
	   * @param efVo
	   * @return
	   * @throws DbException 
	   */
	  public String getBenchmarkingElectric(ElectricmeterVo meterVo, ElectricfeesVo efVo) throws DbException{
		  String pjpue = getLastYearMonthAvgPue(meterVo.getShi(), new Date());
		  if(pjpue == null || pjpue.isEmpty()) 
			  pjpue = "0.00";
		  String pjydl = getLastYearAvgElectric(meterVo.getJzId(), new Date());
		  if(pjydl == null || pjydl.isEmpty())
			  pjydl = "0.00";
		  BigDecimal avgPue = new BigDecimal(pjpue);//全市的去年同期月份的平均PUE
		  BigDecimal rjydl = new BigDecimal(pjydl); //本站去年同期日均电量
		  BigDecimal zsbgl = new BigDecimal(getEquipmentPower(meterVo, efVo)); //主设备功率
		  //ElectricCurrentVo ecVo = getElectricCurrent(meterVo, efVo);//直流主设备电流
		  String defaut_value = "14";//没有监控电流默认14A
		  if(efVo.getCurrentVo() != null){
			  defaut_value = efVo.getCurrentVo().getValue();
		  }
		  BigDecimal fwxs = new BigDecimal(getHouseRatio(meterVo.getHousetypeid(), defaut_value));
		  BigDecimal sqrjydl = new BigDecimal(efVo.getSqrjydl());
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());
		  
		  //计算标杆电量
		  BigDecimal bgdl = new BigDecimal(0);
		  try {
			  bgdl = new BigDecimal(0.5).multiply(avgPue).multiply(zsbgl)
				.divide(new BigDecimal(0.9), 6, RoundingMode.HALF_UP).multiply(fwxs)
				.add(new BigDecimal(0.2).multiply(rjydl))
				.add(new BigDecimal(0.3).multiply(sqrjydl)).multiply(daynum);
			  bgdl = bgdl.setScale(2, RoundingMode.HALF_UP);//保留两位小数
		} catch (Exception e) {
			System.out.println("计算标杆电量异常 " +e);
			bgdl.setScale(2);
		}
		  System.out.println("标杆电量=" + bgdl);
		  return bgdl.toString();
	  }

	  /**
	   * 标杆偏离率 =（用电量-标杆电量）/标杆电量 *100
	   * @param efVo
	   * @return
	   */
	  public String getBenchmarkingDeviateRate(ElectricfeesVo efVo){
		 BigDecimal dl = new BigDecimal(efVo.getDianliang());
		 BigDecimal bgdl = new BigDecimal(efVo.getBgdl());
		 //标杆偏离率
		 BigDecimal bgpll = new BigDecimal(0.00);
		 try {
			bgpll = dl.subtract(bgdl).divide(bgdl, 6, RoundingMode.HALF_UP)
					.multiply(new BigDecimal(100))
					.setScale(2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("标杆偏离率计算异常 " +e);
			bgpll.setScale(2);
		}
		 return bgpll.toString();
	  }
	  
	  
	  /**
	   * 主设备功率
	   * @param meterVo
	   * @param efVo
	   * @return
	   */
	  public String getEquipmentPower(ElectricmeterVo meterVo, ElectricfeesVo efVo){
		  BigDecimal jiaoliu = new BigDecimal(meterVo.getJiaoliu());//未监控的交流主设备功率
		  BigDecimal zhiliu = new BigDecimal(meterVo.getZhiliu());//未监控的直流主设备功率
		  BigDecimal dl = new BigDecimal(efVo.getCurrentVo().getValue());//电流
		  BigDecimal sbgl = dl.multiply(new BigDecimal(54)).add(jiaoliu)
				.add(zhiliu);
		  return sbgl.toString();
	  }
	  
	  
	  /**
	   * 全市的去年同期月份的平均PUE
	   * @param agcode 地市编码
	   * @param date 系统当前时间
	   * @return
	   */
	  public String getLastYearMonthAvgPue(String agcode, Date date) throws DbException {
		  
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  c.add(Calendar.YEAR, -1);//减一年
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  
		  String date_str = CTime.formatRealMonth(c.getTime());
		  
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select a.addtime"+month+" from analysis_2017 a where a.year_str='"+year+"' and a.shicode='"+agcode+"' ");
		  System.out.println("全市的去年同期月份的平均PUE查询 " +dbSql.toString());
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  String avgPue = "";
		  try {
			//db.connectDb();
			rs = db.queryAll(dbSql.toString());
			while(rs.next()){
				avgPue = rs.getString(1);
			}
			if(avgPue.isEmpty()){
				dbSql.setLength(0);
				dbSql.append("select sum( nvl(e.puezhi, 0)*(nvl(a.value, 0) * 54 + nvl(a.zhiliu, 0) + nvl(a.jiaoliu, 0)) )/sum( nvl(a.value, 0) * 54 + nvl(a.zhiliu, 0) + nvl(a.jiaoliu, 0) ) as PUE" +
						" from analysis a left join electricfees e on a.bzid = e.electricfee_id " +
						" left join zhandian z on z.id = a.zdid where z.shi='"+agcode+"' and a.addtime like '"+date_str+"%' ");
				System.out.println("全市的去年同期月份的平均PUE查询 " +dbSql.toString());
				rs = db.queryAll(dbSql.toString());
				while(rs.next()){
					avgPue = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  return avgPue;
	  }
	  
	  /**
	   * 本站去年同期日均电量
	   * @param stationNo
	   * @param date
	   * @return
	   */
	  public String getLastYearAvgElectric(String stationNo, Date date) throws DbException {
		  
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  c.add(Calendar.YEAR, -1);//减一年
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  
		  String date_str = CTime.formatRealMonth(c.getTime());
		  
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select r.epdavg from ring_station_epd_avg r where r.station_no='"+stationNo+"' and r.statistics_date='"+date_str+"' ");
		  System.out.println("本站去年同期日均电量 " +dbSql.toString());
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  String avgElec = "";
		  try {
			//db.connectDb();
			rs = db.queryAll(dbSql.toString());
			while(rs.next()){
				avgElec = rs.getString(1);
			}
			if(avgElec.isEmpty()){
				dbSql.setLength(0);
				dbSql.append("select nvl(e.rjydl, 0) from electricfees e left join dianbiao d on e.dianbiaoid = d.id  where d.ljid='"+stationNo+"' and e.endtime like '"+date_str+"%' ");
				System.out.println("本站去年同期日均电量 " +dbSql.toString());
				rs = db.queryAll(dbSql.toString());
				while(rs.next()){
					avgElec = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  return avgElec;
		  
	  }
	  
	  /**
	   * 房屋系数
	   * @param housetype 房屋类型
	   * @param value_str 监控电流
	   * @return
	   */
	  public String getHouseRatio(String housetype, String value_str){
		  CommonBean commonBean = new CommonBean();		
		  DecimalFormat df1 = new DecimalFormat("0.00");
		  String addtime = CTime.formatRealMonth(new Date());
		  String housetype2="";
		  //20板房 30非板房
		  if("20".equals(housetype)){//housetype is null
        	   housetype2="20";
           }else{
        	   housetype2="30";
           }
           List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//获取房屋系数的最大值和最小值
           
           String housexsid="",MAXZHI="",MINZHI="";
           if(strList3!=null && strList3.size()==3){
        	   housexsid = strList3.get(0);
        	   MAXZHI = strList3.get(1);
        	   MINZHI = strList3.get(2);
        	   if(MINZHI==null || MINZHI==""){
        		  MINZHI="1";
        	   }
              }
           
           List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str.replaceAll(" ", ""));//获取房屋系数所在的区间
           String housexsid_t="",MIN_T="",MAX_T="";
           if(strList4!=null && strList4.size()==3){
        	   housexsid_t = strList4.get(0);
        	   MIN_T = strList4.get(1);
        	   MAX_T = strList4.get(2);
            }
      		String fwxs="";//房屋系数  》》以下为计算房屋系数
      		if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("0")){
      			fwxs=MINZHI;
      		}else if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("9999999999")){
      			fwxs=MAXZHI;
      		}else {
      		
      				//房屋系数计算保留两位小数
      				fwxs=""+df1.format(Double.valueOf(MINZHI).doubleValue()+
      						(Double.valueOf(MAXZHI).doubleValue()-
      								Double.valueOf(MINZHI).doubleValue())
      								*(Double.valueOf(value_str).doubleValue()
      										-Double.valueOf(MIN_T).doubleValue())
      										/(Double.valueOf(MAX_T).doubleValue()
      												-Double.valueOf(MIN_T).doubleValue()));
      			
      			
      		}
      		return fwxs;
	  }
	  
	  /**
	   * 根据电表编码查询电表
	   * @param dbbm 电表编码
	   * @return
	   */
	  public ElectricmeterVo getDb(String dbbm) throws DbException {
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select d.id, d.dbbm, d.electric_supply_way gdfs, d.danjia, d.zzsl, d.beilv, d.shzt, d.dbqyzt, " +
		  		"nvl(d.zhiliu, '0') zhiliu, nvl(d.jiaoliu, '0') jiaoliu, nvl(z.station_no, '0') stationNo, d.zdid, z.shi, z.housetypeid" +
		  		" from dianbiao d left join zhandian z on d.zdid=z.id where d.dbbm='"+dbbm+"'");
		  System.out.println("电费单数据导入-电表信息查询  " + dbSql.toString());
		  //DataBase db = new DataBase(); 
		  ResultSet rs = null;
		  ElectricmeterVo vo = null;
		  try {
			  //db.connectDb();
			  rs = db.queryAll(dbSql.toString());
			  while(rs.next()){
				  vo = new ElectricmeterVo();
				  vo.setId(rs.getInt("id"));
				  vo.setGdfs(rs.getString("gdfs"));//供电方式
				  vo.setDj(rs.getString("danjia"));//电表单价
				  vo.setZzsl(rs.getString("zzsl")); //增值税率
				  vo.setBeilv(rs.getString("beilv"));//倍率
				  vo.setZhiliu(rs.getString("zhiliu"));//直流主设备功率
				  vo.setJiaoliu(rs.getString("jiaoliu"));//交流主设备功率
				  vo.setJzId(rs.getString("stationNo"));//动环局站ID
				  vo.setZdid(rs.getString("zdid"));
				  vo.setShi(rs.getString("shi"));
				  vo.setHousetypeid(rs.getString("housetypeid"));
				  vo.setDbbm(rs.getString("dbbm"));
				  vo.setShzt(rs.getString("shzt")); //审核状态
				  vo.setDbqyzt(rs.getString("dbqyzt"));//启用状态
			  }
		  } catch (Exception e1) {
			  e1.printStackTrace();
		  }finally{
			  try {
				  db.close();
			  } catch (DbException e) {
				  e.printStackTrace();
			  }
		  }
		  return vo;
	  }
	  
	  /**
	   * 直流主设备电流
	   * @param meterVo
	   * @param efVo
	   * @return
	   */
	  public ElectricCurrentVo getElectricCurrent(ElectricmeterVo meterVo, ElectricfeesVo efVo) throws DbException {
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select e.station_no stationNo, to_char( avg(e.value),'fm9999999990.99') value, " +
		  		"to_char( sum(e.electricity),'fm9999999990.99') sbdl, count(1) daynum from ring_electric e " +
		  		"where e.station_no='"+meterVo.getJzId()+"' and e.updatetime >= to_date('"+efVo.getStarttime()+"', 'yyyy-MM-dd') " +
		  				"and e.updatetime <= to_date('"+efVo.getEndtime()+"', 'yyyy-MM-dd') group by  e.station_no ");
		  System.out.println("电费单数据导入-监控电流查询 " +dbSql.toString());
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  ElectricCurrentVo vo = null;
		  try {
			  //db.connectDb();
			  rs = db.queryAll(dbSql.toString());
			  while(rs.next()){
				  vo = new ElectricCurrentVo();
				  vo.setStationNo(rs.getString("stationNo"));
				  vo.setValue(rs.getString("value"));
				  vo.setSbdl(rs.getString("sbdl"));//监控主设备电量
				  vo.setDaynum(rs.getString("daynum"));//监控用电天数
			  }
			  
		  } catch (SQLException e1) {
			  e1.printStackTrace();
		  }finally{
			  try {
				  db.close();
			  } catch (DbException e) {
				  e.printStackTrace();
			  }
		  }
		  return vo;
	  }
	  
	  /**
	   * 校验电量电费数据
	   * @param rowData 单行数据
	   * @return
	   * @throws DbException 
	   */
	  public String checkElectricfees(ArrayList rowData) throws DbException {
		  
		  String dbbm=""; //电表编码
		  String starttime="";//开始日期
		  String endtime="";//结束日期
		  String sqds = ""; //上期读数
		  String bqds = ""; //本期读数
		  String allmoney = "";//电费金额
		  String diansun = "";//电损（读数/金额）
		  
		  dbbm = String.valueOf(rowData.get(0)).trim();
		  starttime = String.valueOf(rowData.get(1)).trim();
		  endtime = String.valueOf(rowData.get(2)).trim();
		  sqds = String.valueOf(rowData.get(3)).trim();
		  bqds = String.valueOf(rowData.get(4)).trim();
		  allmoney = String.valueOf(rowData.get(5)).trim();
		  diansun = String.valueOf(rowData.get(6)).trim();
		  
		  //
		  StringBuffer errMsg = new StringBuffer();
		  
		  
		  ElectricfeesVo efOldVo = new ElectricfeesVo();
		  //首先检验电表是否合法
		  //电表编码
		  if(dbbm == null || dbbm.isEmpty()){
			  errMsg.append("电表编码为空值, ");
		  }else{
			  if(!isValidDb(dbbm)){
				  errMsg.append("不存在, ");
				  
			  }
		  }
		  if(errMsg.length() > 0){
			  errMsg.insert(0, "电表["+dbbm+"]-");
			  return errMsg.toString();
		  }
		 
		  //DataBase db = new DataBase();
		  ResultSet rs = null;
		  StringBuffer sql = new StringBuffer();
		  sql.append("select d.electric_supply_way gdfs," +
		  					"el.beilv beilv," + 
							"el.starttime starttime, " + 
							"el.endtime endtime," + 
							"nvl(el.sqds, 0) sqds," + 
							"nvl(el.bqds, 0) bqds," + 
							"nvl(el.dianliang, 0) dianliang," + 
							"nvl(el.allmoney, 0.00) allmoney," + 
							"nvl(el.diansun, 0) diansun," + 
							"nvl(el.money, 0.00) money," + 
							"nvl(el.tax, 0) tax," + 
							"nvl(el.sqdj, 0.0000) sqdj," + 
							"nvl(el.price, 0.0000) price," + 
							"nvl(el.daynum, 0) daynum," + 
							"nvl(el.rjydl, 0) rjydl," + 
							"nvl(el.sqrjydl, 0) sqrjydl," + 
							"nvl(el.dlpls, 0) dlpls," + 
							"nvl(el.rqpls, 0) rqpls," + 
							"nvl(el.puezhi, 0) puezhi," + 
							"nvl(el.bgdl, 0) bgdl," + 
							"el.starttime_c starttime_c, " + 
							"el.endtime_c endtime_c," + 
							"nvl(el.sqds_c, 0) sqds_c," + 
							"nvl(el.bqds_c, 0) bqds_c," + 
							"nvl(el.dianliang_c, 0) dianliang_c," + 
							"nvl(el.daynum_c, 0) daynum_c," + 
							"nvl(el.rjydl_c, 0) rjydl_c," + 
							"el.state , " + 
							"el.dianbiaoid dbid" + 
							" from electricfees el left join dianbiao d on el.dianbiaoid=d.id" +
							" where d.dbbm='"+dbbm+"' and el.electricfee_id in (");
		  sql.append("select max(e.electricfee_id) electricfee_id" + 
						" from electricfees e " + 
						" group by e.dianbiaoid )");

		  System.out.println("电费单数据导入查询-" + sql.toString());
		  try {
			//db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				efOldVo.setBeilv(rs.getString("beilv"));
				efOldVo.setStarttime(rs.getString("starttime"));
				efOldVo.setEndtime(rs.getString("endtime"));
				efOldVo.setSqds(rs.getString("sqds"));
				efOldVo.setBqds(rs.getString("bqds"));
				efOldVo.setDianliang(rs.getString("dianliang"));
				efOldVo.setAllmoney(rs.getString("allmoney"));
				efOldVo.setDiansun(rs.getString("diansun"));
				efOldVo.setMoney(rs.getString("money")); 
				efOldVo.setTax(rs.getString("tax")); 
				efOldVo.setSqdj(rs.getString("sqdj")); 
				efOldVo.setPrice(rs.getString("price")); 
				efOldVo.setDaynum(rs.getString("daynum"));
				efOldVo.setRjydl(rs.getString("rjydl")); 
				efOldVo.setSqrjydl(rs.getString("sqrjydl"));
				efOldVo.setDlpls(rs.getString("dlpls")); 
				efOldVo.setRqpls(rs.getString("rqpls"));
				efOldVo.setPuezhi(rs.getString("puezhi"));
				efOldVo.setBgdl(rs.getString("bgdl"));
			    efOldVo.setState(rs.getString("state"));
			    
				efOldVo.setDbid(rs.getString("dbid")); //电表id
				efOldVo.setGdfs(rs.getString("gdfs"));
				//手机抄表
				efOldVo.setStarttime_c(rs.getString("starttime_c"));
				efOldVo.setEndtime_c(rs.getString("endtime_c"));
				efOldVo.setSqds_c(rs.getString("sqds_c"));
				efOldVo.setBqds_c(rs.getString("bqds_c"));
				efOldVo.setDianliang_c(rs.getString("dianliang_c"));
				efOldVo.setDaynum_c(rs.getString("daynum_c"));
				efOldVo.setRjydl_c(rs.getString("rjydl_c"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  //=================================================================================
		  //校验数据合法性 step1
		  //=================================================================================
		  String regex = "[0-9]*|[0-9]*+\\.+[0-9]*"; //上期读数、本期读数、电损度数
		  String regex1 = "[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"; //金额
		  boolean isNew = false;//
		  //结束日期
		  if(endtime == null || endtime.isEmpty()){
			  errMsg.append("结束日期为空值, ");
		  }else{
			  if(!isValidDate(endtime)){
				  errMsg.append("结束日期格式不正确, ");
			  }
		  }
		  //本期读数
		  if(bqds == null || bqds.isEmpty()){
			  errMsg.append("本期读数为空值, ");
		  }else{
			  if(!Pattern.matches(regex, bqds)){
				  errMsg.append("本期读数格式不正确, ");
			  }
			  
		  }
		  //电费金额
		  if(allmoney == null || allmoney.isEmpty()){
			  errMsg.append("电费金额为空值, ");
		  }else{
			  if(!Pattern.matches(regex1, allmoney)){
				  errMsg.append("电费金额格式不正确, ");
			  }
			  
		  }
		  
		  //首次录入电费数据 校验项：开始日期 上期读数 
		  if(efOldVo.getDbid() ==null || efOldVo.getDbid().isEmpty()){ 
			  //开始日期
			  if(starttime == null || starttime.isEmpty()){
				  errMsg.append("开始日期为空值, ");
			  }else{
				  if(!isValidDate(starttime)){
					  errMsg.append("开始日期格式不正确, ");
				  }
			  }
			 //上期读数
			  if(sqds == null || sqds.isEmpty()){
				  errMsg.append("上期读数为空值, ");
			  }else{
				  if(!Pattern.matches(regex, sqds)){
					  errMsg.append("上期读数格式不正确, ");
				  }
			  }
			  isNew = true;
		  }

		  //获取电表信息
		  ElectricmeterVo meterVo = getDb(dbbm);
		  if(meterVo == null){
			  errMsg.append("查询失败, ");
			  meterVo = new ElectricmeterVo();
		  }else{
			  //根据电表供电方式校验电损度数列是否必填
			  if(isDirectedPowerSupply(meterVo)){//直供电
				  if(diansun == null || diansun.isEmpty()){
					  errMsg.append("电损度数为空值, ");
				  }else{
					  if(!Pattern.matches(regex, diansun)){
						  errMsg.append("电损度数格式不正确, ");
					  }
				  }
			  }
			  //电表启用状态  zt01在用
			  if("zt02".equals(meterVo.getDbqyzt())){//停用
				  errMsg.append("已停用, ");
			  }else if("zt03".equals(meterVo.getDbqyzt())){//关闭
				  errMsg.append("已关闭, ");
			  }else{
				  System.out.println("电表["+dbbm+"] 启用状态-"+meterVo.getDbqyzt());
			  }
			  //电表审核状态 2已通过
			  if("0".equals(meterVo.getShzt())){//未上报
				  errMsg.append("未上报, ");
			  }else if("1".equals(meterVo.getShzt())){//审核中
				  errMsg.append("审核中, ");
			  }else{
				  System.out.println("电表["+dbbm+"] 审核状态-" +meterVo.getShzt());
			  }
		  }
		  
		  ElectricfeesVo efVo = new ElectricfeesVo();
		  efVo.setEndtime(endtime); //结束日期
		  efVo.setBqds(bqds); //本期读数
		  efVo.setAllmoney(allmoney); //电费金额
		  efVo.setDiansun(diansun);//电损度数
		  efVo.setDbid(String.valueOf(meterVo.getId()));
		  //重复校验
		  if(isRepeatef(efVo)){
			  errMsg.append("电费数据重复, ");
		  }else{
			//报账状态
			  if(efOldVo.getState() !=null && !"4".equals(efOldVo.getState())){
				  errMsg.append("存在未流转完的报账单, ");
			  }
		  }
		  
		  if(errMsg.length() > 0){
			  errMsg.insert(0, "电表["+dbbm+"]-");
			  return errMsg.toString();
		  }
		  //=================================================================================
		  //计算电费其他数据值 step2
          //=================================================================================
		  if(efOldVo.getDbid() ==null || efOldVo.getDbid().isEmpty()){
			  efVo.setStarttime(starttime); //开始日期
			  efVo.setSqds(sqds); //上期读数
			  efVo.setSqdj(meterVo.getDj()); //上期单价
			  efVo.setSqrjydl("0.00"); //上期日均用电量
			  efVo.setDbid(String.valueOf(meterVo.getId()));
		  }else{
			  //有上期数据时
			  if(efOldVo.getEndtime() !=null && !efOldVo.getEndtime().isEmpty()){//上期结束日期 加 1 天
				  Calendar c = Calendar.getInstance();
				  c.setTime(CTime.parseDate(efOldVo.getEndtime()));
				  c.add(Calendar.DAY_OF_MONTH, 1);
				  efVo.setStarttime(CTime.formatRealDate(c.getTime()));//yyyy-MM-dd
			  }
			  efVo.setSqds(efOldVo.getBqds()); //上期读数
			  efVo.setSqdj(efOldVo.getPrice()); //上期单价
			  efVo.setSqrjydl(efOldVo.getRjydl()); //上期日均用电量
			  efVo.setDbid(efOldVo.getDbid());
		  }
		  //获取监控电流数据
		  ElectricCurrentVo ecVo = getElectricCurrent(meterVo, efVo);
		  if(ecVo == null){
			  ecVo = new ElectricCurrentVo();
			  ecVo.setValue("14");//没有监控电流默认14A
		  }
		  efVo.setCurrentVo(ecVo);
		  efVo.setBeilv(meterVo.getBeilv()); //倍率
		  efVo.setPrice(getElectricfeesPrice(meterVo, efVo)); //单价
		  efVo.setDiansun(getElectricLoss(meterVo, efVo)); //电损金额
		  efVo.setDianliang(getElectric(meterVo, efVo)); //电量
		  efVo.setTax(getTax(meterVo, efVo));//税额
		  efVo.setDaynum(String.valueOf(getElectricdays(efVo)));//用电天数
		  efVo.setRjydl(getAvgElectric(efVo));
		  
		  //手机抄表-
		  efVo.setStarttime_c(efVo.getStarttime());
		  efVo.setEndtime_c(efVo.getEndtime());
		  efVo.setSqds_c(efVo.getSqds());
		  efVo.setBqds_c(efVo.getBqds());
		  efVo.setDianliang_c(getElectric_C(efVo));
		  efVo.setDaynum_c(String.valueOf(getElectricdays_C(efVo)));
		  efVo.setRjydl_c(getAvgElectric_C(efVo));

		  //数据分析
		  efVo.setDlpls(getElectricdeviateNo(efVo));//电量偏离数
		  efVo.setRqpls(getDatedeviateNo(efVo));//日期偏离数
		  efVo.setPuezhi(getElectricPue(meterVo, efVo));//PUE
		  /**计算标杆电量*/
		  efVo.setBgdl(getBenchmarkingElectric(meterVo, efVo));
		  efVo.setBgpll(getBenchmarkingDeviateRate(efVo));
		  //=================================================================================
		  //插入数据 step3
		  //=================================================================================
		  SiteManage bean = new SiteManage();
		  if(efVo.getDiansun().isEmpty()) efVo.setDiansun("0");
		  efVo.setDfbzw("0");//导入
		  System.out.println("开始日期 "+efVo.getStarttime());
		  System.out.println("结束日期 "+efVo.getEndtime());
		  int bzId = bean.addDataBaoZhang("0.00", "0", efVo.getTax(), efVo.getStarttime_c(), efVo.getEndtime_c(), 
				  efVo.getSqds_c(), efVo.getBqds_c(), efVo.getDianliang_c(), efVo.getDaynum_c(), 
				  efVo.getRjydl_c(), efVo.getDbid(), efVo.getStarttime(), efVo.getEndtime(), 
				  efVo.getSqds(), efVo.getBqds(), efVo.getDianliang(), efVo.getAllmoney(), 
				  efVo.getDiansun(), efVo.getSqdj(), efVo.getPrice(), efVo.getDaynum(), 
				  efVo.getRjydl(), efVo.getSqrjydl(), daoruRen, CTime.getCurrentDate(), 
				  efVo.getBeilv(), "0", efVo.getDlpls(), efVo.getRqpls(), efVo.getPuezhi(), 
				  efVo.getBgdl(), efVo.getBgpll(), efVo.getDfbzw());
		  
		  return errMsg.toString();
		  
	  }
	  
	  
	  /**
	   * 去掉电表编码数据
	   * @param datas
	   * @return
	   */
	  public ArrayList removeNull( ArrayList datas){
		  for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			  ArrayList<Object> data = (ArrayList<Object>) iterator.next();
			  Object dbbm = data.get(0);
			  if(dbbm == null || "".equals(dbbm)){
				  iterator.remove();
			  }
		}
		  return datas;
	  }
	  
	  
	  //导入判断日期格式
	  public static boolean isValidDate(String sDate) {    
			String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";   
			String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"       
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|" 
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" 
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?(" 
		         + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
		         + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"; 
			if ((sDate != null)) { 
				Pattern pattern = Pattern.compile(datePattern1); 
				Matcher match = pattern.matcher(sDate); 
				if (match.matches()) { 
					pattern = Pattern.compile(datePattern2);
					match = pattern.matcher(sDate); 
					return match.matches();
					} else {  
						return false;   
						}    
				}    
			return false; 
			}
	  
	  public static void main(String[] args) {
		 DaoruElectricfees def = new DaoruElectricfees();
		 String fwxs = def.getHouseRatio("30", "14");
		 System.out.println("fwxs=" + fwxs);
		  
	}
}
