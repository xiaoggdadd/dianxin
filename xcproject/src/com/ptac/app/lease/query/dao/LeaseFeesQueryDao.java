package com.ptac.app.lease.query.dao;

import java.util.List;

import com.ptac.app.lease.query.bean.LeasefeesQuery;

public interface LeaseFeesQueryDao {
	List<LeasefeesQuery> getLeasefeesQuery(String whereStr, String loginId);
}
