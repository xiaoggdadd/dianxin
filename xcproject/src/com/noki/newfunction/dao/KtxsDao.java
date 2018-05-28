package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.util.CTime;
import com.noki.zdqxkz.javabean.XxxgBean;

public class KtxsDao  extends TimerTask{
	public KtxsDao() {
    }
	   	private String nowtime = "";
	    private String yaerMonth = "";
	    private String nowDay = "";
	    private String nowHour = "";
	    private String preSday = "";// 日期
	    private String maxDate = "";
	    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
	            "yyyy-MM-dd");
	// 暂估流程 市级2审核 通过
	public synchronized int sjeshtg(String id, String loginName) {
		int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		StringBuffer sql = new StringBuffer();
		// sql.append("update CBZDXX c set c.shijxf='0',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
		sql.append(" UPDATE ZGSJ S SET S.SJESHZT = '1', SJESHR = '" + loginName
				+ "', S.SJESHSJ =sysdate  WHERE S.ID IN (" + id + ")");

		DataBase db = new DataBase();
		System.out.println("暂估流程市级2审核通过：" + sql.toString());
		try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	// 月份系数 查询
	public synchronized List<Ktxs> getyfxs() {
		List<Ktxs> list = new ArrayList<Ktxs>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			// fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT Y.ID,Y.SHICODE,Y.SHINAME,Y.YMONTH,Y.EMONTH,Y.SMONTH,Y.SIMONTH,Y.WMONTH,Y.LMONTH,"
					+ "Y.QMONTH,Y.BMONTH,Y.JMONTH,Y.SHIMONTH,Y.SYMONTH,Y.SEMONTH,Y.YFBZW FROM YFXS Y ";
			System.out.println("月份系数查询语句：" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			// Query query = new Query();
			// list = query.query(rs);
			while (rs.next()) {
				Ktxs formbean = new Ktxs();
				formbean.setYfxsid(rs.getString(1) != null ? rs.getString(1)
						: "");
				formbean.setShicode(rs.getString(2) != null ? rs.getString(2)
						: "");
				formbean.setShiname(rs.getString(3) != null ? rs.getString(3)
						: "");
				formbean.setYmonth(rs.getString(4) != null ? rs.getString(4)
						: "");
				formbean.setEmonth(rs.getString(5) != null ? rs.getString(5)
						: "");
				formbean.setSmonth(rs.getString(6) != null ? rs.getString(6)
						: "");
				formbean.setSimonth(rs.getString(7) != null ? rs.getString(7)
						: "");
				formbean.setWmonth(rs.getString(8) != null ? rs.getString(8)
						: "");
				formbean.setLmonth(rs.getString(9) != null ? rs.getString(9)
						: "");
				formbean.setQmonth(rs.getString(10) != null ? rs.getString(10)
						: "");
				formbean.setBmonth(rs.getString(11) != null ? rs.getString(11)
						: "");
				formbean.setJmonth(rs.getString(12) != null ? rs.getString(12)
						: "");
				formbean.setShimonth(rs.getString(13) != null ? rs
						.getString(13) : "");
				formbean.setSymonth(rs.getString(14) != null ? rs.getString(14)
						: "");
				formbean.setSemonth(rs.getString(15) != null ? rs.getString(15)
						: "");
				formbean.setYfbzw(rs.getString(16) != null ? rs.getString(16)
						: "");

				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println(list.toString() + "------------------");
		return list;
	}

	// 空调系数 查询
	public synchronized List<Ktxs> getktxs() {
		List<Ktxs> list = new ArrayList<Ktxs>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			// fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,"
					+ "K.IDCJFKTXS,K.SJBZW,K.YYWDKTXS FROM KTXS K ";
			//System.out.println("空调系数查询语句：" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			// Query query = new Query();
			// list = query.query(rs);
			while (rs.next()) {
				Ktxs formbean = new Ktxs();
				formbean.setKtxsid(rs.getString(1) != null ? rs.getString(1)
						: "");
				formbean.setKszlfh(rs.getString(2) != null ? rs.getString(2)
						: "");
				formbean.setJszlfh(rs.getString(3) != null ? rs.getString(3)
						: "");
				formbean.setJzktxs(rs.getString(4) != null ? rs.getString(4)
						: "");
				formbean.setJrwktxs(rs.getString(5) != null ? rs.getString(5)
						: "");
				formbean.setXzzjktxs(rs.getString(6) != null ? rs.getString(6)
						: "");
				formbean.setJyjfktxs(rs.getString(7) != null ? rs.getString(7)
						: "");
				formbean.setQtktxs(rs.getString(8) != null ? rs.getString(8)
						: "");
				formbean.setIdcjfktxs(rs.getString(9) != null ? rs.getString(9)
						: "");
				formbean.setSjbzw(rs.getString(10) != null ? rs.getString(10)
						: "");
				formbean.setYywdktxs(rs.getString(11)!=null?rs.getString(11):"");

				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println(list.toString() + "------------------");
		return list;
	}

	// 房屋系数 查询
	public synchronized List<Ktxs> getfwxs() {
		List<Ktxs> list = new ArrayList<Ktxs>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			// fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT F.ID,F.YFLXCODE,F.FXXS,F.JCXS,F.SJBZW,F.YFNAME FROM FWXS F ";
			System.out.println("房屋系数查询语句：" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			// Query query = new Query();
			// list = query.query(rs);
			while (rs.next()) {
				Ktxs formbean = new Ktxs();
				formbean.setFwxsid(rs.getString(1) != null ? rs.getString(1)
						: "");
				formbean.setFwlxcode(rs.getString(2) != null ? rs.getString(2)
						: "");
				formbean
						.setFxxs(rs.getString(3) != null ? rs.getString(3) : "");
				formbean
						.setJcxs(rs.getString(4) != null ? rs.getString(4) : "");
				formbean.setFwsjbzw(rs.getString(5) != null ? rs.getString(5)
						: "");
				formbean.setYfname(rs.getString(6) != null ? rs.getString(6)
						: "");
				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println(list.toString() + "------------------");
		return list;
	}
	// 信息修改申请 交直流系数 查询
	public synchronized List<XxxgBean> xxxgch() {
		List<XxxgBean> list = new ArrayList<XxxgBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			// fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT F.ZDSX,F.ZDSXNAME,F.ZLFH,F.JLFH FROM  XXXG F ";
			System.out.println("房屋系数查询语句：" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			// Query query = new Query();
			// list = query.query(rs);
			while (rs.next()) {
				XxxgBean formbean = new XxxgBean();
				formbean.setZdsxcd(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdsxnm(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setZlfh(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setJlfh(rs.getString(4)!=null?rs.getString(4):"");
				
				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println(list.toString() + "------------------");
		return list;
	}
	public synchronized int UpdateKtxs1(String jn1, String jn2, String jn3,
			String jn4, String jn5, String jn6, String jn7, String jn8,
			String jn9, String jn10, String jn11, String jn12, String qd1,
			String qd2, String qd3, String qd4, String qd5, String qd6,
			String qd7, String qd8, String qd9, String qd10, String qd11,
			String qd12, String zb1, String zb2, String zb3, String zb4,
			String zb5, String zb6, String zb7, String zb8, String zb9,
			String zb10, String zb11, String zb12, String zz1, String zz2,
			String zz3, String zz4, String zz5, String zz6, String zz7,
			String zz8, String zz9, String zz10, String zz11, String zz12,
			String dy1, String dy2, String dy3, String dy4, String dy5,
			String dy6, String dy7, String dy8, String dy9, String dy10,
			String dy11, String dy12, String yt1, String yt2, String yt3,
			String yt4, String yt5, String yt6, String yt7, String yt8,
			String yt9, String yt10, String yt11, String yt12, String wf1,
			String wf2, String wf3, String wf4, String wf5, String wf6,
			String wf7, String wf8, String wf9, String wf10, String wf11,
			String wf12, String Jn1, String Jn2, String Jn3, String Jn4,
			String Jn5, String Jn6, String Jn7, String Jn8, String Jn9,
			String Jn10, String Jn11, String Jn12, String ta1, String ta2,
			String ta3, String ta4, String ta5, String ta6, String ta7,
			String ta8, String ta9, String ta10, String ta11, String ta12,
			String wh1, String wh2, String wh3, String wh4, String wh5,
			String wh6, String wh7, String wh8, String wh9, String wh10,
			String wh11, String wh12, String rz1, String rz2, String rz3,
			String rz4, String rz5, String rz6, String rz7, String rz8,
			String rz9, String rz10, String rz11, String rz12, String lw1,
			String lw2, String lw3, String lw4, String lw5, String lw6,
			String lw7, String lw8, String lw9, String lw10, String lw11,
			String lw12, String ly1, String ly2, String ly3, String ly4,
			String ly5, String ly6, String ly7, String ly8, String ly9,
			String ly10, String ly11, String ly12, String dz1, String dz2,
			String dz3, String dz4, String dz5, String dz6, String dz7,
			String dz8, String dz9, String dz10, String dz11, String dz12,
			String lc1, String lc2, String lc3, String lc4, String lc5,
			String lc6, String lc7, String lc8, String lc9, String lc10,
			String lc11, String lc12, String bz1, String bz2, String bz3,
			String bz4, String bz5, String bz6, String bz7, String bz8,
			String bz9, String bz10, String bz11, String bz12, String hz1,
			String hz2, String hz3, String hz4, String hz5, String hz6,
			String hz7, String hz8, String hz9, String hz10, String hz11,
			String hz12,String lrr) {
		int msg = 0;
		String sql1 = "update yfxs y set y.ymonth='" + jn1 + "',y.emonth='"
				+ jn2 + "',y.smonth='" + jn3 + "',y.simonth='" + jn4
				+ "',y.wmonth='" + jn5 + "',y.lmonth='" + jn6 + "',y.qmonth='"
				+ jn7 + "',y.bmonth='" + jn8 + "',y.jmonth='" + jn9
				+ "',y.shimonth='" + jn10 + "',y.symonth='" + jn11
				+ "',y.semonth='" + jn12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='1'";
		String sql2 = "update yfxs y set y.ymonth='" + qd1 + "',y.emonth='"
				+ qd2 + "',y.smonth='" + qd3 + "',y.simonth='" + qd4
				+ "',y.wmonth='" + qd5 + "',y.lmonth='" + qd6 + "',y.qmonth='"
				+ qd7 + "',y.bmonth='" + qd8 + "',y.jmonth='" + qd9
				+ "',y.shimonth='" + qd10 + "',y.symonth='" + qd11
				+ "',y.semonth='" + qd12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='2'";
		String sql3 = "update yfxs y set y.ymonth='" + zb1 + "',y.emonth='"
				+ zb2 + "',y.smonth='" + zb3 + "',y.simonth='" + zb4
				+ "',y.wmonth='" + zb5 + "',y.lmonth='" + zb6 + "',y.qmonth='"
				+ zb7 + "',y.bmonth='" + zb8 + "',y.jmonth='" + zb9
				+ "',y.shimonth='" + zb10 + "',y.symonth='" + zb11
				+ "',y.semonth='" + zb12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='3'";
		String sql4 = "update yfxs y set y.ymonth='" + zz1 + "',y.emonth='"
				+ zz2 + "',y.smonth='" + zz3 + "',y.simonth='" + zz4
				+ "',y.wmonth='" + zz5 + "',y.lmonth='" + zz6 + "',y.qmonth='"
				+ zz7 + "',y.bmonth='" + zz8 + "',y.jmonth='" + zz9
				+ "',y.shimonth='" + zz10 + "',y.symonth='" + zz11
				+ "',y.semonth='" + zz12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='4'";
		String sql5 = "update yfxs y set y.ymonth='" + dy1 + "',y.emonth='"
				+ dy2 + "',y.smonth='" + dy3 + "',y.simonth='" + dy4
				+ "',y.wmonth='" + dy5 + "',y.lmonth='" + dy6 + "',y.qmonth='"
				+ dy7 + "',y.bmonth='" + dy8 + "',y.jmonth='" + dy9
				+ "',y.shimonth='" + dy10 + "',y.symonth='" + dy11
				+ "',y.semonth='" + dy12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='5'";
		String sql6 = "update yfxs y set y.ymonth='" + yt1 + "',y.emonth='"
				+ yt2 + "',y.smonth='" + yt3 + "',y.simonth='" + yt4
				+ "',y.wmonth='" + yt5 + "',y.lmonth='" + yt6 + "',y.qmonth='"
				+ yt7 + "',y.bmonth='" + yt8 + "',y.jmonth='" + yt9
				+ "',y.shimonth='" + yt10 + "',y.symonth='" + yt11
				+ "',y.semonth='" + yt12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='6'";
		String sql7 = "update yfxs y set y.ymonth='" + wf1 + "',y.emonth='"
				+ wf2 + "',y.smonth='" + wf3 + "',y.simonth='" + wf4
				+ "',y.wmonth='" + wf5 + "',y.lmonth='" + wf6 + "',y.qmonth='"
				+ wf7 + "',y.bmonth='" + wf8 + "',y.jmonth='" + wf9
				+ "',y.shimonth='" + wf10 + "',y.symonth='" + wf11
				+ "',y.semonth='" + wf12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='7'";
		String sql8 = "update yfxs y set y.ymonth='" + Jn1 + "',y.emonth='"
				+ Jn2 + "',y.smonth='" + Jn3 + "',y.simonth='" + Jn4
				+ "',y.wmonth='" + Jn5 + "',y.lmonth='" + Jn6 + "',y.qmonth='"
				+ Jn7 + "',y.bmonth='" + Jn8 + "',y.jmonth='" + Jn9
				+ "',y.shimonth='" + Jn10 + "',y.symonth='" + Jn11
				+ "',y.semonth='" + Jn12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='8'";
		String sql9 = "update yfxs y set y.ymonth='" + ta1 + "',y.emonth='"
				+ ta2 + "',y.smonth='" + ta3 + "',y.simonth='" + ta4
				+ "',y.wmonth='" + ta5 + "',y.lmonth='" + ta6 + "',y.qmonth='"
				+ ta7 + "',y.bmonth='" + ta8 + "',y.jmonth='" + ta9
				+ "',y.shimonth='" + ta10 + "',y.symonth='" + ta11
				+ "',y.semonth='" + ta12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='9'";
		String sql10 = "update yfxs y set y.ymonth='" + wh1 + "',y.emonth='"
				+ wh2 + "',y.smonth='" + wh3 + "',y.simonth='" + wh4
				+ "',y.wmonth='" + wh5 + "',y.lmonth='" + wh6 + "',y.qmonth='"
				+ wh7 + "',y.bmonth='" + wh8 + "',y.jmonth='" + wh9
				+ "',y.shimonth='" + wh10 + "',y.symonth='" + wh11
				+ "',y.semonth='" + wh12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='10'";
		String sql11 = "update yfxs y set y.ymonth='" + rz1 + "',y.emonth='"
				+ rz2 + "',y.smonth='" + rz3 + "',y.simonth='" + rz4
				+ "',y.wmonth='" + rz5 + "',y.lmonth='" + rz6 + "',y.qmonth='"
				+ rz7 + "',y.bmonth='" + rz8 + "',y.jmonth='" + rz9
				+ "',y.shimonth='" + rz10 + "',y.symonth='" + rz11
				+ "',y.semonth='" + rz12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='11'";
		String sql12 = "update yfxs y set y.ymonth='" + lw1 + "',y.emonth='"
				+ lw2 + "',y.smonth='" + lw3 + "',y.simonth='" + lw4
				+ "',y.wmonth='" + lw5 + "',y.lmonth='" + lw6 + "',y.qmonth='"
				+ lw7 + "',y.bmonth='" + lw8 + "',y.jmonth='" + lw9
				+ "',y.shimonth='" + lw10 + "',y.symonth='" + lw11
				+ "',y.semonth='" + lw12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='12'";
		String sql13 = "update yfxs y set y.ymonth='" + ly1 + "',y.emonth='"
				+ ly2 + "',y.smonth='" + ly3 + "',y.simonth='" + ly4
				+ "',y.wmonth='" + ly5 + "',y.lmonth='" + ly6 + "',y.qmonth='"
				+ ly7 + "',y.bmonth='" + ly8 + "',y.jmonth='" + ly9
				+ "',y.shimonth='" + ly10 + "',y.symonth='" + ly11
				+ "',y.semonth='" + ly12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='13'";
		String sql14 = "update yfxs y set y.ymonth='" + dz1 + "',y.emonth='"
				+ dz2 + "',y.smonth='" + dz3 + "',y.simonth='" + dz4
				+ "',y.wmonth='" + dz5 + "',y.lmonth='" + dz6 + "',y.qmonth='"
				+ dz7 + "',y.bmonth='" + dz8 + "',y.jmonth='" + dz9
				+ "',y.shimonth='" + dz10 + "',y.symonth='" + dz11
				+ "',y.semonth='" + dz12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='14'";
		String sql15 = "update yfxs y set y.ymonth='" + lc1 + "',y.emonth='"
				+ lc2 + "',y.smonth='" + lc3 + "',y.simonth='" + lc4
				+ "',y.wmonth='" + lc5 + "',y.lmonth='" + lc6 + "',y.qmonth='"
				+ lc7 + "',y.bmonth='" + lc8 + "',y.jmonth='" + lc9
				+ "',y.shimonth='" + lc10 + "',y.symonth='" + lc11
				+ "',y.semonth='" + lc12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='15'";
		String sql16 = "update yfxs y set y.ymonth='" + bz1 + "',y.emonth='"
				+ bz2 + "',y.smonth='" + bz3 + "',y.simonth='" + bz4
				+ "',y.wmonth='" + bz5 + "',y.lmonth='" + bz6 + "',y.qmonth='"
				+ bz7 + "',y.bmonth='" + bz8 + "',y.jmonth='" + bz9
				+ "',y.shimonth='" + bz10 + "',y.symonth='" + bz11
				+ "',y.semonth='" + bz12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='16'";
		String sql17 = "update yfxs y set y.ymonth='" + hz1 + "',y.emonth='"
				+ hz2 + "',y.smonth='" + hz3 + "',y.simonth='" + hz4
				+ "',y.wmonth='" + hz5 + "',y.lmonth='" + hz6 + "',y.qmonth='"
				+ hz7 + "',y.bmonth='" + hz8 + "',y.jmonth='" + hz9
				+ "',y.shimonth='" + hz10 + "',y.symonth='" + hz11
				+ "',y.semonth='" + hz12 + "',y.lrr='"+lrr+"',y.lrsj=sysdate where y.yfbzw='17'";

		DataBase db = new DataBase();
		System.out.println("济南修改：" + sql1.toString());
		System.out.println("青岛修改：" + sql2.toString());
		System.out.println("淄博修改：" + sql3.toString());
		System.out.println("枣庄修改：" + sql4.toString());
		System.out.println("东营修改：" + sql5.toString());
		System.out.println("烟台修改：" + sql6.toString());
		System.out.println("潍坊修改：" + sql7.toString());
		System.out.println("济宁修改：" + sql8.toString());
		System.out.println("泰安修改：" + sql9.toString());
		System.out.println("威海修改：" + sql10.toString());
		System.out.println("日照修改：" + sql11.toString());
		System.out.println("莱芜修改：" + sql12.toString());
		System.out.println("临沂修改：" + sql13.toString());
		System.out.println("德州修改：" + sql14.toString());
		System.out.println("聊城修改：" + sql15.toString());
		System.out.println("滨州修改：" + sql16.toString());
		System.out.println("菏泽修改：" + sql17.toString());
		try {
			db.connectDb();
			db.update(sql1.toString());
			db.update(sql2.toString());
			db.update(sql3.toString());
			db.update(sql4.toString());
			db.update(sql5.toString());
			db.update(sql6.toString());
			db.update(sql7.toString());
			db.update(sql8.toString());
			db.update(sql9.toString());
			db.update(sql10.toString());
			db.update(sql11.toString());
			db.update(sql12.toString());
			db.update(sql13.toString());
			db.update(sql14.toString());
			db.update(sql15.toString());
			db.update(sql16.toString());
			db.update(sql17.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	public synchronized int UpdateKtxs2(int s,String kszlfh1, String jszlfh1,
			String jz1, String jrw1, String xzzj1, String jyjf1, String qt1,
			String idcjf1, String kszlfh2, String jszlfh2, String jz2,
			String jrw2, String xzzj2, String jyjf2, String qt2, String idcjf2,
			String kszlfh3, String jszlfh3, String jz3, String jrw3,
			String xzzj3, String jyjf3, String qt3, String idcjf3,
			String kszlfh4, String jszlfh4, String jz4, String jrw4,
			String xzzj4, String jyjf4, String qt4, String idcjf4,
			String kszlfh5, String jszlfh5, String jz5, String jrw5,
			String xzzj5, String jyjf5, String qt5, String idcjf5,
			String kszlfh6, String jz6, String jrw6, String xzzj6,
			String jyjf6, String qt6, String idcjf6, String xs1, String jcxs1,
			String xs2, String jcxs2, String xs3, String jcxs3,String lrr) {
		int msg = 0;
		String sql18 = "update ktxs t set t.kszlfh='" + kszlfh1
				+ "',t.jszlsh='" + jszlfh1 + "',t.jzktxs='" + jz1
				+ "',jrwktxs='" + jrw1 + "',t.xzzjktxs='" + xzzj1
				+ "',t.jyjfktxs='" + jyjf1 + "',t.qtktxs='" + qt1
				+ "',t.idcjfktxs='" + idcjf1 + "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='1'";
		String sql19 = "update ktxs t set t.kszlfh='" + kszlfh2
				+ "',t.jszlsh='" + jszlfh2 + "',t.jzktxs='" + jz2
				+ "',jrwktxs='" + jrw2 + "',t.xzzjktxs='" + xzzj2
				+ "',t.jyjfktxs='" + jyjf2 + "',t.qtktxs='" + qt2
				+ "',t.idcjfktxs='" + idcjf2 + "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='2'";
		String sql20 = "update ktxs t set t.kszlfh='" + kszlfh3
				+ "',t.jszlsh='" + jszlfh3 + "',t.jzktxs='" + jz3
				+ "',jrwktxs='" + jrw3 + "',t.xzzjktxs='" + xzzj3
				+ "',t.jyjfktxs='" + jyjf3 + "',t.qtktxs='" + qt3
				+ "',t.idcjfktxs='" + idcjf3 + "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='3'";
		String sql21 = "update ktxs t set t.kszlfh='" + kszlfh4
				+ "',t.jszlsh='" + jszlfh4 + "',t.jzktxs='" + jz4
				+ "',jrwktxs='" + jrw4 + "',t.xzzjktxs='" + xzzj4
				+ "',t.jyjfktxs='" + jyjf4 + "',t.qtktxs='" + qt4
				+ "',t.idcjfktxs='" + idcjf4 + "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='4'";
		String sql22 = "update ktxs t set t.kszlfh='" + kszlfh5
				+ "',t.jszlsh='" + jszlfh5 + "',t.jzktxs='" + jz5
				+ "',jrwktxs='" + jrw5 + "',t.xzzjktxs='" + xzzj5
				+ "',t.jyjfktxs='" + jyjf5 + "',t.qtktxs='" + qt5
				+ "',t.idcjfktxs='" + idcjf5 + "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='5'";
		String sql23 = "update ktxs t set t.kszlfh='" + kszlfh6
				+ "',t.jzktxs='" + jz6 + "',jrwktxs='" + jrw6
				+ "',t.xzzjktxs='" + xzzj6 + "',t.jyjfktxs='" + jyjf6
				+ "',t.qtktxs='" + qt6 + "',t.idcjfktxs='" + idcjf6
				+ "',t.lrr='"+lrr+"',t.lrsj=sysdate where t.sjbzw='6'";

		String sql24 = "update fwxs f set f.fxxs='" + xs1 + "',f.jcxs='"
				+ jcxs1 + "',f.lrr='"+lrr+"',f.lrsj=sysdate where sjbzw='1'";
		String sql25 = "update fwxs f set f.fxxs='" + xs2 + "',f.jcxs='"
				+ jcxs2 + "',f.lrr='"+lrr+"',f.lrsj=sysdate where sjbzw='2'";
		String sql26 = "update fwxs f set f.fxxs='" + xs3 + "',f.jcxs='"
				+ jcxs3 + "',f.lrr='"+lrr+"',f.lrsj=sysdate where sjbzw='3'";

		DataBase db = new DataBase();

		System.out.println("0-7A修改：" + sql18.toString());
		System.out.println("8-20A修改：" + sql19.toString());
		System.out.println("21-40A修改：" + sql20.toString());
		System.out.println("41-60A修改：" + sql21.toString());
		System.out.println("61-100A修改：" + sql22.toString());
		System.out.println("100A以上修改：" + sql23.toString());
		System.out.println("修改1：" + sql24.toString());
		System.out.println("修改2：" + sql25.toString());
		System.out.println("修改3：" + sql26.toString());
		try {
			db.connectDb();
			if(s==1){
			db.update(sql18.toString());
			db.update(sql19.toString());
			db.update(sql20.toString());
			db.update(sql21.toString());
			db.update(sql22.toString());
			db.update(sql23.toString());
			db.update(sql24.toString());
			db.update(sql25.toString());
			db.update(sql26.toString());
			db.commit();
			msg = 1;}else{
				msg=0;
			}
			
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	public synchronized List<Ktxs> getyfxs1(String yf) {
		List<Ktxs> list = new ArrayList<Ktxs>();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			sql = "SELECT Y.ID,Y.SHICODE,Y.SHINAME,Y." + yf
					+ ",Y.YFBZW FROM YFXS Y ";
			System.out.println("固定月份系数查询语句：" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				Ktxs formbean = new Ktxs();
				formbean.setYfxsid(rs.getString(1) != null ? rs.getString(1)
						: "");
				formbean.setShicode(rs.getString(2) != null ? rs.getString(2)
						: "");
				formbean.setShiname(rs.getString(3) != null ? rs.getString(3)
						: "");
				formbean.setYmonth(rs.getString(4) != null ? rs.getString(4)
						: "");
				formbean.setYfbzw(rs.getString(5) != null ? rs.getString(5)
						: "");

				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return list;
	}
	

	public synchronized int UpKtxs(String kszlfh1, String jszlfh1,
			String kszlfh2, String jszlfh2, String kszlfh3, String jszlfh3,
			String kszlfh4, String jszlfh4, String kszlfh5, String jszlfh5,
			String kszlfh6, String jz1, String jz2, String jz3, String jz4,
			String jz5, String jz6,String jrw1,String jrw2,String jrw3,String jrw4,String jrw5,String jrw6,
			String xzzj1,String xzzj2,String xzzj3,String xzzj4,String xzzj5,String xzzj6,
			String jyjf1,String jyjf2,String jyjf3,String jyjf4,String jyjf5,String jyjf6,
			String qt1,String qt2,String qt3,String qt4,String qt5,String qt6,
			String idcjf1,String idcjf2,String idcjf3,String idcjf4,String idcjf5,String idcjf6,
			String xs1,String jcxs1,String xs2,String jcxs2,String xs3,String jcxs3,
			String jnxs,String qdxs,String zbxs,String zzxs,String dyxs,String ytxs,String wfxs,String Jnxs,String taxs,String whxs,
			String rzxs,String lwxs,String lyxs,String dzxs,String lcxs,String bzxs,String hzxs) {
		
		CTime ctime = new CTime(); 
		String inserttime = ctime.formatWholeDate(new Date());
		String s=inserttime.substring(5,7);
		int msg = 0;//sql1 1-8月为严格
		String sq1 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
				+ "(CASE WHEN aa.ZLFH >="
				+ kszlfh1
				+ "AND aa.ZLFH <"
				+ kszlfh2
				+ "THEN "
				+ jz1
				+ " "
				+ "WHEN aa.ZLFH >="
				+ kszlfh2
				+ " AND aa.ZLFH < "
				+ kszlfh3
				+ " THEN "+jz2+" "
				+ "WHEN aa.ZLFH >= "
				+ kszlfh3
				+ " AND aa.ZLFH < "
				+ kszlfh4
				+ " THEN "+jz3+" "
				+ " WHEN aa.ZLFH >= "
				+ kszlfh4
				+ " AND aa.ZLFH < "
				+ kszlfh5
				+ " THEN "+jz4+" "
				+ "WHEN aa.ZLFH >= "
				+ kszlfh5
				+ " AND aa.ZLFH < "
				+ kszlfh6
				+ " THEN "+jz5+" "
				+ "WHEN aa.ZLFH >= "
				+ kszlfh6
				+ " THEN "+jz6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
				+ "FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
				+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
				+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
				+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
				+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
				+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
				+ "WHERE z.PROPERTY='zdsx02'";
		//9-12严格
		String sq2 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >="
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ jz1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ jszlfh1
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+jz2+" "
			+ "WHEN aa.ZLFH > "
			+ jszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+jz3+" "
			+ " WHEN aa.ZLFH > "
			+ jszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+jz4+" "
			+ "WHEN aa.ZLFH > "
			+ jszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " THEN "+jz5+" "
			+ "WHEN aa.ZLFH > "
			+ jszlfh5
			+ " THEN "+jz6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
			+ "WHERE z.PROPERTY='zdsx02'";
		
		
		
		String sql2 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >= "
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ jrw1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ kszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+jrw2+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+jrw3+" "
			+ " WHEN aa.ZLFH > "
			+ kszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+jrw4+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh5
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " THEN "+jrw5+" "
			+ "when aa.ZLFH > "
			+ kszlfh6
			+ " THEN "+jrw6+" ELSE 0 END) zlfhxs FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "z.FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.yfxs=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select zz.qsdbdl from zhandian zz where zz.id=z.id ) "
			+ "WHERE z.PROPERTY='zdsx05'";
		String sql3 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >="
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ xzzj1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ kszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+xzzj2+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+xzzj3+" "
			+ " WHEN aa.ZLFH > "
			+ kszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+xzzj4+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh5
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " THEN "+xzzj5+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh6
			+ " THEN "+xzzj6+" ELSE 0 END) zlfhxs FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "z.FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID) "
			+ "WHERE z.PROPERTY='zdsx06' or z.PROPERTY='zdsx03'";
		String sql4 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >="
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ jyjf1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ kszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+jyjf2+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+jyjf3+" "
			+ " WHEN aa.ZLFH > "
			+ kszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+jyjf4+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh5
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " then "+jyjf5+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh6
			+ " THEN "+jyjf6+" ELSE 0 END) zlfhxs FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "z.FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID) "
			+ "WHERE z.PROPERTY='zdsx01'";
		
		String sql5 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >="
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ qt1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ kszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+qt2+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+qt3+" "
			+ " WHEN aa.ZLFH > "
			+ kszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+qt4+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh5
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " THEN "+qt5+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh6
			+ " THEN "+qt6+" ELSE 0 END) zlfhxs FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "z.FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID) "
			+ "WHERE z.PROPERTY='zdsx04'";
		String sql6 = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
			+ "(CASE WHEN aa.ZLFH >="
			+ kszlfh1
			+ "AND aa.ZLFH <="
			+ jszlfh1
			+ "THEN "
			+ idcjf1
			+ " "
			+ "WHEN aa.ZLFH >"
			+ kszlfh2
			+ " AND aa.ZLFH <= "
			+ jszlfh2
			+ " THEN "+idcjf2+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh3
			+ " AND aa.ZLFH <= "
			+ jszlfh3
			+ " THEN "+idcjf3+" "
			+ " WHEN aa.ZLFH > "
			+ kszlfh4
			+ " AND aa.ZLFH <= "
			+ jszlfh4
			+ " THEN "+idcjf4+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh5
			+ " AND aa.ZLFH <= "
			+ jszlfh5
			+ " THEN "+idcjf5+" "
			+ "WHEN aa.ZLFH > "
			+ kszlfh6
			+ " THEN "+idcjf6+" ELSE 0 END) zlfhxs FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
			+ "z.FWXS=(select decode(aa.yflx,null, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
			+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
			+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
			+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
			+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
			+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID) "
			+ "WHERE z.PROPERTY='zdsx07'";
		
		//String sql11="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs*zz.ktxs+zz.fwxs) where zz.property = 'zdsx02' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";
	    //String sql11="update zhandian zz set zz.qsdbdl =  zz.scb * (zz.jcxs + zz.yfxs*zz.ktxs+zz.fwxs) where zz.property = 'zdsx02'";
		//String sql21="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx05' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";
		// String sql21="update zhandian zz set zz.qsdbdl =  zz.scb * (zz.jcxs + zz.yfxs*zz.ktxs) where zz.property = 'zdsx05'";
		//String sql31="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property='zdsx06' or zz.property='zdsx03' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";
		//String sql41="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx01' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";
		/*String sql51="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx04' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";
		String sql61="update zhandian zz set zz.qsdbdl =  decode(zz.qsdbdl,'',zz.edhdl,null,zz.edhdl,0,zz.edhdl,zz.qsdbdl)* (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx07' and ((zz.qsdbdl is not null and zz.qsdbdl<>0) or (zz.edhdl is not null and to_number(zz.edhdl)<>0))";*/
		String sql11="update zhandian zz set zz.qsdbdl =(case when zz.scb is not null and zz.scb <> 0 and zz.scb <> '' then zz.scb when (zz.scb is null or zz.scb=0 or zz.scb='')and (zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '') then zz.edhdl*0.9 when (zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='') and (zz.zlfh is not null or zz.zlfh<>0 or zz.zlfh<>'') then zz.zlfh*1.52 when  ((zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='')) and (zz.zlfh is null or zz.zlfh=0 or zz.zlfh='') and(zz.jlfh is not null or zz.jlfh <>0 or zz.jlfh<>'') then zz.jlfh*220*0.85/54*1.52 else 0 end )*(zz.jcxs + zz.yfxs * zz.ktxs + zz.fwxs) where zz.property = 'zdsx02'";
		String sql21="update zhandian zz set zz.qsdbdl =(case when zz.scb is not null and zz.scb <> 0 and zz.scb <> '' then zz.scb when (zz.scb is null or zz.scb=0 or zz.scb='')and (zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '') then zz.edhdl*0.9 when (zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='') and (zz.zlfh is not null or zz.zlfh<>0 or zz.zlfh<>'') then zz.zlfh*1.52 when  ((zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='')) and (zz.zlfh is null or zz.zlfh=0 or zz.zlfh='') and(zz.jlfh is not null or zz.jlfh <>0 or zz.jlfh<>'') then zz.jlfh*220*0.85/54*1.52 else 0 end )*(zz.jcxs + zz.yfxs * zz.ktxs + zz.fwxs) where zz.property = 'zdsx05'";
		String sql31="update zhandian zz set zz.qsdbdl = (case when zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '' then zz.edhdl * 0.9 when (zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is not null or zz.zlfh <> 0 or zz.zlfh <> '') then zz.zlfh * 1.52 when （(zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is null or zz.zlfh = 0 or zz.zlfh = '')） and(zz.jlfh is not null or zz.jlfh <> 0 or zz.jlfh <> '') then  zz.jlfh * 220 * 0.85 / 54 * 1.52 else 0 end) * (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx03'";
		String sql41="update zhandian zz set zz.qsdbdl = (case when zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '' then zz.edhdl * zz.xs when (zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is not null or zz.zlfh <> 0 or zz.zlfh <> '') then zz.zlfh * 1.52 when （(zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is null or zz.zlfh = 0 or zz.zlfh = '')） and(zz.jlfh is not null or zz.jlfh <> 0 or zz.jlfh <> '') then  zz.jlfh * 220 * 0.85 / 54 * 1.52 else 0 end) * (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx01'";
		String sql51="update zhandian zz set zz.qsdbdl = (case when zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '' then zz.edhdl * 0.9 when (zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is not null or zz.zlfh <> 0 or zz.zlfh <> '') then zz.zlfh * 1.52 when （(zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is null or zz.zlfh = 0 or zz.zlfh = '')） and(zz.jlfh is not null or zz.jlfh <> 0 or zz.jlfh <> '') then  zz.jlfh * 220 * 0.85 / 54 * 1.52 else 0 end) * (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx04'";
		String sql61="update zhandian zz set zz.qsdbdl = (case when zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '' then zz.edhdl * 0.9 when (zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is not null or zz.zlfh <> 0 or zz.zlfh <> '') then zz.zlfh * 1.52 when （(zz.edhdl is null or zz.edhdl = 0 or zz.edhdl = '') and (zz.zlfh is null or zz.zlfh = 0 or zz.zlfh = '')） and(zz.jlfh is not null or zz.jlfh <> 0 or zz.jlfh <> '') then  zz.jlfh * 220 * 0.85 / 54 * 1.52 else 0 end) * (zz.jcxs + zz.yfxs * zz.ktxs) where zz.property = 'zdsx07'";
		DataBase db = new DataBase();
	//	System.out.println("基站修改：" + sq1.toString());
		//System.out.println("基站能耗修改：" + sql11.toString());
		/*System.out.println("接入网修改：" + sql2.toString());
		System.out.println("接入网能耗修改：" + sql21.toString());
		System.out.println("乡镇支局修改：" + sql3.toString());
		System.out.println("乡镇支局能耗修改：" + sql31.toString());
		System.out.println("局用机房修改：" + sql4.toString());
		System.out.println("局用机房能耗修改：" + sql41.toString());
		System.out.println("其他修改：" + sql5.toString());
		System.out.println("其他能耗修改：" + sql51.toString());
		System.out.println("idc机房修改：" + sql6.toString());
		System.out.println("idc机房能耗修改：" + sql61.toString());*/
		try {
			db.connectDb();
			int i=Integer.parseInt(s);
			if(i<=8){
				System.out.println("基站修改1-8月：" + sq1.toString());
				System.out.println("基站能耗修改：" + sql11.toString());
				db.update(sq1.toString());
				db.update(sql11.toString());
			}else if(i>=9){
				System.out.println("基站修改9-12月：" + sq2.toString());
				System.out.println("基站能耗修改：" + sql11.toString());
				db.update(sq2.toString());
				db.update(sql11.toString());
			}
		
			/*db.update(sql2.toString());
			db.update(sql21.toString());
			db.update(sql3.toString());
			db.update(sql31.toString());
			db.update(sql4.toString());
			db.update(sql41.toString());
			db.update(sql5.toString());
			db.update(sql51.toString());
			db.update(sql6.toString());
			db.update(sql61.toString());*/
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}
	
//--------------------------------空调系数改为自动更新------------------------------------------------------------------------	
	 public void run() {
	    	
	    	Calendar cal=Calendar.getInstance();

	        cal.set(Calendar.DATE, 1);     //把日期设置为当月第一天

	        cal.roll(Calendar.DATE, -1);   //日期回滚一天，也就是最后一天

	        int max=cal.get(Calendar.DATE);
	        maxDate=String.valueOf(max);
	        
	        nowtime = CTime.formatDate();
	        yaerMonth =CTime.formatWholeDate().toString().substring(0, 10);
	        nowDay = nowtime.substring(6, 8);
	        nowHour = nowtime.substring(8, 10);
	        String s=yaerMonth.substring(5,7);//截取月
	        String ss=yaerMonth.substring(8,10);//截取日
	        System.out.println("MonthSum_timer run== "+CTime.formatWholeDate()+"  nowDay="+nowDay+"  maxDate"+maxDate+yaerMonth);
	       
	        String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
			String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
			String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
			String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
			
			String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "",yywd="", id1 = "", zlfh = "";
			String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "",yywd1="", id11 = "", zlfh1 = "";
			String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "",yywd2="", id12 = "", zlfh2 = "";
			String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "",yywd3="", id13 = "", zlfh3 = "";
			String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "",yywd4="", id14 = "", zlfh4 = "";
			String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "",yywd5="", id15 = "", zlfh5 = "";
			String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "",yywd6="", id16 = "", zlfh6 = "";
			
			String month = "";
			if (s.equals("01")) {
				month = "ymonth";
			} else if (s.equals("02")) {
				month = "emonth";
			} else if (s.equals("03")) {
				month = "smonth";
			} else if (s.equals("04")) {
				month = "wmonth";
			} else if (s.equals("06")) {
				month = "lmonth";
			} else if (s.equals("07")) {
				month = "qmonth";
			} else if (s.equals("08")) {
				month = "bmonth";
			} else if (s.equals("09")) {
				month = "jmonth";
			} else if (s.equals("10")) {
				month = "shimonth";
			} else if (s.equals("11")) {
				month = "symonth";
			} else if (s.equals("12")) {
				month = "semonth";
			}
			String yfxs = "", yfbz = "", jnxs = "", qdxs = "", zbxs = "", zzxs = "", dyxs = "", ytxs = "", wfxs = "", Jnxs = "", taxs = "", whxs = "";
			String rzxs = "", lwxs = "", lyxs = "", dzxs = "", lcxs = "", bzxs = "", hzxs = "";
			DecimalFormat mat = new DecimalFormat("0.00");
			//ss.equals("08")&&(Integer.parseInt(nowHour)==0)
			if (ss.equals("31")){ //每月1号0:00自动更新
	            
	            Calendar calendar = Calendar.getInstance();
	            calendar.add(calendar.DATE, -1);
	            Date perday = calendar.getTime();
	            preSday = sdfk.format(perday);
	            DataBase oracledb = new DataBase();
	          System.out.println(nowHour+"----------------------------------");
	            try {
	            	
	                oracledb.getConnection();
	                System.out.println("房屋系数"+"SELECT F.ID,F.YFLXCODE,F.FXXS,F.JCXS,F.SJBZW,F.YFNAME FROM FWXS F");
	                ResultSet rs = oracledb.queryAll("SELECT F.ID,F.YFLXCODE,F.FXXS,F.JCXS,F.SJBZW,F.YFNAME FROM FWXS F");
	               
	                while(rs.next()){
	                	fwid=rs.getString(1);
	                	fwlx=rs.getString(2);
	                	xs=rs.getString(3);
	                	jcxs=rs.getString(4);
	                	fwbzw=rs.getString(5);
	                	xs= mat.format(Double.parseDouble(xs));
	                	jcxs = mat.format(Double.parseDouble(jcxs));
						if (fwbzw.equals("1")) {
							xs1 = xs;
							jcxs1 = jcxs;
						} else if (fwbzw.equals("2")) {
							xs2 = xs;
							jcxs2 = jcxs;
						} else if (fwbzw.equals("3")) {
							xs3 = xs;
							jcxs3 = jcxs;
						}	
	                }
	                System.out.println("空调系数"+"SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW,K.YYWDKTXS FROM KTXS K ");
	                ResultSet rs1 = oracledb.queryAll("SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW,K.YYWDKTXS FROM KTXS K ");
	                
	                while(rs1.next()){
	                id1 = rs1.getString(1);
					kszlfh = rs1.getString(2);
					jszlfh = rs1.getString(3);
					jz = rs1.getString(4);
					jrw = rs1.getString(5);
					xzzj = rs1.getString(6);
					jyjf = rs1.getString(7);
					qt = rs1.getString(8);
					idcjf = rs1.getString(9);
					yywd = rs1.getString(11);
					jz = mat.format(Double.parseDouble(jz));
					jrw = mat.format(Double.parseDouble(jrw));
					xzzj = mat.format(Double.parseDouble(xzzj));
					jyjf = mat.format(Double.parseDouble(jyjf));
					qt = mat.format(Double.parseDouble(qt));
					idcjf = mat.format(Double.parseDouble(idcjf));
					if (id1.equals("1")) {
						kszlfh1 = kszlfh;
						jszlfh1 = jszlfh;
						jz1 = jz;
						jrw1 = jrw;
						xzzj1 = xzzj;
						jyjf1 = jyjf;
						qt1 = qt;
						idcjf1 = idcjf;
						yywd1=yywd;
					} else if (id1.equals("2")) {
						kszlfh2 = kszlfh;
						jszlfh2 = jszlfh;
						jz2 = jz;
						jrw2 = jrw;
						xzzj2 = xzzj;
						jyjf2 = jyjf;
						qt2 = qt;
						idcjf2 = idcjf;
						yywd2=yywd;
					} else if (id1.equals("3")) {
						kszlfh3 = kszlfh;
						jszlfh3 = jszlfh;
						jz3 = jz;
						jrw3 = jrw;
						xzzj3 = xzzj;
						jyjf3 = jyjf;
						qt3 = qt;
						idcjf3 = idcjf;
						yywd3=yywd;
					} else if (id1.equals("4")) {
						kszlfh4 = kszlfh;
						jszlfh4 = jszlfh;
						jz4 = jz;
						jrw4 = jrw;
						xzzj4 = xzzj;
						jyjf4 = jyjf;
						qt4 = qt;
						idcjf4 = idcjf;
						yywd4=yywd;
					} else if (id1.equals("5")) {
						kszlfh5 = kszlfh;
						jszlfh5 = jszlfh;
						jz5 = jz;
						jrw5 = jrw;
						xzzj5 = xzzj;
						jyjf5 = jyjf;
						qt5 = qt;
						idcjf5 = idcjf;
						yywd5=yywd;
					} else if (id1.equals("6")) {
						kszlfh6 = kszlfh;
						jz6 = jz;
						jrw6 = jrw;
						xzzj6 = xzzj;
						jyjf6 = jyjf;
						qt6 = qt;
						idcjf6 = idcjf;
						yywd6=yywd;
					}
	                }
	            	System.out.println("月份系数查询"+"SELECT Y.ID,Y.SHICODE,Y.SHINAME,Y."+month+",Y.YFBZW FROM YFXS Y ");
					 ResultSet rs2 = oracledb.queryAll("SELECT Y.ID,Y.SHICODE,Y.SHINAME,Y."+month+",Y.YFBZW FROM YFXS Y ");
				
					 while(rs2.next()){
						yfbz = rs2.getString(5);
						yfxs = rs2.getString(4);
						yfxs = mat.format(Double.parseDouble(yfxs));
						if (yfbz.equals("1")) {
							jnxs = yfxs;
						} else if (yfbz.equals("2")) {
							qdxs = yfxs;
						} else if (yfbz.equals("3")) {
							zbxs = yfxs;
						} else if (yfbz.equals("4")) {
							zzxs = yfxs;
						} else if (yfbz.equals("5")) {
							dyxs = yfxs;
						} else if (yfbz.equals("6")) {
							ytxs = yfxs;
						} else if (yfbz.equals("7")) {
							wfxs = yfxs;
						} else if (yfbz.equals("8")) {
							Jnxs = yfxs;
						} else if (yfbz.equals("9")) {
							taxs = yfxs;
						} else if (yfbz.equals("10")) {
							whxs = yfxs;
						} else if (yfbz.equals("11")) {
							rzxs = yfxs;
						} else if (yfbz.equals("12")) {
							lwxs = yfxs;
						} else if (yfbz.equals("13")) {
							lyxs = yfxs;
						} else if (yfbz.equals("14")) {
							dzxs = yfxs;
						} else if (yfbz.equals("15")) {
							lcxs = yfxs;
						} else if (yfbz.equals("16")) {
							bzxs = yfxs;
						} else if (yfbz.equals("17")) {
							hzxs = yfxs;
						}
					 }
		
					//sql1 1-8月为严格
						String sq1_jz = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ jz1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+jz2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+jz3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+jz4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+jz5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+jz6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
								+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx02'";
						//接入网1-8月
						String sql1_jrw="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ jrw1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+jrw2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+jrw3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+jrw4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+jrw5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+jrw6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx05'";
						//乡镇支局
						String sql1_xzzj="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ xzzj1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+xzzj2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+xzzj3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+xzzj4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+xzzj5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+xzzj6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx06'";
						//营业网点
						String sql1_yywd="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ yywd1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+yywd2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+yywd3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+yywd4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+yywd5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+yywd6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx03'";
						//其他
						String sql1_qt="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ qt1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+qt2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+qt3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+qt4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+qt5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+qt6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx04'";
						//局用机房
						String sql1_jyjf="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ jyjf1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+jyjf2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+jyjf3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+jyjf4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+jyjf5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+jyjf6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx01'";
						String sql1_idcjf="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
								+ "(CASE WHEN aa.ZLFH >="
								+ kszlfh1
								+ "AND aa.ZLFH <"
								+ kszlfh2
								+ "THEN "
								+ idcjf1
								+ " "
								+ "WHEN aa.ZLFH >="
								+ kszlfh2
								+ " AND aa.ZLFH < "
								+ kszlfh3
								+ " THEN "+idcjf2+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh3
								+ " AND aa.ZLFH < "
								+ kszlfh4
								+ " THEN "+idcjf3+" "
								+ " WHEN aa.ZLFH >= "
								+ kszlfh4
								+ " AND aa.ZLFH < "
								+ kszlfh5
								+ " THEN "+idcjf4+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh5
								+ " AND aa.ZLFH < "
								+ kszlfh6
								+ " THEN "+idcjf5+" "
								+ "WHEN aa.ZLFH >= "
								+ kszlfh6
								+ " THEN "+idcjf6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
								+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
								+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
								+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
								+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
								+ "WHERE z.PROPERTY='zdsx07'";
//-----------------------------------------------------严格--------------------------------------------------------------------------						
						//9-12宽松
						String sq2_jz = "UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ jz1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+jz2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+jz3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+jz4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+jz5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+jz6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "FWXS=(SELECT DECODE(aa.YFLX,NULL, 0, 'null', 0, '0', 0, 'fwlx01', "+xs1+", 'fwlx02', "+xs2+",'fwlx03', "+xs3+") as yfxs "
							+ "FROM ZHANDIAN aa WHERE aa.ID=z.ID) ,"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx02'";
					 String sql2_jrw="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ jrw1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+jrw2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+jrw3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+jrw4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+jrw5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+jrw6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx05'";
					 String sql2_xzzj="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ xzzj1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+xzzj2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+xzzj3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+xzzj4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+xzzj5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+xzzj6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx06'";
					 String sql2_yywd="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ yywd1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+yywd2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+yywd3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+yywd4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+yywd5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+yywd6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx03'";
					 String sql2_jyjf="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ jyjf1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+jyjf2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+jyjf3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+jyjf4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+jyjf5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+jyjf6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx01'";
					 String sql2_qt="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ qt1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+qt2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+qt3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+qt4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+qt5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+qt6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx04'";
					 String sql2_idcjf="UPDATE ZHANDIAN z SET z.KTXS=(SELECT "
							+ "(CASE WHEN aa.ZLFH >="
							+ kszlfh1
							+ "AND aa.ZLFH <="
							+ jszlfh1
							+ "THEN "
							+ idcjf1
							+ " "
							+ "WHEN aa.ZLFH >"
							+ jszlfh1
							+ " AND aa.ZLFH <= "
							+ jszlfh2
							+ " THEN "+idcjf2+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh2
							+ " AND aa.ZLFH <= "
							+ jszlfh3
							+ " THEN "+idcjf3+" "
							+ " WHEN aa.ZLFH > "
							+ jszlfh3
							+ " AND aa.ZLFH <= "
							+ jszlfh4
							+ " THEN "+idcjf4+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh4
							+ " AND aa.ZLFH <= "
							+ jszlfh5
							+ " THEN "+idcjf5+" "
							+ "WHEN aa.ZLFH > "
							+ jszlfh5
							+ " THEN "+idcjf6+" ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID=z.ID ),"
							+ "z.JCXS=(SELECT DECODE(aa.YFLX, 'fwlx01', "+jcxs1+", 'fwlx02',"+jcxs2+", 'fwlx03',"+jcxs3+") as jcxs FROM ZHANDIAN aa WHERE aa.ID=z.ID), "
							+ "z.YFXS=(SELECT DECODE(aa.SHI,'13701',"+jnxs+",'13702',"+qdxs+",'13703',"+zbxs+",'13704',"+zzxs+",'13705',"+dyxs+",'13706',"+ytxs+","
							+ "'13707',"+wfxs+",'13708',"+Jnxs+",'13709', "+taxs+",'13710',"+whxs+",'13711',"+rzxs+",'13712',"+lwxs+",'13713',"+lyxs+","
							+ "'13714',"+dzxs+",  '13715',"+lcxs+", '13716',"+bzxs+", '13717',"+hzxs+") as yfxs FROM ZHANDIAN aa WHERE aa.ID=z.ID),z.scb=(select s.scb from scb s where s.zdid =z.id ) "
							+ "WHERE z.PROPERTY='zdsx07'";
					 
						String sql11_jz="update zhandian zz set zz.qsdbdl =zz.scb*(zz.jcxs + zz.yfxs * zz.ktxs + zz.fwxs) where zz.property = 'zdsx02'";
						String sql11_jrw="update zhandian zz set zz.qsdbdl =zz.scb*zz.jcxs+(zz.yfxs * zz.ktxs ) where zz.property = 'zdsx05'";
						String sql11_xzzj="update zhandian zz set zz.qsdbdl =zz.scb*(zz.jcxs+ zz.yfxs * zz.ktxs ) where zz.property = 'zdsx06'";
						String sql11_yywd="update zhandian zz set zz.qsdbdl =zz.scb*zz.jcxs+(zz.yfxs * zz.ktxs ) where zz.property = 'zdsx03'";
						String sql11_qt="update zhandian zz set zz.qsdbdl =zz.scb*(zz.jcxs+ zz.yfxs * zz.ktxs ) where zz.property = 'zdsx04'";
						String sql11_idcjf="update zhandian zz set zz.qsdbdl =zz.scb*(zz.jcxs+ zz.yfxs * zz.ktxs ) where zz.property = 'zdsx07'";
						String sql11_jyjf="update zhandian zz set zz.qsdbdl =zz.scb*(zz.jcxs+ zz.yfxs * zz.ktxs ) where zz.property = 'zdsx01'";
						//String sql11_jyjf="update zhandian zz set zz.qsdbdl =(case when zz.scb is not null and zz.scb <> 0 and zz.scb <> '' then zz.scb" +
					 //		" when (zz.scb is null or zz.scb=0 or zz.scb='')and (zz.edhdl is not null and zz.edhdl <> 0 and zz.edhdl <> '') then zz.edhdl*0.9" +
					 //		" when (zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='') and (zz.zlfh is not null or zz.zlfh<>0 or zz.zlfh<>'') then zz.zlfh*1.52" +
					 	//	" when  ((zz.scb is null or zz.scb = 0 or zz.scb = '') and (zz.edhdl is null or zz.edhdl=0 or zz.edhdl='')) and (zz.zlfh is null or zz.zlfh=0 or zz.zlfh='') and(zz.jlfh is not null or zz.jlfh <>0 or zz.jlfh<>'') then zz.jlfh*220*0.85/54*1.52 else 0 end )*(zz.jcxs+ zz.yfxs * zz.ktxs ) where zz.property = 'zdsx01'";
					 
					 int i=Integer.parseInt(s);
					 if(i<=8){
						 if(Integer.parseInt(nowHour)==0){
							 System.out.println("站点表更新空调系数等字段1-8宽泛基站"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标基站"+sql11_jz.toString());
							 oracledb.update(sq1_jz.toString());
							 oracledb.update(sql11_jz.toString());
						 }else if(Integer.parseInt(nowHour)==6){
							 System.out.println("站点表更新空调系数等字段1-8宽泛接入网"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标接入网"+sql11_jz.toString());
							 oracledb.update(sql1_jrw.toString());
							 oracledb.update(sql11_jrw.toString());
						 }else if(Integer.parseInt(nowHour)==5){
							 System.out.println("站点表更新空调系数等字段1-8宽泛乡镇支局"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标乡镇支局"+sql11_jz.toString());
							 oracledb.update(sql1_xzzj.toString());
							 oracledb.update(sql11_xzzj.toString());
						 }else if(Integer.parseInt(nowHour)==4){
							 System.out.println("站点表更新空调系数等字段1-8宽泛局用机房"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标局用机房"+sql11_jz.toString());
							 oracledb.update(sql1_jyjf.toString());
							 oracledb.update(sql11_jyjf.toString());
						 }else if(Integer.parseInt(nowHour)==3){
							 System.out.println("站点表更新空调系数等字段1-8宽泛其他"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标其他"+sql11_jz.toString());
							 oracledb.update(sql1_qt.toString());
							 oracledb.update(sql11_qt.toString());
						 }else if(Integer.parseInt(nowHour)==2){
							 System.out.println("站点表更新空调系数等字段1-8宽泛营业网点"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标营业网点"+sql11_jz.toString());
							 oracledb.update(sql1_yywd.toString());
							 oracledb.update(sql11_yywd.toString());
						 }else if(Integer.parseInt(nowHour)==1){
							 System.out.println("站点表更新空调系数等字段1-8宽泛idc机房"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标idc机房"+sql11_jz.toString());
							 oracledb.update(sql1_idcjf.toString());
							 oracledb.update(sql11_idcjf.toString());
						 }
					 }else if(i>=9){
						 
						 if(Integer.parseInt(nowHour)==0){
							 System.out.println("站点表更新空调系数等字段9-12宽泛基站"+sq2_jz.toString());
							 System.out.println("站点表更新全省定标基站"+sql11_jz.toString());
							 oracledb.update(sq2_jz.toString());
							 oracledb.update(sql11_jz.toString());
						 }else if(Integer.parseInt(nowHour)==6){
							 System.out.println("站点表更新空调系数等字段1-8宽泛接入网"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标接入网"+sql11_jz.toString());
							 oracledb.update(sql2_jrw.toString());
							 oracledb.update(sql11_jrw.toString());
						 }else if(Integer.parseInt(nowHour)==5){
							 System.out.println("站点表更新空调系数等字段1-8宽泛乡镇支局"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标乡镇支局"+sql11_jz.toString());
							 oracledb.update(sql2_xzzj.toString());
							 oracledb.update(sql11_xzzj.toString());
						 }else if(Integer.parseInt(nowHour)==4){
							 System.out.println("站点表更新空调系数等字段1-8宽泛局用机房"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标局用机房"+sql11_jz.toString());
							 oracledb.update(sql2_jyjf.toString());
							 oracledb.update(sql11_jyjf.toString());
						 }else if(Integer.parseInt(nowHour)==3){
							 System.out.println("站点表更新空调系数等字段1-8宽泛其他"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标其他"+sql11_jz.toString());
							 oracledb.update(sql2_qt.toString());
							 oracledb.update(sql11_qt.toString());
						 }else if(Integer.parseInt(nowHour)==2){
							 System.out.println("站点表更新空调系数等字段1-8宽泛营业网点"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标营业网点"+sql11_jz.toString());
							 oracledb.update(sql2_yywd.toString());
							 oracledb.update(sql11_yywd.toString());
						 }else if(Integer.parseInt(nowHour)==1){
							 System.out.println("站点表更新空调系数等字段1-8宽泛idc机房"+sq1_jz.toString());
							 System.out.println("站点表更新全省定标idc机房"+sql11_jz.toString());
							 oracledb.update(sql2_idcjf.toString());
							 oracledb.update(sql11_idcjf.toString());
						 }
					 }
					 
					// oracledb.update(sq1_jz.toString());
					// oracledb.update(sql11_jz.toString());
	            } catch (Exception de) {
	                de.printStackTrace();
	            }finally {
	        	   
	                try {
	                    oracledb.close();
	                } catch (DbException de) {
	                    de.printStackTrace();
	                }

	            }
	        }
	
	
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
