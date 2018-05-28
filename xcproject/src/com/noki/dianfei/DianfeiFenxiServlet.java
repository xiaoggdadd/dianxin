package com.noki.dianfei;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




public class DianfeiFenxiServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String zdid = request.getParameter("zdid");
		String year = request.getParameter("year");
		System.out.println("վ���ţ�"+zdid+" ��ݣ�"+year);
		Dfmanager manage = new Dfmanager();
		List<DianfeiBean> biaogans = manage.searchDianfeiChartByZdid(zdid,year);
		int countValue = 0;
		for (int i = 0; i < biaogans.size(); i++) {
			countValue += Double.parseDouble(biaogans.get(i).getbeilv().trim());
		}
		int pjValue = countValue/12;
		JSONObject objValue = new JSONObject();
		objValue.put("pjValue", pjValue);
		JSONArray value = JSONArray.fromObject(biaogans);
		objValue.put("value", value);
		System.out.println(objValue.toString());
		response.getWriter().write(objValue.toString());
	}
}
