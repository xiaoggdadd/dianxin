package com.ptac.app.statisticcollect.city.elecfeesanalyse.dao;

import java.util.List;

import com.ptac.app.statisticcollect.city.elecfeesanalyse.bean.ElecFeesAnalyseBean;

public interface ElecfeesAnalyseDao {
	/**��ȡ��ѷ�����ϸ��Ϣ
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
	List<ElecFeesAnalyseBean> queryDetails(String[] yearyue,String property,String status,String zzs,String shicode,String loginid);
	String[] total(List<ElecFeesAnalyseBean> list);
}
