package com.noki.zwhd.manage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.zwhd.dao.SystemDaoImpl;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.DwBean;
import com.noki.zwhd.model.ZhandianBean;

public class SystemManage {
	
	public List<DianbiaoBean> searchDianbaoList(String zdid){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchDianbaoList(zdid);
	}
	
	public ZhandianBean searchZhandianByJcode(String jcode){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchZhandianByJcode(jcode);
	}
	
	public List<DwBean> searchSfgs(){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchAGR("1",null,null);
	}
	
	public List<DwBean> searchdsfgs(String _sfgs,String loginid){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchAGR("2",_sfgs,loginid);
	}
	
	public List<DwBean> searchDw(){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchAGR("2",null,null);
	}
	
	public List<DwBean> searchDw(String sheng,String loginid){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchAGR("2",sheng,loginid);
	}
	
	public List<DwBean> searchQx(String _dw,String loginid){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchAGR("3",_dw,loginid);
	}
	
	public List<ZhandianBean> searchZhandian(String _dw,String _qx){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchZhandian(_dw,_qx);
	}
	public List<ZhandianBean> searchZhandian(String _qx){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchZhandian(_qx);
	}
	public List<ZhandianBean> searchZhandianyd(String _shi,String _qx){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchZhandianyd(_shi,_qx);
	}
	public List<ZhandianBean> searchZhandianZX(String _shi,String _qx){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchZhandianZX(_shi,_qx);
	}
	public ZhandianBean searchJzByCode(String zdbm){
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchJzByCode(zdbm);
	}
	
	public List<String> searchPc(){
		List<String> resultList = new ArrayList<String>();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy");
		String year = formate.format(new Date());
		resultList.add(year+"-01");
		resultList.add(year+"-02");
		resultList.add(year+"-03");
		resultList.add(year+"-04");
		resultList.add(year+"-05");
		resultList.add(year+"-06");
		resultList.add(year+"-07");
		resultList.add(year+"-08");
		resultList.add(year+"-09");
		resultList.add(year+"-10");
		resultList.add(year+"-11");
		resultList.add(year+"-12");
//		SystemDaoImpl dao = new SystemDaoImpl();
//		List<String> pcList =  dao.searchPc();
//		for(String pc : pcList){
//			resultList.remove(pc);
//		}
		return resultList;
	}

	public String searchDwNameByDwCode(String t_dsfgs) {
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchDwNameByDwCode(t_dsfgs);
	}

	public ZhandianBean searchJzNameByJzCode(String jzcode) {
		SystemDaoImpl dao = new SystemDaoImpl();
		return dao.searchJzNameByJzCode(jzcode);
	}

}
