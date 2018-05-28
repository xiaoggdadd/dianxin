package com.noki.sysconfig.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.AccountBean;
import com.noki.sysconfig.javabean.SysSetBean;
import com.noki.sysconfig.javabean.SysSetFormBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class SysSetServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    SysSetFormBean formBean= new SysSetFormBean();
    String url = path + "/web/sysconfig/sysSetList.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    SysSetBean bean = new SysSetBean();
    String action = req.getParameter("action");
    String status = "";
 if (action.equals("modify")) {
      String ItemID = req.getParameter("ItemID");  
      //System.out.println("8888888888:"+ItemID+"--"+req.getParameter("ItemValue"));
      url = path + "/web/sysconfig/sysSetList.jsp";
	  formBean = formBean.getSysSetInfo(ItemID);
	  formBean.setItemValue(req.getParameter("ItemValue"));     
      msg = bean.modifySysSet(formBean);
      log.write(msg, formBean.getItemID(), req.getRemoteAddr(), "ÕË»§Î¬»¤");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/sysconfig/sysSetList.jsp");

    }

  }

}
