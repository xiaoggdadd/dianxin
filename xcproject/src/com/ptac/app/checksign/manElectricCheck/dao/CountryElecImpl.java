package com.ptac.app.checksign.manElectricCheck.dao;


import com.noki.database.DataBase;
import com.noki.database.DbException;

public class CountryElecImpl implements CountryElecDao{
	 public synchronized String CheckCityFees(String personnal,String manualauditstatus,String chooseIdStr1,String chooseIdStr2,String bz, String manpassmemo) {
		 
	  	String msg = "��������ʧ�ܣ�";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//ϵͳʱ��
	    DataBase db = new DataBase();

        StringBuffer sql1 = new StringBuffer();//������
        StringBuffer sql2 = new StringBuffer();//��ѱ�
        StringBuffer sql3 = new StringBuffer();//Ԥ���ѱ�
	    try {
	    	db.connectDb();
	          if(chooseIdStr1.split(",").length>10){
	        	  sql1.append("execute");
	        	  sql2.append("execute");
	        	  sql3.append("execute");
	          }
	    	
	    	
         sql1.append(" UPDATE AMMETERDEGREES SET MANUALAUDITSTATUS ='" + manualauditstatus + "',"
        		 +" MANUALAUDITDATETIME = " + time + ","
        		 +" MANUALAUDITNAME = '" + personnal + "'"
	      		 +" WHERE UUID IN ("+chooseIdStr1+")");    
         sql2.append(" UPDATE ELECTRICFEES SET MANUALAUDITSTATUS='" + manualauditstatus + "',"
        		 +" MANUALAUDITDATETIME=" + time + ","
    		     +" MANUALAUDITNAME='" + personnal + "'," 
        		 +" MANPASSMEMO = '" + manpassmemo + "'"
    		     +" WHERE DFUUID IN ("+chooseIdStr1+")");    
         
         sql3.append(" UPDATE PREPAYMENT SET MANUALAUDITSTATUS='" + manualauditstatus + "'," 
	      		 +" MANUALAUDITDATETIME=" + time + ","
	      		 +" MANUALAUDITNAME='" + personnal + "'," 
        		 +" MANPASSMEMO = '" + manpassmemo + "'"
	      		 +" WHERE UUID IN ("+chooseIdStr2+")"); 

   	  	System.out.println("�˹���������������");

	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//������uuid��Ϊ����ִ��sql
	    	  

	    	  db.setAutoCommit(false);
	    	  db.update(sql1.toString());
	    	  db.update(sql2.toString());
	    	  db.commit();
	    	  db.setAutoCommit(true);
	    	  
	    	  if(bz.equals("1")){
				     msg = "��ѵ�����ͨ��, �����ɹ�!";
	    	  }else if(bz.equals("-2")){
	    		  	msg = "��ѵ�������ͨ��, �����ɹ�!";
	    	  }else if(bz.equals("0")){
				     msg = "��ѵ�����ȡ��ͨ��, �����ɹ�!";
				}else{
					 msg = "";
				}
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//���Ԥ����uuid��Ϊ����ִ��sql

	    	  db.setAutoCommit(false);
	    	  db.update(sql3.toString());
	    	  db.commit();
	    	  db.setAutoCommit(true);
	    	  
	    	  
	    	  
	    	  if(bz.equals("1")){
				     msg = "��ѵ�����ͨ��, �����ɹ�!";
				  }else if(bz.equals("-2")){
					 msg = "��ѵ�������ͨ��, �����ɹ�!";
				  }else if(bz.equals("0")){
				     msg = "��ѵ�����ȡ��ͨ��, �����ɹ�!";
				  }else{
					 msg = "";
				  }
	      }
	      //conn.commit();
	      //conn.setAutoCommit(true);
	    } catch (DbException de) {
		      try {
			        db.rollback();
			        db.setAutoCommit(true);
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      de.printStackTrace();
			    }
			    finally {
			      try {

			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
			    }
	    return msg;
	  }

	@Override
	public String CheckCityFees1(String personnal, String manualauditstatus,
			String chooseIdStr1, String chooseIdStr2, String bz,
			String manpassmemo, String cause) {
			 
			  	String msg = "��������ʧ�ܣ�";
			    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//ϵͳʱ��
			    DataBase db = new DataBase();

		        StringBuffer sql1 = new StringBuffer();//������
		        StringBuffer sql2 = new StringBuffer();//��ѱ�
		        StringBuffer sql3 = new StringBuffer();//Ԥ���ѱ�
			    try {
			    	db.connectDb();
			          if(chooseIdStr1.split(",").length>10){
			        	  sql1.append("execute");
			        	  sql2.append("execute");
			        	  sql3.append("execute");
			          }
			    	
		         sql1.append(" UPDATE AMMETERDEGREES SET MANUALAUDITSTATUS ='" + manualauditstatus + "',"
		        		 +" MANUALAUDITDATETIME = " + time + ","
		        		 +" MANUALAUDITNAME = '" + personnal + "'"
			      		 +" WHERE UUID IN ("+chooseIdStr1+")");    
		         sql2.append(" UPDATE ELECTRICFEES SET MANUALAUDITSTATUS='" + manualauditstatus + "',"
		        		 +" MANUALAUDITDATETIME=" + time + ","
		    		     +" MANUALAUDITNAME='" + personnal + "'," 
		        		 +" MANPASSMEMO = '" + manpassmemo + "'," 
		        		 +" NOPASSRG = '" + cause + "'"
		    		     +" WHERE DFUUID IN ("+chooseIdStr1+")");    
		         
		         sql3.append(" UPDATE PREPAYMENT SET MANUALAUDITSTATUS='" + manualauditstatus + "'," 
			      		 +" MANUALAUDITDATETIME=" + time + ","
			      		 +" MANUALAUDITNAME='" + personnal + "'," 
		        		 +" MANPASSMEMO = '" + manpassmemo + "'," 
		        		 +" NOPASS = '" + cause + "'"
			      		 +" WHERE UUID IN ("+chooseIdStr2+")"); 

			      if(chooseIdStr1!=""&&chooseIdStr1!=null){

			    	  db.setAutoCommit(false);
			    	  db.update(sql1.toString());
			    	  db.update(sql2.toString());
			    	  db.commit();
			    	  db.setAutoCommit(true);
			    	  
			    	  if(bz.equals("1")){
						     msg = "�����˹��������ͨ��, �����ɹ�!";
			    	  }else if(bz.equals("-2")){
			    		  	msg = "�����˹����������ͨ��, �����ɹ�!";
			    	  }else if(bz.equals("0")){
						     msg = "�����˹��������ȡ��ͨ��, �����ɹ�!";
						}else{
							 msg = "";
						}
			      }
			      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//���Ԥ����uuid��Ϊ����ִ��sql

			    	  db.setAutoCommit(false);
			    	  db.update(sql3.toString());
			    	  db.commit();
			    	  db.setAutoCommit(true);
			    	  
			    	  if(bz.equals("1")){
						     msg = "�����˹��������ͨ��, �����ɹ�!";
			    	  	}else if(bz.equals("-2")){
			    	  			msg = "�����˹����������ͨ��, �����ɹ�!";
						  }else if(bz.equals("0")){
							  		msg = "�����˹��������ȡ��ͨ��, �����ɹ�!";
						  	}else{
						  				msg = "";
						  	}
			      }
			    } catch (DbException de) {
				      try {
					        db.rollback();
					        db.setAutoCommit(true);
					      }
					      catch (DbException dee) {
					        dee.printStackTrace();
					      }
					      de.printStackTrace();
					    }
					    finally {
					      try {

					        db.close();
					      }
					      catch (Exception de) {
					        de.printStackTrace();
					      }
					    }
			    return msg;
	}

}


