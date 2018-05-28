package com.ptac.app.collectanalysis.onesitedbshi.dao;

import java.util.List;

import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShi;
import com.ptac.app.collectanalysis.onesitedbshi.bean.OneSiteDbShiDetails;



public interface OneSiteDbShiDao {
	/**一站多表查询
	 * @param shi
	 * @param zdqyzt
	 * @param dbqyzt
	 * @param loginId
	 * @return
	 */
	public List<OneSiteDbShi> getOneSiteDb(String shi,String zdqyzt,String dbqyzt,String loginId);
	/** 一站多表,非外租回收一站多表 站点明细
	 * @param xian
	 * @param loginId
	 * @return
	 */
	public List<OneSiteDbShiDetails> getOneSiteDbDetails(String xian,String fwzbzw,String zdqyzt,String dbqyzt,String loginId);
}
