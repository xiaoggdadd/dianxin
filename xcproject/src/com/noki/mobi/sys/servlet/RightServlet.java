package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.RightBean;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class RightServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=GB2312";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sdttWeb/sys/roleManage.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    RightBean bean = new RightBean();
    String action = req.getParameter("action");
    if (action.equals("roleRight")) {
      String[] rightId = req.getParameterValues("rightItem");
      String roleId = req.getParameter("role");
      String rws = req.getParameter("rws");
      msg = bean.matchRoleRight(roleId, rightId,rws);
      url = path+"/web/sys/roleManage.jsp";
      log.write(msg, account.getAccountId(), req.getRemoteAddr(), "分配权限");
    }
    else if (action.equals("rwd")) {
      String[] rRight = req.getParameterValues("itemR");
      String[] wRight = req.getParameterValues("itemRW");
      String[] wdRight = req.getParameterValues("itemRWD");
      String[] wsRight = req.getParameterValues("itemWS");
      String roleId = req.getParameter("role");
      msg = bean.matchRWD(roleId, rRight, wRight, wdRight,wsRight);
      url = path+"/web/sdttWeb/sys/roleManage.jsp";
      log.write(msg, account.getAccountId(), req.getRemoteAddr(), "权限读写设置");
    }

    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path+"/web/msg.jsp");
  }

}
