package com.noki.dataedit.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;

import com.noki.daoruelectricfees.javabean.DaoruDianFei;
import com.noki.daoruelectricfees.javabean.DaoruDianLiang;
import com.noki.dataedit.bean.zhandianbean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.sysconfig.javabean.AutoAuditBean;

public class InsertSession {
	String sqlnote = "";
	  Vector wrongContent = new Vector();
	  private ResultSet rs = null;
	  public synchronized Map getls(Vector content,CountForm cform,String accountname,String loginId)  {
		  String lastdatetime="";
		  Date date1 = null,date2=null;
		  String thisdatetime="",startmonth="",endmonth="",inputdatetime="",accountmonth="",notetime="",kptime="",paydatetime="";
		  String dbid1="";  String blhdl=""; String actualpay="";
		  //System.out.println("电量"+content);
		  
		  String yflxx="";
		  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Object[] a=content.toArray();
		  
		  
		  
		  System.out.println("表数据个数："+a.length);
		  DaoruDianLiang bean = new DaoruDianLiang();
		  DaoruDianFei fees = new DaoruDianFei();
		  AutoAuditBean abean = new AutoAuditBean();
		
		  zhandianbean zdbean=new zhandianbean();
		  	 ArrayList fylist = new ArrayList();
		  	Map<String, zhandianbean> map=new HashMap<String,zhandianbean>();
		  	 fylist = abean.getPageData(1,"");
		  	 List<zhandianbean> ls=new ArrayList();
		  for(int i=0;i<a.length;i++){		
			  
			  String shi,xian,xiaoqu,zdid,zdname,isqy,yflx;
			  String gdfs,zdcq,gxxx,zdsx,zdlx;
			  double dj,jlfh,zlfh,ktyps,kteps,sdbdl,qsdbdl;
			  int kd=0,yy=0,rru=0,ydgx=0,dxgx=0;
			  System.out.println("数据量："+i);
			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  
			  Vector<String> row=new Vector<String>();
			  Object[] b=((Vector)a[i]).toArray();
			 zdid= b[3].toString().trim();
			 shi=b[0].toString().trim();
			 if("".equals(shi)||null==shi){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"市为空！");
				  wrongContent.add(row);
				  break;
			  }
			 String st = zdid.replaceAll(" ", "");
			 System.out.println("站点id："+zdid);
			 if("".equals(st)||null==st){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[4].toString()+b[2].toString()+"站点id"+dbid1);
				  wrongContent.add(row);
				  break;
			  }
			 isqy=b[5].toString().trim();
			 if("".equals(isqy)||null==isqy){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"启用状态为空");
				  wrongContent.add(row);
				  break;
			  }
			 yflx=b[6].toString().trim();
			 if("".equals(yflx)||null==yflx){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"用房类型为空");
				  wrongContent.add(row);
				  break;
			  }
//			 String sql=" SELECT I.CODE FROM INDEXS I WHERE I.NAME='"+yflx+"' AND I.TYPE='FWLX'";
//			 System.out.println("sql用房类型:"+sql);
//			  ResultSet rs=null;
//			   try{
//			db.connectDb();
//			   rs=db.queryAll(sql);
//			   while(rs.next()){
//				  yflxx=rs.getString(1);
//				   
//			   }
//			
//			   }catch(Exception e){
//				   e.printStackTrace();
//			   }finally{
//				   try{
//					   if(rs!=null){
//						   rs.close();
//					   }
//					   if(db!=null){
//						   db.close();
//					   }
//				   }catch(Exception e){
//					   e.printStackTrace();
//				   }
//				   
//			   }
			 
			 System.out.println("用房类型："+yflxx);
			 
			 
			 gdfs=b[7].toString().trim();
			 if("".equals(gdfs)||null==gdfs){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"供电方式为空");
				  wrongContent.add(row);
				  break;
			  }
//			 String s1=b[8].toString().trim();//单价判断
//			 dj=
//				  if(pattern.matcher(str5).matches()==false){	  
//					  for(int j=0;j<b.length;j++){
//			      		   row.add(b[j].toString().trim());
//			      	      }
//					  row.add(b[0].toString()+b[2].toString()+"电表"+dbid1+"本次电表读数格式不正确"); 
//					  wrongContent.add(row);
//					  continue;
//				  }
			 zdcq=b[9].toString().trim();//站点产权
			 if("".equals(zdcq)||null==zdcq){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"站点产权为空");
				  wrongContent.add(row);
				  break;
			  }
			 String jl=b[10].toString().trim();//交流负荷
			 String jlf = jl.replaceAll(" ", "");
			 if(pattern.matcher(jlf).matches()==false||"".equals(jlf)||null==jlf){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"交流负荷为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			 String zl=b[11].toString().trim();//直流负荷
			 String zlf=zl.replaceAll(" ", "");
			 if(pattern.matcher(zlf).matches()==false||"".equals(zlf)||null==zlf){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"直流负荷为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			 String kdsbdk=b[12].toString().trim();//宽带设备端口数
			 String kds=kdsbdk.replaceAll(" ", "");
			 if(pattern.matcher(kds).matches()==false||"".equals(kds)||null==kds){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"宽带设备端口数为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			 String yysbdk=b[13].toString().trim();//语音设备端口数
			 String yysl=yysbdk.replaceAll(" ", "");
			 if(pattern.matcher(yysl).matches()==false||"".equals(yysl)||null==yysl){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"语音设备端口数为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			 String kty=b[14].toString().trim();//空调1匹数
			 String ktyy=kty.replaceAll(" ", "");
			 if(pattern.matcher(ktyy).matches()==false||"".equals(ktyy)||null==ktyy){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"空调1匹数数为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			 String kte=b[15].toString().trim();//空调2匹数
			 String ktee=kte.replaceAll(" ", "");
			 if(pattern.matcher(ktee).matches()==false||"".equals(ktee)||null==ktee){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"空调2匹数数为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			// 共享信息	远供rru数量	移动共享设备数量	电信共享设备数量	
			 // 站点属性	站点类型	全省定标电量	额定耗电量(市定标)
          String gx=b[16].toString().trim();//共享信息
          if("".equals(gx)||null==gx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"共享信息为空");
			  wrongContent.add(row);
			  break;
		  }
          String ru=b[17].toString().trim();//rru数量
          String rr=ru.replaceAll(" ", "");
          if(pattern.matcher(rr).matches()==false||"".equals(rr)||null==rr){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"远供rru数量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
          String ydgxx=b[18].toString().trim();//移动共享设备数量
          String yd=ydgxx.replaceAll(" ","");
          if(pattern.matcher(yd).matches()==false||"".equals(yd)||null==yd){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"移动共享设备数量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
          
          
          String dxgxx=b[19].toString().trim();//电信共享设备数量
          String dx=dxgxx.replaceAll(" ","");
          if(pattern.matcher(dx).matches()==false||"".equals(dx)||null==dx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"电信共享设备数量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
          String zdsxx=b[20].toString().trim();//站点属性
          if("".equals(zdsxx)||null==zdsxx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"站点属性为空");
			  wrongContent.add(row);
			  break;
		  }
          String zdlxx=b[21].toString().trim();//站点类型
          if("".equals(zdlxx)||null==zdlxx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"站点类型为空");
			  wrongContent.add(row);
			  break;
		  }
          String qsdb=b[22].toString().trim();//全省定标电量
          String qs=qsdb.replaceAll(" ", "");
          if(pattern.matcher(qs).matches()==false||"".equals(qs)||null==qs){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"全省定标电量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
          String edhd=b[23].toString().trim();//额定耗电量
          String ed=edhd.replaceAll(" ","");
          if(pattern.matcher(ed).matches()==false||"".equals(ed)||null==ed){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"额定耗电量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
         
          zhandianbean zd=new zhandianbean();
			zd.setShi(shi);
			System.out.println("shi:"+shi);
			zd.setXian(b[1].toString().trim());
			System.out.println("xian:"+b[1].toString().trim());
			zd.setXiaoqu(b[2].toString().trim());
System.out.println("xiaoqu/:"+b[2].toString().trim());
			zd.setId(st);
			zd.setJzname(b[4].toString().trim());
			String qy=isqy;
			if("是".equals(qy)){qy="1";}
			else if("否".equals(qy)){qy="0";}
			zd.setQyzt(qy);
			zd.setYflx(yflx);
			zd.setGdfs(gdfs);
			zd.setDianfei(b[8].toString().trim());
			zd.setZdcq(zdcq);
			zd.setJflx(jlf);
			zd.setZlfh(zlf);
			double k = Double.parseDouble(kds);
			zd.setKdsbdk(k);// zd//宽带k
			double y = Double.parseDouble(yysl);
			zd.setYysbdk(y);// zd//语音
			double ky = Double.parseDouble(ktyy);
			zd.setKtyps(ky);// 空调1psktyy
			double ke = Double.parseDouble(ktee);
			zd.setKteps(ke);
			zd.setGxxx(gx); // 共享信息

			double rruu = Double.parseDouble(rr);
			zd.setRru(rruu);// 远供rru 
			double yydd = Double.parseDouble(yd);
			zd.setYdgxsbsl(yydd);// 移动共享设备数量
			double ddxx = Double.parseDouble(dx);
			zd.setDxgxsbsl(ddxx);// 电信共享设备数量
			zd.setZdsx(zdsxx);// 站点属性
			zd.setZdlx(zdlxx);// 站点类型
			double qss = Double.parseDouble(qs);
			zd.setQsdbdl(qss);// 全省定标电量
			double edd = Double.parseDouble(ed);
			zd.setEdhdl(ed); // 额定耗电量
			
			map.put(zd.getId(), zd);
			System.out.println("444444444");
			Iterator iter = map.entrySet().iterator();
			
            ls.add(zd); 
			}
		  return map;

		}
	  public synchronized Map getlsjyjf(Vector content,CountForm cform,String accountname,String loginId)  {
		  System.out.println("走到这里了！！！！=====");
		  String lastdatetime="";
		  Date date1 = null,date2=null;
		  String thisdatetime="",startmonth="",endmonth="",inputdatetime="",accountmonth="",notetime="",kptime="",paydatetime="";
		  String dbid1="";  String blhdl=""; String actualpay="";
		  
		  Object[] a=content.toArray();
		  
		  
		  
		  System.out.println("表数据个数："+a.length);
		  DaoruDianLiang bean = new DaoruDianLiang();
		  DaoruDianFei fees = new DaoruDianFei();
		  AutoAuditBean abean = new AutoAuditBean();
		
		  zhandianbean zdbean=new zhandianbean();
		  	 ArrayList fylist = new ArrayList();
		  	Map<String, zhandianbean> map=new HashMap<String,zhandianbean>();
		  	 fylist = abean.getPageData(1,"");
		  	 List<zhandianbean> ls=new ArrayList();
		  for(int i=0;i<a.length;i++){		
			  
			  String shi,xian,xiaoqu,zdid,zdname,isqy,yflx;
			  String gdfs,zdcq,gxxx,zdsx,zdlx;
			  double dj,jlfh,zlfh,ktyps,kteps,sdbdl,qsdbdl;
			  int kd=0,yy=0,rru=0,ydgx=0,dxgx=0;
			  System.out.println("数据量："+i);
			  Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 		  
			  Pattern pattern1 = Pattern.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*"); 
			  
			  Vector<String> row=new Vector<String>();
			  Object[] b=((Vector)a[i]).toArray();
			 zdid= b[3].toString().trim();
			 shi=b[0].toString().trim();
			 if("".equals(shi)||null==shi){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"市为空！");
				  wrongContent.add(row);
				  break;
			  }
			 String st = zdid.replaceAll(" ", "");
			 System.out.println("站点id："+zdid);
			 if("".equals(st)||null==st){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("未查到"+b[4].toString()+b[2].toString()+"站点id"+dbid1);
				  wrongContent.add(row);
				  break;
			  }
			 isqy=b[5].toString().trim();
			 if("".equals(isqy)||null==isqy){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"启用状态为空");
				  wrongContent.add(row);
				  break;
			  }
			 
			 
			 yflx=b[6].toString().trim();
			 if("".equals(yflx)||null==yflx){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"用房类型为空");
				  wrongContent.add(row);
				  break;
			  }
			 
			 gdfs=b[7].toString().trim();
			 if("".equals(gdfs)||null==gdfs){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"供电方式为空");
				  wrongContent.add(row);
				  break;
			  }
          
			 
			 
			
			 String jl=b[9].toString().trim();//交流负荷
			 String jlf = jl.replaceAll(" ", "");
			 if(pattern.matcher(jlf).matches()==false||"".equals(jlf)||null==jlf){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"交流负荷为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
			
			 String zl=b[10].toString().trim();//直流负荷
			 String zlf=zl.replaceAll(" ", "");
			 if(pattern.matcher(zlf).matches()==false||"".equals(zlf)||null==zlf){
				  for(int j=0;j<b.length;j++){
		       		   row.add(b[j].toString().trim());
		       	      }
				  row.add("站点id"+b[4].toString()+b[2].toString()+"直流负荷为空或者格式不正确");
				  wrongContent.add(row);
				  break;
			  }
//			 String kdsbdk=b[12].toString().trim();//宽带设备端口数
//			 String kds=kdsbdk.replaceAll(" ", "");
//			 if(pattern.matcher(kds).matches()==false||"".equals(kds)||null==kds){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add("站点id"+b[4].toString()+b[2].toString()+"宽带设备端口数为空或者格式不正确");
//				  wrongContent.add(row);
//				  break;
//			  }
//			 String yysbdk=b[13].toString().trim();//语音设备端口数
//			 String yysl=yysbdk.replaceAll(" ", "");
//			 if(pattern.matcher(yysl).matches()==false||"".equals(yysl)||null==yysl){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add("站点id"+b[4].toString()+b[2].toString()+"语音设备端口数为空或者格式不正确");
//				  wrongContent.add(row);
//				  break;
//			  }
//			 String kty=b[14].toString().trim();//空调1匹数
//			 String ktyy=kty.replaceAll(" ", "");
//			 if(pattern.matcher(ktyy).matches()==false||"".equals(ktyy)||null==ktyy){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add("站点id"+b[4].toString()+b[2].toString()+"空调1匹数数为空或者格式不正确");
//				  wrongContent.add(row);
//				  break;
//			  }
//			 String kte=b[15].toString().trim();//空调2匹数
//			 String ktee=kte.replaceAll(" ", "");
//			 if(pattern.matcher(ktee).matches()==false||"".equals(ktee)||null==ktee){
//				  for(int j=0;j<b.length;j++){
//		       		   row.add(b[j].toString().trim());
//		       	      }
//				  row.add("站点id"+b[4].toString()+b[2].toString()+"空调2匹数数为空或者格式不正确");
//				  wrongContent.add(row);
//				  break;
//			  }
//			// 共享信息	远供rru数量	移动共享设备数量	电信共享设备数量	
//			 // 站点属性	站点类型	全省定标电量	额定耗电量(市定标)
//          String gx=b[16].toString().trim();//共享信息
//          if("".equals(gx)||null==gx){
//			  for(int j=0;j<b.length;j++){
//	       		   row.add(b[j].toString().trim());
//	       	      }
//			  row.add("站点id"+b[4].toString()+b[2].toString()+"共享信息为空");
//			  wrongContent.add(row);
//			  break;
//		  }
//          String ru=b[17].toString().trim();//rru数量
//          String rr=ru.replaceAll(" ", "");
//          if(pattern.matcher(rr).matches()==false||"".equals(rr)||null==rr){
//			  for(int j=0;j<b.length;j++){
//	       		   row.add(b[j].toString().trim());
//	       	      }
//			  row.add("站点id"+b[4].toString()+b[2].toString()+"远供rru数量为空或者格式不正确");
//			  wrongContent.add(row);
//			  break;
//		  }
//          String ydgxx=b[18].toString().trim();//移动共享设备数量
//          String yd=ydgxx.replaceAll(" ","");
//          if(pattern.matcher(yd).matches()==false||"".equals(yd)||null==yd){
//			  for(int j=0;j<b.length;j++){
//	       		   row.add(b[j].toString().trim());
//	       	      }
//			  row.add("站点id"+b[4].toString()+b[2].toString()+"移动共享设备数量为空或者格式不正确");
//			  wrongContent.add(row);
//			  break;
//		  }
//          
//          
//          String dxgxx=b[19].toString().trim();//电信共享设备数量
//          String dx=dxgxx.replaceAll(" ","");
//          if(pattern.matcher(dx).matches()==false||"".equals(dx)||null==dx){
//			  for(int j=0;j<b.length;j++){
//	       		   row.add(b[j].toString().trim());
//	       	      }
//			  row.add("站点id"+b[4].toString()+b[2].toString()+"电信共享设备数量为空或者格式不正确");
//			  wrongContent.add(row);
//			  break;
//		  }
          String zdsxx=b[11].toString().trim();//站点属性
          if("".equals(zdsxx)||null==zdsxx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"站点属性为空");
			  wrongContent.add(row);
			  break;
		  }
          String zdlxx=b[12].toString().trim();//站点类型
          if("".equals(zdlxx)||null==zdlxx){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"站点类型为空");
			  wrongContent.add(row);
			  break;
		  }
          
          
          
          String qsdb=b[14].toString().trim();//全省定标电量
          String qs=qsdb.replaceAll(" ", "");
          if(pattern.matcher(qs).matches()==false||"".equals(qs)||null==qs){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"全省定标电量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
          String edhd=b[13].toString().trim();//额定耗电量
          String ed=edhd.replaceAll(" ","");
          if(pattern.matcher(ed).matches()==false||"".equals(ed)||null==ed){
			  for(int j=0;j<b.length;j++){
	       		   row.add(b[j].toString().trim());
	       	      }
			  row.add("站点id"+b[4].toString()+b[2].toString()+"额定耗电量为空或者格式不正确");
			  wrongContent.add(row);
			  break;
		  }
         
          zhandianbean zd=new zhandianbean();
			zd.setShi(shi);
			System.out.println("shi:"+shi);
			zd.setXian(b[1].toString().trim());
			System.out.println("xian:"+b[1].toString().trim());
			zd.setXiaoqu(b[2].toString().trim());
System.out.println("xiaoqu/:"+b[2].toString().trim());
			zd.setId(st);
			zd.setJzname(b[4].toString().trim());
			String qy=isqy;
			if("是".equals(qy)){qy="1";}
			else if("否".equals(qy)){qy="0";}
			zd.setQyzt(qy);
			zd.setGdfs(gdfs);
			zd.setDianfei(b[8].toString().trim());
			zd.setJflx(jlf);
			zd.setZlfh(zlf);
		    zd.setYflx(yflx);
			zd.setZdsx(zdsxx);// 站点属性
			zd.setZdlx(zdlxx);// 站点类型
			double qss = Double.parseDouble(qs);
			zd.setQsdbdl(qss);// 全省定标电量
			double edd = Double.parseDouble(ed);
			zd.setEdhdl(ed); // 额定耗电量
			
			map.put(zd.getId(), zd);
			System.out.println("444444444");
			Iterator iter = map.entrySet().iterator();
            ls.add(zd); 
			}
		  return map;

		}
}
