package com.ptac.app.statisticcollect.province.elecfeesanalyse.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.elecfeesanalyse.bean.ElecFeesAnalyseBean;
import com.ptac.app.statisticcollect.province.elecfeesanalyse.dao.ElecfeesAnalyseDao;
import com.ptac.app.statisticcollect.province.elecfeesanalyse.dao.ElecfeesAnalyseDaoImpl;
import com.ptac.app.statisticcollect.util.YearYueUtil;

public class ElecfeesAnalyseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("command");
		System.out.println("action:"+action);
		String accountmonth = request.getParameter("beginbztime");//�����·�
		String property = request.getParameter("property");//վ������
		String status = request.getParameter("status");//���״̬
		String zzs = request.getParameter("zzs");//�Ƿ������ֵ˰
		
		YearYueUtil util = new YearYueUtil();
		//����ͬ��
		String[] lastsameyue = util.lastSameYue(accountmonth);
		String[] last1 = lastsameyue[0].split("-");
		String nian1 = last1[0];
		Integer yue1 = Integer.valueOf(last1[1]);
		//�ϸ���
		String lastyue = util.lastYue(accountmonth);
		String[] last2 = lastyue.split("-");
		String nian2 = last2[0];
		Integer yue2 = Integer.valueOf(last2[1]);
		//���굱��
		String[] bzyf = accountmonth.split("-");
		String nian3 = bzyf[0];
		Integer yue3 = Integer.valueOf(bzyf[1]);
		//����һ��
		String[] lastone = lastsameyue[1].split("-");
		String nian4 = lastone[0];
		Integer yue4 = Integer.valueOf(lastone[1]);
		//����һ��
		String oneyue = util.OneYue(accountmonth);
		//�ݹ����������
		//ȥ��ͬ�µ��ϸ���
		String zan1 = util.lastYue(lastsameyue[0]);
		//�����·��ϸ��µ��ϸ���
		String zan2 = util.lastYue(lastyue);
		//����1�µ��ϸ���
		String zan3 = util.lastYue(lastsameyue[1]);
		//����1�µ��ϸ���
		String zan4 = util.lastYue(oneyue);
		
		String[] yearyue = new String[9];
		yearyue[0] =  lastsameyue[0];//����ͬ��
		yearyue[1] =  lastyue;//�����·��ϸ���
		yearyue[2] = accountmonth;//�����·�
		yearyue[3] = lastsameyue[1];//����һ��
		yearyue[4] = oneyue;//����һ��
		yearyue[5] = zan1;//ȥ��ͬ�µ��ϸ���
		yearyue[6] = zan2;//�����·��ϸ��µ��ϸ���
		yearyue[7] = zan3;//����1�µ��ϸ���
		yearyue[8] = zan4;//����1�µ��ϸ���
		
		ElecfeesAnalyseDao dao = new ElecfeesAnalyseDaoImpl();
		List<ElecFeesAnalyseBean> list = new ArrayList<ElecFeesAnalyseBean>();
		list = dao.queryDetails(yearyue, property, status, zzs);
		int num = list.size();
		String[] total = new String[8];
		total = dao.total(list);
		
		request.setAttribute("list", list);
		request.setAttribute("num", num);
		request.setAttribute("total1", total[0]);
		request.setAttribute("total2", total[1]);
		request.setAttribute("total3", total[2]);
		request.setAttribute("total4", total[3]+"%");
		request.setAttribute("total5", total[4]+"%");
		request.setAttribute("total6", total[5]);
		request.setAttribute("total7", total[6]);
		request.setAttribute("total8", total[7]+"%");
		request.setAttribute("nian1", nian1);
		request.setAttribute("yue1", yue1);
		request.setAttribute("nian2", nian2);
		request.setAttribute("yue2", yue2);
		request.setAttribute("nian3", nian3);
		request.setAttribute("yue3", yue3);
		request.setAttribute("nian4", nian4);
		request.setAttribute("yue4", yue4);
		if("chaxun".equals(action)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/elecfeesanalyse/elecfeesanalyse.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/elecfeesanalyse/elecfeesanalyseExport.jsp").forward(request, response);
		}
	}

}
