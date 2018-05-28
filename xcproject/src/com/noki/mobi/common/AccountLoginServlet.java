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
		response.setContentType(CONTENT_TYPE);// 编码
		String path = request.getContextPath();// 获取项目路径
		HttpSession session = request.getSession();

		String accountname = request.getParameter("user");// 获取前台的值
		String password = request.getParameter("pass");
		String rCode = request.getParameter("rCode");//验证码
		String action = request.getParameter("action");
		String typeStr=request.getParameter("typeStr");
		String l1 = request.getParameter("l1");

		Account account = new Account();
		account.setAccountName(accountname);// 封装数据
		account.setPassword(password);

		PrintWriter out = response.getWriter();// 打印
		DbLog log = new DbLog();// 日志

		if ("loginOn".equals(action)) {
			try {
				if(!typeStr.equals("1")){
					String sCode = String.valueOf(session.getAttribute("rCode"));
					session.removeAttribute("rCode");
					String sCode2 = String.valueOf(session.getAttribute("rCode"));
					System.out.println("sCode>>>>>"+sCode+">>>>>>>>>>sCode2>>>>>>>"+sCode2);
					if(StringUtils.isBlank(rCode) || StringUtils.isBlank(sCode)){
						log.write("验证码错误", accountname,
								request.getRemoteAddr(), "登录");
						session.removeAttribute("rCode");
						session.invalidate();
						out.println("2");
						return;
					}else if(!rCode.equals(sCode)){
						log.write("验证码错误", accountname,
								request.getRemoteAddr(), "登录");
						session.removeAttribute("rCode");
						//session.setAttribute("rCode", "");
						session.invalidate();
						out.println("2");
						return;
					}
				}
				
				int checkresult = account.checkLogin(typeStr);// denglu
				if (checkresult == 0) { // 如果登录成功
					// 判断是否有权限，如果有,重定向到功能页面
					session.setAttribute("account", account);//保存用户对象
					session.setAttribute("accountname", accountname);//保存前台登录的用户名

					session.setAttribute("loginName", account.getAccountName());
					session.setAttribute("accountSheng", account.getSheng());
					session.setAttribute("accountShi", account.getShi());
					session.setAttribute("accountxian", account.getXian());
					session.setAttribute("accountRole", account.getRoleId());
					session.setAttribute("accountid", account.getAccountId());
					session.setAttribute("accountCthrnumber", account.getCthrnumber());

					//修改sessionid
					String sessionId=request.getSession().getId();
					System.out.println(">>>>>>>>>>>sessionId>>>>>>>>>>>>>>>"+sessionId);
					ReGenerateSession reGenerateSession = new ReGenerateSession();
					reGenerateSession.reGenerateSessionId(request);
					String sessionId2=request.getSession().getId();
					System.out.println(">>>>>>>>>>>sessionId2>>>>>>>>>>>>>>>"+sessionId2);
					// 将用户信息放在session中
					log.write("登录成功", accountname, request.getRemoteAddr(),
							"登录");// getRemoteAddr()获取IP地址
					out.println("1");
					return;

				} else { // 如果代码不存在
					log.write("用户名或者密码错误", accountname,
							request.getRemoteAddr(), "登录");
					session.removeAttribute("rCode");
					session.invalidate();
					out.println("0");
				}

				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 多项目调用
				ServletContext ContextA = session.getServletContext();
				ContextA.setAttribute("session", session);
				// System.out.println();
			}
		}
		if ("loginOn2".equals(action)) {
			try {
				int checkresult = account.checkLogin1();// denglu
				if (checkresult == 0) { // 如果登录成功
					// 判断是否有权限，如果有,重定向到功能页面
					session.setAttribute("account", account);
					session.setAttribute("loginName", account.getAccountName());
					session.setAttribute("accountSheng", account.getSheng());
					session.setAttribute("accountShi", account.getShi());
					session.setAttribute("accountxian", account.getXian());
					session.setAttribute("accountRole", account.getRoleId());
					session.setAttribute("accountid", account.getAccountId());
					
					// 将用户信息放在session中
					log.write("登录成功", accountname, request.getRemoteAddr(),
							"登录");// getRemoteAddr()获取IP地址
					out.println("1");
					System.out.println("dizhi:"+request.getRemoteAddr());
					
					return;

				} else { // 如果代码不存在
					log.write("用户名或者密码错误", accountname,
							request.getRemoteAddr(), "登录");
					session.invalidate();
					out.println("0");
				}

				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 多项目调用
				ServletContext ContextA = session.getServletContext();
				ContextA.setAttribute("session", session);
			}
		}
		// 审计模块 Url登录处理
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
					
					
					log.write("登录成功", accountname, request.getRemoteAddr(),
							"登录");
					out.println("1");
					//request.setAttubute("messages",aaa);      
//					RequestDispatcher   requestDispatcher=request.getRequestDispatcher("servlet/NewsServlet?action=getnews");   
//					requestDispatcher.forward(request,response);
					request.getRequestDispatcher("/servlet/NewsServlet?action=getnews").forward(request, response);
					return;

				} else {
					log.write("用户名或者密码错误", accountname,
							request.getRemoteAddr(), "登录");
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
