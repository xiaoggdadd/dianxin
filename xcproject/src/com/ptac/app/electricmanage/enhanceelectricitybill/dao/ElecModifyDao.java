package com.ptac.app.electricmanage.enhanceelectricitybill.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

/**
 * @see ��ѵ��޸�.dao��ӿ�
 * @author ZengJin 2014-2-15
 */
public interface ElecModifyDao {
	/**
	 * @param degreeid (String) //���id
	 * @return (List)
	 * @see ��ѯ������ѻ�����Ϣ
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public List getElectricFeesInfo(String degreeid);
	/**
	 * @param dbid (String) //���id
	 * @return (ArrayList)
	 * @see ��ѯ������Ѹ�����Ϣ
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getOtherInfo(String dbid);
	/**
	 * @param elec (ElectricityInfo) //�������bean
	 * @param share (Share) //��̯��Ϣbean
	 * @return (ArrayList)
	 * @see �������޸���Ϣ
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
	/**���ڵ�������ѣ�����
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-6-20
	 */
	AssistInfo lastInfo(String dbid);
}