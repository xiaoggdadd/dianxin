package com.ptac.app.systemmanage.dao;

import java.util.List;

import com.ptac.app.systemmanage.bean.CountUsers;
import com.ptac.app.systemmanage.bean.CountUsersInfo;

public interface CountUsersDao {
	public List<CountUsers> queryCountUsers(String whereStr, String loginId);
	public List<CountUsersInfo> queryInfo(String whereStr);
}
