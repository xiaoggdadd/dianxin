package com.noki.mobi.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.dianliang.model.TopAvgPUEBean;
import com.noki.jizhan.model.GongysAccountBean;
import com.noki.jizhan.model.GongysBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.noki.zwhd.model.ZhandianBean;
import com.ptac.app.util.Constant;
import com.ptac.app.util.Page;

public class CommonBean {
	public CommonBean() {
	}

	/**
	 * 电表ID下拉框
	 * 
	 * @return ArrayList
	 */
	// 得到类型

	public synchronized ArrayList getXxlx(String xxlx) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();

		if (xxlx.equals("0")) {
			return null;
		} else {
			sql.append(("select i.name from gonggao g,indexs i where 1=1 "));
		}
		System.out.println(sql);

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

	public synchronized ArrayList getSelctOptions1(String lxdm1) {
		ArrayList list = new ArrayList();
		lxdm1 = lxdm1.toLowerCase();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from indexs where type='" + lxdm1
				+ "' or type='" + lxdm1.toUpperCase() + "' order by code");

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

	// 站点名称下拉框
	public synchronized ArrayList getZDNAME() {
		ArrayList list = new ArrayList();
		String sql = "select jzcode,jzname from zhandian where shi=13715";

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

	// 电表编码查询
	public synchronized ArrayList getDBBM(String zdmc) throws DbException,
			SQLException {
		ArrayList list = new ArrayList();
		String zdid = "";
		if (zdmc == null) {
			return null;
		} else {
			String sql = "select id from zhandian where jzname='" + zdmc + "'";
			DataBase db = new DataBase();
			ResultSet rs = null;
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				zdid = rs.getString("id");
			}

			DataBase db1 = new DataBase();
			ResultSet rs1 = null;
			String sql2 = "select dbbm from dianbiao where zdid=" + zdid + "";
			System.out.println(sql2);
			try {
				db.connectDb();
				rs1 = db1.queryAll(sql2.toString());
				Query query = new Query();
				list = query.query(rs1);
			} catch (DbException de) {
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
		} catch (DbException de) {
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
		return list;
	}

	/**
	 * 电费支付类型下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getDfzflx() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'dfzflx' order by code ";
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
	 * 电表用途下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getDbyt() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'DBYT' order by code ";

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

	// 支付类型
	public synchronized ArrayList getBargainType() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'ZFLX' order by code ";

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

	// 电费支付类型
	public synchronized ArrayList getSelctOptions() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'dfzflx' order by code ";

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
		sql.append("select accountname,name from account where accountname not like 'admin%' order by accountname");

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

	public synchronized ArrayList getZhanDianType() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from zdtype order by code");

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
	public synchronized String getChildrenArea_shi(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";
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

	// 级联县级
	public synchronized String getChildrenArea_xian(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";

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
		String sql = "select id,jzname from zhandian where xian='" + pid + "'";

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

	// 站点属性
	public synchronized ArrayList getZdxl() {
		ArrayList list = new ArrayList();
		String sql = "select CODE,NAME from indexs where type = 'ZDSX' order by CODE ";

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
		sql.append("select rightid,name from right where block='1' order by rightid");

		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
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
		return list;
	}

	public synchronized ArrayList getAgcode(String agcode) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname from d_area_grade where fagcode=(select fagcode from d_area_grade where agcode='"
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

	public synchronized ArrayList getAgcode(String pcode, String userid) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();

		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select agcode,agname from d_area_grade where fagcode='"
					+ pcode
					+ "' and agcode in (select t.agcode from per_area t where t.accountid='"
					+ userid + "') ORDER BY agcode");
		}
		System.out.println(sql);

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

	public synchronized ArrayList getShi() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode,agname from d_area_grade where agrade='2'");

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

	// 级联乡镇
	public synchronized String getChildrenArea_xiaoqu(String pid) {
		StringBuffer list = new StringBuffer();
		list.append("<response>");
		String sql = "select agcode,agname from d_area_grade where fagcode='"
				+ pid + "'";

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

	/**
	 * 返回区县或者市对应的基站
	 * 
	 * @param shi
	 *            String
	 * @param xian
	 *            String
	 * @return ArrayList
	 */
	public synchronized ArrayList getJiZhan(String shi, String xian) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (!xian.equals("0")) {
			sql.append("select zdcode,jzname from zhandian where xian='" + xian
					+ "'");
		} else {
			sql.append("select zdcode,jzname from zhandian where xian like '"
					+ shi + "%'");
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

	/*
	 * 按钮权限
	 */
	public synchronized String getPermissionRight(String roleId, String rightid) {
		String list = "0";
		StringBuffer sql = new StringBuffer();
		if (roleId.indexOf(",") >= 0) {// indexOf(String str)
										// 返回指定子字符串在此字符串中第一次出现处的索引。
			sql.append("select permission from role_permission where roleid in("
					+ roleId + ") and mid='" + rightid + "'");// 将指定的字符串追加到此字符序列。
		} else {
			sql.append("select permission from role_permission where roleid="
					+ roleId + " and mid='" + rightid + "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("-sql-" + sql.toString());
		try {
			db.connectDb();
			System.out.println("------------------             " + sql);
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				list += "," + rs.getString(1);// getString(int columnIndex) 以
												// Java 编程语言中 String 的形式获取此
												// ResultSet 对象的当前行中指定列的值。

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

	// 得到部门名称
	public synchronized ArrayList getyijibanzu() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DEPTCODE,DEPTNAME FROM DEPARTMENT WHERE DEPTGRADE='3'");
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			// StringBuffer s2 = new StringBuffer();
			// s2.append("select distinct(substr(agcode,0," + sublen
			// +")) from per_area where accountname='" + account +"'");
			// sql.append(" and agcode in(" + s2.toString() +
			// ") ORDER BY AGCODE");

			System.out.println("联动" + sql);
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

	// 得到部门名称
	public synchronized ArrayList getbumenmingcheng() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DEPTCODE,DEPTNAME FROM DEPARTMENT WHERE DEPTGRADE='2'");
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			// StringBuffer s2 = new StringBuffer();
			// s2.append("select distinct(substr(agcode,0," + sublen
			// +")) from per_area where accountname='" + account +"'");
			// sql.append(" and agcode in(" + s2.toString() +
			// ") ORDER BY AGCODE");

			System.out.println("联动" + sql);
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

	// 得到bumen名称
	public synchronized ArrayList getchengbumen() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DEPTCODE,DEPTNAME FROM DEPARTMENT WHERE DEPTGRADE='2'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			System.out.println("联动" + sql);
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
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
		return list;
	}

	// 得到城市名称
	public synchronized ArrayList getchengshimingcheng(String fgcode) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DEPTCODE,DEPTNAME FROM DEPARTMENT WHERE isused='"+Constant.ISUSED_DEFAULT_VALUE+"' and FDEPTCODE='"
				+ fgcode + "' order by deptcode");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			System.out.println("联动" + sql);
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
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
		return list;
	}

	public synchronized ArrayList getAgcode(String pcode, String agcode,
			String account) {
		if (null == pcode || "".equals(pcode)) {
			pcode = "0";
		}
		int sublen = pcode.length() + 2;

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
			// StringBuffer s37 = new StringBuffer();
			// s37.append("select id from per_area t where t.agcode='"+pcode+"' and accountname='"
			// +account + "'");
			// rs = db.queryAll(s37.toString());

			//StringBuffer s2 = new StringBuffer();
			//s2.append("select distinct(substr(agcode,0," + sublen
			//		+ ")) from per_area where accountname='" + account + "'");
			//sql.append(" and agcode in(" + s2.toString() + ") ORDER BY AGCODE");
			
			// update by guol20171220 ↓
			StringBuffer s2 = new StringBuffer();
			s2.append("select agcode from per_area where accountname='" + account + "'");
			sql.append(" and agcode in(" + s2.toString() + ") ORDER BY AGCODE");
			// update by guol20171220 ↑
			
			System.out.println("联动" + sql);
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

	public synchronized ArrayList getdbbm(String pcode) {
		if (null == pcode || "".equals(pcode)) {
			pcode = "0";
		}
		int sublen = pcode.length() + 2;

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select wlbm from dianbiao where zdid='" + pcode
					+ "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			System.out.println("联动" + sql);
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
	public synchronized ArrayList getzdbm(String pcode) {
		if (null == pcode || "".equals(pcode)) {
			pcode = "0";
		}
		int sublen = pcode.length() + 2;

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select jzcode,jzcode from zhandian where jzcode='" + pcode
					+ "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			System.out.println("联动" + sql);
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
	public synchronized ArrayList getPjlx(String pcode) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select code,name from indexs where type='" + pcode
					+ "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			System.out.println("联动" + sql);
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
	public synchronized ArrayList getzdmc(String pcode) {
		if (null == pcode || "".equals(pcode)) {
			pcode = "0";
		}
		int sublen = pcode.length() + 2;

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		if (pcode.equals("0")) {
			return null;
		} else {
			sql.append("select id,jzname from zhandian where id='" + pcode
					+ "'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			System.out.println("联动" + sql);
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

	public synchronized ArrayList getuser() {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select accountid,name from account where roleid like '%445%'");

		// sql.append("select accountid,name from account where roleid like '445'");

		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			System.out.println("联动" + sql);
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

	public String getdiqu(String shi) {
		String diqu = "";
		String sql = "select  agname from D_AREA_GRADE where agcode='" + shi
				+ "'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if (rs.next()) {
				diqu = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diqu;
	}

	/**
	 * 字典下拉框-通用方法
	 * @param lxdm 字典类型
	 * @return
	 */
	public synchronized ArrayList getSelctOptions(String lxdm) {
		ArrayList list = new ArrayList();
		lxdm = lxdm.toLowerCase();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from indexs where lower(type)='"+lxdm+"' order by code");

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
 * 
 * @param lxdms
 * @return
 */
	public synchronized ArrayList getSelctOptions(String[] lxdms) {
		ArrayList list = new ArrayList();
	//	lxdm = lxdm.toLowerCase();
		StringBuffer insql = new StringBuffer();
		for(String lxdm: lxdms){
			insql.append("'").append(lxdm.toUpperCase()).append("',");
		}
		String insqlStr = insql.toString().substring(0, insql.length()-1);
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name,type from indexs where UPPER(type) in ("+ insqlStr +") order by code");

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
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
       return list;
	}
	
	
	
	// 添加站点属性
	public synchronized ArrayList getSelctOptionszd(String lxdm) {
		ArrayList list = new ArrayList();
		lxdm = lxdm.toLowerCase();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from indexs where type='" + lxdm
				+ "' or type='" + lxdm.toUpperCase() + "' order by code");

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

	// 添加集团报表类型
	public synchronized ArrayList getSelctOptionsjt(String lxdm) {
		ArrayList list = new ArrayList();
		lxdm = lxdm.toLowerCase();
		StringBuffer sql = new StringBuffer();
		sql.append("select code,name from indexs where type='" + lxdm
				+ "' or type='" + lxdm.toUpperCase() + "' order by code");

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

	// 全成本-----------------mqy2012-12-24
	public synchronized ArrayList getQcb(String qcbg, String str) {
		// int sublen = pcode.length()+2;

		ArrayList list = new ArrayList();
		String sql = "";
		/*
		 * if (pcode.equals("0")) { return null; } else {
		 * sql.append("select agcode,agname from d_area_grade where fagcode='" +
		 * pcode + "'"); }
		 */
		sql = "select q.qcbname,q.qcbcode from qcbdf q where q.fqcbcode='"
				+ qcbg + "' " + str;

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			/*
			 * StringBuffer s37 = new StringBuffer();
			 * s37.append("select id from per_area t where t.agcode='"
			 * +pcode+"' and accountname='" +account + "'"); rs =
			 * db.queryAll(s37.toString());
			 * 
			 * 
			 * StringBuffer s2 = new StringBuffer();
			 * s2.append("select distinct(substr(agcode,0," + sublen
			 * +")) from per_area where accountname='" + account +"'");
			 * sql.append(" and agcode in(" + s2.toString() + ")");
			 */

			System.out.println("1111111111联动" + sql);
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

	// 全成本-----------------mqy2012-12-24
	public synchronized ArrayList getQcbb(String qcbg) {
		// int sublen = pcode.length()+2;

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		/*
		 * if (pcode.equals("0")) { return null; } else {
		 * sql.append("select agcode,agname from d_area_grade where fagcode='" +
		 * pcode + "'"); }
		 */
		sql.append("select q.qcbname,q.qcbcode from qcbdf q where q.qcbcode='"
				+ qcbg + "'");

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();
			/*
			 * StringBuffer s37 = new StringBuffer();
			 * s37.append("select id from per_area t where t.agcode='"
			 * +pcode+"' and accountname='" +account + "'"); rs =
			 * db.queryAll(s37.toString());
			 * 
			 * 
			 * StringBuffer s2 = new StringBuffer();
			 * s2.append("select distinct(substr(agcode,0," + sublen
			 * +")) from per_area where accountname='" + account +"'");
			 * sql.append(" and agcode in(" + s2.toString() + ")");
			 */

			System.out.println("联动" + sql);
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
	 * 付款周期下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getPaycircle() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'ZLFKZQ' order by code ";

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
	 * 支付方式下拉框
	 * 
	 * @return ArrayList
	 */
	public synchronized ArrayList getPayway() {
		ArrayList list = new ArrayList();
		String sql = "select code,NAME from indexs where type = 'ZFFS' order by code ";

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
	 * 地市编号获取对应完整地市 （**市**区**）
	 * @param agcode
	 * @return
	 */
	public String getAgName(String agcode){
		String sql = "select d1.agcode , concat(concat(d3.agname,d2.agname),d1.agname) agname from d_area_grade d1, d_area_grade d2, d_area_grade d3 where d1.fagcode = d2.agcode and d2.fagcode = d3.agcode and d1.agcode = '"+agcode+"' order by d1.agcode";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    String fullagname = "";
	    try {
			db.connectDb();
			rs = db.queryAll(sql);
			if(rs.next()){
				fullagname = rs.getString("agname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return fullagname;
	}

	/**
	 * 预算责任中心信息
	 * @return
	 */
	public synchronized ArrayList getBudgetDutyCenter(){
		ArrayList list = new ArrayList();
		String sql = "select NAME ,CODE from Ring_Budget_Duty_Center";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 预算项目信息
	 * @return
	 */
	public synchronized ArrayList getBudgetProject(){
		ArrayList list = new ArrayList();
		String sql = "select NAME ,CODE from Ring_Budget_Project";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 成本中心信息
	 * @return
	 */
	public synchronized ArrayList getCostCenter(){
		ArrayList list = new ArrayList();
		String sql = "select NAME ,CODE from ring_cost_center";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 成本中心弹出框
	 * @param code 成本中心编码
	 * @param name 成本中心名称
	 * @param gszt 公司主体
	 * @return 总行数
	 */
	public synchronized int getCostCenterTotalRow(String code, String name,String gszt){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ring_cost_center where 1=1");//code like substr('"+gszt+"',0,1)||'%'
		if(code != null && !code.isEmpty()){
			sql.append(" and code like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		if(gszt !=null && !gszt.isEmpty() && !"0".equals(gszt)){
			sql.append(" and code like substr('"+gszt+"',0,1)||'%'");
		}
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	/**
	 * 成本中心弹出框
	 * @param code 成中心编码
	 * @param name 成本中心内名称
	 * @param gszt 公司主体
	 * @param page 分页工具类
	 * @return 分页数据
	 */
	public synchronized ArrayList getCostCenterPage(String code, String name, String gszt,Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select NAME,CODE from ring_cost_center where 1=1");
		if(code != null && !code.isEmpty()){
			sql.append(" and code like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		if(gszt !=null && !gszt.isEmpty() && !"0".equals(gszt)){
			sql.append(" and code like substr('"+gszt+"',0,1)||'%'");
		}
		sql.append(" order by id desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 供应商弹出框
	 * @param code 供应商编码
	 * @param name 供应商名称
	 * @return 总行数
	 */
	public synchronized int getSupplierTotalRow(String code, String name){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ring_Supplier where 1=1");
		if(code != null && !code.isEmpty()){
			sql.append(" and code like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	/**
	 * 供应商弹出框
	 * @param code 供应商编码
	 * @param name 供应商名称
	 * @param page 分页工具类
	 * @return 分页数据
	 */
	public synchronized ArrayList getSupplierPage(String code, String name, Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select NAME,CODE from ring_Supplier where 1=1");
		if(code != null && !code.isEmpty()){
			sql.append(" and code like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		sql.append(" order by id desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 电表弹出框
	 * @param code 电表编码
	 * @param name 电表名称
	 * @return 总行数
	 */
	public synchronized int getMeterTotalRow(String code, String name,String accountCthrnumber){
		if(!accountCthrnumber.equals("") && accountCthrnumber!=null){
			StringBuffer sql = new StringBuffer();
			sql.append("select count(1) from dianbiao where 1=1 and SHZT=2 and ssf='2' and BZR='"+accountCthrnumber+"' ");
			if(code != null && !code.isEmpty()){//ssf=2 非铁塔标记
				sql.append(" and dbbm like '%").append(code).append("%'");
			}
			if(name != null && !name.isEmpty()){
				sql.append(" and dbname like '%").append(name).append("%'");
			}
			DataBase db = new DataBase();
			int cnt = 0;
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					cnt = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}else{
			return 0;
		}
		
	}
	//铁塔选择电表
	public synchronized int getTietaDianbiao(String code, String name,String accountCthrnumber){
		if(!accountCthrnumber.equals("") && accountCthrnumber!=null){
			StringBuffer sql = new StringBuffer();
			sql.append("select count(1) from dianbiao where 1=1 and SHZT=2 and ssf='1' and BZR='"+accountCthrnumber+"' ");
			if(code != null && !code.isEmpty()){//ssf=1 铁塔
				sql.append(" and dbbm like '%").append(code).append("%'");
			}
			if(name != null && !name.isEmpty()){
				sql.append(" and dbname like '%").append(name).append("%'");
			}
			DataBase db = new DataBase();
			int cnt = 0;
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					cnt = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}else{
			return 0;
		}
		
	}
	public synchronized int getAccountTotalRow(String code, String name){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from account where 1=1");
		if(code != null && !code.isEmpty()){
			sql.append(" and CTHRNUMBER like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	

	/**
	 * 电表弹出框
	 * @param code 电表编码
	 * @param name 电表名称
	 * @param page 分页工具类
	 * @return 分页数据
	 */
	public synchronized ArrayList getMeterPage(String code, String name, Page page,String accountCthrnumber){
		ArrayList list = new ArrayList();
		
		if(!accountCthrnumber.equals("") && accountCthrnumber!=null){
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from ( select a.*, rownum rn from (");
			sql.append("select d.id, d.dbbm, d.dbname,(select i.name from indexs i where i.code=d.gszt and i.type='gszt')gszt,(select c.name from ring_cost_center c where c.code = b.cost_code)cbzx,(select m.name from ring_budget_duty_center m where m.code = b.duty_code)yszrzx  from dianbiao d left join Accounting_Unit_info b on d.id=b.dbcode  where   1=1  and SHZT=2 and ssf='2'  and dbqyzt = 'zt01' and BZR='"+accountCthrnumber+"' ");
			if(code != null && !code.isEmpty()){
				sql.append(" and dbbm like '%").append(code).append("%'");
			}
			if(name != null && !name.isEmpty()){
				sql.append(" and dbname like '%").append(name).append("%'");
			}
			sql.append(" order by id desc");
			sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
			
			System.out.println("根据登录人获取电表:"+sql.toString());
			DataBase db = new DataBase();
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				Query query = new Query();
				list = query.query(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
		}else{
			list=null;
		}
		
		return list;
	}
	
	/**
	 * 铁塔电表弹出框
	 * @param code 电表编码
	 * @param name 电表名称
	 * @param page 分页工具类
	 * @return 分页数据
	 */
	public synchronized ArrayList getTietaPage(String code, String name, Page page,String accountCthrnumber){
		ArrayList list = new ArrayList();
		
		if(!accountCthrnumber.equals("") && accountCthrnumber!=null){
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from ( select a.*, rownum rn from (");
			sql.append("select id, dbbm, dbname from dianbiao where 1=1 and SHZT=2 and ssf='1'  and dbqyzt = 'zt01' and BZR='"+accountCthrnumber+"' ");
			if(code != null && !code.isEmpty()){
				sql.append(" and dbbm like '%").append(code).append("%'");
			}
			if(name != null && !name.isEmpty()){
				sql.append(" and dbname like '%").append(name).append("%'");
			}
			sql.append(" order by id desc");
			sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
			
			System.out.println("根据登录人获取电表:"+sql.toString());
			DataBase db = new DataBase();
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				Query query = new Query();
				list = query.query(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
		}else{
			list=null;
		}
		
		return list;
	}
	public synchronized int getElectricTotalRow(String stationName, String startTime, String endTime){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from zhandian z left join ring_electric e on z.station_no = e.station_no where 1=1 ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(startTime !=null && !startTime.isEmpty()){
			sql.append(" and e.updatetime >= to_date('"+startTime+"', 'yyyy-MM-dd')");
		}
		if(endTime !=null && !endTime.isEmpty()){
			sql.append(" and e.updatetime <= to_date('"+endTime+"', 'yyyy-MM-dd')");
		}
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public synchronized ArrayList getElectricPage(String stationName, String startTime, String endTime, Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select e.id, e.station_no, z.jzname station_name, to_char(e.updatetime,'yyyy-MM-dd') updatetime, to_char(e.electricity, 'fm9999999990.0000') electricity, to_char(e.updatetime, 'dd') d " +
				"from zhandian z left join ring_electric e on z.station_no = e.station_no " +
				"where 1=1 ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(startTime !=null && !startTime.isEmpty()){
			sql.append(" and e.updatetime >= to_date('"+startTime+"', 'yyyy-MM-dd')");
		}
		if(endTime !=null && !endTime.isEmpty()){
			sql.append(" and e.updatetime <= to_date('"+endTime+"', 'yyyy-MM-dd')");
		}
		sql.append(" order by e.station_no, e.updatetime desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		System.out.println("PUE查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public synchronized ArrayList getElectricOne(String id){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select e.id, e.station_no, z.jzname station_name, to_char(e.updatetime,'yyyy-MM-dd') updatetime, to_char(e.electricity, 'fm9999999990.0000') electricity, to_char(e.updatetime, 'dd') d " +
				"from zhandian z left join ring_electric e on z.station_no = e.station_no " +
				"where e.id='"+id+"'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public synchronized ArrayList getAccountPage(String code, String name, Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select ACCOUNTID, CTHRNUMBER, NAME from ACCOUNT where 1=1");
		if(code != null && !code.isEmpty()){
			sql.append(" and CTHRNUMBER like '%").append(code).append("%'");
		}
		if(name != null && !name.isEmpty()){
			sql.append(" and name like '%").append(name).append("%'");
		}
		sql.append(" order by ACCOUNTID desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 供应商信息下拉框
	 * @return
	 */
	public synchronized ArrayList getSuppliers(){
		ArrayList list = new ArrayList();
		String sql = "select NAME ,CODE from Ring_Supplier order by id desc";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 根据供应商编号查询供应商信息
	 * @return
	 */
	public synchronized ArrayList getSupplier(String code){
		ArrayList list = new ArrayList();
		String sql = "select ID ,NAME,CODE,PAYEE_NAME,BANK_ACCOUNT,SUBJECT_BANK,OPENING_BANK,PROVINCE,CITY from Ring_Supplier where code='"+code+"'";
		System.out.println("根据供应商编号查询供应商信息"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 地区公司查询部门下拉框
	 * @param shi 地市编码
	 * @param fdeptcode 部门父类编码
	 * @return
	 */
	public synchronized ArrayList getDept(String shi, String fdeptcode){
		ArrayList list = new ArrayList();
		String sql = "select d.deptcode, d.deptname from department d where d.isused='"+Constant.ISUSED_DEFAULT_VALUE+"' and d.fdeptcode='"+fdeptcode+"' " +
				"and d.shi='"+shi+"' order by d.deptcode";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
     * 根据供应商编号查询供应商信息
     * @return
     */
    public synchronized GongysBean getSupplier2(String code){
    	GongysBean gongysBean = new GongysBean();
        ArrayList list = new ArrayList();
        String sql = "select ID,NAME,CODE,PROVIDER_ID from Ring_Supplier where code='"+code+"'";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()>0){
            	
            	gongysBean.setId(list.get(0).toString());
            	gongysBean.setName(list.get(1).toString());
            	gongysBean.setCode(list.get(2).toString());
            	gongysBean.setProvider_id(list.get(3).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = new ArrayList();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return gongysBean;
    }
    /**
     * 根据供应商编号查询供应商信息
     * @return
     */
    public synchronized List<GongysAccountBean> getGaById(String id){
    	List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
        ArrayList list = new ArrayList();
        String sql = "select ACOUNT_ID,PROVIDER_ID,PROVINCE_CODE,CITY," +
        		"BANK_TYPE,BANK,ACCOUNT_NAME,ACCOUNT_NO,BRNCH,DELETE_FLAG " +
        		"from RING_SUPPLIER_ACCOUNT where ACOUNT_ID='"+id+"'";
        DataBase db = new DataBase();
        System.out.println("sqlyujuceshi+++++"+sql);
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            while (rs.next()) {
            	GongysAccountBean gongysAccountBean = new GongysAccountBean();
            	gongysAccountBean.setAccount_id(rs.getString("ACOUNT_ID"));
            	gongysAccountBean.setProvider_id(rs.getString("PROVIDER_ID"));
            	gongysAccountBean.setProvider_code(rs.getString("PROVINCE_CODE"));
            	gongysAccountBean.setCity(rs.getString("CITY"));
            	gongysAccountBean.setBank_type(rs.getString("BANK_TYPE"));
            	gongysAccountBean.setBank(rs.getString("BANK"));
            	gongysAccountBean.setAccountName(rs.getString("ACCOUNT_NAME"));
            	gongysAccountBean.setAccount_no(rs.getString("ACCOUNT_NO"));
            	gongysAccountBean.setBrnch(rs.getString("BRNCH"));
            	gongysAccountBean.setDelete_flag(rs.getString("DELETE_FLAG"));
            	gongysAccountBeans.add(gongysAccountBean);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            gongysAccountBeans = new ArrayList<GongysAccountBean>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return gongysAccountBeans;
    }
    public synchronized int getAnalysisTotalRow(String stationName, String addtime){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from (");
		sql.append("select sum((nvl(an.shibaodl,0))/((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)/0.9*an.daynum*24/1000 * ( (nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)) / sum((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu) as PUE, an.addtime,z.jzname,sum(an.SHIBAODL) SHIBAODL,sum(an.SHEBEIDL) SHEBEIDL from analysis an inner join zhandian z on z.id = an.zdid where  1=1 and  an.daynum is not null and an.daynum!='0' ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(addtime !=null && !addtime.isEmpty()){
			sql.append(" and an.addtime = '"+addtime+"'");
		}
		sql.append(" group by an.addtime,z.jzname ");
		sql.append(" order by an.addtime,z.jzname desc");
		sql.append(") e");
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
    public synchronized ArrayList getAnalysisPage(String stationName, String addtime, Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
//		sql.append("select an.ID,an.BZID,an.ADDTIME,an.CREATETIME,an.SHIBAODL,an.SHEBEIDL," +
//				"an.DBID,an.ZDID,an.STARTTIME,an.ENDTIME,z.JZNAME from analysis an left join zhandian z on z.STATION_NO = an.zdid where 1=1 ");
		sql.append("select sum((nvl(an.shibaodl,0))/((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)/0.9*an.daynum*24/1000 * ( (nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)) / sum((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu) as PUE, an.addtime,z.jzname,sum(an.SHIBAODL) SHIBAODL,sum(an.SHEBEIDL) SHEBEIDL from analysis an inner join zhandian z on z.id = an.zdid where  1=1 and  an.daynum is not null and an.daynum!='0' ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(addtime !=null && !addtime.isEmpty()){
			sql.append(" and an.addtime = '"+addtime+"'");
		}
		sql.append(" group by an.addtime,z.jzname ");
		sql.append(" order by an.addtime,z.jzname desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		System.out.println("PUE分析查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}  
 
    public synchronized int getXSTotalRow(String houseType_st, String addtime){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from (");
		sql.append("select h.id ID,h.ADDTIME,h.MAXZHI,h.MINZHI,h.HOUSETYPE from housexs h where 1=1 ");
		if(houseType_st !=null && !houseType_st.isEmpty()  && !houseType_st.equals("0")){
			sql.append(" and h.HOUSETYPE = '"+houseType_st+"'");
		}
		if(addtime !=null && !addtime.isEmpty()){
			sql.append(" and h.ADDTIME = '"+addtime+"'");
		}
		sql.append(") e");
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
    public synchronized ArrayList getXSPage(String houseType_st, String addtime, Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select h.id ID,h.ADDTIME,h.MAXZHI,h.MINZHI,h.HOUSETYPE from housexs h where 1=1 ");
		if(houseType_st !=null && !houseType_st.isEmpty() && !houseType_st.equals("0")){
			sql.append(" and h.HOUSETYPE = '"+houseType_st+"'");
		}
		if(addtime !=null && !addtime.isEmpty()){
			sql.append(" and h.ADDTIME = '"+addtime+"'");
		}
	//	sql.append(" order by h.ADDTIME desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		System.out.println("房屋系数分析查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
   
	/**
     * 根据电表id获取stationid 直流，交流，站点id
     * @return
     */
    public synchronized List<String> getstationByDbId(String dbid){
    	String stationId="";String zhiliu="";String jiaoliu="";String zdid="";
    	List<String> strList = new ArrayList<String>();
        ArrayList list = new ArrayList();
        String sql = "select z.STATION_NO,d.ZHILIU,d.JIAOLIU,z.ID from dianbiao d inner join zhandian z on d.zdid=z.id where d.id='"+dbid+"'";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==4){
            	stationId = list.get(0).toString();
            	zhiliu = list.get(1).toString();
            	jiaoliu = list.get(2).toString();
            	zdid = list.get(3).toString();
            }
            strList.add(stationId);
            strList.add(zhiliu);
            strList.add(jiaoliu);
            strList.add(zdid);
            
        } catch (Exception e) {
            e.printStackTrace();
             list = new ArrayList();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    /**
     * 根据电表id获取stationid
     * @return
     */
    public synchronized List<String> getstationByDbId2(String dbid){
    	String stationId="";String zhiliu="";String jiaoliu="";String zdid="",housetype="";
    	List<String> strList = new ArrayList<String>();
        ArrayList list = new ArrayList();
        String sql = "select z.STATION_NO,d.ZHILIU,d.JIAOLIU,z.ID,z.HOUSETYPEID from dianbiao d inner join zhandian z on d.zdid=z.id where d.id='"+dbid+"'";
      System.out.println("根据电表id获取电笔信息："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==5){
            	stationId = list.get(0).toString();
            	zhiliu = list.get(1).toString();
            	jiaoliu = list.get(2).toString();
            	zdid = list.get(3).toString();
            	housetype = list.get(4).toString();
            }
            strList.add(stationId);
            strList.add(zhiliu);
            strList.add(jiaoliu);
            strList.add(zdid);
            strList.add(housetype);
            
        } catch (Exception e) {
            e.printStackTrace();
             list = new ArrayList();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    
    
    /**
     *获取最近区间的电流平均值
     *
     */
    public synchronized String  getdianliupue(String st_id){
    	List<String> strList = new ArrayList<String>();
    	String sum="",daySum="0";
		String avd="";
        ArrayList list = new ArrayList();
        String sql = " select to_char( sum(ELECTRICITY),'9999999990.99'),to_char( avg(VALUE),'9999999990.99'),count(1) from RING_ELECTRIC where  STATION_NO='"+st_id+"'";
       
        System.out.println("根据站点id获取站点的电流值 ："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==3){
            	sum = list.get(0).toString();
            	avd = list.get(1).toString();
            	daySum = list.get(2).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            strList = new ArrayList<String>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return avd;
    }
    /**
     * 根据stationid和时间获取设备电量的总和 ，电流平均值，电流平均值天数
     * @return
     */
    public synchronized List<String> getSumByDbId(String st_id,String startTime,String endTime){
    	List<String> strList = new ArrayList<String>();
    	String sum="",avd="",daySum="0";
        ArrayList list = new ArrayList();
        String sql = "select to_char( sum(ELECTRICITY),'9999999990.99'),to_char( avg(VALUE),'9999999990.99'),count(1) from RING_ELECTRIC where updatetime >= to_date('"+startTime+"', 'yyyy-MM-dd') and updatetime <= to_date('"+endTime+"', 'yyyy-MM-dd') and STATION_NO='"+st_id+"'";
       
        System.out.println("根据站点id和时间获取设备电量的总和 ："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==3){
            	sum = list.get(0).toString();
            	avd = list.get(1).toString();
            	daySum = list.get(2).toString();
            }
            strList.add(sum);
            strList.add(avd);
            strList.add(daySum);
        } catch (Exception e) {
            e.printStackTrace();
            strList = new ArrayList<String>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    /**
     * 电表的日均电量
     * @return
     */
    public synchronized String getRjydlByDbId(String dbid,String adddate){

    	String rjydl="";
        ArrayList list = new ArrayList();
        String sql = " select * from (";
        sql =sql+ "select e.RJYDL from ELECTRICFEES e where e.DIANBIAOID='"+dbid+"' and e.CREATEDATE='"+adddate+"' order by e.CREATEDATE desc";
        sql = sql+ ") where rownum<2";
        
        System.out.println("电表的日均电量 ："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==1){
            	rjydl = list.get(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rjydl="";
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return rjydl;
    }
    /**
     * 房屋系数
     * @return
     */
    public synchronized List<String> getXsByDbId(String HOUSETYPE,String adddate){
    	List<String> strList = new ArrayList<String>();
    	String id="",MAXZHI="",MINZHI="";
        ArrayList list = new ArrayList();
        String sql = " select * from (";
        sql = sql + "select h.ID,h.MAXZHI,h.MINZHI from HOUSEXS h  where h.ADDTIME='"+adddate+"' and h.HOUSETYPE='"+HOUSETYPE+"' order by h.ADDTIME desc ";
        sql = sql+ ") where rownum<2";
        
        System.out.println("房屋系数："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==3){
            	id = list.get(0).toString();
            	MAXZHI = list.get(1).toString();
            	MINZHI = list.get(2).toString();
            }
            strList.add(id);
            strList.add(MAXZHI);
            strList.add(MINZHI);
        } catch (Exception e) {
            e.printStackTrace();
            strList = new ArrayList<String>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    /**
     * 房屋系数
     * @return
     */
    public synchronized List<String> getXs_T_By(String HOUSEID,String VALUE){
    	List<String> strList = new ArrayList<String>();
    	String id="",MIN_T="",MAX_T="";
        ArrayList list = new ArrayList();
        String sql = " select * from (";
        sql = sql + "select h.ID,h.MIN_T,h.MAX_T from HOUSEXS_T h " +
        		"where h.HOUSEID ='"+HOUSEID+"' and h.MIN_T<'"+VALUE+"' " +
        				"and h.MAX_T>='"+VALUE+"' order by h.ID desc ";
        sql = sql+ ") where rownum<2";
        System.out.println("房屋系数_T："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==3){
            	id = list.get(0).toString();
            	MIN_T = list.get(1).toString();
            	MAX_T = list.get(2).toString();
            }
            strList.add(id);
            strList.add(MIN_T);
            strList.add(MAX_T);
        } catch (Exception e) {
            e.printStackTrace();
            strList = new ArrayList<String>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    /**
     * 根据登录人账号获取所在的市区县
     * @return
     */
    public synchronized List<String> getAccountByLoginName(String accountName){
    	List<String> strList = new ArrayList<String>();
    	String shi="",xian="",xiaoqu="";
        ArrayList list = new ArrayList();
        String sql = "select SHI,XIAN,XIAOQU from account where ACCOUNTNAME='"+accountName+"'";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query2(rs);
            if(list!=null && list.size()==3){
            	shi = list.get(0).toString();
            	xian = list.get(1).toString();
            	xiaoqu = list.get(2).toString();
            }
            strList.add(shi);
            strList.add(xian);
            strList.add(xiaoqu);
        } catch (Exception e) {
            e.printStackTrace();
            strList = new ArrayList<String>();
        }finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
        return strList;
    }
    
    /**
     * 获取预提金额的数量
     * @param stationName
     * @param addtime
     * @return
     */
    public synchronized int getAdvanceTotalRow(String stationName,String dianbiaoName,String startTime,String endTime){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from ADVANCE AD left join dianbiao d on ad.dbid=d.id " +
				"left join zhandian z on d.zdid=z.id " +
				"left join account ac on ac.accountid=ad.accountid where 1=1 ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(dianbiaoName !=null && !dianbiaoName.isEmpty()){
			sql.append(" and d.dbname like '%"+dianbiaoName+"%'");
		}
		
		if(startTime !=null && !startTime.isEmpty()){
			sql.append(" and ad.ADVTIME >= to_date('"+startTime+"', 'yyyy-MM-dd')");
		}
		if(endTime !=null && !endTime.isEmpty()){
			sql.append(" and ad.ADVTIME <= to_date('"+endTime+"', 'yyyy-MM-dd')");
		}
//		sql.append(" group by an.addtime,z.jzname ");
//		sql.append(" order by an.addtime,z.jzname desc");
		DataBase db = new DataBase();
		int cnt = 0;
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
    
    /**
     * 获取预提金额列表 分页
     * @param page
     * @return
     */
    public synchronized ArrayList getAdvancePage(String stationName,String dianbiaoName,String startTime,String endTime,Page page){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select a.*, rownum rn from (");
		sql.append("select ad.ID,ad.DBID,ad.ADVTIME,ad.ONEID,ad.TWOID,ad.MONEY," +
				"ad.PRICE,ad.DAYNUM,ad.ACCOUNTID," +
				"d.DBNAME DBNAME,z.JZNAME JZNAME,ac.NAME ACCOUNTNAME " +
				"from ADVANCE AD left join dianbiao d on ad.dbid=d.id " +
				"left join zhandian z on d.zdid=z.id " +
				"left join account ac on ac.accountid=ad.accountid where 1=1 ");
		if(stationName !=null && !stationName.isEmpty()){
			sql.append(" and z.jzname like '%"+stationName+"%'");
		}
		if(dianbiaoName !=null && !dianbiaoName.isEmpty()){
			sql.append(" and d.dbname like '%"+dianbiaoName+"%'");
		}
		
		if(startTime !=null && !startTime.isEmpty()){
			sql.append(" and ad.ADVTIME >= to_date('"+startTime+"', 'yyyy-MM-dd')");
		}
		if(endTime !=null && !endTime.isEmpty()){
			sql.append(" and ad.ADVTIME <= to_date('"+endTime+"', 'yyyy-MM-dd')");
		}
//		sql.append(" group by an.addtime,z.jzname ");
//		sql.append(" order by an.addtime,z.jzname desc");
		sql.append(") a where rownum <= ").append(page.getPage()*page.getPageSize()).append(" ) where rn >").append((page.getPage()-1)*page.getPageSize());
		System.out.println("预提金额查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
  //新添房屋系数
  	public synchronized int addXSManager(String CREATETIME,String CREATENAME,
  			String ADDTIME,String MAXZHI,String MINZHI,String HOUSETYPE) {
  		int msg = 0;
  		int id=0;
  		ResultSet rs = null;

  		DataBase db = new DataBase();
  	
  		try {
  			db.connectDb();
  			//2015-2-2
  			rs = null;
  			StringBuffer sql = new StringBuffer();
  			sql.append("INSERT INTO HOUSEXS(CREATETIME, CREATENAME," +
  					" ADDTIME, MAXZHI, MINZHI, HOUSETYPE");
  			sql.append(")");
  			sql.append("VALUES ('"+CREATETIME+"','"+CREATENAME+"','"+
  			ADDTIME+"','"+MAXZHI+"','"+MINZHI+"','"+HOUSETYPE+"')");
  			System.out.println("添加房屋系数sql:"+sql.toString());
  			
  			db.setAutoCommit(false);
  			id = db.update(sql.toString(),new String[]{"ID"});
  			db.commit();
  			db.setAutoCommit(true);
  			msg = 1;
      
  		

  		} catch (DbException de) {
  			try {
  				
  				db.rollback();
  			} catch (DbException dee) {
  				dee.printStackTrace();
  			}
  			de.printStackTrace();
  			id = 0;
  		} finally {
  			
  			try {
  				if(rs != null){
  				rs.close();
  				}
  				if(db != null){
  				db.close();
  				}
  			} catch (Exception de) {
  				de.printStackTrace();
  			}
  		}

  		return id;
  	}
  //修改房屋系数
  	public synchronized int updateXSManager(String id,String CREATETIME,String CREATENAME,
  			String ADDTIME,String MAXZHI,String MINZHI,String HOUSETYPE) {
  		int msg = 0;
  		ResultSet rs = null;

  		DataBase db = new DataBase();
  	
  		try {
  			db.connectDb();
  			//2015-2-2
  			rs = null;
  			StringBuffer sql = new StringBuffer();
  			sql.append("update HOUSEXS set ADDTIME='"+ADDTIME+
  					"',MAXZHI='"+MAXZHI+"',MINZHI='"+MINZHI+"',HOUSETYPE='"+HOUSETYPE+"' " +
  							"where ID='"+id+"'");
  			System.out.println("修改房屋系数sql:"+sql.toString());
  			
  			db.setAutoCommit(false);
  			msg = db.update(sql.toString());
  			db.commit();
  			db.setAutoCommit(true);
  			msg = 1;

  		} catch (DbException de) {
  			try {
  				
  				db.rollback();
  			} catch (DbException dee) {
  				dee.printStackTrace();
  			}
  			de.printStackTrace();
  			msg = 0;
  		} finally {
  			
  			try {
  				if(rs != null){
  				rs.close();
  				}
  				if(db != null){
  				db.close();
  				}
  			} catch (Exception de) {
  				de.printStackTrace();
  			}
  		}

  		return msg;
  	}
  //修改房屋系数
  	public synchronized int delXSManager_T(String id) {
  		int msg = 0;
  		ResultSet rs = null;

  		DataBase db = new DataBase();
  	
  		try {
  			db.connectDb();
  			//2015-2-2
  			rs = null;
  			StringBuffer sql = new StringBuffer();
  			sql.append("delete from housexs_t where HOUSEID='"+id+"'");
  			System.out.println("删除房屋系数_t的sql:"+sql.toString());
  			
  			db.setAutoCommit(false);
  			msg = db.update(sql.toString());
  			db.commit();
  			db.setAutoCommit(true);
  			msg = 1;

  		} catch (DbException de) {
  			try {
  				
  				db.rollback();
  			} catch (DbException dee) {
  				dee.printStackTrace();
  			}
  			de.printStackTrace();
  			msg = 0;
  		} finally {
  			
  			try {
  				if(rs != null){
  				rs.close();
  				}
  				if(db != null){
  				db.close();
  				}
  			} catch (Exception de) {
  				de.printStackTrace();
  			}
  		}

  		return msg;
  	}
  //新添房屋系数
  	public synchronized int addXSManager_T(String HOUSEID, String MIN_T, String MAX_T) {
  		int msg = 0;
  		int id=0;
  		ResultSet rs = null;

  		DataBase db = new DataBase();
  	
  		try {
  			db.connectDb();
  			//2015-2-2
  			rs = null;
  			StringBuffer sql = new StringBuffer();
  			sql.append("INSERT INTO HOUSEXS_T(HOUSEID, MIN_T, MAX_T");
  			sql.append(")");
  			sql.append("VALUES ('"+HOUSEID+"','"+MIN_T+"','"+MAX_T+"')");
  			System.out.println("添加房屋系数_T sql:"+sql.toString());
  			
  			db.setAutoCommit(false);
  			db.update(sql.toString());
  			db.commit();
  			db.setAutoCommit(true);
  			msg = 1;
      
  		

  		} catch (DbException de) {
  			try {
  				
  				db.rollback();
  			} catch (DbException dee) {
  				dee.printStackTrace();
  			}
  			de.printStackTrace();
  			msg = 0;
  		} finally {
  			
  			try {
  				if(rs != null){
  				rs.close();
  				}
  				if(db != null){
  				db.close();
  				}
  			} catch (Exception de) {
  				de.printStackTrace();
  			}
  		}

  		return msg;
  	}
  	 public synchronized int addXSManager_T(String HOUSEID, String MIN_T[], String MAX_T[]){
		 int cnt = 0;
		  ArrayList<String> sqlList = new ArrayList<String>();
		  StringBuffer sql = new StringBuffer();
		 for (int i = 0; i < MIN_T.length; i++) {
			 sql.setLength(0);
			 if(StringUtils.trimToEmpty(MIN_T[i])==null || StringUtils.trimToEmpty(MIN_T[i]).equals("")){
				 MIN_T[i]="0";
			 }
			 if(StringUtils.trimToEmpty(MAX_T[i])==null || StringUtils.trimToEmpty(MAX_T[i]).equals("")){
				 MIN_T[i]="9999999999";
			 }
			 sql.append("INSERT INTO HOUSEXS_T(HOUSEID, MIN_T, MAX_T) values ('"+HOUSEID+"'," +
					"'"+StringUtils.trimToEmpty(MIN_T[i])+"'," +
					"'"+StringUtils.trimToEmpty(MAX_T[i])+"')");
			 sqlList.add(sql.toString());
		}
		 DataBase db = new DataBase();
		 System.out.println("添加房屋系数_T   ---"+ sqlList);
		 try {
			String[] sqlArr = sqlList.toArray(new String[sqlList.size()]);
			db.connectDb();
			db.updateBatch(sqlArr);
			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		 return cnt;
	 }
  	 /***
  	  * 意见审核多选框
  	  * @return
  	 * @throws SQLException 
  	  */
  	public synchronized List<ZhandianBean> getyijianshenhe() throws SQLException {
		List<ZhandianBean> zdList = new ArrayList<ZhandianBean>();
		String sql = "select CODE,INDEXS1 from INDEXS where type = 'bzyj' order by code ";

		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ZhandianBean zd = new ZhandianBean();
				zd.setLONGITUDE(rs.getString("CODE"));
				zd.setLATITUDE(rs.getString("INDEXS1"));
				zdList.add(zd);
			}
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

		return zdList;
	}
  	//------------------首页pue展示需要的方法———————— start
  	 /**
     * 首页获取17地市的平均pue
     * @param addtime
     * @return
     */
    public synchronized ArrayList getAvgAnalysis(String addtime){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select sum((nvl(an.shibaodl,0))/((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)/0.9*an.daynum*24/1000 * ( (nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)) / sum((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu) as PUE," +
				" d.AGCODE AGCODE " +
				"from analysis an inner join zhandian z on z.id = an.zdid " +
				"inner join D_AREA_GRADE d on z.shi = d.AGCODE " +
				"where  1=1 and  an.daynum is not null and an.daynum!='0' and an.value > 0 and an.addtime='"+addtime+"' ");
		sql.append(" group by d.AGCODE ");
		sql.append(" order by d.AGCODE desc");
		System.out.println("全市平均PUE分析查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	} 
    /**
     * 首页获取17地市的平均pue
     * @param addtime
     * @return
     */
    public synchronized ArrayList getAvgAnalysis2(String addtime){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select sum((nvl(an.shibaodl,0))/((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)/0.9*an.daynum*24/1000 * ( (nvl(an.value,0))*54+an.jiaoliu+an.zhiliu)) / sum((nvl(an.value,0))*54+an.jiaoliu+an.zhiliu) as PUE," +
				" d.AGCODE AGCODE " +
				"from analysis an inner join zhandian z on z.id = an.zdid " +
				"inner join D_AREA_GRADE d on z.shi = d.AGCODE " +
				"where  1=1 and  an.daynum is not null and an.daynum!='0' and an.value > 0 and substr(an.addtime, 0, 4)='"+addtime+"' ");
		sql.append(" group by d.AGCODE ");
		sql.append(" order by d.AGCODE desc");
		System.out.println("全市平均PUE分析查询："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	} 
    /**
     * 首页获取17地市
     * @return
     */
    public synchronized List<TopAvgPUEBean> get17Shi(){
		ArrayList list = new ArrayList();
		 List<TopAvgPUEBean> topAvgPUEBeans = new ArrayList<TopAvgPUEBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select agcode AGCODE,agname AGNAME from d_area_grade where agrade=2 order by agcode ");
		System.out.println("首页获取17地市："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				TopAvgPUEBean topAvgPUEBean = new TopAvgPUEBean();
				topAvgPUEBean.setShicode(rs.getString("AGCODE"));
				topAvgPUEBean.setShiname(rs.getString("agname"));
				topAvgPUEBeans.add(topAvgPUEBean);
			}
			
		} catch (Exception e) {
			topAvgPUEBeans = new ArrayList<TopAvgPUEBean>();
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return topAvgPUEBeans;
	} 
  	//------------------首页pue展示需要的方法———————— end
}
