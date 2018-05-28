package com.ptac.app.print.printpaperbill.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDao;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDaoImp;
import com.ptac.app.print.printpaperbill.bean.QueryPaperBill;
import com.ptac.app.print.printpaperbill.dao.QueryPaperBillDao;
import com.ptac.app.print.printpaperbill.dao.QueryPaperBillDaoImp;

public class QueryPaperBillServlet extends HttpServlet{
	private static final long serialVersionUID = 801403741735251611L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QueryPaperBillDao dao = new QueryPaperBillDaoImp();
		List<QueryPaperBill> list = new ArrayList<QueryPaperBill>();	
		
		String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
		String command = request.getParameter("command")!=null?request.getParameter("command"):"";

		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";
		
		String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";
		String stationtype = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"";
		String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";
		String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"";
		
		String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
		String zdsx =request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"";
		String lururen =request.getParameter("lururen")!=null?request.getParameter("lururen"):"";
		String PJLX =request.getParameter("pjlx")!=null?request.getParameter("pjlx"):"";
		
		if(liuch != null && liuch != "" && !liuch.equals("0")){
			String whereStr = "",str="";
			
			whereStr=whereStr+" AND DY.LIUCHENGHAO='"+liuch+"'";
			str=str+" AND DY.LIUCHENGHAO='"+liuch+"'";
			whereStr=whereStr+" AND DY.MANUALAUDITSTATUS = '2'";
		    str=str+" AND DY.FINANCEAUDIT='2'";
		    
		    if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
				str=str+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
				str=str+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
				str=str+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(bztime != null && bztime != "" && !bztime.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm')='"+bztime+"'";
				str=str+" AND TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm')='"+bztime+"'";
			}
			if(dfzflx != null && dfzflx != "" && !dfzflx.equals("0")){
				whereStr=whereStr+" AND AM.DFZFLX='"+dfzflx+"'";
				str=str+" AND AM.DFZFLX='"+dfzflx+"'";
			}
			if(zdsx != null && zdsx != "" && !"0".equals(zdsx)){
			    whereStr=whereStr+" AND ZD.PROPERTY='"+zdsx+"'";
			    str=str+" AND ZD.PROPERTY='"+zdsx+"'";
			}
			if(zdmc !=null && zdmc != "" && !"0".equals(zdmc)){
			    whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdmc+"%'";
			    str=str+" AND ZD.JZNAME LIKE '%"+zdmc+"%'";
			}
			if(lururen!=null&&lururen!=""&&!"0".equals(lururen)){
			 whereStr=whereStr+"  AND DY.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+lururen+"')";
			 str=str+"  AND DY.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+lururen+"')";
			}
			if(stationtype != null && stationtype != "" && !stationtype.equals("0")){
				whereStr=whereStr+" AND ZD.STATIONTYPE='"+stationtype+"'";
				str=str+" AND ZD.STATIONTYPE='"+stationtype+"'";
			}
			
			if(PJLX != null && PJLX != "" && !PJLX.equals("0")){
				whereStr=whereStr+" AND DY.NOTETYPEID='"+PJLX+"'";
				str=str+" AND DY.NOTETYPEID='"+PJLX+"'";
			}

			//------获得结果集和结果条数------
			list = dao.queryData(whereStr,str,loginId);//结果集
		}
          
		request.setAttribute("list", list);//结果集
		//------根据前台按钮标识判断提交方向
		if("chaxun".equals(command)){//传到查询页面
			request.getRequestDispatcher("/web/appJSP/print/printpaperbill/querypaperbill.jsp")
			.forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/print/printpaperbill/exportpaperbill.jsp")
			.forward(request, response);
		}
	}
}
