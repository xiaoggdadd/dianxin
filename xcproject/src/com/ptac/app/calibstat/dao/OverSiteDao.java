package com.ptac.app.calibstat.dao;

import java.util.List;

import com.ptac.app.calibstat.bean.OverSite;
import com.ptac.app.calibstat.bean.OverSiteDetial;

/** ����վ���ѯ
 * @author WangYiXiao 2014-9-28
 *
 */
public interface OverSiteDao {
	/**����վ���ѯ
	 * @return
	 */
	List<OverSite> getOverSite(String shi,String month,String property,String zdlx,String cbmc,String cbbl,String cbjdz,String accountid);
	/**����վ����ϸ��ѯ֮վ����Ϣ
	 * @return
	 */
	OverSiteDetial getOverSiteDetial(String zdid);
	/**����վ����ϸ��ѯ֮����ʡ����
	 * @return
	 */
	OverSiteDetial getOverSiteSDB(String zdid);
	/**����վ����ϸ��ѯ֮������Ϣ
	 * @return
	 */
	List<OverSiteDetial> getOverSiteDL(String zdid);

}
