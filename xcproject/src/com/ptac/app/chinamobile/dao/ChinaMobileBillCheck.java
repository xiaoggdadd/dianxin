package com.ptac.app.chinamobile.dao;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.chinamobile.bean.ChinaMobileBillBean;

public interface ChinaMobileBillCheck {
	/**
	 * �й��ƶ�̩����˾��ѵ�����ĵ������ݵĲ�ѯ
	 * 
	 * @author gt_ptac
	 * @param loginid
	 * @return
	 */
	public ArrayList<ChinaMobileBillBean> getDownLoadData(String loginid);
	/**
	 * ��ȡû�б��γ���ʱ��� ���ĳ�ʼʹ��ʱ��
	 * @author gt_ptac
	 * 
	 * @param dianbiaoid
	 * @return
	 */
	public String getCSDS(String dianbiaoid);
	
	/**
	 * ��ʼ�������ݣ��ܹ��ĵ��� �����Ҽ�¼�����ĵ����¼
	 * @author gt_ptac
	 * 
	 * @param content
	 * @param cform
	 * @param account
	 * @param request
	 * @param response
	 * @return
	 */
	public Vector input(Vector content, CountForm cform, Account account,
			HttpServletRequest request, HttpServletResponse response);
}
