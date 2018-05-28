package com.ptac.app.statisticcollect.city.modifyunitprice.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.ModifyUnitPriceBean;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.ModifyUnitPriceSumBean;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.UnitPriceDetailsBean;
import com.ptac.app.statisticcollect.city.modifyunitprice.dao.ModifyUnitPriceDao;
import com.ptac.app.statisticcollect.city.modifyunitprice.dao.ModifyUnitPriceDaoImpl;

/**
 * @see 地市修改单价Servlet
 * @author WangYiXiao
 * @update WangYiXiao 2014-5-27 详情超链接
 *
 */
public class ModifyUnitPriceServlet extends HttpServlet {

	private static final long serialVersionUID = 3920068085718725034L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		if("chaxun".equals(command)){//查询修改单价查询
			quaryModifyUnitPrice(request, response);
		}else if("daochu".equals(command)){//导出修改单价查询
			quaryModifyUnitPrice(request, response);
		}else if("luru".equals(command)){//录入详情
			luruDetials(request,response);
		}else if("xiugai".equals(command) || "xiugaidaochu".equals(command) 
				|| "tiaogao".equals(command) || "tiaogaodaochu".equals(command)){//修改单价详情，单价调高详情
			xiugaiDetials(request,response);
		}
	}
	
	public void quaryModifyUnitPrice(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		ModifyUnitPriceDao dao = new ModifyUnitPriceDaoImpl();
		
		StringBuffer whereStr = new StringBuffer();
		StringBuffer whereStr1 = new StringBuffer();
		String command = request.getParameter("command");//标志位   查询，导出
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String dfstatus = request.getParameter("dfshzt");
		String zdqyzt = request.getParameter("zdqyzt");
		String dbqyzt = request.getParameter("dbqyzt");
		
		if(!"".equals(shi)){
			whereStr.append("AND ZD.SHI = '" + shi + "' " );
			whereStr1.append("AND ZHDN.SHI = '" + shi + "' " );
		}
		if(!"0".equals(xian)){
			whereStr.append(" AND ZD.XIAN = '" + xian +"' " );
			whereStr1.append(" AND ZHDN.XIAN = '" + xian +"' " );
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
		List<ModifyUnitPriceBean> beanlist = dao.quaryModifyUnitPrice(whereStr.toString(),whereStr1.toString(),loginId,beginyue,endyue,command);
		String[] sum = dao.getSum(beanlist);
		int numcolor = beanlist.size();
		ModifyUnitPriceSumBean sumbean = new ModifyUnitPriceSumBean();
		
		sumbean.setLrtssum(sum[0]);
		sumbean.setXgdjtssum(sum[1]);
		sumbean.setDjtgtssum(sum[2]);
		sumbean.setTgzbsum(sum[3]);
		
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("sumbean", sumbean);
		request.setAttribute("colornum",numcolor);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("shi", shi);
		request.setAttribute("xian", xian);
		request.setAttribute("dfshzt", dfstatus);
		request.setAttribute("zdqyzt", zdqyzt);
		request.setAttribute("dbqyzt", dbqyzt);
		
		if("chaxun".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/modifyunitprice/ModifyUnitPrice.jsp").forward(request, response);
		}else if("daochu".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/modifyunitprice/地市修改单价查询.jsp").forward(request, response);
		}
	}

	public void luruDetials(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	}
	public void xiugaiDetials(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ModifyUnitPriceDao dao = new ModifyUnitPriceDaoImpl();
		StringBuffer whereStr = new StringBuffer();
		String command = request.getParameter("command");//标志位   查询，导出
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String beginyue = request.getParameter("beginyue");
		String endyue = request.getParameter("endyue");
		String dfstatus = request.getParameter("dfshzt");
		String zdqyzt = request.getParameter("zdqyzt");
		String dbqyzt = request.getParameter("dbqyzt");
		
		if(!"".equals(shi)){
			whereStr.append("AND ZD.SHI = '" + shi + "' " );
		}
		if(!"0".equals(xian)){
			whereStr.append(" AND ZD.XIAN = '" + xian +"' " );
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
			}else if("0".equals(zdqyzt)){
				whereStr.append(" AND ZD.QYZT = '0' ");
			}
		}
		if(!"".equals(dbqyzt)){
			if("1".equals(dbqyzt)){
				whereStr.append("AND DB.DBQYZT = '1' ");	
			}else if("0".equals(dbqyzt)){
				whereStr.append("AND DB.DBQYZT = '0' ");	
			}
		} 
		List<UnitPriceDetailsBean> beanlist = new ArrayList<UnitPriceDetailsBean>();
		beanlist = dao.getModifyUnitPriceDetails(beginyue, endyue, whereStr.toString(), command);
		int numcolor = beanlist.size();
		request.setAttribute("command", command);
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("colornum",numcolor);
		request.setAttribute("shi", shi);
		request.setAttribute("xian", xian);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("dfshzt", dfstatus);
		request.setAttribute("zdqyzt", zdqyzt);
		request.setAttribute("dbqyzt", dbqyzt);
		if("xiugai".equals(command) || "tiaogao".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/modifyunitprice/xiugaiDetails.jsp").forward(request, response);
		}else if("xiugaidaochu".equals(command) || "tiaogaodaochu".equals(command)){
		request.getRequestDispatcher("/web/appJSP/statisticscollect/city/modifyunitprice/修改调高单价导出.jsp").forward(request, response);
		}
		
	}
}
