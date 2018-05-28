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

public class BiaoganAll extends HttpServlet {

	/**
	 * Constructor of the object.
	 * 
	 * 
	 * 2017年11月8日11:04:00
	 * this   gaochunlei    ke  lue  guo   test
	 */
	public BiaoganAll() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("-----------------自己写的");
		ArrayList<BiaoganBean> list =  new  ArrayList<BiaoganBean>();
 		DataBase dataBase = new DataBase();
		String sql = "select bg.id,zd. ,db., bg.yearmonth, zd.JZNAME,bg.zlfh,bg.ktxs,bg.BIAOGANVALUE,bg.ZQ "+
				" from ZHANDIAN  zd , TBL_TT_BIAOGAN_LISHI bg  ,dianbiao db  "+
				"where  bg.dbid=db.dbid and db.dbid=zd.id;";
		sql.toString();
		try {
		ResultSet rs = dataBase.queryAll(sql);
		try {
			//存入数据
			while (rs.next()) {
				BiaoganBean bgBean = new BiaoganBean();
				bgBean.setID(rs.getString(1));
				bgBean.setJZID(rs.getString(2));
				bgBean.setDBBM(rs.getString(3));
				bgBean.setYEARMONTH(rs.getString(4));
				bgBean.setJZNAME(rs.getString(5));
				bgBean.setZLFH(rs.getString(6));
				bgBean.setKTXS(rs.getString(7));
				bgBean.setBIAOGANVALUE(rs.getString(8));
				bgBean.setZQ(rs.getString(9));
				list.add(bgBean);
			}
			HttpSession session =  request.getSession();
			session.setAttribute("BGlist", list);
			response.sendRedirect("web/sdttWeb/BiaoganManager/biaoganHistory.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
