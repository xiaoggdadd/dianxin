package com.noki.tongjichaxu.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keypoint.PngEncoder;
import com.noki.query.tjhzquery.javabean.Hb_nhl;

public class MapdqServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MapdqServlet() {
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

		String bz = request.getParameter("bz");
	  	if("2".equals(bz)||bz=="2"){
	  		BufferedImage chartImage= new MapUtilO().getChartViewer1(request.getParameter("cityStr"), request.getParameter("dataStr"));
		    response.setContentType("image/png");
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	  	}else{
	  		BufferedImage chartImage= new MapUtilO().getChartViewer(request.getParameter("cityStr"), request.getParameter("dataStr"));
		    response.setContentType("image/png");
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	  	}
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

		doGet(request, response);
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
