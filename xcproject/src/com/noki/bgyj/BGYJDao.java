package com.noki.bgyj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.Query;
/**
 * Demo BGYJDao
 * 
 * @author shengaina
 * @date 2017-12-9
 */
public class BGYJDao {
	/**
	 * 标杆预警显示所有
	 */
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
	public synchronized ArrayList SelectBGYJ(int start) throws SQLException{

		ArrayList list=new ArrayList();
		
		DataBase dataBase = new DataBase();
		ResultSet rs = null;
		
		
		StringBuffer sql = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
		
		
		ResultSet rs3 = null;
		try {
			dataBase.connectDb();
			
			 sql.append("select bg.state,bg.id ID,bg.zdid ZDID,to_char(bg.dccbsj, 'YYYY-MM-DD HH24:MI:SS') DCCBSJ,to_char(bg.sccbsj, 'YYYY-MM-DD HH24:MI:SS') SCCBSJ,bg.dccbdl DCCBDL,bg.sccbdl SCCBDL,bg.bgz BGZ,zd.jzname JZNAME from bgyj bg,zhandian zd where bg.state=1 and bg.zdid=zd.id order by bg.id");
			 System.out.println("标杆预警："+sql);
			 
			 s3.append("select count(*)  from (" + sql + ")");
             System.out.println("标杆查询分页"+s3); 
           
             rs3=dataBase.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = dataBase.queryAll(nbean.getQueryStr(start, sql.toString()));
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
					dataBase.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
		return list;
		
	}
	
}