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
 * �����˵�Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class FinanceCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	/*������˲�����Ϣ��Servlet����*/
	/**
	 * @author rock
	 * @see msg.jsp,financecheck.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* ��ȡ�����Ա */
		Account account = new Account();
		HttpSession session = request.getSession();
		account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();// �����Ա
		String kjyf = request.getParameter("kjyfa");
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
		final String url = path + "/web/appJSP/checksign/financecheck/financecheck.jsp";

		String msg = "";

		/* ��ȡ��˵ı�־����������ģʽ��UUID���·� */
		String common = request.getParameter("action");

		/* ʵ�����ӿ� */
		FinanceCheckDao fcd = new FinanceCheckDAOImp();
		ElectricFeesFormBean formBean = new ElectricFeesFormBean();

		SimpleDateFormat fa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time1 = new Date();
		String m1 = fa.format(time1);

		formBean.setFinancialdatetime(m1);
		formBean.setFinancialoperator(personnal);
		
		if("checkpass".equals(common)){//ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	      	String bz = "2";//���ͨ��
	      	msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkpass1".equals(common)){//ͨ��,ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	      	String bz = "2";//���ͨ��
	      	msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}else if("checknopass1".equals(common)){//��ͨ��ajax����
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//Ԥ����uuid 
	  	  	String bz = "-1";//��˲�ͨ��
	  	    msg = fcd.modifyCheckFees(formBean, chooseIdStr1, chooseIdStr2, bz, kjyf);
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}else if("checknopass".equals(common)){//��ͨ������
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	      	String bz = "-1";//��˲�ͨ��
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
