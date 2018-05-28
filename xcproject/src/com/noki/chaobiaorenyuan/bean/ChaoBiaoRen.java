package com.noki.chaobiaorenyuan.bean;

public class ChaoBiaoRen {
	private String id;
	private String username;
	private String password;
	private String loginname;
	private String createtime;
	private String createuser;
	private String updatetime;
	private String updateuser;
	private int deleteflag;
	
	public ChaoBiaoRen() {
		super();
	}

	public ChaoBiaoRen(String id, String username, String password,
			String loginname, int deleteflag) {
		//JSONObject result = new JSONObject();
		//result.put("flag", false);
		//result.put("msg", "查询失败");
		//result.toString();
		//{flag:false,msg:'密码不正确'}
		//resp.getWriter().write("");
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.loginname = loginname;
		this.deleteflag = deleteflag;
	}

	public ChaoBiaoRen(String id, String username, String password,
			String loginname, String createtime, String createuser,
			String updatetime, String updateuser, int deleteflag) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.loginname = loginname;
		this.createtime = createtime;
		this.createuser = createuser;
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.deleteflag = deleteflag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}
	
}
