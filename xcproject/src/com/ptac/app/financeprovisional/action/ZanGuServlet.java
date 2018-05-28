package com.ptac.app.financeprovisional.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDaoImp;
import com.ptac.app.financeprovisional.bean.FinanceZanGuBean;
import com.ptac.app.financeprovisional.dao.ZanGuDao;
import com.ptac.app.financeprovisional.dao.ZanGuDaoImp;
import com.ptac.app.inportuserzibaodian.util.Format;


/** 
 * @author zhouxue
 * @see暂估分析   暂估数据处理并保存
 */
public class ZanGuServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;
	
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request,response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();
			String action = request.getParameter("action")!=null?request.getParameter("action"):"";//获取要做的操作
			Account account = (Account)session.getAttribute("account");
			String loginId = account.getAccountId();	        
			if("zangu".equals(action)){
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
				String zgsj= request.getParameter("zgsj")!=null?request.getParameter("zgsj"):"";//暂估结束时间
				String zgmonth= zgsj.substring(0,7);//暂估月份
				
		 	      String whereStr="";//
		 	      ZanGuDao zgdao = new ZanGuDaoImp();
		 	      String zangushuju ="0";
					if(shi != null && !shi.equals("") && !shi.equals("0")){
						whereStr=whereStr+" and zd.shi='"+shi+"'";
					}
					zangushuju=zgdao.zanGuShuJu(shi, zgsj, zgmonth, whereStr, loginId);
					request.setAttribute("zangushuju", zangushuju);
					request.getRequestDispatcher("/web/appJSP/financeprovisional/ZanGu.jsp?zangushuju="+zangushuju+"").forward(request, response);
			}else if("zanguchaxun".equals(action)){
				String chaxun = request.getParameter("chaxun")!=null?request.getParameter("chaxun"):"";//
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
				String zgsj= request.getParameter("zgsj")!=null?request.getParameter("zgsj"):"";//暂估结束时间
				String zgmonth= request.getParameter("zgmonth")!=null?request.getParameter("zgmonth"):"";//暂估月份
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
				String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
				String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
				String dfzflxx1 = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
				String jzproperty= request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
				String whereStr="";//
		 	      ZanGuDao zgdao = new ZanGuDaoImp();
		 	    
					if(shi != null && !shi.equals("") && !shi.equals("0")){
						whereStr=whereStr+" AND Z.SHI='"+shi+"'";
					}
					if(xian != null && !xian.equals("") && !xian.equals("0")){
						whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
					}
					if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
						whereStr=whereStr+" AND Z.XIAOQU='"+xiaoqu+"'";
					}
					if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
						whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdname+"%'";
					}
					if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
						whereStr=whereStr+" AND Z.STATIONTYPE='"+stationtype1+"'";
					}
					if(dfzflxx1 != null && !dfzflxx1.equals("") && !dfzflxx1.equals("0")){
						whereStr=whereStr+" AND Z.DFZFLX = '"+dfzflxx1+"'";
					}
					
					if(jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
						whereStr=whereStr+" AND Z.PROPERTY='"+jzproperty+"'";
					}
					if(zgsj != null && !zgsj.equals("") && !zgsj.equals("0")){
						String zgyf=zgsj.substring(0, 7);//截取暂估结束时间  作为暂估月份
						whereStr=whereStr+" AND Z.ZGMONTH='"+zgyf+"'";
					}
					ArrayList<FinanceZanGuBean> list = new ArrayList<FinanceZanGuBean>();
					ArrayList<FinanceZanGuBean> listft = new ArrayList<FinanceZanGuBean>();
					FinanceZanGuBean total = new FinanceZanGuBean();
					String zgmoneyhj = "0";
					list=zgdao.zanGuChaXun(whereStr, loginId);
					int num = list.size();
					if(list!=null){
						
						total = zgdao.total(list);
						zgmoneyhj = Double.toString(total.getZgmoneyhj());//电量合计	
						zgmoneyhj = Format.str2(zgmoneyhj);
					}
					
					if("daochu".equals(chaxun)){
						listft=zgdao.zanGuFenTan(whereStr, loginId);
						
					}
					
					request.setAttribute("num", num);//结果条数
					request.setAttribute("list", list);//查询结果集
					request.setAttribute("zgmoneyhj", zgmoneyhj);//暂估金额合计
					request.setAttribute("listft", listft);//查询分摊结果集
					if("chaxun".equals(chaxun)){
						request.getRequestDispatcher("/web/appJSP/financeprovisional/ZanGu.jsp").forward(request, response);
					}else if("daochu".equals(chaxun)){
						request.getRequestDispatcher("/web/appJSP/financeprovisional/暂估信息导出.jsp").forward(request, response);

					}
				
				
				
			}
		}
}
