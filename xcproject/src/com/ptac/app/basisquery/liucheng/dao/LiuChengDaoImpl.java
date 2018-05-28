package com.ptac.app.basisquery.liucheng.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.liucheng.bean.LiuCheng;

public class LiuChengDaoImpl implements LiuChengDao {

	Connection conn = null;
	Statement sta = null;
	@Override
	public LiuCheng getLiuChengInfo(String whereStr) {
		
		LiuCheng bean = new LiuCheng();
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT E.MANUALAUDITSTATUS,E.MANUALAUDITDATETIME,E.MANUALAUDITNAME,E.COUNTYAUDITSTATUS,E.COUNTYAUDITTIME," 
	    		 + "E.COUNTYAUDITNAME,E.CITYAUDIT,E.CITYAUDITTIME,E.CITYAUDITPENSONNEL,E.CITYZRAUDITSTATUS,E.CITYZRAUDITTIME,"
	    		 + "E.CITYZRAUDITNAME,E.FINANCIALDATETIME,E.FINANCIALOPERATOR,E.AUTOAUDITSTATUS,E.ENTRYPENSONNEL,E.ENTRYTIME,E.DAYINREN,E.DAYINTIME"
	    		 + " FROM ELECTRICFEES E "+whereStr);
	    		
	     //" WHERE A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK "+ whereStr);		    
		 //+ whereStr+"  AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");  , AMMETERDEGREES A
	    
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {
	      System.out.println("流程查询:"+sql);
	      db.connectDb();	 
	      conn = db.getConnection();
	      sta = conn.createStatement();
	      rs = sta.executeQuery(sql.toString());
	      while(rs.next()){
	    	  
	    	  String rgzt = rs.getString("MANUALAUDITSTATUS")!=null?rs.getString("MANUALAUDITSTATUS"):"";//人工审核状态
	    	  String rgtime = rs.getString("MANUALAUDITDATETIME")!=null?rs.getString("MANUALAUDITDATETIME"):"";//人工审核时间
	    	  String rgpeople = rs.getString("MANUALAUDITNAME")!=null?rs.getString("MANUALAUDITNAME"):"";//人工审核人
	    	  String qxzt = rs.getString("COUNTYAUDITSTATUS")!=null?rs.getString("COUNTYAUDITSTATUS"):"";//区县审核状态
	    	  String qxtime = rs.getString("COUNTYAUDITTIME")!=null?rs.getString("COUNTYAUDITTIME"):"";//区县审核时间
	    	  String qxpeople = rs.getString("COUNTYAUDITNAME")!=null?rs.getString("COUNTYAUDITNAME"):"";//区县审核人
	    	  String sjzt = rs.getString("CITYAUDIT")!=null?rs.getString("CITYAUDIT"):"";//市级审核状态
	    	  String sjtime = rs.getString("CITYAUDITTIME")!=null?rs.getString("CITYAUDITTIME"):"";//市级审核时间
	    	  String sjpeople = rs.getString("CITYAUDITPENSONNEL")!=null?rs.getString("CITYAUDITPENSONNEL"):"";//市级审核人
	    	  String szrzt = rs.getString("CITYZRAUDITSTATUS")!=null?rs.getString("CITYZRAUDITSTATUS"):"";//市主任审核状态
	    	  String szrtime = rs.getString("CITYZRAUDITTIME")!=null?rs.getString("CITYZRAUDITTIME"):"";//市主任审核时间
	    	  String szrpeople = rs.getString("CITYZRAUDITNAME")!=null?rs.getString("CITYZRAUDITNAME"):"";//市主任审核人
	    	  String cwtime = rs.getString("FINANCIALDATETIME")!=null?rs.getString("FINANCIALDATETIME"):"";//财务审核时间
	    	  String cwpeople = rs.getString("FINANCIALOPERATOR")!=null?rs.getString("FINANCIALOPERATOR"):"";//财务审核人
	    	  String zdzt  = rs.getString("AUTOAUDITSTATUS")!=null?rs.getString("AUTOAUDITSTATUS"):"";//自动审核状态
	    	  String lrry  = rs.getString("ENTRYPENSONNEL")!=null?rs.getString("ENTRYPENSONNEL"):"";//录入人
	    	  String lrsj  = rs.getString("ENTRYTIME")!=null?rs.getString("ENTRYTIME"):"";//录入时间
	    	  String dyr  = rs.getString("DAYINREN")!=null?rs.getString("DAYINREN"):"";//打印人
	    	  String dysj  = rs.getString("DAYINTIME")!=null?rs.getString("DAYINTIME"):"";//打印时间
	    	  
	    	  bean.setRgtime(rgtime);
	    	  bean.setRgpeople(rgpeople);
	    	  bean.setQxtime(qxtime);
	    	  bean.setQxpeople(qxpeople);
	    	  bean.setSjtime(sjtime);
	    	  bean.setSjpeople(sjpeople);
	    	  bean.setSzrtime(szrtime);
	    	  bean.setSzrpeople(szrpeople);
	    	  bean.setCwtime(cwtime);
	    	  bean.setCwpeople(cwpeople);
	    	  bean.setLrry(lrry);
	    	  bean.setLrsj(lrsj);
	    	  bean.setDyr(dyr);
	    	  bean.setDysj(dysj);
	    	  
	    	  	//------自动审核状态------
	    	  	if("0".equals(zdzt)){
		  		    bean.setZdzt("未审核");
		  		}else if("1".equals(zdzt)){
		  			bean.setZdzt("通过");
		  		}
	    	  
		    	//------人工审核状态  财务审核状态------
		  		if("0".equals(rgzt)){
		  		    bean.setRgzt("未审核");
		  		    bean.setCwzt("未审核");
		  		}else if("1".equals(rgzt)){
		  			bean.setRgzt("通过");
		  			bean.setCwzt("未审核");
		  		}else if("2".equals(rgzt)){
		  			bean.setRgzt("通过");
		  			bean.setCwzt("通过");
		  		}else if("-1".equals(rgzt)){
		  			bean.setRgzt("通过");
		  			bean.setCwzt("不通过");
		  		}else if("-2".equals(rgzt)){
		  			bean.setRgzt("不通过");
		  			bean.setCwzt("未审核");
		  		}
       		
	  		    //------区县主任审核状态------
	       		if("0".equals(qxzt)){
	       			bean.setQxzt("未审核");
	       		}else if("1".equals(qxzt)){
	       			bean.setQxzt("通过");
	       		}else if("2".equals(qxzt)){
	       			bean.setQxzt("不通过");
	       		}
	       		
	       		//------市级审核状态------
	       		if("0".equals(sjzt)){
	       			bean.setSjzt("未审核");
	       		}else if("1".equals(sjzt)){
	       			bean.setSjzt("通过");
	       		}else if("-2".equals(sjzt)){
	       			bean.setSjzt("不通过");
	       		}
	       		
	       		//------市主任审核状态------
	       		if("0".equals(szrzt)){
	       			bean.setSzrzt("未审核");
	       		}else if("1".equals(szrzt)){
	       			bean.setSzrzt("通过");
	       		}else if("2".equals(szrzt)){
	       			bean.setSzrzt("不通过");
	       		}
		    	  
	      }
	    } catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs,sta,conn);
	    }
	    return bean;
	  }

}
