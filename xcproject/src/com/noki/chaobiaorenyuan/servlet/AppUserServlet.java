package com.noki.chaobiaorenyuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.chaobiaorenyuan.Dao.GuanLiQuYuDao;

public class AppUserServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//修改编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/**
		 * 抄表人绑定电表的Servlet
		 */
		//接收前台数据
		String dbid = request.getParameter("dbid");	//获取电表ID
		String uuid = request.getParameter("uuid");//抄表人ID
		String caozuo = request.getParameter("caozuo");//判断是绑定还是删除
		//获取Dao层 
		GuanLiQuYuDao dao = new GuanLiQuYuDao(); 
		
		HttpSession session = request.getSession();
		//url最终的跳转地址
		String path = request.getContextPath();
		String url = path+"/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp",msg="";
		session.setAttribute("url", url);
		//msg要显示的结果内容
		session.setAttribute("msg", msg);
		
		//跳转中转页面
		if (uuid != null && !uuid.equals("") && !uuid.equals("0")) {
			if (dbid != null && !dbid.equals("") && !dbid.equals("0")) {
				if(caozuo.equals("bangding")){
					
				int rs = dao.addBangDing(uuid, dbid); //执行新增绑定方法
				if (rs > 0) {
					System.out.println(uuid);
					msg="绑定成功，即将返回主页面";
				} else {
					msg="绑定失败，请与管理员联系";
				}
				session.setAttribute("url", url);
				//msg要显示的结果内容
				session.setAttribute("msg", msg);
				//跳转中转页面
				response.sendRedirect(path + "/web/nomsg.jsp");
			}else if(caozuo.equals("jiebang")){
				int rs = dao.deleteBangDing(uuid, dbid); //执行解绑方法
				if (rs > 0) {
					msg="解绑成功，即将返回主页面";
				} else {
					msg="解绑失败，请与管理员联系";
				}
				session.setAttribute("url", url);
				//msg要显示的结果内容
				session.setAttribute("msg", msg);
				//跳转中转页面
				response.sendRedirect(path + "/web/nomsg.jsp");
				//批量绑定
			}else if(caozuo.equals("bangdingALL")){
			
				if(dbid.contains(",")){
					int rs = 0;
					String [] dbidArr = dbid.split(",");   //拆分字符串
					
					for (int i = 0; i < dbidArr.length; i++) {
						
						 rs = dao.addBangDing(uuid,dbidArr[i]); //执行新增绑定方法
					}
					if (rs > 0) {
						System.out.println(uuid);
						msg="绑定成功，即将返回主页面";
					} else {
						msg="绑定失败，请查看是否有已被绑定的电表或与管理员联系";
					}
					session.setAttribute("url", url);
					//msg要显示的结果内容
					session.setAttribute("msg", msg);
					//跳转中转页面
					response.sendRedirect(path + "/web/nomsg.jsp");
				}else{
					System.out.println("一个数据点击一键绑定时");
					int rs = dao.addBangDing(uuid, dbid); //执行新增绑定方法
					if (rs > 0) {
						System.out.println(uuid);
						msg="绑定成功，即将返回主页面";
					} else {
						msg="绑定失败，请与管理员联系";
					}
					session.setAttribute("url", url);
					//msg要显示的结果内容
					session.setAttribute("msg", msg);
					//跳转中转页面
					response.sendRedirect(path + "/web/nomsg.jsp");
				}
			}else if(caozuo.equals("bangdingALLNO")){
				if(dbid.contains(",")){
					int rs = 0;
					String [] dbidArr = dbid.split(",");   //拆分字符串
					
					for (int i = 0; i < dbidArr.length; i++) {
						
						 rs = dao.deleteBangDing(uuid,dbidArr[i]); //执行解绑方法
					}
					if (rs > 0) {
						System.out.println(uuid);
						msg="解绑成功，即将返回主页面";
					} else {
						msg="解绑失败，请查看是否有未被绑定的电表或与管理员联系";
					}
					session.setAttribute("url", url);
					//msg要显示的结果内容
					session.setAttribute("msg", msg);
					//跳转中转页面
					response.sendRedirect(path + "/web/nomsg.jsp");
				}else{
					System.out.println("一个数据点击一键解绑时");
					int rs = dao.deleteBangDing(uuid, dbid); //执行解绑方法
					if (rs > 0) {
						msg="解绑成功，即将返回主页面";
					} else {
						msg="解绑失败，请与管理员联系";
					}
					session.setAttribute("url", url);
					//msg要显示的结果内容
					session.setAttribute("msg", msg);
					//跳转中转页面
					response.sendRedirect(path + "/web/nomsg.jsp");
				}
			}
		}
		}
	}

}
