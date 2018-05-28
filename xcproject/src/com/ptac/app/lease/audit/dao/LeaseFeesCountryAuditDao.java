package com.ptac.app.lease.audit.dao;

import java.io.IOException;
import java.util.List;

import com.ptac.app.lease.audit.bean.LeasefeesCountryAudit;


public interface LeaseFeesCountryAuditDao {
	
	/** 租赁费用区县审核查询
	 * @return
	 */
	List<LeasefeesCountryAudit> getCountryAuditLeaseFees(String whereStr,String accountid);
	/**租赁费用区县审核 通过，不通过，取消审核
	 * @author WangYiXiao 2014-9-25
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String CheckCountryFees(String personnal, String auditstatus,String chooseIdStr1,String bz);
}
