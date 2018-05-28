package com.ptac.app.checksign.financecheck.dao;

import com.noki.electricfees.javabean.ElectricFeesFormBean;

/**
 * 用来做电费审核的接口
 * @author rock
 *
 */
public interface FinanceCheckDao {
	/**
	 * 审核电费信息的方法
	 * @author rock
	 * @param formBean (ElectricFeesFormBean) 电费信息对象
	 * @param chooseIdStr1(String) 电费的UUID字符串
	 * @param chooseIdStr2(String) 预付费的UUID字符串
	 * @param bz(String) 审核标志
	 * @return String 审核信息（成功/失败）
	 */
	String modifyCheckFees(ElectricFeesFormBean formBean,String chooseIdStr1,String chooseIdStr2,String bz,String kjyf);
}
