package com.ptac.app.electricmanage.enhanceelectricitybill.dao;

import java.util.ArrayList;
import java.util.Map;

import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;
import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

/**
 * @author WangYiXiao 2014-4-15
 * @see 电费单dao层接口
 */
public interface ElecBillDao {

	ArrayList checkMh(String loginId, String jzname, String str);
    BasicInfo bas(String dbid);
    AssistInfo ass(String dbid);
    ElectricityInfo elec(String dbid);
    String addDegreeFees(String dbid,ElectricityInfo elecInfo,Share share,String uuid,String bzw,String dfms,String dfbz,
    		String dlms,String dlbz,String bl,String sqbl,String flag,String qxzr,String cityzr,String jszq,String edhdl,String qsdbdl,String xgbzw,String hbzq,String bzz,String scb);
	ArrayList share1(String dbid);
	ArrayList share2(String dbid);
	ElectricityInfo elec1(String zdcode);
	ArrayList queryElectric(String whereStr, String loginId);
	
    /**获取分摊比例 
     * @param dbid
     * @return
     * @author gt_web
     */
    DomainType getDomainType(String dbid);
	
    /**获取分摊二级详细信息
     * @param dbid
     * @return
     */
    ArrayList<DomainOther> getDomainOther(String dbid);
	
    /**预支类电表获取流程号
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-3-5
	 */
	String getLiuchenghao(String dbid);
	/**上次单价
	 * @param dbid
	 * @return
	 */
	String getLast(String dbid);
	/** 获取判断的标准值
	 * @param itemname
	 * @param itemllag
	 * @return
	 */
	Map<String,String> getValue(String itemllag);
	/**截止上月末本地市平均单价 -改为 标准单价 2015-1-28
	 * @param str 标准单价表中的列
	 * @return
	 * @author WangYiXiao 2014-7-21
	 */
	String getAverageUnitPrice(String dbid,String str);
	/**
	 * 获取合同的结束时间
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	String getHtEndTime(String dbid);

	/**判断电表关联的站点是否为外租回收站点并且与主站点id相关联
	 * 站点属性为 其他,站点名称中包含 '回收'为外租回收站点
	 * 外租回收站点并且未与主站点ID相关联返回true
	 * @param dbid 电表id
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	boolean getOut(String dbid);
	
}
