package com.ptac.app.priceover.documentquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.documentquery.bean.*;;

/**
 * @author zhouxue
 * @see 单价超标省级审核实现层
 */
public class DocumentQueryDaoImp implements DocumentQueryDao {

	@Override
	public List<DocumentQueryBean> queryExport(String string, String loginId,
			String beginbl,String endbl) {
		
		List<DocumentQueryBean> list = new ArrayList<DocumentQueryBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT P.ID,P.ZDID,RNDIQU(P.SHI),RNDIQU(P.XIAN),RNDIQU(P.XIAOQU),P.JZNAME,RTNAME(P.PROPERTY)," +
				"(SELECT NAME FROM INDEXS WHERE CODE = P.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
				"RTNAME(P.GDFS),P.PLD,TO_CHAR(P.ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,P.PROVINCEPD,P.CITYSIGN,P.CITYPD,P.COUNTYSIGN," +
				"P.COUNTYCHECK,P.CITYAUDIT,P.PROVINCEAUDIT FROM PRICEPROCEDURE P WHERE 1=1"  
				 + string
				 + (beginbl==""?"":(" AND P.PLD >="+beginbl))+ (endbl==""?"":(" AND P.PLD <="+endbl)));
		
		System.out.println("工单信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				DocumentQueryBean bean = new DocumentQueryBean();
				
				bean.setId(rs.getString(1) != null ? rs.getString(1) : "");
				bean.setZdid(rs.getString(2) != null ? rs.getString(2) : "");
				bean.setCity(rs.getString(3) != null ? rs.getString(3) : "");
				bean.setXian(rs.getString(4) != null ? rs.getString(4) : "");
				bean.setXiaoqu(rs.getString(5) != null ? rs.getString(5) : "");
				bean.setZdname(rs.getString(6) != null ? rs.getString(6) : "");
				bean.setZdsx(rs.getString(7) != null ? rs.getString(7) : "");
				bean.setZdlx(rs.getString(8) != null ? rs.getString(8) : "");
				bean.setGdfs(rs.getString(9) != null ? rs.getString(9) : "");
				bean.setPld(Format.str2(rs.getString(10) != null ? rs.getString(10) : "0"));
				bean.setAccountmonth(rs.getString(11) != null ? rs.getString(11) : "");
				String shengpd = rs.getString(12) != null ? rs.getString(12) : "";//省级派单
				String shiqs = rs.getString(13) != null ? rs.getString(13) : "";//市签收
				String shipd = rs.getString(14) != null ? rs.getString(14) : "";//市派单
				String qxqs = rs.getString(15) != null ? rs.getString(15) : "";//区县签收
				String qxhs = rs.getString(16) != null ? rs.getString(16) : "";//区县核实
				String shish = rs.getString(17) != null ? rs.getString(17) : "";//市审核
				String state = rs.getString(18) != null ? rs.getString(18) : "";//省级审核状态
				
				if("1".equals(shengpd)){
					bean.setShengpd("已派单");
				}else{
					bean.setShengpd("未派单");
				}
				if("1".equals(shiqs)){
					bean.setShiqs("已签收");
				}else{
					bean.setShiqs("未签收");
				}
				if("1".equals(shipd)){
					bean.setShipd("已派单");
				}else{
					bean.setShipd("未派单");
				}
				if("1".equals(qxqs)){
					bean.setQxqs("已签收");
				}else{
					bean.setQxqs("未签收");
				}
				if("1".equals(qxhs)){
					bean.setQxhs("已核实");
				}else{
					bean.setQxhs("未核实");
				}
				if("1".equals(shish)){
					bean.setShish("通过");
				}else if("2".equals(state)){
					bean.setShish("退单");
				}else{
					bean.setShish("未审核");
				}
				if("1".equals(state)){
					bean.setState("结单");
				}else if("2".equals(state)){
					bean.setState("退单");
				}else{
					bean.setState("未审核");
				}
				
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}








}
