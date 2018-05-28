package com.ptac.app.priceover.citycheck.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.citycheck.dao.CityCheckDao;
import com.ptac.app.priceover.citycheck.dao.CityCheckDaoImp;
import com.ptac.app.priceover.countysigncheck.dao.UpLoadUtil;

/**
 * @author lijing
 * @see 单价超标市审核
 */
public class CityCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		String path = request.getContextPath();
		String command = request.getParameter("command");
		
		if("chaxun".equals(command) || "daochu".equals(command)){
			//市审核查询、导出
			String shi = request.getParameter("shi");//市
			String xian = request.getParameter("xian");//区县	
			String property = request.getParameter("property");//站点属性
			String state = request.getParameter("state");//市审核
			String accountmonth = request.getParameter("accountmonth");//报账月份
			String beginbl = request.getParameter("beginbl");//比例
			String endbl = request.getParameter("endbl");//比例
			String zdmc = request.getParameter("zdmc");//站点名称
			String zdid = request.getParameter("zdid");//站点ID	
			String gdfs = request.getParameter("gdfs");//供电方式
			String shengzt = request.getParameter("shengzt");//省审核
			
			StringBuffer whereStr = new StringBuffer();//查询条件
			if (shi != null && !shi.equals("") && !shi.equals("0")){
				 whereStr.append(" and shi='" + shi + "'");
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr.append(" and xian='" + xian + "'"); 
			}
			if (property != null && !property.equals("") && !property.equals("0")){
				whereStr.append(" and PROPERTY='" + property + "'");
			}
			if (state != null && !state.equals("") && !state.equals("-1")){
				whereStr.append(" and CITYAUDIT='" + state + "'");
			}
			if (accountmonth != null && !accountmonth.equals("") && !accountmonth.equals("0")){
				whereStr.append(" and to_char(ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'");
			}
			if (zdmc != null && !zdmc.equals("")){
				whereStr.append(" and jzname  like '%" + zdmc + "%'");
			}
			if (zdid != null && !zdid.equals("")){
				whereStr.append(" and ZDID='" + zdid + "'");	
			}
			if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")){
				whereStr.append(" and GDFS='" + gdfs + "'");	
			}
			if (shengzt != null && !shengzt.equals("") && !shengzt.equals("-1")){
				whereStr.append(" and PROVINCEAUDIT='" + shengzt + "'");
			}
			
			CityCheckDao dao = new CityCheckDaoImp();
			List<CityCheckBean> list = dao.queryExport(whereStr.toString(),loginId,beginbl,endbl);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/citycheck/citycheck.jsp").forward(request, response);
			}else if("daochu".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/citycheck/citycheckExport.jsp").forward(request, response);
			}
		}else if("info".equals(command)){
			//市审核详细信息
			String id = request.getParameter("id");
			String bzyf = request.getParameter("bzyf");
			CityCheckDao dao = new CityCheckDaoImp();
			UpLoadUtil dao1 = new UpLoadUtil();
			CityCheckBean bean = dao.getInfo(id,bzyf);
			boolean f1 = dao1.CheckFj("YDHTFJ", "PRICEPROCEDURE", "ID", id);
			boolean f2 = dao1.CheckFj("MSZYYJ", "PRICEPROCEDURE", "ID", id);
			boolean f3 = dao1.CheckFj("YZJFDLMX", "PRICEPROCEDURE", "ID", id);
			request.setAttribute("bean", bean);
			request.setAttribute("f1", f1);
			request.setAttribute("f2", f2);
			request.setAttribute("f3", f3);
			request.getRequestDispatcher("/web/appJSP/priceover/citycheck/info.jsp").forward(request, response);
		}else if("tuidan".equals(command)){
			//市审核退单
			String url = path+ "/web/appJSP/priceover/citycheck/citycheck.jsp",msg="";
			CityCheckDao dao = new CityCheckDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tdUpdate(id, loginName);
			if(rs==1){
				msg="退单成功!";
			}else if(rs==0){
				msg="退单失败!";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("tuidan1".equals(command)){ //ajax执行部分
			//市审核退单
			String url = path+ "/web/appJSP/priceover/citycheck/citycheck.jsp",msg="";
			PrintWriter out = response.getWriter();
			CityCheckDao dao = new CityCheckDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tdUpdate(id, loginName);
			  if(rs==0){
			    	msg="退单失败!";
		            session.setAttribute("url", url);
		            session.setAttribute("msg", msg);
		        }
		        out.print(rs);
		        out.flush();
		        out.close();
		}else if("tongguo".equals(command)){
			//市审核通过
			String url = path+ "/web/appJSP/priceover/citycheck/citycheck.jsp",msg="";
			CityCheckDao dao = new CityCheckDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tgUpdate(id, loginName);
			if(rs==1){
				msg="市审核通过!";
			}else if(rs==0){
				msg="市审核失败!";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("tongguo1".equals(command)){//ajax执行部分
			//市审核通过
			String url = path+ "/web/appJSP/priceover/citycheck/citycheck.jsp",msg="";
			PrintWriter out = response.getWriter();
			CityCheckDao dao = new CityCheckDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tgUpdate(id, loginName);
		    if(rs==0){
		    	msg="市审核失败!";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }
	        out.print(rs);
	        out.flush();
	        out.close();
		}
	}

}
