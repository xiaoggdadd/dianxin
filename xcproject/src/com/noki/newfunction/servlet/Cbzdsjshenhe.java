package com.noki.newfunction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.dao.cbzddao;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.CTime;
import com.noki.zdqxkz.javabean.XxxgBean;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class Cbzdsjshenhe extends HttpServlet{
	 private static final String Content_type = "text/html;charset=UTF-8";
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	      IOException, ServletException ,ParseException {
		  
		    resp.setContentType(Content_type);
		    PrintWriter out = resp.getWriter();
		    String path = req.getContextPath();
		    DbLog log = new DbLog();
		    Account account = new Account();
		    //AmmeterDegreeFormBean formBean= new AmmeterDegreeFormBean();
		    String url = path + "/web/jzcbnewfunction/cbzdshishenhe.jsp", msg = "";
		    HttpSession session = req.getSession();
		    account = (Account) session.getAttribute("account");
		    cbzddao bean = new cbzddao();
		    String action = req.getParameter("action");
		    String personnal=account.getAccountName();//审核人员
		    Date currentTime = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateString = formatter.format(currentTime);
		    if (action.equals("cbzdssh")) {
		    	String lg=personnal;
		    	String chooseIdStr1 = req.getParameter("zdid");
		    	String chooseIdStr2=req.getParameter("chooseIdStr1");
		    	System.out.println("zdid:"+chooseIdStr1);
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshishenhe.jsp" ;//scbzdjd
		        KtxsDao dao = new KtxsDao();
		       //----------
		        List<XxxgBean> xxxglist=new ArrayList<XxxgBean>();
				   xxxglist=dao.xxxgch();
		        List<Zdinfo> ls=null;
		        ls=bean.seachSheng(chooseIdStr1,chooseIdStr2);
		        List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = dao.getfwxs();
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = dao.getktxs();
				double dyf;
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
				int j=0;
				 msg = bean.CbCityFees(chooseIdStr2,"1",loginName);
				 j=bean.SHIUpdateZDZ(chooseIdStr1,chooseIdStr2,ls,syf,b1,b2,b3,
						   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
						   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
						   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
						   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
						   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
						   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
						   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,lg,xxxglist);
				
				 if(msg.equals("市级审核通过！")||j>0){
					 msg="市级审核通过！";
			       
				 }
		           
		            log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点市级审核"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");

		    }else if(action.equals("cbzdsshno")){
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshishenhe.jsp" ;
		        msg = bean.CbCityFeesNo(chooseIdStr1,"0",loginName);
		        log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点市级审核不通过"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("cbzdsshqx")){
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshishenhe.jsp" ;
		        msg = bean.CbCityFeesqx(chooseIdStr1,"0",loginName);
		        log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点市级审核取消审核"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("scbzdzg")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshengshenhe.jsp" ;
		            msg = bean.Cbshengtg(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点省级审核整改"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("scbzdno")){
		    	//省审核不通过
		    	String sj = req.getParameter("sj");
		    	String yj = req.getParameter("yj");
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName"); 
		        url = path + "/web/msg.jsp" ;//   /jzcbnewfunction/cbzdshengshenhe.jsp
		            msg = bean.Cbshengno(chooseIdStr1,"1",loginName,sj,yj);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点省级审核退单"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg_sh.jsp");
		    	
		    	
		    	
		    }else if(action.equals("shicbzdno")){
		    	//省审核不通过
		    	String sj = req.getParameter("entrytimeQS");
		    	String yj = req.getParameter("yj");
//		    	System.out.println("-----"+yj);
//		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName"); 
		    	String chooseIdStr1 = req.getParameter("ida");
		    	//System.out.println("-----"+sj);
		    	//System.out.println("-----"+yj);
		    	//System.out.println("-----"+loginName);
		    	//System.out.println("-----"+chooseIdStr1);
		        url = path + "/web/msg.jsp" ;//   /jzcbnewfunction/cbzdshengshenhe.jsp
		            msg = bean.Cbshino(chooseIdStr1,"1",loginName,sj,yj);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点省级审核退单"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg_sh.jsp");
		    	
		    	
		    	
		    }else if(action.equals("scbzdjdxh")){//省级审核批量循环 使用
		    	//省审核
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshengshenhe.jsp" ;
		        String zdid=req.getParameter("zdid1"); 
		        String chooseIdStr2=req.getParameter("chooseIdStr1");
		        List<Zdinfo> ls=null;
		      //获取超标站点详细信息
		        ls=bean.seachShengb(zdid);
		        String cbsj=req.getParameter("cbsj");//对标月份
		         msg = bean.Cbshengtd(chooseIdStr1,"1",loginName,ls);//更新站点属性信息和全省定标电量，更新生产标表里的生产标
		       
		            cbzddao dao=new cbzddao();
		            KtxsDao dao1=new KtxsDao();
		            CTime ctime = new CTime(); 
		            Date date = new Date();
		            String thismonth = ctime.formatWholeDate(date);//当前月份
		        	DecimalFormat mat = new DecimalFormat("0.00");
		            int mon=0; int mon1;String yf="";
		            mon1=Integer.parseInt(thismonth.substring(5,7)); //系统当前月份
		            if(cbsj.length()>0){
		            mon=Integer.parseInt(cbsj.substring(5,7));//前台对标月份
		            }else{
		            	mon=mon1;
		            }
		            //获取2个时间
		          
		            List<Ktxs> fylist1 = new ArrayList<Ktxs>();
		          
		            // 查询空调系数
		            fylist1 =dao1.getktxs();
		        	String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "",yywd = "", id1 = "", zlfh = "";
					String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "",yywd1="", id11 = "", zlfh1 = "";
					String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "",yywd2="", id12 = "", zlfh2 = "";
					String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "",yywd3="", id13 = "", zlfh3 = "";
					String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "",yywd4="", id14 = "", zlfh4 = "";
					String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "",yywd5="", id15 = "", zlfh5 = "";
					String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "",yywd6="", id16 = "", zlfh6 = "";
					
		           if(fylist1!=null){
		        	   for (Ktxs bean2 : fylist1) {
							id1 = bean2.getKtxsid();
							kszlfh = bean2.getKszlfh();
							jszlfh = bean2.getJszlfh();
							jz = bean2.getJzktxs();
							jrw = bean2.getJrwktxs();
							xzzj = bean2.getXzzjktxs();
							jyjf = bean2.getJyjfktxs();
							qt = bean2.getQtktxs();
							idcjf = bean2.getIdcjfktxs();
							yywd = bean2.getYywdktxs();
							jz = mat.format(Double.parseDouble(jz));
							jrw = mat.format(Double.parseDouble(jrw));
							xzzj = mat.format(Double.parseDouble(xzzj));
							jyjf = mat.format(Double.parseDouble(jyjf));
							qt = mat.format(Double.parseDouble(qt));
							idcjf = mat.format(Double.parseDouble(idcjf));
							yywd = mat.format(Double.parseDouble(yywd));
							//规矩自增id 判断各大属性的系数（如果以后表结构有改动或自增id有增加修改 此处也需要修改）
							if (id1.equals("1")) {
								kszlfh1 = kszlfh;
								jszlfh1 = jszlfh;
								jz1 = jz;
								jrw1 = jrw;
								xzzj1 = xzzj;
								jyjf1= jyjf;
								qt1 = qt;
								idcjf1 = idcjf;
								yywd1 = yywd;
							} else if (id1.equals("2")) {
								kszlfh2 = kszlfh;
								jszlfh2 = jszlfh;
								jz2 = jz;
								jrw2 = jrw;
								xzzj2 = xzzj;
								jyjf2 = jyjf;
								qt2 = qt;
								idcjf2 = idcjf;
								yywd2 = yywd;
							} else if (id1.equals("3")) {
								kszlfh3 = kszlfh;
								jszlfh3 = jszlfh;
								jz3 = jz;
								jrw3 = jrw;
								xzzj3 = xzzj;
								jyjf3 = jyjf;
								qt3 = qt;
								idcjf3 = idcjf;
								yywd3 = yywd;
							} else if (id1.equals("4")) {
								kszlfh4 = kszlfh;
								jszlfh4 = jszlfh;
								jz4 = jz;
								jrw4 = jrw;
								xzzj4 = xzzj;
								jyjf4 = jyjf;
								qt4 = qt;
								idcjf4 = idcjf;
								yywd4 = yywd;
							} else if (id1.equals("5")) {
								kszlfh5 = kszlfh;
								jszlfh5 = jszlfh;
								jz5 = jz;
								jrw5 = jrw;
								xzzj5 = xzzj;
								jyjf5 = jyjf;
								qt5 = qt;
								idcjf5 = idcjf;
								yywd5 = yywd;
							} else if (id1.equals("6")) {
								kszlfh6 = kszlfh;
								jz6 = jz;
								jrw6 = jrw;
								xzzj6 = xzzj;
								jyjf6 = jyjf;
								qt6 = qt;
								idcjf6 = idcjf;
								yywd6 = yywd;
							}
						}
		           }
		        	   List<Ktxs> fylist = new ArrayList<Ktxs>();
		        	   //查询房屋类型系数和基础系数（1）
						fylist = dao1.getfwxs();
						String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
						String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
						String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
						String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
						if (fylist != null) {
							for (Ktxs bean3 : fylist) {
								fwid = bean3.getFwxsid();
								fwlx = bean3.getYfname();
								xs = bean3.getFxxs();
								jcxs = bean3.getJcxs();
								fwbzw = bean3.getFwsjbzw();
								xs = mat.format(Double.parseDouble(xs));
								jcxs = mat.format(Double.parseDouble(jcxs));
								//根据房屋系数表里的自增id 去基础系数和房屋类型系数（如果以后表结构有改动或自增id有增加修改 此处也需要修改）
								if (fwbzw.equals("1")) {
									xs1 = xs;
									jcxs1 = jcxs;
								} else if (fwbzw.equals("2")) {
									xs2 = xs;
									jcxs2 = jcxs;
								} else if (fwbzw.equals("3")) {
									xs3 = xs;
									jcxs3 = jcxs;
								}
							}
						}
					
						//根据前台对标月份来判断月份系数为几月
		            for(int i=mon;i<=mon1;i++){
		            	if(mon==1){
		            		yf="ymonth";
		            	}else if(mon==2){
		            		yf="emonth";
		            	}else if(mon==3){
		            		yf="smonth";
		            	}else if(mon==4){
		            		yf="simonth";
		            	}else if(mon==5){
		            		yf="wmonth";
		            	}else if(mon==6){
		            		yf="lmonth";
		            	}else if(mon==7){
		            		yf="qmonth";
		            	}else if(mon==8){
		            		yf="bmonth";
		            	}else if(mon==9){
		            		yf="jmonth";
		            	}else if(mon==10){
		            		yf="shimonth";
		            	}else if(mon==11){
		            		yf="symonth";
		            	}else if(mon==12){
		            		yf="semonth";
		            	}
		            	
		            	//修改qsdb表里的每个月的全省定标电量（有错误）
		            	dao.UpdateQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
		            			jszlfh4, kszlfh5, jszlfh5, kszlfh6, 
		            			jz1, jz2, jz3, jz4, jz5, jz6,
		            			jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
		            			jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,
		            			qt1,qt2,qt3,qt4,qt5,qt6,
		            			idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
		            			yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3,zdid,mon,ls);
		            	if(mon==mon1){
		            	//如果是当前月就更新站点表的全省定标电量
		            	dao.UpQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4, jszlfh4,
		            			kszlfh5,jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
				            	jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
			            		jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,qt1,qt2,qt3,qt4,qt5,qt6,
			            		idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
			            		yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3, zdid, mon1,ls);
		            	}
		            			mon++;
		           
		    }
		           //当前月份-1 更新jzxx表中全省定标（jzxx表里都是上个月份的信息所以更上个月的全省定标电量）
		           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		           	Calendar calendar = Calendar.getInstance();//日历对象  
					calendar.setTime(date);//设置当前日期  
					calendar.add(Calendar.MONTH, -1);//月份减一
					String yuefen =ctime.formatWholeDate(calendar.getTime());
					
					//System.out.println(yuefen+"------------------------------");
					String jmon=yuefen.substring(5,7);
					String bzmonth=yuefen.substring(0,7);
					int jm=Integer.parseInt(jmon);
					if(jm==1){
	            		yf="ymonth";
	            	}else if(jm==2){
	            		yf="emonth";
	            	}else if(jm==3){
	            		yf="smonth";
	            	}else if(jm==4){
	            		yf="simonth";
	            	}else if(jm==5){
	            		yf="wmonth";
	            	}else if(jm==6){
	            		yf="lmonth";
	            	}else if(jm==7){
	            		yf="qmonth";
	            	}else if(jm==8){
	            		yf="bmonth";
	            	}else if(jm==9){
	            		yf="jmonth";
	            	}else if(jm==10){
	            		yf="shimonth";
	            	}else if(jm==11){
	            		yf="symonth";
	            	}else if(jm==12){
	            		yf="semonth";
	            	}
		           dao.UpJzxx(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
		        		  jszlfh4, kszlfh5, jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
		        		  jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
	            			jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,
	            			qt1,qt2,qt3,qt4,qt5,qt6,
	            			idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
	            			yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,
		        		  xs1,xs2,xs3, zdid, mon1,bzmonth,ls);
		       // log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点市级审核结单"); 
		       // session.setAttribute("url", url);
		       // session.setAttribute("msg", msg);
		      //  resp.sendRedirect(path + "/web/msg.jsp");
		        out.print(msg);
		        out.close();
		    	
		    	
		    }else if(action.equals("scbzdjd")){
		    	//省审核
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshengshenhe.jsp" ;
		        String zdid=req.getParameter("zdid1"); 
		        String chooseIdStr2=req.getParameter("chooseIdStr1");
		        List<Zdinfo> ls=null;
		      //获取超标站点详细信息
		        ls=bean.seachShengb(zdid);
		        String cbsj=req.getParameter("cbsj");//对标月份
		         msg = bean.Cbshengtd(chooseIdStr1,"1",loginName,ls);//更新站点属性信息和全省定标电量，更新生产标表里的生产标
		       
		            cbzddao dao=new cbzddao();
		            KtxsDao dao1=new KtxsDao();
		            CTime ctime = new CTime(); 
		            Date date = new Date();
		            String thismonth = ctime.formatWholeDate(date);//当前月份
		        	DecimalFormat mat = new DecimalFormat("0.00");
		            int mon=0; int mon1;String yf="";
		            mon1=Integer.parseInt(thismonth.substring(5,7)); //系统当前月份
		            if(cbsj.length()>0){
		            mon=Integer.parseInt(cbsj.substring(5,7));//前台对标月份
		            }else{
		            	mon=mon1;
		            }
		            //获取2个时间
		          
		            List<Ktxs> fylist1 = new ArrayList<Ktxs>();
		          
		            // 查询空调系数
		            fylist1 =dao1.getktxs();
		        	String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "",yywd = "", id1 = "", zlfh = "";
					String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "",yywd1="", id11 = "", zlfh1 = "";
					String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "",yywd2="", id12 = "", zlfh2 = "";
					String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "",yywd3="", id13 = "", zlfh3 = "";
					String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "",yywd4="", id14 = "", zlfh4 = "";
					String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "",yywd5="", id15 = "", zlfh5 = "";
					String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "",yywd6="", id16 = "", zlfh6 = "";
					
		           if(fylist1!=null){
		        	   for (Ktxs bean2 : fylist1) {
							id1 = bean2.getKtxsid();
							kszlfh = bean2.getKszlfh();
							jszlfh = bean2.getJszlfh();
							jz = bean2.getJzktxs();
							jrw = bean2.getJrwktxs();
							xzzj = bean2.getXzzjktxs();
							jyjf = bean2.getJyjfktxs();
							qt = bean2.getQtktxs();
							idcjf = bean2.getIdcjfktxs();
							yywd = bean2.getYywdktxs();
							jz = mat.format(Double.parseDouble(jz));
							jrw = mat.format(Double.parseDouble(jrw));
							xzzj = mat.format(Double.parseDouble(xzzj));
							jyjf = mat.format(Double.parseDouble(jyjf));
							qt = mat.format(Double.parseDouble(qt));
							idcjf = mat.format(Double.parseDouble(idcjf));
							yywd = mat.format(Double.parseDouble(yywd));
							//规矩自增id 判断各大属性的系数（如果以后表结构有改动或自增id有增加修改 此处也需要修改）
							if (id1.equals("1")) {
								kszlfh1 = kszlfh;
								jszlfh1 = jszlfh;
								jz1 = jz;
								jrw1 = jrw;
								xzzj1 = xzzj;
								jyjf1= jyjf;
								qt1 = qt;
								idcjf1 = idcjf;
								yywd1 = yywd;
							} else if (id1.equals("2")) {
								kszlfh2 = kszlfh;
								jszlfh2 = jszlfh;
								jz2 = jz;
								jrw2 = jrw;
								xzzj2 = xzzj;
								jyjf2 = jyjf;
								qt2 = qt;
								idcjf2 = idcjf;
								yywd2 = yywd;
							} else if (id1.equals("3")) {
								kszlfh3 = kszlfh;
								jszlfh3 = jszlfh;
								jz3 = jz;
								jrw3 = jrw;
								xzzj3 = xzzj;
								jyjf3 = jyjf;
								qt3 = qt;
								idcjf3 = idcjf;
								yywd3 = yywd;
							} else if (id1.equals("4")) {
								kszlfh4 = kszlfh;
								jszlfh4 = jszlfh;
								jz4 = jz;
								jrw4 = jrw;
								xzzj4 = xzzj;
								jyjf4 = jyjf;
								qt4 = qt;
								idcjf4 = idcjf;
								yywd4 = yywd;
							} else if (id1.equals("5")) {
								kszlfh5 = kszlfh;
								jszlfh5 = jszlfh;
								jz5 = jz;
								jrw5 = jrw;
								xzzj5 = xzzj;
								jyjf5 = jyjf;
								qt5 = qt;
								idcjf5 = idcjf;
								yywd5 = yywd;
							} else if (id1.equals("6")) {
								kszlfh6 = kszlfh;
								jz6 = jz;
								jrw6 = jrw;
								xzzj6 = xzzj;
								jyjf6 = jyjf;
								qt6 = qt;
								idcjf6 = idcjf;
								yywd6 = yywd;
							}
						}
		           }
		        	   List<Ktxs> fylist = new ArrayList<Ktxs>();
		        	   //查询房屋类型系数和基础系数（1）
						fylist = dao1.getfwxs();
						String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
						String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
						String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
						String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
						if (fylist != null) {
							for (Ktxs bean3 : fylist) {
								fwid = bean3.getFwxsid();
								fwlx = bean3.getYfname();
								xs = bean3.getFxxs();
								jcxs = bean3.getJcxs();
								fwbzw = bean3.getFwsjbzw();
								xs = mat.format(Double.parseDouble(xs));
								jcxs = mat.format(Double.parseDouble(jcxs));
								//根据房屋系数表里的自增id 去基础系数和房屋类型系数（如果以后表结构有改动或自增id有增加修改 此处也需要修改）
								if (fwbzw.equals("1")) {
									xs1 = xs;
									jcxs1 = jcxs;
								} else if (fwbzw.equals("2")) {
									xs2 = xs;
									jcxs2 = jcxs;
								} else if (fwbzw.equals("3")) {
									xs3 = xs;
									jcxs3 = jcxs;
								}
							}
						}
					
						//根据前台对标月份来判断月份系数为几月
		            for(int i=mon;i<=mon1;i++){
		            	if(mon==1){
		            		yf="ymonth";
		            	}else if(mon==2){
		            		yf="emonth";
		            	}else if(mon==3){
		            		yf="smonth";
		            	}else if(mon==4){
		            		yf="simonth";
		            	}else if(mon==5){
		            		yf="wmonth";
		            	}else if(mon==6){
		            		yf="lmonth";
		            	}else if(mon==7){
		            		yf="qmonth";
		            	}else if(mon==8){
		            		yf="bmonth";
		            	}else if(mon==9){
		            		yf="jmonth";
		            	}else if(mon==10){
		            		yf="shimonth";
		            	}else if(mon==11){
		            		yf="symonth";
		            	}else if(mon==12){
		            		yf="semonth";
		            	}
		            	
		            	//修改qsdb表里的每个月的全省定标电量（有错误）
		            	dao.UpdateQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
		            			jszlfh4, kszlfh5, jszlfh5, kszlfh6, 
		            			jz1, jz2, jz3, jz4, jz5, jz6,
		            			jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
		            			jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,
		            			qt1,qt2,qt3,qt4,qt5,qt6,
		            			idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
		            			yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3,zdid,mon,ls);
		            	if(mon==mon1){
		            	//如果是当前月就更新站点表的全省定标电量
		            	dao.UpQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4, jszlfh4,
		            			kszlfh5,jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
				            	jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
			            		jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,qt1,qt2,qt3,qt4,qt5,qt6,
			            		idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
			            		yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3, zdid, mon1,ls);
		            	}
		            			mon++;
		           
		    }
		           //当前月份-1 更新jzxx表中全省定标（jzxx表里都是上个月份的信息所以更上个月的全省定标电量）
		           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		           	Calendar calendar = Calendar.getInstance();//日历对象  
					calendar.setTime(date);//设置当前日期  
					calendar.add(Calendar.MONTH, -1);//月份减一
					String yuefen =ctime.formatWholeDate(calendar.getTime());
					
					//System.out.println(yuefen+"------------------------------");
					String jmon=yuefen.substring(5,7);
					String bzmonth=yuefen.substring(0,7);
					int jm=Integer.parseInt(jmon);
					if(jm==1){
	            		yf="ymonth";
	            	}else if(jm==2){
	            		yf="emonth";
	            	}else if(jm==3){
	            		yf="smonth";
	            	}else if(jm==4){
	            		yf="simonth";
	            	}else if(jm==5){
	            		yf="wmonth";
	            	}else if(jm==6){
	            		yf="lmonth";
	            	}else if(jm==7){
	            		yf="qmonth";
	            	}else if(jm==8){
	            		yf="bmonth";
	            	}else if(jm==9){
	            		yf="jmonth";
	            	}else if(jm==10){
	            		yf="shimonth";
	            	}else if(jm==11){
	            		yf="symonth";
	            	}else if(jm==12){
	            		yf="semonth";
	            	}
		           dao.UpJzxx(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
		        		  jszlfh4, kszlfh5, jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
		        		  jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
	            			jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,
	            			qt1,qt2,qt3,qt4,qt5,qt6,
	            			idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
	            			yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,
		        		  xs1,xs2,xs3, zdid, mon1,bzmonth,ls);
		           
		        log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点市级审核结单"); 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("scbzdqx")){
		    	//省审核取消
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/cbzdshengshenhe.jsp" ;
		            msg = bean.Cbshengqx(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "超标站点省级取消审核"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("xfshish")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshishenhe.jsp" ;
		            msg = bean.Xfshish(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "下发整改市级审核通过"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
}else if(action.equals("xfshishno")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshishenhe.jsp" ;
		            msg = bean.Xfshishno(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "下发整改市级审核不通过"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("xfshengsh")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshengshenhe.jsp" ;
		        String zdid=req.getParameter("zdid1"); 
		        String chooseIdStr2=req.getParameter("chooseIdStr1");
		        List<Zdinfo> ls=null;
		        ls=bean.seachShengb(zdid);
		        
		        msg = bean.Xfshengsh(chooseIdStr1,"1",loginName,ls);
		        
		        cbzddao dao=new cbzddao();
		        KtxsDao dao1=new KtxsDao();
		        Date date = new Date();
	            CTime ctime = new CTime(); 
	            String thismonth = ctime.formatWholeDate(date);
	        	DecimalFormat mat = new DecimalFormat("0.00");
	            int mon; int mon1;String yf="";
	            String cbsj=req.getParameter("cbsj");
	            mon=Integer.parseInt(cbsj.substring(5,7));
	            mon1=Integer.parseInt(thismonth.substring(5,7)); 
	            System.out.println("------------"+mon+"======="+mon1);
	            
	            
	            List<Ktxs> fylist1 = new ArrayList<Ktxs>();
	            fylist1 =dao.getktxs();
	        	String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "",yywd = "", id1 = "", zlfh = "";
				String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "",yywd1 = "", id11 = "", zlfh1 = "";
				String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "",yywd2 = "", id12 = "", zlfh2 = "";
				String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "",yywd3 = "", id13 = "", zlfh3 = "";
				String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "", yywd4 = "",id14 = "", zlfh4 = "";
				String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "", yywd5 = "",id15 = "", zlfh5 = "";
				String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "",yywd6 = "", id16 = "", zlfh6 = "";
	           if(fylist1!=null){
	        	   for (Ktxs bean2 : fylist1) {
						id1 = bean2.getKtxsid();
						kszlfh = bean2.getKszlfh();
						jszlfh = bean2.getJszlfh();
						jz = bean2.getJzktxs();
						jrw = bean2.getJrwktxs();
						xzzj = bean2.getXzzjktxs();
						jyjf = bean2.getJyjfktxs();
						qt = bean2.getQtktxs();
						idcjf = bean2.getIdcjfktxs();
						yywd = bean2.getYywdktxs();
					jz = mat.format(Double.parseDouble(jz));
						jrw = mat.format(Double.parseDouble(jrw));
						xzzj = mat.format(Double.parseDouble(xzzj));
						jyjf = mat.format(Double.parseDouble(jyjf));
						qt = mat.format(Double.parseDouble(qt));
						idcjf = mat.format(Double.parseDouble(idcjf));
						yywd = mat.format(Double.parseDouble(yywd));
						if (id1.equals("1")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
							yywd1= yywd;
						} else if (id1.equals("2")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
							yywd2= yywd;
						} else if (id1.equals("3")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
							yywd3= yywd;
						} else if (id1.equals("4")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
							yywd4= yywd;
						} else if (id1.equals("5")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
							yywd5= yywd;
						} else if (id1.equals("6")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
							yywd6= yywd;
						}
					}
	           }
	        	   List<Ktxs> fylist = new ArrayList<Ktxs>();
					fylist = dao1.getfwxs();
					String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
					String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
					String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
					String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
					if (fylist != null) {
						for (Ktxs bean3 : fylist) {
							fwid = bean3.getFwxsid();
							fwlx = bean3.getYfname();
							xs = bean3.getFxxs();
							jcxs = bean3.getJcxs();
							fwbzw = bean3.getFwsjbzw();
							xs = mat.format(Double.parseDouble(xs));
							jcxs = mat.format(Double.parseDouble(jcxs));
							if (fwbzw.equals("1")) {
								xs1 = xs;
								jcxs1 = jcxs;
							} else if (fwbzw.equals("2")) {
								xs2 = xs;
								jcxs2 = jcxs;
							} else if (fwbzw.equals("3")) {
								xs3 = xs;
								jcxs3 = jcxs;
							}
						}
					}
	            for(int i=mon;i<=mon1;i++){
	            	if(mon==1){
	            		yf="ymonth";
	            	}else if(mon==2){
	            		yf="emonth";
	            	}else if(mon==3){
	            		yf="smonth";
	            	}else if(mon==4){
	            		yf="simonth";
	            	}else if(mon==5){
	            		yf="wmonth";
	            	}else if(mon==6){
	            		yf="lmonth";
	            	}else if(mon==7){
	            		yf="qmonth";
	            	}else if(mon==8){
	            		yf="bmonth";
	            	}else if(mon==9){
	            		yf="jmonth";
	            	}else if(mon==10){
	            		yf="shimonth";
	            	}else if(mon==11){
	            		yf="symonth";
	            	}else if(mon==12){
	            		yf="semonth";
	            	}
	            	dao.UpdateQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
	            			jszlfh4, kszlfh5, jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
	            			jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
	            			jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,qt1,qt2,qt3,qt4,qt5,qt6,
	            			idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
	            			yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3, zdid,mon,ls);
	            	if(mon==mon1){
	            		dao.UpQsdb(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4, jszlfh4, kszlfh5,
	    	            		jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
	    	            		jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
			            		jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,qt1,qt2,qt3,qt4,qt5,qt6,
			            		idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
			            		yywd1,yywd2,yywd3,yywd4,yywd5,yywd6,xs1,xs2,xs3, zdid, mon1,ls);
	            	}
	            			mon++;
	            }
	            //当前月份-1 更新jzxx表中全省定标
	           	Calendar calendar = Calendar.getInstance();//日历对象  
				calendar.setTime(date);//设置当前日期  
				calendar.add(Calendar.MONTH, -1);//月份减一
				String yuefen =ctime.formatWholeDate(calendar.getTime());
				System.out.println(yuefen+"------------------------------");
				String jmon=yuefen.substring(5,7);
				String bzmonth=yuefen.substring(0,7);
				int jm=Integer.parseInt(jmon);
				if(jm==1){
            		yf="ymonth";
            	}else if(jm==2){
            		yf="emonth";
            	}else if(jm==3){
            		yf="smonth";
            	}else if(jm==4){
            		yf="simonth";
            	}else if(jm==5){
            		yf="wmonth";
            	}else if(jm==6){
            		yf="lmonth";
            	}else if(jm==7){
            		yf="qmonth";
            	}else if(jm==8){
            		yf="bmonth";
            	}else if(jm==9){
            		yf="jmonth";
            	}else if(jm==10){
            		yf="shimonth";
            	}else if(jm==11){
            		yf="symonth";
            	}else if(jm==12){
            		yf="semonth";
            	}
	           dao.UpJzxx(yf, kszlfh1, jszlfh1, kszlfh2, jszlfh2, kszlfh3, jszlfh3, kszlfh4,
	        		  jszlfh4, kszlfh5, jszlfh5, kszlfh6, jz1, jz2, jz3, jz4, jz5, jz6,
	        		  jrw1,jrw2,jrw3,jrw4,jrw5,jrw6,xzzj1,xzzj2,xzzj3,xzzj4,xzzj5,xzzj6,
	            		jyjf1,jyjf2,jyjf3,jyjf4,jyjf5,jyjf6,qt1,qt2,qt3,qt4,qt5,qt6,
	            		idcjf1,idcjf2,idcjf3,idcjf4,idcjf5,idcjf6,
	            		yywd1,yywd2,yywd3,yywd4,yywd5,yywd6, xs1,xs2,xs3, zdid, mon1,bzmonth,ls);
	            
	            
	          
		            log.write(msg, account.getName(), req.getRemoteAddr(), "下发整改省级审核通过结单"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("xfshengshno")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String sj=req.getParameter("sj");
		    	String yj=req.getParameter("yj");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshengshenhe.jsp" ;
		            msg = bean.Xfshengshno(chooseIdStr1,"1",loginName,sj,yj);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "下发整改省级审核不通过"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("chehui")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshishenhe.jsp" ;
		            msg = bean.Xfshijichehui(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "整改市级审核撤单"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    	
		    	
		    	
		    }else if(action.equals("xfshengtuihui")){
		    	//省审核通过
		    	String chooseIdStr1 = req.getParameter("chooseIdStr1");
		    	String loginName = req.getParameter("loginName");
		        url = path + "/web/jzcbnewfunction/xfshengshenhe.jsp" ;
		            msg = bean.Xfshengjituihui(chooseIdStr1,"1",loginName);
		            log.write(msg, account.getName(), req.getRemoteAddr(), "整改省级审核撤单"); 
		  	 
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        resp.sendRedirect(path + "/web/msg.jsp");
		    }else if("sjshxxbc".equals(action)){
              String jlfh=req.getParameter("j");
              String zlfh=req.getParameter("z");
              String edhdl=req.getParameter("e");
              String qsdb=req.getParameter("q");		  
             String id=req.getParameter("id");
              //System.out.println("j:"+jlfh+"z:"+zlfh+"e:"+edhdl+"q:"+qsdb);
          	
              String chooseIdStr1 = req.getParameter("chooseIdStr1");
	    	String loginName = req.getParameter("loginName");
	        url = path + "/web/jzcbnewfunction/shishxx.jsp" ;
	            msg = bean.Sjshxx(jlfh,zlfh,edhdl,qsdb,id,loginName);
	            log.write(msg, account.getName(), req.getRemoteAddr(), "市级审核修改字段"); 
	  	 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        session.setAttribute("ida", id);
	        resp.sendRedirect(url);
		    }
		  
		  
		  
	  }

}
