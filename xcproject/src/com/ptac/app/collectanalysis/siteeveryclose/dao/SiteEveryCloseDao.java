package com.ptac.app.collectanalysis.siteeveryclose.dao;

import java.util.List;

import com.ptac.app.collectanalysis.siteeveryclose.bean.ApplyCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseFeesBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.SiteEveryCloseBean;

/** վ��ÿ�¹ر����ͳ�� �ӿ�
 * @author WangYiXiao 2014-12-30
 *
 */
public interface SiteEveryCloseDao {
	/**վ��ÿ�¹ر����ͳ��
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<SiteEveryCloseBean> getSiteEveryClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**����ر�����
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<ApplyCloseBean> getSiteApplyClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**�����ͨ��վ��ر�����
	 * @param shi
	 * @param zdsx
	 * @param zdlx
	 * @param beginyue
	 * @param endyue
	 * @param loginId
	 * @return
	 */
	public List<PassCloseBean> getSitePassClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId);
	/**��ͨ���Ĺر�վ���ܱ��˵�ѵ�������
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
