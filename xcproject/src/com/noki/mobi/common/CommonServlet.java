package com.noki.mobi.common;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import com.noki.log.DbLog;
import javax.servlet.http.HttpSession;

public class CommonServlet extends HttpServlet {
    public CommonServlet() {
    }

    private static final String Content_type = "text/html;charset=UTF-8";
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException,
            IOException {
        String msg = "操作失败！请重试或与管理员联系";
        //doPost(req, resp);

        resp.setContentType("text/xml; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        CommonAjaxBean bean = new CommonAjaxBean();
        String action = req.getParameter("action");
        DbLog log = new DbLog();
        HttpSession session = req.getSession();
       // String username = (String) session.getAttribute("name");
        String superno = (String) session.getAttribute("loginName");
        String ip = req.getRemoteAddr();
        //String aclassid = (String) session.getAttribute("adminclassid");

        if (action.equals("sheng")) {
            String pid = req.getParameter("pid");
            String list = bean.getChildrenArea_shi(pid);
            System.out.println(list);
            out.print(list);
            out.close();
        } else if (action.equals("shi")) {
            String pid = req.getParameter("pid");
            String list = bean.getChildrenArea_xian(pid);
            out.print(list);
            out.close();
        }

        //out.println("<response>");

        out.println(msg);
        //out.println("</response>");


        out.close();

    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {

    }


}
