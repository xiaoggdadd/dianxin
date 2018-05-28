package com.noki.zwhd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.model.CwybxBean;

public class CwybxAdd extends HttpServlet {
	private String msg;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CwManage cwManage = new CwManage();
		String t_yearmonth = request.getParameter("t_yearmonth");
		String t_rq = request.getParameter("t_rq");
		String t_bxlx = request.getParameter("t_bxlx");
		String t_djbh = request.getParameter("t_djbh");
		String t_zy = request.getParameter("t_zy");
		String t_djzt = request.getParameter("t_djzt");
		String t_bm = request.getParameter("t_bm");
		String t_sfje = request.getParameter("t_sfje");
		String t_fphsje = request.getParameter("t_fphsje");
		String t_fpbhsje = request.getParameter("t_fpbhsje");
		String t_cwcy = request.getParameter("t_cwcy");
		String t_cwyfthydcwbx = request.getParameter("t_cwyfthydcwbx");
		String t_bz = request.getParameter("t_bz")==null||request.getParameter("t_bz").equals("null")||request.getParameter("t_bz").equals("")?"":request.getParameter("t_bz");
		String t_fsyz = request.getParameter("t_fsyz");
		String t_ftje = request.getParameter("t_ftje");
		String t_dh = request.getParameter("t_dh");
		String t_zdbm = request.getParameter("t_zdbm");
		String t_zdmc = request.getParameter("t_zdmc");
		
		CwybxBean cwybx = new CwybxBean();
		cwybx.setBZ(t_bz);
		cwybx.setCY(t_cwcy);
		cwybx.setFSYZ(t_fsyz);
		cwybx.setCWYFTHYDCWBXJE(t_cwyfthydcwbx);
		cwybx.setYWFTJE(t_ftje);
		cwybx.setYEARMONTH(t_yearmonth);
		cwybx.setDJZT(t_djzt);
		cwybx.setRQ(t_rq);
		cwybx.setBXLX(t_bxlx);
		cwybx.setDJPH(t_djbh);
		cwybx.setZY(t_zy);
		cwybx.setBM(t_bm);
		cwybx.setSFJE(t_sfje);
		cwybx.setFPHSJE(t_fphsje);
		cwybx.setFPBHSJE(t_fpbhsje);
		cwybx.setFPHSJE(t_fphsje);
		cwybx.setJSDH(t_dh);
		cwybx.setZDMC(t_zdmc);
		cwybx.setZDBH(t_zdbm);
		
		boolean flag = cwManage.insertCwdfft(cwybx);
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_cwybx_search.jsp";
		
		if(flag){
			msg = "添加财务数据已报销成功";
		}else{
			msg = "添加财务数据已报销失败";
		}
		
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
