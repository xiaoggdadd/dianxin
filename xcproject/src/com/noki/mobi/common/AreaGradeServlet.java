/**
 *
 */
package com.noki.mobi.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DbException;
import com.noki.jizhan.JiZhanBean;

/**
 * @author Administrator
 * 
 */
public class AreaGradeServlet extends HttpServlet {
	// private static final String Content_type = "text/html;charset=UTF-8";
	/**
     *
     */
	public AreaGradeServlet() {
		// TODO Auto-generated constructor stub
	}

	// private static final String Content_type = "text/html;charset=GB2312";
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// 获取登录用户
		Account account = new Account();
		HttpSession session = req.getSession();
		account = (Account) session.getAttribute("account");
		String userid = account.getAccountId();
		// resp.setContentType(Content_type);
		String msg = "操作失败！请重试或与管理员联系";

		resp.setContentType("text/xml; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter out = resp.getWriter();
		String action = req.getParameter("action");
		System.out.println("jilian do get(action):" + action);
		if (action.equals("sheng")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_shi(pid, userid);
			System.out.println(pid);
			out.print(list);
			out.close();
		} else if (action.equals("shi")) {
			String pid = req.getParameter("pid");
			System.out.println("pid:"+pid);
			// System.out.println(pid);
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_xian(pid, userid);
			out.print(list);
			out.close();
		} else if (action.equals("zdmc")) {
			String zdmc = new String(
					req.getParameter("code").getBytes("UTF-8"), "UTF-8");
			CommonAccountBean bean = new CommonAccountBean();
			String list = null;
			try {
				list = bean.getDbbm(zdmc);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(list);
			out.close();
		} else if (action.equals("zdm")) {
			String zdm = (String) req.getParameter("code");
			CommonAccountBean bean = new CommonAccountBean();
			String list = null;
			try {
				list = bean.getZdmc(zdm);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(list);
			out.close();
		} else if (action.equals("xian")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_xiaoqu(pid, userid);
			out.print(list);
			out.close();
		} else if (action.equals("xiaoqu")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_jizhan(pid);
			out.print(list);
			out.close();
		} else if (action.equals("jzname")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_zhandian(pid);
			out.print(list);
			out.close();
		} else if (action.equals("username")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.get_zhandian(pid);
			out.print(list);
			out.close();
		} else if (action.equals("quxian2")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getzdmc2(pid);
			out.print(list);
			out.close();
		} else if (action.equals("quxian")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getzdmc(pid);
			out.print(list);
			out.close();
		} else if (action.equals("dbbm")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getD(pid);
			out.print(list);
			out.close();
		} else if (action.equals("bumen")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getBumen(pid);
			out.print(list);
			out.close();
		} else if (action.equals("banzu")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getbanzu(pid);
			out.print(list);
			out.close();
		} else if (action.equals("stationtype")) {
			String pid = req.getParameter("pid");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChildrenArea_stationtype(pid, userid);
			out.print(list);
			out.close();
		} else if (action.equals("sszy")) {
			String pid = req.getParameter("pid");
			String s1 = req.getParameter("s1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getQcb(pid, s1);
			out.print(list);
			out.close();
		} else if (action.equals("qcb")) {
			String pid = req.getParameter("pid");
			String q1 = req.getParameter("q1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getQcb(pid, q1);
			out.print(list);
			out.close();
		} else if (action.equals("zymx")) {
			String pid = req.getParameter("pid");
			String z1 = req.getParameter("z1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getQcb(pid, z1);
			out.print(list);
			out.close();
		} else if (action.equals("zdsx")) {
			String pid = req.getParameter("pid");
			// String z1=req.getParameter("z1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getZdsx(pid);
			out.print(list);
			out.close();
		} else if (action.equals("changjia")) {
			String pid = req.getParameter("pid");
			// String z1=req.getParameter("z1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getChangjia(pid);
			out.print(list);
			out.close();
		} else if (action.equals("zdsxnull")) {
			String pid = req.getParameter("pid");
			// String z1=req.getParameter("z1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getZdsxnull(pid);
			out.print(list);
			out.close();
		} else if (action.equals("zdsxa")) {
			String pid = req.getParameter("pid");
			// String z1=req.getParameter("z1");
			CommonAccountBean bean = new CommonAccountBean();
			String list = bean.getZdsx2(pid);
			out.print(list);
			out.close();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
