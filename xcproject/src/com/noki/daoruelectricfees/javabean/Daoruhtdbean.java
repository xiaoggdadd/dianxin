package com.noki.daoruelectricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.jizhan.ZhanDianForm;
import com.noki.jizhan.daoru.CountForm;

public class Daoruhtdbean {
	String sqlnote = "";
	  Vector wrongContent = new Vector();
	  public DataBase db;
	  public Daoruhtdbean() {
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
	  //合同单导入
	  public synchronized Vector getWronghtd(Vector content,CountForm cform,String accountname,String loginId) {
		  String lastdatetime="";
		  Date date1 = null,date2=null;
		  String money="",startmonth="",dianliang="",endmonth="",accountmonth="",notetime="",kptime="",pjlx="",pjbh="",memo="";
		 // String thisdatetime="",startmonth="",endmonth="",inputdatetime="",accountmonth="",notetime="",kptime="",paydatetime="";
		  String dbid1="";
		  String blhdl="";
		  String actualpay="";
		  //System.out.println("电量"+content);
		  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Object[] a=content.toArray();
		  AmmeterDegreeFormBean formBean= null;
		  ElectricFeesFormBean feesformBean=null;
		  ElectricFeesBean fees = new ElectricFeesBean();
		  for(int i=1;i<a.length;i++){		
			  Vector<String> row=new Vector<String>();
			  Object[] b=((Vector)a[i]).toArray();
			  dbid1=b[5].toString().trim();
			 // blhdl=b[9].toString().trim();
			  actualpay=b[6].toString().trim();
			  String did="",idd="",zdid="",dianfei="",beilv1="",linelosstype1="",linelossvalue1="";
			   if("".equals(b[4].toString().trim())||null==b[4].toString().trim()){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"站点ID"+b[4].toString().trim()+"为空");
				  wrongContent.add(row);
				  continue;
				  
			  }
			   String sql2=" select zd.id from dianbiao d, zhandian zd " +
		  		" where zd.id=d.zdid  and zd.qyzt = '1' " +
		  		"  and d.dfzflx = 'dfzflx02' " +
		  		"AND zd.id='"+b[4].toString().trim()+"' AND (ZD.XIAOQU IN(SELECT t.AGCODE FROM PER_AREA t WHERE t.ACCOUNTID = '"+loginId+"'))";
		   ResultSet rs8=null;
		   DataBase db8=new DataBase();
		   try{
		   db8.connectDb();
		   rs8=db8.queryAll(sql2);
		   System.out.println("查询站点id是否存在"+sql2.toString());
		   while(rs8.next()){
			   idd=rs8.getString(1);
			  // dianfei=rs.getString(2);
			 //  beilv1=rs.getString(3);
			  // linelosstype1=rs.getString(4);
			  // linelossvalue1=rs.getString(5);
		   }
		   db8.close();
		   rs8.close();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   if("".equals(idd)||null==idd){
				  System.out.println("未查到站点ID"+b[4].toString().trim());
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[0].toString()+b[2].toString()+"站点ID"+b[4].toString().trim());
				  wrongContent.add(row);
				  continue;
			  }
			   
			   
			  
			  String sql1=" select d.dbid from dianbiao d, zhandian zd " +
			  		" where zd.id=d.zdid  and zd.qyzt = '1' " +
			  		"  and d.dfzflx = 'dfzflx02' " +
			  		"AND D.DBID='"+dbid1+"' AND (ZD.XIAOQU IN(SELECT t.AGCODE FROM PER_AREA t WHERE t.ACCOUNTID = '"+loginId+"'))";
			   ResultSet rs=null;
			   DataBase db=new DataBase();
			   try{
			   db.connectDb();
			   rs=db.queryAll(sql1);
			   System.out.println("查询电表id是否存在"+sql1.toString());
			   while(rs.next()){
				   did=rs.getString(1);
				  // dianfei=rs.getString(2);
				 //  beilv1=rs.getString(3);
				  // linelosstype1=rs.getString(4);
				  // linelossvalue1=rs.getString(5);
			   }
			   db.close();
			   rs.close();
			   }catch(Exception e){
				   e.printStackTrace();
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
			 
			  
			  
			

			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  //========金额
			  if(b[6].toString().trim().equals("")||b[6]==null){
				  for(int j=0;j<b.length;j++){
					  row.add(b[j].toString().trim());
				  }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"金额为空");
				  wrongContent.add(row);
				  continue;
			  }
			  else if(pattern1.matcher(b[6].toString()).matches()==false){
				  for(int j=0;j<b.length;j++){
					  row.add(b[j].toString().trim());
				  }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"金额格式不正确！");
				  wrongContent.add(row);
				  continue;
				  
			  }
			 
			 
			
			  if(b[7].toString().trim().equals("")||b[8].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"起始结束年月为空");
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
			//==========================
			 Pattern pattern5 = Pattern.compile(datePattern2); 
			
//			  }
			  
			  SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
			  Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
			  Pattern pattern34 = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02]))|((0[2469]|11))))|(02)");
				  //Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)");
			  
			startmonth=b[7].toString().trim();
			System.out.println(startmonth+"====000000000");

			  if((pattern2.matcher(startmonth).matches()==true&&(b[7].toString().trim().contains("/")&&b[7].toString().trim().length()<=7))||(pattern34.matcher(startmonth).matches()==true&&(b[7].toString().trim().contains("-")&&b[7].toString().trim().length()<=7))){

		         if(b[7].toString().trim().contains("/")){
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
		        	 if(b[7].toString().trim().length()<7){
							try {
								Date end=sdf1.parse(b[6].toString().trim());
								startmonth=sdf1.format(end);
							} catch (ParseException e) {
								e.printStackTrace();
							}  
					 }else{
						 startmonth=b[7].toString().trim();
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
			  String str111=b[8].toString().trim();
			  endmonth = str111.replaceAll(" ", ""); 
			  if((pattern2.matcher(endmonth).matches()==true&&(b[8].toString().trim().contains("/")&&b[8].toString().trim().length()<=7))||(pattern34.matcher(endmonth).matches()==true&&(b[8].toString().trim().contains("-")&&b[8].toString().trim().length()<=7))){
				  if(b[8].toString().trim().contains("/")){
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
					 if(b[8].toString().trim().length()<7){
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
			
			  String str188=b[9].toString().trim();
			  accountmonth = str188.replaceAll(" ", ""); 		 
			  if(b[9].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"报账月份为空");
				  wrongContent.add(row);
				  continue;
			  }
			  
			  if((pattern2.matcher(accountmonth).matches()==true&&(b[9].toString().trim().contains("/")&&b[9].toString().trim().length()<=7))||(pattern2.matcher(accountmonth).matches()==true&&(b[9].toString().trim().contains("-")&&b[9].toString().trim().length()<=7))){
				  if(b[9].toString().trim().contains("/")){   
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
			 if(b[10].toString().trim().equals("")||pattern1.matcher(b[10].toString().trim()).matches()==true){
                      dianliang=b[10].toString().trim();
			 }else{
				 for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"电量格式不正确");
				  wrongContent.add(row);
				  continue;
				 
			 }
		  if(!b[11].toString().trim().equals("")&&b[11].toString().trim()!=null){		 
			  String str211=b[11].toString().trim();
			  notetime = str211.replaceAll(" ", ""); 
			  if((b[11].toString().trim().contains("/")&&pattern4.matcher(notetime).matches()==true)||b[11].toString().trim().contains("-")&&isValidDate(b[11].toString().trim())==true){
				  if(b[11].toString().trim().contains("/")){
			          try {
			        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			        	Date date =sdf.parse(b[11].toString().trim());
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
			        	
						try {
							Date date = sdf1.parse(lastdate);
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
			  notetime=b[11].toString().trim();
		  }
		  String str222=b[12].toString().trim();
		  kptime = str222.replaceAll(" ", "");
		  System.out.println("*************************"+kptime);
		  if(!b[12].toString().trim().equals("")&&b[12].toString().trim()!=null){	
			 
				  if((b[12].toString().trim().contains("/")&&pattern4.matcher(kptime).matches()==true)||b[12].toString().trim().contains("-")&&isValidDate(b[12].toString().trim())==true){
				  if(b[12].toString().trim().contains("/")){
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
			  kptime=b[12].toString().trim();
		  }
		  
//		  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
//		  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
//		  //========金额
//		  if(b[6].toString().trim().equals("")||b[6]==null){
//			  for(int j=0;j<b.length;j++){
//				  row.add(b[j].toString().trim());
//			  }
//			  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"金额为空");
//			  wrongContent.add(row);
//			  continue;
//		  }
//		  else if(pattern1.matcher(b[6].toString()).matches()==false){
//			  for(int j=0;j<b.length;j++){
//				  row.add(b[j].toString().trim());
//			  }
//			  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"金额格式不正确！");
//			  wrongContent.add(row);
//			  continue;
//			  
//		  }
		  String pjje=b[15].toString().trim();
		  if("".equals(pjje)||null==pjje){
              pjje="0";			  
		  }
		  else if(pattern1.matcher(pjje).matches()==false){
			  for(int j=0;j<b.length;j++){
			  row.add(b[j].toString().trim());
		  }
		  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"开票金额格式不正确！");
		  wrongContent.add(row);
		  continue;
	  }
		  
		  
		  //判断导入的数据是否重复
    	 DecimalFormat df1 = new DecimalFormat("#.00");
		  String sql ="select * from yufufeiview t,dianbiao db,zhandian e where  db.dbid='"+dbid1+"' and to_char(t.startmonth,'yyyy-mm') ='"+startmonth+"' and to_char(t.endmonth,'yyyy-mm') ='"+endmonth+"' and to_char(t.accountmonth,'yyyy-mm')='"+accountmonth+"' and  db.dbid = t.prepayment_ammeterid and e.id = t.stationid";
	     System.out.println("判断导入的数据是否重复:"+sql);
	      DataBase db1 = new DataBase();     
	      ArrayList<String> listzd=new ArrayList<String>() ;
	      rs=null;
	      try {  	 
			rs = db1.queryAll(sql);	
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
					db1.close();
				} catch (DbException de) {
					de.printStackTrace();
			}
			}
			  //System.out.println("thisdatetime"+lastdatetime+"thisdatetime"+thisdatetime+"startmonth"+startmonth+"endmonth"+endmonth+"inputdatetime"+inputdatetime+"accountmonth"+accountmonth+"notetime"+notetime+"kptime"+kptime+"paydatetime"+paydatetime);
			  //String amuuid = UUID.randomUUID().toString().trim().replace("-","");
			  //分摊电量、电费
			  //获取电表分摊比例		  
			  AmmeterDegreeFormBean beanm=new AmmeterDegreeFormBean();
			  ArrayList list = new ArrayList();
			  list = beanm.getStationMhchexkt(dbid1);		  
			  String shuoshuzhuanye="";
			  String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="",dbili6="";
			  String blhdl1="",blhdl2="",blhdl3="",blhdl4="",blhdl5="";
			  String actualpay1="",actualpay2="",actualpay3="",actualpay4="",actualpay5="",actualpay6="";
			  double wucha = 0.0000001;
		      DecimalFormat df = new DecimalFormat("#.00");
			  if(list!=null){
					int fycount = ((Integer)list.get(0)).intValue();
					for( int k = fycount;k<list.size()-1;k+=fycount){
					shuoshuzhuanye = (String)list.get(k+list.indexOf("SHUOSHUZHUANYE"));
				    shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
				    dbili = (String)list.get(k+list.indexOf("DBILI"));
				    dbili = dbili != null ? dbili : "0";
				    if(shuoshuzhuanye.equals("zylx01")){
				       dbili1=dbili;
				    }
				     if(shuoshuzhuanye.equals("zylx02")){
				      dbili2=dbili;		    
				    }
				     if(shuoshuzhuanye.equals("zylx03")){		    
				      dbili3=dbili;
				    }
				     if(shuoshuzhuanye.equals("zylx04")){
				      dbili4=dbili;		    
				    }
				     if(shuoshuzhuanye.equals("zylx05")){
				      dbili5=dbili;		    
				    }
				     if(shuoshuzhuanye.equals("zylx06")){
					      dbili6=dbili;		    
					    }
			   }
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
			  
		      feesformBean= new ElectricFeesFormBean();
		      String pjlx1="";
		      if(b[12].toString().trim().equals("发票")){
		    	  pjlx1="pjlx03";
		      }else if(b[12].toString().trim().equals("支票")){
		    	  pjlx1="pjlx02";
		      }else{
		    	  pjlx1=b[12].toString().trim();
		      }
		      feesformBean.setNotetypeid(pjlx.equals("")?"pjlx03":pjlx);
		      feesformBean.setNoteno(b[13].toString().trim());
		      String m=b[6].toString().trim();
		  if(m!=null&&!m.equals("")){
		      DecimalFormat nf = new DecimalFormat("0.00");
		    m=nf.format(Double.parseDouble(b[6].toString().trim()));
		  }

		
		  System.out.println();
		      feesformBean.setActualpay(m);
		     // feesformBean.setActualpay(b[6].toString().trim());
		      feesformBean.setNotetime(notetime);
		    feesformBean.setStartmonth(startmonth);
		      feesformBean.setEndmonth(endmonth);
		      feesformBean.setAccountmonth(accountmonth);
		      feesformBean.setDianliang(dianliang);
		      feesformBean.setMemo(b[16].toString().trim());
		      feesformBean.setAmmererid(dbid1);
		      feesformBean.setStationid(b[4].toString().trim());
		      feesformBean.setKptime(kptime);
		      feesformBean.setEntrypensonnel(accountname);
		      feesformBean.setEntrytime(mat.format(new Date()));
		      feesformBean.setPjje(Double.parseDouble(pjje));
		      System.out.println(b[4]+"站点ID");
		      
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
		      String bzww="0";
		    String  msg=fees.addFeesDegreehtd(feesformBean,bzww,startmonth,endmonth);
	         if(msg.equals("添加合同单成功!")){
	        	// System.out.println(b[9]+"添加电费成功");
		      }
	         else{
	        	   System.out.println(b[9]+"添加合同单失败");
	        	   for(int j=0;j<b.length;j++){
	        		   row.add(b[j].toString().trim());
	        	   }
		    	  row.add(b[1].toString()+b[2].toString()+dbid1+"添加合同单出错!");
		    	  wrongContent.add(row);
		    	  continue;
		    	  
		      }
		 // System.out.println(content.size()+"  "+wrongContent.toString()+" "+row.size());
		 

		}
		  cform.setCg((content.size()-1)-wrongContent.size());
		  cform.setBcg(wrongContent.size());
		  cform.setZg((content.size()-1));
		 
		  
		  return wrongContent;
	  
	  
	  }
	  //批量修改站点单价
	  public synchronized Vector getWrongdj(Vector content,CountForm cform,String accountname,String loginId) {
		  String zdid="",startmonth="",dianliang="",endmonth="",accountmonth="",notetime="",kptime="",pjlx="",pjbh="",memo="";
		  Object[] a=content.toArray();
		  ZhanDianForm zhanbean= new ZhanDianForm();
		  ElectricFeesBean fees = new ElectricFeesBean();
		  for(int i=1;i<a.length;i++){		
			  String zhandianid="";
			  
			  Vector<String> row=new Vector<String>();
			  Object[] b=((Vector)a[i]).toArray();
			  zdid=b[0].toString().trim();
			  String did="",dianfei="",beilv1="",linelosstype1="",linelossvalue1="";
			   if("".equals(b[0].toString().trim())||null==b[0].toString().trim()){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[1].toString()+"的站点代码"+b[0].toString().trim()+"为空");
				  wrongContent.add(row);
				  continue;
				  
			  }
			  
			  String sql1=" select zd.zdcode from zhandian zd " +
			  		" where zd.qyzt = '1' and zd.shsign = '1' " +
			  		"AND zd.zdcode='"+zdid+"' AND (ZD.XIAOQU IN(SELECT t.AGCODE FROM PER_AREA t WHERE t.ACCOUNTID = '"+loginId+"'))";
			   ResultSet rs=null;
			   DataBase db=new DataBase();
			   try{
			   db.connectDb();
			   rs=db.queryAll(sql1);
			   System.out.println("查询站点代码是否存在"+sql1.toString());
			   while(rs.next()){
				   zhandianid=rs.getString(1);
			   }
			   rs.close();
			   db.close();			   
			   }catch(Exception e){
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
			   
			  if("".equals(zhandianid)||zhandianid==null){
				  System.out.println("未查到站点代码"+zdid);
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[1].toString()+"站点代码"+zdid);
				  wrongContent.add(row);
				  continue;
			  }
			 
			  
			  
			

			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  //========金额
			  if(b[4].toString().trim().equals("")||b[4]==null){
				  for(int j=0;j<b.length;j++){
					  row.add(b[j].toString().trim());
				  }
				  row.add(b[1].toString()+"站点单价为空");
				  wrongContent.add(row);
				  continue;
			  }
			  else if(pattern1.matcher(b[4].toString()).matches()==false){
				  for(int j=0;j<b.length;j++){
					  row.add(b[j].toString().trim());
				  }
				  row.add(b[1].toString()+"站点单价格式不正确！");
				  wrongContent.add(row);
				  continue;
				  
			  }
			 
			  String dj=b[4].toString().trim();
			  if(dj==null||"".equals(dj)){dj="0";}
			  DecimalFormat nf = new DecimalFormat("0.0000");
			 dj=nf.format(Double.parseDouble(dj));
			 System.out.println("danjia---===="+dj);
		  zhanbean.setDianfei(dj);
		  zhanbean.setZdcode(b[0].toString().trim());
		      String bzww="0";
		    String  msg=fees.addFeesDegreedj(zhanbean,bzww,startmonth,endmonth);
	         if(msg.equals("修改站点单价成功!")){
		      }
	         else{
	        	   for(int j=0;j<b.length;j++){
	        		   row.add(b[j].toString().trim());
	        	   }
		    	  row.add(b[1].toString()+zdid+"修改站点单价出错!");
		    	  wrongContent.add(row);
		    	  continue;
		    	  
		      }
		 // System.out.println(content.size()+"  "+wrongContent.toString()+" "+row.size());
		 

		}
		  cform.setCg((content.size()-1)-wrongContent.size());
		  cform.setBcg(wrongContent.size());
		  cform.setZg((content.size()-1));
		 
		  
		  return wrongContent;
	  
	  
	  }
	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String amuuid) {
		  String sql ="select ammeterdegreeid from AMMETERDEGREES where uuid='"+amuuid+"'";
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
			db.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return flag;
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
	  
	  
}
