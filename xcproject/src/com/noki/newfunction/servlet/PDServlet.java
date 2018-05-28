package com.noki.newfunction.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.ShiSignDao;

public class PDServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");//������������ʽ��
		response.setContentType("text/html;charsetType=utf-8");//������������ʽ��
		
		String path = request.getContextPath();
		
		
		HttpSession session = request.getSession();
		Account account =(Account)session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		
		String action = request.getParameter("action");
		if(action.equals("paidan")){
			String url = path+ "/web/jzcbnewfunction/citySend.jsp",msg="";
			ShiSignDao dao = new ShiSignDao();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.PDUpdate(id,loginName);
			if(rs==1){
				msg="�ɵ��ɹ�";
			}else if(rs==0){
				msg="�ɵ�ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}else if(action.equals("paidan1")){
			String url = path+ "/web/jzcbnewfunction/dsxf.jsp",msg="";
			ShiSignDao dao = new ShiSignDao();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.PDUpdate1(id,loginName);
			if(rs==1){
				msg="�ɵ��·��ɹ�";
			}else if(rs==0){
				msg="�ɵ��·�ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if(action.equals("chehui")){
			String url = path+ "/web/jzcbnewfunction/dsxf.jsp",msg="";
			ShiSignDao dao = new ShiSignDao();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.CHUpdate1(id,loginName);
			if(rs==1){
				msg="���سɹ�";
			}else if(rs==0){
				msg="����ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}
		
	}
}
