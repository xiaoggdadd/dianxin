package com.noki.jtreport.shi;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ű���bean����ŵ�ǰ���еı�������
 * @author Administrator
 *
 */
public class JtReportBeanFactory {

	private List<JtReportBean> reportList;
	
	public JtReportBeanFactory(){
		reportList = new ArrayList<JtReportBean>();

		JtReportBean bean1 = new JtReportBean();
		bean1.setName("����1�����л�վ�õ�����Ϣ���ܱ�.xls");
		bean1.setReportType("01");
		
		JtReportBean bean2 = new JtReportBean();
		bean2.setName("����2����վ�õ������ܷ�����.xls");
		bean2.setReportType("02");
		
		JtReportBean bean3 = new JtReportBean();
		bean3.setName("����רҵ�õ������ܱ�_��.xls");
		bean3.setReportType("03");
		
		JtReportBean bean4 = new JtReportBean();
		bean4.setName("����6��IDC�õ������ܱ�.xls");
		bean4.setReportType("04");
		
		JtReportBean bean5 = new JtReportBean();
		bean5.setName("����7��ͨ�žַ��õ������ܱ�.xls");
		bean5.setReportType("05");
		
		JtReportBean bean6 = new JtReportBean();
		bean6.setName("ͨ�Ż��������¶ȹ������������ܱ�.xls");
		bean6.setReportType("06");
		
		JtReportBean bean7 = new JtReportBean();
		bean7.setName("IDC�������ܼ���Ӧ��������ܱ�.xls");
		bean7.setReportType("07");
		
		JtReportBean bean8 = new JtReportBean();
		bean8.setName("����3����վ���ܼ��ż���Ӧ�����ͳ�Ʊ�.xls");
		bean8.setReportType("08");
		
		JtReportBean bean9 = new JtReportBean();
		bean9.setName("���ܼ��ż���(����ԭʼ��.xls");
		bean9.setReportType("09");
		
		JtReportBean bean10 = new JtReportBean();
		bean10.setName("�����������õ���̨��.xls");
		bean10.setReportType("0a");
		
		JtReportBean bean11 = new JtReportBean();
		bean11.setName("����8��ͨ�Ż������ܼ���Ӧ����Ϣͳ�Ʊ�.xls");
		bean11.setReportType("0b");
		
		JtReportBean bean12 = new JtReportBean();
		bean12.setName("����5���������������ܼ��ż���Ӧ�����.xls");// ����5���������������ܼ��ż���Ӧ�����ͳ�Ʊ�
		bean12.setReportType("0c");
		
		JtReportBean bean13 = new JtReportBean();
		bean13.setName("����9�����ܼ��Ź�����Ч����.xls");
		bean13.setReportType("0d");
		
		JtReportBean bean14 = new JtReportBean();
		bean14.setName("���ܼ���������ܱ�.xls");
		bean14.setReportType("0e");
		
		JtReportBean bean15 = new JtReportBean();
		bean15.setName("����4�����н����������õ�����Ϣ����.xls");
		bean15.setReportType("0f");
		
		JtReportBean bean16 = new JtReportBean();
		bean16.setName("ͨ�Ż��������¶ȹ����������.xls");
		bean16.setReportType("0g");
		
		JtReportBean bean18 = new JtReportBean();
		bean18.setName("��վ�õ���̨��.xls");
		bean18.setReportType("0h");
		
		JtReportBean bean17 = new JtReportBean();
		bean17.setName("�ɼ���վ�õ�����Ϣ���ܱ�.xls");
		bean17.setReportType("0z");
		
		JtReportBean bean19 = new JtReportBean();
		bean19.setName("����10�������豸�����۱���.xls");
		bean19.setReportType("0v");
		
		reportList.add(bean1);
		reportList.add(bean2);
		reportList.add(bean3);
		reportList.add(bean4);
		reportList.add(bean5);
		reportList.add(bean6);
		reportList.add(bean7);
		reportList.add(bean8);
		reportList.add(bean9);
		reportList.add(bean10);
		reportList.add(bean11);
		reportList.add(bean12);
		reportList.add(bean13);
		reportList.add(bean14);
		reportList.add(bean15);
		reportList.add(bean16);
		reportList.add(bean17);
		reportList.add(bean18);
		reportList.add(bean19);
	}
	
	public List<JtReportBean> getReportList() {
		return reportList;
	}
	//���ر��������
	public String getReportNameByReportType(String reportType){ 
		for(JtReportBean bean:this.reportList){
			if(bean.getReportType().equals(reportType))
				return bean.getName();
		}
		return null;
	}
}
