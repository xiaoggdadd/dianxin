package com.noki.YuFuFei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DbException;
import com.noki.log.DbLog;

public class YufufeiServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//设置输入编码格式。
		res.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		String action = req.getParameter("action");
		String path = req.getContextPath();
		DbLog log = new DbLog();
		HttpSession session = req.getSession();
		String accountid = (String) session.getAttribute("loginName");
		if(action.equals("addyff")){
			String url = path+"/web/sdttWeb/DianfeiManger/addYuFuFei.jsp",msg="";
			String dbbm = req.getParameter("dbbm");
			String jfqsrq = req.getParameter("t_jfqsrq");
			String jfzzrq = req.getParameter("t_jfzzrq");
			String qm = req.getParameter("t_qm");
			String zm = req.getParameter("t_zm");
			String dl = req.getParameter("t_dliang");
			String dj = req.getParameter("t_dj");
			String pjlx = req.getParameter("pjlx");
			String yg = req.getParameter("t_yg");
			String ftje = req.getParameter("t_ftje");
			YuFuFeiBean bean = new YuFuFeiBean();
			bean.setDbbm(dbbm);
			bean.setJfqsrq(jfqsrq);
			bean.setJfzzrq(jfzzrq);
			bean.setQm(qm);
			bean.setZm(zm);
			bean.setDl(dl);
			bean.setDj(dj);
			bean.setPjlx(pjlx);
			bean.setYg(yg);
			bean.setFtje(ftje);
			YuFuFeiManager m = new YuFuFeiManager();
			boolean b=false;
			try {
				b=m.SaveYff(bean);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(b==true){
				msg="添加预付费成功！";
			}else{
				msg="添加失败，请联系管理员！";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "添加预付费信息");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}else if(action.equals("delete")){
			String url = path+"/web/sdttWeb/DianfeiManger/YuFuFeiQuery.jsp",msg="";
			String dlid=req.getParameter("dlid");
			String dfid=req.getParameter("dfid");
			YuFuFeiManager m = new YuFuFeiManager();
			boolean b=false;
			try {
				b=m.deleteYff(dlid, dfid);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(b==true){
				msg="删除预付费成功";;
			}else{
				msg="删除失败";;
			}
			log.write(msg, accountid, req.getRemoteAddr(), "删除预付费信息");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}else if(action.equals("updateyff")){
			String url = path+"/web/sdttWeb/DianfeiManger/addYuFuFei.jsp",msg="";
			String dlid = req.getParameter("dlid");
			String dfid = req.getParameter("dfid");
			String dbbm = req.getParameter("dbbm");
			String jfqsrq = req.getParameter("t_jfqsrq");
			String jfzzrq = req.getParameter("t_jfzzrq");
			String qm = req.getParameter("t_qm");
			String zm = req.getParameter("t_zm");
			String dl = req.getParameter("t_dliang");
			String dj = req.getParameter("t_dj");
			String pjlx = req.getParameter("pjlx");
			String yg = req.getParameter("t_yg");
			String ftje = req.getParameter("t_ftje");
			YuFuFeiBean bean = new YuFuFeiBean();
			bean.setDlid(dlid);
			bean.setDfid(dfid);
			bean.setDbbm(dbbm);
			bean.setJfqsrq(jfqsrq);
			bean.setJfzzrq(jfzzrq);
			bean.setQm(qm);
			bean.setZm(zm);
			bean.setDl(dl);
			bean.setDj(dj);
			bean.setPjlx(pjlx);
			bean.setYg(yg);
			bean.setFtje(ftje);
			YuFuFeiManager m = new YuFuFeiManager();
			boolean b=false;
			try {
				b=m.UpdateYff(bean);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(b==true){
				msg="修改预付费成功！";
			}else{
				msg="修改失败，请联系管理员！";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "修改预付费信息");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
