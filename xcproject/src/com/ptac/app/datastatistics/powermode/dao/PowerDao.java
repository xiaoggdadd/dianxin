package com.ptac.app.datastatistics.powermode.dao;

import java.util.ArrayList;

import com.ptac.app.datastatistics.powermode.bean.PowerBean;

/**
 * @author �
 * @see ���緽ʽ�ɷ�ͳ�ƽӿڲ�
 */
public interface PowerDao {

	ArrayList<PowerBean> queryExport(String whereStr, String loginId);

	/**
	 * @author �
	 * @see �����ܵı��˵��
	 */
	PowerBean totalMoney(ArrayList<PowerBean> list);

}
