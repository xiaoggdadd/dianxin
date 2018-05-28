package com.noki.electricfees.servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.electricfees.javabean.PrepaymentBean;
import com.noki.electricfees.javabean.PrepaymentFormBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.sys.javabean.AccountBean;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricityTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class PrepaymentServlet
    extends HttpServlet {
  private static final String Content_type = "text/html;charset=UTF-8";
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
      IOException, ServletException {
    resp.setContentType(Content_type);
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    PrepaymentFormBean formBean= new PrepaymentFormBean();
    String url = path + "/web/electricfees/addPrepayment.jsp", msg = "";
    //String url = path + "/web/electricfees/prepaymentList.jsp", msg = "";
    HttpSession session = req.getSession();
    account = (Account) session.getAttribute("account");

    PrepaymentBean bean = new PrepaymentBean();
    String action = req.getParameter("action");
    String status = "";
    if(req.getParameter("status") != null){
      status = req.getParameter("status");
    }
    
    
    if (action.equals("add")) {      //添加预付费
    	
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      String entrytime=df.format(new Date());
      url = path + "/web/electricfees/addPrepayment.jsp";
      
      formBean.setStationid(req.getParameter("stationid"));
      
      String dbid = req.getParameter("dbid");
      formBean.setPrepaymentammeterid(dbid);
      
      formBean.setFeetypeid(req.getParameter("feetype"));
      
      String money = req.getParameter("money");
      formBean.setMoney(money);
      
      String startdegree = req.getParameter("startdegree");
      String stopdegree = req.getParameter("stopdegree");//新添加
      formBean.setStartdegree(startdegree);
      formBean.setStopdegree(stopdegree);
      String startdate = req.getParameter("startdate");
      formBean.setStartdate(startdate);
      String preenddate = req.getParameter("preenddate");
      formBean.setEnddate(preenddate);
      formBean.setDianfei(req.getParameter("dianfei"));
      formBean.setSumdegree(req.getParameter("sumdegree"));
      formBean.setNotetypeid(req.getParameter("notetypeid"));
      formBean.setNoteno(req.getParameter("noteno"));
      formBean.setNotetime(req.getParameter("notetime"));
      formBean.setKptime(req.getParameter("kptime")==null?"":req.getParameter("kptime"));
      formBean.setPayoperator(req.getParameter("payoperator"));
      formBean.setPaydatetime(req.getParameter("paydatetime"));
      String accountmonth = req.getParameter("accountmonth");
      formBean.setAccountmonth(accountmonth);
      formBean.setMemo(req.getParameter("memo"));
      formBean.setStartmonth(req.getParameter("startmonth"));
      formBean.setEndmonth(req.getParameter("endmonth"));
      formBean.setEntrypensonnel(req.getParameter("accountname"));
      
      formBean.setLastdf(req.getParameter("lastdf"));
      formBean.setLastdl(req.getParameter("lastdl"));
      formBean.setLastyue(req.getParameter("lastyue"));
      formBean.setLastlch(req.getParameter("lastlch"));
      
      String pjje=req.getParameter("pjje");
      double d=0;
      d=Double.parseDouble(pjje);
      formBean.setPjje(d);
      formBean.setEntrytime(entrytime);
      String actualdegree = req.getParameter("actualdegree");//实际用电量
      formBean.setActualdegree(actualdegree);

      formBean.setNetworkdf(req.getParameter("NETWORKDF"));
      formBean.setInformatizationdf(req.getParameter("INFORMATIZATIONDF"));
      formBean.setAdministrativedf(req.getParameter("ADMINISTRATIVEDF"));
      formBean.setMarketdf(req.getParameter("MARKETDF"));
      formBean.setBuilddf(req.getParameter("BUILDDF"));
      //DDDF 新增
      formBean.setDddf(req.getParameter("DDDF"));
      formBean.setBeilv(req.getParameter("beilv"));
      
      ElectricTool tool = new ElectricTool();
      long jszq1 = tool.getQuot(startdate, preenddate);
      long jszq = jszq1 + 1;
      formBean.setJszq(jszq);
      String qsdbdlbz = req.getParameter("qsdbdlbz");//超省标
      formBean.setQsdbdlbz(qsdbdlbz);
      String dedhdlbz = req.getParameter("dedhdlbz");//超市标(超额定)
      formBean.setDedhdlbz(dedhdlbz);
      String qsdbdl = req.getParameter("qsdbdl");
      String edhdl = req.getParameter("edhdl");
      String beilv = req.getParameter("beilv");
      String shicode = req.getParameter("shicode");
      String property = req.getParameter("property");
      String zlfh = req.getParameter("zlfh");
      String jlfh = req.getParameter("jlfh");
      String scb = req.getParameter("scb");
      String gdfs = req.getParameter("gdfs");
      String stationtype =req.getParameter("stationtype");
      String dfzflx = req.getParameter("dfzflxcode");
      String dbydl = String.valueOf((Format.str_d(stopdegree)-Format.str_d(startdegree))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
      formBean.setDbydl(dbydl);
      String bzw = "1";//标志位	预付费合同标志位    0-------导入     1------录入
      
      
      ElectricTool elecToo = new ElectricTool();
      if(elecToo.checkRepeat1(stopdegree, preenddate, startdegree, startdate, dbid, accountmonth)){
    	  msg="输入信息重复，请核实信息！";
	      }else{
	    	String zdshzt = ""; //自动审核状态标志:0不通过 ,1通过
	     	String zdshms = "";//自动审核不通过的描述信息
	     	
	     	String[] d1 = elecToo.checkElectric2(pjje);//票据金额是否为空
	     	String[] d2 = elecToo.checkDayDegree(dbid, preenddate, startdate, actualdegree);//本次交费日均耗电量大于最后一次交费的日均耗电量20%
	     	String[] d3 = elecToo.checkBcdl(actualdegree, preenddate, startdate, null,dbid, dedhdlbz, "1");//本次电量上下浮动超过站点额定耗电量计算值的20%
	     	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, preenddate, startdate, shicode, property, zlfh, jlfh, edhdl, scb, dbid,actualdegree,qsdbdl,stationtype);//2014-10-28超省标比例,合并周期,标准值
	     	String[] d4 = elecToo.checkBcdl2(cbbl[0]);//本次电量上下浮动超过全省定标的10%
	     	String[] d5 = elecToo.checkExceptAndHigh(dbid, money, actualdegree, preenddate, startdate, String.valueOf(Format.str_d(cbbl[0])/100));//是否异常及高额电费
	     	String[] d6 = elecToo.checckSite(dbid);//是否是1.2万个点
	     	
	    	if("1".equals(d1[0])&&"1".equals(d2[0])&&"1".equals(d3[0])&&"1".equals(d4[0])){
	    		zdshzt = "1";
	    	}else{
	    		zdshzt = "0";
	    	}
	     	
	     	String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
	     	String city = "1";//市级审核状态；审核标志
	     	String cityzr = "1";//市级主任审核状态；审核标志	

	     	if("1".equals(d5[0])){
	     		qxzr = "0";
	     		city = "0";
	     		cityzr = "0";
	     	}else if("0".equals(d5[0])){
	     		if("1".equals(d6[0])){
	     			city = "0";
	     			cityzr = "0";
	     		}else if("0".equals(d6[0])){
	     			if("0".equals(d3[0]) || "0".equals(d4[0])){
	     				city = "0";
	     			}
	     		}
	     	}
	     	
	     	zdshms = d1[1] + d2[1] + d3[1] + d4[1] + d5[1] + d6[1];
        	String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
        	String hbzq = cbbl[1];//合并周期
        	String bzz = cbbl[2];//标准值
        	formBean.setZlfh(zlfh);
        	formBean.setJlfh(jlfh);
        	formBean.setDfzflxcode(dfzflx);
        	formBean.setGdfscode(gdfs);
        	formBean.setStationtypecode(stationtype);
        	formBean.setPropertycode(property);
		    msg = bean.addPrepayment(formBean,bzw,zdshzt,zdshms,qxzr,city,cityzr,d3[5],qsdbdl,d3[3],qsdbdlcbbl,hbzq,bzz,scb);
		    log.write(msg, formBean.getId(), req.getRemoteAddr(), "添加预付费");
	     }
      
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }else if("getCsdb".equals(action)){
    	resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        String startdegree = req.getParameter("startdegree");
        String stopdegree = req.getParameter("stopdegree");
        String startdate = req.getParameter("startdate");
        String preenddate = req.getParameter("preenddate");
    	String dbid = req.getParameter("dbid");
    	String edhdl = req.getParameter("edhdl");
        String beilv = req.getParameter("beilv");
        String shicode = req.getParameter("shicode");
        String property = req.getParameter("property");
        String zlfh = req.getParameter("zlfh");
        String jlfh = req.getParameter("jlfh");
        String scb = req.getParameter("scb");
        String stationtype = req.getParameter("stationtype");
        String qsdbdl = req.getParameter("qsdbdl");
        String actualdegree = req.getParameter("actualdegree");
        String dbydl = String.valueOf((Format.str_d(stopdegree)-Format.str_d(startdegree))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
    	ElectricTool elecToo = new ElectricTool();
    	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, preenddate, startdate, shicode, property, zlfh, jlfh, edhdl, scb, dbid,actualdegree,qsdbdl,stationtype);//2014-10-28超省标比例,合并周期,标准值
    	PrintWriter out = resp.getWriter();
        out.print(Format.str2(cbbl[0])+"%");
        out.close();
    }
    else if (action.equals("del")) {
      String degreeid = req.getParameter("degreeid");

      msg = bean.delPrepayment(degreeid);
      log.write(msg, formBean.getId(), req.getRemoteAddr(), "删除预付费");
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("modify")) {
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	 String entrytime=df.format(new Date());
      String prepayId = req.getParameter("prepayId");
      
      System.out.println(prepayId);
      url = path + "/web/electricfees/prepaymentList.jsp?prepayId="+prepayId;
      String pj=req.getParameter("pjje");
      double d=0;
      d=Double.parseDouble(pj);
      
	  formBean = formBean.getPrepaymentInfo(prepayId);
	  formBean.setPjje(d);
	  formBean.setStationid(req.getParameter("stationid"));
	  String dbid = req.getParameter("dbid");
      formBean.setPrepaymentammeterid(dbid);
     
      formBean.setFeetypeid(req.getParameter("feetype"));
      String money = req.getParameter("money");
      formBean.setMoney(money);
      
      String startdegree = req.getParameter("startdegree");
      String stopdegree = req.getParameter("stopdegree");//新添加
      formBean.setStartdegree(startdegree);
      formBean.setStopdegree(stopdegree);
      
      String startdate = req.getParameter("startdate");
      formBean.setStartdate(startdate);
      String preenddate = req.getParameter("preenddate");
      formBean.setEnddate(preenddate);
     
      formBean.setNotetypeid(req.getParameter("notetypeid"));
      formBean.setNoteno(req.getParameter("noteno"));
      formBean.setNotetime(req.getParameter("notetime"));
      formBean.setKptime(req.getParameter("kptime")==null?"":req.getParameter("kptime"));
      formBean.setPayoperator(req.getParameter("payoperator"));
      formBean.setPaydatetime(req.getParameter("paydatetime"));
      String accountmonth = req.getParameter("accountmonth");
      formBean.setAccountmonth(accountmonth);
      formBean.setMemo(req.getParameter("memo"));
      formBean.setStartmonth(req.getParameter("startmonth"));
      formBean.setEndmonth(req.getParameter("endmonth"));
      formBean.setEntrypensonnel(req.getParameter("accountname"));
      formBean.setEntrytime(entrytime);
      formBean.setSumdegree(req.getParameter("sumdegree"));
      formBean.setDianfei(req.getParameter("danjia"));
      String actualdegree = req.getParameter("actualdegree");
      formBean.setActualdegree(actualdegree);//实际用电量
      formBean.setNetworkdf(req.getParameter("NETWORKDF"));
      formBean.setAdministrativedf(req.getParameter("ADMINISTRATIVEDF"));
      formBean.setMarketdf(req.getParameter("MARKETDF"));
      formBean.setInformatizationdf(req.getParameter("INFORMATIZATIONDF"));
      formBean.setBuilddf(req.getParameter("BUILDDF"));
      formBean.setDddf(req.getParameter("DDDF"));
      
      ElectricTool tool = new ElectricTool();
      long jszq1 = tool.getQuot(startdate, preenddate);
      long jszq = jszq1 + 1;
      formBean.setJszq(jszq);
      
      String qsdbdlbz = req.getParameter("qsdbdlbz");//超省标
      formBean.setQsdbdlbz(qsdbdlbz);
      String dedhdlbz = req.getParameter("dedhdlbz");//超市标(超额定)
      formBean.setDedhdlbz(dedhdlbz);
      String qsdbdl = req.getParameter("qsdbdl");
      String edhdl = req.getParameter("edhdl");
      String beilv = req.getParameter("beilv");
      String shicode = req.getParameter("shicode");
      String property = req.getParameter("property");
      String zlfh = req.getParameter("zlfh");
      String jlfh = req.getParameter("jlfh");
      String scb = req.getParameter("scb");
      String gdfs = req.getParameter("gdfscode");
      String stationtype =req.getParameter("stationtypecode");
      String dfzflx = req.getParameter("dfzflxcode");
      String dbydl = String.valueOf((Format.str_d(stopdegree)-Format.str_d(startdegree))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
      formBean.setDbydl(dbydl);
      
      ElectricTool elecToo = new ElectricTool();
     // if(elecToo.checkRepeat1(stopdegree, preenddate, startdegree, startdate, dbid, accountmonth)){
    	//  msg="输入信息重复，请核实信息！";
	     // }else{
	    	String zdshzt = ""; //自动审核状态标志:0不通过 ,1通过
	     	String zdshms = "";//自动审核不通过的描述信息
	     	
	     	String[] d1 = elecToo.checkElectric2(pj);//票据金额是否为空
	     	String[] d2 = elecToo.checkDayDegree(dbid, preenddate, startdate, actualdegree);//本次交费日均耗电量大于最后一次交费的日均耗电量20%
	     	String[] d3 = elecToo.checkBcdl(actualdegree, preenddate, startdate, null,dbid, dedhdlbz, "1");//本次电量上下浮动超过站点额定耗电量计算值的20%
	     	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, preenddate, startdate, shicode, property, zlfh, jlfh, edhdl, scb, dbid,actualdegree,qsdbdl,stationtype);//2014-10-28超省标比例,合并周期,标准值
	     	String[] d4 = elecToo.checkBcdl2(cbbl[0]);//本次电量上下浮动超过全省定标的10%
	     	String[] d5 = elecToo.checkExceptAndHigh(dbid, money, actualdegree, preenddate, startdate, String.valueOf(Format.str_d(cbbl[0])/100));//是否异常及高额电费
	     	String[] d6 = elecToo.checckSite(dbid);//是否是1.2万个点
	     	
	    	if("1".equals(d1[0])&&"1".equals(d2[0])&&"1".equals(d3[0])&&"1".equals(d4[0])){
	    		zdshzt = "1";
	    	}else{
	    		zdshzt = "0";
	    	}
	     	
	     	String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
	     	String city = "1";//市级审核状态；审核标志
	     	String cityzr = "1";//市级主任审核状态；审核标志	

	     	if("1".equals(d5[0])){
	     		qxzr = "0";
	     		city = "0";
	     		cityzr = "0";
	     	}else if("0".equals(d5[0])){
	     		//qxzr = "1";
	     		if("1".equals(d6[0])){
	     			city = "0";
	     			cityzr = "0";
	     		}else if("0".equals(d6[0])){
	     			//cityzr = "1";
	     			if("0".equals(d3[0]) || "0".equals(d4[0])){
	     				city = "0";
	     			}
	     		}
	     	}
	     	
	     	zdshms = d1[1] + d2[1] + d3[1] + d4[1] + d5[1] + d6[1];
        	String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
        	String hbzq = cbbl[1];//合并周期
        	String bzz = cbbl[2];//标准值
        	formBean.setZlfh(zlfh);
        	formBean.setJlfh(jlfh);
        	formBean.setDfzflxcode(dfzflx);
        	formBean.setGdfscode(gdfs);
        	formBean.setStationtypecode(stationtype);
        	formBean.setPropertycode(property);
		    msg = bean.modifyPrepayment(formBean,prepayId,zdshzt,zdshms,qxzr,city,cityzr,d3[5],qsdbdl,d3[3],qsdbdlcbbl,hbzq,bzz,scb);
		    
		    log.write(msg, formBean.getId(), req.getRemoteAddr(), "修改预付费");
	  //   }
      
      session.setAttribute("url", url);
      session.setAttribute("msg", msg);
      resp.sendRedirect(path + "/web/msg.jsp");

    }
    else if (action.equals("showall")) {
        String degreeid = req.getParameter("ammeterdegreeid");
        url = path + "/web/electricfees/addElectricFeesAll.jsp?degreeid="+degreeid;
  	  //formBean = formBean.getElectricFeesInfo(degreeid);

  	  formBean.setAmmeterdegreeidFk(req.getParameter("ammeterdegreeidFk"));
        

        //msg = bean.modifyElectricFees(formBean);
        //log.write(msg, formBean.getElectricfeeId(), req.getRemoteAddr(), "账户维护");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");

      }
    else if (action.equals("startdegree")) {
    	resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        String amid = req.getParameter("amid");
        String startdegree = bean.getLastAmmeterDegree(amid);
        out.print(startdegree);
        out.close();

      }
    else if (action.equals("preenddata")) {
    	resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();
        //String amid = req.getParameter("amid");
        formBean.setPrepaymentammeterid(req.getParameter("amid"));
        formBean.setMoney(req.getParameter("money"));
        formBean.setStartdate(req.getParameter("startdate"));
        System.out.println(formBean.getStartdate()+"****"+formBean.getPrepaymentammeterid()+"****"+formBean.getMoney()); 
        
        String startdegree = bean.getPreenddate(formBean);
        out.print(startdegree);
        out.close();

      }
  }

}
