package com.noki.zwhd.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.noki.database.DataBase;
import com.noki.zwhd.manage.DfManage;
import com.noki.zwhd.manage.DxDfManage;
import com.noki.zwhd.model.DxdfftqrBean;

public class DxdfftqrAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private String msg;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("dxadd")){
		try {
			String dw = "137";
			String qx = "13715";
			String zdmc1 = request.getParameter("t_zdmc");
			String sql = "select jzname from zhandian where jzcode='"+zdmc1+"'";
			DataBase db = new DataBase();
			ResultSet rs = null;
			String zdmc = "";
			db.connectDb();
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				zdmc=rs.getString("jzname");
			}
			String zdbm = request.getParameter("t_zdbm");
			String dbbm = request.getParameter("t_dbbm");
			//String gdfs = request.getParameter("t_gdfs");
			//String jsfs = request.getParameter("t_jsfs");
			String dh = request.getParameter("t_dh");
			String jfqsrq = request.getParameter("t_jfqsrq");
			//System.out.println(jfqsrq);
			String jfzzrq = request.getParameter("t_jfzzrq");
			String qm = request.getParameter("t_qm");
			String zm = request.getParameter("t_zm");
			String dliang = request.getParameter("t_dliang");
			String dj = request.getParameter("t_dj");
			String jfje = request.getParameter("t_jfje");
			String jfrq = request.getParameter("t_jfrq");
			String jfpjlx = request.getParameter("t_jfpjlx");
			String gdfmc = request.getParameter("t_gdfmc");
			String kh = "电信";
			String dliu = request.getParameter("t_dliu");
			String ftbl = request.getParameter("t_ftbl");
			String ftje = request.getParameter("t_ftje");
			String pici = request.getParameter("pici");
			String qrzt = "已确认";
			String yysxgzt = "已修改";
			String fsyz = request.getParameter("t_fsyz");
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if (dw.equals("0")) {
				msg = "单位不能为空！";
			} else if (qx.equals("0")) {
				msg = "区县不能为空！";
			} else if (zdmc == null) {
				msg = "站点名称不能为空！";
			} else if (zdbm == null) {
				msg = "站点编码不能为空！";
			}
//			if (gdfs == null) {
//				msg = "供电方式不能为空！";
//			} else if (jsfs == null) {
//				msg = "结算方式不能为空！";
//			}
			else if (dh == null) {
				msg = "单号不能为空！";
			}
//			else if (jfqsrq == null) {
//				msg = "缴费起始日期不能为空！";
//			}
			if (jfzzrq == null) {
				msg = "缴费终止日期不能为空！";
			} else if (qm == null) {
				msg = "起码不能为空！";
			}
			if (zm == null) {
				msg = "止码不能为空！";
			} else if (dliang == null) {
				msg = "电量不能为空！";
			} else if (dliu == null) {
				msg = "电流不能为空！";
			}
//			else if (dj == null) {
//				msg = "单价不能为空！";
//			}
			else if (Integer.parseInt(qm) > Integer.parseInt(zm)) {
				msg = "起码不能大于止码";
			} else if (Integer.parseInt(qm) <= 0) {
				msg = "起码必须大于0";
			} else if (Integer.parseInt(dliang) <= 0) {
				msg = "电量必须大于0";
			}
//			else if (Integer.parseInt(dj) <= 0) {
//				msg = "电价必须大于0";
//			}
//			else if (formate.parse(jfqsrq).getTime() > formate.parse(jfzzrq).getTime()) {
//				msg = "缴费起始日期必须小于等于缴费终止日期";
//			} 
			else {
				DxdfftqrBean bean = new DxdfftqrBean();
				bean.setDw(dw);
				bean.setQx(qx);
				bean.setZdmc(zdmc);
				bean.setZdbm(zdbm);
				bean.setGdfmc(gdfmc);
				//bean.setJsfs(jsfs);
				//bean.setGdfs(gdfs);
				bean.setDh(dh);
				bean.setJfqsrq(jfqsrq);
				bean.setJfzzrq(jfzzrq);
				bean.setQm(qm);
				bean.setZm(zm);
				bean.setDliang(dliang);
				bean.setDj(dj);
				bean.setJfje(jfje);
				bean.setJfpjlx(jfpjlx);
				bean.setKh(kh);
				bean.setDliu(dliu);
				bean.setFtbl(ftbl);
				bean.setFtje(ftje);
				bean.setJfrq(jfrq);
				bean.setPici(pici);
				bean.setQrzt(qrzt);
				bean.setDbbm(dbbm);
				bean.setFsyz(fsyz);
				bean.setYysxgzt(yysxgzt);
				DfManage dfManage = new DfManage();
				boolean flag = dfManage.saveDxdfftqr(bean);

				request.setCharacterEncoding("utf-8");// 设置输入编码格式。
				response.setContentType("text/html;charsetType=utf-8");// 设置输出编码格式。
				msg = "添加电信电费单" + (flag ? "成功！" : "失败！");

			}
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_dxdfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		catch (Exception e) {
			msg = "系统故障："+e.getMessage()+"请于管理员联系！";
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_dxdfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		}else if(action.equals("dxupdate")){
			try{
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String dliu = request.getParameter("t_dliu");
			String ftbl = request.getParameter("t_ftbl");
			String ftje = request.getParameter("t_ftje");
			String fsyz = request.getParameter("t_fsyz");
			String jfqsrq = request.getParameter("t_jfqs");
			String jfzzrq = request.getParameter("t_jfzz");
			String qm = request.getParameter("t_qm");
			String zm = request.getParameter("t_zm");
			String dliang = request.getParameter("t_dliang");
			String danjia = request.getParameter("t_danjia");
			DxdfftqrBean bean = new DxdfftqrBean();
			bean.setDliu(dliu);
			bean.setFtbl(ftbl);
			bean.setFtje(ftje);
			bean.setFsyz(fsyz);
			bean.setJfqsrq(jfqsrq);
			bean.setJfzzrq(jfzzrq);
			bean.setQm(qm);
			bean.setZm(zm);
			bean.setDj(danjia);
			bean.setId(id);
			bean.setDliang(dliang);
			DxDfManage dfManage = new DxDfManage();
			boolean flag = dfManage.saveDxdfftqr(bean);

			request.setCharacterEncoding("utf-8");// 设置输入编码格式。
			response.setContentType("text/html;charsetType=utf-8");// 设置输出编码格式。
			msg = "修改电信电费单" + (flag ? "成功！" : "失败！");
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_dxdfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
			}catch (Exception e) {
			msg = "系统故障："+e.getMessage()+"请于管理员联系！";
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_dxdfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		}
	}

		}
