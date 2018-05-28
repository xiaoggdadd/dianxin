package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import javax.servlet.http.HttpSession;
import com.noki.mobi.sys.javabean.Person_DWBean;

public class PersonDWServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=GB2312";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path + "/web/msg.jsp", msg = "给人员分配部门失败！"; ;
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    Person_DWBean bean = new Person_DWBean();

    String[] bmdm = req.getParameterValues("itemSelected");
    String accountId = req.getParameter("account");
    if (bean.fenpeiAccountBM(bmdm, accountId) > 0) {
      msg = "给人员分配部门成功！";
    }
    log.write(msg, account.getAccountId(), req.getRemoteAddr(), "人员部门关系");

    resp.sendRedirect(path + "/web/sys/person_dw.jsp?account=" + accountId +
                      "&bm=" + bmdm + "");

  }

}
