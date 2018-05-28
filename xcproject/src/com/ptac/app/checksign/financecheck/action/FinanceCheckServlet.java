package com.ptac.app.checksign.financecheck.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.financecheck.dao.FinanceCheckDAOImp;
import com.ptac.app.checksign.financecheck.dao.FinanceCheckDao;
/**
 * 电费审核的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class FinanceCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/*用于审核财务信息的Servlet方法*/
	/**
	 * @author rock
	 * @see msg.jsp,financecheck.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* 获取审核人员 */
		Account account = new Account();
		HttpSession session = request.getSession();
		account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();// 审核人员
		String kjyf = request.getParameter("kjyfa");
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
		final String url = path + "/web/appJSP/checksign/financecheck/financecheck.jsp";

		String msg = "";

		/* 获取审核的标志、数据数量模式、UUID、月份 */
		String common = request.getParameter("action");

		/* 实例化接口 */
		FinanceCheckDao fcd = new FinanceCheckDAOImp();
		ElectricFeesFormBean formBean = new ElectricFeesFormBean();

		SimpleDateFormat fa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time1 = new Date();
		String m1 = fa.format(time1);

		formBean.setFinancialdatetime(m1);
		formBean.setFinancialoperator(personnal);
		
		if("checkpass".equals(common)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	      	String bz = "2";//审核通过
	      	msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkpass1".equals(common)){//通过,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	      	String bz = "2";//审核通过
	      	msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        String m="";
	        if(msg=="审核电费信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}else if("checknopass1".equals(common)){//不通过ajax部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//预付费uuid 
	  	  	String bz = "-1";//审核不通过
	  	    msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        String m="";
	        if(msg=="审核电费信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}else if("checknopass".equals(common)){//不通过部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	      	String bz = "-1";//审核不通过
	      	msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}
		
//		if ("buwanquan".equals(common)) {
//			msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
//		} else if ("wanquan".equals(common)) {
//			msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
//			session.setAttribute("url", url);
//			session.setAttribute("msg", msg);
//			response.sendRedirect(path + "/web/msg.jsp");
//		}

	}

}
