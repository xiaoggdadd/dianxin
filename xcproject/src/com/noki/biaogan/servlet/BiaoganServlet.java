package com.noki.biaogan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.noki.biaogan.BiaoganManage;
import com.noki.biaogan.model.BiaoganBean;
import com.noki.zwhd.model.ZhandianBean;

public class BiaoganServlet extends HttpServlet {

	private static org.apache.commons.logging.Log log = LogFactory
			.getLog(BiaoganServlet.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sid = request.getParameter("sid");
		String qxid = request.getParameter("qxid");

		String zdid = request.getParameter("zdid");
		String year = request.getParameter("year");
		log.info("城市：" + sid + "区县：" + qxid + "站点编号：" + zdid + " 年份：" + year);

		int zdSize = 0;
		String resultString = "[";
		JSONObject objValue = new JSONObject();
		JSONArray zdValues = new JSONArray();
		BiaoganManage manage = new BiaoganManage();
		if (zdid != null && !zdid.equals("") && !zdid.equals("null")) {
//			zdSize = 1;
//			List<BiaoganBean> biaogans = manage.searchBiaoganChartByZdid(zdid,
//					year);
//			int countValue = 0;
//			for (int i = 0; i < biaogans.size(); i++) {
//				countValue += Double.parseDouble(biaogans.get(i)
//						.getBIAOGANVALUE());
//			}
//			int pjValue = countValue / 12;
//			objValue.put("zdSize", zdSize);
//			objValue.put("pjValue", pjValue);
//			JSONArray value = JSONArray.fromObject(biaogans);
//			zdValues.add(value);
//			objValue.put("zdValues", zdValues);
		} else {
			List<ZhandianBean> zhandianList = manage.searchZdListByXian(qxid);
			zdSize = zhandianList.size();
			String zd = "";
			for (int i = 0; i < zhandianList.size(); i++) {
				String _zdbm = zhandianList.get(i).getJZCODE();
				String _zdmc = zhandianList.get(i).getJZNAME();
				List<BiaoganBean> biaogans = manage.searchBiaoganChartByZdid(
						_zdbm, year);
				String zdValue = "[";
				for (int j = 0; j < biaogans.size(); j++) {
					BiaoganBean biaogan = biaogans.get(j);
					String biaoganValue = biaogan.getBIAOGANVALUE();
					zdValue += biaoganValue + ",";
				}
				zdValue = zdValue.substring(0, zdValue.length() - 1);
				zdValue += "]";

				zd += "{name:'" + _zdmc + "',";
				zd += "data:" + zdValue;
				zd += "},";
			}
			if(zd.length()>0){
				zd = zd.substring(0, zd.length() - 1);
			}
			
			resultString += zd;
		}
		resultString += "]";
		log.info(resultString);
		response.getWriter().write(resultString);
	}

}
