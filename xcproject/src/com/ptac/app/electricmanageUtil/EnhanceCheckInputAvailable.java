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
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;

/**
 * @author xuzehua 电费单数据的增加，导入完成型检查
 */
public class EnhanceCheckInputAvailable {
	private double averagefees,exceptadjust,exceptlinechangeloss,backadjust1,backadjust2,backadjust3,
	sightvirtualheight1,sightvirtualheight2,escapeaudit1,escapeaudit2,feesheight,
	gapoversize1,gapoversize2,feesadjustexcept,elecoverproof,unitpriceexcept,averageunitprice;
	private String elefeesbl;
	String accountid,accountname;
	String dianbiaoid, dianfei, beilv, linelosstype, linelossvalue, changevalue, edhdl,
			dbid, qsdbdl, dfzflx , liuchenghao , gdfs,gdfscode,gllastdegree,glthisdegree,gllastdate,glthisdate,beilvexcept,
			stationtypecode,propertycode,zlfh,jlfh,scb,zdname;// 完全有值
	String scdf,scdl,lastunitprice;//上次电费，上次电量，上次单价
	String dbds="",xgbzw="",hbzq,bzz;
	String dlmess ="";
	String dfmess ="";
	String dlauto = "0";
	String dfauto = "0";
	double dlzylx01, dlzylx02, dlzylx03, dlzylx04, dlzylx05, dlzylx06;// 电量
	double dfzylx01, dfzylx02, dfzylx03, dfzylx04, dfzylx05, dfzylx06;// 电费
	String dyjdanjia,jfzb,jfz,bfzb,bfz,bpzb,bpz,bgzb,bgz;
	// 检测完成的数据（待导入）
	double floatdegrees, yddf,beilvtemp,danjia, lastdegree, thisdegree, tzdegree, actualdegree, tzdianfei,
			actualdianfei, pjje,pjdl,linelossvalue_temp,floatdegreeandbl,dbydl,jsrj;
	String  lasttimext,lasttimebg,lasttime, thistime, bzmonth, blhdl,sjydl,htendtime,
			enterperson;
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
	public EnhanceCheckInputAvailable() {
		getValues();
	}
	private  void getValues(){
		ElecBillDao dao = new ElecBillDaoImp();
		Map<String,String> map = dao.getValue("3");
		averagefees = Double.parseDouble(map.get("AverageFees"));
		exceptadjust = Double.parseDouble(map.get("ExceptAdjust"));
		exceptlinechangeloss = Double.parseDouble(map.get("ExceptLineChangeLoss"));
		backadjust1 = Double.parseDouble(map.get("BackAdjust1"));
		backadjust2 = Double.parseDouble(map.get("BackAdjust2"));
		backadjust3 = Double.parseDouble(map.get("BackAdjust3"));
		sightvirtualheight1 = Double.parseDouble(map.get("SightVirtualHeight1"));
		sightvirtualheight2 = Double.parseDouble(map.get("SightVirtualHeight2"));
		escapeaudit1 = Double.parseDouble(map.get("EscapeAudit1"));
		escapeaudit2 = Double.parseDouble(map.get("EscapeAudit2"));
		beilvexcept = map.get("BeilvExcept");
		feesheight = Double.parseDouble(map.get("FeesHeight"));
		gapoversize1 = Double.parseDouble(map.get("GapOversize1"));
		gapoversize2 = Double.parseDouble(map.get("GapOversize2"));
		feesadjustexcept = Double.parseDouble(map.get("FeesAdjustExcept"));
		elecoverproof = Double.parseDouble(map.get("ElecOverProof"));
		unitpriceexcept = Double.parseDouble(map.get("UnitPriceExcept"));
		elefeesbl = map.get("EleFeesBl");
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
		accountname = account.getName();
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
		if (row.isEmpty()) {
			row = this.check06(content);
		}
		if (row.isEmpty()) {
			row = this.check07(content);
		}
		if (row.isEmpty()) {
			row = this.check08(content);
		}
		if (row.isEmpty()) {
			row = this.check09(content);
		}
		if (row.isEmpty()) {
			row = this.check10(content);
		}
		if (row.isEmpty()) {
			row = this.check11(content);
		}
		if (row.isEmpty()) {
			row = this.check12(content);
		}
		if (row.isEmpty()) {
			row = this.check13(content);
		}
		if (row.isEmpty()) {
			row = this.check14(content);
		}
		if (row.isEmpty()) {
			row = this.check15(content);
		}
		if (row.isEmpty()) {
			row = this.check16(content);
		}
		if (row.isEmpty()) {
			row = this.check17(content);
		}
		if (row.isEmpty()) {
			row = this.check18(content);
		}
		if (row.isEmpty()) {
			row = this.check19(content);
		}
		if (row.isEmpty()) {
			row = this.check20(content);
		}
		if (row.isEmpty()) {
			row = this.check21(content);
		}
		if (row.isEmpty()) {
			row = this.check22(content);
		}
		if (row.isEmpty()) {
			row = this.check23(content);
		}
		if(row.isEmpty()){
			row = this.checkhtendtime(content);
		}
		if(row.isEmpty()){
        	row = this.check24(content);
        }
		if(row.isEmpty()){
        	row = this.check25(content);
        }
		if(row.isEmpty()){
        	row = this.check26(content);
        }
		if(row.isEmpty()){
        	row = this.check27(content);
        }
		if(row.isEmpty()){
        	row = this.check28(content);
        }
		if(row.isEmpty()){
        	row = this.check29(content);
        }
		if(row.isEmpty()){
        	row = this.check30(content);
        }
		if(row.isEmpty()){
        	row = this.check31(content);
        }
		if(row.isEmpty()){
        	row = this.check42(content);
        }
		if(row.isEmpty()){
        	row = this.check43(content);
        }
		if(row.isEmpty()){
        	row = this.check44(content);
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
		String sql = "SELECT D.DBID, D.DANJIA,D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,D.CHANGEVALUE,(SELECT NAME FROM INDEXS WHERE CODE = Z.GDFS AND TYPE = 'GDFS') GDFS,Z.GDFS GDFSCODE,Z.EDHDL,Z.QSDBDL" 
				+",D.DFZFLX,D.CSDS,TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') CSSYTIME,D.DBDS,D.XGBZW,FF.JFZ,FF.BFZ,FF.BPZ,FF.BGZ,Z.STATIONTYPE STATIONTYPECODE,Z.PROPERTY PROPERTYCODE,Z.ZLFH,Z.JLFH,Z.SCB,Z.JZNAME"
				+ " FROM ZHANDIAN Z, DIANBIAO D ,(SELECT FGP.CITY, DAG.AGCODE AGCODE,FGP.JFZ,FGP.BFZ,FGP.BPZ,FGP.BGZ FROM D_AREA_GRADE DAG , FGP WHERE DAG.AGCODE = FGP.CITY) FF WHERE Z.ID = D.ZDID " 
				+ "AND Z.SHI = FF.CITY(+) "
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
				if (Format.str_d(beilv.toString().trim()) == 0) {
					beilvtemp = 1.0;
				}else{
					beilvtemp = Double.parseDouble(beilv); 
				}
				linelosstype = rs.getString("linelosstype")==null?"":rs.getString("linelosstype");
				linelossvalue = rs.getString("linelossvalue")==null?"0":rs.getString("linelossvalue");
				changevalue = rs.getString("changevalue")==null?"0":rs.getString("changevalue");
				gdfs = rs.getString("gdfs")==null?"":rs.getString("gdfs");
				gdfscode = rs.getString("gdfscode")==null?"":rs.getString("gdfscode");
				edhdl = rs.getString("edhdl")==null?"":rs.getString("edhdl");
				qsdbdl = rs.getString("QSDBDL")==null?"":rs.getString("QSDBDL");
				dfzflx = rs.getString("DFZFLX")==null?"":rs.getString("DFZFLX");
				lastdatetime1 = rs.getString("CSSYTIME")==null?"":rs.getString("CSSYTIME");
				lastdegree1 = rs.getString("CSDS")==null?"":rs.getString("CSDS");
				dbds = rs.getString("DBDS")==null?"":rs.getString("DBDS");
				xgbzw = rs.getString("XGBZW")==null?"":rs.getString("XGBZW");
				jfz = rs.getString("jfz")==null?"":rs.getString("jfz");
				bfz = rs.getString("bfz")==null?"":rs.getString("bfz");
				bgz = rs.getString("bgz")==null?"":rs.getString("bgz");
				bpz = rs.getString("bpz")==null?"":rs.getString("bpz");
				stationtypecode =  rs.getString("STATIONTYPECODE")==null?"":rs.getString("STATIONTYPECODE");
				propertycode =  rs.getString("PROPERTYCODE")==null?"":rs.getString("PROPERTYCODE");
				zlfh =  rs.getString("ZLFH")==null?"":rs.getString("ZLFH");
				jlfh =  rs.getString("JLFH")==null?"":rs.getString("JLFH");
				scb = rs.getString("SCB")==null?"":rs.getString("SCB");
				zdname = rs.getString("JZNAME")==null?"":rs.getString("JZNAME");
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
		if ("".equals(propertycode) || "null".equals(propertycode)) {
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
	 * @author WangYiXiao 2014-12-10
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
					+ dianbiaoid + "系统中上次电表读数为空");
		} else {
			if (!Format.isNumber(lastd.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "系统中上次电表读数格式不正确");
			} else {
				if(Format.str_d(lastd.trim())<0){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "系统中上次电表读数必须大于等于0");
				}else{
					if (content[6] == null || "".equals(content[6].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "上次电表读数为空");
					} else {
						if (!Format.isNumber(content[6].toString().trim())) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString() + "电表"
									+ dianbiaoid + "上次电表读数格式不正确");
						} else {
							if(Format.str_d(content[6].toString().trim())<0){
								for (int j = 0; j < content.length; j++) {
									row.add(content[j].toString().trim());
								}
								row.add(content[0].toString() + content[2].toString() + "电表"
										+ dianbiaoid + "上次电表读数必须大于等于0");
							}else{
								if (!Format.str2(content[6].toString().trim()).equals(
										Format.str2(lastd.toString().trim()))) {
									for (int j = 0; j < content.length; j++) {
										row.add(content[j].toString().trim());
									}
									row.add(content[0].toString() + content[2].toString()
											+ "电表" + dianbiaoid + "上次电表读数与系统中上次电表读数不符合！ 系统上次电表读数为："
											+ Format.str2(lastd.toString().trim()));
								}else{
									lastdegree = Format.str_d(lastd.trim());
								}
							}	
						}
					}
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
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次电表读数为空");
		} else {
			if (!Format.isNumber(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次电表读数格式不正确");
			} else {
				if (Format.str_d(content[7].toString().trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "本次电表读数必须大于等于0");
				} else {
					thisdegree = Format.str_d(content[7].toString().trim());
				}
			}

		}
		return row;
	}

	/**
	 * 电量调整  不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check04(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[10] == null || "".equals(content[10].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电量调整 为空");
		} else {
			if (!Format.isNumber(content[10].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "电量调整 格式不正确");
			} else {
				floatdegrees = Format.d2(Format.str_d(content[10].toString().trim()));
			}

		}
		return row;
	}

	/**
	 * 报账电量 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check05(Object[] content) {
		
		Vector<String> row = new Vector<String>();
		if (content[11] == null || "".equals(content[11].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "报账电量为空");
		} else {
			if (!Format.isNumber(content[11].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "报账电量格式不正确");
			}
		}
		return row;
	}

	/**
	 * 电量备注 不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check06(Object[] content) {
		
		Vector<String> row = new Vector<String>();
		if(floatdegrees!=0){
			if (content[14] == null || "".equals(content[14].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "电量备注为空");
				return row;
			} 
		}
		if (content[14].toString().trim().length()>150) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电量备注不能超过150字");
		}else {
			dlmemo = content[14].toString().trim();
		}
		
		return row;
	}

	/**
	 * 尖峰占比不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check07(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			if (content[29] == null || "".equals(content[29].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "尖峰占比 为空");
			} else {
				if (!Format.isNumber(content[29].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "尖峰占比 格式不正确");
				} else {
					jfzb = content[29].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * 波峰占比不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			if (content[31] == null || "".equals(content[31].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "波峰占比 为空");
			} else {
				if (!Format.isNumber(content[31].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波峰占比 格式不正确");
				} else {
					bfzb = content[31].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * 波平占比不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check09(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			if (content[33] == null || "".equals(content[33].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "波平占比 为空");
			} else {
				if (!Format.isNumber(content[33].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波平占比 格式不正确");
				} else {
					bpzb = content[33].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * 波谷占比不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check10(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			
			if (content[35] == null || "".equals(content[35].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "波谷占比 为空");
			} else {
				if (!Format.isNumber(content[35].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波谷占比 格式不正确");
				} else {
					bgzb = content[35].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * 尖峰波峰波平波谷占比和为100%
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check11(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			if (Format.d2(Format.str_d(jfzb)) + Format.d2(Format.str_d(bfzb)) + Format.d2(Format.str_d(bpzb)) + Format.d2(Format.str_d(bgzb)) != 100.00) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "尖峰占比，波谷占比，波峰占比，波平占比和不等于100%");
			} 
		}
		return row;
	}	
	/**
	 * 尖峰值 不为空 格式 一致
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
				if (content[30] == null || "".equals(content[30].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "尖峰值 为空");
				} else {
					if (!Format.isNumber(content[30].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "尖峰值 格式不正确");
					} else {
						if (!Format.str4(content[30].toString().trim()).equals(
								Format.str4(jfz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "电表" + dianbiaoid + " 尖峰值不能修改，尖峰值与 系统尖峰值不符！ 系统尖峰值为："
									+ Format.str4(jfz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * 波峰值 不为空 格式 一致
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check13(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
				if (content[32] == null || "".equals(content[32].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波峰值 为空");
				} else {
					if (!Format.isNumber(content[32].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "波峰值 格式不正确");
					} else {
						if (!Format.str4(content[32].toString().trim()).equals(
								Format.str4(bfz.toString().trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "电表" + dianbiaoid + " 波峰值不能修改，波峰值与 系统波峰值不符！ 系统波峰值为："
									+ Format.str4(bfz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * 波平值 不为空 格式 一致
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check14(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
				if (content[34] == null || "".equals(content[34].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波平值 为空");
				} else {
					if (!Format.isNumber(content[34].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "波平值 格式不正确");
					} else {
						if (!Format.str4(content[34].toString().trim()).equals(
								Format.str4(bpz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "电表" + dianbiaoid + " 波平值不能修改，波平值与 系统波平值不符！ 系统波平值为："
									+ Format.str4(bpz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * 波谷值 不为空 格式 一致
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check15(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
				if (content[36] == null || "".equals(content[36].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "波谷值 为空");
				} else {
					if (!Format.isNumber(content[36].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "波谷值 格式不正确");
					} else {
						if (!Format.str4(content[36].toString().trim()).equals(
								Format.str4(bgz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "电表" + dianbiaoid + " 波谷值不能修改，波谷值与 系统波谷值不符！ 系统波谷值为："
									+ Format.str4(bgz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * 本次单价 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check16(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
			double dyjdanjiatemp = Format.d4(Format.str_d(Format.str2(jfzb))/100*Format.str_d(Format.str4(jfz))+
			Format.str_d(Format.str2(bfzb))/100*Format.str_d(Format.str4(bfz))+
			Format.str_d(Format.str2(bgzb))/100*Format.str_d(Format.str4(bgz))+
			Format.str_d(Format.str2(bpzb))/100*Format.str_d(Format.str4(bpz)));
			if(dyjdanjiatemp == Format.d4(Format.str_d(content[16].toString().trim()))){
				dyjdanjia = Format.str4(content[16].toString().trim());
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "电业局单价 计算有误，系统计算值为："+dyjdanjiatemp);
			}
		}else {//转供电if("转供电".equals(gdfs))
			if (content[15] == null || "".equals(content[15].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "转供电单价 为空");
			} else {
				if (!Format.isNumber(content[15].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "转供电单价 格式不正确");
				} else {
					if (!Format.str4(content[15].toString().trim()).equals(
							Format.str4(dianfei.toString().trim()))) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString()
								+ "电表" + dianbiaoid + "转供电单价  与电表的实际单价不符合！ 系统转供电单价为："
								+ Format.str4(dianfei.toString().trim()));
					}
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
	synchronized Vector<String> check17(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[17] == null || "".equals(content[17].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "费用调整 为空");
		} else {
			if (!Format.isNumber(content[17].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "费用调整 格式不正确");
			} else {
				tzdianfei = Format.d2(Format.str_d(content[17].toString().trim()));
			}
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
	synchronized Vector<String> check18(Object[] content) {
		Vector<String> row = new Vector<String>();
		pjlx = content[19].toString().trim();
		if (content[19] == null || "".equals(content[19].toString().trim())) {
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
				ps.setString(1, content[19].toString().trim());
				rs = ps.executeQuery();
				ds.commit();
				while(rs.next()){
					pjlx = rs.getString(1);
				}
				if(pjlx==null||"".equals(pjlx)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "票据类型不正确！");
				}
			} catch (Exception e) {
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

	/**
	 * 报账电费 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check19(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[18] == null || "".equals(content[18].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "报账电费 为空");
		} else {
			if (!Format.isNumber(content[18].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "报账电费  格式不正确");
			} else {
				actualdianfei = Format.d2(Format.str_d(content[18].toString().trim()));
			}
		}
		return row;
	}
	
	/**
	 * 电费备注 不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check20(Object[] content) {
		
		Vector<String> row = new Vector<String>();
		if(tzdianfei!=0){
			if (content[24] == null || "".equals(content[24].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "电费备注为空");
				return row;
			}
		}
		if (content[24].toString().trim().length()>150) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电费备注不能超过150字");
		} else {
			dfmemo = content[24].toString().trim();
		}
		return row;
	}

	/**
	 * 上次抄表时间 不为空 格式
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check21(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastt == null || "".equals(lastt.trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统上次抄表时间   为空");
		} else {
			if (!Format.isTime(lastt.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "系统上次抄表时间   格式不正确");
			} else {
				if (content[8] == null || "".equals(content[8].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "上次抄表时间   为空");
				} else {
					if (!Format.isTime(content[8].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "上次抄表时间   格式不正确");
					} else {
						if (Format.isTime02(lastt.trim())) {
							lasttimext = Format.getTime(lastt.trim());
						}else{
						lasttimext = lastt.trim();
						}
						if (Format.isTime02(content[8].toString().trim())) {
							lasttimebg = Format.getTime(content[8].toString().trim());
						}else{
						lasttimebg = content[8].toString().trim();
						}
						
						if (!lasttimext.equals(lasttimebg)) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "电表" + dianbiaoid + "上次抄表时间与系统中上次抄表时间不符合！ 系统上次抄表时间为："
									+ lasttimext);
						}else{
							if (Format.isTime02(lastt.trim())) {
								lasttime = Format.getTime(lastt.trim());
							}else{
							lasttime = lastt.trim();
							}
						}
					}
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
	synchronized Vector<String> check22(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次抄表时间   为空");
		} else {
			if (!Format.isTime(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次抄表时间   格式不正确");
			} else {
				Date now = new Date();
				Date nowday = new Date(now.getYear(),now.getMonth(),now.getDate());
				Date thist = null;
				if (Format.isTime02(content[9].toString().trim())) {
				try {
					thist = new SimpleDateFormat("yyyy/MM/dd").parse(content[9].toString().trim());
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
					thistime = Format.getTime(content[9].toString().trim());
				}
				}else{
					try {
						thist = new SimpleDateFormat("yyyy-MM-dd").parse(content[9].toString().trim());
						if(thist.after(nowday)){
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString() + "电表"
									+ dianbiaoid + "本次抄表时间   大于录入时间");
						}else{
							thistime = content[9].toString().trim();
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
	synchronized Vector<String> check23(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastt != null && !"".equals(lastt.trim())
				&& content[9] != null
				&& !"".equals(content[9].toString().trim())
				&& Format.isTime(content[9].toString().trim())
				&& Format.isTime(lastt.trim())) {
			Date lasttime = Format.String2Time(lastt.trim());
			Date thistime = Format.String2Time(content[9].toString().trim());
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
				Date thistimea = Format.String2Time(content[9].toString().trim());
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

	synchronized Vector<String> check24(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[12] == null || "".equals(content[12].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "抄表操作员 为空");
		} else {
			enterperson = content[12].toString().trim();
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
	synchronized Vector<String> check25(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[13] != null && !"".equals(content[13])) {
			if (!Format.isTime(content[13].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "抄表时间格式不正确");
			} else {
				if (Format.isTime02(content[13].toString().trim())) {
					entertime = Format.getTime(content[13].toString().trim());
				} else {
					entertime = content[13].toString().trim();
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
	synchronized Vector<String> check26(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[20] == null || "".equals(content[20].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "票据金额   为空");
		}else{
			if (!Format.isNumber(content[20].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "票据金额 格式不正确");
			} else {
				pjje = Format.str_d(content[20].toString().trim());
			}
		}
		return row;
	}

	/**
	 * 票据电量
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check27(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[21] == null || "".equals(content[21].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "票据电量  为空");
		}else{
			if (!Format.isNumber(content[21].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "票据电量 格式不正确");
			} else {
				pjdl = Format.str_d(content[21].toString().trim());
			}
		}
		return row;
	}
	/**
	 * 开票时间
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check28(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[22] != null && !"".equals(content[22])) {
			if (!Format.isTime(content[22].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "开票时间 格式不正确");
			} else {
				if (Format.isTime02(content[22].toString().trim())) {
					kptime = Format.getTime(content[22].toString().trim());
				} else {
					kptime = content[22].toString().trim();
				}

			}
		}
		return row;
	}

	/**电量调整*倍率：不能大于（变损值+线损值）
	 * @return
	 */
	synchronized Vector<String> check29(Object[] content){
		Vector<String> row = new Vector<String>();
		linelossvalue_temp  = 0.0;
		double thisdegrees = Format.d2(Format.str_d(content[7].toString().trim()));//本次电表读数
		double lastdegrees = Format.d2(Format.str_d(lastd.trim()));//上次电表读数
		
		if ("02xsbl".equals(linelosstype)) {//线损比例
			linelossvalue_temp = Format.d2((thisdegrees - lastdegrees)*Format.str_d(linelossvalue.toString().trim()));
		} else if("01xstz".equals(linelosstype)){//线损调整
			linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
		}else{
			linelossvalue_temp = 0.0;
		}
		return row;
	}
	/**
	 * 比较实际用电量和实际交费金额 包括电费的计算 前提，
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check30(Object[] content) {
		Vector<String> row = new Vector<String>();
		double dl = 0.00, df = 0.00;
		double sjdl = 0.00;//
		
		double thisdegrees = Format.d2(Format.str_d(content[7].toString().trim()));//本次电表读数
		double lastdegrees = Format.d2(Format.str_d(lastd.trim()));//上次电表读数
		
		if (content[11] != null && !"".equals(content[11].toString().trim())) {
				//电表用电量 = （本次电表读数-上次电表读数）*倍率
				//报账电量 = 电表用电量+电量调整*倍率；
				// 保留两位小数
				sjdl = Format.d2((thisdegrees - lastdegrees)*beilvtemp);//电表用电量
				dl = Format.d2(sjdl + (Format.str_d(changevalue) + linelossvalue_temp + floatdegrees)*beilvtemp);//报账电量
						
				if (dl != Format.d2(Format.str_d(content[11].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "报账电量计算错误 ，系统计算报账电量为：" + dl);
					return row;
				} else {
					blhdl = Double.toString(dl);//倍率耗电量
					sjydl = Double.toString(sjdl);//电表用电量
					dbydl = sjdl;//电表用电量
				}
			
			if (content[18] != null
					&& !"".equals(content[18].toString().trim())) {
				//用电电费=直供电：报账电量*电业局单价；转供电：报账电量*转供电单价；
				//报账电费 = 报账电量*单价+电费调整；
				if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){//直供电
					danjia = Format.str_d(dyjdanjia);
				}else {//转供电if("转供电".equals(gdfs))
					danjia = Format.str_d(dianfei);
				}
				double temp = Format.d2(Format.str_d(content[17].toString().trim()));//费用调整
				yddf = Format.d2(dl*danjia);//用电电费
				df = yddf + temp;//报账电费
				if (df != Format.d2(Format.str_d(content[18].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "电表" + dianbiaoid + "报账电费计算错误 ，系统计算报账电费为：" + df);
					return row;
				} else {
					// 数据处理没有问题，针对Excel 的调查
				
					//pjlx = content[19].toString().trim();
					//pjbh = content[20].toString().trim();
					payperson = content[23].toString().trim();
				}
			}
		}

		return row;
	}
	
	/**电量调整>500/倍率
	 * @param content
	 * @return
	 */
//	11synchronized Vector<String> check30(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (floatdegrees>500/beilvtemp) {
//			
//		} else {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + "电量调整>500/倍率 请在电费单导入");
//		}
//		return row;
//	}
	/**电费调整>60在该页面录入；
	 * @param content
	 * @return
	 */
//	11synchronized Vector<String> check31(Object[] content) {
//		Vector<String> row = new Vector<String>();
//		if (tzdianfei>60) {
//			
//		} else {
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + "电费调整>60 请在电费单导入");
//		}
//		return row;
//	}
//	/**若报账电量/周期>20则（结算表日均量-管理表日均量）/管理表日均量（空调季节按含空调系数计算）大于20%不得提交成功；
//	 * @param content
//	 * @return
//	 */
//	22synchronized Vector<String> check32(Object[] content) {
//		Vector<String> row = new Vector<String>();
//     ElecModifyDao dao = new ElecModifyDaoImp();
//     ElectricityInfo info = dao.elec1(dianbiaoid);
//     String gllastdegree = info.getLastdegree();
//     String glthisdegree = info.getThisdegree();
//     String gllastdate = info.getLastdatetime();
//     String glthisdate = info.getThisdatetime();
//     ElectricTool tool = new ElectricTool();
//     long glts = tool.getQuot(gllastdate, glthisdate) + 1;//管理表周期
//     long jsts = tool.getQuot(lasttime, thistime)+1;//结算表周期
//     double bzbili = 0.2;
//		if(!"".equals(gllastdegree.trim())){
//			if(!"".equals(glthisdegree.trim())){
//				if(!"".equals(gllastdate.trim())){
//					if(!"".equals(glthisdate.trim())){
//						
//							double jsrj = Format.str_d(blhdl)/jsts;
//							double glhdl = Format.str_d(glthisdegree) - Format.str_d(gllastdegree);
//							double glrj = glhdl/glts;
//							if(jsrj>20){//报账电量/周期>20
//								//比较
//								if(glhdl!=0){
//										double bili = (jsrj-glrj)/glrj;
//										if(bili>bzbili){
//											for (int j = 0; j < content.length; j++) {
//												row.add(content[j].toString().trim());
//											}
//											row.add(content[0].toString() + content[2].toString() + "电表"
//													+ dianbiaoid + " 结算表日均量-管理表日均量)/管理表日均量>20%，需核实电量后提交!");
//										}
//								}else{
//									for (int j = 0; j < content.length; j++) {
//										row.add(content[j].toString().trim());
//									}
//									row.add(content[0].toString() + content[2].toString() + "电表"
//											+ dianbiaoid + " 管理电量为空!管理表信息录入不规范，请先确认管理表数据！");
//								}
//							}
//					}else{
//						for (int j = 0; j < content.length; j++) {
//							row.add(content[j].toString().trim());
//						}
//						row.add(content[0].toString() + content[2].toString() + "电表"
//								+ dianbiaoid + " 管理表本次抄表时间为空，请先录入管理表数据！");
//					}
//				}else{
//					for (int j = 0; j < content.length; j++) {
//						row.add(content[j].toString().trim());
//					}
//					row.add(content[0].toString() + content[2].toString() + "电表"
//							+ dianbiaoid + " 管理表上次抄表时间为空，请先录入管理表数据！");
//				}
//			}else{
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "电表"
//						+ dianbiaoid + " 管理表本次电表读数为空，请先录入管理表数据！");
//			}
//		}else{
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "电表"
//					+ dianbiaoid + " 管理表上次电表读数为空，请先录入管理表数据！");
//		}
//		return row;
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//日均电费>6的进行12个判断：日均电费<=6 去原电费单中录入。
	synchronized Vector<String> check31(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool tool = new ElectricTool();
		jszq_temp = tool.getQuot(lasttime, thistime)+1;//结算表周期
		double averagefees_temp = actualdianfei/jszq_temp;
		if (averagefees_temp > averagefees) {
			row = this.check32(content);
			if(row.isEmpty()){
				row = this.check33(content);
			}
			if(row.isEmpty()){
				row = this.check34(content);			
			}
			if(row.isEmpty()){
				row = this.check35(content);
			}
			if(row.isEmpty()){
				row = this.check36(content);
			}
//			if(row.isEmpty()){
//				row = this.check37(content);
//			}
//			if(row.isEmpty()){
//				row = this.check38(content);
//			}
//			if(row.isEmpty()){
//				row = this.check39(content);
//			}
			if(row.isEmpty()){
				row = this.check40(content);
			}
			if(row.isEmpty()){
				row = this.check41(content);
			}
			if(row.isEmpty()){
				row = this.checkcbdf(content);
			}
		} else {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "日均电费<="+averagefees+" 请在电费单导入");
		}
		return row;
	}
	
	//1、异常调整：电量调整*倍率：不能大于电表用电量*5%
	synchronized Vector<String> check32(Object[] content) {
		Vector<String> row = new Vector<String>();
		floatdegreeandbl = floatdegrees * beilvtemp;//电量调整*倍率
		
		if (floatdegreeandbl > dbydl * (exceptadjust/100)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "异常调整!");
		}
		return row;
	}
	
	//2、异常线变损：（线损值+变损值）*倍率：不能大于电表用电量*10%（比例可调）；
	synchronized Vector<String> check33(Object[] content) {
		Vector<String> row = new Vector<String>();
		double lineandchangeandbl = (linelossvalue_temp + Format.str_d(changevalue)) * beilvtemp;//电量调整*倍率
		
		if (lineandchangeandbl > dbydl * (exceptlinechangeloss/100)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "异常线变损!");
		}
		return row;
	}
	
	//3、逆向调整：电量调整*倍率<-1并且电费调整>1：不能在此录入；
	//2015-2-10 加强流程：逆向调整：电量为负，电费为正；再判断：|电量调整*倍率*单价/电费调整| >1.5 可以录入，反之提示逆向调整，不能录入。
/*	synchronized Vector<String> check34(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (floatdegreeandbl < backadjust1 && tzdianfei > backadjust2) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "逆向调整!");
		}
		return row;
	}*/
	synchronized Vector<String> check36(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (floatdegreeandbl < backadjust1 && tzdianfei > backadjust2) {
			if(Math.abs(Format.d2(floatdegreeandbl*danjia/tzdianfei))>backadjust3){
				
			}else{
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "逆向调整!");
			}
		}
		return row;
	}
	
	//4、标杆虚高：（报账电量/周期—省定标电量）/省定标电量 < -0.2并且(电量调整+线损值+变损值）*倍率 > 1；
	//5、躲避审核：（报账电量/周期—省定标电量）/省定标电量 < 0.1并且(电量调整+线损值+变损值）*倍率 < -1；
	//11、电量超标：（报账电量/周期—省定标电量）/省定标电量> 15%
	synchronized Vector<String> check35(Object[] content) {
		Vector<String> row = new Vector<String>();
		jsrj = Format.str_d(blhdl)/jszq_temp;//报账电量/周期
		double bjqq =  (jsrj - Format.str_d(qsdbdl))/Format.str_d(qsdbdl);//（报账电量/周期—省定标电量）/省定标电量
		double dxbb = (floatdegrees + linelossvalue_temp + Format.str_d(changevalue)) * beilvtemp;//(电量调整+线损值+变损值）*倍率
				if(bjqq < sightvirtualheight1 && dxbb > sightvirtualheight2){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "电表"
							+ dianbiaoid + "标杆虚高!");
				}else{
					if(bjqq < escapeaudit1 && dxbb < escapeaudit2){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + "躲避审核!");
					}else{
						if(bjqq > (elecoverproof/100)){
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString() + "电表"
									+ dianbiaoid + "电量超标!");
						}
					}
				}
			
		
		return row;
	}
	
	//6、倍率异常：倍率小于1；或倍率 / 5 不为正整数；提示倍率异常
	//2015-2-11 加强流程：倍率异常：倍率：只能为5；2；3的倍数，反之提示倍率异常，不能录入
	synchronized Vector<String> check34(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] bei = beilvexcept.split(",");
		boolean b = new ElectricTool().beilvBei(beilv, bei);//判断倍率是否是某些数字的整数倍
		if(Format.str_d(beilv) < 1 || (Format.str_d(beilv) != 1 && b)){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "倍率异常!");
		}
		return row;
	}
	
	//7、电费调整过高：电费调整>60；
	synchronized Vector<String> check37(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(tzdianfei > feesheight){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电费调整过高!");
		}
		return row;
	}
	//8、管理电量抄表不实：管理电量的本次抄表时间与结算表的本次抄表时间不同月或起止码一样或为未找到改记录，提示管理电量抄表不实；
	synchronized Vector<String> check38(Object[] content) {
		Vector<String> row = new Vector<String>();
     ElecModifyDao dao = new ElecModifyDaoImp();
     ElectricityInfo info = dao.elec1(dianbiaoid);
     gllastdegree = info.getLastdegree();
     glthisdegree = info.getThisdegree();
     gllastdate = info.getLastdatetime();
     glthisdate = info.getThisdatetime();
     boolean gldlbz = info.isGldlbz();
	     if(gldlbz == false){//未找到记录
	    	 for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "管理电量抄表不实!");
	 	}else{
	 		int jsyue = Integer.parseInt(thistime.substring(5,6));//结算表本次抄表时间月份
	 		int glyue = Integer.parseInt(glthisdate.substring(5,6));//管理表本次抄表时间月份
	 		if(gllastdegree.equals(glthisdegree) || jsyue > glyue){//本次抄表时间不同月或起止码一样
	 			 for (int j = 0; j < content.length; j++) {
	 				row.add(content[j].toString().trim());
	 			}
	 			row.add(content[0].toString() + content[2].toString() + "电表"
	 					+ dianbiaoid + "管理电量抄表不实!");
	 		}
	 	}
	     return row;
	}
	
	//9.报账和抄表差距过大：报账电量/周期>8则（报账电量/周期—管理表电表用电量/周期）/管理表电表电量/周期）（空调季节按含空调系数计算）大于15%（比例可调）不得录入。
	synchronized Vector<String> check39(Object[] content) {
		Vector<String> row = new Vector<String>();
		double jsrj = Format.str_d(blhdl)/jszq_temp;
		double glhdl = Format.str_d(glthisdegree) - Format.str_d(gllastdegree);
		ElectricTool tool = new ElectricTool();
		long glts = tool.getQuot(gllastdate, glthisdate) + 1;//管理表周期
		double glrj = glhdl/glts;
		if(jsrj>20){//报账电量/周期>20
			//比较
			if(glhdl!=0){
					double bili = (jsrj-glrj)/glrj;
					if(bili>(gapoversize2/100)){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "电表"
								+ dianbiaoid + " 报账和抄表差距过大!");
					}
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "管理电量抄表不实!");
			}
		}
		
		if(tzdianfei / yddf > feesadjustexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电费调整异常!");
		}
		return row;
	}
	
	//10、电费调整异常：电费调整 / 用电电费>5%；
	synchronized Vector<String> check40(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(tzdianfei / yddf > feesadjustexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "电费调整异常!");
		}
		return row;
	}
	
	//12、单价异常：（单价—截止本年度当前的该地市的平均单价）/ 截止本年度当前的该地市的平均单价 > 20%；
	//2015-2-11改为 加强流程：单价异常：报账电费 / 报账电量 > 标准单价*（1+xx%），提示单价异常
	synchronized Vector<String> check41(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool elecToo = new ElectricTool();
		String str = elecToo.selectUnitprice(propertycode, zdname, stationtypecode, gdfscode);
		ElecBillDao dao = new ElecBillDaoImp();
		averageunitprice = Format.str_d(dao.getAverageUnitPrice(dbid,str));//标准单价
/*		if((danjia - averageunitprice) / averageunitprice > unitpriceexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "单价异常!");
		}*/
		double a = Format.d4(actualdianfei/Format.str_d(blhdl));
		double b = Format.d4(averageunitprice*(1+unitpriceexcept/100));
		if( a > b ){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "单价异常!");
		}
		return row;
	}
	/**电费超标比例
	 * @param content
	 * @return
	 */
	synchronized Vector<String> checkcbdf(Object[] content) {
		Vector<String> row = new Vector<String>();
		 ElectricTool elecToo = new ElectricTool();
		 String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
		 String[] shiandxian = elecToo.getShiAndXian(dbid);
		 String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], propertycode, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22超省标比例,合并周期,标准值
		double dfcbbl = Format.str_d(elecToo.getOverFeesbl(cbbl[2], String.valueOf(averageunitprice), String.valueOf(actualdianfei)));
		
		if("N".equals(elefeesbl)){
			
		}else{
			if(dfcbbl>Format.str_d(elefeesbl)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "电费超标比例大于"+elefeesbl+"%,不能录入!");
			}
		}
		return row;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 判断数据库中是否有这条数据(重复)， 数据库中有重复数据返回true，否则返回false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check42(Object[] content) {
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
	synchronized Vector<String> check43(Object[] content) {
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
	synchronized Vector<String> check44(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4;
		String[] df1, df2;
		String city = "1";//市级审核状态；审核标志
		String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
		String cityzr = "1";//市级主任审核状态；审核标志	
		
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		ElecBillDao dao = new ElecBillDaoImp();
		String[] shiandxian = tool.getShiAndXian(dbid);
		
        String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
        info.setDbydl(dbydl);
		info.setFloatdegree(String.valueOf(floatdegrees));
		info.setMemo(dlmemo);
		info.setMemo1(dfmemo);
		dl1 = tool.checkFloatDegree(info);
		dl2 = tool.checkDayDegree(dianbiaoid, thistime, lasttime, blhdl);
		dl3 = tool.checkBcdl(blhdl, thistime, lasttime, edhdl, dianbiaoid, null, "2");
		String[] cbbl = tool.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], propertycode, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22超省标比例,合并周期,标准值
		dl4 = tool.checkBcdl2(cbbl[0]);
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

         if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){
        	 info.setJfzb(jfzb);
             info.setBfzb(bfzb);
             info.setBgzb(bgzb);
             info.setBpzb(bpzb);   
         }else {//if("转供电".equals(gdfs))
        	 info.setJfzb("''");
             info.setBfzb("''");
             info.setBgzb("''");
             info.setBpzb("''"); 
         }
         
		info.setLastdegree(String.valueOf(lastdegree));
		info.setThisdegree(String.valueOf(thisdegree));
		info.setDbydl(String.valueOf(sjydl));
		info.setLastdatetime(lasttime);
		info.setThisdatetime(thistime);
		info.setBlhdl(blhdl);
		info.setInputoperator(enterperson);
		info.setEntrypensonnel(accountname);
		info.setPayoperator(payperson);
		info.setLiuchenghao(liuchenghao == null ? "":liuchenghao);//预支流程号
		info.setYddf(String.valueOf(yddf));

		share.setNetworkhdl(dlzylx01);
		share.setMarkethdl(dlzylx02);
		share.setAdministrativehdl(dlzylx03);
		share.setInformatizationhdl(dlzylx04);
		share.setBuildhdl(dlzylx05);
		share.setDddl(dlzylx06);

		info.setTbtzsq("1");
		info.setUnitprice(String.valueOf(danjia));
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
		info.setPjdl(String.valueOf(pjdl));
		info.setScdf(scdf);
		info.setScdl(scdl);
		info.setLastunitprice(lastunitprice);
		 //2014-7-17
		info.setStationtypecode(stationtypecode);
		info.setPropertycode(propertycode);
		info.setDfzflxcode(dfzflx);
		info.setGdfscode(gdfscode);
		info.setZlfh(zlfh);
		info.setJlfh(jlfh);
		info.setBeilv(beilv.trim());
		info.setChangevalue(changevalue);
		info.setLinelosstype(linelosstype);
		info.setLinelossvalue(linelossvalue);
		info.setActuallinelossvalue(String.valueOf(linelossvalue_temp));
    	String hbzq = cbbl[1];//合并周期
    	String bzz = cbbl[2];//标准值
		// 主键生成
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);
		String temp = "0";
		String rtmess = dao.addDegreeFees(dianbiaoid, info, share, uuid, temp, dfmess, dfauto,
				dlmess, dlauto, dl3[3].toString().trim(),cbbl[0].toString().trim(),city,qxzr,cityzr,jszq,dl3[5],qsdbdl,xgbzw,hbzq,bzz,scb);
		
        if(!"保存电费单成功！".equals(rtmess)){
        	for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "Error！");
        }
		return row;
	}
}
