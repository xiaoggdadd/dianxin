package com.ptac.app.lease.statistical.dao;

import java.util.ArrayList;

public interface LeaseStatDao {

	ArrayList queryZlfMsg(String whereStr, String loginId);

}
