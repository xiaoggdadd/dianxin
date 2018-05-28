package com.ptac.app.statisticcollect.city.unitpricePIC.dao.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.noki.database.DataBase;
import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.bean.CityUnitpriceSelectBean;
import com.ptac.app.statisticcollect.city.unitpricePIC.dao.CityUnitpriceMethodsDAO;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.UnitpriceInfoBean;

public class CityUnitpriceMethodsDAOImp implements CityUnitpriceMethodsDAO {

	@Override
	public List<CityUnitpriceBean> findUnitprices(String loginID,
			CityUnitpriceSelectBean bean) {
		List<CityUnitpriceBean> ls = null;

		String where = getWhere(bean);

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.shi,t.xian,  t.zdshi, to_char(t.accountmonth,'yyyy-mm') accountmonth,");
		sql
				.append("(CASE WHEN sum(t.dfid) <> 0 THEN round(sum(t.unitprice) / count(t.dfid), 4) END) as unitprice ");
		sql
				.append(" FROM (SELECT distinct RNDIQU(z.shi) as shi, RNDIQU(z.xian) as xian,f.accountmonth as accountmonth, z.xian as zdshi, f.unitprice as unitprice, f.electricfee_id as dfid ");
		sql
				.append(" FROM ZHANDIAN Z right outer join DIANBIAO D on Z.Id = d.zdid ");
		sql.append(" right outer join DIANDUVIEW V ON D.Dbid=v.ammeterid_fk ");
		sql
				.append(" right outer join DIANFEIVIEW F on v.ammeterdegreeid = f.ammeterdegreeid_fk ");

		sql.append(" WHERE (to_char(f.accountmonth,'yyyy-mm') >= ? AND to_char(f.accountmonth,'yyyy-mm') <= ? )  ");
		sql.append(where);
		sql
				.append("and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = ? ))) ");
		sql
				.append(" order by zdshi, f.accountmonth) t group by t.shi,t.xian, t.accountmonth,t.zdshi order by t.zdshi, t.accountmonth ");

		try {
			
			ls = new ArrayList<CityUnitpriceBean>();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getBzyf1());
			ps.setString(2, bean.getBzyf2());
			ps.setString(3, loginID);

			System.out.println("��ѯ���ִ��:"+sql);
			
			rs = ps.executeQuery();

			String Quxian = "";
			List<UnitpriceInfoBean> uils = new ArrayList<UnitpriceInfoBean>();
			CityUnitpriceBean pb = new CityUnitpriceBean();
			String shi = "";
			while (rs.next()) {
				shi = rs.getString("shi");
				String xian = rs.getString("xian");
				String bzyf = rs.getString("accountmonth");
				String unitprice = rs.getString("unitprice");
				if(unitprice!=null&&!"".equals(unitprice)){
					BigDecimal bd = new BigDecimal(unitprice);
					unitprice = bd.toString();
				}
				
				if (Quxian.equals(xian)) {
					UnitpriceInfoBean ub = new UnitpriceInfoBean(bzyf,
							unitprice);
					uils.add(ub);
					
				} else {
					if (!"".equals(Quxian)) {
						pb = new CityUnitpriceBean(shi,Quxian, uils);
						ls.add(pb);
					}
					Quxian = xian;
					uils = new ArrayList<UnitpriceInfoBean>();
					UnitpriceInfoBean ub = new UnitpriceInfoBean(bzyf,
							unitprice);
					uils.add(ub);
				}
			}
			pb = new CityUnitpriceBean(shi,Quxian,uils);
			ls.add(pb);
			/**
			 * ���ϵķ�����Ϊ����ls���롶pb���ࡣ
			 * ���ȣ���ʼѭ����ʱ�����ѭ���õ���xian����һ��ѭ���õ���xianһ������һ�γ��⣩����ôls���洢�����Ǵ洢ls����ı����·ݵ��ۼ���
			 * ������ϴεĳ��в�һ������洢pb��
			 * �����Ϊ�ٴ洢һ�Σ�������while֮���ٴ�һ��pb
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}

		return ls;
	}
	
	String getWhere(CityUnitpriceSelectBean bean){
		StringBuffer where = new StringBuffer();
		if (bean.getShi() != null && !"".equals(bean.getShi())) {
			where.append(" AND z.shi = '" + bean.getShi() + "' ");
		}
		if (bean.getXian() != null && !"".equals(bean.getXian())) {
			where.append(" AND z.xian = '" + bean.getXian() + "' ");
		}
		if ("1".equals(bean.getDfshzt())) {// �м����ͨ��
			where.append(" AND F.CITYAUDIT='" + bean.getDfshzt() + "' ");
		}
		if ("2".equals(bean.getDfshzt())) {// �˹����ͨ��
			where.append(" AND F.MANUALAUDITSTATUS = '" + bean.getDfshzt()
					+ "' ");
		}
		if ("3".equals(bean.getDfshzt())) {// �������ͨ��
			where.append(" AND F.MANUALAUDITSTATUS = '" + bean.getDfshzt()
					+ "' ");
		}
		if (!"".equals(bean.getZdqyzt()) && bean.getZdqyzt() != null) {
			where.append(" AND Z.QYZT='" + bean.getZdqyzt() + "' ");
		}
		if (!"".equals(bean.getDbqyzt()) && bean.getDbqyzt() != null) {
			where.append(" AND D.DBQYZT='" + bean.getDbqyzt() + "' ");
		}

		return where.toString();
	}

}
