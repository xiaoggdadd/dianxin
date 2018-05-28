package com.ptac.app.electricmanage.enhanceelectricitybill.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

/**
 * @see 电费单修改.dao层接口
 * @author ZengJin 2014-2-15
 */
public interface ElecModifyDao {
	/**
	 * @param degreeid (String) //电费id
	 * @return (List)
	 * @see 查询电量电费基本信息
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public List getElectricFeesInfo(String degreeid);
	/**
	 * @param dbid (String) //电表id
	 * @return (ArrayList)
	 * @see 查询电量电费附加信息
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getOtherInfo(String dbid);
	/**
	 * @param elec (ElectricityInfo) //电量电费bean
	 * @param share (Share) //分摊信息bean
	 * @return (ArrayList)
	 * @see 保存已修改信息
	 * @author ZengJin 2014-2-15
	 */
	public String modifyElectricFees(ElectricityInfo elec,Share share,String dfms,String dfbz,String dlms,String dlbz,String flag,
			String edhdlbz,String qsdbdlbz,String qxzr,String cityzr,String jszq,String edhdl,String qsdbdl,String hbzq,String bzz);
	/**
	 * @param jsdbid
	 * @return
	 * author WangYiXiao 2014-6-19
	 */
	ElectricityInfo elec1(String jsdbid);
	/**上期电量，电费，单价
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-6-20
	 */
	AssistInfo lastInfo(String dbid);
}