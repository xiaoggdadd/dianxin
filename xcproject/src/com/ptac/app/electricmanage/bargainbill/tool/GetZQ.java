package com.ptac.app.electricmanage.bargainbill.tool;

/**
 * �˹����෽����Ҫ�ǽ���ͬ��֮��ֻ���·�û�����ڵ����ݣ�
 * ����ʼ���ڼӡ�-01�������������ڱ�Ϊ��β
 * 
 * @author rock
 * 
 */
public class GetZQ {
	public String getBeginTime(String time) {
		String qssj = time + "-01";
		return qssj;
	}

	public String getEndTime(String time) {
		String jssj = "";
		try {
			String year = time.substring(0, 4);
			String mon = time.substring(5, 7);

			double y = Double.parseDouble(year);
			int m = Integer.parseInt(mon);
			String day = "";

			switch (m) {
			case 1:
				day = "-31";
				break;
			case 2:
				if (y % 4 == 0) {
					day = "-29";
				} else {
					day = "-28";
				}

				break;
			case 3:
				day = "-31";
				break;
			case 4:
				day = "-30";
				break;
			case 5:
				day = "-31";
				break;
			case 6:
				day = "-30";
				break;
			case 7:
				day = "-31";
				break;
			case 8:
				day = "-31";
				break;
			case 9:
				day = "-30";
				break;
			case 10:
				day = "-31";
				break;
			case 11:
				day = "-30";
				break;
			case 12:
				day = "-31";
				break;
			default:
				jssj = "���������ʽ����ȷ";
			}
			jssj = time + day;

		} catch (Exception e) {
			jssj = "���������ʽ����ȷ��";
		}
		return jssj;
	}

}
