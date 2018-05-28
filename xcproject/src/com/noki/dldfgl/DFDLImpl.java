package com.noki.dldfgl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.manage.SystemManage;

public class DFDLImpl {
	
	//private static Log log = LogFactory.getLog(DFDLImpl.class.getName());
	
	public List<DFDLbean> searchWydffft_fy(String loginName,String loginId,int pageSize, int curpage,String sheng,
			String shi, String xian, String xiaoqu,String _qrzt,String _cbzt,String _pc,String _zdbm) {
		List<DFDLbean> wydfftList = new ArrayList<DFDLbean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT RTNAME(B.GDFS) GDFS, A.ID,RNDIQU(A.SFGS) SFGS,RNDIQU(A.DSFGS) DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.CB_DLIANG,A.CB_QM,A.CB_ZM,A.DYCWBXJE,A.CB_DLIU,A.JGJE,A.CB_JFQSRQ,A.CB_JFZZRQ,A.HDZT,A.CBZT,A.SUNHAO,A.LASTCBSJ,A.THISCBSJ,A.BZ,A.YEARMONTH");
		//sql.append("SELECT CASE WHEN B.GDFS='gdfs05' THEN '直供电' ELSE '转供电' end gdfs, A.ID,CASE WHEN A.SFGS='137' THEN '山东省' ELSE '山东省' END SFGS,CASE WHEN A.DSFGS='13715' THEN '聊城市' ELSE '聊城市' END DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.CB_DLIANG,A.CB_QM,A.CB_ZM,A.DYCWBXJE,A.CB_DLIU,A.JGJE,A.CB_JFQSRQ,A.CB_JFZZRQ,A.HDZT,A.CBZT,A.SUNHAO,A.LASTCBSJ,A.THISCBSJ,A.BZ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B WHERE A.ZDBM = B.JZCODE AND A.QRZT='已确认'");
		if (!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '"+xiaoqu+"'");
		} else if (!xian.equals("0")) {
			sql.append("AND B.XIAN='" + xian + "'");
		} else if (!shi.equals("0")) {
			sql.append("AND B.SHI='" + shi + "'");
		} else if (!sheng.equals("0")) {
			sql.append("AND B.SHENG='" + sheng + "'");
		}
		
		if(_pc!=null&&!_pc.equals("")){
			sql.append(" and A.YEARMONTH='" + _pc + "'");
		}
		
		if(_zdbm!=null&&!_zdbm.equals("")&&!_zdbm.equals("0")){
			sql.append(" AND A.ZDBM='"+_zdbm+"'");
		}
		
		if(_cbzt!=null&&!_cbzt.equals("")&&!_cbzt.equals("0")){
			sql.append(" AND A.CBZT='"+_cbzt+"'");
		}
		
		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		//log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				System.out.println(sql);
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String gdfs = rs.getString("gdfs");
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
					//String tzhje = rs.getString("TZHJE");
					//String tzyy = rs.getString("TZYY");
					//String tzr = rs.getString("TZR");
					//String dycwbxje = rs.getString("DYCWBXJE");
					//String dycwhxje = rs.getString("DYCWHXJE");
					//String jgje = rs.getString("JGJE");
					//String cy = rs.getString("CY");
					//String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					//String qrzt = rs.getString("QRZT");
					String bz = rs.getString("BZ");
					String qm = rs.getString("CB_QM")==null||rs.getString("CB_QM").equals("")||rs.getString("CB_QM").equals("null")?"":rs.getString("CB_QM");
					String zm = rs.getString("CB_ZM")==null||rs.getString("CB_ZM").equals("")||rs.getString("CB_ZM").equals("null")?"":rs.getString("CB_ZM");
					String cbzt=rs.getString("CBZT");
					String jfqsrq = rs.getString("CB_JFQSRQ")==null||rs.getString("CB_JFQSRQ").equals("")||rs.getString("CB_JFQSRQ").equals("null")?"":rs.getString("CB_JFQSRQ");
					String jfzzrq = rs.getString("CB_JFZZRQ")==null||rs.getString("CB_JFZZRQ").equals("")||rs.getString("CB_JFZZRQ").equals("null")?"":rs.getString("CB_JFZZRQ");
					String dianliu = rs.getString("CB_DLIU")==null||rs.getString("CB_DLIU").equals("")||rs.getString("CB_DLIU").equals("null")?"":rs.getString("CB_DLIU");
					String dianlaing = rs.getString("CB_DLIANG")==null||rs.getString("CB_DLIANG").equals("")||rs.getString("CB_DLIANG").equals("null")?"":rs.getString("CB_DLIANG");
					String sunhao = rs.getString("sunhao")==null||rs.getString("sunhao").equals("")||rs.getString("sunhao").equals("null")?"":rs.getString("sunhao");
					String lastcbsj = rs.getString("lastcbsj")==null||rs.getString("lastcbsj").equals("")||rs.getString("lastcbsj").equals("null")?"":rs.getString("lastcbsj");
					String thiscbsj = rs.getString("thiscbsj")==null||rs.getString("thiscbsj").equals("")||rs.getString("thiscbsj").equals("null")?"":rs.getString("thiscbsj");
					//String dianliang = rs.getString("ACTUALDEGREE");
					//String time = rs.getString("INPUTDATETIMEOLD");
					
					String yearmonth = rs.getString("YEARMONTH");
					DFDLbean wydfft = new DFDLbean();
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setKH(kh);
					wydfft.setRownum(rownum);
					wydfft.setSFGS(sfgs);
					wydfft.setXZBS(xzbs);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setCB_QM(qm);
					wydfft.setCB_ZM(zm);
					wydfft.setCBZT(cbzt);
					wydfft.setCB_JFQSRQ(jfqsrq);
					wydfft.setCB_JFZZRQ(jfzzrq);
					wydfft.setCB_DLIU(dianliu);
					wydfft.setGDFS(gdfs);
					wydfft.setCB_DLIANG(dianlaing);
					wydfft.setSUNHAO(sunhao);
					wydfft.setLASTCBSJ(lastcbsj);
					wydfft.setTHISCBSJ(thiscbsj);
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
	public int searchCountWydfft() {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(ID) AS COUNTNUMBER");
		sql.append(" FROM TBL_TT_WY_DFFT");
		//log.info("物业数据电费分摊记录总数查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					count = rs.getInt("COUNTNUMBER");
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
		return count;
	}
	
	public List<DFDLbean> searchWydffft_fy1(String loginName,String loginId,int pageSize, int curpage,String sheng,
			String shi, String xian, String xiaoqu,String _hdzt,String _cbzt) {
		List<DFDLbean> wydfftList = new ArrayList<DFDLbean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT CASE WHEN B.GDFS='gdfs05' THEN '直供电' ELSE '转供电' end gdfs, A.ID,CASE WHEN A.SFGS='137' THEN '山东省' ELSE '山东省' END SFGS,CASE WHEN A.DSFGS='13715' THEN '聊城市' ELSE '聊城市' END DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.CB_DLIANG,A.CB_QM,A.CB_ZM,A.DYCWBXJE,A.CB_DLIU,A.JGJE,A.CB_JFQSRQ,A.CB_JFZZRQ,A.HDZT,A.CBZT,A.SUNHAO,A.LASTCBSJ,A.THISCBSJ,A.BZ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B WHERE A.ZDBM = B.JZCODE ");
		if (!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '"+xiaoqu+"'");
		} else if (!xian.equals("0")) {
			sql.append("AND B.XIAN='" + xian + "'");
		} else if (!shi.equals("0")) {
			sql.append("AND B.SHI='" + shi + "'");
		} else if (!sheng.equals("0")) {
			sql.append("AND B.SHENG='" + sheng + "'");
		}
		
		if(!_hdzt.equals("0")){
			sql.append(" and A.HDZT='" + _hdzt + "'");
		}
		
		if(!_cbzt.equals("0")){
			if(_cbzt.equals("未抄表")){
				sql.append("AND A.CBZT='"+_cbzt+"' AND A.QRZT='已确认' ");
			}else{
			sql.append(" AND A.CBZT='"+_cbzt+"'");
			}
		}
		
		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		//log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				System.out.println(sql);
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String gdfs = rs.getString("gdfs");
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
					//String tzhje = rs.getString("TZHJE");
					//String tzyy = rs.getString("TZYY");
					//String tzr = rs.getString("TZR");
					//String dycwbxje = rs.getString("DYCWBXJE");
					//String dycwhxje = rs.getString("DYCWHXJE");
					//String jgje = rs.getString("JGJE");
					//String cy = rs.getString("CY");
					//String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					//String qrzt = rs.getString("QRZT");
					String bz = rs.getString("BZ");
					String qm = rs.getString("CB_QM")==null?"":rs.getString("CB_QM");
					String zm = rs.getString("CB_ZM")==null?"":rs.getString("CB_ZM");
					String cbzt=rs.getString("CBZT");
					String jfqsrq = rs.getString("CB_JFQSRQ")==null?"":rs.getString("CB_JFQSRQ");
					String jfzzrq = rs.getString("CB_JFZZRQ")==null?"":rs.getString("CB_JFZZRQ");
					String dianliu = rs.getString("CB_DLIU")==null?"":rs.getString("CB_DLIU");
					String dianlaing =rs.getString("CB_DLIANG")==null?"":rs.getString("CB_DLIANG");
					String sunhao = rs.getString("SUNHAO")==null?"":rs.getString("SUNHAO");
					String lastcbsj = rs.getString("LASTCBSJ")==null?"":rs.getString("LASTCBSJ");
					String thiscbsj = rs.getString("THISCBSJ")==null?"":rs.getString("THISCBSJ");
					//String dianliang = rs.getString("ACTUALDEGREE");
					//String time = rs.getString("INPUTDATETIMEOLD");
					DFDLbean wydfft = new DFDLbean();
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					//wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					//wydfft.setDYCWBXJE(dycwbxje);
					//wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					//wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setRownum(rownum);
					wydfft.setSFGS(sfgs);
					//wydfft.setTZHJE(tzhje);
					//wydfft.setTZR(tzr);
					//wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					//wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					//wydfft.setQRZT(qrzt);
					wydfft.setCB_QM(qm);
					wydfft.setCB_ZM(zm);
					wydfft.setCBZT(cbzt);
					wydfft.setCB_JFQSRQ(jfqsrq);
					wydfft.setCB_JFZZRQ(jfzzrq);
					wydfft.setCB_DLIU(dianliu);
					wydfft.setGDFS(gdfs);
					wydfft.setCB_DLIANG(dianlaing);
					wydfft.setSUNHAO(sunhao);
					wydfft.setLASTCBSJ(lastcbsj);
					wydfft.setTHISCBSJ(thiscbsj);
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

}
