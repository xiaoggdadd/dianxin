package com.noki.daoruelectricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;

public class DaoruDianLiang {
	/**
	   * 添加电量电费
	   * 以下是要添加的信息
	   */
	  public synchronized String addDegreeFees(AmmeterDegreeFormBean formBean,String uuid,String bzw,ArrayList fylist) {
		//对上次电表读数lastdegree、本次电表读数thisdegree、本次抄表时间thisdatetime传入数据库 查询判断此电费单是否已经存在；
		  System.out.println("=======2012====电费单添加=====");
		  //birthday = birthday.length()>0?birthday:null;
	    String msg = "保存电费单失败！请重试或与管理员联系！";
	    int flag=1;
	    String bbll="";
	    String qbl="";
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		  Date dt;
		  String lastadtime="";
		try {
			dt = sdf.parse(formBean.getLastdatetime());
			Calendar rightNow = Calendar.getInstance();
	         rightNow.setTime(dt);
	         rightNow.add(Calendar.DAY_OF_YEAR,-1);//日期加1天
	         Date dt1=rightNow.getTime();
	         lastadtime= sdf.format(dt1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	  //自动审核判断
	    //计算这次与上次日耗电量比值
	    long lastdaydegree =  formBean.getLastDayAmmeterDegree(formBean.getAmmeteridFk(),lastadtime);
	    //计算本次日耗电量
	    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	    long nd = 1000*24*60*60;//一天的毫秒数
	    //获得两个时间的毫秒时间差异
	    long diff,day;
	    double thisdaydegree,daydegereerate=0;
			try {
				diff = sd.parse(formBean.getThisdatetime()).getTime() - sd.parse(formBean.getLastdatetime()).getTime();
				day = (diff/nd)+1;//计算差多少天
				thisdaydegree = Double.parseDouble(formBean.getBlhdl())/day;//本次日耗电量
				System.out.println("-lastdaydegree-"+lastdaydegree+"-thisdaydegree-"+thisdaydegree+"--");
				daydegereerate =((thisdaydegree-lastdaydegree)/lastdaydegree)*100 ;//这次与上次日耗电量比值
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			} 
	    //AutoAuditBean bean = new AutoAuditBean();
	  	 //ArrayList fylist = new ArrayList();
	  	// fylist = bean.getPageData(1,"");
		String ItemID = "",
		beilv="",
		ItemName = "",
		ItemValue = "",
		ItemDescription = "";
		String autoauditstatus="1",
		autoauditdescription="",
		autoauditdescription1="",
		autoauditdescription2="",
		autoauditdescription3="",
		autoauditdescription4="",
		autoauditdescription5="";
		String auditfee1="";
		String auditfee2="";
		String auditfee3="";
		String auditfee4="";
		String auditfee5 = "";
		
		String auditfee6 = "";
		String auditfee7 = "";
		String auditfee8 = "";
		String auditfee9 = "";
		String auditfee10 = "";
		
		String auditfee11 = "";
		String auditfee12 = "";
		String auditfee13 = "";
		String auditfee14 = "";
		String auditfee15 = "";
		
		String auditfee16 = "";
		String auditfee17 = "";
		String auditfee18 = "";
		String auditfee19 = "";
		String auditfee20 = "";
		
		String auditfee21 = "";
		String auditfee22 = "";
		String auditfee23 = "";
		String auditfee24 = "";
		String auditfee25 = "";
		
		String auditfee26 = "";
		String auditfee27 = "";
		String auditfee28 = "";
		String auditfee29 = "";
		String ad2_bz = formBean.getAd2_bz();
		String floatdegree = formBean.getFloatdegree();
		String memo = formBean.getMemo();
		String ad1_bz = "0";
		String floatdegree_bz = "";
		String memo_bz = "";
		double floatt=0.0;
		if( null!= floatdegree && !floatdegree.equals("")&& !floatdegree.equals(" ")){
			floatt=Double.parseDouble(floatdegree);
		}
		else{
			floatdegree_bz ="0";
		}
		if(floatt==0.0){
			floatdegree_bz ="0";
		}else{
			floatdegree_bz ="1";
		}
		if( null!= memo && !memo.equals("")&& !memo.equals(" ")){
			memo_bz = "1";
		}
		else{
			memo_bz = "0";
		}
		if (memo_bz.equals(floatdegree_bz)||(floatdegree_bz.equals("0")&&memo_bz .equals("1"))) {
			ad1_bz = "1";
		}
		
		/*if(floatdegree==null||floatdegree==""||floatdegree.equals("0")||floatdegree.equals("0.0")||floatdegree.equals("0.00")){
			floatdegree_bz = "0";
		}else{
			floatdegree_bz = "1";
		}
		if(memo==null||memo==""){
			memo_bz = "0";
		}else{
			memo_bz = "1";
		}
		if(memo_bz.equals(floatdegree_bz)){
			ad1_bz = "1";
		}*/
		int bzw1=0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				 bzw1++;
		      ItemID = (String)fylist.get(k+fylist.indexOf("ITEMID"));
		      ItemName = (String)fylist.get(k+fylist.indexOf("ITEMNAME"));
		      ItemValue = (String)fylist.get(k+fylist.indexOf("ITEMVALUE"));
			  ItemDescription = (String)fylist.get(k+fylist.indexOf("ITEMDESCRIPTION"));	
			  
			  if(ItemName.equals("AuditDegree1")&&(!ItemValue.equals("0"))&&ad1_bz.equals("0")){
				  autoauditstatus="0";
				  autoauditdescription1="用电调整没有说明！";
				  auditfee1 = "0";
			  }
			  if(ItemName.equals("AuditDegree2")&&(!ItemValue.equals("0"))){
				  autoauditstatus="0";
				  autoauditdescription2="上次电表读数与最后抄表数不一致！";
				  auditfee2 = "0";
			  }
		    SimpleDateFormat   format   =   new   SimpleDateFormat("yyyy-MM-dd");		  
			int result=0;
			try {
				result = (format.parse(formBean.getLastdatetime().toString())).compareTo(format.parse(formBean.getThisdatetime().toString()));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			  if(ItemName.equals("AuditDegree3")&&(!ItemValue.equals("0"))&&(result>0)){
				  autoauditstatus="0";
				  autoauditdescription3="抄表时间小于本次抄表时间！";
				  auditfee3 = "0";
			  }
			  if(ItemName.equals("AuditDegree4")&&(!ItemValue.equals("0"))&&(daydegereerate>Double.parseDouble(ItemValue))){
				  autoauditstatus="0";
				  autoauditdescription4="平均日耗电量大于上次日均量"+ItemValue+"%（本次交费日均耗电量大于最后一次交费的日均耗电量百分比）！";
				  auditfee4 = "0";
			  }
//			  String edsql="select z.edhdl,d.beilv  from zhandian z,dianbiao d where z.id=d.zdid and d.dbid='"+formBean.getAmmeteridFk()+"'";
//			  ResultSet rs=null;
//			  String edhdl="0";
//			  
//			  try {
//				DataBase db1 = new DataBase();
//				db1.connectDb();
//				rs=db1.queryAll(edsql);
//				if(rs.next()){
//					edhdl=(null==rs.getString(1)?"0":rs.getString(1));
//					beilv=(null==rs.getString(2)?"1":rs.getString(2));
//				}
//				db1.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			  String edsql = "select z.edhdl,d.beilv,z.qsdbdl,z.property,z.ktxs,z.shi,z.zlfh,z.jlfh,z.jcxs,z.fwxs,z.yfxs,z.yflx from zhandian z,dianbiao d where z.id=d.zdid and d.dbid='"
					+ formBean.getAmmeteridFk() + "'";
			ResultSet rs = null;
			String edhdl = "0";
			String qsdbdl="0";
			String property = "";
			String ktxs= "";
			String shi="";
			String zlfh="";
			String jlfh="";
			String jcxs="";
			String yfxs="";
			String fwxs="";
			String fwlx="";
			try {
				DataBase db1 = new DataBase();
				db1.connectDb();
				rs = db1.queryAll(edsql);
				System.out.println("edsql:"+edsql);
				if (rs.next()) {
					edhdl = (null == rs.getString(1) ? "0" : rs
							.getString(1));
					beilv = (null == rs.getString(2) ? "1" : rs
							.getString(2));
					qsdbdl = (null == rs.getString(3) ? "0" : rs
							.getString(3));
					if("".equals(qsdbdl)){
						
						qsdbdl="0";
					}
					property = (null == rs.getString(4) ? "" : rs
							.getString(4));
					ktxs = (null == rs.getString(5) ? "0" : rs
							.getString(5));
					if("".equals(rs.getString(5))){
						
						ktxs="0";
					}
					System.out.println("ktxs开始:"+ktxs);
					shi = (null == rs.getString(6) ? "" : rs
							.getString(6));
					zlfh = (null == rs.getString(7) ? "0" : rs
							.getString(7));
					
					if("".equals(zlfh)){
						
						zlfh="0";
					}
					jlfh = (null == rs.getString(8) ? "0" : rs
							.getString(8));
					if("".equals(jlfh)){
						
						jlfh="0";
					}
					jcxs = (null == rs.getString(9) ? "0" : rs
							.getString(9));
					fwxs = (null == rs.getString(10) ? "0" : rs
							.getString(10));
					yfxs = (null == rs.getString(11) ? "0" : rs
							.getString(11));
					if("".equals(yfxs)){
						
						yfxs="0";
					}
					fwlx = (null == rs.getString(12) ? "" : rs
							.getString(12));
					if("".equals(fwlx)){
						
						fwlx="";
					}		
				}
				rs.close();//rsg
				db1.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//通过结束月份取值
			
			String endm = formBean.getEndmonth();
			String endmo = endm.substring(5, 7);
			String yymonth="";
			Double yf =null;
			if(endmo.equals("01")){yymonth = "YMONTH";}
			if(endmo.equals("02")){yymonth = "EMONTH";}
			if(endmo.equals("03")){yymonth = "SMONTH";}
			if(endmo.equals("04")){yymonth = "SIMONTH";}
			if(endmo.equals("05")){yymonth = "WMONTH";}
			if(endmo.equals("06")){yymonth = "LMONTH";}
			if(endmo.equals("07")){yymonth = "QMONTH";}
			if(endmo.equals("08")){yymonth = "BMONTH";}
			if(endmo.equals("09")){yymonth = "JMONTH";}
			if(endmo.equals("10")){yymonth = "SHIMONTH";}
			if(endmo.equals("11")){yymonth = "SYMONTH";}
			if(endmo.equals("12")){yymonth = "SEMONTH";}
			String sqlyf ="select y."+yymonth+" from zhandian zd, yfxs y where zd.shi=y.shicode";
			System.out.println("月份"+sqlyf.toString());
			ResultSet rsy = null;
			DataBase dbb = new DataBase();
			try {
				dbb.connectDb();
				rsy = dbb.queryAll(sqlyf);
				if (rsy.next()) {
					 yf = Double.parseDouble(rsy.getString(1));
				}
				rsy.close();//rsg
				dbb.close();
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String sss = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ";
			ResultSet r = null;
			String id1="";
			String kszlfh="",kszlfh1="",kszlfh2="",kszlfh3="",kszlfh4="",kszlfh5="",kszlfh6="";
			String jszlfh="",jszlfh1="",jszlfh2="",jszlfh3="",jszlfh4="",jszlfh5="",jszlfh6="";
			String jz="",jz1="",jz2="",jz3="",jz4="",jz5="",jz6="";
			String jrw="",jrw1="",jrw2="",jrw3="",jrw4="",jrw5="",jrw6="";
			String xzzj="",xzzj1="",xzzj2="",xzzj3="",xzzj4="",xzzj5="",xzzj6="";
			String jyjf="",jyjf1="",jyjf2="",jyjf3="",jyjf4="",jyjf5="",jyjf6="";
			String qt ="",qt1 ="",qt2 ="",qt3 ="",qt4 ="",qt5 ="",qt6 ="";
			String idcjf="",idcjf1="",idcjf2="",idcjf3="",idcjf4="",idcjf5="",idcjf6="";
			DataBase d = new DataBase();
			
				try {
					d.connectDb();
					r = d.queryAll(sss);
	                while(r.next()){
	                id1 = r.getString(1);
					kszlfh = r.getString(2);
					jszlfh = r.getString(3);
					jz = r.getString(4);
					jrw = r.getString(5);
					xzzj = r.getString(6);
					jyjf = r.getString(7);
					qt = r.getString(8);
					idcjf = r.getString(9);
					
					
				if (id1.equals("1")) {
					kszlfh1 = kszlfh;
					jszlfh1 = jszlfh;
					jz1 = jz;
					jrw1 = jrw;
					xzzj1 = xzzj;
					jyjf1 = jyjf;
					qt1 = qt;
					idcjf1 = idcjf;
				} else if (id1.equals("2")) {
					kszlfh2 = kszlfh;
					jszlfh2 = jszlfh;
					jz2 = jz;
					jrw2 = jrw;
					xzzj2 = xzzj;
					jyjf2 = jyjf;
					qt2 = qt;
					idcjf2 = idcjf;
				} else if (id1.equals("3")) {
					kszlfh3 = kszlfh;
					jszlfh3 = jszlfh;
					jz3 = jz;
					jrw3 = jrw;
					xzzj3 = xzzj;
					jyjf3 = jyjf;
					qt3 = qt;
					idcjf3 = idcjf;
				} else if (id1.equals("4")) {
					kszlfh4 = kszlfh;
					jszlfh4 = jszlfh;
					jz4 = jz;
					jrw4 = jrw;
					xzzj4 = xzzj;
					jyjf4 = jyjf;
					qt4 = qt;
					idcjf4 = idcjf;
				} else if (id1.equals("5")) {
					kszlfh5 = kszlfh;
					jszlfh5 = jszlfh;
					jz5 = jz;
					jrw5 = jrw;
					xzzj5 = xzzj;
					jyjf5 = jyjf;
					qt5 = qt;
					idcjf5 = idcjf;
				} else if (id1.equals("6")) {
					kszlfh6 = kszlfh;
					jz6 = jz;
					jrw6 = jrw;
					xzzj6 = xzzj;
					jyjf6 = jyjf;
					qt6 = qt;
					idcjf6 = idcjf;
				}
				
				
	                }
	            	r.close();//rsg
	                d.close();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			String jcdxsql = "select f.id,f.jcxs from fwxs f ";
			ResultSet jr = null;
			String id="",jcxsr = "",jcxsr1 = "",jcxsr2 = "",jcxsr3 = "";
			DataBase dbs = new DataBase();
			
			
				try {
					dbs.connectDb();
					jr = dbs.queryAll(jcdxsql);
	                try {
						while(jr.next()){
							id=jr.getString(1);
							jcxsr=jr.getString(2);
							if(id.equals("1")){
								jcxsr1 = jcxsr;
							}if(id.equals("2")){
								jcxsr2 = jcxsr;
							}if(id.equals("3")){
								jcxsr3= jcxsr;
							}
						}
						jr.close();//rsg
						dbs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  CTime time=new CTime();
				long ts=time.getQuot(formBean.getLastdatetime(), formBean.getThisdatetime())+1;
				String dd=formBean.getEdhdl();
				if(dd==null){
					 dd="0";
				}
				//本地定标
				Double hdl=Double.parseDouble(dd)*ts;
				//省定标
				Double qsdbdll = Double.parseDouble(qsdbdl) * ts;
				//实际用电量
				Double bchdl=Double.parseDouble(formBean.getBlhdl());
				//空调系数
				Double ktxss = Double.parseDouble(ktxs);
				DecimalFormat fomcate =new DecimalFormat("0.00");
			    bbll=fomcate.format((bchdl-hdl)/hdl*100);
				qbl = fomcate.format((bchdl-qsdbdll)/qsdbdll*100);
				Double zlfhh= Double.parseDouble(zlfh);
				Double jlfhh = Double.parseDouble(jlfh);
				Double jcxss = Double.parseDouble(jcxs);
				Double fwxss = Double.parseDouble(fwxs);
				Double yfxss = Double.parseDouble(yfxs);
				if(bzw1==9){	
					//不为0
					if(!fomcate.format(qsdbdll).equals("0.00") ){
						if ((bchdl < qsdbdll * (1 - 0.1 )|| bchdl >qsdbdll* (1 + 0.1))){
							System.out.println("qsdbdl:"+qsdbdl);
							System.out.println("ts:"+ts);
							System.out.println("bchdl:"+bchdl+"qsdbdll:"+qsdbdll);
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							 auditfee5 = "0";
							}
					}else if(fomcate.format(qsdbdll).equals("0.00")&& property.equals("zdsx02")){//为0 并且是基站
						if(hdl!=0){
						if((bchdl < hdl*0.9*(jcxss+yfxss*ktxss+fwxss) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxss+yfxss*ktxss+fwxss)* (1 + 0.1))){
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee21 = "0";
						}}else{
							if(zlfhh!=0){
								if((bchdl < zlfhh*0.9*(jcxss+yfxss*ktxss+fwxss) * (1 - 0.1 )|| bchdl >zlfhh*0.9*(jcxss+yfxss*ktxss+fwxss)* (1 + 0.1))){
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee22 = "0";
								}
							}else{
								if((bchdl < jlfhh*0.9*(jcxss+yfxss*ktxss+fwxss) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxss+yfxss*ktxss+fwxss)* (1 + 0.1))){
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee23 = "0";
								}
							}
							
						}
					}
					else if(fomcate.format(qsdbdll).equals("0.00")&& property.equals("zdsx05")){
						Double jrwktxs = null;
						if(zlfhh>=Double.parseDouble(kszlfh1)&&zlfhh<Double.parseDouble(kszlfh2)){
							jrwktxs =Double.parseDouble(jrw1);
						}else if(zlfhh>=Double.parseDouble(kszlfh2)&&zlfhh<Double.parseDouble(kszlfh3)){
							jrwktxs =Double.parseDouble(jrw2);
						}else if(zlfhh>=Double.parseDouble(kszlfh3)&&zlfhh<Double.parseDouble(kszlfh4)){
							jrwktxs =Double.parseDouble(jrw3);
						}else if(zlfhh>=Double.parseDouble(kszlfh4)&&zlfhh<Double.parseDouble(kszlfh5)){
							jrwktxs =Double.parseDouble(jrw4);
						}else if(zlfhh>=Double.parseDouble(kszlfh5)&&zlfhh<Double.parseDouble(kszlfh6)){
							jrwktxs =Double.parseDouble(jrw5);
						}else if(zlfhh>=Double.parseDouble(kszlfh6)){
							jrwktxs =Double.parseDouble(jrw6);
						}
						Double jcxsjc = null;
						if(fwlx.equals("fwlx01")){
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if(fwlx.equals("fwlx02")){
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if(fwlx.equals("fwlx03")){
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						System.out.println("jcxs:__________"+jcxsjc);
						System.out.println("jrwktxs:__________"+jrwktxs);
						if(hdl!=0){
							
						if((bchdl < hdl*0.9*(jcxsjc+yf*jrwktxs) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxsjc+yf*jrwktxs)* (1 + 0.1))){
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee6 = "0";
						}
						}else{
							if(zlfhh!=0){
								if((bchdl < zlfhh*0.9*(jcxsjc+yf*jrwktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.9*(jcxsjc+yf*jrwktxs)* (1 + 0.1))){
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee11 = "0";
								}
							}else{
								if((bchdl < jlfhh*0.9*(jcxsjc+yf*jrwktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxsjc+yf*jrwktxs)* (1 + 0.1))){
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee12 = "0";
							}
							
						}
						
						}
					}else if(property.equals("zdsx01")&&qsdbdll.toString().equals("0.00")){
						Double jyjfktxs = null;
						if(zlfhh>=Double.parseDouble(kszlfh1)&&zlfhh<Double.parseDouble(kszlfh2)){
							jyjfktxs =Double.parseDouble(jyjf1);
						}else if(zlfhh>=Double.parseDouble(kszlfh2)&&zlfhh<Double.parseDouble(kszlfh3)){
							jyjfktxs =Double.parseDouble(jyjf2);
						}else if(zlfhh>=Double.parseDouble(kszlfh3)&&zlfhh<Double.parseDouble(kszlfh4)){
							jyjfktxs =Double.parseDouble(jyjf3);
						}else if(zlfhh>=Double.parseDouble(kszlfh4)&&zlfhh<Double.parseDouble(kszlfh5)){
							jyjfktxs =Double.parseDouble(jyjf4);
						}else if(zlfhh>=Double.parseDouble(kszlfh5)&&zlfhh<Double.parseDouble(kszlfh6)){
							jyjfktxs =Double.parseDouble(jyjf5);
						}else if(zlfhh>=Double.parseDouble(kszlfh6)){
							jyjfktxs =Double.parseDouble(jyjf6);
						}
						Double jcxsjc = null;
						if(fwlx.equals("fwlx01")){
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if(fwlx.equals("fwlx02")){
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if(fwlx.equals("fwlx03")){
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if(hdl!=0){
						if(shi=="13701"||shi=="13702"||shi=="13705"){
							if((bchdl < hdl*0.75*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >hdl*0.75*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee7 = "0";
							}
						}else if(shi=="13710"||shi=="13717"){
							if((bchdl < hdl*0.9*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee8 = "0";
							
						}}else if(shi=="13704"){
							if((bchdl < hdl*1*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >hdl*1*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee9 = "0";
							
						}}else {
							if((bchdl < hdl*0.85*(jcxsjc+yf*jyjfktxs)* (1 - 0.1 )|| bchdl >hdl*0.85*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee10 = "0";
							
						}
							}
					}else{
						if(zlfhh!=0){
						if(shi=="13701"||shi=="13702"||shi=="13705"){
							if((bchdl < zlfhh*0.75*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.75*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee13 = "0";
							}
						}else if(shi=="13710"||shi=="13717"){
							if((bchdl < zlfhh*0.9*(1+yf*jyjfktxs) * (jcxsjc - 0.1 )|| bchdl >zlfhh*0.9*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee14 = "0";
							
						}}else if(shi=="13704"){
							if((bchdl < zlfhh*1*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >zlfhh*1*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee15 = "0";
							
						}}else {
							if((bchdl < zlfhh*0.85*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.85*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee16 = "0";
							
						}
							}
					}else{
						if(shi=="13701"||shi=="13702"||shi=="13705"){
							if((bchdl < jlfhh*0.75*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.75*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee17 = "0";
							}
						}else if(shi=="13710"||shi=="13717"){
							if((bchdl < jlfhh*0.9*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee18 = "0";
							
						}}else if(shi=="13704"){
							if((bchdl < jlfhh*1*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >jlfhh*1*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee19 = "0";
							
						}}else {
							if((bchdl < jlfhh*0.85*(jcxsjc+yf*jyjfktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.85*(jcxsjc+yf*jyjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee20 = "0";
							
						}
							}
					}
					}
					}else if(property.equals("zdsx06")&&qsdbdll.toString().equals("0.00")){
						Double xzzjktxs = null;
						if(zlfhh>=Double.parseDouble(kszlfh1)&&zlfhh<Double.parseDouble(kszlfh2)){
							xzzjktxs =Double.parseDouble(xzzj1);
						}else if(zlfhh>=Double.parseDouble(kszlfh2)&&zlfhh<Double.parseDouble(kszlfh3)){
							xzzjktxs =Double.parseDouble(xzzj2);
						}else if(zlfhh>=Double.parseDouble(kszlfh3)&&zlfhh<Double.parseDouble(kszlfh4)){
							xzzjktxs =Double.parseDouble(xzzj3);
						}else if(zlfhh>=Double.parseDouble(kszlfh4)&&zlfhh<Double.parseDouble(kszlfh5)){
							xzzjktxs =Double.parseDouble(xzzj4);
						}else if(zlfhh>=Double.parseDouble(kszlfh5)&&zlfhh<Double.parseDouble(kszlfh6)){
							xzzjktxs =Double.parseDouble(xzzj5);
						}else if(zlfhh>=Double.parseDouble(kszlfh6)){
							xzzjktxs =Double.parseDouble(xzzj6);
						}
						Double jcxsjc = null;
						if(fwlx.equals("fwlx01")){
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if(fwlx.equals("fwlx02")){
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if(fwlx.equals("fwlx03")){
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if(hdl!=0){
							if((bchdl < hdl*0.9*(jcxsjc+yf*xzzjktxs) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxsjc+yf*xzzjktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee21 = "0";
							}
							}else{
								if(zlfhh!=0){
									if((bchdl < zlfhh*0.9*(jcxsjc+yf*xzzjktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.9*(jcxsjc+yf*xzzjktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee22 = "0";
									}
								}else{
									if((bchdl < jlfhh*0.9*(jcxsjc+yf*xzzjktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxsjc+yf*xzzjktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee23 = "0";
								}
								
							}
							
							
						}
					}else if(property.equals("zdsx04")&&qsdbdll.toString().equals("0.00")){
						Double qtktxs = null;
						if(zlfhh>=Double.parseDouble(kszlfh1)&&zlfhh<Double.parseDouble(kszlfh2)){
							qtktxs =Double.parseDouble(qt1);
						}else if(zlfhh>=Double.parseDouble(kszlfh2)&&zlfhh<Double.parseDouble(kszlfh3)){
							qtktxs =Double.parseDouble(qt2);
						}else if(zlfhh>=Double.parseDouble(kszlfh3)&&zlfhh<Double.parseDouble(kszlfh4)){
							qtktxs =Double.parseDouble(qt3);
						}else if(zlfhh>=Double.parseDouble(kszlfh4)&&zlfhh<Double.parseDouble(kszlfh5)){
							qtktxs =Double.parseDouble(qt4);
						}else if(zlfhh>=Double.parseDouble(kszlfh5)&&zlfhh<Double.parseDouble(kszlfh6)){
							qtktxs =Double.parseDouble(qt5);
						}else if(zlfhh>=Double.parseDouble(kszlfh6)){
							qtktxs =Double.parseDouble(qt6);
						}
						Double jcxsjc = null;
						if(fwlx.equals("fwlx01")){
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if(fwlx.equals("fwlx02")){
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if(fwlx.equals("fwlx03")){
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if(hdl!=0){
							if((bchdl < hdl*0.9*(jcxsjc+yf*qtktxs) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxsjc+yf*qtktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee24 = "0";
							}
							}else{
								if(zlfhh!=0){
									if((bchdl < zlfhh*0.9*(jcxsjc+yf*qtktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.9*(jcxsjc+yf*qtktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee25 = "0";
									}
								}else{
									if((bchdl < jlfhh*0.9*(jcxsjc+yf*qtktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxsjc+yf*qtktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee26 = "0";
								}
								
							}
							
							
						}
					}else if(property.equals("zdsx07")&&qsdbdll.toString().equals("0.00")){
						Double idcjfktxs = null;
						if(zlfhh>=Double.parseDouble(kszlfh1)&&zlfhh<Double.parseDouble(kszlfh2)){
							idcjfktxs =Double.parseDouble(idcjf1);
						}else if(zlfhh>=Double.parseDouble(kszlfh2)&&zlfhh<Double.parseDouble(kszlfh3)){
							idcjfktxs =Double.parseDouble(idcjf2);
						}else if(zlfhh>=Double.parseDouble(kszlfh3)&&zlfhh<Double.parseDouble(kszlfh4)){
							idcjfktxs =Double.parseDouble(idcjf3);
						}else if(zlfhh>=Double.parseDouble(kszlfh4)&&zlfhh<Double.parseDouble(kszlfh5)){
							idcjfktxs =Double.parseDouble(idcjf4);
						}else if(zlfhh>=Double.parseDouble(kszlfh5)&&zlfhh<Double.parseDouble(kszlfh6)){
							idcjfktxs =Double.parseDouble(idcjf5);
						}else if(zlfhh>=Double.parseDouble(kszlfh6)){
							idcjfktxs =Double.parseDouble(idcjf6);
						}
						Double jcxsjc = null;
						if(fwlx.equals("fwlx01")){
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if(fwlx.equals("fwlx02")){
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if(fwlx.equals("fwlx03")){
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if(hdl!=0){
							if((bchdl < hdl*0.9*(jcxsjc+yf*idcjfktxs) * (1 - 0.1 )|| bchdl >hdl*0.9*(jcxsjc+yf*idcjfktxs)* (1 + 0.1))){
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee27 = "0";
							}
							}else{
								if(zlfhh!=0){
									if((bchdl < zlfhh*0.9*(jcxsjc+yf*idcjfktxs) * (1 - 0.1 )|| bchdl >zlfhh*0.9*(jcxsjc+yf*idcjfktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee28 = "0";
									}
								}else{
									if((bchdl < jlfhh*0.9*(jcxsjc+yf*idcjfktxs) * (1 - 0.1 )|| bchdl >jlfhh*0.9*(jcxsjc+yf*idcjfktxs)* (1 + 0.1))){
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee29 = "0";
								}
								
							}
							
							
						}
					}
			
				}
		       if(ItemName.equals("AuditDegree6")){
		    	  
		    	  if(bchdl<hdl*(1-Double.parseDouble(ItemValue)/100)||bchdl>hdl*(1+Double.parseDouble(ItemValue)/100)){
		    		 flag=0;
		    		 autoauditstatus="0";
		    		  autoauditdescription=autoauditdescription+"本次电量上下浮动超过站点额定耗电量计算值的"+ItemValue+"%"+";";
		    	  }
		      }
			}
			if(auditfee1.equals("0")){
				  autoauditdescription = autoauditdescription + autoauditdescription1 + ";" ;
			}
			if(auditfee2.equals("0")){
		      	  autoauditdescription = autoauditdescription + autoauditdescription2 + ";" ; 
			}
			if(auditfee3.equals("0")){
		      	  autoauditdescription = autoauditdescription + autoauditdescription3 + ";" ; 
			}
			if(auditfee4.equals("0")){
		      	  autoauditdescription = autoauditdescription + autoauditdescription4 + ";" ; 
			}

			if (auditfee5.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee6.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee7.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee8.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee9.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if(auditfee10.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee11.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee12.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee13.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee14.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee15.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee16.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee17.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee18.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee19.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			if(auditfee20.equals("0")){
				autoauditdescription = autoauditdescription
				+ autoauditdescription5 + ";";
			}
			
			
			
			

		}
	    //自动审核判断
	    String start=formBean.getStartmonth();
	    String end=formBean.getEndmonth();
//	    int startn=Integer.parseInt(start.substring(0,4));
//	    int starty=Integer.parseInt(start.substring(5,7));
//	    int endn=Integer.parseInt(end.substring(0,4));
//	    int endy=Integer.parseInt(end.substring(5,7));
//	    int time=(endn-startn)*12+endy-starty+1;
//	    System.out.println("--"+startn+"**"+starty+"**"+time);
//	    double yushu=Double.parseDouble(formBean.getBlhdl())%time;
//	    int dianduPermonth = (int) ((Double.parseDouble(formBean.getBlhdl())-yushu)/time);
//	    
//	    double yu=Double.parseDouble(formBean.getActualdegree())%time;
//	    int dian = (int) ((Double.parseDouble(formBean.getActualdegree())-yu)/time);
	    //生产电量分到每个月
	    
	    String aa=formBean.getScdl();
	    String bb=formBean.getBgdl();
	    String cc=formBean.getYydl();
	    String dd=formBean.getXxhdl();
	    String ee=formBean.getJstzdl();
	    String ff=formBean.getDddfdl();//代垫电量
	    if("".equals(aa)||aa==null)aa="0";
	    if("".equals(bb)||bb==null)bb="0";
	    if("".equals(cc)||cc==null)cc="0";
	    if("".equals(dd)||dd==null)dd="0";
	    if("".equals(ee)||ee==null)ee="0";
	    if("".equals(ff)||ff==null)ff="0";//代垫电量
	    
	    /*    double scdlyushu=Double.parseDouble(aa)%time;
	    int scdl = (int) ((Double.parseDouble(aa)-scdlyushu)/time);
	    //办公电量分到每个月
	    double bgdlyushu=Double.parseDouble(bb)%time;
	    int bgdl = (int) ((Double.parseDouble(bb)-bgdlyushu)/time);
	    //营业电量分到每个月
	    double yydlyushu=Double.parseDouble(cc)%time;
	    int yydl = (int) ((Double.parseDouble(cc)-yydlyushu)/time);
	    //信息化电量分到每个月
	    double xxhdlyushu=Double.parseDouble(dd)%time;
	    int xxhdl = (int) ((Double.parseDouble(dd)-xxhdlyushu)/time);
	    //建设投资电量分到每个月
	    double jstzdlyushu=Double.parseDouble(ee)%time;
	    int jstzdl = (int) ((Double.parseDouble(ee)-jstzdlyushu)/time);
	    //代垫电量分到每个月
	    double dddfdlyushu=Double.parseDouble(ff)%time;
	    int dddfdl = (int) ((Double.parseDouble(ff)-dddfdlyushu)/time);
	    
	    
	    //long uuid=System.currentTimeMillis();
	    String[] sqlBatch = new String[time];
	   
	    	 List year_month = new ArrayList();
	    	 List diandu = new ArrayList();//倍率耗电量
	    	 List dianl = new ArrayList();//用电量
	    	 List scdlfentan = new ArrayList();
	    	 List bgdlfentan = new ArrayList();
	    	 List yydlfentan = new ArrayList();
	    	 List xxhdlfentan = new ArrayList();
	    	 List jstzdlfentan = new ArrayList();
	    	 List dddfdlfentan = new ArrayList();//代垫电量
	    	 
	         for(int i = 0;i<time;i++){
	         	String yue = String.valueOf(starty);
	         	if(yue.length()==1)yue = "0"+yue;
	         	String year_month_tmp = startn+"-"+yue;
	         	starty ++;
	         	if(starty>12){
	         		starty = 1;
	         		startn ++;
	         	}
	         	year_month.add(year_month_tmp);
	         	if(i==time-1){
	         		diandu.add(dianduPermonth+yushu);
	         		dianl.add(dian+yu);
	         		scdlfentan.add(scdl+scdlyushu);
	         		bgdlfentan.add(bgdl+bgdlyushu);
	         		yydlfentan.add(yydl+yydlyushu);
	         		xxhdlfentan.add(xxhdl+xxhdlyushu);
	         		jstzdlfentan.add(jstzdl+jstzdlyushu);
	         		dddfdlfentan.add(dddfdl+dddfdlyushu);//代垫电量
	         	}else {
	         		diandu.add(dianduPermonth);
	         		dianl.add(dian);
	         		scdlfentan.add(scdl);
	         		bgdlfentan.add(bgdl);
	         		yydlfentan.add(yydl);
	         		xxhdlfentan.add(xxhdl);
	         		jstzdlfentan.add(jstzdl);
	         		dddfdlfentan.add(dddfdl);//代垫电量
	         	}
	         }*/
	         
	         String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
	         
	         String sqlx="INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME," +
	         		"THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR," +
	         		"STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS," +
	         		"AUTOAUDITDESCRIPTION,UUID,BLHDL,flag," +
	         		"entrypensonnel,entrytime,NETWORKHDL,ADMINISTRATIVEHDL," +
	         		"MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DLBZW,MANUALAUDITSTATUS,DEDHDL,DDDF)"+
	         " VALUES('"+formBean.getAmmeteridFk()+"','"+formBean.getLastdegree()+"','"+formBean.getThisdegree()+"',TO_DATE('"+formBean.getLastdatetime()+"','yyyy-mm-dd')," +
			   " TO_DATE('"+formBean.getThisdatetime()+"','yyyy-mm-dd'),'"+formBean.getFloatdegree()+"','"+formBean.getActualdegree()+"','"+formBean.getInputoperator()+"'," +
			   " TO_DATE('"+start+"','yyyy-mm'),TO_DATE('"+end+"','yyyy-mm'),'"+formBean.getMemo()+"','"+autoauditstatus+"'," +
			   " '"+ autoauditdescription+"','"+uuid+"','"+formBean.getBlhdl()+"','"+flag+"'," +
			   " '"+formBean.getEntrypensonnel()+"',"+s+",'"+Double.parseDouble(aa)+"','"+Double.parseDouble(bb)+"'," +
			   " '"+Double.parseDouble(cc)+"','"+Double.parseDouble(dd)+"','"+Double.parseDouble(ee)+"','"+bzw+"'," +
			   " '0','"+bbll+"','"+Double.parseDouble(ff)+"')";     
	         
	         
	        // for(int i = 0;i<time;i++){
	        	 DataBase db = new DataBase();
//	        	 StringBuffer sql = new StringBuffer();
//	        	 
//		         sql.append("INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR,STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,UUID,BLHDL,flag,entrypensonnel,entrytime,NETWORKHDL,ADMINISTRATIVEHDL,MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DLBZW,MANUALAUDITSTATUS,DEDHDL,DDDF)");
//		         sql.append(" VALUES('" + formBean.getAmmeteridFk() + "'");
//		         sql.append(",'" + formBean.getLastdegree() + "','" + formBean.getThisdegree() + "','" + formBean.getLastdatetime() + "','" + formBean.getThisdatetime() +
//		                    "','" + formBean.getFloatdegree() + "'");
//		         sql.append(",'" + dianl.get(i) + "','" + formBean.getInputoperator() + "','"
//		                   + year_month.get(i) + "','" + year_month.get(i) + "','" + formBean.getMemo() + "','"+autoauditstatus+"','"+autoauditdescription+"','"+uuid+"','"
//		                    +diandu.get(i)+"','"+flag+"','"+formBean.getEntrypensonnel()+"',"+s+",'"+scdlfentan.get(i)+"','"+bgdlfentan.get(i)+"','"+yydlfentan.get(i)+"','"+xxhdlfentan.get(i)+"','"+jstzdlfentan.get(i)+"','"+bzw+"','0','"+bbll+"','"+dddfdlfentan.get(i)+"') ");
//		         System.out.println(sql.toString()+"-----------------------");
		         //sqlBatch[i] = sql.toString();
		         try {
					db.connectDb();
					db.update(sqlx.toString());
					msg = "添加电量成功!";
			         System.out.println("-----------------------");

				} catch (DbException e) {
					try{
						db.rollback();
					}catch (Exception dee) {
						// TODO: handle exception
						dee.printStackTrace();
					}
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try{
						db.close();
						//db=null;
					}catch(Exception a){
						a.printStackTrace();
					}
				}
				
		         
	        //  }
	    
	    
//	    try {
//	    	
////	      db.connectDb();
////	      db.updateBatch(sqlBatch);
//	      msg = "添加电量成功!";
//	    }
//	    catch (DbException de) {
//	      try {
//	        db.rollback();
//	      }
//	      catch (DbException dee) {
//	        dee.printStackTrace();
//	      }
//	      de.printStackTrace();
//	    }
//	    finally {
//	      try {
//
//	        db.close();
//	      }
//	      catch (Exception de) {
//	        de.printStackTrace();
//	      }
//	    }

	    return msg;
	  }
}
