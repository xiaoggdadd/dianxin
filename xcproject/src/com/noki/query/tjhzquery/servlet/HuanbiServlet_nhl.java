package com.noki.query.tjhzquery.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import com.keypoint.PngEncoder;
import com.noki.query.tjhzquery.javabean.Hb_nhl;

public class HuanbiServlet_nhl extends HttpServlet {
  public HuanbiServlet_nhl() {
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
    BufferedImage chartImage= new Hb_nhl().getChartViewer(request.getParameter("cityStr"), request.getParameter("dataStr"));
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
