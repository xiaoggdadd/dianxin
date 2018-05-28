package com.ptac.app.checksign.financecheck.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.ptac.app.checksign.financecheck.bean.FinanceSelectBean;
import com.ptac.app.checksign.financecheck.dao.FinanceSelectDAO;
import com.ptac.app.checksign.financecheck.dao.FinanceSelectDAOImp;
/**
 * 数据查询的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class FinanceSelectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	/*根据过滤条件查询财务电费信息的serlvet方法*/
	/**
	 * @author rock
	 * @see financecheck.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginId = request.getParameter("loginId") != null ? request
				.getParameter("loginId") : "";
		String shi = request.getParameter("shi") != null ? request
				.getParameter("shi") : "";
		String xian = request.getParameter("xian") != null ? request
				.getParameter("xian") : "";
		String xiaoqu = request.getParameter("xiaoqu") != null ? request
				.getParameter("xiaoqu") : "";
		String zdmc = request.getParameter("zdmc") != null ? request
				.getParameter("zdmc") : "";
		String zdlx = request.getParameter("zdlx") != null ? request
				.getParameter("zdlx") : "";
		String zdsx = request.getParameter("zdsx") != null ? request
				.getParameter("zdsx") : "";
		String lch = request.getParameter("lch") != null ? request
				.getParameter("lch") : "";
		String bzyf = request.getParameter("bzyf") != null ? request
				.getParameter("bzyf") : "";
		String lrrq = request.getParameter("lrrq") != null ? request
				.getParameter("lrrq") : "";
		String zdqyzt = request.getParameter("zdqyzt") != null ? request
				.getParameter("zdqyzt") : "";
		String dbqyzt = request.getParameter("dbqyzt") != null ? request
				.getParameter("dbqyzt") : "";
		String cwshzt = request.getParameter("cwshzt") != null ? request
				.getParameter("cwshzt") : "";
		String pjlx = request.getParameter("pjlx") != null ? request
				.getParameter("pjlx") : "";

		StringBuffer whereStr = new StringBuffer();// 定义导出时的查询语句并放入Atruibute
		StringBuffer str1 = new StringBuffer();// 定义查询语句
		StringBuffer str2 = new StringBuffer();// 定义查询语句
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr.append(" and zd.shi='");  
			whereStr.append(shi);
			whereStr.append("' ");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr.append(" and zd.xian='");
			whereStr.append(xian);
			whereStr.append( "' ");
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			
			whereStr.append(" and zd.xiaoqu='" );
			whereStr.append(xiaoqu);
			whereStr.append( "' ");
		}
		if (bzyf != null && bzyf != "" && !bzyf.equals("0")) {
			
			whereStr.append(" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='");
			whereStr.append(bzyf);
			whereStr.append( "' ");
		}
//		if (zdmc != null && !zdmc.equals("") && !zdmc.equals("0")) {
//			
//			whereStr.append( " and zd.jzname like '%" );
//			whereStr.append(zdmc);
//			whereStr.append( "%' ");
//		}
		if (lch != null && lch != "" && !lch.equals("0")) {
			
			whereStr.append(" and ef.liuchenghao='");
			whereStr.append(lch);
			whereStr.append( "' ");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			
			whereStr.append(" and zd.STATIONTYPE='");
			whereStr.append(zdlx);
			whereStr.append( "' ");
		}
		if (zdsx != null && !zdsx.equals("")
				&& !zdsx.equals("0")) {
			
			whereStr.append(" and zd.PROPERTY='" );
			whereStr.append(zdsx);
			whereStr.append("' ");
		}
//		if (lrrq != null && lrrq != "" && !lrrq.equals("0")) {
//		
//			whereStr.append(" and to_char(ef.entrytime,'yyyy-mm-dd') like '%");
//			whereStr.append(lrrq);
//			whereStr.append("%' ");
//		}
		
    	if(cwshzt != null && !cwshzt.equals("") && !cwshzt.equals("-2")){
			
			str1.append(" AND EF.MANUALAUDITSTATUS = '");
			str1.append(cwshzt);
			str1.append("' ");
			str2.append( "AND EF.FINANCEAUDIT='");
			str2.append(cwshzt);
			str2.append("' ");
			
		}
    	if(zdqyzt!=null && !zdqyzt.equals("") &&!zdqyzt.equals("-1")){
    		
    		str1.append("AND ZD.QYZT='");
			str1.append(zdqyzt);
			str1.append("' ");
			str2.append("AND ZD.QYZT='");
			str2.append(zdqyzt);
			str2.append("' ");
    	}
    	if(dbqyzt!=null && !dbqyzt.equals("") &&!dbqyzt.equals("-1")){
    		
    		str1.append("AND D.DBQYZT='");
			str1.append(dbqyzt);
			str1.append("' ");
			str2.append("AND D.DBQYZT='");
			str2.append(dbqyzt);
			str2.append("' ");
    	}
    	if(pjlx!=null &&!pjlx.equals("") &&!pjlx.equals(" ")){
    		
    		str1.append("AND EF.NOTETYPEID='");
			str1.append(pjlx);
			str1.append("' ");
			str2.append("AND EF.NOTETYPEID='");
			str2.append(pjlx);
			str2.append("' ");
    	}
		request.setAttribute("str", whereStr.toString());
		request.setAttribute("str1", str1.toString());
		request.setAttribute("str2", str2.toString());
		

		// 实例化查询bean
		FinanceSelectBean fsb = new FinanceSelectBean();
		fsb.setLoginId(loginId);
		fsb.setShi(shi);
		fsb.setXian(xian);
		fsb.setXiaoqu(xiaoqu);
		fsb.setZdmc(zdmc);
		fsb.setZdlx(zdlx);
		fsb.setZdsx(zdsx);
		fsb.setLch(lch);
		fsb.setBzyf(bzyf);
		fsb.setLrrq(lrrq);
		fsb.setZdqyzt(zdqyzt);
		fsb.setDbqyzt(dbqyzt);
		fsb.setCwshzt(cwshzt);
		fsb.setPjlx(pjlx);
		
		/* 进入DAO层 */
		FinanceSelectDAO fsd = new FinanceSelectDAOImp();
		// 查询数据
		List<ElectricFeesFormBean> ls = fsd.MessageSelect(fsb);

		/* 计算总电量和总电费 */
		double dl = 0;
		double df = 0;

		//通过循环计算电费和电量和
		for (int i = 0; i < ls.size(); i++) {
			try {
				dl = dl + Double.parseDouble(ls.get(i).getBlhdl());
			} catch (Exception e) {

			}
			try {
				df = df + Double.parseDouble(ls.get(i).getActualpay());
			} catch (Exception e) {

			}

		}
		BigDecimal bd_dl = new BigDecimal(dl);
		BigDecimal bd_df = new BigDecimal(df);

		String sum_dl = bd_dl.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String sum_df = bd_df.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		request.setAttribute("sumdl", sum_dl);
		request.setAttribute("sumdf", sum_df);

		
		/*判断长度*/
		try{
			if(ls.size()>0){
				request.setAttribute("Mess", ls);
			}
		}catch(Exception e){
			
		}
		
		
		request.setAttribute("user", fsb);
		request.getRequestDispatcher(
				"/web/appJSP/checksign/financecheck/financecheck.jsp").forward(
				request, response);

	}

}
