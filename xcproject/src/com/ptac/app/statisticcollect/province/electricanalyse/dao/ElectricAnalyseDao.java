package com.ptac.app.statisticcollect.province.electricanalyse.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.electricanalyse.bean.ElectricAnalyseBean;

public interface ElectricAnalyseDao {
	/**��ȡ����������ϸ��Ϣ
	 * @return
	 */
	/**
	 * @param yearyue �·���Ϣ
	 * @param property վ������
	 * @param status ���״̬
	 * @param zzs �Ƿ������ֵ˰
	 * @param loginid �û�id
	 * @return
	 */
	List<ElectricAnalyseBean> queryDetails(String[] yearyue,String property,String status,String zzs);
	String[] total(List<ElectricAnalyseBean> list);
}
