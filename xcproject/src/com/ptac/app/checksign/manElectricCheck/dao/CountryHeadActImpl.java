package com.ptac.app.checksign.manElectricCheck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.noki.database.DataBase;

public class CountryHeadActImpl implements CountryHeadActDao {
	 public synchronized String CheckCityFees(String personnal,String countryheadstatus,String chooseIdStr1,String chooseIdStr2,String bz) {
		 
	 	
	  	String msg = "��������ʧ�ܣ�";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//ϵͳʱ��
	    DataBase db = new DataBase();
	    Connection conn = null;
	    String str = "";
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    
        StringBuffer sql1 = new StringBuffer();//�½ᣬԤ����2
        StringBuffer sql2 = new StringBuffer();//��ͬ�忨Ԥ����1

	    try {
	    	conn = db.getConnection();
	    	//conn.setAutoCommit(false);
         sql1.append(" UPDATE ELECTRICFEES SET COUNTYAUDITSTATUS='" + countryheadstatus + "'," +
	      		       "COUNTYAUDITTIME=" + time + "," +str+
	      		     "COUNTYAUDITNAME='" + personnal + "'" +	
	      		   " WHERE DFUUID IN ("+chooseIdStr1+")");      		      
         sql2.append(" UPDATE PREPAYMENT SET COUNTYAUDITSTATUS='" + countryheadstatus + "'," +
	      		       		"COUNTYAUDITTIME=" + time + "," +str+
		      		     "COUNTYAUDITNAME='" + personnal + "'" +	
                	 	" WHERE UUID IN ("+chooseIdStr2+")"); 
         
   	  System.out.println("�������������������");

	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//������uuid��Ϊ����ִ��sql
	    	  ps1 = conn.prepareStatement(sql1.toString());
	    	  ps1.executeUpdate();
	    	  
	    	  if(bz.equals("1")){
				     msg = "��ѵ�����ͨ��, �����ɹ�!";
				  }else if(bz.equals("2")){
					 msg = "��ѵ�������ͨ��, �����ɹ�!";
				  }else if(bz.equals("0")){
				     msg = "��ѵ�����ȡ��ͨ��, �����ɹ�!";
				  }else{
					 msg = "";
				  }
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//���Ԥ����uuid��Ϊ����ִ��sql
	    	  ps2 = conn.prepareStatement(sql2.toString());
	    	  ps2.executeUpdate();	   
	    	  
	    	  if(bz.equals("1")){
				     msg = "��ѵ�����ͨ��, �����ɹ�!";
				  }else if(bz.equals("2")){
					 msg = "��ѵ�������ͨ��, �����ɹ�!";
				  }else if(bz.equals("0")){
				     msg = "��ѵ�����ȡ��ͨ��, �����ɹ�!";
				  }else{
					 msg = "";
				  }
	      }
	    
	      //conn.commit();
	      //conn.setAutoCommit(true);
	    }catch (Exception e) {//�쳣����
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{//�ͷ���Դ
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
	    return msg;
	  }

		/**
		 * @author lijing
		 * @see ���������������ͨ������ͨ����ȡ��ͨ������
		 * @param personnal
		 * @param countryheadstatus
		 * @param chooseIdStr1
		 * @param chooseIdStr2
		 * @param string
		 * @param cause
		 * @return
		 */
	@Override
	public String CheckCityFees1(String personnal, String countryheadstatus,
			String chooseIdStr1, String chooseIdStr2, String bz,String cause) {
		 
	 	
	  	String msg = "��������ʧ�ܣ�";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//ϵͳʱ��
	    DataBase db = new DataBase();
	    Connection conn = null;
	    String str = "";
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    
        StringBuffer sql1 = new StringBuffer();//�½ᣬԤ����2
        StringBuffer sql2 = new StringBuffer();//��ͬ�忨Ԥ����1

	    try {
	    	conn = db.getConnection();
	    	
         sql1.append(" UPDATE ELECTRICFEES SET COUNTYAUDITSTATUS='" + countryheadstatus + "'," +
	      		       "COUNTYAUDITTIME=" + time + "," +str+
	      		     "COUNTYAUDITNAME='" + personnal + "'," +
	      		   "NOPASSXZR='" + cause + "'" +
	      		   " WHERE DFUUID IN ("+chooseIdStr1+")");      		      
         sql2.append(" UPDATE PREPAYMENT SET COUNTYAUDITSTATUS='" + countryheadstatus + "'," +
	      		       		"COUNTYAUDITTIME=" + time + "," +str+
		      		     "COUNTYAUDITNAME='" + personnal + "'," +
		      		   "NOPASSXZR='" + cause + "'" +
                	 	" WHERE UUID IN ("+chooseIdStr2+")"); 
         
         System.out.println("sql"+sql1);
         System.out.println("sql"+sql2);
         
	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//������uuid��Ϊ����ִ��sql
	    	  ps1 = conn.prepareStatement(sql1.toString());
	    	  ps1.executeUpdate();
	    	  
	    	  if(bz.equals("1")){
				     msg = "�������������������ͨ��, �����ɹ�!";
				  }else if(bz.equals("2")){
					 msg = "���������������������ͨ��, �����ɹ�!";
				  }else if(bz.equals("0")){
				     msg = "�������������������ȡ��ͨ��, �����ɹ�!";
				  }else{
					 msg = "";
				  }
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//���Ԥ����uuid��Ϊ����ִ��sql
	    	  ps2 = conn.prepareStatement(sql2.toString());
	    	  ps2.executeUpdate();	   
	    	  
	    	  if(bz.equals("1")){
				     msg = "�������������������ͨ��, �����ɹ�!";
				  }else if(bz.equals("2")){
					 msg = "���������������������ͨ��, �����ɹ�!";
				  }else if(bz.equals("0")){
				     msg = "�������������������ȡ��ͨ��, �����ɹ�!";
				  }else{
					 msg = "";
				  }
	      }
	    
	      //conn.commit();
	      //conn.setAutoCommit(true);
	    }catch (Exception e) {//�쳣����
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{//�ͷ���Դ
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
	    return msg;
	  }

}
