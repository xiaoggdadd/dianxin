package com.noki.jtreport.shi;

import java.util.ArrayList;
import java.util.List;

/**
 * 集团报表bean，存放当前所有的报表名称
 * @author Administrator
 *
 */
public class JtReportBeanFactory {

	private List<JtReportBean> reportList;
	
	public JtReportBeanFactory(){
		reportList = new ArrayList<JtReportBean>();

		JtReportBean bean1 = new JtReportBean();
		bean1.setName("附件1：地市基站用电量信息汇总表.xls");
		bean1.setReportType("01");
		
		JtReportBean bean2 = new JtReportBean();
		bean2.setName("附件2：基站用电量汇总分析表.xls");
		bean2.setReportType("02");
		
		JtReportBean bean3 = new JtReportBean();
		bean3.setName("固网专业用电量汇总表_市.xls");
		bean3.setReportType("03");
		
		JtReportBean bean4 = new JtReportBean();
		bean4.setName("附件6：IDC用电量汇总表.xls");
		bean4.setReportType("04");
		
		JtReportBean bean5 = new JtReportBean();
		bean5.setName("附件7：通信局房用电量汇总表.xls");
		bean5.setReportType("05");
		
		JtReportBean bean6 = new JtReportBean();
		bean6.setName("通信机房环境温度管理节能情况汇总表.xls");
		bean6.setReportType("06");
		
		JtReportBean bean7 = new JtReportBean();
		bean7.setName("IDC机房节能技术应用情况汇总表.xls");
		bean7.setReportType("07");
		
		JtReportBean bean8 = new JtReportBean();
		bean8.setName("附件3：基站节能减排技术应用情况统计表.xls");
		bean8.setReportType("08");
		
		JtReportBean bean9 = new JtReportBean();
		bean9.setName("节能减排季报(集团原始表）.xls");
		bean9.setReportType("09");
		
		JtReportBean bean10 = new JtReportBean();
		bean10.setName("接入网机房用电量台账.xls");
		bean10.setReportType("0a");
		
		JtReportBean bean11 = new JtReportBean();
		bean11.setName("附件8：通信机房节能技术应用信息统计表.xls");
		bean11.setReportType("0b");
		
		JtReportBean bean12 = new JtReportBean();
		bean12.setName("附件5：接入网机房节能减排技术应用情况.xls");// 附件5：接入网机房节能减排技术应用情况统计表
		bean12.setReportType("0c");
		
		JtReportBean bean13 = new JtReportBean();
		bean13.setName("附件9：节能减排工作成效报表.xls");
		bean13.setReportType("0d");
		
		JtReportBean bean14 = new JtReportBean();
		bean14.setName("节能技术分类汇总表.xls");
		bean14.setReportType("0e");
		
		JtReportBean bean15 = new JtReportBean();
		bean15.setName("附件4：地市接入网机房用电量信息汇总.xls");
		bean15.setReportType("0f");
		
		JtReportBean bean16 = new JtReportBean();
		bean16.setName("通信机房环境温度管理情况汇总.xls");
		bean16.setReportType("0g");
		
		JtReportBean bean18 = new JtReportBean();
		bean18.setName("基站用电量台账.xls");
		bean18.setReportType("0h");
		
		JtReportBean bean17 = new JtReportBean();
		bean17.setName("采集基站用电量信息汇总表.xls");
		bean17.setReportType("0z");
		
		JtReportBean bean19 = new JtReportBean();
		bean19.setName("附件10：节能设备后评价报表.xls");
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
	//返回报表的名称
	public String getReportNameByReportType(String reportType){ 
		for(JtReportBean bean:this.reportList){
			if(bean.getReportType().equals(reportType))
				return bean.getName();
		}
		return null;
	}
}
