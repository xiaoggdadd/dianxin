package com.noki.hetongguanli.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.hetongguanli.Model.HetongModel;
import com.noki.page.NPageBean;

public class HetongDao {
	// 详细页面单独使用请勿改动
	public HetongModel Selectall(String id) {
		HetongModel ht = null;
		String sql = "select * from TBL_HETONG_SHENPI WHERE ID LIKE '%" + id
				+ "%'";
		ArrayList<HetongModel> al = null;
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println(sql+"88888888");
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			
			while (rs.next()) {
				HetongModel hetong = new HetongModel();
				hetong.setID(rs.getInt("ID"));
				hetong.setSTARTTIME(rs.getString("STARTTIME"));
				hetong.setENDTIME(rs.getString("ENDTIME"));
				hetong.setPARTYA(rs.getString("PARTYA"));
				hetong.setPARTYB(rs.getString("PARTYB"));
				hetong.setCONTRACTNAME(rs.getString("CONTRACTNAME"));
				hetong.setPROJECTAMONNT(rs.getString("PROJECTAMONNT"));
				hetong.setCONTRACTDETAIL(rs.getString("CONTRACTDETAIL"));
				return  hetong;
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

		return ht;
	}

	// 页面加载时查询功能的实现
	public ArrayList<HetongModel> Select() {
		ArrayList<HetongModel> al = null;
		String sql = "select * from TBL_HETONG_SHENPI";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<HetongModel>();
			while (rs.next()) {
				HetongModel hetong = new HetongModel();
				hetong.setID(rs.getInt("ID"));
				hetong.setSTARTTIME(rs.getString("STARTTIME"));
				hetong.setENDTIME(rs.getString("ENDTIME"));
				hetong.setPARTYA(rs.getString("PARTYA"));
				hetong.setPARTYB(rs.getString("PARTYB"));
				hetong.setCONTRACTNAME(rs.getString("CONTRACTNAME"));
				hetong.setPROJECTAMONNT(rs.getString("PROJECTAMONNT"));
				hetong.setCONTRACTDETAIL(rs.getString("CONTRACTDETAIL"));
				al.add(hetong);
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

		System.out.println(al.size());
		return al;
	}

	// 查询功能的实现
	public ArrayList Selectoff(String nall) {
		ArrayList<HetongModel> al = new ArrayList<HetongModel>();
		String sql = "select * from TBL_HETONG_SHENPI WHERE PARTYB LIKE '%"
				+ nall + "%'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				HetongModel hetong = new HetongModel();
				hetong.setID(rs.getInt("ID"));
				hetong.setSTARTTIME(rs.getString("STARTTIME"));
				hetong.setENDTIME(rs.getString("ENDTIME"));
				hetong.setPARTYA(rs.getString("PARTYA"));
				hetong.setPARTYB(rs.getString("PARTYB"));
				hetong.setCONTRACTNAME(rs.getString("CONTRACTNAME"));
				hetong.setPROJECTAMONNT(rs.getString("PROJECTAMONNT"));
				al.add(hetong);
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

		System.out.println(al.size());
		return al;
	}

	public ArrayList Selectoff1(String nall) {
		ArrayList<HetongModel> al = new ArrayList<HetongModel>();
		String sql = "select * from TBL_HETONG_SHENPI WHERE PARTYA LIKE '%"
				+ nall + "%'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				HetongModel hetong = new HetongModel();
				hetong.setID(rs.getInt("ID"));
				hetong.setSTARTTIME(rs.getString("STARTTIME"));
				hetong.setENDTIME(rs.getString("ENDTIME"));
				hetong.setPARTYA(rs.getString("PARTYA"));
				hetong.setPARTYB(rs.getString("PARTYB"));
				hetong.setCONTRACTNAME(rs.getString("CONTRACTNAME"));
				hetong.setPROJECTAMONNT(rs.getString("PROJECTAMONNT"));
				al.add(hetong);
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

	public ArrayList Selectoff2(String nall) {
		ArrayList al = null;
		String sql = "select * from TBL_HETONG_SHENPI WHERE  CONTRACTNAME='"
				+ nall + "';";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList();
			while (rs.next()) {
				HetongModel hetong = new HetongModel();
				hetong.setID(rs.getInt("ID"));
				hetong.setSTARTTIME(rs.getString("STARTTIME"));
				hetong.setENDTIME(rs.getString("ENDTIME"));
				hetong.setPARTYA(rs.getString("PARTYA"));
				hetong.setPARTYB(rs.getString("PARTYB"));
				hetong.setCONTRACTNAME(rs.getString("CONTRACTNAME"));
				hetong.setPROJECTAMONNT(rs.getString("PROJECTAMONNT"));
				al.add(hetong);
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

		System.out.println(al.size());
		return al;
	}

	// 进行增加
	public boolean increase(ArrayList<HetongModel> as) {
		boolean flag = false;
		String st = null, et = null, pa = null, pb = null, cn = null, je = null, cl = null;
		for (int i = 0; i < as.size(); i++) {
			st = as.get(i).getSTARTTIME();
			et = as.get(i).getENDTIME();
			pa = as.get(i).getPARTYA();
			pb = as.get(i).getPARTYB();
			cn = as.get(i).getCONTRACTNAME();
			je = as.get(i).getPROJECTAMONNT();
			cl = as.get(i).getCONTRACTDETAIL();
		}
		String sql = "INSERT INTO TBL_HETONG_SHENPI (ID,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL) VALUES (XL_HETONG_GUANLI.nextval,'"
				+ st
				+ "','"
				+ et
				+ "','"
				+ pa
				+ "','"
				+ pb
				+ "','"
				+ cn
				+ "','"
				+ je
				+ "','" + cl + "')";
		System.out.println("添加合同："+sql);
		DataBase db = new DataBase();

		try {
			db.connectDb();
			int i = db.update(sql);
			db.commit();
			if ( i > 0) {
				flag = true;
			}

		} catch (Exception de) {
			de.printStackTrace();
		}

		return flag;
	}
	//进行修改
	public boolean upcrease(ArrayList<HetongModel> as,int id) {
		boolean flag = false;
		String st = null, et = null, pa = null, pb = null, cn = null, je = null, cl = null;
		for (int i = 0; i < as.size(); i++) {
			st = as.get(i).getSTARTTIME();
			et = as.get(i).getENDTIME();
			pa = as.get(i).getPARTYA();
			pb = as.get(i).getPARTYB();
			cn = as.get(i).getCONTRACTNAME();
			je = as.get(i).getPROJECTAMONNT();
			cl = as.get(i).getCONTRACTDETAIL();
		}
		String sql = "UPDATE  TBL_HETONG_SHENPI SET STARTTIME='"+st+"',ENDTIME='"+et+"',PARTYA='"+pa+"',PARTYB='"+pb+
					"',CONTRACTNAME='"+cn+"',PROJECTAMONNT='"+je+"',CONTRACTDETAIL='"+cl+"'where id='"+id+"'";;
		String sqltwo = "UPDATE  TBL_HETONG_GUANLI SET STARTTIME='"+st+"',ENDTIME='"+et+"',PARTYA='"+pa+"',PARTYB='"+pb+
					"',CONTRACTNAME='"+cn+"',PROJECTAMONNT='"+je+"',CONTRACTDETAIL='"+cl+"'where id='"+id+"'";;
		System.out.println("修改合同："+sql);
		DataBase db = new DataBase();

		try {
			db.connectDb();
			int i = db.update(sql);
			int itwo = db.update(sqltwo);
			db.commit();
			if (i > 0&& itwo>0) {
				flag = true;
			}

		} catch (Exception de) {
			de.printStackTrace();
		}

		return flag;
	}
}
