package com.ptac.app.relationquery.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.relationquery.bean.RelationQueryBean;
import com.ptac.app.relationquery.dao.TownRelationDao;
import com.ptac.app.relationquery.dao.TownRelationDaoImp;


/**
 * @author lijing
 * @see 外租站点与主站点关联市级查询
 */
public class TownRelationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");//操作
		if("chaxun".equals(command) || "daochu".equals(command)){
			getQuery(request,response);
		}else if("xiangqing".equals(command)){
			xiangQing(request,response);
		}else if("daochuxx".equals(command)){
			export(request,response);
		}
		
	}

	private void getQuery(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String command = request.getParameter("command");
		String shi = request.getParameter("shi");//市
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND SHI='" + shi + "'";
		}
		
		TownRelationDao dao = new TownRelationDaoImp();
		List<RelationQueryBean> list = dao.getTownRelation(whereStr,loginId);
		int num = list.size();

		request.setAttribute("num", num);
		request.setAttribute("list", list);
		
		if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/relationquery/townRelationQuery.jsp").forward(request, response);
		}else if("daochu".equals(command)){
				request.getRequestDispatcher("/web/appJSP/relationquery/外租站点与主站点市级查询导出.jsp").forward(request, response);
		}
	}
	
	private void xiangQing(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException{
		
		String xian = request.getParameter("xian");
		String bzw = request.getParameter("bzw");
		
		TownRelationDao dao = new TownRelationDaoImp();
		List<RelationQueryBean> list = null;
		if("1".equals(bzw)){
			list = dao.getXiangQing1(xian);
		}else if("2".equals(bzw)){
			list = dao.getXiangQing2(xian);
		}
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("xian", xian);
		request.setAttribute("bzw", bzw);
		
//		String command = request.getParameter("command");
//		if("xiangqing".equals(command)){
			request.getRequestDispatcher("/web/appJSP/relationquery/xiangqing.jsp").forward(request, response);
//		}else if("daochuxx".equals(command)){
//			request.getRequestDispatcher("/web/appJSP/relationquery/exportXiangQing.jsp").forward(request, response);
//		}
	}
	

	private void export(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{

		String xian = request.getParameter("xian");
		String bzw = request.getParameter("bzw");
		
		TownRelationDao dao = new TownRelationDaoImp();
		List<RelationQueryBean> list = null;
		if("1".equals(bzw)){
			list = dao.getXiangQing1(xian);
		}else if("2".equals(bzw)){
			list = dao.getXiangQing2(xian);
		}
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);

		request.getRequestDispatcher("/web/appJSP/relationquery/exportXiangQing.jsp").forward(request, response);
	}

}
