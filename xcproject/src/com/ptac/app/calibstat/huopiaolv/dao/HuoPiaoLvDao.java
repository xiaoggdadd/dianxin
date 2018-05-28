package com.ptac.app.calibstat.huopiaolv.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ptac.app.calibstat.huopiaolv.bean.HuoPiaoLv;

/**获票率dao
 * @author WangYiXiao 2014-10-15
 *
 */
public interface HuoPiaoLvDao {
	/** 求和
	 * @param list
	 * @return
	 */
	BigDecimal[] total(List<HuoPiaoLv> list);
	
	/**获票率查询 
	 * @author WangYiXiao 2014-10-15
	 * @param shi
	 * @param begintime
	 * @param endtime
	 * @param loginId
	 * @return
	 */
	List<HuoPiaoLv>getHuoPiaoLv(String sheng,String shi,String begintime,String endtime,String loginId);

}
