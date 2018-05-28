package com.noki.zdymess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class zdyzhandianDao {
	public synchronized ArrayList<zdyzhandian> getZdyzhandian(){
		 String sql ="select zd.jzname,zd.bieming,zd.address,zd.zdcode,zd.sheng,zd.shi,zd.xian,zd.xiaoqu from zhandian zd where zd.shi='13717'and zd.xian='1371722'";
		 ArrayList<zdyzhandian> list = new ArrayList<zdyzhandian>();
		 DataBase db = new DataBase();
		 ResultSet rs = null;
		    try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				System.out.println("-------"+sql.toString());
				while(rs.next()){
					zdyzhandian  bean = new zdyzhandian();
					bean.setJzname(rs.getString("jzname"));
					bean.setBieming(rs.getString("bieming"));
					bean.setAddress(rs.getString("address"));
					bean.setZdcode(rs.getString("zdcode"));
					bean.setSheng(rs.getString("sheng"));
					bean.setShi(rs.getString("shi"));
					bean.setXian(rs.getString("xian"));
					bean.setXiaoqu(rs.getString("xiaoqu"));
					list.add(bean);
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
