package com.noki.electricfees.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.noki.ammeterdegree.javabean.BargainBean;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;

public class BargainServlet extends HttpServlet {

	 private static final String Content_type = "text/html;charset=UTF-8";
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	      IOException, ServletException {
	    resp.setContentType(Content_type);
	    String path = req.getContextPath();
	    DbLog log = new DbLog();
	    
	    Account account = new Account();
	    BargainBean formBean= new BargainBean();
	    //String url = path + "/web/electricfees/bargainDan.jsp", msg = "";
	    String url = path + "/web/electricfees/bargainDanInput.jsp", msg = "";
	    HttpSession session = req.getSession();
	    account = (Account) session.getAttribute("account");

	    ElectricFeesBean bean = new ElectricFeesBean();
	    String action = req.getParameter("action");
	    if(action.equals("addfd")){
	    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    	 String entrytime=df.format(new Date());
	    	formBean.setStationid(req.getParameter("stationid"));	    	
	    	formBean.setAccountmonth(req.getParameter("accountmonth"));
	    	formBean.setHtbianhao(req.getParameter("htbianhao"));
	    	String pjje=req.getParameter("pjje");
	    	double d=0;
	    	d=Double.parseDouble(pjje);
	    	formBean.setPjje(d);
	    	formBean.setHtbianhao(req.getParameter("htbianhao"));
	    	//formBean.setAmmeterdegreeid_fk(req.getParameter("ammeteridFk"));
	    	formBean.setDanjia(req.getParameter("danjia"));
	    	formBean.setFeetypeid(req.getParameter("feetypeid"));//--meiyou 
	    	formBean.setKptime(req.getParameter("kptime"));//--meiyou
	    	formBean.setMemo(req.getParameter("memo"));
	    	formBean.setMoney(req.getParameter("money"));//--meiyou
	    	//formBean.setBargain(req.getParameter("bargain"));//---meiyou
	    	formBean.setNotetime(req.getParameter("notetime"));
	    	formBean.setNotetypeid(req.getParameter("notetypeid"));
	    	formBean.setPrepayment_ammeterid(req.getParameter("ammeteridFk"));	    	
	    	formBean.setStartmonth(req.getParameter("startmonth"));
	    	formBean.setEndmonth(req.getParameter("endmonth"));
	    	
	    	//没有
	    	formBean.setEntrypensonnel(req.getParameter("accountname"));
	    	//------待测
	    	formBean.setEntrytime(entrytime);
	    	//formBean.setBargainid(req.getParameter("bargainid"));
	    	String start=req.getParameter("startmonth");
	        String end=req.getParameter("endmonth");
	        
	        formBean.setThisdegree(req.getParameter("thisdegree"));
	        formBean.setThisdatetime(req.getParameter("thisdatetime"));
	        formBean.setLinelosstype(req.getParameter("linelosstype;"));
	        formBean.setFloatpay(req.getParameter("floatpay;"));
	        formBean.setInputoperator(req.getParameter("inputoperator;"));
	        formBean.setPaydatetime(req.getParameter("paydatetime"));
	        formBean.setPayoperator(req.getParameter("payoperator"));
	        
	        //获取分摊后算的金额（网络运营线电费(生产)、信息化支撑线电费、行政综合线电费（办公）、市场营销线电费(营业)、建设投资线电费、代垫电费）
	        formBean.setNetworkdf(req.getParameter("networkdf"));
	        formBean.setInformatizationdf(req.getParameter("informatizationdf"));
	        formBean.setAdministrativedf(req.getParameter("administrativedf"));
	        formBean.setMarketdf(req.getParameter("marketdf"));
	        formBean.setBuilddf(req.getParameter("builddf"));
	        formBean.setDddf(req.getParameter("dddf"));
	    	msg = bean.addBargain(formBean,start,end);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        resp.sendRedirect(path + "/web/msg.jsp");

	    	
	    }
	    else if(action.equals("modify")){
	    	  String id = req.getParameter("id");  

	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    	  String entrytime=df.format(new Date());
	    	  
	    	  formBean.setPrepayment_ammeterid(req.getParameter("ammeteridFk"));
	    	  formBean.setDanjia(req.getParameter("danjia"));
	          formBean.setNotetypeid(req.getParameter("notetypeid"));
	          formBean.setNotetime(req.getParameter("notetime"));
	          formBean.setNoteno(req.getParameter("noteno"));
	          formBean.setStationid(req.getParameter("zdid"));
	          formBean.setAccountmonth(req.getParameter("accountmonth"));
	          formBean.setMemo(req.getParameter("memo"));
	          formBean.setKptime(req.getParameter("kptime"));
	          formBean.setEndmonth(req.getParameter("endmonth"));
	          formBean.setStartmonth(req.getParameter("startmonth"));
	          formBean.setMemo(req.getParameter("memo"));
	          formBean.setMoney(req.getParameter("money"));
	          formBean.setHtbianhao(req.getParameter("htbianhao"));
	          String pj=req.getParameter("pjje");
	          if("".equals(pj)||null==pj||"null".equals(pj)){
	        	  pj="0";
	          }
	          formBean.setPjje(Double.parseDouble(pj));
	          //formBean.setAmmeterdegreeid_fk(req.getParameter("ammeterdegreeidFk"));
	          
	         formBean.setEntrypensonnel(req.getParameter("accountname"));
		     formBean.setEntrytime(entrytime);
		      
	          //获取分摊后算的金额（网络运营线电费(生产)、信息化支撑线电费、行政综合线电费（办公）、市场营销线电费(营业)、建设投资线电费）
	          formBean.setNetworkdf(req.getParameter("networkdf"));
	          formBean.setInformatizationdf(req.getParameter("informatizationdf"));
	          formBean.setAdministrativedf(req.getParameter("administrativedf"));
	          formBean.setMarketdf(req.getParameter("marketdf"));
	          formBean.setBuilddf(req.getParameter("builddf"));
	          formBean.setDddf(req.getParameter("dddf"));


	          List ammeterdegreeid=new ArrayList();
	          String start=req.getParameter("startmonth");
		      String end=req.getParameter("endmonth");
	          msg = formBean.modifyBargain(formBean,id,start,end);
	          session.setAttribute("url", url);
	          session.setAttribute("msg", msg);
	          resp.sendRedirect(path + "/web/msg.jsp");

	    	
	    	
	    }
	    else if (action.equals("delete")) {
	    	
	      String id = req.getParameter("id");

	      msg = formBean.delBargain(id);
	     // log.write(msg, formBean.getId(), req.getRemoteAddr(), "删除合同单");
	      session.setAttribute("url", url);
	      session.setAttribute("msg", msg);
	      resp.sendRedirect(path + "/web/msg.jsp");

	    }   
	  }
}
