package com.noki.dldfgl;

import java.util.List;

import com.noki.zwhd.model.WydfftBean;

public class DFDLManage {
	public List<DFDLbean> searchWydfft_fy1(String loginName,String loginId,int pageSize, int curpage,String sheng,
			String shi, String xian, String xiaoqu,String qrzt,String cbzt,String pc,String zdbm) {
		DFDLImpl dao = new DFDLImpl();
		return dao.searchWydffft_fy(loginName,loginId,pageSize, curpage,sheng,shi,xian,xiaoqu,qrzt,cbzt,pc,zdbm);
	}
	public List<DFDLbean> searchWydfft_fy2(String loginName,String loginId,int pageSize, int curpage,String sheng,
			String shi, String xian, String xiaoqu,String hdzt,String qrzt) {
		DFDLImpl dao = new DFDLImpl();
		return dao.searchWydffft_fy1(loginName,loginId,pageSize, curpage,sheng,shi,xian,xiaoqu,hdzt,qrzt);
	}
	public int searchCountWydfft() {
		DFDLImpl dao = new DFDLImpl();
		return dao.searchCountWydfft();
	}
}
