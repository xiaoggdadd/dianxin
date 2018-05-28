package com.ptac.app.calibstat.movefixedelec.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ptac.app.calibstat.movefixedelec.bean.MoveFixedElec;

/**移网固网电量统计
 * @author WangYiXiao 2014-10-14
 *
 */
public interface MoveFixedElecDao {
	public BigDecimal[] total(List<MoveFixedElec> list);
	/**移网固网电量统计查询
	 * @param shi 市
	 * @param begintime 开始月份
 	 * @param endtime 结束月份
	 * @return
	 */
	List<MoveFixedElec> getMoveFixedFee(String sheng,String shi,String begintime,String endtime,String[] time,String loginid);
}
