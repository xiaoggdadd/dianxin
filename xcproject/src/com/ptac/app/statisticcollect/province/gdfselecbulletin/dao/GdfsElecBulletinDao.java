package com.ptac.app.statisticcollect.province.gdfselecbulletin.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.gdfselecbulletin.bean.GdfsElecBulletinBean;

public interface GdfsElecBulletinDao {
	/**��ȡ���緽ʽ����ͨ����ϸ��Ϣ
	 * @return
	 */
	/**
	 * @param yearyue �·���Ϣ
	 * @param property վ������
	 * @param status ���״̬
	 * @param zzs �Ƿ������ֵ˰
	 * @return
	 */
	List<GdfsElecBulletinBean> queryDetails(String yearyue,String property,String status);
	
	/** �ϼ�
	 * @param list
	 * @return
	 */
	String[] total(List<GdfsElecBulletinBean> list);
	
}
