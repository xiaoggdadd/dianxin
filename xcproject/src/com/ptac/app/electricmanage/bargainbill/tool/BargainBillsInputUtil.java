package com.ptac.app.electricmanage.bargainbill.tool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillCountBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSaveBean;
import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.dao.Imp.BargainBillMethodsDAOImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.inportuserzibaodian.util.Format;

/**
 * 合同单导入
 * 
 * @author rock
 * 
 */
public class BargainBillsInputUtil {
	String zdid;
	String dbid;
	String accountid, accountName;
	String money, thismonth, endmonth, accountmonth, ydl, pjsj, kpsj, pjlx,htbh,danjiaa,edhdla,qsdbdla,
			pjbh, pjje, bz,beilv,scb,property,zlfh,jlfh,shicode,dfzflx,stationtype,gdfs;
	String actualpay1 = "", actualpay2 = "", actualpay3 = "", actualpay4 = "",
			actualpay5 = "", actualpay6 = "";
	boolean zbdyhhBol = false;// 是否有自报电用户号

	/**
	 * 合同单 导入的 检查 excel的数据正确性检查和 电费单导入重复性检查
	 */
	public BargainBillsInputUtil() {

	}

	/**
	 * @param content
	 *            单行数据
	 * @param wrong
	 *            错误数据
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		accountid = account.getAccountId();
		accountName = account.getAccountName();
		Vector<String> row = new Vector<String>();

		// 必填项检查
		row = this.check01(content);
		if (row.isEmpty()) {
			row = this.getZdId(content);
		}
		if (row.isEmpty()) {
			row = this.check02(content);
		}
		if (row.isEmpty()) {
			row = this.getDbId(content);
		}
		if (row.isEmpty()) {
			row = this.checkq(content);
		}
		if (row.isEmpty()) {
			row = this.checke(content);
		}
		if (row.isEmpty()) {
			row = this.checkzl(content);
		}
		if (row.isEmpty()) {
			row = this.checkjl(content);
		}
		if (row.isEmpty()) {
			row = this.checkscb(content);
		}
		if (row.isEmpty()) {
			row = this.checkpro(content);
		}
		if (row.isEmpty()) {
			row = this.check03(content);
		}
		if (row.isEmpty()) {
			row = this.beginTime(content);
		}
		if (row.isEmpty()) {
			row = this.endTime(content);
		}
		if (row.isEmpty()) {
			row = this.timeComp(content);
		}
		if (row.isEmpty()) {
			row = this.getAccountMonth(content);
		}
		if (row.isEmpty()) {
			row = this.check06(content);
		}
		if (row.isEmpty()) {
			row = this.check11(content);
		}
		if (row.isEmpty()) {
			row = this.check12(content);
		}

		if (row.isEmpty()) {
			row = this.pjlx_check(content);
		}

		if (row.isEmpty()) {
			row = this.getPjje(content);
		}
		if (row.isEmpty()) {
			row = this.checkhtbh(content);
		}
		if (row.isEmpty()) {
			row = this.compNowEleAndMoney(content);
		}
		if (row.isEmpty()) {
			row = this.boolenSameInfo(content);
		}
		if(row.isEmpty()){
		this.findFentan();
		}
		// 前期工作完成开始添加电费

		if (row.isEmpty()) {
			row = this.addEle(content);
		}
		return row;

	}

	/**
	 * 站点ID不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check01(Object[] content) {
		Vector<String> row = new Vector<String>();
		zdid = content[4].toString().trim();
		if (content[4] == null || "".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "站点ID"
					+ zdid + "站点ID为空");
		}
		return row;
	}

	/**
	 * 查询有无此站点
	 * 
	 * @author rock
	 * @param b
	 * @return
	 */
	synchronized Vector<String> getZdId(Object[] b) {
		Vector<String> row = new Vector<String>();

		String sql2 = " select zd.id from dianbiao d, zhandian zd "
				+ " where zd.id=d.zdid and zd.qyzt = '1' "
				+ "  and d.dfzflx = 'dfzflx02' "
				+ "AND zd.id='"
				+ zdid
				+ "' AND (ZD.XIAOQU IN(SELECT t.AGCODE FROM PER_AREA t WHERE t.ACCOUNTID = '"
				+ accountid + "'))";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			String idd = "";
			while (rs.next()) {
				idd = rs.getString(1);
			}
			if ("".equals(idd) || null == idd) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("未查到" + b[0].toString() + b[2].toString() + "站点ID"
						+ b[4].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			row.add("未查到" + b[0].toString() + b[2].toString() + "站点ID"
					+ b[4].toString().trim());
		} finally {
			db.free(rs, ps, conn);
		}

		return row;
	}

	/**
	 * 电表ID不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check02(Object[] content) {
		Vector<String> row = new Vector<String>();
		dbid = content[5].toString().trim();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "站点ID"
					+ zdid + "电表ID为空");
		}
		return row;
	}

	/**
	 * 电表ID查询
	 * 
	 * @author rock
	 * @param b
	 * @return
	 */
	synchronized Vector<String> getDbId(Object[] b) {
		Vector<String> row = new Vector<String>();

		String sql1 = " select d.dbid,d.beilv,zd.property,zd.zlfh,zd.jlfh,zd.scb,zd.shi shicode,d.dfzflx,zd.stationtype,zd.gdfs,zd.qsdbdl,zd.edhdl,d.danjia " 
				+ " from dianbiao d, zhandian zd "
				+ " where zd.id=d.zdid  and zd.qyzt = '1' "
				+ "  and d.dfzflx = 'dfzflx02' "
				+ "AND D.DBID='"
				+ dbid
				+ "' AND (ZD.XIAOQU IN(SELECT t.AGCODE FROM PER_AREA t WHERE t.ACCOUNTID = '"
				+ accountid + "'))";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			String idd = "";
			while (rs.next()) {
				idd = rs.getString(1);
				 beilv = rs.getString("beilv");
			     shicode = rs.getString("shicode");
			     property = rs.getString("property");
			     scb = rs.getString("scb");
			     zlfh = rs.getString("zlfh");
			     jlfh = rs.getString("jlfh");
			     dfzflx = rs.getString("dfzflx");
			     stationtype = rs.getString("stationtype");
			     gdfs = rs.getString("gdfs");
			     edhdla = rs.getString("edhdl")==null?"0":rs.getString("edhdl");
			     qsdbdla = rs.getString("qsdbdl")==null?"0":rs.getString("qsdbdl");
			     danjiaa = rs.getString("danjia")==null?"0":rs.getString("danjia");
			}
			if ("".equals(idd) || null == idd) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("未查到" + b[0].toString() + b[2].toString() + "站点ID"
						+ b[4].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			row.add("未查到" + b[0].toString() + b[2].toString() + "站点ID"
					+ b[4].toString().trim());
		} finally {
			db.free(rs, ps, conn);
		}

		return row;
	}
	/**
	 * 全省定标电量 不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkq(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(qsdbdla)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中全省定标电量为空！");
		} 
		return row;
	}
	/**
	 * 额定耗电量不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checke(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(edhdla)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中额定耗电量为空！");
		} 
		return row;
	}
	/**
	 * 直流负荷不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkzl(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(zlfh)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中直流负荷为空！");
		} 
		return row;
	}
	/**
	 * 交流负荷不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkjl(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(jlfh)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中交流负荷为空！");
		} 
		return row;
	}
	/**
	 * 生产标不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkscb(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(scb)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中生产标为空！");
		} 
		return row;
	}
	/**
	 * 站点属性不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkpro(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(property) || "null".equals(property)||null==property) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "系统中站点属性为空！");
		} 
		return row;
	}
	/**
	 * 金额 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check03(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[6] == null || "".equals(content[6].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "金额为空");
		} else {
			if (!Format.isNumber(content[6].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "金额格式不正确");
			} else {
				money = content[6].toString().trim();
			}

		}
		return row;
	}

	/**
	 * 起始年月 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> beginTime(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "起始年月为空");
		} else {

			if (!Format.isMonth(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "起始月份 格式不正确");
			} else {
				if (Format.isMonth02(content[7].toString().trim())) {
					thismonth = Format.getMonth(content[7].toString().trim());
				} else {
					thismonth = content[7].toString().trim();
				}
			}
		}

		return row;
	}

	/**
	 * 结束年月 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> endTime(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "结束月份为空");
		} else {
			if (!Format.isMonth(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "结束月份 格式不正确");
			} else {
				if (Format.isMonth02(content[8].toString().trim())) {
					endmonth = Format.getMonth(content[8].toString().trim());
				} else {
					endmonth = content[8].toString().trim();
				}

			}
		}
		return row;
	}

	/**
	 * 起始月份和结束月份的大小的问题
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> timeComp(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[8] != null && !"".equals(content[8].toString().trim())
				&& content[7] != null
				&& !"".equals(content[7].toString().trim())
				&& Format.isMonth(content[7].toString().trim())
				&& Format.isMonth(content[8].toString().trim())) {
			Date startmonth = Format.String2Month(content[7].toString().trim());
			Date endmonth = Format.String2Month(content[8].toString().trim());
			if (startmonth.getTime() > endmonth.getTime()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "起始月份大于结束月份");
			}
		}
		return row;
	}

	/**
	 * 报账月份 不为空、格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> getAccountMonth(Object[] content) {
		Vector<String> row = new Vector<String>();

		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "报账月份 为空");
		} else {
			if (!Format.isMonth(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "报账月份 格式不正确");
			} else {
				if (Format.isMonth02(content[9].toString().trim())) {
					accountmonth = Format
							.getMonth(content[9].toString().trim());
				} else {
					accountmonth = content[9].toString().trim();
				}

			}

		}
		return row;
	}

	/**
	 * 用电量 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check06(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[10] == null || "".equals(content[10].toString().trim())) {
			ydl = "0";
		} else {
			if (!Format.isNumber(content[10].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "用电量 格式不正确");
			} else {
				ydl = content[10].toString().trim();
			}
		}
		return row;
	}

	/**
	 * 票据时间 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check11(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (null == content[11] || "".equals(content[11])) {
			pjsj = "";
		} else {
			if (!Format.isTime(content[11].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "票据时间时间   格式不正确");
			} else {
				if (Format.isTime02(content[11].toString().trim())) {
					pjsj = Format.getTime(content[11].toString().trim());
				} else {
					pjsj = content[11].toString().trim();
				}
				if (pjsj == null || "".equals(pjsj) || "null".equals(pjsj)) {
					pjsj = "";
				}
			}
		}

		return row;
	}

	/**
	 * 开票时间的格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (null == content[12] || "".equals(content[12])) {
			kpsj = "";
		} else {
			if (content[12] != null && !"".equals(content[12])) {
				if (!Format.isTime(content[12].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "合同单" + zdid + "开票时间格式不正确");
				} else {
					if (Format.isTime02(content[12].toString().trim())) {
						kpsj = Format.getTime(content[12].toString().trim());
					} else {
						kpsj = content[12].toString().trim();
					}

				}
			}
		}
		return row;
	}

	/**
	 * 票据类型不为空
	 * 
	 * @author rock
	 * @param content
	 * @return
	 */
	synchronized Vector<String> pjlx_check(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[13] == null || "".equals(content[13].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "票据类型不能为空");
		}else{
			String sql = "SELECT I.CODE FROM INDEXS I WHERE I.NAME= ? AND I.TYPE='PJLX'";
			DataBase ds = new DataBase();
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ds.connectDb();
				conn = ds.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, content[13].toString().trim());
				rs = ps.executeQuery();
				ds.commit();
				while(rs.next()){
					pjlx = rs.getString(1);
				}
				if(pjlx==null||"".equals(pjlx)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "合同单"
							+ zdid + "票据类型不正确！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "票据类型不正确！");
			}finally{
				ds.free(rs, ps, conn);
			}
            
		}
		return row;
	}

	/**
	 * 票据金额 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> getPjje(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[15] == null || "".equals(content[15].toString().trim())) {
			pjje = "0";
		} else {
			if (!Format.isNumber(content[15].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "合同单"
						+ zdid + "票据金额格式不正确");
			} else {
				pjje = content[15].toString().trim();
			}

		}
		return row;
	}
	

	/**
	 * 合同编号
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkhtbh(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[17] == null || "".equals(content[17].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "合同编号为空");
		} else {
			htbh=content[17].toString().trim();

		}
		return row;
	}

	/**
	 * 数据没问题时候的检查，
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> compNowEleAndMoney(Object[] content) {
		Vector<String> row = new Vector<String>();

		// 数据处理没有问题，针对Excel 的调查

		pjlx = content[13].toString().trim();
		pjbh = content[14].toString().trim();
		bz = content[16].toString().trim();
		if (pjlx == null || "".equals(pjlx) || "null".equals(pjlx)) {
			pjlx = "";
		}
		if (pjbh == null || "".equals(pjbh) || "null".equals(pjbh)) {
			pjbh = "";
		}
		if (bz == null || "".equals(bz) || "null".equals(bz)) {
			bz = "";
		}
		if ("发票".equals(pjlx)) {
			pjlx = "pjlx03";
		}
		if ("收据".equals(pjlx)) {
			pjlx = "pjlx05";
		}
		
		if ("增值税发票".equals(pjlx)) {
			pjlx = "pjlx06";
		}

		return row;
	}

	/**
	 * 判断数据库中是否有这条数据(重复)， 数据库中有重复数据返回true，否则返回false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> boolenSameInfo(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool tool = new ElectricTool();
		boolean temp = true;
		temp = tool.checkRepeat2(dbid, accountmonth, thismonth, endmonth);
		if (temp) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "合同单重复，请检查！！！");
		}
		return row;

	}

	/**
	 * 查询 电表的分摊比例 ，针对是否分摊进行检查， 如果没有问题 开始本电费，电量的 分摊计算和赋值
	 * 
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	synchronized void findFentan() {
		AmmeterDegreeFormBean beanm = new AmmeterDegreeFormBean();
		ArrayList list = new ArrayList();
		list = beanm.getStationMhchexkt(dbid);
		String shuoshuzhuanye = "";
		String dbili = "", dbili1 = "", dbili2 = "", dbili3 = "", dbili4 = "", dbili5 = "", dbili6 = "";

		double wucha = 0.0000001;
		DecimalFormat df = new DecimalFormat("#.00");
		if (list != null) {
			int fycount = ((Integer) list.get(0)).intValue();
			for (int k = fycount; k < list.size() - 1; k += fycount) {
				shuoshuzhuanye = (String) list.get(k
						+ list.indexOf("SHUOSHUZHUANYE"));
				shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
				dbili = (String) list.get(k + list.indexOf("DBILI"));
				dbili = dbili != null ? dbili : "0";
				if (shuoshuzhuanye.equals("zylx01")) {
					dbili1 = dbili;
				}
				if (shuoshuzhuanye.equals("zylx02")) {
					dbili2 = dbili;
				}
				if (shuoshuzhuanye.equals("zylx03")) {
					dbili3 = dbili;
				}
				if (shuoshuzhuanye.equals("zylx04")) {
					dbili4 = dbili;
				}
				if (shuoshuzhuanye.equals("zylx05")) {
					dbili5 = dbili;
				}
				if (shuoshuzhuanye.equals("zylx06")) {
					dbili6 = dbili;
				}
			}
			if (dbili1 == null || dbili1 == "") {
				dbili1 = "0.00";
			}
			if (dbili2 == null || dbili2 == "") {
				dbili2 = "0.00";
			}
			if (dbili3 == null || dbili3 == "") {
				dbili3 = "0.00";
			}
			if (dbili4 == null || dbili4 == "") {
				dbili4 = "0.00";
			}
			if (dbili5 == null || dbili5 == "") {
				dbili5 = "0.00";
			}
			if (dbili6 == null || dbili6 == "") {
				dbili6 = "0.00";
			}
		}

		// 分摊电费(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
		if (!"".equals(money) || money != null || money != "0"
				|| !"o".equals(money)) {
			// 生产
			actualpay1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// 营业
			actualpay2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// 办公
			actualpay3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// 信息化
			actualpay4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// 建设投资
			actualpay5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// 代垫电费
			actualpay6 = df.format((new Double(dbili6).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
		} else {
			actualpay1 = "0.00";
			actualpay2 = "0.00";
			actualpay3 = "0.00";
			actualpay4 = "0.00";
			actualpay5 = "0.00";
			actualpay6 = "0.00";
		}
	}

	/**
	 * 开始添加 相应的电费
	 * 
	 * @author gt_web
	 * @param content
	 * @return
	 */
	synchronized Vector<String> addEle(Object[] content) {
		BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();

		BargainBillSaveBean bean = new BargainBillSaveBean();
		bean.setMoney(money);
		bean.setNotetypeid(pjlx);
		bean.setNotetime(pjsj);
		bean.setStartmonth(thismonth);
		bean.setEndmonth(endmonth);
		bean.setAccountmonth(accountmonth);
		bean.setDianliang(ydl);
		bean.setMemo(bz);
		bean.setPrepayment_ammeterid(dbid);
		bean.setStationid(zdid);
		bean.setKptime(kpsj);
		bean.setEntrypensonnel(accountName);
		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bean.setEntrytime(mat.format(new Date()));
		bean.setPjje(Double.parseDouble(pjje));

		// 分摊电费
		// 生产
		bean.setNetworkdf(actualpay1);
		// 营业
		bean.setMarketdf(actualpay2);
		// 办公
		bean.setAdministrativedf(actualpay3);
		// 信息化
		bean.setInformatizationdf(actualpay4);
		// 建设投资
		bean.setBuilddf(actualpay5);
		// 代垫电费
		bean.setDddf(actualpay6);

		/* 存储各级判断 */
		// 实例化工具类
		ElectricTool et = new ElectricTool();

		// 因为不做判断，所以默认通过

		String manualauditname = "";// 人工审核人
		String manualauditstatus = "0";// 人工审核状态
		String manualauditdatetime = null;// 人工审核时间

		boolean ycgedf = false;// 异常高额电费
		boolean sdb = false;// 大于省定标
		boolean edhdl = false;// 小于额定耗电量
		boolean zhandian = false;// 1.2万个点

		// 判断异常及高额电费
		
		//将日期进行转换：
		GetZQ gz = new GetZQ();
		String qssj = gz.getBeginTime(thismonth);
		String jssj = gz.getEndTime(endmonth);
		
		bean.setStartdate(qssj);
		bean.setEnddate(jssj);
		
	//	BargainBillCountBean info = dao.getCount(dbid);
		
		/*存储单价*/
		bean.setDanjia(danjiaa);
		
		if("0".equals(ydl)){
			
			try{
				Double je = new Double(money);
				Double dj = new Double(danjiaa);
				
				double usedl = je/dj;
				BigDecimal bg_yjs = new BigDecimal(usedl);
		        ydl = bg_yjs.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				
			}catch(Exception e){
				ydl = "0";
			}
		}
		 if(Format.str_d(beilv)==0){
        	 beilv = "1";
         }
         String dbydl = ydl;//电表用电量 = (本次电表读数-上次电表读数)*倍率
         bean.setDbydl(dbydl);
         bean.setBeilv(beilv);
         bean.setScb(scb);
		String[] cbbl = et.getQsdbdlCbbl(dbydl, jssj, qssj, shicode, property, zlfh, jlfh, edhdla, scb, dbid,ydl,qsdbdla,stationtype);//2014-10-28超省标比例,合并周期,标准值
		// 判断额定耗电量和省定标
		String ed[] = et.checkBcdl(ydl, jssj, qssj,edhdla, dbid, "", "2");// 额定耗电量
		String qs[] = et.checkBcdl2(cbbl[0]);
		String eah[] = et.checkExceptAndHigh(dbid, money, ydl, jssj, qssj, String.valueOf(Format.str_d(cbbl[0])/100));
		bean.setHbzq(cbbl[1]);
		bean.setBzz(cbbl[2]);
		
		if ("1".equals(eah[0])) {
			ycgedf = false;
		} else if ("0".equals(eah[0])) {
			ycgedf = true;
		}

		String qxsh = "0";
		if (ycgedf == false) {
			qxsh = "0";
		} else {
			qxsh = "1";
		}

		String countyauditstatus = qxsh;// 区县主人审核状态标志位
		String countyauditname = "";// 区县主任审核人
		String countyaudittime = "";// 区县主任审核时间

		String cSheng = cbbl[0];
		String cCity = ed[3];
		
		if ("0".equals(ed[2])) {
			edhdl = false;
		} else if ("1".equals(ed[2])) {
			edhdl = true;
		}
		if ("0".equals(qs[0])) {
			sdb = false;
		} else if ("1".equals(qs[0])) {
			sdb = true;
		}

		// 判断1.2万个点
		String onePointTwo[] = et.checckSite(dbid);
		if ("1".equals(onePointTwo[0])) {
			zhandian = false;
		} else if ("0".equals(onePointTwo[0])) {
			zhandian = true;
		}

		String szrBzw = "0";
		if (ycgedf == false || zhandian == false) {
			szrBzw = "0";
		} else {
			szrBzw = "1";
		}

		String cityzrauditstatus = szrBzw;// 市主任审核状态标志位
		String cityzrauditname = "";// 市主任审核人
		String cityzraudittime = "";// 市主任审核时间

		String cityAudit = "0";
		if (ycgedf == false || edhdl == false || sdb == false
				|| zhandian == false) {
			cityAudit = "0";
		} else {
			cityAudit = "1";
		}
		
		StringBuffer text = new StringBuffer();//不通过原因描述
		text.append(eah[1]);//添加不通过信息
		text.append(" "+ed[1]);
		text.append(" "+qs[1]);
		text.append(" "+onePointTwo[1]);
		
		/*自动审核状态和描述*/
		String autoauditstatus = cityAudit;// 自动审核状态
		String autoauditdescripthion = text.toString();// 自动审核描述

		bean.setCITYAUDIT(cityAudit);
		bean.setZlfh(zlfh);
		bean.setJlfh(jlfh);
		bean.setPropertycode(property);
		bean.setStationtypecode(stationtype);
		bean.setDfzflxcode(dfzflx);
		bean.setGdfscode(gdfs);
		
		Long zq = et.getQuot(qssj, jssj);

		String jszq =zq.toString();
		bean.setJszq(jszq);

		bean.setDianliang(ydl);
		bean.setAutoauditstatus(autoauditstatus);
		bean.setAutoauditdescripthion(autoauditdescripthion);
		bean.setManualauditstatus(manualauditstatus);
		bean.setManualauditname(manualauditname);
		bean.setManualauditdatetime(manualauditdatetime);
		bean.setCountyauditname(countyauditname);
		bean.setCountyauditstatus(countyauditstatus);
		bean.setCountyaudittime(countyaudittime);
		bean.setCityzrauditname(cityzrauditname);
		bean.setCityzrauditstatus(cityzrauditstatus);
		bean.setCityzraudittime(cityzraudittime);
		bean.setFlag(cityAudit);
		bean.setCountyflag(countyauditstatus);
		bean.setCityflag(cityzrauditstatus);
		bean.setQsdbdl(qsdbdla);
		bean.setEdhdl(edhdla);
		bean.setHtbianhao(htbh);
		//计算超省表市标
		
		
		bean.setCsdb(cSheng);
		bean.setDedhdl(cCity);
		

		Vector<String> row = new Vector<String>();
		
		String bzw = "0";
		String a = dao.saveXls(bean, bzw, thismonth, endmonth);
		if (!"1".equals(a)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "合同单"
					+ zdid + "Error！");
		}

		return row;
	}

}
