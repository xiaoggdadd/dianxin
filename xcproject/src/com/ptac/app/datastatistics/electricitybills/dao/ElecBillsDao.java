package com.ptac.app.datastatistics.electricitybills.dao;

import java.util.List;
import com.ptac.app.datastatistics.electricitybills.bean.ElecBillsBean;

/**
 * @author �
 * @see ��ѽ�����ϸDao��
 */
public interface ElecBillsDao {

	/**
	 * @author �
	 * @see ��ѽ�����ϸ��ѯ��Ѻϼ�
	 */
	ElecBillsBean query(List<ElecBillsBean> list);

	/**
	 * @author �
	 * @see ��ѽ�����ϸ����
	 */
	List<ElecBillsBean> export(String whereStr, String loginId);

}
