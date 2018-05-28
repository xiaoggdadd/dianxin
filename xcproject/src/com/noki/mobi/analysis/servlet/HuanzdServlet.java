package com.noki.mobi.analysis.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.awt.image.BufferedImage;
import com.keypoint.PngEncoder;
import com.noki.query.tjhzquery.javabean.Hb_nhl;

public class HuanzdServlet
    extends HttpServlet {
  public HuanzdServlet() {
  }

  @Override
public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
	  	String bz = request.getParameter("bz");
	  	if("3".equals(bz)||bz=="3"){
	  		BufferedImage chartImage= new Hb_nhl().getChartViewer3(request.getParameter("cityStr"), request.getParameter("dataStr"));
		    response.setContentType("image/png");
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	  	}else{
	  		BufferedImage chartImage= new Hb_nhl().getChartViewer(request.getParameter("cityStr"), request.getParameter("dataStr"));
		    response.setContentType("image/png");
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	  	}

  }

  //Process the HTTP Post request
  @Override
public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);

  }

  //Process the HTTP Put request
  @Override
public void doPut(HttpServletRequest request,
                    HttpServletResponse response) throws
      ServletException, IOException {

  }

  //Clean up resources
  @Override
public void destroy() {}

}
