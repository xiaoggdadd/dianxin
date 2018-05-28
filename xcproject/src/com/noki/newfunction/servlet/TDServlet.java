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

public class TDServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");//设置输入编码格式。
		response.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		Account account =(Account)session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		String action = request.getParameter("action");
		if(action.equals("tuidan")){
			String url = path+ "/web/jzcbnewfunction/citySend.jsp",msg="";
			ShiSignDao dao = new ShiSignDao();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.TDUpdate(id, loginName);
			if(rs==1){
				msg="退单成功";
			}else if(rs==0){
				msg="退单失败";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}
		if(action.equals("chehui")){
			String url = path+ "/web/jzcbnewfunction/citySend.jsp",msg="";
			ShiSignDao dao = new ShiSignDao();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.CHUpdate(id, loginName);
			if(rs==1){
				msg="撤回成功";
			}else if(rs==0){
				msg="撤回失败";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
			
			
		}
		
	}

}
