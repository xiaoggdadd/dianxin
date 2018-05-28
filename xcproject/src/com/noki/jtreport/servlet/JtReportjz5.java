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
		            		 return "����ʧ�ܣ�����Ϊ���������";  
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
	         			 return shi+lx+"δӦ�ý��ܼ����ı�˻����ĸ�������Ϊ������!!!"; 
	         	  }
        		  if(s2>zdgs){
        			  System.out.println(s2+"s2");
        			  return shi+lx+" δӦ�ý��ܼ����ı�˻����ĸ������ܴ���"+zdgs+"!!!";
        		  }
        	  }
        	  if(i==3){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�����ͨ���豸�ܹ��ʣ�KW������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  } if(i==4){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�����ʵ�ʺĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
	          }
        	  if(i==5){
        		  ss5=con;
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s5=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"δӦ�ý��ܼ����ı�˻����ı��PUEֵ����Ϊ������!!!"; 
	         	  }
        	  }
        	  if(i==6){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ı�˻�����ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==7){
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s7=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"Ӧ�ý��ܼ����ı�˻����ĸ�������Ϊ�����Ҳ���Ϊ����!!!"; 
	         	  }
        		  if(s7>zdgs){
        			 // System.out.println(con+"con");
        			  return  shi+lx+" Ӧ�ý��ܼ����ı�˻����ĸ������ܴ���"+zdgs+"!!!";
        			  
        		  }
        	  }
        	  if(i==8){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�����ͨ���豸�ܹ��ʣ�KW������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==9){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�����ʵ�ʺĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==10){
        		  ss10=con;
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s10=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"Ӧ�ý��ܼ����ı�˻����ı��PUEֵ����Ϊ�����Ҳ���Ϊ����!!!"; 
	         	  }
			   if("".equals(ss5)==false&&"".equals(ss10)==false){
        		  if(s10/s5<=0.95||s10/s5>=1.05){
        			  //System.out.println(puebg+"-"+puejn);
        			  return  shi+lx+" Ӧ�ý��ܼ����ı�˻�����PUEֵ  ��  δӦ�ý��ܼ����ı�˻�����PUEֵ �ı������¸���Ϊ"+puejn+"������ ";
        		  }
			   }
        	  }
        	  if(i==11){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ı�˻�����ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==12){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻����Ļ�����������������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==13){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�����ͨ���豸�ܹ��ʣ�KW������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==14){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�����ʵ�ʺĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==15){
        		  ss15="";
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s15=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�����PUEֵ����Ϊ�����Ҳ���Ϊ����!!!"; 
	         	  }
			   if("".equals(ss5)==false&&"".equals(ss15)==false){
        		  if(s5/s15<=0.7||s5/s15>=1.3){
        			  return  shi+lx+" δӦ�ý��ܼ����ı�˻�����PUEֵ  ��  δӦ�ý��ܼ����ķǱ�˻�����PUEֵ �ı������¸���Ϊ"+puebg+"������ ";
        		  }
			    }
        	  }
        	  if(i==16){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"δӦ�ý��ܼ����ķǱ�˻�����ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==17){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻����Ļ�����������������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==18){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�����ͨ���豸�ܹ��ʣ�KW������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==19){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�����ʵ�ʺĵ������ȣ�����Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  if(i==20){
        		  ss20=con;//-----------------------
        		  if(pattern1.matcher(con).matches()==true&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        		   s20=Double.parseDouble(con);
        		  }else if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
	         			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�����PUEֵ����Ϊ�����Ҳ���Ϊ����!!!"; 
	         	  }
        		  
				if("".equals(ss10)==false&&"".equals(ss20)==false){
					System.out.println("s10:"+s10+"  s20"+s20+" puebg"+puebg);
           		  if(s10/s20<=0.7||s10/s20>=1.3){
           			  return  shi+lx+" Ӧ�ý��ܼ����ı�˻�����PUEֵ  ��  Ӧ�ý��ܼ����ķǱ�˻�����PUEֵ �ı������¸���Ϊ"+puebg+"������ ";
           		  }
				}
				 if("".equals(ss20)==false&&"".equals(ss15)==false){
           		  if(s20/s15<=0.95||s20/s15>=1.05){
         			  return  shi+lx+" Ӧ�ý��ܼ����ķǱ�˻�����PUEֵ  ��  δӦ�ý��ܼ����ķǱ�˻�����PUEֵ �ı������¸���Ϊ"+puejn+"������ ";
         		  }
				 }
        	  }
        	  if(i==21){
        		  if(pattern1.matcher(con).matches()==false&&con!=null&&!"".equals(con)&&!" ".equals(con)){
        			 return shi+lx+"Ӧ�ý��ܼ����ķǱ�˻�����ʵ�ʵ�ѽ�Ԫ������Ϊ�����Ҳ���Ϊ����!!!";
        		  }
        	  }
        	  
        	  i++;
        	  m++;
          }
         
	   }
	    System.out.println("33333333333�ϴ����");
		return null;
	}



	public void init() throws ServletException {
		// Put your code here
	}

}
