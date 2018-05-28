package com.noki.zdqxkz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.zdqxkz.dao.ShiQuery;
import com.noki.zdqxkz.javabean.XxxgBean;
import com.noki.zdqxkz.javabean.Zdqxkz;

public class ShenHe extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    String action=request.getParameter("action");
		    response.setContentType("text/html;charset=utf-8");
		    request.setCharacterEncoding("utf-8");
		    String path = request.getContextPath();
		    PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
		   Account account = (Account)(request.getSession().getAttribute("account"));
		   String loginName = account.getAccountName();
		   String loginId = account.getAccountId();
		   if("butongguo".equals(action)){
			   String msg1="省审核不通过失败！";
			   ShiQuery sh=new ShiQuery();
			   Zdqxkz kz=new Zdqxkz();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   if("".equals(zdid) && "".equals(zdida)){
				   msg1="省审核不通过成功！";
			   }else{
				   ls=sh.QXseachSheng(zdid);
				   int a=sh.QXSHENGUpdateZD(zdida,ls,loginName,cause,zdid);
				   if(a>0){
					   msg1="省审核不通过成功！";
				   }else{
					   msg1="省审核不通过失败！";
				   }
			   }
			   session.setAttribute("url", url);
			   session.setAttribute("msg", msg1);
			   response.sendRedirect(path+"/web/msg.jsp");
		   } else if("butongguo1".equals(action)){
			   String msg1="省审核不通过失败！";
			   ShiQuery sh=new ShiQuery();
			   Zdqxkz kz=new Zdqxkz();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   ls=sh.QXseachSheng(zdid);
			   int a=sh.QXSHENGUpdateZD(zdida,ls,loginName,cause,zdid);
			  String m="";
			   if(a>0){
				   m="1";
			   }else{
				   m="0";
				 	session.setAttribute("url", url);
		            session.setAttribute("msg", msg1);
			   }
			   out.print(m);
		        out.close();
			  
		   }else if("quxiaopl".equals(action)){
			   String msg1 = "市取消审核失败";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
			   String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   String idd=request.getParameter("qskzid");
			  
			   ls=sh.QXserchShi(zdid,idd);
			   int j = sh.QXShiUpdateZD(zdid, ls,loginName,cause,idd);
			   String m="";
			   if(j>0){
				   m="1";  
			   }else{
				   m="0";
		           session.setAttribute("url", url);
		           session.setAttribute("msg", msg1);
			   } 
		        out.print(m);
		        out.close();
		   }else if("quxiao".equals(action)){
			   String msg1 = "市级取消审核失败！";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
			   String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   String idd=request.getParameter("qskzid");
			   if("".equals(zdid) && "".equals(idd)){
				   msg1="市取消审核成功！";
			   }else{
				   ls=sh.QXserchShi(zdid,idd);//查询信息修改前的老数据
				   int j=sh.QXShiUpdateZD(zdid, ls,loginName,cause,idd);//修改站点信息 还原成老数据
				   if(j>0){
					   msg1="市取消审核成功！";
				   }else{
					   msg1="市取消审核失败！";
				   }
			   }
				session.setAttribute("url", url);
				session.setAttribute("msg", msg1);
				response.sendRedirect(path+"/web/msg.jsp");
		   }else if("chehuipl".equals(action)){
			   String msg1 = "市审核通过失败！";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
				KtxsDao dao = new KtxsDao();
			 String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String qskzid=request.getParameter("qskzid");
			   ls=sh.seachShi(zdid,qskzid);
			   String lg=request.getParameter("lg");
			   if(null==lg){
				   lg="";
			   }
			   List<XxxgBean> xxxglist=new ArrayList<XxxgBean>();
			   xxxglist=dao.xxxgch();
			   List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = dao.getfwxs();
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = dao.getktxs();
				String syf="";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String sff=df.format(new Date());
			System.out.println("月份："+sff.toString());
			String yf=sff.toString().substring(5,7);
				if(yf.equals("01")){
					syf="YMONTH";
				}else if(yf.equals("02")){
					syf="EMONTH";
				}else if(yf.equals("03")){
					syf="SMONTH";
				}else if(yf.equals("04")){
					syf="SIMONTH";
				}else if(yf.equals("05")){
					syf="WMONTH";
				}else if(yf.equals("06")){
					syf="LMONTH";
				}else if(yf.equals("07")){
					syf="QMONTH";
				}else if(yf.equals("08")){
					syf="BMONTH";
				}else if(yf.equals("09")){
					syf="JMONTH";
				}else if(yf.equals("10")){
					syf="SHIMONTH";
				}else if(yf.equals("11")){
					syf="SYMONTH";
				}else if(yf.equals("12")){
					syf="SEMONTH";
				}
				String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
				for(Ktxs fw:fylist){
					fxxs=fw.getFxxs();
					bzw=fw.getFwsjbzw();
					System.out.println("标志位："+bzw);
					if(bzw.equals("1")){
						b1=fxxs;
					}else if(bzw.equals("2")){
						b2=fxxs;
					}else if(bzw.equals("3")){
						b3=fxxs;
					}
				}
				String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
				String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
				String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
				//接入网定义
				String dd3="";
				String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
				String dd18="",dd19="",dd20="",dd21="",dd22="";
				//乡镇支局
				String xx3="";
				String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
				String xx18="",xx19="",xx20="",xx21="",xx22="";
				//局用机房
				String jj3="";
				String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
				String jj18="",jj19="",jj20="",jj21="",jj22="";
				//营业网点
				String yy3="";
				String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
				String yy18="",yy19="",yy20="",yy21="",yy22="";
				//其他
				String qq3="";
				String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
				String qq18="",qq19="",qq20="",qq21="",qq22="";
				//IDC机房
				String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
				String ii18="",ii19="",ii20="",ii21="",ii22="";
				String ii3="";
				for(Ktxs kt:fylist1){
					bb1=kt.getKszlfh();
					bb2=kt.getJszlfh();
					bb4=kt.getSjbzw();
					bb3=kt.getJzktxs();//基站空调系数
					dd3=kt.getJrwktxs();//接入网的空调系数
					xx3=kt.getXzzjktxs();//乡镇支局的空调系数
					jj3=kt.getJyjfktxs();//局用机房空调系数
					yy3=kt.getYywdktxs();//营业网点空调系数
					ii3=kt.getIdcjfktxs();//IDC机房空调系数
					qq3=kt.getQtktxs();//其他空调系数
					if(bb4.equals("1")){
						//基站为bb
						bb5=bb1;
						bb6=bb2;
						bb7=bb3;
						//接入网为dd
						dd5=bb1;
						dd6=bb2;
						dd7=dd3;
						//乡镇支局
						xx5=bb1;
						xx6=bb2;
						xx7=xx3;
						//局用机房
						jj5=bb1;
						jj6=bb2;
						jj7=jj3;
						//营业网点
						yy5=bb1;
						yy6=bb2;
						yy7=yy3;
						//IDC机房
						ii5=bb1;
						ii6=bb2;
						ii7=ii3;
						//其他
						qq5=bb1;
						qq6=bb2;
						qq7=qq3;
						
						
					}
                     if(bb4.equals("2")){
                    	//基站为bb
						bb8=bb1;
						bb9=bb2;
						bb10=bb3;
						//接入网dd
						dd8=bb1;
						dd9=bb2;
						dd10=dd3;
						//乡镇支局
						xx8=bb1;
						xx9=bb2;
						xx10=xx3;
						//局用机房
						jj8=bb1;
						jj9=bb2;
						jj10=jj3;
						//营业网点
						yy8=bb1;
						yy9=bb2;
						yy10=yy3;
						//IDC机房
						ii8=bb1;
						ii9=bb2;
						ii10=ii3;
						//其他
						qq8=bb1;
						qq9=bb2;
						qq10=qq3;
						
					}
                      if(bb4.equals("3")){
                    	//基站为bb
                    	  bb11=bb1;
  						  bb12=bb2;
  						  bb13=bb3;
  						//接入网dd
  						  dd11=bb1;
  						  dd12=bb2;
  						  dd13=dd3;
  						//乡镇支局
  						xx11=bb1;
  						xx12=bb2;
  						xx13=xx3;
  						//====================
  					//局用机房
						jj11=bb1;
						jj12=bb2;
						jj13=jj3;
						//营业网点
						yy11=bb1;
						yy12=bb2;
						yy13=yy3;
						//IDC机房
						ii11=bb1;
						ii12=bb2;
						ii13=ii3;
						//其他
						qq11=bb1;
						qq12=bb2;
						qq13=qq3;
}
                     if(bb4.equals("4")){
                    	//基站为bb
                    	 bb14=bb1;
 						bb15=bb2;
 						bb16=bb3;
 						//接入网dd
 						dd14=bb1;
  						dd15=bb2;
  						dd16=dd3;
  					//乡镇支局
						xx14=bb1;
						xx15=bb2;
						xx16=xx3;
						//===========
						//局用机房
						jj14=bb1;
						jj15=bb2;
						jj16=jj3;
						//营业网点
						yy14=bb1;
						yy15=bb2;
						yy16=yy3;
						//IDC机房
						ii14=bb1;
						ii15=bb2;
						ii16=ii3;
						//其他
						qq14=bb1;
						qq15=bb2;
						qq16=qq3;
}
                     if(bb4.equals("5")){
                    	 //基站bb
                    	 bb17=bb1;
 						bb18=bb2;
 						bb19=bb3;
 						//接入网dd
 						 dd17=bb1;
  						dd18=bb2;
  						dd19=dd3;
  					//乡镇支局
						xx17=bb1;
						xx18=bb2;
						xx19=xx3;
						//==========
						//局用机房
						jj17=bb1;
						jj18=bb2;
						jj19=jj3;
						//营业网点
						yy17=bb1;
						yy18=bb2;
						yy19=yy3;
						//IDC机房
						ii17=bb1;
						ii18=bb2;
						ii19=ii3;
						//其他
						qq17=bb1;
						qq18=bb2;
						qq19=qq3;
}
                     if(bb4.equals("6")){
                    	 //基站bb
                    	 bb20=bb1;
 						bb22=bb3;
 						//==接入网dd
 						 dd20=bb1;
  						dd22=dd3;
  					//乡镇支局
						xx20=bb1;
						xx22=xx3;
						//=========
						//局用机房
						jj20=bb1;
						jj22=jj3;
						//营业网点
						yy20=bb1;
						yy22=yy3;
						//IDC机房
						ii20=bb1;
						ii22=ii3;
						//其他
						qq20=bb1;
						qq22=qq3;
}
				}
				System.out.println("11111111111111111111111111111111111------------------111111111111111111");
				 int j=sh.SHIUpdateZD(zdid,ls,syf,b1,b2,b3,
						   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
						   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
						   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
						   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
						   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
						   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
						   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,lg,xxxglist,loginName,qskzid);
			   String m="";
			   if(j>0){
				   m="1";
			   }else{
				   m="0"; 
				   msg1="市审核通过失败！";
		           session.setAttribute("url", url);
		           session.setAttribute("msg", msg1);
				  
			   }
			   out.print(m);
			   out.close();
		   }else if("chehui".equals(action)){
			   String msg1 = "市审核通过失败！";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
				KtxsDao dao = new KtxsDao();
			 String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String qskzid=request.getParameter("qskzid");
			   if("".equals(zdid) && "".equals(qskzid)){
				   msg1 = "市审核通过成功！";
			   }else{
			   ls=sh.seachShi(zdid,qskzid);
			   String lg=request.getParameter("lg");
			   if(null==lg){
				   lg="";
			   }
			   int i=0;
			   List<XxxgBean> xxxglist=new ArrayList<XxxgBean>();
			   xxxglist=dao.xxxgch();
			   List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = dao.getfwxs();
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = dao.getktxs();
				String syf="";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String sff=df.format(new Date());
			// String yf=	df.toString().substring(5, 7);
			System.out.println("月份："+sff.toString());
			String yf=sff.toString().substring(5,7);
				if(yf.equals("01")){
					syf="YMONTH";
				}else if(yf.equals("02")){
					syf="EMONTH";
				}else if(yf.equals("03")){
					syf="SMONTH";
				}else if(yf.equals("04")){
					syf="SIMONTH";
				}else if(yf.equals("05")){
					syf="WMONTH";
				}else if(yf.equals("06")){
					syf="LMONTH";
				}else if(yf.equals("07")){
					syf="QMONTH";
				}else if(yf.equals("08")){
					syf="BMONTH";
				}else if(yf.equals("09")){
					syf="JMONTH";
				}else if(yf.equals("10")){
					syf="SHIMONTH";
				}else if(yf.equals("11")){
					syf="SYMONTH";
				}else if(yf.equals("12")){
					syf="SEMONTH";
				}
				String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
				for(Ktxs fw:fylist){
					fxxs=fw.getFxxs();
					bzw=fw.getFwsjbzw();
					System.out.println("标志位："+bzw);
					if(bzw.equals("1")){
						b1=fxxs;
					}else if(bzw.equals("2")){
						b2=fxxs;
					}else if(bzw.equals("3")){
						b3=fxxs;
					}
				}
				String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
				String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
				String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
				//接入网定义
				String dd3="";
				String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
				String dd18="",dd19="",dd20="",dd21="",dd22="";
				//乡镇支局
				String xx3="";
				String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
				String xx18="",xx19="",xx20="",xx21="",xx22="";
				//局用机房
				String jj3="";
				String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
				String jj18="",jj19="",jj20="",jj21="",jj22="";
				//营业网点
				String yy3="";
				String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
				String yy18="",yy19="",yy20="",yy21="",yy22="";
				//其他
				String qq3="";
				String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
				String qq18="",qq19="",qq20="",qq21="",qq22="";
				//IDC机房
				String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
				String ii18="",ii19="",ii20="",ii21="",ii22="";
				String ii3="";
				for(Ktxs kt:fylist1){
					bb1=kt.getKszlfh();
					bb2=kt.getJszlfh();
					bb4=kt.getSjbzw();
					bb3=kt.getJzktxs();//基站空调系数
					dd3=kt.getJrwktxs();//接入网的空调系数
					xx3=kt.getXzzjktxs();//乡镇支局的空调系数
					jj3=kt.getJyjfktxs();//局用机房空调系数
					yy3=kt.getYywdktxs();//营业网点空调系数
					ii3=kt.getIdcjfktxs();//IDC机房空调系数
					qq3=kt.getQtktxs();//其他空调系数
					if(bb4.equals("1")){
						//基站为bb
						bb5=bb1;
						bb6=bb2;
						bb7=bb3;
						//接入网为dd
						dd5=bb1;
						dd6=bb2;
						dd7=dd3;
						//乡镇支局
						xx5=bb1;
						xx6=bb2;
						xx7=xx3;
						//局用机房
						jj5=bb1;
						jj6=bb2;
						jj7=jj3;
						//营业网点
						yy5=bb1;
						yy6=bb2;
						yy7=yy3;
						//IDC机房
						ii5=bb1;
						ii6=bb2;
						ii7=ii3;
						//其他
						qq5=bb1;
						qq6=bb2;
						qq7=qq3;
						
						
					}
                     if(bb4.equals("2")){
                    	//基站为bb
						bb8=bb1;
						bb9=bb2;
						bb10=bb3;
						//接入网dd
						dd8=bb1;
						dd9=bb2;
						dd10=dd3;
						//乡镇支局
						xx8=bb1;
						xx9=bb2;
						xx10=xx3;
						//局用机房
						jj8=bb1;
						jj9=bb2;
						jj10=jj3;
						//营业网点
						yy8=bb1;
						yy9=bb2;
						yy10=yy3;
						//IDC机房
						ii8=bb1;
						ii9=bb2;
						ii10=ii3;
						//其他
						qq8=bb1;
						qq9=bb2;
						qq10=qq3;
						
					}
                      if(bb4.equals("3")){
                    	//基站为bb
                    	  bb11=bb1;
  						  bb12=bb2;
  						  bb13=bb3;
  						//接入网dd
  						  dd11=bb1;
  						  dd12=bb2;
  						  dd13=dd3;
  						//乡镇支局
  						xx11=bb1;
  						xx12=bb2;
  						xx13=xx3;
  						//====================
  					//局用机房
						jj11=bb1;
						jj12=bb2;
						jj13=jj3;
						//营业网点
						yy11=bb1;
						yy12=bb2;
						yy13=yy3;
						//IDC机房
						ii11=bb1;
						ii12=bb2;
						ii13=ii3;
						//其他
						qq11=bb1;
						qq12=bb2;
						qq13=qq3;
}
                     if(bb4.equals("4")){
                    	//基站为bb
                    	 bb14=bb1;
 						bb15=bb2;
 						bb16=bb3;
 						//接入网dd
 						dd14=bb1;
  						dd15=bb2;
  						dd16=dd3;
  					//乡镇支局
						xx14=bb1;
						xx15=bb2;
						xx16=xx3;
						//===========
						//局用机房
						jj14=bb1;
						jj15=bb2;
						jj16=jj3;
						//营业网点
						yy14=bb1;
						yy15=bb2;
						yy16=yy3;
						//IDC机房
						ii14=bb1;
						ii15=bb2;
						ii16=ii3;
						//其他
						qq14=bb1;
						qq15=bb2;
						qq16=qq3;
}
                     if(bb4.equals("5")){
                    	 //基站bb
                    	 bb17=bb1;
 						bb18=bb2;
 						bb19=bb3;
 						//接入网dd
 						 dd17=bb1;
  						dd18=bb2;
  						dd19=dd3;
  					//乡镇支局
						xx17=bb1;
						xx18=bb2;
						xx19=xx3;
						//==========
						//局用机房
						jj17=bb1;
						jj18=bb2;
						jj19=jj3;
						//营业网点
						yy17=bb1;
						yy18=bb2;
						yy19=yy3;
						//IDC机房
						ii17=bb1;
						ii18=bb2;
						ii19=ii3;
						//其他
						qq17=bb1;
						qq18=bb2;
						qq19=qq3;
}
                     if(bb4.equals("6")){
                    	 //基站bb
                    	 bb20=bb1;
 						bb22=bb3;
 						//==接入网dd
 						 dd20=bb1;
  						dd22=dd3;
  					//乡镇支局
						xx20=bb1;
						xx22=xx3;
						//=========
						//局用机房
						jj20=bb1;
						jj22=jj3;
						//营业网点
						yy20=bb1;
						yy22=yy3;
						//IDC机房
						ii20=bb1;
						ii22=ii3;
						//其他
						qq20=bb1;
						qq22=qq3;
}
				}
				 int j=sh.SHIUpdateZD(zdid,ls,syf,b1,b2,b3,
						   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
						   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
						   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
						   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
						   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
						   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
						   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,lg,xxxglist,loginName,qskzid);
			  
			   if(j>0){
				   msg1="市审核通过成功！";
			   }else{
				   msg1="市审核通过失败！";
			   }
			}
				session.setAttribute("url", url);
				session.setAttribute("msg", msg1);
				response.sendRedirect(path+"/web/msg.jsp");
		   }else if("butongguosshpl".equals(action)){
			   String msg1 = "市审核不通过失败！";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   cause = cause.replace("'", "''");
			   String qskzid=request.getParameter("qskzid");
			   //ls=sh.seachShi(zdid,qskzid);
			  //  int j=sh.SHIUpdateZDssh(zdid, ls);更改到以前的数据
			   ls=sh.QXserchShi(zdid,qskzid);//查询信息修改前的老数据
			   int i=0;
				   i=sh.SHIUpdatebtg(zdid,ls,loginName,cause,qskzid);
				String m="";
			   if(i>0){
				  m="1";
			   }else{
				   m="0";
				   session.setAttribute("url", url);
				   session.setAttribute("msg", msg1);
			   }
			   out.print(m);
			   out.close();
		   }else if("butongguossh".equals(action)){
			   String msg1 = "市审核不通过成功！";
			   ShiQuery sh=new ShiQuery();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/zdzdqxcx.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
			   cause = cause.replace("'", "''");
			   String qskzid=request.getParameter("qskzid");
			   if("".equals(zdid) && "".equals(qskzid)){
				   msg1="市审核不通过成功！";
			   }else{
				   //ls=sh.seachShi(zdid,qskzid);
				  //  int j=sh.SHIUpdateZDssh(zdid, ls);更改到以前的数据
				   ls=sh.QXserchShi(zdid,qskzid);//查询信息修改前的老数据
				   int i=0;
					   i=sh.SHIUpdatebtg(zdid,ls,loginName,cause,qskzid);
				   if(i>0){
					   msg1="市审核不通过成功！";
				   }else{
					   msg1="市审核不通过失败！";
				   }
			   }
				session.setAttribute("url", url);
				session.setAttribute("msg", msg1);
				response.sendRedirect(path+"/web/msg.jsp");
		   }else if("chehuia".equals(action)){
				KtxsDao dao = new KtxsDao();
			   String msg1="省审核通过失败！";
			   ShiQuery sh=new ShiQuery();
			   String lg=request.getParameter("lg");
			   if(null==lg){
				   lg="";
			   }
			   Zdqxkz kz=new Zdqxkz();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   if("".equals(zdid) && "".equals(zdida)){
				   msg1="省审核通过成功！";
			   }else{
				   ls=sh.seachSheng(zdid);
					List<Ktxs> fylist = new ArrayList<Ktxs>();
					fylist = dao.getfwxs();
					List<Ktxs> fylist1 = new ArrayList<Ktxs>();
					fylist1 = dao.getktxs();
					double dyf;
					String syf="";
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
					//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
					String sff=df.format(new Date());
				// String yf=	df.toString().substring(5, 7);
				//System.out.println("月份："+sff.toString());
				String yf=sff.toString().substring(5,7);
					if(yf.equals("01")){
						syf="YMONTH";
					}else if(yf.equals("02")){
						syf="EMONTH";
					}else if(yf.equals("03")){
						syf="SMONTH";
					}else if(yf.equals("04")){
						syf="SIMONTH";
					}else if(yf.equals("05")){
						syf="WMONTH";
					}else if(yf.equals("06")){
						syf="LMONTH";
					}else if(yf.equals("07")){
						syf="QMONTH";
					}else if(yf.equals("08")){
						syf="BMONTH";
					}else if(yf.equals("09")){
						syf="JMONTH";
					}else if(yf.equals("10")){
						syf="SHIMONTH";
					}else if(yf.equals("11")){
						syf="SYMONTH";
					}else if(yf.equals("12")){
						syf="SEMONTH";
					}
					String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
					//System.out.println("长度："+fylist.size());
					for(Ktxs fw:fylist){
						fxxs=fw.getFxxs();
						bzw=fw.getFwsjbzw();
						//System.out.println("标志位："+bzw+"  fxxs:"+fxxs);
						if(bzw.equals("1")){
							b1=fxxs;
						}else if(bzw.equals("2")){
							b2=fxxs;
						}else if(bzw.equals("3")){
							b3=fxxs;
						}
					}
					String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
					String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
					String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
					//接入网定义
					String dd3="";
					String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
					String dd18="",dd19="",dd20="",dd21="",dd22="";
					//乡镇支局
					String xx3="";
					String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
					String xx18="",xx19="",xx20="",xx21="",xx22="";
					//局用机房
					String jj3="";
					String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
					String jj18="",jj19="",jj20="",jj21="",jj22="";
					//营业网点
					String yy3="";
					String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
					String yy18="",yy19="",yy20="",yy21="",yy22="";
					//其他
					String qq3="";
					String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
					String qq18="",qq19="",qq20="",qq21="",qq22="";
					//IDC机房
					String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
					String ii18="",ii19="",ii20="",ii21="",ii22="";
					String ii3="";
					for(Ktxs kt:fylist1){
						bb1=kt.getKszlfh();
						bb2=kt.getJszlfh();
						bb4=kt.getSjbzw();
						bb3=kt.getJzktxs();//基站空调系数
						dd3=kt.getJrwktxs();//接入网的空调系数
						xx3=kt.getXzzjktxs();//乡镇支局的空调系数
						jj3=kt.getJyjfktxs();//局用机房空调系数
						yy3=kt.getYywdktxs();//营业网点空调系数
						ii3=kt.getIdcjfktxs();//IDC机房空调系数
						qq3=kt.getQtktxs();//其他空调系数
						if(bb4.equals("1")){
							//基站为bb
							bb5=bb1;
							bb6=bb2;
							bb7=bb3;
							//接入网为dd
							dd5=bb1;
							dd6=bb2;
							dd7=dd3;
							//乡镇支局
							xx5=bb1;
							xx6=bb2;
							xx7=xx3;
							//局用机房
							jj5=bb1;
							jj6=bb2;
							jj7=jj3;
							//营业网点
							yy5=bb1;
							yy6=bb2;
							yy7=yy3;
							//IDC机房
							ii5=bb1;
							ii6=bb2;
							ii7=ii3;
							//其他
							qq5=bb1;
							qq6=bb2;
							qq7=qq3;
							
							
						}
	                     if(bb4.equals("2")){
	                    	//基站为bb
							bb8=bb1;
							bb9=bb2;
							bb10=bb3;
							//接入网dd
							dd8=bb1;
							dd9=bb2;
							dd10=dd3;
							//乡镇支局
							xx8=bb1;
							xx9=bb2;
							xx10=xx3;
							//局用机房
							jj8=bb1;
							jj9=bb2;
							jj10=jj3;
							//营业网点
							yy8=bb1;
							yy9=bb2;
							yy10=yy3;
							//IDC机房
							ii8=bb1;
							ii9=bb2;
							ii10=ii3;
							//其他
							qq8=bb1;
							qq9=bb2;
							qq10=qq3;
							
						}
	                      if(bb4.equals("3")){
	                    	//基站为bb
	                    	  bb11=bb1;
	  						  bb12=bb2;
	  						  bb13=bb3;
	  						//接入网dd
	  						  dd11=bb1;
	  						  dd12=bb2;
	  						  dd13=dd3;
	  						//乡镇支局
	  						xx11=bb1;
	  						xx12=bb2;
	  						xx13=xx3;
	  						//====================
	  					//局用机房
							jj11=bb1;
							jj12=bb2;
							jj13=jj3;
							//营业网点
							yy11=bb1;
							yy12=bb2;
							yy13=yy3;
							//IDC机房
							ii11=bb1;
							ii12=bb2;
							ii13=ii3;
							//其他
							qq11=bb1;
							qq12=bb2;
							qq13=qq3;
	}
	                     if(bb4.equals("4")){
	                    	//基站为bb
	                    	 bb14=bb1;
	 						bb15=bb2;
	 						bb16=bb3;
	 						//接入网dd
	 						dd14=bb1;
	  						dd15=bb2;
	  						dd16=dd3;
	  					//乡镇支局
							xx14=bb1;
							xx15=bb2;
							xx16=xx3;
							//===========
							//局用机房
							jj14=bb1;
							jj15=bb2;
							jj16=jj3;
							//营业网点
							yy14=bb1;
							yy15=bb2;
							yy16=yy3;
							//IDC机房
							ii14=bb1;
							ii15=bb2;
							ii16=ii3;
							//其他
							qq14=bb1;
							qq15=bb2;
							qq16=qq3;
	}
	                     if(bb4.equals("5")){
	                    	 //基站bb
	                    	 bb17=bb1;
	 						bb18=bb2;
	 						bb19=bb3;
	 						//接入网dd
	 						 dd17=bb1;
	  						dd18=bb2;
	  						dd19=dd3;
	  					//乡镇支局
							xx17=bb1;
							xx18=bb2;
							xx19=xx3;
							//==========
							//局用机房
							jj17=bb1;
							jj18=bb2;
							jj19=jj3;
							//营业网点
							yy17=bb1;
							yy18=bb2;
							yy19=yy3;
							//IDC机房
							ii17=bb1;
							ii18=bb2;
							ii19=ii3;
							//其他
							qq17=bb1;
							qq18=bb2;
							qq19=qq3;
	}
	                     if(bb4.equals("6")){
	                    	 //基站bb
	                    	 bb20=bb1;
	 						bb22=bb3;
	 						//==接入网dd
	 						 dd20=bb1;
	  						dd22=dd3;
	  					//乡镇支局
							xx20=bb1;
							xx22=xx3;
							//=========
							//局用机房
							jj20=bb1;
							jj22=jj3;
							//营业网点
							yy20=bb1;
							yy22=yy3;
							//IDC机房
							ii20=bb1;
							ii22=ii3;
							//其他
							qq20=bb1;
							qq22=qq3;
	}
					}
					
				   int  a =  sh.SHENGUpdateZD(zdida, ls,syf,b1,b2,b3,
						   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
						   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
						   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
						   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
						   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
						   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
						   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,lg,loginName,zdid);
				  
//				   if(a>0){
//					   b=sh.ShengUpdate(zdida, loginName,zdid);
//				   }
				   if(a>0){
					   msg1="省审核通过成功！";
				   }else{
					   msg1="省审核通过失败！";
				   }
				}
			   session.setAttribute("url", url);
				session.setAttribute("msg", msg1);
				response.sendRedirect(path+"/web/msg.jsp");
		   }else if("chehuia1".equals(action)){
				KtxsDao dao = new KtxsDao();
			   String msg1="省审核通过失败！";
			   ShiQuery sh=new ShiQuery();
			   String lg=request.getParameter("lg");
			   if(null==lg){
				   lg="";
			   }
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   ls=sh.seachSheng(zdid);
				List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = dao.getfwxs();
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = dao.getktxs();
				String syf="";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
				//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				String sff=df.format(new Date());
			// String yf=	df.toString().substring(5, 7);
			//System.out.println("月份："+sff.toString());
			String yf=sff.toString().substring(5,7);
				if(yf.equals("01")){
					syf="YMONTH";
				}else if(yf.equals("02")){
					syf="EMONTH";
				}else if(yf.equals("03")){
					syf="SMONTH";
				}else if(yf.equals("04")){
					syf="SIMONTH";
				}else if(yf.equals("05")){
					syf="WMONTH";
				}else if(yf.equals("06")){
					syf="LMONTH";
				}else if(yf.equals("07")){
					syf="QMONTH";
				}else if(yf.equals("08")){
					syf="BMONTH";
				}else if(yf.equals("09")){
					syf="JMONTH";
				}else if(yf.equals("10")){
					syf="SHIMONTH";
				}else if(yf.equals("11")){
					syf="SYMONTH";
				}else if(yf.equals("12")){
					syf="SEMONTH";
				}
				String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
				//System.out.println("长度："+fylist.size());
				for(Ktxs fw:fylist){
					fxxs=fw.getFxxs();
					bzw=fw.getFwsjbzw();
					//System.out.println("标志位："+bzw+"  fxxs:"+fxxs);
					if(bzw.equals("1")){
						b1=fxxs;
					}else if(bzw.equals("2")){
						b2=fxxs;
					}else if(bzw.equals("3")){
						b3=fxxs;
					}
				}
				String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
				String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
				String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
				//接入网定义
				String dd3="";
				String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
				String dd18="",dd19="",dd20="",dd21="",dd22="";
				//乡镇支局
				String xx3="";
				String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
				String xx18="",xx19="",xx20="",xx21="",xx22="";
				//局用机房
				String jj3="";
				String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
				String jj18="",jj19="",jj20="",jj21="",jj22="";
				//营业网点
				String yy3="";
				String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
				String yy18="",yy19="",yy20="",yy21="",yy22="";
				//其他
				String qq3="";
				String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
				String qq18="",qq19="",qq20="",qq21="",qq22="";
				//IDC机房
				String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
				String ii18="",ii19="",ii20="",ii21="",ii22="";
				String ii3="";
				for(Ktxs kt:fylist1){
					bb1=kt.getKszlfh();
					bb2=kt.getJszlfh();
					bb4=kt.getSjbzw();
					bb3=kt.getJzktxs();//基站空调系数
					dd3=kt.getJrwktxs();//接入网的空调系数
					xx3=kt.getXzzjktxs();//乡镇支局的空调系数
					jj3=kt.getJyjfktxs();//局用机房空调系数
					yy3=kt.getYywdktxs();//营业网点空调系数
					ii3=kt.getIdcjfktxs();//IDC机房空调系数
					qq3=kt.getQtktxs();//其他空调系数
					if(bb4.equals("1")){
						//基站为bb
						bb5=bb1;
						bb6=bb2;
						bb7=bb3;
						//接入网为dd
						dd5=bb1;
						dd6=bb2;
						dd7=dd3;
						//乡镇支局
						xx5=bb1;
						xx6=bb2;
						xx7=xx3;
						//局用机房
						jj5=bb1;
						jj6=bb2;
						jj7=jj3;
						//营业网点
						yy5=bb1;
						yy6=bb2;
						yy7=yy3;
						//IDC机房
						ii5=bb1;
						ii6=bb2;
						ii7=ii3;
						//其他
						qq5=bb1;
						qq6=bb2;
						qq7=qq3;
						
						
					}
                    if(bb4.equals("2")){
                   	//基站为bb
						bb8=bb1;
						bb9=bb2;
						bb10=bb3;
						//接入网dd
						dd8=bb1;
						dd9=bb2;
						dd10=dd3;
						//乡镇支局
						xx8=bb1;
						xx9=bb2;
						xx10=xx3;
						//局用机房
						jj8=bb1;
						jj9=bb2;
						jj10=jj3;
						//营业网点
						yy8=bb1;
						yy9=bb2;
						yy10=yy3;
						//IDC机房
						ii8=bb1;
						ii9=bb2;
						ii10=ii3;
						//其他
						qq8=bb1;
						qq9=bb2;
						qq10=qq3;
						
					}
                     if(bb4.equals("3")){
                   	//基站为bb
                   	  bb11=bb1;
 						  bb12=bb2;
 						  bb13=bb3;
 						//接入网dd
 						  dd11=bb1;
 						  dd12=bb2;
 						  dd13=dd3;
 						//乡镇支局
 						xx11=bb1;
 						xx12=bb2;
 						xx13=xx3;
 						//====================
 					//局用机房
						jj11=bb1;
						jj12=bb2;
						jj13=jj3;
						//营业网点
						yy11=bb1;
						yy12=bb2;
						yy13=yy3;
						//IDC机房
						ii11=bb1;
						ii12=bb2;
						ii13=ii3;
						//其他
						qq11=bb1;
						qq12=bb2;
						qq13=qq3;
}
                    if(bb4.equals("4")){
                   	//基站为bb
                   	 bb14=bb1;
						bb15=bb2;
						bb16=bb3;
						//接入网dd
						dd14=bb1;
 						dd15=bb2;
 						dd16=dd3;
 					//乡镇支局
						xx14=bb1;
						xx15=bb2;
						xx16=xx3;
						//===========
						//局用机房
						jj14=bb1;
						jj15=bb2;
						jj16=jj3;
						//营业网点
						yy14=bb1;
						yy15=bb2;
						yy16=yy3;
						//IDC机房
						ii14=bb1;
						ii15=bb2;
						ii16=ii3;
						//其他
						qq14=bb1;
						qq15=bb2;
						qq16=qq3;
}
                    if(bb4.equals("5")){
                   	 //基站bb
                   	 bb17=bb1;
						bb18=bb2;
						bb19=bb3;
						//接入网dd
						 dd17=bb1;
 						dd18=bb2;
 						dd19=dd3;
 					//乡镇支局
						xx17=bb1;
						xx18=bb2;
						xx19=xx3;
						//==========
						//局用机房
						jj17=bb1;
						jj18=bb2;
						jj19=jj3;
						//营业网点
						yy17=bb1;
						yy18=bb2;
						yy19=yy3;
						//IDC机房
						ii17=bb1;
						ii18=bb2;
						ii19=ii3;
						//其他
						qq17=bb1;
						qq18=bb2;
						qq19=qq3;
}
                    if(bb4.equals("6")){
                   	 //基站bb
                   	 bb20=bb1;
						bb22=bb3;
						//==接入网dd
						 dd20=bb1;
 						dd22=dd3;
 					//乡镇支局
						xx20=bb1;
						xx22=xx3;
						//=========
						//局用机房
						jj20=bb1;
						jj22=jj3;
						//营业网点
						yy20=bb1;
						yy22=yy3;
						//IDC机房
						ii20=bb1;
						ii22=ii3;
						//其他
						qq20=bb1;
						qq22=qq3;
}
				}
				
			   int  a =  sh.SHENGUpdateZD(zdida, ls,syf,b1,b2,b3,
					   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
					   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
					   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
					   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
					   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
					   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
					   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,lg,loginName,zdid);
			  
//			   if(a>0){
//				   b=sh.ShengUpdate(zdida, loginName,zdid);
//			   }
			   String m="";
			   if(a>0){
				   m="1";
			   }else{
				   m="0";
				   msg1="省审核通过失败！";
				   session.setAttribute("url", url);
				   session.setAttribute("msg", msg1);
			   }
			   out.print(m);
		        out.close();
			
		   }else if("chehuicc".equals(action)){
			   String msg1="省审核撤销失败！";
			   ShiQuery sh=new ShiQuery();
			   Zdqxkz kz=new Zdqxkz();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   if("".equals(zdid) && "".equals(zdida)){
				   msg1="省审核撤销成功！";
			   }else{
				   ls=sh.old(zdida,zdid);//查询信息修改申请时的老数据
				   int a=sh.fgzd(zdida, ls,loginName,zdid);
				   if(a>0){
					   msg1="省审核撤销成功！";
				   }else{
					   msg1="省审核撤销失败！";
				   }
			   }
			   session.setAttribute("url", url);
				session.setAttribute("msg", msg1);
				response.sendRedirect(path+"/web/msg.jsp");
		   }else if("chehuicc1".equals(action)){
			   String msg1="省审核撤销失败！";
			   ShiQuery sh=new ShiQuery();
			   Zdqxkz kz=new Zdqxkz();
			   List<Zdqxkz> ls=null;
			 String url = path+ "/web/zdqxkz/shengshenhe.jsp";
			   String zdid=request.getParameter("chooseIdStr");
			   String zdida=request.getParameter("chooseIdStr1");
			   ls=sh.old(zdida,zdid);//查询信息修改申请时的老数据
			   int a=sh.fgzd(zdida, ls,loginName,zdid);
			   String m="";
			   if(a>0){
				   m="1";
			   }else{
				   m="0";
				   msg1="省审核撤销失败！";
				   session.setAttribute("url", url);
				   session.setAttribute("msg", msg1);
			   }
			   out.print(m);
		        out.close();
				
		   }
	}


}
