package com.ptac.app.calibstat.compared.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.calibstat.compared.bean.ComparedBean;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author 李靖
 * @see 建议生产标与生产标对比查询实现层
 */
public class ComparedDaoImp implements ComparedDao {

	@Override
	public List<ComparedBean> queryExport(String str, String loginId) {

		List<ComparedBean> list = new ArrayList<ComparedBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		
		sql = "SELECT RNDIQU(ZD.SHI),RNDIQU(ZD.XAIN),ZD.ZDNAME,ZD.ZDID,RTNAME(ZD.ZDSX),RTNAME(Z.STATIONTYPE),"
		 	+ " ZD.SCB,ZD.JYSCB,((ZD.SCB-ZD.JYSCB)/ZD.JYSCB*100) XCBL"
			+ " FROM T_ZDDBHZ@UNEBD_81 ZD,ZHANDIAN Z"
			+ " WHERE ZD.ZDID = Z.ID "
			+ str
			+ " and exists (select t.agcode from per_area t where t.agcode = z.xiaoqu and t.accountid = '"
			+ loginId
			+ "')";
		
		System.out.println("建议生产标与生产标对比查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ComparedBean bean = new ComparedBean();
				String city = rs.getString(1)!=null?rs.getString(1):"";//市	
				String xian = rs.getString(2)!=null?rs.getString(2):"";//区县	
				String zdname = rs.getString(3)!=null?rs.getString(3):"";//站点名称
				String zdid = rs.getString(4)!=null?rs.getString(4):"";//站点ID	
				String zdsx = rs.getString(5)!=null?rs.getString(5):"";//站点属性	
				String zdlx = rs.getString(6)!=null?rs.getString(6):"";//站点类型
				String scb = rs.getString(7)!=null?rs.getString(7):"";//生产标(度/天)	
				String jyscb = rs.getString(8)!=null?rs.getString(8):"";//建议生产标(度/天)	 
				String xcbl = rs.getString(9)!=null?rs.getString(9):"";//相差比例
				
				bean.setCity(city);
				bean.setXian(xian);
				bean.setZdname(zdname);
				bean.setId(zdid);
				bean.setZdsx(zdsx);	
				bean.setZdlx(zdlx);
				bean.setScb(scb);	
				bean.setJyscb(jyscb);
				bean.setXcbl(Format.str2(xcbl));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
}
