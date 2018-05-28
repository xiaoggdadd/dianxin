package com.noki.jtreport.shi.download.javabean;

/**
 * 报表工厂类
 * @author 王海
 *
 */
public class ReportDaoFactory {
	private ReportDaoFactory(){}
	
	public static ReportDao create(String action){
		if(action.equals("1")){//地市基站用电量信息汇总表.xls（表一）
			return new DSJZYDLXXHZ_ReportDao();
		}
		else if(action.equals("2")){//基站用电量汇总分析表.xls（表二）
			return new JZYDLHZFX_ReportDao();
		}
		else if(action.equals("3")){//固网专业用电量汇总表.xls（表三）
			return new GWZYYDLHZ_ReportDao();
		}
		else if(action.equals("4")){//IDC用电量汇总表.xls（表四）
			return new IDCYDLHZ_ReportDao();
		}
		else if(action.equals("5")){//通信局房用电量汇总表.xls（表五）
			return new TXJFYDLHZ_ReportDao();
		}
		else if(action.equals("6")){//通信机房环境温度管理节能情况汇总表.xls（表六）
			return new TXJFHJWDGLJNQKHZ_ReportDao();
		}
		else if(action.equals("7")){//IDC机房节能技术应用情况汇总表.xls（表七）
			return new IDCJFJNJSYYQKHZ_ReportDao();
		}
		else if(action.equals("8")){//基站节能减排技术应用情况统计表.xls（表八）
			return new JZJNJPJSYYQKTJ_ReportDao();
		}
		else if(action.equals("9")){//节能减排月工作季报表.xls（表九）
			return new JNJPYGZJB_ReportDao();
		}
		else if(action.equals("a")){//接入网机房用电量台账.xls（表十）
			return new JRWJFYDLTZ_ReportDao();
		}
		else if(action.equals("b")){//通信机房节能技术应用信息统计.xls（表十一）
			return new TXJFJNJSYYXXTJ_ReportDao();
		}
		else if(action.equals("c")){//接入网机房节能减排技术应用情况.xls（表十二）
			return new JRWJFJNJPJSYYQK_ReportDao();
		}
		else if(action.equals("d")){//节能减排工作成效报表.xls（表十三）
			return new JNJPGZCX_ReportDao();
		}
		else if(action.equals("e")){//节能技术分类汇总表.xls（表十四）
			return new JNJSFLHZ_ReportDao();
		}
		else if(action.equals("f")){//地市接入网机房用电量信息汇总.xls（表十五）
			return new DSJRWJFYDLXXHZ_ReportDao();
		}
		else if(action.equals("g")){//通信机房环境温度管理情况汇总.xls（表十六）
			return new TXJFHJWDGLQKHZ_ReportDao();
		}
		else if(action.equals("h")){//基站用电量台账.xls（表十六）
			return new JZYDLTZ_ReportDao();
		}
		else if(action.equals("z")){//采集基站用电量信息汇总表.xls（表十七）
			return new CJJZYDLXXHZB_ReportDao();
		}else if(action.equals("v")){//附件10：节能设备后评价报表.xls
			return new JNSBHPJBB_ReportDao();
		}
		return null;
	}
}
