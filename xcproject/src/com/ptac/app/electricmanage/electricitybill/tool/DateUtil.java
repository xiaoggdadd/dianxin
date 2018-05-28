package com.ptac.app.electricmanage.electricitybill.tool;

import java.util.Calendar;
 public class DateUtil {
	public long[][] getYue(String begintime,String endtime){
		int beginyear = Integer.parseInt(begintime.substring(0, 4));
		int endyear = Integer.parseInt(endtime.substring(0, 4));
		int beginyue = Integer.parseInt(begintime.substring(5, 7));
		int endyue = Integer.parseInt(endtime.substring(5, 7));
		int begindate = Integer.parseInt(begintime.substring(8, 10));
		int enddate= Integer.parseInt(endtime.substring(8, 10));
		
		int yue = endyear*12 + endyue-(beginyear*12 + beginyue)+1;
		long[][] a = new long[yue][2];
		if(yue == 1){//yue = 1 Ҳ���� ��ͬ��
			a[0][0] = beginyue;//��ǰ�·�
			a[0][1] = enddate - begindate + 1;//��ǰ����ռ����
		}else{//�����(����������)
			Calendar bcd = Calendar.getInstance();
			Calendar ecd = Calendar.getInstance();
			for(int i = 0;i < yue;i++){
				int last = yue-1;//���һ����
				a[i][0] = beginyue;//��ǰ�·�
				bcd.clear();
				ecd.clear();
				if(i==0){//��һ����
					bcd.set(beginyear, beginyue-1, begindate);//��ʼʱ��
					ecd.set(beginyear, beginyue, 1);//�¸��µĵ�һ��
					ecd.add(Calendar.DAY_OF_MONTH, -1);//���µ����һ��
					long bt = bcd.getTimeInMillis();
					long et = ecd.getTimeInMillis();
					a[i][1] = (et-bt)/(1000*3600*24)+1;
				}else if(i==last){//���һ����
					bcd.set(beginyear, beginyue-1, 1);//���µ�һ��
					ecd.set(endyear, endyue-1, enddate);//����ʱ��
					long bt = bcd.getTimeInMillis();
					long et = ecd.getTimeInMillis();
					a[i][1] = (et-bt)/(1000*3600*24)+1;
				}else{//֮����·�
					bcd.set(beginyear, beginyue-1, 1);//���µ�һ��
					int max = bcd.getActualMaximum(Calendar.DAY_OF_MONTH);
					a[i][1] = max;
				}
				beginyear = (beginyue+1) ==13?(beginyear+1):beginyear;
				beginyue = (beginyue+1) ==13?1:(beginyue+1);
			}
		}
		return a;
	}
}
 
