package com.ptac.app.systemmanage.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.systemmanage.bean.FangWenLiangBean;
import com.ptac.app.systemmanage.bean.LuRuLiangBean;


public class LuRuLiangDaoImpl implements LuRuLiangDao{
	@Override
	
	public List<LuRuLiangBean> queryLuRuCountjs(String shi, String startyear,
			String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<LuRuLiangBean> list = new ArrayList<LuRuLiangBean>();
		String sql1 = "select to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd'),count(t.electricfee_id) " +
				"from luruliang t where to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')>='"+startyear+"' and to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')<='"+endyear+"'and t.shi='"+shi+"'AND t.XUNI = '0' AND t.CAIJI = '0' " +
				"AND t.SHSIGN = '1' group by to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')order by to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("录入量结算："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			LuRuLiangBean bean = new LuRuLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setLcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	public List<LuRuLiangBean> queryLuRuCountgl(String shi, String startyear,
			String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<LuRuLiangBean> list = new ArrayList<LuRuLiangBean>();
		String sql1 = "SELECT TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss')" +
				",'yyyy-mm-dd'),COUNT(AM.AMMETERDEGREEID) FROM ZHANDIAN Z, DIANBIAO D, " +
				"AMMETERDEGREES AM WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND " +
				"Z.CAIJI = '0'AND Z.XUNI = '0'AND Z.SHSIGN = '1'AND D.DBYT = 'dbyt03'and " +
				" TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss')" +
				", 'yyyy-mm-dd')>='"+startyear+"'and TO_CHAR(TO_DATE(AM.ENTRYTIME, " +
				"'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')<='"+endyear+"'and z.shi='"+shi+"' " +
				"GROUP BY TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')" +
				"ORDER BY TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("录入量管理："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			LuRuLiangBean bean = new LuRuLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setLcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	@Override
	public List<LuRuLiangBean> getXiangXi(String shi, String startyear, String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<LuRuLiangBean> list = new ArrayList<LuRuLiangBean>();
		String sql1 = "select to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd'),count(t.electricfee_id) " +
				"from luruliang t where to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')>='"+startyear+"' and to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')<='"+endyear+"'and t.shi='"+shi+"' and t.XUNI = '0' and t.caiji = '0' " +
				"and t.shsign = '1' group by to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss')," +
				"'yyyy-mm-dd')order by to_char(to_date(t.entrytime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("录入量结算折线图："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			LuRuLiangBean bean = new LuRuLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setLcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	@Override
	public List<FangWenLiangBean> getFangWenLiangmx( String startyear,
			String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<FangWenLiangBean> list = new ArrayList<FangWenLiangBean>();
		String sql1 = "select to_char(logtime,'yyyy-mm-dd'),count(to_char(logtime,'yyyy-mm-dd'))  " +
				"from logs t where title is not null and content is not null and title <> 'null' " +
				"and content <> 'null'and to_char(logtime,'yyyy-mm-dd')>='"+startyear+"'" +
				"and to_char(logtime,'yyyy-mm-dd') <= '"+endyear+"' group by " +
				"to_char(logtime,'yyyy-mm-dd') order by to_char(logtime,'yyyy-mm-dd')";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("访问量："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			FangWenLiangBean  bean = new FangWenLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setFcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	@Override
	public List<FangWenLiangBean> getFangWenLiang(String startyear,
			String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<FangWenLiangBean> list = new ArrayList<FangWenLiangBean>();
		String sql1 = "select to_char(logtime,'yyyy-mm-dd'),count(to_char(logtime,'yyyy-mm-dd'))  " +
				"from logs t where title is not null and content is not null and title <> 'null' " +
				"and content <> 'null'and to_char(logtime,'yyyy-mm-dd')>='"+startyear+"'" +
				"and to_char(logtime,'yyyy-mm-dd') <= '"+endyear+"' group by " +
				"to_char(logtime,'yyyy-mm-dd') order by to_char(logtime,'yyyy-mm-dd') ASC";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("访问量折线图："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			FangWenLiangBean bean = new FangWenLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setFcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	@Override
	public List<LuRuLiangBean> getXiangXigl(String shi, String startyear,
			String endyear) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<LuRuLiangBean> list = new ArrayList<LuRuLiangBean>();
		String sql1 = "SELECT TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss')" +
				",'yyyy-mm-dd'),COUNT(AM.AMMETERDEGREEID) FROM ZHANDIAN Z, DIANBIAO D, " +
				"AMMETERDEGREES AM WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND " +
				"Z.CAIJI = '0'AND Z.XUNI = '0'AND Z.SHSIGN = '1'AND D.DBYT = 'dbyt03'and " +
				" TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss')" +
				", 'yyyy-mm-dd')>='"+startyear+"'and TO_CHAR(TO_DATE(AM.ENTRYTIME, " +
				"'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')<='"+endyear+"'and z.shi='"+shi+"' " +
				"GROUP BY TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')" +
				"ORDER BY TO_CHAR(TO_DATE(AM.ENTRYTIME, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd')";
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("录入量折线图管理："+sql1);
			rs = ps.executeQuery(sql1);
		while(rs.next()){   
			LuRuLiangBean bean = new LuRuLiangBean();
			bean.setYear(rs.getString(1)!=null?rs.getString(1):"");
			bean.setLcount(rs.getString(2)!=null?rs.getString(2):"");
			list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	
	
}
