package com.ptac.app.collectanalysis.onesitedb.dao;

import java.util.List;

import com.ptac.app.collectanalysis.onesitedb.bean.OneSiteDb;



public interface OneSiteDbDao {
	/**һվ����ѯ
	 * @param shi
	 * @param zdqyzt
	 * @param dbqyzt
	 * @param loginId
	 * @return
	 */
	public List<OneSiteDb> getOneSiteDb(String shi,String zdqyzt,String dbqyzt,String loginId);
}
