package com.ptac.app.lease.statistical.dao;

import java.util.ArrayList;

public interface LeaseWarnDao {

	ArrayList queryZlyjMsg(String whereStr, String loginId, String jlts);

}
