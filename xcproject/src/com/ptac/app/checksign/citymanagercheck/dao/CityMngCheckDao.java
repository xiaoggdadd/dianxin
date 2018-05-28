package com.ptac.app.checksign.citymanagercheck.dao;

import java.util.List;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;

/**
 * @author lijing
 * @see 市级主任审核dao层接口
 */
public interface CityMngCheckDao {

	/**
	 * @author lijing
	 * @param whereStr String 查询过滤条件
	 * @param loginId String 权限过滤条件
	 * @return List<CityMngCheckBean> 市级主任审核查询结果
	 */
	public List<CityMngCheckBean> queryCityMngCheck(String whereStr,String loginId,String lrbz1,String lrbz2);

	public List<CityMngCheckBean> getCityMngCheckInfo(String dbid, String dfzflx, String dfbzw, String accountmonth);
	
	 /**
	  * @author lijing
	  * @param personnal (String) 审核人
	  * @param cityzrauditstatus (String) 审核标志
	  * @param chooseIdStr1 (String) 电费uuid
	  * @param chooseIdStr2 （String） 预付费uuid
	  * @param bz (String) msg标志
	  * @see 市级电费审核
	  * @param accountId (String) 账号id
	  * @return String 审核信息msg
	  */
	public String CheckCityFees(String personnal,String cityzrauditstatus,String chooseIdStr1,String chooseIdStr2,String bz);
	
}
