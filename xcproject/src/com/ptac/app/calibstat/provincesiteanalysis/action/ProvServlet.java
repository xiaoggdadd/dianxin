package com.ptac.app.calibstat.provincesiteanalysis.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.provincesiteanalysis.bean.ProvBean;
import com.ptac.app.calibstat.provincesiteanalysis.dao.ProvDao;
import com.ptac.app.calibstat.provincesiteanalysis.dao.ProvDaoImp;

/**
 * @author �
 * @see ȫʡվ�㳬�����
 */
public class ProvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = "";//��
		String shi2 ="";//��������
		String property = request.getParameter("jzproperty");//վ������
		String zdlx = request.getParameter("zdlx");//վ������
		String year = request.getParameter("year") != null ? request.getParameter("year"): "";//���
		
		String command = request.getParameter("command");
		if("chaxun".equals(command) || "daochu".equals(command)){ 
			//����ǲ�ѯ  �Ͱ�shi ��������ó����Ž�session�� ��Ϊ��ѯ�����ͼ����Ϣ�����õ��Ĺ�������shi(��) �ǲ�һ����
			shi = request.getParameter("shi");
//			session.setAttribute("shi", shi);
		}else if("xiangqing".equals(command)){  
			//�����ͼ�������   ��ֱ�ӵ���xiangqing�������  �õ�dataString  ǰ̨��ͼ����ʾ ���ǿ��������
			 shi2 = request.getParameter("shi2");
			 String dataString = xiangQing(shi2,property,zdlx,year,loginId);
			 request.setAttribute("dataString", dataString );
		}
//		if(session.getAttribute("shi") == null){ 
//			//Ȼ���session�л�ȡshi ���û�� �ʹ�request�л�ȡ
//			shi = request.getParameter("shi");
//		}else{
//			shi = session.getAttribute("shi").toString();
//		}
		
		//�����ǲ�ѯ  ����ͼ������  ��Ҫ��ʾlist  �������queryExport���� ��������ִ��  ��ȡlist
		List<ProvBean> list = queryExport(shi,property,zdlx,year,loginId);
		request.setAttribute("list", list);
		request.setAttribute("num", list.size());
		
		if("chaxun".equals(command) || "xiangqing".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/provsiteanalysis/ProvSiteAnalysis.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/provsiteanalysis/ȫʡվ�㳬�����.jsp").forward(request, response);
		}
	}

	/**
	 * @see ȫʡվ�㳬����� ��ѯ������
	 */
	private List<ProvBean> queryExport(String shi, String property, String zdlx, String year, String loginId){
	
		String str = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			str = str + " AND ZD.SHI='" + shi + "'";
		}
		if (property != null && !property.equals("") && !property.equals("0")) {
			str = str + " AND ZD.ZDSX='" + property + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			str = str + " AND ZZ.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			str = str + " AND ZD.YEAR='" + year + "'";
		}
		
		ProvDao dao = new ProvDaoImp();
		List<ProvBean> list = dao.getQueryExport(str,loginId);
		return list;
	}

	/**
	 * @see ȫʡվ�㳬���������ͼ����Ϣ
	 */
	private String xiangQing(String shi, String property, String zdlx, String year, String loginId){
	
		String str = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			str = str + " AND ZD.SHI='" + shi + "'";
		}
		if (property != null && !property.equals("") && !property.equals("0")) {
			str = str + " AND ZD.ZDSX='" + property + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			str = str + " AND ZZ.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			str = str + " AND ZD.YEAR='" + year + "'";
		}
		
		ProvDao dao = new ProvDaoImp();
		ProvBean bean = new ProvBean();
		bean = dao.getXiangXi(str,loginId);
		String dataString = "";
		if(bean != null){
			dataString += "<chart caption='' subcaption='"+bean.getCity()+"' xAxisName='�·�' yAxisName='����' numberSuffix='%' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
			dataString += "<set label='1��' value='"+(bean.getCbbl01()==null?"":bean.getCbbl01().replace("%", ""))+"' />";
			dataString += "<set label='2��' value='"+(bean.getCbbl02()==null?"":bean.getCbbl02().replace("%", ""))+"' />";
			dataString += "<set label='3��' value='"+(bean.getCbbl03()==null?"":bean.getCbbl03().replace("%", ""))+"' />";
			dataString += "<set label='4��' value='"+(bean.getCbbl04()==null?"":bean.getCbbl04().replace("%", ""))+"' />";
			dataString += "<set label='5��' value='"+(bean.getCbbl05()==null?"":bean.getCbbl05().replace("%", ""))+"' />";
			dataString += "<set label='6��' value='"+(bean.getCbbl06()==null?"":bean.getCbbl06().replace("%", ""))+"' />";
			dataString += "<set label='7��' value='"+(bean.getCbbl07()==null?"":bean.getCbbl07().replace("%", ""))+"' />";
			dataString += "<set label='8��' value='"+(bean.getCbbl08()==null?"":bean.getCbbl08().replace("%", ""))+"' />";
			dataString += "<set label='9��' value='"+(bean.getCbbl09()==null?"":bean.getCbbl09().replace("%", ""))+"' />";
			dataString += "<set label='10��' value='"+(bean.getCbbl10()==null?"":bean.getCbbl10().replace("%", ""))+"' />";
			dataString += "<set label='11��' value='"+(bean.getCbbl11()==null?"":bean.getCbbl11().replace("%", ""))+"' />";
			dataString += "<set label='12��' value='"+(bean.getCbbl12()==null?"":bean.getCbbl12().replace("%", ""))+"' />";
			dataString += "</chart>";
		}
		return dataString;
	}
}
