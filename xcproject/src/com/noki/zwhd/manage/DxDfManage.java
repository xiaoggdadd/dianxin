package com.noki.zwhd.manage;

import com.noki.bgfenxi.Model.DxdffxBean;
import com.noki.zwhd.dao.DxdfftqrDaoImpl;
import com.noki.zwhd.dao.WyDaoImpl;
import com.noki.zwhd.dao.YddfftqrDaoImpl;
import com.noki.zwhd.model.DxdfftqrBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class DxDfManage {
	public boolean saveDxdfftqr(DxdfftqrBean bean) {
		DxdfftqrDaoImpl dao = new DxdfftqrDaoImpl();
		return dao.updateDxdfftqr(bean);
	}
	public WydfftBean searchWydfft(String yearmonth,String zdbm,String dh,String kh){
		DxdfftqrDaoImpl dao = new DxdfftqrDaoImpl();
		return dao.searchWydfft(yearmonth,zdbm,dh,kh);
	}
	public boolean updateWydfftdx(WydfftBean wydfft) {
		DxdfftqrDaoImpl dao = new DxdfftqrDaoImpl();
		return dao.updateWydfftyd(wydfft);
	}
}
