package com.noki.htnygl.dlsjbd.servlce;

import java.util.ArrayList;

import com.noki.htnygl.dlsjbd.javabean.dlsjbdBean;
import com.noki.htnygl.dlsjbd.javabean.zdDlsjbdBean;

/**
 * @author xuzehua
 * */

public interface dlsjbdService {
	/**
	 * ���ղ�ѯ���� ����Ȼ�£� ��ѯ��ͨ��dao���ص����ݽ��в������ļ��㣬��ĳһ�����µ��������ؽ�����ʾ(���б�ѡ)
	 * 
	 *  ��ѯ�� �����ݽ��� ��״ͼ�������Խ�� tool���µ� �������ļ�
	 * */
    public ArrayList<dlsjbdBean> getDlsjbd(String shi,String quxian,String startmonth,String endmonth);
    
    /**
     * �����ϵ� ����dao �������ݵĻ����ϣ� ��ĳһ������ �µ�վ�������ʾ��zdDlsjbdBean   (ѡ����ĳ�����غ󴥷�)
     * 
     * 
     * */
    public ArrayList<zdDlsjbdBean> getZdDlsjbd(String shi,String quxian, String startmonth,String endmonth);
}
