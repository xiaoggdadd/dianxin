package com.noki.dgyydgl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.dgyydgl.javabean.DgyydBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.flow.javabean.FlowBean;

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

public class DgyydServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();
		Account account = new Account();
		String url = path + "/web/sdttWeb/dgyyd/list.jsp", msg = "";
		HttpSession session = req.getSession();
		account = (Account) session.getAttribute("account");

		DgyydBean bean = new DgyydBean();
		String action = req.getParameter("action");
		if (action.equals("add")) {
			String bz = req.getParameter("bz");
			String shi = req.getParameter("shi");
			String xian = req.getParameter("xian");
			String xiaoqu = req.getParameter("xiaoqu");
			String byqrl = req.getParameter("byqrl");
			String glys = req.getParameter("glys");
			String dj = req.getParameter("dj");
			String zhdj = req.getParameter("zhdj");
			String jc = req.getParameter("jc");
			String zddj = req.getParameter("zddj");
			String phddl = req.getParameter("phddl");
			String rl = req.getParameter("rl");
			String zb = req.getParameter("zb");
			if ("1".equals(bz)) {
				String id = (String) session.getAttribute("id");
				String sql = "UPDATE dgyydgl SET shi='" + shi + "',xian='"
						+ xian + "',xiaoqu='" + xiaoqu + "',byqrl='" + byqrl
						+ "'," + "glys='" + glys + "',dj='" + dj + "',zhdj='"
						+ zhdj + "',jc='" + jc + "',zddj='" + zddj
						+ "',phddl='" + phddl + "',rl='" + rl + "',zb='" + zb
						+ "' where id=" + id + "";
				DataBase db = new DataBase();
				System.out.println("====="+sql);
				int a = 0;
				try {
					db.connectDb();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					a = db.update(sql.toString());
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (a == 1) {
					msg = "修改信息成功";
				} else {
					msg = "修改失败，请联系管理员！";
				}
				log.write(msg, account.getAccountName(), req.getRemoteAddr(),
						"修改信息");
				PrintWriter out = resp.getWriter();
				String ss = "'" + path + "/web/sdttWeb/dgyyd/list.jsp'";
				out.print("<script>opener.location=" + ss
						+ ";window.close();</script>");
			} else {
				msg = bean.add(shi, xian, xiaoqu, byqrl, glys, dj, zhdj, jc,
						zddj, phddl, rl, zb);
				log.write(msg, account.getAccountName(), req.getRemoteAddr(),
						"添加信息");
				PrintWriter out = resp.getWriter();
				String ss = "'" + path + "/web/sdttWeb/dgyyd/list.jsp'";
				out.print("<script>opener.location=" + ss
						+ ";window.close();</script>");
			}

		} else if (action.equals("modify")) {
			String flowId = req.getParameter("flowId");
			String name = req.getParameter("flowName");
			String flowDesc = req.getParameter("flowDesc") != null ? req
					.getParameter("flowDesc") : "";
			msg = bean.modifyFlow(flowId, name, flowDesc);
			log.write(msg, account.getAccountName(), req.getRemoteAddr(),
					"修改流程");
			PrintWriter out = resp.getWriter();
			String ss = "'" + path + "/web/sdttWeb/Flow/flowList.jsp'";
			out.print("<script>opener.location=" + ss
					+ ";window.close();</script>");

		} else if (action.equals("del")) {
			String id = req.getParameter("id");
			msg = bean.del(id);
			log.write(msg, account.getAccountName(), req.getRemoteAddr(),
					"删除信息");

		}
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		resp.sendRedirect(path + "/web/msg.jsp");
	}

}
