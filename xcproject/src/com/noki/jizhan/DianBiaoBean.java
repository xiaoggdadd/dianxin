package com.noki.jizhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;


import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.DianBiaoErrorBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.ptac.app.accounting.AccountingDao;
import com.ptac.app.util.Code;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class DianBiaoBean {
	private int allPage;

	public DianBiaoBean() {
		
	}
	// ������
	public synchronized ArrayList getPageData(int start, String sheng,
			String shi, String xian, String xiaoqu, String dbid,
			String loginName, String zdmc, String loginId, String jztype,
			String jzproperty, String dbyt, String dfzflx1, String bgsign,
			String dbqyzt, String zdqyzt,String keyword,String txtDianbiao,String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();// StringBuffer�̰߳�ȫ�Ŀɱ��ַ�������Ҫ������
												// append �� insert ����
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if(keyword!= ""){
			cxtj.append(" and z.jzname like '%" + keyword + "%'");
		}
		if (!jztype.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype + "'");
		}
		if (!jzproperty.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty + "'");
		}
		if (dbid.length() > 0 && dbid != null) {
			cxtj.append(" and d.dbid like '%" + dbid + "%'");
		}
		if (!zdmc.equals("") && zdmc != null) {
			cxtj.append(" and z.jzname like '%" + zdmc + "%'");
		}
		if (!dbyt.equals("0")) {
			cxtj.append(" and d.dbyt='" + dbyt + "'");
		}
		if (!dfzflx1.equals("0")) {
			cxtj.append(" and d.dfzflx='" + dfzflx1 + "'");
		}
		if (!bgsign.equals("-1")) {
			cxtj.append("  and z.bgsign='" + bgsign + "'");
		}
		if (!dbqyzt.equals("-1")) {
			cxtj.append("  and d.dbqyzt='" + dbqyzt + "'");
		}
		if (!zdqyzt.equals("-1")) {
			cxtj.append("  and z.qyzt='" + zdqyzt + "'");
		}
		if (txtDianbiao != "") {
			cxtj.append(" and d.DBNAME like '%" + txtDianbiao + "%'");
			
		}
		
	//System.out.println("zdqyzt"+zdqyzt);
		StringBuffer s2 = new StringBuffer();

		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs3 = null;
		StringBuffer strall = new StringBuffer();

		strall.append("select count(*) from dianbiao d,zhandian z where d.zdid=z.id ");
		strall.append(cxtj.toString());

		try {
			db.connectDb();
			String fzarea = "", fzzdid = "", fzzdstr = "";
			// fzarea = getFuzeArea(db, loginName);
			StringBuffer s3 = new StringBuffer();
			// fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");//��û���� û��
			System.out.println(cxtj.toString()+"---------"+loginId);
			s2.append("select d.id,d.dbid,d.shzt,z.jzname,z.jztype,(select t.name from indexs t where t.code=d.dfzflx and t.type='dfzflx') as dfzflx,"
							+ // ��indexs����й���
							"(select t.name from indexs t where t.code=d.sszy and t.type='SSZY') as sszy"
							+ ",(select t.name from indexs t where t.code=d.dbyt and t.type='DBYT') as dbyt"
							+",(select a.name from account a, s_workflow w where w.auditorid = to_char(a.accountid) and  w.id=(select max(w.id) from account a, s_workflow w where w.auditorid = to_char(a.accountid) and w.task_id = d.id group by w.task_id)) as accountname "//����������ж�
							+ ",(select t.name from indexs t where t.code=d.dbqyzt and t.type='dbzt') as dbzt"
							+ ",(select t.name from indexs t where t.code=z.STATIONTYPE and t.type='stationtype') as zdlx"
							+ ",(select  ag.agname from d_area_grade ag where ag.agcode =z.xian) as xian"
							+ ",(select  ag.agname from d_area_grade ag where ag.agcode =z.shi) as shi"
							+ ",d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime,d.beilv,d.dbxh,d.memo,d.zrr,d.bzr,d.yhbh,d.dbbm,d.dbname,d.wlbm"
							+ ",(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian and fagcode=z.shi) else '' end)"
							+ "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu and fagcode=z.xian) else '' end) as szdq," 
							+ "z.id as zdid,acc1.name BZRNAME,acc2.name ZRRNAME "
							+ " from dianbiao d left join zhandian z on d.zdid=z.id " +
							"left join account acc1 on acc1.CTHRNUMBER=d.bzr left join account acc2 on acc2.CTHRNUMBER=d.zrr where 1=1 "
							+whereStr
							+ cxtj.toString()
							+ " "
							+ "  and ((z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"
							+ loginId
							+ "'))) order by z.sheng,z.shi,z.xian,z.xiaoqu,z.jzname");
			
			System.out.println("�������ѯ��" + s2);
			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("����ѯ��ҳ" + s3);
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	// ������
	public synchronized ArrayList getPageDataSP(int start, String sheng,
			String shi, String xian, String xiaoqu, String dbid,
			String loginName, String zdmc, String loginId, String jztype,
			String jzproperty, String dbyt, String dfzflx1, String bgsign,
			String dbqyzt, String zdqyzt,String keyword) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();// StringBuffer�̰߳�ȫ�Ŀɱ��ַ�������Ҫ������
												// append �� insert ����
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}
		if(!keyword.equals("0")){
			cxtj.append(" and z.jzname like '%" + keyword + "%'");
		}
		if (!jztype.equals("0")) {
			cxtj.append(" and z.jztype='" + jztype + "'");
		}
		if (!jzproperty.equals("0")) {
			cxtj.append(" and z.property='" + jzproperty + "'");
		}
		if (dbid.length() > 0 && dbid != null) {
			cxtj.append(" and d.dbid like '%" + dbid + "%'");
		}
		if (!zdmc.equals("") && zdmc != null) {
			cxtj.append(" and z.jzname like '%" + zdmc + "%'");
		}
		if (!dbyt.equals("0")) {
			cxtj.append(" and d.dbyt='" + dbyt + "'");
		}
		if (!dfzflx1.equals("0")) {
			cxtj.append(" and d.dfzflx='" + dfzflx1 + "'");
		}
		if (!bgsign.equals("-1")) {
			cxtj.append("  and z.bgsign='" + bgsign + "'");
		}
		if (!dbqyzt.equals("-1")) {
			cxtj.append("  and d.dbqyzt='" + dbqyzt + "'");
		}
		if (!zdqyzt.equals("-1")) {
			cxtj.append("  and z.qyzt='" + zdqyzt + "'");
		}
		System.out.println("zdqyzt"+zdqyzt);
		StringBuffer s2 = new StringBuffer();

		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs3 = null;
		StringBuffer strall = new StringBuffer();

		strall.append("select count(*) from dianbiao d,zhandian z where d.zdid=z.id ");
		strall.append(cxtj.toString());

		try {
			db.connectDb();
			String fzarea = "", fzzdid = "", fzzdstr = "";
			// fzarea = getFuzeArea(db, loginName);
			StringBuffer s3 = new StringBuffer();
			// fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");//��û���� û��
			System.out.println(cxtj.toString()+"---------"+loginId);
			s2.append("select d.id,d.dbid,z.jzname,z.jztype,(select t.name from indexs t where t.code=d.dfzflx and t.type='dfzflx') as dfzflx,"
							+ // ��indexs����й���
							"(select t.name from indexs t where t.code=d.sszy and t.type='SSZY') as sszy"
							+ ",(select t.name from indexs t where t.code=d.dbyt and t.type='DBYT') as dbyt"
							+ ",(select t.name from indexs t where t.code=z.STATIONTYPE and t.type='stationtype') as zdlx"
							+ ",d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime,d.beilv,d.dbxh,d.memo"
							+ ",(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian and fagcode=z.shi) else '' end)"
							+ "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu and fagcode=z.xian) else '' end) as szdq,z.id as zdid"
							+ " from dianbiao d,zhandian z where d.issp !=0 and d.zdid=z.id "
							+ cxtj.toString()
							+ " "
							+ "  and ((z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"
							+ loginId
							+ "'))) order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");

			System.out.println("�������ѯ��" + s2);
			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("����ѯ��ҳ" + s3);
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	// ��Ӽ���������
	public synchronized ArrayList getPageData(int start, String sheng,
			String shi, String xian, String xiaoqu, String dbid,
			String loginName, String zdmc, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();// StringBuffer�̰߳�ȫ�Ŀɱ��ַ�������Ҫ������
												// append �� insert ����
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}

		if (dbid.length() > 0 && dbid != null) {
			cxtj.append(" and d.dbid like '%" + dbid + "%'");
		} else if (!zdmc.equals("") && zdmc != null) {
			cxtj.append(" and z.jzname like '%" + zdmc + "%'");
		}

		StringBuffer s2 = new StringBuffer();

		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();

		strall.append(cxtj.toString());

		try {
			db.connectDb();
			String fzarea = "", fzzdid = "";
			// fzarea = getFuzeArea(db, loginName);
			StringBuffer s3 = new StringBuffer();
			// fzzdid = this.getFuzeZdid(db, loginName, fzarea, "0");
			System.out.println("fzzdid====" + fzzdid);
			s2
					.append("select d.id,d.dbid,d.shzt,z.jzname,z.jztype,(select t.name from indexs t where t.code=d.dfzflx and t.type='dfzflx') as dfzflx,"
							+ // ��indexs����й���
							"(select t.name from indexs t where t.code=d.sszy and t.type='SSZY') as sszy,"
							+ ",(select t.name from indexs t where t.code=d.dbyt and t.type='DBYT') as dbyt"
							+ ",d.csds, to_char(d.cssytime,'yyyy-mm-dd') cssytime,d.beilv,d.dbxh,d.memo"
							+ ",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)"
							+ "||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian and fagcode=z.shi) else '' end)"
							+ "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu and fagcode=z.xian) else '' end) as szdq"
							+ " from dianbiao d,zhandian z where d.zdid=z.id and z.qyzt='1' and d.dbyt='dbyt01' and d.dbqyzt='1' "
							+ cxtj.toString()
							+ ""
							+ " and z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"
							+ loginId
							+ "') order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");

			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("�����ѯ��ҳ" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();

			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}

	
	 public synchronized ArrayList getYJData() {
         ArrayList list = new ArrayList();
         DataBase db = new DataBase();
         String sql = "";
         sql +="select e.dianbiaoid, max(e.endtime) from electricfees e where 1=1 and e.endtime is not null  group by e.dianbiaoid";
         ResultSet rs = null;
         System.out.println("����Ԥ��sql="+sql);
         int ts=0;
         try {
             db.connectDb();
             rs = db.queryAll(sql);
             int zq=0;
             int tianshu=0;
             Date date=new Date();
             String type="";
             while (rs.next()) {
                     String id=rs.getString("DIANBIAOID");
                     String lastEndTime=rs.getString("MAX(E.ENDTIME)");
                     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
                     Date lasttime=sdf.parse(lastEndTime); 
                     String str=sdf.format(lasttime); 
                     ResultSet rs2 = null;
                     String sql2="select d.dbname,d.dbbm,d.jfzq,d.jffs from dianbiao d where d.id='"+id+"'";
         			System.out.println("sql2="+sql2);
                    rs2 = db.queryAll(sql2);  
                    while (rs2.next()) {
                        	String dbname=rs2.getString("DBNAME");
                        	String wlbm=rs2.getString("DBBM");
                        	String jfzq=rs2.getString("JFZQ");
                        	String jffs=rs2.getString("JFFS");
                        	if(jfzq.equals("fkzq01")){
                        		zq=30;
                        	}else if(jfzq.equals("fkzq02")){
                        		zq=60;
                        	}else if(jfzq.equals("fkzq03")){
                        		zq=90;
                        	}else if(jfzq.equals("fkzq06")){
                        		zq=180;
                        	}else if(jfzq.equals("fkzq07")){
                        		zq=365;
                        	}else if(jfzq.equals("fkzq08")){
                        		zq=730;
                        	}
                        	if(jffs.equals("jffs01")||jffs.equals(	"jffs02")){
                        		type="hf";
                        	}else if(jffs.equals("jffs03")){
                        		type="yf";
                        	}
                        	String newdate=plusDay(zq,lastEndTime);
                            java.util.Date nexttime=sdf.parse(newdate); 
                            tianshu=differentDays(date,nexttime);
                            ts=Integer.parseInt(getTJTS(type));
                            if(tianshu<=ts){
                            	list.add(dbname);
                            	list.add(wlbm);
                            	list.add(str);
                            	list.add("");
                            }
                        }
                    }
                     
         } catch (DbException de) {
             de.printStackTrace();
         } catch (SQLException se) {
             se.printStackTrace();
         } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
             try {
                 db.close();
             } catch (DbException de) {
                 de.printStackTrace();
             }
         }

         return list;
     }
	 //app����Ԥ��
	 public synchronized ArrayList getYJDataapp() {
         ArrayList list = new ArrayList();
         DataBase db = new DataBase();
         String sql = "";
         sql +="select e.dianbiaoid, max(e.endtime_c) from electricfees e where 1=1 and e.endtime is not null  group by e.dianbiaoid";
         ResultSet rs = null;
         System.out.println("sql="+sql);
         int ts=0;
         try {
             db.connectDb();
             rs = db.queryAll(sql);
             int zq=0;
             int tianshu=0;
             Date date=new Date();
             String type="";
             while (rs.next()) {
                     String id=rs.getString("DIANBIAOID");
                     String lastEndTime=rs.getString("MAX(E.ENDTIME_C)");
                     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
                     Date lasttime=sdf.parse(lastEndTime); 
                     String str=sdf.format(lasttime); 
                     ResultSet rs2 = null;
                     String sql2="select d.dbname,d.dbbm,d.jfzq,d.jffs from dianbiao d where d.id='"+id+"'";
         			System.out.println("sql2="+sql2);
                    rs2 = db.queryAll(sql2);
                    while (rs2.next()) {
                        	String dbname=rs2.getString("DBNAME");
                        	String wlbm=rs2.getString("DBBM");
                        	String jfzq=rs2.getString("JFZQ");
                        	String jffs=rs2.getString("JFFS");
                        	if(jfzq.equals("fkzq01")){
                        		zq=30;
                        	}else if(jfzq.equals("fkzq02")){
                        		zq=60;
                        	}else if(jfzq.equals("fkzq03")){
                        		zq=90;
                        	}else if(jfzq.equals("fkzq06")){
                        		zq=180;
                        	}else if(jfzq.equals("fkzq07")){
                        		zq=365;
                        	}else if(jfzq.equals("fkzq08")){
                        		zq=730;
                        	}
                        	if(jffs.equals("jffs01")||jffs.equals(	"jffs02")){
                        		type="hf";
                        	}else if(jffs.equals("jffs03")){
                        		type="yf";
                        	}
                        	String newdate=plusDay(zq,lastEndTime);
                            java.util.Date nexttime=sdf.parse(newdate); 
                            tianshu=differentDays(date,nexttime);
                            ts=Integer.parseInt(getTJTS(type));
                            if(tianshu<=ts){
                            	list.add(dbname);
                            	list.add(wlbm);
                            	list.add(str);
                            	list.add("");
                            }
                        }
                    }
                     
         } catch (DbException de) {
             de.printStackTrace();
         } catch (SQLException se) {
             se.printStackTrace();
         } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
             try {
                 db.close();
             } catch (DbException de) {
                 de.printStackTrace();
             }
         }

         return list;
     }
	 /**
	      * ָ�����ڼ��������������
	     * @param num Ϊ���ӵ�����
	       * @param newDate ����ʱ��
	      * @return
	       * @throws ParseException 
	      */
	     public static String plusDay(int num,String newDate) throws ParseException{
	         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	         Date  currdate = format.parse(newDate);
	         Calendar ca = Calendar.getInstance();
	         ca.setTime(currdate);  
	         ca.add(Calendar.DATE, num);// numΪ���ӵ����������Ըı��
	         currdate = ca.getTime();
	         String enddate = format.format(currdate);
	         return enddate;
	     }
	     /**
	      * date2��date1�������
	      * @param date1    
	      * @param date2
	      * @return    
	      */
	     public static int differentDays(Date date1,Date date2)
	     {
	         Calendar cal1 = Calendar.getInstance();
	         cal1.setTime(date1);
	         
	         Calendar cal2 = Calendar.getInstance();
	         cal2.setTime(date2);
	        int day1= cal1.get(Calendar.DAY_OF_YEAR);
	         int day2 = cal2.get(Calendar.DAY_OF_YEAR);
	         
	         int year1 = cal1.get(Calendar.YEAR);
	         int year2 = cal2.get(Calendar.YEAR);
	         if(year1 != year2)   //ͬһ��
	         {
	             int timeDistance = 0 ;
	             for(int i = year1 ; i < year2 ; i ++)
	             {
	                 if(i%4==0 && i%100!=0 || i%400==0)    //����            
	                 {
	                     timeDistance += 366;
	                 }
	                 else    //��������
	                 {
	                     timeDistance += 365;
	                 }
	             }
	             
	             return timeDistance + (day2-day1) ;
	         }
	         else    //��ͬ��
	         {
	             System.out.println("�ж�day2 - day1 : " + (day2-day1));
	             return day2-day1;
	         }
	     }
	     public synchronized String getTJTS(String type) {
	    	 DataBase db = new DataBase();
	    	 String nextStep = "";
	         ResultSet rs = null;
	         String sql="";
	         try {
	        	 if(type.equals("hf")){
	        		 sql="select name  from indexs where code='cbyj01'"; 
	        	 }
	        	 else if(type.equals("yf")){
	        		 sql="select name  from indexs where code='cbyj02'"; 
	        	 }
	        	 System.out.println("===="+sql);
	        	 rs = db.queryAll(sql);
	             while (rs.next()) {
	                 return rs.getString(1);
	             }
	             rs.close();
	         } catch (DbException de) {
	             de.printStackTrace();
	         } catch (SQLException se) {
	             se.printStackTrace();
	         }
	         finally {
	             try {
	                 db.close();
	             } catch (DbException de) {
	                 de.printStackTrace();
	             }
	         }

	         return nextStep;
	     } 
	 
	private String getQueryStr_zd_all(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select z.id from zhandian z where ");

		s2.append(" z.id in(" + fzzdid + ")");

		s.append("select count(*) from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) ");
		System.out.println("վ��������䣺" + s.toString());
		return s.toString();
	}

	private String getQueryStr_zd(String fzzdid, String querystr) {
		StringBuffer s = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		s2.append("select d.id,d.dbid,z.jzname,");
		s2.append("(select t.name from indexs t where t.code=d.sszy ) as sszy");
		s2
				.append(",(select t.name from indexs t where t.code=d.dbyt ) as dbyt");
		s2.append(",d.csds,d.cssytime,d.beilv,d.dbxh,d.memo");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select agname from d_area_grade where agcode=z.xian) else '' end) as szdq");
		s2.append(" from dianbiao d,zhandian z where d.zdid=z.id ");

		s2.append(" and d.zdid in(" + fzzdid + ")");

		s.append("select * from (");
		s.append(querystr);
		s.append(" union ");
		s.append(s2.toString());
		s.append(" ) order by dbid");
		System.out.println("վ��������䣺" + s.toString());
		return s.toString();
	}

	private String getFuzeZdid(DataBase db, String loginName, String fzarea,
			String jztype) throws DbException, SQLException {
		StringBuffer s = new StringBuffer("0");
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountname='"
						+ loginName
						+ "' and begincode is not null and endcode is not null");
		StringBuffer cxtj = new StringBuffer("(");
		int csnum = 0;
		while (rs.next()) {
			if (csnum == 0) {
				cxtj.append(" (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')");
			} else {
				cxtj.append(" or ( zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')");
			}
			csnum++;
		}
		if (csnum == 0) {
			return s.toString();
		}
		cxtj.append(")");
		if (fzarea.equals("'0'")) { // û�����ø������� ���ز�ѯ����վ��Ĳ�ѯ����
			s.setLength(0);
			s.append(cxtj.toString());
		} else { // �����ø������� ����û���ڸ��������ڵĸ���վ��id
			StringBuffer s3 = new StringBuffer();
			s3.append("select id from zhandian where ");
			if (cxtj.equals("()")) {
				s3.append(" id not in (");
			} else {
				s3.append(cxtj.toString());
				s3.append(" and id not in (");
			}

			s3.append("select id from zhandian where xiaoqu in (" + fzarea
					+ ") ");
			if (!jztype.equals("0")) {
				s3.append(" and jztype='" + jztype + "'");
			}
			s3.append(")");
			if (!jztype.equals("0")) {
				s3.append(" and jztype='" + jztype + "'");
			}
			System.out.println(s3.toString());
			rs = db.queryAll(s3.toString());
			StringBuffer ids = new StringBuffer();
			while (rs.next()) {

				ids.append("," + rs.getString(1));

			}
			s.append(ids.toString());
		}

		return s.toString();
	}

	private String getFuzeArea(DataBase db, String loginName)
			throws DbException, SQLException {
		StringBuffer s = new StringBuffer();
		ResultSet rs = db
				.queryAll("select agcode from per_area where accountname='"
						+ loginName + "' order by agcode");
		ResultSet rs2 = null;
		while (rs.next()) {
			if (rs.getString(1).length() == 9) {
				s.append("'" + rs.getString(1) + "',");
			} else {
				rs2 = db
						.queryAll("select agcode from d_area_grade where agcode like'"
								+ rs.getString(1) + "%'");
				while (rs2.next()) {
					s.append("'" + rs2.getString(1) + "',");
				}
			}
		}
		s.append("'-1'");
		return s.toString();
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public synchronized ArrayList getOutPutData(String sheng, String shi,
			String xian, String xiaoqu, String dbid) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		} else if (!sheng.equals("0")) {
			cxtj.append(" and z.sheng='" + sheng + "'");
		}

		if (dbid.length() > 0 && dbid != null) {
			cxtj.append(" and d.dbid like '%" + dbid + "%'");
		}

		StringBuffer s2 = new StringBuffer();
		s2.append("select d.id,d.dbid,z.jzname,");
		s2.append("(select t.name from indexs t where t.code=d.sszy ) as sszy");
		s2
				.append(",(select t.name from indexs t where t.code=d.dbyt ) as dbyt");
		s2.append(",d.csds,d.cssytime,d.beilv,d.dbxh,d.memo");
		s2
				.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
		s2
				.append("||(case when z.xian is not null then (select agname from d_area_grade where agcode=z.xian) else '' end) as szdq");
		s2.append(" from dianbiao d,zhandian z where d.zdid=z.id ");
		s2.append(cxtj.toString());
		s2.append(" order by szdq");
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			rs = db.queryAll(s2.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}

	public synchronized int delData(String id) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();

		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("delete from dianbiao where id=" + id);
		sql1.append("delete from sbgl where dianbiaoid=(select dbid from dianbiao where id='"+ id + "')");
		System.out.println("ɾ������" + sql1.toString());
		System.out.println("ɾ�����" + sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			if (this.validateDelDianbiao(db, id).equals("����")) {
				return 2;
			} else if (this.validateDelDianbiao(db, id).equals("Ԥ����")) {
				return 3;
			}
			db.setAutoCommit(false);
			db.update(sql1.toString());
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			db.close();
		} catch (DbException de) {

			de.printStackTrace();
		} catch (SQLException de) {

			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	public synchronized int delData1(String id) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();

		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql.append("delete from TBL_TT_WY_DFFT where id=" + id);
		//sql1.append("delete from sbgl where dianbiaoid=(select dbid from dianbiao where id='"+ id + "')");
		//System.out.println("ɾ������" + sql1.toString());
		System.out.println("ɾ�����" + sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			
//			if (this.validateDelDianbiao(db, id).equals("����")) {
//				return 2;
//			} else if (this.validateDelDianbiao(db, id).equals("Ԥ����")) {
//				return 3;
//			}
			db.setAutoCommit(false);
			//db.update(sql1.toString());
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			System.out.println("1111111111111");
			msg = 1;
			db.close();
			
		}
	catch (DbException de) {

			de.printStackTrace();
		}
//		catch (SQLException de) {
//
//			de.printStackTrace();
//		}
		finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
		
}
	
	
	
	private String validateDelDianbiao(DataBase db, String dbid)
			throws DbException, SQLException {
		String msg = "1";
		System.out
				.println("select * from sbgl where dianbiaoid=(select dbid from dianbiao where id="
						+ dbid + ")");
		/*
		 * ResultSet rs = db.queryAll(
		 * "select * from sbgl where dianbiaoid=(select dbid from dianbiao where id="
		 * + dbid + ")");
		 */

		ResultSet rs2 = db
				.queryAll("select * from prepayment p,dianbiao d where d.dbid=p.prepayment_ammeterid and d.id='"
						+ dbid + "'");
		ResultSet rs = db
				.queryAll("select * from ammeterdegrees a,dianbiao d where d.dbid=a.ammeterid_fk and d.id='"
						+ dbid + "'");

		if (rs2.next()) {
			msg = "Ԥ����";
		}
		if (rs.next()) {
			msg = "����";

		}
		return msg;
	}

	// �õ��id�Զ����� ����������DBID Ȼ��+1
	public synchronized ArrayList<String> SelectDBID(String shi, String str) {
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select max(to_number(substr(b.dbid,4))) as dbid from dianbiao b,zhandian z  where shi='"
						+ shi
						+ "' and b.zdid=z.id and b.dbyt<>'dbyt02' and dbid like '%"
						+ str + "%'");

		System.out.println(sql.toString());
		ArrayList<String> list = new ArrayList<String>();
		DataBase db = new DataBase();
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			try {
				while (rs.next()) {
					System.out.println(rs.getString(1));
					list.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			rs.close();

		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}

		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return list;
	}

	// ��ӵ��
	public synchronized int addData(String dbid, String zdid, String dllx,
			String ydsb, String shi, String sszy, String dbyt, String csds,
			String cssytime, String beilv, String dbxh, String memo,
			String cjr, String netpoint_name, String netpoint_id, String yhdl,
			String dfzflx, String dbname, String linelosstype,
			String linelossvalue, String entrypensonnel, String entrytime,
			String dbqyzt, String zbdyhh, String ydbid,String danjia,String bsdl,String zgdj,String zzsl,String countbzw) {

		int msg = 0;
		CTime ctime = new CTime();
		ResultSet rs = null;
		DataBase db = new DataBase();
		if("0".equals(countbzw) && "dbyt01".equals(dbyt)){
			StringBuffer sql1 = new StringBuffer();
			sql1.append("update dianbiao set DBQYZT = '0' where dbyt='dbyt01' and zdid=" + zdid);
			try {
				db.update(sql1.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			 System.out.println("�رո�վ�������н�����"+sql1.toString());
		}

		Random rnd = new Random();// �����
		dbid = (String) (rnd.nextInt(999999999) + "");

		System.out.println("���id�������" + dbid);
		String jcdsql = "insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,XJID,XJBILI)values((select id from dianbiao where dbid='"
				+ dbid
				+ "'),"
				+ "to_char((select id from dianbiao where dbid = '"
				+ dbid
				+ "')) || '01"
				+ "','zylx01','100','1',to_char((select id from dianbiao where dbid = '"
				+ dbid + "')) || '0101" + "','100')";
		//

		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		StringBuffer sql = new StringBuffer();
		sql
				.append("INSERT INTO DIANBIAO(zdid,dbid,dllx,sszy,dbyt,csds,cssytime,beilv,dbxh,memo,ydsb,netpoint_name, "
						+ "netpoint_id,dbname,dfzflx,linelosstype,linelossvalue,entrypensonnel,entrytime,bzw,dbqyzt,dbzbdyhh,ydbid,danjia,changevalue,zgdj,zzsl,CSLRR,CSLRSJ)");
		sql.append("VALUES (" + zdid + ",'" + dbid + "','" + dllx + "','"
				+ sszy + "','" + dbyt + "','" + csds + "',to_date('" + cssytime + "','yyyy-mm-dd'),");
		sql.append("'" + beilv + "','" + dbxh + "','" + memo + "','" + ydsb
				+ "','" + netpoint_name + "','" + netpoint_id + "','" + dbname
				  + "','" + dfzflx + "','" + linelosstype + "',"
				+ "'" + linelossvalue + "','" + entrypensonnel + "'," + s
				+ ",'1','" + dbqyzt + "','" + zbdyhh + "','" + ydbid + "','" + danjia + "','" + bsdl + "',"+zgdj+","+zzsl+",'"+entrypensonnel+"',"+s+")");

		// sql.append(",0,'" "')");   zgdj,String zzsl

		String sql4 = "update dianbiao set dbid=(select id from dianbiao where dbid='"
				+ dbid
				+ "') where id=(select id from dianbiao where dbid='"
				+ dbid + "')";

		System.out.println("������" + sql.toString());
		System.out.println("Ϊ�����ӷ�̯��" + jcdsql.toString());
		System.out.println("�޸ĵ��id������" + sql4);

		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql.toString());
			if (dbyt.equals("dbyt01")) {
				db.update(jcdsql.toString());
			}
			rs = db.queryAll(sql4);
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			db.close();
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	public synchronized int addDB(String zdid,String dbbm,String dbname,String cssysj,String beilv,String csds,String dfzflx,String wlbm,String maxds,String dwjslx,String isglf
			,String glbm,String zrr,String bzr,String ydsx,String ydlx,String jffs,String jfzq,String jldw,String yhbh,String dbqyzt,String bgje,String mtllhd,
			String danjia,String gysmc,String gysbm,String skfmc,String yhzh,String ssyh,String khyh,String zhsss,String zhssshi,String memo,String fplx,String zzsl,
			String dbyt,String dbch,String dbscbs,String ENTRYTIMEOLD,
			 String is_cont, String cont_type, String zndb, String isdblx, String cbzx, String cbzxbm, String production_prop,
			 String bur_stand_propertion, String cont_id, String electric_supply_way, String total_electricity, String zhangqi, 
			 String zhz,String gszt,String LJID,String dbtype,String thid,String jiaoliu,String zhiliu,String ssf,String ftbl,String sfyz) {

		int msg = 0;
		int eidStr = 0;
		StringBuffer sql = new StringBuffer();
		//���������Ҫ�������ı���
		sql.append("INSERT INTO DIANBIAO(ZDID,DBBM,DBNAME,BEILV,CSDS,DFZFLX,DBID,wlbm,MAXDS,DWJSLX,ISGLF," +
				"GLBM,ZRR,BZR,YDSX,YDLX,JFFS,JFZQ,JLDW,YHBH,DBQYZT,BGJE,MTLLHD,DANJIA,GYSMC,GYSBM,SKFMC,YHZH," +
				"SSYH,KHYH,ZHSSS,ZHSSSHI,MEMO,FPLX,ZZSL,DBYT,DBCH,DBSCBS," +
				" is_cont,cont_type,zndb,isdblx,cbzx,cbzxbm,production_prop," +
				" bur_stand_propertion,cont_id,electric_supply_way,total_electricity,zq,zhz,gszt,LJID,dbtype,SHZT,thid,jiaoliu,ssf,ftbl,sfyz,zhiliu,ENTRYTIMEOLD) " +
				"VALUES ("+zdid+",'"+dbbm+"','"+dbname+"',"+beilv+","+csds+",'"+dfzflx+"','"+zdid+"','"+wlbm+"','"+maxds+"'" +
				",'"+dwjslx+"','"+isglf+"','"+glbm+"','"+zrr+"','"+bzr+"','"+ydsx+"','"+ydlx+"','"+jffs+"','"+jfzq+"','"+jldw+"','"+yhbh+"'" +
				",'"+dbqyzt+"','"+bgje+"','"+mtllhd+"','"+danjia+"','"+gysmc+"','"+gysbm+"','"+skfmc+"','"+yhzh+"','"+ssyh+"','"+khyh+"'," +
				"'"+zhsss+"','"+zhssshi+"','"+memo+"','"+fplx+"','"+zzsl+"','"+dbyt+"','"+dbch+"','"+dbscbs+"','"+
				is_cont+"','"+cont_type+"','"+zndb+"','"+isdblx+"','"+cbzx+"','"+cbzxbm+"','"+production_prop+"','"+bur_stand_propertion+"','"+
				cont_id+"','"+electric_supply_way+"','"+total_electricity+"','"+zhangqi+"','"+zhz+"','"+gszt+"','"+LJID+"','"+dbtype+"',0,'"+thid+
				"','"+jiaoliu+"','"+ssf+"','"+ftbl+"','"+sfyz+"',"+zhiliu+",'"+ENTRYTIMEOLD+"')");
		//�����������Ҫ�������ı���
//		sql.append("INSERT INTO DIANBIAO(ZDID,DBBM,DBNAME,BEILV,CSDS,DFZFLX,DBID,wlbm,MAXDS,DWJSLX,ISGLF," +
//				"GLBM,ZRR,BZR,YDSX,YDLX,JFFS,JFZQ,JLDW,YHBH,DBQYZT,BGJE,MTLLHD,DANJIA,GYSMC,GYSBM,SKFMC,YHZH," +
//				"SSYH,KHYH,ZHSSS,ZHSSSHI,MEMO,FPLX,ZZSL,DBYT,DBCH,DBSCBS," +
//				" is_cont,cont_type,zndb,isdblx,cbzx,cbzxbm,production_prop," +
//				" bur_stand_propertion,cont_id,electric_supply_way,total_electricity,zq,zhz,gszt,LJID,dbtype,SHZT,thid,jiaoliu,ssf,ftbl,sfyz,zhiliu,ENTRYTIMEOLD) " +
//				"VALUES ("+zdid+",'"+dbbm+"','"+dbname+"',"+beilv+","+csds+",'"+dfzflx+"','"+zdid+"','"+wlbm+"','"+maxds+"'" +
//				",'"+dwjslx+"','"+isglf+"','"+glbm+"','"+zrr+"','"+bzr+"','"+ydsx+"','"+ydlx+"','"+jffs+"','"+jfzq+"','"+jldw+"','"+yhbh+"'" +
//				",'"+dbqyzt+"','"+bgje+"','"+mtllhd+"','"+danjia+"','"+gysmc+"','"+gysbm+"','"+skfmc+"','"+yhzh+"','"+ssyh+"','"+khyh+"'," +
//				"'"+zhsss+"','"+zhssshi+"','"+memo+"','"+fplx+"','"+zzsl+"','"+dbyt+"','"+dbch+"','"+dbscbs+"','"+
//				is_cont+"','"+cont_type+"','"+zndb+"','"+isdblx+"','"+cbzx+"','"+cbzxbm+"','"+production_prop+"','"+bur_stand_propertion+"','"+
//				cont_id+"','"+electric_supply_way+"','"+total_electricity+"','"+zhangqi+"','"+zhz+"','"+gszt+"','"+LJID+"','"+dbtype+"',2,'"+thid+
//				"','"+jiaoliu+"','"+ssf+"','"+ftbl+"','"+sfyz+"',"+zhiliu+",'"+ENTRYTIMEOLD+"')");
		System.out.println("�������"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			db.setAutoCommit(false);
			eidStr = db.update(sql.toString(), new String[]{"ID"});
			System.out.println("eidStr:"+eidStr);
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			db.close();
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return eidStr;
	}

	public synchronized int modifyData(String dbid, String id, String dllx,
			String ydsb, String sszy, String dbyt, String csds,
			String cssytime, String beilv, String dbxh, String memo,
			String netpoint_name, String netpoint_id, 
			String dfzflx, String dbname, String linelosstype,
			String linelossvalue, String entrypensonnel, String entrytime,
			String dbqyzt, String zbdyhh, String ydbid,String danjia,String bsdl,String zgdj,String zzsl,
			String zdid, String countbzw, String count,String qydb, String gbdb) {
		// birthday = birthday.length()>0?birthday:null;
		int msg = 0;
		CTime ctime = new CTime();
		String s = " to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS') ";
		DataBase db = new DataBase();		
		try {
			StringBuffer sql1 = new StringBuffer();
			sql1.append("update dianbiao d set d.dbqyzt='0' where d.zdid='"+zdid+"' and d.dbid<>'"+dbid+"' and d.dbyt='dbyt01'");
			
			StringBuffer sql3 = new StringBuffer();
			sql3.append("update dianbiao d set d.dbqyzt='1' where d.dbid=(select max(d.dbid) from dianbiao d ");
			sql3.append("where d.dbyt='dbyt01' and d.dbid<>'"+dbid+"' and d.zdid='"+zdid+"')");
			
			if("1".equals(dbqyzt) && "0".equals(countbzw) && "dbyt01".equals(dbyt)){
				 db.update(sql1.toString());
				 System.out.println("�ر�վ�������е��,���޸ĵı����"+sql1.toString());
			}else if("0".equals(dbqyzt) && (Double.parseDouble(qydb))>1 && "0".equals(countbzw)){
	          
				db.update(sql1.toString());
				System.out.println("�ر�վ�������е��,���޸ĵı����"+sql1.toString());
				String thisdatetime = this.getMaxDate(db,zdid,dbid);//��ȡվ��ĵ����󳭱�ʱ�� ,�������
				if("".equals(thisdatetime) || "null".equals(thisdatetime) || thisdatetime == null){
					db.update(sql3.toString());
					System.out.println("����Ϊ��,���õ��ID���ĵ��,�������:"+sql3.toString());
				}else{
					thisdatetime = thisdatetime.substring(0,10);
					StringBuffer sql2 = new StringBuffer();
					sql2.append("update dianbiao d set d.dbqyzt='1' where d.zdid='"+zdid+"' and d.dbid<>'"+dbid+"' and d.dbyt='dbyt01' and ");
					sql2.append(" d.dbid=(select max(d.dbid) from dianbiao d ,ammeterdegrees a where d.dbid=a.ammeterid_fk ");
					sql2.append(" and to_char(a.thisdatetime,'yyyy-mm-dd')='"+thisdatetime+"' and d.dbyt='dbyt01' and d.dbid<>'"+dbid+"' and d.zdid='"+zdid+"')");
					System.out.println("���ڲ�Ϊ��,���ݲ�ѯ������󱾴γ���ʱ��,��ȡ���ID���ĵ��,�޸�����״̬Ϊ1��"+sql2.toString());
					db.update(sql2.toString());
				}
			}else if("0".equals(dbqyzt) && (Double.parseDouble(qydb))==0 && "0".equals(countbzw)){
				
				String thisdatetime = this.getMaxDate(db,zdid,dbid);//��ȡվ��ĵ����󳭱�ʱ�� ,�������
				if("".equals(thisdatetime) || "null".equals(thisdatetime) || thisdatetime == null){
					db.update(sql3.toString());
					System.out.println("����Ϊ��,���õ��ID���ĵ��,�������"+sql3.toString());
				}else{
					thisdatetime = thisdatetime.substring(0,10);
					StringBuffer sql2 = new StringBuffer();
					sql2.append("update dianbiao d set d.dbqyzt='1' where d.zdid='"+zdid+"' and d.dbid<>'"+dbid+"' and d.dbyt='dbyt01' and ");
					sql2.append(" d.dbid=(select max(d.dbid) from dianbiao d ,ammeterdegrees a where d.dbid=a.ammeterid_fk ");
					sql2.append(" and to_char(a.thisdatetime,'yyyy-mm-dd')='"+thisdatetime+"' and d.dbyt='dbyt01' and d.dbid<>'"+dbid+"' and d.zdid='"+zdid+"')");
					System.out.println("���ڲ�Ϊ��,���ݲ�ѯ������󱾴γ���ʱ��,��ȡ���ID���ĵ��,�޸�����״̬Ϊ1��"+sql2.toString());
					db.update(sql2.toString());
				}
			}
			
			StringBuffer sql = new StringBuffer();
			sql.append("update dianbiao set DBID='" + dbid + "',DLLX='" + dllx
					+ "', YDSB='" + ydsb + "', SSZY='" + sszy + "', DBYT='" + dbyt
					+ "', CSDS='" + csds + "'");
			sql.append(",CSSYTIME= to_date('" + cssytime + "','yyyy-mm-dd'), BEILV='" + beilv
					+ "', DBXH='" + dbxh + "',MEMO='" + memo + "'");
			sql.append(",netpoint_name='" + netpoint_name + "',netpoint_id='"
					+ netpoint_id + "',dbname='" + dbname + "',dfzflx='" + dfzflx + "',linelosstype='" + linelosstype
					+ "',linelossvalue='" + linelossvalue + "',"
					+ "entrypensonnel='" + entrypensonnel + "',entrytime=" + s
					+ " ,bzw='1',dbqyzt='" + dbqyzt + "',dbzbdyhh='" + zbdyhh
					+ "',ydbid='" + ydbid + "',danjia='"+danjia + "',CHANGEVALUE='"+bsdl+"',ZGDJ="+zgdj+",ZZSL="+zzsl+" ");
			sql.append(" where id=" + id);
	
			System.out.println("�޸ĵ����Ϣ��"+sql.toString());
			
			db.connectDb();			
			db.setAutoCommit(true);
			
			db.update(sql.toString());
			db.commit();
			db.setAutoCommit(true);
			msg = 1;
			
			db.close();
		} catch (DbException de) {
//			try {
//				db.rollback();
//			} catch (DbException dee) {
//				dee.printStackTrace();
//			}
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		
		return msg;
	}
	
	private synchronized String getMaxDate(DataBase db, String zdid, String dbid) {

		StringBuffer sql = new StringBuffer();
		sql.append("select max(a.thisdatetime) from zhandian z ,dianbiao d ,ammeterdegrees a,electricfees e");
		sql.append(" where z.id=d.zdid and d.dbid=a.ammeterid_fk  and a.ammeterdegreeid=e.ammeterdegreeid_fk ");
		sql.append(" and z.id='"+zdid+"' and d.dbid<>'"+dbid+"' and d.dbyt='dbyt01'");
		System.out.println("��ѯվ��ĵ����󳭱�ʱ�� ,�������"+sql.toString());
		String thisdatetime = "";
		ResultSet rs = null;

		try {
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				thisdatetime = rs.getString(1);
			}
			if(rs != null){
				rs.close();
			}
			if(db != null){
				db.close();
			}
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}
		return thisdatetime;
	}

	public synchronized String validateName(String dbid, String id) {
		String msg = "��ϲ�㣬�˵��ID���ظ���";
		String sql = "";
		if (id.equals("0")) {
			sql = "SELECT id FROM dianbiao WHERE dbid='" + dbid + "'";
		} else {
			sql = "SELECT id FROM dianbiao WHERE dbid='" + dbid + "' and id!="
					+ id;
		}

		DataBase db = new DataBase();
		try {
			db.connectDb();

			ResultSet rs = db.queryAll(sql);
			while (rs.next()) {
				return "�˵��ID�Ѿ����ڣ�����ѡ��һ����";
			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	public synchronized String vdbid(String dbid) {
		// birthday = birthday.length()>0?birthday:null;
		String msg = "��֤���ID�ظ�ʧ�ܣ������Ի������Ա��ϵ��";

		StringBuffer sql = new StringBuffer();
		sql.append("select id from dianbiao where dbid='" + dbid.trim() + "'");

		System.out.println(sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();

			ResultSet rs = db.queryAll(sql.toString());
			if (rs.next()) {
				msg = "���ID�ظ�";
			} else {
				msg = "ok";
			}

		} catch (DbException de) {

			de.printStackTrace();
		} catch (SQLException de) {

			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	public synchronized String vdbid(String dbid, String id) {
		// birthday = birthday.length()>0?birthday:null;
		String msg = "��֤���ID�ظ�ʧ�ܣ������Ի������Ա��ϵ��";

		StringBuffer sql = new StringBuffer();
		sql.append("select id from dianbiao where dbid='" + dbid.trim()
				+ "' and id!=" + id);

		System.out.println(sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();

			ResultSet rs = db.queryAll(sql.toString());
			if (rs.next()) {
				msg = "���ID�ظ�";
			} else {
				msg = "ok";
			}

		} catch (DbException de) {

			de.printStackTrace();
		} catch (SQLException de) {

			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	
	public synchronized ArrayList getDianbiao() {
		

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;

		try {
			db.connectDb();

			sql.append("select d.id ID,d.dbname DBNAME from DIANBIAO d");

			System.out.println("�������sql��"+sql);
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
    
    public synchronized String getDianbiaoByDbbm(String dbbm,String id) {
		String strs = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			
			if(id.equals("0")){
					s2.append("select d.id   from dianbiao d where d.wlbm='"+dbbm+"'");
					rs=db.queryAll(s2.toString());
		            Query query = new Query();
		            list = query.query2(rs);
		            rs.close();
		            
		            if(list!=null){
		            	for(int i=0;i<list.size();i++){
		            		 strs = (String) list.get(i);
		            	  }
		              }
			}else{
				s2.append("select d.id   from dianbiao d where d.wlbm='"+dbbm+"'");
				rs=db.queryAll(s2.toString());
	            Query query = new Query();
	            list = query.query2(rs);
	            rs.close();
	            
	            if(list!=null){
	            	for(int i=0;i<list.size();i++){
	            		String idStr = (String) list.get(i);
	            		if(idStr.equals(id)){
	            			strs = "";
	            		}else{
	            			 strs = (String) list.get(i);
	            		}
	            		
	            	  }
	              }
			}
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return strs;
	}
    
    
    public synchronized String getZDXian(String dbid) {
   	 DataBase db = new DataBase();
   	 String nextStep = "";
        ResultSet rs = null;
        try {
       	 String sql="select z.xian from dianbiao d,zhandian z where z.id=d.zdid and d.id="+dbid+"";
       	 System.out.println("sql==="+sql);
       	 rs = db.queryAll(sql);
            while (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return nextStep;
    }
    
    
    public synchronized String getZDShi(String zdid) {
      	 DataBase db = new DataBase();
      	 String nextStep = "";
           ResultSet rs = null;
           try {
          	 String sql="select z.shi from zhandian z where z.id='"+zdid+"'";
          	 System.out.println("sql==="+sql);
          	 rs = db.queryAll(sql);
               while (rs.next()) {
                   return rs.getString(1);
               }
               rs.close();
           } catch (DbException de) {
               de.printStackTrace();
           } catch (SQLException se) {
               se.printStackTrace();
           }
           finally {
               try {
                   db.close();
               } catch (DbException de) {
                   de.printStackTrace();
               }
           }

           return nextStep;
       }

	/**
	 * ������������Ϣ
	 * @param dataList
	 */
	public synchronized void updateBatch(ArrayList<Map<String, Object>> dataList){
		DataBase db = new DataBase();
 		try {
 			ArrayList<String> sqlList = getUpdateBatchSql(dataList);	//����2
			db.connectDb();
			db.updateBatch(sqlList.toArray(new String[sqlList.size()])); //����4
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * У�����ظ�����
	 * @param dbcode
	 * @return
	 */
	public static boolean validateRepeat(String dbcode){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from dianbiao where (wlbm='"+dbcode+"' or dbbm='"+dbcode+"')");
		DataBase db = new DataBase();
		ResultSet rs = null;
		boolean isRepeat = Boolean.FALSE;
		int cnt = 0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		if(cnt>0){
			isRepeat = true;
		}
		return isRepeat;
	}
	/**
	 * ���ݾ�վ��Ŷ�̬�����������   - ��վ���� + ���1��2
	 * @param jzID
	 * @return0.0
	 */
	public int updateBatchName(){
		DataBase db = new DataBase();
		int cnt = 0;
		try {
			db.connectDb();
			ArrayList<String> sqlList = getUpdateBatchNameSql(); 
			db.updateBatch(sqlList.toArray(new String[sqlList.size()]));
			cnt++;
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	/**
	 * У���վID�Ƿ����
	 * @param dbcode
	 * @return
	 */
	public static boolean ljidexistence(String ljid){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) FROM ZHANDIAN WHERE STATION_NO  = '"+ljid+"'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		boolean isRepeat = Boolean.FALSE;
		int cnt = 0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		if(cnt == 0){
			isRepeat = true;
		}
		return isRepeat;
	}

	/**
	 * ��������������   Step 1
	 * �����������SQL
	 * @param dataList
	 */
	public synchronized ArrayList<String> getUpdateBatchSql(ArrayList<Map<String, Object>> dataList){
		//����3
		
		StringBuffer sql = new StringBuffer();
		
		ArrayList<String> sqlList = new ArrayList<String>();
		
		String zdid= "0"; 			
		String dbname= ""; 
		String cssysj= ""; 
		String beilv= ""; 
		String csds= ""; 
		String dfzflx= ""; 
		String dbbm= ""; 
		String maxds= ""; 
		String dwjslx= ""; 
		String isglf= ""; 
		String glbm= "";
		String zrr= ""; 
		String bzr= "";
		String ydsx= ""; 
		String ydlx= ""; 
		String jffs= ""; 
		String jfzq= ""; 
		String jldw= ""; 
		String yhbh= "";
		String dbqyzt= "";
		String bgje= ""; 
		String mtllhd= ""; 
		String danjia= ""; 
		String gysmc= "";
		String gysbm= ""; 
		String skfmc= ""; 
		String yhzh= ""; 
		String ssyh= ""; 
		String khyh= ""; 
		String zhsss= ""; 
		String zhssshi= ""; 
		String memo= "";
		String fplx= "";
		String zzsl= ""; 
		String dbyt= ""; 
		String dbch= ""; 
		String dbscbs= ""; 
		String is_cont= ""; 
		String cont_type= "";
		String zndb= ""; 
		String isdblx= ""; 
		String cbzx= ""; 
		String cbzxbm= ""; 
		String production_prop= ""; 
		String bur_stand_propertion= ""; 
		String cont_id= ""; 
		String electric_supply_way= ""; 
		String total_electricity= "";
		String zhangqi= ""; String zhz= ""; String gszt="";
		String dbtype = "0";
		String LJID = "";
		
		//�ֹ�˾
		
		String fgs = "" ; 	
		
		//���طֹ�˾
		
		String qxfgs = "" ;			
		
		//���״̬
		
		String shzt = "2";			
		
		//�Ƿ�ɾ����־��Ĭ��Ψ1δɾ����0Ϊɾ����
			
		String DELSIGN = "1";		
			
		//�������Ƿ�������� 1:���� 2��������
			
		String SSF = "2";	
			
		//¼����Ա
			
		String ENTRYPENSONNEL = "admins";
			
		//¼��ʱ��
		
		//Date d = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ENTRYTIMEOLD = "5/5/2018";
		
		//Ĭ��Ϊ��������� 0:���� 1��¼��
			
		String BZW = "0";
			
		for(Map<String, Object> map : dataList){
			sql.setLength(0);
			//�ֵ����� 
			dwjslx = String.valueOf(map.get("dwjslx")); //����������� 
			dbyt = String.valueOf(map.get("dbyt")); //�����;
			ydsx = String.valueOf(map.get("ydsx")); //�õ�����
			ydlx = String.valueOf(map.get("ydlx")); //�õ�����
			dfzflx = String.valueOf(map.get("fkfs")); //�ɷѷ�ʽ  dfzflx 
			jfzq = String.valueOf(map.get("fkzq")); //�ɷ����� jfzq
			jffs = String.valueOf(map.get("jffs")); //�Ʒѷ�ʽ
			jldw = String.valueOf(map.get("jldw")); //������λ ���ȣ�
			dbqyzt = String.valueOf(map.get("dbzt"));//���״̬ dbqyzt
			fplx = String.valueOf(map.get("pjlx"));//��Ʊ���� fplx
			is_cont = String.valueOf(map.get("is_cont"));//�Ƿ��к�ͬ
			cont_type = String.valueOf(map.get("cont_type_dict"));//��ͬ���� cont_type
			electric_supply_way = String.valueOf(map.get("gdfs"));//���緽ʽ electric_supply_way
			isdblx = String.valueOf(map.get("isdblx_dict"));//������� isdblx
			zndb = String.valueOf(map.get("shifou"));//�Ƿ����ܵ�� zndb
			isglf = String.valueOf(map.get("isglf"));//�Ƿ��й����
			shzt = String.valueOf(map.get("shzt"));//������״̬
			
			//����
			if(map.get("beilv") !=null && !"".equals(map.get("beilv").toString().trim())){
				beilv = Double.valueOf(map.get("beilv").toString())+"";
			}
			//����ʱ����
			if(map.get("csds") !=null && !"".equals(map.get("csds").toString().trim())){
				csds = (Double.valueOf(map.get("csds").toString())).intValue()+"";
			}
			dbbm = String.valueOf(map.get("dbbm"));
			if(map.get("maxds") !=null && !"".equals(map.get("maxds").toString().trim())){
				maxds = (Double.valueOf(map.get("maxds").toString())).intValue()+"";
			}
			glbm = String.valueOf(map.get("glbm"));
			zrr = String.valueOf(map.get("zrr"));
			bzr = String.valueOf(map.get("bzr"));
			yhbh = String.valueOf(map.get("yhbh"));
			if(map.get("bgje") !=null && !"".equals(map.get("bgje").toString().trim())){
				bgje = Double.valueOf(map.get("bgje").toString())+"";
			}
			if(map.get("danjia") !=null && !"".equals(map.get("danjia").toString().trim())){
				danjia = Double.valueOf(map.get("danjia").toString())+"";
			}
			gysmc = String.valueOf(map.get("gysmc"));
			gysbm = String.valueOf(map.get("gysbm"));
			skfmc = String.valueOf(map.get("skfmc"));
			yhzh = String.valueOf(map.get("yhzh"));
			ssyh = String.valueOf(map.get("ssyh"));
			khyh = String.valueOf(map.get("khyh"));
			zhsss = String.valueOf(map.get("zhsss"));
			zhssshi = String.valueOf(map.get("zhssshi"));
			memo = String.valueOf(map.get("memo"));
			
			dbname = String.valueOf(map.get("dbname"));	//�������

			if(map.get("zzsl") !=null && !"".equals(map.get("zzsl").toString().trim())){//˰��
				zzsl = Double.parseDouble(map.get("zzsl").toString())+"";
			}
			cbzx = String.valueOf(map.get("cbzx"));
			cbzxbm = String.valueOf(map.get("cbzxbm"));
			if(map.get("production_prop") !=null && !"".equals(map.get("production_prop").toString().trim())){
				production_prop = ((Double)map.get("production_prop")).doubleValue()+"";;
			}
			cont_id = String.valueOf(map.get("cont_id"));
			total_electricity = String.valueOf(map.get("total_electricity"));
			if(map.get("ljid") !=null){
				LJID = (Double.valueOf(map.get("ljid").toString())).intValue()+"";
			}
			fgs = String.valueOf(map.get("fgs"));
			qxfgs = String.valueOf(map.get("qxfgs"));
			
			//���Զ��滻�ֵ�����ݺ��ֶ��滻û�ж�Ӧ���ֵ�����
			
			if(ydsx.equals("��������")){
				ydsx = "ydsx03";				//����ֻ������õ����ԣ�
			}
			if(ydsx.equals("�ƶ�����վ")){
				ydsx = "ydsx04";				//���߾ַ����õ����ԣ�
			}
			if(ydsx.equals("�������")){
				ydsx = "ydsx05";				//��������õ����ԣ�
			}
			if(jldw.equals("��")){
				jldw = "jldw01";				//�ȣ�������λ��
			}
			if(fplx.equals("������ҵ�վ�")){
				fplx = "pjlx02";				//��ֵ˰��ͨ��Ʊ��ͨ�÷�Ʊ��Ʊ�����ͣ�
			}
			if(fplx.equals("�վ�")){
				fplx = "pjlx08";				//��ͨ�վݣ�Ʊ�����ͣ�
			}
			if(fplx.equals("����")){
				fplx = "pjlx08";				//��ͨ�վݣ�Ʊ�����ͣ�
			}
			if(fplx.equals("�޷�Ʊ")){
				fplx = "pjlx08";				//��ͨ�վݣ�Ʊ�����ͣ�
			}
			//����ĵ�����״̬ǿ��Ϊ��ͨ�� ��ֵΪ2
			shzt = "2";
			//ռ��վ����û��������Ĭ��Ϊ1
			if(bur_stand_propertion == "" || "".equals(bur_stand_propertion)){
				bur_stand_propertion = "1";
			}
			
			sql.append("INSERT INTO DIANBIAO(ZDID,DBNAME,BEILV,CSDS,DFZFLX,DBID,DBBM,MAXDS,DWJSLX,ISGLF," +
					"GLBM,ZRR,BZR,YDSX,YDLX,JFFS,JFZQ,JLDW,YHBH,DBQYZT,BGJE,MTLLHD,DANJIA,GYSMC,GYSBM,SKFMC,YHZH," +
					"SSYH,KHYH,ZHSSS,ZHSSSHI,MEMO,FPLX,ZZSL,DBYT,DBCH,DBSCBS," +
					" is_cont,cont_type,zndb,isdblx,cbzx,cbzxbm,production_prop," +
					" bur_stand_propertion,cont_id,electric_supply_way,total_electricity,zq,zhz,gszt,LJID,dbtype,fgs," +
					"qxfgs,shzt,DELSIGN,SSF,ENTRYPENSONNEL,ENTRYTIMEOLD,BZW) " +
					"VALUES ("+zdid+",'"+dbname+"',"+beilv+","+csds+",'"+dfzflx+"','"+zdid+"','"+dbbm+"','"+maxds+"'" +
					",'"+dwjslx+"','"+isglf+"','"+glbm+"','"+zrr+"','"+bzr+"','"+ydsx+"','"+ydlx+"','"+jffs+"','"+jfzq+"','"+jldw+"','"+yhbh+"'" +
					",'"+dbqyzt+"','"+bgje+"','"+mtllhd+"','"+danjia+"','"+gysmc+"','"+gysbm+"','"+skfmc+"','"+yhzh+"','"+ssyh+"','"+khyh+"'," +
					"'"+zhsss+"','"+zhssshi+"','"+memo+"','"+fplx+"','"+zzsl+"','"+dbyt+"','"+dbch+"','"+dbscbs+"','"+
					is_cont+"','"+cont_type+"','"+zndb+"','"+isdblx+"','"+cbzx+"','"+cbzxbm+"','"+production_prop+"','"+bur_stand_propertion+"','"+
					cont_id+"','"+electric_supply_way+"','"+total_electricity+"','"+zhangqi+"','"+zhz+"','"+gszt+"','"+LJID+"','"+dbtype+"','"+fgs+"','"+
					qxfgs+"','"+shzt+"','"+DELSIGN+"','"+SSF+"','"+ENTRYPENSONNEL+"','"+ENTRYTIMEOLD+"','"+BZW+"')");

		   sqlList.add(sql.toString());
		
		}
		System.out.println("�������������ݣ�"+ sqlList);
		return sqlList;
	}

	/**
	 * ��������������   Step 2
	 * ���ݾ�վ�޸ĵ������ SQL
	 * @param dataList
	 */
	public ArrayList<String> getUpdateBatchNameSql(){
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		ArrayList<String> sqlList = new ArrayList<String>();
		try {
			db.connectDb();
			/*rs = db.queryAll("select d.ljid, (select z.id from zhandian z where z.station_no = d.ljid) jzid, " +
					"(select nvl(z.jzname, z.userstationname) from zhandian z where z.station_no = d.ljid) jzname, " +
					"(select code from indexs where lower(type)='gszt' and code like substr(d.cbzxbm,0,1)|| '%') gszt, " +
					"(select count(*) from dianbiao where ljid=d.ljid ) cnt from dianbiao d where nvl(d.ljid, '0') != '0' order by d.ljid");
			*/
			//�����վIDû�ж�Ӧ�ľ�վ����where����nvl(z.id, '0')
			rs = db.queryAll("select d.id, z.id jzid, nvl(z.jzname, z.userstationname) jzname, " +
					"(select code from indexs where lower(type)='gszt' and nvl(d.cbzxbm,'0') <>'0' and code like substr(d.cbzxbm,0,1)|| '%') gszt, " +
					"row_number() over(partition by d.ljid order by d.id) cn " +
					"from dianbiao d left join zhandian z on d.ljid = z.station_no " +
					"where nvl(d.ljid, '0') <>'0' and nvl(z.id, '0') <>'0' order by d.ljid");
			while(rs.next()){
				String cnt = rs.getString("cn");
				String dbid = rs.getString("id");
				String jzid = rs.getString("jzid");
				String jzname = rs.getString("jzname");
				String gszt = rs.getString("gszt");
				int number = Integer.parseInt(cnt) ;
				sql.setLength(0);
				//sql.append("update dianbiao d set d.dbname = '"+jzname+"���" +(number)+"', d.zdid='"+jzid+"', d.gszt='"+gszt+"' where d.id='"+dbid+"'");
				
				sql.append("update dianbiao d set d.zdid='"+jzid+"', d.gszt='"+gszt+"' where d.id='"+dbid+"'");
				sqlList.add(sql.toString());
			}
			
			/*Query query = new Query();
			list = query.query(rs);
			int count = ((Integer)list.get(0)).intValue();
			for(int i=count; i< list.size()-1; i+=count){
				String ljid = String.valueOf(list.get(i+ list.indexOf("LJID")));//������վID
				String jzid = String.valueOf(list.get(i+ list.indexOf("JZID")));
				String jzname = String.valueOf(list.get(i+ list.indexOf("JZNAME")));
				String gszt = String.valueOf(list.get(i+ list.indexOf("GSZT")));
				String cnt = String.valueOf(list.get(i+ list.indexOf("CNT")));
				int number = 1;
				for (int j = 1; j <= Integer.parseInt(cnt); j++) {
					sql.setLength(0);
					sql.append("update dianbiao d set d.dbname = '"+jzname+"���" +(number)+"', d.zdid='"+jzid+"' where d.ljid='"+ljid+"'");
					sqlList.add(sql.toString());
					number++;
				}
			}*/
			System.out.println("�����޸ĵ�����ƣ�"+sqlList);
			//String[] sqlArr = sqlList.toArray(new String[sqlList.size()]);
			//db.updateBatch(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return sqlList;
	}

	/**
	 * ��������������   Step 3
	 * 
	 * @param dataList
	 * @return
	 */
	public int importBatch(ArrayList dataList){
		DataBase db = new DataBase();
		ArrayList<String> allSql = new ArrayList<String>();
		ArrayList<String> sql1 = getUpdateBatchSql(dataList);
		ArrayList<String> sql2 = getUpdateBatchNameSql();
		allSql.addAll(sql1);
		allSql.addAll(sql2);
		int cnt = 0;
		try {
			db.connectDb();
			db.updateBatch(allSql.toArray(new String[allSql.size()]));
			cnt++;
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	/**
	 * ���ݾ�վ�������ɵ�����
	 * 
	 * @param zdid
	 * @return
	 */
	public String getCustomDbbm(String zdid) {
		Code code = new Code();
		String prefix = "NDB" + getZDShi(zdid);
		return code.getEntityCode(prefix, code.getMeterNextVal(), 14);
	}

	/**
	 * ��ȡ������վID
	 * @param zdid
	 * @return
	 */
	public String getZDStationNo(String zdid) {
		String sql = "select station_no from zhandian where id='"+zdid+"'";
		System.out.println("zhangdian"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ��ѯ���е����벢���뼯��
	 * @param fgs
	 * @return
	 */
	public ArrayList<String> getDbbmList() {
	
		ArrayList<String> al = null;
		String sql = "select d.dbbm from dianbiao d ";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<String>();
			while(rs.next()){
				String dbbm = rs.getString("dbbm");
				al.add(dbbm);
			}
			//
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return al;
	}
	//��ѯ���е����벢���뼯��
		public ArrayList<String> SelectLJID() {
			ArrayList<String> al = null;
			String sql = "SELECT STATION_NO FROM ZHANDIAN";
			DataBase db = new DataBase();
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql);
				al = new ArrayList<String>();
				while(rs.next()){
					String STATION_NO = rs.getString("STATION_NO");
					al.add(STATION_NO);
				}
			} catch (Exception de) {
				de.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
			return al;
		}
}
