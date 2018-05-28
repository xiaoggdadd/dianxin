package com.ptac.app.print.printpaperbill.action;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.print.printpaperbill.bean.PrintPaperBill;
import com.ptac.app.print.printpaperbill.bean.QueryPaperBill;
import com.ptac.app.print.printpaperbill.dao.PrintPaperBillDao;
import com.ptac.app.print.printpaperbill.dao.PrintPaperBillDaoImp;
import com.ptac.app.print.printpaperbill.dao.QueryPaperBillDao;
import com.ptac.app.print.printpaperbill.dao.QueryPaperBillDaoImp;

public class PrintPaperBillServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		String dypeople=account.getName();
		
		Date date=new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
		String dytime=time.format(date);

		String chooseIdStr1 = request.getParameter("chooseIdStr1") != null ? request.getParameter("chooseIdStr1"): "";
		String chooseIdStr2 = request.getParameter("chooseIdStr2") != null ? request.getParameter("chooseIdStr2"): "";
		String liuch = request.getParameter("lch") != null ? request.getParameter("lch"): "0";		
		String shi1 = request.getParameter("shi") != null ? request.getParameter("shi"): "";	
		String dy=request.getParameter("dayin") != null ? request.getParameter("dayin"): "";
		String dystr = request.getParameter("dystr")!= null ? request.getParameter("dystr"): "";
		boolean isload = false;
		
		
       String whereStr="";
       
       if(chooseIdStr1==""){
       	whereStr=chooseIdStr2;
       }else if(chooseIdStr2==""){
       	whereStr=chooseIdStr1;
       }else if(chooseIdStr1==""&&chooseIdStr2==""){
       	whereStr="";
       }else{
       	whereStr = chooseIdStr1+","+chooseIdStr2;
       }
		
		PrintPaperBillDao dao = new PrintPaperBillDaoImp();

  		List<List<PrintPaperBill>> list = dao.getPageDataCaiwu(whereStr,dystr);
  		List<PrintPaperBill> listt=dao.getFentan(whereStr);
  		
  		
    	if(null==liuch||liuch.equals("")||liuch.equals(" ")||"0".equals(liuch)||liuch=="0"){
    		liuch=dao.getMaxlch(shi1);
    	}
    	
    	request.setAttribute("liuch", liuch);
    	request.setAttribute("dypeople", dypeople);
    	request.setAttribute("dytime", dytime);
    	request.setAttribute("list", list);
    	request.setAttribute("listt", listt);
    	request.getRequestDispatcher("/web/appJSP/print/printpaperbill/printpaperbill.jsp").forward(request, response);
	}
}
