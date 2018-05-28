package com.ptac.app.statisticcollect.province.unitpricePIC.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import com.ptac.app.statisticcollect.city.unitpricePIC.tool.ChartUtil;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProUnitpriceBean;

@SuppressWarnings("serial")
public class ProPicServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("------------------����PIC----------------");
		/*
		 * String loginID = request.getParameter("loginId");
		 * 
		 * String shi = request.getParameter("shi");//�� String bzyf1 =
		 * request.getParameter("bzyf1");//�����·ݣ�ǰ�� String bzyf2 =
		 * request.getParameter("bzyf2");//�����·ݣ��� String dfshzt =
		 * request.getParameter("dfshzt");//������״̬ String zdqyzt =
		 * request.getParameter("zdqtzt");//վ������״̬ String dbqyzt =
		 * request.getParameter("dbqyzt");//�������״̬
		 */
		// ProSelectBean bean = new ProSelectBean(shi, bzyf1, bzyf2, dfshzt,
		// zdqyzt, dbqyzt);

		List<ProUnitpriceBean> ls = (List) request.getSession().getAttribute(
				"list");
		List<String> mon_ls = (List) request.getSession().getAttribute(
		"mon");
		request.getSession().removeAttribute("list");
		request.getSession().removeAttribute("mon");
		
		if (ls == null) {
			ChartUtil cu = new ChartUtil();

			double[][] data = new double[][] {};
			String[] rowKeys = {};
			String[] columnKeys = {};
			CategoryDataset dataset = cu.getBarData(data, rowKeys, columnKeys);

			cu.createBarChart(dataset, "", "", "", response);
		} else {
			
			int l = ls.size();
			/*Ϊcity���鸳ֵ*/
			String [] city = new String[l];
			for(int i = 0;i<l;i++){
				city[i]=ls.get(i).getShi();
			}
			/*ΪMonth��ֵ*/
			String[] month = new String[mon_ls.size()];
			for(int i = 0;i<mon_ls.size();i++){
				month[i]=mon_ls.get(i);
			}
			/*Ϊdata��ֵ*/
			double[][]d = new double[mon_ls.size()][l];
			/**
			 * ����forѭ����
			 * �����Ϊ��ѭ����d��ֵ��
			 * ��������Ϊ��У��׼ȷ����
			 */
			for(int i = 0; i < mon_ls.size();i++){
				double[] bb = new double[l];
				for(int j = 0;j<l;j++){
					for(int s = 0; s < ls.get(j).getLs().size();s++){
						if(month[i].equals(ls.get(j).getLs().get(s).getBzyf())){
							bb[j] = Double.parseDouble(ls.get(j).getLs().get(s).getUnitprice());
							break;
						}else{
							bb[j]=0;
						}
						
					}
				}
				d[i]=bb;
			}
		
			/*��������*/
//			for(int i = 0;i<d.length;i++){
//				for(int j = 0;j<d[i].length;j++){
//					System.out.println("�أ�"+i+"�飺"+d[i][j]);
//				}
//			}
			/*��������*/
			/*			for(int i = 0;i<city.length;i++){
				System.out.println("                                            ���У�"+city[i]);
			}
			for(int i = 0;i<month.length;i++){
				System.out.println("------------------------------------------�·�:"+month[i]+"-----------------");
			}
			System.out.println(d.length+":"+month.length+":"+city.length);

			for(int i = 0;i<city.length;i++){
				System.out.println("Chengshi:"+city[i]);
			}
			*/
			ChartUtil cu = new ChartUtil();

			/*double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
					{ 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 },
					{ 100, 200, 300, 400, 1000 } };
			String[] rowKeys = { "HTC", "����", "ƻ��", "��Ϊ" };//Month
			String[] columnKeys = { "����", "�Ϻ�", "����", "�ɶ�", "����" };//city*/
			CategoryDataset dataset = cu.getBarData(d, month, city);
			cu.createBarChart(dataset, "x����", "y����", "��״ͼ", response);
		}

	}

}
