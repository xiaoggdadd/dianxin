package com.ptac.app.checksign.manElectricCheck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.noki.database.DataBase;

public class CountryHeadActImpl implements CountryHeadActDao {
	 public synchronized String CheckCityFees(String personnal,String countryheadstatus,String chooseIdStr1,String chooseIdStr2,String bz) {
		 
	 	
	  	String msg = "批量操作失败！";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//系统时间
	    DataBase db = new DataBase();
	    Connection conn = null;
	    String str = "";
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    
        StringBuffer sql1 = new StringBuffer();//月结，预付费2
        StringBuffer sql2 = new StringBuffer();//合同插卡预付费1

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
         
   	  System.out.println("区县主任审核批量操作");

	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//如果电费uuid不为空则执行sql
	    	  ps1 = conn.prepareStatement(sql1.toString());
	    	  ps1.executeUpdate();
	    	  
	    	  if(bz.equals("1")){
				     msg = "电费单批量通过, 操作成功!";
				  }else if(bz.equals("2")){
					 msg = "电费单批量不通过, 操作成功!";
				  }else if(bz.equals("0")){
				     msg = "电费单批量取消通过, 操作成功!";
				  }else{
					 msg = "";
				  }
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//如果预付费uuid不为空则执行sql
	    	  ps2 = conn.prepareStatement(sql2.toString());
	    	  ps2.executeUpdate();	   
	    	  
	    	  if(bz.equals("1")){
				     msg = "电费单批量通过, 操作成功!";
				  }else if(bz.equals("2")){
					 msg = "电费单批量不通过, 操作成功!";
				  }else if(bz.equals("0")){
				     msg = "电费单批量取消通过, 操作成功!";
				  }else{
					 msg = "";
				  }
	      }
	    
	      //conn.commit();
	      //conn.setAutoCommit(true);
	    }catch (Exception e) {//异常处理
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{//释放资源
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
	    return msg;
	  }

		/**
		 * @author lijing
		 * @see 济宁区县主任审核通过、不通过、取消通过方法
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
		 
	 	
	  	String msg = "批量操作失败！";
	    String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";//系统时间
	    DataBase db = new DataBase();
	    Connection conn = null;
	    String str = "";
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    
        StringBuffer sql1 = new StringBuffer();//月结，预付费2
        StringBuffer sql2 = new StringBuffer();//合同插卡预付费1

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
         
	      if(chooseIdStr1!=""&&chooseIdStr1!=null){//如果电费uuid不为空则执行sql
	    	  ps1 = conn.prepareStatement(sql1.toString());
	    	  ps1.executeUpdate();
	    	  
	    	  if(bz.equals("1")){
				     msg = "济宁区县主任审核批量通过, 操作成功!";
				  }else if(bz.equals("2")){
					 msg = "济宁区县主任审核批量不通过, 操作成功!";
				  }else if(bz.equals("0")){
				     msg = "济宁区县主任审核批量取消通过, 操作成功!";
				  }else{
					 msg = "";
				  }
	      }
	      if(chooseIdStr2!=""&&chooseIdStr2!=null){	//如果预付费uuid不为空则执行sql
	    	  ps2 = conn.prepareStatement(sql2.toString());
	    	  ps2.executeUpdate();	   
	    	  
	    	  if(bz.equals("1")){
				     msg = "济宁区县主任审核批量通过, 操作成功!";
				  }else if(bz.equals("2")){
					 msg = "济宁区县主任审核批量不通过, 操作成功!";
				  }else if(bz.equals("0")){
				     msg = "济宁区县主任审核批量取消通过, 操作成功!";
				  }else{
					 msg = "";
				  }
	      }
	    
	      //conn.commit();
	      //conn.setAutoCommit(true);
	    }catch (Exception e) {//异常处理
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{//释放资源
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
	    return msg;
	  }

}
