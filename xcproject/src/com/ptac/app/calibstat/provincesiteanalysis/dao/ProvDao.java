package com.ptac.app.calibstat.provincesiteanalysis.dao;

import java.util.List;
import com.ptac.app.calibstat.provincesiteanalysis.bean.ProvBean;

/**
 * @author �
 * @see ȫʡվ�㳬������ӿڲ�
 */
public interface ProvDao {

	/**
	 * @see ȫʡվ�㳬����� ��ѯ������
	 */
	List<ProvBean> getQueryExport(String str, String loginId);

	/**
	 * @see ȫʡվ�㳬���������ͼ����Ϣ
	 */
	ProvBean getXiangXi(String str, String loginId);

}
