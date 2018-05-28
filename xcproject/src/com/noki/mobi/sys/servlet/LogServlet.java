package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.LogBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class LogServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=GB2312";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sys/logManage.jsp", msg = "删除日志失败！";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    String beginTime = req.getParameter("delBeginTime");
    String endTime = req.getParameter("delEndTime");

    LogBean bean = new LogBean();
    if (del(beginTime)) {

      if (bean.delLogs(beginTime, endTime)) {
        msg = "删除日志成功！时间段为：" + beginTime + "--" + endTime;
      }
    }
    else {
      msg = "最近十天的数据不能删除！";
    }

    log.write(msg, account.getAccountId(), req.getRemoteAddr(), "日志管理");
    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path+"/web/msg.jsp");
  }

  /**
   * 最近十天的数据不允许删除
   * @param beginTime String
   * @return boolean
   */
  private synchronized boolean del(String beginTime) {
    boolean sign = false;
    TimeZone zone = TimeZone.getTimeZone("GMT+8:00");
    Calendar calendar = Calendar.getInstance(zone);
    Date time = new Date();
    calendar.setTime(time);
    calendar.add(calendar.DATE, -10);
    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    try {
      if (calendar.getTime().after(d.parse(beginTime))) {
        sign = true;
      }
    }
    catch (ParseException pe) {
      pe.printStackTrace();
    }
    return sign;
  }

}
