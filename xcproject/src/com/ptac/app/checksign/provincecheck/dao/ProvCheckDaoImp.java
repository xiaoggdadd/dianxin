package com.ptac.app.checksign.provincecheck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checksign.provincecheck.bean.ProvCheckBean;

/**
 * @author lijing
 * @see 省级新增站点审核实现层
 */
public class ProvCheckDaoImp implements ProvCheckDao {

	@Override
	public List<ProvCheckBean> queryProvince(StringBuffer whereStr,
			String loginId) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProvCheckBean> list = new ArrayList<ProvCheckBean>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(Z.SHI),Z.JZNAME,RTNAME(Z.PROPERTY),"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "Z.ZLFH,Z.JLFH,Z.EDHDL,rtname(z.gdfs)," 
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.GXXX AND TYPE = 'gxxx') AS gxxx," 
						+ "RNDIQU(Z.XIAN),RNDIQU(Z.XIAOQU),Z.ID,Z.PROVAUDITSTATUS"
						+ " FROM ZHANDIAN Z"
						+ " WHERE Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' AND Z.QYZT='1' AND Z.PROVAUDITSTATUS <> '1' "
						+ whereStr
						+ " AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		System.out.println("省级新增站点审核查询:"+sql.toString());
	
		DataBase db = new DataBase();
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				
				ProvCheckBean bean = new ProvCheckBean();
				String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";// 站点名称
				String property = rs.getString(3) != null ? rs.getString(3): "";// 站点属性
				String stationtype = rs.getString(4) != null ? rs.getString(4): "";// 站点类型
				String zlfh = rs.getString(5) != null ? rs.getString(5) : "";//直流负荷
				String jlfh = rs.getString(6) != null ? rs.getString(6) : "";//交流负荷
				String edhdl = rs.getString(7) != null ? rs.getString(7) : "";//额定耗电量
				String gdfs = rs.getString(8) != null ? rs.getString(8) : "";// 供电方式
				String gxmsg = rs.getString(9) != null ? rs.getString(9) : "";//共享信息
				String quxian = rs.getString(10) != null ? rs.getString(10): "";// 区县
				String town = rs.getString(11) != null ? rs.getString(11) : "";//乡镇
				String zdid = rs.getString(12) != null ? rs.getString(12) : "";// 站点ID
				String provauditstatus = rs.getString(13) != null ? rs.getString(13) : "";// 省级新增站点审核标志
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setProperty(property);
				bean.setStationtype(stationtype);
				bean.setZlfh(zlfh);
				bean.setJlfh(jlfh);
				bean.setEdhdl(edhdl);
				bean.setGdfs(gdfs);
				bean.setGxmsg(gxmsg);
				bean.setXian(quxian);
				bean.setTown(town);
				bean.setZdid(zdid);
				bean.setProvauditstatus(provauditstatus);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, ps, null);
		}
		return list;
	}
	
	@Override
	public int checkProvFees(String[] ids, String shsign, String personnal) {

		int msg = 0;
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// 系统时间
		StringBuffer sql = new StringBuffer();
		StringBuffer sid = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			if (i == 0) {
				sid.append(ids[i]);
			} else {
				sid.append("," + ids[i]);
			}
		}

		sql.append("update zhandian set PROVAUDITSTATUS='" + shsign + "',PROVAUDITNAME='"+personnal
				  +"',PROVAUDITTIME="+time+" where id in("+ sid.toString() + ")");
		System.out.println("省级新增站点审核:"+sql.toString());

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
			msg = 1;
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			db.free(null, ps, null);
		}
		return msg;
	}
}
