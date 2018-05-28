package com.ptac.app.print.printpaperbill.dao;

import java.util.List;

import com.ptac.app.print.printpaperbill.bean.QueryPaperBill;

public interface QueryPaperBillDao {
	public List<QueryPaperBill> queryData(String whereStr,String Str, String loginId);
}
