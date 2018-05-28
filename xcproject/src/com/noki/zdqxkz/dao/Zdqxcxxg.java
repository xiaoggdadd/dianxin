package com.noki.zdqxkz.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.util.Query;
import com.noki.zdqxkz.javabean.XxxgBean;
import com.noki.zdqxkz.javabean.Zdqxkz;
import com.noki.zdqxkz.javabean.jcnh;

public class Zdqxcxxg {

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
	 * 站点信息修改审核--站点信息查询
	 * 
	 * @param whereStr
	 *            :查询条件
	 * @param loginId
	 *            ：用户登录id
	 * @return List<Zdinfo>：站点信息存入Zdinfo，Zdinfo存入list
	 */
	public synchronized List<Zdqxkz> getZdqxcx(String whereStr, String loginId) {
		List<Zdqxkz> list = new ArrayList<Zdqxkz>();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);


			sql = "SELECT RNDIQU(ZD.SHI) AS SHI, RNDIQU(ZD.XIAN) AS XIAN, RNDIQU(ZD.XIAOQU) AS XIAOQU,ZD.JZNAME,ZD.ID,QX.ID AS QXID,QX.SHISHBZ,QX.SHENGSHBZ,QX.SJLY,QX.DBID,QX.NOREASON, "
				    +" (CASE WHEN qx.bgdj='1' THEN 'A' WHEN QX.BGDJ='2' THEN 'B' WHEN QX.BGDJ='3' THEN 'C' WHEN QX.BGDJ='4' THEN 'D' END) BGDJ "
					+ " FROM QSKZ QX, ZHANDIAN ZD "
					+ " WHERE QX.ZDID = ZD.ID "
					+ whereStr
					+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))" + fzzdstr + ") ORDER BY ZD.ID";
			db.connectDb();
			System.out.println("站点信息修改审核--站点信息查询：" + sql);
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				Zdqxkz formbean = new Zdqxkz();
				formbean.setShi(rs.getString("SHI") != null ? rs
						.getString("SHI") : "");
				formbean.setXian(rs.getString("XIAN") != null ? rs
						.getString("XIAN") : "");
				formbean.setXiaoqu(rs.getString("XIAOQU") != null ? rs
						.getString("XIAOQU") : "");
				formbean.setZdname(rs.getString("JZNAME") != null ? rs
						.getString("JZNAME") : "");
				formbean.setZdid(rs.getString("ID") != null ? rs
						.getString("ID") : "");
				formbean.setId(rs.getString("QXID") != null ? rs
						.getString("QXID") : "");
				formbean.setShishbz(rs.getString("SHISHBZ") != null ? rs
						.getString("SHISHBZ") : "");
				formbean.setShengshbz(rs.getString("SHENGSHBZ") != null ? rs
						.getString("SHENGSHBZ") : "");
				formbean.setSjly(rs.getString("SJLY") != null ? rs
						.getString("SJLY") : "");
				formbean.setDbid(rs.getString("DBID")!=null?rs.getString("DBID"):"");
				formbean.setBghs(rs.getString("BGDJ")!=null?rs.getString("BGDJ"):"");
				formbean.setNoreason(rs.getString("NOREASON")!=null?rs.getString("NOREASON"):"");
				
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
	 * 权限控制--站点信息修改--站点原始信息查询是否重复
	 * 
	 * @param id
	 *            ：站点唯一标识zdid
	 * @return bz:bz--判断选择的站点有修改但是没有审核的信息；0--表示有，1表示无
	 */
	public synchronized String getZdqxcxxgxxsfcf(String zdid) {
		String bz = "1";
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			sql = "SELECT * FROM QSKZ QK  WHERE QK.SHISHBZ='0'AND QK.SHENGSHBZ='0'AND QK.ZDID='"
					+ zdid + "'";

			System.out.println("权限控制--站点信息修改--站点原始信息查询是否重复：" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if (rs.next()) {
				bz = "0";
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
		return bz;
	}

	/**
	 * 权限控制--站点信息修改--站点原始信息查询
	 * 
	 * @param id
	 *            ：站点唯一标识zdid
	 * @return formbean:formbean--bean存放站点详细信息
	 * @author mqy 修改其原来生产标，c
	 */
	public synchronized Zdqxkz getZdqxcxxgxx(String zdid,String dbid) {
		Zdqxkz formbean = new Zdqxkz();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			sql = "SELECT ZD.ID,ZD.BGSIGN,ZD.DIANFEI,ZD.EDHDL,ZD.ZLFH,ZD.JLFH,ZD.PROPERTY,"
					+ "ZD.STATIONTYPE,ZD.YFLX,ZD.GXXX,ZD.GDFS,ZD.ZGD,"
					+ "ZD.AREA,ZD.QYZT,ZD.G2,ZD.G3,"
					+ "ZD.ZPSL,ZD.ZSSL,ZD.CHANGJIA,ZD.JZLX,ZD.SHEBEI,ZD.BBU,"
					+ "ZD.RRU,ZD.YDSHEBEI,ZD.GXGWSL,RNDIQU(ZD.SHI) AS SHI,RNDIQU(ZD.XIAN) AS XIAN,RNDIQU(ZD.XIAOQU) AS XIAOQU,ZD.JZNAME,ZD.QSDBDL,DB.DANJIA,ZD.BZW,DB.DFZFLX,DB.DBDS,DB.XGBZW FROM ZHANDIAN ZD,DIANBIAO DB "
					+ " WHERE ZD.ID=DB.ZDID AND ZD.ID='"
					+ zdid + "' AND DB.DBID='"+dbid+"'";
			

			System.out.println("权限控制--站点信息修改--站点原始信息查询：" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formbean.setOldbgsign(rs.getString("BGSIGN") != null ? rs
						.getString("BGSIGN") : "0");
				formbean.setOlddianfei(rs.getString("DANJIA") != null ? rs
						.getString("DANJIA") : "0.0000");
				formbean.setOldedhdl(rs.getString("EDHDL") != null ? rs
						.getString("EDHDL") : "0.00");
				formbean
						.setOldzlfh(Double.toString(rs.getDouble("ZLFH")) != null ? Double
								.toString(rs.getDouble("ZLFH"))
								: "0.00");
				formbean
						.setOldjlfh(Double.toString(rs.getDouble("JLFH")) != null ? Double
								.toString(rs.getDouble("JLFH"))
								: "0.00");
				formbean.setOldproperty(rs.getString("PROPERTY") != null ? rs
						.getString("PROPERTY") : "");
				formbean
						.setOldstationtype(rs.getString("STATIONTYPE") != null ? rs
								.getString("STATIONTYPE")
								: "");
				formbean.setOldyflx(rs.getString("YFLX") != null ? rs
						.getString("YFLX") : "");
				formbean.setOldgxxx(rs.getString("GXXX") != null ? rs
						.getString("GXXX") : "");
				formbean.setOldgdfs(rs.getString("GDFS") != null ? rs
						.getString("GDFS") : "");
				formbean.setOldzgd(rs.getString("ZGD") != null ? rs
						.getString("ZGD") : "0");
				formbean.setOldarea(rs.getString("AREA") != null ? rs
						.getString("AREA") : "0");
				formbean.setOldqyzt(rs.getString("QYZT") != null ? rs
						.getString("QYZT") : "0");
				formbean.setOldg2(rs.getString("G2") != null ? rs
						.getString("G2") : "0");
				formbean.setOldg3(rs.getString("G3") != null ? rs
						.getString("G3") : "0");
				formbean.setOldzpsl(rs.getString("ZPSL") != null ? rs
						.getString("ZPSL") : "0");
				formbean.setOldzssl(rs.getString("ZSSL") != null ? rs
						.getString("ZSSL") : "0");
				formbean.setOldchangjia(rs.getString("CHANGJIA") != null ? rs
						.getString("CHANGJIA") : "");
				formbean.setOldjzlx(rs.getString("JZLX") != null ? rs
						.getString("JZLX") : "");
				formbean.setOldshebei(rs.getString("SHEBEI") != null ? rs
						.getString("SHEBEI") : "");
				formbean.setOldbbu(rs.getString("BBU") != null ? rs
						.getString("BBU") : "0");
				formbean.setOldrru(rs.getString("RRU") != null ? rs
						.getString("RRU") : "0");
				formbean.setOldydshebei(rs.getString("YDSHEBEI") != null ? rs
						.getString("YDSHEBEI") : "0");
				formbean.setOldgxgwsl(rs.getString("GXGWSL") != null ? rs
						.getString("GXGWSL") : "0");
				formbean.setShi(rs.getString("SHI") != null ? rs
						.getString("SHI") : "");
				formbean.setXian(rs.getString("XIAN") != null ? rs
						.getString("XIAN") : "");
				formbean.setXiaoqu(rs.getString("XIAOQU") != null ? rs
						.getString("XIAOQU") : "");
				formbean.setZdname(rs.getString("JZNAME") != null ? rs
						.getString("JZNAME") : "");
				formbean.setOldqsdbdl(rs.getString("QSDBDL") != null ? rs
						.getString("QSDBDL") : "");
				formbean.setZdbzw(rs.getString("BZW"));
				// formbean.setDbdj(rs.getString("DANJIA")!=null?rs.getString("DANJIA"):"0");
				formbean.setOlddfzflx(rs.getString("DFZFLX")!= null ? rs
						.getString("DFZFLX") : "");
				formbean.setOlddbds(rs.getString("DBDS")!= null ? rs
						.getString("DBDS") : "");
				formbean.setOldxgbzw(rs.getString("XGBZW")!= null ? rs
						.getString("XGBZW") : "");
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

	/**
	 * 删除权限控制--站点信息修改过的站点
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String delZdqxcxxg(String id) {
		String msg = "删除权限控制站点信息失败！";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			String delete = "DELETE FROM QSKZ WHERE ID ='" + id + "'";
			System.out.println("删除权限控制--站点信息修改过的站点:" + delete.toString());
			msg = "删除权限控制站点信息失败！";
			db.update(delete);
			msg = "删除权限控制站点信息成功！";
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

		return msg;
	}
	public synchronized String zdqxssh(String id,String qsdb,String flgg,String ggbz) {
		String msg = "删除权限控制站点信息失败！";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			String delete = "UPDATE QSKZ K SET K.NEWQSDB='"+qsdb+"',K.FLGG='"+flgg+"'"+("2".equals(ggbz)?",K.QXPDBZ='2'":"")+" WHERE K.ID ='" + id + "'";
			System.out.println("省市审核修改信息:" + delete.toString());
			msg = "省市审核修改信息失败！";
			db.update(delete);
			msg = "省市审核修改信息成功！";
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

		return msg;
	}
	
	public synchronized String queryFlgg(String id) {
		String flgg = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql = "SELECT K.FLGG FROM QSKZ K WHERE K.ID='"+id +"'";
			rs = db.queryAll(sql);
			if(rs.next()){
				flgg = rs.getString("FLGG")==null?"":rs.getString("FLGG");
			}
		} catch (Exception de) {
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

		return flgg;
	}

	/**
	 * 增加权限控制--站点信息修改过的站点--插入
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyZdqxcxxg(Zdqxkz formBean, String path,
			String filename) {
		String msg = "增加权限控制站点信息失败！";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			String updatesql = "insert into qskz(id,zdid,newbgsign,newdianfei,newedhdl,newjlfh,newzlfh,newproperty,"
					+ "newstationtype,newyflx,newgxxx,newgdfs,newzgd,newarea,newqyzt,newg2,newg3,newzpsl,newzssl,"
					+ "newchangjia,newjzlx,newshebei,newbbu,newrru,newydshebei,newgxgwsl,xgsm,xgyj,fjmc,qxtjr,qxtjbz,"
					+ "qxtjtime,qxpdbz,oldbgsign,olddianfei,oldedhdl,oldjlfh,oldzlfh,oldproperty,oldstationtype,"
					+ "oldyflx,oldgxxx,oldgdfs,oldzgd,oldarea,oldqyzt,oldg2,oldg3,oldzpsl,oldzssl,oldchangjia,"
					+ "oldjzlx,oldshebei,oldbbu,oldrru,oldydshebei,oldgxgwsl,filepath,biaoti,newqsdb,oldqsdb," 
					+ "dbid,dbds,xgbzw,newdfzflx,olddfzflx,oldqsdbdl,olddbds,oldxgbzw,FLGG) values('','"
					+ formBean.getZdid()
					+ "','"
					+ formBean.getNewbgsign()
					+ "',"
					+ formBean.getNewdianfei()
					+ ","
					+ formBean.getNewedhdl()
					+ ",'"
					+ formBean.getNewjlfh()
					+ "','"
					+ formBean.getNewzlfh()
					+ "','"
					+ formBean.getNewproperty()
					+ "','"
					+ formBean.getNewstationtype()
					+ "','"
					+ formBean.getNewyflx()
					+ "','"
					+ formBean.getNewgxxx()
					+ "','"
					+ formBean.getNewgdfs()
					+ "','"
					+ formBean.getNewzgd()
					+ "','"
					+ formBean.getNewarea()
					+ "','"
					+ formBean.getNewqyzt()
					+ "','"
					+ formBean.getNewg2()
					+ "','"
					+ formBean.getNewg3()
					+ "',"
					+ formBean.getNewzpsl()
					+ ","
					+ formBean.getNewzssl()
					+ ",'"
					+ formBean.getNewchangjia()
					+ "','"
					+ formBean.getNewjzlx()
					+ "','"
					+ formBean.getNewshebei()
					+ "',"
					+ formBean.getNewbbu()
					+ ","
					+ formBean.getNewrru()
					+ ","
					+ formBean.getNewydshebei()
					+ ","
					+ formBean.getNewgxgwsl()
					+ ",'"
					+ formBean.getXgsm()
					+ "','"
					+ formBean.getXgyj()
					+ "','"
					+ filename
					+ "','"
					+ formBean.getQxtjr()
					+ "','"
					+ formBean.getQxtjbz()
					+ "',to_date('"
					+ formBean.getQxtjtime()
					+ "','yyyy-mm-dd HH24:mi:ss'),'"
					+ formBean.getQxpdbz()
					+ "','"
					+ formBean.getOldbgsign()
					+ "',"
					+ formBean.getOlddianfei()
					+ ","
					+ formBean.getOldedhdl()
					+ ",'"
					+ formBean.getOldjlfh()
					+ "','"
					+ formBean.getOldzlfh()
					+ "','"
					+ formBean.getOldproperty()
					+ "','"
					+ formBean.getOldstationtype()
					+ "','"
					+ formBean.getOldyflx()
					+ "','"
					+ formBean.getOldgxxx()
					+ "','"
					+ formBean.getOldgdfs()
					+ "','"
					+ formBean.getOldzgd()
					+ "','"
					+ formBean.getOldarea()
					+ "','"
					+ formBean.getOldqyzt()
					+ "','"
					+ formBean.getOldg2()
					+ "','"
					+ formBean.getOldg3()
					+ "',"
					+ formBean.getOldzpsl()
					+ ","
					+ formBean.getOldzssl()
					+ ",'"
					+ formBean.getOldchangjia()
					+ "','"
					+ formBean.getOldjzlx()
					+ "','"
					+ formBean.getOldshebei()
					+ "',"
					+ formBean.getOldbbu()
					+ ","
					+ formBean.getOldrru()
					+ ","
					+ formBean.getOldydshebei()
					+ ","
					+ formBean.getOldgxgwsl()
					+ ",'"
					+ path
					+ "','"
					+ formBean.getBiaoti()
					+ "','"
					+formBean.getNewqsdbdl()
					+ "','"
					+formBean.getOldqsdbdl()
					+"','"+formBean.getDbid()
					+"','"+formBean.getDbds()
					+"','"+formBean.getDsbzw()
					+"','"+formBean.getNewdfzflx()
					+"','"+formBean.getOlddfzflx()
					+"','"+formBean.getOldqsdb()
					+"','"+formBean.getOlddbds()
					+"','"+formBean.getOldxgbzw()
					+"','"+formBean.getFlgg()
					+"')";
			System.out.println("增加权限控制--站点信息修改过的站点--插入:" + updatesql.toString());
			msg = "增加权限控制站点信息失败！";
			db.update(updatesql);
			msg = "增加权限控制站点信息成功！";
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

		return msg;
	}

	// 模糊查询站点
	public synchronized ArrayList getStationMhchexk(String loginId, String mc,
			String str) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		String zdmc = mc;
		if (zdmc.contains("?")) {
			System.out.println(zdmc.contains("?"));
			StringBuffer zdmc1 = new StringBuffer(zdmc);
			zdmc = zdmc1.substring(0, zdmc1.lastIndexOf("?"));
		}
		sql
				.append("SELECT Z.JZNAME,Z.ID,D.DBID,Z.BZW FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBYT='dbyt01'  AND  "
						+ "Z.SHSIGN = '1' "
						+ str
						+ " "
						+ " AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"
						+ loginId + "')");

		if (!"请输入".equals(mc)) {
			sql.append(" AND JZNAME LIKE '%" + zdmc + "%'");
		}

		sql.append(" ORDER BY Z.JZNAME");

		String result = "";
		DataBase db = new DataBase();
		try {
			System.out.println("模糊查询站点:" + sql);
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());

			Query query = new Query();
			list = query.query(rs);

			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
	 * 权限控制--站点信息修改--站点修改前后信息查询
	 * 
	 * @param id
	 *            ：站点唯一标识zdid
	 * @return formbean:formbean--bean存放站点详细信息
	 */
	public synchronized Zdqxkz modifyZdqxcxxgxx(String id) {
		Zdqxkz formBean = new Zdqxkz();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			sql = "SELECT ZDID,NEWBGSIGN,NEWDIANFEI,NEWEDHDL,NEWJLFH,NEWZLFH,NEWPROPERTY,"
					+ "NEWSTATIONTYPE,NEWYFLX,NEWGXXX,NEWGDFS,NEWZGD,NEWAREA,NEWQYZT,NEWG2,NEWG3,NEWZPSL,NEWZSSL,"
					+ "NEWCHANGJIA,NEWJZLX,NEWSHEBEI,NEWBBU,NEWRRU,NEWYDSHEBEI,NEWGXGWSL,XGSM,XGYJ,FJMC,QXTJR,QXTJBZ,"
					+ "QXTJTIME,QXPDBZ,OLDBGSIGN,OLDDIANFEI,OLDEDHDL,OLDJLFH,OLDZLFH,OLDPROPERTY,OLDSTATIONTYPE,"
					+ "OLDYFLX,OLDGXXX,OLDGDFS,OLDZGD,OLDAREA,OLDQYZT,OLDG2,OLDG3,OLDZPSL,OLDZSSL,OLDCHANGJIA,"
					+ "OLDJZLX,OLDSHEBEI,OLDBBU,OLDRRU,OLDYDSHEBEI,OLDGXGWSL,FILEPATH,BIAOTI,NEWQSDB,OLDQSDB,ZD.ZDID,ZD.DBID,ZD.DBDS,Z.BZW,NEWDFZFLX,OLDDFZFLX "
					+ " FROM QSKZ ZD,zhandian z WHERE z.id=zd.zdid and  ZD.ID='" + id + "'";

			System.out.println("权限控制--站点信息修改--站点修改前后信息查询：" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formBean.setZdid(rs.getString("ZDID") != null ? rs
						.getString("ZDID") : "");
				formBean.setNewbgsign(rs.getString("NEWBGSIGN") != null ? rs
						.getString("NEWBGSIGN") : "0");
				formBean.setNewdianfei(Double.toString(rs
						.getDouble("NEWDIANFEI")) != null ? Double.toString(rs
						.getDouble("NEWDIANFEI")) : "0.0000");
				formBean
						.setNewedhdl(Double.toString(rs.getDouble("NEWEDHDL")) != null ? Double
								.toString(rs.getDouble("NEWEDHDL"))
								: "0.00");
				formBean.setNewzlfh(rs.getString("NEWZLFH") != null ? rs
						.getString("NEWZLFH") : "0.00");
				formBean.setNewjlfh(rs.getString("NEWJLFH") != null ? rs
						.getString("NEWJLFH") : "0.00");
				formBean
						.setNewproperty(rs.getString("NEWPROPERTY") != null ? rs
								.getString("NEWPROPERTY")
								: "");
				formBean
						.setNewstationtype(rs.getString("NEWSTATIONTYPE") != null ? rs
								.getString("NEWSTATIONTYPE")
								: "");
				formBean.setNewyflx(rs.getString("NEWYFLX") != null ? rs
						.getString("NEWYFLX") : "");
				formBean.setNewgxxx(rs.getString("NEWGXXX") != null ? rs
						.getString("NEWGXXX") : "");
				formBean.setNewgdfs(rs.getString("NEWGDFS") != null ? rs
						.getString("NEWGDFS") : "");
				formBean.setNewzgd(rs.getString("NEWZGD") != null ? rs
						.getString("NEWZGD") : "0");
				formBean.setNewarea(rs.getString("NEWAREA") != null ? rs
						.getString("NEWAREA") : "0");
				formBean.setNewqyzt(rs.getString("NEWQYZT") != null ? rs
						.getString("NEWQYZT") : "0");
				formBean.setNewg2(rs.getString("NEWG2") != null ? rs
						.getString("NEWG2") : "0");
				formBean.setNewg3(rs.getString("NEWG3") != null ? rs
						.getString("NEWG3") : "0");
				formBean.setNewzpsl(rs.getString("NEWZPSL") != null ? rs
						.getString("NEWZPSL") : "0");
				formBean.setNewzssl(rs.getString("NEWZSSL") != null ? rs
						.getString("NEWZSSL") : "0");
				formBean
						.setNewchangjia(rs.getString("NEWCHANGJIA") != null ? rs
								.getString("NEWCHANGJIA")
								: "");
				formBean.setNewjzlx(rs.getString("NEWJZLX") != null ? rs
						.getString("NEWJZLX") : "");
				formBean.setNewshebei(rs.getString("NEWSHEBEI") != null ? rs
						.getString("NEWSHEBEI") : "");
				formBean.setNewbbu(rs.getString("NEWBBU") != null ? rs
						.getString("NEWBBU") : "0");
				formBean.setNewrru(rs.getString("NEWRRU") != null ? rs
						.getString("NEWRRU") : "0");
				formBean
						.setNewydshebei(rs.getString("NEWYDSHEBEI") != null ? rs
								.getString("NEWYDSHEBEI")
								: "0");
				formBean.setNewgxgwsl(rs.getString("NEWGXGWSL") != null ? rs
						.getString("NEWGXGWSL") : "0");
				formBean.setXgsm(rs.getString("XGSM") != null ? rs
						.getString("XGSM") : "");
				formBean.setXgyj(rs.getString("XGYJ") != null ? rs
						.getString("XGYJ") : "");
				formBean.setFjmc(rs.getString("FJMC") != null ? rs
						.getString("FJMC") : "");
				formBean.setOldbgsign(rs.getString("OLDBGSIGN") != null ? rs
						.getString("OLDBGSIGN") : "0");
				formBean.setOlddianfei(Double.toString(rs
						.getDouble("OLDDIANFEI")) != null ? Double.toString(rs
						.getDouble("OLDDIANFEI")) : "0.0000");
				formBean
						.setOldedhdl(Double.toString(rs.getDouble("OLDEDHDL")) != null ? Double
								.toString(rs.getDouble("OLDEDHDL"))
								: "0.00");
				formBean.setOldzlfh(rs.getString("OLDZLFH") != null ? rs
						.getString("OLDZLFH") : "0.00");
				formBean.setOldjlfh(rs.getString("OLDJLFH") != null ? rs
						.getString("OLDJLFH") : "0.00");
				formBean
						.setOldproperty(rs.getString("OLDPROPERTY") != null ? rs
								.getString("OLDPROPERTY")
								: "");
				formBean
						.setOldstationtype(rs.getString("OLDSTATIONTYPE") != null ? rs
								.getString("OLDSTATIONTYPE")
								: "");
				formBean.setOldyflx(rs.getString("OLDYFLX") != null ? rs
						.getString("OLDYFLX") : "");
				formBean.setOldgxxx(rs.getString("OLDGXXX") != null ? rs
						.getString("OLDGXXX") : "");
				formBean.setOldgdfs(rs.getString("OLDGDFS") != null ? rs
						.getString("OLDGDFS") : "");
				formBean.setOldzgd(rs.getString("OLDZGD") != null ? rs
						.getString("OLDZGD") : "0");
				formBean.setOldarea(rs.getString("OLDAREA") != null ? rs
						.getString("OLDAREA") : "0");
				formBean.setOldqyzt(rs.getString("OLDQYZT") != null ? rs
						.getString("OLDQYZT") : "0");
				formBean.setOldg2(rs.getString("OLDG2") != null ? rs
						.getString("OLDG2") : "0");
				formBean.setOldg3(rs.getString("OLDG3") != null ? rs
						.getString("OLDG3") : "0");
				formBean.setOldzpsl(rs.getString("OLDZPSL") != null ? rs
						.getString("OLDZPSL") : "0");
				formBean.setOldzssl(rs.getString("OLDZSSL") != null ? rs
						.getString("OLDZSSL") : "0");
				formBean
						.setOldchangjia(rs.getString("OLDCHANGJIA") != null ? rs
								.getString("OLDCHANGJIA")
								: "");
				formBean.setOldjzlx(rs.getString("OLDJZLX") != null ? rs
						.getString("OLDJZLX") : "");
				formBean.setOldshebei(rs.getString("OLDSHEBEI") != null ? rs
						.getString("OLDSHEBEI") : "");
				formBean.setOldbbu(rs.getString("OLDBBU") != null ? rs
						.getString("OLDBBU") : "0");
				formBean.setOldrru(rs.getString("OLDRRU") != null ? rs
						.getString("OLDRRU") : "0");
				formBean
						.setOldydshebei(rs.getString("OLDYDSHEBEI") != null ? rs
								.getString("OLDYDSHEBEI")
								: "0");
				formBean.setOldgxgwsl(rs.getString("OLDGXGWSL") != null ? rs
						.getString("OLDGXGWSL") : "0");
				formBean.setFilepath(rs.getString("FILEPATH") != null ? rs
						.getString("FILEPATH") : "");
				formBean.setBiaoti(rs.getString("BIAOTI") != null ? rs
						.getString("BIAOTI") : "");
				formBean.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "");
				formBean.setOldqsdbdl(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "");
				formBean.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
				formBean.setDbid(rs.getString("DBID")!=null?rs.getString("DBID"):"");
				formBean.setDbds(rs.getString("DBDS")!=null?rs.getString("DBDS"):"");
				formBean.setZdbzw(rs.getString("BZW")!=null?rs.getString("BZW"):"");
				formBean.setOlddfzflx(rs.getString("OLDDFZFLX")!=null?rs.getString("OLDDFZFLX"):"");
				formBean.setNewdfzflx(rs.getString("NEWDFZFLX")!=null?rs.getString("NEWDFZFLX"):"");
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
		return formBean;
	}
	/**
	 * 增加权限控制--站点信息修改过的站点--修改
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyZdqxcxxg1(Zdqxkz formBean, String path,
			String filename) {
		String msg = "修改权限控制站点信息失败！";
		DataBase db = new DataBase();
		
		
			String updatesql = "update qskz set SHISHBZ='0',SHENGSHBZ='0',SHISHR=NULL,SHENGSHR=NULL,SHISHSJ=NULL,SHENGSHSJ=NULL,QXLY=NULL,SJLY=NULL,newbgsign='"+formBean.getNewbgsign()+"',newdianfei="+formBean.getNewdianfei()+",newedhdl="+formBean.getNewedhdl()+
					",newjlfh='"+formBean.getNewjlfh()+"',newzlfh='"+formBean.getNewzlfh()+"',newproperty='"+formBean.getNewproperty()+"',newqsdb='"+formBean.getNewqsdbdl()+"',"
					+ "newstationtype='"+formBean.getNewstationtype()+"',newyflx='"+formBean.getNewyflx()+"',newgxxx='"+formBean.getNewgxxx()+
					"',newgdfs='"+formBean.getNewgdfs()+"',newzgd='"+formBean.getNewzgd()+"',newarea='"+formBean.getNewarea()+"',newqyzt='"+formBean.getNewqyzt()+
					"',newg2='"+formBean.getNewg2()+"',newg3='"+formBean.getNewg3()+"',newzpsl="+formBean.getNewzpsl()+",newzssl="+formBean.getNewzssl()+","
					+ "newchangjia='"+formBean.getNewchangjia()+"',newjzlx='"+formBean.getNewjzlx()+"',newshebei='"+formBean.getNewshebei()+"',newbbu="+formBean.getNewbbu()+
					",newrru="+formBean.getNewrru()+",newydshebei="+formBean.getNewydshebei()+",newgxgwsl="+formBean.getNewgxgwsl()+",xgsm='"+formBean.getXgsm()+
					"',xgyj='"+formBean.getXgyj()+"',fjmc='"+filename+"',qxtjr='"+formBean.getQxtjr()+"',qxtjbz='"+formBean.getQxtjbz()+"',"
					+ "qxtjtime=to_date('"+formBean.getQxtjtime()+"','yyyy-mm-dd HH24:mi:ss'),qxpdbz='"+formBean.getQxpdbz()+"'" +
//					",oldbgsign='"+formBean.getOldbgsign()+
//					"',olddianfei="+formBean.getOlddianfei()+",oldedhdl="+formBean.getOldedhdl()+",oldjlfh='"+formBean.getOldjlfh()+"',oldzlfh='"+formBean.getOldzlfh()+
//					"',oldproperty='"+formBean.getOldproperty()+"',oldstationtype='"+formBean.getOldstationtype()+"',oldyflx='"+formBean.getOldyflx()+
//					"',oldgxxx='"+formBean.getOldgxxx()+"',oldgdfs='"+formBean.getOldgdfs()+"',oldzgd='"+formBean.getOldzgd()+"',oldarea='"+formBean.getOldarea()+
//					"',oldqyzt='"+formBean.getOldqyzt()+"',oldg2='"+formBean.getOldg2()+"',oldg3='"+formBean.getOldg3()+"',oldzpsl="+formBean.getOldzpsl()+
//					",oldzssl="+formBean.getOldzssl()+",oldchangjia='"+formBean.getOldchangjia()+"',oldjzlx='"+formBean.getOldjzlx()+
//					"',oldshebei='"+formBean.getOldshebei()+"',oldbbu="+formBean.getOldbbu()+",oldrru="+formBean.getOldrru()+",oldydshebei="+formBean.getOldydshebei()+
//					",oldgxgwsl="+formBean.getOldgxgwsl()+
					",filepath='"+path+"',biaoti='"+formBean.getBiaoti()+"',dbds='"+formBean.getDbds()+"',xgbzw='"+formBean.getDsbzw()
					+"', newdfzflx='"+formBean.getNewdfzflx()+"',flgg='"+formBean.getFlgg()+"',bftg=null,bfbtg=null where id="+formBean.getId();
			try {
				db.connectDb();
			System.out
					.println("增加权限控制--站点信息修改过的站点--修改:" + updatesql.toString());
		//	msg = "修改权限控制站点信息失败！";
			db.update(updatesql); 
			db.commit();
			msg = "修改权限控制站点信息成功！";
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
		System.out.println(msg+"------------------------------------------------");
		return msg;
		
	}
	public synchronized Zdqxkz getDBId(String zdid,String time) {
	    ArrayList list = new ArrayList();
	    String sql = "";
	    DataBase db = new DataBase();
	    Zdqxkz formBean = new Zdqxkz();
	    ResultSet rs = null;
	    //调用负责站点条件函数
	    try {				
			  sql="select q.id from qskz q where q.zdid='"+zdid+"' and to_char(q.qxtjtime,'yyyy-mm-dd hh24:mi:ss')='"+time+"'";
			 System.out.println("对标管理区县申请上传查询id:"+sql);
			 db.connectDb();
		      rs = db.queryAll(sql.toString());	      
		     /* Query query=new Query();
		      zd = query.query(rs);*/
		      while(rs.next()){
		      formBean.setId(rs.getString(1));
		     //return formBean;
		      }
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
		return formBean;
	}
	public synchronized Zdqxkz getZdqxcxxgxxgx(String zdid) {
		Zdqxkz formbean = new Zdqxkz();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			sql=" SELECT S.SCB  FROM SCB S WHERE S.ZDID="+zdid+" ";
			System.out.println("11111:"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formbean.setScb(rs.getString("SCB")!=null?rs.getString("SCB"):"0");
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
	public synchronized jcnh getZdqxkzbl(String zdid) {
		jcnh formbean = new jcnh();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		try {
			sql="SELECT  MAX(J.NHNUMBER) NHJC, MAX(J.JYSCB) JYSCB, MAX(J.SJZ) SJZ,J.ZDID FROM T_BASICSCALIBRATION_TEMP1502 J WHERE J.ZDID='"+zdid+"' GROUP BY J.ZDID";//@UNEBD_81
			System.out.println("11111:"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formbean.setNhjcz(rs.getString("nhjc"));
				formbean.setJyscb(rs.getString("jyscb"));
				formbean.setSjz(rs.getString("sjz"));
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

	/**
	 * 增加权限控制--只降低标杆更新交流直流负荷、生产标、全省定标电量(站点)  生产标(scb)
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String updatezdjb(Zdqxkz formBean, String lg) {
		System.out.println("999999999999999999");
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		ResultSet rs7 = null;
		Statement st = null;
		Connection connc = null;
		String msg = "自动降标失败请重新录入！";
		KtxsDao dao = new KtxsDao();
		 List<XxxgBean> xxxglist=new ArrayList<XxxgBean>();
		   xxxglist=dao.xxxgch();
		 List<Ktxs> fylist = new ArrayList<Ktxs>();
			fylist = dao.getfwxs();
		 List<Ktxs> fylist1 = new ArrayList<Ktxs>();
			fylist1 = dao.getktxs();
			String syf="";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			String sff=df.format(new Date());
		System.out.println("月份："+sff.toString());
		String yf=sff.toString().substring(5,7);
		//-----------------
		int j = Integer.parseInt(yf);
		//-----------------
			if(yf.equals("01")){
				syf="YMONTH";
			}else if(yf.equals("02")){
				syf="EMONTH";
			}else if(yf.equals("03")){
				syf="SMONTH";
			}else if(yf.equals("04")){
				syf="SIMONTH";
			}else if(yf.equals("05")){
				syf="WMONTH";
			}else if(yf.equals("06")){
				syf="LMONTH";
			}else if(yf.equals("07")){
				syf="QMONTH";
			}else if(yf.equals("08")){
				syf="BMONTH";
			}else if(yf.equals("09")){
				syf="JMONTH";
			}else if(yf.equals("10")){
				syf="SHIMONTH";
			}else if(yf.equals("11")){
				syf="SYMONTH";
			}else if(yf.equals("12")){
				syf="SEMONTH";
			}
			String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
			for(Ktxs fw:fylist){
				fxxs=fw.getFxxs();
				bzw=fw.getFwsjbzw();
				System.out.println("标志位："+bzw);
				if(bzw.equals("1")){
					b1=fxxs;
				}else if(bzw.equals("2")){
					b2=fxxs;
				}else if(bzw.equals("3")){
					b3=fxxs;
				}
			}
			String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
			String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
			String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
			//接入网定义
			String dd3="";
			String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
			String dd18="",dd19="",dd20="",dd21="",dd22="";
			//乡镇支局
			String xx3="";
			String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
			String xx18="",xx19="",xx20="",xx21="",xx22="";
			//局用机房
			String jj3="";
			String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
			String jj18="",jj19="",jj20="",jj21="",jj22="";
			//营业网点
			String yy3="";
			String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
			String yy18="",yy19="",yy20="",yy21="",yy22="";
			//其他
			String qq3="";
			String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
			String qq18="",qq19="",qq20="",qq21="",qq22="";
			//IDC机房
			String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
			String ii18="",ii19="",ii20="",ii21="",ii22="";
			String ii3="";
			for(Ktxs kt:fylist1){
				bb1=kt.getKszlfh();
				bb2=kt.getJszlfh();
				bb4=kt.getSjbzw();
				bb3=kt.getJzktxs();//基站空调系数
				dd3=kt.getJrwktxs();//接入网的空调系数
				xx3=kt.getXzzjktxs();//乡镇支局的空调系数
				jj3=kt.getJyjfktxs();//局用机房空调系数
				yy3=kt.getYywdktxs();//营业网点空调系数
				ii3=kt.getIdcjfktxs();//IDC机房空调系数
				qq3=kt.getQtktxs();//其他空调系数
				if(bb4.equals("1")){
					//基站为bb
					bb5=bb1;
					bb6=bb2;
					bb7=bb3;
					//接入网为dd
					dd5=bb1;
					dd6=bb2;
					dd7=dd3;
					//乡镇支局
					xx5=bb1;
					xx6=bb2;
					xx7=xx3;
					//局用机房
					jj5=bb1;
					jj6=bb2;
					jj7=jj3;
					//营业网点
					yy5=bb1;
					yy6=bb2;
					yy7=yy3;
					//IDC机房
					ii5=bb1;
					ii6=bb2;
					ii7=ii3;
					//其他
					qq5=bb1;
					qq6=bb2;
					qq7=qq3;
					
					
				}
               if(bb4.equals("2")){
              	//基站为bb
					bb8=bb1;
					bb9=bb2;
					bb10=bb3;
					//接入网dd
					dd8=bb1;
					dd9=bb2;
					dd10=dd3;
					//乡镇支局
					xx8=bb1;
					xx9=bb2;
					xx10=xx3;
					//局用机房
					jj8=bb1;
					jj9=bb2;
					jj10=jj3;
					//营业网点
					yy8=bb1;
					yy9=bb2;
					yy10=yy3;
					//IDC机房
					ii8=bb1;
					ii9=bb2;
					ii10=ii3;
					//其他
					qq8=bb1;
					qq9=bb2;
					qq10=qq3;
					
				}
                if(bb4.equals("3")){
              	//基站为bb
              	  bb11=bb1;
					  bb12=bb2;
					  bb13=bb3;
					//接入网dd
					  dd11=bb1;
					  dd12=bb2;
					  dd13=dd3;
					//乡镇支局
					xx11=bb1;
					xx12=bb2;
					xx13=xx3;
					//====================
				//局用机房
					jj11=bb1;
					jj12=bb2;
					jj13=jj3;
					//营业网点
					yy11=bb1;
					yy12=bb2;
					yy13=yy3;
					//IDC机房
					ii11=bb1;
					ii12=bb2;
					ii13=ii3;
					//其他
					qq11=bb1;
					qq12=bb2;
					qq13=qq3;
}
               if(bb4.equals("4")){
              	//基站为bb
              	 bb14=bb1;
					bb15=bb2;
					bb16=bb3;
					//接入网dd
					dd14=bb1;
					dd15=bb2;
					dd16=dd3;
				//乡镇支局
					xx14=bb1;
					xx15=bb2;
					xx16=xx3;
					//===========
					//局用机房
					jj14=bb1;
					jj15=bb2;
					jj16=jj3;
					//营业网点
					yy14=bb1;
					yy15=bb2;
					yy16=yy3;
					//IDC机房
					ii14=bb1;
					ii15=bb2;
					ii16=ii3;
					//其他
					qq14=bb1;
					qq15=bb2;
					qq16=qq3;
}
               if(bb4.equals("5")){
              	 //基站bb
              	 bb17=bb1;
					bb18=bb2;
					bb19=bb3;
					//接入网dd
					 dd17=bb1;
					dd18=bb2;
					dd19=dd3;
				//乡镇支局
					xx17=bb1;
					xx18=bb2;
					xx19=xx3;
					//==========
					//局用机房
					jj17=bb1;
					jj18=bb2;
					jj19=jj3;
					//营业网点
					yy17=bb1;
					yy18=bb2;
					yy19=yy3;
					//IDC机房
					ii17=bb1;
					ii18=bb2;
					ii19=ii3;
					//其他
					qq17=bb1;
					qq18=bb2;
					qq19=qq3;
}
               if(bb4.equals("6")){
              	 //基站bb
              	 bb20=bb1;
					bb22=bb3;
					//==接入网dd
					 dd20=bb1;
					dd22=dd3;
				//乡镇支局
					xx20=bb1;
					xx22=xx3;
					//=========
					//局用机房
					jj20=bb1;
					jj22=jj3;
					//营业网点
					yy20=bb1;
					yy22=yy3;
					//IDC机房
					ii20=bb1;
					ii22=ii3;
					//其他
					qq20=bb1;
					qq22=qq3;
}
			}
			DataBase db = new DataBase();
			String sj = "SYSDATE";
		try {
			connc = db.getConnection();
			
			st = connc.createStatement();
			connc.setAutoCommit(false);
		
			if(formBean.getNewproperty().equals("zdsx01")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "SELECT S.SCB FROM SCB S WHERE S.ZDID = " + formBean.getZdid();
				rs1 = st.executeQuery(sql12);
				//从scb中查询 scb，如果有记录 则更新，如果没值 则 插入一条
				
				if(rs1.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" + formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12'," + formBean.getZdid() + "," + formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if (kts>0){//有空调
					//月份判断
					if (j >= 9) {//9月之后
						ssql = " update zhandian q set q.qsdbdl= " + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <=" + jj6
							+ " THEN " + jj7 + " WHEN aa.ZLFH >" + jj6 + " AND aa.ZLFH <=" + jj9 + " THEN " + jj10 + " " + "WHEN aa.ZLFH > " + jj9
							+ " AND aa.ZLFH <= " + jj12 + " THEN " + jj13 + " WHEN aa.ZLFH > " + jj12 + " AND aa.ZLFH <= " + jj15 + " THEN " + jj16
							+ " WHEN aa.ZLFH > " + jj15 + " AND aa.ZLFH <= " + jj18 + " THEN " + jj19 + " WHEN aa.ZLFH > " + jj18 + " THEN " + jj22
							+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf 
							+ " from yfxs y where y.shicode = q.shi)"
							+" ),q.scb=" + formBean.getNewqsdbdl() + " where q.id=(" + formBean.getZdid() + ")";
					} else {
						ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <" + jj6
							+ " THEN " + jj7 + " WHEN aa.ZLFH >=" + jj6 + " AND aa.ZLFH <" + jj8 + " THEN " + jj10 + " WHEN aa.ZLFH >= " + jj8
							+ " AND aa.ZLFH <" + jj11 + " THEN " + jj13 + " WHEN aa.ZLFH >= " + jj11 + " AND aa.ZLFH < " + jj14 + " THEN " + jj16
							+ " WHEN aa.ZLFH >= " + jj14 + " AND aa.ZLFH < " + jj17 + " THEN " + jj19 + " WHEN aa.ZLFH >= " + jj17 + " THEN "
							+ jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+ syf
							+ " from yfxs y where y.shicode = q.shi)"
							+" ),q.scb=" + formBean.getNewqsdbdl() + " where q.id="+ formBean.getZdid() + "";
					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + formBean.getNewqsdbdl() +", ZD.SCB = " + formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
		       System.out.println("sql2:"+sql2.toString());
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				st.executeUpdate(sql34);
				System.out.println("ssql:"+ssql.toString());
				System.out.println("sqlkongtiao:"+sqlkongtiao.toString());
				System.out.println("sql12:"+sql12.toString());
				System.out.println("sql3:"+sql3.toString());
				connc.commit();
				msg="自动降标成功！";
				
			}
			
			if(formBean.getNewproperty().equals("zdsx02")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){//基站
				
				System.out.println("wo zai zhe le ");
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "SELECT S.SCB FROM SCB S WHERE S.ZDID = " + formBean.getZdid();
				rs1 = st.executeQuery(sql12);
				//从scb中查询 scb，如果有记录 则更新，如果没值 则 插入一条
				if(rs1.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" + formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12'," + formBean.getZdid() + "," + formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
					if(kts>0){//有空调
						if (j >= 9) {
							ssql = " update zhandian q set q.qsdbdl= " + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <=" + bb6
									+ " THEN " + bb7 + " WHEN aa.ZLFH >" + bb6 + " AND aa.ZLFH <=" + bb9 + " THEN " + bb10 + " WHEN aa.ZLFH > " + bb9+ " AND aa.ZLFH <= "
									+ bb12 + " THEN " + bb13 + " WHEN aa.ZLFH > " + bb12 + " AND aa.ZLFH <= " + bb15 + " THEN " + bb16 + " WHEN aa.ZLFH > "
									+ bb15 + " AND aa.ZLFH <= " + bb18 + " THEN " + bb19 + " WHEN aa.ZLFH > " + bb18 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
									+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2 + ", 'fwlx03',"
									+ b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " + " ),q.scb=" + formBean + " where q.id=(" + formBean.getZdid() + ")";
						} else {
							ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6 + " THEN " + bb7
									+ " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8 + " AND aa.ZLFH <" + bb11
									+ " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16 + " WHEN aa.ZLFH >= " + bb14
									+ " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
									+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2
									+ ", 'fwlx03'," + b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " + " ),q.scb=" + formBean.getNewqsdbdl() + " where q.id=" + formBean.getZdid() + "";
						}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + formBean.getNewqsdbdl() +", ZD.SCB = " + formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
					}
					st.executeUpdate(ssql);
					//更新站点的交流负荷、直流负荷
					String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
					st.executeUpdate(sql2);
					//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
					String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'   where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
			System.out.println("sql34:"+sql34.toString());
					st.executeUpdate(sql34);
					connc.commit();  
					msg="自动降标成功！";
				}
			
			if(formBean.getNewproperty().equals("zdsx03")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				
				sql12 = "(select s.scb from scb s where s.zdid = " + formBean.getZdid() + ")";
				rs3 = st.executeQuery(sql12);
				if(rs3.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" + formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12'," + formBean.getZdid() + "," + formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if(kts>0){
					if (j >= 9) {
						ssql = " update zhandian q set q.qsdbdl= " + formBean.getNewqsdbdl() + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <=" + yy6 + " THEN "
								+ yy7 + " WHEN aa.ZLFH >" + yy6 + " AND aa.ZLFH <=" + yy9 + " THEN " + yy10 + " WHEN aa.ZLFH > " + yy9 + " AND aa.ZLFH <= "
								+ yy12 + " THEN " + yy13 + " WHEN aa.ZLFH > " + yy12 + " AND aa.ZLFH <= " + yy15 + " THEN " + yy16 + " WHEN aa.ZLFH > "
								+ yy15 + " AND aa.ZLFH <= " + yy18 + " THEN " + yy19 + " WHEN aa.ZLFH > " + yy18 + " THEN " + yy22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)"
								+",q.scb=" + formBean.getNewqsdbdl() + "  where q.id=(" + formBean.getZdid() + ")";

					} else {
						ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + " *1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <" + yy6
								+ " THEN " + yy7 + " WHEN aa.ZLFH >=" + yy6 + " AND aa.ZLFH <" + yy8 + " THEN " + yy10 + " WHEN aa.ZLFH >= "
								+ yy8 + " AND aa.ZLFH <" + yy11 + " THEN " + yy13 + " WHEN aa.ZLFH >= " + yy11 + " AND aa.ZLFH < " + yy14 + " THEN "
								+ yy16 + " WHEN aa.ZLFH >= " + yy14 + " AND aa.ZLFH < " + yy17 + " THEN " + yy19 + " WHEN aa.ZLFH >= " + yy17
								+ " THEN " + yy22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)"
								+",q.scb=" + formBean.getNewqsdbdl() + "  where q.id="+ formBean.getZdid() + "";
					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + formBean.getNewqsdbdl() +", ZD.SCB = " + formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				//更新站点的交流负荷、直流负荷
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				st.executeUpdate(sql34);
				connc.commit();
				msg="自动降标成功！";
			}
			if(formBean.getNewproperty().equals("zdsx04")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "(select s.scb from scb s where s.zdid = " + formBean.getZdid() + ")";
				rs4 = st.executeQuery(sql12);
				if (rs4.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" + formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-08'," + formBean.getZdid() + "," + formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if(kts>0){
					if (j >= 9) {
						ssql = " update zhandian q set q.qsdbdl= " + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <="
								+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >" + qq6 + " AND aa.ZLFH <=" + qq9 + " THEN " + qq10 + " WHEN aa.ZLFH > "
								+ qq9 + " AND aa.ZLFH <= " + qq12 + " THEN " + qq13 + " WHEN aa.ZLFH > " + qq12 + " AND aa.ZLFH <= " + qq15 + " THEN "
								+ qq16 + " WHEN aa.ZLFH > " + qq15 + " AND aa.ZLFH <= " + qq18 + " THEN " + qq19 + " WHEN aa.ZLFH > " + qq18
								+ " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" +formBean.getNewqsdbdl()+ " where q.id=(" + formBean.getZdid() + ")";

					} else {
						ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <" + qq6
								+ " THEN " + qq7 + " WHEN aa.ZLFH >=" + qq6 + " AND aa.ZLFH <" + qq8 + " THEN " + qq10 + " WHEN aa.ZLFH >= " + qq8
								+ " AND aa.ZLFH <" + qq11 + " THEN " + qq13 + " WHEN aa.ZLFH >= " + qq11 + " AND aa.ZLFH < " + qq14 + " THEN "
								+ qq16 + " WHEN aa.ZLFH >= " + qq14 + " AND aa.ZLFH < " + qq17 + " THEN " + qq19 + " WHEN aa.ZLFH >= " + qq17
								+ " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + formBean.getNewqsdbdl()+ " where q.id=" + formBean.getZdid() + "";
					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + formBean.getNewqsdbdl() +", ZD.SCB = " +formBean.getNewqsdbdl()  + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				//更新站点的交流负荷、直流负荷
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				st.executeUpdate(sql34);
				connc.commit();
				msg="自动降标成功！";
				
			}
			if(formBean.getNewproperty().equals("zdsx05")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "(select s.scb from scb s where s.zdid = " + formBean.getZdid() + ")";
				rs5 = st.executeQuery(sql12);
				if (rs5.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" + formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-11'," + formBean.getZdid() + "," + formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if(kts>0){
					if (j >= 9) {
						ssql = " update zhandian q set q.qsdbdl= " + formBean.getNewqsdbdl() + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + dd5 + " AND aa.ZLFH <="
							+ dd6 + " THEN " + dd7 + " WHEN aa.ZLFH >" + dd6 + " AND aa.ZLFH <=" + dd9 + " THEN " + dd10 + " WHEN aa.ZLFH > " + dd9
							+ " AND aa.ZLFH <= " + dd12 + " THEN " + dd13 + " WHEN aa.ZLFH > " + dd12 + " AND aa.ZLFH <= " + dd15 + " THEN "
							+ dd16 + " WHEN aa.ZLFH > " + dd15 + " AND aa.ZLFH <= " + dd18 + " THEN " + dd19 + " WHEN aa.ZLFH > " + dd18 + " THEN "
							+ dd22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
							+",q.scb=" + formBean.getNewqsdbdl() + " where q.id=(" + formBean.getZdid() + ")";
					} else {
						ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + "*1 " + "+(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6
								+ " THEN " + bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8
								+ " AND aa.ZLFH <" + bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN "
								+ bb16 + " WHEN aa.ZLFH >= " + bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17
								+ " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
								+ " from yfxs y where y.shicode = q.shi),q.scb=" + formBean.getNewqsdbdl() + " " + " where q.id=" + formBean.getZdid() + "";
					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + formBean.getNewqsdbdl() +", ZD.SCB = " + formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				//更新站点的交流负荷、直流负荷
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
				System.out.println("交流负荷："+sql2.toString());
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为9(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				System.out.println("审核标志位："+sql34.toString());
				st.executeUpdate(sql34);
				connc.commit();
				msg="自动降标成功！";
			}
			if(formBean.getNewproperty().equals("zdsx06")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "(select s.scb from scb s where s.zdid = " + formBean.getZdid() + ")";
				rs6 = st.executeQuery(sql12);
				if (rs6.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" +  formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid();
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12'," + formBean.getZdid() + "," +  formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if(kts>0){
					if (j >= 9) {
						ssql = " update zhandian q set q.qsdbdl= " +  formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <=" + xx6 + " THEN "
								+ xx7 + " WHEN aa.ZLFH >" + xx6 + " AND aa.ZLFH <=" + xx9 + " THEN " + xx10 + "WHEN aa.ZLFH > " + xx9 + " AND aa.ZLFH <= "
								+ xx12 + " THEN " + xx13 + " WHEN aa.ZLFH > " + xx12 + " AND aa.ZLFH <= " + xx15 + " THEN " + xx16 + " WHEN aa.ZLFH > "
								+ xx15 + " AND aa.ZLFH <= " + xx18 + " THEN " + xx19 + " WHEN aa.ZLFH > " + xx18 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" +  formBean.getNewqsdbdl() + " where q.id=(" + formBean.getZdid() + ")";

					} else {
						ssql = "update zhandian q set q.qsdbdl=" +  formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <" + xx6 + " THEN "
								+ xx7 + " WHEN aa.ZLFH >=" + xx6 + " AND aa.ZLFH <" + xx8 + " THEN " + xx10 + " WHEN aa.ZLFH >= " + xx8 + " AND aa.ZLFH <"
								+ xx11 + " THEN " + xx13 + " WHEN aa.ZLFH >= " + xx11 + " AND aa.ZLFH < " + xx14 + " THEN " + xx16 + " WHEN aa.ZLFH >= "
								+ xx14 + " AND aa.ZLFH < " + xx17 + " THEN " + xx19 + " WHEN aa.ZLFH >= " + xx17 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)" +" ),q.scb=" +  formBean.getNewqsdbdl() + " where q.id=" + formBean.getZdid() + "";

					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " +  formBean.getNewqsdbdl() +", ZD.SCB = " +  formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				//更新站点的交流负荷、直流负荷
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				st.executeUpdate(sql34);
				connc.commit();
				msg="自动降标成功！";
			}
			if(formBean.getNewproperty().equals("zdsx07")&&(formBean.getQxpdbz().equals("3")||formBean.getQxpdbz().equals("4"))){
				String ssql = "", sql3 = "", sql12 = "";
				String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ formBean.getZdid()+"'";//查询 有无 空调
				rs = st.executeQuery(sqlkongtiao);
				int kts = 0;
				if(rs.next()){
					kts = rs.getInt("KTS");
				}
				sql12 = "(select s.scb from scb s where s.zdid = " + formBean.getZdid() + ")";
				rs7 = st.executeQuery(sql12);
				if (rs7.next()) {
					sql3 = "UPDATE SCB S SET S.SCB=" +  formBean.getNewqsdbdl() + " WHERE S.ZDID =" + formBean.getZdid() + "";
					st.executeUpdate(sql3);
				}else{
					String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12'," + formBean.getZdid() + "," +  formBean.getNewqsdbdl() + ",'" + lg + "'," + sj + "," + 0 + ")";
					st.executeUpdate(sql22);
				}
				if(kts>0){
					if (j >= 9) {
						ssql = " update zhandian q set q.qsdbdl= " +  formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <=" + ii6
								+ " THEN " + ii7 + " WHEN aa.ZLFH >" + ii6 + " AND aa.ZLFH <=" + ii9 + " THEN " + ii10 + " WHEN aa.ZLFH > " + ii9
								+ " AND aa.ZLFH <= " + ii12 + " THEN " + ii13 + " WHEN aa.ZLFH > " + ii12 + " AND aa.ZLFH <= " + ii15 + " THEN " + ii16
								+ " WHEN aa.ZLFH > " + ii15 + " AND aa.ZLFH <= " + ii18 + " THEN " + ii19 + " WHEN aa.ZLFH > " + ii18 + " THEN " + ii22
								+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
								+ " ),q.scb=" + formBean.getNewqsdbdl() + " where q.id=(" + formBean.getZdid() + ")";
					} else {
						ssql = "update zhandian q set q.qsdbdl=" + formBean.getNewqsdbdl() + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <" + ii6
								+ " THEN " + ii7 + " WHEN aa.ZLFH >=" + ii6 + " AND aa.ZLFH <" + ii8 + " THEN " + ii10 + " WHEN aa.ZLFH >= " + ii8
								+ " AND aa.ZLFH <" + ii11 + " THEN " + ii13 + " WHEN aa.ZLFH >= " + ii11 + " AND aa.ZLFH < " + ii14 + " THEN " + ii16
								+ " WHEN aa.ZLFH >= " + ii14 + " AND aa.ZLFH < " + ii17 + " THEN " + ii19 + " WHEN aa.ZLFH >= " + ii17 + " THEN "
								+ ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
								+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + formBean.getNewqsdbdl()+ " where q.id=" + formBean.getZdid() + "";
					}
				}else{
					ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " +  formBean.getNewqsdbdl() +", ZD.SCB = " +  formBean.getNewqsdbdl() + " where ZD.ID=" + formBean.getZdid();
				}
				st.executeUpdate(ssql);
				//更新站点的交流负荷、直流负荷
				String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.ZLFH=" + formBean.getNewzlfh()+",ZD.JLFH="+formBean.getNewjlfh()+",ZD.EDHDL="+formBean.getNewqsdbdl()+" WHERE ZD.ID="+formBean.getZdid();
				st.executeUpdate(sql2);
				//更新qskz表里的标志位 市级审核自动通、省级审核自动通过,部分通过字段为3(标杆自动通过)
				String sql34 = "update QSKZ c set c.SHENGSHBZ='1',c.shishbz='1',c.qxpdbz='2',c.shishsj=SYSDATE,c.shengshsj=SYSDATE,c.BFTG='9'  where  c.zdid in (" + formBean.getZdid() + ") and c.id='"+formBean.getId()+"'";
				st.executeUpdate(sql34);
				connc.commit();
				msg="自动降标成功！";
			}
		
			
		} catch (Exception de) {
			try {
				connc.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(rs, null, null);
			db.free(rs1, null, null);
			db.free(rs2, null, null);
			db.free(rs3, null, null);
			db.free(rs4, null, null);
			db.free(rs5, null, null);
			db.free(rs6, null, null);
			db.free(rs7, st, connc);
		}


		return msg;
	}
	/**
	 * 增加权限控制--站点信息修标杆等级识别
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized int zdbgdj(Zdqxkz formBean,String lg) {
		System.out.println("wo zai zhel i");
		int i=0;
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null; 
		Zdqxkz zdbz=new Zdqxkz();
		Zdqxkz zdbzz=new Zdqxkz();
		try {
			 db.connectDb();
			//首先获取站点等级标志 省审核通过的 该站点最新的标杆等级 A-1 B-2 C-3 D-4 
			String getbgdj="SELECT CC.ZDID,K.ID,CC.SJ,K.BGDJ FROM (SELECT MAX(K.SHENGSHSJ)sj, K.ZDID FROM QSKZ K  WHERE K.SHENGSHBZ='1' AND K.BGDJ<>'5' AND K.ZDID = '"+formBean.getZdid()+"' GROUP BY K.ZDID) CC, QSKZ K " +
					" WHERE CC.ZDID = K.ZDID AND  CC.SJ=K.SHENGSHSJ AND " +
					" K.ZDID='"+formBean.getZdid()+"'";
		System.out.println("getbgdj:"+getbgdj.toString());	
			rs = db.queryAll(getbgdj.toString());
		   while(rs.next()){
			   zdbz.setBghs(rs.getString("BGDJ")!=null?rs.getString("BGDJ"):"");
			   zdbz.setShengshsj(rs.getString("SJ"));
			   zdbz.setZdid(rs.getString("ZDID"));
		   }
		   System.out.println("wo zai zhel i1");
		  
		   //rs1=db.queryAll(sql.toString());
		    String zlfh="",jlfh="";//交、直流负荷
		    String fgsdb="";//分公司定标
		    String nhjcz="",jyscb="",sjz="";//能耗基础值，建议生产标，实际值
		    String sql4="SELECT  MAX(J.NHNUMBER) NHJC, MAX(J.JYSCB) JYSCB, MAX(J.SJZ) SJZ,J.ZDID FROM T_BASICSCALIBRATION_TEMP1502 J WHERE J.ZDID='"+formBean.getZdid()+"' GROUP BY J.ZDID";//@UNEBD_81
		    System.out.println("sql4:"+sql4.toString());
		    jcnh nh=new jcnh();
		    rs2=db.queryAll(sql4.toString());
		    while(rs2.next()){
		    	System.out.println("wo zai zhel i3");
		    	nh.setJyscb(rs2.getString("JYSCB"));
		    	nh.setNhjcz(rs2.getString("NHJC"));
		    	nh.setSjz(rs2.getString("SJZ"));
		    	nh.setZdid(rs2.getString("ZDID"));
		    }
		    
		    System.out.println("jyscb:"+nh.getJyscb()+" nhjc:"+nh.getNhjcz()+" sjz:"+nh.getSjz()+" zdid:"+nh.getZdid());
		    
		    //如果标杆等级为空的话则此站点还未标记等级  标记等级
		    zlfh=formBean.getNewzlfh();
		    jlfh=formBean.getNewjlfh();
		    double d1=0,d2=0,d3=0,d4=0,d5=0,d6=1.52,d7=5.28,d8=0,d9=0,d10=0,d11=0,d12=0,d13=0,d14=0,d15=0,d16=0;
			 d4= Double.parseDouble(zlfh);
			 d5= Double.parseDouble(jlfh);
			 d10=Double.parseDouble(formBean.getNewqsdbdl()!=null?formBean.getNewqsdbdl():"0");//分公司标
			 d1= Double.parseDouble(nh.getNhjcz()!=null?nh.getNhjcz():"0.00");//能耗基础值
			 d2= Double.parseDouble(nh.getJyscb()!=null?nh.getJyscb():"0.00");//建议生产标
			 d3= Double.parseDouble(nh.getSjz()!=null?nh.getSjz():"0.00");//实际值
			 
			 d8=d4*d6;//直流电量
			 d9=d5*d7;//交流电量
			 d11=Math.abs((d1-d10)/d10);//能耗基础值绝对值占比
			 d12=Math.abs((d2-d10)/d10);//建议生产标绝对值占比
			 d13=Math.abs((d3-d10)/d10);//实际值绝对值占比
			 d14=Math.abs((d8-d10)/d10);//直流电量绝对值占比
			 d15=Math.abs((d9-d10)/d10);//交流电量绝对值占比
			 if(d1==0.00){
				 d11=0.0;
			 }if(d2==0.00){
				 d12=0;
			 }
			 if(d3==0.00){
				 d13=0;
			 }
			 d16=Double.parseDouble(formBean.getNewedhdl());
			 System.out.println(rs.next());
			 System.out.println("能耗基础值绝对值占比:"+d11);
			 System.out.println("建议生产标绝对值占比:"+d12);
			 System.out.println("实际值绝对值占比:"+d13);
			 System.out.println("直流电量绝对值占比:"+d14);
			 System.out.println("交流电量绝对值占比:"+d15);
			 System.out.println("jjj:"+rs.next());
			 boolean b=d16==d10;
			 System.out.println("ed:"+d16+" qsdb:"+d10+" d16==d10?:"+b);
			 //新增一条为当前最大一条
			   String sql="(SELECT MAX(K.ID)ID,K.ZDID FROM QSKZ K WHERE K.ZDID='"+formBean.getZdid()+"' GROUP BY K.ZDID)";
			  rs1=db.queryAll(sql.toString());
			  System.out.println("sql:"+sql.toString());
			   if(rs1.next()){
				   System.out.println("wo zai zhel i2");
				   zdbzz.setId(rs1.getString("ID"));
				   zdbzz.setZdid(rs1.getString("ZDID"));
				   formBean.setId(rs1.getString("ID"));
				   if(((d11<=0.1&&d12<=0.1)||(d11<=0.1&&d13<=0.1)||(d12<=0.1&&d13<=0.1))&&d14<=0.1&&d15<=0.1){
						  formBean.setBghs("1");
					  }else
					  //------B-2等级
					   if(((d11<=0.15&&d12<=0.15)||(d11<=0.15&&d13<=0.15)||(d12<=0.15&&d13<=0.15))&&d14<=0.15&&d15<=0.15){
						   formBean.setBghs("2");
					   }
					  //------C-3等级
					   else if(((d11<=0.2&&d12<=0.2)||(d11<=0.2&&d13<=0.2)||(d12<=0.2&&d13<=0.2))&&d14<=0.2&&d15<=0.2){
						   formBean.setBghs("3");
					   }
					  //------D-4等级
					   else if(((d11<=0.25&&d12<=0.25)||(d11<=0.25&&d13<=0.25)||(d12<=0.25&&d13<=0.25))&&d14<=0.25&&d15<=0.25){
						   formBean.setBghs("4");			  
					   }else {
						   formBean.setBghs("5");
					   }
				   
			   }
			   //初始等级 前期无级别
		  if(null==zdbz.getBghs()||zdbz.getBghs().equals("")){
			  String s="";
			  System.out.println("wo zai zhel i4");
			
			  //------A-1等级
			  if(((d11<=0.1&&d12<=0.1)||(d11<=0.1&&d13<=0.1)||(d12<=0.1&&d13<=0.1))&&d14<=0.1&&d15<=0.1){
				  Zdqxcxxg zg=new Zdqxcxxg();
				   String msga= zg.updatezdjb(formBean,lg);
				   //省部分通过字段为3(标杆自动通过)
				  s="UPDATE QSKZ K SET K.BGDJ='1',k.shengshbz='1',k.qxpdbz='2',k.shishbz='1',k.shishsj=SYSDATE,k.shengshsj=SYSDATE,k.BFTG='9' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID="+formBean.getId()+" ";
				  formBean.setBghs("1");
			  }else
			  //------B-2等级
			   if(((d11<=0.15&&d12<=0.15)||(d11<=0.15&&d13<=0.15)||(d12<=0.15&&d13<=0.15))&&d14<=0.15&&d15<=0.15){
				   s="UPDATE QSKZ K SET K.BGDJ='2',K.SHISHBZ='0',K.SHENGSHBZ='0',K.QXPDBZ='2' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID="+zdbzz.getId()+"";
				   formBean.setBghs("2");
			   }
			  //------C-3等级
			   else if(((d11<=0.2&&d12<=0.2)||(d11<=0.2&&d13<=0.2)||(d12<=0.2&&d13<=0.2))&&d14<=0.2&&d15<=0.2){
				   s="UPDATE QSKZ K SET K.BGDJ='3',K.SHISHBZ='0',K.SHENGSHBZ='0',K.QXPDBZ='2'  WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID="+zdbzz.getId()+" ";
				   formBean.setBghs("3");
			   }
			  //------D-4等级
			   else if(((d11<=0.25&&d12<=0.25)||(d11<=0.25&&d13<=0.25)||(d12<=0.25&&d13<=0.25))&&d14<=0.25&&d15<=0.25){
				   s="UPDATE QSKZ K SET K.BGDJ='4',K.SHISHBZ='0',K.SHENGSHBZ='0',K.QXPDBZ='2'  WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID="+zdbzz.getId()+" ";
				   formBean.setBghs("4");			  
			   }  
			   else 
			  {
				   s="UPDATE QSKZ K SET K.SHENGSHBZ ='0',K.SHISHBZ='2',K.QXPDBZ='2' WHERE K.ZDID ='"+formBean.getZdid()+"' AND K.ID="+zdbzz.getId()+"";
				   formBean.setBghs("5");
			  
			  }
			  System.out.println("插入时间："+s.toString());
			  int h=0;
			  
				h=  db.update(s.toString());
				  if(h>0){
					  i=1;
				  }
			System.out.println("s:"+s.toString());
		
		  }
		 
			 
		  if (zdbz.getBghs()!=null&&!zdbz.getBghs().equals("")){
			  String sql3="";
			  System.out.println("wo zai zhel i5");
			 
			 System.out.println("zdbz.getBghs():"+zdbz.getBghs()+" formBean.getBghs():"+formBean.getBghs());
			 if((null==formBean.getBghs()||formBean.getBghs().equals("null")||formBean.getBghs().equals(""))){
				 sql3=" UPDATE QSKZ K SET K.SHISHBZ='0',K.SHENGSHBZ='0',K.QXPDBZ='2',K.BGDJ='"+formBean.getBghs()+"' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID='"+formBean.getId()+"'";	  
			 }
			  // 降级 市级不通过、省级不通过
			 else  if((Integer.parseInt(zdbz.getBghs())<Integer.parseInt(formBean.getBghs()))){
			    sql3=" UPDATE QSKZ K SET K.SHISHBZ='2',K.SHENGSHBZ='2',K.NOREASON='标杆等级降级',K.QXPDBZ='2',K.BGDJ='"+formBean.getBghs()+"' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID='"+formBean.getId()+"'";	  
			
			  }
			  // 未降级市级审核、省级审核
			  else if((Integer.parseInt(zdbz.getBghs())>=Integer.parseInt(formBean.getBghs()))){
			    sql3="UPDATE QSKZ K SET K.SHISHBZ='0',K.SHENGSHBZ='0',K.QXPDBZ='2',K.BGDJ='"+formBean.getBghs()+"' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID='"+formBean.getId()+"'";
			  }
			  else if(formBean.getBghs().equals("5")) {
				  sql3="UPDATE QSKZ K SET K.SHISHBZ='2',K.SHENGSHBZ='2',K.NOREASON='抄表比例严重，大于25%',K.QXPDBZ='2',K.BGDJ='"+formBean.getBghs()+"' WHERE K.ZDID='"+formBean.getZdid()+"' AND K.ID='"+formBean.getId()+"'";
			  }
			   System.out.println("降级sql："+sql3.toString());
			   int j=0;
				j=  db.update(sql3.toString());
				if(j>0){
					i=2;
				}
		  }
		  
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (Exception dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {
				if(db!=null){
				db.close();
				}
				if(rs!=null){
					rs.close();
					}
				if(rs1!=null){
					rs1.close();
				}
				if(rs2!=null){
					rs2.close();
				}
				if(rs3!=null){
					rs3.close();
				}
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return i;
	}
	public synchronized Zdqxkz maxqskzid(String zdid) {
		Zdqxkz formBean = new Zdqxkz();
		Zdqxkz z=new Zdqxkz();
		String sql = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			db.connectDb();
			
			String sql1="SELECT max(k.id) as idc,k.zdid FROM QSKZ K WHERE K.ZDID='"+zdid+"' group by k.zdid";
			System.out.println("sql1:"+sql1.toString());
			rs1=db.queryAll(sql1.toString());
			
			while(rs1.next()){
				z.setId(rs1.getString("idc"));
				z.setZdid(rs1.getString("zdid"));
			}
			
			
		
			sql = "SELECT ID,ZDID,NEWBGSIGN,NEWDIANFEI,NEWEDHDL,NEWJLFH,NEWZLFH,NEWPROPERTY,"
					+ "NEWSTATIONTYPE,NEWYFLX,NEWGXXX,NEWGDFS,NEWZGD,NEWAREA,NEWQYZT,NEWG2,NEWG3,NEWZPSL,NEWZSSL,"
					+ "NEWCHANGJIA,NEWJZLX,NEWSHEBEI,NEWBBU,NEWRRU,NEWYDSHEBEI,NEWGXGWSL,XGSM,XGYJ,FJMC,QXTJR,QXTJBZ,"
					+ "QXTJTIME,QXPDBZ,OLDBGSIGN,OLDDIANFEI,OLDEDHDL,OLDJLFH,OLDZLFH,OLDPROPERTY,OLDSTATIONTYPE,"
					+ "OLDYFLX,OLDGXXX,OLDGDFS,OLDZGD,OLDAREA,OLDQYZT,OLDG2,OLDG3,OLDZPSL,OLDZSSL,OLDCHANGJIA,"
					+ "OLDJZLX,OLDSHEBEI,OLDBBU,OLDRRU,OLDYDSHEBEI,OLDGXGWSL,FILEPATH,BIAOTI,NEWQSDB,OLDQSDB,ZD.ZDID,ZD.DBID,ZD.DBDS,ZD.QXPDBZ "
					+ " FROM QSKZ ZD WHERE ZD.ID='" + z.getId() + "' and zd.zdid='"+z.getZdid()+"'";

			System.out.println("权限控制--站点信息修改--站点修改前后信息查询：" + sql);
			
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				formBean.setId(rs.getString("ID")!=null?rs.getString("ID"):"");
				formBean.setZdid(rs.getString("ZDID") != null ? rs
						.getString("ZDID") : "");
				formBean.setNewbgsign(rs.getString("NEWBGSIGN") != null ? rs
						.getString("NEWBGSIGN") : "0");
				formBean.setNewdianfei(Double.toString(rs
						.getDouble("NEWDIANFEI")) != null ? Double.toString(rs
						.getDouble("NEWDIANFEI")) : "0.0000");
				formBean
						.setNewedhdl(Double.toString(rs.getDouble("NEWEDHDL")) != null ? Double
								.toString(rs.getDouble("NEWEDHDL"))
								: "0.00");
				formBean.setNewzlfh(rs.getString("NEWZLFH") != null ? rs
						.getString("NEWZLFH") : "0.00");
				formBean.setNewjlfh(rs.getString("NEWJLFH") != null ? rs
						.getString("NEWJLFH") : "0.00");
				formBean
						.setNewproperty(rs.getString("NEWPROPERTY") != null ? rs
								.getString("NEWPROPERTY")
								: "");
				formBean
						.setNewstationtype(rs.getString("NEWSTATIONTYPE") != null ? rs
								.getString("NEWSTATIONTYPE")
								: "");
				formBean.setNewyflx(rs.getString("NEWYFLX") != null ? rs
						.getString("NEWYFLX") : "");
				formBean.setNewgxxx(rs.getString("NEWGXXX") != null ? rs
						.getString("NEWGXXX") : "");
				formBean.setNewgdfs(rs.getString("NEWGDFS") != null ? rs
						.getString("NEWGDFS") : "");
				formBean.setNewzgd(rs.getString("NEWZGD") != null ? rs
						.getString("NEWZGD") : "0");
				formBean.setNewarea(rs.getString("NEWAREA") != null ? rs
						.getString("NEWAREA") : "0");
				formBean.setNewqyzt(rs.getString("NEWQYZT") != null ? rs
						.getString("NEWQYZT") : "0");
				formBean.setNewg2(rs.getString("NEWG2") != null ? rs
						.getString("NEWG2") : "0");
				formBean.setNewg3(rs.getString("NEWG3") != null ? rs
						.getString("NEWG3") : "0");
				formBean.setNewzpsl(rs.getString("NEWZPSL") != null ? rs
						.getString("NEWZPSL") : "0");
				formBean.setNewzssl(rs.getString("NEWZSSL") != null ? rs
						.getString("NEWZSSL") : "0");
				formBean
						.setNewchangjia(rs.getString("NEWCHANGJIA") != null ? rs
								.getString("NEWCHANGJIA")
								: "");
				formBean.setNewjzlx(rs.getString("NEWJZLX") != null ? rs
						.getString("NEWJZLX") : "");
				formBean.setNewshebei(rs.getString("NEWSHEBEI") != null ? rs
						.getString("NEWSHEBEI") : "");
				formBean.setNewbbu(rs.getString("NEWBBU") != null ? rs
						.getString("NEWBBU") : "0");
				formBean.setNewrru(rs.getString("NEWRRU") != null ? rs
						.getString("NEWRRU") : "0");
				formBean
						.setNewydshebei(rs.getString("NEWYDSHEBEI") != null ? rs
								.getString("NEWYDSHEBEI")
								: "0");
				formBean.setNewgxgwsl(rs.getString("NEWGXGWSL") != null ? rs
						.getString("NEWGXGWSL") : "0");
				formBean.setXgsm(rs.getString("XGSM") != null ? rs
						.getString("XGSM") : "");
				formBean.setXgyj(rs.getString("XGYJ") != null ? rs
						.getString("XGYJ") : "");
				formBean.setFjmc(rs.getString("FJMC") != null ? rs
						.getString("FJMC") : "");
				formBean.setOldbgsign(rs.getString("OLDBGSIGN") != null ? rs
						.getString("OLDBGSIGN") : "0");
				formBean.setOlddianfei(Double.toString(rs
						.getDouble("OLDDIANFEI")) != null ? Double.toString(rs
						.getDouble("OLDDIANFEI")) : "0.0000");
				formBean
						.setOldedhdl(Double.toString(rs.getDouble("OLDEDHDL")) != null ? Double
								.toString(rs.getDouble("OLDEDHDL"))
								: "0.00");
				formBean.setOldzlfh(rs.getString("OLDZLFH") != null ? rs
						.getString("OLDZLFH") : "0.00");
				formBean.setOldjlfh(rs.getString("OLDJLFH") != null ? rs
						.getString("OLDJLFH") : "0.00");
				formBean
						.setOldproperty(rs.getString("OLDPROPERTY") != null ? rs
								.getString("OLDPROPERTY")
								: "");
				formBean
						.setOldstationtype(rs.getString("OLDSTATIONTYPE") != null ? rs
								.getString("OLDSTATIONTYPE")
								: "");
				formBean.setOldyflx(rs.getString("OLDYFLX") != null ? rs
						.getString("OLDYFLX") : "");
				formBean.setOldgxxx(rs.getString("OLDGXXX") != null ? rs
						.getString("OLDGXXX") : "");
				formBean.setOldgdfs(rs.getString("OLDGDFS") != null ? rs
						.getString("OLDGDFS") : "");
				formBean.setOldzgd(rs.getString("OLDZGD") != null ? rs
						.getString("OLDZGD") : "0");
				formBean.setOldarea(rs.getString("OLDAREA") != null ? rs
						.getString("OLDAREA") : "0");
				formBean.setOldqyzt(rs.getString("OLDQYZT") != null ? rs
						.getString("OLDQYZT") : "0");
				formBean.setOldg2(rs.getString("OLDG2") != null ? rs
						.getString("OLDG2") : "0");
				formBean.setOldg3(rs.getString("OLDG3") != null ? rs
						.getString("OLDG3") : "0");
				formBean.setOldzpsl(rs.getString("OLDZPSL") != null ? rs
						.getString("OLDZPSL") : "0");
				formBean.setOldzssl(rs.getString("OLDZSSL") != null ? rs
						.getString("OLDZSSL") : "0");
				formBean
						.setOldchangjia(rs.getString("OLDCHANGJIA") != null ? rs
								.getString("OLDCHANGJIA")
								: "");
				formBean.setOldjzlx(rs.getString("OLDJZLX") != null ? rs
						.getString("OLDJZLX") : "");
				formBean.setOldshebei(rs.getString("OLDSHEBEI") != null ? rs
						.getString("OLDSHEBEI") : "");
				formBean.setOldbbu(rs.getString("OLDBBU") != null ? rs
						.getString("OLDBBU") : "0");
				formBean.setOldrru(rs.getString("OLDRRU") != null ? rs
						.getString("OLDRRU") : "0");
				formBean
						.setOldydshebei(rs.getString("OLDYDSHEBEI") != null ? rs
								.getString("OLDYDSHEBEI")
								: "0");
				formBean.setOldgxgwsl(rs.getString("OLDGXGWSL") != null ? rs
						.getString("OLDGXGWSL") : "0");
				formBean.setFilepath(rs.getString("FILEPATH") != null ? rs
						.getString("FILEPATH") : "");
				formBean.setBiaoti(rs.getString("BIAOTI") != null ? rs
						.getString("BIAOTI") : "");
				formBean.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "");
				formBean.setOldqsdbdl(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "");
				formBean.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
				formBean.setDbid(rs.getString("DBID")!=null?rs.getString("DBID"):"");
				formBean.setDbds(rs.getString("DBDS")!=null?rs.getString("DBDS"):"");
				formBean.setQxpdbz(rs.getString("QXPDBZ")!=null?rs.getString("QXPDBZ"):"");
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
		return formBean;
	}
	public synchronized String[] getZggd(String zdid) {
		Logger logger = Logger.getLogger(Zdqxcxxg.class);
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		
		String[] hh = new String[2];
		String cbzdid = "";
		String scb = "";
		StringBuffer sql = new StringBuffer("SELECT AA.ID AAID, AA.QSDB FROM (SELECT CZ.ID, CZ.QSDB FROM CBZDXX CZ,CBZD CC WHERE CC.ID = CZ.WJID AND CC.SHENGJSH='0' AND CC.SHIJSH = '2' AND CC.ZDID = '")
		.append(zdid).append("' AND CZ.QSDB = (SELECT BB.QSDB FROM (SELECT CD.QSDB FROM CBZDXX CD,CBZD C WHERE C.ID = CD.WJID AND C.SHENGJSH = '0' AND C.SHIJSH = '2' AND C.ZDID = '")
		.append(zdid).append("' ORDER BY CD.QSDB) BB WHERE ROWNUM = 1) ORDER BY CC.CBSJ DESC) AA WHERE ROWNUM = 1");
		logger.info("获取整改工单最小生产标及工单id:"+sql.toString());
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs.next()){
				cbzdid = rs.getString("AAID");
				scb = rs.getString("QSDB");
			}
		} catch (Exception e) {
			logger.error("获取整改工单最小生产标及工单id失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		hh[0] = cbzdid;
		hh[1] = scb;
		return hh;
	}
	public synchronized boolean setZggd(String zggdid,String zlfh,String jlfh,String edhdl,String scb){
		boolean flag = false;
		Logger logger = Logger.getLogger(Zdqxcxxg.class);
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		StringBuffer sql = new StringBuffer("UPDATE CBZDXX CB SET CB.ZLZFH=").append(zlfh)
		.append(",CB.JLZFH=").append(jlfh).append(",CB.BDEDHDL=").append(edhdl)
		.append(",CB.QSDB=").append(scb).append(" WHERE CB.ID=").append(zggdid);
		
		try {
		connc = db.getConnection();
		st = connc.createStatement();
			connc.setAutoCommit(false);
		logger.info("区县申请更新整改工单直流,交流,额定,生产:"+sql.toString());
		int count = st.executeUpdate(sql.toString());
		connc.commit();
		flag = count>0?true:false;
		} catch (Exception e) {
			logger.error("区县申请更新整改工单直流,交流,额定,生产失败:"+e.getMessage());
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
