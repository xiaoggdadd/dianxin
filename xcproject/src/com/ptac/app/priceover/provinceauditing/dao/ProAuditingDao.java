package com.ptac.app.priceover.provinceauditing.dao;

import java.util.List;

import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.provinceauditing.bean.ProAuditingBean;

/**
 * @author lijing
 * @see ���۳�������˽ӿڲ�
 */
public interface ProAuditingDao {

	List<ProAuditingBean> queryExport(String string, String loginId, String beginbl,String endbl);

	CityCheckBean getInfo(String id, String bzyf);

	int tdUpdate(String id, String loginName);

	int tgUpdate(String id, String loginName);

}
