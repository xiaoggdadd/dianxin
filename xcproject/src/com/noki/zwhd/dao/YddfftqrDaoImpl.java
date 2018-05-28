package com.noki.zwhd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.DwBean;
import com.noki.zwhd.model.DxdfftqrBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class YddfftqrDaoImpl {
	
	private static Log log = LogFactory.getLog(YddfftqrDaoImpl.class.getName());

	public boolean saveDxdfftqr(DxdfftqrBean bean) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
 		sql.append("UPDATE TBL_TT_WY_DFFT T SET T.YYSXGZT='已修改', T.DBBM='"+bean.getDbbm()+"',T.JSZQ="+bean.getJszq()+",T.CB_QM="+bean.getQm()+",T.CB_ZM="+bean.getZm()+", " +
				" T.CB_DLIANG="+bean.getDliang()+",T.BCDJ="+bean.getDj()+",T.JFRQ='"+bean.getJfrq()+"',T.SUNHAO="+bean.getSh()+",T.LASTCBSJ='"+bean.getJfqsrq()+"',T.THISCBSJ='"+bean.getJfzzrq()+"'  " +
				" WHERE T.ZDBM='"+bean.getZdbm()+"' AND  T.YEARMONTH='"+bean.getPc()+"' AND T.DH='"+bean.getDh()+"'");
	
		
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
	public ZhandianBean searchZhandianByJcode(String jcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT RNDIQU(SHI) SHI,RNDIQU(XIAN) XIAN, ID,JZNAME,JZCODE,LONGITUDE,LATITUDE,ADDRESS,RTNAME(GDFS) GDFS,'抄表结算' AS JSFS FROM ZHANDIAN WHERE JZCODE = "
				+ jcode + " ");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setSHI(rs.getString("SHI"));
					zhandian.setXIAN(rs.getString("XIAN"));
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandian.setGDFS(rs.getString("GDFS"));
					zhandian.setLONGITUDE(rs.getString("LONGITUDE"));
					zhandian.setLATITUDE(rs.getString("LATITUDE"));
					zhandian.setADDRESS(rs.getString("ADDRESS"));
					zhandian.setJSFS(rs.getString("JSFS"));
					return zhandian;
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
	public List<DianbiaoBean> searchDianbaoList(String zdid) {
		List<DianbiaoBean> dianbiaoList = new ArrayList<DianbiaoBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DBBM FROM DIANBIAO WHERE ZDID = '" + zdid + "' ");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					DianbiaoBean dianbiao = new DianbiaoBean();
					dianbiao.setDBBM(rs.getString("DBBM"));
					dianbiaoList.add(dianbiao);
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
		return dianbiaoList;
	}
	public List<DwBean> searchAGR(String AGRADE, String _dw, String userid) {
		List<DwBean> dwBeanList = new ArrayList<DwBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT AGID,AGCODE,AGNAME,FAGCODE,AGRADE,GOOGLEID FROM D_AREA_GRADE WHERE AGRADE = '"
				+ AGRADE + "'");
		if (userid != null && !userid.equals("") && !userid.equals("null")
				&& !userid.equals("NULL") && !userid.equals("0")) {
			sql.append(" AND AGCODE IN (select t.agcode from per_area t where t.accountid='"
					+ userid + "')");
		}
		if (_dw != null && !_dw.equals("") && !_dw.equals("null")
				&& !_dw.equals("NULL") && !_dw.equals("0")) {
			sql.append("AND FAGCODE = '" + _dw + "'");
		}
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					DwBean dwBean = new DwBean();
					dwBean.setAGID(rs.getString("AGID"));
					dwBean.setAGCODE(rs.getString("AGCODE"));
					dwBean.setAGNAME(rs.getString("AGNAME"));
					dwBean.setFAGCODE(rs.getString("FAGCODE"));
					dwBean.setAGRADE(rs.getString("AGRADE"));
					dwBean.setGOOGLEID(rs.getString("GOOGLEID"));
					dwBeanList.add(dwBean);
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
		return dwBeanList;
	}
	public ZhandianBean searchJz(String id){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT T.ZDBM,T.DBBM,T.YEARMONTH,T.DH,T.JSZQ,T.THISCBSJ,T.LASTCBSJ,T.CB_QM,T.CB_ZM,T.CB_DLIANG,T.BCDJ," +
				" T.JFJE,T.JFRQ,T.JFPJLX,T.GDFMC,T.SUNHAO,T.FTBL,T.FSYZ,T.FTJE  FROM TBL_TT_WY_DFFT T WHERE T.ID = "+id+" ");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String jzcode="";
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setPc(rs.getString("YEARMONTH"));
					zhandian.setDh(rs.getString("DH"));
					zhandian.setJszq(rs.getString("JSZQ"));
					zhandian.setLastcbsj(rs.getString("LASTCBSJ"));
					zhandian.setThiscbsj(rs.getString("THISCBSJ"));
					zhandian.setQm(rs.getString("CB_QM"));
					zhandian.setZm(rs.getString("CB_ZM"));
					zhandian.setDliang(rs.getString("CB_DLIANG"));
					zhandian.setDj(rs.getString("BCDJ"));
					zhandian.setJfje(rs.getString("JFJE"));
					zhandian.setJfrq(rs.getString("JFRQ"));
					zhandian.setJfpjlx(rs.getString("JFPJLX"));
					zhandian.setGdfmc(rs.getString("GDFMC"));
					zhandian.setSh(rs.getString("SUNHAO"));
					zhandian.setDbbm(rs.getString("DBBM"));
					zhandian.setZdbm(rs.getString("ZDBM"));
					zhandian.setFtbl(rs.getString("FTBL"));
					zhandian.setFtje(rs.getString("FTJE"));
					zhandian.setFsyz(rs.getString("FSYZ"));
					//jzcode=rs.getString("ZDBM");
					
					return zhandian;
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
	public List<ZhandianBean> searchWydffft_fy(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String _hdzt, String _qrzt, String _pc, String _zdbm) {
		List<ZhandianBean> wydfftList = new ArrayList<ZhandianBean>();
		   StringBuffer sql = new StringBuffer();
_pc=_pc.trim();
	        sql.append("SELECT RNDIQU(B.SHI) SHI,RNDIQU(B.XIAN) XIAN,B.ADDRESS,B.JZNAME,B.JZCODE,  B.LONGITUDE||','||B.LATITUDE AS JWD,'' AS ZCJJZHBM,RTNAME(B.GDFS) GDFS,'抄表结算' AS JSFS,C.DBBM,");
	        sql.append("A.DH,A.JFJE,A.JFPJLX,A.GDFMC,A.FTBL,A.FSYZ,A.FTJE,A.JSZQ,A.THISCBSJ,A.LASTCBSJ,A.CB_QM,A.CB_ZM,A.CB_DLIANG,A.BCDJ,A.SUNHAO,A.JFRQ");
	        sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B,DIANBIAO C where  A.ZDBM = B.JZCODE  AND C.ZDID = B.ID  AND A.QRZT = '已确认' AND A.KH = '移动' AND (A.YYSXGZT='未修改' OR A.YYSXGZT IS NULL) ");
	      sql.append(" AND B.XIAOQU in (select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
	        if (xiaoqu!=null&&!xiaoqu.equals("0")) {
				sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
			}  if (xian!=null&&!xian.equals("0")) {
				sql.append(" AND B.XIAN='" + xian + "'");
			}  if (shi!=null&&!shi.equals("0")) {
				sql.append(" AND B.SHI='" + shi + "'");
			} if (_pc!=null&&!_pc.equals("")) {
			sql.append(" and A.YEARMONTH='" + _pc + "'");
			}
			
			if (_zdbm!=null&&!_zdbm.equals("")) {
				sql.append(" and A.ZDBM='" + _zdbm + "'");
			}
	        log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String shia=rs.getString("SHI");
					String xiana=rs.getString("XIAN");
					String jzname=rs.getString("JZNAME");
					String address=rs.getString("ADDRESS");
					String jzcode=rs.getString("JZCODE");
					String jwd=rs.getString("JWD");
					String zcjjzhbm=rs.getString("ZCJJZHBM");
				    String gdfs=rs.getString("GDFS");
				    String jsfs=rs.getString("JSFS");
					String dbbm=rs.getString("DBBM");
					String dh=rs.getString("DH");
					String jfje=rs.getString("JFJE");
					String jfpjlx=rs.getString("JFPJLX");
					String gdfmc=rs.getString("GDFMC");
					String ftbl=rs.getString("FTBL");
					String fsyz=rs.getString("FSYZ");
					String ftje=rs.getString("FTJE");
					//A.JSZQ,A.THISCBSJ,A.LASTCBSJ,A.CB_QM,A.CB_ZM,A.CB_DLIANG,
					//A.BCDJ,A.SUNHAO,A.JFRQ
					String jszq=rs.getString("JSZQ");String bccbsj=rs.getString("THISCBSJ");
				    String sccbsj=rs.getString("LASTCBSJ");
				    String qm=rs.getString("CB_QM");String zm=rs.getString("CB_ZM");
				    String dl=rs.getString("CB_DLIANG");String dj=rs.getString("BCDJ");
				    String sh=rs.getString("SUNHAO"); String jfrq=rs.getString("JFRQ");
					
					ZhandianBean wydfft = new ZhandianBean();
					wydfft.setJszq(jszq);
					wydfft.setThiscbsj(bccbsj);
					wydfft.setLastcbsj(sccbsj);
					wydfft.setQm(qm);wydfft.setZm(zm);wydfft.setDliang(dl);
					wydfft.setDj(dj);wydfft.setSh(sh);wydfft.setJfrq(jfrq);
					
				    wydfft.setSHI(shia);         wydfft.setDbbm(dbbm);
				    wydfft.setXIAN(xiana);       wydfft.setDh(dh);
				    wydfft.setJZNAME(jzname);    wydfft.setJfje(jfje);
				    wydfft.setJZCODE(jzcode);    wydfft.setJfpjlx(jfpjlx);
				    wydfft.setJwd(jwd);          wydfft.setGdfmc(gdfmc);
				    wydfft.setZcjjzhbm(zcjjzhbm);wydfft.setFtbl(ftbl);
				    wydfft.setGdfs(gdfs);        wydfft.setFsyz(fsyz);
				    wydfft.setJsfs(jsfs);        wydfft.setFtje(ftje);
				    wydfft.setPc(_pc);           wydfft.setADDRESS(address);
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
	public List<ZhandianBean> searchWydffft_fyyd(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String _hdzt, String _qrzt, String _pc, String _zdbm) {
		List<ZhandianBean> wydfftList = new ArrayList<ZhandianBean>();
		   StringBuffer sql = new StringBuffer();
_pc=_pc.trim();
	        sql.append("SELECT RNDIQU(B.SHI) SHI,RNDIQU(B.XIAN) XIAN,B.ADDRESS,B.JZNAME,B.JZCODE,  B.LONGITUDE||','||B.LATITUDE AS JWD,'' AS ZCJJZHBM,RTNAME(B.GDFS) GDFS,'抄表结算' AS JSFS,C.DBBM,");
	        sql.append("A.DH,A.JFJE,A.JFPJLX,A.GDFMC,A.FTBL,A.FSYZ,A.FTJE,A.JSZQ,A.THISCBSJ,A.LASTCBSJ,A.CB_QM,A.CB_ZM,A.CB_DLIANG,A.BCDJ,A.SUNHAO,A.JFRQ");
	        sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B,DIANBIAO C where  A.ZDBM = B.JZCODE  AND C.ZDID = B.ID  AND A.QRZT = '已确认' AND A.KH = '移动' AND A.YYSXGZT='已修改' ");
	      sql.append(" AND B.XIAOQU in (select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
	        if (xiaoqu!=null&&!xiaoqu.equals("0")) {
				sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
			}  if (xian!=null&&!xian.equals("0")) {
				sql.append(" AND B.XIAN='" + xian + "'");
			}  if (shi!=null&&!shi.equals("0")) {
				sql.append(" AND B.SHI='" + shi + "'");
			} if (_pc!=null&&!_pc.equals("")) {
			sql.append(" and A.YEARMONTH='" + _pc + "'");
			}
			
			if (_zdbm!=null&&!_zdbm.equals("")) {
				sql.append(" and A.ZDBM='" + _zdbm + "'");
			}
	        log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String shia=rs.getString("SHI");
					String xiana=rs.getString("XIAN");
					String jzname=rs.getString("JZNAME");
					String address=rs.getString("ADDRESS");
					String jzcode=rs.getString("JZCODE");
					String jwd=rs.getString("JWD");
					String zcjjzhbm=rs.getString("ZCJJZHBM");
				    String gdfs=rs.getString("GDFS");
				    String jsfs=rs.getString("JSFS");
					String dbbm=rs.getString("DBBM");
					String dh=rs.getString("DH");
					String jfje=rs.getString("JFJE");
					String jfpjlx=rs.getString("JFPJLX");
					String gdfmc=rs.getString("GDFMC");
					String ftbl=rs.getString("FTBL");
					String fsyz=rs.getString("FSYZ");
					String ftje=rs.getString("FTJE");
					//A.JSZQ,A.THISCBSJ,A.LASTCBSJ,A.CB_QM,A.CB_ZM,A.CB_DLIANG,
					//A.BCDJ,A.SUNHAO,A.JFRQ
					String jszq=rs.getString("JSZQ");String bccbsj=rs.getString("THISCBSJ");
				    String sccbsj=rs.getString("LASTCBSJ");
				    String qm=rs.getString("CB_QM");String zm=rs.getString("CB_ZM");
				    String dl=rs.getString("CB_DLIANG");String dj=rs.getString("BCDJ");
				    String sh=rs.getString("SUNHAO"); String jfrq=rs.getString("JFRQ");
					
					ZhandianBean wydfft = new ZhandianBean();
					wydfft.setJszq(jszq);
					wydfft.setThiscbsj(bccbsj);
					wydfft.setLastcbsj(sccbsj);
					wydfft.setQm(qm);wydfft.setZm(zm);wydfft.setDliang(dl);
					wydfft.setDj(dj);wydfft.setSh(sh);wydfft.setJfrq(jfrq);
					
				    wydfft.setSHI(shia);         wydfft.setDbbm(dbbm);
				    wydfft.setXIAN(xiana);       wydfft.setDh(dh);
				    wydfft.setJZNAME(jzname);    wydfft.setJfje(jfje);
				    wydfft.setJZCODE(jzcode);    wydfft.setJfpjlx(jfpjlx);
				    wydfft.setJwd(jwd);          wydfft.setGdfmc(gdfmc);
				    wydfft.setZcjjzhbm(zcjjzhbm);wydfft.setFtbl(ftbl);
				    wydfft.setGdfs(gdfs);        wydfft.setFsyz(fsyz);
				    wydfft.setJsfs(jsfs);        wydfft.setFtje(ftje);
				    wydfft.setPc(_pc);           wydfft.setADDRESS(address);
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
	public ZhandianBean searchWydfft(String _yearmonth, String _zdbm,String _dh, String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT T.ID,T.ZDBM,T.DBBM,T.YEARMONTH,T.DH,T.JSZQ,T.THISCBSJ,T.LASTCBSJ,T.CB_QM,T.CB_ZM,T.CB_DLIANG,T.BCDJ ");
		sql.append(" FROM TBL_TT_WY_DFFT T WHERE YEARMONTH = '" + _yearmonth+ "' AND ZDBM = '"+_zdbm+"' AND KH = '"+_kh+"' AND DH = '"+_dh+"' AND QRZT = '已确认'");
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
					ZhandianBean wydfft = new ZhandianBean();
				    wydfft.setID(id);
					
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
}
