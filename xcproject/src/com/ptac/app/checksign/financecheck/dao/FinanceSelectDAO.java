package com.ptac.app.checksign.financecheck.dao;

import java.util.List;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.ptac.app.checksign.financecheck.bean.FinanceSelectBean;

/**
 * ��ѯ���ݺ�����������
 * @author rock
 */
public interface FinanceSelectDAO {
	/**
	 * ������ѯ����
	 * @author rock
	 * @param fsb FinanceSelectBean��������ѯ���ݵ�JavaBean
	 * @return List<ElectricFeesFormBean> ����һ��ElectricFeesFormBean�ļ���
	 */
	List<ElectricFeesFormBean> MessageSelect(FinanceSelectBean fsb);//���ݲ�ѯ
	
	/**
	 * �����������ķ���
	 * @param whereStr
	 * @param loginId
	 * @param str1
	 * @param str2
	 * @return
	 */
	List<ElectricFeesFormBean> daochu(String whereStr,String loginId,String str1,String str2);//������ѯ
}
