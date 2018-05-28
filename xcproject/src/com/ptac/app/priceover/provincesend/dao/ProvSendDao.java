package com.ptac.app.priceover.provincesend.dao;

import java.util.List;
import com.ptac.app.priceover.provincesend.bean.ProvSendBean;

/**
 * @author lijing
 * @see 单价超标省派单接口层
 */
public interface ProvSendDao {

	List<ProvSendBean> queryExport(String string, String bl);

	int pdUpdate(String id, String loginName);

	List<ProvSendBean> getInfo(String zdid,String bzyf);

}
