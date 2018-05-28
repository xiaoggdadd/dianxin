package com.ptac.app.priceover.documentquery.dao;

import java.util.List;


import com.ptac.app.priceover.documentquery.bean.*;

/**
 * @author lijing
 * @see 单价超标市审核接口层
 */
public interface DocumentQueryDao {

	List<DocumentQueryBean> queryExport(String string, String loginId, String beginbl,String endbl);



}
