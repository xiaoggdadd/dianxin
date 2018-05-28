package com.noki.mobi.common;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;

import java.util.Date;

import org.jfree.util.Log;

import sun.util.logging.resources.logging;

public class CommonAccountBean {
	public CommonAccountBean() {
	}

	/**
	 * 电表ID下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getAmmeterId(String zdid) {
		ArrayList list = new ArrayList();
		String sql = "select A_ID,AMMETERID from ammeters where stationid='"
				+ zdid + "' order by A_ID";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 费用类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getFeeType() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'FYLX' order by code ";
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 票据类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getNoteType() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'PJLX' order by code ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 2011-07-21 sj 下拉 用房类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getUseHouseType() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'FWLX' order by code ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 2011-07-21 sj 下拉 产权类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getChanQuanType() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'ZDCQ' order by code ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 2011-12-04 各种类型复选框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getCommBoxType(String typebox) {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = '" + typebox
				+ "' order by code ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 根据账号取角色ID
	 * 
	 * @param accountId
	 *            String
	 * @return int
	 */
	public synchronized static int getRoleId(String accountId) {
		int roleId = 0;
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql = "SELECT ROLEID FROM ACCOUNT_ROLE WHERE ACCOUNTID="
				+ accountId;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if (rs.next()) {
				roleId = rs.getInt(1);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		return roleId;
	}

	/**
	 * 根据账号判断是否具体读写权限，ｔｒｕｅ可编辑，ｆａｌｅ只读
	 * 
	 * @param accountId
	 *            String
	 * @param rightId
	 *            String 权限表功能项对应的ｉｄ,判断是哪个页面的
	 * @return boolean
	 */
	public synchronized int readWriteSign(String accountId, String rightId) {
		int sign = 0;
		String sql = "SELECT RR.OPERATIONRIGHT FROM ROLE_RIGHT RR  WHERE RR.ROLEID=(SELECT AR.ROLEID FROM ACCOUNT_ROLE AR WHERE AR.ACCOUNTID="
				+ accountId + " AND RR.RIGHTID=" + rightId + ")";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				sign = rs.getInt(1);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
			} catch (DbException dee) {
				dee.printStackTrace();
			}
		}
		return sign;
	}

	/**
	 * 根据账号ｉｄ返回账号
	 * 
	 * @param accountId
	 *            String
	 * @return String
	 */
	public synchronized static String getAccountName(String accountId) {
		String vorder = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql = "SELECT ACCOUNTNAME FROM ACCOUNT WHERE ACCOUNTID="
				+ accountId;
		// System.out.println(sql);
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if (rs.next()) {
				vorder = rs.getString(1);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
		return vorder;
	}

	/**
	 * 根据账号返回姓名
	 * 
	 * @param accountId
	 *            String
	 * @return String
	 */
	public synchronized static String getName(String accountId) {
		String vorder = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql = "SELECT NAME FROM ACCOUNT WHERE ACCOUNTID=" + accountId;
		// System.out.println(sql);
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if (rs.next()) {
				vorder = rs.getString(1);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
		return vorder;
	}

	/**
	 * 
	 * @param structId
	 *            String
	 * @param roleId
	 *            String
	 * @return ArrayList
	 */
	public synchronized ArrayList getAccount(String structId, int roleId) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select accountname,name from account where accountname not like 'admin%' order by accountname");

		// System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized static String getRoleName(String roleId) {
		String name = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql = "SELECT NAME FROM ROLE WHERE ROLEID=" + roleId;
		// System.out.println(sql);
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
		return name;
	}

	public static String getRQ() {
		String rq = "";
		CTime ct = new CTime();
		rq = ct.formatRealDate(new Date());
		return rq;
	}

	public static String getYear() {
		String rq = "";
		CTime ct = new CTime();
		rq = ct.formatRealDate(new Date()).substring(0, 4) + "-01-01";
		return rq;

	}

	public static String getB_RQ() {
		String rq = "";
		CTime ct = new CTime();
		rq = ct.formatRealDate(new Date());
		rq = rq.substring(0, 8) + "01";
		return rq;
	}

	/**
	 * 专项资金
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getSheng() {
		ArrayList list = new ArrayList();
		String sql = "select AGCODE,AGNAME from d_area_grade where AGRADE='1' order by AGCODE";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 所属专业
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getSszy() {
		ArrayList list = new ArrayList();
		String sql = "select code,name from indexs where  type = 'SSZY' order by code";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 电表用途
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getDbyt() {
		ArrayList list = new ArrayList();
		String sql = "select code,name from indexs where  type = 'DBYT' order by code";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/**
	 * 用电设备
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getYdsblist() {
		ArrayList list = new ArrayList();
		String sql = "select code,name from indexs where  type = 'YDSB' order by code";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	public synchronized ArrayList getZhanDianType() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from zdtype order by code");

		// System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	/**
	 * 站点选择框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getZhanDian() {
		ArrayList list = new ArrayList();
		String sql = "select AGCODE,AGNAME from d_area_grade where AGRADE=1 order by AGCODE";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	/*
	 * 四级级联菜单 省-市-县-站点
	 */
	// 级联市级
	public synchronized String getChildrenArea_shi(String pid, String userid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid
				+ "' and agcode in (select t.agcode from per_area t where t.accountid='"
				+ userid + "')";
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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

		return list.toString();
	}

	// 获取市列表
	public synchronized String getShengChildrenArea(String pid, String userid) {
		StringBuffer list = new StringBuffer();
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid
				+ "' and agcode in (select t.agcode from per_area t where t.accountid='"
				+ userid + "') order by agcode";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append(rs.getString(1) + ",");
				list.append(rs.getString(2) + ";");
			}
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

		return list.toString();
	}

	// 级联县级
	public synchronized String getChildrenArea_xian(String pid, String userid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid
				+ "' and agcode in (select t.agcode from per_area t where t.accountid='"
				+ userid + "')";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
		return list.toString();
	}

	// 站点编码
	public synchronized String getDbbm(String zdmc) throws DbException,
			SQLException {
		String sqlq = "select id from zhandian where jzname='" + zdmc + "'";
		DataBase db1 = new DataBase();
		String zdid = "";
		ResultSet rs1 = null;
		db1.connectDb();
		rs1 = db1.queryAll(sqlq.toString());
		while (rs1.next()) {
			zdid = rs1.getString("id");
		}
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select dbid,dbbm from dianbiao where zdid=" + zdid + "";
		// System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
		} catch (DbException de) {
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

		return list.toString();
	}

	// 站点名称
	public synchronized String getZdmc(String zdmc) throws DbException,
			SQLException {

		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select jzname,jzcode from zhandian where xiaoqu=" + zdmc
				+ "";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				// list.append("<res>"+rs.getString(2)+"</res>");
			}
			list.append("</response>");
		} catch (DbException de) {
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

		return list.toString();
	}

	// 根据市获取区县列表（王代军）
	public synchronized String getShiChildrenArea(String shi, String userid) {
		StringBuffer list = new StringBuffer();
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ shi
				+ "' and agcode in (select substr(t.agcode, 0, 7) from per_area t where t.accountid='"
				+ userid + "')";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append(rs.getString(1) + ",");
				list.append(rs.getString(2) + ";");
			}
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

		return list.toString().substring(0, list.toString().length() - 1);
	}

	// 级联乡镇
	public synchronized String getChildrenArea_xiaoqu(String pid, String userid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid
				+ "' and agcode in (select t.agcode from per_area t where t.accountid='"
				+ userid + "')";
		System.out.println("级联乡镇：" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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

		return list.toString();
	}

	// 级联查找名称
	public synchronized String getChildrenArea_stationtype(String pid,
			String userid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select id,jzname from zhandian where JZTYPE='"
				+ pid
				+ "' and XIAOQU IN(select t.agcode from per_area t where t.accountid='"
				+ userid + "')";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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

		return list.toString();
	}

	// 级联站点
	public synchronized String getChildrenArea_zhandian(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select id,jzname from zhandian where xian='" + pid
				+ "' and qyzt='1'";
		Log.info("[二级关联]" + sql);
		String sqlq = "select accountid,name from account where xian='" + pid
				+ "'";
		Log.info("[二级关联]" + sqlq);
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			rs1 = db.queryAll(sqlq);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			while (rs1.next()) {
				list.append("<res>" + rs1.getString(1) + "</res>");
				list.append("<res>" + rs1.getString(2) + "</res>");
			}
			list.append("</response>");
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

		return list.toString();
	}

	public synchronized String get_zhandian(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select jzcode,jzname from zhandian where xian='" + pid
				+ "'";
		String sqlq = "select accountid,name from account where xian='" + pid
				+ "'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			rs1 = db.queryAll(sqlq);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			while (rs1.next()) {
				list.append("<res>" + rs1.getString(1) + "</res>");
				list.append("<res>" + rs1.getString(2) + "</res>");
			}
			list.append("</response>");
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
		return list.toString();
	}

	public synchronized String getZhandian(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select id,jzname from zhandian where xiaoqu='" + pid
				+ "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	public synchronized String getzdmc(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select id,jzname from zhandian where xiaoqu='" + pid
				+ "' and qyzt='1'";
		Log.info("[二级关联站点]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	public synchronized String getzdmc2(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select id,jzname from zhandian where xian = '" + pid
				+ "'";
		Log.info("[二级关联站点]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	public synchronized String getD(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select wlbm,wlbm from dianbiao where zdid='" + pid + "'";
		String sq = "select jzcode,jzcode from zhandian where id = '" + pid
				+ "'";
		DataBase db = new DataBase();
		DataBase db1 = new DataBase();
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			rs1 = db1.queryAll(sq);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			while (rs1.next()) {
				list.append("<res>" + rs1.getString(1) + "</res>");
				list.append("<res>" + rs1.getString(2) + "</res>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	public synchronized String getBumen(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select deptcode,deptname from department where fdeptcode='"
				+ pid + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	public synchronized String getbanzu(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select deptcode,deptname from department where fdeptcode='"
				+ pid + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<ress>" + rs.getString(1) + "</ress>");
				list.append("<ress>" + rs.getString(2) + "</ress>");
			}
			list.append("</response>");
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
		return list.toString();
	}

	/**
	 * 集团报表类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getStationPointType() {
		ArrayList list = new ArrayList();
		String sql = "select CODE,NAME from indexs where type = 'ZDLX' order by CODE ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		}

		catch (DbException de) {
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

	public synchronized ArrayList getMenu() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select rightid,name from right where block='1' order by rightid");

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getAgcode(String agcode) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select agcode,agname from d_area_grade where fagcode=(select fagcode from d_area_grade where agcode='"
						+ agcode + "')");

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getAgcode(String pcode, String agcode) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select agcode,agname from d_area_grade where fagcode='"
					+ pcode + "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getFenzu() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name from fenzu order by orderid");

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getShi(String userid) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select agcode,agname from d_area_grade where agrade='2' and agcode in (select substr(t.agcode, 0, 5) from per_area t where t.accountid='"
						+ userid + "') ORDER BY AGCODE");

		System.out.println("shi:" + sql);

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getQu(String shiCode, String userid) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select agcode,agname from d_area_grade where agrade='3' and agcode in (select substr(t.agcode, 0, 7) from per_area t where t.accountid='"
						+ userid
						+ "') and substr(agcode, 0, 5)='"
						+ shiCode
						+ "'");

		System.out.println("shi:" + sql);

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	public synchronized ArrayList getXiaoQu(String quCode, String userid) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select agcode,agname from d_area_grade where agrade='4' and agcode in (select substr(t.agcode, 0, 9) from per_area t where t.accountid='"
						+ userid
						+ "') and substr(agcode, 0, 7)='"
						+ quCode
						+ "'");

		System.out.println("shi:" + sql);

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
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

	/*
	 * 按钮权限
	 */
	public synchronized String getPermissionRight(String roleId, String rightid) {
		String list = "0";
		StringBuffer sql = new StringBuffer();
		if (roleId.indexOf(",") >= 0) {
			sql
					.append("select permission from role_permission where roleid in("
							+ roleId + ") and mid='" + rightid + "'");
		} else {
			sql.append("select permission from role_permission where roleid="
					+ roleId + " and mid='" + rightid + "'");
		}
		System.out.println(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list += "," + rs.getString(1);
			}
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

	// 级联全成本
	public synchronized String getQcb(String pid, String s) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select q.qcbcode,q.qcbname from qcbdf q where q.fqcbcode='"
				+ pid + "'" + " and bzw='" + s + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
				if (db != null) {
					db.close();
				}
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	// 站点属性与站点类型联动
	public synchronized String getZdsx(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select z.code,z.name from zdsx z where z.fjcode='" + pid
				+ "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			System.out.println("站点属性、类型联动：" + sql);
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
				if (db != null) {
					db.close();
				}
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	// 站点属性与站点类型联动
	public synchronized String getZdsx2(String pid) {
		pid = "".equals(pid) ? "''" : pid;
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select z.code,z.name from zdsx z where z.fjcode in("
				+ pid + ")";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			System.out.println("站点属性、类型联动：" + sql);
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
				if (db != null) {
					db.close();
				}
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	// 站点属性为空时查出所有的站点类型
	public synchronized String getZdsxnull(String lxdm) {
		lxdm = lxdm.toLowerCase();
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select code,name from indexs where type='" + lxdm
				+ "' or type='" + lxdm.toUpperCase() + "' order by code";

		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			System.out.println("站点属性为空时查出所有的站点类型：" + sql);
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
				if (db != null) {
					db.close();
				}
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	// 厂家与设备类型联动
	public synchronized String getChangjia(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select z.code,z.name from changjia z where z.fjcode='"
				+ pid + "'";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			System.out.println("站点属性、类型联动：" + sql);
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString(1) + "</res>");
				list.append("<res>" + rs.getString(2) + "</res>");
			}
			list.append("</response>");
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
				if (db != null) {
					db.close();
				}
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list.toString();
	}

	public String getChildrenArea_jizhan(String xiaoqu) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select ID,JZNAME from zhandian where XIAOQU='" + xiaoqu
				+ "' and qyzt='1'";
		System.out.println(sql);
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				list.append("<res>" + rs.getString("ID") + "</res>");
				list.append("<res>" + rs.getString("JZNAME") + "</res>");
			}
			list.append("</response>");
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
		System.out.println(list.toString());
		return list.toString();
	}
}
