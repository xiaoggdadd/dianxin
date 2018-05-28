package com.noki.mobi.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.MD5;

public class Account {
	private String accountId;

	private String accountName;

	private String personTypeId;

	private String name;

	private String position;

	private String memo;

	private String age;

	private String address;

	private String mobile;

	private String email;

	private String sex;

	private String password;

	private String productType;

	private long loginTime;

	private String accountCode;
	
	private String shiname;
	

	private String structid;
	private String roleId;
	private String sheng;
	private String shi;
	private String xian;
	private String roleName;
	private String cthrnumber;
	private String deptNo;//���ű���


	public Account() {
	}

	// ��¼��麯��
	// ����ֵ:0_��ȷ,1_�޴˲���Ա 2_����Ա��������Ч 3_����Ա���벻��ȷ
	public int checkLogin(String typeStr) throws DbException {
		int result = 1;
		DataBase db = null;
		ResultSet rs = null;
		String sqltext = "";
		String czypassword = "";
		String oldpassword = "";
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			sqltext = "SELECT a.accountid,a.accountname,a.name,a.mobile,a.password,a.enable,a.roleid,a.sheng,a.shi,a.xian," +
					"a.rolename,a.cthrnumber, a.bumen "
					+ " FROM account a"
					+ " WHERE a.accountname='"
					+ getAccountName() + "' and a.delsign=1";
			System.out.println("�û���ѯ��" + sqltext);
			ps = conn.prepareStatement(sqltext.toString());
			rs = ps.executeQuery();
			if (rs.next()) { // ����в���Ա
				String enable = rs.getString(6);// ��ȡ�������ֶ�
				if (enable != null && enable.equals("0")) { // �����Ч����Ա �����1��½�ɹ�
					result = 2;
				}else if(typeStr.equals("1")){
					setAccountId(rs.getString(1));
					setAccountName(rs.getString(2));
					setName(rs.getString(3));
					setMobile(rs.getString(4));
					setRoleId(rs.getString(7));
					setSheng(rs.getString(8));
					setShi(rs.getString(9));
					setXian(rs.getString(10));
					setRoleName(rs.getString(11));
					setCthrnumber(rs.getString(12));
					setDeptNo(rs.getString(13));
					result = 0;
					String sql = "update account set register=1 where accountname='"
							+ getAccountName() + "'";
					ps1 = conn.prepareStatement(sql.toString());
					int i = ps1.executeUpdate();
				} else {
					System.out.println("lolo11");
					MD5 md = new MD5();
					czypassword = md.getMD5ofStr(getPassword());// ��ȡbean���������
					System.out.println(czypassword+"     ffffffffssssssssssssss");
					System.out.println("lolo22");
					oldpassword = rs.getString(5);// ��ȡ���ݿ��������

					System.out.println(czypassword + "  " + oldpassword);
					if (czypassword.equals(oldpassword)) { // ���롢����У��ɹ�
						setAccountId(rs.getString(1));
						setAccountName(rs.getString(2));
						setName(rs.getString(3));
						setMobile(rs.getString(4));
						setRoleId(rs.getString(7));
						setSheng(rs.getString(8));
						setShi(rs.getString(9));
						setXian(rs.getString(10));
						setRoleName(rs.getString(11));
						setCthrnumber(rs.getString(12));
						setDeptNo(rs.getString(13));
						result = 0;
						String sql = "update account set register=1 where accountname='"
								+ getAccountName() + "'";
						ps1 = conn.prepareStatement(sql.toString());
						int i = ps1.executeUpdate();
					} else { // ���벻��
						result = 3;
					}
				}
			} else { // ����޲���Ա
				result = 1;
			}
		} catch (Exception e) {
			throw new DbException("У���¼���� error in check user!" + e);

		} finally {
			try {
				db.free(null, ps, null);
				db.free(rs, ps1, conn);
			} catch (Exception e) {
				throw new DbException("�ر����ݿ����error in close db!" + e);
			}

		}

		return result;

	}

	// ��½У��
	public int checkLogin1() throws DbException {
		int result = 1;
		DataBase db = null;
		ResultSet rs = null;
		String sqltext = "";
		String czypassword = "";
		String oldpassword = "";
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			sqltext = "SELECT a.accountid,a.accountname,a.name,a.mobile,a.password,a.enable,a.roleid,a.sheng,a.shi,a.xian,a.rolename,a.CTHRNUMBER "
					+ " FROM account a"
					+ " WHERE a.mobile='"
					+ getAccountName() + "' and a.delsign=1";
			System.out.println("��½У����䣺" + sqltext);
			ps = conn.prepareStatement(sqltext.toString());
			rs = ps.executeQuery();
			if (rs.next()) { // ����в���Ա
				String enable = rs.getString(6);// ��ȡ�������ֶ�
				if (enable != null && enable.equals("0")) { // �����Ч����Ա �����1��½�ɹ�
					result = 2;
				} else {
					MD5 md = new MD5();
					czypassword = md.getMD5ofStr(getPassword());// ��ȡbean���������
					oldpassword = rs.getString(5);// ��ȡ���ݿ��������

					if (czypassword.equals(oldpassword)) { // ���롢����У��ɹ�
						setAccountId(rs.getString(1));
						setAccountName(rs.getString(2));
						setName(rs.getString(3));
						setMobile(rs.getString(4));
						setRoleId(rs.getString(7));
						setSheng(rs.getString(8));
						setShi(rs.getString(9));
						setXian(rs.getString(10));
						setRoleName(rs.getString(11));
						setCthrnumber(rs.getString(12));
						// setRoleId(rs.getString(9));
						result = 0;
						String sql = "update account set register=1 where mobile='"
								+ getMobile() + "'";
						System.out.println("�޸ĵ�½��Ϣ��" + sql);
						ps1 = conn.prepareStatement(sql.toString());
						int i = ps1.executeUpdate();
					} else { // ���벻��
						result = 3;
					}
				}
			} else { // ����޲���Ա
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("chech:" + e);
			throw new DbException("У���¼���� error in check user!" + e);

		} finally {
			try {
				db.free(null, ps, null);
				db.free(rs, ps1, conn);
			} catch (Exception e) {
				throw new DbException("�ر����ݿ����error in close db!" + e);
			}

		}

		return result;

	}
	/**
	 * Sj ���Ӳ��֣�����md5����
	 * @return
	 * @throws DbException
	 */
	public int checkLogin2() throws DbException {
		int result = 1;
		DataBase db = null;
		ResultSet rs = null;
		String sqltext = "";
		String czypassword = "";
		String oldpassword = "";
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;
		try {
			db = new DataBase();
			db.connectDb();
			conn = db.getConnection();
			sqltext = "SELECT a.accountid,a.accountname,a.shi,a.name,a.mobile,a.password,a.enable,a.roleid,a.sheng,a.shi,a.xian,a.rolename,a.CTHRNUMBER "
					+ " FROM account a"
					+ " WHERE a.accountname='"
					+ getAccountName() + "' and a.delsign=1";
			System.out.println("�û���ѯ��" + sqltext);
			try {
				ps = conn.prepareStatement(sqltext.toString());
				rs = ps.executeQuery();
				
				if (rs.next()) { // ����в���Ա
					String enable = rs.getString(6);// ��ȡ�������ֶ�
					if (enable != null && enable.equals("0")) { // �����Ч����Ա �����1��½�ɹ�
						result = 2;
					} else {
						czypassword = getPassword();// ��ȡbean���������
						oldpassword = rs.getString(5);// ��ȡ���ݿ��������

						if (czypassword.equals(oldpassword)) { // ���롢����У��ɹ�
							setAccountId(rs.getString(1));
							setAccountName(rs.getString(2));
							setName(rs.getString(3));
							setMobile(rs.getString(4));
							setRoleId(rs.getString(7));
							setSheng(rs.getString(8));
							setShi(rs.getString(9));
							setXian(rs.getString(10));
							setRoleName(rs.getString(11));
							setShiname(rs.getString(12));
							setCthrnumber(rs.getString(13));
							result = 0;
							String sql = "update account set register=1 where accountname='"
									+ getAccountName() + "'";
							ps1 = conn.prepareStatement(sql.toString());
							int i = ps1.executeUpdate();
							System.out.println("dddddd");
						} else { // ���벻��
							result = 3;
						}
					}
				} else { // ����޲���Ա
					result = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  finally {
			try {
				db.free(null, ps, null);
				db.free(rs, ps1, conn);
			} catch (Exception e) {
				throw new DbException("�ر����ݿ����error in close db!" + e);
			}

		}

		return result;

	}

	public int getTuichu(String loginId) throws Exception {

		DataBase db = new DataBase();
		db.connectDb();
		String sql = "update account set register='0' where accountid='"
				+ loginId + "'";
		int number = db.update(sql);
		db.close();
		return number;
	}

	public String getNumber() throws Exception {

		String number = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		db.connectDb();
		String sql = "select count(*) from account where register='1'";
		rs = db.queryAll(sql);
		while (rs.next()) {
			number = rs.getString(1);
		}
		rs.close();
		db.close();
		return number;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPersonTypeId() {
		return personTypeId;
	}

	public void setPersonTypeId(String personTypeId) {
		this.personTypeId = personTypeId;
	}

	public String getName() {
		return name;
	}

	public String getShiname() {
		return shiname;
	}

	public void setShiname(String shiname) {
		this.shiname = shiname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getStructId() {
		return structid;
	}

	public void setStructId(String structid) {
		this.structid = structid;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getSheng() {
		return sheng;
	}

	public String getShi() {
		return shi;
	}

	public String getXian() {
		return xian;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCthrnumber() {
		return cthrnumber;
	}

	public void setCthrnumber(String cthrnumber) {
		this.cthrnumber = cthrnumber;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

}
