package com.noki.function;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;

public class NewHtdServlet extends HttpServlet {

	private static final String Content_type = "text/html;charset=UTF-8";
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 resp.setContentType(Content_type);
		    String path = req.getContextPath();
		    DbLog log = new DbLog();
		    
		    Account account = new Account();
		    HtdMange formBean= new HtdMange();
		    String url = path + "/web/newgn/hetong/newht.jsp";
		    String msg = "";
		    HttpSession session = req.getSession();
		    account = (Account) session.getAttribute("account");
                double  sm=0;
                String ssm="";
                DecimalFormat w =new DecimalFormat("0.00");   
		    HtdMange bean = new HtdMange();
		    String action = req.getParameter("action");
		    if(action.equals("modify")){
		    	  String id = req.getParameter("id");  
		    	  String ida=req.getParameter("uuid");
                   String fplx=req.getParameter("fplx");
                   String fpje=req.getParameter("fpje");
                   String fp=req.getParameter("fp");
                   sm=(Double.parseDouble(fpje)+Double.parseDouble(fp));
                   ssm=w.format(sm);
                   String stationid=req.getParameter("zdid");
                   String dbid=req.getParameter("dbid");
                   formBean.setId(Integer.parseInt(id));
                   formBean.setUuid(ida);
                   formBean.setStationid(stationid);
                   formBean.setPrepayment_ammeterid(dbid);
                   formBean.setEntrypensonnel(req.getParameter("accountname"));
                   formBean.setFpje(ssm);
                   formBean.setFplx(fplx);
		    	  
//		    	  formBean.setPrepayment_ammeterid(req.getParameter("ammeteridFk"));
//		    	  formBean.setDanjia(req.getParameter("danjia"));
//		          formBean.setNotetypeid(req.getParameter("notetypeid"));
//		          formBean.setNotetime(req.getParameter("notetime"));
//		          formBean.setNoteno(req.getParameter("noteno"));
//		          formBean.setStationid(req.getParameter("zdid"));
//		          formBean.setAccountmonth(req.getParameter("accountmonth"));
//		          formBean.setMemo(req.getParameter("memo"));
//		          formBean.setKptime(req.getParameter("kptime"));
//		          formBean.setEndmonth(req.getParameter("endmonth"));
//		          formBean.setStartmonth(req.getParameter("startmonth"));
//		          formBean.setMemo(req.getParameter("memo"));
//		          formBean.setMoney(req.getParameter("money"));
//		          formBean.setHtbianhao(req.getParameter("htbianhao"));
//		          String pj=req.getParameter("pjje");
//		          if("".equals(pj)||null==pj||"null".equals(pj)){
//		        	  pj="0";
//		          }
//		          formBean.setPjje(Double.parseDouble(pj));
		          //formBean.setAmmeterdegreeid_fk(req.getParameter("ammeterdegreeidFk"));
		          
		       
			      
//		          //获取分摊后算的金额（网络运营线电费(生产)、信息化支撑线电费、行政综合线电费（办公）、市场营销线电费(营业)、建设投资线电费）
//		          formBean.setNetworkdf(req.getParameter("networkdf"));
//		          formBean.setInformatizationdf(req.getParameter("informatizationdf"));
//		          formBean.setAdministrativedf(req.getParameter("administrativedf"));
//		          formBean.setMarketdf(req.getParameter("marketdf"));
//		          formBean.setBuilddf(req.getParameter("builddf"));


//		          List ammeterdegreeid=new ArrayList();
//		          String start=req.getParameter("startmonth");msg, accountid, req.getRemoteAddr()
//			      String end=req.getParameter("endmonth");
		          msg = formBean.modifyBargain(formBean,id);
		          
		          log.write(msg,dbid,req.getRemoteAddr(),"合同单补录发票");
		          
		          session.setAttribute("url", url);
		          session.setAttribute("msg", msg);
		          resp.sendRedirect(path + "/web/msg.jsp");

		    	
		    	
		    }
		
	}

}
