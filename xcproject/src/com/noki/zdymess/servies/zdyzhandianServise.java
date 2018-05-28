package com.noki.zdymess.servies;

import java.util.ArrayList;


import com.noki.zdymess.dao.zdyzhandian;
import com.noki.zdymess.dao.zdyzhandianDao;
import com.noki.zdymess.util.ExtHelper;

public class zdyzhandianServise {
    public ArrayList<zdyzhandian> getZdyzhandian(){
    	zdyzhandianDao dao = new zdyzhandianDao();
    	ArrayList<zdyzhandian>  list = new ArrayList<zdyzhandian>();
    	list = dao.getZdyzhandian();
    	
    	return list;
    }
    public String getZdyXML(){
    	zdyzhandianDao dao = new zdyzhandianDao();
    	ArrayList<zdyzhandian>  list = new ArrayList<zdyzhandian>();
    	list = dao.getZdyzhandian();
    	String xml = ExtHelper.getXmlFromList(list);
    	//System.out.println(xml);
    	return xml;
    }
    public String getZdyJSON(){
    	zdyzhandianDao dao = new zdyzhandianDao();
    	ArrayList<zdyzhandian>  list = new ArrayList<zdyzhandian>();
    	list = dao.getZdyzhandian();
    	String json = ExtHelper.getJsonFromList(list);
    	return json;
    }
}
