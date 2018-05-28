package com.ptac.app.electricmanage.input.dao;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;

/**
 * @author WangYiXiao ��ʼ������ʼdb ��������db ��ѵ���
 */
public interface InputEnhanceElectricBill {

	

	/**
	 * ��ѵ��ĵ���
	 * @param content
	 * @param cform
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Vector input(Vector content, CountForm cform, Account account,HttpServletRequest request, HttpServletResponse response);
}
