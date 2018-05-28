package com.ptac.app.statisticcollect.city.electricanalyse.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.electricanalyse.bean.ElectricAnalyseBean;

public interface ElectricAnalyseDao {
	/**��ȡ����������ϸ��Ϣ
	 * @return
	 */
	/**
	 * @param yearyue �·���Ϣ
	 * @param property վ������
	 * @param status ���״̬
	 * @param zzs �Ƿ������ֵ˰
	 * @param shicode ��
	 * @param loginid �û�id
	 * @return
	 */
	List<ElectricAnalyseBean> queryDetails(String[] yearyue,String property,String status,String zzs,String shicode,String loginid);
	String[] total(List<ElectricAnalyseBean> list);
}
