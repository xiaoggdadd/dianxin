package com.noki.biaogan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.biaogan.model.BiaoganBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class BGServlet extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	this.doPost(request, response);
}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//编码格式转换
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取选择
		String action = request.getParameter("action");
		String path = request.getContextPath();
		/*切勿格式化代码*/
		BiaoganBean biaoganBean = new BiaoganBean();
		DataBase db = new DataBase();
		HttpSession session = request.getSession();
		//url最终的跳转地址
		String url = path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp",msg="";
		//添加标杆
		if (action.equals("addBG")) {
			try {
				System.out.println("进入测试！");
				// 站点ID==电表ID
				String zdm = request.getParameter("zdmc").trim();
				int zdmc = Integer.parseInt(zdm);
				// 查询站点编码
				String sql = "select jzcode from zhandian a  where  a.id='"+zdmc+"'";
				ResultSet rs = db.queryAll(sql);
				String zdbm = "";
				System.out.println(sql);
				while (rs.next()) {
					zdbm = rs.getString(1);
					break;
				}
				// 电表编码
				String dbbm = request.getParameter("dbbm").trim();
				String ydxs = request.getParameter("ydxs").trim();
				String zlfh = request.getParameter("zlfh").trim();
				String ktxs = request.getParameter("ktxs").trim();
				String biaoganvalue = request.getParameter("biaoganvalue").trim();
				String yearmonth = request.getParameter("yearmonth").trim();
				String spsj = "2015-01";
				String spzt = "0";
				String zq = "0.0";
				// 这条语句
				String sql1 = "Insert into TBL_TT_BIAOGAN_LISHI "
						+"(ZDBM,YEARMONTH,DBBM,BIAOGANVALUE,ZLFH,YDXS,KTXS,SPZT,ZDID,DBID,ZQ)"
						+"values('"
						+zdbm
						+"','"
						+yearmonth
						+"','"
						+dbbm
						+"','"
						+biaoganvalue
						+"','"
						+zlfh
						+"','"
						+ydxs
						+"','"
						+ktxs
						+"','"
						+spzt
						+"','"
						+zdmc+"','"+zdmc+"','"+zq+"')";
				int b = db.update(sql1);
				if(b==1){
	            	msg="添加成功,即将返回首页";
	            }else {
	            	msg="添加失败,请联系管理员";
	            }
				session.setAttribute("url", url);
				//msg要显示的结果内容
				session.setAttribute("msg", msg);
				//跳转中转页面
				response.sendRedirect(path + "/web/nomsg.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DbException e) {
				e.printStackTrace();
			}

			
		} else if("delBG".equals(action)){
			try {
				System.out.println("删除标杆");
				String  id = request.getParameter("id");
				String sql1 = "select spzt from TBL_TT_BIAOGAN_LISHI where id ='"+id+"'";
				ResultSet  rs3 =db.queryAll(sql1);
				String spzt="";
				while (rs3.next()) {
					 spzt= rs3.getString(1);
					break;
				}
				//判断删除
				if(spzt.equals("0")){
					String sql = "delete TBL_TT_BIAOGAN_LISHI where id ='"+id+"'";
						int b =db.update(sql);
						if(b==1){
			            	msg="刪除成功,即将返回首页";
			            }else {
			            	msg="刪除失败,请联系管理员";
			            }
				}else{
					
					msg="已审核，不能删除。";
					
				}
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		}else if ("updateBG".equals(action)) {
			//进入修改Servlet
			System.out.println("修改Servlet");
			String id = request.getParameter("id");
			// 站点ID==电表ID
			String zdm = request.getParameter("zdmc");
			int zdmc = Integer.parseInt(zdm);
			// 查询站点编码
			try {
			String sql = "select jzcode from zhandian a  where  a.id='"+zdmc+"'";
			ResultSet rs;
				rs = db.queryAll(sql);
			String zdbm = "";
			System.out.println(sql);
			while (rs.next()) {
				zdbm = rs.getString(1);
				break;
			}
			// 电表编码
			String dbbm = request.getParameter("dbbm").trim();
			String ydxs = request.getParameter("ydxs").trim();
			String zlfh = request.getParameter("zlfh").trim();
			String ktxs = request.getParameter("ktxs").trim();
			String biaoganvalue = request.getParameter("bg").trim();
			String yearmonth = request.getParameter("yearmonth").trim();
			// 这条语句
			String sql1 ="update TBL_TT_BIAOGAN_LISHI set zdbm='"+zdbm+"',yearmonth='"+yearmonth+"',dbbm='"+dbbm+"',biaoganvalue='"+biaoganvalue+
					"',zlfh='"+zlfh+"',ydxs='"+ydxs+"',ktxs='"+ktxs+"',zdid='"+zdmc+"',dbid='"+zdmc+"'where id='"+id+"'";
			System.out.println(sql1);
			int  b = db.update(sql1);
			if(b==1){
            	msg="修改成功，即将跳转页面。";
            }else {
            	msg="修改失败，请联系管理员。";
            }
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/nomsg.jsp");
			} catch (DbException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}