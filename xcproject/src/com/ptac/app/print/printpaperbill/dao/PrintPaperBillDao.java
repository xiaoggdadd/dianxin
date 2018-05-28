package com.ptac.app.print.printpaperbill.dao;

import java.util.List;

import com.noki.electricfees.javabean.DianfeidandayinBean;
import com.ptac.app.print.printpaperbill.bean.PrintPaperBill;

public interface PrintPaperBillDao {
	public List<List<PrintPaperBill>> getPageDataCaiwu(String whereStr,String dystr);
	public List<PrintPaperBill> getFentan(String whereStr);
	public String getMaxlch(String shi);
}
