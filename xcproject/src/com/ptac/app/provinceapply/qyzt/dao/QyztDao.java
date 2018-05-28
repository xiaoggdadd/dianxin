package com.ptac.app.provinceapply.qyzt.dao;

import java.util.List;

import com.ptac.app.provinceapply.qyzt.bean.QyztBean;

/**
 * @author lijing
 * @see  °…Í«Î∆Ù”√◊¥Ã¨…Û∫ÀΩ”ø⁄≤„
 */
public interface QyztDao {

	List<QyztBean> query(String string,String shengzt,String loginId);

	boolean CheckFj(String string);

	String check(List<QyztBean> list, String personnal, String cause,String string);

	List<QyztBean> queryCheck(String id);

}
