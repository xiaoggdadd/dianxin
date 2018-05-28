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
	  //��ѵ�����
	  public synchronized Vector getWrong(Vector content,CountForm cform,String accountname,String loginId) {
		  String lastdatetime="";
		  Date date1 = null,date2=null;
		  String thisdatetime="",startmonth="",endmonth="",inputdatetime="",accountmonth="",notetime="",kptime="",paydatetime="";
		  String dbid1="";  String blhdl=""; String actualpay="";
		  //System.out.println("����"+content);
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
			   //������2012-12-3 
			   Properties pro=new Properties();
			   pro.setProperty("1", dbid1);
			   pro.setProperty("2", loginId);
			  // db.connectDb();
			 //  System.out.println(sql1);
			 //  System.out.println(pro.getProperty("1"));
			 //  System.out.println(pro.getProperty("2"));
			   System.out.println("��ѵ������ѯ��䣺"+sql1);
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
				  System.out.println("δ�鵽���"+dbid1);
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("δ�鵽"+b[0].toString()+b[2].toString()+"���"+dbid1);
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴε�������ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str4==""){		
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴε�����Ϊ��"); 
				  wrongContent.add(row);
				  continue;
				  }
			  String str55=b[5].toString().trim();
			  String str5 = str55.replaceAll(" ", "");
			  if(pattern.matcher(str5).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���ε�������ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(str5==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���ε�����Ϊ��"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[6].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴγ���ʱ��Ϊ��"); 
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[7].toString().trim()==""){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���γ���ʱ��Ϊ��"); 
				  wrongContent.add(row);
				  continue;
			  }
			  String str88=b[8].toString().trim();
			  String str8 = str88.replaceAll(" ", "");
			  if(pattern1.matcher(str8).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�õ���������ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str8.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�õ�������Ϊ��");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ���õ�����ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }else  if(str9.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				//  System.out.println("-3-"+str9+"--");
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ���õ���Ϊ��");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[10].toString().trim().equals("")||b[11].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʼ�����·�Ϊ��");
				  wrongContent.add(row);
				  continue;
			  }
			  String str155=b[15].toString().trim();
			  String str15 = str155.replaceAll(" ", "");
			  if(pattern.matcher(str15).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���۸�ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str15.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����Ϊ��");
				  wrongContent.add(row);
				  continue;
			  }
			  String str166=b[16].toString().trim();
			  String str16 = str166.replaceAll(" ", "");
			  if(pattern1.matcher(str16).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���õ�����ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str16.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���õ���Ϊ��");
				  wrongContent.add(row);
				  continue;
			  }
			  String str177=b[17].toString().trim();
			  String str17 = str177.replaceAll(" ", "");
			  if(pattern1.matcher(str17).matches()==false){	  
				  for(int j=0;j<b.length;j++){
		      		   row.add(b[j].toString().trim());
		      	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ�ʵ�ѽ���ʽ����ȷ"); 
				  wrongContent.add(row);
				  continue;
			  }else if(str17.equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ�ʵ�ѽ��Ϊ��");
				  wrongContent.add(row);
				  continue;
			  }
			  if(b[18].toString().trim().equals("")){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�����·�Ϊ��");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴγ���ʱ��Ϊ��   ����   ��ʽ����ȷ");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴγ���ʱ���ʽ����ȷ!!");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���γ���ʱ��Ϊ��   ����   ��ʽ����ȷ");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"���γ���ʱ���ʽ����ȷ!!");
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
						  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�ϴγ���ʱ����ڱ��γ���ʱ��");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʼ����Ϊ�ջ��߸�ʽ����ȷ");
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
							  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʼ�·ݴ��ڽ����·�");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��������Ϊ�ջ��߸�ʽ����ȷ");
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
					  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����ʱ���ʽ����ȷ!!");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����ʱ��Ϊ��");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����ʱ���ʽ����ȷ!!");
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
						  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"�����·�Ϊ�ջ��߸�ʽ����ȷ");
						  wrongContent.add(row);
						  continue;
					  }
//			  String str177=b[17].toString().trim();
//			  String str17 = str177.replaceAll(" ", "");
//			  if(pattern.matcher(str17).matches()==false){	  
//				  for(int j=0;j<b.length;j++){
//		      		   row.add(b[j].toString().trim());
//		      	      }
//				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ�ʵ�ѽ���ʽ����ȷ"); 
//				  wrongContent.add(row);
//				  continue;
//			  }else if(str17.equals("")){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"ʵ�ʵ�ѽ��Ϊ��");
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
		  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"Ʊ�ݽ���ʽ����ȷ"); 
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"Ʊ��ʱ���ʽ����ȷ");
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
				  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"Ʊ��ʱ���ʽ����ȷ!!");
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
					  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��Ʊʱ���ʽ����ȷ");
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
					  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��Ʊʱ���ʽ����ȷ!!");
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
					  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����ʱ���ʽ����ȷ");
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
					  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"����ʱ���ʽ����ȷ!!");
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
			     //System.out.println("2ϵͳ-"+danjia2);
				 if(!danjia1.equals(danjia2)){
			    	  for(int j=0;j<b.length;j++){
			       		   row.add(b[j].toString().trim());
			       	      }
				    	  row.add("���ۺ�վ�㵥�۲�һ��!ϵͳ����Ϊ��"+danjia2);	    	  
				    	  wrongContent.add(row);
				    	  continue;					    	   		      
				// }	
			}

		//�Ƚ�ʵ���õ�����ʵ�ʽ��ѽ��  
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
	    if(linelosstype1.equals("�������")){
	    	if(linelossvalue==0)  linelossvalue=1.0;
	    	if(beilv==0)  beilv=1.0;
	    	//ʵ�ʺĵ���=����*�������ε�����-��ʼ�����룩*�������[��+����ֵ]+����������
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
	 	    	  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʵ���õ��������Ľ����һ��!!ϵͳ������Ϊ��"+jsdeg);	    	  
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
	      	    	  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʵ���õ��������Ľ����һ��!ϵͳ������Ϊ��"+jsdeg);	    	  
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
	  	    	  row.add(b[0].toString()+b[2].toString()+"���"+dbid1+"��ʵ�ʵ�ѽ�������Ľ����һ��!ϵͳ������Ϊ��"+jsmoneys);	    	  
	  	    	  wrongContent.add(row);
	  	    	  continue;
	    }
//	    if(!jsmoneys.equals(moneys)){
//	    	
//	    }   
	}
		  //�жϵ���������Ƿ��ظ�
    	 DecimalFormat df1 = new DecimalFormat("0.00");
    	 String lastdegrees2 = df1.format(Double.parseDouble(b[4].toString().trim().equals("")?"0":b[4].toString().trim()));
		 String thisdegrees2 = df1.format(Double.parseDouble(b[5].toString().trim().equals("")?"0":b[5].toString().trim()));
		  String sql ="SELECT 'A'FROM dbtemp_09_view T, DIANBIAO DB, DFTEMP_011_VIEW E WHERE DB.DBID = T.AMMETERID_FK AND DB.DBID =? AND T.LASTDEGREE =? AND T.THISDEGREE =? AND TO_CHAR(T.LASTDATETIME,'yyyy-mm-dd') =? AND TO_CHAR(T.THISDATETIME,'yyyy-mm-dd') =? AND T.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND T0_CHAR(E.ACCOUNTMONTH,'yyyy-mm') =?";
	     System.out.println("�жϵ���������Ƿ��ظ�:"+sql);
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
	       		System.out.println("1212121�����ظ�����21"+rs.toString());
		    	row.add("�����ظ�����!");	    	  
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
		      
			  //��̯���������
			  //��ȡ����̯����		  
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
		       
		       //��̯����(��ע����0.0000001��Ϊ���DecimalFormat������������С��λ����0.005����������)
		       Pattern pthdl = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?"); 
		        pattern.matcher(blhdl).matches();
		       if(!"".equals(blhdl)||blhdl!=null||blhdl!="0"||!"o".equals(blhdl)){
			       //����
		    	   blhdl1=df.format((new Double(dbili1).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //Ӫҵ
		    	   blhdl2=df.format((new Double(dbili2).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
			       //�칫
		    	   blhdl3=df.format((new Double(dbili3).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //��Ϣ��	
		    	   blhdl4=df.format((new Double(dbili4).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           // ����Ͷ��
		    	   blhdl5=df.format((new Double(dbili5).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		           //�������
		    	   blhdl6=df.format((new Double(dbili6).doubleValue()/100)*new Double(blhdl).doubleValue()+wucha);
		       }else{
		    	   blhdl1="0.00";
		    	   blhdl2="0.00";
		    	   blhdl3="0.00";
		    	   blhdl4="0.00";
		    	   blhdl5="0.00";
		    	   blhdl6="0.00";
		       }

		       //��̯���(��ע����0.0000001��Ϊ���DecimalFormat������������С��λ����0.005����������)
			  if(!"".equals(actualpay)||actualpay!=null||actualpay!="0"||!"o".equals(actualpay)){
			       //����
			       actualpay1=df.format((new Double(dbili1).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           //Ӫҵ
			       actualpay2=df.format((new Double(dbili2).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
			       //�칫
			       actualpay3=df.format((new Double(dbili3).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           //��Ϣ��	
			       actualpay4=df.format((new Double(dbili4).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           // ����Ͷ��
			       actualpay5=df.format((new Double(dbili5).doubleValue()/100)*new Double(actualpay).doubleValue()+wucha);
		           // ������
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
		      //��̯����
		  		//����
		      formBean.setScdl(blhdl1);
		      	//�칫
		      formBean.setYydl(blhdl2);
		      	//Ӫҵ
		      formBean.setBgdl(blhdl3);
		      	//��Ϣ�� 
		      formBean.setXxhdl(blhdl4);
		      	//����Ͷ��
		      formBean.setJstzdl(blhdl5);
		      	//�������
		      formBean.setDddfdl(blhdl6);
		      
		      feesformBean= new ElectricFeesFormBean();
		      
		      String bzw="0";
		    //ͨ��������ȡ����id
		      String msg="";
		      msg=bean.addDegreeFees(formBean, uuid,bzw,fylist);
		      if(msg.equals("��ӵ����ɹ�!")){
		    	 // System.out.println(b[9]+"��ӵ����ɹ�");
		      }
		      else{
		    	  System.out.println(b[3]+"��ӵ���ʧ��");
		    	  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
		    	  row.add(b[0].toString()+b[1].toString()+b[2].toString()+dbid1+"��ӵ�������!");
		    	  
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
		      if(b[19].toString().trim().equals("��Ʊ")){
		    	  pjlx="pjlx03";
		      }else if(b[19].toString().trim().equals("֧Ʊ")){
		    	  pjlx="pjlx02";
		      }else if(b[19].toString().trim().equals("��ѷָ")){
		    	  pjlx="pjlx04";
		      }else if(b[19].toString().trim().equals("�վ�")){
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
		      
		      //��̯���
		      //����
		      feesformBean.setScdff(actualpay1);
		      //Ӫҵ
		      feesformBean.setYydf(actualpay2);
		      //�칫
		      feesformBean.setBgdf(actualpay3);
		      //��Ϣ��
		      feesformBean.setXxhdf(actualpay4);
		      //����Ͷ��
		      feesformBean.setJstzdl(actualpay5);
		      //������
		      feesformBean.setDddfdf(actualpay6);
		      
		     // System.out.println(start+"/*****"+end);
		      
		      ElectricFeesBean beana = new ElectricFeesBean();
		      List ammeterdegreeid=new ArrayList();
		      ammeterdegreeid =beana.getAmUuid(uuid);
		      System.out.println("�������ɲ�ѯid:"+ammeterdegreeid); 
		      int flag=feesformBean.getFlag(ammeterdegreeid.get(0).toString());
		      System.out.println("flag------>>>:"+flag);
		      feesformBean.setFlag(flag);
		      //ȡ���������Զ���˱�־
		       String autoauditstatus =feesformBean.getZdshbz(ammeterdegreeid.get(0).toString());
		       feesformBean.setAutoauditstatus(autoauditstatus);
		       System.out.println("autoauditstatus----->>>"+autoauditstatus);
		      msg=fees.addFeesDegree(feesformBean,startmonth,endmonth,this.getDLID(uuid),bzw,fylist);
	         if(msg.equals("��ӵ�ѳɹ�!")){
	        	// System.out.println(b[9]+"��ӵ�ѳɹ�");
		      }
	         else{
	        	   System.out.println(b[9]+"��ӵ��ʧ��");
	        	   for(int j=0;j<b.length;j++){
	        		   row.add(b[j].toString().trim());
	        	   }
		    	  row.add(b[1].toString()+b[2].toString()+dbid1+"��ӵ�ѳ���!");
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
	     // System.out.println("--���amuuid����id--"+sql);
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
	   * �����������
	   */
	  public synchronized Vector getWrongFee(Vector content,String idStr) {
		    StringBuffer sql = new StringBuffer();
		    int m = 0, i = 0;
		    ArrayList fylistn = new ArrayList();
		    AmmeterDegreeBean abean= new AmmeterDegreeBean();
		    Vector row=new Vector();
		    System.out.println("���"+content);
		    return row;
		    /*
		      catch (Exception e) {
		        e.printStackTrace();
		        row.add(e.toString());
		        wrongContent.add(row);
		      }
		     }//m����>1
		    }
		    System.out.println("wrongContent:"+wrongContent);
		    return wrongContent;*/
		  }
	  
	  

	  private boolean getDBID(String con) {
	      String sql ="select id from dianbiao where dbid='"+con+"'";
	      System.out.println("--���code����id--"+sql);
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
	   * У����Ϸ���
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
			 System.out.println("���["+dbbm+"] ������");
			 return false;
		 }
	  }

	  /**
	   * �Ƿ��ظ���Ѽ�¼   <p>ͬһ�����,��¼��ĵ�ѵ���������С�ڵ������еĵ�ѵ�����������Ϊ�ظ�����</p>
	   * @param dbid ���id
	   * @param endtime ��������
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
	   * <p>��ͨ��ת����
	   *    ������ܽ������-����ֹ����-��ʼ����������*���� </p>
	   * <p>��ҵ��ֱ����
	   *    ���������</p>
	   * @param meterVo ���ʵ��
	   * @param vo �������ʵ��
	   * @return
	   */
	  public String getElectricLoss(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  if (isDirectedPowerSupply(meterVo)) {// ֱ����
			  BigDecimal Allmoney = new BigDecimal(vo.getAllmoney());
			  BigDecimal sqds = new BigDecimal(vo.getSqds());
			  BigDecimal bqds = new BigDecimal(vo.getBqds());
			  BigDecimal beilv = new BigDecimal(vo.getBeilv());
			  BigDecimal price = new BigDecimal(vo.getPrice());
			  //������
			  BigDecimal dsje = Allmoney.subtract(bqds.subtract(sqds).multiply(beilv)
					  .multiply(price));
			  return dsje.toString();
		  } else {
			  return vo.getDiansun();
		  }
		  
	  }

	  /**
	   * <p>��ͨ��ת����
	   *    ���ۣ����ĵ���</p>
	   * <p>��ҵ��ֱ����
	   *    ���ۣ��ܽ����ԣ�(��ֹ����-��ʼ����������+�������)</p>
	   * @param meterVo ���ʵ��
	   * @param vo �������ʵ��
	   * @return
	   */
	  public String getElectricfeesPrice(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  if (isDirectedPowerSupply(meterVo)) {// ֱ����
			  BigDecimal Allmoney = new BigDecimal(vo.getAllmoney());
			  BigDecimal sqds = new BigDecimal(vo.getSqds());
			  BigDecimal bqds = new BigDecimal(vo.getBqds());
			  BigDecimal beilv = new BigDecimal(vo.getBeilv());
			  BigDecimal diansun = new BigDecimal(vo.getDiansun());
			  //����
			  BigDecimal danjia = new BigDecimal(0);
			  try {
				
				  danjia = Allmoney.divide(bqds.subtract(sqds).multiply(beilv)
						  .add(diansun), 4, RoundingMode.HALF_UP);
			} catch (Exception e) {
				System.out.println("���ۼ����쳣 " +e);
				danjia.setScale(4);
			}
			  System.out.println("dianjia="+ danjia);
			  return danjia.toString();
			  
		  } else {
			  return meterVo.getDj();
		  }
		  
	  }

	  /**
	   * �Ƿ�ֱ���磺 ֱ���緵��true
	   * @param meterVo ���ʵ��
	   * @return
	   */
	  public boolean isDirectedPowerSupply(ElectricmeterVo meterVo) {
		  if (meterVo.getGdfs() != null && meterVo.getGdfs().equals("gdfs05")) {// ֱ����
			  return true;
		  } else {
			  return false;
		  }
	  }
	  /**
	   * �Ƿ�ֱ���磺 ֱ���緵��true
	   * @param dbbm ������
	   * @return
	 * @throws DbException 
	   */
	  public boolean isDirectedPowerSupply(String dbbm) throws DbException {
		  //�����Ϣ
		  ElectricmeterVo meterVo = getDb(dbbm);
		  if (meterVo.getGdfs() != null && meterVo.getGdfs().equals("gdfs05")) {// ֱ����
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
	  /**
	   * ���� =����ֹ����-��ʼ���� ��*����
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
	   * ˰����㹫ʽ : �ܽ��/(1+˰��)*˰��   ������λС��
	   * 
	   * @param meterVo ���ʵ��
	   * @param vo �������ʵ��
	   * @return
	   */
	  public String getTax(ElectricmeterVo meterVo, ElectricfeesVo vo) {
		  BigDecimal allmoney = new BigDecimal(vo.getAllmoney());
		  BigDecimal zzsl = new BigDecimal(meterVo.getZzsl());
		  BigDecimal one = new BigDecimal(1);
		  //˰�����
		  BigDecimal tax = new BigDecimal(0);
		  try {
			  tax = allmoney.divide(zzsl.add(one), 6, RoundingMode.HALF_UP)
						.multiply(zzsl).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			System.out.println("˰������쳣 " +e);
			tax.setScale(2);
		}
  
		  return tax.toString();
	  }
	  public Long getElectricdays(ElectricfeesVo efVo) {
		  long day =  CTime.getQuot(efVo.getStarttime(), efVo.getEndtime());
		  return day;
	  }

	  /**
	   * �վ��õ��� = �õ��� /�õ�����  ������λС��
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
			System.out.println("�վ��õ��������쳣, " +e);
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
			System.out.println("�����ֻ������վ������쳣 " +e);
			avg_c.setScale(2);
		}
		  return avg_c.toString();
	  }
	  
	  /**
	   *<p>����ƫ���� = ʵ���õ��� / �����õ���</p>
	   *<p>�����õ����� �õ���  + (ʵ���õ�����-�õ�����) * �վ��õ���</p>
	   *<p>ʵ���õ���: ��Դ�ֻ������õ���</p>
	   *<p>ʵ���õ�����: ��Դ�ֻ������õ�����</p>
	   * @param efVo
	   * @return
	   */
	  public String getElectricdeviateNo(ElectricfeesVo efVo) {
		  BigDecimal dianliang = new BigDecimal(efVo.getDianliang());//�õ���
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());//�õ�����
		  BigDecimal rjydl =  new BigDecimal(efVo.getRjydl()); //�վ��õ���
		  BigDecimal daynum_c = new BigDecimal(efVo.getDaynum_c());//ʵ���õ�����
		  BigDecimal dianliang_c =  new BigDecimal(efVo.getDianliang_c()); //ʵ���õ���
		  //�������ƫ����
		  BigDecimal dlpls = new BigDecimal(0.00);
		  try {
			  dlpls = dianliang_c.divide(
					  dianliang.add(daynum_c.subtract(daynum).multiply(rjydl)), 2,
					  RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("����ƫ���������쳣  " +e);
			dlpls.setScale(2);
		}
		  System.out.println("dlpls="+dlpls);

		  return dlpls.toString();
	  }

	  /**
	   * ����ƫ���� = ��������-�����������-1
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
	   * PUE=�����õ���/[��ֱ�����豸����*54V?+δ��صĽ������豸����+δ��ص�ֱ�����豸���ʣ�/0.9 *����*24Сʱ/1000]
	   * @param efVo
	   * @return
	   */
	  public String getElectricPue(ElectricmeterVo meterVo, ElectricfeesVo efVo) {
		  BigDecimal dianliang = new BigDecimal(efVo.getDianliang());//�õ���
		  BigDecimal jiaoliu = new BigDecimal(meterVo.getJiaoliu());//δ��صĽ������豸����
		  BigDecimal zhiliu = new BigDecimal(meterVo.getZhiliu());//δ��ص�ֱ�����豸����
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());//�õ�����
		  BigDecimal dianliu = new BigDecimal(efVo.getCurrentVo().getValue());//ֱ�����豸����
		  //����Pueֵ
		  BigDecimal pue = new BigDecimal(0);
		  try {
			  pue = dianliang.divide(
						dianliu.multiply(new BigDecimal(54)).add(jiaoliu).add(zhiliu)
								.divide(new BigDecimal(0.9), 6, RoundingMode.HALF_UP)
								.multiply(daynum).multiply(new BigDecimal(24))
								.divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP),
						2, RoundingMode.HALF_UP);
		 } catch (Exception e) {
			  System.out.println("PUE�����쳣 " +e);
			  pue.setScale(2);
		 }
		  System.out.println("pue="+pue);
		  return pue.toString();
	  }

	  /**
	   * ��˵���=(0.5*ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE*���豸����/0.9*24Сʱ*����ϵ��+0.2*��վȥ��ͬ���վ�����+0.3*��վ�ϴα����վ�����)*���νɷ�����
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
		  BigDecimal avgPue = new BigDecimal(pjpue);//ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE
		  BigDecimal rjydl = new BigDecimal(pjydl); //��վȥ��ͬ���վ�����
		  BigDecimal zsbgl = new BigDecimal(getEquipmentPower(meterVo, efVo)); //���豸����
		  //ElectricCurrentVo ecVo = getElectricCurrent(meterVo, efVo);//ֱ�����豸����
		  String defaut_value = "14";//û�м�ص���Ĭ��14A
		  if(efVo.getCurrentVo() != null){
			  defaut_value = efVo.getCurrentVo().getValue();
		  }
		  BigDecimal fwxs = new BigDecimal(getHouseRatio(meterVo.getHousetypeid(), defaut_value));
		  BigDecimal sqrjydl = new BigDecimal(efVo.getSqrjydl());
		  BigDecimal daynum = new BigDecimal(efVo.getDaynum());
		  
		  //�����˵���
		  BigDecimal bgdl = new BigDecimal(0);
		  try {
			  bgdl = new BigDecimal(0.5).multiply(avgPue).multiply(zsbgl)
				.divide(new BigDecimal(0.9), 6, RoundingMode.HALF_UP).multiply(fwxs)
				.add(new BigDecimal(0.2).multiply(rjydl))
				.add(new BigDecimal(0.3).multiply(sqrjydl)).multiply(daynum);
			  bgdl = bgdl.setScale(2, RoundingMode.HALF_UP);//������λС��
		} catch (Exception e) {
			System.out.println("�����˵����쳣 " +e);
			bgdl.setScale(2);
		}
		  System.out.println("��˵���=" + bgdl);
		  return bgdl.toString();
	  }

	  /**
	   * ���ƫ���� =���õ���-��˵�����/��˵��� *100
	   * @param efVo
	   * @return
	   */
	  public String getBenchmarkingDeviateRate(ElectricfeesVo efVo){
		 BigDecimal dl = new BigDecimal(efVo.getDianliang());
		 BigDecimal bgdl = new BigDecimal(efVo.getBgdl());
		 //���ƫ����
		 BigDecimal bgpll = new BigDecimal(0.00);
		 try {
			bgpll = dl.subtract(bgdl).divide(bgdl, 6, RoundingMode.HALF_UP)
					.multiply(new BigDecimal(100))
					.setScale(2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			System.out.println("���ƫ���ʼ����쳣 " +e);
			bgpll.setScale(2);
		}
		 return bgpll.toString();
	  }
	  
	  
	  /**
	   * ���豸����
	   * @param meterVo
	   * @param efVo
	   * @return
	   */
	  public String getEquipmentPower(ElectricmeterVo meterVo, ElectricfeesVo efVo){
		  BigDecimal jiaoliu = new BigDecimal(meterVo.getJiaoliu());//δ��صĽ������豸����
		  BigDecimal zhiliu = new BigDecimal(meterVo.getZhiliu());//δ��ص�ֱ�����豸����
		  BigDecimal dl = new BigDecimal(efVo.getCurrentVo().getValue());//����
		  BigDecimal sbgl = dl.multiply(new BigDecimal(54)).add(jiaoliu)
				.add(zhiliu);
		  return sbgl.toString();
	  }
	  
	  
	  /**
	   * ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE
	   * @param agcode ���б���
	   * @param date ϵͳ��ǰʱ��
	   * @return
	   */
	  public String getLastYearMonthAvgPue(String agcode, Date date) throws DbException {
		  
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  c.add(Calendar.YEAR, -1);//��һ��
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  
		  String date_str = CTime.formatRealMonth(c.getTime());
		  
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select a.addtime"+month+" from analysis_2017 a where a.year_str='"+year+"' and a.shicode='"+agcode+"' ");
		  System.out.println("ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE��ѯ " +dbSql.toString());
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
				System.out.println("ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE��ѯ " +dbSql.toString());
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
	   * ��վȥ��ͬ���վ�����
	   * @param stationNo
	   * @param date
	   * @return
	   */
	  public String getLastYearAvgElectric(String stationNo, Date date) throws DbException {
		  
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  c.add(Calendar.YEAR, -1);//��һ��
		  int year = c.get(Calendar.YEAR);
		  int month = c.get(Calendar.MONTH) + 1;
		  
		  String date_str = CTime.formatRealMonth(c.getTime());
		  
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select r.epdavg from ring_station_epd_avg r where r.station_no='"+stationNo+"' and r.statistics_date='"+date_str+"' ");
		  System.out.println("��վȥ��ͬ���վ����� " +dbSql.toString());
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
				System.out.println("��վȥ��ͬ���վ����� " +dbSql.toString());
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
	   * ����ϵ��
	   * @param housetype ��������
	   * @param value_str ��ص���
	   * @return
	   */
	  public String getHouseRatio(String housetype, String value_str){
		  CommonBean commonBean = new CommonBean();		
		  DecimalFormat df1 = new DecimalFormat("0.00");
		  String addtime = CTime.formatRealMonth(new Date());
		  String housetype2="";
		  //20�巿 30�ǰ巿
		  if("20".equals(housetype)){//housetype is null
        	   housetype2="20";
           }else{
        	   housetype2="30";
           }
           List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//��ȡ����ϵ�������ֵ����Сֵ
           
           String housexsid="",MAXZHI="",MINZHI="";
           if(strList3!=null && strList3.size()==3){
        	   housexsid = strList3.get(0);
        	   MAXZHI = strList3.get(1);
        	   MINZHI = strList3.get(2);
        	   if(MINZHI==null || MINZHI==""){
        		  MINZHI="1";
        	   }
              }
           
           List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str.replaceAll(" ", ""));//��ȡ����ϵ�����ڵ�����
           String housexsid_t="",MIN_T="",MAX_T="";
           if(strList4!=null && strList4.size()==3){
        	   housexsid_t = strList4.get(0);
        	   MIN_T = strList4.get(1);
        	   MAX_T = strList4.get(2);
            }
      		String fwxs="";//����ϵ��  ��������Ϊ���㷿��ϵ��
      		if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("0")){
      			fwxs=MINZHI;
      		}else if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("9999999999")){
      			fwxs=MAXZHI;
      		}else {
      		
      				//����ϵ�����㱣����λС��
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
	   * ���ݵ������ѯ���
	   * @param dbbm ������
	   * @return
	   */
	  public ElectricmeterVo getDb(String dbbm) throws DbException {
		  StringBuffer dbSql = new StringBuffer();
		  dbSql.append("select d.id, d.dbbm, d.electric_supply_way gdfs, d.danjia, d.zzsl, d.beilv, d.shzt, d.dbqyzt, " +
		  		"nvl(d.zhiliu, '0') zhiliu, nvl(d.jiaoliu, '0') jiaoliu, nvl(z.station_no, '0') stationNo, d.zdid, z.shi, z.housetypeid" +
		  		" from dianbiao d left join zhandian z on d.zdid=z.id where d.dbbm='"+dbbm+"'");
		  System.out.println("��ѵ����ݵ���-�����Ϣ��ѯ  " + dbSql.toString());
		  //DataBase db = new DataBase(); 
		  ResultSet rs = null;
		  ElectricmeterVo vo = null;
		  try {
			  //db.connectDb();
			  rs = db.queryAll(dbSql.toString());
			  while(rs.next()){
				  vo = new ElectricmeterVo();
				  vo.setId(rs.getInt("id"));
				  vo.setGdfs(rs.getString("gdfs"));//���緽ʽ
				  vo.setDj(rs.getString("danjia"));//�����
				  vo.setZzsl(rs.getString("zzsl")); //��ֵ˰��
				  vo.setBeilv(rs.getString("beilv"));//����
				  vo.setZhiliu(rs.getString("zhiliu"));//ֱ�����豸����
				  vo.setJiaoliu(rs.getString("jiaoliu"));//�������豸����
				  vo.setJzId(rs.getString("stationNo"));//������վID
				  vo.setZdid(rs.getString("zdid"));
				  vo.setShi(rs.getString("shi"));
				  vo.setHousetypeid(rs.getString("housetypeid"));
				  vo.setDbbm(rs.getString("dbbm"));
				  vo.setShzt(rs.getString("shzt")); //���״̬
				  vo.setDbqyzt(rs.getString("dbqyzt"));//����״̬
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
	   * ֱ�����豸����
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
		  System.out.println("��ѵ����ݵ���-��ص�����ѯ " +dbSql.toString());
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
				  vo.setSbdl(rs.getString("sbdl"));//������豸����
				  vo.setDaynum(rs.getString("daynum"));//����õ�����
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
	   * У������������
	   * @param rowData ��������
	   * @return
	   * @throws DbException 
	   */
	  public String checkElectricfees(ArrayList rowData) throws DbException {
		  
		  String dbbm=""; //������
		  String starttime="";//��ʼ����
		  String endtime="";//��������
		  String sqds = ""; //���ڶ���
		  String bqds = ""; //���ڶ���
		  String allmoney = "";//��ѽ��
		  String diansun = "";//���𣨶���/��
		  
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
		  //���ȼ������Ƿ�Ϸ�
		  //������
		  if(dbbm == null || dbbm.isEmpty()){
			  errMsg.append("������Ϊ��ֵ, ");
		  }else{
			  if(!isValidDb(dbbm)){
				  errMsg.append("������, ");
				  
			  }
		  }
		  if(errMsg.length() > 0){
			  errMsg.insert(0, "���["+dbbm+"]-");
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

		  System.out.println("��ѵ����ݵ����ѯ-" + sql.toString());
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
			    
				efOldVo.setDbid(rs.getString("dbid")); //���id
				efOldVo.setGdfs(rs.getString("gdfs"));
				//�ֻ�����
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
		  //У�����ݺϷ��� step1
		  //=================================================================================
		  String regex = "[0-9]*|[0-9]*+\\.+[0-9]*"; //���ڶ��������ڶ������������
		  String regex1 = "[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"; //���
		  boolean isNew = false;//
		  //��������
		  if(endtime == null || endtime.isEmpty()){
			  errMsg.append("��������Ϊ��ֵ, ");
		  }else{
			  if(!isValidDate(endtime)){
				  errMsg.append("�������ڸ�ʽ����ȷ, ");
			  }
		  }
		  //���ڶ���
		  if(bqds == null || bqds.isEmpty()){
			  errMsg.append("���ڶ���Ϊ��ֵ, ");
		  }else{
			  if(!Pattern.matches(regex, bqds)){
				  errMsg.append("���ڶ�����ʽ����ȷ, ");
			  }
			  
		  }
		  //��ѽ��
		  if(allmoney == null || allmoney.isEmpty()){
			  errMsg.append("��ѽ��Ϊ��ֵ, ");
		  }else{
			  if(!Pattern.matches(regex1, allmoney)){
				  errMsg.append("��ѽ���ʽ����ȷ, ");
			  }
			  
		  }
		  
		  //�״�¼�������� У�����ʼ���� ���ڶ��� 
		  if(efOldVo.getDbid() ==null || efOldVo.getDbid().isEmpty()){ 
			  //��ʼ����
			  if(starttime == null || starttime.isEmpty()){
				  errMsg.append("��ʼ����Ϊ��ֵ, ");
			  }else{
				  if(!isValidDate(starttime)){
					  errMsg.append("��ʼ���ڸ�ʽ����ȷ, ");
				  }
			  }
			 //���ڶ���
			  if(sqds == null || sqds.isEmpty()){
				  errMsg.append("���ڶ���Ϊ��ֵ, ");
			  }else{
				  if(!Pattern.matches(regex, sqds)){
					  errMsg.append("���ڶ�����ʽ����ȷ, ");
				  }
			  }
			  isNew = true;
		  }

		  //��ȡ�����Ϣ
		  ElectricmeterVo meterVo = getDb(dbbm);
		  if(meterVo == null){
			  errMsg.append("��ѯʧ��, ");
			  meterVo = new ElectricmeterVo();
		  }else{
			  //���ݵ���緽ʽУ�����������Ƿ����
			  if(isDirectedPowerSupply(meterVo)){//ֱ����
				  if(diansun == null || diansun.isEmpty()){
					  errMsg.append("�������Ϊ��ֵ, ");
				  }else{
					  if(!Pattern.matches(regex, diansun)){
						  errMsg.append("���������ʽ����ȷ, ");
					  }
				  }
			  }
			  //�������״̬  zt01����
			  if("zt02".equals(meterVo.getDbqyzt())){//ͣ��
				  errMsg.append("��ͣ��, ");
			  }else if("zt03".equals(meterVo.getDbqyzt())){//�ر�
				  errMsg.append("�ѹر�, ");
			  }else{
				  System.out.println("���["+dbbm+"] ����״̬-"+meterVo.getDbqyzt());
			  }
			  //������״̬ 2��ͨ��
			  if("0".equals(meterVo.getShzt())){//δ�ϱ�
				  errMsg.append("δ�ϱ�, ");
			  }else if("1".equals(meterVo.getShzt())){//�����
				  errMsg.append("�����, ");
			  }else{
				  System.out.println("���["+dbbm+"] ���״̬-" +meterVo.getShzt());
			  }
		  }
		  
		  ElectricfeesVo efVo = new ElectricfeesVo();
		  efVo.setEndtime(endtime); //��������
		  efVo.setBqds(bqds); //���ڶ���
		  efVo.setAllmoney(allmoney); //��ѽ��
		  efVo.setDiansun(diansun);//�������
		  efVo.setDbid(String.valueOf(meterVo.getId()));
		  //�ظ�У��
		  if(isRepeatef(efVo)){
			  errMsg.append("��������ظ�, ");
		  }else{
			//����״̬
			  if(efOldVo.getState() !=null && !"4".equals(efOldVo.getState())){
				  errMsg.append("����δ��ת��ı��˵�, ");
			  }
		  }
		  
		  if(errMsg.length() > 0){
			  errMsg.insert(0, "���["+dbbm+"]-");
			  return errMsg.toString();
		  }
		  //=================================================================================
		  //��������������ֵ step2
          //=================================================================================
		  if(efOldVo.getDbid() ==null || efOldVo.getDbid().isEmpty()){
			  efVo.setStarttime(starttime); //��ʼ����
			  efVo.setSqds(sqds); //���ڶ���
			  efVo.setSqdj(meterVo.getDj()); //���ڵ���
			  efVo.setSqrjydl("0.00"); //�����վ��õ���
			  efVo.setDbid(String.valueOf(meterVo.getId()));
		  }else{
			  //����������ʱ
			  if(efOldVo.getEndtime() !=null && !efOldVo.getEndtime().isEmpty()){//���ڽ������� �� 1 ��
				  Calendar c = Calendar.getInstance();
				  c.setTime(CTime.parseDate(efOldVo.getEndtime()));
				  c.add(Calendar.DAY_OF_MONTH, 1);
				  efVo.setStarttime(CTime.formatRealDate(c.getTime()));//yyyy-MM-dd
			  }
			  efVo.setSqds(efOldVo.getBqds()); //���ڶ���
			  efVo.setSqdj(efOldVo.getPrice()); //���ڵ���
			  efVo.setSqrjydl(efOldVo.getRjydl()); //�����վ��õ���
			  efVo.setDbid(efOldVo.getDbid());
		  }
		  //��ȡ��ص�������
		  ElectricCurrentVo ecVo = getElectricCurrent(meterVo, efVo);
		  if(ecVo == null){
			  ecVo = new ElectricCurrentVo();
			  ecVo.setValue("14");//û�м�ص���Ĭ��14A
		  }
		  efVo.setCurrentVo(ecVo);
		  efVo.setBeilv(meterVo.getBeilv()); //����
		  efVo.setPrice(getElectricfeesPrice(meterVo, efVo)); //����
		  efVo.setDiansun(getElectricLoss(meterVo, efVo)); //������
		  efVo.setDianliang(getElectric(meterVo, efVo)); //����
		  efVo.setTax(getTax(meterVo, efVo));//˰��
		  efVo.setDaynum(String.valueOf(getElectricdays(efVo)));//�õ�����
		  efVo.setRjydl(getAvgElectric(efVo));
		  
		  //�ֻ�����-
		  efVo.setStarttime_c(efVo.getStarttime());
		  efVo.setEndtime_c(efVo.getEndtime());
		  efVo.setSqds_c(efVo.getSqds());
		  efVo.setBqds_c(efVo.getBqds());
		  efVo.setDianliang_c(getElectric_C(efVo));
		  efVo.setDaynum_c(String.valueOf(getElectricdays_C(efVo)));
		  efVo.setRjydl_c(getAvgElectric_C(efVo));

		  //���ݷ���
		  efVo.setDlpls(getElectricdeviateNo(efVo));//����ƫ����
		  efVo.setRqpls(getDatedeviateNo(efVo));//����ƫ����
		  efVo.setPuezhi(getElectricPue(meterVo, efVo));//PUE
		  /**�����˵���*/
		  efVo.setBgdl(getBenchmarkingElectric(meterVo, efVo));
		  efVo.setBgpll(getBenchmarkingDeviateRate(efVo));
		  //=================================================================================
		  //�������� step3
		  //=================================================================================
		  SiteManage bean = new SiteManage();
		  if(efVo.getDiansun().isEmpty()) efVo.setDiansun("0");
		  efVo.setDfbzw("0");//����
		  System.out.println("��ʼ���� "+efVo.getStarttime());
		  System.out.println("�������� "+efVo.getEndtime());
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
	   * ȥ������������
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
	  
	  
	  //�����ж����ڸ�ʽ
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
