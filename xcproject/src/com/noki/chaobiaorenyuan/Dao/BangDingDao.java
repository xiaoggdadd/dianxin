package com.noki.chaobiaorenyuan.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.chaobiaorenyuan.bean.ZhanDian;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;

public class BangDingDao {
	//根据用户名进行模糊查询
	public ArrayList<ZhanDian> SelectZDNAME(String shi, String xian,String xiaoqu ) {
		ArrayList<ZhanDian> al = null;
		String sql = "SELECT ID,JZNAME FROM ZHANDIAN WHERE SHI = "+shi+" AND XIAN = "+xian+" AND XIAOQU = "+xiaoqu+"";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ZhanDian>();
			while(rs.next()){
				ZhanDian cbr = new ZhanDian();
				cbr.setId(rs.getInt("ID"));	//ID
				cbr.setJzname(rs.getString("Jzname"));	//用户名
				al.add(cbr);
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
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
		return al;
	}
}
