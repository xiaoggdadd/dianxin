package com.ptac.app.electricmanage.liuchenghaobill.dao;

import java.util.List;

import com.ptac.app.electricmanage.liuchenghaobill.bean.LiuchBean;

public interface LiuchDao {

	List<LiuchBean> query(String string, String loginId);
	List<LiuchBean> xiangxi(String string, String str);
	List<LiuchBean> xiangxift(String liuch);
	String[] getSum(List<LiuchBean> list);

}
