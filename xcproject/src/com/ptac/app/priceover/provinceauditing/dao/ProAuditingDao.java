package com.ptac.app.priceover.provinceauditing.dao;

import java.util.List;

import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.provinceauditing.bean.ProAuditingBean;

/**
 * @author lijing
 * @see 单价超标市审核接口层
 */
public interface ProAuditingDao {

	List<ProAuditingBean> queryExport(String string, String loginId, String beginbl,String endbl);

	CityCheckBean getInfo(String id, String bzyf);

	int tdUpdate(String id, String loginName);

	int tgUpdate(String id, String loginName);

}
