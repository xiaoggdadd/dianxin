package com.noki.htnygl.dlsjbd.dao;

import java.util.ArrayList;

import com.noki.htnygl.dlsjbd.javabean.dlsjbdBean;
import com.noki.htnygl.dlsjbd.javabean.zdDlsjbdBean;

/**
 * @author xuzehua
 * */
public interface dlsjbdDao {
	    /**
	     * 按条件过滤数据 获取 上次和本次抄表时间均等同于查询月份范围内的 数据
	     * 
	     * */
	    public ArrayList<dlsjbdBean> getDlsjbd(String shi,String quxian,String startmonth,String endmonth);
	    /**
	     * 
	     * 按照同上的条件 的基础之上，但是是对具体到的每个符合要求的站点返回数据
	     * */
	    public ArrayList<zdDlsjbdBean> getZdDlsjbd(String shi,String quxian, String startmonth,String endmonth);
	}
