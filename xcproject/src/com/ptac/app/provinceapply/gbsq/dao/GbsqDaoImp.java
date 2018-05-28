package com.ptac.app.provinceapply.gbsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.noki.zdqxkz.util.ZdqxkzUtil;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.provinceapply.gbsq.bean.GbsqBaseBean;
import com.ptac.app.provinceapply.gbsq.bean.GbsqBean;

/**
 * @author lijing
 * @see 省申请改标审核实现层
 */
public class GbsqDaoImp implements GbsqDao {

	private Logger logger = Logger.getLogger(GbsqDaoImp.class);
	@Override
	public List<GbsqBean> query(String string,String loginId,String shengzt) {
		
		List<GbsqBean> list = new ArrayList<GbsqBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(Z.SHI) SHI,RNDIQU(Z.XIAN) XIAN,Z.JZNAME JZNAME,Q.OLDQSDB OLDQSDB,Q.NEWQSDB NEWQSDB,"
				 + "RNDIQU(Z.XIAOQU) XIAOQU,Z.ID ZDID,'"+shengzt+"' SHENGSHBZ,Q.ID ID,Q.NEWZLFH,Q.NEWJLFH,Q.NEWEDHDL, (CASE WHEN DBMS_LOB.GETLENGTH(FJNR) IS NOT NULL OR DBMS_LOB.GETLENGTH(FJNR)>0 THEN '1' ELSE '0' END) FJNR "
				 + "FROM QSKZ Q,ZHANDIAN Z WHERE Q.ZDID = Z.ID AND Q.OLDQSDB <> Q.NEWQSDB AND Q.FLGG IS NOT NULL AND Q.QXPDBZ='2' AND Q.SHISHBZ='1' "+ string
				 +" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))) ORDER BY Z.JZNAME");
		logger.info("省申请改标审核信息查询："+sql.toString());
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				GbsqBean bean = new GbsqBean();
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//市
				String xian = rs.getString("XIAN")==null?"":rs.getString("XIAN");//区县
				String zdname = rs.getString("JZNAME")==null?"":rs.getString("JZNAME");//站点名称
				String oldq = rs.getString("OLDQSDB")==null?"":rs.getString("OLDQSDB");//申请前
				String newq = rs.getString("NEWQSDB")==null?"":rs.getString("NEWQSDB");//申请后
				String xiaoqu = rs.getString("XIAOQU")==null?"":rs.getString("XIAOQU");//乡镇
				String zdid = rs.getString("ZDID")==null?"":rs.getString("ZDID");//站点ID
				String state = rs.getString("SHENGSHBZ")==null?"":rs.getString("SHENGSHBZ");//审核状态
				String id = rs.getString("ID")==null?"":rs.getString("ID");
				String zlfh = rs.getString("NEWZLFH")==null?"":rs.getString("NEWZLFH");//直流负荷
				String jlfh = rs.getString("NEWJLFH")==null?"":rs.getString("NEWJLFH");//交流负荷
				String edhdl = rs.getString("NEWEDHDL")==null?"":rs.getString("NEWEDHDL");//额定耗电量
				Boolean fjnr = rs.getString("FJNR")==null?Boolean.FALSE:("1".equals(rs.getString("FJNR"))?Boolean.TRUE:Boolean.FALSE);
				
				bean.setCity(city);
				bean.setXian(xian);
				bean.setZdname(zdname);
				bean.setOldq(oldq);
				bean.setNewq(newq);
				bean.setXiaoqu(xiaoqu);
				bean.setZdid(zdid);
				bean.setState(state);
				bean.setId(id);
				bean.setZlfh(zlfh);
				bean.setJlfh(jlfh);
				bean.setEdhdl(edhdl);
				bean.setFjnr(fjnr);
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("省申请改标审核信息查询出错："+e.getMessage());
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<GbsqBaseBean> queryBase(String[] qxkzid) {
		List<GbsqBaseBean> list = new ArrayList<GbsqBaseBean>();
		StringBuffer buffer = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection connc = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int size = qxkzid.length;
		for (int i = 1; i <= size; i++) {
			buffer.append(qxkzid[i-1])
			.append(
					(i%1000!=0 || (i%1000==0 && i==size))
					?
					","
							:
								") OR QZ.ID IN("
						);
		}
		sql.append("SELECT QZ.ID QSKZID,QZ.ZDID,QZ.OLDQSDB,QZ.NEWQSDB,QZ.OLDQSDBDL,QZ.NEWZLFH,QZ.NEWJLFH,QZ.NEWPROPERTY,QZ.FLGG,QZ.BFTG,QZ.BFBTG FROM QSKZ QZ")
			.append(" WHERE QZ.ID IN(").append(buffer.substring(0, buffer.length()-1)).append(")");
			try {
				connc = db.getConnection();
				ps = connc.prepareStatement(sql.toString());
				logger.info("省申请改标审核更改基本信息查询:"+sql.toString());
				rs = ps.executeQuery();
				while(rs.next()){
					GbsqBaseBean gb = new GbsqBaseBean();
					gb.setQskzid(rs.getString("QSKZID")==null?"":rs.getString("QSKZID"));
					gb.setZdid(rs.getString("ZDID")==null?"":rs.getString("ZDID"));
					gb.setOldqsdb(rs.getString("OLDQSDB")==null?"0":rs.getString("OLDQSDB"));
					gb.setNewqsdb(rs.getString("NEWQSDB")==null?"0":rs.getString("NEWQSDB"));
					gb.setOldqsdbdl(rs.getString("OLDQSDBDL")==null?"0":rs.getString("OLDQSDBDL"));
					gb.setNewzlfh(rs.getString("NEWZLFH")==null?"0":rs.getString("NEWZLFH"));
					gb.setNewjlfh(rs.getString("NEWJLFH")==null?"0":rs.getString("NEWJLFH"));
					gb.setNewproperty(rs.getString("NEWpROPERTY")==null?"":rs.getString("NEWpROPERTY"));
					gb.setFlgg(rs.getString("FLGG")==null?"":rs.getString("FLGG"));
					gb.setBftg(rs.getString("BFTG")==null?"":rs.getString("BFTG"));
					gb.setBfbtg(rs.getString("BFBTG")==null?"":rs.getString("BFBTG"));
					list.add(gb);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("省申请改标审核更改基本信息查询出错:"+e.getMessage());
			}finally{
				db.free(rs, ps, connc);
			}
		return list;
	}

	@Override
	public String checkNoPass(List<GbsqBaseBean> list, String personnal,
			String cause) {
		String msg = "省级标杆审核不通过失败!";
		int count = list.size();
		int b = 0;
		DataBase db = new DataBase();
		Connection connc = null;
		Statement ps = null;
		StringBuffer sql = new StringBuffer();
		String flgg = "",bftg="",bfbtg="",sbz= "";
		ZdqxkzUtil util = new ZdqxkzUtil();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			ps = connc.createStatement();
	for (GbsqBaseBean gb : list) {
		flgg = gb.getFlgg();
		bftg = gb.getBftg();
		bfbtg = gb.getBfbtg();
		
		bfbtg = util.getBftg(bfbtg, "9");
		
		if(util.getShengbz(flgg, bfbtg)){
			sbz = "2";
		}else{
			sbz = util.checkKong(bftg)?"4":"5";
		}
		sql.append("UPDATE QSKZ QZ SET QZ.SHENGSHBZ='").append(sbz).append("',QZ.SHENGSHR='").append(personnal)
		.append("',QZ.QXLY='").append(cause).append("',QZ.SHENGSHSJ=SYSDATE,QZ.BFBTG='").append(bfbtg)
		.append("' WHERE QZ.ID = '").append(gb.getQskzid()).append("'");
		logger.info("省级标杆审核不通过:"+sql.toString());
		int a = ps.executeUpdate(sql.toString());
		b = a>0?++b:b;
		sql.setLength(0);//清空
	}
		connc.commit();
		connc.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("省级标杆审核不通过出错:"+e.getMessage());
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(null, ps, connc);
		}
		return b==count?"省级标杆审核不通过成功!":msg;
	}

	@Override
	public String checkQuXiao(List<GbsqBaseBean> list, String personnal) {
		String msg = "省级标杆审核取消审核失败!";
		int count = list.size();
		int b = 0,c=0,d=0;
		DataBase db = new DataBase();
		Connection connc = null;
		Statement ps = null;
		Statement ps1 = null;
		Statement ps2 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		String bftg="",bfbtg="",sbz= "";
		ZdqxkzUtil util = new ZdqxkzUtil();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			ps = connc.createStatement();
			ps1 = connc.createStatement();
			ps2 = connc.createStatement();
	for (GbsqBaseBean gb : list) {

		bftg = gb.getBftg();
		bfbtg = gb.getBfbtg();
		
		bftg = util.getFlbz(bftg,"9");
		bfbtg = util.getFlbz(bfbtg,"9");
		
		if(util.checkKong(bftg)){
			sbz = util.checkKong(bfbtg)?"0":"4";
		}else{
			sbz = util.checkKong(bfbtg)?"3":"5";
		}
		
		sql1.append("UPDATE ZHANDIAN ZD SET ZD.QSDBDL='").append(gb.getOldqsdbdl()).append("',ZD.SCB='").append(gb.getOldqsdb()).append("' WHERE ZD.ID ='").append(gb.getZdid()).append("'");
		sql2.append("UPDATE SCB S SET S.SCB='").append(gb.getOldqsdb()).append("' WHERE S.ZDID ='").append(gb.getZdid()).append("'");
		sql.append("UPDATE QSKZ QZ SET QZ.SHENGSHBZ='").append(sbz).append("',QZ.SHENGSHR='").append(personnal)
		.append("',QZ.QXLY=NULL,QZ.SHENGSHSJ=SYSDATE,QZ.BFTG='").append(bftg).append("',QZ.BFBTG='").append(bfbtg)
		.append("' WHERE QZ.ID = '").append(gb.getQskzid()).append("'");
		logger.info("省级标杆审核取消审核:"+sql.toString());
		logger.info("省级标杆审核取消审核还原站点信息:"+sql1.toString());
		logger.info("省级标杆审核取消审核还原生产标:"+sql2.toString());
		int a = ps.executeUpdate(sql.toString());
		int aa = ps1.executeUpdate(sql1.toString());
		int aaa = ps2.executeUpdate(sql2.toString());
		b = a>0?++b:b;
		c = aa>0?++c:c;
		d = aaa>0?++d:d;
		sql.setLength(0);//清空
		sql1.setLength(0);
		sql2.setLength(0);
	}
		connc.commit();
		connc.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("省级标杆审核取消审核出错:"+e.getMessage());
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(null, ps, connc);
		}
		return (b==count && c==count && d==count)?"省级标杆审核取消审核成功!":msg;
	}
	@Override
	public synchronized String checkPass(List<GbsqBaseBean> ls,
			String syf, String b1, String b2, String b3, String bb5,
			String bb6, String bb7, String bb8, String bb9, String bb10,
			String bb11, String bb12, String bb13, String bb14, String bb15,
			String bb16, String bb17, String bb18, String bb19, String bb20,
			String bb22, String dd5, String dd6, String dd7, String dd8,
			String dd9, String dd10, String dd11, String dd12, String dd13,
			String dd14, String dd15, String dd16, String dd17, String dd18,
			String dd19, String dd20, String dd22, String xx5, String xx6,
			String xx7, String xx8, String xx9, String xx10, String xx11,
			String xx12, String xx13, String xx14, String xx15, String xx16,
			String xx17, String xx18, String xx19, String xx20, String xx22,
			String yy5, String yy6, String yy7, String yy8, String yy9,
			String yy10, String yy11, String yy12, String yy13, String yy14,
			String yy15, String yy16, String yy17, String yy18, String yy19,
			String yy20, String yy22, String qq5, String qq6, String qq7,
			String qq8, String qq9, String qq10, String qq11, String qq12,
			String qq13, String qq14, String qq15, String qq16, String qq17,
			String qq18, String qq19, String qq20, String qq22, String ii5,
			String ii6, String ii7, String ii8, String ii9, String ii10,
			String ii11, String ii12, String ii13, String ii14, String ii15,
			String ii16, String ii17, String ii18, String ii19, String ii20,
			String ii22, String jj5, String jj6, String jj7, String jj8,
			String jj9, String jj10, String jj11, String jj12, String jj13,
			String jj14, String jj15, String jj16, String jj17, String jj18,
			String jj19, String jj20, String jj22, String loginName) {
		String msg = "省级标杆审核通过失败!";
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		String sff = df.format(new Date());
		String ssyf = sff.toString().substring(5, 7);
		int j = Integer.parseInt(ssyf);
		ResultSet rs = null;
		String sj = "SYSDATE";
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			st = connc.createStatement();
			if (ls != null) {
				ZdqxkzUtil util = new ZdqxkzUtil();
				String flgg = "", bftg="",bfbtg="",sbz= "";
				for (GbsqBaseBean lst : ls) {
					String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ lst.getZdid()+"'";//查询 有无 空调
					rs = st.executeQuery(sqlkongtiao);
					int kts = 0;
					if(rs.next()){
						kts = rs.getInt("KTS");
					}
					String ssql = "", sql2 = "", sql3 = "", sql12 = "";
					String s = lst.getNewqsdb();
					double d = 0.0, dd = 0.0;
					if ("0".equals(s) || null == s || "".equals(s)) {
						d = Format.str_d(lst.getNewzlfh()) * 54 * 24 / 1000 / 0.85;
						dd = Format.str_d(lst.getNewjlfh()) * 220 * 24 / 1000;
						if (d >= dd) {
							s = String.valueOf(dd);
						} else if (d <= dd) {
							s = String.valueOf(d);
						}
					}
					sql12 = "SELECT S.SCB FROM SCB S WHERE S.ZDID = '" + lst.getZdid() + "'";
					rs = st.executeQuery(sql12);
					if (rs.next()) {
						sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid() + "'";
						st.executeUpdate(sql3);
					}else{
						String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2014-08','" + lst.getZdid() + "'," + s + ",'" + loginName + "'," + sj + "," + 0 + ")";
						st.executeUpdate(sql22);
					}
					if ("zdsx02".equals(lst.getNewproperty())) {//基站
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <=" + bb6
										+ " THEN " + bb7 + " WHEN aa.ZLFH >" + bb6 + " AND aa.ZLFH <=" + bb9 + " THEN " + bb10 + " WHEN aa.ZLFH > " + bb9
										+ " AND aa.ZLFH <= " + bb12 + " THEN " + bb13 + " WHEN aa.ZLFH > " + bb12 + " AND aa.ZLFH <= " + bb15 + " THEN " + bb16
										+ " WHEN aa.ZLFH > " + bb15 + " AND aa.ZLFH <= " + bb18 + " THEN " + bb19
										+ " WHEN aa.ZLFH > " + bb18 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02',"
										+ b2 + ", 'fwlx03'," + b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6
										+ " THEN " + bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8
										+ " AND aa.ZLFH <" + bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16
										+ " WHEN aa.ZLFH >= " + bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN "
										+ bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2 + ", 'fwlx03'," + b3
										+ ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID )), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
						}
						st.executeUpdate(ssql);
					} else if ("zdsx05".equals(lst.getNewproperty())) {//接入网
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + dd5 + " AND aa.ZLFH <=" + dd6
										+ " THEN " + dd7 + " WHEN aa.ZLFH >" + dd6 + " AND aa.ZLFH <=" + dd9 + " THEN " + dd10 + " WHEN aa.ZLFH > " + dd9
										+ " AND aa.ZLFH <= " + dd12 + " THEN " + dd13 + " WHEN aa.ZLFH > " + dd12 + " AND aa.ZLFH <= " + dd15 + " THEN " + dd16
										+ " WHEN aa.ZLFH > " + dd15 + " AND aa.ZLFH <= " + dd18 + " THEN " + dd19 + " WHEN aa.ZLFH > " + dd18 + " THEN " + dd22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)" 
										+ ",q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + "*1 " + "+(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6 + " THEN "
										+ bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8 + " AND aa.ZLFH <"
										+ bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16 + " WHEN aa.ZLFH >= "
										+ bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN " + bb22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ ", q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
						}
						st.executeUpdate(ssql);

					} else if ("zdsx06".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <="
										+ xx6 + " THEN " + xx7 + " WHEN aa.ZLFH >" + xx6 + " AND aa.ZLFH <=" + xx9 + " THEN " + xx10 + " WHEN aa.ZLFH > "
										+ xx9 + " AND aa.ZLFH <= " + xx12 + " THEN " + xx13 + " WHEN aa.ZLFH > " + xx12 + " AND aa.ZLFH <= " + xx15
										+ " THEN " + xx16 + " WHEN aa.ZLFH > " + xx15 + " AND aa.ZLFH <= " + xx18 + " THEN " + xx19 + " WHEN aa.ZLFH > "
										+ xx18 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <"
										+ xx6 + " THEN " + xx7 + " WHEN aa.ZLFH >=" + xx6 + " AND aa.ZLFH <" + xx8 + " THEN " + xx10 + "WHEN aa.ZLFH >= "
										+ xx8 + " AND aa.ZLFH <" + xx11 + " THEN " + xx13 + " WHEN aa.ZLFH >= " + xx11 + " AND aa.ZLFH < " + xx14
										+ " THEN " + xx16 + " WHEN aa.ZLFH >= " + xx14 + " AND aa.ZLFH < " + xx17 + " THEN " + xx19 + " WHEN aa.ZLFH >= "
										+ xx17 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
										+ " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
					}
						st.executeUpdate(ssql);

					} else if ("zdsx03".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <=" + yy6 + " THEN "
										+ yy7 + " WHEN aa.ZLFH >" + yy6 + " AND aa.ZLFH <=" + yy9 + " THEN " + yy10 + " WHEN aa.ZLFH > " + yy9 + " AND aa.ZLFH <= "
										+ yy12 + " THEN " + yy13 + " WHEN aa.ZLFH > " + yy12 + " AND aa.ZLFH <= " + yy15 + " THEN " + yy16 + " WHEN aa.ZLFH > "
										+ yy15 + " AND aa.ZLFH <= " + yy18 + " THEN " + yy19 + " WHEN aa.ZLFH > " + yy18 + " THEN " + yy22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ ", q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " *1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <" + yy6
										+ " THEN " + yy7 + " WHEN aa.ZLFH >=" + yy6 + " AND aa.ZLFH <" + yy8 + " THEN " + yy10 + " WHEN aa.ZLFH >= "
										+ yy8 + " AND aa.ZLFH <" + yy11 + " THEN " + yy13 + " WHEN aa.ZLFH >= " + yy11 + " AND aa.ZLFH < " + yy14
										+ " THEN " + yy16 + " WHEN aa.ZLFH >= " + yy14 + " AND aa.ZLFH < " + yy17 + " THEN " + yy19 + " WHEN aa.ZLFH >= " + yy17
										+ " THEN " + yy22  + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " , q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
						}
						st.executeUpdate(ssql);

					} else if ("zdsx04".equals(lst.getNewproperty())) {//其他
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <="
										+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >" + qq6 + " AND aa.ZLFH <=" + qq9 + " THEN " + qq10 + " WHEN aa.ZLFH > "
										+ qq9 + " AND aa.ZLFH <= " + qq12 + " THEN " + qq13 + " WHEN aa.ZLFH > " + qq12 + " AND aa.ZLFH <= " + qq15
										+ " THEN " + qq16 + " WHEN aa.ZLFH > " + qq15 + " AND aa.ZLFH <= " + qq18 + " THEN " + qq19 + " WHEN aa.ZLFH > "
										+ qq18 + " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <"
										+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >=" + qq6 + " AND aa.ZLFH <" + qq8 + " THEN " + qq10 + " WHEN aa.ZLFH >= "
										+ qq8 + " AND aa.ZLFH <" + qq11 + " THEN " + qq13 + " WHEN aa.ZLFH >= " + qq11 + " AND aa.ZLFH < " + qq14
										+ " THEN " + qq16 + " WHEN aa.ZLFH >= " + qq14 + " AND aa.ZLFH < " + qq17 + " THEN " + qq19 + " WHEN aa.ZLFH >= "
										+ qq17 + " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
					}
						st.executeUpdate(ssql);

					} else if ("zdsx07".equals(lst.getNewproperty())) {//idc机房
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <=" + ii6
										+ " THEN " + ii7 + " WHEN aa.ZLFH >" + ii6 + " AND aa.ZLFH <=" + ii9 + " THEN " + ii10 + " WHEN aa.ZLFH > " + ii9
										+ " AND aa.ZLFH <= " + ii12 + " THEN " + ii13 + " WHEN aa.ZLFH > " + ii12 + " AND aa.ZLFH <= " + ii15 + " THEN "
										+ ii16 + " WHEN aa.ZLFH > " + ii15 + " AND aa.ZLFH <= " + ii18 + " THEN " + ii19 + " WHEN aa.ZLFH > " + ii18
										+ " THEN " + ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <" + ii6
										+ " THEN " + ii7 + " WHEN aa.ZLFH >=" + ii6 + " AND aa.ZLFH <" + ii8 + " THEN " + ii10 + " WHEN aa.ZLFH >= " + ii8
										+ " AND aa.ZLFH <" + ii11 + " THEN " + ii13 + " WHEN aa.ZLFH >= " + ii11 + " AND aa.ZLFH < " + ii14 + " THEN " + ii16
										+ " WHEN aa.ZLFH >= " + ii14 + " AND aa.ZLFH < " + ii17 + " THEN " + ii19 + " WHEN aa.ZLFH >= " + ii17 + " THEN "
										+ ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
										+ " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
						}
						st.executeUpdate(ssql);

					} else if ("zdsx01".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <=" + jj6 + " THEN " + jj7 + " WHEN aa.ZLFH >" + jj6
										+ " AND aa.ZLFH <=" + jj9 + " THEN " + jj10 + " WHEN aa.ZLFH > " + jj9 + " AND aa.ZLFH <= " + jj12 + " THEN " + jj13 + " WHEN aa.ZLFH > "
										+ jj12 + " AND aa.ZLFH <= " + jj15 + " THEN " + jj16 + " WHEN aa.ZLFH > " + jj15 + " AND aa.ZLFH <= " + jj18 + " THEN " + jj19
										+ " WHEN aa.ZLFH > " + jj18 + " THEN " + jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
	
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <" + jj6
										+ " THEN " + jj7 + " WHEN aa.ZLFH >=" + jj6 + " AND aa.ZLFH <" + jj8 + " THEN " + jj10 + " WHEN aa.ZLFH >= " + jj8
										+ " AND aa.ZLFH <" + jj11 + " THEN " + jj13 + " WHEN aa.ZLFH >= " + jj11 + " AND aa.ZLFH < " + jj14 + " THEN " + jj16
										+ " WHEN aa.ZLFH >= " + jj14 + " AND aa.ZLFH < " + jj17 + " THEN " + jj19 + " WHEN aa.ZLFH >= " + jj17 + " THEN "
										+ jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id='" + lst.getZdid() + "'";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = '" + lst.getZdid()+"'";
					}
						st.executeUpdate(ssql);
					}
					bftg = lst.getBftg();
					bfbtg = lst.getBfbtg();
					flgg = lst.getFlgg();
					
					bftg = util.getBftg(bftg, "9");
					if(util.getShengbz(flgg, bftg)){
						sbz = "1";
					}else{
						sbz = util.checkKong(bfbtg)?"3":"5";
					}
					String sqlshengtg = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.QXLY=NULL,c.SHENGSHR='" + loginName + "',c.SHENGSHSJ=sysdate,C.BFTG='"+bftg+"' where  c.zdid = '" + lst.getZdid()
					+ "' and c.id  ='" + lst.getQskzid() + "'";
					st.executeUpdate(sqlshengtg);
				}
			}
			connc.commit();
			connc.setAutoCommit(true);
			msg = "省级标杆审核通过成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("省级标杆审核通过出错!"+e.getMessage());
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.error("省级标杆审核通过出错回滚出错!"+e1.getMessage());
			}
		} finally {
			db.free(rs, st, connc);
		}

		return msg;
	}
	

}
