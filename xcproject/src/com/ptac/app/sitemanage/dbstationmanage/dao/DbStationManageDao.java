package com.ptac.app.sitemanage.dbstationmanage.dao;

import java.util.List;

import com.ptac.app.sitemanage.dbstationmanage.bean.DbStationManageBean;

/**���վ��ά��--��ѯ,����
 * @author WangYiXiao 2014-12-16
 *
 */
public interface DbStationManageDao {

	/**���վ��ά��--վ����Ϣ��ѯ
	 * @param whereStr (String) ��������
	 * @return (List<DbStationManageBean>) վ����Ϣ
	 */
	List<DbStationManageBean> getStationInfo(String whereStr,String loginId);
	/**���վ��ά��--���Ķ��״̬
	 * @param wherezdid (String) վ��ID
	 * @param status (String) ���״̬
	 * @return
	 */
	String checkStatus(String wherezdid,String status);
}
