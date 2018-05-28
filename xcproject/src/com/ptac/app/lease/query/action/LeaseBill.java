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
 * @author �
 * @see ���޺�ͬά�����������޸ġ�ɾ����
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
		String action = request.getParameter("action")!=null?request.getParameter("action"):"";//��ȡҪ���Ĳ���
        	        
		if ("zdName".equals(action)) {
			//վ������ģ����ѯ
			String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//��
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";//����	
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";//����
			String stationtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"";//վ������	
			String jzname = request.getParameter("dfmc")!=null?(String)request.getParameter("dfmc"):"";//վ������
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
				if (!("��ѡ��").equals(stationtype)){
					str = str + " and Z.STATIONTYPE IN('" + stationtype + "')";
				}
			}

			ArrayList zdname = new ArrayList();
			zdname = dao.checkMh(loginId,jzname,str); 
			request.setAttribute("jzname",jzname);
			request.setAttribute("zdname",zdname);
			
			request.getRequestDispatcher("/web/appJSP/lease/query/leaseAdd.jsp").forward(request, response);
		
		}else if ("zdMessage".equals(action)) {

			//��������ҳ����ʾ��վ����Ϣ  
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
			 //��������
			 String url = path + "/web/appJSP/lease/query/leaseAdd.jsp";
			 String accountid=request.getParameter("accountid");
			 String dbid = request.getParameter("dbid");
			 DbLog log = new DbLog();
			 String msg = "";
			 
	         //������Ϣ
	         String leasenum = request.getParameter("leasenum");//���޺�ͬ���
	         String leasename = request.getParameter("leasename");//���޺�ͬ����
	         String begintime = request.getParameter("begintime");//��ͬ��ʼʱ��
	         String endtime = request.getParameter("endtime");//��ͬ����ʱ��
	         String money = request.getParameter("money");//��ͬ���
	         String agelimit = request.getParameter("agelimit");//��ͬ����
	         String leasername = request.getParameter("leasername");//���ⷽ����
	         String leaserphone = request.getParameter("leaserphone");///���ⷽ�绰
	         String leaserbank = request.getParameter("leaserbank");//���ⷽ������
	         String leaseraccount = request.getParameter("leaseraccount");//���ⷽ�˺�
	         String payway = request.getParameter("payway");//֧����ʽ
	         String paycircle = request.getParameter("paycircle");//��������
	         String bargainhandler = request.getParameter("bargainhandler");//��ͬ������
	         
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
		     log.write(msg, accountid, request.getRemoteAddr(), "�������޺�ͬ��");
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/appJSP/lease/query/msg.jsp");
		}else if ("delete".equals(action)){
			//���޺�ͬɾ��������Ϣ
			String url = path + "/web/appJSP/lease/query/leaseBill.jsp";
			DbLog log = new DbLog();
			String leaseid = request.getParameter("leaseid");
			String msg = "";
			msg = dao.deleteZl(leaseid);
			log.write(msg,leaseid,request.getRemoteAddr(),"ɾ�����޺�ͬ��");
			session.setAttribute("url",url);
			session.setAttribute("msg",msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("getInfo".equals(action)){
			//���޺�ͬ�޸�ҳ���ѯ��Ϣ
		    Account account = (Account)session.getAttribute("account");
			String loginId = account.getAccountId();
			String leaseid = request.getParameter("leaseid");
			LeaseBean lb = new LeaseBean();
			lb = dao.getZl(leaseid,loginId);
			request.setAttribute("leaseid", leaseid);
	    	request.setAttribute("lb", lb);
	    	request.getRequestDispatcher("/web/appJSP/lease/query/leaseModify.jsp").forward(request, response);
		}else if("modify".equals(action)){
			 //�����޸�
			 String url = path + "/web/appJSP/lease/query/leaseBill.jsp";
			 String accountid=request.getParameter("accountid");
			 String dbid = request.getParameter("dbid");
			 String leaseid = request.getParameter("leaseid");
			 DbLog log = new DbLog();
			 String msg = "";
			 
	         //�޸���Ϣ
	         String leasenum = request.getParameter("leasenum");//���޺�ͬ���
	         String leasename = request.getParameter("leasename");//���޺�ͬ����
	         String begintime = request.getParameter("begintime");//��ͬ��ʼʱ��
	         String endtime = request.getParameter("endtime");//��ͬ����ʱ��
	         String money = request.getParameter("money");//��ͬ���
	         String agelimit = request.getParameter("agelimit");//��ͬ����
	         String leasername = request.getParameter("leasername");//���ⷽ����
	         String leaserphone = request.getParameter("leaserphone");///���ⷽ�绰
	         String leaserbank = request.getParameter("leaserbank");//���ⷽ������
	         String leaseraccount = request.getParameter("leaseraccount");//���ⷽ�˺�
	         String payway = request.getParameter("payway");//֧����ʽ
	         String paycircle = request.getParameter("paycircle");//��������
	         String bargainhandler = request.getParameter("bargainhandler");//��ͬ������
	         
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
		     log.write(msg, accountid, request.getRemoteAddr(), "�޸����޺�ͬ��");
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/appJSP/lease/query/msg.jsp");
		} 
	}

}
