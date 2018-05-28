package com.noki.zdymess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class zdySessionDao {
	public synchronized ArrayList<session> getSession(){
		 String sql ="select v.LOGON_TIME, v.USERNAME,v.STATUS,v.MACHINE,v.SQL_EXEC_START,v.MODULE from v$session v";
		 ArrayList<session> list = new ArrayList<session>();
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		    try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				System.out.println(sql.toString());
				while(rs.next()){
					session s = new session();
					s.setLogintime(rs.getString("LOGON_TIME"));
					s.setStatus(rs.getString("STATUS"));
					s.setMachine(rs.getString("MACHINE"));
					s.setUsername(rs.getString("USERNAME"));
					s.setMehord(rs.getString("MODULE"));
					s.setSqlexectime(rs.getString("SQL_EXEC_START"));
					list.add(s);
				}
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if(rs != null){rs.close();}
				} catch (SQLException e) {}
				try {
					if(db !=null){db.close();}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			return list;
	}

    
    
}
