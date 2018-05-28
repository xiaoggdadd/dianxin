package com.noki.zwhd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.DfManageYd;
import com.noki.zwhd.model.DxdfftqrBean;

public class YddfftqrAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private String msg;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String dw = request.getParameter("t_dw");
			String qx = request.getParameter("t_qx");
			String zdmc = request.getParameter("t_zdmc");
			
			String gdfs = request.getParameter("t_gdfs");
			String jsfs = request.getParameter("t_jsfs");
			
			//String jfqsrq = request.getParameter("t_jfqsrq");
			//String jfzzrq = request.getParameter("t_jfzzrq");
			String qm = request.getParameter("t_qm");
			String zm = request.getParameter("t_zm");
			
			
			String jfje = request.getParameter("t_jfje");
			
			String jfpjlx = request.getParameter("t_jfpjlx");
			String gdfmc = request.getParameter("t_gdfmc");
			
			String dliu = request.getParameter("t_dliu");
			String ftbl = request.getParameter("t_ftbl");
			String ftje = request.getParameter("t_ftje");
             //ȷ��ĳ���˵�
			String dbbm=request.getParameter("t_dbbm");//������
			String zdbm = request.getParameter("t_zdbm");//վ�����
			String pc=request.getParameter("t_yearmonth");//����
			String dh = request.getParameter("t_dh");//����
			String kh="�ƶ�";//�ͻ�
			//��Ҫ���µĶ���
			String jszq=request.getParameter("t_jszq");//����
			String jfzzrq=request.getParameter("t_bccbsj");//���γ���ʱ��
			String jfqsrq=request.getParameter("t_sccbsj");//�ϴ�����ʱ��
			String cbzm=request.getParameter("t_bccbzm");//���γ���ֹ��
			String cbsczm=request.getParameter("t_sccbzm");//�ϴγ���ֹ��
			String dliang=request.getParameter("t_dliang");//����
			String dj=request.getParameter("t_dj");//����
			String jfrq=request.getParameter("t_jfrq");//�ɷ�����
			String sh=request.getParameter("t_sunhao");//���
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

//			if (dw.equals("0")) {
//				msg = "��λ����Ϊ�գ�";
//			} else if (qx.equals("0")) {
//				msg = "��λ����Ϊ�գ�";
//			} else if (zdmc == null) {
//				msg = "վ�����Ʋ���Ϊ�գ�";
//			} else if (zdbm == null) {
//				msg = "վ����벻��Ϊ�գ�";
//			}
//			if (gdfs == null) {
//				msg = "���緽ʽ����Ϊ�գ�";
//			} else if (jsfs == null) {
//				msg = "���㷽ʽ����Ϊ�գ�";
//			} else if (dh == null) {
//				msg = "���Ų���Ϊ�գ�";
//			} else if (jfqsrq == null) {
//				msg = "�ɷ���ʼ���ڲ���Ϊ�գ�";
//			}
//			if (jfzzrq == null) {
//				msg = "�ɷ���ֹ���ڲ���Ϊ�գ�";
//			} else if (qm == null) {
//				msg = "���벻��Ϊ�գ�";
//			}
//			if (zm == null) {
//				msg = "ֹ�벻��Ϊ�գ�";
//			} else if (dliang == null) {
//				msg = "��������Ϊ�գ�";
//			} else if (dliu == null) {
//				msg = "��������Ϊ�գ�";
//			} else if (dj == null) {
//				msg = "���۲���Ϊ�գ�";
//			} else if (Integer.parseInt(qm) > Integer.parseInt(zm)) {
//				msg = "���벻�ܴ���ֹ��";
//			} else if (Integer.parseInt(qm) <= 0) {
//				msg = "����������0";
//			} else if (Integer.parseInt(dliang) <= 0) {
//				msg = "�����������0";
//			} else if (Integer.parseInt(dj) <= 0) {
//				msg = "��۱������0";
//			} else if (formate.parse(jfqsrq).getTime() > formate.parse(jfzzrq).getTime()) {
//				msg = "�ɷ���ʼ���ڱ���С�ڵ��ڽɷ���ֹ����";
			//} else {
				DxdfftqrBean bean = new DxdfftqrBean();
				bean.setZdbm(zdbm);
				bean.setDbbm(dbbm);
				bean.setPc(pc);
				bean.setDh(dh);
				bean.setKh(kh);
				
				bean.setJszq(jszq);//����
				bean.setQm(cbsczm);//����
				bean.setZm(cbzm);//ֹ��
				bean.setDliang(dliang);//����
				bean.setDj(dj);//����
				bean.setJfrq(jfrq);//�ɷ�����
				bean.setSh(sh);//���
				bean.setJfqsrq(jfqsrq);//�ϴ�����ʱ��
				bean.setJfzzrq(jfzzrq);//���γ���ʱ��
				
			

				DfManageYd dfManage = new DfManageYd();
				boolean flag = dfManage.saveYddfftqr(bean);

				request.setCharacterEncoding("utf-8");// ������������ʽ��
				response.setContentType("text/html;charsetType=utf-8");// ������������ʽ��
				msg = "�޸��ƶ���ѵ�" + (flag ? "�ɹ���" : "ʧ�ܣ�");

			//}
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_yddfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		} catch (Exception e) {
			msg = "ϵͳ���ϣ�"+e.getMessage()+"���ڹ���Ա��ϵ��";
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			String url = path + "/web/wyftdfsh/tt_yddfftqr_search.jsp";
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}

	}

}
