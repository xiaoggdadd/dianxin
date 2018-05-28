package com.noki.fybzgl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.fybzgl.bean.fybzglBean;

/**
 * @author GT 费用保障管理
 * */
public class fybzglDao {
	// 得到总的费用信息
	public synchronized ArrayList<fybzglBean> getAall(String whereStr,
			String str,String feiyongtype,String zflx) {
		String yj = "";
		String yj1 ="";
		String yz = "";
		String yz1 = "";
		if (null != feiyongtype && feiyongtype != "" && feiyongtype != "0") {
			if("-1".equals(feiyongtype)){
				yj = yj + " and  e.actualpay<0 ";
				yj1 = yj1 + " and  ee.actualpay<0 ";
				yz = yz + " and  e.money<0 ";
				yz1 = yz1 + " and  ee.money<0 ";
			}else{
				if("1".equals(feiyongtype)){
					yj = yj + " and  e.actualpay>0 ";
					yj1 = yj1 + " and  ee.actualpay>0 ";
					yz = yz + " and  e.money>0 ";
					yz1 = yz1 + " and  ee.money>0 ";
				}
			}
			
		}
		String sql = "SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zz.shi)shi, "
				+ "COUNT(ee.electricfee_id)countid, SUM(ee.actualpay/10000)monery, SUM(CASE WHEN ee.manualauditstatus='2' THEN ee.actualpay/10000 END)AS cwmoney, SUM( ee.NETWORKDF/10000 )AS wlyy, SUM( ee.MARKETDF/10000 )AS scjy, SUM( ee.ADMINISTRATIVEDF/10000 )AS xzzh, SUM( ee.INFORMATIZATIONDF/10000 )AS xxhzc, SUM( ee.BUILDDF/10000 )AS tzjs, SUM( ee.dddf/10000 )AS dddf, "
				+ "ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy"
				+ " FROM  (SELECT shi, SUM(CASE WHEN s.zymxcode='11' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydzy1, SUM(CASE WHEN s.zymxcode='12' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydzy2, SUM(CASE WHEN s.zymxcode='21' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS gwzy, SUM(CASE WHEN s.zymxcode='09' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS gygtzy, SUM(CASE WHEN s.zymxcode='19' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydgtzy, "
				+ "SUM(CASE WHEN s.zymxcode='00' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS bkftzy"
				+ " FROM ZHANDIAN Z, DIANBIAO D, SBGL S, dianduview A, dianfeiview E "
				+ " WHERE Z.ID = D.ZDID AND d.dbid=s.dianbiaoid AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.Qyzt = '1' AND D.Dbqyzt = '1'AND Z.Xuni = '0' AND E.manualauditstatus >= '1' "
				+ whereStr+yj
				+ "  GROUP  BY z.shi)ab,zhandian zz,dianbiao dd,dianduview aa,dianfeiview ee "
				+ " WHERE ab.shi=zz.shi AND zz.id=dd.zdid  AND dd.dbid=aa.ammeterid_fk  AND aa.ammeterdegreeid=ee.ammeterdegreeid_fk AND zz.qyzt = '1'  AND dd.dbqyzt = '1'AND zz.xuni = '0' AND ee.manualauditstatus >= '1'  "
				+ str+yj1
				+ " GROUP  BY zz.shi,ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy ORDER BY zz.shi";
		String sqlyz = "SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zz.shi)shi, COUNT(ee.prepayment_ammeterid)countid, "
				+ "SUM(ee.money/10000)monery,SUM(CASE WHEN ee.financeaudit='2' THEN ee.money/10000 END)AS cwmoney,"
				+ "SUM( ee.NETWORKDF/10000 )AS wlyy, SUM( ee.MARKETDF/10000 )AS scjy, SUM( ee.ADMINISTRATIVEDF/10000 )AS xzzh,"
				+ " SUM( ee.INFORMATIZATIONDF/10000 )AS xxhzc, SUM( ee.BUILDDF/10000 )AS tzjs, SUM(ee.dddf / 10000) AS dddf,"
				+ " ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy "
				+ " FROM  (SELECT shi, SUM(CASE WHEN s.zymxcode='11' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydzy1,"
				+ " SUM(CASE WHEN s.zymxcode='12' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydzy2, "
				+ "SUM(CASE WHEN s.zymxcode='21' THEN e.money*s.dbili*s.xjbili/100000000 END)AS gwzy,"
				+ " SUM(CASE WHEN s.zymxcode='09' THEN e.money*s.dbili*s.xjbili/100000000 END)AS gygtzy, "
				+ "SUM(CASE WHEN s.zymxcode='19' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydgtzy, "
				+ "SUM(CASE WHEN s.zymxcode='00' THEN e.money*s.dbili*s.xjbili/100000000 END)AS bkftzy "
				+ "FROM ZHANDIAN Z, DIANBIAO D, SBGL S,yufufeiview e WHERE Z.ID = D.ZDID AND "
				+ "d.dbid=s.dianbiaoid AND D.DBID = e.prepayment_ammeterid AND Z.Qyzt = '1' AND D.Dbqyzt = '1' AND Z.Xuni = '0' AND e.cityaudit = '1' "
				+ whereStr+yz
				+ " GROUP BY z.shi)ab,zhandian zz,dianbiao dd,yufufeiview ee  "
				+ "WHERE ab.shi=zz.shi AND zz.id=dd.zdid  AND dd.dbid=ee.prepayment_ammeterid  AND zz.qyzt = '1' AND dd.dbqyzt = '1' AND zz.xuni = '0' AND ee.cityaudit = '1' "
				+ str+yz1
				+ " GROUP  BY zz.shi,ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy ORDER BY zz.shi";
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rsyz = null;
		ArrayList<fybzglBean> list = new ArrayList<fybzglBean>();
		HashMap<String, fybzglBean> hlist = new HashMap<String, fybzglBean>();
		HashMap<String, fybzglBean> hlistyz = new HashMap<String, fybzglBean>();
		
		
		
		
		
		try {
			db.connectDb();
			System.out.println("     "+zflx);
			if("1".equals(zflx)){
				System.out.println("月结电费:" + sql);
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					fybzglBean fybzgl = new fybzglBean();
					fybzgl.setShi(rs.getString("shi"));
					fybzgl.setCount(rs.getString("countid"));
					fybzgl.setMonerycount(rs.getString("monery"));
					fybzgl.setCwmonerycount(rs.getString("cwmoney"));
					fybzgl.setYj01(rs.getString("wlyy"));
					fybzgl.setYj02(rs.getString("scjy"));
					fybzgl.setYj03(rs.getString("xzzh"));
					fybzgl.setYj04(rs.getString("xxhzc"));
					fybzgl.setYj05(rs.getString("tzjs"));
					fybzgl.setYj06(rs.getString("dddf"));

					fybzgl.setErj01(rs.getString("ydzy1"));
					fybzgl.setErj02(rs.getString("ydzy2"));
					fybzgl.setErj03(rs.getString("gwzy"));
					fybzgl.setErj04(rs.getString("gygtzy"));
					fybzgl.setErj05(rs.getString("ydgtzy"));
					fybzgl.setErj06(rs.getString("bkftzy"));
					hlist.put(rs.getString("shi"), fybzgl);
				}
				for (fybzglBean bean : hlist.values()) {
					list.add(bean);
				}
			}else{
				if("-1".equals(zflx)){
					System.out.println("预付费:" + sqlyz);
					rsyz = db.queryAll(sqlyz.toString());
					while (rsyz.next()) {
						fybzglBean fybzgl = new fybzglBean();
						fybzgl.setShi(rsyz.getString("shi"));
						fybzgl.setCount(rsyz.getString("countid"));
						fybzgl.setMonerycount(rsyz.getString("monery"));
						fybzgl.setCwmonerycount(rsyz.getString("cwmoney"));
						fybzgl.setYj01(rsyz.getString("wlyy"));
						fybzgl.setYj02(rsyz.getString("scjy"));
						fybzgl.setYj03(rsyz.getString("xzzh"));
						fybzgl.setYj04(rsyz.getString("xxhzc"));
						fybzgl.setYj05(rsyz.getString("tzjs"));
						fybzgl.setYj06(rsyz.getString("dddf"));

						fybzgl.setErj01(rsyz.getString("ydzy1"));
						fybzgl.setErj02(rsyz.getString("ydzy2"));
						fybzgl.setErj03(rsyz.getString("gwzy"));
						fybzgl.setErj04(rsyz.getString("gygtzy"));
						fybzgl.setErj05(rsyz.getString("ydgtzy"));
						fybzgl.setErj06(rsyz.getString("bkftzy"));
						hlist.put(rsyz.getString("shi"), fybzgl);
					}
					for (fybzglBean bean : hlist.values()) {
						list.add(bean);
				    }
					
				}else{
					
		//总共的
			System.out.println("预付费:" + sqlyz);
			System.out.println("月结电费:" + sql);
			rs = db.queryAll(sql.toString());
			rsyz = db.queryAll(sqlyz.toString());
			
			while (rs.next()) {
				fybzglBean fybzgl = new fybzglBean();
				fybzgl.setShi(rs.getString("shi"));
				fybzgl.setCount(rs.getString("countid"));
				fybzgl.setMonerycount(rs.getString("monery"));
				fybzgl.setCwmonerycount(rs.getString("cwmoney"));
				fybzgl.setYj01(rs.getString("wlyy"));
				fybzgl.setYj02(rs.getString("scjy"));
				fybzgl.setYj03(rs.getString("xzzh"));
				fybzgl.setYj04(rs.getString("xxhzc"));
				fybzgl.setYj05(rs.getString("tzjs"));
				fybzgl.setYj06(rs.getString("dddf"));

				fybzgl.setErj01(rs.getString("ydzy1"));
				fybzgl.setErj02(rs.getString("ydzy2"));
				fybzgl.setErj03(rs.getString("gwzy"));
				fybzgl.setErj04(rs.getString("gygtzy"));
				fybzgl.setErj05(rs.getString("ydgtzy"));
				fybzgl.setErj06(rs.getString("bkftzy"));
				hlist.put(rs.getString("shi"), fybzgl);
			}

			while (rsyz.next()) {

				String name = rsyz.getString("shi");

				if (hlist.containsKey(name)) {
					fybzglBean fybzgl = new fybzglBean();
					fybzgl.setShi(name);
					fybzgl
							.setCount(String
									.valueOf(rsyz.getDouble("countid")
											+ Double
													.valueOf(hlist.get(name)
															.getCount() != null ? hlist
															.get(name)
															.getCount()
															: "0")));
					fybzgl
							.setMonerycount(String.valueOf(rsyz
									.getDouble("monery")
									+ Double.valueOf(hlist.get(name)
											.getMonerycount() != null ? hlist
											.get(name).getMonerycount() : "0")));
					fybzgl
							.setCwmonerycount(String
									.valueOf(rsyz.getDouble("cwmoney")
											+ Double
													.valueOf(hlist.get(name)
															.getCwmonerycount() != null ? hlist
															.get(name)
															.getCwmonerycount()
															: "0")));
					fybzgl
							.setYj01(String
									.valueOf(rsyz.getDouble("wlyy")
											+ Double.valueOf(hlist.get(name)
													.getYj01() != null ? hlist
													.get(name).getYj01() : "0")));
					fybzgl
							.setYj02(String
									.valueOf(rsyz.getDouble("scjy")
											+ Double.valueOf(hlist.get(name)
													.getYj02() != null ? hlist
													.get(name).getYj02() : "0")));
					fybzgl
							.setYj03(String
									.valueOf(rsyz.getDouble("xzzh")
											+ Double.valueOf(hlist.get(name)
													.getYj03() != null ? hlist
													.get(name).getYj03() : "0")));
					fybzgl
							.setYj04(String
									.valueOf(rsyz.getDouble("xxhzc")
											+ Double.valueOf(hlist.get(name)
													.getYj04() != null ? hlist
													.get(name).getYj04() : "0")));
					fybzgl
							.setYj05(String
									.valueOf(rsyz.getDouble("tzjs")
											+ Double.valueOf(hlist.get(name)
													.getYj05() != null ? hlist
													.get(name).getYj05() : "0")));
					fybzgl
							.setYj06(String
									.valueOf(rsyz.getDouble("dddf")
											+ Double.valueOf(hlist.get(name)
													.getYj06() != null ? hlist
													.get(name).getYj06() : "0")));

					fybzgl
							.setErj01(String
									.valueOf(rsyz.getDouble("ydzy1")
											+ Double
													.valueOf(hlist.get(name)
															.getErj01() != null ? hlist
															.get(name)
															.getErj01()
															: "0")));
					fybzgl
							.setErj02(String
									.valueOf(rsyz.getDouble("ydzy2")
											+ Double
													.valueOf(hlist.get(name)
															.getErj02() != null ? hlist
															.get(name)
															.getErj02()
															: "0")));
					fybzgl
							.setErj03(String
									.valueOf(rsyz.getDouble("gwzy")
											+ Double
													.valueOf(hlist.get(name)
															.getErj03() != null ? hlist
															.get(name)
															.getErj03()
															: "0")));
					fybzgl
							.setErj04(String
									.valueOf(rsyz.getDouble("gygtzy")
											+ Double
													.valueOf(hlist.get(name)
															.getErj04() != null ? hlist
															.get(name)
															.getErj04()
															: "0")));
					fybzgl
							.setErj05(String
									.valueOf(rsyz.getDouble("ydgtzy")
											+ Double
													.valueOf(hlist.get(name)
															.getErj05() != null ? hlist
															.get(name)
															.getErj05()
															: "0")));
					fybzgl
							.setErj06(String
									.valueOf(rsyz.getDouble("bkftzy")
											+ Double
													.valueOf(hlist.get(name)
															.getErj06() != null ? hlist
															.get(name)
															.getErj06()
															: "0")));
					list.add(fybzgl);
					hlist.remove(name);
				} else {

				}
			}
			//////
			
			
			for (fybzglBean bean : hlist.values()) {
				list.add(bean);
			}
				}
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rsyz != null) {
				try {
					rsyz.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;
	}

	// 得到站点属性的分类
	public synchronized ArrayList<fybzglBean> getZdsxMonery(String whereStr,String feiyongtype,String zflx) {
		String yj = "";
		String yz = "";
		if (null != feiyongtype && feiyongtype != "" && feiyongtype != "0") {
			if("-1".equals(feiyongtype)){
				yj = yj + " and  e.actualpay<0 ";
				yz = yz + " and  e.money<0 ";
			}else{
				if("1".equals(feiyongtype)){
					yj = yj + " and  e.actualpay>0 ";
					yz = yz + " and  e.money>0 ";
				}
			}
			
		}
		String sqlyz = "SELECT  SUM(E.MONEY / 10000)ZDSXSUM,DECODE(Z.PROPERTY,'','其他',Z.PROPERTY)ZDSX FROM ZHANDIAN Z, DIANBIAO D, YUFUFEIVIEW E WHERE Z.ID = D.ZDID AND D.DBID = E.PREPAYMENT_AMMETERID"
				+ " AND Z.QYZT = '1' AND D.DBQYZT = '1'  AND Z.XUNI = '0' AND E.CITYAUDIT = '1' "
				+ whereStr +yz+ " GROUP BY Z.PROPERTY ORDER BY Z.PROPERTY" + "";
		String sql = "SELECT SUM(E.ACTUALPAY / 10000)ZDSXSUM,DECODE(Z.PROPERTY,'','其他',Z.PROPERTY)ZDSX FROM ZHANDIAN Z, DIANBIAO D,  DIANDUVIEW A, DIANFEIVIEW E"
				+ " WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND Z.QYZT = '1' AND D.DBQYZT = '1' AND Z.XUNI = '0'  AND E.MANUALAUDITSTATUS >= '1'"
				+ " " + whereStr +yj+ " GROUP BY Z.PROPERTY ORDER BY Z.PROPERTY";
		
		DataBase db = new DataBase();
		ResultSet rsyz = null;
		ResultSet rs = null;
		ArrayList<fybzglBean> list = new ArrayList<fybzglBean>();
		HashMap<String, fybzglBean> hlist = new HashMap<String, fybzglBean>();
		HashMap<String, fybzglBean> hlistyz = new HashMap<String, fybzglBean>();
		try {
			db.connectDb();
			
			System.out.println("     "+zflx);
			//月结
			if("1".equals(zflx)){
				System.out.println("站点属性：" + sql.toString());
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					fybzglBean fybzgl = new fybzglBean();
					fybzgl.setZdsx(rs.getString("ZDSX"));
					fybzgl.setZdsxmonery(rs.getString("ZDSXSUM"));
					hlist.put(rs.getString("ZDSX"), fybzgl);
				}
				for (fybzglBean bean : hlist.values()) {
					list.add(bean);
				}
				
			}else{
				//预支
				if("-1".equals(zflx)){
					System.out.println("站点属性yz：" + sqlyz.toString());
					rsyz = db.queryAll(sqlyz.toString());
					while (rsyz.next()) {
						fybzglBean fybzgl = new fybzglBean();
						fybzgl.setZdsx(rsyz.getString("ZDSX"));
						fybzgl.setZdsxmonery(rsyz.getString("ZDSXSUM"));
						hlist.put(rsyz.getString("ZDSX"), fybzgl);
					}
					for (fybzglBean bean : hlist.values()) {
						list.add(bean);
					}
				}else{
					
			
			System.out.println("站点属性yz：" + sqlyz.toString());
			System.out.println("站点属性：" + sql.toString());
			rsyz = db.queryAll(sqlyz.toString());
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				fybzglBean fybzgl = new fybzglBean();
				fybzgl.setZdsx(rs.getString("ZDSX"));
				fybzgl.setZdsxmonery(rs.getString("ZDSXSUM"));
				hlist.put(rs.getString("ZDSX"), fybzgl);
			}
			while (rsyz.next()) {
				String name = rsyz.getString("ZDSX");
				if (hlist.containsKey(name)) {
					fybzglBean fybzgl = new fybzglBean();
					fybzgl.setZdsx(name);
					fybzgl.setZdsxmonery(String
							.valueOf(rsyz.getDouble("ZDSXSUM")
									+ Double.valueOf(hlist.get(name)
											.getZdsxmonery() != null ? hlist
											.get(name).getZdsxmonery() : "0")));
					list.add(fybzgl);
					hlist.remove(name);
				}
			}
			for (fybzglBean bean : hlist.values()) {
				list.add(bean);
			}
				}
			}
			return list;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rsyz != null) {
				try {
					rsyz.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}

		return null;
	}

	// 得到供电方式的分类
	public synchronized ArrayList<fybzglBean> getGdfsMonery(String whereStr,String feiyongtype,String zflx) {
		String yj = "";
		String yz = "";
		if (null != feiyongtype && feiyongtype != "" && feiyongtype != "0") {
			if("-1".equals(feiyongtype)){
				yj = yj + " and  e.actualpay<0 ";
				yz = yz + " and  e.money<0 ";
			}else{
				if("1".equals(feiyongtype)){
					yj = yj + " and  e.actualpay>0 ";
					yz = yz + " and  e.money>0 ";
				}
			}
			
		}
		String sqlyz = "SELECT  SUM(E.MONEY / 10000)GDFSSUM,DECODE(Z.GDFS,'','gdfs04',Z.GDFS)GDFS FROM ZHANDIAN Z, DIANBIAO D, YUFUFEIVIEW E  WHERE Z.ID = D.ZDID "
				+ "  AND D.DBID = E.PREPAYMENT_AMMETERID AND Z.QYZT = '1' AND D.DBQYZT = '1' AND Z.XUNI = '0' AND E.CITYAUDIT = '1'"
				+ whereStr + yz+"  GROUP BY Z.GDFS" + "";
		String sql = "SELECT  SUM(E.ACTUALPAY / 10000)GDFSSUM,DECODE(Z.GDFS,'','gdfs04',Z.GDFS)GDFS FROM ZHANDIAN Z, DIANBIAO D,  DIANDUVIEW A, DIANFEIVIEW E"
				+ "  WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.QYZT = '1'"
				+ " AND D.DBQYZT = '1' AND Z.XUNI = '0' AND E.MANUALAUDITSTATUS >= '1' " + whereStr +yj+ "  GROUP BY Z.GDFS";
		
		DataBase db = new DataBase();
		ResultSet rsyz = null;
		ResultSet rs = null;
		ArrayList<fybzglBean> list = new ArrayList<fybzglBean>();
		HashMap<String, fybzglBean> hlist = new HashMap<String, fybzglBean>();
		try {
			db.connectDb();
			if("1".equals(zflx)){
				System.out.println("供电方式：" + sql.toString());
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					fybzglBean fybzgl = new fybzglBean();
					String name = rs.getString("GDFS");
					fybzgl.setGdfs(name);
					fybzgl.setGdfsmonery(rs.getString("GDFSSUM"));
					hlist.put(name, fybzgl);
				}
				for (fybzglBean bean : hlist.values()) {
					list.add(bean);
				}
			}else{
				if("-1".equals(zflx)){
					System.out.println("供电方式yz：" + sqlyz.toString());
					rsyz = db.queryAll(sqlyz.toString());
					while (rsyz.next()) {
						fybzglBean fybzgl = new fybzglBean();
						String name = rsyz.getString("GDFS");
						fybzgl.setGdfs(name);
						fybzgl.setGdfsmonery(rsyz.getString("GDFSSUM"));
						hlist.put(name, fybzgl);
					}
					for (fybzglBean bean : hlist.values()) {
						list.add(bean);
					}
					System.out.println("--------结束");
				}else{
					
			
			System.out.println("供电方式yz：" + sqlyz.toString());
			System.out.println("供电方式：" + sql.toString());
			rsyz = db.queryAll(sqlyz.toString());
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				fybzglBean fybzgl = new fybzglBean();
				String name = rs.getString("GDFS");
				fybzgl.setGdfs(name);
				fybzgl.setGdfsmonery(rs.getString("GDFSSUM"));
				hlist.put(name, fybzgl);
			}
			
			while (rsyz.next()) {
				String name = rsyz.getString("GDFS");
				if (hlist.containsKey(name)) {
					fybzglBean fybzgl = new fybzglBean();
					fybzgl.setGdfs(name);
					fybzgl.setGdfsmonery(String
							.valueOf(rsyz.getDouble("GDFSSUM")
									+ Double.valueOf(hlist.get(name)
											.getGdfsmonery() != null ? hlist
											.get(name).getGdfsmonery() : "0")));
					list.add(fybzgl);
					hlist.remove(name);
				}
			}
			for (fybzglBean bean : hlist.values()) {
				list.add(bean);
			}
				}
			}
			return list;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rsyz != null) {
				try {
					rsyz.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}

		return null;
	}

}
