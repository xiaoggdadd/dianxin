package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.IndividualBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class IndividualServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=GB2312";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path + "/web/sys/individual.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    //String accountName = req.getParameter("accountName");
    String address = req.getParameter("address");
    String mobile = req.getParameter("mobile");
    String email = req.getParameter("email");
    String sex = req.getParameter("sex");
    String name = req.getParameter("name");
    String tel = req.getParameter("tel");
    String zip = req.getParameter("zip");
    String accountId = account.getAccountId();
    IndividualBean bean = new IndividualBean();
    msg = bean.updateInidividual(name, zip, address, mobile, email, sex,
                                 accountId, tel);

    log.write(msg, account.getAccountId(), req.getRemoteAddr(), "��������");
    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path + "/web/msg.jsp");
  }

}
