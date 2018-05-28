package com.noki.zwhd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.model.CwybxBean;

public class CwybxUpdate extends HttpServlet {
	
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
		String t_cwid = request.getParameter("t_cwid");
		String t_rq = request.getParameter("t_rq");
		String t_bxlx = request.getParameter("t_bxlx");
		String t_djbh = request.getParameter("t_djbh");
		String t_zy = request.getParameter("t_zy");
		String t_djzt = request.getParameter("t_djzt");
		String t_bm = request.getParameter("t_bm");
		String t_sfje = request.getParameter("t_sfje");
		String t_fphsje = request.getParameter("t_fphsje");
		String t_fpbshje = request.getParameter("t_fpbshje");
		String t_jsdh = request.getParameter("t_jsdh");
		String t_zdmc = request.getParameter("t_zdmc");
		String t_zdbh = request.getParameter("t_zdbh");
		String t_cwyfthydcwbxje = request.getParameter("t_cwyfthydcwbxje");
		String t_cy = request.getParameter("t_cy");
		String t_wyftje = request.getParameter("t_wyftje");
		String t_fsyz = request.getParameter("t_fsyz");
		String t_bz = request.getParameter("t_bz");
		
		CwybxBean cwybx = new CwybxBean();
		cwybx.setYEARMONTH(t_yearmonth);
		cwybx.setID(t_cwid);
		cwybx.setRQ(t_rq);
		cwybx.setBXLX(t_bxlx);
		cwybx.setDJPH(t_djbh);
		cwybx.setZY(t_zy);
		cwybx.setDJZT(t_djzt);
		cwybx.setBM(t_bm);
		cwybx.setSFJE(t_sfje);
		cwybx.setFPHSJE(t_fphsje);
		cwybx.setFPBHSJE(t_fpbshje);
		cwybx.setJSDH(t_jsdh);
		cwybx.setZDMC(t_zdmc);
		cwybx.setZDBH(t_zdbh);
		cwybx.setCWYFTHYDCWBXJE(t_cwyfthydcwbxje);
		cwybx.setCY(t_cy);
		cwybx.setYWFTJE(t_wyftje);
		cwybx.setFSYZ(t_fsyz);
		cwybx.setBZ(t_bz);
		
		boolean flag = true;
		
		if(cwybx.getID()!=null&&!cwybx.getID().equals("")&&!cwybx.getID().equals("null")){
			flag = cwManage.updateCwdfft(cwybx);
		}
		if(flag){
			msg = "财务数据已报销修改失败！";
		}else{
			msg = "财务数据已报销修改成功！";
		}
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_cwybx_search.jsp";
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
		
	}

}
