package com.ptac.app.lease.query.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.lease.query.bean.LeaseBean;
import com.ptac.app.lease.query.dao.LeaseBillDao;
import com.ptac.app.lease.query.dao.LeaseBillDaoImp;

/**
 * @author 李靖
 * @see 租赁合同维护（新增、修改、删除）
 */
public class LeaseBill extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		LeaseBillDao dao = new LeaseBillDaoImp();
		String action = request.getParameter("action")!=null?request.getParameter("action"):"";//获取要做的操作
        	        
		if ("zdName".equals(action)) {
			//站点名称模糊查询
			String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";//区县	
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";//乡镇
			String stationtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"";//站点类型	
			String jzname = request.getParameter("dfmc")!=null?(String)request.getParameter("dfmc"):"";//站点名称
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
			zdname = dao.checkMh(loginId,jzname,str); 
			request.setAttribute("jzname",jzname);
			request.setAttribute("zdname",zdname);
			
			request.getRequestDispatcher("/web/appJSP/lease/query/leaseAdd.jsp").forward(request, response);
		
		}else if ("zdMessage".equals(action)) {

			//租赁增加页面显示的站点信息  
			String stationname=request.getParameter("stationname");
			String dbid="",zdcode="";
	        if(stationname!=null){
	        	if(stationname.contains(",")){
	        		int aa=stationname.indexOf(",");
	        		 dbid=stationname.substring(0,aa);
	        		 zdcode=stationname.substring((aa+1));
	        	}
	        }
			
	        LeaseBean lb = new LeaseBean();
			lb = dao.bas(dbid);

			request.setAttribute("lb",lb);
			request.setAttribute("dbid",dbid);
			
			request.getRequestDispatcher("/web/appJSP/lease/query/leaseBillXx.jsp").forward(request, response);
		
		}else if ("addZl".equals(action)) {
			 //租赁增加
			 String url = path + "/web/appJSP/lease/query/leaseAdd.jsp";
			 String accountid=request.getParameter("accountid");
			 String dbid = request.getParameter("dbid");
			 DbLog log = new DbLog();
			 String msg = "";
			 
	         //租赁信息
	         String leasenum = request.getParameter("leasenum");//租赁合同编号
	         String leasename = request.getParameter("leasename");//租赁合同名称
	         String begintime = request.getParameter("begintime");//合同起始时间
	         String endtime = request.getParameter("endtime");//合同结束时间
	         String money = request.getParameter("money");//合同金额
	         String agelimit = request.getParameter("agelimit");//合同年限
	         String leasername = request.getParameter("leasername");//出租方姓名
	         String leaserphone = request.getParameter("leaserphone");///出租方电话
	         String leaserbank = request.getParameter("leaserbank");//出租方开户行
	         String leaseraccount = request.getParameter("leaseraccount");//出租方账号
	         String payway = request.getParameter("payway");//支付方式
	         String paycircle = request.getParameter("paycircle");//付款周期
	         String bargainhandler = request.getParameter("bargainhandler");//合同经办人
	         
	         LeaseBean lb = new LeaseBean();
	         lb.setLeasenum(leasenum.trim());
	         lb.setLeasename(leasename.trim());
	         lb.setBegintime(begintime);
	         lb.setEndtime(endtime);
	         lb.setMoney(Format.str_d(money.trim()));
	         lb.setAgelimit(Format.str_d(agelimit.trim()));
	         lb.setLeasername(leasername.trim());
	         lb.setLeaserphone(leaserphone.trim());
	         lb.setLeaserbank(leaserbank.trim());
	         lb.setLeaseraccount(leaseraccount.trim());
	         lb.setPayway(payway);
	         lb.setPaycircle(paycircle.trim());
	         lb.setBargainhandler(bargainhandler.trim());

	         msg = dao.addZlht(dbid,lb);
		     log.write(msg, accountid, request.getRemoteAddr(), "新增租赁合同单");
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/appJSP/lease/query/msg.jsp");
		}else if ("delete".equals(action)){
			//租赁合同删除单条信息
			String url = path + "/web/appJSP/lease/query/leaseBill.jsp";
			DbLog log = new DbLog();
			String leaseid = request.getParameter("leaseid");
			String msg = "";
			msg = dao.deleteZl(leaseid);
			log.write(msg,leaseid,request.getRemoteAddr(),"删除租赁合同单");
			session.setAttribute("url",url);
			session.setAttribute("msg",msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("getInfo".equals(action)){
			//租赁合同修改页面查询信息
		    Account account = (Account)session.getAttribute("account");
			String loginId = account.getAccountId();
			String leaseid = request.getParameter("leaseid");
			LeaseBean lb = new LeaseBean();
			lb = dao.getZl(leaseid,loginId);
			request.setAttribute("leaseid", leaseid);
	    	request.setAttribute("lb", lb);
	    	request.getRequestDispatcher("/web/appJSP/lease/query/leaseModify.jsp").forward(request, response);
		}else if("modify".equals(action)){
			 //租赁修改
			 String url = path + "/web/appJSP/lease/query/leaseBill.jsp";
			 String accountid=request.getParameter("accountid");
			 String dbid = request.getParameter("dbid");
			 String leaseid = request.getParameter("leaseid");
			 DbLog log = new DbLog();
			 String msg = "";
			 
	         //修改信息
	         String leasenum = request.getParameter("leasenum");//租赁合同编号
	         String leasename = request.getParameter("leasename");//租赁合同名称
	         String begintime = request.getParameter("begintime");//合同起始时间
	         String endtime = request.getParameter("endtime");//合同结束时间
	         String money = request.getParameter("money");//合同金额
	         String agelimit = request.getParameter("agelimit");//合同年限
	         String leasername = request.getParameter("leasername");//出租方姓名
	         String leaserphone = request.getParameter("leaserphone");///出租方电话
	         String leaserbank = request.getParameter("leaserbank");//出租方开户行
	         String leaseraccount = request.getParameter("leaseraccount");//出租方账号
	         String payway = request.getParameter("payway");//支付方式
	         String paycircle = request.getParameter("paycircle");//付款周期
	         String bargainhandler = request.getParameter("bargainhandler");//合同经办人
	         
	         LeaseBean lb = new LeaseBean();
	         lb.setLeasenum(leasenum.trim());
	         lb.setLeasename(leasename.trim());
	         lb.setBegintime(begintime);
	         lb.setEndtime(endtime);
	         lb.setMoney(Format.str_d(money.trim()));
	         lb.setAgelimit(Format.str_d(agelimit.trim()));
	         lb.setLeasername(leasername.trim());
	         lb.setLeaserphone(leaserphone.trim());
	         lb.setLeaserbank(leaserbank.trim());
	         lb.setLeaseraccount(leaseraccount.trim());
	         lb.setPayway(payway);
	         lb.setPaycircle(paycircle.trim());
	         lb.setBargainhandler(bargainhandler.trim());

	         msg = dao.addModify(leaseid,dbid,lb);
		     log.write(msg, accountid, request.getRemoteAddr(), "修改租赁合同单");
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/appJSP/lease/query/msg.jsp");
		} 
	}

}
