package com.noki.mobi.flow.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.flow.javabean.FlowBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FlowServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    String url = path+"/web/sdttWeb/Flow/flowList.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    FlowBean bean = new FlowBean();
    String action = req.getParameter("action");
    if (action.equals("add")) {
      String name = req.getParameter("flowName");
      String flowType = req.getParameter("flowType");
      String flowDesc = req.getParameter("flowDesc") != null ? req.getParameter("flowDesc") : "";
      System.out.println("  ----------------------------"+name+""+flowDesc+""+flowType);
      //msg = bean.addFlow(name,flowType, flowDesc);
      msg = bean.addFlow(name, flowType, flowDesc);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "添加新流程");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/Flow/flowList.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

      //session.setAttribute("url", url);
      //session.setAttribute("msg", msg);
      //resp.sendRedirect("/mobilebuss/web/msg.jsp");

    }
    else if (action.equals("modify")) {
      String flowId = req.getParameter("flowId");
      String name = req.getParameter("flowName");
      String flowtypes = req.getParameter("flowtypes");

      String flowDesc = req.getParameter("flowDesc") != null ? req.getParameter("flowDesc") :
          "";
      msg=bean.modifyFlow(flowId, name, flowtypes, flowDesc);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "修改流程");
      PrintWriter out = resp.getWriter();
      String ss = "'"+path+"/web/sdttWeb/Flow/flowList.jsp'";
      out.print("<script>opener.location=" + ss + ";window.close();</script>");

    }
    else if (action.equals("del")) {
      String[] flowId = req.getParameterValues("flowIdItem");
      msg = bean.delFlows(flowId);
      log.write(msg, account.getAccountName(), req.getRemoteAddr(), "删除流程");
//      session.setAttribute("url", url);
//      session.setAttribute("msg", msg);
//      resp.sendRedirect(path+"/web/msg.jsp");

    }
    else if (action.equals("flowStep")) {
        String[] roleId = req.getParameterValues("roleItem");
        String actionId = req.getParameter("step");
        String rws = req.getParameter("rws");
        //msg = bean.matchActionRole(actionId, roleId,rws);
        url = path+"/web/sdttWeb/Flow/roleManage.jsp";
        log.write(msg, account.getAccountId(), req.getRemoteAddr(), "分配步骤");

      }
    else if (action.equals("del")) {
        String[] flowId = req.getParameterValues("flowIdItem");
        msg = bean.delFlows(flowId);
        log.write(msg, account.getAccountName(), req.getRemoteAddr(), "删除流程");
//        session.setAttribute("url", url);
//        session.setAttribute("msg", msg);
//        resp.sendRedirect(path+"/web/msg.jsp");

      }
    else if (action.equals("addStep")) {
        String flowId = req.getParameter("flowId");
        bean.delFlowSteps(flowId);//删除原有步骤
        int n =  Integer.parseInt(req.getParameter("n"));
        String actionId="";
        for(int i=0;i<n;i++){
        	actionId=req.getParameter("ACTION_ID_"+(i+1));
        	if(!(actionId.equals("")||actionId==null)){
        		msg = bean.addStep(flowId, actionId, i+1);
        	}
        }
        log.write(msg, account.getAccountName(), req.getRemoteAddr(), "添加步骤");
        PrintWriter out = resp.getWriter();
        String ss = "'"+path+"/web/sdttWeb/Flow/stepList.jsp'";
        out.print("<script>opener.location=" + ss + ";window.close();</script>");

      }
	session.setAttribute("url", url);
	session.setAttribute("msg", msg);
	resp.sendRedirect(path+"/web/msg.jsp");
  }

}
