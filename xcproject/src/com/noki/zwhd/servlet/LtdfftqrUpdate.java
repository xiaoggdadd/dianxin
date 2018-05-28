package com.noki.zwhd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;

public class LtdfftqrUpdate extends HttpServlet {

	private String msg ="";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String t_wyid = request.getParameter("t_wyid");
		String t_bccbsj = request.getParameter("t_bccbsj");
		String t_sccbsj = request.getParameter("t_sccbsj");
		String t_bccbzm = request.getParameter("t_bccbzm");
		String t_sccbzm = request.getParameter("t_sccbzm");
		String t_dliang = request.getParameter("t_dliang");
		String t_sh = request.getParameter("t_sh");
		String t_nyglxtid = request.getParameter("t_nyglxtid");
		String t_zzs = request.getParameter("t_zzs");
		String t_szs = request.getParameter("t_szs");
		String t_dbhh = request.getParameter("t_dbhh");
		
		WyManage wyManage = new WyManage();
		WydfftBean wydfft = wyManage.searchWydfftById(t_wyid);
		wydfft.setBccbsj(t_bccbsj);
		wydfft.setSccbsj(t_sccbsj);
		wydfft.setQM(t_sccbzm);
		wydfft.setZM(t_bccbzm);
		wydfft.setCB_SZS(t_szs);
		wydfft.setSh(t_sh);
		wydfft.setCB_ZZS(t_zzs);
		wydfft.setDLIANG(t_dliang);
		wydfft.setCB_NYGLXTID(t_nyglxtid);
		boolean flag = wyManage.updateWydfft(wydfft);
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_ltdfftqr_search.jsp";
		if(flag){
			msg = "修改联通电费单成功";
		}else{
			msg = "修改联通电费单成功";
		}	
	
		HttpSession session = request.getSession();
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
