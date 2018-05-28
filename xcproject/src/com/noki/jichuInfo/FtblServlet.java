package com.noki.jichuInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;

public class FtblServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//设置输入编码格式。
		res.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		String action = req.getParameter("action");
		String path = req.getContextPath();
		DbLog log = new DbLog();
		HttpSession session = req.getSession();
		String accountid = (String) session.getAttribute("loginName");
		
		if(action.equals("addftbl")){
			String url = path+"/web/sdttWeb/jichuInfoManager/addFtbl.jsp",msg="";
			 DecimalFormat df = new DecimalFormat("#.00");
			String zdbm = req.getParameter("zdmc");
			String dbbm = req.getParameter("dbbm");
			String dbid="";
			String sq = "select dbid from dianbiao where dbbm='"+dbbm+"'";
			DataBase dbb = new DataBase();
			try {
				dbb.connectDb();
			} catch (DbException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			ResultSet rs = null;
			try {
				rs=dbb.queryAll(sq.toString());
			} catch (DbException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				while(rs.next()){
					dbid = rs.getString("dbid");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			String year = req.getParameter("yearmonth");
			Double yddl = Double.valueOf(req.getParameter("yddl"));
			Double ltdl = Double.valueOf(req.getParameter("ltdl"));
			Double dxdl = Double.valueOf(req.getParameter("dxdl"));
			Double qtdl = Double.valueOf(req.getParameter("qtdl"));
			Double zdl = Double.valueOf(req.getParameter("zdl"));
			Double a = Double.valueOf(req.getParameter("a"));
			Double b = Double.valueOf(req.getParameter("b"));
			Double c = Double.valueOf(req.getParameter("c"));
			Double d = Double.valueOf(req.getParameter("d"));
			
			Double yidong = a*100;
			Double liantong = b*100;
			Double dianxin = c*100;
			Double qita = d*100;
			String bz = req.getParameter("bz");
			String id = (String) session.getAttribute("idd")==null?"":(String) session.getAttribute("idd");
			if("1".equals(bz)){
				DataBase db1 = new DataBase();
				String sqll = "update ftbl set dbid='"+dbid+"', zdid='"+zdbm+"',dbbm='"+dbbm+"',yearmonth='"+year+"',yidong='"+df.format(yidong)+"',liantong='"+df.format(liantong)+"',qita='"+df.format(qita)+"',dianxin='"+df.format(dianxin)+"',yddl='"+yddl+"',ltdl='"+ltdl+"',dxdl='"+dxdl+"',qtdl='"+qtdl+"',zdl='"+zdl+"' where id ="+id+"";
				System.out.println(sqll);
				int q=0;
				try {
					db1.connectDb();
				}catch (DbException e) {
					e.printStackTrace();
				}
				try {
					q=db1.update(sqll.toString());
				} catch (DbException e) {
					e.printStackTrace();
				}
				if(q==1){
	            	msg="分摊比例修改成功";
	            }else {
	            	msg="修改失败，请联系管理员";
	            }
				log.write(msg, accountid, req.getRemoteAddr(), "修改分摊比例");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				res.sendRedirect(path + "/web/msg.jsp");
			}else{
			DataBase db = new DataBase();
			String sql = "INSERT INTO FTBL(DBID,ZDID,DBBM,YEARMONTH,YIDONG,LIANTONG,DIANXIN,QITA,YDDL,LTDL,DXDL,QTDL,ZDL,ZDBM) VALUES ("+dbid+","+zdbm+","+dbbm+",'"+year+"','"+df.format(yidong)+"','"+df.format(liantong)+"','"+df.format(dianxin)+"','"+df.format(qita)+"',"+yddl+","+ltdl+","+dxdl+","+qtdl+","+zdl+",(select jzcode from zhandian where id='"+zdbm+"'))";
			System.out.println(sql.toString());
			int w=0;
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				w=db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			if(w==1){
            	msg="分摊比例添加成功";
            }else {
            	msg="添加失败，请联系管理员";
            }
			log.write(msg, accountid, req.getRemoteAddr(), "添加分摊比例");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
			}
			}else if(action.equals("delete")){
				String url = path+"/web/sdttWeb/jichuInfoManager/ftblmanage.jsp",msg="";
				String id = req.getParameter("id");
				String sql = "DELETE FROM FTBL WHERE ID = "+id+"";
				DataBase db = new DataBase();
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
	            	msg="分摊比例删除成功";
	            }else {
	            	msg="添加失败，请联系管理员";
	            }
	            log.write(msg, accountid, req.getRemoteAddr(), "删除分摊比例");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				res.sendRedirect(path + "/web/msg.jsp");
			}
	}
}
