package com.ptac.app.calibstat.huopiaolv.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ptac.app.calibstat.huopiaolv.bean.HuoPiaoLv;

/**��Ʊ��dao
 * @author WangYiXiao 2014-10-15
 *
 */
public interface HuoPiaoLvDao {
	/** ���
	 * @param list
	 * @return
	 */
	BigDecimal[] total(List<HuoPiaoLv> list);
	
	/**��Ʊ�ʲ�ѯ 
	 * @author WangYiXiao 2014-10-15
	 * @param shi
	 * @param begintime
	 * @param endtime
	 * @param loginId
	 * @return
	 */
	List<HuoPiaoLv>getHuoPiaoLv(String sheng,String shi,String begintime,String endtime,String loginId);

}
