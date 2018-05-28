package com.noki.zwhd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.DfManageYd;
import com.noki.zwhd.model.DxdfftqrBean;

public class YddfftqrAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private String msg;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String dw = request.getParameter("t_dw");
			String qx = request.getParameter("t_qx");
			String zdmc = request.getParameter("t_zdmc");
			
			String gdfs = request.getParameter("t_gdfs");
			String jsfs = request.getParameter("t_jsfs");
			
			//String jfqsrq = request.getParameter("t_jfqsrq");
			//String jfzzrq = request.getParameter("t_jfzzrq");
			String qm = request.getParameter("t_qm");
			String zm = request.getParameter("t_zm");
			
			
			String jfje = request.getParameter("t_jfje");
			
			String jfpjlx = request.getParameter("t_jfpjlx");
			String gdfmc = request.getParameter("t_gdfmc");
			
			String dliu = request.getParameter("t_dliu");
			String ftbl = request.getParameter("t_ftbl");
			String ftje = request.getParameter("t_ftje");
             //确定某个账单
			String dbbm=request.getParameter("t_dbbm");//电表编码
			String zdbm = request.getParameter("t_zdbm");//站点编码
			String pc=request.getParameter("t_yearmonth");//批次
			String dh = request.getParameter("t_dh");//单号
			String kh="移动";//客户
			//需要更新的东西
			String jszq=request.getParameter("t_jszq");//账期
			String jfzzrq=request.getParameter("t_bccbsj");//本次抄表时间
			String jfqsrq=request.getParameter("t_sccbsj");//上传抄表时间
			String cbzm=request.getParameter("t_bccbzm");//本次抄表止码
			String cbsczm=request.getParameter("t_sccbzm");//上次抄表止码
			String dliang=request.getParameter("t_dliang");//电量
			String dj=request.getParameter("t_dj");//单价
			String jfrq=request.getParameter("t_jfrq");//缴费日期
			String sh=request.getParameter("t_sunhao");//损耗
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

//			if (dw.equals("0")) {
//				msg = "单位不能为空！";
//			} else if (qx.equals("0")) {
//				msg = "单位不能为空！";
//			} else if (zdmc == null) {
//				msg = "站点名称不能为空！";
//			} else if (zdbm == null) {
//				msg = "站点编码不能为空！";
//			}
//			if (gdfs == null) {
//				msg = "供电方式不能为空！";
//			} else if (jsfs == null) {
//				msg = "结算方式不能为空！";
//			} else if (dh == null) {
//				msg = "单号不能为空！";
//			} else if (jfqsrq == null) {
//				msg = "缴费起始日期不能为空！";
//			}
//			if (jfzzrq == null) {
//				msg = "缴费终止日期不能为空！";
//			} else if (qm == null) {
//				msg = "起码不能为空！";
//			}
//			if (zm == null) {
//				msg = "止码不能为空！";
//			} else if (dliang == null) {
//				msg = "电量不能为空！";
//			} else if (dliu == null) {
//				msg = "电流不能为空！";
//			} else if (dj == null) {
//				msg = "单价不能为空！";
//			} else if (Integer.parseInt(qm) > Integer.parseInt(zm)) {
//				msg = "起码不能大于止码";
//			} else if (Integer.parseInt(qm) <= 0) {
//				msg = "起码必须大于0";
//			} else if (Integer.parseInt(dliang) <= 0) {
//				msg = "电量必须大于0";
//			} else if (Integer.parseInt(dj) <= 0) {
//				msg = "电价必须大于0";
//			} else if (formate.parse(jfqsrq).getTime() > formate.parse(jfzzrq).getTime()) {
//				msg = "缴费起始日期必须小于等于缴费终止日期";
			//} else {
				DxdfftqrBean bean = new DxdfftqrBean();
				bean.setZdbm(zdbm);
				bean.setDbbm(dbbm);
				bean.setPc(pc);
				bean.setDh(dh);
				bean.setKh(kh);
				
				bean.setJszq(jszq);//账期
				bean.setQm(cbsczm);//起码
				bean.setZm(cbzm);//止码
				bean.setDliang(dliang);//电量
				bean.setDj(dj);//单价
				bean.setJfrq(jfrq);//缴费日期
				bean.setSh(sh);//损耗
				bean.setJfqsrq(jfqsrq);//上传抄表时间
				bean.setJfzzrq(jfzzrq);//本次抄表时间
				
			

				DfManageYd dfManage = new DfManageYd();
				boolean flag = dfManage.saveYddfftqr(bean);

				request.setCharacterEncoding("utf-8");// 设置输入编码格式。
				response.setContentType("text/html;charsetType=utf-8");// 设置输出编码格式。
				msg = "修改移动电费单" + (flag ? "成功！" : "失败！");

			//}
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_yddfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		} catch (Exception e) {
			msg = "系统故障："+e.getMessage()+"请于管理员联系！";
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_yddfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}

	}

}
