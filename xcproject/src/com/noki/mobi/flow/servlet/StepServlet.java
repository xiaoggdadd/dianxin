package com.noki.mobi.flow.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.flow.javabean.StepBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class StepServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sdttWeb/Flow/stepList.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    StepBean bean = new StepBean();
    String action = req.getParameter("action");
    if (action.equals("add")) {
      String name = req.getParameter("actionName");
      String actionDesc = req.getParameter("actionDesc") != null ? req.getParameter("actionDesc") :
          "";
      msg = bean.addAction(name, actionDesc);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "添加新步骤");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/Flow/stepList.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

      //session.setAttribute("url", url);
      //session.setAttribute("msg", msg);
      //resp.sendRedirect("/mobilebuss/web/msg.jsp");

    }
    else if (action.equals("modify")) {
      String actionId = req.getParameter("actionId");
      String name = req.getParameter("actionName");
      String actionDesc = req.getParameter("actionDesc") != null ? req.getParameter("actionDesc") :
          "";
      msg = bean.modifyAction(actionId, name, actionDesc);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "修改步骤");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/Flow/stepList.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

    }
    else if (action.equals("del")) {
      String[] actionId = req.getParameterValues("actionIdItem");
      msg = bean.delActions(actionId);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "删除步骤");
//      session.setAttribute("url", url);
//      session.setAttribute("msg", msg);
//      resp.sendRedirect(path+"/web/msg.jsp");

    }
    else if (action.equals("stepRole")) {
        String[] roleId = req.getParameterValues("roleItem");
        String actionId = req.getParameter("step");
        String rws = req.getParameter("rws");
        msg = bean.matchActionRole(actionId, roleId,rws);
        url = path+"/web/sdttWeb/Flow/roleManage.jsp";
        log.write(msg, account.getAccountId(), req.getRemoteAddr(), "分配角色");

      }
	session.setAttribute("url", url);
	session.setAttribute("msg", msg);
	resp.sendRedirect(path+"/web/msg.jsp");
  }

}
