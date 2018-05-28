package com.noki.dgyydgl.javabean;

import java.util.ArrayList;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class DgyydBean {
	private int allPage;
	public DgyydBean() {
	}

	public synchronized String add(String shi, String xian, String xiaoqu,
			String byqrl, String glys, String dj,String zhdj,String jc,String zddj,String phddl,String rl,String zb) {
		String msg = "添加信息失败！";
		DataBase db = new DataBase();
		String sql = "INSERT INTO DGYYDGL(ID,SHI,XIAN,XIAOQU,BYQRL,GLYS,DJ,ZHDJ,JC,ZDDJ,PHDDL,RL,ZB) VALUES(seq_flow.nextval,'"
				+ shi + "','" + xian + "','" + xiaoqu + "','" + byqrl + "','" + glys + "','" + dj + "','" + zhdj + "','" + jc + "','" + zddj + "','" + phddl + "','" + rl + "','" + zb + "')";
		try {
			db.connectDb();

			db.update(sql);
			return "添加信息成功！";

		} catch (DbException de) {
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	public synchronized ArrayList getPageData(int start,
			String shi, String xian, String xiaoqu) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();// StringBuffer线程安全的可变字符序列主要操作是
												// append 和 insert 方法
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and d.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and d.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and d.shi='" + shi + "'");
		} 
		StringBuffer s2 = new StringBuffer();

		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs3 = null;
		StringBuffer strall = new StringBuffer();

		strall.append("select count(*) from dgyydgl d");
		strall.append(cxtj.toString());
		System.out.println("cxtj.toString()="+cxtj.toString());
		try {
			db.connectDb();
			String fzarea = "", fzzdid = "", fzzdstr = "";
			// fzarea = getFuzeArea(db, loginName);
			StringBuffer s3 = new StringBuffer();
			// fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");//有没有用 没加
			s2.append("select d.id,d.byqrl,to_char(d.glys,'fm9999999990.00') glys,to_char(d.dj,'fm9999999990.0000') dj,to_char(d.zhdj,'fm9999999990.0000') zhdj,to_char(d.jc,'fm9999999990.0000') jc,to_char(d.zddj,'fm9999999990.00') zddj,d.phddl,d.rl,to_char(d.zb,'fm9999999990.00') zb"
							+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xian) as xian"
							+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.shi) as shi"
							+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xiaoqu) as xiaoqu "
							+ " from dgyydgl d where 1=1 " +
							cxtj.toString()+"");

			System.out.println("大工业用电管理查询：" + s2);
			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("大工业用电查询分页" + s3);
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
			db.close();
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
			if (rs3 != null) {
				try {
					rs3.close();
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

	/**
	 * 返回所有
	 * 
	 * @return ArrayList
	 */

	public synchronized ArrayList getAll() {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		// sql
		// +="SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,F.NAME  AS FZNAME,R.FENZU FROM ROLE R,FENZU F WHERE R.FENZU=F.ID ORDER BY ROLEID";
		/*
		 * if (sign == 0) { sql +=
		 * "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID NOT IN(4,5,6) ORDER BY ROLEID"
		 * ;//不包括促销员与业务员 } else if (sign == 1) { sql +=
		 * "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID!=1 ORDER BY ROLEID"
		 * ; }
		 */
		sql += "select d.byqrl,d.glys,d.dj"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xian) as xian"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.shi) as shi"
			+ ",(select  ag.agname from d_area_grade ag where ag.agcode =d.xiaoqu) as shi"
			+ " from dgyydgl d";
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					list.add(rs.getString(i));
				}
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}

		return list;
	}

	

	/**
	 * 删除
	 * 
	 * @param id
	 *            String
	 * @return String
	 */
	public synchronized String del(String id) {
		String msg = "";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			String sql = "";
			sql += "DELETE FROM dgyydgl WHERE ID=" + id;
			db.update(sql);
			return "您选择的信息已经全部删除！";
		} catch (DbException de) {
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	

	/**
	 * 修改流程信息
	 * 
	 * @param flowId
	 *            String
	 * @param name
	 *            String
	 * @param flowDesc
	 *            String
	 * @return String
	 */
	public synchronized String modifyFlow(String flowId, String name,
			String flowDesc) {
		String msg = "修改流程信息失败！";
		String sql = "UPDATE S_FLOW_INFO SET FLOW_NAME='" + name
				+ "',FLOW_DESC='" + flowDesc + "' WHERE FLOW_ID=" + flowId;
		System.out.println(sql);
		DataBase db = new DataBase();
		try {
			db.connectDb();
			
				db.update(sql);
				return "修改流程信息成功！";
			
		} catch (DbException de) {
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	

	

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

}
