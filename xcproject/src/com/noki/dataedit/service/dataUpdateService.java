package com.noki.dataedit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.noki.dataedit.bean.updateBean;
import com.noki.dataedit.dao.updateDao;

public class dataUpdateService {
    public String dataSave(ArrayList a1,ArrayList a2){
    	
    	//System.out.println(a1.toString());
    	System.out.println("------------------+-----");
    	//System.out.println(a2.toString());
    	
    	HashMap<String, updateBean> mess = new HashMap<String, updateBean>();
    	//System.out.println(a1.size());
    	for(int i=0;i<a1.size();i++){
    		updateBean bean = new updateBean();
    		//System.out.println("i为"+i+":"+a1.get(i));
    		String[] arr1 = a1.get(i).toString().split("の");
    		String[] arr2 = a2.get(i).toString().split("の");
    		//bean.sete
    		String zdid = arr1[4].toString();
    		System.out.println("站点id:"+zdid);
    		bean.setZdid(zdid);
    		double jlfh = Double.parseDouble(arr1[7]);
    		bean.setJlfh(jlfh);
    		double zlfh = Double.parseDouble(arr1[8]);
    		bean.setZlfh(zlfh);
    		double kdsl = Double.parseDouble(arr1[9]);
    		bean.setKdsbsl(kdsl);
    		double yysl = Double.parseDouble(arr1[10]);
    		bean.setYysbsl(yysl);
    	    String kt1 = arr1[11].toString();
    	    bean.setKtps1(kt1);
    	    String kt2 = arr1[12].toString();
    	    bean.setKtps2(kt2);
    	    double rru = Double.parseDouble(arr1[13]);
    	    bean.setRru(rru);
    	    double ydgx = Double.parseDouble(arr1[14]);
    	    bean.setYdgxsbsl(ydgx);
    	    double dxgx = Double.parseDouble(arr1[15]);
    	    bean.setDxgxsbsl(dxgx);
    	    double qsdb = Double.parseDouble(arr1[16]);
    	    bean.setQsdbdl(qsdb);
    	    double edhdl = Double.parseDouble(arr1[17]);
    	   
    	    bean.setEdhdl(edhdl);
    	    
    	    //---------------------
    	    
    	    String qyzt = arr2[0].toString();
    	    
    	    bean.setQyzt(qyzt);
    	    String yflx = arr2[1].toString();
    	    bean.setYflx(yflx);
    	    String gdfs = arr2[2].toString();
    	    bean.setGdfs(gdfs);
    	    String zdcq = arr2[3].toString();
    	    bean.setZdcq(zdcq);
    	    String gxxx = arr2[4].toString();
    	    bean.setGxxx(gxxx);
    	    String zdsx = arr2[5].toString();
    	    bean.setZdsx(zdsx);
    	    String zdlx = arr2[6].toString();
    	    bean.setZdlx(zdlx);
    	    mess.put(arr1[0], bean);
    	}
    	System.out.println("+++++++++++++++++++");
    	
    	updateDao dao = new updateDao();
    	
    	Iterator iterator = mess.keySet().iterator();
    	   while(iterator.hasNext()) {
    		   System.out.println(mess.get(iterator.next()));
    		}
    
    	
    	
    	
    	String remess = dao.toGetDate(mess);
    	
    	
    	return remess;
    }
  public String dataSaveJyjf(ArrayList a1,ArrayList a2){
    	
    	//System.out.println(a1.toString());
    	System.out.println("-----------------------");
    	//System.out.println(a2.toString());
    	updateBean bean = new updateBean();
    	HashMap<String, updateBean> mess = new HashMap<String, updateBean>();
    	System.out.println("a1.size:"+a1.size());
    	System.out.println("a2.size:"+a2.size());
    	for(int i=0;i<a1.size();i++){
    		
    		//System.out.println("i为"+i+":"+a1.get(i));
    		String[] arr1 = a1.get(i).toString().split("の");
    		String[] arr2 = a2.get(i).toString().split("の");
    		//bean.sete
    		String zdid = arr1[4].toString();
    		System.out.println("站点id:"+zdid);
    		bean.setZdid(zdid);
    		double jlfh = Double.parseDouble(arr1[7]);
    		bean.setJlfh(jlfh);
    		double zlfh = Double.parseDouble(arr1[8]);
    		bean.setZlfh(zlfh);
    	
    	    double edhdl = Double.parseDouble(arr1[10]);
    	    bean.setEdhdl(edhdl);
    	    
    	    //---------------------
    	    
    	    String qyzt = arr2[0].toString();
    	    
    	    bean.setQyzt(qyzt);
    	    String yflx = arr2[1].toString();
    	    bean.setYflx(yflx);
    	    System.out.println("用房类型111："+yflx);
    	    String gdfs = arr2[2].toString();
    	    bean.setGdfs(gdfs);
    	  
    	    String zdsx = arr2[3].toString();
    	    bean.setZdsx(zdsx);
    	    String zdlx = arr2[4].toString();
    	    bean.setZdlx(zdlx);
    	    mess.put(arr1[0], bean);
    	}
    	System.out.println("+++++++++++++++++++");
    	
    	updateDao dao = new updateDao();
    	
    	String remess = dao.toGetDateJyjf(mess);
    	
    	
    	return remess;
    }
}
