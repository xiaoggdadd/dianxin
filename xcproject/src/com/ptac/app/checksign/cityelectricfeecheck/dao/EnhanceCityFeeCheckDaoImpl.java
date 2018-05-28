package com.ptac.app.checksign.cityelectricfeecheck.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author WangYiXiao 2014-1-23
 * @see 市级电费审核实现类 市级电费审核：查询，导出，电费电量求和，审核
 */
public class EnhanceCityFeeCheckDaoImpl implements EnhanceCityFeeCheckDao {

	/**
	 * @param whereStr
	 *            (String) 过滤条件
	 * @param loginId
	 *            (String) 权限
	 * @see 市级电费审核查询，导出
	 * @author WangYiXiao 2014-1-24
	 * @update WangYiXiao 2014-4-8  市级审核查询sql把流程号为空默认条件去掉
	 * @update zhouxue 2014-05-16 查询sql加默认条件 站点非虚拟，非采集，站点审核通过
	 * @update WangYiXiao 2014-8-11去掉tbtzsq=1，sql2可用
	 */
	public List<CityElectricFeeCheck> queryCityFeeCheck(String whereStr,
			String loginId,String lrbz1,String lrbz2) {
		System.out.println("ElectricFeesBean审核电费列表条件:");
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		DataBase db = new DataBase();
		StringBuffer sql1 = new StringBuffer();// 市级审核查询1 sql（月结，预付费2）
		StringBuffer sql2 = new StringBuffer();// 市级审核查询2（合同、插卡、预付费1）
		List<CityElectricFeeCheck> list = new ArrayList<CityElectricFeeCheck>();
		sql1
				.append("SELECT ZD.ZDCODE, ZD.JZNAME,"
						+ "D.DBID,RTNAME(ZD.PROPERTY),RTNAME(D.DFZFLX),"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "RTNAME(EF.NOTETYPEID),(SELECT IS2.NAME FROM INDEXS IS2 WHERE D.LINELOSSTYPE=IS2.CODE AND IS2.TYPE='xslx') AS LINELOSSTYPE,D.LINELOSSVALUE,D.BEILV,"
						+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,EF.EDHDL,AM.lastdatetime,AM.thisdatetime,EF.UNITPRICE,'1' AS DFBZW,AM.BLHDL,AM.DEDHDL, ZD.ZLFH,ZD.JLFH,"
						+ " EF.JSZQ,EF.QSDBDL,EF.PJJE,ZD.ID, "
						+ " D.DBNAME,rtname(ZD.GDFS),to_char(AM.LASTDATETIME,'yyyy-mm-dd'),to_char(AM.THISDATETIME,'yyyy-mm-dd'),AM.FLOATDEGREE,AM.MEMO,EF.UNITPRICE,EF.FLOATPAY,EF.MEMO,rndiqu(zd.xian),AM.ACTUALDEGREE,AM.CSDB,EF.MANPASSMEMO,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),AM.TBTZSQ, AM.AMMETERDEGREEID,"
						+ "AM.THISDEGREE,AM.LASTDEGREE,AM.LASTELECFEES,AM.LASTELECDEGREE,AM.LASTUNITPRICE,AM.LASTFLOATDEGREE,AM.LASTACTUALDEGREE,EF.CHANGEVALUE,EF.ACTUALLINELOSSVALUE,AM.BLHDL/EF.JSZQ BZRJ"
						+ " FROM CITYCHECK_DDF EF,ZHANDIAN ZD,DIANBIAO D,CITYCHECK_DDV AM  WHERE  D.DBID=AM.AMMETERID_FK AND ZD.ID=D.ZDID " +
						  "AND AM.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
						+ " AND (EF.MANUALAUDITSTATUS = '-1' OR EF.MANUALAUDITSTATUS = '1')"
						+ "  AND EF.COUNTYAUDITSTATUS='1' "
						+ whereStr+lrbz1
						+ " AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		sql2
				.append("SELECT ZD.ZDCODE, ZD.JZNAME,"
						+ "D.DBID,RTNAME(ZD.PROPERTY),RTNAME(D.DFZFLX),"
						+ "(SELECT NAME  FROM INDEXS WHERE CODE = ZD.STATIONTYPE  AND TYPE = 'stationtype') AS STATIONTYPE,"
						+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) "
						+ "ELSE  ''  END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "RTNAME(EF.NOTETYPEID),(SELECT IS2.NAME FROM INDEXS IS2 WHERE D.LINELOSSTYPE=IS2.CODE AND IS2.TYPE='xslx') AS LINELOSSTYPE,"
						+ "D.LINELOSSVALUE,D.BEILV, EF.UUID, EF.MONEY, EF.CITYAUDIT,EF.ID,EF.EDHDL,'2' as dfbzw , zd.zlfh,zd.jlfh,"
						+ " EF.JSZQ,EF.QSDBDL,EF.PJJE,ZD.ID, "
						+ " D.DBNAME,rtname(ZD.GDFS),to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),EF.DANJIA,EF.MANUALAUDITSTATUS,rndiqu(zd.xian), "
						+ " EF.DEDHDL,EF.DIANLIANG,EF.AUTOAUDITSTATUS,EF.CSDB,EF.MANPASSMEMO,to_char(EF.ACCOUNTMONTH,'yyyy-mm'), EF.AMMETERDEGREEID_FK,EF.FINANCEAUDIT,EF.LASTYUE,EF.DIANLIANG/EF.JSZQ BZRJ"
						+ " FROM CITYCHECK_YFF EF, ZHANDIAN ZD, DIANBIAO D WHERE ZD.ID = D.ZDID AND EF.PREPAYMENT_AMMETERID=D.DBID " +
						  " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1'"
						+ " AND (EF.MANUALAUDITSTATUS = '-1' OR EF.MANUALAUDITSTATUS = '1') "
						+ whereStr+lrbz2
						+ " AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx03' OR D.DFZFLX='dfzflx04') AND EF.COUNTYAUDITSTATUS='1'"
						+ " AND EF.CITYAUDIT <= '1' AND EF.FINANCEAUDIT <= '1'  AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		try {
			conn = db.getConnection();// 获取连接
			ps1 = conn.prepareStatement(sql1.toString());// 预编译
			ps2 = conn.prepareStatement(sql2.toString());// 预编译
			System.out.println("市级审核查询1"+sql1.toString());
			rs1 = ps1.executeQuery();// 执行sql1
			System.out.println("市级审核查询2"+sql2.toString());
			rs2 = ps2.executeQuery();// 执行sql2
			// while字段(月结，预付费2)
			String zdid = null;
			String zdcode = null;
			String jzname = null;
			String dbid = null;
			String property = null;
			String dfzflx = null;
			String stationtype = null;
			String szdq = null;
			String notetypeid = null;
			String linelosstype = null;
			String linelossvalue = null;
			String beilv = null;
			String electricfee_id = null;
			String dfuuid = null;
			String actualpay = null;
			String autoauditstatus = null;
			String manualauditstatus = null;
			String cityaudit = null;
			String edhdl = null;
			String lastdatetime = null;//没用了
			String thisdatetime = null;//没用了
			String unitprice = null;//没用了
			String dfbzw = null;
			String blhdl = null;
			String dedhdl = null;
			String zlfh = null;
			String jlfh = null;
			String jszq = null;
			String qsdbdl = null;
			String pjje = null;
			
			String dbname = "";// 电表名称
			String gdfs = "";// 供电方式
			String dbydl = "";// 电表用电量
			String sccbsj = "";// 上次抄表时间
			String bccbsj = "";// 本次抄表时间
//			String bzydl = "";// 报账用电量
			String ydltz = "";// 用电量调整
			String dlmemo = "";// 电量调整备注
			String danjia = "";// 单价
			String dftz = "";// 电费调整
			String dfmemo = "";// 电费调整备注
			String csdbdfe = "";// 超省定标电费额
			String csdbdfbz = "";// 超省定标电费比值
			String csdbdfjdz = "";//超省定标绝对值
			String cshidbdfbz = "";// 超市定标电费比值
			String cshidbdfjdz = "";//超市定标电费绝对值
			String quxian="";
			String qsdbdlcbbl = null;//全省定标电量超标比例
			String manpassmemo = null;//人工审核通过原因
			String accountmonth = null;//报账月份
			String tbtzsq = null;//特别调整申请
			String dlid = null;//电量ID
			String thisdegree = "";//本次的电表读数
			String lastdegree = "";//上次电表读数
			String lastelecfee = "";//上期电费
			String lastelecdegree = "";//上期电量
			String lastunitprice = "";//上期单价
			

			// while2字段(合同、插卡、预付费1)
			String zdid2 = null;
			String zdcode2 = null;
			String jzname2 = null;
			String dbid2 = null;
			String property2 = null;
			String dfzflx2 = null;
			String stationtype2 = null;
			String szdq2 = null;
			String notetypeid2 = null;
			String linelosstype2 = null;
			String linelossvalue2 = null;
			String beilv2 = null;
			String uuid2 = null;
			String actualpay2 = null;
			String cityaudit2 = null;
			String yff_id2 = null;
			String edhdl2 = null;
			String dfbzw2 = null;
			String zlfh2 = null;
			String jlfh2 = null;
			String jszq2 = null;
			String qsdbdl2 = null;
			String pjje2 = null;
			String dedhdl2 = "";
			String blhdl2 = "";
			String qsdbdlcbbl2 = null;//全省定标电量超标比例
			
			String dbname2 = "";// 电表名称
			String gdfs2 = "";// 供电方式
			String dbydl2 = "";// 电表用电量
			String sccbsj2 = "";// 上次抄表时间
			String bccbsj2 = "";// 本次抄表时间
//			String bzydl2 = "";// 报账用电量
			String ydltz2 = "";// 用电量调整
			String dlmemo2 = "";// 电量调整备注
			String danjia2 = "";// 单价
			String dftz2 = "";// 电费调整
			String dfmemo2 = "";// 电费调整备注
			String csdbdfe2 = "";// 超省定标电费额
			String csdbdfbz2 = "";// 超省定标电费比值
			String csdbdfjdz2 = "";//超省定标电费绝对值
			String cshidbdfbz2 = "";// 超市定标电费比值
			String cshidbdfjdz2 = "";//超市定标电费绝对值
			String rgshzt2 = "";
			String quxian2 ="";
			
			String autoauditstatus2 = "";//自动审核状态
			String manpassmemo2 = null;//人工审核通过原因
			String accountmonth2 = null;//报账月份
			
			String dlid2 = null;//电量ID
			
			String lastyue = "";//上期余额

			while (rs1.next()) {
				String gdtx = "";//工单提醒
				zdcode = rs1.getString(1);// 站点code
				jzname = rs1.getString(2);// 站点名称
				dbid = rs1.getString(3);// 电表id
				property = rs1.getString(4);// 站点属性
				dfzflx = rs1.getString(5);// 电费支付类型
				stationtype = rs1.getString(6);// 站点类型
				szdq = rs1.getString(7);// 所在地区
				notetypeid = rs1.getString(8);// 票据类型
				linelosstype = rs1.getString(9);// 线损类型
				linelossvalue = rs1.getString(10);// 线损值
				beilv = rs1.getString(11);// 倍率
				electricfee_id = rs1.getString(12);// 电费id
				dfuuid = rs1.getString(13);// 电费uuid
				actualpay = rs1.getString(14);// 实际电费金额double
				autoauditstatus = rs1.getString(15);// 自动审核状态
				manualauditstatus = rs1.getString(16);// 人工审核状态
				cityaudit = rs1.getString(17);// 市审核状态
				edhdl = rs1.getString(18);// 额定耗电量
				lastdatetime = rs1.getString(19);// 上次抄表时间
				thisdatetime = rs1.getString(20);// 本次抄表时间
				unitprice = rs1.getString(21);// 单价
				dfbzw = rs1.getString(22);// 电费标志位
				blhdl = rs1.getString(23);// 实际用电量double
				dedhdl = rs1.getString(24);// 电量超标比例
				zlfh = rs1.getString(25);// 直流负荷double
				jlfh = rs1.getString(26);// 交流负荷double
				jszq = rs1.getString(27);// 结算周期
				qsdbdl = rs1.getString(28);// 全省定标电量double
				pjje = rs1.getString(29);// 票据金额double
				zdid = rs1.getString(30);// 站点id

				dbname = rs1.getString(31) != null ? rs1.getString(31) : "";// 电表名称
				gdfs = rs1.getString(32) != null ? rs1.getString(32) : "";// 供电方式
				sccbsj = rs1.getString(33) != null ? rs1.getString(33) : "";// 上次抄表时间
				bccbsj = rs1.getString(34) != null ? rs1.getString(34) : "";// 本次抄表时间
				ydltz = rs1.getString(35) != null ? rs1.getString(35) : "";// 用电量调整
				dlmemo = rs1.getString(36) != null ? rs1.getString(36) : "";// 电量调整备注
				danjia = rs1.getString(37) != null ? rs1.getString(37) : "0";// 单价
				dftz = rs1.getString(38) != null ? rs1.getString(38) : "";// 电费调整
				dfmemo = rs1.getString(39) != null ? rs1.getString(39) : "";// 电费调整备注
				quxian = rs1.getString(40) != null ? rs1.getString(40) : "";//区县
				String actualdegree = rs1.getString(41) != null ? rs1.getString(41) : "";
				qsdbdlcbbl = rs1.getString(42) != null ? rs1.getString(42) : "";
				manpassmemo = rs1.getString(43);//人工审核通过原因
				accountmonth = rs1.getString(44);//报账月份
				tbtzsq = rs1.getString(45);//特别调整申请
				dlid = rs1.getString(46);//电量ID
				thisdegree = rs1.getString(47);//本次电表读数
				lastdegree = rs1.getString(48);//上次电表读数
				lastelecfee = rs1.getString(49);//上期电费
				lastelecdegree = rs1.getString(50);//上期电量
				lastunitprice = rs1.getString(51);//上期单价
				
				// 判断是否为空
				if (bccbsj == null || bccbsj == "" || bccbsj == "o"
						|| "null".equals(bccbsj)) {
					bccbsj = "";
				}
				if (sccbsj == null || sccbsj == "" || sccbsj == "o"
						|| "null".equals(sccbsj)) {
					sccbsj = "";
				}
				if (blhdl == null || blhdl == "" || blhdl == "o"
						|| "null".equals(blhdl)) {
					blhdl = "0";
				}
				if (" ".equals(qsdbdl) || "null".equals(qsdbdl)
						|| qsdbdl == null || "".equals(qsdbdl) || Format.str_d(qsdbdl)==0) {
					qsdbdl = "";
				}
				if ("".equals(zlfh) || "null".equals(zlfh) || zlfh == null) {
					zlfh = "";// 原项目就是这样处理的
				}
				if ("".equals(jlfh) || "null".equals(jlfh) || jlfh == null) {
					jlfh = "";// 原项目就是这样处理的
				}
				if ("".equals(jszq) || "null".equals(jszq) || jszq == null) {
					jszq = "";// 原项目就是这样处理的
				}
				if ("".equals(dedhdl) || "null".equals(dedhdl)
						|| dedhdl == null) {
					dedhdl = "";// 原来的就是这样处理的
				}
				if (unitprice == null || "".equals(unitprice)
						|| "o".equals(unitprice) || "null".equals(unitprice)) {
					unitprice = "0";// 原来的就是这样处理的
				}
				if ("".equals(property) || "null".equals(property)
						|| property == null) {
					property = "";
				}
				if ("".equals(stationtype) || "null".equals(stationtype)
						|| stationtype == null) {
					stationtype = "";
				}
				if ("".equals(dfzflx) || "null".equals(dfzflx)
						|| dfzflx == null) {
					dfzflx = "";
				}
				if ("".equals(edhdl) || "null".equals(edhdl) || edhdl == null) {
					edhdl = "0";
				}
				if ("null".equals(linelosstype) || linelosstype == null) {
					linelosstype = "";
				}
				if ("null".equals(linelossvalue) || linelossvalue == null) {
					linelossvalue = "";
				}
				if (beilv == null || beilv == "" || beilv == "o") {
					beilv = "1";
				}
				if ("null".equals(actualpay) || "".equals(actualpay)
						|| actualpay == null) {
					actualpay = "0";
				}
				if ("null".equals(notetypeid) || null == notetypeid) {
					notetypeid = "";
				}
				if ("null".equals(pjje) || "".equals(pjje) || null == pjje) {
					pjje = "";// 原来的看不明白怎么处理的
				}
				cityaudit = cityaudit != null ? cityaudit : "";

				//工单提醒判断
				
				if("0".equals(cityaudit)){//市级未通过
					gdtx = "待审核";
				}else if("1".equals(cityaudit)){//市级通过
					if("-1".equals(manualauditstatus)){//财务不通过
						gdtx = "上级退单";
					}
				}else if("-2".equals(cityaudit)){//市级不通过
					gdtx = "已撤回";
				}
				
				// 计算
				double eddf = 0;
				String zq = jszq;// 周期用于计算，原来是java计算的，现在是sql直接查出来的
				Long l = Long.parseLong(zq != "" ? zq : "0");
//				eddf = Format.d2(Double.parseDouble(edhdl)
//						* Double.parseDouble(unitprice) * l); // 额定电费公式：额定耗电量*本次单价*周期；页面上没这个字段了
//				String qsdbdlcbbl1 = "";
//				double qsdbdlcbbl = 0;
//				if (!"".equals(qsdbdl) && !"".equals(jszq)) {
//					long quot = Long.parseLong(jszq);// 周期
//					double sjydl = Double.parseDouble(blhdl);// 实际用电量
//					double dbdl1 = Double.parseDouble(qsdbdl) * quot;// 全省定标电量*周期
//					qsdbdlcbbl = ((sjydl - dbdl1) / dbdl1) * 100;// 全省定标电量超标比例:（实际用电量-（全省定标电量*周期））/（全省定标电量*周期）
//					// 预付费的不进行公式计算有就显示省定标没有就不显示
//					qsdbdlcbbl1 = Format.str2(String.valueOf(qsdbdlcbbl));
//				}
				qsdbdlcbbl = "".equals(qsdbdlcbbl)?"":Format.str2(qsdbdlcbbl);
				// 计算电表用电量
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree));
				if(Format.str_d(beilv)==0){
					beilv = "1";
				}
				Double dbdl = 0.0;
				
					dbdl = actdegree * Format.str_d(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				
				dbydl = dbdl.toString();// 电表用电量
				
				/* 新字段的一些计算 */
				if (!"".equals(qsdbdl) && !"".equals(jszq)) {

					double zq2 = 0;// 周期
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}

				

					/* 计算超省定标电费额 */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(qsdbdl)
										* Double.parseDouble(danjia) * zq2);
						BigDecimal bg2 = new BigDecimal(sdbdf);
						sdbdf = bg2.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						sdbdf = 0.0;
					}
					csdbdfe = sdbdf.toString();// 超省定标电费额
					csdbdfjdz = String.valueOf(Format.d2(Math.abs(sdbdf.doubleValue())));//超省定标电费绝对值

					/* 计算超省定标电费比值 */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl)
										* Double.parseDouble(danjia) * zq2)
								* 100;
						BigDecimal bg1 = new BigDecimal(csdbbz.doubleValue());
						csdbbz = bg1.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
							e.printStackTrace();
						csdbbz = 0.0;
					}
					csdbdfbz = Format.str2(csdbbz.toString());// 超省定标电费比值
				}
				if (!"0".equals(edhdl) && !"".equals(jszq)) {

					double zq2 = 0;// 周期
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}
					/* 计算超市定标电费额 */
					Double shidbdf = 0.0;
					try {
						shidbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(edhdl)
										* Double.parseDouble(danjia) * zq2);
						BigDecimal bg3 = new BigDecimal(shidbdf);
						shidbdf = bg3.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						shidbdf = 0.0;
					}
					cshidbdfjdz = String.valueOf(Format.d2(Math.abs(shidbdf.doubleValue())));//超市定标电费绝对值

					/* 计算超市定标电费比值 */
					Double cshidbbz = 0.0;
					try {
						cshidbbz = shidbdf
								/ (Double.parseDouble(edhdl)
										* Double.parseDouble(danjia) * zq2)
								* 100;
						BigDecimal bg4 = new BigDecimal(cshidbbz);
						cshidbbz = bg4.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {

						cshidbbz = 0.0;
					}
					cshidbdfbz = Format.str2(cshidbbz.toString());// 超市定标电费比值

				}
				// 赋值
				CityElectricFeeCheck feesbeana = new CityElectricFeeCheck();
				feesbeana.setZdcode(zdcode);
				feesbeana.setJzname(jzname);
				feesbeana.setDbid(dbid);
				feesbeana.setProperty(property);
				feesbeana.setDfzflx(dfzflx);
				feesbeana.setStationtype(stationtype);
				feesbeana.setSzdq(szdq);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setLinelosstype(linelosstype);
				feesbeana.setLinelossvalue(linelossvalue);
				feesbeana.setEy_id(electricfee_id);
				feesbeana.setUuid(dfuuid);
				feesbeana.setActualpay(Format.str2(actualpay));
				feesbeana.setAutoauditstatus(autoauditstatus);
				feesbeana.setManualauditstatus(manualauditstatus);
				feesbeana.setCityaudit(cityaudit);
				feesbeana.setEdhdl(Format.str2(edhdl));
				feesbeana.setLastdatetime(lastdatetime);
				feesbeana.setThisdatetime(thisdatetime);
				feesbeana.setUnitprice(Format.str4(unitprice));
				feesbeana.setDfbzw(dfbzw);
				feesbeana.setBlhdl(Format.str2(blhdl));
				if ("∞".equals(dedhdl) || "-∞".equals(dedhdl)) {// 如果为∞直接赋值(防止格式化出错)，否则格式化再赋值
					feesbeana.setDedhdl(dedhdl);
				} else {
					feesbeana.setDedhdl( !"".equals(dedhdl) ? Format.str2(dedhdl) : "");
				}
				feesbeana.setZlfh( !"".equals(zlfh) ? Format.str2(zlfh) : "");
				feesbeana.setJlfh( !"".equals(jlfh) ? Format.str2(jlfh) : "");
				feesbeana.setJszq(jszq);
				feesbeana.setQsdbdl( !"".equals(qsdbdl) ? Format.str2(qsdbdl) : "");// 为空则不格式化,因为空时显示""而不是"0"
				feesbeana.setPjje( !"".equals(pjje) ? Format.str2(pjje) : "");
				feesbeana.setEddf(Format.str2(String.valueOf(eddf)));
				feesbeana.setQsdbdlcbbl(qsdbdlcbbl);
				feesbeana.setZdid(zdid);

				feesbeana.setDbname(dbname);// 电表名称
				feesbeana.setGdfs(gdfs);// 供电方式
				feesbeana.setDbydl(dbydl);// 电表用电量
				feesbeana.setSccbsj(sccbsj);// 上次抄表时间
				feesbeana.setBccbsj(bccbsj);// 本次抄表时间
//				feesbeana.setBzydl(bzydl);// 报账用电量
				feesbeana.setYdltz("".equals(ydltz)?"":Format.str2(ydltz));// 用电量调整
				feesbeana.setDlmemo(dlmemo);// 电量调整备注
				feesbeana.setDanjia(Format.str4(danjia));// 单价
				feesbeana.setDftz("".equals(dftz)?"":Format.str2(dftz));// 电费调整
				feesbeana.setDfmemo(dfmemo);// 电费调整备注
				feesbeana.setCsdbdfe(csdbdfe);// 超省定标电费额
				feesbeana.setCsdbdfbz(csdbdfbz + "%");// 超省定标电费比值
				feesbeana.setQuxian(quxian);
				feesbeana.setManpassmemo(manpassmemo);//人工审核通过原因
				feesbeana.setAccountmonth(accountmonth);//报账月份
				feesbeana.setLrbz("电费单录入");//录入标志
				feesbeana.setLastelecfees(lastelecfee);//上期电费
				feesbeana.setLastelecdegree(lastelecdegree);//上期电量
				feesbeana.setLastunitprice(lastunitprice);//上期单价
				
				if("1".equals(tbtzsq)){
					tbtzsq = "有申请，审核赋值";
				}else if("0".equals(tbtzsq)){
					tbtzsq = "无申请";
				}

				feesbeana.setTbtzsq(tbtzsq);
				feesbeana.setDlid(dlid);
				
				//工单提醒
				feesbeana.setGdtx(gdtx);
				
				feesbeana.setLastyue("");
				

				//电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(beilv)==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = beilv;
				}
				String lastactualdegree = rs1.getString("LASTACTUALDEGREE")!=null?rs1.getString("LASTACTUALDEGREE"):"0.00"; //上期电表用电量
				String lastfloatdegree = rs1.getString("LASTFLOATDEGREE")!=null?rs1.getString("LASTFLOATDEGREE"):"0.00"; //上期电量调整
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(feesbeana.getYdltz())*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				feesbeana.setLastfloatdegreeandbl(lastfloatdegreeandbl);//上期电量调整*倍率
				feesbeana.setLastactualdegree(lastactualdegree);//上期电表用电量
				feesbeana.setFloatdegreeandbl(floatdegreeandbl);//电量调整*倍率
				
				String cshengbdld;
				String cshibdld;
				//超省标电量度，超市标电量度
				if(Format.str_d(feesbeana.getQsdbdl())==0){
					cshengbdld = "";
				}else{
					cshengbdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getQsdbdl())* Format.str_d(feesbeana.getJszq())));
				}
				if(Format.str_d(feesbeana.getEdhdl())==0){
					cshibdld = "";
				}else{
					cshibdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getEdhdl())* Format.str_d(feesbeana.getJszq())));
				}
				feesbeana.setCshengbdld(cshengbdld);
				feesbeana.setCshibdld(cshibdld);
				feesbeana.setCsdbdfjdz(csdbdfjdz);
				feesbeana.setCshidbdfjdz(cshidbdfjdz);
				feesbeana.setCshidbdfbl(cshidbdfbz+ "%");
				
				
				
				/*String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +feesbeana.getZdid()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
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
				feesbeana.setGlbrjl(Format.str2(""));
				String changevalue = rs1.getString("CHANGEVALUE")!=null?rs1.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs1.getString("ACTUALLINELOSSVALUE")!=null?rs1.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs1.getString("BZRJ")!=null?rs1.getString("BZRJ"):"0"; //报账日均电量
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				feesbeana.setLineandchangeandbl(changeandlineandbl);//线变损电量
				feesbeana.setBzrj(Format.str2(bzrj));//报账日均电量
				feesbeana.setBeilv(beilv);
				list.add(feesbeana);// 将bean添加到list中

			}
			while (rs2.next()) {
				String gdtx2 = "";//工单提醒
				String financeaudit = "";//财务审核状态
				zdcode2 = rs2.getString(1);// 站点code
				jzname2 = rs2.getString(2);// 站点名称
				dbid2 = rs2.getString(3);// 电表id
				property2 = rs2.getString(4);// 站点属性
				dfzflx2 = rs2.getString(5);// 电费支付类型
				stationtype2 = rs2.getString(6);// 站点类型
				szdq2 = rs2.getString(7);// 所在地区
				notetypeid2 = rs2.getString(8);// 票据类型
				linelosstype2 = rs2.getString(9);// 线损类型
				linelossvalue2 = rs2.getString(10);// 线损值
				beilv2 = rs2.getString(11);// 倍率
				uuid2 = rs2.getString(12);// 预付费uuid
				actualpay2 = rs2.getString(13);// 实际电费金额double
				cityaudit2 = rs2.getString(14);// 市审核状态
				yff_id2 = rs2.getString(15);// 预付费id
				edhdl2 = rs2.getString(16);// 额定耗电量
				dfbzw2 = rs2.getString(17);// 电费标志位
				zlfh2 = rs2.getString(18);// 直流负荷double
				jlfh2 = rs2.getString(19);// 交流负荷double
				jszq2 = rs2.getString(20);// 结算周期
				qsdbdl2 = rs2.getString(21);// 全省定标电量double
				pjje2 = rs2.getString(22);// 票据金额double
				zdid2 = rs2.getString(23);// 站点id

				dbname2 = rs2.getString(24) != null ? rs2.getString(24) : "";// 电表名称
				gdfs2 = rs2.getString(25) != null ? rs2.getString(25) : "";// 供电方式
				sccbsj2 = rs2.getString(26) != null ? rs2.getString(26) : "";// 上次抄表时间
				bccbsj2 = rs2.getString(27) != null ? rs2.getString(27) : "";// 本次抄表时间
				danjia2 = rs2.getString(28) != null ? rs2.getString(28) : "0";// 单价
				rgshzt2 = rs2.getString(29) != null ? rs2.getString(29) : "";// 人工审核状态
				quxian2 = rs2.getString(30) != null ? rs2.getString(30) : "";//区县
				
				dedhdl2 = rs2.getString(31);//电量超标比例
				blhdl2 = rs2.getString(32);//实际用电量
				autoauditstatus2 = rs2.getString(33);//自动审核状态
				qsdbdlcbbl2 = rs2.getString(34);
				manpassmemo2 = rs2.getString(35);//人工审核通过原因
				accountmonth2 = rs2.getString(36);//报账月份
				dlid2 = rs2.getString(37);
				financeaudit = rs2.getString(38) != null ? rs2.getString(38) : "";//财务审核状态
				lastyue = rs2.getString(39) != null ? rs2.getString(39) : "";//上期余额
				
				if("-1".equals(financeaudit) || "2".equals(financeaudit)){//财务审核状态为-1或2
					rgshzt2 = financeaudit;
				}

				// 判断是否为空
				if ("".equals(dedhdl2) || "null".equals(dedhdl2) || dedhdl2 == null) {
					dedhdl2 = "";//原来的就是这样处理的
				}
				 if (blhdl2 == null || blhdl2 == "" || blhdl2 == "o" || "null".equals(blhdl2)){
			        	blhdl2 = "0";
			        	}
				if (bccbsj2 == null || bccbsj2 == "" || bccbsj2 == "o"
						|| "null".equals(bccbsj2)) {
					bccbsj2 = "";
				}
				if (sccbsj2 == null || sccbsj2 == "" || sccbsj2 == "o"
						|| "null".equals(sccbsj2)) {
					sccbsj2 = "";
				}
				if (" ".equals(qsdbdl2) || "null".equals(qsdbdl2)
						|| qsdbdl2 == null || Format.str_d(qsdbdl2)==0) {
					qsdbdl2 = "";
				}
				if ("".equals(zlfh2) || "null".equals(zlfh2) || zlfh2 == null) {
					zlfh2 = "";
				}
				if ("".equals(jlfh2) || "null".equals(jlfh2) || jlfh2 == null) {
					jlfh2 = "";
				}
				if ("".equals(jszq2) || "null".equals(jszq2) || jszq2 == null) {
					jszq2 = "";
				}
				if ("".equals(property2) || "null".equals(property2)
						|| property2 == null) {
					property2 = "";
				}
				if ("".equals(stationtype2) || "null".equals(stationtype2)
						|| stationtype2 == null) {
					stationtype2 = "";
				}
				if ("".equals(dfzflx2) || "null".equals(dfzflx2)
						|| dfzflx2 == null) {
					dfzflx2 = "";
				}
				if ("".equals(edhdl2) || "null".equals(edhdl2)
						|| edhdl2 == null) {
					edhdl2 = "0";
				}
				if ("null".equals(linelosstype2) || linelosstype2 == null) {
					linelosstype2 = "";
				}
				if ("null".equals(linelossvalue2) || linelossvalue2 == null) {
					linelossvalue2 = "";
				}
				if (beilv2 == null || beilv2 == "" || beilv2 == "o") {
					beilv2 = "0";
				}
				if ("null".equals(actualpay2) || "".equals(actualpay2)
						|| actualpay2 == null) {
					actualpay2 = "0";
				}
				if ("null".equals(notetypeid2) || null == notetypeid2) {
					notetypeid2 = "";
				}
				if ("null".equals(pjje2) || "".equals(pjje2) || null == pjje2) {
					pjje2 = "";
				}
				cityaudit2 = cityaudit2 != null ? cityaudit2 : "";
				
				qsdbdlcbbl2 = "".equals(qsdbdlcbbl2)?"":Format.str2(qsdbdlcbbl2);
				
				//工单提醒判断
				if("0".equals(cityaudit2)){//市级未通过
					gdtx2 = "待审核";
				}else if("1".equals(cityaudit2)){//市级通过
					if("-1".equals(rgshzt2)){//财务不通过
						gdtx2 = "上级退单";
					}
				}else if("-2".equals(cityaudit2)){//市级不通过
					gdtx2 = "已撤回";
				}
				// 计算电表用电量
				if(Format.str_d(beilv2)==0){
					beilv2 ="1";
				}
				Double dbdl = 0.0;
				
					dbdl = Format.str_d(blhdl2) * Format.str_d(beilv2);
					BigDecimal bg1 = new BigDecimal(dbdl);
					dbdl = bg1.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				dbydl2 = dbdl.toString();// 电表用电量
				
				if (!"".equals(qsdbdl2) && !"".equals(jszq2)) {

					/* 新字段的一些计算 */
					double zq2 = 0;// 周期
					
						zq2 = Format.str_d(jszq2);
					

				

					/* 计算超省定标电费额 */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay2)
								- (Double.parseDouble(qsdbdl2)
										* Double.parseDouble(danjia2) * zq2);
						
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
					csdbdfe2 = Format.str2(sdbdf.toString());// 超省定标电费额
					csdbdfjdz2 = String.valueOf(Format.d2(Math.abs(sdbdf.doubleValue())));//超省定标电费绝对值
					/* 计算超省定标电费比值 */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl2)
										* Double.parseDouble(danjia2) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						csdbbz = 0.0;
					}
					csdbdfbz2 = Format.str2(csdbbz.toString());// 超省定标电费比值
				}
				if (!"0".equals(edhdl2) && !"".equals(jszq2)) {

					double zq2 = 0;// 周期
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}
					/* 计算超市定标电费额 */
					Double shidbdf = 0.0;
					try {
						shidbdf = Double.parseDouble(actualpay2)
								- (Double.parseDouble(edhdl2)
										* Double.parseDouble(danjia2) * zq2);
						BigDecimal bg = new BigDecimal(shidbdf);
						shidbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						shidbdf = 0.0;
					}
					cshidbdfjdz2 = String.valueOf(Format.d2(Math.abs(shidbdf.doubleValue())));//超市定标电费绝对值

					/* 计算超市定标电费比值 */
					Double cshidbbz = 0.0;
					try {
						cshidbbz = shidbdf
								/ (Double.parseDouble(edhdl2)
										* Double.parseDouble(danjia2) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(cshidbbz);
						cshidbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						cshidbbz = 0.0;
					}
					cshidbdfbz2 = Format.str2(cshidbbz.toString());// 超市定标电费比值

				}
				
				// 赋值
				CityElectricFeeCheck feesbeana = new CityElectricFeeCheck();
				feesbeana.setZdcode(zdcode2);
				feesbeana.setJzname(jzname2);
				feesbeana.setDbid(dbid2);
				feesbeana.setProperty(property2);
				feesbeana.setDfzflx(dfzflx2);
				feesbeana.setStationtype(stationtype2);
				feesbeana.setSzdq(szdq2);
				feesbeana.setNotetypeid(notetypeid2);
				feesbeana.setLinelosstype(linelosstype2);
				feesbeana.setLinelossvalue(linelossvalue2);
				feesbeana.setUuid(uuid2);
				feesbeana.setActualpay(Format.str2(actualpay2));
				feesbeana.setCityaudit(cityaudit2);
				feesbeana.setEy_id(yff_id2);
				feesbeana.setEdhdl(Format.str2(edhdl2));
				feesbeana.setDfbzw(dfbzw2);
				feesbeana.setZlfh(!"".equals(zlfh2) ? Format.str2(zlfh2) : "");// 为空则不格式化,因为空时显示""而不是"0"
				feesbeana.setJlfh(!"".equals(jlfh2) ? Format.str2(jlfh2) : "");// 为空则不格式化
				feesbeana.setJszq(jszq2);
				feesbeana.setQsdbdl( !"".equals(qsdbdl2) ? Format.str2(qsdbdl2) : "");// 为空则不格式化
				feesbeana.setPjje( !"".equals(pjje2) ? Format.str2(pjje2) : "");// 为空则不格式化
				feesbeana.setQsdbdlcbbl(qsdbdlcbbl2);// 为空则不格式化
				feesbeana.setZdid(zdid2);

				feesbeana.setDbname(dbname2);// 电表名称
				feesbeana.setGdfs(gdfs2);// 供电方式
				feesbeana.setDbydl(dbydl2);// 电表用电量
				feesbeana.setSccbsj(sccbsj2);// 上次抄表时间
				feesbeana.setBccbsj(bccbsj2);// 本次抄表时间
//				feesbeana.setBzydl(bzydl2);// 报账用电量
				feesbeana.setYdltz("".equals(ydltz2)?"":Format.str2(ydltz2));// 用电量调整
				feesbeana.setDlmemo(dlmemo2);// 电量调整备注
				feesbeana.setDanjia(Format.str4(danjia2));// 单价
				feesbeana.setDftz("".equals(dftz2)?"":Format.str2(dftz2));// 电费调整
				feesbeana.setDfmemo(dfmemo2);// 电费调整备注
				feesbeana.setCsdbdfe(csdbdfe2);// 超省定标电费额
				feesbeana.setCsdbdfbz(csdbdfbz2 + "%");// 超省定标电费比值
				feesbeana.setManualauditstatus(rgshzt2);
				feesbeana.setQuxian(quxian2);
				if("∞".equals(dedhdl2) || "-∞".equals(dedhdl2)){//如果为∞直接赋值(防止格式化出错)，否则格式化再赋值
		        	feesbeana.setDedhdl(dedhdl2);
		        }else{
		        	feesbeana.setDedhdl(!"".equals(dedhdl2)?Format.str2(dedhdl2):"");
		        }
		        feesbeana.setBlhdl(Format.str2(blhdl2));
		        feesbeana.setAutoauditstatus(autoauditstatus2);
		        feesbeana.setManpassmemo(manpassmemo2);//人工审核通过原因
		        feesbeana.setAccountmonth(accountmonth2);//报账月份
		        feesbeana.setLrbz("预付费录入");//录入标志
		        feesbeana.setGdtx(gdtx2);
		        
		        feesbeana.setTbtzsq("");
		        feesbeana.setDlid(dlid2);
		        feesbeana.setLastyue(lastyue);
		        
		        feesbeana.setLastfloatdegreeandbl("");//上期电量调整*倍率
				feesbeana.setLastactualdegree("");//上期电表用电量
				feesbeana.setFloatdegreeandbl("");//电量调整*倍率
				
				String cshengbdld;
				String cshibdld;
				//超省标电量度，超市标电量度
				if(Format.str_d(feesbeana.getQsdbdl())==0){
					cshengbdld = "";
				}else{
					cshengbdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getQsdbdl())* Format.str_d(feesbeana.getJszq())));
				}
				if(Format.str_d(feesbeana.getEdhdl())==0){
					cshibdld = "";
				}else{
					cshibdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getEdhdl())* Format.str_d(feesbeana.getJszq())));
				}
				feesbeana.setCshengbdld(cshengbdld);
				feesbeana.setCshibdld(cshibdld);
				feesbeana.setCsdbdfjdz(csdbdfjdz2);
				feesbeana.setCshidbdfjdz(cshidbdfjdz2);
				feesbeana.setCshidbdfbl(cshidbdfbz2+ "%");
				feesbeana.setGlbrjl("");
				feesbeana.setLineandchangeandbl("");//线变损电量
				String bzrj = rs2.getString("BZRJ")!=null?rs2.getString("BZRJ"):"0"; //报账日均电量
				feesbeana.setBzrj(Format.str2(bzrj));//报账日均电量
				feesbeana.setBeilv(beilv2);
				list.add(feesbeana);
			}
		} catch (DbException dbe) {// 捕捉异常
			dbe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 释放资源
			db.free(rs1, ps1, null);
			db.free(rs2, ps2, conn);
		}
		return list;// 返回结果集
	}

	/**
	 * @param list
	 *            List(CityElectricFeeCheck)
	 * @return (double[]) total[0] 电量合计 total[1] 电费合计
	 * @see 计算电量合计和电费合计
	 * @author WangYiXiao 2014-1-24
	 */
	public String[] total(List<CityElectricFeeCheck> list) {
		String[] total = new String[2];
		double totalelectric = 0;// 电量合计
		double totalmoney = 0;// 电费合计
		int i;
		int size = list.size();// 数据条数
		String blhdl = null;
		for (i = 0; i < size; i++) {// 遍历求和
			CityElectricFeeCheck bean = list.get(i);// 获得一个对象
			blhdl = bean.getBlhdl();// 实际用电量
			if (blhdl == null || blhdl == "" || blhdl == "o"
					|| "null".equals(blhdl)) {// 实际用电量为空则赋0
				blhdl = "0";
			}
			totalelectric = totalelectric + Double.parseDouble(blhdl);
			totalmoney = totalmoney + Double.parseDouble(bean.getActualpay());
		}
		// 放入total
		total[0] = Format.str2(String.valueOf(totalelectric));
		total[1] = Format.str2(String.valueOf(totalmoney));
		return total;
	}

	/**
	 * @param personnal
	 *            (String) 审核人
	 * @param cityaudit
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
	 * @author WangYiXaio 2014-1-25
	 * @update WangYiXiao 2014-4-2 把报账月份 改为 当前月份
	 * @return String 审核信息msg
	 */
	public synchronized String CheckCityFees(String personnal,
			String cityaudit, String chooseIdStr1, String chooseIdStr2,
			String bz) {
		String msg = "审核电费信息失败！";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// 系统时间
		String yuefen = "to_date(to_char(sysdate,'yyyy-mm'),'yyyy-mm')";//当前月份
		DataBase db = new DataBase();
		Connection conn = null;
		String str = "";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		if (bz.equals("1")) {
			str = "";
		} else if (bz.equals("0")) {
			str = "LIUCHENGHAO='',";// 流程号
		}
		StringBuffer sql1 = new StringBuffer();// 月结，预付费2
		StringBuffer sql2 = new StringBuffer();// 合同插卡预付费1
		try {
			conn = db.getConnection();
			// conn.setAutoCommit(false);
			sql1.append(" UPDATE ELECTRICFEES SET CITYAUDIT='" + cityaudit
					+ "'," + "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+" ACCOUNTMONTH=" + yuefen
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYAUDIT='" + cityaudit + "',"
					+ "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+ "ACCOUNTMONTH=" + yuefen
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// 如果电费uuid不为空则执行sql
				ps1 = conn.prepareStatement(sql1.toString());
				System.out.println("市级审核电费单"+sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) { // 如果预付费uuid不为空则执行sql
				ps2 = conn.prepareStatement(sql2.toString());
				System.out.println("市级审核预付费"+sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "审核电费信息通过！";
			} else if (bz.equals("0")) {
				msg = "审核电费信息取消通过！";
			} else {
				msg = "审核电费信息未通过！";
			}
			// conn.commit();
			// conn.setAutoCommit(true);
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {// 释放资源
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}


	/**
	 * @author lijing
	 * @see 济宁市级电费审核通过、不通过、取消通过方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Override
	public String CheckCityFees1(String personnal, String cityaudit,
			String chooseIdStr1, String chooseIdStr2, String bz,
			String cause) {
		String msg = "审核电费信息失败！";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// 系统时间
		String yuefen = "to_date(to_char(sysdate,'yyyy-mm'),'yyyy-mm')";//当前月份
		DataBase db = new DataBase();
		Connection conn = null;
		String str = "";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		if (bz.equals("1")) {
			str = "";
		} else if (bz.equals("0")) {
			str = "LIUCHENGHAO='',";// 流程号
		}
		StringBuffer sql1 = new StringBuffer();// 月结，预付费2
		StringBuffer sql2 = new StringBuffer();// 合同插卡预付费1
		try {
			conn = db.getConnection();
			
			sql1.append(" UPDATE ELECTRICFEES SET CITYAUDIT='" + cityaudit
					+ "'," + "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+" ACCOUNTMONTH=" + yuefen+","
					+" NOPASSCITY='" + cause+"'"
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYAUDIT='" + cityaudit + "',"
					+ "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+ "ACCOUNTMONTH=" + yuefen+","
					+" NOPASSCITY='" + cause + "'"
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// 如果电费uuid不为空则执行sql
				ps1 = conn.prepareStatement(sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) { // 如果预付费uuid不为空则执行sql
				ps2 = conn.prepareStatement(sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "济宁市级电费审核信息通过！";
			} else if (bz.equals("0")) {
				msg = "济宁市级电费审核信息取消通过！";
			} else {
				msg = "济宁市级电费审核信息未通过！";
			}
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {// 释放资源
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}

}
