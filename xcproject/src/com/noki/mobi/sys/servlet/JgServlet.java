package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.noki.mobi.common.Account;
import com.noki.jizhan.JiZhanBean;
import com.noki.mobi.sys.javabean.JgBean;
import com.noki.mobi.schedule.DianLiangSchedule;
import com.noki.mobi.schedule.Handel;
import javax.servlet.ServletContextEvent;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JgServlet extends HttpServlet {
    private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();

        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        String url = path + "/web/sys/accountList.jsp", msg = "";
        HttpSession session = req.getSession();
        //account = (Account) session.getAttribute("account");
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        String action = req.getParameter("action");
        if (action.equals("jgday")) {
            url = path + "/web/sys/jgday.jsp";
            String jgday = req.getParameter("jgday");
            msg = "修改解析电表数据间隔时间失败！请重试或与管理员联系！";
            JgBean bean = new JgBean();
            String retsign = bean.modifyDanJia(jgday);
            /*
                         if (retsign == 1) {
                msg = "修改站点单价成功." + ndianfei + ":" + id + "<";
                         }
             */
            log.write(retsign, accountid, req.getRemoteAddr(), "解析电表参数配置");
            session.setAttribute("url", url);
            session.setAttribute("msg", retsign);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("tab")) {
            url = path + "/web/sys/jgday.jsp";
            String tabname = req.getParameter("tabname");
            String dbid = req.getParameter("dbid");
            String ldata = req.getParameter("ldata");
            String bdata = req.getParameter("bdata");
            String ltime = req.getParameter("ltime");
            String btime = req.getParameter("btime");
            String sjdata = req.getParameter("sjdata");
            String zdcb = req.getParameter("zdcb");
            msg = "修改电量表参数配置失败！请重试或与管理员联系！";
            JgBean bean = new JgBean();
            String retsign = bean.addtab(tabname, dbid, ldata, bdata, ltime,
                                         btime, sjdata, zdcb);
            /*
                         if (retsign == 1) {
                msg = "修改站点单价成功." + ndianfei + ":" + id + "<";
                         }
             */
            log.write(retsign, accountid, req.getRemoteAddr(), "解析电表参数配置");
            session.setAttribute("url", url);
            session.setAttribute("msg", retsign);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("db")) {
            url = path + "/web/sys/jgday.jsp";
            String dbip = req.getParameter("dbip");
            String dbname = req.getParameter("dbname");
            String dbuser = req.getParameter("dbuser");
            String dbpass = req.getParameter("dbpass");
            msg = "修改数据库配置失败！请重试或与管理员联系！";
            JgBean bean = new JgBean();
            String retsign = bean.adddb(dbip, dbname, dbuser, dbpass);
            /*
                         if (retsign == 1) {
                msg = "修改站点单价成功." + ndianfei + ":" + id + "<";
                         }
             */
            log.write(retsign, accountid, req.getRemoteAddr(), "解析电表参数配置");
            session.setAttribute("url", url);
            session.setAttribute("msg", retsign);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if(action.equals("handel")){
            url = path + "/web/sys/handeljx.jsp";
            DianLiangSchedule dl = new DianLiangSchedule();
            String btime = req.getParameter("btime");
            String etime = req.getParameter("etime");
            Handel handel = new Handel(btime,etime);
            String retsign=handel.beginHandel();
            log.write(retsign, accountid, req.getRemoteAddr(), "解析电表参数配置");
            session.setAttribute("url", url);
            session.setAttribute("msg", retsign);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
    }
}
