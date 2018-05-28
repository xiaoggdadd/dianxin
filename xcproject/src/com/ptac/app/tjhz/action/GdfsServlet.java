package com.ptac.app.tjhz.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptac.app.tjhz.bean.GdfsBean;
import com.ptac.app.tjhz.dao.GdfsDaoImp;
/*
 * @ʡ�����緽ʽ�����ѯ
 * @��ʾ��
 * ����	  ��վ������	ֱ����վ������	ת����վ������	�ܽ��	
 * ֱ������(��Ԫ)	ת������(���)	ֱ�������(���)	ת�������(���)
 * update name:zhouxue  time :2014.06.06 �ӹ������� �������ԣ����루����С��0����֧�������ô���0��
 * ���״̬��Ϊ��ҵ�����ͨ�����м��������ͨ�������������ͨ��
 * վ���������Ǹ��ݽ����������жϣ��˹�վ�����ͨ��ʱ��С�ڵ��� ���������·�
 * */
public class GdfsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getContextPath();
		String action = request.getParameter("command")!=null?request.getParameter("command"):"";//���մ�����
		System.out.println("action:"+action);
			String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//��
		String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";//�м��������״̬
		String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//�������״̬
		String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//վ������״̬
		String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"";//վ������
		String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";//�����·�(��)
		String jstime = request.getParameter("jstime")!=null?request.getParameter("jstime"):"";//�����·ݣ�ֹ��
		String fysx = request.getParameter("fysx")!=null?request.getParameter("fysx"):"";//��������
		String whereStr = "",str="";
		if(!"".equals(shi)&&!"0".equals(shi)){
			whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
			str = str+" AND Z.SHI='"+shi+"'";
		}
		
		if("1".equals(citystatus)){
			whereStr=whereStr+" AND E.CITYZRAUDITSTATUS='"+citystatus+"'";
		}else{
			whereStr=whereStr+" AND E.MANUALAUDITSTATUS='"+citystatus+"'";
		}
		if(!"".equals(fysx)&&!"2".equals(fysx)){
			if("0".equals(fysx)){
				whereStr=whereStr+" AND E.ACTUALPAY<'0'";
			}else {
				whereStr=whereStr+" AND E.ACTUALPAY>'0'";
			}
		}
		
		if(!"".equals(dbqyzt)&&!"-1".equals(dbqyzt)){
			whereStr=whereStr+" AND D.DBQYZT='"+dbqyzt+"'";
		}
		if(!"".equals(qyzt)&&!"-1".equals(qyzt)){
			whereStr=whereStr+" AND ZD.QYZT='"+qyzt+"'";
			str=str+" AND Z.QYZT='"+qyzt+"'";
		}
		if(!"".equals(jzproperty)&&!"0".equals(jzproperty)){
			whereStr=whereStr+" AND ZD.PROPERTY='"+jzproperty+"'";
			str=str+" AND Z.PROPERTY='"+jzproperty+"'";
		}
		if(!"".equals(bztime)&&bztime != null&&!"".equals(jstime)&&jstime != null){
			whereStr=whereStr+" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')>='"+bztime+"'";
			whereStr=whereStr+" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')<='"+jstime+"'";
			//whereStr=whereStr+" AND SUBSTR(ZD.MANUALAUDITDATETIME_STATION, 0, 7)<='"+jstime+"'";
			//str=str+" AND SUBSTR(Z.MANUALAUDITDATETIME_STATION, 0, 7)<='"+jstime+"'";
		}
//------��ý�����ͽ������------
GdfsDaoImp dao = new GdfsDaoImp();
List<GdfsBean> list = new ArrayList<GdfsBean>();	
list = dao.queryGdfs(whereStr, loginId,str);//�����
int num = list.size();//�������
GdfsBean total=new GdfsBean();
//------��վ�������ϼ�/ֱ����վ�������ϼ�/ת����վ�������ϼ�/�ܽ��ϼ�/ֱ������ϼ�/ת������ϼ�/ֱ��������ϼ�/ת��������ϼ�------
double zdzlhj=0,zgdzdhj=0,zhgdzdhj=0,moneyhj=0,zgdmoneyhj=0,zhgdmoneyhj=0,zgddlhj=0,zhgddlhj=0;
if(list!=null){
	total = dao.total(list);
	zdzlhj=Double.parseDouble(total.getZdzlhj());
	zgdzdhj=Double.parseDouble(total.getZgdzdhj());
	zhgdzdhj=Double.parseDouble(total.getZhgdzdhj());
	moneyhj=Double.parseDouble(total.getMoneyhj());
	zgdmoneyhj=Double.parseDouble(total.getZgdmoneyhj());
	zhgdmoneyhj=Double.parseDouble(total.getZhgdmoneyhj());
	zgddlhj=Double.parseDouble(total.getZgddlhj());
	zhgddlhj=Double.parseDouble(total.getZhgddlhj());
}
request.setAttribute("num", num);//�������
request.setAttribute("list", list);//�����

String zl=zdzlhj+"";
DecimalFormat   df   =new   DecimalFormat("0");  
String s=df.format(zdzlhj);
String s1=df.format(zgdzdhj);
String s2=df.format(zhgdzdhj);
DecimalFormat   dff   =new   DecimalFormat("0.00");
String s3=dff.format(moneyhj);
String s4=dff.format(zgdmoneyhj);
String s5=dff.format(zhgdmoneyhj);
String s6=dff.format(zgddlhj);
String s7=dff.format(zhgddlhj);
request.setAttribute("zdzlhj", s);//վ�������ϼ�
request.setAttribute("zgdzdhj", s1);//ֱ���������ϼ�
request.setAttribute("zhgdzdhj", s2);//ת���������ϼ�
request.setAttribute("moneyhj", s3);//�ܽ��ϼ�
request.setAttribute("zgdmoneyhj", s4);//ֱ������ϼ�
request.setAttribute("zhgdmoneyhj", s5);//ת������ϼ�
request.setAttribute("zgddlhj", s6);//ֱ��������ϼ�
request.setAttribute("zhgddlhj", s7);//ת��������ϼ�
//------����ǰ̨��ť��ʶ�ж��ύ����
if("chaxun".equals(action)){//������ѯҳ��
	request.getRequestDispatcher("/web/appJSP/tongji/gdfstj.jsp")
	.forward(request, response);
}else if("daochu".equals(action)){//��������ҳ��
	request.getRequestDispatcher("/web/appJSP/tongji/���緽ʽͳ�ƻ��ܱ�.jsp")
	.forward(request, response);
}
	}

}
