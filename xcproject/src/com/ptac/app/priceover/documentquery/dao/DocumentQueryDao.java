package com.ptac.app.priceover.documentquery.dao;

import java.util.List;


import com.ptac.app.priceover.documentquery.bean.*;

/**
 * @author lijing
 * @see ���۳�������˽ӿڲ�
 */
public interface DocumentQueryDao {

	List<DocumentQueryBean> queryExport(String string, String loginId, String beginbl,String endbl);



}
