package com.noki.htnygl.dlsjbd.dao;

import java.util.ArrayList;

import com.noki.htnygl.dlsjbd.javabean.dlsjbdBean;
import com.noki.htnygl.dlsjbd.javabean.zdDlsjbdBean;

/**
 * @author xuzehua
 * */
public interface dlsjbdDao {
	    /**
	     * �������������� ��ȡ �ϴκͱ��γ���ʱ�����ͬ�ڲ�ѯ�·ݷ�Χ�ڵ� ����
	     * 
	     * */
	    public ArrayList<dlsjbdBean> getDlsjbd(String shi,String quxian,String startmonth,String endmonth);
	    /**
	     * 
	     * ����ͬ�ϵ����� �Ļ���֮�ϣ������ǶԾ��嵽��ÿ������Ҫ���վ�㷵������
	     * */
	    public ArrayList<zdDlsjbdBean> getZdDlsjbd(String shi,String quxian, String startmonth,String endmonth);
	}
