package com.ptac.app.inportuserzibaodian.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.electricmanage.bargainbill.bean.ZhurenPanduanBean;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;
import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

public interface ElecBillZbdDao {

	@SuppressWarnings("unchecked")
	ArrayList checkMh(String loginId, String jzname, String str);
    BasicInfo bas(String dbid);
    AssistInfo ass(String dbid);
    ElectricityInfo elec(String dbid);
    String addAm(String dbid,ElectricityInfo elecInfo,Share share,String uuid,String bzw,String dfms,String dfbz,String dlms,String dlbz,String bl,String flag,ZhurenPanduanBean bean,String hbzq,String bzz,String scb);
    @SuppressWarnings("unchecked")
	String addDegreeFees(List ammeterdegreeid,String dbid,ElectricityInfo elecInfo,Share share,String uuid,String bzw,String dfms,String dfbz,String dlms,String dlbz,String bl,String flag,ZhurenPanduanBean bean,String jszq,String edhdl,String qsdbdl);
	@SuppressWarnings("unchecked")
	ArrayList share1(String dbid);
	@SuppressWarnings("unchecked")
	ArrayList share2(String dbid);
	ElectricityInfo elec1(String zdcode);
	@SuppressWarnings("unchecked")
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
	String getHtEndTime(String dbid);
	boolean getOut(String dbid);
	
}
