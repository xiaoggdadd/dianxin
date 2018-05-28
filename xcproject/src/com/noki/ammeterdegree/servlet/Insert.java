package com.noki.ammeterdegree.servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.daoru.CountForm;
import com.noki.sysconfig.javabean.AutoAuditBean;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
//��������
public class Insert {

  StringBuffer sql = new StringBuffer();

  Vector wrongContent = new Vector();
  public DataBase db;
  public Insert() {
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
  public synchronized Vector getWrong(Vector content,CountForm cform,String accountname) {
	  String lastdatetime="";
	  String thisdatetime="",startmonth="",endmonth="",inputdatetime="";
	 Object[] a=content.toArray();
	
	  for(int i=1;i<a.length;i++){
		  Vector<String> row=new Vector<String>();
		  Object[] b=((Vector)a[i]).toArray();
		  
		  if(!this.getDBID(b[3].toString().trim())){
			  System.out.println("δ�鵽���"+b[9].toString().trim());
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("δ�鵽"+b[0].toString()+b[2].toString()+"���"+b[3].toString().trim());
			  wrongContent.add(row);
              
			  continue;	
			  
		  }
		  String dbyt=this.getDbyt(b[3].toString().trim()).get(0).toString();
		  System.out.println("dbyt"+dbyt);
		  if(!dbyt.equals("dbyt03")){
        	  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("ֻ�ܵ����������ĵ���");
			  wrongContent.add(row);
			  continue;
		  }
				  
		  if(b[4].toString().trim()==""||b[4].toString().trim().contains(",")){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"�ϴε�����Ϊ�� ���߸�ʽ����ȷ"); 
			  wrongContent.add(row);
			  continue;
		  }
		  if(b[5].toString().trim()==""||b[5].toString().trim().contains(",")){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"���ε�����Ϊ�� ���߸�ʽ����ȷ"); 
			  wrongContent.add(row);
			  continue;
		  }
		  if(b[8].toString().trim()==""||b[8].toString().trim().contains(",")){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"�õ�������Ϊ�� ���߸�ʽ����ȷ"); 
			  wrongContent.add(row);
			  continue;
		  }
		  if(b[9].toString().trim()==""||b[9].toString().trim().contains(",")){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"������õ���Ϊ�� ���߸�ʽ����ȷ"); 
			  wrongContent.add(row);
			  continue;
		  }	
		  
		  
			 Pattern pattern3 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}-[0-9]{1}-[0-9]{2}");//�����ж�-
			 Pattern pattern4 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");//�����ж�/
			
		  
		  String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"    //�����ж�
	             + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
	             + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
	             + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
			Pattern pattern5 = Pattern.compile(datePattern2); 
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  System.out.println("1b[6].toString().trim()"+b[6].toString().trim());
		  if((b[6].toString().trim().contains("/")&&pattern4.matcher(b[6].toString().trim()).matches()==true)||b[6].toString().trim().contains("-")&&pattern5.matcher(b[6].toString().trim()).matches()==true&&isValidDate(b[6].toString().trim())==true){
			  if(b[6].toString().trim().contains("/")){
	          try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        	Date date =sdf.parse(b[6].toString().trim());
	        	 lastdatetime=sdf1.format(date);
	        	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  }else{
				  System.out.println("2b[6].toString().trim()"+b[6].toString().trim());
				  lastdatetime=b[6].toString().trim();
			  }
	    }else{
	    	 for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"�ϴγ���ʱ��Ϊ��   ���� ��ʽ����ȷ");
			  wrongContent.add(row);
			  continue;
	    }
		  
		  
		  
		
		  
		  
		  
		  if((b[7].toString().trim().contains("/")&&pattern4.matcher(b[7].toString().trim()).matches()==true)||b[7].toString().trim().contains("-")&&pattern5.matcher(b[7].toString().trim()).matches()==true&&isValidDate(b[7].toString().trim())==true){
		  if(b[7].toString().trim().contains("/")){
	          try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        	Date date =sdf.parse(b[7].toString().trim());
	        	 thisdatetime=sdf1.format(date);
	        	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  }else{
				 
				  thisdatetime=b[7].toString().trim();
			  }
		  }else{
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"���γ���ʱ��Ϊ��   ����  ��ʽ����ȷ");
			  wrongContent.add(row);
			  continue;
		  }
		  Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
		  startmonth=b[10].toString().trim();
		  if((pattern2.matcher(startmonth).matches()==true&&(startmonth.contains("/")&&startmonth.length()<=7))||(pattern2.matcher(startmonth).matches()==true&&(startmonth.contains("-")&&startmonth.length()<=7))){
	         if(b[10].toString().trim().contains("/")){
			  try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        	Date date =sdf.parse(b[10].toString().trim());
	        	 startmonth=sdf1.format(date);
	        	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         }else{
				 
	        	 startmonth=b[10].toString().trim();
			  } 
			
			
	        
		  }else{
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"��ʼ����Ϊ��  ����  ��ʽ����ȷ");
			  wrongContent.add(row);
			  continue;
		  }
	 
	
		  endmonth=b[11].toString().trim();
		  if((pattern2.matcher(endmonth).matches()==true&&(endmonth.contains("/")&&endmonth.length()<=7))||(pattern2.matcher(endmonth).matches()==true&&(endmonth.contains("-")&&endmonth.length()<=7))){
			  if(b[11].toString().trim().contains("/")){
			  try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        	Date date =sdf.parse(b[11].toString().trim());
	        	 endmonth=sdf1.format(date);
	        	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  }else{
				 
				  endmonth=b[11].toString().trim();
			  } 
			  
		  }else{
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"��������Ϊ��  ����  ��ʽ����ȷ");
			  wrongContent.add(row);
			  continue;
		  }
		  if(!b[13].toString().trim().equals("")){
			  if((b[13].toString().trim().contains("/")&&pattern5.matcher(b[13].toString().trim()).matches()==true)||(b[13].toString().trim().contains("-")&&pattern5.matcher(b[13].toString().trim()).matches()==true)){
			  if(b[13].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
		        	Date date =sdf.parse(b[13].toString().trim());
		        	 inputdatetime=sdf1.format(date);
		        	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				  }else{
					 
					  inputdatetime=b[13].toString().trim();
				  } 
			  }else{
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add(b[0].toString()+b[2].toString()+"���"+b[3].toString()+"����ʱ���ʽ����ȷ");
				  wrongContent.add(row);
				  continue;
			  }
			  }else{
				  inputdatetime=b[13].toString().trim();
			  }
		  //�жϵ���������Ƿ��ظ�
		  String sql ="select * from dianduview t,dianbiao db where db.dbid=t.ammeterid_fk and db.dbid='"+b[3].toString().trim()+"' and t.lastdegree='"+b[4].toString().trim()+"' and t.thisdegree='"+b[5].toString().trim()+"' and t.lastdatetime='"+lastdatetime+"' and t.thisdatetime='"+thisdatetime+"'";
	      System.out.println("--id--"+sql);
	      DataBase db = new DataBase();     
	      ArrayList<String> listzd=new ArrayList<String>() ;
	      rs=null;
	      try {  	 
			rs = db.queryAll(sql);	
			//System.out.println("121212121"+rs);
			 if(rs.next()){		
	    	  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	    	  }
	       		System.out.println("1212121�����ظ�����21"+rs);
		    	  row.add("�����ظ�����!");	    	  
		    	  wrongContent.add(row);
		    	  continue;
				    	   		      
		      }	
			 rs.close();
	      } catch (Exception e) {
	  		e.printStackTrace();
	  		try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  	 }
	    
	      
		  
		  
		  
		  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  AmmeterDegreeFormBean formBean = new AmmeterDegreeFormBean();
		  formBean.setAmmeteridFk(b[3].toString().trim());
		  formBean.setLastdegree(b[4].toString().trim());
	      formBean.setThisdegree(b[5].toString().trim());
	      formBean.setLastdatetime(lastdatetime);
	      formBean.setThisdatetime(thisdatetime);	      	    
	      formBean.setFloatdegree(b[8].toString().trim().equals("")?"0":b[8].toString().trim());
	      formBean.setActualdegree(b[9].toString().trim());
	      formBean.setBlhdl(b[9].toString().trim());	 
	      formBean.setStartmonth(startmonth);
	      formBean.setEndmonth(endmonth);
	      formBean.setInputoperator(b[12].toString().trim());
	      formBean.setInputdatetime(inputdatetime);
	      formBean.setMemo(b[14].toString().trim());
	      formBean.setEntrypensonnel(accountname);
	      formBean.setEntrytime(mat.format(new Date()));
	      
    //�Զ�����ж�
      //����������ϴ��պĵ�����ֵ
      long lastdaydegree =  formBean.getLastDayAmmeterDegree(formBean.getAmmeteridFk(),formBean.getLastdatetime());
      //System.out.println(lastdaydegree);
      //���㱾���պĵ���
      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
      long nd = 1000*24*60*60;//һ��ĺ�����
      //�������ʱ��ĺ���ʱ�����
      double diff,day,thisdaydegree,daydegereerate = 0;
  		try {
  			//System.out.println("111111:"+formBean.getThisdatetime());
  			System.out.println("222222:"+sd.parse(formBean.getThisdatetime()).getTime());
  			System.out.println("333333:"+sd.parse(formBean.getLastdatetime()).getTime());
  			diff = sd.parse(formBean.getThisdatetime()).getTime() - sd.parse(formBean.getLastdatetime()).getTime();
  			day = diff/nd;//����������
  			thisdaydegree = Double.parseDouble(formBean.getBlhdl())/day;//�����պĵ���
  			daydegereerate =((thisdaydegree-lastdaydegree)/lastdaydegree)*100 ;//������ϴ��պĵ�����ֵ
  			//System.out.println("��������:"+day);
  			//System.out.println("�����õ���:"+formBean.getActualdegree());
  			//System.out.println("�����պĵ���:"+thisdaydegree);
  			//System.out.println("�ϴ��պĵ���:"+lastdaydegree);
  			//System.out.println("������ϴ��պĵ�����ֵ:"+daydegereerate);
  		} catch (ParseException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		} 
  		//�ж�ad2_bz
  		String ad2_bz = "0";
  		AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
	    beanad = beanad.getLastAmmeterDegree(formBean.getAmmeteridFk());
	    String lastad = beanad.getLastdegree();

	  

	    if(lastad==null){
	       System.out.println("�ϴε�����Ϊ�գ�");

	       AmmeterDegreeFormBean beanada = new AmmeterDegreeFormBean();
	       beanada = beanada.getStartAmmeterModfiyDegree(formBean.getAmmeteridFk());
	       lastad = beanada.getInitialdegree();
	    }
	    //System.out.println("�ϴε�����(����):"+formBean.getLastdegree());
	    //System.out.println("�ϴε�����:"+lastad);
	    if(!formBean.getLastdegree().equals(lastad)){
	    	ad2_bz="1";
	    }
	    formBean.setAd2_bz(ad2_bz);
	    //�ж�ad2_bz
      AutoAuditBean bean = new AutoAuditBean();
    	 ArrayList fylist = new ArrayList();
    	 fylist = bean.getPageData(1,"");
  	String ItemID = "",
  	ItemName = "",
  	ItemValue = "",
  	ItemDescription = "";
  	String autoauditstatus="1",
  	autoauditdescription="",
  	autoauditdescription1="",
  	autoauditdescription2="",
  	autoauditdescription3="",
  	autoauditdescription4="";
  	String auditfee1="";
  	String auditfee2="";
  	String auditfee3="";
  	String auditfee4="";
  	//ad2_bz = formBean.getAd2_bz();
  	String floatdegree = formBean.getFloatdegree();
  	String memo = formBean.getMemo();
  	String ad1_bz = "0";
  	String floatdegree_bz = "";
  	String memo_bz = "";
  	if(floatdegree==null||floatdegree==""||floatdegree.equals("0")){
  		floatdegree_bz = "0";
  	}else{
  		floatdegree_bz = "1";
  	}
  	if(memo==null||memo==""){
  		memo_bz = "0";
  	}else{
  		memo_bz = "1";
  	}
  	if(memo_bz.equals(floatdegree_bz)){
  		ad1_bz = "1";
  	}
  	formBean.setAd2_bz1(ad1_bz);
      //�Զ�����ж�
            
      
  	 AmmeterDegreeBean abean= new AmmeterDegreeBean(); 
    
//      try {
  	String bzw="0";//��־λ
   	String  msg = abean.addDegree(formBean,bzw);
//      System.out.println("msg:"+msg);
//
//        if (msg.equals("�������ʧ�ܣ������Ի������Ա��ϵ��")) {
//        	bcg++;
//          row.add("�д���");
//          wrongContent.add(row);
//        }else{
//        	cg++;
//        }
//
//      }
//      catch (Exception e) {
//        e.printStackTrace();
//        row.add(e.toString());
//        wrongContent.add(row);
//      }
//      }//m����>1
//    }
//    cform.setCg(cg);
//    cform.setBcg(bcg);
//    cform.setZg(zg);
//    System.out.println("wrongContent:"+wrongContent);
	  }
      cform.setCg((content.size()-1)-wrongContent.size());
	  cform.setBcg(wrongContent.size());
	  cform.setZg((content.size()-1));
      return wrongContent;
    }
  private boolean getDBID(String con) {
	  ResultSet rs = null;
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
     return flag;
  }
      private ArrayList getDbyt(String con) {
    	  ResultSet rs = null;
    	   String sql ="select dbyt from dianbiao where dbid='"+con+"'";
          System.out.println("--���dbyt����id--"+sql);
          DataBase db = new DataBase();
          ArrayList list=new ArrayList() ;
          rs=null;
          try {
    		rs = db.queryAll(sql);
    	
    	      while(rs.next()) {
    	    	 // System.out.println(rs.getString("ammeterdegreeid"));
    	    	  list.add(rs.getString("dbyt"));
    	      }
    	  
          } catch (Exception e) {
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
         return list;
      
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
 

}
