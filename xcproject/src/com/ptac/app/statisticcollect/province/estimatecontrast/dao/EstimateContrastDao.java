package com.ptac.app.statisticcollect.province.estimatecontrast.dao;

import java.util.List;

import com.ptac.app.statisticcollect.province.estimatecontrast.bean.EstimateContrastBean;

/**ȷ�����ݹ����ݶ��ղ�ѯDao
 * @author WangYiXiao 2014-4-29
 *
 */
public interface EstimateContrastDao {
/**��ѯ
 * @param zangumonth (String) �ݹ��·�
 * @return
 */
List<EstimateContrastBean> query(String zangumonth);
}
