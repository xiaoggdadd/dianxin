package com.noki.check.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.check.javabean.CheckDegreeBean;
import com.noki.check.javabean.ZhanDianForm;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.AccountBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class CheckDegreeServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    PrintWriter out = resp.getWriter();
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    //AmmeterDegreeFormBean formBean= new AmmeterDegreeFormBean();
    String url = path + "/web/check/checkDegreeManual.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");
    CheckDegreeBean bean = new CheckDegreeBean();
    String action = req.getParameter("action");
    String personnal=account.getAccountName();//审核人员
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(currentTime);

    if (action.equals("checkdmno")) {
      AmmeterDegreeFormBean formBean= new AmmeterDegreeFormBean();
      String chooseIdStr = req.getParameter("chooseIdStr");     
      url = path + "/web/check/checkDegreeManual.jsp" ;
      String[] chooseId = chooseIdStr.split(",");
      for(int i=0;i<chooseId.length;i++){
    	  formBean = formBean.getAmmeterDegreeInfo(chooseId[i]); 
    	  formBean.setManualauditstatus("0");
          //formBean.setManualauditname(account);
          formBean.setManualauditdatetime(dateString);
          msg = bean.modifyCheckDegree(formBean,"0");
          log.write(msg, account.getName(), req.getRemoteAddr(), "人工电量审核"); 
      }
	 
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }
    //人工电量审核
    else if (action.equals("checkdm")) {
    	AmmeterDegreeFormBean formBean= new AmmeterDegreeFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkDegreeManual.jsp" ;
        String[] chooseId = chooseIdStr.split(",");
        for(int i=0;i<chooseId.length;i++){
        	System.out.println("begin______");
          //更改信息   注解原有的 全部 bean信息
      	  //formBean = formBean.getAmmeterDegreeInfo(chooseId[i]);
          //新增  id的信息
          formBean.setAmmeterdegreeid(chooseId[i]);
      	  formBean.setCityid(formBean.getXian(chooseId[i]));
      	  formBean.setAmmeteruse(formBean.getDBYT(chooseId[i]));
   	      //System.out.println(chooseId[i]+"**--**"+formBean.getXian(chooseId[i])+"**--**"+formBean.getDBYT(chooseId[i]));
      	  
   	        //参数信息
   	        formBean.setManualauditstatus("1");
            formBean.setManualauditdatetime(dateString);
            formBean.setManualauditname(personnal);
            
            
            msg = bean.modifyCheckDegree(formBean,"1");
          
            log.write(msg, account.getName(), req.getRemoteAddr(), "人工电量审核"); 
        }
        System.out.println("33333333");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkfm")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkFeesManual.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
        //for(int i=0;i<chooseId.length;i++){
        //	System.out.println("------"+chooseId[i]);
      	  //formBean = formBean.getElectricFeesInfo(chooseId[i]);
      	  formBean.setManualauditstatus("1");
            //formBean.setManualauditname(account);
            formBean.setManualauditdatetime(dateString);
            formBean.setManualauditname(personnal);
            
            msg = bean.modifyCheckFees(formBean,chooseIdStr,"","1");
            log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核");
       // }
        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("*****人工电费审核******************************结束时间："+m2+" 开始时间:"+m1+" 用时："+m3/1000+"s");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkfmno")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkFeesManual.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        //for(int i=0;i<chooseId.length;i++){
        	
          //formBean = formBean.getElectricFeesInfo(chooseId[i]);
      	  formBean.setManualauditstatus("0");
            //formBean.setManualauditname(account);
            formBean.setManualauditdatetime(dateString);
            formBean.setManualauditname(personnal);
            msg = bean.modifyCheckFees(formBean,chooseIdStr,"","0");
            log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核"); 
        //}
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkfmno22")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkFeesManual.jsp" ;
      	formBean.setManualauditstatus("-2");
        formBean.setManualauditdatetime(dateString);
        formBean.setManualauditname(personnal);
        msg = bean.modifyCheckFees(formBean,chooseIdStr,"","0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核"); 
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkcity")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	
    	String chooseIdStr1 = req.getParameter("chooseIdStr1"); 
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
        url = path + "/web/check/checkFeesCity.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
        //for(int i=0;i<chooseId.length;i++){
        //	System.out.println("------"+chooseId[i]);
      	//  formBean = formBean.getElectricFees(chooseId[i]);
      	  formBean.setCityaudit("1");
      	//===========添加审核人员和审核时间===========
      	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      	 String entrytime=df.format(new Date());
      	 //==================================================
            //formBean.setManualauditname(account);
      	 formBean.setCityauditpensonnel(personnal);
      	 formBean.setCityaudittime(dateString);
           
            msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"1");
            log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核"); 
        //}
        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("****市级电费审核****************************结束时间："+m2+"开始时间:"+m1+"用时："+m3/1000+"s");
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkcityno")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
    	
        url = path + "/web/check/checkFeesCity.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        //for(int i=0;i<chooseId.length;i++){
        	
          //formBean = formBean.getElectricFees(chooseId[i]);
      	  formBean.setCityaudit("0");
            //formBean.setManualauditname(account);
            formBean.setCityaudittime(dateString);
            formBean.setCityauditpensonnel(personnal);
            msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"0");
            log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核"); 
        //}
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    } else if (action.equals("checkcityno2")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
    	
        url = path + "/web/check/checkFeesCity.jsp" ;
      	formBean.setCityaudit("-2");
        formBean.setCityaudittime(dateString);
        formBean.setCityauditpensonnel(personnal);
        msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核"); 
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkff")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1"); 
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
    	//String dfzflx=req.getParameter("dfzflx");
    	String currentmonth=req.getParameter("currentmonth");
        url = path + "/web/check/checkFeesFinance.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
        //for(int i=0;i<chooseId.length;i++){
      	  //formBean = formBean.getElectricFees(chooseId[i]);
      	  formBean.setManualauditstatus("2");
            //formBean.setManualauditname(account);
            formBean.setFinancialdatetime(dateString);
            formBean.setCurrentmonth(currentmonth);
            formBean.setFinancialoperator(personnal);
            msg = bean.modifyCheckFees(formBean,chooseIdStr1,chooseIdStr2,"1");
            log.write(msg, account.getName(), req.getRemoteAddr(), "财务电费审核"); 
       // }
        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("**财务电费审核*****************************结束时间："+m2+"开始时间:"+m1+"用时："+m3/1000+"s");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkffno")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
    	//String dfzflx=req.getParameter("dfzflx");
        url = path + "/web/check/checkFeesFinance.jsp" ;
        //String[] chooseId = chooseIdStr.split(",");
        //for(int i=0;i<chooseId.length;i++){
         // formBean = formBean.getElectricFees(chooseId[i]);
      	  formBean.setManualauditstatus("-1");
            //formBean.setManualauditname(account);
            formBean.setFinancialdatetime(dateString);
            formBean.setFinancialoperator(personnal);
            System.out.println("========================"+personnal);
            msg = bean.modifyCheckFees(formBean,chooseIdStr1,chooseIdStr2,"0");
            log.write(msg, account.getName(), req.getRemoteAddr(), "财务电费审核"); 
        //}
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checksp")) {
    	ZhanDianForm formBean= new ZhanDianForm();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkStationPointManual.jsp" ;
        String[] chooseId = chooseIdStr.split(",");
        for(int i=0;i<chooseId.length;i++){
          formBean = formBean.getZhanDianInfo(chooseId[i]);
          //System.out.println(chooseId[i]);
      	  formBean.setManualauditstatus_station("1");
            //formBean.setManualauditname(account);
            formBean.setManualauditdatetime_station(dateString);
            msg = bean.modifyCheckZhanDian(formBean,"1");
            log.write(msg, account.getName(), req.getRemoteAddr(), "人工站点审核"); 
        }
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("checkspno")) {
    	ZhanDianForm formBean= new ZhanDianForm();
    	String chooseIdStr = req.getParameter("chooseIdStr");     
        url = path + "/web/check/checkStationPointManual.jsp" ;
        String[] chooseId = chooseIdStr.split(",");
        for(int i=0;i<chooseId.length;i++){
          formBean = formBean.getZhanDianInfo(chooseId[i]);
      	  formBean.setManualauditstatus_station("0");
            //formBean.setManualauditname(account);
            formBean.setManualauditdatetime_station(dateString);
            msg = bean.modifyCheckZhanDian(formBean,"0");
            log.write(msg, account.getName(), req.getRemoteAddr(), "人工站点审核"); 
        }
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }else if (action.equals("checkcity1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	
    	String chooseIdStr1 = req.getParameter("chooseIdStr1"); 
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
        url = path + "/web/check/checkFeesCity.jsp" ;
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
      	formBean.setCityaudit("1");
      	//===========添加审核人员和审核时间===========
      	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      	String entrytime=df.format(new Date());
      	formBean.setCityauditpensonnel(personnal);
      	formBean.setCityaudittime(dateString);
           
        msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"1");
        log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核"); 

        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("****市级电费审核****************************结束时间："+m2+"开始时间:"+m1+"用时："+m3/1000+"s");
        String m="";
        if(msg=="审核电费信息失败！"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }else if (action.equals("checkcityno1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
        url = path + "/web/check/checkFeesCity.jsp" ;
  	  	formBean.setCityaudit("0");
        formBean.setCityaudittime(dateString);
        formBean.setCityauditpensonnel(personnal);
        msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核");
        String m="";
        if(msg=="审核电费信息失败！"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }else if (action.equals("checkcityno11")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
        url = path + "/web/check/checkFeesCity.jsp" ;
  	  	formBean.setCityaudit("-2");
        formBean.setCityaudittime(dateString);
        formBean.setCityauditpensonnel(personnal);
        msg = bean.CheckCityFees(formBean,chooseIdStr1,chooseIdStr2,"0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "市级电费审核");
        String m="";
        if(msg=="审核电费信息失败！"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }
    else if (action.equals("checkfm1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
      	formBean.setManualauditstatus("1");
        formBean.setManualauditdatetime(dateString);
        formBean.setManualauditname(personnal);        
        msg = bean.modifyCheckFees(formBean,chooseIdStr,"","1");
        log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核");
        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("*****人工电费审核******************************结束时间："+m2+" 开始时间:"+m1+" 用时："+m3/1000+"s");
    }else if (action.equals("checkfmno1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");
  	  	formBean.setManualauditstatus("0");
        formBean.setManualauditdatetime(dateString);
        formBean.setManualauditname(personnal);
        msg = bean.modifyCheckFees(formBean,chooseIdStr,"","0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核"); 

    }else if (action.equals("checkfmno11")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr = req.getParameter("chooseIdStr");
  	  	formBean.setManualauditstatus("-2");
        formBean.setManualauditdatetime(dateString);
        formBean.setManualauditname(personnal);
        msg = bean.modifyCheckFees(formBean,chooseIdStr,"","0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "人工电费审核"); 

    }else if (action.equals("checkff1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1"); 
    	String chooseIdStr2 = req.getParameter("chooseIdStr2");
    	String currentmonth=req.getParameter("currentmonth");
        SimpleDateFormat fa=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = new Date();
        String m1 = fa.format(time1);
        formBean.setManualauditstatus("2");
        formBean.setFinancialdatetime(dateString);
        formBean.setCurrentmonth(currentmonth);
        formBean.setFinancialoperator(personnal);
        msg = bean.modifyCheckFees(formBean,chooseIdStr1,chooseIdStr2,"1");
        log.write(msg, account.getName(), req.getRemoteAddr(), "财务电费审核"); 
        Date time2 = new Date();
        String m2 = fa.format(time2);
        long m3 = time2.getTime()-time1.getTime();
        System.out.println("**财务电费审核*****************************结束时间："+m2+"开始时间:"+m1+"用时："+m3/1000+"s");
    }else if (action.equals("checkffno1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
    	String chooseIdStr2 = req.getParameter("chooseIdStr2"); 
  	  	formBean.setManualauditstatus("-1");
        formBean.setFinancialdatetime(dateString);
        formBean.setFinancialoperator(personnal);
        msg = bean.modifyCheckFees(formBean,chooseIdStr1,chooseIdStr2,"0");
        log.write(msg, account.getName(), req.getRemoteAddr(), "财务电费审核");

    }else if (action.equals("checknewhtsh")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	
    	String chooseIdStr = req.getParameter("chooseIdStr"); 
        url = path + "/web/newgn/hetong/checknewhtsh.jsp" ;
           
         msg = bean.Checknewhtsh(chooseIdStr,"1");
         log.write(msg, account.getName(), req.getRemoteAddr(), "合同单补录发票审核"); 
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }else if (action.equals("checknewhtsh1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();    	
    	String chooseIdStr = req.getParameter("chooseIdStr"); 
        url = path + "/web/newgn/hetong/checknewhtsh.jsp";
           
        msg = bean.Checknewhtsh(chooseIdStr,"1");
        log.write(msg, account.getName(), req.getRemoteAddr(), "合同单补录发票审核"); 

        String m="";
        if(msg=="审核合同单补录发票信息失败！"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }else if (action.equals("checknewhtshno1")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();
    	
    	String chooseIdStr = req.getParameter("chooseIdStr"); 
        url = path +"/web/newgn/hetong/checknewhtsh.jsp" ;
           
         msg = bean.Checknewhtsh(chooseIdStr,"2");
         log.write(msg, account.getName(), req.getRemoteAddr(), "合同单补录发票审核"); 
  	 
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

    }else if (action.equals("checknewhtshno11")) {
    	ElectricFeesFormBean formBean= new ElectricFeesFormBean();    	
    	String chooseIdStr = req.getParameter("chooseIdStr"); 
        url = path + "/web/newgn/hetong/checknewhtsh.jsp";
           
        msg = bean.Checknewhtsh(chooseIdStr,"2");
        log.write(msg, account.getName(), req.getRemoteAddr(), "合同单补录发票审核"); 

        String m="";
        if(msg=="审核合同单补录发票信息失败！"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }
 
    
  }

}
