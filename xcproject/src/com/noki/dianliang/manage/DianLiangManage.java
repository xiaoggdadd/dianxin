package com.noki.dianliang.manage;

import java.util.ArrayList;

import com.noki.dianliang.dao.DianLiangDaoImpl;
import com.noki.dianliang.model.AmmeterdegreesBean;
import com.noki.dianliang.model.DianbiaoBean;
import com.noki.dianliang.model.TopBaozhangBean;
import com.noki.dianliang.model.ZhandianBean;

public class DianLiangManage {

	public ArrayList<AmmeterdegreesBean> searchDianLiangDaoru(int start,
			String loginName, String loginId, String whereStr) {
		DianLiangDaoImpl dao = new DianLiangDaoImpl();
		return dao.searchDianLiangDaoru(start, loginName, loginId, whereStr);
	}
	public ArrayList<TopBaozhangBean> searchTopBaozhang(int start,
			String loginName, String loginId, String whereStr) {
		DianLiangDaoImpl dao = new DianLiangDaoImpl();
		return dao.searchTopBaozhang(start, loginName, loginId, whereStr);
	}
	public DianbiaoBean searchDianbiao(String dbzbdyhh) {
		DianLiangDaoImpl dao = new DianLiangDaoImpl();
		return dao.searchDianbiaoByDbzbdyhh(dbzbdyhh);
	}
	
	public ZhandianBean searchZhandianByZdid(String zdid){
		DianLiangDaoImpl dao = new DianLiangDaoImpl();
		return dao.searchZhandianByZdid(zdid);
	}
	
}
