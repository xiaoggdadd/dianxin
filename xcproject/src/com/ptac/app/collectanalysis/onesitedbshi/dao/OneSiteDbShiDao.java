package com.ptac.app.collectanalysis.onesitedbshi.dao;

import java.util.List;

import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShi;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShiDetails;



public interface OneSiteDbShiDao {
	/**һվ����ѯ
	 * @param shi
	 * @param zdqyzt
	 * @param dbqyzt
	 * @param loginId
	 * @return
	 */
	public List<OneSiteDbShi> getOneSiteDb(String shi,String zdqyzt,String dbqyzt,String loginId);
	/** һվ���,���������һվ��� վ����ϸ
	 * @param xian
	 * @param loginId
	 * @return
	 */
	public List<OneSiteDbShiDetails> getOneSiteDbDetails(String xian,String fwzbzw,String zdqyzt,String dbqyzt,String loginId);
}
