
package com.noki.tongjichaxu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.zdbzbean;
import com.noki.util.CTime;

public class Jzhdl {
	public synchronized List<zdbzbean> getPageDatap(String whereStr) {		
		List<zdbzbean> list = new ArrayList<zdbzbean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "",sql1="";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	    ResultSet rs = null,rs1=null;
		try {
	     //  sql="select G2,G3,YEAR,ZP,ZS,SHEBEI,JZTYPE,JANUARY,FEBRUARY,MARCH,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER,APRIL,CHANGJIA from zdbz "+whereStr+" ";
	      
	       sql=" SELECT distinct  zd.Id,(CASE WHEN Zd.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Zd.XIAn) ELSE '' END) xian,"+
               " zd.jzname,db.dbid FROM zhandian zd,dianbiao db,DIANDUVIEW DW, DIANFEIVIEW DF "+
               " WHERE zd.id=db.zdid AND db.dbyt='dbyt01' AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK AND ZD.QYZT='1' AND DB.DBQYZT='1' "+whereStr+" ";
	    //   sql1="SELECT DISTINCT ZD.ID,ZD.JZNAME,DB.DBID,(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE ''  END) XIAN "+
           //    " FROM ZHANDIAN ZD, DIANBIAO DB, YUFUFEIVIEW YF, DIANDUVIEW DD "+
             //  " WHERE ZD.ID = DB.ZDID  AND DB.DBID = DD.AMMETERID_FK AND DB.DBID = YF.PREPAYMENT_AMMETERID  AND ZD.QYZT='1' AND DB.DBQYZT='1' "+whereStr+"";
	       		
	       System.out.println("基站月耗电量趋势右："+sql);
	       System.out.println("基站月耗电量趋势右1："+sql1);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	     // rs1 = db.queryAll( sql1.toString());
	      while(rs.next()){
	    	  zdbzbean zb=new zdbzbean();
	    	 zb.setId(rs.getString("ID"));
	    	 zb.setXian(rs.getString("XIAN"));
	    	 zb.setZDNAME(rs.getString("JZNAME"));
	    	 zb.setDbid(rs.getString("DBID"));
				list.add(zb);
	      }
	    //  while(rs1.next()){
	    //	  zdbzbean zb1=new zdbzbean();
	    //	 zb1.setId(rs1.getString("ID"));
	    //	 zb1.setXian(rs1.getString("XIAN"));
	    //	 zb1.setZDNAME(rs1.getString("JZNAME"));
	    	// zb1.setDbid(rs1.getString("DBID"));
	    //	 System.out.println("预付费");
			//	list.add(zb1);
	    //  }
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
//	      if (rs1 != null) {
//		        try {
//		          rs1.close();
//		        }
//		        catch (SQLException se) {
//		          se.printStackTrace();
//		        }
//		      }
//	      try {
//	        db.close();
//	      }
//	      catch (DbException de) {
//	        de.printStackTrace();
//	      }

	    }

	    return list;
	  }
	public synchronized List<zdbzbean> getPageDatapa(String whereStr) {		
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
	      
	       sql=" SELECT DFF.ACTUALPAY,DWW.BLHDL,TO_CHAR(DFF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DBB.DBID,TO_CHAR(DWW.THISDATETIME,'yyyy-mm-dd') THISDATETIME, TO_CHAR(DWW.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,"+
	           " (((CASE WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN 0 ELSE  BLHDL / TO_NUMBER(THISDATETIME - LASTDATETIME + 1)  END))) BL,"+
	           " (((CASE WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN 0 ELSE  TO_NUMBER(THISDATETIME - LASTDATETIME + 1)  END))) TS"+
	           " FROM ZHANDIAN ZDD,DIANBIAO DBB,DIANDUVIEW DWW,DIANFEIVIEW DFF "+
               " WHERE ZDD.ID = DBB.ZDID AND DBB.DBID = DWW.AMMETERID_FK AND DWW.AMMETERDEGREEID = DFF.AMMETERDEGREEID_FK "+whereStr+"  ORDER BY DFF.ACCOUNTMONTH ";
	       System.out.println("基站月耗电量趋势左："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbean zb=new zdbzbean();
	    	  zb.setMoney(rs.getString("ACTUALPAY"));
	    	  zb.setHdl(rs.getString("BLHDL"));
	    	  zb.setMonth(rs.getString("ACCOUNTMONTH"));
	    	  zb.setG2(rs.getString("BL"));
	    	  zb.setTs(rs.getString("TS"));
	    	  zb.setThisdatetime(rs.getString("THISDATETIME"));
	    	  zb.setLastdatetime(rs.getString("LASTDATETIME"));
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
	public synchronized List<zdbzbean> getPageDatapapp(String whereStr) {		
		List<zdbzbean> list = new ArrayList<zdbzbean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	    ResultSet rs = null;
		try {
	       sql=" SELECT (CASE WHEN Zd.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Zd.XIAn) ELSE '' END) XIANA,to_char(DF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,SUM(DF.ACTUALPAY) ACTUALPAY, SUM(DW.BLHDL) HDL"+
                " FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DW, DIANFEIVIEW DF "+
                " WHERE ZD.ID = DB.ZDID AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK  AND ZD.QYZT = '1' AND DB.DBQYZT = '1' "+whereStr+"  GROUP BY ZD.XIAN, ACCOUNTMONTH ORDER BY ACCOUNTMONTH ";
	       System.out.println("月耗电量趋势左aaaa："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbean zb=new zdbzbean();
	    	  zb.setXian(rs.getString("XIANA"));
	    	  zb.setMoney(rs.getString("ACTUALPAY"));
	    	  zb.setMonth(rs.getString("ACCOUNTMONTH"));
	    	  zb.setHdl(rs.getString("HDL"));
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
