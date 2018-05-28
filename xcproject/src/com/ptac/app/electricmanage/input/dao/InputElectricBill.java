package com.ptac.app.electricmanage.input.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;

/**
 * @author GT 初始化：开始db 最终销毁db 电费导入
 */
public interface InputElectricBill {

	

	/**
	 * 电费单的导入
	 * @param content
	 * @param cform
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Vector input(Vector content, CountForm cform, Account account,HttpServletRequest request, HttpServletResponse response);
}
