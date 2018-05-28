package com.ptac.app.statisticcollect.province.accountwithout.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;

public interface AccountWithoutDao {

	List<AccountWithoutBean> queryExport(String string, String loginId);

	String[] getSum(List<AccountWithoutBean> list);

}
