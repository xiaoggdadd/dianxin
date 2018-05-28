package com.noki.jizhan.daoru;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import com.noki.database.DataBase;
import java.util.Iterator;
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
public class InsertZD {
    public InsertZD() {
        try {
            db = new DataBase();
            db.connectDb();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    StringBuffer sql = new StringBuffer();
    String sql2 = new String();
   
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
    	 String zdcode="";
    	 System.out.println("----------------------------begin");
    	 String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
    	 SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        int m = 0, i = 0, cg = 0, bcg = 0, zg = 0;
        String fkzq="";
        System.out.println("content==" + content);
        for (Iterator it = content.iterator(); it.hasNext(); ) {
            zg++;
            m++;
            i = 0;
            Vector row = (Vector) it.next();
            Iterator iter = row.iterator();
            sql.setLength(0);
            sql.append("INSERT INTO ZHANDIAN(SHENG,SHI,ZDCODE,XIAN,XIAOQU,JZNAME,STATIONTYPE,JZTYPE,PROPERTY,YFLX,BIEMING,BGSIGN,JNGLMK,ADDRESS,GDFS,AREA,GSF,FZR,XUNI,CAIJI,ZZJGBM,ZLFH,EDHDL,KONGTIAO,DIANFEI,MEMO,entrypensonnel,entrytime,qyzt,zdbzw) values(");
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
                            row.add("����ʧ�ܣ�ʡ���ء�������Ϊ�գ�");
                            wrongContent.add(row);
                            bcg++;
                            break;
                        }
                          switch (i) {
                            case 0:
                            	tempstr = this.getAreaCode(db, con);
                            	System.out.println(con+tempstr+"ʡ1");
                            	if(tempstr==""){
                            		bin=true;
                            		 row.add("����ʧ�ܣ�ʡ���Ʋ����ڣ�");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	 break;
                            case 1:
                            	tempstr = this.getAreaCode(db, con);
                            	
                            	
                            	
                            	
                            	 Random rnd = new Random();
                            	 zdcode = (String) (rnd.nextInt(999999999)+"");
                            	
                            	
                            	 System.out.println("-----------------"+zdcode);
                            	
                            	
                            	
                            	
                            	
                            	
                            	
                            	
                            	
                            	System.out.println(con+tempstr+"ʡ2");
                            	if(tempstr==""){
                            		bin=true;
                            		 row.add("����ʧ�ܣ������Ʋ����ڣ�");
                                     wrongContent.add(row);
                                     bcg++;
                            	}
                            	  break;
                            case 2:
                            	tempstr = this.getExactAreaCode(db, con,fagName);
                            	//System.out.println(con+tempstr+"ʡ3");
                            	if(tempstr==""){
                            		bin=true;
                           		    row.add("����ʧ�ܣ���Ӧ���µ��������Ʋ����ڣ�");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                            case 3:
                            	tempstr = this.getExactAreaCode(db, con,fagName);
                            	//System.out.println(con+tempstr+"ʡ4");
                            	if(tempstr==""){
                            		bin=true;
                           		    row.add("����ʧ�ܣ���Ӧ���µ��������Ʋ����ڣ�");
                                    wrongContent.add(row);
                                    bcg++;
                             	}
                            	  break;
                        }       
                        if(i >= 1 && i <= 3){
                        	fagName=con;
                        	//System.out.println(fagName+"��������");
                        }
                        switch (i) {
                        case 0:
                        	//System.out.println(tempstr+"���ˣ���");
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
                    	if(con == null || con.length() == 0){
                    		 row.add("����ʧ�ܣ�վ�����Ʋ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                           
                    	}else{
                    		sql.append(",'" + con + "'");	
                    	}
                        
                    }
                    if (i == 5) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ�վ�����Ͳ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                        } else {

                            tempstr = this.getPropertyCode(db, con, "stationtype");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ�վ�����Ͳ����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }
                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "stationtype") +
                                       "'");
                        }
                      
                    }
  
                    if (i == 6) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ����ű������Ͳ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                        } else {

                            tempstr = this.getPropertyCode(db, con, "ZDLX");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ����ű������Ͳ����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }
                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "ZDLX") +
                                       "'");
                        }
                    } else if (i == 7) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ�վ�����Բ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                        } else {

                            tempstr = this.getPropertyCode(db, con, "ZDSX");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ�վ�����Բ����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }

                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "ZDSX") +
                                       "'");
                        }
                    } else if (i == 8) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ��������Ͳ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                        } else {

                            tempstr = this.getPropertyCode(db, con, "FWLX");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ��������Ͳ����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }

                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "FWLX") +
                                       "'");
                        }
                    } 
                    else if (i == 9) {
                        sql.append(",'" + con + "'");
                    }/* else if (i == 9) {
                        tempstr = this.getZdCode(db, con);
                        if (!tempstr.equals("0")) {
                            bin = true;
                            row.add("����ʧ�ܣ�վ������Ѿ����ڣ��������ظ�");
                            wrongContent.add(row);
                            bcg++;
                            break;
                        }

                        sql.append(",'" + con + "'");
                    }*/ else if (i == 10) {
                        if (con.equals("��")) {
                            sql.append(",'1'");
                        } else {
                            sql.append(",'0'");
                        }
                    } else if (i == 11) {

                        if (con == null || con.length() == 0) {
                            sql.append(",'0'");
                        } else {
                            if (con.equals("��")) {
                                sql.append(",'1'");
                            } else if (con.equals("��")) {
                                sql.append(",'0'");
                            }else{
                                sql.append(",'" + con + "'");
                            }

                        }

                    } else if (i == 12) {
                        sql.append(",'" + con + "'");
                    } else if (i == 13) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ����緽ʽ����Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                        } else {

                            tempstr = this.getPropertyCode(db, con, "GDFS");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ����緽ʽ�����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }

                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "GDFS") +
                                       "'");
                        }

                    } else if (i == 14) {
                        sql.append(",'" + con + "'");
                    }else if (i == 15) {
                        if (con == null || con.length() == 0) {
                        	 row.add("����ʧ�ܣ����������Ͳ���Ϊ�գ�");
                             wrongContent.add(row);
                             bcg++;
                            //sql.append(",''");
                        } else {

                            tempstr = this.getPropertyCode(db, con, "gsf");
                            if (tempstr.equals("0")) {
                                bin = true;
                                row.add("����ʧ�ܣ����������Ͳ����ڣ�");
                                wrongContent.add(row);
                                bcg++;
                                break;

                            }

                            sql.append(",'" +
                                       this.getPropertyCode(db, con, "gsf") +
                                       "'");
                        }
                      
                    }  
                    else if (i == 16) { //fzr
                        sql.append(",'" + this.getAccountCode(db, con) + "'");
                    } else if (i == 17) {
                        if (con == null || con.length() == 0) {
                            con = "0";
                        }
                        if (con.trim().equals("��")) {
                            con = "1";
                        } else if (con.trim().equals("��")) {
                            con = "0";
                        }
                        sql.append(",'" + con + "'");
                    } else if (i == 18) {
                        if (con == null || con.length() == 0) {
                            con = "0";
                        }

                        if (con.trim().equals("��")) {
                            con = "1";
                        } else if (con.trim().equals("��")) {
                            con = "0";
                        }

                        sql.append(",'" + con + "'");
                    } else if (i == 19) {
                        sql.append(",'" + con + "'");
                    } else if (i == 20) {
                        sql.append(",'" + con + "'");
                    } else if (i == 21) {
                        sql.append(",'" + con + "'");
                    } else if (i == 22) {
                    	if (con == null || con.length() == 0) {
                          	 row.add("����ʧ�ܣ��������ڲ���Ϊ�գ�");
                               wrongContent.add(row);
                               bcg++;
                          }  
                    sql2="INSERT INTO ZDDFINFO(ZDID,FKZQ) values ((select id from zhandian where zdcode='"+zdcode+"'),'"+this.getPropertyCode(db, con, "FKZQ")+"')";
                    	 System.out.println(sql2.toString());     
                    } else if (i == 23) {
                        sql.append(",'" + con + "'");
                    } else if (i == 24) {
                    	if (con == null || con.length() == 0) {
                       	 row.add("����ʧ�ܣ����۲���Ϊ�գ�");
                            wrongContent.add(row);
                            bcg++;
                       } else {
                    	
                        sql.append(",'" + con + "'");
                        }
                    }else if (i == 25) {
                        sql.append(",'" + con + "'");
                    } 
                    i++;

                }
                sql.append(",'"+accountname+"',"+s+"");
                sql.append(",'1','0')");
                int tt = -1;
                if (!bin) {
                    System.out.println("վ�㵼�룺"+sql.toString());                  
                    tt = db.update(sql.toString());	 
                    System.out.println("---------------------------------"+sql);
	               db.update(sql2);	
                   String sql3="update zhandian set zdcode=(select id from zhandian where zdcode='"+zdcode+"') where id=(select id from zhandian where zdcode='"+zdcode+"')";
                   System.out.println("sql3"+sql3);
                   String sql4="insert into scb(id,dbmonth,zdid,scb,MODIFIER,MODIFICATIONTIME,yscb) " +
                   		"values('','2013-12',(select id from zhandian where zdcode='"+zdcode+"'),'0','','','')";
                   System.out.println("sql4"+sql4+"zdcode"+zdcode);
                   DataBase db = new DataBase();     
                   ArrayList<String> listzd=new ArrayList<String>() ;
                   rs=null;
                   try {  	 
                	   db.update(sql4);
             		rs = db.queryAll(sql3);	
             		//System.out.println("121212121"+rs);
             			
                   } catch (Exception e) {
               		e.printStackTrace();
               	 }
                  
                   
                    
                    //String sql="select zdid from zhandian where zdcode='"+zdcode+"'";
                    
	               
	               
                    closeDb();
                    
                    cg++;
                }
                if (tt != 1 && !bin) {
                    bcg++;
                    row.add("����ʧ�ܣ�");
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
        System.out.println("name"+name.trim());
        rs = db.queryAll("select agcode from d_area_grade where agname like '%" +
                         name.trim() + "%'");
        
        if (rs.next()) {
        	System.out.println("--"+rs.getString(1));
            return rs.getString(1);
        }
        closeDb();
        return code;
    }
    //����õ�����CodeΪ�ػ�����ʱ    ���þ�׼���жϷ�����ͬʱ�ж����ƺ͸����������ƶ�Ӧ�ı��룩������
    private String getExactAreaCode(DataBase db, String name,String fagName) throws Exception,
    SQLException {
		String code = "";
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
        System.out.println("select code from indexs where name = '" +
                           name.trim() + "' and type='" + typename + "'");
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

    private String getAccountCode(DataBase db, String name) throws Exception,
            SQLException {
        String code = "";
        if (name == null || name.length() == 0) {
            return "";
        }
        rs = db.queryAll("select accountname from account where name like '%" +
                         name + "%'");

        if (rs.next()) {
            return rs.getString(1);
        } else {
            code = "0";
        }
        closeDb();
        return code;
    }


}
