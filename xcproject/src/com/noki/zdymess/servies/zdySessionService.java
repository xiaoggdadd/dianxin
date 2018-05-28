package com.noki.zdymess.servies;

import java.util.ArrayList;

import com.noki.zdymess.dao.session;
import com.noki.zdymess.dao.zdySessionDao;

public class zdySessionService {
     private zdySessionDao dao = new zdySessionDao();
     /**
      * @author gt
      * 得到 本时间短的数据连接数
      * */
     
     public ArrayList<session> getSessionCount(){
    	 ArrayList<session> list = new ArrayList<session>();
    	 list = dao.getSession();
    	 return list;
     }
     
     
// 	public static void main(String[] args) {
//		zdySessionDao d = new zdySessionDao();
//		ArrayList<session> a = d.getSession();
//		for(session obj : a){
//			System.out.println(obj.getLogintime());
//			
//		}
//	}
}
