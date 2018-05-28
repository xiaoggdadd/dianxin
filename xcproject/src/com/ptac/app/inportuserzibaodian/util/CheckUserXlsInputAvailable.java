package com.ptac.app.inportuserzibaodian.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.bargainbill.bean.ZhurenPanduanBean;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.inportuserzibaodian.dao.ElecBillZbdDao;
import com.ptac.app.inportuserzibaodian.dao.ElecBillZbdDaoImp;

/**
 * 
 * @author rock 电费单数据的增加，导入完成型检查
 * 
 */
public class CheckUserXlsInputAvailable {
	String accountid,accountName;
	String dianbiaoid, zbdyhh, dfauto;String dfmess = "";
	String dianfei, beilv, edhdl, dbid;String dlmess = "";
	double thisdegree, lastdegree, actualdegree;
	String  bzmonth, thistime;
	String blhdl = "0";
	String enterperson, dlmemo, dfmemo, pjlx, payperson, qsdbdl,htendtime,
			kptime, paytime,property,zlfh,jlfh,scb,dfzflx,stationtype,gdfs;
	String lastdatetime="";
	String dlauto = "0";
	String cbsj, kpsj;
	double actualdianfei;

	String zy1, zy2, zy3, zy4, zy5, linelosstype, linelossvalue, cssytime;
	String blhdl1, blhdl2, blhdl3, blhdl4, blhdl5,blhdl6;
	String actualpay, actualpay1, actualpay2, actualpay3, actualpay4,
			actualpay5,actualpay6;
	boolean zbdyhhBol = false;// 是否有自报电用户号
	String scdf="",scdl="",lastunitprice="";//上次电费，上次电量，上次单价
	String pjje;//票据金额

	/**
	 * 电费单 导入的 检查 excel的数据正确性检查和 电费单导入重复性检查
	 */
	public CheckUserXlsInputAvailable() {

	}

	/**
	 * @param content
	 *            单行数据
	 * @param wrong
	 *            错误数据
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account, String biaozhi) {
		accountid = account.getAccountId();
		accountName = account.getAccountName();
		Vector<String> row = new Vector<String>();

		// 必填项检查
		row = this.check01(content, account);
		if (row.isEmpty()) {
			row = this.check02(content);
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
			row = this.checkOutAndConnect(content);
		}
		if (row.isEmpty()) {
			row = this.check03(content);
		}
		if (row.isEmpty()) {
			row = this.check04(content);
		}
		if (row.isEmpty()) {
			row = this.check05(content);
		}
		if (row.isEmpty()) {
			row = this.checkhtendtime(content);
		}
		if (row.isEmpty()) {
			row = this.check06(content);
		}
//		if (row.isEmpty()) {
//			row = this.check07(content);
//		}
		if (row.isEmpty()) {
			row = this.check08(content);
		}
		if (row.isEmpty()) {
			row = this.check09(content);
		}
//		if (row.isEmpty()) {
//			row = this.check10(content);
//		}
//		if (row.isEmpty()) {
//			row = this.check11(content);
//		}
		if (row.isEmpty()) {
			row = this.check12(content);
		}
		if (row.isEmpty()) {
			row = this.check15(content);
		}
		// / 时间的比较

//		if (row.isEmpty()) {
//			row = this.check13(content);
//		}
//1 有上次抄表时间
//2 没有上次抄表时间 前台的标志位都改为2，到后台来之后都从后台查询
		if (row.isEmpty()) {
			if ("1".equals(biaozhi)) {
//				row = beginTime(content);// 上次抄表时间
				row = check14(content);
			} else if ("2".equals(biaozhi)) {
				this.noLastMonth(content);
			}
		}

		// 有无上次抄表时间

		if (row.isEmpty()) {
			row = this.writeMonth(content);
		}

//		if (row.isEmpty()) {
//			row = this.timeComp(content);
//		}

		// 开始分摊

		this.findFentan();

		// 开始导入前的检查
		if (row.isEmpty()) {
			row = this.compNowEleAndMoney(content);
		}
		if (row.isEmpty()) {
			row = this.boolenSameInfo(content);
		}

		// 前期工作完成开始添加电费

		if (row.isEmpty()) {
			row = this.addEle(content);
		}
		return row;

	}

	/**
	 * 电表id check
	 * 
	 * @param wrong
	 * @return 增加单行 错误
	 * 
	 */
	synchronized Vector<String> check01(Object[] content, Account account) {

		DataBase ds = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String id = "";
		zbdyhh = content[3].toString().trim();

		Vector<String> row = new Vector<String>();
		String sql = "SELECT D.id, D.DANJIA, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,"//Z.dianfei 改为 d.danjia单价
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI else 0 END))AS ZY1,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI else 0 END))AS ZY2,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI else 0 END))AS ZY3,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI else 0 END))AS ZY4,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI else 0 END))AS ZY5,  "
				+ "d.dbid,TO_CHAR(d.CSSYTIME,'yyyy-mm-dd') CSSYTIME,Z.qsdbdl,D.DBID,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,D.DFZFLX,Z.GDFS,Z.STATIONTYPE"
				+ " FROM ZHANDIAN Z, DIANBIAO D,SBGL S  WHERE Z.ID = D.ZDID   AND D.DBYT = 'dbyt01' AND D.DBQYZT='1' AND Z.QYZT='1' "
				+ "  AND S.DIANBIAOID(+)=D.DBID AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?)"
				+ " GROUP BY D.ID,D.DANJIA, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,d.dbid,d.CSSYTIME,z.qsdbdl,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,D.DFZFLX,Z.GDFS,Z.STATIONTYPE";//Z.dianfei 改为 d.danjia单价
		try {
			conn = ds.getConnection();
		} catch (DbException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(sql.toString());
			System.out.println(content[3]);

			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, zbdyhh);
			ps.setString(2, accountid);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);
				dianfei = rs.getString(2);
				beilv = rs.getString(3);
				linelosstype = rs.getString(4);
				linelossvalue = rs.getString(5);
				edhdl = rs.getString(6);
				zy1 = rs.getString(7);
				zy2 = rs.getString(8);
				zy3 = rs.getString(9);
				zy4 = rs.getString(10);
				zy5 = rs.getString(11);
				dbid = rs.getString(12);
				cssytime = rs.getString(13);
				qsdbdl = rs.getString(14);
				dianbiaoid = rs.getString(15);
				property = rs.getString(16);
				zlfh = rs.getString(17);
				jlfh = rs.getString(18);
				scb = rs.getString(19);
				dfzflx = rs.getString(20);
				gdfs = rs.getString(21);
				stationtype = rs.getString(22);
				
			}
			ElecBillZbdDao dao = new ElecBillZbdDaoImp();
			if("dfzflx02".equals(dfzflx)){//合同
				String htt = dao.getHtEndTime(dbid);
				htendtime = htt==null?"":htt;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ds.free(rs, ps, conn);
		}

		actualpay = content[12].toString().trim();

		if (id == null || "".equals(id)) {
			zbdyhhBol = false;
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}

			row.add("未查到自报电用户号" + content[0].toString() + content[2].toString()
					+ "自报电用户号" + zbdyhh);
		} else {
			zbdyhhBol = true;
		}
		return row;
	}

	/**
	 * 自报电用户号不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check02(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[3] == null || "".equals(content[3].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "自报电用户号为空");
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
		if (Format.str_d(qsdbdl)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "系统中全省定标电量为空！");
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
		if (Format.str_d(edhdl)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "系统中额定耗电量为空！");
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
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "系统中直流负荷为空！");
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
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "系统中交流负荷为空！");
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
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "系统中生产标为空！");
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
		if ("".equals(property) || "null".equals(property) || null == property) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中站点属性为空！");
		} 
		return row;
	}
	
	/** 外租回收站点未和主站点ID相关联
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	synchronized Vector<String> checkOutAndConnect(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElecBillZbdDao dao = new ElecBillZbdDaoImp();
		if(dao.getOut(dbid)){//是外租回收,外租回收站点未和主站点ID相关联
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "请先关联主站点ID号，再录入电费！");
			
		} 
		return row;
	}

	/**
	 * 上次电表读数 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check03(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[4] == null || "".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "上次电表读数为空");
		} else {
			if (!Format.isNumber(content[4].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "上次电表读数格式不正确");
			} else {
				lastdegree = Format.str_d(content[4].toString().trim());
			}

		}
		return row;
	}

	/**
	 * 本次电表读数 不为空 格式
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check04(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "本次电表读数为空");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "本次电表读数格式不正确");
			} else {
				thisdegree = Format.str_d(content[5].toString().trim());
			}

		}
		return row;
	}

	/**
	 * 本次抄表时间 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check05(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "本次抄表时间   为空");
		} else {
			if (!Format.isTime(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "本次抄表时间   格式不正确");
			} else {
				

					Date now = new Date();
					Date nowday = new Date(now.getYear(),now.getMonth(),now.getDate());
					Date thist = null;
					if (Format.isTime02(content[7].toString().trim())) {
					try {
						thist = new SimpleDateFormat("yyyy/MM/dd").parse(content[7].toString().trim());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if(thist.after(nowday)){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "本次抄表时间   大于录入时间");
						
					}else{
						thistime = Format.getTime(content[7].toString().trim());
					}
					}else{
						try {
							thist = new SimpleDateFormat("yyyy-MM-dd").parse(content[7].toString().trim());
							if(thist.after(nowday)){
								for (int j = 0; j < content.length; j++) {
									row.add(content[j].toString().trim());
								}
								row.add(content[0].toString() + content[2].toString() + "电表"
										+ dianbiaoid + "本次抄表时间   大于录入时间");
							}else{
								thistime = content[7].toString().trim();
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
			}
		}
		return row;
	}
	
	/**
	 * 合同 结束时间,本次抄表时间 比较
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkhtendtime(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(htendtime != null){
			if("".equals(htendtime)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "先录入合同单再录入电费单!");
			}else{
				Date thistimea = Format.String2Time(content[7].toString().trim());
				Date htendtimedate = Format.String2Time(htendtime);
				if(thistimea.after(htendtimedate)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "合同已到期不允许录入!");
				}
			}
		}
		return row;
	}

	/**
	 * 实际用电量 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @update WangYiXiao 2014-5-7  实际电费金额格式 不正确     改为    自报电用户号" + zbdyhh + "实际用电量格式不正确
	 */
	synchronized Vector<String> check06(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "实际用电量为空");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "实际用电量 格式不正确");
			} else {
				blhdl =content[8].toString().trim();
			}
		}
		return row;
	}

//	/**
//	 * 起始年月 格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> beginTime(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[9] == null || "".equals(content[9].toString().trim())) {
//			thismonth = "";
//		} else {
//
//			if (!Format.isMonth(content[9].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "自报电用户号" + zbdyhh + "起始月份 格式不正确");
//			} else {
//				if (Format.isMonth02(content[9].toString().trim())) {
//					thismonth = Format.getMonth(content[9].toString().trim());
//				} else {
//					thismonth = content[9].toString().trim();
//				}
//
//				if (thismonth == null || "".equals(thismonth)
//						|| "null".equals(thismonth)) {
//					thismonth = "";
//				}
//
//			}
//		}
//
//		return row;
//	}

//	/**
//	 * 结束年月 不为空 格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check07(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[10] == null || "".equals(content[10].toString().trim())) {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
//					+ zbdyhh + "结束月份为空");
//		} else {
//			if (!Format.isMonth(content[10].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "自报电用户号" + zbdyhh + "结束月份 格式不正确");
//			} else {
//				if (Format.isMonth(content[10].toString().trim())) {
//					endmonth = Format.getMonth(content[10].toString().trim());
//				} else {
//					endmonth = content[10].toString().trim();
//				}
//
//			}
//		}
//		return row;
//	}

//	/**
//	 * 起始月份和结束月份的大小的问题
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> timeComp(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[10] != null && !"".equals(content[10].toString().trim())
//				&& content[9] != null
//				&& !"".equals(content[9].toString().trim())
//				&& Format.isMonth(content[9].toString().trim())
//				&& Format.isMonth(content[10].toString().trim())) {
//			Date startmonth = Format.String2Month(content[9].toString().trim());
//			Date endmonth = Format.String2Month(content[10].toString().trim());
//			if (startmonth.getTime() > endmonth.getTime()) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "自报电用户号" + zbdyhh + "起始月份大于结束月份");
//			}
//		}
//		return row;
//	}

	/**
	 * 上次抄表时间和本次抄表时间的大小的问题
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> writeMonth(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[7] != null && !"".equals(content[7].toString().trim())
				&& null != lastdatetime 
				&& !"".equals(lastdatetime)
				&& Format.isTime(content[7].toString().trim())) {
			Date startmonth = Format.String2Time(lastdatetime);
			Date endmonth = Format.String2Time(content[7].toString().trim());
			if (startmonth.getTime() > endmonth.getTime()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "上次抄表时间大于本次抄表时间");
			}
		}
		return row;
	}

	/**
	 * 抄表时间 格式
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[10] == null || "".equals(content[10])) {

		} else {
			if (!Format.isMonth(content[10].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "抄表时间 格式不正确");
			} else {
				if (Format.isMonth02(content[10].toString().trim())) {
					cbsj = Format.getMonth(content[10].toString().trim());
				} else {
					cbsj = content[10].toString().trim();
				}
				if (cbsj == null || "".equals(cbsj) || "null".equals(cbsj)) {
					cbsj = "";
				}
			}
		}

		return row;
	}

	/**
	 * 实际电费金额 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check09(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[12] == null || "".equals(content[12].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "实际电费金额 为空");
		} else {
			if (!Format.isNumber(content[12].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "实际电费金额 格式不正确");
			}else if(Format.str_d(content[12].toString().trim())>0 && Format.str_d(content[12].toString().trim())<5){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "实际电费金额 不能小于5元");
			} else {
				actualdianfei = Format.str_d(content[12].toString().trim());
			}
		}
		return row;
	}

//	/**
//	 * 报账月份 不为空、格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check10(Object[] content) {
//		Vector<String> row = new Vector<String>();
//
//		if (content[15] == null || "".equals(content[15].toString().trim())) {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
//					+ zbdyhh + "报账月份 为空");
//		} else {
//			if (!Format.isMonth(content[15].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "自报电用户号" + zbdyhh + "报账月份 格式不正确");
//			} else {
//				if (Format.isMonth(content[15].toString().trim())) {
//					bzmonth = Format.getMonth(content[15].toString().trim());
//				} else {
//					bzmonth = content[15].toString().trim();
//				}
//
//			}
//
//		}
//		return row;
//	}

	synchronized Vector<String> pjlx_check(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[13] == null || "".equals(content[13].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "票据类型不能为空");
		}
		return row;
	}

//	/**
//	 * 票据时间 格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check11(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (null == content[18] || "".equals(content[18])) {
//			pjtime = "";
//		} else {
//			if (!Format.isTime(content[18].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "自报电用户号" + zbdyhh + "票据时间时间   格式不正确");
//			} else {
//				if (Format.isTime02(content[18].toString().trim())) {
//					pjtime = Format.getTime(content[18].toString().trim());
//				} else {
//					pjtime = content[18].toString().trim();
//				}
//				if (pjtime == null || "".equals(pjtime)
//						|| "null".equals(pjtime)) {
//					pjtime = "";
//				}
//			}
//		}
//
//		return row;
//	}

	/**
	 * 开票时间的格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (null == content[15] || "".equals(content[15])) {

		} else {
			if (content[15] != null && !"".equals(content[15])) {
				if (!Format.isTime(content[15].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "自报电用户号" + zbdyhh + "开票时间格式不正确");
				} else {
					if (Format.isTime02(content[15].toString().trim())) {
						kpsj = Format.getTime(content[15].toString().trim());
					} else {
						kpsj = content[15].toString().trim();
					}

				}
			}
		}

		return row;
	}

//	/**
//	 * 交费时间的格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check13(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (null == content[21] || "".equals(content[21])) {
//			entertime = "";
//		} else {
//			if (content[21] != null && !"".equals(content[21])) {
//				if (!Format.isTime(content[21].toString().trim())) {
//					for (int j = 0; j < content.length; j++) {
//						row.add(content[j].toString().trim());
//					}
//					row.add(content[0].toString() + content[2].toString()
//							+ "自报电用户号" + zbdyhh + "交费时间格式不正确");
//				} else {
//					if (Format.isTime02(content[21].toString().trim())) {
//						entertime = Format.getTime(content[21].toString()
//								.trim());
//					} else {
//						entertime = content[21].toString().trim();
//					}
//					if (entertime == null || "".equals(entertime)
//							|| "null".equals(entertime)) {
//						entertime = "";
//					}
//
//				}
//			}
//		}
//
//		return row;
//	}
	
	/**
	 * 上次抄表时间 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check14(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[6] == null || "".equals(content[6].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "上次抄表时间   为空");
		} else {
			if (!Format.isTime(content[6].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + zbdyhh + "上次抄表时间   格式不正确");
			} else {
				if (Format.isTime02(content[6].toString().trim())) {
					lastdatetime = Format.getTime(content[7].toString().trim());
				}else{
					lastdatetime = content[6].toString().trim();
				}
			}
		}
		return row;
	}
	
	/**
	 * 票据金额
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check15(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[14] == null || "".equals(content[14].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "自报电用户号"
					+ zbdyhh + "票据金额   为空");
		}else {
			if (!Format.isNumber(content[14].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "票据金额 格式不正确");
			} else {
				pjje = content[14].toString().trim();
			}
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

		enterperson = content[9].toString().trim();
		dlmemo = content[11].toString().trim();
		pjlx = content[13].toString().trim();
//		pjbh = content[17].toString().trim();
		payperson = content[16].toString().trim();
		dfmemo = content[17].toString().trim();

		if (enterperson == null || "".equals(enterperson)
				|| "null".equals(enterperson)) {
			enterperson = "";
		}
		if (dlmemo == null || "".equals(dlmemo)
				|| "null".equals(dlmemo)) {
			dlmemo = "";
		}
		if (pjlx == null || "".equals(pjlx) || "null".equals(pjlx)) {
			pjlx = "";
		}
//		if (pjbh == null || "".equals(pjbh) || "null".equals(pjbh)) {
//			pjbh = "";
//		}
		if (payperson == null || "".equals(payperson)
				|| "null".equals(payperson)) {
			payperson = "";
		}
		if (dfmemo == null || "".equals(dfmemo)
				|| "null".equals(dfmemo)) {
			dfmemo = "";
		}
		if("发票".equals(pjlx)){
			pjlx = "pjlx03";
		}
		if("收据".equals(pjlx)){
			pjlx = "pjlx05";
		}
		if("增值税发票".equals(pjlx)){
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
		 //设置一下当前时间给报账月份
	    Date today = new Date(); 
	    int tyear = 1900 + today.getYear();
	    int tmonth = today.getMonth() + 1;
	   String month = String.valueOf(tmonth);
	   if(tmonth < 10){
	 	 month="0" + tmonth;
	    }
	   bzmonth = tyear + "-" + month;
		temp = tool.checkRepeat(String
				.valueOf(lastdegree), lastdatetime, dianbiaoid, bzmonth);
		if (temp) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString()
					+ "电表电费录入重复，请检查！！！");
		}
		return row;

	}

	/**
	 * 查询 电表的分摊比例 ，针对是否分摊进行检查， 如果没有问题 开始本电费，电量的 分摊计算和赋值
	 * 
	 * @param content
	 * @return
	 */
	synchronized void findFentan() {

		double wucha = 0.0000001;

		DecimalFormat df = new DecimalFormat("#.00");

		String dbili1 = zy1;
		String dbili2 = zy2;
		String dbili3 = zy3;
		String dbili4 = zy4;
		String dbili5 = zy5;
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

		if (!"".equals(blhdl) && blhdl != null && blhdl != "0"
				&& !"o".equals(blhdl)) {
			// 生产
			blhdl1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// 营业
			blhdl2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// 办公
			blhdl3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// 信息化
			blhdl4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// 建设投资
			blhdl5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			//代垫电费
			blhdl6 = df.format((new Double(blhdl) - new Double(blhdl1)
					- new Double(blhdl2) - new Double(blhdl3)
					- new Double(blhdl4)-new Double(blhdl5) ));
		} else {
			blhdl1 = "0.00";
			blhdl2 = "0.00";
			blhdl3 = "0.00";
			blhdl4 = "0.00";
			blhdl5 = "0.00";
			blhdl6 = "0.00";
		}
		if (!"".equals(actualpay) && actualpay != null && actualpay != "0"
				&& !"o".equals(actualpay)) {
			// 生产
			actualpay1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// 营业
			actualpay2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// 办公
			actualpay3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// 信息化
			actualpay4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// 建设投资
			actualpay5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			//代垫电费
			actualpay6 = df.format((new Double(actualpay) - new Double(actualpay1)
					- new Double(actualpay2) - new Double(actualpay3)
					- new Double(actualpay4)-new Double(actualpay5) ));
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
	 * 如果没有上次抄表时间
	 */
	synchronized void noLastMonth(Object[] content) {

		Vector<String> row = new Vector<String>();

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 查询出本次抄表时间 结束月份
		String sql2 = "SELECT T.*,ROWNUM FROM(SELECT TO_CHAR(AM.THISDATETIME+1,'YYYY-MM-DD'),AM.BLHDL YDL,DF.ACTUALPAY ACTUALPAY,DF.UNITPRICE UNITPRICE FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES AM,ELECTRICFEES DF "
				+ "WHERE Z.ID=D.ZDID AND D.DBID=AM.AMMETERID_FK AND DF.CITYAUDIT = '1' AND DF.CITYZRAUDITSTATUS = '1' AND DF.AMMETERDEGREEID_FK = AM.AMMETERDEGREEID "
				+ " AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE"
				+ " P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?) ORDER BY AM.THISDATETIME desc nulls last )t where rownum=1";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql2);
			ps.setString(1, zbdyhh);
			ps.setString(2, accountid);
			System.out.println("通过自报电用户号批量导入电量电费,查询出本次抄表时间 结束月份:" + sql2);
			rs = ps.executeQuery();
			while (rs.next()) {
				lastdatetime = rs.getString(1);
//				startmonth = rs.getString(2);
				scdl = rs.getString(2)!= null?rs.getString(2):"" ;
				scdf = rs.getString(3)!= null?rs.getString(3):"" ;
				lastunitprice = rs.getString(4)!= null?rs.getString(4):"" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}
		if (lastdatetime == "" || lastdatetime == null
				|| "".equals(lastdatetime)) {
			lastdatetime = cssytime;
		}

//		if (startmonth == ""||startmonth==null) {
			if ("".equals(lastdatetime) || lastdatetime == null) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add("未查到自报电用户号" + zbdyhh
						+ "的上次抄表时间：该电表没有交过费用，请到电表管理把电表的'初始使用时间'填上");
			}
//			else {
//				startmonth = lastdatetime.substring(0, 7);
//			}

//		}
//		thismonth=startmonth;
	}

	/**
	 * 开始添加 相应的电费
	 * 
	 * @author gt_web
	 * @param content
	 * @return
	 */
	@SuppressWarnings("deprecation")
	synchronized Vector<String> addEle(Object[] content) {

		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4;
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		info.setMemo(dlmemo);
		info.setMemo1(dfmemo);
		dl1 = tool.checkFloatDegree(info);


		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		String thistime_s;
		String last_s;

		try {
			Date thistime_d = new Date(thistime);
			thistime_s = ft.format(thistime_d);
		} catch (Exception e) {
			thistime_s = thistime;
		}

		try {
			Date lastdatetime_d = new Date(lastdatetime);
			last_s = ft.format(lastdatetime_d);
		} catch (Exception e) {
			last_s = lastdatetime;
		}
	////////////////////////////////////////////////////////////////	
		if(Format.str_d(beilv)==0){
       	 beilv = "1";
        }
        String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
		info.setDbydl(dbydl);
		info.setBeilv(beilv);
        String[] shiandxian = tool.getShiAndXian(dbid);
		dl2 = tool.checkDayDegree(dianbiaoid, thistime_s, last_s, blhdl);//上次日耗电量比值	
		dl3 = tool.checkBcdl(blhdl, thistime_s, last_s, null, dianbiaoid,"","");//额定耗电量比值
		String[] cbbl = tool.getQsdbdlCbbl(dbydl, thistime_s, last_s, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dianbiaoid,blhdl,qsdbdl,stationtype);//2014-10-28超省标比例,合并周期,标准值
		dl4 = tool.checkBcdl2(cbbl[0]);//省定标超标比值
		String[] expecthigh = tool.checkExceptAndHigh(dianbiaoid, actualpay, blhdl, thistime_s, last_s, String.valueOf(Format.str_d(cbbl[0])/100));//异常及高额
		String[] site = tool.checckSite(dianbiaoid);//是否1.2万个点
		String[] adjust2 = tool.adjustCheck2(lastunitprice, dianfei);//单价调整
    	String[] chayue = tool.chaYue(thistime_s, bzmonth);//本次抄表时间对应月份-报账月份>=2
    	String[] thiselecfee = tool.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//本次电费金额大于区县上月平均电费金额
    	String[] outprice = new String[]{"1",""};//默认 外借电判断为通过（即没有进行该判断）
    	if("zdsx04".equals(expecthigh[2])){//属性为 其他 则 判断
    		outprice = tool.OutElecUnitPrice(dianfei, shiandxian[0], shiandxian[1]);
    	}
		
    	dlmess = dlmess + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1];
    	//此处的if()else{} 判断是指如果电量自动审核不通过则 电费的自动审核也不通过，如果电量审核通过则对电费进行判 断
    	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])){
    		dlauto="1";//电量自动审核通过
    		String[] df6 = tool.checkElectric2(pjje);//票据金额为空判断
    			dfmess = dfmess  + df6[1] + outprice[1];
    		if("1".equals(df6[0]) && "1".equals(outprice[0])){
    			dfauto = "1";//电费自动审核通过
    		}else{
    			dfauto = "0";//电费自动审核不通过
    		}
    	}else{
    		dlauto="0";//电量自动审核不通过
    		dfauto="0";//电费自动审核不通过
    	}
    	

    	String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
    	String city = "1";//市级审核状态；审核标志
    	String cityzr = "1";//市级主任审核状态；审核标志		        
    	
    	if("1".equals(adjust2[0]) || "1".equals(chayue[0]) || "1".equals(thiselecfee[0])){
    		qxzr = "0";
    		city = "0";
    		cityzr = "0";
    	}
    	if("1".equals(expecthigh[0])){
    		qxzr = "0";
    		city = "0";
    		cityzr = "0";
    	}else if("0".equals(expecthigh[0])){
    		if("1".equals(site[0])){
    			city = "0";
    			cityzr = "0";
    		}else if("0".equals(site[0])){
    			if("0".equals(dl3[0]) || "0".equals(dl4[0])){ 
    				city = "0";
    			}
    		}
    	}
    	dfmess =dfmess + adjust2[1] + chayue[1] + thiselecfee[1] + expecthigh[1] + site[1];//描述信息增加到电费表中
		
		if(thisdegree-lastdegree!=Double.parseDouble(blhdl)){
			dfmess = dfmess+"   上次电表读数与最后抄表数不一致！" ;
			dfauto="0";
			dlmess = dlmess+"   上次电表读数与最后抄表数不一致！" ;
			dlauto="0";
		}
		info.setZlfh(zlfh);
		info.setJlfh(jlfh);
		info.setPropertycode(property);
		info.setGdfscode(gdfs);
		info.setDfzflxcode(dfzflx);
		info.setStationtypecode(stationtype);
		info.setLastdegree(String.valueOf(lastdegree));
		info.setThisdegree(String.valueOf(thisdegree));
		info.setActualdegree(String.valueOf(blhdl));
		info.setLastdatetime(lastdatetime);
		
		info.setThisdatetime(thistime);

		info.setBlhdl(blhdl);
		info.setInputoperator(enterperson);
		info.setEntrypensonnel(accountName);
		info.setPayoperator(payperson);

		info.setFloatdegree("0");
		info.setFloatpay("0");

		share.setNetworkhdl(new Double(blhdl1));
		share.setMarkethdl(new Double(blhdl2));
		share.setAdministrativehdl(new Double(blhdl3));
		share.setInformatizationhdl(new Double(blhdl4));
		share.setBuildhdl(new Double(blhdl5));
		share.setDddl(new Double(blhdl6));

		info.setUnitprice(dianfei);
		info.setAccountmonth(bzmonth);
		info.setActualpay(actualdianfei);
		info.setNotetypeid(pjlx);
		info.setPjje(Double.parseDouble(pjje));
		//info.setFlag(0);////////////////////////////////////////////////////////?
		
		info.setScdf(scdf);
		info.setScdl(scdl);
		info.setLastunitprice(lastunitprice);

		share.setNetworkdf(new Double(actualpay1));
		share.setMarketdf(new Double(actualpay2));
		share.setAdministrativedf(new Double(actualpay3));
		share.setInformatizationdf(new Double(actualpay4));
		share.setBuilddf(new Double(actualpay5));
		share.setDddf(new Double(actualpay6));

		ElecBillZbdDao dao = new ElecBillZbdDaoImp();
		// 主键生成

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String amuuid = uuid2 + Long.toString(uuid1).substring(3);
		ZhurenPanduanBean zpb = new ZhurenPanduanBean();
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String countyaudittime = "1".equals(qxzr) ? s : null;//区县主任审核时间
		String cityaudittime = "1".equals(city) ? s : null;//市级审核时间
		String cityzraudittime = "1".equals(cityzr) ? s : null;//市级主任审核时间
		

		zpb.setCountyauditstatus(qxzr);//区县主任审核状态
		zpb.setCountyaudittime(countyaudittime);//区县主任审核时间
		
		zpb.setCityaudittime(cityaudittime);//市级审核时间------------------------------------------
		
		zpb.setCityzrauditstatus(cityzr);//市级主任审核状态
		zpb.setCityzraudittime(cityzraudittime);//市级主任审核时间
		
		
		String cShengdb=cbbl[0];//超省标比例
		String cShidb=dl3[3];
		String edhdl1 = dl3[5];
		String qsdbdl1 = qsdbdl;//全省定标电量
		zpb.setCsdb(cShengdb);
		String temp = "0";//0导入还是录入
    	String hbzq = cbbl[1];//合并周期
    	String bzz = cbbl[2];//标准值
		
		String a = dao.addAm(dianbiaoid, info, share, amuuid, temp, dfmess,
				dfauto, dlmess, dlauto, cShidb, city,zpb,hbzq,bzz,scb);
		if ("保存成功！".equals(a)) {
			String rtmess = dao.addDegreeFees(this.getDLID(amuuid), dianbiaoid,
					info, share, amuuid, temp, dfmess, dfauto, dlmess, dlauto,
					beilv, city,zpb,dl3[4],edhdl1,qsdbdl1);
			if (!"保存成功！".equals(rtmess)) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + dianbiaoid + "Error！");
			}
		}

		return row;	
	}
	
	
	/**
	 * 开始添加 相应的电费
	 * 
	 * @author gt_web
	 * @param content
	 * @return
	 */
/*	@SuppressWarnings("deprecation")
	synchronized Vector<String> addEle1(Object[] content) {

		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4;
		String[] df1;
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		info.setMemo(dlmemo);
		info.setMemo1(dfmemo);
		dl1 = tool.checkFloatDegree(info);

		String flag = "1";

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		String thistime_s;
		String last_s;

		try {
			Date thistime_d = new Date(thistime);
			thistime_s = ft.format(thistime_d);
		} catch (Exception e) {
			thistime_s = thistime;
		}

		try {
			Date lastdatetime_d = new Date(lastdatetime);
			last_s = ft.format(lastdatetime_d);
		} catch (Exception e) {
			last_s = lastdatetime;
		}
		
//		if("0".equals(edhdl)||"".equals(edhdl)||edhdl==null){
//
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString()
//					+ "自报电用户号" + dianbiaoid + "额定耗电量为0或为空");
//			return row;
//		
//		}
		
		dl2 = tool.checkDayDegree(dianbiaoid, thistime_s, last_s, blhdl);
		dl3 = tool.checkBcdl(blhdl, thistime_s, last_s, edhdl, dianbiaoid,"","");
		dl4 = tool.checkBcdl2(blhdl, qsdbdl, thistime_s, last_s, dianbiaoid,"","");
		if ("0".equals(dl1[0])) {
			dlmess += dl1[1];
		}
		if ("0".equals(dl2[0])) {
			dlmess += dl2[1];
		}
		if ("0".equals(dl3[0])) {
			dlmess += dl3[1];
		}
		if ("0".equals(dl4[0])) {
			dlmess += dl4[1];
			flag = dl4[2];
		}

		if ("1".equals(dl1[0]) && "1".equals(dl2[0]) && "1".equals(dl3[0])
				&& "1".equals(dl4[0])) {
			dlauto = "1";
			info.setMemo1(dfmemo);
			df1 = tool.checkElectric1(info);
				dfmess = df1[1] ;
			if ("1".equals(df1[0])) {
				dfauto = "1";
			} else {
				dfauto = "0";
			}
		} else {
			dfauto = "0";
			dlauto = "0";
		}
		if(thisdegree-lastdegree!=Double.parseDouble(blhdl)){
			dfmess = dfmess+"   上次电表读数与最后抄表数不一致！" ;
			dfauto="0";
			dlmess = dlmess+"   上次电表读数与最后抄表数不一致！" ;
			dlauto="0";
		}

		info.setLastdegree(String.valueOf(lastdegree));
		info.setThisdegree(String.valueOf(thisdegree));
		info.setActualdegree(String.valueOf(blhdl));
		info.setLastdatetime(lastdatetime);
		
		info.setThisdatetime(thistime);
		//info.setStartmonth(startmonth);
		//info.setEndmonth(endmonth);
		info.setBlhdl(blhdl);
		info.setInputoperator(enterperson);
		info.setEntrypensonnel(accountName);
		info.setPayoperator(payperson);

		info.setFloatdegree("0");
		info.setFloatpay("0");

		share.setNetworkhdl(new Double(blhdl1));
		share.setMarkethdl(new Double(blhdl2));
		share.setAdministrativehdl(new Double(blhdl3));
		share.setInformatizationhdl(new Double(blhdl4));
		share.setBuildhdl(new Double(blhdl5));
		share.setDddl(new Double(blhdl6));

		info.setUnitprice(dianfei);
		info.setAccountmonth(bzmonth);
		info.setActualpay(actualdianfei);
		info.setNotetypeid(pjlx);
		//info.setNoteno(pjbh);
		//info.setNotetime(pjtime);
		//info.setPaydatetime(entertime);
		info.setFlag(0);
		
		info.setScdf(scdf);
		info.setScdl(scdl);
		info.setLastunitprice(lastunitprice);

		share.setNetworkdf(new Double(actualpay1));
		share.setMarketdf(new Double(actualpay2));
		share.setAdministrativedf(new Double(actualpay3));
		share.setInformatizationdf(new Double(actualpay4));
		share.setBuilddf(new Double(actualpay5));
		share.setDddf(new Double(actualpay6));

		ElecBillZbdDao dao = new ElecBillZbdDaoImp();
		// 主键生成

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String amuuid = uuid2 + Long.toString(uuid1).substring(3);

		String temp = "0";
		
		
		
		
		ZhurenPanduanBean zpb = new ZhurenPanduanBean();
		 存储各级判断 
		// 实例化工具类
		ElectricTool et = new ElectricTool();

		boolean b_ycgedf = false;// 异常高额电费
		boolean b_sdb = false;// 大于省定标
		boolean b_edhdl = false;// 小于额定耗电量
		boolean b_zhandian = false;// 1.2万个点

		// 判断异常及高额电费
		
		//将日期进行转换：
		Double money = actualdianfei;
		String eah[] = et.checkExceptAndHigh(dbid, "",
				money.toString(), blhdl,thistime , lastdatetime,
				"", "", "2", "", "2");

		if ("1".equals(eah[0])) {
			b_ycgedf = false;
		} else if ("0".equals(eah[0])) {
			b_ycgedf = true;
		}

		String qxsh = "0";
		if (b_ycgedf == false) {
			qxsh = "1";
		} else {
			qxsh = "2";
		}

		String countyauditstatus = qxsh;// 区县主人审核状态标志位
		String countyauditname = "";// 区县主任审核人
		String countyaudittime = "";// 区县主任审核时间

		// 判断额定耗电量和省定标
		String ed[] = et.checkBcdl(blhdl, thistime, lastdatetime, edhdl, dbid, "", "2");// 额定耗电量
		String qs[] = et.checkBcdl2(blhdl, qsdbdl, thistime, lastdatetime, dbid, "", "2");
		
		
		if ("0".equals(ed[2])) {
			b_edhdl = false;
		} else if ("1".equals(ed[2])) {
			b_edhdl = true;
		}
		if ("0".equals(qs[2])) {
			b_sdb = false;
		} else if ("1".equals(qs[2])) {
			b_sdb = true;
		}

		// 判断1.2万个点
		String onePointTwo[] = et.checckSite(dbid);
		if ("1".equals(onePointTwo[0])) {
			b_zhandian = false;
		} else if ("0".equals(onePointTwo[0])) {
			b_zhandian = true;
		}

		String szrBzw = "0";
		if (b_ycgedf == false || b_zhandian == false) {
			szrBzw = "0";
		} else {
			szrBzw = "1";
		}

		String cityzrauditstatus = szrBzw;// 市主任审核状态标志位
		String cityzrauditname = "0";// 市主任审核人
		String cityzraudittime = "";// 市主任审核时间

		String cityAudit = "0";
		if (b_ycgedf == false || b_edhdl == false || b_sdb == false
				|| b_zhandian == false) {
			cityAudit = "0";
		} else {
			cityAudit = "1";
		}

		zpb.setCITYAUDIT(cityAudit);
		
//		Long zq = et.getQuot(thistime, lastdatetime);
//		String jszq = zq.toString();
//		zpb.setJszq(jszq);

		zpb.setCountyauditname(countyauditname);
		zpb.setCountyauditstatus(countyauditstatus);
		zpb.setCountyaudittime(countyaudittime);
		zpb.setCityzrauditname(cityzrauditname);
		zpb.setCityzrauditstatus(cityzrauditstatus);
		zpb.setCityzraudittime(cityzraudittime);
		
		
		String cShengdb=qs[3];
		String cShidb=ed[3];
		String edhdl1 = ed[5];
		String qsdbdl1 = qs[5];
		//计算超省表市标
//		String cShengdb="";
//		String cShidb="";
//		try{
//		String qsdb;
//		if("".equals(qsdbdl)||qsdbdl==null){
//			qsdb="0";
//		}else{
//			qsdb = qsdbdl;
//		}
//			Double cb_sb= Double.parseDouble(qsdb);
//			Double cb_zq = Double.parseDouble(jszq);
//			
//			
//		
//			Double cb_ed = Double.parseDouble(edhdl);
//			double csdb = 0;
//			double ccity=0;
//			if(cb_sb*cb_zq!=0){
//				csdb = (Double.parseDouble(blhdl)-cb_sb*cb_zq)/(cb_sb*cb_zq);
//			}
//			if(cb_ed+cb_zq!=0){
//				ccity = (Double.parseDouble(blhdl)-cb_ed*cb_zq)/(cb_ed*cb_zq);
//			}
//			
//			BigDecimal bg_cs = new BigDecimal(csdb);
//			cShengdb = bg_cs.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
//			
//			BigDecimal bg_city= new BigDecimal(ccity);
//	        cShidb = bg_city.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
//			
//		}catch(Exception e){
//			cShengdb="";
//			cShidb="";
//		}
		//计算超省表市标
		
		zpb.setCsdb(cShengdb);
			
		String a = dao.addAm(dianbiaoid, info, share, amuuid, temp, dfmess,
				dfauto, dlmess, dlauto, cShidb, flag,zpb);
		if ("保存成功！".equals(a)) {
			String rtmess = dao.addDegreeFees(this.getDLID(amuuid), dianbiaoid,
					info, share, amuuid, temp, dfmess, dfauto, dlmess, dlauto,
					beilv, flag,zpb,ed[4],edhdl1,qsdbdl1);
			if (!"保存成功！".equals(rtmess)) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "自报电用户号" + dianbiaoid + "Error！");
			}
		}

		return row;
	}*/

	/**
	 * 获取外键
	 * 
	 * @param amuuid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String amuuid) {
		String sql = "select ammeterdegreeid from AMMETERDEGREES where uuid='"
				+ amuuid + "'";
		System.out.println("--电表amuuid返回id--" + sql);
		DataBase db11 = new DataBase();
		ResultSet rss = null;
		ArrayList list = new ArrayList();
		rss = null;
		try {
			rss = db11.queryAll(sql);

			while (rss.next()) {
				list.add(rss.getString("ammeterdegreeid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rss != null) {
					rss.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (db11 != null) {
						db11.close();
					}
				} catch (DbException e) {
					e.printStackTrace();
				}
			}
		}
		return list;

	}

}
