package com.noki.mobi.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.util.CTime;

public class AccountJzq {
	public synchronized List<zdbzbean> getPageDatap(String whereStr) {		
		List<zdbzbean> list = new ArrayList<zdbzbean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	    ResultSet rs = null;
		try {
	     //  sql="select G2,G3,YEAR,ZP,ZS,SHEBEI,JZTYPE,JANUARY,FEBRUARY,MARCH,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER,APRIL,CHANGJIA from zdbz "+whereStr+" ";
	      
	       sql="SELECT TT.ZP,TT.ZS, TT.G2, TT.G3, TT.CHANGJIA,TT.JZTYPE,TT.SHEBEI,"+
			 "month1 AS EDGLXZ1,month2 AS EDGLXZ2, month3 AS EDGLXZ3,month7 AS EDGLXZ7,"+
			 "month4 AS EDGLXZ4,month5 AS EDGLXZ5,month6 AS EDGLXZ6,month8 AS EDGLXZ8,"+
			 "month9 AS EDGLXZ9, month10 AS EDGLXZ10,month11 AS EDGLXZ11,month12 AS EDGLXZ12 "+
			 "FROM ZDNHBZ TT "+whereStr+"ORDER BY TT.ZP,TT.ZS";
	       System.out.println("基站能耗标准查询："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbean zb=new zdbzbean();
	    	  zb.setG2(rs.getString("G2"));
				zb.setG3(rs.getString("G3"));
				//zb.setYEAR(rs.getString("YEAR"));
				zb.setZP(rs.getInt("ZP"));
				zb.setZS(rs.getInt("ZS"));
				zb.setJANUARY(rs.getString("EDGLXZ1"));
				zb.setFEBRUARY(rs.getString("EDGLXZ2"));
				zb.setMARCH(rs.getString("EDGLXZ3"));
				zb.setAPRIL(rs.getString("EDGLXZ4"));
				zb.setMAY(rs.getString("EDGLXZ5"));
				zb.setJUNE(rs.getString("EDGLXZ6"));
				zb.setJULY(rs.getString("EDGLXZ7"));
				zb.setAUGUST(rs.getString("EDGLXZ8"));
				zb.setSEPTEMBER(rs.getString("EDGLXZ9"));
				zb.setOCTOBER(rs.getString("EDGLXZ10"));
				zb.setNOVEMBER(rs.getString("EDGLXZ11"));
				zb.setDECEMBER(rs.getString("EDGLXZ12"));
				zb.setCHANGJIA(rs.getString("CHANGJIA"));
				list.add(zb);
	      }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
}
