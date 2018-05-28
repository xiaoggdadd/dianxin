package com.ptac.app.collectanalysis.siteeveryclose.dao;

import java.util.List;

import com.ptac.app.collectanalysis.siteeveryclose.bean.ApplyCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseFeesBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.SiteEveryCloseBean;

/** 站点每月关闭情况统计 接口
 * @author WangYiXiao 2014-12-30
 *
 */
public interface SiteEveryCloseDao {
	/**站点每月关闭情况统计
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<SiteEveryCloseBean> getSiteEveryClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**申请关闭总数
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<ApplyCloseBean> getSiteApplyClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**已审核通过站点关闭总数
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<PassCloseBean> getSitePassClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**已通过的关闭站点总报账电费电量周期
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<PassCloseFeesBean> getSitePassCloseFees(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
}
