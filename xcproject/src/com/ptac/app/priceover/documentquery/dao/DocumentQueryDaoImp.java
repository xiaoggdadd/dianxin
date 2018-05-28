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
 * @see ���۳���ʡ�����ʵ�ֲ�
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
		
		System.out.println("������Ϣ��ѯ��"+sql);
		
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
				String shengpd = rs.getString(12) != null ? rs.getString(12) : "";//ʡ���ɵ�
				String shiqs = rs.getString(13) != null ? rs.getString(13) : "";//��ǩ��
				String shipd = rs.getString(14) != null ? rs.getString(14) : "";//���ɵ�
				String qxqs = rs.getString(15) != null ? rs.getString(15) : "";//����ǩ��
				String qxhs = rs.getString(16) != null ? rs.getString(16) : "";//���غ�ʵ
				String shish = rs.getString(17) != null ? rs.getString(17) : "";//�����
				String state = rs.getString(18) != null ? rs.getString(18) : "";//ʡ�����״̬
				
				if("1".equals(shengpd)){
					bean.setShengpd("���ɵ�");
				}else{
					bean.setShengpd("δ�ɵ�");
				}
				if("1".equals(shiqs)){
					bean.setShiqs("��ǩ��");
				}else{
					bean.setShiqs("δǩ��");
				}
				if("1".equals(shipd)){
					bean.setShipd("���ɵ�");
				}else{
					bean.setShipd("δ�ɵ�");
				}
				if("1".equals(qxqs)){
					bean.setQxqs("��ǩ��");
				}else{
					bean.setQxqs("δǩ��");
				}
				if("1".equals(qxhs)){
					bean.setQxhs("�Ѻ�ʵ");
				}else{
					bean.setQxhs("δ��ʵ");
				}
				if("1".equals(shish)){
					bean.setShish("ͨ��");
				}else if("2".equals(state)){
					bean.setShish("�˵�");
				}else{
					bean.setShish("δ���");
				}
				if("1".equals(state)){
					bean.setState("�ᵥ");
				}else if("2".equals(state)){
					bean.setState("�˵�");
				}else{
					bean.setState("δ���");
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
