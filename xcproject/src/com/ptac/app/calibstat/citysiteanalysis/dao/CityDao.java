package com.ptac.app.calibstat.citysiteanalysis.dao;
import java.util.List;
import com.ptac.app.calibstat.citysiteanalysis.bean.CityBean;

/**
 * @author �
 * @see ����վ�㳬������ӿڲ�
 */
public interface CityDao {

	/**
	 * @see ����վ�㳬���������ͼ����Ϣ
	 */
	CityBean getXiangXi(String str, String loginId);

	/**
	 * @see ����վ�㳬����� ��ѯ������
	 */
	List<CityBean> getQueryExport(String str, String loginId);

}
