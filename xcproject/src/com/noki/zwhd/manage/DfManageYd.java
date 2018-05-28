package com.noki.zwhd.manage;

import java.util.List;

import com.noki.zwhd.dao.WyDaoImpl;
import com.noki.zwhd.dao.YddfftqrDaoImpl;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.DwBean;
import com.noki.zwhd.model.DxdfftqrBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class DfManageYd {

	public boolean saveYddfftqr(DxdfftqrBean bean) {
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.saveDxdfftqr(bean);
	}
	public ZhandianBean searchZhandianByJcode(String jcode){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchZhandianByJcode(jcode);
	}
	public List<DianbiaoBean> searchDianbaoList(String zdid){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchDianbaoList(zdid);
	}
	public List<DwBean> searchDw(String sheng,String loginid){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchAGR("2",sheng,loginid);
	}
	public List<DwBean> searchQx(String _dw,String loginid){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchAGR("3",_dw,loginid);
	}
	public ZhandianBean searchJzcode(String id){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchJz(id);
	}
	public List<ZhandianBean> searchWydfft_fy(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String hdzt, String qrzt,String pc,String zdbm) {
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchWydffft_fy(loginName, loginId, pageSize, curpage,
				sheng, shi, xian, xiaoqu, hdzt, qrzt,pc,zdbm);
	}
	public List<ZhandianBean> searchWydfft_fyyd(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String hdzt, String qrzt,String pc,String zdbm) {
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchWydffft_fyyd(loginName, loginId, pageSize, curpage,
				sheng, shi, xian, xiaoqu, hdzt, qrzt,pc,zdbm);
	}
	
	public ZhandianBean searchWydfft(String yearmonth,String zdbm,String dh,String kh){
		YddfftqrDaoImpl dao = new YddfftqrDaoImpl();
		return dao.searchWydfft(yearmonth,zdbm,dh,kh);
	}
	
}
