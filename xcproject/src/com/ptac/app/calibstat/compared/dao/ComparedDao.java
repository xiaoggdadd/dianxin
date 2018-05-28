package com.ptac.app.calibstat.compared.dao;

import java.util.List;
import com.ptac.app.calibstat.compared.bean.ComparedBean;

/**
 * @author 李靖
 * @see 建议生产标与生产标对比查询接口层
 */
public interface ComparedDao {

	List<ComparedBean> queryExport(String str, String loginId);

}
