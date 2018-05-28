package com.ptac.app.autocalibration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.database.DataBase;

public class calibrationServerlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public calibrationServerlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String shi = request.getParameter("name");
		DataBase db = new DataBase();
		Connection conn = null;
		CallableStatement statement = null;
		ResultSet rs = null;
		try {
			db.connectDb();
			conn = db.getConnection();
			if ("auto01".equals(action)) {
				String sql = "{call p_dingbiaobasics}";
				statement = conn.prepareCall(sql);
				statement.executeUpdate();
			}
			if ("auto02".equals(action)) {
				String sql = "{call p_BASICSCALIBRATION(?)}";
				statement = conn.prepareCall(sql);
				statement.setString(1, shi);
				statement.executeUpdate();
			}
			if("auto03".equals(action)){
				String sql = "{call p_jyscbTEST(?)}";
				statement = conn.prepareCall(sql);
				statement.setString(1, shi);
				statement.executeUpdate();
			}
			if("auto04".equals(action)){
				String sql = "{call p_dbtempchange01(?)}";
				statement = conn.prepareCall(sql);
				statement.setString(1, shi);
				statement.executeUpdate();
				
				
				String sql1 = "{call p_dbtempchange03(?)}";
				statement = conn.prepareCall(sql1);
				statement.setString(1, shi);
				statement.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.free(rs, statement, conn);
		}

		System.out.println(action);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
