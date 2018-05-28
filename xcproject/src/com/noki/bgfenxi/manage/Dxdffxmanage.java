package com.noki.bgfenxi.manage;

import java.sql.SQLException;
import java.util.List;

import com.noki.bgfenxi.Dao.DxdffxDaoImpl;
import com.noki.bgfenxi.Model.DxdffxBean;
import com.noki.database.DbException;
import com.noki.zwhd.dao.DxdfftqrDaoImpl;
import com.noki.zwhd.model.DxdfftqrBean;

public class Dxdffxmanage {
	public List<DxdffxBean> searchWydfft_dxdfftqr(String loginName,String loginId, int pageSize, int curpage, String sheng,String shi, String xian, String xiaoqu, String yysqrzt,String pc) {
		DxdffxDaoImpl dao = new DxdffxDaoImpl();
		return dao.searchWydfft_dxdfftqr(loginName, loginId, pageSize, curpage,sheng, shi, xian, xiaoqu, yysqrzt,pc);
	}
	public boolean adddxfx(DxdffxBean bean) throws DbException{
		DxdffxDaoImpl dao = new DxdffxDaoImpl();
		return dao.adddxfx(bean);
	}
	public boolean saveDxdffx(DxdffxBean bean) {
		DxdffxDaoImpl dao = new DxdffxDaoImpl();
		return dao.UpdateDxdffx(bean);
	}
	public List<DxdffxBean> searchdxdfftqr(String loginName,String loginId, int pageSize, int curpage, String sheng,String shi, String xian, String xiaoqu, String zdmc,String zdbm) throws DbException, SQLException {
		DxdffxDaoImpl dao = new DxdffxDaoImpl();
		return dao.searchdxdfftqr(loginName, loginId, pageSize, curpage,sheng, shi, xian, xiaoqu, zdmc,zdbm);
	}
}
