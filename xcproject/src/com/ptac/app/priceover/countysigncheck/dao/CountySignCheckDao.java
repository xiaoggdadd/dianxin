package com.ptac.app.priceover.countysigncheck.dao;

import java.util.ArrayList;
import java.util.List;

import com.ptac.app.priceover.countysigncheck.bean.ChaXunBean;
import com.ptac.app.priceover.countysigncheck.bean.CheckBean;

public interface CountySignCheckDao {
	/**判断是否有未签收的单子
	 * @param loginid
	 * @return
	 */
	boolean selectNoSign(String loginid);
	/**签收
	 * @param loginid
	 * @param loginName
	 * @return
	 */
	boolean sign(String loginid,String loginName);
	/** 查询
	 * @param wherestr
	 * @param loginid
	 * @return
	 */
	List<ChaXunBean> chaxun(String wherestr,String loginid);
	/**核实内容查询
	 * @param pid
	 * @return
	 */
	CheckBean check(String pid);
	/**核实提交
	 * @param bean
	 * @param accountid
	 * @param personnal
	 * @return
	 */
	String commit(CheckBean bean,String accountid,String personnal);
	ArrayList getPageDataModelpriceover(String id,String loginid);
}
