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
 * @省级供电方式分类查询
 * @显示列
 * 城市	  总站点数量	直供电站点数量	转供电站点数量	总金额	
 * 直供电金额(万元)	转供电金额(万度)	直供电电量(万度)	转供电电量(万度)
 * update name:zhouxue  time :2014.06.06 加过滤条件 费用属性：收入（费用小于0），支出（费用大于0）
 * 审核状态改为：业务审核通过（市级主任审核通过），财务审核通过
 * 站点总数：是根据结束报账月判断，人工站点审核通过时间小于等于 结束报账月份
 * */
public class GdfsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {doPost(request,response);}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getContextPath();
		String action = request.getParameter("command")!=null?request.getParameter("command"):"";//接收处理方法
		System.out.println("action:"+action);
			String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
		String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";//市级主任审核状态
		String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态
		String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态
		String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"";//站点属性
		String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";//报账月份(起)
		String jstime = request.getParameter("jstime")!=null?request.getParameter("jstime"):"";//报账月份（止）
		String fysx = request.getParameter("fysx")!=null?request.getParameter("fysx"):"";//费用属性
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
//------获得结果集和结果条数------
GdfsDaoImp dao = new GdfsDaoImp();
List<GdfsBean> list = new ArrayList<GdfsBean>();	
list = dao.queryGdfs(whereStr, loginId,str);//结果集
int num = list.size();//结果条数
GdfsBean total=new GdfsBean();
//------总站点数量合计/直供电站点数量合计/转供电站点数量合计/总金额合计/直供电金额合计/转供电金额合计/直供电电量合计/转供电电量合计------
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
request.setAttribute("num", num);//结果条数
request.setAttribute("list", list);//结果集

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
request.setAttribute("zdzlhj", s);//站点总数合计
request.setAttribute("zgdzdhj", s1);//直供电总数合计
request.setAttribute("zhgdzdhj", s2);//转供电总数合计
request.setAttribute("moneyhj", s3);//总金额合计
request.setAttribute("zgdmoneyhj", s4);//直供电金额合计
request.setAttribute("zhgdmoneyhj", s5);//转供电金额合计
request.setAttribute("zgddlhj", s6);//直供电电量合计
request.setAttribute("zhgddlhj", s7);//转供电电量合计
//------根据前台按钮标识判断提交方向
if("chaxun".equals(action)){//传到查询页面
	request.getRequestDispatcher("/web/appJSP/tongji/gdfstj.jsp")
	.forward(request, response);
}else if("daochu".equals(action)){//传到导出页面
	request.getRequestDispatcher("/web/appJSP/tongji/供电方式统计汇总表.jsp")
	.forward(request, response);
}
	}

}
