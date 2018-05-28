package com.ptac.app.chinamobile.dao;

import java.util.Vector;

import com.noki.mobi.common.Account;

public interface CMCCCheckBillAvailable {
	/**导入
	 * @author gt_ptac
	 * @param content
	 * @param account
	 * @return
	 */
	public  Vector<String> inputCheck(Object[] content,Account account);
	/**是否拥有电表
	 * @param content
	 * @param account
	 * @return
	 */
	public Vector<String> check01(Object[] content, Account account);
	/**是否重复导入
	 * 电表id ，起始读数和起始时间 
	 * @param content
	 * @return
	 */
	public Vector<String> check02(Object[] content) ;
	/**终止读数
	 * @param content
	 * @return
	 */
	public Vector<String> check03(Object[] content) ;
	/**用电量调整
	 * @param content
	 * @return
	 */
	public Vector<String> check04(Object[] content) ;
	/**电费调整
	 * @param content
	 * @return
	 */
	public Vector<String> check05(Object[] content) ;
	/**截止日期
	 * @param content
	 * @return
	 */
	public Vector<String> check06(Object[] content) ;
	/**结算用电量
	 * @param content
	 * @return
	 */
	public Vector<String> check07(Object[] content) ;
	/**结算用电费
	 * @param content
	 * @return
	 */
	public Vector<String> check08(Object[] content) ;
	/**插入确认
	 * @param content
	 * @return
	 */
	public Vector<String> inputConfirm (Object[] content) ;
	
	/**前提条件  站点id 和电表id不能为空
	 * @param content
	 * @return
	 */
	public Vector<String> check09(Object[] content);
	/**起始日期
	 * @param content
	 * @return
	 */
	public Vector<String> check10(Object[] content) ;
	/**日期关系
	 * @param content
	 * @return
	 */
	public Vector<String> check11(Object[] content) ;
	/**起始读数
	 * @param content
	 * @return
	 */
	public Vector<String> check12(Object[] content) ;
	
	/**电量计算
	 * @param content
	 * @return
	 */
	public Vector<String> check13(Object[] content) ;
	/**电费计算
	 * @param content
	 * @return
	 */
	public Vector<String> check14(Object[] content) ;
}
