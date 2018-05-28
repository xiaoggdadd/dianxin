package com.noki.jizhan.daoru;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;
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
public class DaoruInsert {
    public DaoruInsert() {
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
    StringBuffer sql13=new StringBuffer();
    StringBuffer sql11=new StringBuffer();
    Vector wrongContent = new Vector();
    public DataBase db;


    public void closeDb() {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public synchronized Vector getWrong(Vector content, CountForm cform,String accountname) {
    	 SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        int m = 0, i = 0, cg = 0, bcg = 0, zg = 0;
        String fkzq="";
       // System.out.println("content==" + content.size());
        for (Iterator it = content.iterator(); it.hasNext(); ) {
        	 double sc=0.0,bg=0.0,yy=0.0,xxh=0.0,jstz=0.0;
        	 String zdcode="";String dbid="",thisdegree="",lastdegree="",stationtype="",yflx="",zlfh="",xlx="",jrwtype="",zdsx="";
        	 double scdl=0.0,bgdl=0.0,yydl=0.0,xxhdl=0.0,jstzdl=0.0,scdf=0.0,bgdf=0.0,yydf=0.0,xxhdf=0.0,jstzdf=0.0;
            zg++;
            m++;
            i = 0;
            Vector row = (Vector) it.next();
            Iterator iter = row.iterator();
            sql.setLength(0);
            sql3.setLength(0);
            sql11.setLength(0);
            sql13.setLength(0);
            sql.append("INSERT INTO ZHANDIAN(SHENG,SHI,ZDCODE,XIAN,XIAOQU,JZNAME,ADDRESS,STATIONTYPE,PROPERTY,JZTYPE,ZDCQ,YFLX,DYTYPE,G2,G3,KDSB,YYSB,ZPSL,ZSSL,KDSBSL,YYSBSL,KT1,KT2,ZLFH,XLX,JRWTYPE,JLFH,ZGD,MEMO,YID,DIANFEI,BGSIGN,JNGLMK,GDFS,EDHDL,ENTRYPENSONNEL,ENTRYTIME,SHSIGN,QYZT,ZDBZW) values(");
            sql3.append("INSERT INTO DIANBIAO(ZDID,DBID,DBNAME,DFZFLX,BEILV,LINELOSSTYPE,LINELOSSVALUE,DBYT,ENTRYPENSONNEL,ENTRYTIME,BZW,DBQYZT) values(");
            sql11.append("INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,BLHDL,NETWORKHDL,INFORMATIZATIONHDL,ADMINISTRATIVEHDL,MARKETHDL,BUILDHDL,AMUUID,UUID,STARTMONTH,ENDMONTH,ENTRYPENSONNEL,ENTRYTIME,MANUALAUDITSTATUS,DLBZW) VALUES(");
            sql13.append("INSERT INTO ELECTRICFEES(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,ACTUALPAY,NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF,DFUUID,ACCOUNTMONTH,ENTRYPENSONNEL,ENTRYTIME,MANUALAUDITSTATUS,CITYAUDIT,DFBZW) VALUES(");

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
                            	tempstr = this.getAreaCode(db, con);
                            	//System.out.println(con+tempstr+"省1");
                            	if(tempstr==""){
                            		bin=true;
                            		 row.add("导入失败！省名称不存在！");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	 break;
                            case 1:
                            	tempstr = this.getAreaCode(db, con);
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
                            	tempstr = this.getExactAreaCode(db, con,fagName);
                            	//System.out.println(con+tempstr+"省3");
                            	if(tempstr==""){
                            		bin=true;
                           		    row.add("导入失败！对应市下的区县名称不存在！");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                            case 3:
                            	tempstr = this.getExactAreaCode(db, con,fagName);
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
                        	fagName=con;
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
                     
	            	   if (i == 4) { sql.append(",'" + con + "'");	
                     } if (i == 5) { sql.append(",'" + con + "'");	
                     } if (i == 6) { 
                    	 stationtype=this.getPropertyCode(db, con, "stationtype");
						String zdsxc="",zdlx="";
                    	 zdsx=this.getPropertyCode(db, con, "ZDSX");
                    	 if("StationType02".equals(stationtype)){
                    		 zdsxc="zdsx02";
                    		 zdlx="zdlx08";
                    	 }if("StationType03".equals(stationtype)){
                    		 zdsxc="zdsx05";
                    		 zdlx="zdlx07";
                    	 }if("StationType12".equals(stationtype)){
                    		 zdsxc="zdsx05";
                    		 zdlx="zdlx07";
                    	 }
                    	 sql.append(",'" + stationtype + "','"+zdsxc+"','"+zdlx+"'");	
                     } if (i == 7) { sql.append(",'" + this.getPropertyCode(db, con, "PropertyRightType") + "'");	
                     } if (i == 8) { 
                    	 yflx=this.getPropertyCode(db, con, "FWLX");
                    	 sql.append(",'" + yflx + "'");	
                    	 
                    	 
                    	 
                    	 
                     } if (i == 9) { sql.append(",'" + this.getPropertyCode(db, con, "dytype") + "'");	
                     } if (i == 10) {
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
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
                     } if (i == 14) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 15) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 16) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 17) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 18) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 19) { 
                    	 if (con.trim().equals("有")) {
                             con = "1";
                    	 } else if (con.trim().equals("无")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 20) { 
                    	 zlfh=con;
                     double zl=0.0;
                     if(zlfh!=null&&zlfh!=" "&&zlfh!=""){
                    	 zl=Double.parseDouble(zlfh);
                     }
                    	 if("zdsx02".equals(zdsx)){
                    	    if("fwlx01".equals(yflx)){
                 			if(zl>=0&&zl<=10){
                 				xlx="jzlx01";
                 			}else if(10<zl&&zl<=20){
                 				xlx="jzlx02";
                 			}else if(20<zl&&zl<=30){
                 				xlx="jzlx03";
                 			}else if(30<zl&&zl<=40){
                 				xlx="jzlx04";
                 			}else if(40<zl&&zl<=50){
                 				xlx="jzlx05";
                 			}else if(50<zl&&zl<=60){
                 				xlx="jzlx06";
                 			}else if(60<zl&&zl<=70){
                 				xlx="jzlx07";
                 			}else if(70<zl&&zl<=80){
                 				xlx="jzlx08";
                 			}else if(80<zl&&zl<=90){
                 				xlx="jzlx09";
                 			}else if(90<zl&&zl<=100){
                 				xlx="jzlx10";
                 			}else if(zl>100){
                 				xlx="jzlx11";
                 			}else{
                 				xlx="0";
                 			}
                 		}else if("fwlx02".equals(yflx)){
                 					
                 		    if(zl>=0&&zl<=10){
                 		    	xlx="jzlx12";
                 			}else if(10<zl&&zl<=20){
                 				xlx="jzlx13";
                 			}else if(20<zl&&zl<=30){
                 				xlx="jzlx14";
                 			}else if(30<zl&&zl<=40){
                 				xlx="jzlx15";
                 			}else if(40<zl&&zl<=50){
                 				xlx="jzlx16";
                 			}else if(50<zl&&zl<=60){
                 				xlx="jzlx17";
                 			}else if(60<zl&&zl<=70){
                 				xlx="jzlx18";
                 			}else if(70<zl&&zl<=80){
                 				xlx="jzlx19";
                 			}else if(80<zl&&zl<=90){
                 				xlx="jzlx20";
                 			}else if(90<zl&&zl<=100){
                 				xlx="jzlx21";
                 			}else if(zl>100){
                 				xlx="jzlx22";
                 			}else{
                 				xlx="0";
                 			}
                 		    }
                    	 }else if("zdsx05".equals(zdsx)){ 
                    		 if("fwlx01".equals(yflx)){
                 				if(zl>=0&&zl<=20){
                 					 	jrwtype="jrwlx01";
                 			        }else if(20<zl&&zl<=40){
                 			        	jrwtype="jrwlx02";
                 			        }else if(40<zl&&zl<=60){
                 			        	jrwtype="jrwlx03";
                 					}else if(60<zl&&zl<=80){
                 						jrwtype="jrwlx04";
                 					}else if(80<zl&&zl<=100){
                 						jrwtype="jrwlx05";
                 					}else if(zl>100){
                 						jrwtype="jrwlx06";
                 					}else{
                 						jrwtype="0";
                 					}
                 		     }else if("fwlx02".equals(yflx)){
                 				if(zl>=0&&zl<=20){
                 			        	jrwtype="jrwlx07";
                 			        }else if(20<zl&&zl<=40){
                 			        	jrwtype="jrwlx08";
                 			        }else if(40<zl&&zl<=60){
                 			        	jrwtype="jrwlx09";
                 					}else if(60<zl&&zl<=80){
                 						jrwtype="jrwlx10";
                 					}else if(80<zl&&zl<=100){
                 						jrwtype="jrwlx11";
                 					}else if(zl>100){
                 						jrwtype="jrwlx12";
                 					}else{
                 						jrwtype="0";
                 					}
                 			 }
                    	 }else if(("zdsx02".equals(zdsx)||"zdsx05".equals(zdsx)) && "fwlx03".equals(yflx)){
                 				xlx="0";
                 				jrwtype="0";
                 		 }
                    	 
                    	 //System.out.println("zl:"+zl);
                    	 //System.out.println("zdsx:"+zdsx);
                    	 //System.out.println("yflx:"+yflx);
                    	 
                    	 sql.append(",'" + zlfh + "','"+xlx+"','"+jrwtype+"'");	
                    	 
                    	 
                     } if (i == 21) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 22) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 23) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 24) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 25) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 26) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 27) {
                    	 if (con.trim().equals("是")) {
                             con = "1";
                    	 } else if (con.trim().equals("否")) {
                             con = "0";
                         }
                    	 sql.append(",'" + con + "'");	
                     } if (i == 28) { sql.append(",'" + this.getPropertyCode(db, con, "GDFS") + "'");	
                     } if (i == 29) { sql.append(",'" + con.trim() + "'");	
                     } if (i == 30) {
                         sql2="INSERT INTO ZDDFINFO(ZDID,FKZQ) values ((select id from zhandian where zdcode='"+zdcode+"'),'"+this.getPropertyCode(db, con, "FKZQ")+"')";
                     } if (i == 31) {
                        // con = this.getZdid(db, zdcode);
                         //System.out.println("4"+con);
                         sql3.append("'" + zdcode + "'");
                     
                       
                       
                       Random rnd = new Random();
                       dbid = (String) (rnd.nextInt(999999999)+"");
                      
               		 sql3.append(",'" + dbid +"','结算电表','"+ this.getPropertyCode(db, con, "dfzflx")+ "'"); 
                     }if(i==32){
                         sql3.append(",'" + con.trim() + "'");
                     }if(i==33){
                         sql3.append(",'" + this.getPropertyCode(db, con, "xslx") + "'");
                     }if(i==34){
                         sql3.append(",'" + con + "','dbyt01'");
                     }if(i==35){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		sc=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+1+"','100','"+dbid+1+"','"+dbid+1+"','zylx01','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    		 System.out.println(sql+"sql");
                    	 }
                     }if(i==36){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 xxh=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+4+"','100','"+dbid+4+"','"+dbid+4+"','zylx04','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }
                     }if(i==37){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 bg=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+3+"','100','"+dbid+3+"','"+dbid+3+"','zylx03','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }
                     }if(i==38){
                    	 if(!"0".equals(con)&&!"0.0".equals(con)&& con!=null && con.length() != 0 && !"".equals(con) && !" ".equals(con)){
                    		 yy=Double.parseDouble(con);
                    		 String sql="INSERT INTO SBGL(XJID,XJBILI,DIANBIAOID,SHEIEBANID,SHUOSHUZHUANYE,DBILI,ENTRYPENSONNEL,ENTRYTIME) VALUES('"+dbid+2+"','100','"+dbid+2+"','"+dbid+2+"','zylx02','"+con+"','"+accountname+"','"+mat.format(new Date())+"')";
                    		 db.update(sql);	
                    	 }
                     }if(i==39){
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
                     }if(i==40){
                    	 lastdegree=con;
                    	sql11.append("'"+dbid+"','"+lastdegree+"'"); 
                     }if(i==41){
                    	 thisdegree=con;
                     	sql11.append(",'"+thisdegree+"'"); 
                     }if(i==42){
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
                		sql11.append(",'"+con+"'"); 
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
                     	sql11.append(",'"+con+"'"); 
                     }if(i==44){
                    	 if(con==null||con==""||con==" "){
                    		 con="0";
                    	 }
                     	sql11.append(",'"+con+"'"); 
                     }if(i==45){
                    	 String amuuid = UUID.randomUUID().toString().trim().replace("-",""); 
                         long uuid=System.currentTimeMillis();
                         double cons=0.0;
                         if(con!=null&&con!=""&&con!=" "){
                        	 cons=Double.parseDouble(con);
	                         scdl=(cons*sc)/100.0;
	                         xxhdl=(cons*xxh)/100.0;
	                         bgdl=(cons*bg)/100.0;
	                         yydl=(cons*yy)/100.0;
	                         jstzdl=(cons*jstz)/100.0;
	                         
                         }
                         //System.out.println(sc+"| "+xxh+"| "+bg+"| "+yy+"| "+jstz);
                        // System.out.println(scdl+"| "+xxhdl+"| "+bgdl+"| "+yydl+"| "+jstzdl);
                     	sql11.append(",'"+con+"','"+scdl+"','"+xxhdl+"','"+bgdl+"','"+yydl+"','"+jstzdl+"','"+amuuid+"','"+uuid+"'"); 
                     }if(i==46){
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
                     	sql11.append(",'"+con+"'"); 
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
                     	sql11.append(",'"+con+"'"); 
                     }
                     
                     if(i==48){
                     	sql13.append("'"+dbid+"','"+con+"'"); 
                     }if(i==49){
                    	 if(con==null||con==""||con==" "){
                    		 con="0";
                    	 }
                     	sql13.append(",'"+con+"'"); 
                     }if(i==50){
                    	 double cons=0.0;
                         if(con!=null&&con!=""&&con!=" "){
                    		 cons=Double.parseDouble(con);
	                         scdf=(cons*sc)/100.0;
	                         xxhdf=(cons*xxh)/100.0;
	                         bgdf=(cons*bg)/100.0;
	                         yydf=(cons*yy)/100.0;
	                         jstzdf=(cons*jstz)/100.0;
                         }
                         //System.out.println(sc+"| "+xxh+"| "+bg+"| "+yy+"| "+jstz);
                        // System.out.println(scdf+"| "+xxhdf+"| "+bgdf+"| "+yydf+"| "+jstzdf);
                     	sql13.append(",'"+con+"','"+scdf+"','"+xxhdf+"','"+bgdf+"','"+yydf+"','"+jstzdf+"'"); 
                     }if(i==51){
                    	 long uuid = System.currentTimeMillis();
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
                     	sql13.append(",'"+uuid+"','"+con+"'"); 
                     }
                    i++;
                }
               
                sql.append(",'"+accountname+"','"+mat.format(new Date())+"','1','1','0')");
                sql3.append(",'"+accountname+"','"+mat.format(new Date())+"','1','0')");
                sql11.append(",'"+accountname+"','"+mat.format(new Date())+"','1','0')");
                sql13.append(",'"+accountname+"','"+mat.format(new Date())+"','1','1','0')");
                int tt = -1;
                if (!bin) {
                    System.out.println(sql.toString());  
                    tt = db.update(sql.toString());	
                  
                   String sql4="update zhandian set zdcode=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from zhandian where zdcode='"+zdcode+"')";
                  
                   System.out.println(sql2.toString()); 
                   db.update(sql2);	
                   if(lastdegree!=null&&lastdegree!=" "&&lastdegree!=""){
	                   System.out.println("sql11:"+sql11);
	                   tt =db.update(sql11.toString());	
                   }
                  
                   System.out.println(sql3.toString()); 
                   tt=db.update(sql3.toString());
                   
                   if(thisdegree!=null&&thisdegree!=" "&&thisdegree!=""){
	                   System.out.println(sql13.toString()); 
	                   tt=db.update(sql13.toString());
                   }
                   
                   String sql5="update DIANBIAO set dbid=(select id from DIANBIAO where dbid='"+dbid+"'),zdid=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from dianbiao where dbid='"+dbid+"')";
                   
                  
                   String sql6="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0101',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '01' where id=(select id from sbgl where DIANBIAOID='"+dbid+1+"')";
                   String sql7="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0202',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '02' where id=(select id from sbgl where DIANBIAOID='"+dbid+2+"')";
                   String sql8="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0303',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '03' where id=(select id from sbgl where DIANBIAOID='"+dbid+3+"')";
                   String sql9="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0404',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '04' where id=(select id from sbgl where DIANBIAOID='"+dbid+4+"')";
                   String sql10="update sbgl set XJID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '0505',DIANBIAOID=(select id from dianbiao where dbid='"+dbid+"'), SHEIEBANID=to_char((select id from dianbiao where dbid = '"+dbid+"')) || '05' where id=(select id from sbgl where DIANBIAOID='"+dbid+5+"')";

                 //  System.out.println(sql6.toString());
                   
                   String sql12="update ammeterdegrees set AMMETERID_FK=(select id from dianbiao where dbid='"+dbid+"') where AMMETERDEGREEID=(select AMMETERDEGREEID from ammeterdegrees where AMMETERID_FK='"+dbid+"')";
                  

                   String sql14="update electricfees set AMMETERDEGREEID_FK=(select AMMETERDEGREEID from ammeterdegrees where AMMETERID_FK='"+dbid+"') where ELECTRICFEE_ID=(select ELECTRICFEE_ID from electricfees where AMMETERDEGREEID_FK='"+dbid+"')";
                 
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
	                	rs = db.queryAll(sql14);	
	                	//System.out.println(sql14.toString()); 
	                	rs = db.queryAll(sql12);
	                	//System.out.println(sql12.toString()); 
                	  
                	}
             		rs = db.queryAll(sql5);	
             		//System.out.println(sql5.toString()); 
             		rs = db.queryAll(sql4);
             		//System.out.println("sql4:"+sql4);
             		
             			
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

    private ResultSet rs = null;
    private String getAreaCode(DataBase db, String name) throws Exception,
            SQLException {
        String code = "";
       // System.out.println("name"+name.trim());
        rs = db.queryAll("select agcode from d_area_grade where agname like '%" +
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
		ResultSet rs = null;
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
        ResultSet rs = null;
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


}
