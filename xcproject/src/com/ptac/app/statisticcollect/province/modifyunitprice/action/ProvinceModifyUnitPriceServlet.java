package com.ptac.app.statisticcollect.province.modifyunitprice.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.modifyunitprice.bean.ProvinceModifyUnitPriceBean;
import com.ptac.app.statisticcollect.province.modifyunitprice.bean.ProvinceModifyUnitPriceSumBean;
import com.ptac.app.statisticcollect.province.modifyunitprice.dao.ProvinceModifyUnitPriceDao;
import com.ptac.app.statisticcollect.province.modifyunitprice.dao.ProvinceModifyUnitPriceDaoImpl;



public class ProvinceModifyUnitPriceServlet extends HttpServlet {

	private static final long serialVersionUID = -4097419234240050123L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		if("chaxun".equals(command)){//修改单价查询
			quaryModifyUnitPrice(request, response);
		}else if("daochu".equals(command)){//修改单价导出查询
			quaryModifyUnitPrice(request, response);
		}
	}
	
	public void quaryModifyUnitPrice(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		ProvinceModifyUnitPriceDao dao = new ProvinceModifyUnitPriceDaoImpl();
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer whereStr1 = new StringBuffer();
		String command = request.getParameter("command");//操作
		String shi = request.getParameter("shi");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String dfstatus = request.getParameter("dfshzt");
		String zdqyzt = request.getParameter("zdqyzt");
		String dbqyzt = request.getParameter("dbqyzt");
		
		if(!"".equals(shi)){
			whereStr.append("AND ZD.SHI = '" + shi + "' " );
			whereStr1.append("AND ZHDN.SHI = '" + shi + "' " );
		}
		if(!"".equals(dfstatus)){
			if("0".equals(dfstatus)){
				whereStr.append(" AND EF.CITYAUDIT = '1' ");
			}else if("1".equals(dfstatus)){
				whereStr.append(" AND EF.MANUALAUDITSTATUS >= '1' ");
			}else if("2".equals(dfstatus)){
				whereStr.append(" AND EF.MANUALAUDITSTATUS = '2' ");
			}else if("3".equals(dfstatus)){
				whereStr.append(" AND EF.CITYZRAUDITSTATUS = '1' ");
			}
		}
		if(!"".equals(zdqyzt)){
			if("1".equals(zdqyzt)){
				whereStr.append(" AND ZD.QYZT = '1' ");
				whereStr1.append(" AND ZHDN.QYZT = '1' ");
			}else if("0".equals(zdqyzt)){
				whereStr.append(" AND ZD.QYZT = '0' ");
				whereStr1.append(" AND ZHDN.QYZT = '0' ");
			}
		}
		if(!"".equals(dbqyzt)){
			if("1".equals(dbqyzt)){
				whereStr.append("AND DB.DBQYZT = '1' ");	
			}else if("0".equals(dbqyzt)){
				whereStr.append("AND DB.DBQYZT = '0' ");	
			}
		}
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		
		List<ProvinceModifyUnitPriceBean> beanlist = dao.quaryModifyUnitPrice(whereStr.toString(),whereStr1.toString(),loginId,beginyue,endyue,command);
		String[] sum = dao.getSum(beanlist);
		int numcolor = beanlist.size();
		
		ProvinceModifyUnitPriceSumBean sumbean = new ProvinceModifyUnitPriceSumBean();
		
		sumbean.setLrtssum(sum[0]);
		sumbean.setXgdjtssum(sum[1]);
		sumbean.setDjtgtssum(sum[2]);
		sumbean.setTgzbsum(sum[3]);
		
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("sumbean", sumbean);
		request.setAttribute("colornum",numcolor);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		if("chaxun".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/province/modifyunitprice/ModifyUnitPrice.jsp").forward(request, response);
		}else if("daochu".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/province/modifyunitprice/省级修改单价查询.jsp").forward(request, response);
		}
		
		
		
	}

}
