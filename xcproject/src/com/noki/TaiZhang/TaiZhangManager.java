package com.noki.TaiZhang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class TaiZhangManager {
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
	public synchronized ArrayList getPageData(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 "+whereStr);
		//strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT RNDIQU(ZD.SHI)AS SHI,RNDIQU(ZD.XIAN)AS XIAN,RNDIQU(ZD.XIAOQU)AS XIAOQU,ZD.JZNAME,ZD.JZCODE,ZD.GDFNAME,RTNAME(ZD.GDFS) GDFS,'抄表结算' JSFS,");
            s2.append("AM.BLHDL,AM.THISDEGREE,AM.LASTDEGREE,to_char(AM.THISDATE,'yyyy-mm-dd')THISDATE,EF.ACTUALPAY,EF.ACTUALPAY_D,EF.ACTUALPAY_L,EF.ACTUALPAY_Y,RTNAME(EF.NOTETYPEID) PJLX,EF.UNITPRICE,");
            s2.append("EF.FTBL_L,EF.FTBL_Y,EF.FTBL_D,(CASE WHEN EF.NOTETYPEID='pjlx05' THEN 0.1964 ELSE 0.00 END)SFYZ FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF");
            s2.append(" WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK"+whereStr);
			System.out.println("站点查询"+s2);
               s3.append("select count(*)  from (" + s2 + ")");
               System.out.println("站点查询分页"+s3); 
               ResultSet rs3 = null;
               rs3=db.queryAll(s3.toString());
               if(rs3.next()){
               	this.setAllPage((rs3.getInt(1)+9)/10);
               }
              NPageBean nbean = new NPageBean();
              rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
              Query query = new Query();
              list = query.query(rs);
              rs3.close();		
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
}
