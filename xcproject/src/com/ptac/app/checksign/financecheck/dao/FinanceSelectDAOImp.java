package com.ptac.app.checksign.financecheck.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.ptac.app.checksign.financecheck.bean.FinanceSelectBean;

public class FinanceSelectDAOImp implements FinanceSelectDAO {
	/*
	 * DataJdbc db ; public FinanceSelectDAOImp(){ db = DataJdbc.getInstance();
	 * }
	 * update zhouxue 2014-05-15 查询语句添加默认条件站点非虚拟，非采集，站点人工审核通过；   票据类型判断添加‘增值税发票’
	 * update zhouxue 2014-06-11 电费单查询语句默认条件修改：去掉限制条件 电费支付类型为预支和月结
	 */
	@Override
	public synchronized List<ElectricFeesFormBean> MessageSelect(
			FinanceSelectBean fsb) {// 根据过滤条件查询财务数据的方法
		StringBuffer whereStr = new StringBuffer();
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();

		String loginId = fsb.getLoginId();
		String shi = fsb.getShi();
		String xian = fsb.getXian();
		String xiaoqu = fsb.getXiaoqu();
		String zdmc = fsb.getZdmc();
		String zdlx = fsb.getZdlx();
		String zdsx = fsb.getZdsx();
		String lch = fsb.getLch();
		String bzyf = fsb.getBzyf();
		String lrrq = fsb.getLrrq();
		String zdqyzt = fsb.getZdqyzt();
		String dbqyzt = fsb.getDbqyzt();
		String cwshzt = fsb.getCwshzt();
		String pjlx = fsb.getPjlx();
		// 拼接SQL语句
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr.append(" and zd.shi='");
			whereStr.append(shi);
			whereStr.append("' ");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr.append(" and zd.xian='");
			whereStr.append(xian);
			whereStr.append("' ");
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {

			whereStr.append(" and zd.xiaoqu='");
			whereStr.append(xiaoqu);
			whereStr.append("' ");
		}
		if (bzyf != null && bzyf != "" && !bzyf.equals("0")) {

			whereStr.append(" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='");
			whereStr.append(bzyf);
			whereStr.append("' ");
		}
		if (zdmc != null && !zdmc.equals("") && !zdmc.equals("0")) {

			whereStr.append(" and zd.jzname like '%");
			whereStr.append(zdmc);
			whereStr.append("%' ");
		}
		if (lch != null && lch != "" && !lch.equals("0")) {

			whereStr.append(" and ef.liuchenghao='");
			whereStr.append(lch);
			whereStr.append("' ");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {

			whereStr.append(" and zd.STATIONTYPE='");
			whereStr.append(zdlx);
			whereStr.append("' ");
		}
		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {

			whereStr.append(" and zd.PROPERTY='");
			whereStr.append(zdsx);
			whereStr.append("' ");
		}
		if (lrrq != null && lrrq != "" && !lrrq.equals("0")) {

			whereStr.append(" and to_char(ef.entrytime,'yyyy-mm-dd') like '%");
			whereStr.append(lrrq);
			whereStr.append("%' ");
		}
		if (cwshzt != null && !cwshzt.equals("") && !cwshzt.equals("-2")) {

			str1.append(" AND EF.MANUALAUDITSTATUS = '");
			str1.append(cwshzt);
			str1.append("' ");
			str2.append("AND EF.FINANCEAUDIT='");
			str2.append(cwshzt);
			str2.append("' ");

		}
		if (zdqyzt != null && !zdqyzt.equals("") && !zdqyzt.equals("-1")) {

			str1.append("AND ZD.QYZT='");
			str1.append(zdqyzt);
			str1.append("' ");
			str2.append("AND ZD.QYZT='");
			str2.append(zdqyzt);
			str2.append("' ");
		}
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {

			str1.append("AND D.DBQYZT='");
			str1.append(dbqyzt);
			str1.append("' ");
			str2.append("AND D.DBQYZT='");
			str2.append(dbqyzt);
			str2.append("' ");
		}
		if (pjlx != null && !pjlx.equals("") && !pjlx.equals(" ")) {

			str1.append("AND EF.NOTETYPEID='");
			str1.append(pjlx);
			str1.append("' ");
			str2.append("AND EF.NOTETYPEID='");
			str2.append(pjlx);
			str2.append("' ");
		}
//电费单查询
		String sql1 = "SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY,"
				+ "D.DFZFLX,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "EF.NOTETYPEID,"
				+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.NOTENO,EF.ENTRYTIME,EF.MANUALAUDITSTATUS,"
				+ "(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) "
				+ " || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ,"
				+ "AD.LASTDEGREE,to_char(AD.LASTDATETIME,'yyyy-mm-dd'),AD.THISDEGREE,to_char(AD.THISDATETIME,'yyyy-mm-dd'),to_char(AD.STARTMONTH,'yyyy-mm'),to_char(AD.ENDMONTH,'yyyy-mm'),D.BEILV,AD.FLOATDEGREE,AD.BLHDL, EF.UNITPRICE, EF.FLOATPAY,EF.PJJE,EF.MANPASSMEMO,'1' AS DFBZW  "
				+ "FROM  financecheckdf_ddf EF,ZHANDIAN ZD,DIANBIAO D,financecheckdf_ddv AD WHERE ZD.ID=D.ZDID  AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=AD.AMMETERID_FK "
				+ "AND AD.AMMETERDEGREEID =EF.AMMETERDEGREEID_FK AND EF.CITYAUDIT='1'  and EF.CITYZRAUDITSTATUS='1' " +
				  " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
				+ whereStr.toString()
				+ str1.toString()
				+ " "
				+ " AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))";
//预付费查询
		String sql2 = "SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY,"
				+ "D.DFZFLX,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "EF.NOTETYPEID,"
				+ "EF.UUID,EF.MONEY,EF.NOTENO,EF.ENTRYTIME,EF.FINANCEAUDIT,"
				+ "(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END)  "
				+ "|| (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ, "
				+ "EF.STARTDEGREE,EF.STOPDEGREE,to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),to_char(EF.STARTMONTH,'yyyy-mm'),to_char(EF.ENDMONTH,'yyyy-mm'),D.BEILV,EF.DIANLIANG,EF.MONEY,EF.DANJIA,EF.PJJE,EF.AUTOAUDITSTATUS,EF.MANPASSMEMO,'2' AS DFBZW "
				+ "FROM  financecheck_yff EF,ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND EF.LIUCHENGHAO IS NOT NULL" +
				  " AND D.DBID=EF.PREPAYMENT_AMMETERID AND EF.CITYAUDIT='1'  and EF.CITYZRAUDITSTATUS='1' " +
				  " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
				+ whereStr.toString()
				+ str2.toString()
				+ " "
				+ "AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx04' OR D.DFZFLX = 'dfzflx03' ) AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))";


		DataBase db = new DataBase();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		System.out.println("财务查询电费："+sql1.toString());
		System.out.println("财务查询预付费："+sql2.toString());
		List<ElectricFeesFormBean> ls = new ArrayList<ElectricFeesFormBean>();

		try {
			db.connectDb();
		
			rs1 = db.queryAll(sql1);
			while (rs1.next()) {
				ElectricFeesFormBean feesbeana = new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs1.getString(1) != null ? rs1
						.getString(1) : "");
				feesbeana.setJzname(rs1.getString(2) != null ? rs1.getString(2)
						: "");
				feesbeana.setAccountmonth(rs1.getString(3) != null ? rs1
						.getString(3) : "");
				feesbeana.setDbid(rs1.getString(4) != null ? rs1.getString(4)
						: "");
				feesbeana.setDllx(rs1.getString(5) != null ? rs1.getString(5)
						: "");
				if(feesbeana.getDllx()==null||"null".equals(feesbeana.getDllx())){
					feesbeana.setDllx("");
				}

				String ydsb = rs1.getString(6) != null ? rs1.getString(6) : "";

				String property = rs1.getString(7) != null ? rs1.getString(7)
						: "";

				String dfzflx = rs1.getString(8) != null ? rs1.getString(8)
						: "";

				feesbeana.setStationtype(rs1.getString(9) != null ? rs1
						.getString(9) : "");

				String notetypeid = rs1.getString(10) != null ? rs1
						.getString(10) : "";

				feesbeana.setElectricfeeId(rs1.getString(11) != null ? rs1
						.getString(11) : "");
				feesbeana.setDfuuid(rs1.getString(12) != null ? rs1
						.getString(12) : "");

				String actualpay = rs1.getString(13) != null ? rs1
						.getString(13) : "";

				String autoauditstatus = rs1.getString(14) != null ? rs1
						.getString(14) : "";
				feesbeana.setNoteno(rs1.getString(15) != null ? rs1
						.getString(15) : "");
				feesbeana.setEntrytime(rs1.getString(16) != null ? rs1
						.getString(16) : "");
				String manualauditstatus = rs1.getString(17) != null ? rs1
						.getString(17) : "";
				feesbeana.setSzdq(rs1.getString(18) != null ? rs1.getString(18)
						: "");

				String lastdegree = rs1.getString(19) != null ? rs1
						.getString(19) : "";

				feesbeana.setLastdatetime(rs1.getString(20) != null ? rs1
						.getString(20) : "");

				String thisdegree = rs1.getString(21) != null ? rs1
						.getString(21) : "";

				feesbeana.setThisdatetime(rs1.getString(22) != null ? rs1
						.getString(22) : "");
				feesbeana.setStartmonth(rs1.getString(23) != null ? rs1
						.getString(23) : "");
				feesbeana.setEndmonth(rs1.getString(24) != null ? rs1
						.getString(24) : "");
				feesbeana.setBeilv(rs1.getString(25) != null ? rs1
						.getString(25) : "");

				String floatdegree = rs1.getString(26) != null ? rs1
						.getString(26) : "";
				String blhdl = rs1.getString(27) != null ? rs1.getString(27)
						: "";

				feesbeana.setUnitprice(rs1.getString(28) != null ? rs1
						.getString(28) : "");

				String floatpay = rs1.getString(29) != null ? rs1.getString(29)
						: "";

				String pjjedf = rs1.getString(30) != null ? rs1.getString(30)
						: "";
				String bzw = rs1.getString(32);
				
				
				if("1".equals(autoauditstatus)){
					feesbeana.setRgshtgyy("");
				}else{
					String rgshtgyy = rs1.getString(31)!=null?rs1.getString(31):"";
					feesbeana.setRgshtgyy(rgshtgyy);
				}
				
				if ("null".equals(feesbeana.getLastdatetime())
						|| feesbeana.getLastdatetime() == null) {
					feesbeana.setLastdatetime("");
				}
				if ("null".equals(feesbeana.getThisdatetime())
						|| feesbeana.getThisdatetime() == null) {
					feesbeana.setThisdatetime("");
				}

				DecimalFormat mat = new DecimalFormat("0.00");
				DecimalFormat mat1 = new DecimalFormat("0.0");

				// 通过判断来解决四舍五入问题
				double df = 0.00;

				if (actualpay == null || actualpay == "") {
					actualpay = "0";
					df = Double.parseDouble(actualpay);
					actualpay = mat.format(df);
				}

				feesbeana.setActualpay(actualpay);

				if (lastdegree != null && lastdegree != ""
						&& !" ".equals(lastdegree)) {
					double last = Double.parseDouble(lastdegree);
					lastdegree = mat1.format(last);
				}
				feesbeana.setLastdegree(lastdegree);

				if (thisdegree != null && thisdegree != ""
						&& !" ".equals(thisdegree)) {
					double last = Double.parseDouble(thisdegree);
					thisdegree = mat1.format(last);
				}
				feesbeana.setThisdegree(thisdegree);

				if (floatdegree != null && floatdegree != ""
						&& !" ".equals(floatdegree)) {
					double last = Double.parseDouble(floatdegree);
					floatdegree = mat1.format(last);
				}
				feesbeana.setFloatdegree(floatdegree);

				if (floatpay != null && floatpay != "" && !" ".equals(floatpay)) {
					double last = Double.parseDouble(floatpay);
					floatpay = mat.format(last);
				}
				feesbeana.setFloatpay(floatpay);

				if (blhdl != null && blhdl != "" && !"".equals(blhdl)) {
					double last = Double.parseDouble(blhdl);
					blhdl = mat1.format(last);
				}
				feesbeana.setBlhdl(blhdl);

				if (actualpay != null && actualpay != ""
						&& !"".equals(actualpay)) {
					double last = Double.parseDouble(actualpay);
					actualpay = mat.format(last);
				}
				feesbeana.setActualpay(actualpay);

				if (pjjedf != null && pjjedf != "" && !"".equals(pjjedf)) {
					double last = Double.parseDouble(pjjedf);
					pjjedf = mat.format(last);
				}

				feesbeana.setPjjedf(pjjedf);

				if (ydsb.equals("ybsb01")) {
					ydsb = "照明用电";
				} else if (ydsb.equals("ybsb02")) {
					ydsb = "空调用电";
				} else if (ydsb.equals("ybsb03")) {
					ydsb = "设备用电";
				} else if (ydsb.equals("ybsb04")) {
					ydsb = "其它用电设备";
				} else if (ydsb.equals("ybsb05")) {
					ydsb = "总表";
				} else if (ydsb.equals("ybsb06")) {
					ydsb = "综合";
				} else {
					ydsb = "";
				}

				if (property.equals("zdsx01")) {
					property = "局用机房";
				} else if (property.equals("zdsx02")) {
					property = "基站";
				} else if (property.equals("zdsx03")) {
					property = "营业网点";
				} else if (property.equals("zdsx04")) {
					property = "其他";
				} else if (property.equals("zdsx05")) {
					property = "接入网";
				} else if (property.equals("zdsx06")) {
					property = "乡镇支局";
				} else {
					property = "";
				}

				if (notetypeid.equals("pjlx05")) {
					notetypeid = "收据";
				} else if (notetypeid.equals("pjlx03")) {
					notetypeid = "发票";
				} else if(notetypeid.equals("pjlx06")){
					notetypeid = "增值税发票";
				}else {
					notetypeid="";
				}
				if (dfzflx.equals("dfzflx01")) {
					dfzflx = "月结";
				} else if (dfzflx.equals("dfzflx02")) {
					dfzflx = "合同";
				} else if (dfzflx.equals("dfzflx03")) {
					dfzflx = "预支";
				} else if (dfzflx.equals("dfzflx04")) {
					dfzflx = "插卡";
				} else {
					dfzflx = "";
				}

				feesbeana.setYdsb(ydsb);
				feesbeana.setProperty(property);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setDfzflx(dfzflx);

				if (autoauditstatus != null && autoauditstatus.equals("1")) {
					feesbeana.setAutoauditstatus("通过");
				} else {
					feesbeana.setAutoauditstatus("不通过");
				}

				feesbeana.setManualauditstatus(manualauditstatus);
				feesbeana.setBzw(bzw);
				

				ls.add(feesbeana);
			}

			

			rs2 = db.queryAll(sql2);
			while (rs2.next()) {
				ElectricFeesFormBean feesbeana = new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs2.getString(1) != null ? rs2
						.getString(1) : "");
				feesbeana.setJzname(rs2.getString(2) != null ? rs2.getString(2)
						: "");
				feesbeana.setAccountmonth(rs2.getString(3) != null ? rs2
						.getString(3) : "");
				feesbeana.setDbid(rs2.getString(4) != null ? rs2.getString(4)
						: "");
				feesbeana.setDllx(rs2.getString(5) != null ? rs2.getString(5)
						: "");
				if(feesbeana.getDllx()==null||"null".equals(feesbeana.getDllx())){
					feesbeana.setDllx("");
				}

				String ydsb = rs2.getString(6) != null ? rs2.getString(6) : "";

				String property = rs2.getString(7) != null ? rs2.getString(7)
						: "";
				String dfzflx = rs2.getString(8) != null ? rs2.getString(8)
						: "";
				feesbeana.setStationtype(rs2.getString(9) != null ? rs2
						.getString(9) : "");
				String notetypeid = rs2.getString(10) != null ? rs2
						.getString(10) : "";
				feesbeana.setDfuuid(rs2.getString(11) != null ? rs2
						.getString(11) : "");
				feesbeana.setActualpay(rs2.getString(12) != null ? rs2
						.getString(12) : "");
				feesbeana.setNoteno(rs2.getString(13) != null ? rs2
						.getString(13) : "");
				feesbeana.setEntrytime(rs2.getString(14) != null ? rs2
						.getString(14) : "");
				String manualauditstatus = rs2.getString(15) != null ? rs2
						.getString(15) : "";
				feesbeana.setSzdq(rs2.getString(16) != null ? rs2.getString(16)
						: "");

				String lastdegree = rs2.getString(17) != null ? rs2
						.getString(17) : "";
				String thisdegree = rs2.getString(18) != null ? rs2
						.getString(18) : "";

				feesbeana.setLastdatetime(rs2.getString(19) != null ? rs2
						.getString(19) : "");
				feesbeana.setThisdatetime(rs2.getString(20) != null ? rs2
						.getString(20) : "");
				
				if ("null".equals(feesbeana.getLastdatetime())
						|| feesbeana.getLastdatetime() == null) {
					feesbeana.setLastdatetime("");
				}
				if ("null".equals(feesbeana.getThisdatetime())
						|| feesbeana.getThisdatetime() == null) {
					feesbeana.setThisdatetime("");
				}

				feesbeana.setStartmonth(rs2.getString(21) != null ? rs2
						.getString(21) : "");
				feesbeana.setEndmonth(rs2.getString(22) != null ? rs2
						.getString(22) : "");
				feesbeana.setBeilv(rs2.getString(23) != null ? rs2
						.getString(23) : "");

				String blhdl = rs2.getString(24) != null ? rs2.getString(24)
						: "";

				String actualpay = rs2.getString(25) != null ? rs2
						.getString(25) : "";

				feesbeana.setUnitprice(rs2.getString(26) != null ? rs2
						.getString(26) : "");
				String pjjedf = rs2.getString(27) != null ? rs2.getString(27)
						: "";
				String autoauditstatus = rs2.getString(28)!=null?rs2.getString(28):"";
				String bzw1 = rs2.getString(30);
				if("1".equals(autoauditstatus)){
					feesbeana.setRgshtgyy("");
				}else{
					String rgshtgyy = rs2.getString(29)!=null?rs2.getString(29):"";
					feesbeana.setRgshtgyy(rgshtgyy);
				}
				
				if (autoauditstatus != null && autoauditstatus.equals("1")) {
					feesbeana.setAutoauditstatus("通过");
				} else {
					feesbeana.setAutoauditstatus("不通过");
				}
				

				DecimalFormat mat = new DecimalFormat("0.00");
				DecimalFormat mat1 = new DecimalFormat("0.0");

				double df = 0.00;
				if (actualpay == null || actualpay == "") {
					actualpay = "0";
					df = Double.parseDouble(actualpay);
					actualpay = mat.format(df);
				}
				feesbeana.setActualpay(actualpay);

				if (lastdegree != null && lastdegree != ""
						&& !" ".equals(lastdegree)) {
					double last = Double.parseDouble(lastdegree);
					lastdegree = mat1.format(last);
				}
				feesbeana.setLastdegree(lastdegree);

				if (thisdegree != null && thisdegree != ""
						&& !" ".equals(thisdegree)) {
					double last = Double.parseDouble(thisdegree);
					thisdegree = mat1.format(last);
				}
				feesbeana.setThisdegree(thisdegree);

				if (blhdl != null && blhdl != "" && !"".equals(blhdl)) {
					double last = Double.parseDouble(blhdl);
					blhdl = mat1.format(last);
				}
				feesbeana.setBlhdl(blhdl);

				if (actualpay != null && actualpay != ""
						&& !"".equals(actualpay)) {
					double last = Double.parseDouble(actualpay);
					actualpay = mat.format(last);
				}
				feesbeana.setActualpay(actualpay);

				if (pjjedf != null && pjjedf != "" && !"".equals(pjjedf)) {
					double last = Double.parseDouble(pjjedf);
					pjjedf = mat.format(last);
				}
				feesbeana.setPjjedf(pjjedf);

				feesbeana.setFloatdegree("0");

				feesbeana.setFloatpay("0");

				if (ydsb.equals("ybsb01")) {
					ydsb = "照明用电";
				} else if (ydsb.equals("ybsb02")) {
					ydsb = "空调用电";
				} else if (ydsb.equals("ybsb03")) {
					ydsb = "设备用电";
				} else if (ydsb.equals("ybsb04")) {
					ydsb = "其它用电设备";
				} else if (ydsb.equals("ybsb05")) {
					ydsb = "总表";
				} else if (ydsb.equals("ybsb06")) {
					ydsb = "综合";
				} else {
					ydsb = "";
				}
				if (property.equals("zdsx01")) {
					property = "局用机房";
				} else if (property.equals("zdsx02")) {
					property = "基站";
				} else if (property.equals("zdsx03")) {
					property = "营业网点";
				} else if (property.equals("zdsx04")) {
					property = "其他";
				} else if (property.equals("zdsx05")) {
					property = "接入网";
				} else if (property.equals("zdsx06")) {
					property = "乡镇支局";
				} else {
					property = "";
				}
				if (notetypeid.equals("pjlx05")) {
					notetypeid = "收据";
				} else if (notetypeid.equals("pjlx03")) {
					notetypeid = "发票";
				} else if(notetypeid.equals("pjlx06")){
					notetypeid = "增值税发票";
				}else {
					notetypeid = "";
				}
				if (dfzflx.equals("dfzflx01")) {
					dfzflx = "月结";
				} else if (dfzflx.equals("dfzflx02")) {
					dfzflx = "合同";
				} else if (dfzflx.equals("dfzflx03")) {
					dfzflx = "预支";
				} else if (dfzflx.equals("dfzflx04")) {
					dfzflx = "插卡";
				} else {
					dfzflx = "";
				}

				feesbeana.setYdsb(ydsb);
				feesbeana.setProperty(property);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setDfzflx(dfzflx);
				feesbeana.setManualauditstatus(manualauditstatus);
				feesbeana.setBzw(bzw1);
				
				ls.add(feesbeana);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * JDBCFactory.free(rs1, ps1, conn); JDBCFactory.free(rs2, ps2,
			 * conn);
			 */

			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException see) {
					see.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return ls;
	}
/*
 *update zhouxue 2014-05-15 查询语句添加默认条件站点非虚拟，非采集，站点人工审核通过；   票据类型判断添加‘增值税发票’
 * 
 */
	public synchronized List<ElectricFeesFormBean> daochu(String whereStr,// 根据过滤条件导出财务数据的方法
			String loginId, String str1, String str2) {
		DataBase db = new DataBase();
		String fzzdstr = getFuzeZdid(loginId);

		String sql1 = "SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY,"
				+ "D.DFZFLX,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "EF.NOTETYPEID,"
				+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.NOTENO,EF.ENTRYTIME,EF.MANUALAUDITSTATUS,"
				+ "(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) "
				+ " || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ,"
				+ "AD.LASTDEGREE,to_char(AD.LASTDATETIME,'yyyy-mm-dd'),AD.THISDEGREE,to_char(AD.THISDATETIME,'yyyy-mm-dd'),to_char(AD.STARTMONTH,'yyyy-mm'),to_char(AD.ENDMONTH,'yyyy-mm'),D.BEILV,AD.FLOATDEGREE,AD.BLHDL, EF.UNITPRICE, EF.FLOATPAY,EF.PJJE,EF.MANPASSMEMO "
				+ "FROM  financecheckdf_ddf EF,ZHANDIAN ZD,DIANBIAO D,financecheckdf_ddv AD " +
				  "WHERE ZD.ID=D.ZDID  AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=AD.AMMETERID_FK " +
				  " AND AD.AMMETERDEGREEID =EF.AMMETERDEGREEID_FK AND EF.CITYAUDIT='1' and EF.CITYZRAUDITSTATUS='1' " +
				  " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1'  "
				+ whereStr
				+ str1
				+ " "
				+ " AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))" + fzzdstr + ")";

		String sql2 = "SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY,"
				+ "D.DFZFLX,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "EF.NOTETYPEID,"
				+ "EF.UUID,EF.MONEY,EF.NOTENO,EF.ENTRYTIME,EF.FINANCEAUDIT,"
				+ "(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END)  "
				+ "|| (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ, "
				+ "EF.STARTDEGREE,EF.STOPDEGREE,to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),to_char(EF.STARTMONTH,'yyyy-mm'),to_char(EF.ENDMONTH,'yyyy-mm'),D.BEILV,EF.DIANLIANG,EF.MONEY,EF.DANJIA,EF.PJJE,EF.AUTOAUDITSTATUS,EF.MANPASSMEMO "
				+ "FROM  financecheck_yff EF,ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND EF.LIUCHENGHAO IS NOT NULL " +
				   " AND D.DBID=EF.PREPAYMENT_AMMETERID AND EF.CITYAUDIT='1' and EF.CITYZRAUDITSTATUS='1' " +
				   " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
				+ whereStr
				+ str2
				+ " "
				+ "AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx04' OR D.DFZFLX = 'dfzflx03' ) AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))" + fzzdstr + ")";

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		List<ElectricFeesFormBean> ls = new ArrayList<ElectricFeesFormBean>();

		try {
			db.connectDb();

			System.out.println("财务审核导出1："+sql1.toString());
			rs1 = db.queryAll(sql1);
			while (rs1.next()) {
				ElectricFeesFormBean feesbeana = new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs1.getString(1) != null ? rs1
						.getString(1) : "");
				feesbeana.setJzname(rs1.getString(2) != null ? rs1.getString(2)
						: "");
				feesbeana.setAccountmonth(rs1.getString(3) != null ? rs1
						.getString(3) : "");
				feesbeana.setDbid(rs1.getString(4) != null ? rs1.getString(4)
						: "");
				feesbeana.setDllx(rs1.getString(5) != null ? rs1.getString(5)
						: "");

				String ydsb = rs1.getString(6) != null ? rs1.getString(6) : "";

				String property = rs1.getString(7) != null ? rs1.getString(7)
						: "";

				String dfzflx = rs1.getString(8) != null ? rs1.getString(8)
						: "";

				feesbeana.setStationtype(rs1.getString(9) != null ? rs1
						.getString(9) : "");

				String notetypeid = rs1.getString(10) != null ? rs1
						.getString(10) : "";

				feesbeana.setElectricfeeId(rs1.getString(11) != null ? rs1
						.getString(11) : "");
				feesbeana.setDfuuid(rs1.getString(12) != null ? rs1
						.getString(12) : "");

				String actualpay = rs1.getString(13) != null ? rs1
						.getString(13) : "";

				String autoauditstatus = rs1.getString(14) != null ? rs1
						.getString(14) : "";
				feesbeana.setNoteno(rs1.getString(15) != null ? rs1
						.getString(15) : "");
				feesbeana.setEntrytime(rs1.getString(16) != null ? rs1
						.getString(16) : "");
				String manualauditstatus = rs1.getString(17) != null ? rs1
						.getString(17) : "";
				feesbeana.setSzdq(rs1.getString(18) != null ? rs1.getString(18)
						: "");

				String lastdegree = rs1.getString(19) != null ? rs1
						.getString(19) : "";

				feesbeana.setLastdatetime(rs1.getString(20) != null ? rs1
						.getString(20) : "");

				String thisdegree = rs1.getString(21) != null ? rs1
						.getString(21) : "";

				feesbeana.setThisdatetime(rs1.getString(22) != null ? rs1
						.getString(22) : "");
				feesbeana.setStartmonth(rs1.getString(23) != null ? rs1
						.getString(23) : "");
				feesbeana.setEndmonth(rs1.getString(24) != null ? rs1
						.getString(24) : "");
				feesbeana.setBeilv(rs1.getString(25) != null ? rs1
						.getString(25) : "");

				String floatdegree = rs1.getString(26) != null ? rs1
						.getString(26) : "";
				String blhdl = rs1.getString(27) != null ? rs1.getString(27)
						: "";

				feesbeana.setUnitprice(rs1.getString(28) != null ? rs1
						.getString(28) : "");

				String floatpay = rs1.getString(29) != null ? rs1.getString(29)
						: "";

				String pjjedf = rs1.getString(30) != null ? rs1.getString(30)
						: "";
				
				if("1".equals(autoauditstatus)){
					feesbeana.setRgshtgyy("");
				}else{
					String rgshtgyy = rs1.getString(31)!=null?rs1.getString(31):"";
					feesbeana.setRgshtgyy(rgshtgyy);
				}
				
				DecimalFormat mat = new DecimalFormat("0.00");
				DecimalFormat mat1 = new DecimalFormat("0.0");

				double df = 0.00;
				if (actualpay == null || actualpay == "") {
					actualpay = "0";
					df = Double.parseDouble(actualpay);
					actualpay = mat.format(df);
				}

				feesbeana.setActualpay(actualpay);

				if (lastdegree != null && lastdegree != ""
						&& !" ".equals(lastdegree)) {
					double last = Double.parseDouble(lastdegree);
					lastdegree = mat1.format(last);
				}
				feesbeana.setLastdegree(lastdegree);

				if (thisdegree != null && thisdegree != ""
						&& !" ".equals(thisdegree)) {
					double last = Double.parseDouble(thisdegree);
					thisdegree = mat1.format(last);
				}
				feesbeana.setThisdegree(thisdegree);

				if (floatdegree != null && floatdegree != ""
						&& !" ".equals(floatdegree)) {
					double last = Double.parseDouble(floatdegree);
					floatdegree = mat1.format(last);
				}
				feesbeana.setFloatdegree(floatdegree);

				if (floatpay != null && floatpay != "" && !" ".equals(floatpay)) {
					double last = Double.parseDouble(floatpay);
					floatpay = mat.format(last);
				}
				feesbeana.setFloatpay(floatpay);

				if (blhdl != null && blhdl != "" && !"".equals(blhdl)) {
					double last = Double.parseDouble(blhdl);
					blhdl = mat1.format(last);
				}
				feesbeana.setBlhdl(blhdl);

				if (actualpay != null && actualpay != ""
						&& !"".equals(actualpay)) {
					double last = Double.parseDouble(actualpay);
					actualpay = mat.format(last);
				}
				feesbeana.setActualpay(actualpay);

				if (pjjedf != null && pjjedf != "" && !"".equals(pjjedf)) {
					double last = Double.parseDouble(pjjedf);
					pjjedf = mat.format(last);
				}

				feesbeana.setPjjedf(pjjedf);

				if (ydsb.equals("ybsb01")) {
					ydsb = "照明用电";
				} else if (ydsb.equals("ybsb02")) {
					ydsb = "空调用电";
				} else if (ydsb.equals("ybsb03")) {
					ydsb = "设备用电";
				} else if (ydsb.equals("ybsb04")) {
					ydsb = "其它用电设备";
				} else if (ydsb.equals("ybsb05")) {
					ydsb = "总表";
				} else if (ydsb.equals("ybsb06")) {
					ydsb = "综合";
				} else {
					ydsb = "";
				}

				if (property.equals("zdsx01")) {
					property = "局用机房";
				} else if (property.equals("zdsx02")) {
					property = "基站";
				} else if (property.equals("zdsx03")) {
					property = "营业网点";
				} else if (property.equals("zdsx04")) {
					property = "其他";
				} else if (property.equals("zdsx05")) {
					property = "接入网";
				} else if (property.equals("zdsx06")) {
					property = "乡镇支局";
				} else {
					property = "";
				}

				if (notetypeid.equals("pjlx05")) {
					notetypeid = "收据";
				} else if (notetypeid.equals("pjlx03")) {
					notetypeid = "发票";
				} else if (notetypeid.equals("pjlx06")) {
					notetypeid = "增值税发票";
				}else {
					notetypeid = "";
				}
				if (dfzflx.equals("dfzflx01")) {
					dfzflx = "月结";
				} else if (dfzflx.equals("dfzflx02")) {
					dfzflx = "合同";
				} else if (dfzflx.equals("dfzflx03")) {
					dfzflx = "预支";
				} else if (dfzflx.equals("dfzflx04")) {
					dfzflx = "插卡";
				} else {
					dfzflx = "";
				}

				feesbeana.setYdsb(ydsb);
				feesbeana.setProperty(property);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setDfzflx(dfzflx);

				if (autoauditstatus != null && autoauditstatus.equals("1")) {
					feesbeana.setAutoauditstatus("通过");
				} else {
					feesbeana.setAutoauditstatus("未通过");
				}

				feesbeana.setManualauditstatus(manualauditstatus);

				ls.add(feesbeana);
			}

			System.out.println("财务审核导出2："+sql2.toString());

			rs2 = db.queryAll(sql2);
			while (rs2.next()) {
				ElectricFeesFormBean feesbeana = new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs2.getString(1) != null ? rs2
						.getString(1) : "");
				feesbeana.setJzname(rs2.getString(2) != null ? rs2.getString(2)
						: "");
				feesbeana.setAccountmonth(rs2.getString(3) != null ? rs2
						.getString(3) : "");
				feesbeana.setDbid(rs2.getString(4) != null ? rs2.getString(4)
						: "");
				feesbeana.setDllx(rs2.getString(5) != null ? rs2.getString(5)
						: "");

				String ydsb = rs2.getString(6) != null ? rs2.getString(6) : "";

				String property = rs2.getString(7) != null ? rs2.getString(7)
						: "";
				String dfzflx = rs2.getString(8) != null ? rs2.getString(8)
						: "";
				feesbeana.setStationtype(rs2.getString(9) != null ? rs2
						.getString(9) : "");
				String notetypeid = rs2.getString(10) != null ? rs2
						.getString(10) : "";
				feesbeana.setDfuuid(rs2.getString(11) != null ? rs2
						.getString(11) : "");
				feesbeana.setActualpay(rs2.getString(12) != null ? rs2
						.getString(12) : "");
				feesbeana.setNoteno(rs2.getString(13) != null ? rs2
						.getString(13) : "");
				feesbeana.setEntrytime(rs2.getString(14) != null ? rs2
						.getString(14) : "");
				String manualauditstatus = rs2.getString(15) != null ? rs2
						.getString(15) : "";
				feesbeana.setSzdq(rs2.getString(16) != null ? rs2.getString(16)
						: "");

				String lastdegree = rs2.getString(17) != null ? rs2
						.getString(17) : "";
				String thisdegree = rs2.getString(18) != null ? rs2
						.getString(18) : "";

				feesbeana.setLastdatetime(rs2.getString(19) != null ? rs2
						.getString(19) : "");
				feesbeana.setThisdatetime(rs2.getString(20) != null ? rs2
						.getString(20) : "");
				feesbeana.setStartmonth(rs2.getString(21) != null ? rs2
						.getString(21) : "");
				feesbeana.setEndmonth(rs2.getString(22) != null ? rs2
						.getString(22) : "");
				feesbeana.setBeilv(rs2.getString(23) != null ? rs2
						.getString(23) : "");

				String blhdl = rs2.getString(24) != null ? rs2.getString(24)
						: "";

				String actualpay = rs2.getString(25) != null ? rs2
						.getString(25) : "";

				feesbeana.setUnitprice(rs2.getString(26) != null ? rs2
						.getString(26) : "");
				String pjjedf = rs2.getString(27) != null ? rs2.getString(27)
						: "";

				String autoauditstatus = rs2.getString(28)!=null?rs2.getString(28):"";
				
				if("1".equals(autoauditstatus)){
					feesbeana.setRgshtgyy("");
				}else{
					String rgshtgyy = rs2.getString(29)!=null?rs2.getString(29):"";
					feesbeana.setRgshtgyy(rgshtgyy);
				}

				DecimalFormat mat = new DecimalFormat("0.00");
				DecimalFormat mat1 = new DecimalFormat("0.0");

				double df = 0.00;
				if (actualpay == null || actualpay == "") {
					actualpay = "0";
					df = Double.parseDouble(actualpay);
					actualpay = mat.format(df);
				}
				feesbeana.setActualpay(actualpay);

				if (lastdegree != null && lastdegree != ""
						&& !" ".equals(lastdegree)) {
					double last = Double.parseDouble(lastdegree);
					lastdegree = mat1.format(last);
				}
				feesbeana.setLastdegree(lastdegree);

				if (thisdegree != null && thisdegree != ""
						&& !" ".equals(thisdegree)) {
					double last = Double.parseDouble(thisdegree);
					thisdegree = mat1.format(last);
				}
				feesbeana.setThisdegree(thisdegree);

				if (blhdl != null && blhdl != "" && !"".equals(blhdl)) {
					double last = Double.parseDouble(blhdl);
					blhdl = mat1.format(last);
				}
				feesbeana.setBlhdl(blhdl);

				if (actualpay != null && actualpay != ""
						&& !"".equals(actualpay)) {
					double last = Double.parseDouble(actualpay);
					actualpay = mat.format(last);
				}
				feesbeana.setActualpay(actualpay);

				if (pjjedf != null && pjjedf != "" && !"".equals(pjjedf)) {
					double last = Double.parseDouble(pjjedf);
					pjjedf = mat.format(last);
				}
				feesbeana.setPjjedf(pjjedf);

				feesbeana.setFloatdegree("0");

				feesbeana.setFloatpay("0");

				if (ydsb.equals("ybsb01")) {
					ydsb = "照明用电";
				} else if (ydsb.equals("ybsb02")) {
					ydsb = "空调用电";
				} else if (ydsb.equals("ybsb03")) {
					ydsb = "设备用电";
				} else if (ydsb.equals("ybsb04")) {
					ydsb = "其它用电设备";
				} else if (ydsb.equals("ybsb05")) {
					ydsb = "总表";
				} else if (ydsb.equals("ybsb06")) {
					ydsb = "综合";
				} else {
					ydsb = "";
				}
				if (property.equals("zdsx01")) {
					property = "局用机房";
				} else if (property.equals("zdsx02")) {
					property = "基站";
				} else if (property.equals("zdsx03")) {
					property = "营业网点";
				} else if (property.equals("zdsx04")) {
					property = "其他";
				} else if (property.equals("zdsx05")) {
					property = "接入网";
				} else if (property.equals("zdsx06")) {
					property = "乡镇支局";
				} else {
					property = "";
				}
				if (notetypeid.equals("pjlx05")) {
					notetypeid = "收据";
				} else if (notetypeid.equals("pjlx03")) {
					notetypeid = "发票";
				} else if (notetypeid.equals("pjlx06")) {
					notetypeid = "增值税发票";
				}else {
					notetypeid = "";
				}
				if (dfzflx.equals("dfzflx01")) {
					dfzflx = "月结";
				} else if (dfzflx.equals("dfzflx02")) {
					dfzflx = "合同";
				} else if (dfzflx.equals("dfzflx03")) {
					dfzflx = "预支";
				} else if (dfzflx.equals("dfzflx04")) {
					dfzflx = "插卡";
				} else {
					dfzflx = "";
				}

				feesbeana.setYdsb(ydsb);
				feesbeana.setProperty(property);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setDfzflx(dfzflx);
				feesbeana.setAutoauditstatus("未通过");
				feesbeana.setManualauditstatus(manualauditstatus);

				ls.add(feesbeana);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * JDBCFactory.free(rs1, ps1, conn); JDBCFactory.free(rs2, ps2,
			 * conn);
			 */

			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException see) {
					see.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return ls;
	}

	private synchronized String getFuzeZdid(String loginid) {/* 查询负责站点 */
		DataBase db = new DataBase();
		ResultSet rs = null;
		/*
		 * if(db==null){ db = DataJdbc.getInstance(); }
		 */

		/*
		 * Connection conn = null; PreparedStatement ps = null;
		 */
		String cxtj = new String("");

		String sql = "select begincode,endcode from per_zhandian where accountid='"
				+ loginid
				+ "' and begincode is not null and endcode is not null";

		try {
			db.connectDb();

			// conn =JDBCFactory.getConnection();
			// ps = conn.prepareStatement(sql);
			rs = db.queryAll(sql);
			while (rs.next()) {
				cxtj = cxtj + " or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// JDBCFactory.free(rs, ps, conn);

			if (rs != null) {
				try {
					rs.close();
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

		// rs = db.queryAll();

		return cxtj.toString();
	}

}
