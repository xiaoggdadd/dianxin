package com.ptac.app.calibstat.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.noki.database.DataBase;
import com.ptac.app.calibstat.bean.CalibStatBean;

/**
 * @author �
 * @see ����ͳ��ʵ�ֲ�
 */
public class CalibStatDaoImp implements CalibStatDao {

	/**
	 * @author �
	 * @see ��ϸ��ѯ������
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
			
			System.out.println("��ϸ��ѯ��������"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CalibStatBean bean = new CalibStatBean();

				String city = rs.getString(1)!=null?rs.getString(1):"";//����	
				String xian = rs.getString(2)!=null?rs.getString(2):"";//����	
				String xiaoqu = rs.getString(3)!=null?rs.getString(3):"";//����	
				String zdname = rs.getString(4)!=null?rs.getString(4):"";//վ������
				String zdid = rs.getString(5)!=null?rs.getString(5):"";//վ��ID	
				String zdsx = rs.getString(6)!=null?rs.getString(6):"";//վ������	
				String zlfh = rs.getString(7)!=null?rs.getString(7):"";//ֱ������(A)	
				String jlfh = rs.getString(8)!=null?rs.getString(8):"";//��������(A)	
				String bddb = rs.getString(9)!=null?rs.getString(9):"";//���ض���(��/��)	
				String scb = rs.getString(10)!=null?rs.getString(10):"";//������(��/��)	
				String nhjcz = rs.getString(11)!=null?rs.getString(11):"";//�ܺĻ���ֵ	
				String sjz = rs.getString(12)!=null?rs.getString(12):"";//ʵ��ֵ(��/��)	
				String jyscb = rs.getString(13)!=null?rs.getString(13):"";//����������(��/��)	
				String bddbzb = rs.getString(14)!=null?rs.getString(14):"";//���ض���ռ��	
				String jyscbzb = rs.getString(15)!=null?rs.getString(15):"";//����������ռ��	
				String scbzb = rs.getString(16)!=null?rs.getString(16):"";//������ռ��	
				String nhjczzb = rs.getString(17)!=null?rs.getString(17):"";//�ܺĻ���ֵռ��	
				String sjzzb = rs.getString(18)!=null?rs.getString(18):"";//ʵ��ֵռ��	
				String hdl1 = rs.getString(19)!=null?rs.getString(19):"";//xx��01�ºĵ���(��/��)	
				String hdl2 = rs.getString(20)!=null?rs.getString(20):"";//xx��02�ºĵ���(��/��)	
				String hdl3 = rs.getString(21)!=null?rs.getString(21):"";//xx��03�ºĵ���(��/��)	
				String hdl4 = rs.getString(22)!=null?rs.getString(22):"";//xx��04�ºĵ���(��/��)	
				String hdl5 = rs.getString(23)!=null?rs.getString(23):"";//xx��05�ºĵ���(��/��)	
				String hdl6 = rs.getString(24)!=null?rs.getString(24):"";//xx��06�ºĵ���(��/��)	
				String hdl7 = rs.getString(25)!=null?rs.getString(25):"";//xx��07�ºĵ���(��/��)	
				String hdl8 = rs.getString(26)!=null?rs.getString(26):"";//xx��08�ºĵ���(��/��)	
				String hdl9 = rs.getString(27)!=null?rs.getString(27):"";//xx��09�ºĵ���(��/��)	
				String hdl10 = rs.getString(28)!=null?rs.getString(28):"";//xx��10�ºĵ���(��/��)	
				String hdl11 = rs.getString(29)!=null?rs.getString(29):"";//xx��11�ºĵ���(��/��)	
				String hdl12 = rs.getString(30)!=null?rs.getString(30):"";//xx��12�ºĵ���(��/��)
				String zdlx = rs.getString(31)!=null?rs.getString(31):"";//վ������
				
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
	 * @author �
	 * @see ��վ�㳬�������ѯ
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
			
			System.out.println("��վ�㳬�������ѯ��վ����Ϣ����"+sql);
			System.out.println("��վ�㳬�������ѯ��������Ϣ����"+sql1);
			
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
				String scb = rs.getString(10)!= null ? rs.getString(10) : "";//������
				String zdlx = rs.getString(11)!= null ? rs.getString(11) : "";
				String fwlx = rs.getString(12)!= null ? rs.getString(12) : "";//��������
				String ktgs = rs.getString(13)!= null ? rs.getString(13) : "";//�յ�����
				
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

				String hdl1 = rs1.getString(1)!=null?rs1.getString(1):"";//xx��01�ºĵ���(��/��)	
				String hdl2 = rs1.getString(2)!=null?rs1.getString(2):"";//xx��02�ºĵ���(��/��)	
				String hdl3 = rs1.getString(3)!=null?rs1.getString(3):"";//xx��03�ºĵ���(��/��)	
				String hdl4 = rs1.getString(4)!=null?rs1.getString(4):"";//xx��04�ºĵ���(��/��)	
				String hdl5 = rs1.getString(5)!=null?rs1.getString(5):"";//xx��05�ºĵ���(��/��)	
				String hdl6 = rs1.getString(6)!=null?rs1.getString(6):"";//xx��06�ºĵ���(��/��)	
				String hdl7 = rs1.getString(7)!=null?rs1.getString(7):"";//xx��07�ºĵ���(��/��)	
				String hdl8 = rs1.getString(8)!=null?rs1.getString(8):"";//xx��08�ºĵ���(��/��)	
				String hdl9 = rs1.getString(9)!=null?rs1.getString(9):"";//xx��09�ºĵ���(��/��)	
				String hdl10 = rs1.getString(10)!=null?rs1.getString(10):"";//xx��10�ºĵ���(��/��)	
				String hdl11 = rs1.getString(11)!=null?rs1.getString(11):"";//xx��11�ºĵ���(��/��)	
				String hdl12 = rs1.getString(12)!=null?rs1.getString(12):"";//xx��12�ºĵ���(��/��)
				String qsdbdl01 = rs1.getString(13)!= null ? rs1.getString(13) : "";//ȫʡ�������1��
				String qsdbdl02 = rs1.getString(14)!= null ? rs1.getString(14) : "";//ȫʡ�������2��
				String qsdbdl03 = rs1.getString(15)!= null ? rs1.getString(15) : "";//ȫʡ�������3��
				String qsdbdl04 = rs1.getString(16)!= null ? rs1.getString(16) : "";//ȫʡ�������4��
				String qsdbdl05 = rs1.getString(17)!= null ? rs1.getString(17) : "";//ȫʡ�������5��
				String qsdbdl06 = rs1.getString(18)!= null ? rs1.getString(18) : "";//ȫʡ�������6��
				String qsdbdl07 = rs1.getString(19)!= null ? rs1.getString(19) : "";//ȫʡ�������7��
				String qsdbdl08 = rs1.getString(20)!= null ? rs1.getString(20) : "";//ȫʡ�������8��
				String qsdbdl09 = rs1.getString(21)!= null ? rs1.getString(21) : "";//ȫʡ�������9��
				String qsdbdl10 = rs1.getString(22)!= null ? rs1.getString(22) : "";//ȫʡ�������10��
				String qsdbdl11 = rs1.getString(23)!= null ? rs1.getString(23) : "";//ȫʡ�������11��
				String qsdbdl12 = rs1.getString(24)!= null ? rs1.getString(24) : "";//ȫʡ�������12��
				
				Double cbbl01 = 0.0;//�������1��
				Double cbbl02 = 0.0;//�������2��
				Double cbbl03 = 0.0;//�������3��
				Double cbbl04 = 0.0;//�������4��
				Double cbbl05 = 0.0;//�������5��
				Double cbbl06 = 0.0;//�������6��
				Double cbbl07 = 0.0;//�������7��
				Double cbbl08 = 0.0;//�������8��
				Double cbbl09 = 0.0;//�������9��
				Double cbbl10 = 0.0;//�������10��
				Double cbbl11 = 0.0;//�������11��
				Double cbbl12 = 0.0;//�������12��
				
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
