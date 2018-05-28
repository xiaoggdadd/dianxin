package com.ptac.app.statisticcollect.province.unitpricePIC.util;

import java.util.ArrayList;
import java.util.List;

/**
 * �����ࣺͨ�����������·ݲ�����ȡ�·ݼ��ϵķ���
 * @author rock
 *
 */
public class GetMonthes {

	private String[] getAllMonths(String start, String end){
        String splitSign="-";
        String regex="\\d{4}"+splitSign+"(([0][1-9])|([1][012]))"; //�ж�YYYY-MMʱ���ʽ��������ʽ
        if(!start.matches(regex) || !end.matches(regex)) return new String[0];
        
        List<String> list=new ArrayList<String>();
        if(start.compareTo(end)>0){
            //start����end����ʱ������
            String temp=start;
            start=end;
            end=temp;
        }
        
        String temp=start; //����С�·ݿ�ʼ
        while(temp.compareTo(start)>=0 && temp.compareTo(end)<=0){
            list.add(temp); //���ȼ�����С�·�,���ż�����һ���·�
            String[] arr=temp.split(splitSign);
            int year=Integer.valueOf(arr[0]);
            int month=Integer.valueOf(arr[1])+1;
            if(month>12){
                month=1;
                year++;
            }
            if(month<10){//��0����
                temp=year+splitSign+"0"+month;
            }else{
                temp=year+splitSign+month;
            }
        }
        
        int size=list.size();
        String[] result=new String[size]; 
        for(int i=0;i<size;i++){
            result[i]=list.get(i);
        }
        return result;
    }
	
	public List<String> getMonthes(String start,String end){
		List<String> ls = new ArrayList<String>();
		
        String[] result=getAllMonths(start, end);
        for (String str : result) {
            ls.add(str);
        }
		return ls;
	}
}
