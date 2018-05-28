package com.ptac.app.basisquery.electricdetail.dao;
import java.util.List;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
/**
 * @see ��ѽ�����ϸdao��ӿ�
 * @author ZengJin 2014-1-14
 */
public interface ElecDetailDao {
	/**
	 * @param whereStr (String)	//sql������
	 * @param loginId (String) //Ȩ�޹������� 
	 * @param bzw (String) //Ȩ�޹������� 
	 * @return (ElectricDetail)
	 * @see ��ѽ�����ϸ��ѯ�͵���
	 * @author ZengJin 2014-1-14
	 */
	public List<ElectricDetail> queryElectric(String whereStr, String loginId, String bzw);
	/**
	 * @param list List(ElectricDetail)
	 * @return (ElectricDetail)
	 * @see ��������ϼƺ͵�Ѻϼ�
	 * @author ZengJin 2014-1-16
	 */
	public ElectricDetail total(List<ElectricDetail> list);
}
