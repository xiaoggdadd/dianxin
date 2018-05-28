package com.ptac.app.inportuserzibaodian.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;

public interface InputUserZibaodian {

	

	/**
	 * 自报电用户信息的导入
	 * @param content
	 * @param cform
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Vector input(Vector content, CountForm cform, Account account,String biaozh,HttpServletRequest request, HttpServletResponse response);
}
