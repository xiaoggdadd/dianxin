package com.noki.biaogan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.biaogan.model.BiaoganBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class BGServlet extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	this.doPost(request, response);
}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����ʽת��
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//��ȡѡ��
		String action = request.getParameter("action");
		String path = request.getContextPath();
		/*�����ʽ������*/
		BiaoganBean biaoganBean = new BiaoganBean();
		DataBase db = new DataBase();
		HttpSession session = request.getSession();
		//url���յ���ת��ַ
		String url = path+"/web/sdttWeb/BiaoganManager/biaoganHistory.jsp",msg="";
		//��ӱ��
		if (action.equals("addBG")) {
			try {
				System.out.println("������ԣ�");
				// վ��ID==���ID
				String zdm = request.getParameter("zdmc").trim();
				int zdmc = Integer.parseInt(zdm);
				// ��ѯվ�����
				String sql = "select jzcode from zhandian a  where  a.id='"+zdmc+"'";
				ResultSet rs = db.queryAll(sql);
				String zdbm = "";
				System.out.println(sql);
				while (rs.next()) {
					zdbm = rs.getString(1);
					break;
				}
				// ������
				String dbbm = request.getParameter("dbbm").trim();
				String ydxs = request.getParameter("ydxs").trim();
				String zlfh = request.getParameter("zlfh").trim();
				String ktxs = request.getParameter("ktxs").trim();
				String biaoganvalue = request.getParameter("biaoganvalue").trim();
				String yearmonth = request.getParameter("yearmonth").trim();
				String spsj = "2015-01";
				String spzt = "0";
				String zq = "0.0";
				// �������
				String sql1 = "Insert into TBL_TT_BIAOGAN_LISHI "
						+"(ZDBM,YEARMONTH,DBBM,BIAOGANVALUE,ZLFH,YDXS,KTXS,SPZT,ZDID,DBID,ZQ)"
						+"values('"
						+zdbm
						+"','"
						+yearmonth
						+"','"
						+dbbm
						+"','"
						+biaoganvalue
						+"','"
						+zlfh
						+"','"
						+ydxs
						+"','"
						+ktxs
						+"','"
						+spzt
						+"','"
						+zdmc+"','"+zdmc+"','"+zq+"')";
				int b = db.update(sql1);
				if(b==1){
	            	msg="��ӳɹ�,����������ҳ";
	            }else {
	            	msg="���ʧ��,����ϵ����Ա";
	            }
				session.setAttribute("url", url);
				//msgҪ��ʾ�Ľ������
				session.setAttribute("msg", msg);
				//��ת��תҳ��
				response.sendRedirect(path + "/web/nomsg.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (DbException e) {
				e.printStackTrace();
			}

			
		} else if("delBG".equals(action)){
			try {
				System.out.println("ɾ�����");
				String  id = request.getParameter("id");
				String sql1 = "select spzt from TBL_TT_BIAOGAN_LISHI where id ='"+id+"'";
				ResultSet  rs3 =db.queryAll(sql1);
				String spzt="";
				while (rs3.next()) {
					 spzt= rs3.getString(1);
					break;
				}
				//�ж�ɾ��
				if(spzt.equals("0")){
					String sql = "delete TBL_TT_BIAOGAN_LISHI where id ='"+id+"'";
						int b =db.update(sql);
						if(b==1){
			            	msg="�h���ɹ�,����������ҳ";
			            }else {
			            	msg="�h��ʧ��,����ϵ����Ա";
			            }
				}else{
					
					msg="����ˣ�����ɾ����";
					
				}
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		}else if ("updateBG".equals(action)) {
			//�����޸�Servlet
			System.out.println("�޸�Servlet");
			String id = request.getParameter("id");
			// վ��ID==���ID
			String zdm = request.getParameter("zdmc");
			int zdmc = Integer.parseInt(zdm);
			// ��ѯվ�����
			try {
			String sql = "select jzcode from zhandian a  where  a.id='"+zdmc+"'";
			ResultSet rs;
				rs = db.queryAll(sql);
			String zdbm = "";
			System.out.println(sql);
			while (rs.next()) {
				zdbm = rs.getString(1);
				break;
			}
			// ������
			String dbbm = request.getParameter("dbbm").trim();
			String ydxs = request.getParameter("ydxs").trim();
			String zlfh = request.getParameter("zlfh").trim();
			String ktxs = request.getParameter("ktxs").trim();
			String biaoganvalue = request.getParameter("bg").trim();
			String yearmonth = request.getParameter("yearmonth").trim();
			// �������
			String sql1 ="update TBL_TT_BIAOGAN_LISHI set zdbm='"+zdbm+"',yearmonth='"+yearmonth+"',dbbm='"+dbbm+"',biaoganvalue='"+biaoganvalue+
					"',zlfh='"+zlfh+"',ydxs='"+ydxs+"',ktxs='"+ktxs+"',zdid='"+zdmc+"',dbid='"+zdmc+"'where id='"+id+"'";
			System.out.println(sql1);
			int  b = db.update(sql1);
			if(b==1){
            	msg="�޸ĳɹ���������תҳ�档";
            }else {
            	msg="�޸�ʧ�ܣ�����ϵ����Ա��";
            }
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/nomsg.jsp");
			} catch (DbException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}