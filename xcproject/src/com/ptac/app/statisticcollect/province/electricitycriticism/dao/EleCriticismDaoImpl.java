package com.ptac.app.statisticcollect.province.electricitycriticism.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.electricitycriticism.bean.EleCriticismBean;

public class EleCriticismDaoImpl implements  EleCriticismDao{
	public List<EleCriticismBean> checkPrice(String wherestr,String cbbly,String cbble){
		List<EleCriticismBean> list = new ArrayList<EleCriticismBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
     sql.append(" SELECT RNDIQU(HJ.SHI),COUNT(HJ.ID), COUNT ((CASE WHEN ((hj.dl - hj.bzz) / hj.bzz) * 100 > '"+cbbly+"' THEN HJ.ID END)) BLY," +
		" COUNT ((CASE WHEN ((hj.dl - hj.bzz) / hj.bzz) * 100 > '"+cbble+"' THEN HJ.ID END)) BLE " +
		"FROM (SELECT Z.SHI,Z.ID,SUM(A.BLHDL)DL,SUM(A.BZZ)BZZ FROM ZHANDIAN Z,DIANBIAO D, AMMETERDEGREES A,ELECTRICFEES E" +
		" WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK  AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK" +
		" AND Z.SHSIGN = '1' AND Z.XUNI = '0'  AND Z.CAIJI = '0'  AND Z.JZNAME NOT LIKE '%����%'" +
		" AND Z.STATIONTYPE <> 'StationType35' AND Z.STATIONTYPE <> 'StationType36'  AND Z.STATIONTYPE <> 'StationType37'" +
		" AND Z.STATIONTYPE <> 'StationType38'  AND Z.STATIONTYPE <> 'StationType39' AND Z.STATIONTYPE <> 'StationType40' " +
		"AND Z.STATIONTYPE <> 'StationType41' AND Z.STATIONTYPE <> 'StationType42' " +
		"AND A.BLHDL > 0 AND E.ACTUALPAY > 0  "+wherestr+" " +
		"GROUP BY Z.SHI,Z.ID)HJ GROUP BY HJ.SHI ORDER BY HJ.SHI");
     
		System.out.println("��������վ��ͨ��:"+sql.toString());
		
		try {
			db.connectDb();//�������ݿ�����
			conn = db.getConnection();//��ȡ���Ӷ���
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				

				
				EleCriticismBean bean = new EleCriticismBean();
				String bzzds = rs.getString(2)!=null?rs.getString(2):"0";
				String djcby = rs.getString(3)!=null?rs.getString(3):"0";
				String dbcbe = rs.getString(4)!=null?rs.getString(4):"0";
				double cby = 0.0;//����ռ��1
				double cbe = 0.0;//����ռ��2
				if(bzzds.equals("0")||bzzds==null||bzzds.equals("")){
					cby = 0.0;
					cbe = 0.0;
				}else{
				 cby = (Format.str_d(djcby)/Format.str_d(bzzds))*100;
				 cbe = (Format.str_d(dbcbe)/Format.str_d(bzzds))*100;
				}
				
				
				String cbzdzby = cby+"";
				String cbzdzbe = cbe+"";
				
				bean.setShi(rs.getString(1)!=null?rs.getString(1):"");//����
				bean.setBzzds(bzzds);//����վ�����
				bean.setDjcby(djcby);//��۳���1
				bean.setDbcbe(dbcbe);//��۳���2
				bean.setCbzdzby(Format.str2(cbzdzby));//��۳���ռ��1
				bean.setCbzdzbe(Format.str2(cbzdzbe));//��۳���ռ��2
				list.add(bean);
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String[] getSum(List<EleCriticismBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		EleCriticismBean bean = null;
		int zdcountsum = 0;//����վ�����ϼ�
		int cbycountsum = 0;//��������20%��վ��
		int cbecountsum = 0;//��������50%��վ��
		Double zbycountsum = 0.00;;//��������20%��վ�� ռ��
		Double zbecountsum = 0.00;;//��������50%��վ�� ռ��
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			Double zdcounthj = Double.parseDouble(bean.getBzzds());
			Double cbycounthj = Double.parseDouble(bean.getDjcby());
			Double cbecounthj = Double.parseDouble(bean.getDbcbe());
			Double zbycounthj = Double.parseDouble(bean.getCbzdzby());
			Double zbecounthj = Double.parseDouble(bean.getCbzdzbe());
			zdcountsum += zdcounthj;
			cbycountsum += cbycounthj;
			cbecountsum += cbecounthj;
			zbycountsum += zbycounthj;
			zbecountsum += zbecounthj;
		}
		sum[0] = String.valueOf(zdcountsum);
		sum[1] = String.valueOf(cbycountsum);
		sum[2] = String.valueOf(cbecountsum);
		sum[3] = String.valueOf(Format.d2(zbycountsum));
		sum[4] = String.valueOf(Format.d2(zbecountsum));
		return sum;
	}
}
