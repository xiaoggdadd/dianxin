package com.noki.hetongguanli.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.hetongguanli.Dao.HetongDao;
import com.noki.hetongguanli.Model.HetongModel;

public class HetongManage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
	
		
		System.out.println("ָ�"+action);
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		String url = path+"/web/sdttWeb/hetongInfoManager/biaoganHistory.jsp",msg="";
		if(action.equals("1")){
			//���
			Integer nall =Integer.parseInt(request.getParameter("id"));
			int id = nall.intValue();
			System.out.println("��ͬ�޸�");
			HetongModel hetong = new HetongModel();
			ArrayList<HetongModel> al = new ArrayList<HetongModel>();
			HetongDao dao = new HetongDao();
			hetong.setSTARTTIME(request.getParameter("STARTTIME"));
			hetong.setENDTIME(request.getParameter("ENDTIME"));
			hetong.setPARTYA(request.getParameter("PARTYA"));
			hetong.setPARTYB(request.getParameter("PARTYB"));
			hetong.setCONTRACTNAME(request.getParameter("CONTRACTNAME"));
			hetong.setPROJECTAMONNT(request.getParameter("PROJECTAMONNT"));
			hetong.setCONTRACTDETAIL(request.getParameter("CONTRACTDETAIL"));
			al.add(hetong);
				boolean flag = dao.upcrease(al,id);
				if(flag){
					msg = "�޸ĳɹ�";
				}else{
					msg = "�޸ĳɹ�";
				}
			
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/nomsg.jsp");
								
			}
			
		else{
			System.out.println("��ͬ���");
			HetongModel hetong = new HetongModel();
			ArrayList<HetongModel> al = new ArrayList<HetongModel>();
			HetongDao dao = new HetongDao();
			hetong.setSTARTTIME(request.getParameter("STARTTIME"));
			hetong.setENDTIME(request.getParameter("ENDTIME"));
			hetong.setPARTYA(request.getParameter("PARTYA"));
			hetong.setPARTYB(request.getParameter("PARTYB"));
			hetong.setCONTRACTNAME(request.getParameter("CONTRACTNAME"));
			hetong.setPROJECTAMONNT(request.getParameter("PROJECTAMONNT"));
			hetong.setCONTRACTDETAIL(request.getParameter("CONTRACTDETAIL"));
			al.add(hetong);
			boolean flag = dao.increase(al);
			if(flag){
				msg = "��ӳɹ�";
			}else{
				msg = "���ʧ��";

		}

		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/nomsg.jsp");
	}
		}
	
	}


