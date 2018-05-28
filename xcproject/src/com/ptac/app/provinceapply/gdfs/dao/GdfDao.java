package com.ptac.app.provinceapply.gdfs.dao;

import java.util.List;

import com.ptac.app.provinceapply.gdfs.bean.GdfBean;

/**
 * @author lijing
 * @see 省申请供电方式审核接口
 */
public interface GdfDao {

	List<GdfBean> query(String string,String loginId,String shengzt);

	List<GdfBean> queryCheck(String id);

	String check(List<GdfBean> list, String personnal, String cause,
			String bz);

}
