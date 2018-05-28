package com.ptac.app.basisquery.contractbill.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.basisquery.contractbill.bean.ContractBill;
import com.ptac.app.basisquery.contractbill.dao.ContractBillDao;
import com.ptac.app.basisquery.contractbill.dao.ContractBillDaoImpl;

/**
 * @author lijing
 * @see 合同单查询及导出
 */
public class ContractBillServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		String command = request.getParameter("command");
		
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String zdsx = request.getParameter("jzproperty");
		String stationtype = request.getParameter("zdlx");//站点类型	
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		String jzname = request.getParameter("zdname");//站点名称
		String id = request.getParameter("zdid");//站点ID
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String dbname = request.getParameter("dbname");//电表名称
		String manauditstatus = request.getParameter("manauditstatus");//人工审核标志
		String countryheadstatus = request.getParameter("countryheadstatus");// 区县审核标志
		String cityaudit = request.getParameter("citystatus");//市级审核状态	
		String sjzrsh = request.getParameter("sjzrsh");//市级主任审核状态
		String financialstatus = request.getParameter("financialstatus");//财务审核标志
		String ftyf = request.getParameter("ftyf");//分摊月份
		
		//------根据获取的值拼接sql查询语句-----
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND Z.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND Z.XIAN='" + xian + "'";
		}
		if (cityaudit != null && !cityaudit.equals("")
				&& !cityaudit.equals("2")) {
			whereStr = whereStr + " AND P.CITYAUDIT='" + cityaudit + "'";
		}

		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND Z.PROPERTY='"+zdsx+"' ";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
			if (!("请选择").equals(stationtype)){
			whereStr = whereStr + " and Z.STATIONTYPE IN('" + stationtype + "')";
			}
		}
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {
			whereStr = whereStr + "AND D.DBQYZT='" + dbqyzt + "'";
		}
		if (jzname != null && !jzname.equals("") && !jzname.equals("0")) {
			whereStr = whereStr + " AND Z.JZNAME LIKE '%" + jzname + "%'";
		}
		if (id != null && !id.equals("") && !id.equals("0")) {
			whereStr = whereStr + " AND Z.ID='" + id + "'";
		}

		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + "AND Z.QYZT='" + qyzt + "'";
		}

		if (dbname != null && !dbname.equals("") && !dbname.equals("0")) {
			whereStr = whereStr + " AND D.DBNAME LIKE '%" + dbname + "%'";
		}
		
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("5")) {
			whereStr = whereStr + " AND P.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}
		
		if (sjzrsh != null && !sjzrsh.equals("") && !sjzrsh.equals("-1")) {
			whereStr = whereStr + "AND P.CITYZRAUDITSTATUS='" + sjzrsh + "'";
		}

		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND Z.XIAOQU='" + xiaoqu + "'";
		}
		if (manauditstatus != null && !manauditstatus.equals("") && !manauditstatus.equals("3")) {
			whereStr = whereStr + "AND P.MANUALAUDITSTATUS='" + manauditstatus + "'";
		}
		if (financialstatus != null && !financialstatus.equals("") && !financialstatus.equals("3")) {
			whereStr = whereStr + "AND P.FINANCEAUDIT='" + financialstatus + "'";
		}
		
		if (ftyf != null && ftyf != "" && !ftyf.equals("0")) {
			whereStr = whereStr + " AND TO_CHAR(P.STARTMONTH,'yyyy-mm') ='" + ftyf + "'";
		}
		
		//------获得结果集和结果条数------
		ContractBillDao dao = new ContractBillDaoImpl();
		List<ContractBill> list = new ArrayList<ContractBill>();	
		list = dao.getConBill(whereStr, loginId);
		int num = list.size();//结果条数
		
		//------获得电费合计------
		double totalMoney = 0.00;
		for(int i=0;i<num;i++){
			totalMoney = totalMoney + Double.parseDouble(list.get(i).getMoney());
		}
		BigDecimal totalMoney1 = new BigDecimal(totalMoney);
		String totalMoney2 = totalMoney1.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		
		//------获得电量合计------
		double totalDl = 0.00;
		for(int i=0;i<num;i++){
			totalDl = totalDl + Double.parseDouble(list.get(i).getDianliang());
		}
		BigDecimal totalDl1 = new BigDecimal(totalDl);
		String totalDl2 = totalDl1.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		
		request.setAttribute("totalelectric", totalDl2);//电量合计
		request.setAttribute("totalmoney", totalMoney2);//电费合计
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集
		//------根据前台按钮标识判断提交方向
		if("chaxun".equals(command)){//传到查询页面
			request.getRequestDispatcher("/web/appJSP/basisquery/contractbill/contractbill.jsp")
			.forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/basisquery/contractbill/合同单导出.jsp")
			.forward(request, response);
		}
		
		
	}

}
