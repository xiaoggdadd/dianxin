package com.noki.mobi.common;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AccountLogoutServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=GBK";

    //Initialize global variables
    public void init() throws ServletException {
    }

    //Process the HTTP Post request
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("window.close();");
        out.println("</script>");
        out.flush();
        out.close();

        //session.invalidate();
        response.sendRedirect(response.encodeURL(path + "/index.jsp"));

    }

    //Process the HTTP Post request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        doPost(request, response);
    }

    //Clean up resources
    public void destroy() {
    }
}
