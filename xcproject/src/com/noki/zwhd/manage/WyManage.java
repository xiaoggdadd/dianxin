package com.noki.zwhd.manage;

import java.util.List;

import com.noki.zwhd.dao.WyDaoImpl;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.HdjlBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class WyManage {
	
	public WydfftBean searchWydfft(String yearmonth,String zdbm,String dh,String kh){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft(yearmonth,zdbm,dh,kh);
	}
	
	public WydfftBean searchWydfft(String yearmonth,String zdbm,String kh){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft(yearmonth,zdbm,kh);
	}
	public WydfftBean searchWydfftyd(String yearmonth,String zdbm,String kh,String xgzt){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftyd(yearmonth,zdbm,kh,xgzt);
	}
	public WydfftBean searchWydfftydzjds(String yearmonth,String zdbm,String dbbm,String xgzt,String kh){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftydzjds(yearmonth,zdbm,dbbm,xgzt,kh);
	}
	public WydfftBean searchWydfftltzjds(String yearmonth,String zdbm,String dbbm,String xgzt,String kh){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftydzjds(yearmonth,zdbm,dbbm,xgzt,kh);
	}
	public String searchYddj(String dbbm){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchYddj(dbbm);
	}
	
	public boolean insertWydfft(WydfftBean wydfft){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.saveWydfft(wydfft);
	}
	
	public String searchZdmcByZdbm(String zdbm){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchZdmcByZdbm(zdbm);
	}
	
	public List<WydfftBean> searchWydfft_fy(String zdbm){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft_fy(zdbm);
	}
	
	public List<HdjlBean> searchWydffthdjl() {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydffthdjl();
	}

	public boolean saveWydfftHdjl(String loginName, String yearmonth) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.saveWydfftHdjl(loginName, yearmonth);
	}

	public boolean saveWydfft(WydfftBean wydfft) {
		WyDaoImpl dao = new WyDaoImpl();
		return !dao.saveWydfft(wydfft);
	}

	public List<WydfftBean> searchWydfft_ltdfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String yysqrzt,String pc,String zdbm,String yysxgzt) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft_ltdfftqr(loginName, loginId, pageSize, curpage,
				sheng, shi, xian, xiaoqu, yysqrzt,pc,zdbm,yysxgzt);
	}

	public List<WydfftBean> searchWydfft_fy(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String hdzt, String qrzt,String pc,String zdbm,String dh,String cy) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydffft_fy(loginName, loginId, pageSize, curpage,
				sheng, shi, xian, xiaoqu, hdzt, qrzt,pc,zdbm,dh,cy);
	}

	public List<WydfftBean> searchWydfft_dxdfftqr(String loginName,String loginId, int pageSize, int curpage, String sheng,String shi, String xian, String xiaoqu, String yysqrzt,String pc) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft_dxdfftqr(loginName, loginId, pageSize, curpage,sheng, shi, xian, xiaoqu, yysqrzt,pc);
	}

	public List<WydfftBean> searchWydfft_yddfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String yysqrzt,String zdbm,String pc) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft_yddfftqr(loginName, loginId, pageSize, curpage,
				sheng, shi, xian, xiaoqu, yysqrzt,zdbm,pc);
	}

	public List<WydfftBean> searchWydfft(String wyyearmonth) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfft(wyyearmonth);
	}

	public WydfftBean searchWydfftById(String id) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftById(id);
	}

	public boolean updateWydfft(String wydfft_id, String wydfft_dybxdje,
			String wydfft_dyhxdje, String wydfft_jgje, String wydfft_yc,String wydfft_bz) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft(wydfft_id, wydfft_dybxdje, wydfft_dyhxdje,
				wydfft_jgje, wydfft_yc,wydfft_bz);
	}

	public boolean updateWydffthdjl(String wydfft_yearmonth) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydffthdjl(wydfft_yearmonth);
	}

	public boolean updateWydfft_shzt(String yearmonth) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft_shzt(yearmonth);
	}

	public boolean updateWydfft_qx(String id, String zthje, String ztyy,
			String tzr, String bz) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft_qx(id, zthje, ztyy, tzr, bz);
	}

	public boolean updateWydfft_yxg(WydfftBean wydfft) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft_yxg(wydfft);
	}

	public boolean updateWydfft_yqr(String ids) {
		ids = ids.substring(1, ids.length() - 2);
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft_yqr(ids);
	}

	public boolean updateDxdfft_yqr(String ids) {
		ids = ids.substring(1, ids.length() - 2);
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateDxdfft_yqr(ids);
	}

	public boolean isWydfftCf(String yearmonth,String zdbm, String dh, String kh, String jfje,
			String jfrq) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.isWydfftCf(yearmonth,zdbm, dh, kh, jfje, jfrq);
	}

	public boolean updateWydfft(WydfftBean wydfft) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfft(wydfft);
	}
	public boolean updateWydfftyd(ZhandianBean wydfft) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateWydfftyd(wydfft);
	}
	
	public boolean deleteWydfftById(String id){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.deleteWydfftById(id);
	}
	
	public boolean deleteWydfftByZdbm(String zdbm){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.deleteWydfftByZdbm(zdbm);
	}
	
	public boolean deleteWydfftByIds(String ids){
		WyDaoImpl dao = new WyDaoImpl();
		ids = ids.substring(0, ids.length()-1);
		String[] idList = ids.split(",");
		for(int i=0;i<idList.length;i++){
			String id = idList[i];
			id = id.split("undefined")[1];
			dao.deleteWydfftById(id);
		}
		return true;
	}

	public List<WydfftBean> searchWydfftBy(String wyYearMonth,String wydfft_zdbm) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftBy(wyYearMonth,wydfft_zdbm);
	}

	public boolean deleteWydfftByPc(String pc) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftBy(pc);
	}

	public boolean updateForQx(String id, String zthje, String ztyy, String loginId) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.updateForQx(id,zthje,ztyy,loginId);
	}
	
	public ZhandianBean searchZdByWyid(String wyid){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchZdByWyid(wyid);
	}
	
	public DianbiaoBean searchDbByWyid(String wyid){
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchDbByWyid(wyid);
	}

	public List<WydfftBean> searchWydfftBy(String wyYearMonth, String cw_zdbm,
			String cw_dh) {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftBy(wyYearMonth,cw_zdbm,cw_dh);
	}

	public String searchWydfftId() {
		WyDaoImpl dao = new WyDaoImpl();
		return dao.searchWydfftId();
	}
	
}
