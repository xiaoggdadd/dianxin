package com.ptac.app.noadvicescb.piechart;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.keypoint.PngEncoder;

public class PieChartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PieChartServlet() {}

	  public void doGet(HttpServletRequest request,HttpServletResponse response) 
	  throws ServletException,IOException {
		  
		  	BufferedImage chartImage= new PieChartImp().getChartViewer(request.getParameter("cityStr"), request.getParameter("dataStr"));
			response.setContentType("image/png");
			PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());

	  }

	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response) throws
	      ServletException, IOException {
	    doGet(request, response);

	  }
	  public void doPut(HttpServletRequest request,
	                    HttpServletResponse response) throws
	      ServletException, IOException {

	  }
	  public void destroy() {}

}
