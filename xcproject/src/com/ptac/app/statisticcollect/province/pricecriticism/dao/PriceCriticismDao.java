package com.ptac.app.statisticcollect.province.pricecriticism.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.pricecriticism.bean.PriceCriticismBean;

public interface PriceCriticismDao {
	/**
	 * @see  ��۳���վ��ͨ��
	 * @param wherestr ��������
	 * @param cbbly   �������1
	 * @param cbble   �������2
	 * @return list 
	 */
	List<PriceCriticismBean> checkPrice(String wherestr,String cbbly,String cbble);
	/**
	 * @see  ��������֮�� ��������
	 * @param list
	 * @return
	 */
	String[] getSum(List<PriceCriticismBean> list);
}
