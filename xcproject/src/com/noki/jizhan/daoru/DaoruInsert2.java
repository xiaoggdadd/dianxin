package com.noki.jizhan.daoru;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import com.noki.ammeterdegree.javabean.DaoFormBean;
import com.noki.database.DataBase;
import java.util.Iterator;
import com.noki.database.DbException;


import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DaoruInsert2 {
    public DaoruInsert2() {
        try {
            db = new DataBase();
            db.connectDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    StringBuffer sql = new StringBuffer();
    String sql2=new String();
    StringBuffer sql3=new StringBuffer();
    Vector wrongContent = new Vector();
    public DataBase db;


    public void closeDb() {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResultSet rs = null;

	public synchronized Vector getWrong(Vector content, CountForm cform,String accountname) {
    	 SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        int m = 0, i = 0, cg = 0, bcg = 0, zg = 0;
       // System.out.println("content==" + content.size());
        for (Iterator it = content.iterator(); it.hasNext(); ) {
        	 double sc=0.0,bg=0.0,yy=0.0,xxh=0.0,jstz=0.0;
        	 String amuuid="";long uuid=0,uuid1=0;
        	 String address="",xiaoqu="",zdcode="",dbid="",thisdegree="",lastdegree="",stationtype="",yflx="",zlfh="",fkzq="",edhdl="";
        	 String lastdatetime="",thisdatetime="",startmonth="",endmonth="",actualpay="",floatdegree="",floatpay="";
        	 String actualdegree="",unitprice="",accountmonth="", scd="",jzname="";
        	 double scdl=0.0,bgdl=0.0,yydl=0.0,xxhdl=0.0,jstzdl=0.0,scdf=0.0,bgdf=0.0,yydf=0.0,xxhdf=0.0,jstzdf=0.0;
            zg++;
            m++;
            i = 0;
            Vector row = (Vector) it.next();
            Iterator iter = row.iterator();
            sql.setLength(0);
            sql3.setLength(0);
            sql.append("INSERT INTO ZHANDIAN(SHENG,SHI,ZDCODE,XIAN,XIAOQU,JZNAME,ADDRESS,STATIONTYPE,LYJHJGS,ZDCQ,YFLX,DYTYPE,G2,G3,KDSB,YYSB,ZPSL,ZSSL,KDSBSL,YYSBSL,KT1,KT2,ZLFH,JLFH,ZGD,MEMO,YID,DIANFEI,BGSIGN,JNGLMK,GDFS,EDHDL,ENTRYPENSONNEL,ENTRYTIME,SHSIGN,QYZT,ZDBZW) values(");
            sql3.append("INSERT INTO DIANBIAO(ZDID,DBID,DBNAME,DFZFLX,BEILV,LINELOSSTYPE,LINELOSSVALUE,DBYT,ENTRYPENSONNEL,ENTRYTIME,BZW,DBQYZT) values(");
            String con = "";
            String tempstr="";
            String fagName="";

            try {
                boolean bin = false;
                while (iter.hasNext()) {
                	
                    tempstr = "";

                    Object value = iter.next();
                    if (value == null) {
                        value = "";
                    }
                    con = value.toString().trim();
                   
                    if (i >= 0 && i <= 3) {
                        
                        if (con == null || con.length() == 0) {
                            bin = true;
                            row.add("导入失败！省市县、乡镇不能为空！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                        }
                          switch (i) {
                            case 0:
                            	tempstr = this.getAreaCode(db, con.trim());
                            	//System.out.println(con+tempstr+"省1");
                            	if(tempstr==""){
                            		bin=true;
                            		 row.add("导入失败！省名称不存在！");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	 break;
                            case 1:
                            	tempstr = this.getAreaCode(db, con.trim());
                            	//生成随机数 为后面的站点id做基础
                            	 Random rnd = new Random();
                            	 zdcode = (String) (rnd.nextInt(999999999)+"");
                            	 
                            	//System.out.println(con+tempstr+"省2");
                            	if(tempstr==""){
                            		bin=true;
                            		 row.add("导入失败！市名称不存在！");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	  break;
                            case 2:
                            	tempstr = this.getExactAreaCode(db, con.trim(),fagName);
                            	//System.out.println(con+tempstr+"省3");
                            	if(tempstr==""){
                            		bin=true;
                           		    row.add("导入失败！对应市下的区县名称不存在！");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                            case 3:
                            	tempstr = this.getExactAreaCode(db, con.trim(),fagName);
                              xiaoqu=tempstr;
                            	//System.out.println(con+tempstr+"省4");
                            	if(tempstr==""){
                            		bin=true;
                           		    row.add("导入失败！对应县下的乡镇名称不存在！");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                        }       
                        if(i >= 1 && i <= 3){
                        	fagName=con.trim();
                        	//System.out.println(fagName+"父级区域");
                        }
                        switch (i) {
                        case 0:
                        	//System.out.println(tempstr+"到了！！");
                            sql.append("'" + tempstr + "'");
                            break;
                        case 1:
                            sql.append(",'" + tempstr + "','" + zdcode + "'");
                            break;
                        case 2:
                            sql.append(",'" + tempstr + "'");
                            break;
                        case 3:
                            sql.append(",'" + tempstr + "'");
                            break;
                         }
                       }
                     
	            	   if (i == 4) { 
	            		   jzname=con.trim();
	            		   sql.append(",'" + con.trim() + "'");
                     } if (i == 5) {
                    	 address=con.trim();
                    	 sql.append(",'" + con.trim() + "'");
                     } if (i == 6) { 
                    	 System.out.println("xiaoqu:"+xiaoqu);
                    	 stationtype=this.getPropertyCode(db, con.trim(), "stationtype");
                    	 
                    	 String sqlw="select id from zhandian z where z.xiaoqu='"+xiaoqu+"' and jzname ='"+jzname+"' and z.stationtype='"+stationtype+"' and address='"+address+"'";
                    	
                    	 DataBase db2 = new DataBase();     
                         ArrayList<String> listzd=new ArrayList<String>() ;
                         ResultSet  rs2=null;
                         try {  
                          System.out.println("121212121"+sqlw);

                   		  rs2 = db2.queryAll(sqlw);	
                   		 if(rs2.next()){		
                       	
                   		        
                          		System.out.println("1212121不能重复导入21"+rs2);
                          		bin=true;
                       		    row.add("导入失败！不能重复导入！");
                                wrongContent.add(row);
                                bcg++;
                                rs2.close();
                           		db2.close();
                           	
                                break;
                   	    	
                   			    	   		      
                   	      }
                   		 rs2.close();
                         db2.close();
                   		
                   		 
                         } catch (Exception e) {
                     		e.printStackTrace();
                     	 }
                    	 
                    	 
                    	 sql.append(",'" + stationtype +"'");	
                     }  if (i == 7) {
                    	 String ss="";
                    	 if(con.trim().contains(".")){
                    		 int d=con.indexOf(".");
                    		ss=con.substring(0,d);
                    	 }
                    	 sql.append(",'" +ss+ "'");	
                     } if (i == 8) { 
                    	 sql.append(",'" + this.getPropertyCode(db, con.trim(), "PropertyRightType") + "'");	
                     } if (i == 9) { 
                    	 yflx=this.getPropertyCode(db, con.trim(), "FWLX");
                    	 sql.append(",'" + yflx + "'");	
                     } if (i == 10) { 
                    	 sql.append(",'" + this.getPropertyCode(db, con.trim(), "dytype") + "'");	
                     } if (i == 11) {
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 12) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 13) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 14) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 15) {
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 16) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 17) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 18) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 19) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 20) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 21) { 
                    	 sql.append(",'" + zlfh + "'");	
                     } if (i == 22) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 23) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 24) {
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 25) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 26) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 27) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 28) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 29) { 
                    	 sql.append(",'" + this.getPropertyCode(db, con.trim(), "GDFS") + "'");	
                     } if (i == 30) { 
                    	 sql.append(",'" + con.trim() + "'");	
                     } if (i == 31) {
                         sql2="INSERT INTO ZDDFINFO(ZDID,FKZQ) values ((select id from zhandian where zdcode='"+zdcode+"'),'"+this.getPropertyCode(db, con.trim(), "FKZQ")+"')";
                     } if (i == 32) {
                         sql3.append("'" + zdcode + "'");
                       Random rnd = new Random();
                       dbid = (String) (rnd.nextInt(999999999)+"");
                      
               		 sql3.append(",'" + dbid +"','结算电表','"+ this.getPropertyCode(db, con.trim(), "dfzflx")+ "'"); 
                     }if(i==33){
                         sql3.append(",'" + con.trim() + "'");
                     }if(i==34){
                         sql3.append(",'" + this.getPropertyCode(db, con.trim(), "xslx") + "'");
                     }if(i==35){
                         sql3.append(",'" + con.trim() + "','dbyt01'");
                     }if(i==36){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		sc=Double.parseDouble(con);
                   		    String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+1+"','100','"+dbid+1+"','"+dbid+1+"','zylx01','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    		 System.out.println(sql+"sql");
                    	 }
                     }if(i==37){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 xxh=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+4+"','100','"+dbid+4+"','"+dbid+4+"','zylx04','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }  
                     }if(i==38){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 bg=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+3+"','100','"+dbid+3+"','"+dbid+3+"','zylx03','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }
                     }if(i==39){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 yy=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+2+"','100','"+dbid+2+"','"+dbid+2+"','zylx02','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }
                     }if(i==40){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 jstz=Double.parseDouble(con);
                    		 if("100".equals(con)||"100.0".equals(con)){
                        		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+5+"','100','"+dbid+5+"','"+dbid+5+"','zylx05','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                        		 db.update(sql);
                        		 }else{
                        			 bin=true;
                            		 row.add("导入失败！建设投资的比例只能是100或者0！");
                                     wrongContent.add(row);
                                     bcg++;
                                     break;
                        		 }
                    	 }
                     }if(i==41){
                    	 lastdegree=con;
                    	
                     }if(i==42){
                    	 thisdegree=con;
                     	
                     }if(i==43){
                	  if(con.contains("/")){
            	          try {
            	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            	        	Date date =sdf.parse(con);
            	        	con=sdf1.format(date);
            	        	
            			} catch (ParseException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			}
            			 
            			}
                	  lastdatetime=con;
                	
                     }if(i==44){
                    	 if(con.contains("/")){
                    	 try {
             	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
             	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
             	        	Date date =sdf.parse(con);
             	        	con=sdf1.format(date);
             	        	
             			} catch (ParseException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}
             			 
             			}
                    	 thisdatetime=con;
                     	
                     }if(i==45){
                    	 if(con==null||con==""||con==" "){
                    		 con="0";
                    	 }
                    	 floatdegree=con;
                     	
                     }if(i==46){
                    	amuuid = UUID.randomUUID().toString().trim().replace("-",""); 
                        uuid=System.currentTimeMillis();
                         double cons=0.0;
                         if(con!=null&&con!=""&&con!=" "){
                        	 cons=Double.parseDouble(con);
	                         scdl=(cons*sc)/100.0;
	                         xxhdl=(cons*xxh)/100.0;
	                         bgdl=(cons*bg)/100.0;
	                         yydl=(cons*yy)/100.0;
	                         jstzdl=(cons*jstz)/100.0;
	                         
                         }
                         actualdegree=con;
                         //System.out.println(sc+"| "+xxh+"| "+bg+"| "+yy+"| "+jstz);
                        // System.out.println(scdl+"| "+xxhdl+"| "+bgdl+"| "+yydl+"| "+jstzdl);
                     }if(i==47){
                    	 if(con.contains("/")){
                    	 try {
             	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
             	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
             	        	Date date =sdf.parse(con);
             	        	con=sdf1.format(date);
             	        	
             			} catch (ParseException e) {
             				// TODO Auto-generated catch block
             				e.printStackTrace();
             			}
             			 
             			}
                    	 startmonth=con;
                     }if(i==48){
                    	 if(con.contains("/")){
                        	 try {
                 	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
                 	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                 	        	Date date =sdf.parse(con);
                 	        	con=sdf1.format(date);
                 	        	
                 			} catch (ParseException e) {
                 				// TODO Auto-generated catch block
                 				e.printStackTrace();
                 			}
                 		
                 			}
                    	 endmonth=con;
                     }
                     
                     if(i==49){
                    	 unitprice=con;
                     }if(i==50){
                    	 if(con==null||con==""||con==" "){
                    		 con="0";
                    	 }
                    	 floatpay=con;
                     }if(i==51){
                    	 double cons=0.0;
                         if(con!=null&&con!=""&&con!=" "){
                    		 cons=Double.parseDouble(con);
	                         scdf=(cons*sc)/100.0;
	                         xxhdf=(cons*xxh)/100.0;
	                         bgdf=(cons*bg)/100.0;
	                         yydf=(cons*yy)/100.0;
	                         jstzdf=(cons*jstz)/100.0;
                         }
                         actualpay=con;
                         //System.out.println(sc+"| "+xxh+"| "+bg+"| "+yy+"| "+jstz);
                        // System.out.println(scdf+"| "+xxhdf+"| "+bgdf+"| "+yydf+"| "+jstzdf);
                     }if(i==52){
                    	  uuid1 = System.currentTimeMillis();
                    	 if(con.contains("/")){
                        	 try {
                 	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
                 	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                 	        	Date date =sdf.parse(con);
                 	        	con=sdf1.format(date);
                 	        	
                 			} catch (ParseException e) {
                 				// TODO Auto-generated catch block
                 				e.printStackTrace();
                 			}
                 			 
                 			}
                    	 accountmonth=con;
                    	 System.out.println("accountmonth"+accountmonth);
                     }
                    i++;
                }
               
                sql.append(",'"+accountname+"','"+mat.format(new Date())+"','1','1','0')");
                sql3.append(",'"+accountname+"','"+mat.format(new Date())+"','1','0')");
                
                DaoFormBean formBean= new DaoFormBean();
                formBean.setAmmeteridFk(dbid);
                formBean.setBgdl(bgdl+"");
                formBean.setScdl(scdl+"");
                formBean.setYydl(yydl+"");
                formBean.setXxhdl(xxhdl+"");
                formBean.setJstzdl(jstzdl+"");
                formBean.setBgdf(bgdf+"");
                formBean.setScdf(scdf+"");
                formBean.setYydf(yydf+"");
                formBean.setXxhdf(xxhdf+"");
                formBean.setJstzdf(jstzdf+"");
                formBean.setEntrypensonnel(accountname);
                formBean.setEntrytime(mat.format(new Date()));
                formBean.setLastdatetime(lastdatetime);
                formBean.setThisdatetime(thisdatetime);
                formBean.setLastdegree(lastdegree);
                formBean.setThisdegree(thisdegree);
                formBean.setFloatdegree(floatdegree);
                formBean.setFloatpay(floatpay);
                formBean.setActualdegree(actualdegree);
                formBean.setActualpay(actualpay);
                formBean.setStartmonth(startmonth);
                formBean.setEndmonth(endmonth);
                formBean.setUnitprice(unitprice);
                formBean.setAccountmonth(accountmonth);
                String sql4="update zhandian set zdcode=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from zhandian where zdcode='"+zdcode+"')";
                String sql5="update DIANBIAO set dbid=(select id from DIANBIAO where dbid='"+dbid+"'),zdid=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from dianbiao where dbid='"+dbid+"')";
                String sql6="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0101',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '01' where id=(select id from sbgl where DIANBIAOID='"+dbid+1+"')";
                String sql7="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0202',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '02' where id=(select id from sbgl where DIANBIAOID='"+dbid+2+"')";
                String sql8="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0303',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '03' where id=(select id from sbgl where DIANBIAOID='"+dbid+3+"')";
                String sql9="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0404',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '04' where id=(select id from sbgl where DIANBIAOID='"+dbid+4+"')";
                String sql10="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0505',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '05' where id=(select id from sbgl where DIANBIAOID='"+dbid+5+"')";

                int tt = -1;
                if (!bin) {
                							System.out.println("站点导入："+sql.toString()); 
                	  //站点导入
                      tt = db.update(sql.toString());	
                     //缴费周期类型
                	  						System.out.println("缴费周期导入："+sql2.toString()); 
                      db.update(sql2);
                      //电表导入
                	  						System.out.println("电表导入："+sql3.toString()); 
	                  tt=db.update(sql3.toString());
	                 
                   DataBase db = new DataBase();     
                   ArrayList<String> listzd=new ArrayList<String>() ;
                   rs=null;
                   try {  	
                	rs=db.queryAll(sql6);
                	//System.out.println(sql6.toString()); 
                	rs=db.queryAll(sql7);
                	rs=db.queryAll(sql8);
                	rs=db.queryAll(sql9);
                	rs=db.queryAll(sql10);
                	if(lastdegree!=null&&lastdegree!=" "&&lastdegree!=""&&thisdegree!=null&&thisdegree!=" "&&thisdegree!=""){
 	                	//电量导入
 	                	String msg=formBean.addDegreeFees(formBean,amuuid,uuid,this.getDLID1(dbid));
 	                	//电费导入 
 	                	String msg1=formBean.addFeesDegree(formBean,startmonth,endmonth,this.getDLID(amuuid),uuid);
                   
                    }
             		rs = db.queryAll(sql5);	
             		System.out.println("电表修改："+sql5.toString()); 
             		rs = db.queryAll(sql4);
             		System.out.println("站点修改:"+sql4);
             		 
             			
                   } catch (Exception e) {
               		e.printStackTrace();
               	   }
                    closeDb();
                    
                    cg++;
                }
                if (tt != 1 && !bin) {
                    bcg++;
                    row.add("导入失败！");
                    wrongContent.add(row);
                }

            } catch (Exception e) {
                e.printStackTrace();
                row.add(e.toString());
                wrongContent.add(row);
            }

        }
        
        
       // System.out.println(wrongContent);
        cform.setCg(cg);
        cform.setBcg(bcg);
        cform.setZg(zg);
        return wrongContent;
    }

    private String getAreaCode(DataBase db, String name) throws Exception,
            SQLException {
        String code = "";
        DataBase db11=new DataBase();
       // System.out.println("name"+name.trim());
        rs = db11.queryAll("select agcode from d_area_grade where agname like '%" +
                         name.trim() + "%'");
        
        if (rs.next()) {
        	//System.out.println("--"+rs.getString(1));
            return rs.getString(1);
        }
        closeDb();
        return code;
    }
    //当获得的区域Code为县或乡镇时    采用精准的判断方法（同时判断名称和父级区域名称对应的编码）王代军
    private String getExactAreaCode(DataBase db, String name,String fagName) throws Exception,
    SQLException {
		String code = "";
		rs=null;
		rs = db.queryAll("select agcode from d_area_grade where agname = '" +
		 name.trim() + "' and fagcode in (select agcode from d_area_grade where agname = '" +fagName.trim() + "' )");
		if (rs.next()) {
		    return rs.getString(1);
		}
		closeDb();
		return code;
		}
    private String getPropertyCode(DataBase db, String name, String typename) throws
            Exception,
            SQLException {
        String code = "";
        rs=null;
            // System.out.println("select code from indexs where name = '" +
       //                    name.trim() + "' and type='" + typename + "'");
        rs = db.queryAll("select code from indexs where name = '" +
                         name.trim() + "' and type='" + typename + "'");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            code = "0";
        }
        closeDb();
        return code;
    }
    private ArrayList getDLID(String amuuid) {
  	  String sql ="select ammeterdegreeid from AMMETERDEGREES where amuuid='"+amuuid+"'";
       // System.out.println("--电表amuuid返回id--"+sql);
    
        ArrayList list=new ArrayList() ;
        rs=null;
        try {
  		rs = db.queryAll(sql);
  	
  	      while(rs.next()) {
  	    	 // System.out.println(rs.getString("ammeterdegreeid"));
  	    	  list.add(rs.getString("ammeterdegreeid"));
  	      }
  	     
        } catch (Exception e) {
    		e.printStackTrace();
    	 }
        closeDb();
       return list;
       
    
  }
    private ArrayList getDLID1(String dbid) {
    	  String sql ="select id from dianbiao where dbid='"+dbid+"'";
          System.out.println("--电表返回id--"+sql);
          ArrayList list=new ArrayList() ;
          rs=null;
          try {
    		rs = db.queryAll(sql);
    	      while(rs.next()) {
    	    	 // System.out.println(rs.getString("ammeterdegreeid"));
    	    	  list.add(rs.getString("id"));
    	      }
    	     
          } catch (Exception e) {
      		e.printStackTrace();
      	 }
          closeDb();
         return list;
    }


}
