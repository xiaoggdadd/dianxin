package com.noki.zwhd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.ammeterdegree.javabean.newAmmeterDegreeFormBean;
import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.CwybxBean;
import com.noki.zwhd.model.WydfftBean;

public class WydfftUpdate extends HttpServlet {
	private String msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		SystemManage sysManage = new SystemManage();
//		CwManage cwManage = new CwManage();
		WyManage wyManage = new WyManage();
//		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat mdFormat = new SimpleDateFormat("MM.dd");
		
		String t_yearmonth = request.getParameter("t_yearmonth");
		String t_sfgs = request.getParameter("t_sfgs");
		//t_sfgs = sysManage.searchDwNameByDwCode(t_sfgs);
		String t_dsfgs = request.getParameter("t_dsfgs");
		//t_dsfgs = sysManage.searchDwNameByDwCode(t_dsfgs);
		String t_zdbm = request.getParameter("t_zdbm");
		String t_zdmc = wyManage.searchZdmcByZdbm(t_zdbm);
		String t_dh = request.getParameter("t_dh");
		String t_zq1 = request.getParameter("t_zq1");
		String t_zq2 = request.getParameter("t_zq2");
		
		String t_jfje = request.getParameter("t_jfje");
		String t_jfrq = request.getParameter("t_jfrq");
		String t_xzbs = request.getParameter("t_xzbs");
		String t_jfpjlx = request.getParameter("t_jfpjlx");
		String t_gdfmc = request.getParameter("t_gdfmc");
		String t_kh = request.getParameter("t_kh");
		String t_ftbl = request.getParameter("t_ftbl");
		String t_fsyz = request.getParameter("t_fsyz");
		String t_ftje = request.getParameter("t_ftje");
		String t_kplx = request.getParameter("t_kplx");
		String t_bz = request.getParameter("t_bz")==null||request.getParameter("t_bz").equals("null")||request.getParameter("t_bz").equals("")?"":request.getParameter("t_bz");
		String t_dycwbxdje = request.getParameter("t_dycwbxdje");
		String t_dycwhxdje = request.getParameter("t_dycwhxdje");
		String t_jgje = request.getParameter("t_jgje");
		String t_cy = request.getParameter("t_cy");
		String t_wyid = request.getParameter("t_wyid");
		
		WydfftBean wydfft = new WydfftBean();
		wydfft.setXZBS(t_xzbs);
		wydfft.setKPLX(t_kplx);
		wydfft.setJFPJLX(t_jfpjlx);
		wydfft.setYEARMONTH(t_yearmonth);
		wydfft.setSFGS(t_sfgs);
		wydfft.setDSFGS(t_dsfgs);
		wydfft.setZDMC(t_zdmc);
		wydfft.setZDBM(t_zdbm);
		wydfft.setDH(t_dh);
		wydfft.setZQ(t_zq1+";"+t_zq2);
		wydfft.setJFJE(t_jfje);
		wydfft.setJFRQ(t_jfrq);
		wydfft.setGDFMC(t_gdfmc);
		wydfft.setKH(t_kh);
		wydfft.setFTBL(t_ftbl);
		wydfft.setFSYZ(t_fsyz);
		wydfft.setFTJE(t_ftje);
		wydfft.setBZ(t_bz);
		wydfft.setDYCWBXJE(t_dycwbxdje);
		wydfft.setDYCWHXJE(t_dycwhxdje);
		wydfft.setJGJE(t_jgje);
		wydfft.setCY(t_cy);
		wydfft.setID(t_wyid);
		wydfft.setYEARMONTH(t_yearmonth);
		
//		String t_rq = request.getParameter("t_rq");
//		String t_bxlx = request.getParameter("t_bxlx");
//		String t_djbh = request.getParameter("t_djbh");
//		String t_zy = request.getParameter("t_zy");
//		String t_djzt = request.getParameter("t_djzt");
//		String t_bm = request.getParameter("t_bm");
//		String t_sfje = request.getParameter("t_sfje");
//		String t_fphsje = request.getParameter("t_fphsje");
//		String t_fpbhsje = request.getParameter("t_fpbhsje");
//		String t_wycy = request.getParameter("t_wycy");
//		String t_cwcy = request.getParameter("t_cwcy");
//		String t_cwyfthydcwbx = request.getParameter("t_cwyfthydcwbx");
//		String t_cwid = request.getParameter("t_cwid");
		
		
//		CwybxBean cwybx = new CwybxBean();
//		cwybx.setID(t_cwid);
//		cwybx.setBZ(t_bz);
//		cwybx.setCY(t_cwcy);
//		cwybx.setFSYZ(t_fsyz);
//		cwybx.setCWYFTHYDCWBXJE(t_cwyfthydcwbx);
//		cwybx.setYWFTJE(t_ftje);
//		cwybx.setYEARMONTH(t_yearmonth);
//		cwybx.setDJZT(t_djzt);
//		cwybx.setRQ(t_rq);
//		cwybx.setBXLX(t_bxlx);
//		cwybx.setDJPH(t_djbh);
//		cwybx.setZY(t_zy);
//		cwybx.setBM(t_bm);
//		cwybx.setSFJE(t_sfje);
//		cwybx.setFPHSJE(t_fphsje);
//		cwybx.setFPBHSJE(t_fpbhsje);
//		cwybx.setFPHSJE(t_fphsje);
//		cwybx.setJSDH(t_dh);
//		cwybx.setZDMC(t_zdmc);
//		cwybx.setZDBH(t_zdbm);
		
//		boolean flag1 = true;
		boolean flag2 = true;
		
//		if(cwybx.getID()!=null&&!cwybx.getID().equals("")&&!cwybx.getID().equals("null")){
//			flag1 = cwManage.updateCwdfft(cwybx);
//		}
		
		if(wydfft.getID()!=null&&!wydfft.getID().equals("")&&!wydfft.getID().equals("null")){
			flag2 = wyManage.updateWydfft(wydfft);
		}
		if(flag2){
			msg = "物业数据电费分摊修改失败！";
		}else{
			msg = "物业数据电费分摊修改成功！";
		}
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_wydfft_search.jsp";
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
