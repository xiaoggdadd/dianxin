package com.ptac.app.datastatistics.powermode.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.ptac.app.datastatistics.powermode.bean.PowerBean;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author 李靖
 * @see 供电方式缴费统计实现层
 */
public class PowerDaoImp implements PowerDao {

	@Override
	public ArrayList<PowerBean> queryExport(String whereStr, String loginId) {
		
		ArrayList<PowerBean> list = new ArrayList<PowerBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta  = null;
		ResultSet rs = null;
	 
		sql.append("SELECT RNDIQU(CC.SHI),RNDIQU(CC.XIAN),RNDIQU(CC.XIAOQU),CC.JZNAME,CC.ID,RTNAME(CC.PROPERTY),"
				 + "RTNAME(CC.STATIONTYPE),SUM(CC.BLHDL),SUM(CC.ACTUALPAY),SUM(CC.JSZQ)"
				 + " FROM (SELECT ZD.SHI,ZD.XIAN,ZD.XIAOQU,ZD.JZNAME,ZD.ID,ZD.PROPERTY,ZD.STATIONTYPE,A.BLHDL,E.ACTUALPAY,E.JSZQ"
				 + " FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E"
				 + " WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK"
				 + whereStr
				 + " AND E.ACTUALPAY > 0  AND a.blhdl>0 AND ZD.STATIONTYPE NOT IN ('StationType01', 'StationType27')"
				 + " AND ZD.QYZT = '1'  AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND ZD.SHSIGN = '1' ) CC"
				 + " GROUP BY CC.SHI, CC.XIAN, CC.XIAOQU, CC.ID, CC.PROPERTY, CC.JZNAME,CC.STATIONTYPE");
		
		System.out.println("供电方式缴费统计:"+sql);
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			
			while(rs.next()){
				PowerBean bean = new PowerBean();
				String city = rs.getString(1)!=null?rs.getString(1):"";
				String xian = rs.getString(2)!=null?rs.getString(2):"";
				String xiaoqu = rs.getString(3)!=null?rs.getString(3):"";
				String jzname = rs.getString(4)!=null?rs.getString(4):"";
				String id = rs.getString(5)!=null?rs.getString(5):"";
				String zdsx = rs.getString(6)!=null?rs.getString(6):"";
				String zdlx = rs.getString(7)!=null?rs.getString(7):"";
				String blhdl = rs.getString(8)!=null?rs.getString(8):"";
				String actualpay = rs.getString(9)!=null?rs.getString(9):"";
				String jszq = rs.getString(10)!=null?rs.getString(10):"";
				
				bean.setShi(city);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setJzname(jzname);
				bean.setId(id);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setBlhdl(blhdl);
				bean.setActualpay(actualpay);
				bean.setJszq(jszq);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, sta, conn);
		}
		return list;
	}

	/**
	 * @author 李靖
	 * @see 计算总的报账电费
	 */
	public PowerBean totalMoney(ArrayList<PowerBean> list) {
		PowerBean bean = new PowerBean();
 		double totalmoney = 0;	
		int i;
		for(i = 0; i<list.size();i++){
			bean = list.get(i);
     		totalmoney = totalmoney + Double.parseDouble(bean.getActualpay());
		}
		bean.setTotalmoney(Format.d2(totalmoney));
		return bean;
	}

}
