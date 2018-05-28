package com.noki.newfunction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.javabean.Zdinfo;


public class PdTestBewrite {

	// 负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException,
			SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {
			cxtj = cxtj + " or (zdcode>='" + rs.getString(1)
					+ "' and zdcode <='" + rs.getString(2) + "')";
		}
		rs.close();
		db.close();
		System.out.println("负责站点条件：" + cxtj);
		return cxtj.toString();
	}

	/**
	 * 站点超表管理--测试描述查询语句
	 * 
	 * @param whereStr
	 *            :查询条件
	 * @param loginId
	 *            ：用户登录id
	 * @return List<Zdinfo>：站点信息存入Zdinfo，Zdinfo存入list
	 */
	public synchronized List<Zdinfo> getPageDatap(String whereStr,
			String loginId) {
		List<Zdinfo> list = new ArrayList<Zdinfo>();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "SELECT CB.ID,CB.ZDID,CB.ZDNAME,CB.SHI || CB.XIAN || CB.XIAOQU AS SZDQ,CB.CBSJ,CB.CBBL*100 AS BILI,CB.SJLRSJ,CB.SJLRR,CB.QXTJSH,CB.SJTDYY,CB.SHIJSH " +
					"FROM CBZD CB LEFT JOIN ZHANDIAN ZD ON CB.ZDID=ZD.ID " +
					"WHERE 1=1  and CB.QXPD='1' "+ whereStr
					+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))" + fzzdstr + ") ORDER BY CB.ZDID";
			db.connectDb();
			System.out.println("站点超表管理--测试描述查询语句：" + sql);
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				Zdinfo formbean = new Zdinfo();
				formbean.setWjid(rs.getString("ID") != null ? rs
						.getString("ID") : "");
				formbean.setZdid(rs.getString("ZDID") != null ? rs
						.getString("ZDID") : "");
				formbean.setZdname(rs.getString("ZDNAME") != null ? rs
						.getString("ZDNAME") : "");
				formbean.setSzdq(rs.getString("SZDQ") != null ? rs
						.getString("SZDQ") : "");
				formbean.setCbsj(rs.getString("CBSJ") != null ? rs
						.getString("CBSJ") : "");
				formbean.setCbbl(rs.getString("BILI") != null ? rs
						.getString("BILI") : "0.00");

				formbean.setLrsj(rs.getString("SJLRSJ") != null ? rs.getString("SJLRSJ")
						: "");
				formbean.setLrr(rs.getString("SJLRR") != null ? rs.getString("SJLRR")
						: "");
				formbean.setQxtjsh(rs.getString("QXTJSH") != null ? rs.getString("QXTJSH")
						: "");
				formbean.setSjtdyy(rs.getString("SJTDYY") != null ? rs.getString("SJTDYY")
						: "");
				formbean.setSjshbz(rs.getString("SHIJSH") != null ? rs.getString("SHIJSH")
						: "");
				list.add(formbean);
			}
		} catch (DbException de) {
			de.printStackTrace();
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

		return list;
	}

	/**
	 * 站点超表管理--测试描述站点详细信息
	 * 
	 * @param id
	 *            ：站点唯一标识id
	 * @return zdinfo:zdinfo--bean存放站点详细信息
	 */
	public synchronized Zdinfo getPdTestBewritezdxx(String id) {
		Zdinfo formbean = new Zdinfo();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {

			/*sql = "SELECT CB.ZDID,CB.ZDNAME,CB.SHI || CB.XIAN || CB.XIAOQU AS SZDQ," +
					"CB.CBSJ,CB.BZPLD*100 AS BILI,CB.SJLRR,CB.SJLRSJ,CBXX.ZGYQ,(CASE WHEN CBXX.SJNAME IS NULL THEN '0' ELSE '1' END) AS SJFJ," +
					"CB.G2,CB.G3,CB.CHANGJIA,CB.ZP,CB.ZS,CB.JZTYPE,CB.SHEBEI,CB.BBU,CB.RRU,CB.YDSHEBEI," +
<<<<<<< .mine
					"CB.GXGWSL,CB.DXGXSB,CB.YDGXSB,CB.G2XQ,CB.G3SQ,cbxx.g2 XG2,cbxx.g3 XG3,cbxx.zp XZP,cbxx.zs XZS,cbxx.changjia XCJ,cbxx.jztype XJZTYPE,cbxx.shebei XSB,cbxx.bbu XBBU,cbxx.rru XRRU,cbxx.gxgwsl XGXGWSL,cbxx.dxgxsb XDXGXSB,cbxx.ydgxsb XYDGXSB,cbxx.g2xq XG2XQ,cbxx.g3sq XG3SQ" +
					" FROM CBZD CB, CBZDXX CBXX WHERE CB.ID = CBXX.WJID AND CB.ID = '"+id+"'";

=======
					"CB.GXGWSL,CB.DXGXSB,CB.YDGXSB,CB.G2XQ,CB.G3SQ FROM CBZD CB, CBZDXX CBXX WHERE CB.ID = CBXX.WJID AND CB.ID = '"+id+"'";*/
			sql = "SELECT CB.ZDID,CB.ZDNAME,CB.SHI || CB.XIAN || CB.XIAOQU AS SZDQ," +
			"CB.CBSJ,CB.CBBL*100 AS BILI,CB.SJLRR,CB.SJLRSJ,CBXX.ZGYQ,CBXX.SJNAME," +
			"CB.G2,CB.G3,CB.CHANGJIA,CB.ZP,CB.ZS,CB.JZTYPE,CB.SHEBEI,CB.BBU,CB.RRU,CB.YDSHEBEI," +
			"CB.GXGWSL,CB.DXGXSB JDXGXSB,CB.YDGXSB,CB.G2XQ,CB.G3SQ,cbxx.g2 XG2,cbxx.g3 XG3,cbxx.zp XZP,cbxx.zs XZS,cbxx.changjia XCHANGJIA,cbxx.jztype XJZTYPE,cbxx.shebei XSHEBEI,cbxx.bbu XBBU,cbxx.rru XRRU,cbxx.gxgwsl XGXGWSL,cbxx.dxgxsb XDXGXSB,cbxx.ydgxsb XYDGXSB,cbxx.g2xq XG2XQ,cbxx.g3sq XG3SQ," +
			"  CBXX.DBDS,CBXX.KGDYZLFH,CBXX.YDGXSBZLFH,CBXX.DXGXSBZLFH,CBXX.GYGXSBZLFH,CBXX.ZYYGSBZLFH,CBXX.YYFX,CBXX.CSZRR,CB.NHBZ,CB.EDHDL,CB.ZLFH,CBXX.ZLZFH,CBXX.JLZFH,CBXX.BDEDHDL,CBXX.QSDB "+
			" FROM CBZD CB, CBZDXX CBXX WHERE CB.ID = CBXX.WJID AND CB.ID = '"+id+"'";

			System.out.println("站点超表管理--测试描述站点信息和11大类查询语句：" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formbean.setDbds(rs.getString("DBDS"));
				formbean.setKGDYZLFH(rs.getString("KGDYZLFH"));
				formbean.setYDGXSBZLFH(rs.getString("YDGXSBZLFH"));
				formbean.setDXGXSBZLFH(rs.getString("DXGXSBZLFH"));
				formbean.setGYGXSBZLFH(rs.getString("GYGXSBZLFH"));
				formbean.setZYYGSBZLFH(rs.getString("ZYYGSBZLFH"));
				formbean.setYyfx(rs.getString("YYFX"));
				formbean.setCszrr(rs.getString("CSZRR"));
				formbean.setZdid(rs.getString("ZDID") != null ? rs.getString("ZDID") : "");
				formbean.setZdname(rs.getString("ZDNAME") != null ? rs.getString("ZDNAME") : "");
				formbean.setSzdq(rs.getString("SZDQ") != null ? rs.getString("SZDQ") : "");
				formbean.setCbsj(rs.getString("CBSJ") != null ? rs.getString("CBSJ") : "");
				formbean.setCbbl(rs.getString("BILI") != null ? rs.getString("BILI") : "0.00");
				formbean.setLrsj(rs.getString("SJLRSJ") != null ? rs.getString("SJLRSJ"): "");
				formbean.setLrr(rs.getString("SJLRR") != null ? rs.getString("SJLRR"): "");
				formbean.setZgyq(rs.getString("ZGYQ") != null ? rs.getString("ZGYQ") : "");
				formbean.setSjname(rs.getString("SJNAME") != null ? rs.getString("SJNAME") : "");
				formbean.setG2(rs.getString("G2").trim() != null ? rs.getString("G2").trim() : "0");
				formbean.setG3(rs.getString("G3").trim() != null ? rs.getString("G3").trim() : "0");
				formbean.setChangjia(rs.getString("CHANGJIA") != null ? rs.getString("CHANGJIA") : "");
				formbean.setZp(rs.getString("ZP") != null ? rs.getString("ZP"): "0");
				formbean.setZs(rs.getString("ZS") != null ? rs.getString("ZS"): "0");
				formbean.setJztype(rs.getString("JZTYPE") != null ? rs.getString("JZTYPE") : "");
				formbean.setShebei(rs.getString("SHEBEI") != null ? rs.getString("SHEBEI") : "");
				formbean.setBbu(rs.getString("BBU") != null ? rs.getString("BBU") : "0");
				formbean.setRru(rs.getString("RRU") != null ? rs.getString("RRU") : "0");
				formbean.setYdshebei(rs.getString("YDSHEBEI") != null ? rs.getString("YDSHEBEI") : "0");
				formbean.setGxgwsl(rs.getString("GXGWSL") != null ? rs.getString("GXGWSL") : "0");
				formbean.setDxgxsb(rs.getString("JDXGXSB") != null ? rs.getString("JDXGXSB") : "0");
				formbean.setYdgxsb(rs.getString("YDGXSB") != null ? rs.getString("YDGXSB") : "0");
				formbean.setG2xq(rs.getString("G2XQ") != null ? rs.getString("G2XQ") : "0");
				formbean.setG3sq(rs.getString("G3SQ") != null ? rs.getString("G3SQ") : "0");
				formbean.setXg2(rs.getString("XG2"));
				formbean.setXg3(rs.getString("XG3"));
				
				String s=rs.getString("XZP");
				if(null==s||"".equals(s)||"null".equals(s)){
					s="0";
				}
				formbean.setXzp(Integer.parseInt(s));
				String s1=rs.getString("XZS");
				if(null==s1||"".equals(s1)||"null".equals(s1)){
					s1="0";
				}
				formbean.setXzs(Integer.parseInt(s1));
				formbean.setXchangjia(rs.getString("XCHANGJIA"));
				formbean.setXjztype(rs.getString("XJZTYPE"));
				formbean.setXshebei(rs.getString("XSHEBEI"));
				String s2=rs.getString("XBBU");
				if(null==s2||"".equals(s2)||"null".equals(s2)){
					s2="0";
				}
				
				formbean.setXbbu(Integer.parseInt(s2));
				String s4=rs.getString("XRRU");
				if(null==s4||"".equals(s4)||"null".equals(s4)){
					s4="0";
				}
				formbean.setXrru(Integer.parseInt(s4));
				String s3=rs.getString("XGXGWSL");
				if(null==s3||"".equals(s3)||"null".equals(s3)){
					s3="0";
				}
				formbean.setXgxgwsl(Integer.parseInt(s3));
				formbean.setJdxgxsb(rs.getString("XDXGXSB"));
				formbean.setJydgxsb(rs.getString("XYDGXSB"));
				formbean.setJg2xq(rs.getString("XG2XQ"));
				formbean.setJg3sq(rs.getString("XG3SQ"));
				formbean.setNhbz(rs.getString("NHBZ"));
				formbean.setEdhdl(rs.getString("EDHDL"));
				formbean.setZlfh(rs.getString("ZLFH"));
				
				formbean.setZlzfh(rs.getString("ZLZFH"));
				formbean.setJlzfh(rs.getString("JLZFH"));
				formbean.setBdhdl(rs.getString("BDEDHDL"));
				formbean.setQsdb(rs.getString("QSDB"));
				
			}
		} catch (DbException de) {
			de.printStackTrace();
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
		return formbean;
	}
	public synchronized String getZdsx(String zdid){
		Logger logger = Logger.getLogger(PdTestBewrite.class);
		String zdsx = "";
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT ZD.PROPERTY FROM ZHANDIAN ZD WHERE ZD.ID = " + zdid;
		try {
			connc  = db.getConnection();
			st = connc.createStatement();
			logger.error("站点属性查询sql:"+sql);
			rs = st.executeQuery(sql);	
			if(rs.next()){
				zdsx = rs.getString("PROPERTY");
			}
		} catch (Exception e) {
			logger.error("站点属性查询失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		
		return zdsx;
	}
/**
 * 测试描述修改11大类和更新核实状态
 * @param formBean:11大类信息相关信息
 * @return msg：修改站点信息是否成功
 */
	public synchronized String updatePdTestBewritezdxx(Zdinfo formBean,String path,String name) {		
		String msg = "保存账户失败！请重试或与管理员联系！";
		
		String sqlcb = "";
		if(!path.equals("")){
			path=path+"\\"+name;
		}
		//path=path+"\\"+name;
		// name=formBean.getId()+formBean.getZdid()+"ID"+name;
		sqlcb="UPDATE CBZD CB SET CB.QXTJSH ='1',CB.SHIJSH ='0',CB.SHENGJSH ='0',CB.SJTDYY='',CB.TDYY ='',CB.FKSJ =null,CB.QXTJR ='"+formBean.getQxlrr()+
		"',CB.QXTJSHSJ=TO_DATE('"+formBean.getQxlrsj()+"','YYYY-MM-DD HH24:MI:SS') WHERE CB.ID='"+formBean.getWjid()+"'";
		
		String sql="";		
		sql="UPDATE CBZDXX CB SET CB.QXPATH='"+path+"',CB.QXNAME='"+name+"',CB.G2 ='"+formBean.getG2()+"',CB.G3 ='"+formBean.getG3()+"',CB.CHANGJIA ='"+formBean.getChangjia()+
		"',CB.ZP ='"+formBean.getZp()+"', CB.ZS ='"+formBean.getZs()+"',CB.JZTYPE='"+formBean.getJztype()+"',CB.SHEBEI ='"+formBean.getShebei()+
		"',CB.RRU ='"+formBean.getRru()+"',CB.BBU ='"+formBean.getBbu()+"',CB.GXGWSL ='"+formBean.getGxgwsl()+"',CB.DXGXSB ='"+formBean.getDxgxsb()+
		"',CB.CSMS ='"+formBean.getCsms()+"',CB.YYFX='"+formBean.getYyfx()+"',CB.CSZRR ='"+formBean.getCszrr()+"',CB.QXLRR ='"+formBean.getQxlrr()+
		"',CB.QXLRSJ = TO_DATE('"+formBean.getQxlrsj()+"','YYYY-MM-DD HH24:MI:SS'),CB.DBDS="+formBean.getDbds()+",CB.KGDYZLFH="+formBean.getKGDYZLFH()+
		",CB.YDGXSBZLFH="+formBean.getYDGXSBZLFH()+",CB.DXGXSBZLFH="+formBean.getDXGXSBZLFH()+",CB.GYGXSBZLFH="+formBean.getGYGXSBZLFH()+"," +
		"CB.YDGXSB="+formBean.getYdgxsb()+",CB.ZYYGSBZLFH="+formBean.getZYYGSBZLFH()+",CB.G2XQ="+formBean.getG2xq()+",CB.G3SQ="+formBean.getG3sq()+
		",CB.zlzfh="+formBean.getZlzfh()+",CB.jlzfh="+formBean.getJlzfh()+",CB.bdedhdl="+formBean.getBdhdl()+",CB.qsdb="+formBean.getQsdb()+" WHERE CB.WJID='"+formBean.getWjid()+"'";
		DataBase db = new DataBase();
		System.out.println("测试描述修改更新核实状态："+sql.toString());
		System.out.println("测试描述修改11大类："+sqlcb.toString());
		try {
			db.update(sqlcb);
			db.update(sql);
			msg = "修改站点信息成功!";
		} catch (DbException de) {
		} finally {
			try {
                if(db!=null)
				{
                	db.close();
				}
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}
	 public synchronized int CSupdate(String chooseIdStr){//测试描述撤销
			int msg=0;
			StringBuffer sql = new StringBuffer();
			sql.append("update CBZD c set c.qxtjsh='0' where c.QXPD='1' and c.id in ("+chooseIdStr+")");
			DataBase db = new DataBase();
			System.out.println("测试描述撤销："+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				msg = 1;
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {
					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
		    return msg;
		 }
	 
	 
		public synchronized String[] getXgsq(String zdid) {
			Logger logger = Logger.getLogger(PdTestBewrite.class);
			DataBase db = new DataBase();
			Connection connc = null;
			Statement st = null;
			ResultSet rs = null;
			
			String[] hh = new String[2];
			String cbzdid = "";
			String scb = "";
			StringBuffer sql = new StringBuffer("SELECT AA.ID AAID, AA.NEWQSDB FROM (SELECT QZ.ID, QZ.NEWQSDB FROM QSKZ QZ WHERE QZ.SHENGSHBZ = '0' AND QZ.SHISHBZ= '1' AND QZ.ZDID = '")
			.append(zdid).append("' AND QZ.NEWQSDB = (SELECT BB.NEWQSDB FROM (SELECT QD.NEWQSDB FROM QSKZ QD WHERE QD.SHENGSHBZ = '0' AND QD.SHISHBZ = '1' AND QD.ZDID = '")
			.append(zdid).append("' ORDER BY QD.NEWQSDB) BB WHERE ROWNUM = 1) ORDER BY QZ.QXTJTIME DESC) AA WHERE ROWNUM = 1 ");
			logger.info("获取信息修改申请最小生产标及申请id:"+sql.toString());
			try {
				connc = db.getConnection();
				st = connc.createStatement();
				rs = st.executeQuery(sql.toString());
				if(rs.next()){
					cbzdid = rs.getString("AAID");
					scb = rs.getString("NEWQSDB");
				}
			} catch (Exception e) {
				logger.error("获取信息修改申请最小生产标及申请id失败:"+e.getMessage());
				e.printStackTrace();
			}finally{
				db.free(rs, st, connc);
			}
			hh[0] = cbzdid;
			hh[1] = scb;
			return hh;
		}
		
		public synchronized boolean setXxxg(String xxxgid,String zlfh,String jlfh,String edhdl,String scb){
			boolean flag = false;
			Logger logger = Logger.getLogger(PdTestBewrite.class);
			DataBase db = new DataBase();
			Connection connc = null;
			Statement st = null;
			StringBuffer sql = new StringBuffer("UPDATE QSKZ QZ SET QZ.NEWZLFH=").append(zlfh)
			.append(",QZ.NEWJLFH=").append(jlfh).append(",QZ.NEWEDHDL=").append(edhdl)
			.append(",QZ.NEWQSDB=").append(scb).append(" WHERE QZ.ID=").append(xxxgid);
			
			try {
			connc = db.getConnection();
			st = connc.createStatement();
				connc.setAutoCommit(false);
			logger.info("整改工单更新区县申请直流,交流,额定,生产:"+sql.toString());
			int count = st.executeUpdate(sql.toString());
			connc.commit();
			flag = count>0?true:false;
			} catch (Exception e) {
				logger.error("整改工单更新区县申请直流,交流,额定,生产失败:"+e.getMessage());
				e.printStackTrace();
				try {
					db.rollback();
				} catch (DbException e1) {
					e1.printStackTrace();
				}
			}finally{
				try {
					connc.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				db.free(null, st, connc);
			}
			return flag;
		}
}
