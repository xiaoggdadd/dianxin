package com.ptac.app.priceover.citycheck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;

/**
 * @author lijing
 * @see 单价超标市审核实现层
 */
public class CityCheckDaoImp implements CityCheckDao {

	@Override
	public List<CityCheckBean> queryExport(String string, String loginId,
			String beginbl,String endbl) {
		
		List<CityCheckBean> list = new ArrayList<CityCheckBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(SHI),JZNAME,PLD,BZPRICE,SJPRICE,ACTUALPAY,BLHDL,RTNAME(PROPERTY),"
				 + "(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " rtname(GDFS),ZDID,RNDIQU(XIAN),RNDIQU(XIAOQU),CITYAUDIT,ID,TO_CHAR(ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,PROVINCEAUDIT"
				 + " FROM PRICEPROCEDURE WHERE 1=1  AND COUNTYCHECK = '1' "
				 + string
				 + (beginbl==""?"":(" AND PLD >="+beginbl))+ (endbl==""?"":(" AND PLD <="+endbl)));
		
		System.out.println("市审核信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CityCheckBean bean = new CityCheckBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//站点名称
				String pld = rs.getString(3) != null ? rs.getString(3) : "";//偏离度
				String bzdj = rs.getString(4) != null ? rs.getString(4) : "";//标准单价
				String sjdj = rs.getString(5) != null ? rs.getString(5) : "";//实际单价
				String bzdf = rs.getString(6) != null ? rs.getString(6) : "";//报账电费
				String bzdl = rs.getString(7) != null ? rs.getString(7) : "";//报账电量
				String zdsx = rs.getString(8) != null ? rs.getString(8) : "";//站点属性
				String zdlx = rs.getString(9) != null ? rs.getString(9) : "";//站点类型
				String gdfs = rs.getString(10) != null ? rs.getString(10) : "";//供电方式
				String zdid = rs.getString(11) != null ? rs.getString(11) : "";//站点ID
				String xian = rs.getString(12) != null ? rs.getString(12) : "";//区县
				String xiaoqu = rs.getString(13) != null ? rs.getString(13) : "";//乡镇
				String state = rs.getString(14) != null ? rs.getString(14) : "";//市审核状态
				String id = rs.getString(15) != null ? rs.getString(15) : "";
				String accountmonth = rs.getString(16) != null ? rs.getString(16) : "";//报账月份
				String shbzw = rs.getString(17) != null ? rs.getString(17) : "";//省审核标志位
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				if("0".equals(state)){
					bean.setState("未审核");
				}else if("1".equals(state)){
					bean.setState("通过");
				}else if("2".equals(state)){
					bean.setState("退单");
				}
				bean.setId(id);
				bean.setAccountmonth(accountmonth);
				bean.setShbzw(shbzw);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public CityCheckBean getInfo(String id, String bzyf) {
		
		CityCheckBean bean = new CityCheckBean();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT JZNAME,ZDID,RTNAME(PROPERTY),(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " RNDIQU(SHI),RNDIQU(XIAN),RNDIQU(XIAOQU),to_char(ACCOUNTMONTH,'yyyy-mm'),ACTUALPAY,BLHDL,SJPRICE,BZPRICE,PLD,rtname(GDFS),rtname(YZYDLX),YZHDJCDJ,"
				 + " HZDJ,BZPRICE,JFDLZB,PDLZB,ZGDJCDJ,GFDLZB,GDLZB,BYQRL,BL,rtname(YDSX),QSDBDL,XBSL,XBSDL,round(GLFSFXS,2),round(MSSFXS,2),ID"
				 + " FROM PRICEPROCEDURE WHERE ID = '"+id+"'" +(bzyf==""?"":("  AND to_char(ACCOUNTMONTH,'yyyy-mm') = '"+bzyf+"'")));
		
		System.out.println("市审核详细信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			if(rs.next()){
				
				String zdname = rs.getString(1) != null ? rs.getString(1) : "";//站点名称
				String zdid = rs.getString(2) != null ? rs.getString(2) : "";//站点ID
				String zdsx = rs.getString(3) != null ? rs.getString(3) : "";//站点属性
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//站点类型
				String city = rs.getString(5) != null ? rs.getString(5) : "";//市
				String xian = rs.getString(6) != null ? rs.getString(6) : "";//区县
				String xiaoqu = rs.getString(7) != null ? rs.getString(7) : "";//乡镇
				String accountmonth = rs.getString(8) != null ? rs.getString(8) : "";//报账月份
				String bzdf = rs.getString(9) != null ? rs.getString(9) : "";//报账电费
				String bzdl = rs.getString(10) != null ? rs.getString(10) : "";//报账电量
				String sjdj = rs.getString(11) != null ? rs.getString(11) : "";//实际单价
				String bzdj = rs.getString(12) != null ? rs.getString(12) : "";//标准单价
				String pld = rs.getString(13) != null ? rs.getString(13) : "";//偏离度
				String gdfs = rs.getString(14) != null ? rs.getString(14) : "";//供电方式
				String yzydlx = rs.getString(15) != null ? rs.getString(15) : "";//业主用电类型
				String yzhdjcdj = rs.getString(16) != null ? rs.getString(16) : "";//业主获电基础单价
				String hzdj = rs.getString(17) != null ? rs.getString(17) : "";//核准单价
				String sbzdj = rs.getString(18) != null ? rs.getString(18) : "";//省标准单价
				String jfdlzb = rs.getString(19) != null ? rs.getString(19) : "";//尖峰电量(占比)
				String pdlzb = rs.getString(20) != null ? rs.getString(20) : "";//平电量(占比)
				String zgdjcdj = rs.getString(21) != null ? rs.getString(21) : "";//直供电基础单价
				String gfdlzb = rs.getString(22) != null ? rs.getString(22) : "";//高峰电量(占比)
				String gdlzb = rs.getString(23) != null ? rs.getString(23) : "";//谷电量(占比)
				String byqrl = rs.getString(24) != null ? rs.getString(24) : "";//变压器容量
				String beilv = rs.getString(25) != null ? rs.getString(25) : "";//倍率
				String ydsx = rs.getString(26) != null ? rs.getString(26) : "";//用电属性(生产/非生产)
				String sdb = rs.getString(27) != null ? rs.getString(27) : "";//省定标
				String xbsl = rs.getString(28) != null ? rs.getString(28) : "";//线变损率
				String xbsdl = rs.getString(29) != null ? rs.getString(29) : "";//线变损电量(线变损率*省定标)
				String glfsfxs = rs.getString(30) != null ? rs.getString(30) : "";//管理费上浮系数(%)
				String msfxs = rs.getString(31) != null ? rs.getString(31) : "";//忙时上浮系数(%)
				String id1 = rs.getString(32) != null ? rs.getString(32) : "";
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setAccountmonth(accountmonth);
				bean.setYzhdjcdj(yzhdjcdj);
				bean.setYzydlx(yzydlx);
				bean.setHzdj(hzdj);
				bean.setSbzdj(sbzdj);
				bean.setJfdlzb(jfdlzb);
				bean.setPdlzb(pdlzb);
				bean.setZgdjcdj(zgdjcdj);
				bean.setGfdlzb(gfdlzb);
				bean.setGdlzb(gdlzb);
				bean.setByqrl(byqrl);
				bean.setBeilv(beilv);
				bean.setYdsx(ydsx);
				bean.setSdb(sdb);
				bean.setXbsl(xbsl);
				bean.setXbsdl(xbsdl);
				bean.setGlfsfxs(glfsfxs);
				bean.setMsfxs(msfxs);
				bean.setId(id1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return bean;
	}

	@Override
	public int tdUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set CITYAUDIT='2',CITYAUDITPERSON='"+loginName+"',CITYAUDITTIME=sysdate where ID in ("+id+")";
		System.out.println("市退单："+sql.toString());
		DataBase db = new DataBase();
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

	@Override
	public int tgUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set CITYAUDIT='1',CITYAUDITPERSON='"+loginName+"',CITYAUDITTIME=sysdate where ID in ("+id+")";
		System.out.println("市审核通过："+sql.toString());
		DataBase db = new DataBase();
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

}
