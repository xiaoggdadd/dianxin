package com.noki.mobi.up;

import javax.servlet.http.HttpServlet;
import com.noki.util.Path;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.Vector;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DelData
    extends HttpServlet {
  public DelData() {
  }

  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
  public void init() throws ServletException {}

  private String msg;
  private String url;
  private String sendUrl;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    String path = request.getContextPath();
    url = path + "/web/up/uplist.jsp";
    //sendUrl = "/mobilebuss/web/msg.jsp";


    InData indata = new InData();
    indata.delAllAccount();

    response.sendRedirect(url);
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
    doPost(request, response);
  }

}
