package com.ptac.app.basisquery.electricdetail.action;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDao;
import com.ptac.app.basisquery.electricdetail.dao.ElecDetailDaoImp;
import com.ptac.app.inportuserzibaodian.util.Format;
/**
 * @see ��ѽ�����ϸ.Servlet
 * @author ZengJin 2014-1-16
 * update LiJing
 * updateRemark �޸�queryElectric()��������Ĳ���������ͨ������������SQL�Ĺ�������
 */
public class ElectricDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//------��ȡ��¼�˻���Ϣ------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		//------��ȡǰ̨��ť��ʶ------
		String command = request.getParameter("command");
		//------��ȡǰ̨��������------
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String cityaudit = request.getParameter("citystatus");//�м����״̬	
		String startentrytime = request.getParameter("entrytimeQS");//��ʼ¼��ʱ��
		String endentrytime = request.getParameter("entrytimeJS");//����¼��ʱ��
		String manualauditstatus = request.getParameter("manauditstatus");//�˹����״̬,
		String autoauditstatus = request.getParameter("autoauditstatus");//�Զ����״̬
		String zdsx = request.getParameter("zdsx1");
		String stationtype = request.getParameter("zdlx1");//վ������	
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		String jzname = request.getParameter("zdname");//վ������
		String id = request.getParameter("zdid");//վ��ID
		String entrytime = request.getParameter("entryTime1");//¼���·�
		String entrypensonnel = request.getParameter("entrypensonnel");//¼����Ա	
		String qyzt = request.getParameter("qyzt");//վ������״̬
		String dfzflx = request.getParameter("dfzflx");//���֧������
		String liuchenghao = request.getParameter("liuch");//���̺�
		String blhdl = request.getParameter("blhdl1");//�ĵ�������
		String actualpay = request.getParameter("dianfeidd");//��Ѵ���
		String gdfs = request.getParameter("gdfs");//���緽ʽ
		String kjyf = request.getParameter("KJYF");//�����·�(����·�)
		String dbname = request.getParameter("dbname");//�������
		String countryheadstatus = request.getParameter("countryheadstatus");// ������˱�־
		String sjzrsh = request.getParameter("sjzrsh");//�м��������״̬
		String beginTime = request.getParameter("beginTime");//��ʼ�����·�
		String endTime = request.getParameter("endTime");//���������·�
		//------���ݻ�ȡ��ֵƴ��sql��ѯ���-----
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}
		if (cityaudit != null && !cityaudit.equals("")
				&& !cityaudit.equals("2")) {
			whereStr = whereStr + " AND E.CITYAUDIT='" + cityaudit + "'";
		}
		if (startentrytime != null && !startentrytime.equals("")
				&& !startentrytime.equals("0")) {
			startentrytime = startentrytime + " 00:00:00";
			whereStr = whereStr + " AND TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='" + startentrytime + "'";
		}
		if (endentrytime != null && !endentrytime.equals("")
				&& !endentrytime.equals("0")) {
			endentrytime = endentrytime + " 24:00:00";
			whereStr = whereStr + " AND TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='" + endentrytime + "'";
		}
		if (manualauditstatus != null && !manualauditstatus.equals("")
				&& !manualauditstatus.equals("3")) {
			if ("1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='"
						+ manualauditstatus + "'";
			}
			if ("2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("0".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("-2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='"
						+ manualauditstatus + "'";
			}
			if ("-1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2' ";
			}
		}
		if (autoauditstatus != null && !autoauditstatus.equals("")
				&& !autoauditstatus.equals("2")) {
			whereStr = whereStr + " AND E.AUTOAUDITSTATUS='" + autoauditstatus + "'";
		}
		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND ZD.PROPERTY IN ("+zdsx+") ";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0")) {
			//if (!("��ѡ��").equals(stationtype)){
			whereStr = whereStr + " and ZD.STATIONTYPE IN(" + stationtype + ")";
			//}
		}
		
		
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {
			whereStr = whereStr + "AND D.DBQYZT='" + dbqyzt + "'";
		}
		if (jzname != null && !jzname.equals("") && !jzname.equals("0")) {
			whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
		}
		if (id != null && !id.equals("") && !id.equals("0")) {
			whereStr = whereStr + " AND ZD.ID='" + id + "'";
		}
		if (entrytime != null && entrytime != "" && !entrytime.equals("0")) {
			int months = 0;
			int years = 0;
			int month1 = Integer.parseInt(entrytime.substring(5, 7)) + 1;
			int year1 = Integer.parseInt(entrytime.substring(0, 4));
			if (month1 > 12) {
				years = year1 + 1;
				months = month1 - 12;
			} else {
				years = year1;
				months = month1;
			}
			String month = "";
			if (months < 10) {
				month = "0" + Integer.toString(months);
			} else {
				month = Integer.toString(months);
			}
			String year = Integer.toString(years);
			String entryTime2 = entrytime + "-01 00:00:00";
			String entryTime3 = year + "-" + month + "-01 00:00:00";
			whereStr = whereStr + " AND TO_CHAR(A.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='" + entryTime2 + "'"
					+ " AND TO_CHAR(A.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'" + entryTime3 + "'";
		}
		if (entrypensonnel != null && !entrypensonnel.equals("")
				&& !entrypensonnel.equals("0")) {
			whereStr = whereStr
					+ " AND (E.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"
					+ entrypensonnel + "'))";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + "AND ZD.QYZT='" + qyzt + "'";
		}
		if (dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")) {
			whereStr = whereStr + " AND D.DFZFLX='" + dfzflx + "'";
		}
		if (liuchenghao != null && liuchenghao != "" && !liuchenghao.equals("0")) {
			whereStr = whereStr + " AND E.LIUCHENGHAO = '" + liuchenghao + "'";
		}
		if (blhdl != null && !blhdl.equals("")) {
			whereStr = whereStr + " AND A.BLHDL >'" + blhdl + "'";
		}
		if (actualpay != null && !actualpay.equals("")) {
			whereStr = whereStr + " AND E.ACTUALPAY >'" + actualpay + "'";
		}
		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}
		if (kjyf != null && kjyf != "" && !kjyf.equals("0")) {
			whereStr = whereStr + " AND TO_CHAR(E.KJYF,'yyyy-mm') ='" + kjyf + "'";
		}
		if (dbname != null && !dbname.equals("") && !dbname.equals("0")) {
			whereStr = whereStr + " AND D.DBNAME LIKE '%" + dbname + "%'";
		}
		
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("5")) {
			whereStr = whereStr + " AND E.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}
		
		if (sjzrsh != null && !sjzrsh.equals("") && !sjzrsh.equals("-1")) {
			whereStr = whereStr + "AND E.CITYZRAUDITSTATUS='" + sjzrsh + "'";
		}
		if (beginTime != null && !beginTime.equals("") && !beginTime.equals("0")) {
			whereStr = whereStr + "AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')>='" + beginTime + "'";
		}
		if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
			whereStr = whereStr + "AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')<='" + endTime + "'";
		}
		//------�˱�־λ�ж�С���Ƿ�Ϊ��(��������ѡ������sql���)------
		String bzw = "";
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			bzw = "1";
		}
		//------��ý�����ͽ������------
		ElecDetailDao dao = new ElecDetailDaoImp();
		List<ElectricDetail> list = new ArrayList<ElectricDetail>();	
		list = dao.queryElectric(whereStr,loginId,bzw);//�����
		int num = list.size();//�������
		//------��õ����ϼƺ͵�Ѻϼ�------
		ElectricDetail total = new ElectricDetail();
		String totalelectric = "0";
		String totalmoney = "0";
		double df = 0;
		for (int i = 0; i < list.size(); i++) {
			try {
				df = df + Double.parseDouble(list.get(i).getActualpay());
				//System.out.println("��ѣ�"+df);
			} catch (Exception e) {

			}
		}
		BigDecimal bd_df = new BigDecimal(df);//ʵ�ʵ��
		String sum_df = bd_df.setScale(2, BigDecimal.ROUND_HALF_UP).toString();//ʵ�ʵ��
		
		if(list!=null){
			total = dao.total(list);
			totalelectric = Double.toString(total.getTotalelectric());//�����ϼ�	
	 		totalelectric = Format.str2(totalelectric);
		}
		
		
		//------��ǰ̨ҳ�洫ֵ------
		System.out.println("��Ѻϼƣ�"+sum_df);
		request.setAttribute("totalelectric", totalelectric);//�����ϼ�
		request.setAttribute("totalmoney", sum_df);//��Ѻϼ�
		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����
		//------����ǰ̨��ť��ʶ�ж��ύ����
		if("chaxun".equals(command)){//������ѯҳ��
			request.getRequestDispatcher("/web/appJSP/basisquery/electricdetail/eldetail.jsp")
			.forward(request, response);
		}else if("daochu".equals(command)){//��������ҳ��
			request.getRequestDispatcher("/web/appJSP/basisquery/electricdetail/�����Ϣ.jsp")
			.forward(request, response);
		}
	}
}
