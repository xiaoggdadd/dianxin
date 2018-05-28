package com.noki.jtreport.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.daoru.CountForm;

public class JtReportjz extends HttpServlet {

	
	public JtReportjz() {
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
		   String shi="";
		   double zdgs=this.getZdgs("ZDGS","DSJZ");//  从静态数据拿出来的  判断条件（例如：基站5个  不能大于5）
		   double zdxs=this.getZdgs("ZDXS","DSJZ");
		   //Pattern pattern = Pattern.compile("^[0-9]*$"); 
 
		   Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*"); 
		   for (Iterator it = content.iterator(); it.hasNext(); ) {
	          String con="",lx="";
	          i = 0;
	          Vector row = (Vector) it.next();
	          Iterator iter = row.iterator();
	          String see="",smm="",sii="",sqq="";
	          double se=0.0,sm=0.0,si=0.0,sq=0.0;
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
		          }
	        	  if(i==2){
	        		  double s=0.0;
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        		  System.out.println("pattern.matcher(con).matches():"+pattern1.matcher(con).matches());

	        		   s=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		         			 return shi+lx+"未应用节能技术的标杆基站的个数必须为正整数!!!"; 
		         		 } 
	        		  if(s>zdgs){
	        			  return shi+lx+"未应用节能技术的标杆基站的个数不能大于"+zdgs+"!!!";
	        			  
	        		  }
	        	  }
	        	  if(i==3){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的标杆基站的总用电量实际抄表数（度）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==4){
	        		  see=con;
	        		  System.out.println("2222"+con+"111111"+"".equals(con)+"4444444");
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&"".equals(con)==false&&" ".equals(con)==false){
	        			  System.out.println(se+"sesesese");
	         			 se=Double.parseDouble(con);
	         			 System.out.println(se+"sesesese2");
	         		 } else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"未应用节能技术的标杆基站的平均用电量（度）必须为数字且不能为负数!!!"; 
	         		 } 
	        	  }
	        	  if(i==5){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的标杆基站的实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==6){
	        		  double s=0.0;
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		   s=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		         			 return shi+lx+"未应用节能技术的标杆基站的个数必须为正整数!!!"; 
		         	  } 
	        		  if(s>zdgs){
	        			  return shi+lx+"应用节能技术的标杆基站的个数不能大于"+zdgs+"!!!";
	        			  
	        		  }
	        	  }
	        	  if(i==7){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"应用节能技术的标杆基站的总用电量实际抄表数（度）必须为数字且不能为负数!!!";
	        		  }
	        	  }
	        	  if(i==8){
	        		  sii=con;
	        		  if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&!"".equals(con)&&!" ".equals(con)){
	         			 si=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"应用节能技术的标杆基站的平均用电量（度）必须为数字且不能为负数!!!"; 
 
	         		 } 
	        	  }
	        	  if(i==9){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"应用节能技术的标杆基站的实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==10){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的非标杆基站的基站数量（个）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==11){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的非标杆基站的总用电量实际抄表数（度）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==12){
	        		 if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&"".equals(con)==false&&" ".equals(con)==false){
	        			 sm=Double.parseDouble(con);
	        			 System.out.println("".equals(se)+"11111111111"+"".equals(sm));
						 if("".equals(see)==false&&"".equals(smm)==false){
		    				 if((sm-se)/se>zdxs){
			        	    	 return shi+lx+"（未应用节能技术的非标杆基站中的平均用电量（度）减去  未应用节能技术的标杆基站 中的 平均用电量（度））除以  未应用节能技术的标杆基站 中的 平均用电量（度）的值必须小于"+zdxs+"!!!";
			        	     } 
	        			 }
	        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的非标杆基站的平均用电量（度）必须为数字且不能为负数!!!";
	        		 }
	        	  }
	        	  if(i==13){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"未应用节能技术的标杆基站的实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==14){
	        		  if(pattern1.matcher(con).matches()==false&&!con.equals(null)&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"应用节能技术的非标杆基站的基站数量（个）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  } if(i==15){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 return shi+lx+"应用节能技术的非标杆基站的总用电量实际抄表数（度）必须为数字且不能为负数!!!";
		        		  }
		        			  
		        	  }
	        	  if(i==16){
	        		  sqq=con;
	         		 if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&"".equals(con)==false&&" ".equals(con)==false){
	         			 sq=Double.parseDouble(con);
	         			 if("".equals(sqq)==false&&"".equals(sii)==false){
		     				 if((sq-si)/si>zdxs){
		 	        	    	 return shi+lx+"（应用节能技术的非标杆基站 中的 平均用电量（度）减去  应用节能技术的标杆基站 中的 平均用电量（度））除以  应用节能技术的标杆基站 中的 平均用电量（度）的值必须小于"+zdxs+"!!!";
		 	        	     } 
	         			 }
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"应用节能技术的非标杆基站的平均用电量（度）必须为数字且不能为负数!!!";
	        		 }
	         	  }
	        	  if(i==17){
	        		  if(pattern1.matcher(con).matches()==false){
	        			 return shi+lx+"应用节能技术的非标杆基站的实际电费金额（元）必须为数字且不能为负数!!!";
	        		  }
	        			  
	        	  }
	        	  i++;
	        	  m++;
	          }
	         
		   }
		   System.out.println("读表完成");
			return null;
		}



	public void init() throws ServletException {
		// Put your code here
	}
	public double getZdgs(String code,String type){
		double mm=0;
		String sql="SELECT  T.INDEXS2 from indexs t where CODE='"+code+"' AND TYPE='"+type+"'";
		DataBase dba = new DataBase();  
		ArrayList<String> listdj=new ArrayList<String>() ;
		ResultSet rs = null; 
		try {
			rs = dba.queryAll(sql);
			if(rs.next()){	
			     listdj.add(rs.getString(1));
			     String m=listdj.get(0);
			     mm=Double.parseDouble(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	     
		return mm;
	}

}
