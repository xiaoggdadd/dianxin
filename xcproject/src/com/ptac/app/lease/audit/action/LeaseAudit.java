package com.ptac.app.lease.audit.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.lease.audit.dao.LeaseAuditDao;
import com.ptac.app.lease.audit.dao.LeaseAuditDaoImp;
import com.ptac.app.lease.query.bean.LeaseBean;


/**
 * @author lijing
 * @see ���޺�ͬ���ͨ������ͨ������ϸ��Ϣ
 */
public class LeaseAudit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); //�����  һ����servlet�ж�Ҫд�� �Ǳ��ҳ����ôû�£�д��������  ������ǲ��ȶ���
		String command = request.getParameter("command");
		LeaseAuditDao dao = new LeaseAuditDaoImp();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String auditor=account.getAccountName();//�����Ա
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();
	    String url = "",msg = "";
	    
		if("pass".equals(command)){//���ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); 
	        url = path + "/web/appJSP/lease/audit/leaseAudit.jsp" ;
	      	String auditstatus = "1";
	        msg = dao.CheckZl(auditor,auditstatus,chooseIdStr1,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "���޺�ͬ���ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("pass1".equals(command)){//���ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	        url = path + "/web/appJSP/lease/audit/leaseAudit.jsp" ;
	      	String auditstatus = "1";
	        msg = dao.CheckZl(auditor,auditstatus,chooseIdStr1,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "���޺�ͬ���ͨ��"); 
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("nopass".equals(command)){//��˲�ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	        url = path + "/web/appJSP/lease/audit/leaseAudit.jsp" ;
	  	  	String auditstatus = "2";
	        msg = dao.CheckZl(auditor,auditstatus,chooseIdStr1,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "���޺�ͬ��˲�ͨ��");
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("nopass1".equals(command)){//��˲�ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	        url = path + "/web/appJSP/lease/audit/leaseAudit.jsp" ;
	      	String auditstatus = "2";
	        msg = dao.CheckZl(auditor,auditstatus,chooseIdStr1,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "���޺�ͬ��˲�ͨ��"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("xiangqing".equals(command)){//��ϸ��Ϣ
			//���޺�ͬ�޸�ҳ���ѯ��Ϣ
			String loginId = account.getAccountId();
			String statusi = request.getParameter("statusi");
			String leaseid = request.getParameter("leaseid"+statusi);
			LeaseBean lb = new LeaseBean();
			lb = dao.getXiangQing(leaseid,loginId);
			request.setAttribute("leaseid", leaseid);
	    	request.setAttribute("lb", lb);
	    	request.getRequestDispatcher("/web/appJSP/lease/audit/xq.jsp").forward(request, response);
		}
	    
	}

}
