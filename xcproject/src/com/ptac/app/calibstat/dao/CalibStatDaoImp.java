package com.ptac.app.calibstat.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.ptac.app.calibstat.bean.CalibStatBean;

/**
 * @author 李靖
 * @see 定标统计实现层
 */
public class CalibStatDaoImp implements CalibStatDao {

	/**
	 * @author 李靖
	 * @see 明细查询、导出
	 */
	@Override
	public ArrayList queryMingXi(String whereStr, String loginId) {
		
		ArrayList list = new ArrayList();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			sql = "SELECT RNDIQU(ZD.SHI),RNDIQU(ZD.XAIN),RNDIQU(ZD.XIAOQU),ZD.ZDNAME,ZD.ZDID,RTNAME(ZD.ZDSX),ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZD.SCB,ZD.NHJCZ,ZD.SJZ,ZD.JYSCB,"
				+ "ZD.EDHDLZB,ZD.JYSCBZB,ZD.SCBZD,ZD.NHJCZZB,ZD.SJZZB,ZD.RJDL1,ZD.RJDL2,ZD.RJDL3,ZD.RJDL4,ZD.RJDL5,ZD.RJDL6,ZD.RJDL7,ZD.RJDL8,ZD.RJDL9,ZD.RJDL10,ZD.RJDL11,ZD.RJDL12,RTNAME(Z.STATIONTYPE)"
				+ " FROM T_ZDDBHZ@UNEBD_81 ZD,ZHANDIAN Z"
				+ " WHERE ZD.ZDID = Z.ID "
				+ whereStr
				+ " and exists (select t.agcode from per_area t where t.agcode=z.xiaoqu and t.accountid = '"
				+ loginId
				+ "')";
			
			System.out.println("明细查询、导出："+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CalibStatBean bean = new CalibStatBean();

				String city = rs.getString(1)!=null?rs.getString(1):"";//地市	
				String xian = rs.getString(2)!=null?rs.getString(2):"";//区县	
				String xiaoqu = rs.getString(3)!=null?rs.getString(3):"";//乡镇	
				String zdname = rs.getString(4)!=null?rs.getString(4):"";//站点名称
				String zdid = rs.getString(5)!=null?rs.getString(5):"";//站点ID	
				String zdsx = rs.getString(6)!=null?rs.getString(6):"";//站点属性	
				String zlfh = rs.getString(7)!=null?rs.getString(7):"";//直流负荷(A)	
				String jlfh = rs.getString(8)!=null?rs.getString(8):"";//交流负荷(A)	
				String bddb = rs.getString(9)!=null?rs.getString(9):"";//本地定标(度/天)	
				String scb = rs.getString(10)!=null?rs.getString(10):"";//生产标(度/天)	
				String nhjcz = rs.getString(11)!=null?rs.getString(11):"";//能耗基础值	
				String sjz = rs.getString(12)!=null?rs.getString(12):"";//实际值(度/天)	
				String jyscb = rs.getString(13)!=null?rs.getString(13):"";//建议生产标(度/天)	
				String bddbzb = rs.getString(14)!=null?rs.getString(14):"";//本地定标占比	
				String jyscbzb = rs.getString(15)!=null?rs.getString(15):"";//建议生产标占比	
				String scbzb = rs.getString(16)!=null?rs.getString(16):"";//生产标占比	
				String nhjczzb = rs.getString(17)!=null?rs.getString(17):"";//能耗基础值占比	
				String sjzzb = rs.getString(18)!=null?rs.getString(18):"";//实际值占比	
				String hdl1 = rs.getString(19)!=null?rs.getString(19):"";//xx年01月耗电量(度/天)	
				String hdl2 = rs.getString(20)!=null?rs.getString(20):"";//xx年02月耗电量(度/天)	
				String hdl3 = rs.getString(21)!=null?rs.getString(21):"";//xx年03月耗电量(度/天)	
				String hdl4 = rs.getString(22)!=null?rs.getString(22):"";//xx年04月耗电量(度/天)	
				String hdl5 = rs.getString(23)!=null?rs.getString(23):"";//xx年05月耗电量(度/天)	
				String hdl6 = rs.getString(24)!=null?rs.getString(24):"";//xx年06月耗电量(度/天)	
				String hdl7 = rs.getString(25)!=null?rs.getString(25):"";//xx年07月耗电量(度/天)	
				String hdl8 = rs.getString(26)!=null?rs.getString(26):"";//xx年08月耗电量(度/天)	
				String hdl9 = rs.getString(27)!=null?rs.getString(27):"";//xx年09月耗电量(度/天)	
				String hdl10 = rs.getString(28)!=null?rs.getString(28):"";//xx年10月耗电量(度/天)	
				String hdl11 = rs.getString(29)!=null?rs.getString(29):"";//xx年11月耗电量(度/天)	
				String hdl12 = rs.getString(30)!=null?rs.getString(30):"";//xx年12月耗电量(度/天)
				String zdlx = rs.getString(31)!=null?rs.getString(31):"";//站点类型
				
				bean.setCity(city);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setZdname(zdname);
				bean.setZdid(zdid);
				bean.setZdsx(zdsx);	
				bean.setZlfh(zlfh);
				bean.setJlfh(jlfh);
				bean.setBddb(bddb);	
				bean.setScb(scb);	
				bean.setNhjcz(nhjcz);
				bean.setSjz(sjz);
				bean.setJyscb(jyscb);
				bean.setBddbzb(bddbzb);
				bean.setJyscbzb(jyscbzb);
				bean.setScbzb(scbzb);
				bean.setNhjczzb(nhjczzb);
				bean.setSjzzb(sjzzb);
				bean.setHdl1(hdl1);
				bean.setHdl2(hdl2);
				bean.setHdl3(hdl3);
				bean.setHdl4(hdl4);
				bean.setHdl5(hdl5);
				bean.setHdl6(hdl6);
				bean.setHdl7(hdl7);
				bean.setHdl8(hdl8);
				bean.setHdl9(hdl9);
				bean.setHdl10(hdl10);
				bean.setHdl11(hdl11);
				bean.setHdl12(hdl12);
				bean.setZdlx(zdlx);
				
				list.add(bean);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}

	/**
	 * @author 李靖
	 * @see 单站点超标分析查询
	 */
	@Override
	public CalibStatBean querySingleSite(String whereStr,String str,String loginId) {
		
		CalibStatBean bean = new CalibStatBean();
		String sql = "",sql1 = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		try {
			sql = "SELECT ZD.JZNAME,ZD.ID,RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,"
				+ "RTNAME(ZD.PROPERTY) PROPERTY,ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZD.SCB,RTNAME(ZD.STATIONTYPE) STATIONTYPE,RTNAME(ZD.YFLX) YFLX,ZD.KTS"
				+ " FROM ZHANDIAN ZD,T_ZDDBSDB@UNEBD_81 Z"
				+ " WHERE ZD.ID = Z.ZDID"
				+ whereStr
				+ " and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
				+ loginId
				+ "')";
			
			sql1 = "SELECT ZD.RJDL1,ZD.RJDL2,ZD.RJDL3,ZD.RJDL4,ZD.RJDL5,ZD.RJDL6,ZD.RJDL7,ZD.RJDL8,ZD.RJDL9,ZD.RJDL10,ZD.RJDL11,ZD.RJDL12,"
				 + "Z.RJDL1,Z.RJDL2,Z.RJDL3,Z.RJDL4,Z.RJDL5,Z.RJDL6,Z.RJDL7,Z.RJDL8,Z.RJDL9,Z.RJDL10,Z.RJDL11,Z.RJDL12"
				 + " FROM T_ZDDBHZ@UNEBD_81 ZD,T_ZDDBSDB@UNEBD_81 Z,ZHANDIAN ZZ"
				 + " WHERE ZD.ZDID = Z.ZDID AND Z.ZDID = ZZ.ID "
				 + str
				 + " and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
				 + loginId
				 + "')";
			
			System.out.println("单站点超标分析查询（站点信息）："+sql);
			System.out.println("单站点超标分析查询（超标信息）："+sql1);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ps1 = conn.prepareStatement(sql1);
			rs1 = ps1.executeQuery();
			
			if(rs.next()){
				
				String zdname = rs.getString(1)!= null ? rs.getString(1) : "";
				String zdid = rs.getString(2)!= null ? rs.getString(2) : "";
				String city = rs.getString(3)!= null ? rs.getString(3) : "";
				String xian = rs.getString(4)!= null ? rs.getString(4) : "";
				String xiaoqu = rs.getString(5)!= null ? rs.getString(5) : "";
				String zdsx = rs.getString(6)!= null ? rs.getString(6) : "";
				String zlfh = rs.getString(7)!= null ? rs.getString(7) : "";
				String jlfh = rs.getString(8)!= null ? rs.getString(8) : "";
				String edhdl = rs.getString(9)!= null ? rs.getString(9) : "";
				String scb = rs.getString(10)!= null ? rs.getString(10) : "";//生产标
				String zdlx = rs.getString(11)!= null ? rs.getString(11) : "";
				String fwlx = rs.getString(12)!= null ? rs.getString(12) : "";//房屋类型
				String ktgs = rs.getString(13)!= null ? rs.getString(13) : "";//空调个数
				
				bean.setZdname(zdname);
				bean.setZdid(zdid);
				bean.setCity(city);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setZdsx(zdsx);	
				bean.setZlfh(zlfh);
				bean.setJlfh(jlfh);
				bean.setEdhdl(edhdl);
				bean.setScb(scb);
				bean.setZdlx(zdlx);
				bean.setFwlx(fwlx);
				bean.setKtgs(ktgs);
			}
			
			if(rs1.next()){

				String hdl1 = rs1.getString(1)!=null?rs1.getString(1):"";//xx年01月耗电量(度/天)	
				String hdl2 = rs1.getString(2)!=null?rs1.getString(2):"";//xx年02月耗电量(度/天)	
				String hdl3 = rs1.getString(3)!=null?rs1.getString(3):"";//xx年03月耗电量(度/天)	
				String hdl4 = rs1.getString(4)!=null?rs1.getString(4):"";//xx年04月耗电量(度/天)	
				String hdl5 = rs1.getString(5)!=null?rs1.getString(5):"";//xx年05月耗电量(度/天)	
				String hdl6 = rs1.getString(6)!=null?rs1.getString(6):"";//xx年06月耗电量(度/天)	
				String hdl7 = rs1.getString(7)!=null?rs1.getString(7):"";//xx年07月耗电量(度/天)	
				String hdl8 = rs1.getString(8)!=null?rs1.getString(8):"";//xx年08月耗电量(度/天)	
				String hdl9 = rs1.getString(9)!=null?rs1.getString(9):"";//xx年09月耗电量(度/天)	
				String hdl10 = rs1.getString(10)!=null?rs1.getString(10):"";//xx年10月耗电量(度/天)	
				String hdl11 = rs1.getString(11)!=null?rs1.getString(11):"";//xx年11月耗电量(度/天)	
				String hdl12 = rs1.getString(12)!=null?rs1.getString(12):"";//xx年12月耗电量(度/天)
				String qsdbdl01 = rs1.getString(13)!= null ? rs1.getString(13) : "";//全省定标电量1月
				String qsdbdl02 = rs1.getString(14)!= null ? rs1.getString(14) : "";//全省定标电量2月
				String qsdbdl03 = rs1.getString(15)!= null ? rs1.getString(15) : "";//全省定标电量3月
				String qsdbdl04 = rs1.getString(16)!= null ? rs1.getString(16) : "";//全省定标电量4月
				String qsdbdl05 = rs1.getString(17)!= null ? rs1.getString(17) : "";//全省定标电量5月
				String qsdbdl06 = rs1.getString(18)!= null ? rs1.getString(18) : "";//全省定标电量6月
				String qsdbdl07 = rs1.getString(19)!= null ? rs1.getString(19) : "";//全省定标电量7月
				String qsdbdl08 = rs1.getString(20)!= null ? rs1.getString(20) : "";//全省定标电量8月
				String qsdbdl09 = rs1.getString(21)!= null ? rs1.getString(21) : "";//全省定标电量9月
				String qsdbdl10 = rs1.getString(22)!= null ? rs1.getString(22) : "";//全省定标电量10月
				String qsdbdl11 = rs1.getString(23)!= null ? rs1.getString(23) : "";//全省定标电量11月
				String qsdbdl12 = rs1.getString(24)!= null ? rs1.getString(24) : "";//全省定标电量12月
				
				Double cbbl01 = 0.0;//抄表比例1月
				Double cbbl02 = 0.0;//抄表比例2月
				Double cbbl03 = 0.0;//抄表比例3月
				Double cbbl04 = 0.0;//抄表比例4月
				Double cbbl05 = 0.0;//抄表比例5月
				Double cbbl06 = 0.0;//抄表比例6月
				Double cbbl07 = 0.0;//抄表比例7月
				Double cbbl08 = 0.0;//抄表比例8月
				Double cbbl09 = 0.0;//抄表比例9月
				Double cbbl10 = 0.0;//抄表比例10月
				Double cbbl11 = 0.0;//抄表比例11月
				Double cbbl12 = 0.0;//抄表比例12月
				
				if(!"".equals(qsdbdl01)&&!"".equals(hdl1)){
						cbbl01 = (Double.parseDouble(hdl1) - Double.parseDouble(qsdbdl01))/ Double.parseDouble(qsdbdl01) * 100;
						BigDecimal bg = new BigDecimal(cbbl01);
						cbbl01 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl02)&&!"".equals(hdl2)){
					cbbl02 = (Double.parseDouble(hdl2) - Double.parseDouble(qsdbdl02))/ Double.parseDouble(qsdbdl02) * 100;
					BigDecimal bg = new BigDecimal(cbbl02);
					cbbl02 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl03)&&!"".equals(hdl3)){
					cbbl03 = (Double.parseDouble(hdl3) - Double.parseDouble(qsdbdl03))/ Double.parseDouble(qsdbdl03) * 100;
					BigDecimal bg = new BigDecimal(cbbl03);
					cbbl03 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl04)&&!"".equals(hdl4)){
					cbbl04 = (Double.parseDouble(hdl4) - Double.parseDouble(qsdbdl04))/ Double.parseDouble(qsdbdl04) * 100;
					BigDecimal bg = new BigDecimal(cbbl04);
					cbbl04 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl05)&&!"".equals(hdl5)){
					cbbl05 = (Double.parseDouble(hdl5) - Double.parseDouble(qsdbdl05))/ Double.parseDouble(qsdbdl05) * 100;
					BigDecimal bg = new BigDecimal(cbbl05);
					cbbl05 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl06)&&!"".equals(hdl6)){
					cbbl06 = (Double.parseDouble(hdl6) - Double.parseDouble(qsdbdl06))/ Double.parseDouble(qsdbdl06) * 100;
					BigDecimal bg = new BigDecimal(cbbl06);
					cbbl06 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl07)&&!"".equals(hdl7)){
					cbbl07 = (Double.parseDouble(hdl7) - Double.parseDouble(qsdbdl07))/ Double.parseDouble(qsdbdl07) * 100;
					BigDecimal bg = new BigDecimal(cbbl07);
					cbbl07 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl08)&&!"".equals(hdl8)){
					cbbl08 = (Double.parseDouble(hdl8) - Double.parseDouble(qsdbdl08))/ Double.parseDouble(qsdbdl08) * 100;
					BigDecimal bg = new BigDecimal(cbbl08);
					cbbl08 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl09)&&!"".equals(hdl9)){
					cbbl09 = (Double.parseDouble(hdl9) - Double.parseDouble(qsdbdl09))/ Double.parseDouble(qsdbdl09) * 100;
					BigDecimal bg = new BigDecimal(cbbl09);
					cbbl09 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl10)&&!"".equals(hdl10)){
					cbbl10 = (Double.parseDouble(hdl10) - Double.parseDouble(qsdbdl10))/ Double.parseDouble(qsdbdl10) * 100;
					BigDecimal bg = new BigDecimal(cbbl10);
					cbbl10 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl11)&&!"".equals(hdl11)){
					cbbl11 = (Double.parseDouble(hdl11) - Double.parseDouble(qsdbdl11))/ Double.parseDouble(qsdbdl11) * 100;
					BigDecimal bg = new BigDecimal(cbbl11);
					cbbl11 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				if(!"".equals(qsdbdl12)&&!"".equals(hdl12)){
					cbbl12 = (Double.parseDouble(hdl12) - Double.parseDouble(qsdbdl12))/ Double.parseDouble(qsdbdl12) * 100;
					BigDecimal bg = new BigDecimal(cbbl12);
					cbbl12 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				bean.setHdl1(hdl1);
				bean.setHdl2(hdl2);
				bean.setHdl3(hdl3);
				bean.setHdl4(hdl4);
				bean.setHdl5(hdl5);
				bean.setHdl6(hdl6);
				bean.setHdl7(hdl7);
				bean.setHdl8(hdl8);
				bean.setHdl9(hdl9);
				bean.setHdl10(hdl10);
				bean.setHdl11(hdl11);
				bean.setHdl12(hdl12);
				bean.setQsdbdl01(qsdbdl01);
				bean.setQsdbdl02(qsdbdl02);
				bean.setQsdbdl03(qsdbdl03);
				bean.setQsdbdl04(qsdbdl04);
				bean.setQsdbdl05(qsdbdl05);
				bean.setQsdbdl06(qsdbdl06);
				bean.setQsdbdl07(qsdbdl07);
				bean.setQsdbdl08(qsdbdl08);
				bean.setQsdbdl09(qsdbdl09);
				bean.setQsdbdl10(qsdbdl10);
				bean.setQsdbdl11(qsdbdl11);
				bean.setQsdbdl12(qsdbdl12);
				bean.setCbbl01(cbbl01.toString()+ "%");
				bean.setCbbl02(cbbl02.toString()+ "%");
				bean.setCbbl03(cbbl03.toString()+ "%");
				bean.setCbbl04(cbbl04.toString()+ "%");
				bean.setCbbl05(cbbl05.toString()+ "%");
				bean.setCbbl06(cbbl06.toString()+ "%");
				bean.setCbbl07(cbbl07.toString()+ "%");
				bean.setCbbl08(cbbl08.toString()+ "%");
				bean.setCbbl09(cbbl09.toString()+ "%");
				bean.setCbbl10(cbbl10.toString()+ "%");
				bean.setCbbl11(cbbl11.toString()+ "%");
				bean.setCbbl12(cbbl12.toString()+ "%");
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return bean;
	}

}
