package com.ptac.app.lease.query.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.lease.query.bean.Leasebargainfees;
import com.ptac.app.lease.query.dao.LeaseFeesDao;
import com.ptac.app.lease.query.dao.LeaseFeesDaoImpl;

public class LeaseBargainFeesServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2461149344776297737L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		DbLog log = new DbLog();
		LeaseFeesDao dao = new LeaseFeesDaoImpl();
		String action = request.getParameter("action");
		if ("zdName".equals(action)) {
			String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";//区县	
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";//乡镇
			String stationtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"";//站点类型	
			String jzname = (String)request.getParameter("zdbh")!=null?(String)request.getParameter("zdbh"):"";//站点名称
			String str="";
			if(!shi.equals("")&& !shi.equals("0")){
				str=str+" and Z.shi='"+shi+"'";
			}
			if(!xian.equals("")&& !xian.equals("0")){
				str=str+" and Z.xian='"+xian+"'";
			}
			if(!xiaoqu.equals("")&& !xiaoqu.equals("0")){
				str=str+" and Z.xiaoqu='"+xiaoqu+"'";
			}
			if (!stationtype.equals("") && !stationtype.equals("0")) {
				if (!("请选择").equals(stationtype)){
					str = str + " and Z.STATIONTYPE IN('" + stationtype + "')";
				}
			}
			ArrayList zdname = new ArrayList();
			zdname = dao.getZhandianAndHetong(loginId,jzname,str); 
			request.setAttribute("zdbh",jzname);
			request.setAttribute("zdname",zdname);
			request.getRequestDispatcher("/web/appJSP/lease/query/leaseFeesAdd.jsp").forward(request, response);
		
		}else if ("zdMessage".equals(action)) {
			String stationname=request.getParameter("stationname");
			String zdid="",leaseid="";
	        if(stationname!=null){
	        	if(stationname.contains(",")){
	        		int aa=stationname.indexOf(",");
	        		 zdid=stationname.substring(0,aa);
	        		 leaseid=stationname.substring((aa+1));
	        	}
	        }
	        String lastpayendtime = dao.getLastPayendtime(leaseid);
	        Leasebargainfees lbf = dao.getBaseInfo(zdid, leaseid);
	        if(lastpayendtime!=null){
	        	lbf.setPaybegintime(lastpayendtime);
	        }
	        String accountmonth = null;
	        Date today = new Date(); 
	        int tyear = 1900 + today.getYear();//年
	        int tmonth = today.getMonth() + 1;//月
	        String month = String.valueOf(tmonth);
	        if(tmonth < 10){
	        	 month="0" + tmonth;
	        }
	        accountmonth = tyear + "-" + month;
	        lbf.setAccountmonth(accountmonth);
	        request.setAttribute("lbf",lbf);
	        request.getRequestDispatcher("/web/appJSP/lease/query/LeaseFeesMessage.jsp").forward(request, response);
		}else if("addLeaseFees".equals(action)){
			String paybegintime = request.getParameter("paybegintime")==null?"":request.getParameter("paybegintime");
			String payendtime = request.getParameter("payendtime")==null?"":request.getParameter("payendtime");
			String paymoney = request.getParameter("paymoney")==null?"":request.getParameter("paymoney");
			String payhandler = request.getParameter("payhandler")==null?"":request.getParameter("payhandler");
			String accountmonth = request.getParameter("accountmonth")==null?"":request.getParameter("accountmonth");
			String leaseid_fk = request.getParameter("leaseid_fk")==null?"":request.getParameter("leaseid_fk");
			Leasebargainfees leasefees = new Leasebargainfees();
			leasefees.setPaybegintime(paybegintime);
			leasefees.setPayendtime(payendtime);
			leasefees.setPaymoney(Format.str_d(paymoney.trim()));
			leasefees.setPayhandler(payhandler.trim());
			leasefees.setAccountmonth(accountmonth);
			leasefees.setLeaseid_fk(leaseid_fk);
			
			String msg = dao.addLeaseFees(leasefees);
			String url = path + "/web/appJSP/lease/query/leaseFeesAdd.jsp";
			session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if("chaxun".equals(action) || "daochu".equals(action)){
		    Account account = (Account)session.getAttribute("account");
			String loginId = account.getAccountId();
			String shi = request.getParameter("shi") ;// 城市
			String xian = request.getParameter("xian");// 区县
			String xiaoqu = request.getParameter("xiaoqu");// 乡镇
			String jzname = request.getParameter("zdbh");// 站点名称
			String stationtype = request.getParameter("stationtype");//站点类型
			String htmc = request.getParameter("htmc");//租赁合同名称
			String accountmonth = request.getParameter("accountmonth");//报账月份
			String whereStr = "";
			if(shi != null){
				whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
			}
			if (xian != null && !xian.equals("0")) {
				whereStr = whereStr + " AND ZD.XIAN ='" + xian + "'";
			}
			if (xiaoqu != null && !xiaoqu.equals("0")) {
				whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			}
			if (stationtype != null && !stationtype.equals("0")) {
				whereStr = whereStr + " AND ZD.STATIONTYPE='" + stationtype + "'";
			}
			if (jzname != null && jzname != "") {
				whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
			}
			if (htmc != null && htmc != "") {
				whereStr = whereStr + " AND LG.LEASENAME LIKE '%" + htmc + "%'";
			}
			if (accountmonth != null && !accountmonth.equals("")) {
				whereStr = whereStr + " AND LF.ACCOUNTMONTH ='" + accountmonth + "'";
			}
			List<Leasebargainfees> list = new ArrayList<Leasebargainfees>(); 
			list = dao.selectLeaseFees(whereStr, loginId);
			int num = list.size();//结果条数
			request.setAttribute("num", num);//结果条数
			request.setAttribute("list", list);//结果集
			if ("chaxun".equals(action)) {// 传到租赁费用查询页面
				request.getRequestDispatcher("/web/appJSP/lease/query/leaseFeesManage.jsp")
						.forward(request, response);
			} else if ("daochu".equals(action)) {// 传到租赁费用导出页面
				request.getRequestDispatcher("/web/appJSP/lease/query/租赁费用导出.jsp")
						.forward(request, response);
			}
		}else if("delete".equals(action)){
			String leasefeeid = request.getParameter("leasefeeid");
			String url = path + "/web/appJSP/lease/query/leaseFeesManage.jsp"; 
		     String msg = dao.delLeaseFee(leasefeeid);
		      log.write(msg, leasefeeid, request.getRemoteAddr(), "删除租赁费用");
		      session.setAttribute("url", url);
		      session.setAttribute("msg", msg);
		      response.sendRedirect(path + "/web/msg.jsp");
		}else if("getInfo".equals(action)){
			String leasefeeid = request.getParameter("leasefeeid");
			Leasebargainfees lbf = new Leasebargainfees();
			lbf = dao.getLeaseFeeInfo(leasefeeid);
	        String accountmonth = null;
	        Date today = new Date(); 
	        int tyear = 1900 + today.getYear();//年
	        int tmonth = today.getMonth() + 1;//月
	        String month = String.valueOf(tmonth);
	        if(tmonth < 10){
	        	 month="0" + tmonth;
	        }
	        accountmonth = tyear + "-" + month;
	        lbf.setAccountmonth(accountmonth);
			request.setAttribute("lbf", lbf);
	    	request.getRequestDispatcher("/web/appJSP/lease/query/leaseFeesModify.jsp").forward(request, response);
		}else if("modify".equals(action)){
			System.out.println("nimei");
			String paybegintime = request.getParameter("paybegintime")==null?"":request.getParameter("paybegintime");
			String payendtime = request.getParameter("payendtime")==null?"":request.getParameter("payendtime");
			String paymoney = request.getParameter("paymoney")==null?"":request.getParameter("paymoney");
			String payhandler = request.getParameter("payhandler")==null?"":request.getParameter("payhandler");
			String accountmonth = request.getParameter("accountmonth")==null?"":request.getParameter("accountmonth");
			String leasefeeid = request.getParameter("leasefeeid")==null?"":request.getParameter("leasefeeid");
			Leasebargainfees leasefees = new Leasebargainfees();
			leasefees.setPaybegintime(paybegintime);
			leasefees.setPayendtime(payendtime);
			leasefees.setPaymoney(Format.str_d(paymoney.trim()));
			leasefees.setPayhandler(payhandler.trim());
			leasefees.setAccountmonth(accountmonth);
			leasefees.setLeasefeeid(leasefeeid);
			
			String msg = dao.updateLeaseFees(leasefees);
			String url = path + "/web/appJSP/lease/query/leaseFeesManage.jsp";
			session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
	}

}
