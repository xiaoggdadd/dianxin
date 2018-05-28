package com.ptac.app.calibstat.movefixedelec.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ptac.app.calibstat.movefixedelec.bean.MoveFixedElec;

/**������������ͳ��
 * @author WangYiXiao 2014-10-14
 *
 */
public interface MoveFixedElecDao {
	public BigDecimal[] total(List<MoveFixedElec> list);
	/**������������ͳ�Ʋ�ѯ
	 * @param shi ��
	 * @param begintime ��ʼ�·�
 	 * @param endtime �����·�
	 * @return
	 */
	List<MoveFixedElec> getMoveFixedFee(String sheng,String shi,String begintime,String endtime,String[] time,String loginid);
}
