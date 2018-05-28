package com.noki.mobi.cx;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import com.noki.mobi.common.Account;
import com.noki.log.DbLog;
import javax.servlet.http.HttpSession;
import java.util.Vector;
import com.noki.util.WriteExcle;
import com.noki.util.Path;

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
public class CxDaoChuServlet extends HttpServlet {
    public CxDaoChuServlet() {
    }

    private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        String url = path + "/web/sys/accountList.jsp", msg = "";
        HttpSession session = req.getSession();
        account = (Account) session.getAttribute("account");
        String ip = req.getRemoteAddr();
        String username = (String) session.getAttribute("name");
        url = path + "/web/msgdrcx.jsp";
        String aclassid = (String) session.getAttribute("adminclassid");
        String action = req.getParameter("action");
        if (action.equals("zynh")) { //专业类型能耗统计导出
            msg = "专业类型能耗统计导出失败！";
            String beginTime = req.getParameter("beginTime");
            String endTime = req.getParameter("endTime");

            String sheng = req.getParameter("sheng") != null ?
                           req.getParameter("sheng") : "0";
            String shi = req.getParameter("shi") != null ?
                         req.getParameter("shi") : "0";
            String xian = req.getParameter("xian") != null ?
                          req.getParameter("xian") : "0";
            String xiaoqu = req.getParameter("xiaoqu") != null ?
                            req.getParameter("xiaoqu") : "0";
            ZhuanYeNengHao cbean = new ZhuanYeNengHao();
            Path ppath = new Path();
            ppath.servletInitialize(getServletConfig().getServletContext());
            Vector content = cbean.getPageData_dc(beginTime, endTime,
                                                  shi, xian, xiaoqu);
            WriteExcle wr = new WriteExcle();
            System.out.println("专业类型能耗统计导出");
            String dir2 = ppath.getPhysicalPath("/output/", 0); // 传参数
            wr.Write(content, "专业类型能耗统计" + ".xls", "专业类型能耗统计",
                     "专业类型能耗统计", 5, dir2);
            msg = "专业类型能耗统计导出成功！";
            log.write(msg, account.getAccountName(), ip, "账户维护");
            session.setAttribute("url", path + "/web/cx/zhuanyenenghao.jsp");
            session.setAttribute("msg", msg);
            session.setAttribute("wfile", path + "/output/专业类型能耗统计.xls");

        }
        resp.sendRedirect(url);
    }

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException,
            IOException {

    }

}
