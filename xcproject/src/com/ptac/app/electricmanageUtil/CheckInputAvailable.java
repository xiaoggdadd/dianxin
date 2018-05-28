package com.ptac.app.electricmanageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;

/**
 * @author xuzehua 电费单数据的增加，导入完成型检查
 */
public class CheckInputAvailable {
	private double averagefees;
	private String averagefeestrueorfalse,startshi;
	String accountid,accountname;
	String dianbiaoid, dianfei, beilv, linelosstype, linelossvalue, edhdl,htendtime,
			dbid, qsdbdl, dfzflx , liuchenghao,shi,property,zlfh,jlfh,scb,stationtype,gdfs;// 完全有值
	String scdf,scdl,lastunitprice;//上次电费，上次电量，上次单价
	String dbds="",xgbzw="",hbzq,bzz;
	String dlmess ="";
	String dfmess ="";
	String dlauto = "0";
	String dfauto = "0";
	double dlzylx01, dlzylx02, dlzylx03, dlzylx04, dlzylx05, dlzylx06;// 电量
	double dfzylx01, dfzylx02, dfzylx03, dfzylx04, dfzylx05, dfzylx06;// 电费
	// 检测完成的数据（待导入）
	double lastdegree, thisdegree, tzdegree, tzdianfei,
			actualdianfei//报账电费用于判断
			, pjje;
	String lasttime, thistime, bzmonth, blhdl,sjydl,
			enterperson,dbydl;
	String dlmemo = "";String dfmemo = "";String entertime = "";
	String payperson = "";String pjlx = "";String kptime ="";
	String lastd,lastt;//上次电表读数，上次抄表时间   (临时变量)  
	long jszq_temp;//结算周期
	private DataBase ds = new DataBase();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * 电费单 导入的 检查 excel的数据正确性检查和 电费单导入重复性检查
	 */
	public CheckInputAvailable() {
		getValues();
	}
	private  void getValues(){
		ElecBillDao dao = new ElecBillDaoImp();
		Map<String,String> map = dao.getValue("3");
		averagefees = Double.parseDouble(map.get("AverageFees"));
		averagefeestrueorfalse = map.get("AverageFeesTrueOrFalse");
		startshi = map.get("StartShi")==null?"":map.get("StartShi");
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
		accountname = account.getAccountName();
		Vector<String> row = new Vector<String>();

		// 必填项检查
		row = this.check01(content, account);
		
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
			row = this.check02(content);
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
//		if (row.isEmpty()) {
//			row = this.check06(content);
//		}
		if (row.isEmpty()) {
			row = this.check07(content);
		}
		if (row.isEmpty()) {
			row = this.check08(content);
		}
		if (row.isEmpty()) {
			row = this.check09(content);
		}
//		if (row.isEmpty()) {
//			row = this.check10(content);
//		}
		if (row.isEmpty()) {
			row = this.check11(content);
		}
		if (row.isEmpty()) {
			row = this.check12(content);
		}
//		if (row.isEmpty()) {
//			row = this.check14(content);
//		}
        
		// / 时间的比较

		if (row.isEmpty()) {
			row = this.check13(content);
		}
//		if (row.isEmpty()) {
//			row = this.check15(content);
//		}
		if(row.isEmpty()){
			row = this.checkhtendtime(content);
		}
		// / 大小检查
		if (row.isEmpty()) {
			row = this.check16(content);
		}
		if (row.isEmpty()) {
			row = this.check17(content);
		}
//		if (row.isEmpty()) {
//			row = this.check18(content);
//		}
		if (row.isEmpty()) {
			row = this.check19(content);
		}
//		if (row.isEmpty()) {
//			row = this.check20(content);
//		}

		// 其他
		if (row.isEmpty()) {
			row = this.check21(content);
		}

		// 开始导入前的检查
		if (row.isEmpty()) {
			row = this.check22(content);
		}
		// 开始分摊的检查，没问题开始给 电表比例赋值

		if (row.isEmpty()) {
			row = this.check23(content);
		}
		if(row.isEmpty()){
        	row = this.check24(content);
        }
		if(row.isEmpty()){
        	row = this.check26(content);
        }
		// 前期工作完成开始添加电费
        if(row.isEmpty()){
        	row = this.check25(content);
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
		Vector<String> row = new Vector<String>();
		String sql = "SELECT D.DBID, D.DANJIA,D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE, Z.EDHDL,Z.QSDBDL,D.DFZFLX,D.CSDS,TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') CSSYTIME,"
				+ "D.DBDS,D.XGBZW,Z.SHI,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,Z.STATIONTYPE,Z.GDFS "
				+ " FROM ZHANDIAN Z, DIANBIAO D  WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt01'"
				+ " AND D.DBQYZT = '1' AND Z.QYZT = '1' AND D.DBID =?"
				+ " AND EXISTS (SELECT 'A' FROM PER_AREA P WHERE P.AGCODE = Z.XIAOQU AND P.ACCOUNTID = ?)";
		String 	sql1 = "SELECT S.BLHDL YDL,DF.ACTUALPAY ACTUALPAY,DF.UNITPRICE UNITPRICE, S.THISDEGREE AS LASTDEGREE,TO_CHAR(S.THISDATETIME,'yyyy-mm-dd') AS LASTDATETIME"
				+" FROM (SELECT MAX(T.THISDATETIME) LASTDATETIME, T.AMMETERID_FK"
				+" FROM DIANDUVIEW T, DIANFEIVIEW D"
				+" WHERE T.AMMETERDEGREEID = D.AMMETERDEGREEID_FK" 
				+" AND D.CITYAUDIT = '1' AND D.CITYZRAUDITSTATUS = '1'"
				+" AND AMMETERID_FK = ?" 
				+" GROUP BY T.AMMETERID_FK) A,DIANDUVIEW S,DIANFEIVIEW DF" 
				+" WHERE S.AMMETERID_FK = A.AMMETERID_FK"
				+" AND S.THISDATETIME = A.LASTDATETIME AND DF.AMMETERDEGREEID_FK = S.AMMETERDEGREEID";
		try {
			conn = ds.getConnection();
		} catch (DbException e) {
			e.printStackTrace();
		}

		try {
			String lastdatetime1 = null;
			String lastdegree1 = null;
			System.out.println("导入信息查询"+sql);
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, content[3].toString().trim());
			ps.setString(2, account.getAccountId());
			rs = ps.executeQuery();
			while (rs.next()) {
				dbid = rs.getString("dbid");
				dianfei = rs.getString("danjia").trim();
				beilv = rs.getString("beilv")==null?"":rs.getString("beilv");
				linelosstype = rs.getString("linelosstype")==null?"":rs.getString("linelosstype");
				linelossvalue = rs.getString("linelossvalue")==null?"":rs.getString("linelossvalue");
				edhdl = rs.getString("edhdl")==null?"":rs.getString("edhdl");
				qsdbdl = rs.getString("QSDBDL")==null?"":rs.getString("QSDBDL");
				dfzflx = rs.getString("DFZFLX")==null?"":rs.getString("DFZFLX");
				lastdatetime1 = rs.getString("CSSYTIME")==null?"":rs.getString("CSSYTIME");
				lastdegree1 = rs.getString("CSDS")==null?"":rs.getString("CSDS");
				dbds = rs.getString("DBDS")==null?"":rs.getString("DBDS");
				xgbzw = rs.getString("XGBZW")==null?"":rs.getString("XGBZW");
				shi = rs.getString("SHI")==null?"":rs.getString("SHI");
				property = rs.getString("PROPERTY")==null?"":rs.getString("PROPERTY");
				zlfh = rs.getString("ZLFH")==null?"":rs.getString("ZLFH");
				jlfh = rs.getString("JLFH")==null?"":rs.getString("JLFH");
				scb = rs.getString("SCB")==null?"":rs.getString("SCB");
				gdfs = rs.getString("GDFS")==null?"":rs.getString("GDFS");
				stationtype = rs.getString("STATIONTYPE")==null?"":rs.getString("STATIONTYPE");
				ElecBillDao dao = new ElecBillDaoImp();
				if("dfzflx03".equals(dfzflx)){//预支
					liuchenghao = dao.getLiuchenghao(dbid);
				}else if("dfzflx02".equals(dfzflx)){//合同
					String htt = dao.getHtEndTime(dbid);
					htendtime = htt==null?"":htt;
				}
			}
			System.out.println("上次抄表时间，上次电表读数,上次电费，上次电量，上次单价查询");
			ps = conn.prepareStatement(sql1);
			ps.setString(1, content[3].toString().trim());
			rs = ps.executeQuery();
			if(rs.next()){
				scdf = rs.getString("ACTUALPAY") == null ? "" : rs.getString("ACTUALPAY");
				scdl = rs.getString("YDL")== null ? "" : rs.getString("YDL");
				lastunitprice = rs.getString("UNITPRICE")== null ? "" : rs.getString("UNITPRICE");
				
				String lastti = rs.getString("LASTDATETIME") == null ? "" : rs.getString("LASTDATETIME");
				lastd = rs.getString("LASTDEGREE") == null ? "" : rs.getString("LASTDEGREE");
				if(!"".equals(lastti)){
				Calendar c = Calendar.getInstance();
				Date dt = null;
				try {
					dt = new SimpleDateFormat("yyyy-MM-dd").parse(lastti);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				c.setTime(dt);
				int day = c.get(Calendar.DATE);
				c.set(Calendar.DATE, day + 1);//上次抄表时间要加一天
				lastt = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				}
			}else{
				lastt = lastdatetime1;
				lastd = lastdegree1;
			}
	       if("1".equals(xgbzw)){//如果修改电表读数标志位为1  上次电表读数等于  电表修改读数（信息修改）
	    	   lastd = dbds;
	       }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ds.free(rs, ps, conn);
		}
		if (dbid == null || "".equals(dbid)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			dianbiaoid = content[3].toString().trim();
			row.add("未查到" + content[0].toString() + content[2].toString()
					+ "电表" + dianbiaoid);
		}
		dianbiaoid = content[3].toString().trim();
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
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中全省定标电量为空！");
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
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中额定耗电量为空！");
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
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中直流负荷为空！");
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
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中交流负荷为空！");
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
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中生产标为空！");
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
		if ("".equals(property) || "null".equals(property)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中站点属性为空！");
		} 
		return row;
	}
	/**
	 * 外租回收站点未和主站点ID相关联
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @author WangYiXiao 2014-12-08
	 */
	synchronized Vector<String> checkOutAndConnect(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElecBillDao dao = new ElecBillDaoImp();
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
	 * 上次电表读数 不为空 ，格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check02(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastd == null || "".equals(lastd.trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "上次电表读数为空");
		} else {
			if (!Format.isNumber(lastd.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "上次电表读数格式不正确");
			} else {
				if (Format.str_d(lastd.trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "上次电表读数必须大于等于0");
				} else {
					lastdegree = Format.str_d(lastd.trim());
				}
			}
		}
		return row;
	}

	/**
	 * 本次电表读数 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check03(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次电表读数为空");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次电表读数格式不正确");
			} else {
				if (Format.str_d(content[5].toString().trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "本次电表读数必须大于等于0");
				} else {
					thisdegree = Format.str_d(content[5].toString().trim());
				}
			}

		}
		return row;
	}

	/**
	 * 用电量调整 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check04(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "用电量调整为空");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "用电量调整格式不正确");
			} else {
				tzdegree = Format.d2(Format.str_d(content[8].toString().trim()));
			}

		}
		return row;
	}

	/**
	 * 实际用电量调整 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check05(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "报账电量为空");
		} else {
			if (!Format.isNumber(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "报账电量格式不正确");
			}
		}
		return row;
	}

//	/**
//	 * 起始月份 不为空 格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check06(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[10] == null || "".equals(content[10].toString().trim())) {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + "起始月份 为空");
//		} else {
//			if (!Format.isMonth(content[10].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "起始月份 格式不正确");
//			} else {
//				if (Format.isMonth02(content[10].toString().trim())) {
//					thismonth = Format.getMonth(content[10].toString().trim());
//				}
//				else{
//					thismonth = content[10].toString().trim();
//				}
//			}
//
//		}
//		return row;
//	}

//	/**
//	 * 和结束月份 不为空 格式
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check14(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[11] == null || "".equals(content[11].toString().trim())) {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + "结束月份为空");
//		} else {
//			if (!Format.isMonth(content[11].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "结束月份 格式不正确");
//			} else {
//				if (Format.isMonth02(content[11].toString().trim())) {
//					endmonth = Format.getMonth(content[11].toString().trim());
//				} else {
//					endmonth = content[11].toString().trim();
//				}
//
//			}
//		}
//		return row;
//	}

//	/**
//	 * 其实月份和结束月份的大小的问题
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check15(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[11] != null && !"".equals(content[11].toString().trim())
//				&& content[10] != null
//				&& !"".equals(content[10].toString().trim())
//				&& Format.isMonth(content[10].toString().trim())
//				&& Format.isMonth(content[11].toString().trim())) {
//			Date startmonth = Format
//					.String2Month(content[10].toString().trim());
//			Date endmonth = Format.String2Month(content[11].toString().trim());
//			if (startmonth.getTime() > endmonth.getTime()) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "起始月份大于结束月份");
//			}
//		}
//		return row;
//	}

	/**
	 * 本次单价 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check07(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[13] == null || "".equals(content[13].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次单价 为空");
		} else {
			if (!Format.isNumber(content[13].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次单价 格式不正确");
			} else {
				if (!Format.str4(content[13].toString().trim()).equals(
						Format.str4(dianfei.toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "本次单价  与电表的实际单价不符合！ 系统单价为："
							+ Format.str4(dianfei.toString().trim()));
				}
			}
		}
		return row;
	}

	/**
	 * 费用调整 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[14] == null || "".equals(content[14].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "费用调整 为空");
		} else {
			if (!Format.isNumber(content[14].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "费用调整 格式不正确");
			} else {
				tzdianfei = Format.d2(Format.str_d(content[14].toString().trim()));
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
		if (content[15] == null || "".equals(content[15].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "报账电费金额 为空");
		} else {
			if (!Format.isNumber(content[15].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "报账电费金额 格式不正确");
			} else {
				actualdianfei = Format.d2(Format.str_d(content[15].toString().trim()));
			}
		}
		return row;
	}

//	/**
//	 * 报账月份 不为空
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check10(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[18] == null || "".equals(content[18].toString().trim())) {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + "报账月份 为空");
//		} else {
//			if (!Format.isMonth(content[18].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "报账月份 格式不正确");
//			} else {
//				if (Format.isMonth02(content[18].toString().trim())) {
//					bzmonth = Format.getMonth(content[18].toString().trim());
//				} else {
//					bzmonth = content[18].toString().trim();
//				}
//
//			}
//
//		}
//		return row;
//	}

	/**
	 * 上次抄表时间 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check11(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastt == null || "".equals(lastt.trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "上次抄表时间   为空");
		} else {
			if (!Format.isTime(lastt.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "上次抄表时间   格式不正确");
			} else {
				if (Format.isTime02(lastt.trim())) {
					lasttime = Format.getTime(lastt.trim());
				}else{
				lasttime = lastt.trim();
				}
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
	@SuppressWarnings("deprecation")
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次抄表时间   为空");
		} else {
			if (!Format.isTime(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次抄表时间   格式不正确");
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
	 * 上次抄表时间大于本次抄表时间
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check13(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastt != null && !"".equals(lastt.trim())
				&& content[7] != null
				&& !"".equals(content[7].toString().trim())
				&& Format.isTime(content[7].toString().trim())
				&& Format.isTime(lastt.trim())) {
			Date lasttime = Format.String2Time(lastt.trim());
			Date thistime = Format.String2Time(content[7].toString().trim());
			if (lasttime.getTime() > thistime.getTime()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "上次抄表时间大于本次抄表时间");
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
	 * 抄表时间的格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check16(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[11] != null && !"".equals(content[11])) {
			if (!Format.isTime(content[11].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "抄表时间格式不正确");
			} else {
				if (Format.isTime02(content[11].toString().trim())) {
					entertime = Format.getTime(content[11].toString().trim());
				} else {
					entertime = content[11].toString().trim();
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
	synchronized Vector<String> check17(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[17] == null || "".equals(content[17])) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "票据金额   为空");
		}else{
			if (!Format.isNumber(content[17].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "票据金额 格式不正确");
			} else {
				pjje = Format.str_d(content[17].toString().trim());
			}
		}
		return row;
	}

//	/**
//	 * 票据时间
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check18(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[22] != null && !"".equals(content[22].toString().trim())){
//			if (!Format.isTime(content[22].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "票据时间 格式不正确");
//			} else {
//				if (Format.isTime02(content[22].toString().trim())) {
//					pjtime = Format.getTime(content[22].toString().trim());
//				} else {
//					pjtime = content[22].toString().trim();
//				}
//
//			}
//		}
//		return row;
//	}

	/**
	 * 开票时间
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check19(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[18] != null && !"".equals(content[18])) {
			if (!Format.isTime(content[18].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "开票时间 格式不正确");
			} else {
				if (Format.isTime02(content[18].toString().trim())) {
					kptime = Format.getTime(content[18].toString().trim());
				} else {
					kptime = content[18].toString().trim();
				}

			}
		}
		return row;
	}

//	/**
//	 * 交费时间
//	 * 
//	 * @param content
//	 * @param row
//	 * @return
//	 */
//	synchronized Vector<String> check20(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (content[25] != null && !"".equals(content[25])) {
//			if (!Format.isTime(content[25].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + "交费时间 格式不正确");
//			} else {
//				if (Format.isTime02(content[25].toString().trim())) {
//					paytime = Format.getTime(content[25].toString().trim());
//				} else {
//					paytime = content[25].toString().trim();
//				}
//
//			}
//		}
//		return row;
//	}

	/**
	 * 比较实际用电量和实际交费金额 包括电费的计算 前提，
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check21(Object[] content) {
		Vector<String> row = new Vector<String>();
		double dl = 0.00, df = 0.00;
		double sjdl = 0.00;//实际用电量：本次读数-上次读数+电量调整
		// 实际用电量和 用电费 经过 判断
		double thisdegrees = Format.d2(Format.str_d(content[5].toString()
				.trim()));
		double lastdegrees = Format.d2(Format.str_d(lastd
				.trim()));
		double beilv_temp = 0.0;// 查处 无判断
		double linelossvalue_temp = 0.0;// 查处 无判断
		double floatdegrees = Format.d2(Format.str_d(content[8].toString()
				.trim()));
		if (linelosstype == null || "".equals(linelosstype.toString().trim())) {
			linelosstype = "";
		}
		if ("02xsbl".equals(linelosstype)) {//线损比例
				linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
			
			if (Format.str_d(beilv.toString().trim()) == 0) {
				beilv_temp = 1.0;
			}else{
				beilv_temp = Double.parseDouble(beilv); 
			}	
		} else {
			//if("01xstz".equals(linelosstype)){//线损调整
				linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
				if (Format.str_d(beilv.toString().trim()) == 0) {
					beilv_temp = 1.0;
				}else{
					beilv_temp = Double.parseDouble(beilv);
				}
			//}
		}
		if (content[9] != null && !"".equals(content[9].toString().trim())) {
			if ("02xsbl".equals(linelosstype)) {//线损比例
				// (（thisdegree-lastdegree）*linelossvalue+floatdegree)*mpower
				// 保留两位小数
				//（(本次电表读数-上次电表读数）*（1+线损比例）+电量调整）*倍率
				dl = Format.d2(((thisdegrees - lastdegrees)
						* (1 + linelossvalue_temp) + floatdegrees)
						* beilv_temp);//倍率耗电量
				sjdl = Format.d2((thisdegrees - lastdegrees)+floatdegrees);//实际用电量
						
				if (dl != Format.d2(Format.str_d(content[9].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "电量计算错误 ，系统计算电量为：" + dl + ",上次电表读数来自数据库，Excel表格中的上次电表读数不允许修改 ");
					return row;
				} else {
					blhdl = Double.toString(dl);
					sjydl = Double.toString(sjdl);//实际用电量
				}
			} else {
				// Thisdegree-lastdegree+linelossvalue+floatdegree）*mpower
				sjdl = Format.d2((thisdegrees - lastdegrees)+floatdegrees);//实际用电量
				dl = Format
						.d2((thisdegrees - lastdegrees + linelossvalue_temp + floatdegrees)
								* beilv_temp);//倍率耗电量
				if (dl != Format.d2(Format.str_d(content[9].toString().trim()))) {
					if (dl != Format.d2(Format.str_d(content[9].toString()
							.trim()))) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString()
								+ "电表" + dianbiaoid + "电量计算错误 ，系统计算电量为：" + dl + ",上次电表读数来自数据库，Excel表格中的上次电表读数不允许修改 ");
						return row;
					}
				} else {
					blhdl = Double.toString(dl);//倍率耗电量
					sjydl = Double.toString(sjdl);//实际用电量
				}
			}
			if (content[15] != null
					&& !"".equals(content[15].toString().trim())) {
				
				double temp = Format.str_d(content[14].toString().trim());
				double dianfei_temp = Format.str_d(dianfei.toString().trim());
				df = Format.d2(dl * dianfei_temp + temp);
				
				if (df != Format
						.d2(Format.str_d(content[15].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "电费计算错误 ，系统计算电费为：" + df);
					return row;
				} else if (df>0 && df<5){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "电费金额不能小于5元");
					return row;
					
				}else {
					// 数据处理没有问题，针对Excel 的调查
				
					dlmemo = content[12].toString().trim();
					enterperson = content[10].toString().trim();
					dfmemo = content[20].toString().trim();
					pjlx = content[16].toString().trim();
					//pjbh = content[20].toString().trim();
					payperson = content[19].toString().trim();
				}
			}
		}

		return row;
	}

	//济宁日均电费>6
	synchronized Vector<String> check22(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("1".equals(averagefeestrueorfalse)){
			ElectricTool tool = new ElectricTool();
			jszq_temp = tool.getQuot(lasttime, thistime)+1;//结算表周期
			double averagefees_temp = actualdianfei/jszq_temp;
			if (shiLimit() && averagefees_temp > averagefees) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "日均电费>"+averagefees+" 请在电费管理下电费单加强功能导入");
			}
		}
		
		return row;
	}
	/**针对哪些地市开启加强功能判断
	 * @return
	 */
	synchronized boolean shiLimit(){
		if(startshi.trim().equals("")){
			return false;
		}
		String[] str=startshi.split(",");
		int lengtha = str.length;
		for(int i=0;i<lengtha;i++){
			if(shi.equals(str[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断数据库中是否有这条数据(重复)， 数据库中有重复数据返回true，否则返回false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check23(Object[] content) {
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
				.valueOf(lastdegree), lasttime, dianbiaoid, bzmonth);
		if (temp) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电费录入重复，请检查！！！");
		}
		return row;

	}

	/**
	 * 查询 电表的分摊比例 ，针对是否分摊进行检查， 如果没有问题 开始本电费，电量的 分摊计算和赋值
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check24(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElecBillDao dao = new ElecBillDaoImp();
		ArrayList<DomainOther> list = new ArrayList<DomainOther>();
		DomainType bean = dao.getDomainType(dianbiaoid);
		list = dao.getDomainOther(dianbiaoid);
		boolean check = false;
		for (DomainOther other : list) {
			if ("".equals(other.getDomain()) || "0".equals(other.getDbzb())
					|| "".equals(other.getQcbkm())
					|| "".equals(other.getKjkm())
					|| "0.00".equals(other.getFtbl())
					|| "".equals(other.getZymx()) || bean == null) {
				check = true;
			}
		}
		if (check) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "分摊信息有误或者没有进行分摊，请到监测点管理进行查询！！");
		} else {
			//分摊电量(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
			double wucha = 0.0000001;
			dlzylx01 = Format
					.d2((Format.str_d(bean.getZylx01().toString())/100) * Format
							.str_d(blhdl)+wucha);
			dlzylx02 = Format
					.d2((Format.str_d(bean.getZylx02().toString())/100) * Format
					.str_d(blhdl)+wucha);
			dlzylx03 = Format
					.d2((Format.str_d(bean.getZylx03().toString())/100) * Format
					.str_d(blhdl)+wucha);
			dlzylx04 = Format
					.d2((Format.str_d(bean.getZylx04().toString())/100) * Format
					.str_d(blhdl)+wucha);
			dlzylx05 = Format
					.d2((Format.str_d(bean.getZylx05().toString())/100) * Format
					.str_d(blhdl)+wucha);
			dlzylx06 = Format
					.d2((Format.str_d(bean.getZylx06().toString())/100) * Format
					.str_d(blhdl)+wucha);
			
			dfzylx01 = Format
					.d2((Format.str_d(bean.getZylx01().toString())/100) * actualdianfei+wucha);
			dfzylx02 = Format
					.d2((Format.str_d(bean.getZylx02().toString())/100) * actualdianfei+wucha);
			dfzylx03 = Format
					.d2((Format.str_d(bean.getZylx03().toString())/100) * actualdianfei+wucha);
			dfzylx04 = Format
					.d2((Format.str_d(bean.getZylx04().toString())/100) * actualdianfei+wucha);
			dfzylx05 = Format
					.d2((Format.str_d(bean.getZylx05().toString())/100) * actualdianfei+wucha);
			dfzylx06 = Format
					.d2((Format.str_d(bean.getZylx06().toString())/100) * actualdianfei+wucha);

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
	synchronized Vector<String> check25(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4,cbbl;
		String[] df1, df2;
		String city = "1";//市级审核状态；审核标志
		String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
		String cityzr = "1";//市级主任审核状态；审核标志	
		
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		ElecBillDao dao = new ElecBillDaoImp();
		String[] shiandxian = tool.getShiAndXian(dbid);
        if(Format.str_d(beilv)==0){
       	 beilv = "1";
        }
        dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
        info.setDbydl(dbydl);
        info.setBeilv(beilv);
		
		info.setFloatdegree(String.valueOf(tzdegree));
		info.setMemo(dlmemo);
		info.setMemo1(dfmemo);
		info.setZlfh(zlfh);
		info.setJlfh(jlfh);
		info.setPropertycode(property);
		info.setStationtypecode(stationtype);
		info.setDfzflxcode(dfzflx);
		info.setGdfscode(gdfs);
		dl1 = tool.checkFloatDegree(info);
		dl2 = tool.checkDayDegree(dianbiaoid, thistime, lasttime, blhdl);
		dl3 = tool.checkBcdl(blhdl, thistime, lasttime, edhdl, dianbiaoid, null, "2");
		cbbl = tool.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
		dl4 = tool.checkBcdl2(cbbl[0]);
    	//String[] expecthigh = tool.checkExceptAndHigh(dbid, qsdbdl, Double.toString(actualdianfei), Double.toString(actualdegree), thistime, lasttime, null, null, "2",null,"2");//异常及高额
    	String[] expecthigh = tool.checkExceptAndHigh(dbid, String.valueOf(actualdianfei), blhdl, thistime, lasttime, String.valueOf(Format.str_d(cbbl[0])/100));//异常及高额
    	String[] site = tool.checckSite(dbid);//是否1.2万个点
    	String[] adjust1 = tool.adjustCheck1(String.valueOf(tzdianfei), String.valueOf(tzdegree));//电费，电量调整
    	String[] adjust2 = tool.adjustCheck2(lastunitprice, dianfei);//单价调整
    	String[] chayue = tool.chaYue(thistime, bzmonth);//本次抄表时间对应月份-报账月份>=2
    	String[] thiselecfee = tool.checkThisFees(String.valueOf(actualdianfei),shiandxian[0],shiandxian[1]);//本次电费金额大于区县上月平均电费金额
    	String[] outprice = new String[]{"1",""};//默认 外借电判断为通过（即没有进行该判断）
    	String[] adjustelec = tool.adjustElec(String.valueOf(tzdegree),beilv);//如果是5,6,7,8,9,10月份电量调整大于800需要四级审核  否则大于500
    	String[] adjustfeeandelec1 = tool.adjustFeeAndElec1(String.valueOf(tzdianfei), String.valueOf(tzdegree));//是否电费正调整大于10并且电量负调整
    	String[] adjustfeeandelec2 = tool.adjustFeeAndElec2(String.valueOf(tzdegree), String.valueOf(tzdianfei), dianfei);//电量（正）负调整，并且电费（正）负调整：（电量调整*单价-电费调整）/电费调整>1.1 
    	if("zdsx04".equals(expecthigh[2])){//属性为 其他 则 判断
    		outprice = tool.OutElecUnitPrice(dianfei, shiandxian[0], shiandxian[1]);
    	}
    	
    	dlmess = dlmess + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1] + adjustfeeandelec1[1];
		info.setFloatpay(String.valueOf(tzdianfei));
		if ("1".equals(dl1[0]) && "1".equals(dl2[0]) && "1".equals(dl3[0])
				&& "1".equals(dl4[0]) && "1".equals(outprice[0]) && "0".equals(adjustfeeandelec1[0])) {
			dlauto = "1";
			info.setMemo1(dfmemo);
			df1 = tool.checkElectric1(info);
			df2 = tool.checkElectric2(String.valueOf(pjje));
				dfmess = dfmess + df1[1] + df2[1] + outprice[1] + adjustfeeandelec1[1];
			if ("1".equals(df1[0]) && "1".equals(df2[0]) && "1".equals(outprice[0]) && "0".equals(adjustfeeandelec1[0])) {
				dfauto = "1";
			} else {
				dfauto = "0";
			}
		} else {
			dfauto = "0";
			dlauto = "0";
		}
			  
    	if("1".equals(adjust1[2]) || "1".equals(adjust2[0]) || "1".equals(chayue[0]) || "1".equals(thiselecfee[0])
    			 || "1".equals(adjustelec[0]) || "1".equals(adjustfeeandelec1[0]) || "1".equals(adjustfeeandelec2[0])){
    		qxzr = "0";
    		city = "0";
    		cityzr = "0";
    	}else if("1".equals(adjust1[0])){
    		qxzr = "0";
    		city = "0";
    	}
    	if("1".equals(expecthigh[0])){
    		qxzr = "0";
    		city = "0";
    		cityzr = "0";
    	}else if("0".equals(expecthigh[0])){
    		//qxzr = "1";
    		if("1".equals(site[0])){
    			city = "0";
    			cityzr = "0";
    		}else if("0".equals(site[0])){
    			//cityzr = "1";
    			if("0".equals(dl3[0]) || "0".equals(dl4[0])){
    				city = "0";
    				dfauto = "0";
    				dlauto = "0";
    			}
    		}
    	}
    	dfmess = dfmess + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//描述信息增加到电费表中

		info.setLastdegree(String.valueOf(lastdegree));
		info.setThisdegree(String.valueOf(thisdegree));
		info.setActualdegree(String.valueOf(sjydl));
		info.setLastdatetime(lasttime);
		info.setThisdatetime(thistime);
		info.setBlhdl(blhdl);
		info.setInputoperator(enterperson);
		info.setEntrypensonnel(accountname);
		info.setPayoperator(payperson);
		info.setLiuchenghao(liuchenghao == null ? "":liuchenghao);//预支流程号

		share.setNetworkhdl(dlzylx01);
		share.setMarkethdl(dlzylx02);
		share.setAdministrativehdl(dlzylx03);
		share.setInformatizationhdl(dlzylx04);
		share.setBuildhdl(dlzylx05);
		share.setDddl(dlzylx06);

		info.setUnitprice(dianfei);
		info.setAccountmonth(bzmonth);
		info.setActualpay(actualdianfei);
		info.setNotetypeid(pjlx);
		//info.setNoteno(pjbh);
		//info.setNotetime(pjtime);
		info.setKptime(kptime);
		//info.setPaydatetime(paytime);
		info.setPjje(pjje);
		info.setFlag(0);
		info.setEntrytime(entertime);
		share.setNetworkdf(dfzylx01);
		share.setMarketdf(dfzylx02);
		share.setAdministrativedf(dfzylx03);
		share.setInformatizationdf(dfzylx04);
		share.setBuilddf(dfzylx05);
		share.setDddf(dfzylx06);
		String jszq = dl3[4];//结算周期
		
		info.setScdf(scdf);
		info.setScdl(scdl);
		info.setLastunitprice(lastunitprice);
		
		hbzq = cbbl[1];//合并周期
		bzz = cbbl[2];//标准值
		// 主键生成
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);
		String temp = "0";
		
//		System.out.println("actualdianfei:"+actualdianfei);
//		System.out.println("actualdegree:"+actualdegree);
//		System.out.println("tzdianfei:"+tzdianfei);
//		System.out.println("tzdegree:"+tzdegree);
		
		String rtmess = dao.addDegreeFees(dianbiaoid, info, share, uuid, temp, dfmess, dfauto,
				dlmess, dlauto, dl3[3].toString().trim(),cbbl[0],city,qxzr,cityzr,jszq,dl3[5],qsdbdl,xgbzw,hbzq,bzz,scb);
		
        if(!"保存电费单成功！".equals(rtmess)){
        	for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "Error！");
        }
		return row;
	}
	/**
	 * 票据类型判断
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check26(Object[] content) {
		Vector<String> row = new Vector<String>();
		pjlx = content[16].toString().trim();
		if (content[16] == null || "".equals(content[16].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "票据类型为空！");
		} else {
			String sql = "SELECT I.CODE FROM INDEXS I WHERE I.NAME= ? AND I.TYPE='PJLX'";
			try {
				ds.connectDb();
				conn = ds.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, content[16].toString().trim());
				rs = ps.executeQuery();
				ds.commit();
				while(rs.next()){
					pjlx = rs.getString(1);
//					System.out.println(pjlx);
				}
				if(pjlx==null||"".equals(pjlx)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "票据类型不正确！");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "票据类型不正确！");
			}finally{
				ds.free(rs, ps, conn);
			}
            
		}
		return row;
	}
}
