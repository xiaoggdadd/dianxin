package com.noki.ammeterdegree.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.AccountBean;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class AmmeterDegreeServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
	  
	  System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    AmmeterDegreeFormBean formBean= new AmmeterDegreeFormBean();
    String url = path + "/web/dianliang/dianliangList.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    AmmeterDegreeBean bean = new AmmeterDegreeBean();
    String action = req.getParameter("action");
    String status = "";
    if(req.getParameter("status") != null){
      status = req.getParameter("status");
    }
    
    
    if (action.equals("add")) {//新增电量
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	 String entrytime=df.format(new Date());
      //url = path + "/web/dianliang/dianliangList.jsp";
      url = path + "/web/dianliang/addDegree.jsp";
      formBean.setAmmeteridFk(req.getParameter("ammeteridFk"));
      formBean.setLastdegree(req.getParameter("lastdegree"));
      formBean.setLastdatetime(req.getParameter("lastdatetime"));
      formBean.setThisdegree(req.getParameter("thisdegree"));
      formBean.setThisdatetime(req.getParameter("thisdatetime"));
      formBean.setInputoperator(req.getParameter("inputoperator"));
      formBean.setInputdatetime(req.getParameter("inputdatetime"));
      formBean.setFloatdegree(req.getParameter("floatdegree")!=null?req.getParameter("floatdegree"):"0");
      formBean.setActualdegree(req.getParameter("actualdegree"));
      formBean.setStartmonth(req.getParameter("startmonth"));
      formBean.setEndmonth(req.getParameter("endmonth"));
      formBean.setMemo(req.getParameter("memo"));
      formBean.setBlhdl(req.getParameter("blhdl"));
      formBean.setEntrypensonnel(req.getParameter("accountname"));
      formBean.setEntrytime(entrytime);
      String ad2_bz = req.getParameter("ad2_bz");
      formBean.setAd2_bz(ad2_bz);
      String ad2_bz1 = req.getParameter("ad2_bz1");
      formBean.setAd2_bz1(ad2_bz1);
      //2014-10-28 WangYiXiao
      String beilv = req.getParameter("beilv");
      String zlfh = req.getParameter("zlfh"); 
      String jlfh = req.getParameter("jlfh");
      String property = req.getParameter("property");
      String edhdl = req.getParameter("edhdl");
      String scb = req.getParameter("scb");
      String shi = req.getParameter("shi");
      String qsdbdl = req.getParameter("qsdbdl");
      String stationtype = req.getParameter("stationtype");
      if(Format.str_d(beilv)==0){
     	 beilv = "1";
      }
      String dbydl = String.valueOf((Format.str_d(formBean.getThisdegree())-Format.str_d(formBean.getLastdegree()))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
      ElectricTool elecToo = new ElectricTool();
      String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, formBean.getThisdatetime(), formBean.getLastdatetime(), shi, property, zlfh, jlfh, edhdl, scb, formBean.getAmmeteridFk(),req.getParameter("blhdl"),qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
  	  String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
      String hbzq = cbbl[1];//合并周期
  	  String bzz = cbbl[2];//标准值
  	  formBean.setBeilv(beilv);
  	  formBean.setHbzq(hbzq);
  	  formBean.setBzz(bzz);
  	  formBean.setScbbl(qsdbdlcbbl);
  	  formBean.setScb(scb);
  	  formBean.setDbydl(dbydl);
      
      String bzw="1";//标志位
      msg = bean.addDegree(formBean,bzw);
      log.write(msg, formBean.getAmmeterdegreeid(), req.getRemoteAddr(), "增加电量");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("del")) {
      String degreeid = req.getParameter("degreeid");

      msg = bean.delAmmeterDegree(degreeid);
      log.write(msg, formBean.getAmmeterdegreeid(), req.getRemoteAddr(), "删除电量");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }else if(action.equals("deletes")){
      String chooseIdStr = req.getParameter("chooseIdStr");     
	  msg = bean.deletesElectricFees(chooseIdStr);
      log.write(msg, formBean.getAmmeterdegreeid(), req.getRemoteAddr(), "批量删除电量"); 
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");
    }
    else if (action.equals("modify")) {
    	//System.out.println("11111111");
      String degreeid = req.getParameter("ammeterdegreeid");
      if(status.equals("qmodify")){
    	url = path + "/web/check/checkDegreeAuto.jsp?degreeid="+degreeid;  
      }else{
        url = path + "/web/dianliang/dianliangList.jsp?degreeid="+degreeid;
      }
      
 	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 String entrytime=df.format(new Date());
      
      String ad2_bz = req.getParameter("ad2_bz");
	  formBean = formBean.getAmmeterDegreeInfo(degreeid);

	  formBean.setAd2_bz(ad2_bz);
	  formBean.setAmmeterdegreeid(degreeid);
	  formBean.setAmmeteridFk(req.getParameter("ammeteridFk"));
      formBean.setLastdegree(req.getParameter("lastdegree"));
      formBean.setLastdatetime(req.getParameter("lastdatetime"));
      formBean.setThisdegree(req.getParameter("thisdegree"));
      formBean.setThisdatetime(req.getParameter("thisdatetime"));
      formBean.setInputoperator(req.getParameter("inputoperator"));
      formBean.setInputdatetime(req.getParameter("inputdatetime"));
      formBean.setFloatdegree(req.getParameter("floatdegree"));
      formBean.setActualdegree(req.getParameter("actualdegree"));
      formBean.setStartmonth(req.getParameter("startmonth"));
      formBean.setEndmonth(req.getParameter("endmonth"));
      formBean.setMemo(req.getParameter("memo"));
      formBean.setAutoauditstatus(req.getParameter("autoauditstatus"));//自动审核状态
      formBean.setAutoauditdescription(req.getParameter("autoauditdescription"));//自动审核描述
      formBean.setBlhdl(req.getParameter("blhdl"));//倍率耗电量
      formBean.setEntrypensonnel(req.getParameter("accountname"));
      formBean.setEntrytime(entrytime);
      //2014-10-28
      String beilv = req.getParameter("beilv");
      String zlfh = req.getParameter("zlfh"); 
      String jlfh = req.getParameter("jlfh");
      String property = req.getParameter("property");
      String edhdl = req.getParameter("edhdl");
      String scb = req.getParameter("scb");
      String shi = req.getParameter("shi");
      String qsdbdl = req.getParameter("qsdbdl");
      String stationtype = req.getParameter("stationtype");
      if(Format.str_d(beilv)==0){
     	 beilv = "1";
      }
      String dbydl = String.valueOf((Format.str_d(formBean.getThisdegree())-Format.str_d(formBean.getLastdegree()))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
      ElectricTool elecToo = new ElectricTool();
      String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, formBean.getThisdatetime(), formBean.getLastdatetime(), shi, property, zlfh, jlfh, edhdl, scb, formBean.getAmmeteridFk(),req.getParameter("blhdl"),qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
  	  String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
      String hbzq = cbbl[1];//合并周期
  	  String bzz = cbbl[2];//标准值
  	  formBean.setBeilv(beilv);
  	  formBean.setHbzq(hbzq);
  	  formBean.setBzz(bzz);
  	  formBean.setScbbl(qsdbdlcbbl);
  	  formBean.setScb(scb);
  	  formBean.setDbydl(dbydl);
      
      String str="";
      msg = bean.modifyAmmeterDegree(formBean,str,degreeid);
      
      log.write(msg, formBean.getAmmeterdegreeid(), req.getRemoteAddr(), "修改电量");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("pay")) {
    	ElectricFeesFormBean payformBean= new ElectricFeesFormBean();
    	      url = path + "/web/dianliang/payElectricFees.jsp";
    	      payformBean.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
    	      payformBean.setUnitprice(req.getParameter("unitprice"));
    	      payformBean.setFloatpay(req.getParameter("floatpay"));
    	      payformBean.setActualpay(req.getParameter("actualpay"));
    	      payformBean.setNotetypeid(req.getParameter("notetypeid"));
    	      System.out.println(req.getParameter("notetypeid"));
    	      payformBean.setNoteno(req.getParameter("noteno"));
    	      payformBean.setPayoperator(req.getParameter("payoperator"));
    	      payformBean.setPaydatetime(req.getParameter("paydatetime"));
    	      payformBean.setAccountmonth(req.getParameter("accountmonth"));
    	      payformBean.setMemo(req.getParameter("memo"));
    	      String start=req.getParameter("startmonth");
    	      String end=req.getParameter("endmonth");
    	      
    	      //session.setAttribute("degreeid", payformBean.getAmmeterdegreeidFk());
    	      ElectricFeesBean paybean = new ElectricFeesBean();
    	      msg = paybean.addElectricFees(payformBean,start,end);
    	      log.write(msg, payformBean.getElectricfeeId(), req.getRemoteAddr(), "交费");
    	      session.setAttribute("url", url);
    	      session.setAttribute("msg", msg);
    	      resp.sendRedirect(path + "/web/msg.jsp");
    }



  }

}
