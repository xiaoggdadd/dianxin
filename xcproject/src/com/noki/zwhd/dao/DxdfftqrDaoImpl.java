package com.noki.zwhd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.DxdfftqrBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class DxdfftqrDaoImpl {
	
	private static Log log = LogFactory.getLog(DxdfftqrDaoImpl.class.getName());

	public boolean saveDxdfftqr(DxdfftqrBean bean) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_WY_DFFT(ID,SFGS,DSFGS,FSYZ,BCDJ,ZDMC,ZDBM,DH,YEARMONTH,CB_JFQSRQ,CB_JFZZRQ,CB_QM,CB_ZM,CB_DLIANG,CB_DLIU,JFJE,GDFMC,JFRQ,JFPJLX,KH,QRZT,YYSXGZT,FTJE,DBBM,FTBL)");
		sql.append("VALUES(XL_TT_WY_DFFT.nextval,");
		sql.append("'"+bean.getDw()+"','"+bean.getQx()+"','"+bean.getFsyz()+"','"+bean.getDj()+"','"+bean.getZdmc()+"','"+bean.getZdbm()+"',");
		sql.append("'"+bean.getDh()+"','"+bean.getPici()+"','"+bean.getJfqsrq()+"','"+bean.getJfzzrq()+"','"+bean.getQm()+"','"+bean.getZm()+"','"+bean.getDliang()+"',");
		sql.append("'"+bean.getDliu()+"','"+bean.getJfje()+"','"+bean.getGdfmc()+"','"+bean.getJfrq()+"','"+bean.getJfpjlx()+"','"+bean.getKh()+"','"+bean.getQrzt()+"','"+bean.getYysxgzt()+"','"+bean.getFtje()+"','"+bean.getDbbm()+"','"+bean.getFtbl()+"'");
		sql.append(")");		
		log.info("电信电费单添加SQL:" + sql.toString());

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
	public boolean updateDxdfftqr(DxdfftqrBean bean) {
		boolean flag = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update tbl_tt_wy_dfft set bcdj='"+bean.getDj()+"', cb_dliu='"+bean.getDliu()+"',ftbl='"+bean.getFtbl()+"',ftje='"+bean.getFtje()+"',fsyz='"+bean.getFsyz()+"',cb_jfqsrq='"+
		bean.getJfqsrq()+"',cb_jfzzrq='"+bean.getJfzzrq()+"',cb_qm='"+bean.getQm()+"',cb_zm='"+bean.getZm()+"',cb_dliang='"+bean.getDliang()+"' where id="+bean.getId()+"");		
		log.info("电信电费单修改SQL:" + sql.toString());

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
	public WydfftBean searchWydfft(String _yearmonth, String _zdbm,String _dh, String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,CB_JFQSRQ,CB_JFZZRQ,CB_QM,CB_ZM,CB_DLIANG,CB_DLIU,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH,THISCBSJ,LASTCBSJ");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + _yearmonth+ "' AND ZDBM = '"+_zdbm+"' AND KH = '"+_kh+"' AND DH = '"+_dh+"' AND QRZT = '已确认'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String thiscbsj = rs.getString("THISCBSJ");
					String lastcbsj = rs.getString("LASTCBSJ");
					String jfqsrq = rs.getString("CB_JFQSRQ");
					String jfzzrq = rs.getString("CB_JFZZRQ");
					String qm = rs.getString("CB_QM");
					String zm = rs.getString("CB_ZM");
					String dliang = rs.getString("CB_DLIANG");
					String dliu = rs.getString("CB_DLIU");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setJfqsrq(jfqsrq);
					wydfft.setJfzzrq(jfzzrq);
					wydfft.setDLIANG(dliang);
					wydfft.setDLIU(dliu);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					return wydfft;
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
		return null;
	}
	public boolean updateWydfftyd(WydfftBean bean) {
		boolean flag = false;
		System.out.println("10101919");

//		dbBean.setDj(wydfftBean.getDj());
//		dbBean.setSh(wydfftBean.getSh());
//		dbBean.setJfrq(wydfftBean.getJfrq());
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT");
		sql.append(" SET ");
		
		if(bean.getDLIU()!=null){
			sql.append(" CB_DLIU = '" + bean.getDLIU() + "',");
		}
		
		if(bean.getJfqsrq()!=null){
			sql.append(" CB_JFQSRQ = '" + bean.getJfqsrq() + "',");
		}
		
		if(bean.getJfzzrq()!=null){
			sql.append(" CB_JFZZRQ = '" + bean.getJfzzrq() + "',");
		}
		
		if(bean.getQM()!=null){
			sql.append(" CB_QM = '" + bean.getQM() + "',");
		}
		if(bean.getZM()!=null){
			sql.append(" CB_ZM = '" + bean.getZM() + "',");
		}
		
		if(bean.getYYSXGZT()!=null){
			sql.append(" YYSXGZT = '" + bean.getYYSXGZT() + "',");
		}
		
		if(bean.getDLIANG()!=null){
			sql.append(" CB_DLIANG = '" +bean.getDLIANG() + "'");
		}
//		}
//		
//		if(bean.getDj()!=null){
//			sql.append(" BCDJ = '" + bean.getDj() + "',");
//		}
//		
//		if(bean.getSh()!=null){
//			sql.append(" SUNHAO = '"+bean.getSh()+"',");
//		}
//		
//		if(bean.getJfrq()!=null){
//			sql.append(" JFRQ = '" + bean.getJfrq() + "',");
//		}
//		
		//sql.append(" YEARMONTH = '" + bean.getPc() + "'");
		sql.append(" WHERE ID = " + bean.getID() + "");

		log.info("修改物业单位分摊数据SQL:" + sql.toString());

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
