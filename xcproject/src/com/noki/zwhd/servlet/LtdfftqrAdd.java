package com.noki.zwhd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;

public class LtdfftqrAdd extends HttpServlet {

	private String msg ="";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SystemManage systemManage = new SystemManage();
//		String t_dw = request.getParameter("t_dw");
//		String t_qx = request.getParameter("t_qx");
//		String t_zdbm = request.getParameter("t_zdbm");
//		String t_zdmc = systemManage.searchJzNameByJzCode(t_zdbm).getJZNAME();
//		String t_address = request.getParameter("t_address");
//		String t_longitude = request.getParameter("t_longitude");
//		String t_latitude = request.getParameter("t_latitude");
//		String t_zcjjzzbm = request.getParameter("t_zcjjzzbm");
//		String t_gdfs = request.getParameter("t_gdfs");
//		String t_jsfs = request.getParameter("t_jsfs");
//		String t_dbbm = request.getParameter("t_dbbm");
//		String t_dh = request.getParameter("t_dh");
//		String t_zq_s = request.getParameter("t_zq_s");
//		String t_zq_e = request.getParameter("t_zq_e");
//		String t_dj = request.getParameter("t_dj");
//		String t_sh = request.getParameter("t_sh");
//		String t_jfje = request.getParameter("t_jfje");
//		String t_jfrq = request.getParameter("t_jfrq");
//		String t_jfpjlx = request.getParameter("t_jfpjlx");
//		String t_gdfmc = request.getParameter("t_gdfmc");
//		String t_ftbl = request.getParameter("t_ftbl");
//		String t_ftje = request.getParameter("t_ftje");
		
		
		String t_wyid = request.getParameter("t_wyid");
		String t_sh = request.getParameter("t_sh");
		String t_bccbsj = request.getParameter("t_bccbsj");
		String t_sccbsj = request.getParameter("t_sccbsj");
		String t_bccbzm = request.getParameter("t_bccbzm");
		String t_sccbzm = request.getParameter("t_sccbzm");
		String t_dliang = request.getParameter("t_dliang");
		String t_szs =  request.getParameter("t_szs");
		String t_zzs =  request.getParameter("t_zzs");
		String t_nyglxtid =  request.getParameter("t_nyglxtid");
		String t_zcjjzzbm = request.getParameter("t_zcjjzzbm");
		String t_zzlx = request.getParameter("t_zzlx");
		String t_dj = request.getParameter("t_dj");
		String t_jfqsrq = request.getParameter("t_jfqsrq");
		String t_jfzzrq = request.getParameter("t_jfzzrq");
		
		WyManage wyManage = new WyManage();
		WydfftBean wydfftBean = wyManage.searchWydfftById(t_wyid);
		wydfftBean.setJfqsrq(t_jfqsrq);
		wydfftBean.setJfzzrq(t_jfzzrq);
		wydfftBean.setBCDJ(t_dj);
		wydfftBean.setCB_ZZLX(t_zzlx);
		wydfftBean.setBccbsj(t_bccbsj);
		wydfftBean.setSccbsj(t_sccbsj);
		wydfftBean.setQM(t_sccbzm);
		wydfftBean.setZM(t_bccbzm);
		wydfftBean.setDLIANG(t_dliang);
		wydfftBean.setSh(t_sh);
		wydfftBean.setCB_SZS(t_szs);
		wydfftBean.setCB_ZZS(t_zzs);
		wydfftBean.setCB_NYGLXTID(t_nyglxtid);
		wydfftBean.setZcjjzhbm(t_zcjjzzbm);
		wydfftBean.setYYSXGZT("已修改");
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_ltdfftqr_search.jsp";
		boolean flag = wyManage.updateWydfft(wydfftBean);
		if(flag){
			msg = "添加联通电费单成功";
		}else{
			msg = "添加联通电费单成功";
		}	
	
		HttpSession session = request.getSession();
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
