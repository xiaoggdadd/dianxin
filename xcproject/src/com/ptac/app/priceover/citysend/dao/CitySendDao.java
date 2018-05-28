package com.ptac.app.priceover.citysend.dao;

import java.util.List;

import com.ptac.app.priceover.citysend.bean.CitySendBean;

/**
 * @author lijing
 * @see 地市签收及派单接口层
 */
public interface CitySendDao {

	List<CitySendBean> queryExport(String string, String loginId, String beginbl,String endbl);

	int pdUpdate(String id, String loginName);

	List<CitySendBean> getInfo(String zdid, String bzyf);

}
