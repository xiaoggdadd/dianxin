package com.ptac.app.statisticcollect.province.elecfeesanalyse.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.elecfeesanalyse.bean.ElecFeesAnalyseBean;

public interface ElecfeesAnalyseDao {
	/**��ȡ��ѷ�����ϸ��Ϣ
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
	List<ElecFeesAnalyseBean> queryDetails(String[] yearyue,String property,String status,String zzs);
	String[] total(List<ElecFeesAnalyseBean> list);
}
