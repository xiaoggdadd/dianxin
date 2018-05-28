package com.noki.syq;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
public class SYQServlet extends HttpServlet {
    public SYQServlet() {
    }

    private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        String path = req.getContextPath();
        DbLog log = new DbLog();
        HttpSession session = req.getSession();
        String url = "", msg = "";
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");

        String action = req.getParameter("action");
        SyqBean syq = new SyqBean();
        if (action.equals("addyongshui")) {
            String nhlx = req.getParameter("nhlx");
            String pagelx = req.getParameter("pagelx");
            String pageTitle = "";
            if (pagelx.equals("02")) {
                pageTitle = "用水";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "柴油";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "汽油";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "天然气";
                url = path + "/web/syq/tianranqiinh.jsp";
            }
            System.out.println(url);
            msg = "添加" + pageTitle + "能耗失败！";

            String yuef = req.getParameter("yuef");
            String bzh = req.getParameter("bzh");
            String xinghao = req.getParameter("xinghao");

            String nhyt = req.getParameter("nhyt");
            String zdid = req.getParameter("zdid");
            String nhsl = req.getParameter("nhsl");
            String tzsl = req.getParameter("tzsl");
            String sjsl = req.getParameter("sjsl");
            String danjia = req.getParameter("danjia");
            String nhje = req.getParameter("nhje");
            String tzje = req.getParameter("tzje");
            String sjje = req.getParameter("sjje");
            String qss = req.getParameter("qss");
            String jss = req.getParameter("jss");
            String bdate = req.getParameter("bdate");
            String edate = req.getParameter("edate");
            String sfr = req.getParameter("sfr");
            String sfdate = req.getParameter("sfdate");
            String pjlx = req.getParameter("pjlx");
            String pjh = req.getParameter("pjh");
            String kpdate = req.getParameter("kpdate");
            String memo = req.getParameter("memo");
            int retsign = syq.addData(yuef, bzh, xinghao, nhlx, nhyt, zdid,
                                      nhsl,
                                      tzsl, sjsl, danjia, nhje, tzje, sjje, qss,
                                      jss, bdate, edate, sfr, sfdate, pjlx, pjh,
                                      kpdate, memo, accountid);
            if (retsign == 1) {
                msg = "添加" + pageTitle + "能耗成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "能耗");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("del_ys")) {
            String pagelx = req.getParameter("pagelx");
            String pageTitle = "";
            if (pagelx.equals("02")) {
                pageTitle = "用水";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "柴油";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "汽油";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "天然气";
                url = path + "/web/syq/tianranqinh.jsp";
            }

            msg = "删除用水能耗失败！";

            String[] ids = req.getParameterValues("itemSelected");

            int retsign = syq.delData(ids);
            if (retsign == 1) {
                msg = "删除" + pageTitle + "能耗成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "能耗");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("mys")) {
            String pagelx = req.getParameter("pagelx");
            String pageTitle = "";
            if (pagelx.equals("02")) {
                pageTitle = "用水";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "柴油";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "汽油";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "天然气";
                url = path + "/web/syq/tianranqinh.jsp";
            }

            msg = "修改" + pageTitle + "能耗失败！";

            String id = req.getParameter("id");
            String yuef = req.getParameter("yuef");
            String bzh = req.getParameter("bzh");
            String xinghao = req.getParameter("xinghao");
            String nhlx = req.getParameter("nhlx");
            String nhyt = req.getParameter("nhyt");
            String zdid = req.getParameter("zdid");
            String nhsl = req.getParameter("nhsl");
            String tzsl = req.getParameter("tzsl");
            String sjsl = req.getParameter("sjsl");
            String danjia = req.getParameter("danjia");
            String nhje = req.getParameter("nhje");
            String tzje = req.getParameter("tzje");
            String sjje = req.getParameter("sjje");
            String qss = req.getParameter("qss");
            String jss = req.getParameter("jss");
            String bdate = req.getParameter("bdate");
            String edate = req.getParameter("edate");
            String sfr = req.getParameter("sfr");
            String sfdate = req.getParameter("sfdate");
            String pjlx = req.getParameter("pjlx");
            String pjh = req.getParameter("pjh");
            String kpdate = req.getParameter("kpdate");
            String memo = req.getParameter("memo");
            int retsign = syq.modifyData_ys(yuef, bzh, xinghao, nhlx, nhyt,
                                            zdid, nhsl,
                                            tzsl, sjsl, danjia, nhje, tzje,
                                            sjje, qss,
                                            jss, bdate, edate, sfr, sfdate,
                                            pjlx, pjh,
                                            kpdate, memo, id);
            if (retsign == 1) {
                msg = "修改" + pageTitle + "能耗成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "能耗");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("shenhe")) {

            msg = "审核水油气能耗失败！";
            url = path + "/web/syq/shenhe.jsp";
            String[] ids = req.getParameterValues("itemSelected");
            String shsign = req.getParameter("shsign");
            int retsign = syq.SHData(ids,shsign);
            if (retsign == 1) {
                msg = "审核水油气能耗成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "审核水油气能耗");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        String msg = "";
        String path = req.getContextPath();
        DbLog log = new DbLog();
        HttpSession session = req.getSession();
        //account = (Account) session.getAttribute("account");
        String accountid = (String) session.getAttribute("loginName");
        String action = req.getParameter("action");
        SyqBean syq = new SyqBean();
        if (action.equals("xishu")) {
            String olddata = req.getParameter("olddata");
            String newdata = req.getParameter("newdata");
            String bh = req.getParameter("bh");

            msg="修改编号为："+bh+" 的能源折标煤系数失败！";
            int retsn = syq.modifyZbxs(bh,newdata);
            if(retsn==1){
                msg="修改编号为："+bh+" 的能源折标煤系数成功！原系数为："+olddata+"；新系数为："+newdata;
            }
            log.write(msg, accountid, req.getRemoteAddr(), "能源折标煤系数");
            out.println(msg);
            out.close();

        }
    }
}
