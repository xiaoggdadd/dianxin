package com.ptac.app.inter.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.inter.bean.SjInterFace;
import com.ptac.app.inter.dao.InterfaceCheck;

public class getMessageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getMessageAction() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        String month = request.getParameter("month");
        if(month != null && !"".equals(month)){
        	int index = month.indexOf("-");
        	StringBuffer sb = new StringBuffer(month);
        	sb.replace(index, index+1, "");
        	month = sb.toString();
        }
		ArrayList<SjInterFace> faces = new ArrayList<SjInterFace>();
		InterfaceCheck interfaceCheck = new InterfaceCheck();
		faces = interfaceCheck.getSjMessage(month);
        request.setAttribute("list", faces);
        request.getRequestDispatcher("/web/appJSP/interface/interfaceManager.jsp").forward(request, response);
        
	}

}
