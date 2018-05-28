package com.noki.query.caijipoint.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.keypoint.PngEncoder;

public class GuanLiDBServlet_Hdl extends HttpServlet {

	public GuanLiDBServlet_Hdl(){
		
	}
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response) throws ServletException,
	      IOException {
	    // get the chart from session
	    HttpSession session = request.getSession();
	    BufferedImage chartImage = (BufferedImage) session.getAttribute(
	        "gldb_hdl");
	    // set the content type so the browser can see this as a picture
	    response.setContentType("image/png");
	    // send the picture
	    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
	    response.getOutputStream().write(encoder.pngEncode());

	  }

	  //Process the HTTP Post request
	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response) throws
	      ServletException, IOException {
	    doGet(request, response);

	  }

	  //Process the HTTP Put request
	  public void doPut(HttpServletRequest request,
	                    HttpServletResponse response) throws
	      ServletException, IOException {

	  }

	  //Clean up resources
	  public void destroy() {}

	}
