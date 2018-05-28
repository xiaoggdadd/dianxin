package com.noki.chaobiaorenyuan.bean;

public class ChaoBiaoRen_new {
	private int accountid;		//抄表人员ID
	private String accountname;	//抄表人员登录名
	private String name;		//抄表人员性名
	private String password;	//抄表人员登录密码
	private String roleid;		//角色id
	private String rolename;	//角色名称
	private int delsign; 		//删除标志(1显示0不显示)
	private String sheng;		//抄表人员性名
	private String shi;			//抄表人员登录密码
	private String xian;		//角色id
	private String xiaoqu;		//角色名称
	
	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getXian() {
		return xian;
	}

	public void setXian(String xian) {
		this.xian = xian;
	}

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public ChaoBiaoRen_new() {
		super();
	}

	public ChaoBiaoRen_new(int accountid, String accountname, String name,
			String password, String roleid, String rolename, int delsign) {
		super();
		this.accountid = accountid;
		this.accountname = accountname;
		this.name = name;
		this.password = password;
		this.roleid = roleid;
		this.rolename = rolename;
		this.delsign = delsign;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getDelsign() {
		return delsign;
	}

	public void setDelsign(int delsign) {
		this.delsign = delsign;
	}
	
	
}
