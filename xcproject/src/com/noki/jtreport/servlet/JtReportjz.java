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
		   double zdgs=this.getZdgs("ZDGS","DSJZ");//  �Ӿ�̬�����ó�����  �ж����������磺��վ5��  ���ܴ���5��
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
		            		 return "����ʧ�ܣ�����Ϊ���������";  
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
		         			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ�ĸ�������Ϊ������!!!"; 
		         		 } 
	        		  if(s>zdgs){
	        			  return shi+lx+"δӦ�ý��ܼ����ı�˻�վ�ĸ������ܴ���"+zdgs+"!!!";
	        			  
	        		  }
	        	  }
	        	  if(i==3){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ�����õ���ʵ�ʳ��������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
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
	         			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ��ƽ���õ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!"; 
	         		 } 
	        	  }
	        	  if(i==5){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ��ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==6){
	        		  double s=0.0;
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		   s=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		         			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ�ĸ�������Ϊ������!!!"; 
		         	  } 
	        		  if(s>zdgs){
	        			  return shi+lx+"Ӧ�ý��ܼ����ı�˻�վ�ĸ������ܴ���"+zdgs+"!!!";
	        			  
	        		  }
	        	  }
	        	  if(i==7){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�վ�����õ���ʵ�ʳ��������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        	  }
	        	  if(i==8){
	        		  sii=con;
	        		  if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&!"".equals(con)&&!" ".equals(con)){
	         			 si=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�վ��ƽ���õ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!"; 
 
	         		 } 
	        	  }
	        	  if(i==9){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�վ��ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==10){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�վ�Ļ�վ��������������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==11){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�վ�����õ���ʵ�ʳ��������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==12){
	        		 if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&"".equals(con)==false&&" ".equals(con)==false){
	        			 sm=Double.parseDouble(con);
	        			 System.out.println("".equals(se)+"11111111111"+"".equals(sm));
						 if("".equals(see)==false&&"".equals(smm)==false){
		    				 if((sm-se)/se>zdxs){
			        	    	 return shi+lx+"��δӦ�ý��ܼ����ķǱ�˻�վ�е�ƽ���õ������ȣ���ȥ  δӦ�ý��ܼ����ı�˻�վ �е� ƽ���õ������ȣ�������  δӦ�ý��ܼ����ı�˻�վ �е� ƽ���õ������ȣ���ֵ����С��"+zdxs+"!!!";
			        	     } 
	        			 }
	        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�վ��ƽ���õ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }
	        	  }
	        	  if(i==13){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�վ��ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  if(i==14){
	        		  if(pattern1.matcher(con).matches()==false&&!con.equals(null)&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�վ�Ļ�վ��������������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  } if(i==15){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�վ�����õ���ʵ�ʳ��������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
		        		  }
		        			  
		        	  }
	        	  if(i==16){
	        		  sqq=con;
	         		 if(pattern1.matcher(con).matches()==true&&!con.equals(null)&&"".equals(con)==false&&" ".equals(con)==false){
	         			 sq=Double.parseDouble(con);
	         			 if("".equals(sqq)==false&&"".equals(sii)==false){
		     				 if((sq-si)/si>zdxs){
		 	        	    	 return shi+lx+"��Ӧ�ý��ܼ����ķǱ�˻�վ �е� ƽ���õ������ȣ���ȥ  Ӧ�ý��ܼ����ı�˻�վ �е� ƽ���õ������ȣ�������  Ӧ�ý��ܼ����ı�˻�վ �е� ƽ���õ������ȣ���ֵ����С��"+zdxs+"!!!";
		 	        	     } 
	         			 }
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�վ��ƽ���õ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }
	         	  }
	        	  if(i==17){
	        		  if(pattern1.matcher(con).matches()==false){
	        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�վ��ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        			  
	        	  }
	        	  i++;
	        	  m++;
	          }
	         
		   }
		   System.out.println("�������");
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
