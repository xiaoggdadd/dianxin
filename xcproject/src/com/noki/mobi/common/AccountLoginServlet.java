package com.noki.mobi.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.noki.log.DbLog;
import com.noki.unit.ReGenerateSession;

public class AccountLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);// ����
		String path = request.getContextPath();// ��ȡ��Ŀ·��
		HttpSession session = request.getSession();

		String accountname = request.getParameter("user");// ��ȡǰ̨��ֵ
		String password = request.getParameter("pass");
		String rCode = request.getParameter("rCode");//��֤��
		String action = request.getParameter("action");
		String typeStr=request.getParameter("typeStr");
		String l1 = request.getParameter("l1");

		Account account = new Account();
		account.setAccountName(accountname);// ��װ����
		account.setPassword(password);

		PrintWriter out = response.getWriter();// ��ӡ
		DbLog log = new DbLog();// ��־

		if ("loginOn".equals(action)) {
			try {
				if(!typeStr.equals("1")){
					String sCode = String.valueOf(session.getAttribute("rCode"));
					session.removeAttribute("rCode");
					String sCode2 = String.valueOf(session.getAttribute("rCode"));
					System.out.println("sCode>>>>>"+sCode+">>>>>>>>>>sCode2>>>>>>>"+sCode2);
					if(StringUtils.isBlank(rCode) || StringUtils.isBlank(sCode)){
						log.write("��֤�����", accountname,
								request.getRemoteAddr(), "��¼");
						session.removeAttribute("rCode");
						session.invalidate();
						out.println("2");
						return;
					}else if(!rCode.equals(sCode)){
						log.write("��֤�����", accountname,
								request.getRemoteAddr(), "��¼");
						session.removeAttribute("rCode");
						//session.setAttribute("rCode", "");
						session.invalidate();
						out.println("2");
						return;
					}
				}
				
				int checkresult = account.checkLogin(typeStr);// denglu
				if (checkresult == 0) { // �����¼�ɹ�
					// �ж��Ƿ���Ȩ�ޣ������,�ض��򵽹���ҳ��
					session.setAttribute("account", account);//�����û�����
					session.setAttribute("accountname", accountname);//����ǰ̨��¼���û���

					session.setAttribute("loginName", account.getAccountName());
					session.setAttribute("accountSheng", account.getSheng());
					session.setAttribute("accountShi", account.getShi());
					session.setAttribute("accountxian", account.getXian());
					session.setAttribute("accountRole", account.getRoleId());
					session.setAttribute("accountid", account.getAccountId());
					session.setAttribute("accountCthrnumber", account.getCthrnumber());

					//�޸�sessionid
					String sessionId=request.getSession().getId();
					System.out.println(">>>>>>>>>>>sessionId>>>>>>>>>>>>>>>"+sessionId);
					ReGenerateSession reGenerateSession = new ReGenerateSession();
					reGenerateSession.reGenerateSessionId(request);
					String sessionId2=request.getSession().getId();
					System.out.println(">>>>>>>>>>>sessionId2>>>>>>>>>>>>>>>"+sessionId2);
					// ���û���Ϣ����session��
					log.write("��¼�ɹ�", accountname, request.getRemoteAddr(),
							"��¼");// getRemoteAddr()��ȡIP��ַ
					out.println("1");
					return;

				} else { // ������벻����
					log.write("�û��������������", accountname,
							request.getRemoteAddr(), "��¼");
					session.removeAttribute("rCode");
					session.invalidate();
					out.println("0");
				}

				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// ����Ŀ����
				ServletContext ContextA = session.getServletContext();
				ContextA.setAttribute("session", session);
				// System.out.println();
			}
		}
		if ("loginOn2".equals(action)) {
			try {
				int checkresult = account.checkLogin1();// denglu
				if (checkresult == 0) { // �����¼�ɹ�
					// �ж��Ƿ���Ȩ�ޣ������,�ض��򵽹���ҳ��
					session.setAttribute("account", account);
					session.setAttribute("loginName", account.getAccountName());
					session.setAttribute("accountSheng", account.getSheng());
					session.setAttribute("accountShi", account.getShi());
					session.setAttribute("accountxian", account.getXian());
					session.setAttribute("accountRole", account.getRoleId());
					session.setAttribute("accountid", account.getAccountId());
					
					// ���û���Ϣ����session��
					log.write("��¼�ɹ�", accountname, request.getRemoteAddr(),
							"��¼");// getRemoteAddr()��ȡIP��ַ
					out.println("1");
					System.out.println("dizhi:"+request.getRemoteAddr());
					
					return;

				} else { // ������벻����
					log.write("�û��������������", accountname,
							request.getRemoteAddr(), "��¼");
					session.invalidate();
					out.println("0");
				}

				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// ����Ŀ����
				ServletContext ContextA = session.getServletContext();
				ContextA.setAttribute("session", session);
			}
		}
		// ���ģ�� Url��¼����
		if ("loginSj".equals(action)) {
			try {
				Account account2 = new Account();
				account2.setAccountName(accountname);
				account2.setPassword(l1);
				int checkresult = account2.checkLogin2();
				if (checkresult == 0) {
					session.setAttribute("account", account2);
					session.setAttribute("loginName", account2.getAccountName());
					session.setAttribute("accountSheng", account2.getSheng());
					session.setAttribute("accountShi", account2.getShi());
					session.setAttribute("accountxian", account2.getXian());
					session.setAttribute("accountRole", account2.getRoleId());
					session.setAttribute("accountid", account2.getAccountId());
					session.setAttribute("accountCthrnumber", account.getCthrnumber());
					
					
					log.write("��¼�ɹ�", accountname, request.getRemoteAddr(),
							"��¼");
					out.println("1");
					//request.setAttubute("messages",aaa);      
//					RequestDispatcher   requestDispatcher=request.getRequestDispatcher("servlet/NewsServlet?action=getnews");   
//					requestDispatcher.forward(request,response);
					request.getRequestDispatcher("/servlet/NewsServlet?action=getnews").forward(request, response);
					return;

				} else {
					log.write("�û��������������", accountname,
							request.getRemoteAddr(), "��¼");
					session.invalidate();
					out.println("0");
				}
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ServletContext ContextA = session.getServletContext();
				ContextA.setAttribute("session", session);
			}
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
