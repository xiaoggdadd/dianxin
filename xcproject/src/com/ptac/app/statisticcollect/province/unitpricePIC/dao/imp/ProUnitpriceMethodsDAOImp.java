package com.ptac.app.statisticcollect.province.unitpricePIC.dao.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProSelectBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.ProUnitpriceBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.bean.UnitpriceInfoBean;
import com.ptac.app.statisticcollect.province.unitpricePIC.dao.ProUnitpriceMethodsDAO;

public class ProUnitpriceMethodsDAOImp implements ProUnitpriceMethodsDAO {

	@Override
	public List<ProUnitpriceBean> findUnitprices(String loginID,
			ProSelectBean bean) {
		List<ProUnitpriceBean> ls = null;

		String where = getWhere(bean);

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.shi,  t.zdshi, to_char(t.accountmonth,'yyyy-mm') accountmonth,");
		sql
				.append("(CASE WHEN sum(t.dfid) <> 0 THEN round(sum(t.unitprice) / count(t.dfid), 4) END) as unitprice ");
		sql
				.append(" FROM (SELECT distinct RNDIQU(z.shi) as shi,f.accountmonth as accountmonth, z.shi as zdshi, f.unitprice as unitprice, f.electricfee_id as dfid ");
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
				.append(" order by zdshi, f.accountmonth) t group by t.shi, t.accountmonth,t.zdshi order by t.zdshi, t.accountmonth ");

		try {
			
			ls = new ArrayList<ProUnitpriceBean>();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, bean.getBzyf1());
			ps.setString(2, bean.getBzyf2());
			// ps.setString(3, where);
			ps.setString(3, loginID);

			System.out.println("查询语句执行:"+sql);
			
			rs = ps.executeQuery();

			String city = "";
			List<UnitpriceInfoBean> uils = new ArrayList<UnitpriceInfoBean>();
			ProUnitpriceBean pb = new ProUnitpriceBean();
			while (rs.next()) {
				String shi = rs.getString("shi");
				String bzyf = rs.getString("accountmonth");
				String unitprice = rs.getString("unitprice");
				if(unitprice!=null&&!"".equals(unitprice)){
					BigDecimal bd = new BigDecimal(unitprice);
					unitprice = bd.toString();
				}
				
				if (city.equals(shi)) {
					UnitpriceInfoBean ub = new UnitpriceInfoBean(bzyf,
							unitprice);
					uils.add(ub);
					
				} else {
					if (!"".equals(city)) {
						pb = new ProUnitpriceBean(city, uils);
						ls.add(pb);
					}
					city = shi;
					uils = new ArrayList<UnitpriceInfoBean>();
					UnitpriceInfoBean ub = new UnitpriceInfoBean(bzyf,
							unitprice);
					uils.add(ub);
				}
			}
			pb = new ProUnitpriceBean(city,uils);
			ls.add(pb);
			/**
			 * 以上的方法是为了让ls存入《pb》类。
			 * 首先，开始循环的时候，如果循环得到的shi和上一次循环得到的shi一样（第一次除外），那么ls不存储，而是存储ls里面的报账月份单价集合
			 * 如果和上次的城市不一样，则存储pb。
			 * 最后，因为少存储一次，所以在while之外再存一次pb
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}

		return ls;
	}

	String getWhere(ProSelectBean bean) {
		StringBuffer where = new StringBuffer();
		if (bean.getShi() != null && !"".equals(bean.getShi())) {
			where.append(" AND z.shi = '" + bean.getShi() + "' ");
		}
		if ("1".equals(bean.getDfshzt())) {// 市级审核通过
			where.append(" AND F.CITYAUDIT='" + bean.getDfshzt() + "' ");
		}
		if ("2".equals(bean.getDfshzt())) {// 人工审核通过
			where.append(" AND F.MANUALAUDITSTATUS = '" + bean.getDfshzt()
					+ "' ");
		}
		if ("3".equals(bean.getDfshzt())) {// 财务审核通过
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
