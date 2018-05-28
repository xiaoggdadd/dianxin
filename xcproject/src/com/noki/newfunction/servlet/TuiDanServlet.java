package com.noki.newfunction.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.XianSignDao;
import com.noki.newfunction.dao.ZGShenHeDao;

public class TuiDanServlet extends HttpServlet {

//�����ɵ���ǩ��
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//������������ʽ��
		response.setContentType("text/html;charsetType=utf-8");//������������ʽ��
		
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		String action = request.getParameter("action");
		String url="";String msg="";
		if(action.equals("tuidan")){//�����˵�
			url = path + "/web/jzcbnewfunction/qxSend.jsp";
			XianSignDao dao = new XianSignDao();
			String id = request.getParameter("chooseIdStr");				
			int rs=dao.TDUpdate(id,loginName);
			if (rs == 1) {
				msg = "�˵��ɹ���";
			} else if (rs == 0) {
				msg = "�˵�ʧ�ܣ�����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		if(action.equals("paidan")){//�����ɵ�
			url = path + "/web/jzcbnewfunction/qxSend.jsp";
			String chooseIdStr = request.getParameter("chooseIdStr");
			XianSignDao dao1 = new XianSignDao();
			int Rs = dao1.PDupdate(chooseIdStr,loginName);
			if(Rs==1){
				msg = "�ɵ��ɹ���"; 
			}else if(Rs==0){
				msg = "�ɵ�ʧ��,����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		if(action.equals("chexiao")){//�����ɵ�����
			url = path + "/web/jzcbnewfunction/qxSend.jsp";
			String chooseIdStr = request.getParameter("chooseIdStr");
			XianSignDao dao2 = new XianSignDao();
			int Rs = dao2.CXupdate(chooseIdStr);
			if(Rs==1){
				msg = "�ɵ������ɹ���"; 
			}else if(Rs==0){
				msg = "�ɵ�����ʧ��,����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		if(action.equals("TJSHsave")){//
			url = path + "/web/jzcbnewfunction/ZGShenHe.jsp";
			String wcsm = request.getParameter("wcsm");
			String beginTime1 = request.getParameter("beginTime1");
			String zgfzr = request.getParameter("zgfzr");
			String id = request.getParameter("cid");
			String path1="";
			String name="";
			
			ZGShenHeDao dao = new ZGShenHeDao();
		//int Rs = dao.TJSHsave(loginName, wcsm, beginTime1, zgfzr,id);
			int Rs=0;
			System.out.println(id+"zzz");
			if(Rs==1){
				msg = "����ɹ���"; 
			}else if(Rs==0){
				msg = "����ʧ��,����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		if(action.equals("SHchexiao")){//��������ύ������
			url = path + "/web/jzcbnewfunction/ZGShenHe.jsp";
			String chooseIdStr = request.getParameter("chooseIdStr");
			ZGShenHeDao dao = new ZGShenHeDao();
			int Rs = dao.SHchexiao(chooseIdStr);
			if(Rs==1){
				msg = "�����ɹ���"; 
			}else if(Rs==0){
				msg = "����ʧ��,����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
	}
}

