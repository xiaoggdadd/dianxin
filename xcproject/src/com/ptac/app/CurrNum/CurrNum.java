package com.ptac.app.CurrNum;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CurrNum implements HttpSessionListener {

	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();

		// ��application��Χ��һ��HashSet���������е�session
		HashSet sessions = (HashSet) application.getAttribute("sessions");
		if (sessions == null) {
			sessions = new HashSet();
			application.setAttribute("sessions", sessions);
		}

		// �´�����session����ӵ�HashSet����
		sessions.add(session);
		// �����ڱ𴦴�application��Χ��ȡ��sessions����
		// Ȼ��ʹ��sessions.size()��ȡ��ǰ���session������Ϊ������������

	}

	@SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		HashSet sessions = (HashSet) application.getAttribute("sessions");

		// ���ٵ�session����HashSet�����Ƴ�
		sessions.remove(session);
	}

}
