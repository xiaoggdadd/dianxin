package com.ptac.app.lease.audit.dao;

import java.util.ArrayList;

import com.ptac.app.lease.query.bean.LeaseBean;

public interface LeaseAuditDao {

	ArrayList queryZlMsg(String whereStr, String loginId);

	String CheckZl(String auditor, String auditstatus, String chooseIdStr1,
			String bz);

	LeaseBean getXiangQing(String leaseid, String loginId);

}
