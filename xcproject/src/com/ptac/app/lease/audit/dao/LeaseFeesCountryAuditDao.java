package com.ptac.app.lease.audit.dao;

import java.io.IOException;
import java.util.List;

import com.ptac.app.lease.audit.bean.LeasefeesCountryAudit;


public interface LeaseFeesCountryAuditDao {
	
	/** ���޷���������˲�ѯ
	 * @return
	 */
	List<LeasefeesCountryAudit> getCountryAuditLeaseFees(String whereStr,String accountid);
	/**���޷���������� ͨ������ͨ����ȡ�����
	 * @author WangYiXiao 2014-9-25
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String CheckCountryFees(String personnal, String auditstatus,String chooseIdStr1,String bz);
}
