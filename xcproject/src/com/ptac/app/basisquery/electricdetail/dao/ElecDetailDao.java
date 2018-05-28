package com.ptac.app.basisquery.electricdetail.dao;
import java.util.List;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
/**
 * @see 电费缴纳明细dao层接口
 * @author ZengJin 2014-1-14
 */
public interface ElecDetailDao {
	/**
	 * @param whereStr (String)	//sql语句后半段
	 * @param loginId (String) //权限过滤条件 
	 * @param bzw (String) //权限过滤条件 
	 * @return (ElectricDetail)
	 * @see 电费缴纳明细查询和导出
	 * @author ZengJin 2014-1-14
	 */
	public List<ElectricDetail> queryElectric(String whereStr, String loginId, String bzw);
	/**
	 * @param list List(ElectricDetail)
	 * @return (ElectricDetail)
	 * @see 计算电量合计和电费合计
	 * @author ZengJin 2014-1-16
	 */
	public ElectricDetail total(List<ElectricDetail> list);
}
