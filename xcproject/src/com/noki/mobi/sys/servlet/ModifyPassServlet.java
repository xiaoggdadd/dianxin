package com.noki.mobi.sys.servlet;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.ModifyPassBean;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ModifyPassServlet extends HttpServlet {
  private static final String Content_type = "text/html;charset=GB2312";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sdttWeb/sys/modifyPass1.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    ModifyPassBean bean = new ModifyPassBean();
    String oldPass = req.getParameter("oldPass");
    String newPass = req.getParameter("newPass");
    msg = bean.modifyPass(oldPass, newPass, account.getAccountId());

    log.write(msg, account.getName(), req.getRemoteAddr(),"ÐÞ¸ÄÃÜÂë");
    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path+"/web/msg.jsp");
  }

}
