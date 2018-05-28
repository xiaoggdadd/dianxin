package com.ptac.app.statisticcollect.city.unitAveShi.dao;
import java.util.List;
import com.ptac.app.statisticcollect.city.unitAveShi.bean.UnitAveShiBean;
/**
 * @see 电费缴纳明细dao层接口
 * @author ZengJin 2014-1-14
 */
public interface UnitAveShiDao {
	/**
	 * @param whereStr (String)	//sql语句后半段
	 * @param loginId (String) //权限过滤条件 
	 * @return List(UnitAveShiBean)
	 * @see 市级单价平均和平均单价
	 * @author ZengJin 2014-3-31
	 */
	public List<UnitAveShiBean> queryElectric(String whereStr, String loginId, String shi, String bzyfstart, String bzyfend);

}
