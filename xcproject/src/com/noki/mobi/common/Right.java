package com.noki.mobi.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class Right {
	private String rightId;
	private String name;
	private String url;
	private String memo;
	private String waporweb;

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private String block;

	public Right() {
	}

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getWaporweb() {
		return waporweb;
	}

	public String getBlock() {
		return block;
	}

	public void setWaporweb(String waporweb) {
		this.waporweb = waporweb;
	}

	public void setBlock(String block) {
		this.block = block;
	}
	
	public boolean saveUserMenuids(String accountid,String id) throws DbException{
		boolean flag = false;
		
		DataBase db = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			String sql1 = "select menuids from account where accountid = '"+accountid+"'";
			ps = conn.prepareStatement(sql1.toString());
			rs = ps.executeQuery();
			String menuids = "";
			if (rs != null) {
				while (rs.next()) {
					menuids = rs.getString("menuids");
					String[] menuidList = menuids.split(",");
					if(menuidList.length>=7){
						return false;
					}
				}
			}
			menuids +=","+id;
			
			String sql = "update account set menuids = '"+menuids+"' where accountid = '"+accountid+"'";
			System.out.println(sql);
			flag = !ps.execute(sql);
			conn.commit();
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in saveUserMenuids!" + e);
		} finally {
			db.free(null, ps, conn);
		}
		return flag;
	}

	/**
	 * 查询用户的wap权限
	 * 
	 * @param accountid
	 *            --帐户id号
	 * @return 用户所具有的wap权限
	 * @throws DbException
	 */
	public ArrayList selectWapRightByAccountId(String accountid)
			throws DbException {

		ArrayList rightList = new ArrayList();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb "
					+ " from [right] a,role_right b,account c "
					+ " where a.rightid=b.rightid and b.roleid=c.roleid "
					+ " and c.accountid='"
					+ accountid
					+ "' and a.waporweb='0' ";
			System.out.println("sql:" + sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			// rs = db.queryAll(sql);
			System.out.println("sql:" + sql);
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWapRightByAccountId!" + e);
		} finally {
			db.free(rs, ps, conn);
		}
		return rightList;
	}

	public ArrayList<Right> selectWebRightByAccountId_son(String accountid,
			String fatherID) throws DbException {
		fatherID = fatherID.substring(0,2);
		ArrayList<Right> rightList = new ArrayList<Right>();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			StringBuffer s2 = new StringBuffer();

			s2.append("select roleid from account where accountid=" + accountid);
			System.out.println("s2:" + s2.toString() + " accountid:"
					+ accountid);
			ps = conn.prepareStatement(s2.toString());
			rs = ps.executeQuery();
			String roleid = "";
			if (rs.next()) {
				roleid = rs.getString(1);
			}
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb,a.vorder,a.block,a.icon "
					+ " from right a,role_right b"
					+ " where a.block = '0' and a.rightid = b.rightid and a.rightid like '"+fatherID+"%' and b.roleid in("
					+ roleid + ") order by a.vorder, a.rightid";

			System.out.println("sql:" + sql);
			ps1 = conn.prepareStatement(sql.toString());
			rs = ps1.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					right.setBlock(rs.getString(7));
					right.setIcon(rs.getString(8));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWebRightByAccountId!" + e);
		} finally {
			db.free(null, ps, null);
			db.free(rs, ps1, conn);
		}
		return rightList;
	}

	public ArrayList<Right> selectWebRightByAccountId_father(String accountid)
			throws DbException {
		ArrayList<Right> rightList = new ArrayList<Right>();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			StringBuffer s2 = new StringBuffer();

			s2.append("select roleid from account where accountid=" + accountid);
			System.out.println("s2:" + s2.toString() + " accountid:"
					+ accountid);
			ps = conn.prepareStatement(s2.toString());
			rs = ps.executeQuery();
			// rs = db.queryAll(s2.toString());
			String roleid = "";
			if (rs.next()) {
				roleid = rs.getString(1);
			}
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb,a.vorder,a.block,a.icon "
					+ " from right a,role_right b"
					+ " where a.block = '1' and a.rightid=b.rightid and b.roleid in("
					+ roleid + ") order by a.vorder, a.rightid";

			System.out.println("sql:" + sql);
			ps1 = conn.prepareStatement(sql.toString());
			rs = ps1.executeQuery();
			// rs = db.queryAll(sql);
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					right.setBlock(rs.getString(7));
					right.setIcon(rs.getString(8));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWebRightByAccountId!" + e);
		} finally {
			db.free(null, ps, null);
			db.free(rs, ps1, conn);
		}
		return rightList;
	}

	/**
	 * 查询用户的web权限
	 * 
	 * @param accountid
	 *            --帐户id号
	 * @return 用户所具有的web权限
	 * @throws DbException
	 */
	public ArrayList selectWebRightByAccountId(String accountid)
			throws DbException {

		ArrayList rightList = new ArrayList();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			StringBuffer s2 = new StringBuffer();

			s2.append("select roleid from account where accountid=" + accountid);
			System.out.println("s2:" + s2.toString() + " accountid:"
					+ accountid);
			ps = conn.prepareStatement(s2.toString());
			rs = ps.executeQuery();
			// rs = db.queryAll(s2.toString());
			String roleid = "";
			if (rs.next()) {
				roleid = rs.getString(1);
			}
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb,a.vorder,a.block,a.icon "
					+ " from right a,role_right b"
					+ " where a.rightid=b.rightid and b.roleid in("
					+ roleid
					+ ") order by a.vorder, a.rightid";

			System.out.println("sql:" + sql);
			ps1 = conn.prepareStatement(sql.toString());
			rs = ps1.executeQuery();
			// rs = db.queryAll(sql);
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					right.setBlock(rs.getString(7));
					right.setIcon(rs.getString(8));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWebRightByAccountId!" + e);
		} finally {
			db.free(null, ps, null);
			db.free(rs, ps1, conn);
		}
		return rightList;
	}
	
	/**
	 * 查询用户的web权限
	 * 
	 * @param accountid
	 *            --帐户id号
	 * @return 用户所具有的web权限
	 * @throws DbException
	 */
	public ArrayList selectWebRightByAccountId_byUser_son(String accountid)
			throws DbException {

		ArrayList rightList = new ArrayList();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			
			StringBuffer s2 = new StringBuffer();
			s2.append("select roleid,menuids from account where accountid=" + accountid);
			System.out.println("s2:" + s2.toString() + " accountid:"
					+ accountid);
			ps = conn.prepareStatement(s2.toString());
			rs = ps.executeQuery();
			// rs = db.queryAll(s2.toString());
			String roleid = "";
			String menuids = "";
			if (rs.next()) {
				roleid = rs.getString(1);
				menuids = rs.getString(2);
			}
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb,a.vorder,a.block,a.icon "
					+ " from right a,role_right b"
					+ " where a.block = '0' and a.rightid=b.rightid and a.rightid in("+menuids+") and b.roleid in("
					+ roleid
					+ ") order by a.vorder, a.rightid";

			System.out.println("sql:" + sql);
			ps1 = conn.prepareStatement(sql.toString());
			rs = ps1.executeQuery();
			// rs = db.queryAll(sql);
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					right.setBlock(rs.getString(7));
					right.setIcon(rs.getString(8));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWebRightByAccountId!" + e);
		} finally {
			db.free(null, ps, null);
			db.free(rs, ps1, conn);
		}
		return rightList;
	}
	
	/**
	 * 查询用户的web权限
	 * 
	 * @param accountid
	 *            --帐户id号
	 * @return 用户所具有的web权限
	 * @throws DbException
	 */
	public ArrayList selectWebRightByAccountId_byUser(String accountid)
			throws DbException {

		ArrayList rightList = new ArrayList();
		DataBase db = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			Right right = null;
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			
			StringBuffer s2 = new StringBuffer();
			s2.append("select roleid,menuids from account where accountid=" + accountid);
			System.out.println("s2:" + s2.toString() + " accountid:"
					+ accountid);
			ps = conn.prepareStatement(s2.toString());
			rs = ps.executeQuery();
			// rs = db.queryAll(s2.toString());
			String roleid = "";
			String menuids = "";
			if (rs.next()) {
				roleid = rs.getString(1);
				menuids = rs.getString(2);
			}
			String sql = " select distinct(a.rightid),a.name,a.url,a.memo,a.waporweb,a.vorder,a.block,a.icon "
					+ " from right a,role_right b"
					+ " where a.rightid=b.rightid and a.rightid in("+menuids+") and b.roleid in("
					+ roleid
					+ ") order by a.vorder, a.rightid";

			System.out.println("sql:" + sql);
			ps1 = conn.prepareStatement(sql.toString());
			rs = ps1.executeQuery();
			// rs = db.queryAll(sql);
			if (rs != null) {
				while (rs.next()) {
					right = new Right();
					right.setRightId(rs.getString(1));
					right.setName(rs.getString(2));
					right.setUrl(rs.getString(3));
					right.setMemo(rs.getString(4));
					right.setWaporweb(rs.getString(5));
					right.setBlock(rs.getString(7));
					right.setIcon(rs.getString(8));
					rightList.add(right);
				}
			}
		} catch (Exception e) {
			throw new DbException(
					"查询WAP权限出错dderror in selectWebRightByAccountId!" + e);
		} finally {
			db.free(null, ps, null);
			db.free(rs, ps1, conn);
		}
		return rightList;
	}

}
