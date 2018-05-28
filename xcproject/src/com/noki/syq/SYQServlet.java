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
                pageTitle = "��ˮ";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "����";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "����";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "��Ȼ��";
                url = path + "/web/syq/tianranqiinh.jsp";
            }
            System.out.println(url);
            msg = "���" + pageTitle + "�ܺ�ʧ�ܣ�";

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
                msg = "���" + pageTitle + "�ܺĳɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "�ܺ�");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("del_ys")) {
            String pagelx = req.getParameter("pagelx");
            String pageTitle = "";
            if (pagelx.equals("02")) {
                pageTitle = "��ˮ";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "����";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "����";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "��Ȼ��";
                url = path + "/web/syq/tianranqinh.jsp";
            }

            msg = "ɾ����ˮ�ܺ�ʧ�ܣ�";

            String[] ids = req.getParameterValues("itemSelected");

            int retsign = syq.delData(ids);
            if (retsign == 1) {
                msg = "ɾ��" + pageTitle + "�ܺĳɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "�ܺ�");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("mys")) {
            String pagelx = req.getParameter("pagelx");
            String pageTitle = "";
            if (pagelx.equals("02")) {
                pageTitle = "��ˮ";
                url = path + "/web/syq/yongshuinh.jsp";
            } else if (pagelx.equals("03")) {
                pageTitle = "����";
                url = path + "/web/syq/chaiyounh.jsp";
            } else if (pagelx.equals("04")) {
                pageTitle = "����";
                url = path + "/web/syq/qiyounh.jsp";
            } else if (pagelx.equals("05")) {
                pageTitle = "��Ȼ��";
                url = path + "/web/syq/tianranqinh.jsp";
            }

            msg = "�޸�" + pageTitle + "�ܺ�ʧ�ܣ�";

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
                msg = "�޸�" + pageTitle + "�ܺĳɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), pageTitle + "�ܺ�");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("shenhe")) {

            msg = "���ˮ�����ܺ�ʧ�ܣ�";
            url = path + "/web/syq/shenhe.jsp";
            String[] ids = req.getParameterValues("itemSelected");
            String shsign = req.getParameter("shsign");
            int retsign = syq.SHData(ids,shsign);
            if (retsign == 1) {
                msg = "���ˮ�����ܺĳɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���ˮ�����ܺ�");
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

            msg="�޸ı��Ϊ��"+bh+" ����Դ�۱�úϵ��ʧ�ܣ�";
            int retsn = syq.modifyZbxs(bh,newdata);
            if(retsn==1){
                msg="�޸ı��Ϊ��"+bh+" ����Դ�۱�úϵ���ɹ���ԭϵ��Ϊ��"+olddata+"����ϵ��Ϊ��"+newdata;
            }
            log.write(msg, accountid, req.getRemoteAddr(), "��Դ�۱�úϵ��");
            out.println(msg);
            out.close();

        }
    }
}
