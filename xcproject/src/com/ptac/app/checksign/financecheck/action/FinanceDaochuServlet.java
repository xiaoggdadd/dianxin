package com.ptac.app.checksign.financecheck.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.financecheck.dao.FinanceSelectDAO;
import com.ptac.app.checksign.financecheck.dao.FinanceSelectDAOImp;
/**
 * 电费导出的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class FinanceDaochuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
/*用来做导出的Servlet方法*/
	/**
	 * @author rock
	 * @see financeDaochu.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String whereStr = request.getParameter("whereStr");
		String str1 = request.getParameter("str1");
		String str2 = request.getParameter("str2");
		String lrrq = request.getParameter("lrrq");
		String zdmc = request.getParameter("zdmc");
		
		if(zdmc != null &&!zdmc.equals("")&& !zdmc.equals("0")&&!zdmc.equals("null")){
			whereStr=whereStr+" and zd.jzname like '%"+zdmc+"%'";
		}
		
        if(lrrq != null &&!lrrq.equals("")&&!lrrq.equals("null")&& !lrrq.equals("0")){
			whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
		} 
        
		Account account = new Account();
        account = (Account)request.getSession().getAttribute("account");
        String loginId = account.getAccountId();
		
        /*实例化接口*/
		FinanceSelectDAO fsd = new FinanceSelectDAOImp();
		List<ElectricFeesFormBean> ls = fsd.daochu(whereStr, loginId, str1, str2);
		
		/* 计算总电费 */
		double df = 0;

		//通过循环计算电费总和
		for (int i = 0; i < ls.size(); i++) {
			try {
				df = df + Double.parseDouble(ls.get(i).getActualpay());
			} catch (Exception e) {

			}

		}
		BigDecimal bd_df = new BigDecimal(df);

		String sum_df = bd_df.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		request.setAttribute("sumdf", sum_df);
		
		request.setAttribute("ls", ls);
		request.setAttribute("size", ls.size());
		request.getRequestDispatcher("/web/appJSP/checksign/financecheck/financeDaochu.jsp").forward(request, response);
		
	}

}
