package com.ptac.app.checksign.manElectricCheck.dao;


import com.noki.database.DataBase;
import com.noki.database.DbException;

public class CountryElecImpl implements CountryElecDao{
	 public synchronized String CheckCityFees(String personnal,String manualauditstatus,String chooseIdStr1,String chooseIdStr2,String bz, String manpassmemo) {
		 
	  	String msg = "批量操作失败！";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//系统时间
	    DataBase db = new DataBase();

        StringBuffer sql1 = new StringBuffer();//电量表
        StringBuffer sql2 = new StringBuffer();//电费表
        StringBuffer sql3 = new StringBuffer();//预付费表
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

   	  	System.out.println("人工电费审核批量操作");

	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//如果电费uuid不为空则执行sql
	    	  

	    	  db.setAutoCommit(false);
	    	  db.update(sql1.toString());
	    	  db.update(sql2.toString());
	    	  db.commit();
	    	  db.setAutoCommit(true);
	    	  
	    	  if(bz.equals("1")){
				     msg = "电费单批量通过, 操作成功!";
	    	  }else if(bz.equals("-2")){
	    		  	msg = "电费单批量不通过, 操作成功!";
	    	  }else if(bz.equals("0")){
				     msg = "电费单批量取消通过, 操作成功!";
				}else{
					 msg = "";
				}
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//如果预付费uuid不为空则执行sql

	    	  db.setAutoCommit(false);
	    	  db.update(sql3.toString());
	    	  db.commit();
	    	  db.setAutoCommit(true);
	    	  
	    	  
	    	  
	    	  if(bz.equals("1")){
				     msg = "电费单批量通过, 操作成功!";
				  }else if(bz.equals("-2")){
					 msg = "电费单批量不通过, 操作成功!";
				  }else if(bz.equals("0")){
				     msg = "电费单批量取消通过, 操作成功!";
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
			 
			  	String msg = "批量操作失败！";
			    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//系统时间
			    DataBase db = new DataBase();

		        StringBuffer sql1 = new StringBuffer();//电量表
		        StringBuffer sql2 = new StringBuffer();//电费表
		        StringBuffer sql3 = new StringBuffer();//预付费表
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
						     msg = "济宁人工审核批量通过, 操作成功!";
			    	  }else if(bz.equals("-2")){
			    		  	msg = "济宁人工审核批量不通过, 操作成功!";
			    	  }else if(bz.equals("0")){
						     msg = "济宁人工审核批量取消通过, 操作成功!";
						}else{
							 msg = "";
						}
			      }
			      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//如果预付费uuid不为空则执行sql

			    	  db.setAutoCommit(false);
			    	  db.update(sql3.toString());
			    	  db.commit();
			    	  db.setAutoCommit(true);
			    	  
			    	  if(bz.equals("1")){
						     msg = "济宁人工审核批量通过, 操作成功!";
			    	  	}else if(bz.equals("-2")){
			    	  			msg = "济宁人工审核批量不通过, 操作成功!";
						  }else if(bz.equals("0")){
							  		msg = "济宁人工审核批量取消通过, 操作成功!";
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


