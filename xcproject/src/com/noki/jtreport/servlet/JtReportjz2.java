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

public class JtReportjz2 extends HttpServlet {

	
	public JtReportjz2() {
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
	   int i=0;
	   JtReportjz jr=new JtReportjz();
	   double zdgs=jr.getZdgs("MPTB","JZDL");
	   double bymin=jr.getZdgs("BYMIN","JZDL");
	   double bymax=jr.getZdgs("BYMAX","JZDL");
	   Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*"); 
	   for (Iterator it = content.iterator(); it.hasNext(); ) {
          String con="";
          i = 0;
          Vector row = (Vector) it.next();
          Iterator iter = row.iterator();
          String address="";
          while (iter.hasNext()) {
        	  double s11=0.0,s12=0.0,si=0.0,sq=0.0;
        	  //System.out.println("con311111111111111");
              Object value = iter.next();
              if (value == null) {
                  value = "";
              }
              con = value.toString().trim();
              if(i==0){
            	  address=con; 
              }
              if(i==2){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"�߼�վ��  ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==3){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��Ƶ����  ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==4){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�õ�������ȣ� �����ۼ�  ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==5){
        		  if(!con.contains("%")&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�õ�������ȣ�ͬ��  ����Ϊ�ٷ���!!!";
        		  }
        	  }
              if(i==6){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�õ�������ȣ� ���� ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==7){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�����֧������Ԫ�������ۼ� ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==8){
        		  if(!con.contains("%")&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�����֧������Ԫ��ͬ��  ����Ϊ�ٷ����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==9){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"��վ�����֧������Ԫ�������ۼ� ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
              if(i==10){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"ÿ��Ƶ�õ�������/�£������ۼ� ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==11){
        		  if(con.contains("%")){
        		  int ss=con.indexOf("%");
        		     String shu=con.substring(0, ss);
        		     s11=Double.parseDouble(shu);
		      		 if(zdgs-s11<0){
		    			  return address+" ÿ��Ƶ�õ�����  ͬ��  ����С�� "+zdgs+"!!!";
		    		  }
        		  }else{
        			  return address+"ÿ��Ƶ�õ�����  ͬ��  ����Ϊ�ٷ���";
        		  }
        		  
        	  }
        	  if(i==12){
    			  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
    				  s12=(Double.parseDouble(con));
    				 // System.out.println("s12"+s12+"bymin"+bymin+"bymax"+bymax);
    				  if(s12<bymin||s12>bymax){
        				  return address+" ÿ��Ƶ�õ�����  ���µķ�Χ�Ǵ���"+bymin+"С��"+bymax+"!!!";
        			  }
    			  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
    				   return address+" ÿ��Ƶ�õ�����  ���� ����Ϊ�����Ҳ���Ϊ����!!!";
    			  }
    			  
    			 
    		  }
        	  if(i==13){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"ÿ��Ƶ���֧����Ԫ/�£������ۼ� ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==14){
        		  if(!con.contains("%")&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"ÿ��Ƶ���֧����Ԫ/�£�ͬ��  ����Ϊ�ٷ����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  
        	  if(i==15){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return address+"ÿ��Ƶ���֧����Ԫ/�£�����  ����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	 
        	  i++;
          }
         
         
	   }
	   System.out.println("�������");
	   
		return null;
	}



	public void init() throws ServletException {
		// Put your code here
	}

}
