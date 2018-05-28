package com.ptac.app.checksign.citymanagercheck.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author lijing
 * @see 市级主任审核
 */
public class CityMngCheckDaoImpl implements CityMngCheckDao {

	/**
	 * @author lijing
	 * @see 市级主任审核查询信息
	 * @param whereStr
	 *            (String) 过滤条件不
	 * @param loginId
	 *            (String) 获取当前登录人的ID
	 * @return list 查询出来的结果集list
	 * update zhouxue 2014-05-16 查询sql语句加默认过滤条件 站点非虚拟，非采集，站点人工审核通过
	 * update zhouxue 2014-06-10  查询电费单sql语句去掉默认过滤条件  ：电费支付类型为预支、月结
	 */
	public List<CityMngCheckBean> queryCityMngCheck(String whereStr,
			String loginId,String lrbz1,String lrbz2) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CityMngCheckBean> list = new ArrayList<CityMngCheckBean>();

		StringBuffer sql = new StringBuffer();// 市级审核查询 （月结，预付费2）
		StringBuffer sql1 = new StringBuffer();// 市级审核查询（合同、插卡、预付费1）
		sql
				.append("SELECT Z.ID,Z.JZNAME,to_char(E.ACCOUNTMONTH,'yyyy-mm'),A.BLHDL,E.ACTUALPAY,E.UNITPRICE,E.QSDBDL,E.EDHDL,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "RTNAME(Z.PROPERTY),D.DBID,D.DBNAME,A.LASTDEGREE,A.THISDEGREE,to_char(A.LASTDATETIME,'yyyy-mm-dd'),to_char(A.THISDATETIME,'yyyy-mm-dd'),"
						+ "A.FLOATDEGREE,A.MEMO,E.FLOATPAY,E.MEMO,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "E.ELECTRICFEE_ID,E.DFUUID,E.CITYZRAUDITSTATUS,RTNAME(D.DFZFLX),A.DEDHDL,A.CSDB,'1' AS DFBZW,"
						+ " rndiqu(z.xian),rtname(z.gdfs),E.JSZQ,E.MANUALAUDITSTATUS,E.CITYAUDIT,D.BEILV,RTNAME(E.NOTETYPEID),A.ACTUALDEGREE,E.AUTOAUDITSTATUS,E.MANPASSMEMO,"
						+ "A.LASTELECFEES,A.LASTELECDEGREE,A.LASTUNITPRICE,E.CHANGEVALUE,E.ACTUALLINELOSSVALUE,A.BLHDL/E.JSZQ BZRJ"
						+ ",A.LASTFLOATDEGREE,A.LASTACTUALDEGREE"
						+ " FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E"
						+ " WHERE Z.ID=D.ZDID AND D.DBID=A.AMMETERID_FK AND A.AMMETERDEGREEID=E.AMMETERDEGREEID_FK "
						+ " AND (E.MANUALAUDITSTATUS = '-1' OR E.MANUALAUDITSTATUS = '1') "
						+ " AND E.CITYAUDIT = '1' "
						+ " AND E.LIUCHENGHAO IS NULL "
						+ " AND A.CITYFLAG = '0' AND Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' "
						+ whereStr+lrbz1
						+ " AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		System.out.println("市级主任审核查询月结,预付费2sql:"+sql.toString());
		sql1
				.append("SELECT Z.ID,Z.JZNAME,to_char(E.ACCOUNTMONTH,'yyyy-mm'),E.DIANLIANG,E.MONEY,E.DANJIA,E.QSDBDL,E.EDHDL,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "RTNAME(Z.PROPERTY),D.DBID,D.DBNAME,E.CITYZRAUDITSTATUS,RTNAME(D.DFZFLX),E.ID,E.UUID,E.DEDHDL,E.CSDB,'2' as dfbzw,"
						+ " rndiqu(z.xian),rtname(z.gdfs),E.JSZQ,E.MANUALAUDITSTATUS,E.CITYAUDIT,D.BEILV,RTNAME(E.NOTETYPEID),to_char(E.STARTDATE,'yyyy-mm-dd'),"
						+ " to_char(E.ENDDATE,'yyyy-mm-dd'),E.AUTOAUDITSTATUS,E.MANPASSMEMO,E.LASTYUE,E.DIANLIANG/E.JSZQ BZRJ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,yufufeiview E"
						+ " WHERE Z.ID = D.ZDID AND E.PREPAYMENT_AMMETERID = D.DBID"
						+ " AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx03' OR D.DFZFLX='dfzflx04')"
						+ " AND (E.FINANCEAUDIT = '-1' OR E.FINANCEAUDIT = '1') "
						+ " AND E.CITYAUDIT = '1' "
						+ " AND E.LIUCHENGHAO IS NULL "
						+ " AND E.CITYFLAG = '0' AND Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' "
						+ whereStr+lrbz2
						+ " AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		System.out.println("市级主任审核查询合同、插卡、预付费1sql:"+sql1.toString());
	
		DataBase db = new DataBase();
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps1 = conn.prepareStatement(sql1.toString());
			rs = ps.executeQuery();
			rs1 = ps1.executeQuery();

			while (rs.next()) {
				CityMngCheckBean bean = new CityMngCheckBean();
				String zdid = rs.getString(1) != null ? rs.getString(1) : "";// 站点ID
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";// 站点名称
				String accountmonth = rs.getString(3) != null ? rs.getString(3)
						: "";// 报账月份
				String blhdl = rs.getString(4) != null ? rs.getString(4) : "";// 实际用电量double
				String actualpay = rs.getString(5) != null ? rs.getString(5)
						: "";// 实际电费
				String unitprice = rs.getString(6) != null ? rs.getString(6)
						: "0.0000";// 单价
				String qsdbdl = rs.getString(7) != null ? rs.getString(7) : "";// 省定标
				String edhdl = rs.getString(8) != null ? rs.getString(8) : "";// 市定标
				String stationtype = rs.getString(9) != null ? rs.getString(9)
						: "";// 站点类型
				String property = rs.getString(10) != null ? rs.getString(10)
						: "";// 站点属性
				String dbid = rs.getString(11) != null ? rs.getString(11) : "";// 电表id
				String dbname = rs.getString(12) != null ? rs.getString(12)
						: "";// 电表名称
				String lastdegree = rs.getString(13) != null ? rs.getString(13)
						: "";// 起始电表数
				String thisdegree = rs.getString(14) != null ? rs.getString(14)
						: "";// 结束电表数
				String lastdatetime = rs.getString(15) != null ? rs
						.getString(15) : "";// 上次抄表时间
				String thisdatetime = rs.getString(16) != null ? rs
						.getString(16) : "";// 本次抄表时间
				String floatdegree = rs.getString(17) != null ? rs
						.getString(17) : "";// 电量调整
				String memo = rs.getString(18) != null ? rs.getString(18) : "";// 电量调整备注
				String floatpay = rs.getString(19) != null ? rs.getString(19)
						: "";// 电费调整
				String memo1 = rs.getString(20) != null ? rs.getString(20) : "";// 电费调整备注
				String szdq = rs.getString(21) != null ? rs.getString(21) : "";// 所在地区
				String electricfee_id = rs.getString(22) != null ? rs
						.getString(22) : "";// 电费id
				String uuid = rs.getString(23) != null ? rs.getString(23) : "";// uuid
				String cityzrauditstatus = rs.getString(24) != null ? rs
						.getString(24) : "";// 市级主任审核状态
				String dfzflx = rs.getString(25) != null ? rs.getString(25)
						: "";// 电费支付类型
				String dedhdl = rs.getString(26) != null ? rs.getString(26)
						: "";// 超市定标
				String csdb = rs.getString(27) != null ? rs.getString(27) : "";// 超省定标
				String dfbzw = rs.getString(28) != null ? rs.getString(28) : "";// 电费标志位

				String quxian = rs.getString(29) != null ? rs.getString(29)
						: "";// 区县
				String gdfs = rs.getString(30) != null ? rs.getString(30) : "";// 供电方式
				String jszq = rs.getString(31) != null ? rs.getString(31) : "";// 结算周期
				String rgshzt = rs.getString(32) != null ? rs.getString(32)
						: "";// 人工审核状态
				String sjshzt = rs.getString(33) != null ? rs.getString(33)
						: "";// 市级审核状态
				String beilv = rs.getString(34) != null ? rs.getString(34) : "1";// 倍率
				String pjlx = rs.getString(35) != null ? rs.getString(35) : "";// 票据类型
				String actualdegree = rs.getString(36) != null ? rs.getString(36) : "";// 交费时间
				String autoauditstatus = rs.getString(37) != null ? rs.getString(37) : "";//自动审核状态(0未通过  1通过）
				String manpassmemo = rs.getString(38) != null ? rs.getString(38) : "";//人工审核通过原因
				
				bean.setLastelecfees(rs.getString(39) != null ? rs.getString(39) : "");//上次电费
				bean.setLastelecdegree(rs.getString(40) != null ? rs.getString(40) : "");//上次电量
				bean.setLastunitprice(rs.getString(41) != null ? rs.getString(41) : "");//上次单价
				
				
				String dbydl = "";// 电表用电量
				String csdbdfe = "";// 超省标电费额
				String csdbdfbz = "";// 超省标电费比值

				/* 新字段的一些计算 */
				double zq2 = 0;// 周期
				try {
					zq2 = Double.parseDouble(jszq);
				} catch (Exception e) {

				}

				// 计算电表用电量
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree));
				Double dbdl = 0.0;
				try {

					dbdl = actdegree * Double.parseDouble(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				} catch (Exception e) {

				}
				dbydl = dbdl.toString();// 电表用电量

				if(!"".equals(qsdbdl) && !"".equals(zq2)){
				
					/* 计算超省定标电费额 */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(qsdbdl)
										* Double.parseDouble(unitprice) * zq2);
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
					}
					csdbdfe = sdbdf.toString();// 超省定标电费额
	
					/* 计算超省定标电费比值 */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl)
										* Double.parseDouble(unitprice) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
						csdbbz = 0.0;
					}
					csdbdfbz = csdbbz.toString();// 超省定标电费比值
				}
				//电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(beilv)==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = beilv;
				}
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0"; //上期电表用电量
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0"; //上期电量调整
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				bean.setLastfloatdegreeandbl(lastfloatdegreeandbl);//上期电量调整*倍率
				bean.setLastactualdegree(lastactualdegree);//上期电表用电量
				bean.setFloatdegreeandbl(floatdegreeandbl);//电量调整*倍率
				
				bean.setZdid(zdid);
				bean.setZdname(zdname);
				bean.setAccountmonth(accountmonth);
				bean.setBlhdl(Format.str2(blhdl));
				bean.setActualpay(Format.str2(actualpay));
				bean.setUnitprice(unitprice);
				bean.setCsbl(Format.str2(csdb));
				bean.setCsbbl(Format.str2(dedhdl));
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(edhdl);
				bean.setStationtype(stationtype);
				bean.setProperty(property);
				bean.setDbid(dbid);
				bean.setDbname(dbname);
				bean.setLastdegree(lastdegree);
				bean.setThisdegree(thisdegree);
				bean.setLastdatetime(lastdatetime);
				bean.setThisdatetime(thisdatetime);
				bean.setFloatdegree(floatdegree);
				bean.setMemo(memo);
				bean.setFloatpay(floatpay);
				bean.setMemo1(memo1);
				bean.setSzdq(szdq);
				bean.setElectricfeeid(electricfee_id);
				bean.setUuid(uuid);
				bean.setCityzrauditstatus(cityzrauditstatus);
				bean.setDfzflx(dfzflx);
				bean.setDfbzw(dfbzw);

				/* 储存新字段 */

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjlx(pjlx);
				bean.setDbydl(dbydl);
				bean.setCsdbdfe(csdbdfe);
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLrzt("电费单录入");
				
				if("0".equals(autoauditstatus)){
					bean.setManpassmemo(manpassmemo);
				}else{
					bean.setManpassmemo("");
				}

				if("0".equals(cityzrauditstatus)){
					bean.setGdtx("待审核");
				}else if("1".equals(cityzrauditstatus)){
					if("-1".equals(rgshzt)){
						bean.setGdtx("上级退单");
					}else if("2".equals(rgshzt) || "0".equals(rgshzt) || "1".equals(rgshzt) || "-2".equals(rgshzt)){
						bean.setGdtx("");
					}
				}
				
				bean.setLastyue("");
				/*String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +bean.getZdid()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("人工审核电费管理日均电量查询"+sqla.toString());
				ResultSet rsa = staa.executeQuery(sqla);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//管理表日均电量
				}
				dba.free(rsa, staa, conna);*/
				bean.setGlbrjl(Format.str2(""));
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				bean.setLineandchangeandbl(changeandlineandbl);//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				
				list.add(bean);
			}

			while (rs1.next()) {

				String zdid1 = rs1.getString(1) != null ? rs1.getString(1) : "";// 站点ID
				String zdname1 = rs1.getString(2) != null ? rs1.getString(2)
						: "";// 站点名称
				String accountmonth1 = rs1.getString(3) != null ? rs1
						.getString(3) : "";// 报账月份
				String blhdl1 = rs1.getString(4) != null ? rs1.getString(4)
						: "";// 实际用电量double
				String actualpay1 = rs1.getString(5) != null ? rs1.getString(5)
						: "";// 实际电费
				String unitprice1 = rs1.getString(6) != null ? rs1.getString(6)
						: "0.0000";// 单价
				String qsdbdl1 = rs1.getString(7) != null ? rs1.getString(7)
						: "";// 省定标
				String edhdl1 = rs1.getString(8) != null ? rs1.getString(8)
						: "";// 市定标
				String stationtype1 = rs1.getString(9) != null ? rs1
						.getString(9) : "";// 站点类型
				String property1 = rs1.getString(10) != null ? rs1
						.getString(10) : "";// 站点属性
				String dbid1 = rs1.getString(11) != null ? rs1.getString(11)
						: "";// 电表id
				String dbname1 = rs1.getString(12) != null ? rs1.getString(12)
						: "";// 电表名称
				String cityzrauditstatus1 = rs1.getString(13) != null ? rs1
						.getString(13) : "";// 市级主任审核状态
				String dfzflx1 = rs1.getString(14) != null ? rs1.getString(14)
						: "";// 电费支付类型
				String id1 = rs1.getString(15) != null ? rs1.getString(15) : "";
				String uuid1 = rs1.getString(16) != null ? rs1.getString(16)
						: "";
				String dedhdl1 = rs1.getString(17) != null ? rs1.getString(17)
						: "";// 超市定标
				String csdb1 = rs1.getString(18) != null ? rs1.getString(18)
						: "";// 超省定标
				String dfbzw1 = rs1.getString(19) != null ? rs1.getString(19)
						: "";// 电费标志位

				String quxian = rs1.getString(20) != null ? rs1.getString(20)
						: "";// 区县
				String gdfs = rs1.getString(21) != null ? rs1.getString(21) : "";// 供电方式
				String jszq = rs1.getString(22) != null ? rs1.getString(22) : "";// 结算周期
				String rgshzt = rs1.getString(23) != null ? rs1.getString(23)
						: "";// 人工审核状态
				String sjshzt = rs1.getString(24) != null ? rs1.getString(24)
						: "";// 市级审核状态
				String beilv = rs1.getString(25) != null ? rs1.getString(25) : "";// 倍率
				String pjlx = rs1.getString(26) != null ? rs1.getString(26) : "";// 票据类型
				String startTime = rs1.getString(27) != null ? rs1.getString(27) : "";// 起始时间
				String endTime = rs1.getString(28) != null ? rs1.getString(28) : "";// 结束时间
				
				String autoauditstatus1 = rs1.getString(29) != null ? rs1.getString(29) : "";//自动审核状态(0未通过  1通过）
				String manpassmemo1 = rs1.getString(30) != null ? rs1.getString(30) : "";//人工审核通过原因
				
				String lastyue = rs1.getString(31) != null ? rs1.getString(31) : "";//上期余额

				String dbydl = "";// 电表用电量
				String csdbdfe = "";// 超省标电费额
				String csdbdfbz = "";// 超省标电费比值

				/* 新字段的一些计算 */
				double zq2 = 0;// 周期
				try {
					zq2 = Double.parseDouble(jszq);
				} catch (Exception e) {

				}

				// 计算电表用电量
				Double dbdl = 0.0;
				try {

					dbdl = Format.str_d(blhdl1) * Double.parseDouble(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				} catch (Exception e) {

				}
				dbydl = dbdl.toString();// 电表用电量
				
				if(!"".equals(qsdbdl1) && !"".equals(zq2)){
	
					/* 计算超省定标电费额 */
					Double sdbdf = 0.0;
					
					try {
						sdbdf = Double.parseDouble(actualpay1)
								- (Double.parseDouble(qsdbdl1)
										* Double.parseDouble(unitprice1) * zq2);
						
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					csdbdfe = sdbdf.toString();// 超省定标电费额
					
	
					/* 计算超省定标电费比值 */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl1)
										* Double.parseDouble(unitprice1) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
						csdbbz = 0.0;
					}
					csdbdfbz = csdbbz.toString();// 超省定标电费比值
				}
				
				CityMngCheckBean bean = new CityMngCheckBean();
				bean.setZdid(zdid1);
				bean.setZdname(zdname1);
				bean.setAccountmonth(accountmonth1);
				bean.setBlhdl(Format.str2(blhdl1));
				bean.setActualpay(Format.str2(actualpay1));
				bean.setUnitprice(unitprice1);
				bean.setQsdbdl(qsdbdl1);
				bean.setEdhdl(edhdl1);
				bean.setStationtype(stationtype1);
				bean.setProperty(property1);
				bean.setDbid(dbid1);
				bean.setDbname(dbname1);
				bean.setCityzrauditstatus(cityzrauditstatus1);
				bean.setDfzflx(dfzflx1);
				bean.setElectricfeeid(id1);
				bean.setUuid(uuid1);
				bean.setCsbbl(Format.str2(dedhdl1));
				bean.setCsbl(Format.str2(csdb1));
				bean.setDfbzw(dfbzw1);
				
				bean.setFloatdegree("");
				bean.setMemo("");
				bean.setFloatpay("");
				bean.setMemo1("");

				/* 储存新字段 */

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjlx(pjlx);
				bean.setDbydl(dbydl);
				bean.setCsdbdfe(csdbdfe);
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLastdatetime(startTime);
				bean.setThisdatetime(endTime);
				bean.setLrzt("预付费录入");
				if("0".equals(autoauditstatus1)){
					bean.setManpassmemo(manpassmemo1);
				}else{
					bean.setManpassmemo("");
				}
				
				bean.setLastelecfees("");//上次电费
				bean.setLastelecdegree("");//上次电量
				bean.setLastunitprice("");//上次单价

				if("0".equals(cityzrauditstatus1)){
					bean.setGdtx("待审核");
				}else if("1".equals(cityzrauditstatus1)){
					if("-1".equals(rgshzt)){
						bean.setGdtx("上级退单");
					}else if("2".equals(rgshzt) || "0".equals(rgshzt) || "1".equals(rgshzt) || "-2".equals(rgshzt)){
						bean.setGdtx("");
					}
				}
				
				bean.setLastyue(lastyue);
				
				bean.setLastfloatdegreeandbl("");//上期电量调整*倍率
				bean.setLastactualdegree("");//上期电表用电量
				bean.setFloatdegreeandbl("");//电量调整*倍率
				bean.setLineandchangeandbl("");//线变损电量
				String bzrj = rs1.getString("BZRJ")!=null?rs1.getString("BZRJ"):"0"; //报账日均电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				bean.setGlbrjl("");
				list.add(bean);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, ps, null);
			db.free(rs1, ps1, conn);
		}

		return list;
	}

	/**
	 * @author lijing
	 * @see 市级主任审核详细信息
	 * @param dbid
	 *            (String) 获取当前的电表ID
	 * @param dfzflx
	 *            (String) 根据电费支付类型判断是月结,预付费2还是合同,插卡,预付费1
	 * @param dfbzw
	 *            (String) 根据电费标志位判断是月结,预付费2还是合同,插卡,预付费1
	 * @param accountmonth
	 *            （String） 过滤条件报账月份小于等于现在的报账月份
	 * @return list 详细信息list
	 */
	public List<CityMngCheckBean> getCityMngCheckInfo(String dbid,
			String dfzflx, String dfbzw, String accountmonth) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CityMngCheckBean> list = new ArrayList<CityMngCheckBean>();

		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql
				.append("SELECT Z.ID,Z.JZNAME,D.DBNAME,E.ACCOUNTMONTH,A.BLHDL,E.ACTUALPAY,"
						+ "A.LASTDEGREE,A.THISDEGREE,A.LASTDATETIME,A.THISDATETIME,"
						+ "A.FLOATDEGREE,A.MEMO,E.FLOATPAY,E.MEMO,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E"
						+ " WHERE Z.ID=D.ZDID AND D.DBID=A.AMMETERID_FK AND A.AMMETERDEGREEID=E.AMMETERDEGREEID_FK "
						+ " AND D.DBID = '"
						+ dbid
						+ "' AND E.ACCOUNTMONTH<='"
						+ accountmonth + "'order by ACCOUNTMONTH DESC");

		sql1
				.append("SELECT Z.ID,Z.JZNAME,D.DBNAME,E.ACCOUNTMONTH,E.DIANLIANG,E.MONEY,"
						+ "E.STARTDEGREE,E.STOPDEGREE,E.STARTDATE,E.ENDDATE,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,yufufeiview E"
						+ " WHERE Z.ID = D.ZDID AND E.PREPAYMENT_AMMETERID = D.DBID AND Z.QYZT = '1' "
						+ " AND D.DBID = '"
						+ dbid
						+ "' AND E.ACCOUNTMONTH<='"
						+ accountmonth + "'order by ACCOUNTMONTH DESC");

		DataBase db = new DataBase();
		if (("月结".equals(dfzflx) || "预支".equals(dfzflx)) && dfbzw.equals("1")) {
			System.out.println("市级主任审核详细查询月结,预付费2:");
			try {
				db.connectDb();
				conn = db.getConnection();
				ps = conn.prepareStatement(sql.toString());
				rs = ps.executeQuery();

				while (rs.next()) {

					String zdid = rs.getString(1) != null ? rs.getString(1)
							: "";// 站点ID
					String zdname = rs.getString(2) != null ? rs.getString(2)
							: "";// 站点名称
					String dbname = rs.getString(3) != null ? rs.getString(3)
							: "";// 电表名称
					String accountmonth1 = rs.getString(4) != null ? rs
							.getString(4) : "";// 报账月份
					String blhdl = rs.getString(5) != null ? rs.getString(5)
							: "";// 实际用电量double
					String actualpay = rs.getString(6) != null ? rs
							.getString(6) : "";// 实际电费
					String lastdegree = rs.getString(7) != null ? rs
							.getString(7) : "";// 起始电表数
					String thisdegree = rs.getString(8) != null ? rs
							.getString(8) : "";// 结束电表数
					String lastdatetime = rs.getString(9) != null ? rs
							.getString(9) : "";// 上次抄表时间
					String thisdatetime = rs.getString(10) != null ? rs
							.getString(10) : "";// 本次抄表时间
					String floatdegree = rs.getString(11) != null ? rs
							.getString(11) : "";// 电量调整
					String memo = rs.getString(12) != null ? rs.getString(12)
							: "";// 电量调整备注
					String floatpay = rs.getString(13) != null ? rs
							.getString(13) : "";// 电费调整
					String memo1 = rs.getString(14) != null ? rs.getString(14)
							: "";// 电费调整备注
					String szdq = rs.getString(15) != null ? rs.getString(15)
							: "";// 所在地区

					CityMngCheckBean bean = new CityMngCheckBean();
					bean.setZdid(zdid);
					bean.setZdname(zdname);
					bean.setDbname(dbname);
					bean.setAccountmonth(accountmonth1);
					bean.setBlhdl(blhdl);
					bean.setActualpay(Format.str2(actualpay));
					bean.setLastdegree(lastdegree);
					bean.setThisdegree(thisdegree);
					bean.setLastdatetime(lastdatetime);
					bean.setThisdatetime(thisdatetime);
					bean.setFloatdegree(floatdegree);
					bean.setMemo(memo);
					bean.setFloatpay(floatpay);
					bean.setMemo1(memo1);
					bean.setSzdq(szdq);

					list.add(bean);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs, ps, conn);
			}
		}

		if (("合同".equals(dfzflx) || "插卡".equals(dfzflx) || "预支".equals(dfzflx))
				&& dfbzw.equals("2")) {
			System.out.println("市级主任审核详细查询合同,插卡,预付费1:");
			try {
				db.connectDb();
				conn = db.getConnection();
				ps1 = conn.prepareStatement(sql1.toString());
				rs1 = ps1.executeQuery();

				while (rs.next()) {

					CityMngCheckBean bean = new CityMngCheckBean();
					bean.setZdid(rs1.getString(1));
					bean.setZdname(rs1.getString(2));
					bean.setDbname(rs1.getString(3));
					bean.setAccountmonth(rs1.getString(4));
					bean.setBlhdl(rs1.getString(5));
					bean.setActualpay(Format.str2(rs1.getString(6)));
					bean.setLastdegree(rs1.getString(7));
					bean.setThisdegree(rs1.getString(8));
					bean.setLastdatetime(rs1.getString(9));
					bean.setThisdatetime(rs1.getString(10));
					bean.setFloatdegree("");
					bean.setMemo("");
					bean.setFloatpay("");
					bean.setMemo1("");
					bean.setSzdq(rs1.getString(15));

					list.add(bean);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs1, ps1, conn);
			}
		}

		return list;
	}

	/**
	 * @author lijing
	 * @param personnal
	 *            (String) 审核人
	 * @param cityzrauditstatus
	 *            (String) 审核标志
	 * @param chooseIdStr1
	 *            (String) 电费uuid
	 * @param chooseIdStr2
	 *            （String） 预付费uuid
	 * @param bz
	 *            (String) msg标志
	 * @see 市级电费审核
	 * @param accountId
	 *            (String) 账号id
	 * @return String 审核信息msg
	 */
	public String CheckCityFees(String personnal, String cityzrauditstatus,
			String chooseIdStr1, String chooseIdStr2, String bz) {
		String msg = "市级主任审核电费信息失败！";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// 系统时间
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		StringBuffer sql1 = new StringBuffer();// 月结，预付费2
		StringBuffer sql2 = new StringBuffer();// 合同插卡预付费1
		try {
			conn = db.getConnection();

			sql1.append(" UPDATE ELECTRICFEES SET CITYZRAUDITSTATUS='"
					+ cityzrauditstatus + "'," + "CITYZRAUDITTIME=" + time
					+ "," + "CITYZRAUDITNAME='" + personnal + "'"
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYZRAUDITSTATUS='"
					+ cityzrauditstatus + "'," + "CITYZRAUDITTIME=" + time
					+ "," + "CITYZRAUDITNAME='" + personnal + "'"
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {
				ps1 = conn.prepareStatement(sql1.toString());
				System.out.println("市级主任审核电费单:"+sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) {
				ps2 = conn.prepareStatement(sql2.toString());
				System.out.println("市级主任审核预付费:"+sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "市级主任审核信息通过！";
			} else if (bz.equals("0")) {
				msg = "市级主任审核信息取消通过！";
			} else {
				msg = "市级主任审核信息不通过！";
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}

}
