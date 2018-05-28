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
		        			 return jzname+"�ַ��ܽ������(�O)����Ϊ�����Ҳ���Ϊ����!!!";
		        		  }
		        			  
		        	  }
	              if(i==8){
	        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 return jzname+"������װ��ʹ�����(�O)����Ϊ�����Ҳ���Ϊ����!!!";
		        		  }
		        			  
		        	  }
	        	  if(i==9){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		      s9=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"ʵ�ʳ����ܺĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		  }
	        	  }
	        	  if(i==10){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 s10=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�����úĵ��������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 } 
	        	  }if(i==11){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s11=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�豸�ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }   
	         	  }if(i==12){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s12=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�յ��ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }   
	         	  }if(i==13){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	          			 s13=Double.parseDouble(con);
	          		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�����������ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }   
	         	  }
	        	  if(i==14){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        		   s14=Double.parseDouble(con);
	        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�ܺĵ����У��칫�õ�ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
		        	  } 
	        		 double s0=s11+s12+s13;
	         		 double d = 0.0000001;
	         		 if(Math.abs(s10-s0)>d){
	        			  return jzname+"  �豸�ĵ���  + �յ��ĵ���  + �����������ĵ���  ������  �����úĵ�����������";
	        		  }
	        	  }
	        	  if(i==15){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 s15=Double.parseDouble(con);
	         		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�ܺĵ����У�Ӫҵ���������õ�ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 }   
	        	  }
	        	  if(i==16){
	        		 if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 s16=Double.parseDouble(con);
	        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			  return jzname+"�ܺĵ����У������ĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
	        		 } 
	        		 double s0=s10+s14+s15+s16;
	        		 double d = 0.0000001;
	        		 if(Math.abs(s9-s0)>d){
	       			  return jzname+"   �����úĵ�����  + �칫�õ�ĵ���  + Ӫҵ���������õ�ĵ���  + �����ĵ���  ������  ʵ�ʳ����ܺĵ���������";
	       		     }
	        	  }
	        	  if(i==17){
	        		 if(pattern1.matcher(con).matches()==false){
	        			  return jzname+"ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
	        		 }   
	        	  }
	        	  if(i==18){
		        		 if(pattern1.matcher(con).matches()==false){
		        			  return jzname+"ʵ�ʵ�ѽ���У������õ��ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
		        		 }   
		        	  }
	        	  if(i==19){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 s18=Double.parseDouble(con);
		        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			  return jzname+"�õ�Ч�ʣ�PUEֵ������Ϊ�����Ҳ���Ϊ����!!!";
		        		 } 
	        		  if(yue.equals("07")||yue.equals("08")||yue.equals("09")){
	        			  System.out.println("puemax"+puemax+"puekt"+puekt);
		         			if(s18>puemax||s18<puekt){
		             			  return jzname+"  ���õ�Ч�ʣ�PUEֵ����ΧӦ����  ����"+puekt+",С��"+puemax+"!!!";
		         			}
	        		   }else{
	        			   System.out.println("puemax"+puemax+"puemin"+puemin);
	        			   if(s18>puemax||s18<puemin){
	        				     return jzname+"  ���õ�Ч�ʣ�PUEֵ����ΧӦ����  ����"+puemin+",С��"+puemax+"!!!";
		         			} 
	         		   } 
	        	 }
	        	  if(i==20){
	        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			 s19=Double.parseDouble(con);
		        		 }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
		        			  return jzname+"�õ�Ч�ʣ�PUEֵ����׼ֵƫ���(%)����Ϊ�����Ҳ���Ϊ����!!!";
		        		 }
	        		  if(s19>puefwm||s19<puefw){
             			  return jzname+"  ���õ�Ч�ʣ�PUEֵ����׼ֵƫ���(%)��ΧӦ����  ����"+puefw+",С��"+puefwm+"!!!";
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
		        			 return jzname+"��ѷ����·ݵĸ�ʽ����ȷ����ȷ��ʽΪ��YYYY-MM!!!";
		        		 }
	        		  }else{
		        			 return jzname+"��ѷ����·ݲ���Ϊ��!!!";

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
		        			 return jzname+"¼�����ڵĸ�ʽ����ȷ����ȷ��ʽΪ��YYYY-MM-dd!!!";
		        		 }
	        		}else{
	        			 return jzname+"¼�����ڲ���Ϊ��!!!";

	        		}
	        	  }
	        	  if(i==24){
	        		  System.out.println("con1"+con);
	        		  if(con==null||con.equals("")||con.equals(" ")){
		        		  System.out.println("con2"+con);

	        			  return jzname+"¼����Ա����Ϊ��!!!";
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
