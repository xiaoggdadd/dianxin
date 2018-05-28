package com.ptac.app.statisticcollect.province.unitAveSheng.dao;
import java.util.List;
import com.ptac.app.statisticcollect.province.unitAveSheng.bean.UnitAveShengBean;
/**
 * @see ��ѽ�����ϸdao��ӿ�
 * @author ZengJin 2014-1-14
 */
public interface UnitAveShengDao {
	/**
	 * @param whereStr (String)	//sql������
	 * @param loginId (String) //Ȩ�޹������� 
	 * @return List(UnitAveShiBean)
	 * @see �м�����ƽ����ƽ������
	 * @author ZengJin 2014-3-31
	 */
	public List<UnitAveShengBean> queryElectric(String whereStr, String loginId, String shi, String bzyfstart, String bzyfend);

}
