package com.noki.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class RoleFilter implements Filter {
    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
            ServletException {
        StringBuffer URL = ((HttpServletRequest) request).getRequestURL();
        String[] urlTemp = (URL.toString()).split("/");
        String strServlet = "/" + urlTemp[urlTemp.length - 2] + "/" +
                            urlTemp[urlTemp.length - 1];
        String roleFlg = ((HttpServletRequest) request).getParameter("roleFlg");
        HttpSession session = ((HttpServletRequest) request).getSession();
        String roleid = (String) session.getAttribute("accountRole");
        String mid = "";
        String chek = "";
        if (strServlet != null && roleid != null) {
            mid = getServletmaping(strServlet, roleid);
        }
        if (!"".equals(mid) && !"".equals(roleid) && !"".equals(roleFlg) &&
            roleFlg != null) {
            chek = getRolePermission(roleid, roleFlg, mid);
        }
        if ("".equals(chek) && !"/servlet/login".equals(strServlet)) {
            System.out.println("haha");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("msg|此操作没有权限");
            out.flush();
            out.close();
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //角色操作画面情况表 角色操作哪几个画面的哪几个权限类型
    public String getRolePermission(String roleid, String roleFlg, String mid) {
        String ret = "";
        StringBuffer sql = new StringBuffer();
        sql.append("select t.roleid ,t.permission ,t.mid   " +
                   "from role_permission t where 1=1 ");
        sql.append(" and roleid='" + roleid + "'");
        sql.append(" and mid='" + mid + "'");
        sql.append(" and permission='" + roleFlg + "'");
        DataBase db = new DataBase();
        ResultSet rs = null;
        if (sql.length() > 0) {
            try {
                db.connectDb();
                rs = db.queryAll(sql.toString());
                if (rs.next()) {
                    ret = "1";
                }
            } catch (Exception de) {
                de.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
                try {
                    db.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }

            }
        }
        return ret;
    }

    //servlet和模块号对应表
    public String getServletmaping(String strServlet, String roleid) {
        String ret = "";
        StringBuffer sql = new StringBuffer();
        sql.append("select t.servletname,t.servleturl,t.rightid ,r.roleid  " +
                   "from servletmaping t,role_right r where t.rightid = r.rightid(+)");
        sql.append(" and t.servleturl ='" + strServlet + "'");
        sql.append(" and r.roleid ='" + roleid + "'");
        DataBase db = new DataBase();
        ResultSet rs = null;
        if (sql.length() > 0) {
            try {
                db.connectDb();
                rs = db.queryAll(sql.toString());
                if (rs.next()) { //
                    ret = rs.getString("rightid");
                }
            } catch (Exception de) {
                de.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
                try {
                    db.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }

            }
        }
        return ret.toString();
    }

}
