package com.ptac.app.elecquantitymanage.elecquantitylist.input.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;

/**
 * @see 电量导入
 * @author WangYiXiao 2014-4-24
 *
 */
public interface InputElecQuantity {
	/**
	 * @param content
	 * @param cform
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Vector input(Vector content, CountForm cform, Account account,HttpServletRequest request, HttpServletResponse response);
}
