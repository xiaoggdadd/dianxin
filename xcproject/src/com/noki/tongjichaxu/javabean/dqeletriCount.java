package com.noki.tongjichaxu.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class dqeletriCount {
	
	public synchronized ArrayList<dqbean> getShiCount(String whereStr,String qstr,String gstr) {
    	System.out.println("DQelecount:"+whereStr);
        CTime ct = new CTime();
        StringBuffer s2 = new StringBuffer();
        StringBuffer s3 = new StringBuffer();
        String fzzdstr = "";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    ArrayList<dqbean> list = new ArrayList<dqbean>();
	    // fzzdstr = getFuzeZdid(db,loginId);
       //s2.append("SELECT ZD.SHI, SUM(DF.ACTUALPAY) ACTUALPAY FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DW, DIANFEIVIEW DF WHERE ZD.ID = DB.ZDID AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK "+whereStr+" GROUP BY ZD.SHI");
     String sql ="SELECT (CASE WHEN ZD.SHI IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)  ELSE    '' END) " +
     		"" +qstr+
     		" AS SZDQ,SUM(DF.ACTUALPAY) ACTUALPAY, SUM(DECODE(ZD.STATIONTYPE, 'StationType01', DF.ACTUALPAY, 0)) XLT, SUM(DECODE(ZD.STATIONTYPE, 'StationType02', DF.ACTUALPAY, 0)) JZ, SUM(DECODE(ZD.STATIONTYPE, 'StationType03', DF.ACTUALPAY, 0)) JRW, SUM(DECODE(ZD.STATIONTYPE, 'StationType04', DF.ACTUALPAY, 0)) BG," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType05', DF.ACTUALPAY, 0)) SWJG, SUM(DECODE(ZD.STATIONTYPE, 'StationType06', DF.ACTUALPAY, 0)) WLAN, SUM(DECODE(ZD.STATIONTYPE, 'StationType07', DF.ACTUALPAY, 0)) YYWD, SUM(DECODE(ZD.STATIONTYPE, 'StationType08', DF.ACTUALPAY, 0)) TXDL," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType09', DF.ACTUALPAY, 0)) SNFB, SUM(DECODE(ZD.STATIONTYPE, 'StationType10', DF.ACTUALPAY, 0)) AG, SUM(DECODE(ZD.STATIONTYPE, 'StationType11', DF.ACTUALPAY, 0)) PON,  SUM(DECODE(ZD.STATIONTYPE, 'StationType12', DF.ACTUALPAY, 0)) MKJ," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType13', DF.ACTUALPAY, 0)) SPJK, SUM(DECODE(ZD.STATIONTYPE, 'StationType14', DF.ACTUALPAY, 0)) LYJHJ,  SUM(DECODE(ZD.STATIONTYPE, 'StationType15', DF.ACTUALPAY, 0)) YDLYZ, SUM(DECODE(ZD.STATIONTYPE, 'StationType16', DF.ACTUALPAY, 0)) YDHZ," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType17', DF.ACTUALPAY, 0)) KDSBJ, SUM(DECODE(ZD.STATIONTYPE, 'StationType18', DF.ACTUALPAY, 0)) QT,  SUM(DECODE(ZD.STATIONTYPE, 'StationType19', DF.ACTUALPAY, 0)) YDJZ, SUM(DECODE(ZD.STATIONTYPE, 'StationType20', DF.ACTUALPAY, 0)) HXJF," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType21', DF.ACTUALPAY, 0)) BGYL,  SUM(DECODE(ZD.STATIONTYPE, 'StationType22', DF.ACTUALPAY, 0)) ONU, SUM(DECODE(ZD.STATIONTYPE, 'StationType23', DF.ACTUALPAY, 0)) A9806H,  SUM(DECODE(ZD.STATIONTYPE, 'StationType24', DF.ACTUALPAY, 0)) LAN," +
     		//" SUM(DECODE(ZD.STATIONTYPE, 'StationType25', DF.ACTUALPAY, 0)) XZZJ,  SUM(DECODE(ZD.STATIONTYPE, 'StationType26', DF.ACTUALPAY, 0)) XQJF,  SUM(DECODE(ZD.STATIONTYPE, 'StationType27', DF.ACTUALPAY, 0)) XLTJZXD, " +
     		"  SUM(DECODE(ZD.GDFS, 'gdfs01', DF.ACTUALPAY, 0)) GDJ," +
     		"  SUM(DECODE(ZD.GDFS, 'gdfs02', DF.ACTUALPAY, 0)) YZ, SUM(DECODE(ZD.GDFS, 'gdfs03', DF.ACTUALPAY, 0)) GDQT FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DW, DIANFEIVIEW DF" +
     		"  WHERE ZD.ID = DB.ZDID AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK" +
     		"" +whereStr+
     		"  GROUP BY ZD.SHI" +gstr+
     		"";
	    s2.append(sql);
        System.out.println(s2.toString());
        String sq=" SELECT SUM(DF.ACTUALPAY)ALLMON FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DW, DIANFEIVIEW DF WHERE ZD.ID = DB.ZDID  AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK"+
                   ""+whereStr;
        s3.append(sq);
        try {
            db.connectDb();
            DecimalFormat df=new DecimalFormat("#0.00"); 
            double tempt =0.00;
        	double b1 =0.00000000;
        	double b2 =0.00000000;
            System.out.println("strall.toString():"+s2.toString());
            System.out.println("strall.toString():"+s3.toString());
            rs = db.queryAll(s3.toString());
            while(rs.next()){
            	 b1 = rs.getDouble("ALLMON");
            }
            rs = db.queryAll(s2.toString());
            
            
            while(rs.next()){
            	dqbean bean = new dqbean();
            	bean.setSZDQ(rs.getString("SZDQ"));
            	bean.setACTUALPAY(df.format(rs.getDouble("ACTUALPAY")));
            	b2 = rs.getDouble("ACTUALPAY");
            	tempt =b2/b1*100;
            	bean.setShibili(
            			df.format(tempt)+"%"
                     
            	);
            	
            	b2 = rs.getDouble("GDJ");
            	tempt = b2;
            	bean.setGDJ(df.format(tempt));
            	
            	b2 = rs.getDouble("YZ");
            	tempt = b2;
            	bean.setYZ(df.format(tempt));
            	
            	b2 = rs.getDouble("GDQT");
            	tempt = b2;
            	bean.setGsQT(df.format(tempt));
//            	dqCount bean = new dqCount();
//            	bean.setShi(rs.getString("SHI"));
//            	bean.setAcctry(rs.getString("ACTUALPAY"));
            	list.add(bean);
            }
            
    	     
        }

        catch (Exception de) {
            de.printStackTrace();
        }

        finally {
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
    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
  //负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			
		}	
    System.out.println("负责站点条件："+cxtj);
		return cxtj.toString();
	}

}
