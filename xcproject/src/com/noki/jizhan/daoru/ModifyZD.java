package com.noki.jizhan.daoru;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import com.noki.database.DataBase;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.noki.database.DbException;
import com.noki.jizhan.JiZhanBean;

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
public class ModifyZD {
    public ModifyZD() {
        try {
            db = new DataBase();
            db.connectDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    StringBuffer sql = new StringBuffer();
    StringBuffer sql3 = new StringBuffer();
    StringBuffer sql5 = new StringBuffer();
   
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
        System.out.println("content==" + content);
        for (Iterator it = content.iterator(); it.hasNext(); ) {
            zg++;
            m++;
            i = 0;
            String sheng="",shi="",xian="",xiaoqu="",zdcode="",property="",jztype="",stationtype="",gxxx="",zdcq="", 
            yflx="",gsf="",dytype="",dbid="",gdfs="",fkzq="",dfzflx="",dbyt="",fkfs="",linelosstype="",id="",sc="",
            yy="",bg="",xxh="",jstz="",dbzbdyhh="",sydate="",cssytime="",xlx="",jrwtype="",dddf="";

            Vector row = (Vector) it.next();
            Iterator iter = row.iterator();
            Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*"); 
            
   		     //Pattern pattern2 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");

            String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        	Pattern pattern2 = Pattern.compile(datePattern2); 
            sql.setLength(0);
            sql3.setLength(0);
            //update 表名 set 列名=新值 where(过滤条件) 条件(关系条件,子查询条件....,连接条件...);
            sql.append("UPDATE ZHANDIAN SET ");
            sql3.append("UPDATE DIANBIAO SET ");
            String con = "";
            String tempstr = "";
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
                            	sheng = this.getAreaCode(db, con);
                            	//System.out.println(con+tempstr+"省1");
                            	if(sheng==""){
                            		bin=true;
                            		 row.add("导入失败！省名称不存在！");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	 break;
                            case 1:
                            	shi = this.getAreaCode(db, con);
                            	
                            	if(shi==""){
                            		bin=true;
                            		 row.add("导入失败！市名称不存在！");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	  break;
                            case 2:
                            	xian = this.getExactAreaCode(db, con,fagName);
                            	//System.out.println(con+tempstr+"省3");
                            	if(xian==""){
                            		bin=true;
                           		    row.add("导入失败！对应市下的区县名称不存在！");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                            case 3:
                            	xiaoqu = this.getExactAreaCode(db, con,fagName);
                            	//System.out.println(con+tempstr+"省4");
                            	if(xiaoqu==""){
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
                           // sql.append("sheng='" + sheng + "'");
                            break;
                        case 1:
                         //   sql.append("sheng='" + sheng + "',shi='" + shi + "'");
                            break;
                        case 2:
                          //  sql.append("sheng='" + sheng + "',shi='" + shi + "',xian='"+xian+"'");
                            break;
                        case 3:
                            sql.append("sheng='" + sheng + "',shi='" + shi + "',xian='"+xian+"',xiaoqu='"+xiaoqu+"'");
                            break;
                        }
                    }
                    if (i == 4) {
                    		sql.append(",jzname='" + con + "'");	
                    }
                    if (i == 5) {
                    
                    	if(con.contains(".")){
                    	  int zdid=con.indexOf(".");
                    	  zdcode=con.substring(0,zdid);
                    	}else{
                    		zdcode=con;
                    	}
                    	id=getZdCode(db,zdcode);
                    	String dbidd=this.getDBID(zdcode);
                    	if(dbidd.equals("0")){
                    		 bin = true;
                    		 row.add("导入失败！没有该站点！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                            
                    	}
                    	//System.out.println(zdcode);
                    }
                    if (i == 6) {
                    	sql.append(",yid='" + con + "'");	
                    }if (i == 7) {
                    	sql.append(",bieming='" + con + "'");	
                    }if (i == 8) {
                    	 if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！占地面积必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }else{
                        	sql.append(",area='" + con + "'");	
                        }
                    }if (i == 9) {
               		    sql.append(",address='" + con + "'");	
                    }if (i == 10) {
                    	if(!"".equals(con)){
                    	 property = this.getPropertyCode(db, con, "ZDSX");
                         if (property.equals("0")) {
                             bin = true;
                             row.add("导入失败！站点属性不存在！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                         }
                    	}
               		    sql.append(",property='" + property + "'");	
                    }
                    if (i == 11) {
                      if(!"".equals(con)){	
                   	  jztype = this.getPropertyCode(db, con, "ZDLX");
                        if (jztype.equals("0")) {
                            bin = true;
                            row.add("导入失败！集团报表类型不存在！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                        }
                      }
              		    sql.append(",jztype='" + jztype + "'");	
                   }
                    if (i == 12) {
                    	if(!"".equals(con)){
                    	  stationtype = this.getPropertyCode(db, con, "stationtype");
                            if (stationtype.equals("0")) {
                                bin = true;
                                row.add("导入失败！站点类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                    	}
                            sql.append(",stationtype='" +stationtype+ "'");
                    }
                    if (i == 13) {
                    	if(!"".equals(con)){
                            gxxx = this.getPropertyCode(db, con, "gxxx");
                            if (gxxx.equals("0")) {
                                bin = true;
                                row.add("导入失败！共享信息类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                    	}
                            sql.append(",gxxx='" +gxxx +"'");
                    } 
                    if (i == 14) {
                      if(!"".equals(con)){
                        zdcq = this.getPropertyCode(db, con, "ZDCQ");
                        if (zdcq.equals("0")) {
                            bin = true;
                            row.add("导入失败！站点产权不存在！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                        }
                      }
                        sql.append(",zdcq='" +zdcq +"'");
                    } 
                     
                    if (i == 15) {
                    	if(!"".equals(con)){
                            yflx = this.getPropertyCode(db, con, "FWLX");
                            if (yflx.equals("0")) {
                                bin = true;
                                row.add("导入失败！房屋类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                    	}
                            sql.append(",yflx='" +yflx+"'");
                    } 
                   if (i == 16) {
                	  if(!"".equals(con)){
                	   gsf = this.getPropertyCode(db, con, "gsf");
                       if (gsf.equals("0")) {
                           bin = true;
                           row.add("导入失败！归属方不存在！");
                           wrongContent.add(row);
                           bcg++;
                           break;
                       }
                	  }
                        sql.append(",gsf='" + gsf + "'");
                    }
                    if (i == 17) {
                        sql.append(",fzr='" + con + "'");
                    }
                    if (i == 18) {
                    	//投入使用时间
                    	System.out.println("concon"+con);
                    	System.out.println("pattern2.matcher(con).matches()"+pattern2.matcher(con).matches());
                        if(pattern2.matcher(con).matches()==true||con.equals("")||con.equals(" ")){
	                    	if(con.trim().contains("/")){
		              	          try {
		              	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		              	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		              	        	Date date =sdf.parse(con);
		              	        	sydate=sdf1.format(date);
		              	        	
		              			} catch (ParseException e) {  
		              				// TODO Auto-generated catch block
		              				e.printStackTrace();
		              			}
	              			  }else{
	              				  sydate=con;
	              			  }
                        }else{
                        	 bin = true;
                        	 row.add("导入失败！投入时间格式不正确！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }
                    	System.out.println("sydatesydate"+sydate);
                        sql.append(",sydate='" + sydate + "'");
                    } 
                    if (i == 19) {
	                     if(!"".equals(con)){
	                        if (con.equals("是")) {
	                            sql.append(",qyzt='1'");
	                        } else {
	                            sql.append(",qyzt='0'");
	                        }
	                     }
                    } 
                    if (i == 20) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true; 
                        	 row.add("导入失败！楼宇交换机个数必须为数字！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                       }else{
                      	  sql.append(",lyjhjgs='" + con + "'");
                       }
                    }
                    if (i == 21) {
                      if(!"".equals(con)){
                    	 dytype = this.getPropertyCode(db, con, "dytype");
                         if (dytype.equals("0")) {
                             bin = true;
                             row.add("导入失败！地域属性不存在！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                          }
                        }
                          sql.append(",dytype='" + dytype + "'");
                    } if (i == 22) {
                    	if(!"".equals(con)){
	                        if (con.equals("有")) {
	                            sql.append(",g2='1'");
	                        } else {
	                            sql.append(",g2='0'");
	                        }
                    	}
                    } 
                    if (i == 23) {
                      if(!"".equals(con)){
                        if (con.equals("有")) {
                            sql.append(",g3='1'");
                        } else {
                            sql.append(",g3='0'");
                        }
                      }
                    } 
                    if (i == 24) {
                      if(!"".equals(con)){
                    	if (con.equals("有")) {
                            sql.append(",kdsb='1'");
                        } else {
                            sql.append(",kdsb='0'");
                        }
                      }
                    }
                    if (i == 25) {
                      if(!"".equals(con)){
                    	if (con.equals("有")) {
                            sql.append(",yysb='1'");
                        } else {
                            sql.append(",yysb='0'");
                        }
                      }
                   }
                    if (i == 26) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！载频数量(个)必须为数字！");
                               wrongContent.add(row);
                               bcg++;
                               break;
                          }else{
                            	 sql.append(",zpsl='" + con + "'");
                          }
                   }
                    if (i == 27) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                         	 row.add("导入失败！载扇数量(个)必须为数字！");
                              wrongContent.add(row);
                              bcg++;
                              break;
                         }else{
                           	 sql.append(",zssl='" + con + "'");
                         }
                   }
                    if (i == 28) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！宽带设备数量（端口）必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }else{
                         	 sql.append(",kdsbsl='" + con + "'");
                        }
                    }
                    if (i == 29) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！语音设备数量（线）必须为数字！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                       }else{
                        	 sql.append(",yysbsl='" + con + "'");
                       }
                    }
                    if (i == 30) {
                      if(!"".equals(con)){
                        if (con.equals("有")) {
                            sql.append(",kt1='1'");
                        } else {
                            sql.append(",kt1='0'");
                        }
                      }
                    } 
                    if (i == 31) {
                      if(!"".equals(con)){
                        if (con.equals("有")) {
                            sql.append(",kt2='1'");
                        } else {
                            sql.append(",kt2='0'");
                        }
                      }
                    } 
                    if (i == 32) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！直流设备负荷（A）必须为数字！");
                               wrongContent.add(row);
                               bcg++;
                               break;
                          }
                    	double zl=0.0;
                    	if(con!=null&&con!=" "&&con!=""){
                       	 zl=Double.parseDouble(con);
                        }
                    	 if(property.equals("zdsx02")){
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
                  				xlx="";
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
                     	 }else if("zdsx05".equals(property)){ 
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
                  						jrwtype="";
                  					}
                  			 }
                     	 }else if(("zdsx02".equals(property)||"zdsx05".equals(property)) && "fwlx03".equals(yflx)){
                  				xlx="";
                  				jrwtype="";
                  		 }
                     	 sql.append(",zlfh='" + con + "',xlx='"+xlx+"',jrwtype='"+jrwtype+"'");
                    }
                    if (i == 33) {
                    	if (pattern.matcher(con).matches()==false) {
                    		bin = true;
                       	    row.add("导入失败！交流设备负荷（非空调及照明等）（A）必须为数字！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                       }else{
                       	 sql.append(",jlfh='" + con + "'");
                       }
                    }
                    if (i == 34) {
                     if(!"".equals(con)){
                    	if (con.equals("是")) {
                            sql.append(",zgd='1'");
                        } else {
                            sql.append(",zgd='0'");
                        }
                     }
                    }
                    if (i == 35) {
                    	 sql.append(",memo='" + con + "'");
                    }
                    if (i == 36) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！单价必须为数字！");
                               wrongContent.add(row);
                               bcg++;
                               break;
                          }else{
                        	     sql.append(",dianfei='" + con + "'");
                          }
                    }
                    if (i == 37) {
                     if(!"".equals(con)){
                        if (con.equals("是")) {
                            sql.append(",bgsign='1'");
                        } else {
                            sql.append(",bgsign='0'");
                        }
                     }
                    } 
                    if (i == 38) {
                     if(!"".equals(con)){
                        if (con.equals("是")) {
                            sql.append(",jnglmk='1'");
                        } else {
                            sql.append(",jnglmk='0'");
                        }
                     }
                    } 
                    if (i == 39) {
                     if(!"".equals(con)){
                        gdfs = this.getPropertyCode(db, con, "GDFS");
                        if (gdfs.equals("0")) {
                            bin = true;
                            row.add("导入失败！供电方式不存在！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                            }
                         }
                            sql.append(",gdfs='" +gdfs+"'");
                    } 
                    if (i == 40) {
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                         	 row.add("导入失败！额定耗电量必须为数字！");
                              wrongContent.add(row);
                              bcg++;
                              break;
                         }else{
                             sql.append(",edhdl='" + con + "'");
                         }
                    }
                    if (i == 41) {
                    	 String bei="";
	                    	if(con.contains(".")){
	                    		int s=con.indexOf(".");
	                    		bei=con.substring(0,s);
	                    	}else{	
	                    		bei=con;
	                    	}
	                    if(!"".equals(con)){
                    	 fkzq = this.getPropertyCode(db, bei, "FKZQ");
                            if (fkzq.equals("0")) {
                                bin = true;
                                row.add("导入失败！付款周期类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
	                    }
                    }  
                     if (i == 42) { 
                    	 if(con.contains(".")){
                    		int dbi=con.indexOf(".");
                    		dbid=con.substring(0,dbi);
                    	 }else{
                    		dbid=con;
                    	 }
                    } 
                     if (i == 43) {
                      if(!"".equals(con)){
                    	 dfzflx = this.getPropertyCode(db, con, "dfzflx");
                    	 System.out.println("dfzflx"+dfzflx);
                         if (dfzflx.equals("0")) {
                             bin = true;
                             row.add("导入失败！电费支付类型不存在！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                         }
                        }
                         sql3.append("dfzflx='" +dfzflx+ "',");
                        }
                        if (i == 44) {
                        	if (pattern.matcher(con).matches()==false) {
                        		 bin = true;
                            	 row.add("导入失败！倍率必须为数字！");
                                 wrongContent.add(row);
                                 bcg++;
                                 break;
                            }else{
                                sql3.append("beilv='" + con + "',");
                            }
                       } 
                        if (i == 45) {
                        if(!"".equals(con)){
                        	dbyt = this.getPropertyCode(db, con, "DBYT");
                            if (dbyt.equals("0")) {
                                bin = true;
                                row.add("导入失败！电表用途类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                        }
                            sql3.append("dbyt='" +dbyt+ "',");
                    } 
                        if (i == 46) {
                          if(!"".equals(con)){
                        	fkfs = this.getPropertyCode(db, con, "FKFS");
                            if (fkfs.equals("0")) {
                                bin = true;
                                row.add("导入失败！付款方式类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                          }
                    } 
                        if (i == 47) {
                        	dbzbdyhh=con;
                        	 sql3.append("dbzbdyhh='" +dbzbdyhh+ "',");
                    } 
                        //是否套表计费
                        if (i == 48) {
                          if(!"".equals(con)){
                        	if (con.equals("是")) {
                               con="2";
                            } else {
                               con="1";
                            }
                          }
                            String sql1="";
                        	String sql="select info.zdid from zddfinfo info where info.zdid='"+id+"'";
                        	boolean flag=false;
                        	 try {
                        	  		rs = db.queryAll(sql);
                        	  	      if (rs.next()) {
                        	  	    	  flag=true;
                        	  	      }
                        	  	      
                        	        } catch (Exception e) {
                        	        	e.printStackTrace();
                        	        }finally{
                        	        	if(rs != null){
                        	        		rs.close();
                        	        	}
                        	        	closeDb();
                        	        }
                    	        if(con!=null&&!con.equals("")&&!con.equals(" ")){
		                        	if(flag==true){
		                        		sql1="update zddfinfo l set fkzq='"+fkzq+"',fkfs='"+fkfs+"',watchcost='"+con+"' where l.zdid='"+id+"' ";
		                        	}else{
		                        		sql1="insert into zddfinfo values('1','','"+fkzq+"','','"+fkfs+"','','','','','','','"+id+"','','','','','','','','"+con+"')";
		                        	}
		                        	db.update(sql1);
		                        	closeDb();
		                        	System.out.println("zddfinfo"+sql1);
                    	        }
                    } 
                        if (i == 49) {
                          if(!"".equals(con)){
                        	linelosstype = this.getPropertyCode(db, con, "xslx");
                            if (linelosstype.equals("0")) {
                                bin = true;
                                row.add("导入失败！线损类型不存在！");
                                wrongContent.add(row);
                                bcg++;
                                break;
                            }
                          }
                            sql3.append("linelosstype='" +linelosstype+ "',");
                    } 
                     if (i == 50) {
                    	 if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！线损值必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }else{
                            sql3.append("linelossvalue='" + con + "',");
                        }
                    } 
                    if (i == 51) {
                    	 if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！初始读数必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }else{
                            sql3.append("csds='" + con + "',");
                        }
                    } 
                    if (i == 52) {
                    	cssytime=con;
                        if(pattern2.matcher(cssytime).matches()==true||con.equals("")||con.equals(" ")){
	                    	if(cssytime.trim().contains("/")){
		              	          try {
		              	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		              	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		              	        	Date date =sdf.parse(cssytime);
		              	        	cssytime=sdf1.format(date);
		              	        	
		              			} catch (ParseException e) {  
		              				// TODO Auto-generated catch block
		              				e.printStackTrace();
		              			}
	            			  }else{
	            				  cssytime=con;
	            			  }
	                    	sql3.append("cssytime='" + cssytime + "',");
                        }else{
                        	 bin = true;
                        	 row.add("导入失败！初始使用时间格式不正确！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }
                    } 
                    if (i == 53) {//网络运营线 （生产）zylx01
                    	 if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！网络运营线必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }
                    	if(con.contains(".")){
                    		int s=con.indexOf(".");
                    		sc=con.substring(0,s);
                    	}else{	
                    		sc=con;
                    	}
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx01'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	  	      
                      	  		
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
                	        if(sc!=null&&!sc.equals("")&&!sc.equals(" ")&&!sc.equals("0")&&!sc.equals("0.0")){
		                    	if(flag==true){
		                    		sql1="update sbgl l set dbili='"+sc+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx01' and l.dianbiaoid='"+dbid+"'";
		                    	}else{
		                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"01','','','zylx01','','','','','','','','','','"+sc+"','','1','"+dbid+"0101','100','"+accountname+"','"+mat.format(new Date())+"')";
		                    	}
		                    	
                	        }else if(sc.equals("0.0")||sc.equals("0")){
                	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx01'";
                	        }
                	        db.update(sql1);
	                    	closeDb();
                    	System.out.println("sc"+sql1);
                    } 
                    if (i == 54) {//信息化支撑线  04
                    	 if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                        	 row.add("导入失败！信息化支撑线必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                        }else{
                    	xxh=con;
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx04'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
	            	        if(xxh!=null&&!xxh.equals("")&&!xxh.equals(" ")&&!xxh.equals("0")&&!xxh.equals("0.0")){
		                    	if(flag==true){
		                    		sql1="update sbgl l set dbili='"+xxh+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx04' and l.dianbiaoid='"+dbid+"'";
		                    	}else{
		                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"04','','','zylx04','','','','','','','','','','"+xxh+"','','1','"+dbid+"0404','100','"+accountname+"','"+mat.format(new Date())+"')";
		                    	}
		                    	
	            	        }else if(xxh.equals("0")||xxh.equals("0.0")){
                	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx04'";
                	        }
	                    	System.out.println("xxh"+sql1);
                	        db.update(sql1);
	                    	closeDb();
                        }
                    } 
                    if (i == 55) {//行政综合线 （办公）	03
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                       	     row.add("导入失败！行政综合线 必须为数字！");
                            wrongContent.add(row);
                            bcg++;
                            break;
                       }else{
                    	   System.out.println("bg:"+con);
                    	bg=con;
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx03'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
                	        if(bg!=null&&!bg.equals("")&&!bg.equals(" ")&&!bg.equals("0")&&!bg.equals("0.0")){
                	        	//System.out.println("崖南机房（1005057285000）"+flag);
		                    	if(flag==true){
		                    		sql1="update sbgl l set dbili='"+bg+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx03' and l.dianbiaoid='"+dbid+"'";
		                    	}else{
		                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"03','','','zylx03','','','','','','','','','','"+bg+"','','1','"+dbid+"0303','100','"+accountname+"','"+mat.format(new Date())+"')";
		                    	}
                	        }else if(bg.equals("0")||bg.equals("0.0")){
                	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx03'";
                	        }
	                    	System.out.println("bg"+sql1);
                	        db.update(sql1);
	                    	closeDb();
                       }
                    } 
                    if (i == 56) {//市场营销线 （营业）02
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！市场营销线  必须为数字！");
                               wrongContent.add(row);
                               bcg++;
                               break;
                          }else{
                    	yy=con;
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx02'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
                    	if(yy!=null&&!yy.equals("")&&!yy.equals(" ")&&!yy.equals("0")&&!yy.equals("0.0")){
	                    	if(flag==true){
	                    		sql1="update sbgl l set dbili='"+yy+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx02' and l.dianbiaoid='"+dbid+"'";
	                    	}else{
	                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"02','','','zylx02','','','','','','','','','','"+yy+"','','1','"+dbid+"0202','100','"+accountname+"','"+mat.format(new Date())+"')";
	                    	}
                    	}else if(yy.equals("0")||yy.equals("0.0")){
            	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx02'";
            	        }
                    	System.out.println("yy"+sql1);
            	        db.update(sql1);
                    	closeDb();
                      }
                    
                    } 
                    if (i == 57) {//建设投资线	05
                    	System.out.println("con:"+con);
                       if("100".equals(con)||"100.0".equals(con)||"0".equals(con)||"0.0".equals(con)){
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！建设投资线 必须为数字！");
                             wrongContent.add(row);
                             bcg++;
                             break;
                          }else{
                    	jstz=con;
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx05'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
                    	if(jstz!=null&&!jstz.equals("")&&!jstz.equals(" ")&&!jstz.equals("0.0")&&!jstz.equals("0")){
	                    	if(flag==true){
	                    		sql1="update sbgl l set dbili='"+jstz+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx05' and l.dianbiaoid='"+dbid+"'";
	                    	}else{
	                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"05','','','zylx05','','','','','','','','','','"+jstz+"','','1','"+dbid+"0505','100','"+accountname+"','"+mat.format(new Date())+"')";
	                    	}
                    	}else if(jstz.equals("0.0")||jstz.equals("0")){
            	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx05'";
            	        }
                    	System.out.println("jstz"+sql1);
            	        db.update(sql1);
                    	closeDb();
                       }
                    }else{
                  	  bin = true;
                    	 row.add("导入失败！建设投资的比例只能是100或者0！");
                         wrongContent.add(row);
                         bcg++;
                         break; 
                   }
                  }
          
                    if (i == 58) {//代垫电费
                    	if (pattern.matcher(con).matches()==false) {
                    		 bin = true;
                          	 row.add("导入失败！市场营销线  必须为数字！");
                               wrongContent.add(row);
                               bcg++;
                               break;
                          }else{
                    	dddf=con;
                    	String sql1="";
                    	String sql="select s.sheiebanid from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx06'";
                    	boolean flag=false;
                    	 try {
                    	  		rs = db.queryAll(sql);
                    	  	      if (rs.next()) {
                    	  	    	  flag=true;
                    	  	      }
                    	        } catch (Exception e) {
                    	        	e.printStackTrace();
                    	        }finally{
                    	        	if(rs != null){
                    	        		rs.close();
                    	        	}
                    	        	closeDb();
                    	        }
                    	if(dddf!=null&&!dddf.equals("")&&!dddf.equals(" ")&&!dddf.equals("0")&&!dddf.equals("0.0")){
	                    	if(flag==true){
	                    		sql1="update sbgl l set dbili='"+dddf+"',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where  l.shuoshuzhuanye='zylx06' and l.dianbiaoid='"+dbid+"'";
	                    	}else{
	                    		sql1="insert into sbgl values('"+dbid+"','"+dbid+"06','','','zylx06','','','','','','','','','','"+dddf+"','','1','"+dbid+"0606','100','"+accountname+"','"+mat.format(new Date())+"')";
	                    	}
                    	}else if(dddf.equals("0")||dddf.equals("0.0")){
            	        	sql1="delete from sbgl s where s.dianbiaoid='"+dbid+"' and s.shuoshuzhuanye='zylx06'";
            	        }
                    	System.out.println("dddf"+sql1);
            	        db.update(sql1);
                    	closeDb();
                      }
                    
                    } 
                    i++;
                }
                sql.append(",ZDBZW='0',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where zdcode='"+zdcode+"'");
                sql3.append("BZW='0',entrypensonnel='"+accountname+"',entrytime='"+mat.format(new Date())+"' where dbid='"+dbid+"'");
                int tt = -1;
                if (!bin) {
                	  System.out.println("sql1:"+sql);
                	  System.out.println("sql3:"+sql3);
                    tt = db.update(sql.toString());	            	
                    tt = db.update(sql3.toString());
                    closeDb();
                    cg++;
                }
               // System.out.println("tt:"+tt+"bin:"+bin);
                if (tt != 1 && !bin) {
                    bcg++;
                    row.add("修改失败！");
                    wrongContent.add(row);
                }

            } catch (Exception e) {
                e.printStackTrace();
                row.add(e.toString());
                wrongContent.add(row);
            }
        }
        System.out.println(wrongContent);
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
        if(rs != null){
        	rs.close();
        }
        if(db != null){
        	db.close();
        }
		 closeDb();
        return code;
    }
    //当获得的区域Code为县或乡镇时    采用精准的判断方法（同时判断名称和父级区域名称对应的编码）王代军
    private String getExactAreaCode(DataBase db, String name,String fagName) throws Exception,
    SQLException {
		String code = "";
		
		rs = db.queryAll("select agcode from d_area_grade where agname = '" +
		 name.trim() + "' and fagcode in (select agcode from d_area_grade where agname = '" +fagName.trim() + "' )");
		if (rs.next()) {
		    return rs.getString(1);
		}
		if(rs != null){
        	rs.close();
        }
        if(db != null){
        	db.close();
        }
		 closeDb();
		return code;
		}
    private String getPropertyCode(DataBase db, String name, String typename) throws
            Exception,
            SQLException {
        String code = "";
       
        System.out.println("select code from indexs where name = '" +
                           name.trim() + "' and type='" + typename + "'");
        rs = db.queryAll("select code from indexs where name ='" +
                         name.trim() + "' and type='" + typename + "'");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            code = "0";
        }
        if(rs != null){
        	rs.close();
        }
        if(db != null){
        	db.close();
        }
        closeDb();
        return code;
    }

    private String getZdCode(DataBase db, String name) throws
            Exception,
            SQLException {
        String code = "";
        rs = db.queryAll("select id from zhandian where zdcode='" + name + "'");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            code = "0";
        }
        if(rs != null){
        	rs.close();
        }
        if(db != null){
        	db.close();
        }
        closeDb();
        return code;
    }
    private String getDBID(String con) {
        String sql ="select id from zhandian where zdcode='"+con+"'";
        System.out.println("--电表code返回id--"+sql);
        DataBase db = new DataBase();
        String code="";
        rs=null;
        try {
  		rs = db.queryAll(sql);
  	
  	      if (rs.next()) {
  	    	 return rs.getString(1);
  	      }else {
              code = "0";
          }
  	     
        } catch (Exception e) {
    		e.printStackTrace();
    	 } finally{
    		 if(rs != null){
    	         	try {
    					rs.close();
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	         }
    		 if(db != null){
    			 try {
					db.close();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		 }
    	 }
       
        	 closeDb();
           return code;
    }


}
