package com.ptac.app.priceover.countysigncheck.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.priceover.countysigncheck.bean.ChaXunBean;
import com.ptac.app.priceover.countysigncheck.bean.CheckBean;

public interface CountySignCheckDao {
	/**�ж��Ƿ���δǩ�յĵ���
	 * @param loginid
	 * @return
	 */
	boolean selectNoSign(String loginid);
	/**ǩ��
	 * @param loginid
	 * @param loginName
	 * @return
	 */
	boolean sign(String loginid,String loginName);
	/** ��ѯ
	 * @param wherestr
	 * @param loginid
	 * @return
	 */
	List<ChaXunBean> chaxun(String wherestr,String loginid);
	/**��ʵ���ݲ�ѯ
	 * @param pid
	 * @return
	 */
	CheckBean check(String pid);
	/**��ʵ�ύ
	 * @param bean
	 * @param accountid
	 * @param personnal
	 * @return
	 */
	String commit(CheckBean bean,String accountid,String personnal);
	ArrayList getPageDataModelpriceover(String id,String loginid);
}
