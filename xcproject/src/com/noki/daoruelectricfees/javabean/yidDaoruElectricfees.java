package com.noki.daoruelectricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.sysconfig.javabean.AutoAuditBean;

public class yidDaoruElectricfees {
	String sqlnote = "";
	  Vector wrongContent = new Vector();
	  public DataBase db;
	  public yidDaoruElectricfees() {
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
			 
			  String ydbid="",did="",id="",dianfei="",beilv1="1",linelosstype1="",linelossvalue1="",edhdl="0";
			  String zy1="",zy2="",zy3="",zy4="",zy5="",zy6="";
			  System.out.println("_-"+dbid1+"--"+b[9].toString().trim()+"--");
			  ydbid=b[3].toString().trim();
			  blhdl=b[9].toString().trim();
			  actualpay=b[17].toString().trim();
			  String  sql1="SELECT D.ID, Z.DIANFEI, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI END)AS ZY1," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI END)AS ZY2," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI END)AS ZY3," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI END)AS ZY4," +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI END)AS ZY5,  " +
			  		"(CASE WHEN S.SHUOSHUZHUANYE='zylx06' THEN S.DBILI END)AS ZY6,  " +
			  		"d.dbid FROM ZHANDIAN Z, DIANBIAO D,SBGL S   WHERE Z.ID = D.ZDID   AND D.DBYT = 'dbyt01' " +
			  		"  AND S.DIANBIAOID(+)=D.DBID AND D.YDBID=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?)";
			   ResultSet rs=null;
			   DataBase db=new DataBase();
			   try{
			   //更改中2012-12-3 
			   Properties pro=new Properties();
			   pro.setProperty("1", ydbid);
			   pro.setProperty("2", loginId);
			   db.connectDb();
			   System.out.println(sql1);
			   System.out.println(pro.getProperty("1"));
			   System.out.println(pro.getProperty("2"));
			   rs=db.queryAll001(sql1,pro);
			   while(rs.next()){
				   id=rs.getString(1);
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
				   did=rs.getString(13);
			   }
			   db.close();
			   rs.close();
			   }catch(Exception e){
				   e.printStackTrace();
			   }
			   
			  if("".equals(id)||null==id){
				  System.out.println("未查到原电表id："+ydbid);
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[0].toString()+b[2].toString()+"原电表id："+ydbid);
				  wrongContent.add(row);
				  continue;
			  }
			  String str44=b[4].toString().trim();
			  String str4 = str44.replaceAll(" ", "");

			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  
			  System.out.println("eeee"+str4+"eeee");
			  if(pattern.matcher(str4).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次电表读数格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str4==""){		
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次电表读数为空"); 
				  wrongContent.add(row);
				  continue;
				  }
			  String str55=b[5].toString().trim();
			  String str5 = str55.replaceAll(" ", "");
			  if(pattern.matcher(str5).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"本次电表读数格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str5==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"本次电表读数为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[6].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次抄表时间为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[7].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"本次抄表时间为空"); 
				  wrongContent.add(row);
				  continue;
			  }
			  String str88=b[8].toString().trim();
			  String str8 = str88.replaceAll(" ", "");
			  if(pattern1.matcher(str8).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"用电量调整格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str8.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"用电量调整为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str99=b[9].toString().trim();
			  String str9 = str99.replaceAll(" ", "");
			  System.out.println("-1-"+str9+"--"+pattern.matcher(str9).matches());
			  if(pattern.matcher(str9).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  System.out.println("-2-"+str9+"--");
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"实际用电量格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else  if(str9.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  System.out.println("-3-"+str9+"--");
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"实际用电量为空");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[10].toString().trim().equals("")||b[11].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"起始结束月份为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str155=b[15].toString().trim();
			  String str15 = str155.replaceAll(" ", "");
			  if(pattern.matcher(str15).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"单价格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str15.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"单价为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str166=b[16].toString().trim();
			  String str16 = str166.replaceAll(" ", "");
			  if(pattern1.matcher(str16).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"费用调整格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str16.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"费用调整为空");
				  wrongContent.add(row);
				  continue;
			  }
			  String str177=b[17].toString().trim();
			  String str17 = str177.replaceAll(" ", "");
			  if(pattern.matcher(str17).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"实际电费金额格式不正确"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str17.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"实际电费金额为空");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[18].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"报账月份为空");
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
			  if((b[6].toString().trim().contains("/")&&pattern4.matcher(lastdatetime).matches()==true)||b[6].toString().trim().contains("-")){
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次抄表时间为空   或者   格式不正确");
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
						System.out.println("lastdatetime+=+++"+lastdatetime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	        	 
			 }
			  if(pattern5.matcher(lastdatetime).matches()==false){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次抄表时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
			  String str77=b[7].toString().trim();
			  thisdatetime = str77.replaceAll(" ", "");  
			  if((b[7].toString().trim().contains("/")&&pattern4.matcher(thisdatetime).matches()==true)||b[7].toString().trim().contains("-")){
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"本次抄表时间为空   或者   格式不正确");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"本次抄表时间格式不正确!!");
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
						  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"上次抄表时间大于本次抄表时间");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"起始年月为空或者格式不正确");
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
							  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"起始月份大于结束月份");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"结束年月为空或者格式不正确");
				  wrongContent.add(row);
				  continue;
			  }	  
			  if(!b[13].toString().trim().equals("")&&b[13].toString().trim()!=null){
				  String str133=b[13].toString().trim();
				  inputdatetime = str133.replaceAll(" ", ""); 
			  if((b[13].toString().trim().contains("/")&&pattern4.matcher(inputdatetime).matches()==true)||b[13].toString().trim().contains("-")){
				  if(b[13].toString().trim().length()!=10){
					  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
					  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"抄表时间格式不正确!!");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"抄表时间为空");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"抄表时间格式不正确!!");
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
						  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"报账月份为空或者格式不正确");
						  wrongContent.add(row);
						  continue;
					  }
			 
		  if(!b[21].toString().trim().equals("")&&b[21].toString().trim()!=null){		 
			  String str211=b[21].toString().trim();
			  notetime = str211.replaceAll(" ", ""); 
			  if((b[21].toString().trim().contains("/")&&pattern4.matcher(notetime).matches()==true)||b[21].toString().trim().contains("-")){
				  if(b[21].toString().trim().contains("/")){
			          try {
			        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date =sdf.parse(b[21].toString().trim());
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"票据时间格式不正确");
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
				  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"票据时间格式不正确!!");
				  wrongContent.add(row);
				  continue;
			  }
		  }else{
			  notetime=b[21].toString().trim();
		  }
		 
		  if(!b[22].toString().trim().equals("")&&b[22].toString().trim()!=null){	
			  String str222=b[22].toString().trim();
			  kptime = str222.replaceAll(" ", ""); 
				  if((b[22].toString().trim().contains("/")&&pattern4.matcher(kptime).matches()==true)||b[22].toString().trim().contains("-")){
				  if(b[22].toString().trim().contains("/")){
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
					  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"开票时间格式不正确");
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
					  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"开票时间格式不正确!!");
					  wrongContent.add(row);
					  continue;
				  }
		  }else{
			  kptime=b[22].toString().trim();
		  }
		  
		  if(!b[24].toString().trim().equals("")&&b[24].toString().trim()!=null){
			  String str244=b[24].toString().trim();
			  paydatetime = str244.replaceAll(" ", ""); 
				  if((b[24].toString().trim().contains("/")&&pattern4.matcher(paydatetime).matches()==true)||b[24].toString().trim().contains("-")){
				  if(b[24].toString().trim().contains("/")){
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
					  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"交费时间格式不正确");
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
					  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"交费时间格式不正确!!");
					  wrongContent.add(row);
					  continue;
				  }
		  }else{
			  paydatetime=b[24].toString().trim();
		  }
		  
	    	     
			     DecimalFormat dmat=new DecimalFormat("0.0000");
			     String danjia1=b[15].toString().trim();
			     if("".equals(dianfei)||dianfei==null)dianfei="1";
			     if("".equals(danjia1)||danjia1==null)danjia1="1";
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
	    if(linelosstype1.equals("02xsbl")){
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
	 	    	  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"的实际用电量与计算的结果不一致!!系统算出结果为："+jsdeg);	    	  
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
	      	    	  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"的实际用电量与计算的结果不一致!系统算出结果为："+jsdeg);	    	  
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
	  	    	  row.add(b[0].toString()+b[2].toString()+"原电表id："+ydbid+"的实际电费金额与计算的结果不一致!系统算出结果为："+jsmoneys);	    	  
	  	    	  wrongContent.add(row);
	  	    	  continue;
	    }
//	    if(!jsmoneys.equals(moneys)){
//	    	
//	    }   
	}
		  //判断导入的数据是否重复
    	 DecimalFormat df1 = new DecimalFormat("#.00");
    	 String lastdegrees2 = df1.format(Double.parseDouble(b[4].toString().trim().equals("")?"0":b[4].toString().trim()));
		 String thisdegrees2 = df1.format(Double.parseDouble(b[5].toString().trim().equals("")?"0":b[5].toString().trim()));
		  String sql ="SELECT 'A'FROM dbtemp_09_view T, DIANBIAO DB, DFTEMP_011_VIEW E WHERE DB.DBID = T.AMMETERID_FK AND DB.YDBID =? AND T.LASTDEGREE =? AND T.THISDEGREE =? AND TO_CHAR(T.LASTDATETIME,'yyyy-mm-dd') =? AND TO_CHAR(T.THISDATETIME,'yyyy-mm-dd') =? AND T.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') =?";
	     System.out.println("判断导入的数据是否重复:"+sql);
	      DataBase db1 = new DataBase();     
	      ArrayList<String> listzd=new ArrayList<String>() ;
	      rs=null;
	      try {  
	    	Properties pro=new Properties();
	    	pro.setProperty("1", ydbid);
	    	pro.setProperty("2", lastdegrees2);
	    	pro.setProperty("3", thisdegrees2);
	    	pro.setProperty("4", lastdatetime);
	    	pro.setProperty("5", thisdatetime);
	    	pro.setProperty("6", accountmonth);
			rs = db1.queryAll002(sql,pro);	
			//System.out.println("121212121"+rs);
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
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
			}
			}
			  System.out.println("thisdatetime"+lastdatetime+"thisdatetime"+thisdatetime+"startmonth"+startmonth+"endmonth"+endmonth+"inputdatetime"+inputdatetime+"accountmonth"+accountmonth+"notetime"+notetime+"kptime"+kptime+"paydatetime"+paydatetime);
			  //String amuuid = UUID.randomUUID().toString().trim().replace("-","");
			  
			  long uuid1 = System.currentTimeMillis();
			  String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		      String amuuid = uuid2+Long.toString(uuid1).substring(3);
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
		      DecimalFormat df = new DecimalFormat("#.00");
				  dbili1=zy1;
				  dbili2=zy2;
			      dbili3=zy3;
			      dbili4=zy4;
			      dbili5=zy5;	
			      dbili6=zy6;
		       if(dbili1==null||dbili1==""){
	        	 dbili1="0.00";
		       }
		       if(dbili2==null||dbili2==""){
	        	 dbili2="0.00";
		       }
		       if(dbili3==null||dbili3==""){
	        	 dbili3="0.00";
		       }
		       if(dbili4==null||dbili4==""){
	        	 dbili4="0.00";
		       }
		       if(dbili5==null||dbili5==""){
	        	 dbili5="0.00";
		       }
		       if(dbili6==null||dbili6==""){
		        	 dbili6="0.00";
			       }
		       
		       //分摊电量(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
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
			       //代垫电费
			       actualpay6=df.format((new Double(dbili6).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
			       System.out.println("生产："+actualpay1+"aaaaaa"+actualpay2+"aaaaaa"+actualpay3+"aaaaaa"+((new Double(dbili1).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha)+"aaaaaa"+((new Double(dbili2).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha)+"aaaaaa"+((new Double(dbili3).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha));
			  }else{
				  actualpay1="0.00";
				  actualpay2="0.00";
				  actualpay3="0.00";
				  actualpay4="0.00";
				  actualpay5="0.00";
				  actualpay6="0.00";
			  }  
			  
			  formBean= new AmmeterDegreeFormBean();
			  
			  formBean.setAmmeteridFk(did);
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
		      System.out.println("0--0"+formBean.getActualdegree());	      
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
		      
		      String bzw="0";
	          String msg=bean.addDegreeFees(formBean, amuuid,bzw,fylist);
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
		     
		      
		      feesformBean= new ElectricFeesFormBean();
		     // feesformBean.setAmmeterdegreeid(this.getDLID(amuuid));
		      feesformBean.setUnitprice(b[15].toString().trim());
		      feesformBean.setFloatpay(b[16].toString().trim()!=null?b[16].toString().trim():"0");
		      feesformBean.setActualpay(b[17].toString().trim()!=null?b[17].toString().trim():"0");
		      String pjlx="";
		      if(b[19].toString().trim().equals("发票")){
		    	  pjlx="pjlx03";
		      }else if(b[19].toString().trim().equals("支票")){
		    	  pjlx="pjlx02";
		      }else if(b[19].toString().trim().equals("电费分割单")){
		    	  pjlx="pjlx04";
		      }else{
		    	  pjlx=b[19].toString().trim();
		      }
		      feesformBean.setNotetypeid(pjlx.equals("")?"pjlx03":pjlx);
		      //System.out.println(req.getParameter("notetypeid"));
		      feesformBean.setNoteno(b[20].toString().trim());
		      feesformBean.setNotetime(notetime);
		      feesformBean.setPayoperator(b[23].toString().trim());
		      feesformBean.setPaydatetime(paydatetime);
		      feesformBean.setAccountmonth(accountmonth);
		      feesformBean.setMemo(b[25].toString().trim());
		      feesformBean.setAmmererid(dbid1);
		      feesformBean.setKptime(kptime);
		      feesformBean.setEntrypensonnel(accountname);
		      feesformBean.setDfuuid(amuuid);
		      feesformBean.setEntrytime(mat.format(new Date()));
		      
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
		      feesformBean.setDddf(actualpay6);
		      
		     // System.out.println(start+"/*****"+end);
		      msg=fees.addFeesDegree(feesformBean,startmonth,endmonth,this.getDLID(amuuid),bzw,fylist);
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
	private ArrayList getDLID(String amuuid) {
		  String sql ="select ammeterdegreeid from AMMETERDEGREES where uuid='"+amuuid+"'";
	     // System.out.println("--电表amuuid返回id--"+sql);
	      DataBase db1 = new DataBase();
	      ResultSet rss=null;
	      ArrayList list=new ArrayList() ;
	      try {
	    	  rss = db1.queryAll(sql);
		
		      while(rss.next()) {
		    	 // System.out.println(rs.getString("ammeterdegreeid"));
		    	  list.add(rss.getString("ammeterdegreeid"));
		      }
		     
	      } catch (Exception e) {
	  		e.printStackTrace();
	  	 }
	      try {
			db1.close();
			try {
				rss.close();
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
	      DataBase db1 = new DataBase();
	      ResultSet rss=null;
	      boolean flag=false;
	      try {
			rss = db1.queryAll(sql);
		
		      if (rss.next()) {
		    	  flag=true;
		      }
		     
	      } catch (Exception e) {
	  		e.printStackTrace();
	  	 }
	      try {
			db1.close();
			rss.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return flag;
	  }
}
