package com.noki.tongjichaxu.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;

public class jzelectriCount {
	public synchronized ArrayList<jzelebean> getShiCount(String whereStr) {
    	System.out.println("DQelecount:"+whereStr);
        CTime ct = new CTime();
        StringBuffer s2 = new StringBuffer();
        StringBuffer s3 = new StringBuffer();
        String fzzdstr = "";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    ArrayList<jzelebean> list = new ArrayList<jzelebean>();
     String sql ="   SELECT to_char(DF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,(CASE WHEN DF.ACCOUNTMONTH IS NOT NULL THEN COUNT(DISTINCT ZD.ID)  ELSE   0 END) AS bzcount  FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW DW, DIANFEIVIEW DF" +
     		
     		" WHERE ZD.ID = DB.ZDID AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK" +whereStr+
     		"  GROUP BY ACCOUNTMONTH" ;
	    s2.append(sql);
        System.out.println(s2.toString());
        try {
            db.connectDb();
            DecimalFormat df=new DecimalFormat("#0.00"); 
            
            rs = db.queryAll(s2.toString());
            
            
            
            while(rs.next()){
            	jzelebean bean = new jzelebean();
            	bean.setMonth(rs.getString("ACCOUNTMONTH"));
                bean.setYbzcount(rs.getString("bzcount"));
            	
            	
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
