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
 * @author xuzehua ��ѵ����ݵ����ӣ���������ͼ��
 */
public class EnhanceCheckInputAvailable {
	private double averagefees,exceptadjust,exceptlinechangeloss,backadjust1,backadjust2,backadjust3,
	sightvirtualheight1,sightvirtualheight2,escapeaudit1,escapeaudit2,feesheight,
	gapoversize1,gapoversize2,feesadjustexcept,elecoverproof,unitpriceexcept,averageunitprice;
	private String elefeesbl;
	String accountid,accountname;
	String dianbiaoid, dianfei, beilv, linelosstype, linelossvalue, changevalue, edhdl,
			dbid, qsdbdl, dfzflx , liuchenghao , gdfs,gdfscode,gllastdegree,glthisdegree,gllastdate,glthisdate,beilvexcept,
			stationtypecode,propertycode,zlfh,jlfh,scb,zdname;// ��ȫ��ֵ
	String scdf,scdl,lastunitprice;//�ϴε�ѣ��ϴε������ϴε���
	String dbds="",xgbzw="",hbzq,bzz;
	String dlmess ="";
	String dfmess ="";
	String dlauto = "0";
	String dfauto = "0";
	double dlzylx01, dlzylx02, dlzylx03, dlzylx04, dlzylx05, dlzylx06;// ����
	double dfzylx01, dfzylx02, dfzylx03, dfzylx04, dfzylx05, dfzylx06;// ���
	String dyjdanjia,jfzb,jfz,bfzb,bfz,bpzb,bpz,bgzb,bgz;
	// �����ɵ����ݣ������룩
	double floatdegrees, yddf,beilvtemp,danjia, lastdegree, thisdegree, tzdegree, actualdegree, tzdianfei,
			actualdianfei, pjje,pjdl,linelossvalue_temp,floatdegreeandbl,dbydl,jsrj;
	String  lasttimext,lasttimebg,lasttime, thistime, bzmonth, blhdl,sjydl,htendtime,
			enterperson;
	String dlmemo = "";String dfmemo = "";String entertime = "";
	String payperson = "";String pjlx = "";String kptime ="";
	String lastd,lastt;//�ϴε��������ϴγ���ʱ��   (��ʱ����)   
	long jszq_temp;//��������
	private DataBase ds = new DataBase();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	/**
	 * ��ѵ� ����� ��� excel��������ȷ�Լ��� ��ѵ������ظ��Լ��
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
	 *            ��������
	 * @param wrong
	 *            ��������
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		accountid = account.getAccountId();
		accountname = account.getName();
		Vector<String> row = new Vector<String>();

		// ��������
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
	 * ���id check
	 * 
	 * @param wrong
	 * @return ���ӵ��� ����
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
			System.out.println("������Ϣ��ѯ"+sql);
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
				if("dfzflx03".equals(dfzflx)){//Ԥ֧
					liuchenghao = dao.getLiuchenghao(dbid);
				}else if("dfzflx02".equals(dfzflx)){//��ͬ
					String htt = dao.getHtEndTime(dbid);
					htendtime = htt==null?"":htt;
				}
			}
			System.out.println("�ϴγ���ʱ�䣬�ϴε�����,�ϴε�ѣ��ϴε������ϴε��۲�ѯ");
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
				c.set(Calendar.DATE, day + 1);//�ϴγ���ʱ��Ҫ��һ��
				lastt = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				}
			}else{
				lastt = lastdatetime1;
				lastd = lastdegree1;
			}
			 if("1".equals(xgbzw)){//����޸ĵ�������־λΪ1  �ϴε���������  ����޸Ķ�������Ϣ�޸ģ�
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
			row.add("δ�鵽" + content[0].toString() + content[2].toString()
					+ "���" + dianbiaoid);
		}
		dianbiaoid = content[3].toString().trim();
		return row;
	}

	/**
	 * ȫʡ������� ��Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��ȫʡ�������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * ��ĵ�����Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ�ж�ĵ���Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * ֱ�����ɲ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��ֱ������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * �������ɲ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ�н�������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * �����겻Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * վ�����Բ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��վ������Ϊ�գ�");
		} 
		return row;
	}
	
	/**
	 * �������վ��δ����վ��ID�����
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	synchronized Vector<String> checkOutAndConnect(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElecBillDao dao = new ElecBillDaoImp();
		if(dao.getOut(dbid)){//���������,�������վ��δ����վ��ID�����
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ȹ�����վ��ID�ţ���¼���ѣ�");
			
		} 
		return row;
	}
	/**
	 * �ϴε����� ��Ϊ�� ����ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ���ϴε�����Ϊ��");
		} else {
			if (!Format.isNumber(lastd.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "ϵͳ���ϴε�������ʽ����ȷ");
			} else {
				if(Format.str_d(lastd.trim())<0){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "ϵͳ���ϴε�����������ڵ���0");
				}else{
					if (content[6] == null || "".equals(content[6].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "�ϴε�����Ϊ��");
					} else {
						if (!Format.isNumber(content[6].toString().trim())) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString() + "���"
									+ dianbiaoid + "�ϴε�������ʽ����ȷ");
						} else {
							if(Format.str_d(content[6].toString().trim())<0){
								for (int j = 0; j < content.length; j++) {
									row.add(content[j].toString().trim());
								}
								row.add(content[0].toString() + content[2].toString() + "���"
										+ dianbiaoid + "�ϴε�����������ڵ���0");
							}else{
								if (!Format.str2(content[6].toString().trim()).equals(
										Format.str2(lastd.toString().trim()))) {
									for (int j = 0; j < content.length; j++) {
										row.add(content[j].toString().trim());
									}
									row.add(content[0].toString() + content[2].toString()
											+ "���" + dianbiaoid + "�ϴε�������ϵͳ���ϴε����������ϣ� ϵͳ�ϴε�����Ϊ��"
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
	 * ���ε����� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���ε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ε�������ʽ����ȷ");
			} else {
				if (Format.str_d(content[7].toString().trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "���ε�����������ڵ���0");
				} else {
					thisdegree = Format.str_d(content[7].toString().trim());
				}
			}

		}
		return row;
	}

	/**
	 * ��������  ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�������� Ϊ��");
		} else {
			if (!Format.isNumber(content[10].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�������� ��ʽ����ȷ");
			} else {
				floatdegrees = Format.d2(Format.str_d(content[10].toString().trim()));
			}

		}
		return row;
	}

	/**
	 * ���˵��� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���˵���Ϊ��");
		} else {
			if (!Format.isNumber(content[11].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���˵�����ʽ����ȷ");
			}
		}
		return row;
	}

	/**
	 * ������ע ��Ϊ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "������עΪ��");
				return row;
			} 
		}
		if (content[14].toString().trim().length()>150) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "������ע���ܳ���150��");
		}else {
			dlmemo = content[14].toString().trim();
		}
		
		return row;
	}

	/**
	 * ���ռ�Ȳ�Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check07(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
			if (content[29] == null || "".equals(content[29].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ռ�� Ϊ��");
			} else {
				if (!Format.isNumber(content[29].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "���ռ�� ��ʽ����ȷ");
				} else {
					jfzb = content[29].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * ����ռ�Ȳ�Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
			if (content[31] == null || "".equals(content[31].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "����ռ�� Ϊ��");
			} else {
				if (!Format.isNumber(content[31].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "����ռ�� ��ʽ����ȷ");
				} else {
					bfzb = content[31].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * ��ƽռ�Ȳ�Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check09(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
			if (content[33] == null || "".equals(content[33].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��ƽռ�� Ϊ��");
			} else {
				if (!Format.isNumber(content[33].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "��ƽռ�� ��ʽ����ȷ");
				} else {
					bpzb = content[33].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * ����ռ�Ȳ�Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check10(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
			
			if (content[35] == null || "".equals(content[35].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "����ռ�� Ϊ��");
			} else {
				if (!Format.isNumber(content[35].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "����ռ�� ��ʽ����ȷ");
				} else {
					bgzb = content[35].toString().trim();
				}
			}
		}
		return row;
	}
	/**
	 * ��岨�岨ƽ����ռ�Ⱥ�Ϊ100%
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check11(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
			if (Format.d2(Format.str_d(jfzb)) + Format.d2(Format.str_d(bfzb)) + Format.d2(Format.str_d(bpzb)) + Format.d2(Format.str_d(bgzb)) != 100.00) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ռ�ȣ�����ռ�ȣ�����ռ�ȣ���ƽռ�ȺͲ�����100%");
			} 
		}
		return row;
	}	
	/**
	 * ���ֵ ��Ϊ�� ��ʽ һ��
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check12(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
				if (content[30] == null || "".equals(content[30].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "���ֵ Ϊ��");
				} else {
					if (!Format.isNumber(content[30].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "���ֵ ��ʽ����ȷ");
					} else {
						if (!Format.str4(content[30].toString().trim()).equals(
								Format.str4(jfz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "���" + dianbiaoid + " ���ֵ�����޸ģ����ֵ�� ϵͳ���ֵ������ ϵͳ���ֵΪ��"
									+ Format.str4(jfz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * ����ֵ ��Ϊ�� ��ʽ һ��
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check13(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
				if (content[32] == null || "".equals(content[32].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "����ֵ Ϊ��");
				} else {
					if (!Format.isNumber(content[32].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "����ֵ ��ʽ����ȷ");
					} else {
						if (!Format.str4(content[32].toString().trim()).equals(
								Format.str4(bfz.toString().trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "���" + dianbiaoid + " ����ֵ�����޸ģ�����ֵ�� ϵͳ����ֵ������ ϵͳ����ֵΪ��"
									+ Format.str4(bfz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * ��ƽֵ ��Ϊ�� ��ʽ һ��
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check14(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
				if (content[34] == null || "".equals(content[34].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "��ƽֵ Ϊ��");
				} else {
					if (!Format.isNumber(content[34].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "��ƽֵ ��ʽ����ȷ");
					} else {
						if (!Format.str4(content[34].toString().trim()).equals(
								Format.str4(bpz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "���" + dianbiaoid + " ��ƽֵ�����޸ģ���ƽֵ�� ϵͳ��ƽֵ������ ϵͳ��ƽֵΪ��"
									+ Format.str4(bpz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * ����ֵ ��Ϊ�� ��ʽ һ��
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check15(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
				if (content[36] == null || "".equals(content[36].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "����ֵ Ϊ��");
				} else {
					if (!Format.isNumber(content[36].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "����ֵ ��ʽ����ȷ");
					} else {
						if (!Format.str4(content[36].toString().trim()).equals(
								Format.str4(bgz.trim()))) {
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString()
									+ "���" + dianbiaoid + " ����ֵ�����޸ģ�����ֵ�� ϵͳ����ֵ������ ϵͳ����ֵΪ��"
									+ Format.str4(bgz.trim()));
						}
					}
				}
		}
		
		return row;	
	}
	/**
	 * ���ε��� ��Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check16(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��ҵ�ֵ��� ��������ϵͳ����ֵΪ��"+dyjdanjiatemp);
			}
		}else {//ת����if("ת����".equals(gdfs))
			if (content[15] == null || "".equals(content[15].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "ת���絥�� Ϊ��");
			} else {
				if (!Format.isNumber(content[15].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "ת���絥�� ��ʽ����ȷ");
				} else {
					if (!Format.str4(content[15].toString().trim()).equals(
							Format.str4(dianfei.toString().trim()))) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString()
								+ "���" + dianbiaoid + "ת���絥��  �����ʵ�ʵ��۲����ϣ� ϵͳת���絥��Ϊ��"
								+ Format.str4(dianfei.toString().trim()));
					}
				}
			}
		}
		
		return row;	
	}

	/**
	 * ���õ��� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���õ��� Ϊ��");
		} else {
			if (!Format.isNumber(content[17].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���õ��� ��ʽ����ȷ");
			} else {
				tzdianfei = Format.d2(Format.str_d(content[17].toString().trim()));
			}
		}
		return row;
	}
	
	/**
	 * Ʊ�������ж�
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Ʊ������Ϊ�գ�");
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
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "Ʊ�����Ͳ���ȷ��");
				}
			} catch (Exception e) {
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Ʊ�����Ͳ���ȷ��");
			}finally{
				ds.free(rs, ps, conn);
			}
            
		}
		return row;
	}

	/**
	 * ���˵�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���˵�� Ϊ��");
		} else {
			if (!Format.isNumber(content[18].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���˵��  ��ʽ����ȷ");
			} else {
				actualdianfei = Format.d2(Format.str_d(content[18].toString().trim()));
			}
		}
		return row;
	}
	
	/**
	 * ��ѱ�ע ��Ϊ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��ѱ�עΪ��");
				return row;
			}
		}
		if (content[24].toString().trim().length()>150) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "��ѱ�ע���ܳ���150��");
		} else {
			dfmemo = content[24].toString().trim();
		}
		return row;
	}

	/**
	 * �ϴγ���ʱ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ�ϴγ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(lastt.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "ϵͳ�ϴγ���ʱ��   ��ʽ����ȷ");
			} else {
				if (content[8] == null || "".equals(content[8].toString().trim())) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "�ϴγ���ʱ��   Ϊ��");
				} else {
					if (!Format.isTime(content[8].toString().trim())) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "�ϴγ���ʱ��   ��ʽ����ȷ");
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
									+ "���" + dianbiaoid + "�ϴγ���ʱ����ϵͳ���ϴγ���ʱ�䲻���ϣ� ϵͳ�ϴγ���ʱ��Ϊ��"
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
	 * ���γ���ʱ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���γ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���γ���ʱ��   ��ʽ����ȷ");
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
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "���γ���ʱ��   ����¼��ʱ��");
					
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
							row.add(content[0].toString() + content[2].toString() + "���"
									+ dianbiaoid + "���γ���ʱ��   ����¼��ʱ��");
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
	 * �ϴγ���ʱ����ڱ��γ���ʱ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�ϴγ���ʱ����ڱ��γ���ʱ��");
			}

		}
		return row;
	}
	
	/**
	 * ��ͬ ����ʱ��,���γ���ʱ�� �Ƚ�
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��¼���ͬ����¼���ѵ�!");
			}else{
				Date thistimea = Format.String2Time(content[9].toString().trim());
				Date htendtimedate = Format.String2Time(htendtime);
				if(thistimea.after(htendtimedate)){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "��ͬ�ѵ��ڲ�����¼��!");
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�������Ա Ϊ��");
		} else {
			enterperson = content[12].toString().trim();
		}
		return row;
	}
	/**
	 * ����ʱ��ĸ�ʽ
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "����ʱ���ʽ����ȷ");
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
	 * Ʊ�ݽ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Ʊ�ݽ��   Ϊ��");
		}else{
			if (!Format.isNumber(content[20].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Ʊ�ݽ�� ��ʽ����ȷ");
			} else {
				pjje = Format.str_d(content[20].toString().trim());
			}
		}
		return row;
	}

	/**
	 * Ʊ�ݵ���
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Ʊ�ݵ���  Ϊ��");
		}else{
			if (!Format.isNumber(content[21].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Ʊ�ݵ��� ��ʽ����ȷ");
			} else {
				pjdl = Format.str_d(content[21].toString().trim());
			}
		}
		return row;
	}
	/**
	 * ��Ʊʱ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��Ʊʱ�� ��ʽ����ȷ");
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

	/**��������*���ʣ����ܴ��ڣ�����ֵ+����ֵ��
	 * @return
	 */
	synchronized Vector<String> check29(Object[] content){
		Vector<String> row = new Vector<String>();
		linelossvalue_temp  = 0.0;
		double thisdegrees = Format.d2(Format.str_d(content[7].toString().trim()));//���ε�����
		double lastdegrees = Format.d2(Format.str_d(lastd.trim()));//�ϴε�����
		
		if ("02xsbl".equals(linelosstype)) {//�������
			linelossvalue_temp = Format.d2((thisdegrees - lastdegrees)*Format.str_d(linelossvalue.toString().trim()));
		} else if("01xstz".equals(linelosstype)){//�������
			linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
		}else{
			linelossvalue_temp = 0.0;
		}
		return row;
	}
	/**
	 * �Ƚ�ʵ���õ�����ʵ�ʽ��ѽ�� ������ѵļ��� ǰ�ᣬ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check30(Object[] content) {
		Vector<String> row = new Vector<String>();
		double dl = 0.00, df = 0.00;
		double sjdl = 0.00;//
		
		double thisdegrees = Format.d2(Format.str_d(content[7].toString().trim()));//���ε�����
		double lastdegrees = Format.d2(Format.str_d(lastd.trim()));//�ϴε�����
		
		if (content[11] != null && !"".equals(content[11].toString().trim())) {
				//����õ��� = �����ε�����-�ϴε�������*����
				//���˵��� = ����õ���+��������*���ʣ�
				// ������λС��
				sjdl = Format.d2((thisdegrees - lastdegrees)*beilvtemp);//����õ���
				dl = Format.d2(sjdl + (Format.str_d(changevalue) + linelossvalue_temp + floatdegrees)*beilvtemp);//���˵���
						
				if (dl != Format.d2(Format.str_d(content[11].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "���" + dianbiaoid + "���˵���������� ��ϵͳ���㱨�˵���Ϊ��" + dl);
					return row;
				} else {
					blhdl = Double.toString(dl);//���ʺĵ���
					sjydl = Double.toString(sjdl);//����õ���
					dbydl = sjdl;//����õ���
				}
			
			if (content[18] != null
					&& !"".equals(content[18].toString().trim())) {
				//�õ���=ֱ���磺���˵���*��ҵ�ֵ��ۣ�ת���磺���˵���*ת���絥�ۣ�
				//���˵�� = ���˵���*����+��ѵ�����
				if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){//ֱ����
					danjia = Format.str_d(dyjdanjia);
				}else {//ת����if("ת����".equals(gdfs))
					danjia = Format.str_d(dianfei);
				}
				double temp = Format.d2(Format.str_d(content[17].toString().trim()));//���õ���
				yddf = Format.d2(dl*danjia);//�õ���
				df = yddf + temp;//���˵��
				if (df != Format.d2(Format.str_d(content[18].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "���" + dianbiaoid + "���˵�Ѽ������ ��ϵͳ���㱨�˵��Ϊ��" + df);
					return row;
				} else {
					// ���ݴ���û�����⣬���Excel �ĵ���
				
					//pjlx = content[19].toString().trim();
					//pjbh = content[20].toString().trim();
					payperson = content[23].toString().trim();
				}
			}
		}

		return row;
	}
	
	/**��������>500/����
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
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + "��������>500/���� ���ڵ�ѵ�����");
//		}
//		return row;
//	}
	/**��ѵ���>60�ڸ�ҳ��¼�룻
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
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + "��ѵ���>60 ���ڵ�ѵ�����");
//		}
//		return row;
//	}
//	/**�����˵���/����>20�򣨽�����վ���-������վ�����/������վ������յ����ڰ����յ�ϵ�����㣩����20%�����ύ�ɹ���
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
//     long glts = tool.getQuot(gllastdate, glthisdate) + 1;//���������
//     long jsts = tool.getQuot(lasttime, thistime)+1;//���������
//     double bzbili = 0.2;
//		if(!"".equals(gllastdegree.trim())){
//			if(!"".equals(glthisdegree.trim())){
//				if(!"".equals(gllastdate.trim())){
//					if(!"".equals(glthisdate.trim())){
//						
//							double jsrj = Format.str_d(blhdl)/jsts;
//							double glhdl = Format.str_d(glthisdegree) - Format.str_d(gllastdegree);
//							double glrj = glhdl/glts;
//							if(jsrj>20){//���˵���/����>20
//								//�Ƚ�
//								if(glhdl!=0){
//										double bili = (jsrj-glrj)/glrj;
//										if(bili>bzbili){
//											for (int j = 0; j < content.length; j++) {
//												row.add(content[j].toString().trim());
//											}
//											row.add(content[0].toString() + content[2].toString() + "���"
//													+ dianbiaoid + " ������վ���-������վ���)/������վ���>20%�����ʵ�������ύ!");
//										}
//								}else{
//									for (int j = 0; j < content.length; j++) {
//										row.add(content[j].toString().trim());
//									}
//									row.add(content[0].toString() + content[2].toString() + "���"
//											+ dianbiaoid + " �������Ϊ��!�������Ϣ¼�벻�淶������ȷ�Ϲ�������ݣ�");
//								}
//							}
//					}else{
//						for (int j = 0; j < content.length; j++) {
//							row.add(content[j].toString().trim());
//						}
//						row.add(content[0].toString() + content[2].toString() + "���"
//								+ dianbiaoid + " ������γ���ʱ��Ϊ�գ�����¼���������ݣ�");
//					}
//				}else{
//					for (int j = 0; j < content.length; j++) {
//						row.add(content[j].toString().trim());
//					}
//					row.add(content[0].toString() + content[2].toString() + "���"
//							+ dianbiaoid + " ������ϴγ���ʱ��Ϊ�գ�����¼���������ݣ�");
//				}
//			}else{
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + " ������ε�����Ϊ�գ�����¼���������ݣ�");
//			}
//		}else{
//			for (int j = 0; j < content.length; j++) {
//				row.add(content[j].toString().trim());
//			}
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + " ������ϴε�����Ϊ�գ�����¼���������ݣ�");
//		}
//		return row;
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//�վ����>6�Ľ���12���жϣ��վ����<=6 ȥԭ��ѵ���¼�롣
	synchronized Vector<String> check31(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool tool = new ElectricTool();
		jszq_temp = tool.getQuot(lasttime, thistime)+1;//���������
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�վ����<="+averagefees+" ���ڵ�ѵ�����");
		}
		return row;
	}
	
	//1���쳣��������������*���ʣ����ܴ��ڵ���õ���*5%
	synchronized Vector<String> check32(Object[] content) {
		Vector<String> row = new Vector<String>();
		floatdegreeandbl = floatdegrees * beilvtemp;//��������*����
		
		if (floatdegreeandbl > dbydl * (exceptadjust/100)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�쳣����!");
		}
		return row;
	}
	
	//2���쳣�߱��𣺣�����ֵ+����ֵ��*���ʣ����ܴ��ڵ���õ���*10%�������ɵ�����
	synchronized Vector<String> check33(Object[] content) {
		Vector<String> row = new Vector<String>();
		double lineandchangeandbl = (linelossvalue_temp + Format.str_d(changevalue)) * beilvtemp;//��������*����
		
		if (lineandchangeandbl > dbydl * (exceptlinechangeloss/100)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�쳣�߱���!");
		}
		return row;
	}
	
	//3�������������������*����<-1���ҵ�ѵ���>1�������ڴ�¼�룻
	//2015-2-10 ��ǿ���̣��������������Ϊ�������Ϊ�������жϣ�|��������*����*����/��ѵ���| >1.5 ����¼�룬��֮��ʾ�������������¼�롣
/*	synchronized Vector<String> check34(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (floatdegreeandbl < backadjust1 && tzdianfei > backadjust2) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�������!");
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�������!");
			}
		}
		return row;
	}
	
	//4�������ߣ������˵���/���ڡ�ʡ���������/ʡ������� < -0.2����(��������+����ֵ+����ֵ��*���� > 1��
	//5�������ˣ������˵���/���ڡ�ʡ���������/ʡ������� < 0.1����(��������+����ֵ+����ֵ��*���� < -1��
	//11���������꣺�����˵���/���ڡ�ʡ���������/ʡ�������> 15%
	synchronized Vector<String> check35(Object[] content) {
		Vector<String> row = new Vector<String>();
		jsrj = Format.str_d(blhdl)/jszq_temp;//���˵���/����
		double bjqq =  (jsrj - Format.str_d(qsdbdl))/Format.str_d(qsdbdl);//�����˵���/���ڡ�ʡ���������/ʡ�������
		double dxbb = (floatdegrees + linelossvalue_temp + Format.str_d(changevalue)) * beilvtemp;//(��������+����ֵ+����ֵ��*����
				if(bjqq < sightvirtualheight1 && dxbb > sightvirtualheight2){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "������!");
				}else{
					if(bjqq < escapeaudit1 && dxbb < escapeaudit2){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "������!");
					}else{
						if(bjqq > (elecoverproof/100)){
							for (int j = 0; j < content.length; j++) {
								row.add(content[j].toString().trim());
							}
							row.add(content[0].toString() + content[2].toString() + "���"
									+ dianbiaoid + "��������!");
						}
					}
				}
			
		
		return row;
	}
	
	//6�������쳣������С��1������ / 5 ��Ϊ����������ʾ�����쳣
	//2015-2-11 ��ǿ���̣������쳣�����ʣ�ֻ��Ϊ5��2��3�ı�������֮��ʾ�����쳣������¼��
	synchronized Vector<String> check34(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] bei = beilvexcept.split(",");
		boolean b = new ElectricTool().beilvBei(beilv, bei);//�жϱ����Ƿ���ĳЩ���ֵ�������
		if(Format.str_d(beilv) < 1 || (Format.str_d(beilv) != 1 && b)){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�����쳣!");
		}
		return row;
	}
	
	//7����ѵ������ߣ���ѵ���>60��
	synchronized Vector<String> check37(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(tzdianfei > feesheight){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "��ѵ�������!");
		}
		return row;
	}
	//8�������������ʵ����������ı��γ���ʱ��������ı��γ���ʱ�䲻ͬ�»���ֹ��һ����Ϊδ�ҵ��ļ�¼����ʾ�����������ʵ��
	synchronized Vector<String> check38(Object[] content) {
		Vector<String> row = new Vector<String>();
     ElecModifyDao dao = new ElecModifyDaoImp();
     ElectricityInfo info = dao.elec1(dianbiaoid);
     gllastdegree = info.getLastdegree();
     glthisdegree = info.getThisdegree();
     gllastdate = info.getLastdatetime();
     glthisdate = info.getThisdatetime();
     boolean gldlbz = info.isGldlbz();
	     if(gldlbz == false){//δ�ҵ���¼
	    	 for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�����������ʵ!");
	 	}else{
	 		int jsyue = Integer.parseInt(thistime.substring(5,6));//������γ���ʱ���·�
	 		int glyue = Integer.parseInt(glthisdate.substring(5,6));//������γ���ʱ���·�
	 		if(gllastdegree.equals(glthisdegree) || jsyue > glyue){//���γ���ʱ�䲻ͬ�»���ֹ��һ��
	 			 for (int j = 0; j < content.length; j++) {
	 				row.add(content[j].toString().trim());
	 			}
	 			row.add(content[0].toString() + content[2].toString() + "���"
	 					+ dianbiaoid + "�����������ʵ!");
	 		}
	 	}
	     return row;
	}
	
	//9.���˺ͳ�������󣺱��˵���/����>8�򣨱��˵���/���ڡ���������õ���/���ڣ�/����������/���ڣ����յ����ڰ����յ�ϵ�����㣩����15%�������ɵ�������¼�롣
	synchronized Vector<String> check39(Object[] content) {
		Vector<String> row = new Vector<String>();
		double jsrj = Format.str_d(blhdl)/jszq_temp;
		double glhdl = Format.str_d(glthisdegree) - Format.str_d(gllastdegree);
		ElectricTool tool = new ElectricTool();
		long glts = tool.getQuot(gllastdate, glthisdate) + 1;//���������
		double glrj = glhdl/glts;
		if(jsrj>20){//���˵���/����>20
			//�Ƚ�
			if(glhdl!=0){
					double bili = (jsrj-glrj)/glrj;
					if(bili>(gapoversize2/100)){
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + " ���˺ͳ��������!");
					}
			}else{
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�����������ʵ!");
			}
		}
		
		if(tzdianfei / yddf > feesadjustexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "��ѵ����쳣!");
		}
		return row;
	}
	
	//10����ѵ����쳣����ѵ��� / �õ���>5%��
	synchronized Vector<String> check40(Object[] content) {
		Vector<String> row = new Vector<String>();
		if(tzdianfei / yddf > feesadjustexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "��ѵ����쳣!");
		}
		return row;
	}
	
	//12�������쳣�������ۡ���ֹ����ȵ�ǰ�ĸõ��е�ƽ�����ۣ�/ ��ֹ����ȵ�ǰ�ĸõ��е�ƽ������ > 20%��
	//2015-2-11��Ϊ ��ǿ���̣������쳣�����˵�� / ���˵��� > ��׼����*��1+xx%������ʾ�����쳣
	synchronized Vector<String> check41(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool elecToo = new ElectricTool();
		String str = elecToo.selectUnitprice(propertycode, zdname, stationtypecode, gdfscode);
		ElecBillDao dao = new ElecBillDaoImp();
		averageunitprice = Format.str_d(dao.getAverageUnitPrice(dbid,str));//��׼����
/*		if((danjia - averageunitprice) / averageunitprice > unitpriceexcept/100){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�����쳣!");
		}*/
		double a = Format.d4(actualdianfei/Format.str_d(blhdl));
		double b = Format.d4(averageunitprice*(1+unitpriceexcept/100));
		if( a > b ){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�����쳣!");
		}
		return row;
	}
	/**��ѳ������
	 * @param content
	 * @return
	 */
	synchronized Vector<String> checkcbdf(Object[] content) {
		Vector<String> row = new Vector<String>();
		 ElectricTool elecToo = new ElectricTool();
		 String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//����õ��� = (���ε�����-�ϴε�����)*����
		 String[] shiandxian = elecToo.getShiAndXian(dbid);
		 String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], propertycode, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22��ʡ�����,�ϲ�����,��׼ֵ
		double dfcbbl = Format.str_d(elecToo.getOverFeesbl(cbbl[2], String.valueOf(averageunitprice), String.valueOf(actualdianfei)));
		
		if("N".equals(elefeesbl)){
			
		}else{
			if(dfcbbl>Format.str_d(elefeesbl)){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��ѳ����������"+elefeesbl+"%,����¼��!");
			}
		}
		return row;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * �ж����ݿ����Ƿ�����������(�ظ�)�� ���ݿ������ظ����ݷ���true�����򷵻�false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check42(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElectricTool tool = new ElectricTool();
		boolean temp = true;
		
		 //����һ�µ�ǰʱ��������·�
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���¼���ظ������飡����");
		}
		return row;

	}

	/**
	 * ��ѯ ���ķ�̯���� ������Ƿ��̯���м�飬 ���û������ ��ʼ����ѣ������� ��̯����͸�ֵ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "��̯��Ϣ�������û�н��з�̯���뵽���������в�ѯ����");
		} else {
			//��̯����(��ע����0.0000001��Ϊ���DecimalFormat������������С��λ����0.005����������)
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
	 * ��ʼ��� ��Ӧ�ĵ��
	 * 
	 * @author gt_web
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check44(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4;
		String[] df1, df2;
		String city = "1";//�м����״̬����˱�־
		String qxzr = "1";//�����������״̬ 0����Ҫ���,1:����Ҫ��ˣ���˱�־    0��δ��ˣ�1�����ͨ����2��˲�ͨ��
		String cityzr = "1";//�м��������״̬����˱�־	
		
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		ElecBillDao dao = new ElecBillDaoImp();
		String[] shiandxian = tool.getShiAndXian(dbid);
		
        String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//����õ��� = (���ε�����-�ϴε�����)*����
        info.setDbydl(dbydl);
		info.setFloatdegree(String.valueOf(floatdegrees));
		info.setMemo(dlmemo);
		info.setMemo1(dfmemo);
		dl1 = tool.checkFloatDegree(info);
		dl2 = tool.checkDayDegree(dianbiaoid, thistime, lasttime, blhdl);
		dl3 = tool.checkBcdl(blhdl, thistime, lasttime, edhdl, dianbiaoid, null, "2");
		String[] cbbl = tool.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], propertycode, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22��ʡ�����,�ϲ�����,��׼ֵ
		dl4 = tool.checkBcdl2(cbbl[0]);
    	String[] expecthigh = tool.checkExceptAndHigh(dbid, String.valueOf(actualdianfei), blhdl, thistime, lasttime, String.valueOf(Format.str_d(cbbl[0])/100));//�쳣���߶�
    	String[] site = tool.checckSite(dbid);//�Ƿ�1.2�����
    	String[] adjust1 = tool.adjustCheck1(String.valueOf(tzdianfei), String.valueOf(tzdegree));//��ѣ���������
    	String[] adjust2 = tool.adjustCheck2(lastunitprice, dianfei);//���۵���
    	String[] chayue = tool.chaYue(thistime, bzmonth);//���γ���ʱ���Ӧ�·�-�����·�>=2
    	String[] thiselecfee = tool.checkThisFees(String.valueOf(actualdianfei),shiandxian[0],shiandxian[1]);//���ε�ѽ�������������ƽ����ѽ��
    	String[] outprice = new String[]{"1",""};//Ĭ�� �����ж�Ϊͨ������û�н��и��жϣ�
    	String[] adjustelec = tool.adjustElec(String.valueOf(tzdegree),beilv);//�����5,6,7,8,9,10�·ݵ�����������800��Ҫ�ļ����  �������500
    	String[] adjustfeeandelec1 = tool.adjustFeeAndElec1(String.valueOf(tzdianfei), String.valueOf(tzdegree));//�Ƿ�������������10���ҵ���������
    	String[] adjustfeeandelec2 = tool.adjustFeeAndElec2(String.valueOf(tzdegree), String.valueOf(tzdianfei), dianfei);//���������������������ҵ�ѣ�����������������������*����-��ѵ�����/��ѵ���>1.1 
    	if("zdsx04".equals(expecthigh[2])){//����Ϊ ���� �� �ж�
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
    	dfmess = dfmess + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//������Ϣ���ӵ���ѱ���

         if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){
        	 info.setJfzb(jfzb);
             info.setBfzb(bfzb);
             info.setBgzb(bgzb);
             info.setBpzb(bpzb);   
         }else {//if("ת����".equals(gdfs))
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
		info.setLiuchenghao(liuchenghao == null ? "":liuchenghao);//Ԥ֧���̺�
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
		String jszq = dl3[4];//��������
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
    	String hbzq = cbbl[1];//�ϲ�����
    	String bzz = cbbl[2];//��׼ֵ
		// ��������
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);
		String temp = "0";
		String rtmess = dao.addDegreeFees(dianbiaoid, info, share, uuid, temp, dfmess, dfauto,
				dlmess, dlauto, dl3[3].toString().trim(),cbbl[0].toString().trim(),city,qxzr,cityzr,jszq,dl3[5],qsdbdl,xgbzw,hbzq,bzz,scb);
		
        if(!"�����ѵ��ɹ���".equals(rtmess)){
        	for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Error��");
        }
		return row;
	}
}
