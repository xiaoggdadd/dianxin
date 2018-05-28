package com.noki.jichuInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;

public class DianjiaServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//设置输入编码格式。
		res.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		String action = req.getParameter("action");
		String path = req.getContextPath();
		DbLog log = new DbLog();
		String entrypensonnelrg=req.getParameter("accountname");
		SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String entrytimerg=mat.format(new Date());
		HttpSession session = req.getSession();
		String accountid = (String) session.getAttribute("loginName");
		String url = path+"/web/sdttWeb/jichuInfoManager/danjiamanage.jsp",msg="";
		if(action.equals("adddianjia")){
			String zdmc = req.getParameter("zdmc");
			String dbbm = req.getParameter("dbbm");
			String yearmonth = req.getParameter("yearmonth");
			String danjia = req.getParameter("danjia");
			String id = (String) session.getAttribute("danjia")!=null?(String) session.getAttribute("danjia"):"";
			String bz = req.getParameter("bz");
			if(bz.equals("1")){
				DataBase db1 = new DataBase();
				String sqll = "update dianjia set zdid='"+zdmc+"',dbbm='"+dbbm+"',yearmonth='"+yearmonth+"',danjia='"+danjia+"' where id ="+id+"";
				int b=0;
				try {
					db1.connectDb();
				} catch (DbException e) {
					e.printStackTrace();
				}
				try {
					b=db1.update(sqll.toString());
				} catch (DbException e) {
					e.printStackTrace();
				}
				if(b==1){
	            	msg="电价修改成功";
	            }else {
	            	msg="修改失败，请联系管理员";
	            }
				log.write(msg, accountid, req.getRemoteAddr(), "修改电价");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				res.sendRedirect(path + "/web/msg.jsp");
			}else{
				DataBase db = new DataBase();
				String sql = "INSERT INTO DIANJIA (ZDID,DBBM,YEARMONTH,DANJIA,ZDBM,DBID,CREATE_DATE,CREATE_USERID) VALUES ('"+zdmc+"','"+dbbm+"','"+yearmonth+"','"+danjia+"',(select jzcode from zhandian where id="+zdmc+"),(select dbid from dianbiao where dbbm='"+dbbm+"'),to_date('"+entrytimerg+"','yyyy-mm-dd hh24:mi:ss'),'"+accountid+"')";
				System.out.println(sql.toString());
				int a=0;
				try {
					db.connectDb();
				} catch (DbException e) {
					e.printStackTrace();
				}
				try {
					a=db.update(sql.toString());
				} catch (DbException e) {
					e.printStackTrace();
				}
				if(a==1){
	            	msg="电价添加成功";
	            }else {
	            	msg="添加失败，请联系管理员";
	            }
				log.write(msg, accountid, req.getRemoteAddr(), "添加电价");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				res.sendRedirect(path + "/web/msg.jsp");
			}
		}else if(action.equals("deletedianjia")){
			String id = req.getParameter("id");
			DataBase db = new DataBase();
			String sql = "DELETE FROM DIANJIA WHERE ID = '"+id+"'";
			int a=0;
			try {
				db.connectDb();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a=db.update(sql.toString());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a==1){
				msg="删除电价成功";
			}else{
				msg="删除失败，请联系管理员";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "删除电价");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
