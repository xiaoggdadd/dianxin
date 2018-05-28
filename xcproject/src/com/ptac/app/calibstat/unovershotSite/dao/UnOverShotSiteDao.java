package com.ptac.app.calibstat.unovershotSite.dao;

import java.util.List;

import com.noki.mobi.common.zdbzbeanB;

public interface UnOverShotSiteDao {
	 /** ≤È—ØŒ¥≥¨±Í’æµ„
	 * @param whereStr
	 * @param str
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @param str5
	 * @param loginId
	 * @return
	 */
	List<zdbzbeanB> getPageDatauncbcx(String whereStr,String str,String str1,String str2,String str3,String str4,String str5,String loginId);
}
