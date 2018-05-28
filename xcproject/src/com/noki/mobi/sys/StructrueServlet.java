package com.noki.mobi.sys;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import com.noki.log.DbLog;
import com.noki.mobi.sys.javabean.StructureForm;
import com.noki.mobi.sys.javabean.StructureBean;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class StructrueServlet extends HttpServlet {
    private static final String Content_type = "text/html;charset=GB2312";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        String spath = req.getContextPath();

        String url = "", msg = "";
        DbLog log = new DbLog();
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("name");
        String superno = (String) session.getAttribute("username");
        String ip = req.getRemoteAddr();
        String aclassid= (String)session.getAttribute("adminclassid");
        // account = (Account) session.getAttribute("account");
        String path = req.getContextPath();
        String action = req.getParameter("action");
        //  QykBean nbean = new QykBean();
        StructureBean bean = new StructureBean();
        if (action.equals("add")) {
            String bm = req.getParameter("bm");
            String[] dwqc = req.getParameterValues("dwqc");
            String[] dwjc = req.getParameterValues("danwjc");
            String[] des = req.getParameterValues("des");
            String parentid = req.getParameter("parentId");
            String ss="";

            for (int i = 0; i < dwqc.length; i++) {
                ss+=dwqc[i]+";";
            }
            if (bean.addStructure(parentid, dwqc, dwjc, des) == 1) {
                msg = "保存组织机构节点 " + ss + " 成功";
            } else {
                msg = "保存组织机构节点 " + ss + " 失败";
            }
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            url = spath + "/web/sys/structure.jsp?bm=" + bm;
        } else if (action.equals("modify")) {
            String bm = req.getParameter("bm");
            String dwqc = req.getParameter("dwqc");
            System.out.println(dwqc);
            resp.setContentType("text/html; charset=UTF-8");
            resp.setHeader("Cache-Control", "no-cache");
            PrintWriter out = resp.getWriter();
            out.println(">>> " + dwqc + " <<");
            //out.println("</response>");
            out.close();
            url = spath + "/web/sys/structure.jsp?bm=" + bm;
            //msg = nbean.modifyZxzj(id, djrq, qyname, qymemo,dwdm);
        } else if (action.equals("del")) {
            String bm = req.getParameter("bm");
            String id = req.getParameter("deleteId");
            int sign = bean.delStructure(id);
            if (sign == 1) {
                msg = "删除部门成功！";
            } else if (sign == 2) {
                msg = "要删除的部门存在子部门，不允许删除";
            } else if (sign == 3) {
                msg = "要删除的部门下有员工，不允许删除";
            }
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            url = spath + "/web/sys/structure.jsp?bm=" + bm;
            // msg = nbean.delZxzj(id);
        } else if (action.equals("up")) {
            String bm = req.getParameter("bm");
            String id = req.getParameter("deleteId");
            if (bean.upStructure(id) == 1) {
                msg = "上移节点成功！";
            } else {
                msg = "上移节点失败！";
            }
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            url = spath + "/web/sys/structure.jsp?bm=" + bm;

        } else if (action.equals("down")) {
            String bm = req.getParameter("bm");
            String id = req.getParameter("deleteId");
            if (bean.downStructure(id) == 1) {
                msg = "下移节点成功！";
            } else {
                msg = "下移节点失败！";
            }
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            url = spath + "/web/sys/structure.jsp?bm=" + bm;

        } else if (action.equals("jiansuo")) {
            String bm = req.getParameter("bm");
            session.setAttribute("orgemptree", null);
            resp.sendRedirect(spath + "/web/sys/structure.jsp?bm=" + bm);
            return;
        } else if (action.equals("csbm_add")) {

            String[] neworgname = req.getParameterValues("neworgname");

            String parentid = req.getParameter("parentId");

            if (bean.addStructure_csbm(parentid, neworgname) == 1) {
                msg = "保存抄送部门组织机构节点 成功";
            } else {
                msg = "保存抄送部门组织机构节点 失败";
            }
            url = "/web/sys/admin_structure_csbm.jsp";
            session.setAttribute("orgemptree_csbm", null);
            session.setAttribute("msg", msg);
            session.setAttribute("url", url);
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            resp.sendRedirect(path + "/web/common/msg.jsp");
            return;

        }else if (action.equals("csbm_del")) {


            String parentid = req.getParameter("deleteId");
            int k = bean.delStructure_csbm(parentid);
            if (k == 1) {
                msg = "删除抄送部门组织机构节点 成功";
            } else if(k==2){
                msg = "要删除的抄送部门存在子节点，不允许删除";
            }else{
                msg = "删除少送部门节点失败，请重试或与管理员联系";
            }
            url =  "/web/sys/admin_structure_csbm.jsp";
            session.setAttribute("orgemptree_csbm", null);
            session.setAttribute("msg", msg);
            session.setAttribute("url", url);
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            resp.sendRedirect(path + "/web/common/msg.jsp");
            return;

        }



        //log.write(msg, account.getAccountId(), req.getRemoteAddr(), "企业库建设");
        session.setAttribute("orgemptree", null);
        session.setAttribute("msg", msg);
        session.setAttribute("orgmsg", msg);
        session.setAttribute("org_sign", "ok");
        resp.sendRedirect(url);
    }

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException,
            IOException {

        String msg = "修改部门设置失败！";
        //doPost(req, resp);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        DbLog log = new DbLog();
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("name");
        String superno = (String) session.getAttribute("username");
        String ip = req.getRemoteAddr();
        String aclassid= (String)session.getAttribute("adminclassid");

        if (action.equals("modifyorg")) {
            StructureForm form = new StructureForm();
            StructureBean bean = new StructureBean();

            String dwqc = req.getParameter("dwqc");
            dwqc = new String(dwqc.getBytes("iso-8859-1"), "GBK");
            form.setDwqc(dwqc);

            String dwjc = new String(req.getParameter("dwjc").getBytes(
                    "iso-8859-1"),
                                     "GBK");
            form.setDwjc(dwjc);
            String fzr = req.getParameter("fzr");

            if (fzr == null) {
                fzr = "";
            }
            form.setFzr(new String(fzr.getBytes("iso-8859-1"),
                                   "GBK"));


            form.setSuperno(req.getParameter("superno"));
            form.setTel(req.getParameter("tel"));
            form.setFax(req.getParameter("fax"));
            form.setEmail(req.getParameter("email"));
            form.setAddress(new String(req.getParameter("address").getBytes(
                    "iso-8859-1"), "GBK"));
            form.setZipcode(req.getParameter("zipcode"));

            form.setJgsz(new String(req.getParameter("jgsz").getBytes(
                    "iso-8859-1"),
                                    "GBK"));
            form.setDepinfo(new String(req.getParameter("depinfo").getBytes(
                    "iso-8859-1"), "GBK"));

            form.setClassid(req.getParameter("classid"));
            //temp.setMemo(memo);
            //temp.setCreator(operator.get);

            try {
                /*
                 if (bean.validateModifyStructName(classid, dwjc)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("orgemptree", null);
                    msg = "存在重名的";
                                 }else{
                 */
                if (bean.modifyStructure(form,"structure") == 1) {
                    msg = "修改部门设置成功！";

                    session.setAttribute("orgemptree", null);
                } else {
                    msg = "修改部门设置失败！";
                }
                // }
            } catch (Exception e) {
                e.printStackTrace();

            }
            log.write(msg,superno,ip,username,"机构管理",aclassid);
            //out.println("<response>");
            out.println(">>> " + msg + " <<");
            //out.println("</response>");
            out.close();

        }

    }

    }

