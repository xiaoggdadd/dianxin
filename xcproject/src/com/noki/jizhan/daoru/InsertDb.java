package com.noki.jizhan.daoru;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.DianbiaoBean;
import com.ptac.app.electricmanageUtil.Format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import jxl.DateCell;
import java.util.Date;

/**
 * <p>
 * Title: 电表导入
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
public class InsertDb {
	public InsertDb() {
		try {
			db = new DataBase();
			db.connectDb();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	StringBuffer sql = new StringBuffer();
	StringBuffer sql2 = new StringBuffer();
	StringBuffer sql3 = new StringBuffer();
	Vector wrongContent = new Vector();
	public DataBase db;

	public void closeDb() {
		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 站点管理---电表信息导入
	public synchronized Vector getWrong(List<DianbiaoBean> dianbiaoList,
			List<Vector> vectors, CountForm cform, String accountname) {
		// int m = 0, i = 0, cg = 0, bcg = 0, zg = 0;

		int cg = 0, bcg = 0, zg = 0;
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'), 'yyyy-mm-dd hh24:MI:SS')";
		SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zg = dianbiaoList.size();
		// List<Vector> vectors = new ArrayList<Vector>();
		// Iterator it = content.iterator();
		// if (it.hasNext()) {
		// Vector row = (Vector) it.next();
		// vectors.add(row);
		// }
		System.out.println("vectors size:" + vectors.size());
		for (int i = 0; i < dianbiaoList.size(); i++) {
			DianbiaoBean dianbiao = dianbiaoList.get(i);
			String zdid = dianbiao.getZdid();
			String dbbm = dianbiao.getDBBM();
			String dbbl = dianbiao.getBEILV();
			String dbdj = dianbiao.getDANJIA();
			String csds = dianbiao.getCSDS();
			Random rnd = new Random();
			String dbid = (String) (rnd.nextInt(999999999) + "");

			if (dbbm == null || dbbm.length() == 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表编码不能为空！");
				wrongContent.add(row);
				bcg++;
				continue;
			}

			if (dbbl == null || dbbl.length() == 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表倍率不能为空！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else

			if (!Format.isNumber(dbbl)) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表倍率必须为数字！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else if (Format.str_d(dbbl.trim()) <= 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表倍率必须大于0！");
				wrongContent.add(row);
				bcg++;
				continue;
			}

			if (dbdj == null || dbdj.length() == 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表单价不能为空！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else

			if (!Format.isNumber(dbdj)) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表单价必须为数字！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else if (Format.str_d(dbdj.trim()) <= 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！电表单价必须大于0！");
				wrongContent.add(row);
				bcg++;
				continue;
			}

			if (csds == null || csds.length() == 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！初始读数不能为空！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else

			if (!Format.isNumber(csds)) {
				Vector row = vectors.get(i);
				row.add("导入失败！初始读数必须为数字！");
				wrongContent.add(row);
				bcg++;
				continue;
			} else if (Format.str_d(csds.trim()) < 0) {
				Vector row = vectors.get(i);
				row.add("导入失败！初始读数必须大于0！");
				wrongContent.add(row);
				bcg++;
				continue;
			}

			StringBuffer sql = new StringBuffer();
			sql
					.append("INSERT INTO DIANBIAO(zdid,dbid,DBBM,BEILV,DANJIA,CSDS,DBYT,entrypensonnel,entrytime,dbqyzt,CSLRR,CSLRSJ) values(");
			sql.append("'" + zdid + "','" + dbid + "','" + dbbm + "','" + dbbl
					+ "','" + dbdj + "','" + csds + "','dbyt01','"
					+ accountname + "'," + s + ",'1','" + accountname + "',"
					+ s + "");
			sql.append(")");
			System.out.println(sql.toString());
			try {
				db = new DataBase();
				db.connectDb();
				db.setAutoCommit(false);
				db.update(sql.toString());
				db.commit();
				cg++;
			} catch (DbException e) {
				bcg++;
				e.printStackTrace();
			} finally {
				try {
					db.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
		}

		// for (Iterator it = content.iterator(); it.hasNext(); ) {
		// String dbid="",zdid="",countbzw="",thistime="";
		// zg++;
		// String dbyt="",ydsb="",dfzflx="";
		// String danjia = "";
		// m++;
		// i = 0;
		// Vector row = (Vector) it.next();
		// System.out.println("updianbiao row==" + row);
		// Iterator iter = row.iterator();
		// sql.setLength(0);
		// sql.append("INSERT INTO DIANBIAO(zdid,dbid,DBBM,BEILV,DANJIA,CSDS,DBYT,entrypensonnel,entrytime,dbqyzt,CSLRR,CSLRSJ) values(");
		// String con = "";
		// try {
		// boolean bin = false;
		// while (iter.hasNext()) {
		// i++;
		// Object value = iter.next();
		// if (value == null) {
		// value = "";
		// }
		// con = value.toString().trim();
		// // System.out.println("i=="+i+";con=="+con);
		// if (i == 4) {
		// con = this.getZdid(db, con);
		// zdid=value.toString().trim();
		// countbzw = this.getCountBzw(db, zdid);
		// thistime = this.getThisTime(db, zdid);
		// DecimalFormat price=new DecimalFormat("0");
		//                       
		//                        
		//                        
		// // System.out.println("4"+zdid);
		// sql.append("'" + con + "'");
		//                    
		// ArrayList<String> listt=this.getShi(zdid);
		// String shi=listt.get(0).toString();
		// // danjia = listt.get(1).toString();
		// System.out.println("单价："+danjia);
		//                      
		//                      
		// Random rnd = new Random();
		// dbid = (String) (rnd.nextInt(999999999)+"");
		//                 	
		//                        
		//                     
		// sql.append(",'" + dbid + "'");
		// }
		// /*if (i == 5) {
		// if (this.getZbid(db, con)) {
		// bcg++;
		// bin=true;
		// row.add("电表id重复");
		// wrongContent.add(row);
		// continue;
		// }
		// if (con == null || con.length() == 0) {
		// bcg++;
		// bin=true;
		// row.add("电表id不能为空");
		// wrongContent.add(row);
		// continue;
		// }
		// sql.append(",'" + con + "'");
		// }*/
		// String tempstr="";
		// if (i == 5) {
		// if (con == null || con.length() == 0) {
		// row.add("导入失败！电表编码不能为空！");
		// wrongContent.add(row);
		// bcg++;
		// }
		// sql.append(",'" + con + "'");
		// }else if (i == 6) {
		// if (con == null || con.length() == 0) {
		// row.add("导入失败！电表倍率不能为空！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(!Format.isNumber(con.trim())){
		// row.add("导入失败！电表倍率必须为数字！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(Format.str_d(con.trim())<=0){
		// row.add("导入失败！电表倍率必须大于0！");
		// wrongContent.add(row);
		// bcg++;
		// }
		// sql.append(",'" + con + "'");
		// } else if (i == 7) {
		// if (con == null || con.length() == 0) {
		// row.add("导入失败！电表单价不能为空！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(!Format.isNumber(con.trim())){
		// row.add("导入失败！单价必须为数字！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(Format.str_d(con.trim())<=0){
		// row.add("导入失败！单价必须大于0！");
		// wrongContent.add(row);
		// bcg++;
		// }
		// sql.append(",'" + con + "'");
		// } else if (i == 8) {
		// if (con == null || con.length() == 0) {
		// row.add("导入失败！初始读数不能为空！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(!Format.isNumber(con.trim())){
		// row.add("导入失败！初始读数必须为数字！");
		// wrongContent.add(row);
		// bcg++;
		// }else if(Format.str_d(con.trim())<=0){
		// row.add("导入失败！初始读数必须大于0！");
		// wrongContent.add(row);
		// bcg++;
		// }
		// sql.append(",'" + con + "'");
		// }
		//
		// }
		// sql.append(",'dbyt01','"+accountname+"',"+s+"");
		// sql.append(",'1','1','"+accountname+"',"+s+")");
		// // String
		// jcdsql="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,XJID,XJBILI)values((select id from dianbiao where dbid='"+dbid+"'),"+" to_char((select id from dianbiao where dbid = '"+dbid+"')) || '01"+"','zylx01',100,'"+dbid+"'||'01','100')";
		// System.out.println(sql.toString());
		// int tt = 0;
		// if(!bin){
		// db = new DataBase();
		// db.setAutoCommit(false);
		//                	
		// if("0".equals(countbzw) && "dbyt01".equals(dbyt)){}
		//                	
		// tt = db.update(sql.toString());
		//                    
		//                    
		// // if(this.getPropertyCode(db, con, "DBYT").equals("dbyt01")){
		// if(dbyt.equals("dbyt01")){
		// // System.out.println("jicedian--"+jcdsql.toString());
		// // db.update(jcdsql.toString());
		// }
		// // db.commit();
		// // db.setAutoCommit(true);
		// String
		// sql3="update dianbiao set dbid=(select id from dianbiao where dbid='"+dbid+"') where id=(select id from dianbiao where dbid='"+dbid+"')";
		// System.out.println("sql3"+sql3);
		// // DataBase db = new DataBase();
		// // ArrayList<String> listzd=new ArrayList<String>() ;
		// rs=null;
		// try {
		// // db.setAutoCommit(false);
		// rs = db.queryAll(sql3);
		// // db.commit();
		// //db.setAutoCommit(true);
		// } catch (Exception e) {
		//                	 
		// e.printStackTrace();
		// }
		// // closeDb();///没有时不能及时关闭连接 会报游标超限错误
		// if(rs != null){
		// rs.close();
		// }
		// if(db != null){
		// db.close();
		// }
		//                
		//                   
		// }
		// if (tt != 1) {
		// // bcg++;
		// row.add("导入失败行！");
		// wrongContent.add(row);
		// } else {
		// cg++;
		// }
		// db.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// //row.add(e.toString());
		// //wrongContent.add(row);
		// }finally {
		// if (rs != null) {
		// try {
		// rs.close();
		// } catch (SQLException se) {
		// se.printStackTrace();
		// }
		// }
		// try {
		// db.close();
		// } catch (DbException de) {
		// de.printStackTrace();
		// }
		// }
		//
		// }
		cform.setCg(cg);
		cform.setBcg(bcg);
		cform.setZg(zg);

		System.out.println(wrongContent);
		return wrongContent;
	}

	private ResultSet rs = null;

	private String getZdid(DataBase db, String name) {
		String code = "";
		try {
			rs = db.queryAll("select id from zhandian where zdcode='"
					+ name.trim() + "'");
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (db != null) {
					db.close();
				}
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return code;
	}

	private String getCountBzw(DataBase db, String name) {
		String countBzw = "";
		System.out.println("name: " + name);
		try {
			rs = db.queryAll("select COUNTBZW from zhandian where id='"
					+ name.trim() + "'");
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (db != null) {
					db.close();
				}
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return countBzw;
	}

	// 获取站点最大抄表时间
	private String getThisTime(DataBase db, String zdid) throws DbException,
			SQLException {

		String thistime = "";
		rs = db
				.queryAll(" select to_char(max(a.thisdatetime),'yyyy-mm-dd') from zhandian z ,dianbiao d ,ammeterdegrees a ,electricfees e "
						+ " where z.id = d.zdid and d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk and z.id = '"
						+ zdid
						+ "' and d.dbyt='dbyt01' and e.CITYZRAUDITSTATUS = '1' ");

		if (rs.next()) {
			thistime = rs.getString(1) != null ? rs.getString(1) : "";
			if (thistime == "0" || thistime.equals("0")
					|| thistime.equals("null") || thistime.equals(" ")) {
				thistime = "";
			}
		}
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
		return thistime;
	}

	@SuppressWarnings("unused")
	private boolean getZbid(DataBase db, String dbid) throws Exception,
			SQLException {
		String code = "";

		rs = db.queryAll("select id from dianbiao where dbid='" + dbid + "'");
		db.close();
		if (rs.next()) {
			closeDb();
			return true;
		} else {
			closeDb();
			return false;
		}

	}

	private ArrayList<String> getShi(String con) throws Exception, SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select shi,dianfei from zhandian where zdcode='" + con
				+ "'");
		System.out.println(sql.toString());
		ArrayList<String> list = new ArrayList<String>();
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			try {
				while (rs.next()) {

					list.add(rs.getString(1));
					list.add(rs.getString(2));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeDb();
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			rs.close();
			closeDb();
		}
		return list;

	}

	/*
	 * private String getID(DataBase db, String zdcode) throws Exception,
	 * SQLException {
	 * 
	 * rs = db.queryAll("select id from zhandian where zdcode='" + zdcode +
	 * "'"); if (rs.next()) { return rs.getString(1); } else { return ""; } }
	 */

	private String getPropertyCode(DataBase db, String name, String typename)
			throws Exception, SQLException {
		String code = "";
		System.out.println("select code from indexs where name='" + name
				+ "' and type='" + typename + "'");
		rs = db.queryAll("select code from indexs where name='" + name
				+ "' and type='" + typename + "'");
		if (rs.next()) {
			return rs.getString(1);
		} else {
			code = "0";
		}
		// closeDb();
		if (rs != null) {
			rs.close();
		}
		if (db != null) {
			db.close();
		}
		return code;
	}

	private String getNewFormateDate(String olddate) {
		StringBuffer newdate = new StringBuffer();
		if (olddate.indexOf("-") > 0) {
			newdate.append(olddate);
		} else {
			if (olddate.indexOf("/") >= 3) {
				/*
				 * newdate.append(olddate.substring(0, 4) + "-");
				 * newdate.append(olddate.substring(olddate.indexOf("/") + 1,
				 * olddate.lastIndexOf("/")) + "-");
				 * newdate.append(olddate.lastIndexOf("/"));
				 */
				newdate.append(olddate.replaceAll("/", "-"));
				// System.out.println(newdate.toString());
			} else {

				newdate.append(olddate.lastIndexOf("/") + "-");

				newdate.append(olddate.substring(olddate.indexOf("/") + 1,
						olddate.lastIndexOf("/"))
						+ "-");
				newdate.append(olddate.substring(0, olddate.indexOf("/")));

			}
		}
		return newdate.toString();
	}

	public synchronized ArrayList<String> SelectDBID(String shi, String str) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select max(to_number(substr(b.dbid,4))) as dbid from dianbiao b,zhandian z  where shi='"
						+ shi
						+ "' and b.zdid=z.id and b.dbyt<>'dbyt02' and dbid like '%"
						+ str + "%'");
		System.out.println(sql.toString());
		ArrayList<String> list = new ArrayList<String>();
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
			closeDb();
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}

		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeDb();
		}
		return list;
	}

}
