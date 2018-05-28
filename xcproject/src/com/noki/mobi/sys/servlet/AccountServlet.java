package com.noki.mobi.sys.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.ResultSet;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.AccountBean;
import com.noki.mobi.sys.javabean.Per_area;
import com.noki.mobi.sys.javabean.Per_zhandian;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AccountServlet extends HttpServlet {
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
        AccountBean bean = new AccountBean();
        String aclassid = (String) session.getAttribute("adminclassid");
        String action = req.getParameter("action");
        if (action.equals("add")) {
            url = path + "/web/sdttWeb/sys/addAccount.jsp";
            String accountName = req.getParameter("accountName");
            String password = req.getParameter("password");
            String[] roleId = req.getParameterValues("role");
            System.out.println("roleid==" + roleId);
            String position = req.getParameter("position");
            String bumen = req.getParameter("bumen");
            String zip = req.getParameter("zip");
            String address = req.getParameter("address");
            String mobile = req.getParameter("mobile");
            String email = req.getParameter("email");
            String sex = req.getParameter("sex");
            String name = req.getParameter("name");
            String sheng = req.getParameter("sheng");
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian") != null ?
                          req.getParameter("xian") : "";
            String xiaoqu = req.getParameter("xiaoqu") != null ?
                            req.getParameter("xiaoqu") : "";
            String tel = req.getParameter("tel");
            String memo = req.getParameter("memo");
            String cthrnumber = req.getParameter("msd");
            msg = bean.addAccount(accountName, name, roleId, position,
                                  zip, address, mobile, email, sex,
                                  password, tel, memo, bumen, sheng, shi, xian,
                                  xiaoqu,cthrnumber);
            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("del")) {
        	
        	url = path + "/web/sdttWeb/sys/accountList.jsp";
        	 
            String[] accountId = req.getParameterValues("accountId");

            msg = bean.delAccounts(accountId);
            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("xiugai")) {
            String sheng = req.getParameter("sheng");
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian") != null ?
                          req.getParameter("xian") : "";
            String xiaoqu = req.getParameter("xiaoqu") != null ?
                            req.getParameter("xiaoqu") : "";
            String accountName = req.getParameter("accountName");
            String position = req.getParameter("position");
            String bumen = req.getParameter("bumen");
            String zip = req.getParameter("zip");

            String accountId = req.getParameter("accountId");
            //String accountName = req.getParameter("accountName");
            String[] roleId = req.getParameterValues("role");

            String address = req.getParameter("address");
            String mobile = req.getParameter("mobile");
            String email = req.getParameter("email");
            String sex = req.getParameter("sex");
            String name = req.getParameter("name");
            String tel = req.getParameter("tel");

            String memo = req.getParameter("memo");
            
            /*String bumenSql = "select d.deptcode from department d where d.deptname = '"+bumen+"'";
            DataBase db = new DataBase();
            try {
				ResultSet rs = db.queryAll(bumenSql);
				if(rs.next()){
					bumen = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
            
            

            msg = bean.modifyAccount(accountId, accountName,
                                     name, position, bumen, zip, roleId,
                                     address, mobile,
                                     email, sex,
                                     tel, memo, sheng, shi, xian, xiaoqu);
            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("msg", msg);
            //resp.sendRedirect("/mobilebuss/web/sys/sysMsg.jsp");
            ///*
             String sname = req.getParameter("sname");
            String srole = req.getParameter("srole");
            String sorg = req.getParameter("orgId");

            PrintWriter out = resp.getWriter();
            String ss = "'" + path + "/web/sdttWeb/sys/accountList.jsp?roleId=" + srole +
                        "&sorg=" + sorg + "&sname=" + sname + "'";
            out.print("<script>opener.location=" + ss +
                      ";window.close();</script>");
            //*/
        } else if (action.equals("resetPass")) {
        	url = path + "/web/sdttWeb/sys/accountList.jsp";
            String[] accountId = req.getParameterValues("itemSelected");
            String oper = account.getName();
            msg = bean.resetPass(accountId, oper);

            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("onOrOff")) {
            String[] accountId = req.getParameterValues("itemSelected");
            String sign = req.getParameter("sign");
            msg = bean.accountOnOrOff(accountId, sign);

            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("pzhandian")) { //人员负责的区域

            Per_zhandian pbean = new Per_zhandian();

            String[] begincode = req.getParameterValues("begincode");
            String[] endcode = req.getParameterValues("endcode");
            String accountid = req.getParameter("accountid");
            url = path+"/web/sys/per_zhandian.jsp?accountid=" + accountid;
            try {
                msg = pbean.AddPer_Zhandian(accountid, begincode, endcode);
                if (msg.equals("no")) {
                    msg = "保存人员负责站点数据失败！";
                } else {
                    msg = "保存人员负责站点数据成功！";
                }
                // }
            } catch (Exception e) {
                e.printStackTrace();

            }
            log.write(msg, account.getAccountName(), ip, username, "人员负责站点",
                      aclassid);
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("delfz")) { //人员负责的区域
            msg = "删除人员负责站点数据失败！";
            Per_zhandian pbean = new Per_zhandian();
            String accountid = req.getParameter("accountid");
            String delid = req.getParameter("id");
            url = path+"/web/sys/per_zhandian.jsp?accountid=" + accountid;
            try {
                int ret = pbean.delPer_Zhandian(delid);
                if (ret == 1) {
                    msg = "删除人员负责站点数据成功！";
                }
                // }
            } catch (Exception e) {
                e.printStackTrace();

            }
            log.write(msg, account.getAccountName(), ip, username, "人员负责站点",
                      aclassid);
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("sh")) {
        	
        	
        	url = path + "/web/sdttWeb/ShenpiManager/DanjiaSh.jsp";
        	 
            String accountId = req.getParameter("id");
            String zt=req.getParameter("zt");

            msg = bean.delSh(accountId,zt);
            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/nomsg.jsp");

        }else if (action.equals("shdj")) {
        	
        	
        	url = path + "/web/sdttWeb/ShenpiManager/DanjiaSh_xx.jsp";
        	 
            String accountId = req.getParameter("id");
            String zt=req.getParameter("zt");
            String memo=req.getParameter("memo")!=null?req.getParameter("memo"):"";
            msg = bean.delShdj(accountId,zt,memo);
            log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/nomsg.jsp");

        }else if (action.equals("shdldf")) {
			
			
			url = path + "/web/sdttWeb/ShenpiManager/DifenShujuShenpi_xx.jsp";
			 
		    String accountId = req.getParameter("id");
		    String zt=req.getParameter("zt");
		    String memo=req.getParameter("memo")!=null?req.getParameter("memo"):"";
		    msg = bean.delShdldf(accountId,zt,memo);
		    log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    resp.sendRedirect(path + "/web/nomsg.jsp");
		
		}
        
        else if (action.equals("parea")) { //人员负责的区域
			 Per_area perArea = new Per_area();
			 String agcode = req.getParameter("agcodelist");
	         String[] list=agcode.split(",");
	         String accountid = req.getParameter("accountid");
	         msg = perArea.changePer_area(list, accountid);
	         log.write(msg, account.getAccountName(), ip, username, "人员负责区域", aclassid);
	         session.setAttribute("url", url);
			    session.setAttribute("msg", msg);
			    resp.sendRedirect(path + "/web/nomsg.jsp");
		}
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
        String aclassid = (String) session.getAttribute("adminclassid");

        if (action.equals("parea")) { //人员负责的区域
            Per_area bean = new Per_area();
            System.out.println("------------------------------");
            String agcode = req.getParameter("agcodelist");
            String[] list=agcode.split(",");
            String accountid = req.getParameter("accountid");
           // String ad = req.getParameter("ad");
            System.out.println(agcode);
            try {
                msg = bean.changePer_area(list, accountid);
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.write(msg, superno, ip, username, "人员负责区域", aclassid);
            //out.println("<response>");
            out.println("" + msg + "");
            //out.println("</response>");
            out.close();
        }
        if (action.equals("pareads")) { //人员负责的地市
            Per_area bean = new Per_area();
            String agcode = req.getParameter("agcodelist");
            String[] list=agcode.split(",");
            String accountid = req.getParameter("accountid");
            System.out.println(agcode);
            try {
                msg = bean.changePer_areads(list, accountid);
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.write(msg, superno, ip, username, "人员负责地市", aclassid);
            //out.println("<response>");
            out.println(">>> " + msg + " <<");
            //out.println("</response>");
            out.close();
        }
    }
    
}
