package com.ptac.app.chinamobile.dao;

import java.util.Vector;

import com.noki.mobi.common.Account;

public interface CMCCCheckBillAvailable {
	/**����
	 * @author gt_ptac
	 * @param content
	 * @param account
	 * @return
	 */
	public  Vector<String> inputCheck(Object[] content,Account account);
	/**�Ƿ�ӵ�е��
	 * @param content
	 * @param account
	 * @return
	 */
	public Vector<String> check01(Object[] content, Account account);
	/**�Ƿ��ظ�����
	 * ���id ����ʼ��������ʼʱ�� 
	 * @param content
	 * @return
	 */
	public Vector<String> check02(Object[] content) ;
	/**��ֹ����
	 * @param content
	 * @return
	 */
	public Vector<String> check03(Object[] content) ;
	/**�õ�������
	 * @param content
	 * @return
	 */
	public Vector<String> check04(Object[] content) ;
	/**��ѵ���
	 * @param content
	 * @return
	 */
	public Vector<String> check05(Object[] content) ;
	/**��ֹ����
	 * @param content
	 * @return
	 */
	public Vector<String> check06(Object[] content) ;
	/**�����õ���
	 * @param content
	 * @return
	 */
	public Vector<String> check07(Object[] content) ;
	/**�����õ��
	 * @param content
	 * @return
	 */
	public Vector<String> check08(Object[] content) ;
	/**����ȷ��
	 * @param content
	 * @return
	 */
	public Vector<String> inputConfirm (Object[] content) ;
	
	/**ǰ������  վ��id �͵��id����Ϊ��
	 * @param content
	 * @return
	 */
	public Vector<String> check09(Object[] content);
	/**��ʼ����
	 * @param content
	 * @return
	 */
	public Vector<String> check10(Object[] content) ;
	/**���ڹ�ϵ
	 * @param content
	 * @return
	 */
	public Vector<String> check11(Object[] content) ;
	/**��ʼ����
	 * @param content
	 * @return
	 */
	public Vector<String> check12(Object[] content) ;
	
	/**��������
	 * @param content
	 * @return
	 */
	public Vector<String> check13(Object[] content) ;
	/**��Ѽ���
	 * @param content
	 * @return
	 */
	public Vector<String> check14(Object[] content) ;
}
