package com.noki.dfdfcx.servise;

import java.util.ArrayList;
import java.util.Collections;

import com.noki.dfdfcx.ContentComparator;
import com.noki.dfdfcx.Dao.dfdfcxDao;
import com.noki.dfdfcx.bean.dfdfBean;

public class dfdfcxServise {
    /**
     * @author GT
     * 多付电费查询 处理
     * 
     * */
	
	
	public ArrayList<dfdfBean>  getDfdfChaxun(String whereStr){
		ArrayList<dfdfBean> list = new ArrayList<dfdfBean>();
		ArrayList<dfdfBean> list1 = new ArrayList<dfdfBean>();
		dfdfcxDao dao = new dfdfcxDao();
		list = dao.getDfdfChaxun(whereStr);
		
		ContentComparator comp = new ContentComparator();  
		Collections.sort(list,comp);
        int temp=1;
		for(int i=0;i<list.size();i++){
			list1.add(list.get(i));
			temp++;
			if(temp==6){
				break;
			}
		}
		
		return list1;
	}

	/**
	 * @author GT
	 * 总共区分部分
	 * 
	 * **/
	public ArrayList<dfdfBean>  getDfdfChaxunAll(String whereStr,String xian){
		ArrayList<dfdfBean> list = new ArrayList<dfdfBean>();
		ArrayList<dfdfBean> list1 = new ArrayList<dfdfBean>();
		dfdfcxDao dao = new dfdfcxDao();
		if("".equals(xian)||null==xian||"-1".equals(xian)){
			list = dao.getDfdfChaxun(whereStr);
		}else{
			if(xian!=null){
				list =dao.getDfdfChaxunxian(whereStr);
			}
		}
		
		
		ContentComparator comp = new ContentComparator();  
		Collections.sort(list,comp);
        int temp=1;
		for(int i=0;i<list.size();i++){
			list1.add(list.get(i));
			temp++;
			if(temp==6){
				break;
			}
		}
		return list1;
	}
	
	
	
	/**
	 * @author GT
	 * zd 部分信息
	 * 
	 * **/
	public ArrayList<dfdfBean>  getDfdfChaxunzd(String whereStr){
		System.out.println("dddddddddddd");
		ArrayList<dfdfBean> list = new ArrayList<dfdfBean>();
		ArrayList<dfdfBean> list1 = new ArrayList<dfdfBean>();
		dfdfcxDao dao = new dfdfcxDao();
		list = dao.getDfdfChaxunZd(whereStr);
		ContentComparator comp = new ContentComparator();  
		Collections.sort(list,comp);
        int temp=1;
        if(list.size()>=10){
        	for(int i=0;i<list.size();i++){
    			list1.add(list.get(i));
    			temp++;
    			if(temp==11){
    				break;
    			}
    		}
        }else{
        	return list;
        }
		
		return list1;
	}
	
}
