package com.ptac.app.statisticcollect.province.electricitycriticism.dao;

import java.util.List;
import com.ptac.app.statisticcollect.province.electricitycriticism.bean.EleCriticismBean;

public interface EleCriticismDao {
	/**
	 * @see  ��������վ��ͨ��
	 * @param wherestr ��������
	 * @param cbbly   �������1
	 * @param cbble   �������2
	 * @return list 
	 */
	List<EleCriticismBean> checkPrice(String wherestr,String cbbly,String cbble);
	/**
	 * @see  ��������֮�� ��������
	 * @param list
	 * @return
	 */
	String[] getSum(List<EleCriticismBean> list);
}
