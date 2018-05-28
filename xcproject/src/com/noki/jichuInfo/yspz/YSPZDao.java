package com.noki.jichuInfo.yspz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.util.Page;

public class YSPZDao {
	ResultSet rs = null;
	DataBase db = new DataBase();

	/**
	 * 查询分页
	 * @param maxrow
	 * @return
	 */
	public ArrayList<YSPZBEAN> QueryYSPZ(String sql,Page p) {
		ArrayList<YSPZBEAN> list = new ArrayList<YSPZBEAN>();
	
		try {
			rs = db.query(sql, p.getPage());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询全部
	 * @param sql
	 * @param maxrow
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<YSPZBEAN> QueryYSPZ(String sql) throws SQLException {
		ArrayList<YSPZBEAN> list = new ArrayList<YSPZBEAN>();
		try {
			rs = db.queryAll(sql);
			while(rs.next()){
				YSPZBEAN yspz = new YSPZBEAN();
				yspz.setYid(rs.getInt("YID"));
				yspz.setENTRYPERSON(rs.getString("ENTRYPERSON"));
				yspz.setINSERTTIME(rs.getString("INSERTTIME"));
				yspz.setMONEY(rs.getDouble("MONEY"));
				yspz.setMONTH(rs.getString("MONTH"));
				yspz.setSHI(rs.getString("SHI"));
				yspz.setSTATE(rs.getInt("STATE"));
				yspz.setUPDATETIME(rs.getString("UPDATETIME"));
				list.add(yspz);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int  QueryCount(String sql) throws SQLException {
		int i = 0;
		try {
			rs = db.queryAll(sql);
			if(rs.next()){
				i = rs.getInt(0);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i ;
		
	}
}
