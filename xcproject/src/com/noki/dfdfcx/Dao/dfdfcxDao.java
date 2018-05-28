package com.noki.dfdfcx.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.dfdfcx.bean.dfdfBean;
import com.noki.util.CTime;

public class dfdfcxDao {
	
	/***
	 * @author GT
	 * 获得 总的 多付电费查询
	 * */
	public synchronized ArrayList<dfdfBean> getDfdfChaxun(String whereStr) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
			
			sql = "select aa.*,(aa.jsdf-aa.chundf)cha from(select tt.shi,sum(tt.dl)jdl, tt.shiid,sum(tt.zsdl)zshdl,sum(tt.floatdegree)dltz,sum(tt.xsdl)xsdl,sum(tt.beilv)/count(tt.ammeterdegreeid)pjbl,sum(tt.dianfei)/count(tt.ammeterdegreeid)pjdj,sum(tt.jsdf)jsdf,sum(tt.chundf)chundf  from("
					+ " select (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zd.shi)shi,zd.shi shiid,(ddv.thisdegree -ddv.lastdegree) dl,  ddv.ammeterdegreeid, decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue +  ddv.floatdegree) * db.beilv,"
					+ "  '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue + ddv.floatdegree) * db.beilv, (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)) zsdl, ddv.floatdegree, decode(db.linelosstype,"
					+ "  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue) , '02xsbl',  ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue), (ddv.thisdegree - ddv.lastdegree) )xsdl, db.beilv, zd.dianfei,"
					+ "  decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue + ddv.floatdegree) * db.beilv*zd.dianfei, '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue + "
					+ "  ddv.floatdegree) * db.beilv*zd.dianfei,  (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)*zd.dianfei) jsdf, (ddv.thisdegree - ddv.lastdegree)*zd.dianfei chundf, db.linelosstype "
					+ "   from zhandian zd, dianbiao db, dianduview ddv where zd.id = db.zdid and db.dbid = ddv.ammeterid_fk and zd.qyzt = '1' and db.dbqyzt = '1' and ddv.feesbz = '1'"
					+ "   "+whereStr+")tt group by shi ,shiid)aa";
		
		System.out.println("多付电费11：" + sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				dfdfBean bean = new dfdfBean();
				bean.setJsydl(rs.getString("jdl"));
				bean.setShi(rs.getString("shi"));
				bean.setZshdl(rs.getString("zshdl"));
				bean.setTzdl(rs.getString("dltz"));
				bean.setXsdl(rs.getString("xsdl"));
				bean.setPjbl(rs.getString("pjbl"));
				bean.setPjdj(rs.getString("pjdj"));
				bean.setJsdf(rs.getString("jsdf"));
				bean.setChundf(rs.getString("chundf"));
				bean.setCha(rs.getString("cha")!=null?rs.getString("cha"):"0");
				bean.setShiid(rs.getString("shiid"));
				list.add(bean);
				}
//			Query query = new Query();
//			list = query.query(rs);
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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
	public synchronized ArrayList<dfdfBean> getDfdfChaxunxian(String whereStr) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
			
			sql = "select aa.*,(aa.jsdf-aa.chundf)cha from(select tt.shi,tt.shiid,tt.xian xianid,sum(tt.dl)jdl,sum(tt.zsdl)zshdl,sum(tt.floatdegree)dltz,sum(tt.xsdl)xsdl,sum(tt.beilv)/count(tt.ammeterdegreeid)pjbl,sum(tt.dianfei)/count(tt.ammeterdegreeid)pjdj,sum(tt.jsdf)jsdf,sum(tt.chundf)chundf  from("
					+ " select (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zd.xian)shi, zd.shi shiid,zd.xian,(ddv.thisdegree -ddv.lastdegree) dl,  ddv.ammeterdegreeid, decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue +  ddv.floatdegree) * db.beilv,"
					+ "  '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue + ddv.floatdegree) * db.beilv, (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)) zsdl, ddv.floatdegree, decode(db.linelosstype,"
					+ "  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue) , '02xsbl',  ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue), (ddv.thisdegree - ddv.lastdegree) )xsdl, db.beilv, zd.dianfei,"
					+ "  decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue + ddv.floatdegree) * db.beilv*zd.dianfei, '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue + "
					+ "  ddv.floatdegree) * db.beilv*zd.dianfei,  (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)*zd.dianfei) jsdf, (ddv.thisdegree - ddv.lastdegree)*zd.dianfei chundf, db.linelosstype "
					+ "   from zhandian zd, dianbiao db, dianduview ddv where zd.id = db.zdid and db.dbid = ddv.ammeterid_fk and zd.qyzt = '1' and db.dbqyzt = '1' and ddv.feesbz = '1'"
					+ "   "+whereStr+")tt group by tt.shiid,shi,xian )aa";
		
		System.out.println("多付电费222：" + sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				dfdfBean bean = new dfdfBean();
				bean.setJsydl(rs.getString("jdl"));
				bean.setShi(rs.getString("shi"));
				bean.setZshdl(rs.getString("zshdl"));
				bean.setTzdl(rs.getString("dltz"));
				bean.setXsdl(rs.getString("xsdl"));
				bean.setPjbl(rs.getString("pjbl"));
				bean.setPjdj(rs.getString("pjdj"));
				bean.setJsdf(rs.getString("jsdf"));
				bean.setChundf(rs.getString("chundf"));
				bean.setCha(rs.getString("cha"));
				bean.setXianid(rs.getString("xianid"));
				bean.setShiid(rs.getString("shiid"));
				list.add(bean);
				}
//			Query query = new Query();
//			list = query.query(rs);
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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
	
	
	
	/**
	 * @author GT
	 * 
	 * 来子站点
	 * */
	
	
	public synchronized ArrayList<dfdfBean> getDfdfChaxunZd(String whereStr) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
			
			sql = "select aa.*,(aa.jsdf-aa.chundf)cha from (select tt.jzname,sum(tt.dl)jdl,sum(tt.zsdl)zshdl,sum(tt.floatdegree)dltz,sum(tt.xsdl)xsdl,sum(tt.beilv)/count(tt.ammeterdegreeid)pjbl,sum(tt.dianfei)/count(tt.ammeterdegreeid)pjdj,sum(tt.jsdf)jsdf,sum(tt.chundf)chundf  from("
					+ "  select zd.jzname,(ddv.thisdegree -ddv.lastdegree) dl,  ddv.ammeterdegreeid, decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue +  ddv.floatdegree) * db.beilv,"
					+ "    '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue + ddv.floatdegree) * db.beilv, (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)) zsdl, ddv.floatdegree, decode(db.linelosstype,"
					+ "    '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue) , '02xsbl',  ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue), (ddv.thisdegree - ddv.lastdegree) )xsdl, db.beilv, zd.dianfei,"
					+ "    decode(db.linelosstype,  '01xstz', (ddv.thisdegree - ddv.lastdegree + db.linelossvalue + ddv.floatdegree) * db.beilv*zd.dianfei, '02xsbl', ((ddv.thisdegree - ddv.lastdegree) * db.linelossvalue +"
					+ "    ddv.floatdegree) * db.beilv*zd.dianfei,  (ddv.thisdegree - ddv.lastdegree+ddv.floatdegree)*zd.dianfei) jsdf, (ddv.thisdegree - ddv.lastdegree)*zd.dianfei chundf, db.linelosstype       "
					+ "     from zhandian zd, dianbiao db, dianduview ddv where zd.id = db.zdid and db.dbid = ddv.ammeterid_fk and zd.qyzt = '1' and db.dbqyzt = '1' and ddv.feesbz = '1'"
					+ "     "+whereStr+" )tt group by jzname)aa ";
		
		System.out.println("多付电费zd：" + sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				dfdfBean bean = new dfdfBean();
				bean.setJsydl(rs.getString("jdl"));
				bean.setJzname(rs.getString("jzname"));
				bean.setZshdl(rs.getString("zshdl"));
				bean.setTzdl(rs.getString("dltz"));
				bean.setXsdl(rs.getString("xsdl"));
				bean.setPjbl(rs.getString("pjbl"));
				bean.setPjdj(rs.getString("pjdj"));
				bean.setJsdf(rs.getString("jsdf"));
				bean.setChundf(rs.getString("chundf"));
				bean.setCha(rs.getString("cha"));
				list.add(bean);
				}
//			Query query = new Query();
//			list = query.query(rs);
			return list;
		}
		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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
        System.out.println(list);
		return list;
	}

}
