package com.ptac.app.basisquery.electricdetail.action;
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
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDao;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDaoImp;
import com.ptac.app.inportuserzibaodian.util.Format;
/**
 * @see 电费缴纳明细.Servlet
 * @author ZengJin 2014-1-16
 * update LiJing
 * updateRemark 修改queryElectric()方法里面的参数个数，通过传参来增加SQL的过滤条件
 */
public class ElectricDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		//------获取前台按钮标识------
		String command = request.getParameter("command");
		//------获取前台限制条件------
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String cityaudit = request.getParameter("citystatus");//市级审核状态	
		String startentrytime = request.getParameter("entrytimeQS");//开始录入时间
		String endentrytime = request.getParameter("entrytimeJS");//结束录入时间
		String manualauditstatus = request.getParameter("manauditstatus");//人工审核状态,
		String autoauditstatus = request.getParameter("autoauditstatus");//自动审核状态
		String zdsx = request.getParameter("zdsx1");
		String stationtype = request.getParameter("zdlx1");//站点类型	
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		String jzname = request.getParameter("zdname");//站点名称
		String id = request.getParameter("zdid");//站点ID
		String entrytime = request.getParameter("entryTime1");//录入月份
		String entrypensonnel = request.getParameter("entrypensonnel");//录入人员	
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String dfzflx = request.getParameter("dfzflx");//电费支付类型
		String liuchenghao = request.getParameter("liuch");//流程号
		String blhdl = request.getParameter("blhdl1");//耗电量大于
		String actualpay = request.getParameter("dianfeidd");//电费大于
		String gdfs = request.getParameter("gdfs");//供电方式
		String kjyf = request.getParameter("KJYF");//财务月份(会计月份)
		String dbname = request.getParameter("dbname");//电表名称
		String countryheadstatus = request.getParameter("countryheadstatus");// 区县审核标志
		String sjzrsh = request.getParameter("sjzrsh");//市级主任审核状态
		String beginTime = request.getParameter("beginTime");//起始报账月份
		String endTime = request.getParameter("endTime");//结束报账月份
		//------根据获取的值拼接sql查询语句-----
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}
		if (cityaudit != null && !cityaudit.equals("")
				&& !cityaudit.equals("2")) {
			whereStr = whereStr + " AND E.CITYAUDIT='" + cityaudit + "'";
		}
		if (startentrytime != null && !startentrytime.equals("")
				&& !startentrytime.equals("0")) {
			startentrytime = startentrytime + " 00:00:00";
			whereStr = whereStr + " AND TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='" + startentrytime + "'";
		}
		if (endentrytime != null && !endentrytime.equals("")
				&& !endentrytime.equals("0")) {
			endentrytime = endentrytime + " 24:00:00";
			whereStr = whereStr + " AND TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='" + endentrytime + "'";
		}
		if (manualauditstatus != null && !manualauditstatus.equals("")
				&& !manualauditstatus.equals("3")) {
			if ("1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='"
						+ manualauditstatus + "'";
			}
			if ("2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("0".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("-2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("-1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2' ";
			}
		}
		if (autoauditstatus != null && !autoauditstatus.equals("")
				&& !autoauditstatus.equals("2")) {
			whereStr = whereStr + " AND E.AUTOAUDITSTATUS='" + autoauditstatus + "'";
		}
		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND ZD.PROPERTY IN ("+zdsx+") ";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
			//if (!("请选择").equals(stationtype)){
			whereStr = whereStr + " and ZD.STATIONTYPE IN(" + stationtype + ")";
			//}
		}
		
		
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {
			whereStr = whereStr + "AND D.DBQYZT='" + dbqyzt + "'";
		}
		if (jzname != null && !jzname.equals("") && !jzname.equals("0")) {
			whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
		}
		if (id != null && !id.equals("") && !id.equals("0")) {
			whereStr = whereStr + " AND ZD.ID='" + id + "'";
		}
		if (entrytime != null && entrytime != "" && !entrytime.equals("0")) {
			int months = 0;
			int years = 0;
			int month1 = Integer.parseInt(entrytime.substring(5, 7)) + 1;
			int year1 = Integer.parseInt(entrytime.substring(0, 4));
			if (month1 > 12) {
				years = year1 + 1;
				months = month1 - 12;
			} else {
				years = year1;
				months = month1;
			}
			String month = "";
			if (months < 10) {
				month = "0" + Integer.toString(months);
			} else {
				month = Integer.toString(months);
			}
			String year = Integer.toString(years);
			String entryTime2 = entrytime + "-01 00:00:00";
			String entryTime3 = year + "-" + month + "-01 00:00:00";
			whereStr = whereStr + " AND TO_CHAR(A.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='" + entryTime2 + "'"
					+ " AND TO_CHAR(A.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'" + entryTime3 + "'";
		}
		if (entrypensonnel != null && !entrypensonnel.equals("")
				&& !entrypensonnel.equals("0")) {
			whereStr = whereStr
					+ " AND (E.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"
					+ entrypensonnel + "'))";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + "AND ZD.QYZT='" + qyzt + "'";
		}
		if (dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")) {
			whereStr = whereStr + " AND D.DFZFLX='" + dfzflx + "'";
		}
		if (liuchenghao != null && liuchenghao != "" && !liuchenghao.equals("0")) {
			whereStr = whereStr + " AND E.LIUCHENGHAO = '" + liuchenghao + "'";
		}
		if (blhdl != null && !blhdl.equals("")) {
			whereStr = whereStr + " AND A.BLHDL >'" + blhdl + "'";
		}
		if (actualpay != null && !actualpay.equals("")) {
			whereStr = whereStr + " AND E.ACTUALPAY >'" + actualpay + "'";
		}
		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}
		if (kjyf != null && kjyf != "" && !kjyf.equals("0")) {
			whereStr = whereStr + " AND TO_CHAR(E.KJYF,'yyyy-mm') ='" + kjyf + "'";
		}
		if (dbname != null && !dbname.equals("") && !dbname.equals("0")) {
			whereStr = whereStr + " AND D.DBNAME LIKE '%" + dbname + "%'";
		}
		
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("5")) {
			whereStr = whereStr + " AND E.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}
		
		if (sjzrsh != null && !sjzrsh.equals("") && !sjzrsh.equals("-1")) {
			whereStr = whereStr + "AND E.CITYZRAUDITSTATUS='" + sjzrsh + "'";
		}
		if (beginTime != null && !beginTime.equals("") && !beginTime.equals("0")) {
			whereStr = whereStr + "AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')>='" + beginTime + "'";
		}
		if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
			whereStr = whereStr + "AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')<='" + endTime + "'";
		}
		//------此标志位判断小区是否为空(用来决定选用哪条sql语句)------
		String bzw = "";
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			bzw = "1";
		}
		//------获得结果集和结果条数------
		ElecDetailDao dao = new ElecDetailDaoImp();
		List<ElectricDetail> list = new ArrayList<ElectricDetail>();	
		list = dao.queryElectric(whereStr,loginId,bzw);//结果集
		int num = list.size();//结果条数
		//------获得电量合计和电费合计------
		ElectricDetail total = new ElectricDetail();
		String totalelectric = "0";
		String totalmoney = "0";
		double df = 0;
		for (int i = 0; i < list.size(); i++) {
			try {
				df = df + Double.parseDouble(list.get(i).getActualpay());
				//System.out.println("电费："+df);
			} catch (Exception e) {

			}
		}
		BigDecimal bd_df = new BigDecimal(df);//实际电费
		String sum_df = bd_df.setScale(2, BigDecimal.ROUND_HALF_UP).toString();//实际电费
		
		if(list!=null){
			total = dao.total(list);
			totalelectric = Double.toString(total.getTotalelectric());//电量合计	
	 		totalelectric = Format.str2(totalelectric);
		}
		
		
		//------向前台页面传值------
		System.out.println("电费合计："+sum_df);
		request.setAttribute("totalelectric", totalelectric);//电量合计
		request.setAttribute("totalmoney", sum_df);//电费合计
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集
		//------根据前台按钮标识判断提交方向
		if("chaxun".equals(command)){//传到查询页面
			request.getRequestDispatcher("/web/appJSP/basisquery/electricdetail/eldetail.jsp")
			.forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/basisquery/electricdetail/电费信息.jsp")
			.forward(request, response);
		}
	}
}
