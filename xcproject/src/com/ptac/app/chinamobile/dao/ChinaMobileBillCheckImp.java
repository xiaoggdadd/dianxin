package com.ptac.app.chinamobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.chinamobile.bean.ChinaMobileBillBean;
import com.ptac.app.electricmanageUtil.CheckInputAvailable;

public class ChinaMobileBillCheckImp implements ChinaMobileBillCheck {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public ArrayList<ChinaMobileBillBean> getDownLoadData(String loginid) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT DB.DBID, ZD.DIANFEI,ZD.JZNAME,ZD.ID, DECODE(AMM.MANUALAUDITSTATUS, '1', AMM.THISDEGREE, '') THISDEGREE,"
				+ " DECODE(AMM.MANUALAUDITSTATUS, '1', TO_CHAR(AMM.THISDATETIME,'yyyy-mm-dd'), '') THISDATETIME FROM (SELECT DISTINCT DD.AMMETERID_FK,"
				+ " DD.THISDEGREE, DD.THISDATETIME, DD.MANUALAUDITSTATUS FROM DIANDUVIEW DD, (SELECT MAX(AMM.THISDATETIME) THISDATETIME, AMM.AMMETERID_FK"
				+ " FROM DIANDUVIEW AMM WHERE AMM.MANUALAUDITSTATUS = '1'GROUP BY AMM.AMMETERID_FK) B WHERE DD.AMMETERID_FK = B.AMMETERID_FK"
				+ " AND DD.THISDATETIME = B.THISDATETIME AND DD.MANUALAUDITSTATUS = '1') AMM, DIANBIAO DB, ZHANDIAN ZD"
				+ " WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = DB.ZDID(+) AND DB.DBYT = 'dbyt01' AND ZD.QYZT = '1'"
				+ " AND ZD.SHSIGN = '1'AND DB.DBQYZT = '1' AND ZD.QYZT = '1' AND (DB.DFZFLX = 'dfzflx01' OR DB.DFZFLX = 'dfzflx03')"
				+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = ?)))";

		DataBase db = new DataBase();
		ArrayList<ChinaMobileBillBean> list = new ArrayList<ChinaMobileBillBean>();
		System.out.println(sql);
		System.out.println(loginid);
		int columnCount = 0;
		try {
			db.connectDb();
			conn = db.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			rs = ps.executeQuery();
			System.out.println(rs);
			ResultSetMetaData rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();
			System.out.println();
			if (rs.next()) {
				while (rs.next()) {

					ChinaMobileBillBean bean = new ChinaMobileBillBean();
					bean.setJzname(rs.getString("jzname"));
					bean.setZdid(rs.getString("id"));
					bean.setDianbiaoid(rs.getString("dbid"));
					bean.setStartdegree(rs.getDouble("THISDEGREE"));
					if (rs.getString("THISDATETIME") == null
							|| "".equals(rs.getString("THISDATETIME"))) {
						bean.setStarttime(this.getCSDS(bean.getDianbiaoid()));
					} else {
						bean.setStarttime(rs.getString("THISDATETIME"));
					}
					bean.setDianfei(rs.getDouble("DIANFEI"));
					list.add(bean);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}

		return list;
	}

	@Override
	public String getCSDS(String dianbiaoid) {
		// TODO Auto-generated method stub
		String sql = "SELECT TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME FROM DIANBIAO DB WHERE DB.DBID=?";
		DataBase ds = new DataBase();
		String dbcsds = "";
		Connection con1 = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			ds.connectDb();
			con1 = ds.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ps1 = con1.prepareStatement(sql);
			ps1.setString(1, dianbiaoid);
			rs1 = ps1.executeQuery();
			while (rs1.next()) {
				if (rs1.getString("CSSYTIME") != null
						|| "".equals(rs1.getString("CSSYTIME"))) {
					dbcsds = rs1.getString("CSSYTIME");
				}
			}
			return dbcsds;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ds.free(rs1, ps1, con1);
		}

		return dbcsds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector input(Vector content, CountForm cform, Account account,
			HttpServletRequest request, HttpServletResponse response) {
		Vector wrongContent = new Vector();
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// 错误数据保存
		Vector<String> wcell = new Vector<String>();
		// 转换成 行数组
		Object[] rows = content.toArray();
		int inputdata = rows.length;
		int inputerror = 0;
		int inputcount = 0;
		int inputlast = rows.length;
		session.setAttribute("inputdata", rows.length);
		for (int temp = 0; temp < rows.length; temp++) {
			// 每个行中的数据的遍历
			Object[] cells = ((Vector) rows[temp]).toArray();
			wcell = new CMCCCheckBillAvailableImp().inputCheck(cells, account);
			if (!wcell.isEmpty()) {
				wrongContent.add(wcell);
				inputerror++;
			}else{
				inputcount++;
			}
			inputlast = inputdata - inputerror -inputcount;
			if(session.getAttribute("inputerror")!=null||session.getAttribute("inputcount")!=null
					||session.getAttribute("inputlast")!=null){
				session.removeAttribute("inputerror");
				session.removeAttribute("inputcount");
				session.removeAttribute("inputlast");
			}
			session.setAttribute("inputerror", inputerror);
			session.setAttribute("inputcount", inputcount);
			session.setAttribute("inputlast", inputlast);

		}
		session.removeAttribute("inputerror");
		session.removeAttribute("inputcount");
		session.removeAttribute("inputlast");
		session.removeAttribute("inputdata");
		

		cform.setCg((content.size()) - wrongContent.size());
		cform.setBcg(wrongContent.size());
		cform.setZg((content.size()));

		System.out.println(cform.getCg() + "   " + cform.getBcg() + "   "
				+ cform.getZg());
		return wrongContent;
	
	}

}
