package com.noki.zwhd.manage;

import com.noki.zwhd.dao.DxdfftqrDaoImpl;
import com.noki.zwhd.model.DxdfftqrBean;

public class DfManage {

	public boolean saveDxdfftqr(DxdfftqrBean bean) {
		DxdfftqrDaoImpl dao = new DxdfftqrDaoImpl();
		return dao.saveDxdfftqr(bean);
	}
	
}
