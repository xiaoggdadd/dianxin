/**
 *
 */
package com.noki.mobi.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.JiZhanBean;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 *
 */
public class LBServlet extends HttpServlet {

    /**
     *
     */
    public LBServlet() {
        // TODO Auto-generated constructor stub
    }

    private static final String Content_type = "text/html;charset=GB2312";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        //String path = req.getContextPath();
    }

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException,
            IOException {
        //String msg = "操作失败！请重试或与管理员联系";

        System.out.println("jilian do get");
        resp.setContentType("text/xml; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        HttpSession session = req.getSession();
        String account = (String)session.getAttribute("loginName");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("sheng")) {
            String pid = req.getParameter("pid");
            JiZhanBean bean = new JiZhanBean();
            String list = bean.getChildrenArea_shi(pid);
            System.out.println(list);
            out.print(list);
            out.close();
        } else if (action.equals("shi")) {
            String pid = req.getParameter("pid");

            JiZhanBean bean = new JiZhanBean();
            String list = bean.getChildrenArea_xian(pid,account);
            out.print(list);
            out.close();
        } else if (action.equals("xian")) {
            String pid = req.getParameter("pid");
            JiZhanBean bean = new JiZhanBean();
            String list = bean.getChildrenArea_xiaoqu(pid,account);
            out.print(list);
            out.close();
        } else if (action.equals("getjz")) {
            String pid = req.getParameter("pid");
            JiZhanBean bean = new JiZhanBean();
            String list = bean.getJizhan(pid);
            out.print(list);
            out.close();
        } else if (action.equals("shi_p")) {
            JiZhanBean bean = new JiZhanBean();
            String list = bean.getShi();
            out.print(list);
            out.close();
        } else if (action.equals("fenzu")) {
            String pid = req.getParameter("fid");
            JiLianBean bean = new JiLianBean();
            String list = bean.getFenzu_Role(pid);
            out.print(list);
            out.close();
        } else if (action.equals("menudetail")) {
            System.out.println(">>>>>>..menudetail");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setHeader("Cache-Control", "no-cache");
            String pid = req.getParameter("pid");
            String roleid = req.getParameter("roleid");
            JiLianBean bean = new JiLianBean();
            String list = bean.getMenu_permission(pid, roleid);
            out.print(list);
            out.close();
        } else if (action.equals("changeRP")) {
            System.out.println(">>>>>>..menudetail");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setHeader("Cache-Control", "no-cache");
            String permission = req.getParameter("permission");
            String roleid = req.getParameter("roleid");
            String rightid = req.getParameter("rightid");
            String ad = req.getParameter("ad");
            JiLianBean bean = new JiLianBean();
            String list = bean.changeRP(permission, roleid, rightid, ad);
            out.print(list);
            out.close();
        }else if(action.equals("shitoxian")){// add new account
            String pid = req.getParameter("pid");// shi agcode
            String ssagcode = req.getParameter("ssagcode");
            ZtCommon ztcommon = new ZtCommon();
            String list = ztcommon.getAgcode_(ssagcode,pid,pid.length()+2);
            out.print(list);
            out.close();
        }else if(action.equals("xiantoxiaoqu")){// add new account
            String pid = req.getParameter("pid");// shi agcode
            String ssagcode = req.getParameter("ssagcode");
            ZtCommon ztcommon = new ZtCommon();
            String list = ztcommon.getAgcode_(ssagcode,pid,pid.length()+2);
            out.print(list);
            out.close();
        }


    }

}
