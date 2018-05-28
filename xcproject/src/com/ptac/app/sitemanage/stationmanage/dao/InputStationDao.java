package com.ptac.app.sitemanage.stationmanage.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;

/**
 * @author WangYiXiao 2014-9-19 初始化：开始db 最终销毁db 站点导入
 */
public interface InputStationDao {

	

	/**
	 * 站点的导入
	 * @param content
	 * @param cform
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Vector input(Vector content, CountForm cform, Account account,HttpServletRequest request, HttpServletResponse response);
}
