package com.ptac.app.lease.query.dao;

import java.util.ArrayList;

import com.ptac.app.lease.query.bean.LeaseBean;

public interface LeaseBillDao {

	ArrayList checkMh(String loginId, String jzname, String str);

	LeaseBean bas(String dbid);

	String addZlht(String dbid, LeaseBean lb);

	ArrayList queryZlMsg(String whereStr, String loginId);

	String deleteZl(String leaseid);

	LeaseBean getZl(String leaseid,String loginId);

	String addModify(String leaseid, String dbid, LeaseBean lb);

}
