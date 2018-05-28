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
 * @see ��ѵ�dao��ӿ�
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
	
    /**��ȡ��̯���� 
     * @param dbid
     * @return
     * @author gt_web
     */
    DomainType getDomainType(String dbid);
	
    /**��ȡ��̯������ϸ��Ϣ
     * @param dbid
     * @return
     */
    ArrayList<DomainOther> getDomainOther(String dbid);
	
    /**Ԥ֧�����ȡ���̺�
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-3-5
	 */
	String getLiuchenghao(String dbid);
	/**�ϴε���
	 * @param dbid
	 * @return
	 */
	String getLast(String dbid);
	/** ��ȡ�жϵı�׼ֵ
	 * @param itemname
	 * @param itemllag
	 * @return
	 */
	Map<String,String> getValue(String itemllag);
	/**��ֹ����ĩ������ƽ������ -��Ϊ ��׼���� 2015-1-28
	 * @param str ��׼���۱��е���
	 * @return
	 * @author WangYiXiao 2014-7-21
	 */
	String getAverageUnitPrice(String dbid,String str);
	/**
	 * ��ȡ��ͬ�Ľ���ʱ��
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	String getHtEndTime(String dbid);

	/**�жϵ�������վ���Ƿ�Ϊ�������վ�㲢������վ��id�����
	 * վ������Ϊ ����,վ�������а��� '����'Ϊ�������վ��
	 * �������վ�㲢��δ����վ��ID���������true
	 * @param dbid ���id
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	boolean getOut(String dbid);
	
}
