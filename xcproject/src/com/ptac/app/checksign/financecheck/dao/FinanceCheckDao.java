package com.ptac.app.checksign.financecheck.dao;

import com.noki.electricfees.javabean.ElectricFeesFormBean;

/**
 * �����������˵Ľӿ�
 * @author rock
 *
 */
public interface FinanceCheckDao {
	/**
	 * ��˵����Ϣ�ķ���
	 * @author rock
	 * @param formBean (ElectricFeesFormBean) �����Ϣ����
	 * @param chooseIdStr1(String) ��ѵ�UUID�ַ���
	 * @param chooseIdStr2(String) Ԥ���ѵ�UUID�ַ���
	 * @param bz(String) ��˱�־
	 * @return String �����Ϣ���ɹ�/ʧ�ܣ�
	 */
	String modifyCheckFees(ElectricFeesFormBean formBean,String chooseIdStr1,String chooseIdStr2,String bz,String kjyf);
}
