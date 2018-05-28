package com.ptac.app.calibstat.dao;

import java.util.List;

import com.ptac.app.calibstat.bean.OverSite;
import com.ptac.app.calibstat.bean.OverSiteDetial;

/** 超标站点查询
 * @author WangYiXiao 2014-9-28
 *
 */
public interface OverSiteDao {
	/**超标站点查询
	 * @return
	 */
	List<OverSite> getOverSite(String shi,String month,String property,String zdlx,String cbmc,String cbbl,String cbjdz,String accountid);
	/**超标站点明细查询之站点信息
	 * @return
	 */
	OverSiteDetial getOverSiteDetial(String zdid);
	/**超标站点明细查询之各月省定标
	 * @return
	 */
	OverSiteDetial getOverSiteSDB(String zdid);
	/**超标站点明细查询之电量信息
	 * @return
	 */
	List<OverSiteDetial> getOverSiteDL(String zdid);

}
