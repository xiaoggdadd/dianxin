package com.noki.jichuInfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;

public class SfyzServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("111111111111111");
		req.setCharacterEncoding("utf-8");//设置输入编码格式。
		res.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		String action = req.getParameter("action");
		String path = req.getContextPath();
		DbLog log = new DbLog();
		HttpSession session = req.getSession();
		String accountid = (String) session.getAttribute("loginName");
	
		if(action.equals("addsfyz")){
			String url = path+"/web/sdttWeb/jichuInfoManager/addSfyz.jsp",msg="";
			String zdmc = req.getParameter("zdmc");
			String dbbm = req.getParameter("dbbm");
			String sfyz = req.getParameter("sfyz");
			String yearmonth = req.getParameter("yearmonth");
			String bz = req.getParameter("bz");
			String id = (String) session.getAttribute("sfyz")!=null?(String) session.getAttribute("sfyz"):"";
			if(bz.equals("1")){
				DataBase db1 = new DataBase();
				String sqll = "update sfyz set zdid='"+zdmc+"',dbbm='"+dbbm+"',yearmonth='"+yearmonth+"',sfyz='"+sfyz+"' where id ="+id+"";
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
	            	msg="税负因子修改成功";
	            }else {
	            	msg="修改失败，请联系管理员";
	            }
			}else{
				DataBase db = new DataBase();
				String sql = "INSERT INTO SFYZ (ZDID,DBBM,YEARMONTH,SFYZ) VALUES ("+zdmc+","+dbbm+",'"+yearmonth+"',"+sfyz+")";
				System.out.println(sql);
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
	            	msg="税负因子添加成功";
	            }else {
	            	msg="添加失败，请联系管理员";
	            }
			}
			log.write(msg, accountid, req.getRemoteAddr(), "添加税负因子");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}else if(action.equals("delsite")){
			String url = path+"/web/sdttWeb/jichuInfoManager/sfyzmanage.jsp",msg="";
			String id=req.getParameter("id");
			String s="delete from sfyz where id='"+id+"'";
			DataBase db = new DataBase();
			int a=0;
			try {
				db.connectDb();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				db.setAutoCommit(false);
				a=db.update(s.toString());
				db.commit();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a==1){
				msg="刪除稅負因子成功！";
			}else{
				msg="刪除失敗，請聯繫管理員";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "刪除稅負因子");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}
	}

}
