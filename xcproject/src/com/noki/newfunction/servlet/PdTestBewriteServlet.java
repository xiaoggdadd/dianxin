package com.noki.newfunction.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.noki.log.DbLog;
import com.noki.newfunction.dao.PdTestBewrite;
import com.noki.newfunction.javabean.Zdinfo;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class PdTestBewriteServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();

		String url = path + "/web/jzcbnewfunction/pdtestbewrite.jsp", msg = "";
		HttpSession session = req.getSession();

		PdTestBewrite bean = new PdTestBewrite();
		
		Zdinfo formBean = new Zdinfo();
		String action = req.getParameter("action");

		if (action.equals("cbzdxg")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());
			formBean.setWjid(req.getParameter("idcz"));
			formBean.setG2(req.getParameter("g2now"));
			formBean.setG3(req.getParameter("g2now"));
			formBean.setZp(req.getParameter("zpnow"));
			formBean.setZs(req.getParameter("zsnow"));
			formBean.setChangjia(req.getParameter("changjianow"));

			formBean.setJztype(req.getParameter("jztypenow"));
			formBean.setShebei(req.getParameter("shebeinow"));
			formBean.setBbu(req.getParameter("bbunow"));
			formBean.setRru(req.getParameter("rrunow"));
			formBean.setYdshebei(req.getParameter("ydshebeinow"));
			formBean.setGxgwsl(req.getParameter("gxgwslnow"));
			formBean.setCsms(req.getParameter("teststruction"));
			formBean.setYyfx(req.getParameter("reasonanalyse"));
			formBean.setCszrr(req.getParameter("testperson"));
			formBean.setQxlrr(req.getParameter("accountname"));
			formBean.setQxlrsj(entrytime);
			String path1=""; 
			String name="";
			msg = bean.updatePdTestBewritezdxx(formBean,path1,name);
			log.write(msg, formBean.getWjid(), req.getRemoteAddr(),"测试描述修改");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
