package com.noki.jtreport.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.database.DataBase;
import com.noki.jizhan.daoru.CountForm;

public class JtReportjz3 extends HttpServlet {

	
	public JtReportjz3() {
		super();
	}
	
	
	 public DataBase db;
	public void closeDb() {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
   public synchronized String getWrong(Vector content,CountForm cform,String yue) {
	   int i=0;
	   JtReportjz jr=new JtReportjz();
	   double puemin=jr.getZdgs("PUEMIN","TXJF");
	   double puemax=jr.getZdgs("PUEMAX","TXJF");
	   double puekt=jr.getZdgs("PUEKT","TXJF");
	   double puefw=jr.getZdgs("PUEFW","TXJF");
	   double puefwm=jr.getZdgs("PUEFWM","TXJF");
	  
	   Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*"); 
	   Pattern pattern2 = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02]))|((0[2469]|11))))|(02)");
	   
	   for (Iterator it = content.iterator(); it.hasNext(); ) {
          String con="";
          i = 0;
          Vector row = (Vector) it.next();
          Iterator iter = row.iterator();
          double s9=0.0,s10=0.0,s14=0.0,s15=0.0,s16=0.0;
          double s11=0.0,s12=0.0,s13=0.0,s18=0.0,s19=0.0;
          String jzname="";
	          while (iter.hasNext()) {
	              Object value = iter.next();
	              if (value == null) {
	                  value = "";
	              }
	              con = value.toString().trim();
	              if(i==2){
	        		  jzname=con;
	        	  }
	              if(i==7){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 return jzname+"局房总建筑面积(㎡)必须为数字且不能为负数!!!";
		        		  }
		        			  
		        	  }
	              if(i==8){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 return jzname+"机房已装机使用面积(㎡)必须为数字且不能为负数!!!";
		        		  }
		        			  
		        	  }
	        	  if(i==9){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		      s9=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"实际抄表总耗电量（度）必须为数字且不能为负数!!!";
	        		  }
	        	  }
	        	  if(i==10){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 s10=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"生产用耗电总量（度）必须为数字且不能为负数!!!";
	        		 } 
	        	  }if(i==11){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s11=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"设备耗电量（度）必须为数字且不能为负数!!!";
	        		 }   
	         	  }if(i==12){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s12=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"空调耗电量（度）必须为数字且不能为负数!!!";
	        		 }   
	         	  }if(i==13){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s13=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"照明及其他耗电量（度）必须为数字且不能为负数!!!";
	        		 }   
	         	  }
	        	  if(i==14){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		   s14=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"总耗电量中：办公用电耗电量（度）必须为数字且不能为负数!!!";
		        	  } 
	        		 double s0=s11+s12+s13;
	         		 double d = 0.0000001;
	         		 if(Math.abs(s10-s0)>d){
	        			  return jzname+"  设备耗电量  + 空调耗电量  + 照明及其他耗电量  不等于  生产用耗电总量！！！";
	        		  }
	        	  }
	        	  if(i==15){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 s15=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"总耗电量中：营业厅、生活用电耗电量（度）必须为数字且不能为负数!!!";
	        		 }   
	        	  }
	        	  if(i==16){
	        		 if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 s16=Double.parseDouble(con);
	        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"总耗电量中：其他耗电量（度）必须为数字且不能为负数!!!";
	        		 } 
	        		 double s0=s10+s14+s15+s16;
	        		 double d = 0.0000001;
	        		 if(Math.abs(s9-s0)>d){
	       			  return jzname+"   生产用耗电总量  + 办公用电耗电量  + 营业厅、生活用电耗电量  + 其他耗电量  不等于  实际抄表总耗电量！！！";
	       		     }
	        	  }
	        	  if(i==17){
	        		 if(pattern1.matcher(con).matches()==false){
	        			  return jzname+"实际电费金额（元）必须为数字且不能为负数!!!";
	        		 }   
	        	  }
	        	  if(i==18){
		        		 if(pattern1.matcher(con).matches()==false){
		        			  return jzname+"实际电费金额中：生产用电电费金额（元）必须为数字且不能为负数!!!";
		        		 }   
		        	  }
	        	  if(i==19){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 s18=Double.parseDouble(con);
		        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			  return jzname+"用电效率（PUE值）必须为数字且不能为负数!!!";
		        		 } 
	        		  if(yue.equals("07")||yue.equals("08")||yue.equals("09")){
	        			  System.out.println("puemax"+puemax+"puekt"+puekt);
		         			if(s18>puemax||s18<puekt){
		             			  return jzname+"  的用电效率（PUE值）范围应该在  大于"+puekt+",小于"+puemax+"!!!";
		         			}
	        		   }else{
	        			   System.out.println("puemax"+puemax+"puemin"+puemin);
	        			   if(s18>puemax||s18<puemin){
	        				     return jzname+"  的用电效率（PUE值）范围应该在  大于"+puemin+",小于"+puemax+"!!!";
		         			} 
	         		   } 
	        	 }
	        	  if(i==20){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 s19=Double.parseDouble(con);
		        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			  return jzname+"用电效率（PUE值）基准值偏离度(%)必须为数字且不能为负数!!!";
		        		 }
	        		  if(s19>puefwm||s19<puefw){
             			  return jzname+"  的用电效率（PUE值）基准值偏离度(%)范围应该在  大于"+puefw+",小于"+puefwm+"!!!";
         			}
	        		  
	        		   
	        	  }
	        	  if(i==21){
	        		  if(con!=null&&!con.equals("")&&!con.equals(" ")){
		        		  if(con.contains("/")){
		        			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		      	        	  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		      	        	  Date date;
							try {
								date = sdf.parse(con);
								con=sdf1.format(date);
							} catch (ParseException e) {
								e.printStackTrace();
							}
		      	         }
		        		  System.out.println(pattern2.matcher(con).matches()+"11111111");
		        		 if(pattern2.matcher(con).matches()==false){
		        			 return jzname+"电费发生月份的格式不正确，正确格式为：YYYY-MM!!!";
		        		 }
	        		  }else{
		        			 return jzname+"电费发生月份不能为空!!!";

		        	 }
	        	  }
	        	  String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
	                  + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
	                  + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
	                  + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
	                  + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	                  + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	     		Pattern pattern5 = Pattern.compile(datePattern2); 
	     		 
	        	  if(i==22){
	        		  if(con!=null&&!con.equals("")&&!con.equals(" ")){
		        		  if(con.contains("/")){
		        			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		      	        	  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		      	        	  Date date;
							try {
								date = sdf.parse(con);
								con=sdf1.format(date);
							} catch (ParseException e) {
								e.printStackTrace();
							}
		      	         }
		        		  System.out.println(pattern5.matcher(con).matches()+"11111111");
		        		 if(pattern5.matcher(con).matches()==false){
		        			 return jzname+"录入日期的格式不正确，正确格式为：YYYY-MM-dd!!!";
		        		 }
	        		}else{
	        			 return jzname+"录入日期不能为空!!!";

	        		}
	        	  }
	        	  if(i==24){
	        		  System.out.println("con1"+con);
	        		  if(con==null||con.equals("")||con.equals(" ")){
		        		  System.out.println("con2"+con);

	        			  return jzname+"录入人员不能为空!!!";
	        		  }
	        		  
	        	  }
	        	  
	        	  i++;
	          }
	   }
	   System.out.println("读表完成");
		return null;
	}

  


	public void init() throws ServletException {
		// Put your code here
	}

}
