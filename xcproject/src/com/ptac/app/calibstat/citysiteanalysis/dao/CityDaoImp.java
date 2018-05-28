package com.ptac.app.calibstat.citysiteanalysis.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.calibstat.citysiteanalysis.bean.CityBean;

/**
 * @author �
 * @see ����վ�㳬�����ʵ�ֲ�
 */
public class CityDaoImp implements CityDao {

	/**
	 * @see ����վ�㳬����� ��ѯ������
	 */
	public List<CityBean> getQueryExport(String str, String loginId) {
		
		List<CityBean> list = new ArrayList<CityBean>();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			sql = "SELECT SUM(ZD.RJDL1),SUM(ZD.RJDL2),SUM(ZD.RJDL3),SUM(ZD.RJDL4),SUM(ZD.RJDL5),SUM(ZD.RJDL6),SUM(ZD.RJDL7),"
				 + "SUM(ZD.RJDL8),SUM(ZD.RJDL9),SUM(ZD.RJDL10),SUM(ZD.RJDL11),SUM(ZD.RJDL12),SUM(Z.RJDL1),SUM(Z.RJDL2),"
				 + "SUM(Z.RJDL3),SUM(Z.RJDL4),SUM(Z.RJDL5),SUM(Z.RJDL6),SUM(Z.RJDL7),SUM(Z.RJDL8),SUM(Z.RJDL9),"
				 + "SUM(Z.RJDL10),SUM(Z.RJDL11),SUM(Z.RJDL12),RNDIQU(ZD.XAIN),ZD.XAIN"
				 + " FROM T_ZDDBHZ@UNEBD_81 ZD, T_ZDDBSDB@UNEBD_81 Z,ZHANDIAN ZZ"
				 + " WHERE ZD.ZDID = Z.ZDID AND Z.ZDID = ZZ.ID "
				 + str
				 + " and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
				 + loginId
				 + "') GROUP BY ZD.XAIN";
			
			System.out.println("����վ�㳬�������"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){

				CityBean bean = new CityBean();
				String hdl1 = rs.getString(1)!=null?rs.getString(1):"";//xx��01�ºĵ���(��/��)	
				String hdl2 = rs.getString(2)!=null?rs.getString(2):"";//xx��02�ºĵ���(��/��)	
				String hdl3 = rs.getString(3)!=null?rs.getString(3):"";//xx��03�ºĵ���(��/��)	
				String hdl4 = rs.getString(4)!=null?rs.getString(4):"";//xx��04�ºĵ���(��/��)	
				String hdl5 = rs.getString(5)!=null?rs.getString(5):"";//xx��05�ºĵ���(��/��)	
				String hdl6 = rs.getString(6)!=null?rs.getString(6):"";//xx��06�ºĵ���(��/��)	
				String hdl7 = rs.getString(7)!=null?rs.getString(7):"";//xx��07�ºĵ���(��/��)	
				String hdl8 = rs.getString(8)!=null?rs.getString(8):"";//xx��08�ºĵ���(��/��)	
				String hdl9 = rs.getString(9)!=null?rs.getString(9):"";//xx��09�ºĵ���(��/��)	
				String hdl10 = rs.getString(10)!=null?rs.getString(10):"";//xx��10�ºĵ���(��/��)	
				String hdl11 = rs.getString(11)!=null?rs.getString(11):"";//xx��11�ºĵ���(��/��)	
				String hdl12 = rs.getString(12)!=null?rs.getString(12):"";//xx��12�ºĵ���(��/��)
				String qsdbdl01 = rs.getString(13)!= null ? rs.getString(13) : "";//ȫʡ�������1��
				String qsdbdl02 = rs.getString(14)!= null ? rs.getString(14) : "";//ȫʡ�������2��
				String qsdbdl03 = rs.getString(15)!= null ? rs.getString(15) : "";//ȫʡ�������3��
				String qsdbdl04 = rs.getString(16)!= null ? rs.getString(16) : "";//ȫʡ�������4��
				String qsdbdl05 = rs.getString(17)!= null ? rs.getString(17) : "";//ȫʡ�������5��
				String qsdbdl06 = rs.getString(18)!= null ? rs.getString(18) : "";//ȫʡ�������6��
				String qsdbdl07 = rs.getString(19)!= null ? rs.getString(19) : "";//ȫʡ�������7��
				String qsdbdl08 = rs.getString(20)!= null ? rs.getString(20) : "";//ȫʡ�������8��
				String qsdbdl09 = rs.getString(21)!= null ? rs.getString(21) : "";//ȫʡ�������9��
				String qsdbdl10 = rs.getString(22)!= null ? rs.getString(22) : "";//ȫʡ�������10��
				String qsdbdl11 = rs.getString(23)!= null ? rs.getString(23) : "";//ȫʡ�������11��
				String qsdbdl12 = rs.getString(24)!= null ? rs.getString(24) : "";//ȫʡ�������12��
				String county = rs.getString(25)!= null ? rs.getString(25) : "";//��(����)
				String xian = rs.getString(26)!= null ? rs.getString(26) : "";//��(code��)
				
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
				bean.setCounty(county);
				bean.setXian(xian);
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
	 * @see ����վ�㳬���������ͼ����Ϣ
	 */
	public CityBean getXiangXi(String str, String loginId) {
		
		CityBean bean = new CityBean();
		String sql = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			sql = "SELECT SUM(ZD.RJDL1),SUM(ZD.RJDL2),SUM(ZD.RJDL3),SUM(ZD.RJDL4),SUM(ZD.RJDL5),SUM(ZD.RJDL6),SUM(ZD.RJDL7),"
				 + "SUM(ZD.RJDL8),SUM(ZD.RJDL9),SUM(ZD.RJDL10),SUM(ZD.RJDL11),SUM(ZD.RJDL12),SUM(Z.RJDL1),SUM(Z.RJDL2),"
				 + "SUM(Z.RJDL3),SUM(Z.RJDL4),SUM(Z.RJDL5),SUM(Z.RJDL6),SUM(Z.RJDL7),SUM(Z.RJDL8),SUM(Z.RJDL9),"
				 + "SUM(Z.RJDL10),SUM(Z.RJDL11),SUM(Z.RJDL12),RNDIQU(ZD.XAIN)"
				 + " FROM T_ZDDBHZ@UNEBD_81 ZD, T_ZDDBSDB@UNEBD_81 Z,ZHANDIAN ZZ"
				 + " WHERE ZD.ZDID = Z.ZDID AND Z.ZDID = ZZ.ID "
				 + str
				 + " and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
				 + loginId
				 + "') GROUP BY ZD.XAIN";
			
			System.out.println("����վ�㳬���������ͼ����Ϣ��"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				String hdl1 = rs.getString(1)!=null?rs.getString(1):"";//xx��01�ºĵ���(��/��)	
				String hdl2 = rs.getString(2)!=null?rs.getString(2):"";//xx��02�ºĵ���(��/��)	
				String hdl3 = rs.getString(3)!=null?rs.getString(3):"";//xx��03�ºĵ���(��/��)	
				String hdl4 = rs.getString(4)!=null?rs.getString(4):"";//xx��04�ºĵ���(��/��)	
				String hdl5 = rs.getString(5)!=null?rs.getString(5):"";//xx��05�ºĵ���(��/��)	
				String hdl6 = rs.getString(6)!=null?rs.getString(6):"";//xx��06�ºĵ���(��/��)	
				String hdl7 = rs.getString(7)!=null?rs.getString(7):"";//xx��07�ºĵ���(��/��)	
				String hdl8 = rs.getString(8)!=null?rs.getString(8):"";//xx��08�ºĵ���(��/��)	
				String hdl9 = rs.getString(9)!=null?rs.getString(9):"";//xx��09�ºĵ���(��/��)	
				String hdl10 = rs.getString(10)!=null?rs.getString(10):"";//xx��10�ºĵ���(��/��)	
				String hdl11 = rs.getString(11)!=null?rs.getString(11):"";//xx��11�ºĵ���(��/��)	
				String hdl12 = rs.getString(12)!=null?rs.getString(12):"";//xx��12�ºĵ���(��/��)
				String qsdbdl01 = rs.getString(13)!= null ? rs.getString(13) : "";//ȫʡ�������1��
				String qsdbdl02 = rs.getString(14)!= null ? rs.getString(14) : "";//ȫʡ�������2��
				String qsdbdl03 = rs.getString(15)!= null ? rs.getString(15) : "";//ȫʡ�������3��
				String qsdbdl04 = rs.getString(16)!= null ? rs.getString(16) : "";//ȫʡ�������4��
				String qsdbdl05 = rs.getString(17)!= null ? rs.getString(17) : "";//ȫʡ�������5��
				String qsdbdl06 = rs.getString(18)!= null ? rs.getString(18) : "";//ȫʡ�������6��
				String qsdbdl07 = rs.getString(19)!= null ? rs.getString(19) : "";//ȫʡ�������7��
				String qsdbdl08 = rs.getString(20)!= null ? rs.getString(20) : "";//ȫʡ�������8��
				String qsdbdl09 = rs.getString(21)!= null ? rs.getString(21) : "";//ȫʡ�������9��
				String qsdbdl10 = rs.getString(22)!= null ? rs.getString(22) : "";//ȫʡ�������10��
				String qsdbdl11 = rs.getString(23)!= null ? rs.getString(23) : "";//ȫʡ�������11��
				String qsdbdl12 = rs.getString(24)!= null ? rs.getString(24) : "";//ȫʡ�������12��
				String county = rs.getString(25)!= null ? rs.getString(25) : "";//��(����)
				
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
				bean.setCounty(county);
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return bean;
	}

}
