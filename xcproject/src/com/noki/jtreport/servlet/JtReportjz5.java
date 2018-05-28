package com.noki.jtreport.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.database.DataBase;
import com.noki.jizhan.daoru.CountForm;

public class JtReportjz5 extends HttpServlet {

	
	public JtReportjz5() {
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
	   int i=0,m=0;
	   JtReportjz jr=new JtReportjz();
	   double zdgs=jr.getZdgs("ZDGS","DSJZ");
	   double puebg=jr.getZdgs("PUEBG","TSJF");
	   double puejn=jr.getZdgs("PUEJN","TSJF");
	   //Pattern pattern = Pattern.compile("^[0-9]*$"); 
	   Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*"); 
	   for (Iterator it = content.iterator(); it.hasNext(); ) {
          String con="",shi="",lx="";
          i = 0;
          Vector row = (Vector) it.next();
          Iterator iter = row.iterator();
          double s2=0.0,s7=0.0,s5=0.0,s10=0.0,s15=0.0,s20=0.0;
          String ss5="",ss10="",ss15="",ss20="";
          while (iter.hasNext()) {
              Object value = iter.next();
              if (value == null) {
                  value = "";
              }
              con = value.toString().trim();
              if(i==0){
            	  shi=con;
            	  if(m==0){
	            		 if(shi.trim().equals("")||shi.trim()==null||shi.trim().equals(" ")){
		            		 return "导入失败，地市为必填项！！！";  
		            	 }	 
	            	 }
              }
              if(i==1){
            	 lx=con; 
            	 System.out.println("lx:"+lx);
              }
        	  if(i==2){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s2=Double.parseDouble(con);
        		   System.out.println("tttttt:"+s2+"zzz:"+zdgs);
        		   
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"未应用节能技术的标杆机房的个数必须为正整数!!!"; 
	         	  }
        		  if(s2>zdgs){
        			  System.out.println(s2+"s2");
        			  return shi+lx+" 未应用节能技术的标杆机房的个数不能大于"+zdgs+"!!!";
        		  }
        	  }
        	  if(i==3){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的标杆机房的通信设备总功率（KW）必须为数字且不能为负数!!!";
        		  }
        	  } if(i==4){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的标杆机房的实际耗电量（度）必须为数字且不能为负数!!!";
        		  }
	          }
        	  if(i==5){
        		  ss5=con;
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s5=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"未应用节能技术的标杆机房的标杆PUE值必须为正整数!!!"; 
	         	  }
        	  }
        	  if(i==6){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的标杆机房的实际电费金额（元）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==7){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s7=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"应用节能技术的标杆机房的个数必须为数字且不能为负数!!!"; 
	         	  }
        		  if(s7>zdgs){
        			 // System.out.println(con+"con");
        			  return  shi+lx+" 应用节能技术的标杆机房的个数不能大于"+zdgs+"!!!";
        			  
        		  }
        	  }
        	  if(i==8){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的标杆机房的通信设备总功率（KW）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==9){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的标杆机房的实际耗电量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==10){
        		  ss10=con;
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s10=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"应用节能技术的标杆机房的标杆PUE值必须为数字且不能为负数!!!"; 
	         	  }
			   if("".equals(ss5)==false&&"".equals(ss10)==false){
        		  if(s10/s5<=0.95||s10/s5>=1.05){
        			  //System.out.println(puebg+"-"+puejn);
        			  return  shi+lx+" 应用节能技术的标杆机房的PUE值  和  未应用节能技术的标杆机房的PUE值 的比例上下浮动为"+puejn+"！！！ ";
        		  }
			   }
        	  }
        	  if(i==11){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的标杆机房的实际电费金额（元）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==12){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的非标杆机房的机房数量（个）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==13){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的非标杆机房的通信设备总功率（KW）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==14){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的非标杆机房的实际耗电量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==15){
        		  ss15="";
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s15=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"未应用节能技术的非标杆机房的PUE值必须为数字且不能为负数!!!"; 
	         	  }
			   if("".equals(ss5)==false&&"".equals(ss15)==false){
        		  if(s5/s15<=0.7||s5/s15>=1.3){
        			  return  shi+lx+" 未应用节能技术的标杆机房的PUE值  和  未应用节能技术的非标杆机房的PUE值 的比例上下浮动为"+puebg+"！！！ ";
        		  }
			    }
        	  }
        	  if(i==16){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"未应用节能技术的非标杆机房的实际电费金额（元）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==17){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的非标杆机房的机房数量（个）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==18){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的非标杆机房的通信设备总功率（KW）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==19){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的非标杆机房的实际耗电量（度）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  if(i==20){
        		  ss20=con;//-----------------------
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s20=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"应用节能技术的非标杆机房的PUE值必须为数字且不能为负数!!!"; 
	         	  }
        		  
				if("".equals(ss10)==false&&"".equals(ss20)==false){
					System.out.println("s10:"+s10+"  s20"+s20+" puebg"+puebg);
           		  if(s10/s20<=0.7||s10/s20>=1.3){
           			  return  shi+lx+" 应用节能技术的标杆机房的PUE值  和  应用节能技术的非标杆机房的PUE值 的比例上下浮动为"+puebg+"！！！ ";
           		  }
				}
				 if("".equals(ss20)==false&&"".equals(ss15)==false){
           		  if(s20/s15<=0.95||s20/s15>=1.05){
         			  return  shi+lx+" 应用节能技术的非标杆机房的PUE值  和  未应用节能技术的非标杆机房的PUE值 的比例上下浮动为"+puejn+"！！！ ";
         		  }
				 }
        	  }
        	  if(i==21){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"应用节能技术的非标杆机房的实际电费金额（元）必须为数字且不能为负数!!!";
        		  }
        	  }
        	  
        	  i++;
        	  m++;
          }
         
	   }
	    System.out.println("33333333333上传完成");
		return null;
	}



	public void init() throws ServletException {
		// Put your code here
	}

}
