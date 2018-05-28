package com.noki.mobi.sys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.FenzuBean;

public class FenzuServlet extends HttpServlet {
    private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        String url = path + "/web/sys/fenzulist.jsp", msg = "";
        HttpSession session = req.getSession();
        account = (Account) session.getAttribute("account");

        FenzuBean bean = new FenzuBean();
        String action = req.getParameter("action");
        if (action.equals("add")) {
            String name = req.getParameter("name");
            String memo = req.getParameter("memo") != null ?
                          req.getParameter("memo") :
                          "";
            String orderid = req.getParameter("orderid");
            msg = bean.addRole(name, memo, orderid);
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "����·���");
            PrintWriter out = resp.getWriter();
            String ss = "'" + path + "/web/sys/fenzulist.jsp'";
            out.print("<script>opener.location=" + ss +
                      ";window.close();</script>");

            //session.setAttribute("url", url);
            //session.setAttribute("msg", msg);
            //resp.sendRedirect(path+"/web/msg.jsp");

        } else if (action.equals("add_qx")) {
            String name = req.getParameter("name");
            String biaoshi = req.getParameter("biaoshi");
            String memo = req.getParameter("memo") != null ?
                          req.getParameter("memo") :
                          "";
            String orderid = req.getParameter("orderid");
            msg = bean.addBiaoshi(name, biaoshi, memo, orderid);
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "����±�ʶ");
            PrintWriter out = resp.getWriter();
            String ss = "'" + path + "/web/sys/quanxianbiaoshi.jsp'";
            out.print("<script>opener.location=" + ss +
                      ";window.close();</script>");

            //session.setAttribute("url", url);
            //session.setAttribute("msg", msg);
            //resp.sendRedirect(path+"/web/msg.jsp");

        }else if (action.equals("del_bs")) {
            url = path + "/web/sys/quanxianbiaoshi.jsp";
            String[] roleId = req.getParameterValues("roleIdItem");
            msg = "ɾ��Ȩ�ޱ�ʶʧ��!";
            int k = bean.delBiaoshi(roleId);
            if(k==1){
                 msg = "ɾ��Ȩ�ޱ�ʶ�ɹ�!";
            }else{

            }
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "ɾ��Ȩ�ޱ�ʶ");


            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path+"/web/msg.jsp");

        }else if (action.equals("modify")) {
            String name = req.getParameter("name");
            String memo = req.getParameter("memo") != null ?
                          req.getParameter("memo") :
                          "";
            String id = req.getParameter("id");
            String orderid = req.getParameter("orderid");
            msg = bean.modifyRole(id,name, memo, orderid);
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "�޸ķ���");
            PrintWriter out = resp.getWriter();
            String ss = "'" + path + "/web/sys/fenzulist.jsp'";
            out.print("<script>opener.location=" + ss +
                      ";window.close();</script>");

            //session.setAttribute("url", url);
            //session.setAttribute("msg", msg);
            //resp.sendRedirect(path+"/web/msg.jsp");

        }else if (action.equals("del")) {

            String[] ids = req.getParameterValues("roleIdItem");
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "ɾ������");

            msg = "ɾ������ʧ��!";
            int k = bean.delFenzu(ids);
            if(k==1){
                 msg = "ɾ������ɹ�!";
            }else if(k==2){
                msg = "Ҫɾ���ķ��������ڱ�ʹ�õģ�������ɾ����";
            }
            log.write(msg, account.getAccountId(), req.getRemoteAddr(), "ɾ������");


            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path+"/web/msg.jsp");


        }



        /*
           else if (action.equals("modify")) {
          String roleId = req.getParameter("roleId");
          String name = req.getParameter("roleName");
         String memo = req.getParameter("memo") != null ? req.getParameter("memo") :
              "";
          msg = bean.modifyRole(roleId, name, memo);
          log.write(msg, account.getAccountId(), req.getRemoteAddr(), "�޸Ľ�ɫ");
          PrintWriter out = resp.getWriter();
          String ss = "'"+path+"/web/sys/roleManage.jsp'";
         out.print("<script>opener.location=" + ss + ";window.close();</script>");

           }
           else if (action.equals("del")) {
          String[] roleId = req.getParameterValues("roleIdItem");
          msg = bean.delRoles(roleId);
          log.write(msg, account.getAccountId(), req.getRemoteAddr(), "ɾ����ɫ");
          session.setAttribute("url", url);
          session.setAttribute("msg", msg);
          resp.sendRedirect(path+"/web/msg.jsp");

           }
         */

    }
}
