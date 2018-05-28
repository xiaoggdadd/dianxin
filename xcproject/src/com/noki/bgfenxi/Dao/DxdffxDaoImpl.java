package com.noki.bgfenxi.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.bgfenxi.Model.DxdffxBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class DxdffxDaoImpl {
	
	private static Log log = LogFactory.getLog(DxdffxDaoImpl.class.getName());
	
	public List<DxdffxBean> searchWydfft_dxdfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String _yysqrzt,String pc) {
		List<DxdffxBean> wydfftList = new ArrayList<DxdffxBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,A.DW,A.QX,A.ZDMC,A.ZDBM,A.GDFS,A.JSFS,A.DH,A.ZDLX,A.CB_QM,A.CB_ZM,A.CB_DLIU,A.TS,A.CY,A.CB_JFQSRQ,A.CB_JFZZRQ,A.CB_DLIANG,A.DANJIA,A.JFJE,A.JFRQ,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,");
		sql.append("B.sheng, rndiqu(B.sheng) SHENG_NAME,B.shi,rndiqu(B.shi) SHI_NAME,B.xian,rndiqu(B.xian) XIAN_NAME,");
		sql.append("B.gdfname");
		sql.append(" FROM TBL_TT_WY_DFFT_TDBG A,ZHANDIAN B,dianbiao C WHERE  A.ZDBM = B.JZCODE AND c.zdid = b.id  AND KH = '电信' ");
		if (xiaoqu!=null&&!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
		} else if (xian!=null&&!xian.equals("0")) {
			sql.append(" AND B.XIAN='" + xian + "'");
		}else if (shi!=null&&!shi.equals("0")) {
			sql.append(" AND B.SHI='" + shi + "'");
		} 
		else if (!sheng.equals("0")) {
			sql.append(" AND B.SHENG='" + sheng + "'");
		}

		if (_yysqrzt!=null&&!_yysqrzt.equals("0")) {
			sql.append(" and A.YYSZT='" + _yysqrzt + "'");
		}
		if (pc!=null&&!pc.equals("0")) {
			sql.append(" and A.YEARMONTH='" + pc + "'");
		}
		sql.append(" ORDER BY A.ZDMC DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("电信电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String id = rs.getString("ID");
					String dw = rs.getString("DW");
					String qx = rs.getString("QX");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ")==null?"":rs.getString("JFRQ");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC")==null?"":rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String jfqsrq = rs.getString("CB_JFQSRQ");
					String jfzzrq = rs.getString("CB_JFZZRQ");
					String jsfs = rs.getString("JSFS");
					String gdfs = rs.getString("GDFS");
					String danjia = rs.getString("DANJIA");
					String dliang = rs.getString("CB_DLIANG");
					String zzlx = rs.getString("zdlx");
					String ts = rs.getString("ts")==null?"":rs.getString("ts");
					String cy = rs.getString("cy");
					String qm = rs.getString("cb_qm");
					String zm = rs.getString("cb_zm");
					String dliu = rs.getString("cb_dliu")==null?"":rs.getString("cb_dliu");

					DxdffxBean wydfft = new DxdffxBean();
					wydfft.setRownum(rownum);
					wydfft.setGDFMC(gdfmc);
					wydfft.setDw(dw);
					wydfft.setQX(qx);
					wydfft.setID(id);
					wydfft.setJSFS(jsfs);
					wydfft.setGDFS(gdfs);
					wydfft.setDLIANG(dliang);
					wydfft.setDH(dh);
					wydfft.setJfqsrq(jfqsrq);
					wydfft.setJfzzrq(jfzzrq);
					wydfft.setBCDJ(danjia);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setKH(kh);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					wydfft.setTs(ts);
					wydfft.setCY(cy);
					wydfft.setDLIU(dliu);
					wydfft.setZzlx(zzlx);
					wydfftList.add(wydfft);
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
		}
		return wydfftList;
	}
	
	public List<DxdffxBean> searchdxdfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu,String zdmc,String zdbm) throws DbException, SQLException {
		List<DxdffxBean> wydfftList = new ArrayList<DxdffxBean>();
		StringBuffer sql1 = new StringBuffer();
		String quxian="";
		sql1.append("select * from d_area_grade where agcode='"+xian+"'");
		DataBase db1 = new DataBase();
		ResultSet rs1 = null;
		db1.connectDb();
		rs1=db1.queryAll(sql1.toString());
		while(rs1.next()){
			quxian=rs1.getString("agname");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select tt.*,rownum as rowno from(");
		sql.append("select max(bg.zdname),max(bg.dw),max(bg.qx),min(bg.jfqsrq),max(bg.jfzzrq),max(bg.zdbm),max(bg.gdfs),max(bg.jsfs),max(bg.zdlx),max(dx.xfje),max(dx.ftje),max(dx.ts),count(bg.id) ");
		sql.append("from dxbgfx@dblink_tdbg.regress.rdbms.dev.us.oracle.com bg, dianxinfenxi@dblink_tdbg.regress.rdbms.dev.us.oracle.com dx ");
		sql.append("where bg.zdbm = dx.zdcode");
		//sql.append("B.gdfname");
		//sql.append(" FROM TBL_TT_WY_DFFT_TDBG A,ZHANDIAN B,dianbiao C WHERE  A.ZDBM = B.JZCODE AND c.zdid = b.id  AND KH = '电信' ");
//		if (xiaoqu!=null&&!xiaoqu.equals("0")) {
//			sql.append("AND B.AGCODE = '" + xiaoqu + "'");
//		} 
//		else 
		if (xian!=null&&!xian.equals("0")) {
			sql.append(" AND BG.QX = '" + quxian + "'");
		}
//		else if (shi!=null&&!shi.equals("0")) {
//			sql.append(" AND B.AGCODE='" + shi + "'");
//		} else if (!sheng.equals("0")) {
//			sql.append(" AND B.AGCODE='" + sheng + "'");
//		}
		if(zdmc!=null&&!zdmc.equals("0")&&!zdmc.equals("")){
			sql.append(" and bg.zdname='"+zdmc+"'");
		}
		if (zdbm!=null&&!zdbm.equals("0")&&!zdbm.equals("")){
			sql.append(" and bg.zdbm='"+zdbm+"'");
		}
		
		sql.append(" GROUP BY BG.ZDBM,DX.XFJE,DX.FTJE,DX.TS ");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("电信电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					//String id = rs.getString("ID");
					String dw = rs.getString("max(bg.dw)");
					String qx = rs.getString("max(bg.qx)");
					String zdmcc = rs.getString("max(bg.zdname)");
					String zdbmm = rs.getString("max(bg.zdbm)");
					String zdlx = rs.getString("max(bg.zdlx)");
					String xfje = rs.getString("max(dx.xfje)");
					String ftje = rs.getString("max(dx.ftje)");
					String jsfs = rs.getString("max(bg.jsfs)");
					String gdfs = rs.getString("max(bg.gdfs)");
					String ts = rs.getString("max(dx.ts)");
					String jfqsrq = rs.getString("min(bg.jfqsrq)");
					String jfzzrq = rs.getString("max(bg.jfzzrq)");
					String jfcs = rs.getString("count(bg.id)");

					DxdffxBean wydfft = new DxdffxBean();
					wydfft.setRownum(rownum);
					//wydfft.setGDFMC(gdfmc);
					wydfft.setDw(dw);
					wydfft.setQX(qx);
					wydfft.setJSFS(jsfs);
					wydfft.setGDFS(gdfs);
					wydfft.setFTJE(ftje);
					wydfft.setZDMC(zdmcc);
					wydfft.setZDBM(zdbmm);
					wydfft.setTs(ts);
					wydfft.setXfje(xfje);
					wydfft.setZdlx(zdlx);
					wydfft.setJfqsrq(jfqsrq);
					wydfft.setJfzzrq(jfzzrq);
					wydfft.setJfcs(jfcs);
					wydfftList.add(wydfft);
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
		}
		return wydfftList;
	}
	
	
	public boolean adddxfx(DxdffxBean bean) throws DbException{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_WY_DFFT_TDBG (ID,DW,QX,ZDMC,ZDBM,GDFS,JSFS,DH,CB_JFQSRQ,CB_JFZZRQ,CB_DLIANG,DANJIA,JFJE,JFRQ,JFPJLX,GDFMC,KH,ZDLX,CB_DLIU,CB_QM,CB_ZM,TS,CY,FTBL,FSYZ,FTJE)");
		sql.append("VALUES (");
		sql.append("SEQ_TDBG.nextval,'"+bean.getDw()+"','"+bean.getQx()+"','"+bean.getZDMC()+"','"+bean.getZDBM()+"',");
		sql.append("'"+bean.getGDFS()+"','"+bean.getJSFS()+"','"+bean.getDH()+"','"+bean.getJfqsrq()+"','"+bean.getJfzzrq()+"',");
		sql.append("'"+bean.getDLIANG()+"','"+bean.getBCDJ()+"','"+bean.getJFJE()+"','"+bean.getJFRQ()+"','"+bean.getJFPJLX()+"',");
		sql.append("'"+bean.getGDFMC()+"','"+bean.getKH()+"','"+bean.getZzlx()+"','"+bean.getDLIU()+"','"+bean.getQM()+"','"+bean.getZM()+"','"+bean.getTs()+"','"+bean.getCY()+"','"+bean.getFTBL()+"','"+bean.getFSYZ()+"','"+bean.getFTJE()+"')");
		log.info("添加电信数据分析SQL:" + sql.toString());
		DataBase db = new DataBase();
		db.connectDb();
		int i = db.update(sql.toString());
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean UpdateDxdffx(DxdffxBean bean) {
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update tbl_tt_wy_dfft_tdbg set danjia='"+bean.getBCDJ()+"', cb_dliu='"+bean.getDLIU()+"',ftbl='"+bean.getFTBL()+"',ftje='"+bean.getFTJE()+"',fsyz='"+bean.getFSYZ()+"',lastcbsj='"+
		bean.getSccbsj()+"',thiscbsj='"+bean.getBccbsj()+"',cb_qm='"+bean.getQM()+"',cb_zm='"+bean.getZM()+"',cb_dliang='"+bean.getDLIANG()+"' where id="+bean.getID()+"");		
		log.info("电信电费包干分析修改SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return flag;
	}
}
