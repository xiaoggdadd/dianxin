package com.ptac.app.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.news.bean.GGBean;

public class NewGongGaoDaoImpl implements NewGongGaoDao {

	@Override
	public List<GGBean> getNewGongGao(String accountname) {
		
		DataBase db = null;
		Connection conn = null;
		Statement stt = null;
		ResultSet rs = null;
		String whereStr = "";;
		List<GGBean> list = new ArrayList<GGBean>();
		
		String sql  = "SELECT GG.GGID FROM NEWS GG WHERE GG.USERID = '" + accountname + "'";
		String sql1 = "SELECT ID,BT,NR,GGTIME FROM GONGGAO G ORDER BY G.GGTIME DESC";
		db = new DataBase();
		try {
			conn = db.getConnection();
			stt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			System.out.println("sql"+sql);
			rs = stt.executeQuery(sql);
			
			if(rs.next()== false){//нч
			}else{
				rs.beforeFirst();
				while(rs.next()){
					if(rs.isLast()){
					whereStr = whereStr + rs.getString("GGID");
					}else{
						whereStr = whereStr + rs.getString("GGID")+",";
					}
				}
				sql1 = "SELECT ID,BT,GGTIME FROM GONGGAO G WHERE G.ID NOT IN (" + whereStr + ") ORDER BY G.GGTIME DESC";
			}
			System.out.println("sql1"+sql1);
			rs = stt.executeQuery(sql1);
			while(rs.next()){
				GGBean bean = new GGBean();
				bean.setId(rs.getString("ID")==null ? "" : rs.getString("ID"));
				bean.setBt(rs.getString("BT")==null ? "" : rs.getString("BT"));
				bean.setGgtime(rs.getString("GGTIME")==null ? "" : rs.getString("GGTIME"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		db.free(rs, stt, conn);	
		}
		return list;
	}

	@Override
	public void insertNews(String ggid, String accountid) {
		DataBase db = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO NEWS(GGID,USERID) VALUES(?,?)";
		
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ggid);
			ps.setString(2, accountid);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(rs, ps, conn);
		}
		
	}

}
