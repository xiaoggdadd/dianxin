package com.ptac.app.statisticcollect.province.estimatecontrast.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.estimatecontrast.bean.EstimateContrastBean;
import com.ptac.app.statisticcollect.province.estimatecontrast.dao.EstimateContrastDao;
import com.ptac.app.statisticcollect.province.estimatecontrast.dao.EstimateContrastDaoImpl;

/**确定后暂估数据对照查询
 * @author WangYiXiao 2014-4-29
 *
 */
public class EstimateContrastServlet extends HttpServlet {

	private static final long serialVersionUID = 1512259845623956352L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		//-----根据command不通选择不通处理方法------
		if("chaxun".equals(command) || "daochu".equals(command)){//查询，导出
			queryexport(request,response);
		}
	}
	
	/**查询，导出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//------获取前台操作标识------
		String command = request.getParameter("command");
		String zangumonth = request.getParameter("zangumonth");
		
		EstimateContrastDao ecdao = new EstimateContrastDaoImpl();
		List<EstimateContrastBean> list = new ArrayList<EstimateContrastBean>();
		list = ecdao.query(zangumonth);
		int num = list.size();
		
		request.setAttribute("num", num);
		request.setAttribute("zangumonth", zangumonth);
		request.setAttribute("list", list);

		//------根据前台按钮标识判断提交方向
		if("chaxun".equals(command)){//传到查询页面 
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/estimatecontrast/EstimateContrast.jsp").forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/estimatecontrast/确定后暂估数据对照导出.jsp").forward(request, response);
		}
		
	}

}
