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

public class JtReportjz4 extends HttpServlet {

	
	public JtReportjz4() {
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

	
   public synchronized String getWrong(Vector content,CountForm cform) {
	   //i12=i13+i14+i15
	   int i=0;
	   JtReportjz jr=new JtReportjz();
	   double zdgs=jr.getZdgs("IDCXS","IDC");
	   Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*"); 
	   Pattern pattern3 = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{2}|[0-9]{2}-[0-9]{2}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{2}");
	   Pattern pattern2 = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02]))|((0[2469]|11))))|(02)");
	   String datePattern4 = "^((\\d{2}(([02468][048])|([13579][26]))"
           + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
           + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
           + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
           + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
           + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern pattern5 = Pattern.compile(datePattern4); 
	   for (Iterator it = content.iterator(); it.hasNext(); ) {
          String con="",shi="";
          String jzname="";
          i = 0;
          Vector row = (Vector) it.next();
          Iterator iter = row.iterator();
          double s12=0.0,s13=0.0,s14=0.0,s15=0.0;
          double s22=0.0,s23=0.0,s24=0.0,s25=0.0;
          while (iter.hasNext()) {
              Object value = iter.next();
              if (value == null) {
                  value = "";
              }
              con = value.toString().trim();
              if(i==1){
            	  shi=con;
              }if(i==2){
            	  jzname=con;
              }
              if(i==5){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"机房面积(㎡)必须为数字且不能为负数!!!";
	        		  }
	          }
              if(i==6){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用面积(㎡)必须为数字且不能为负数!!!";
	        		  }
	          }
              if(i==7){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"机柜总数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
              if(i==8){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用机柜数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
              if(i==9){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"U总数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
              if(i==10){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用U数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==11){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			  s12=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			  return jzname+"实际抄表耗电总量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==12){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
         			 s13=Double.parseDouble(con);
         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
         			 return jzname+"其中：设备耗电量（度）必须为数字且不能为负数!!!";
       		     }  
        		  if(s12/s13>zdgs){
        			 return jzname+" 采用节能技术的机房   实际抄表用电总量/设备耗电量必须小于"+zdgs+"!"; 
        		  }
        	  }
        	  if(i==13){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s14=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			  return jzname+"其中：空调耗电量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==14){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
         			 s15=Double.parseDouble(con);
         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
       			  return jzname+"其中：照明及其他耗电量（度）必须为数字且不能为负数!!!";
       		  } 
        		   double s0=s13+s14+s15;
        		   double d = 0.0000001;

        		  if(Math.abs(s12-s0)>d){
        			  return jzname+" 采用节能技术的机房    设备耗电量+空调耗电量+照明及其他耗电量不等于总抄表耗电量！！！";
        		  }
        	  }
        	  if(i==15){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==16){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"机房面积(㎡)必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==17){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用面积(㎡)必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==18){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"机柜总数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==19){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用机柜数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==20){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"U总数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==21){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"已使用U数（个）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==22){
        		  
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s22=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			  return jzname+"实际抄表耗电总量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==23){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
         			 s23=Double.parseDouble(con);
         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
       			  return jzname+"其中：设备耗电量（度）必须为数字且不能为负数!!!";
       		  } 
        		  if(s22/s23>zdgs){
         			 return jzname+" 未采用节能技术的机房   实际抄表用电总量/设备耗电量必须小于"+zdgs+"!"; 
         		  }
        	  }
        	  if(i==24){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s24=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			  return jzname+"其中：空调耗电量（度）必须为数字且不能为负数!!!";
        		  }
        		 
        	  }
        	  if(i==25){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
         			 s25=Double.parseDouble(con);
         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
       			  return jzname+"其中：照明及其他耗电量（度）必须为数字且不能为负数!!!";
       		  } 
        		   double s0=s23+s24+s25;
        		   double d = 0.0000001;

        		  if(Math.abs(s22-s0)>d){
        			  return jzname+" 未采用节能技术的机房   设备耗电量+空调耗电量+照明及其他耗电量不等于总抄表耗电量！！！";
        		  }
        	  }
        	  if(i==26){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return jzname+"未采用节能技术的机房 实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	          }
        	  if(i==27){
        		  if(!"".equals(con)&&null!=con){
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
        		  System.out.println(pattern2.matcher(con).matches()+"11111111-"+con+"--"+jzname+"-");
        		 if(pattern2.matcher(con).matches()==false){
        			 return jzname+"电费发生月份的格式不正确，正确格式为：YYYY-MM!!!";
        		 }}
        	  } if(i==28){
        		  if(!"".equals(con)&&null!=con){
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
        		  if(pattern3.matcher(con).matches()==true){
     				 String lastdate="20"+con;
     				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
     		        	Date date;
     					try {
     						date = sdf1.parse(lastdate);
     						con=sdf1.format(date);
     						System.out.println("lastdatetime+=+++"+con);
     					} catch (ParseException e) {
     						// TODO Auto-generated catch block
     						e.printStackTrace();
     					}	        	 
     			 }
        		  
        		  System.out.println(pattern5.matcher(con).matches()+"11111111-"+con+"--"+jzname+"-");
        		 if(pattern5.matcher(con).matches()==false){
        			 return jzname+"录入日期的格式不正确，正确格式为：YYYY-MM-DD!!!";
        		 }
        	  }}
        	  if(i==29){
        		  if("".equals(con)||null==con||con.equals(" ")){
        			  return "录入单位（自动带入）是必填项！！！";
        		  }
        	  }
        	  if(i==30){
        		  if("".equals(con)||null==con||con.equals(" ")){
        			  return "录入人员（自动带入）是必填项！！！";
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
