package com.noki.zwhd.manage;

import java.util.ArrayList;
import java.util.List;

import com.noki.zwhd.dao.CwDaoImpl;
import com.noki.zwhd.dao.WyDaoImpl;
import com.noki.zwhd.model.CwybxBean;
import com.noki.zwhd.model.HdjlBean;

public class CwManage {
	
	public boolean insertCwdfft(CwybxBean cwybx){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.saveCwybx(cwybx);
	}
	
	public List<HdjlBean> searchCwbxhdjl(){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwbxhdjl();
	}
	
	public boolean saveCwybxHdjl(String loginName,String yearmonth){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.saveCwybxHdjl(loginName,yearmonth);
	}

	public boolean saveCwybx(CwybxBean cwybx) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.saveCwybx(cwybx);
	}
	
	public List<CwybxBean> searchCwybx_fy(String loginName,String loginId,int pageSize,int curpage,String sheng,
			String shi, String xian, String xiaoqu,String hdzt,String qrzt,String pc,String zdbm,String dh,String cy){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwybx_fy(loginName,loginId,pageSize,curpage,sheng,shi,xian,xiaoqu,hdzt,qrzt,pc,zdbm,dh,cy);
	}
	
	public List<CwybxBean> searchCwybx(String yearmonth){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwybx(yearmonth);
	}

	public boolean updateCwybx(String cwybx_id,String cwybx_cwyfthydcwybxje, String cwybx_cy,
			String cwybx_wyftje, String cwybx_fsyz,String cwybx_bz) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.updateCwybx(cwybx_id,cwybx_cwyfthydcwybxje,cwybx_cy,cwybx_wyftje,cwybx_fsyz,cwybx_bz);
	}
	
	public boolean updateCwybx_shzt(String yearmonth){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.updateCwybx_shzt(yearmonth);
	}

	public boolean isCwybxCf(String yearmonth, String zdbm, String jsdh,
			String djph, String fphsje, String fpbhsje) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.isCwybxCf(yearmonth,zdbm,jsdh,djph,fphsje,fpbhsje);
	}

	public boolean updateCwdfft(CwybxBean cwybx) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.updateCwdfft(cwybx);
	}

	public CwybxBean searchCwybxByWyid(String id){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwybxByWyid(id);
	}
	
	public boolean deleteCwybxById(String id){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.deleteCwybxById(id);
	}
	
	public boolean deleteCwybxByIds(String ids){
		CwDaoImpl dao = new CwDaoImpl();
		String[] idList = ids.split(",");
		for(int i=0;i<idList.length;i++){
			String id = idList[i];
			dao.deleteCwybxById(id);
		}
		return true;
	}

	public List<CwybxBean> searchCwybxByWydfft(String cwYearMonth,
			String wydfft_dh, String wydfft_zdbm) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwybxByWydfft(cwYearMonth,wydfft_dh,wydfft_zdbm);
	}

	public boolean deleteCwybxByPc(String pc) {
		CwDaoImpl dao = new CwDaoImpl();
		return dao.deleteCwybxByPc(pc);
	}
	
	public List<CwybxBean> searchCwybx_fy(String zdbm){
		CwDaoImpl dao = new CwDaoImpl();
		return dao.searchCwybx_fy(zdbm);
	}
}
