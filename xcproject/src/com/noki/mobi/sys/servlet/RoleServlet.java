package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.RoleBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class RoleServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sdttWeb/sys/roleManager.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    RoleBean bean = new RoleBean();
    String action = req.getParameter("action");
    if (action.equals("add")) {
      String name = req.getParameter("roleName");
      String memo = req.getParameter("memo") != null ? req.getParameter("memo") :
          "";
        String fenzu = req.getParameter("fenzu");
      msg = bean.addRole(name, memo,fenzu);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "添加新角色");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/sys/roleManager.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

      //session.setAttribute("url", url);
      //session.setAttribute("msg", msg);
      //resp.sendRedirect("/mobilebuss/web/msg.jsp");

    }
    else if (action.equals("modify")) {
      String roleId = req.getParameter("roleId");
      String name = req.getParameter("roleName");
      String fenzu = req.getParameter("fenzu");
      String memo = req.getParameter("memo") != null ? req.getParameter("memo") :
          "";
      msg = bean.modifyRole(roleId, name, memo,fenzu);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "修改角色");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/sys/roleManager.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

    }
    else if (action.equals("del")) {
      String[] roleId = req.getParameterValues("roleIdItem");
      msg = bean.delRoles(roleId);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "删除角色");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path+"/web/msg.jsp");

    }

  }

}
