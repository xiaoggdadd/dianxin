package com.noki.jichuInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.noki.biaogan.BiaoganManage;
import com.noki.biaogan.model.BiaoganBean;

public class DianliangFenxiServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String zdid = request.getParameter("zdid");
		String year = request.getParameter("year");
		System.out.println("Õ¾µã±àºÅ£º"+zdid+" Äê·Ý£º"+year);
		DlFxmanage manage = new DlFxmanage();
		List<DianLiangBean> biaogans = manage.searchBiaoganChartByZdid(zdid,year);
		int countValue = 0;
		for (int i = 0; i < biaogans.size(); i++) {
			countValue += Integer.parseInt(biaogans.get(i).getBlhdl());
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
