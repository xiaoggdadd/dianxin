package com.ptac.app.statisticcollect.province.pricecriticism.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.pricecriticism.bean.PriceCriticismBean;
import com.ptac.app.statisticcollect.province.pricecriticism.dao.PriceCriticismDao;
import com.ptac.app.statisticcollect.province.pricecriticism.dao.PriceCriticismDaoImpl;
/**
 * 
 * @author zx 2015-03-23
 * @see ��۳���վ��ͨ��
 */

public class PriceCriticismServlet extends HttpServlet {
	
	//serialVersionUID���ã� ���л�ʱΪ�˱��ְ汾�ļ����ԣ����ڰ汾����ʱ�����л��Ա��ֶ����Ψһ��
	private static final long serialVersionUID = 4387357330372475854L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		String action = request.getParameter("action");
		if("price".equals(action)){
			String command = request.getParameter("command");
			String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";
			String dfshzt = request.getParameter("dfshzt")!=null?request.getParameter("dfshzt"):"";
			String cbbly = request.getParameter("cbbly")!=null?request.getParameter("cbbly"):"";
			String cbble = request.getParameter("cbble")!=null?request.getParameter("cbble"):"";
			String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"";
			String wherestr ="";
			if(bzyf!=null&&!bzyf.equals("")){
				wherestr = wherestr+" AND TO_CHAR(E.ACCOUNTMONTH,'YYYY-MM')='"+bzyf+"' ";
			}
			if(dfshzt!=null&&!dfshzt.equals("")){
				if(dfshzt.equals("2")){
				wherestr = wherestr+" AND E.MANUALAUDITSTATUS='2' ";//�������
				}else{
					wherestr = wherestr+" AND E.CITYZRAUDITSTATUS='1' ";//ҵ�����
				}
			}
			if(jzproperty!=null&&!jzproperty.equals("")&&!jzproperty.equals("0")){
					wherestr = wherestr+" AND Z.PROPERTY='"+jzproperty+"' ";
			}
			
			PriceCriticismDao dao = new PriceCriticismDaoImpl();
			List<PriceCriticismBean> list = dao.checkPrice(wherestr, cbbly, cbble);
			int num = list.size();
			String[] sum = dao.getSum(list);
			PriceCriticismBean sumbean = new PriceCriticismBean();
			
			sumbean.setBzzdshj(sum[0]);//����վ�����ϼ�
			sumbean.setCbyhj(sum[1]);//����20%վ�����ϼ�
			sumbean.setCbehj(sum[2]);//����50%վ�����ϼ�
			sumbean.setCbzbyhj(sum[3]);//����20%ռ��վ�����ϼ�
			sumbean.setCbzbehj(sum[4]);//����50%ռ��վ�����ϼ�
			
			request.setAttribute("num", num);
			request.setAttribute("sumbean", sumbean);
			request.setAttribute("list", list);
			request.setAttribute("cbbly", cbbly);
			request.setAttribute("cbble", cbble);
			
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/statisticscollect/province/pricecriticism/pricecriticism.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/web/appJSP/statisticscollect/province/pricecriticism/priceexport.jsp").forward(request, response);
			}
		}
	}
	
	

}
