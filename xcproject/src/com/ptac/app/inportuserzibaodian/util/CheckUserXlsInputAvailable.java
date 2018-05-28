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
 * @author rock ��ѵ����ݵ����ӣ���������ͼ��
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
	boolean zbdyhhBol = false;// �Ƿ����Ա����û���
	String scdf="",scdl="",lastunitprice="";//�ϴε�ѣ��ϴε������ϴε���
	String pjje;//Ʊ�ݽ��

	/**
	 * ��ѵ� ����� ��� excel��������ȷ�Լ��� ��ѵ������ظ��Լ��
	 */
	public CheckUserXlsInputAvailable() {

	}

	/**
	 * @param content
	 *            ��������
	 * @param wrong
	 *            ��������
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account, String biaozhi) {
		accountid = account.getAccountId();
		accountName = account.getAccountName();
		Vector<String> row = new Vector<String>();

		// ��������
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
		// / ʱ��ıȽ�

//		if (row.isEmpty()) {
//			row = this.check13(content);
//		}
//1 ���ϴγ���ʱ��
//2 û���ϴγ���ʱ�� ǰ̨�ı�־λ����Ϊ2������̨��֮�󶼴Ӻ�̨��ѯ
		if (row.isEmpty()) {
			if ("1".equals(biaozhi)) {
//				row = beginTime(content);// �ϴγ���ʱ��
				row = check14(content);
			} else if ("2".equals(biaozhi)) {
				this.noLastMonth(content);
			}
		}

		// �����ϴγ���ʱ��

		if (row.isEmpty()) {
			row = this.writeMonth(content);
		}

//		if (row.isEmpty()) {
//			row = this.timeComp(content);
//		}

		// ��ʼ��̯

		this.findFentan();

		// ��ʼ����ǰ�ļ��
		if (row.isEmpty()) {
			row = this.compNowEleAndMoney(content);
		}
		if (row.isEmpty()) {
			row = this.boolenSameInfo(content);
		}

		// ǰ�ڹ�����ɿ�ʼ��ӵ��

		if (row.isEmpty()) {
			row = this.addEle(content);
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

		DataBase ds = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String id = "";
		zbdyhh = content[3].toString().trim();

		Vector<String> row = new Vector<String>();
		String sql = "SELECT D.id, D.DANJIA, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,"//Z.dianfei ��Ϊ d.danjia����
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI else 0 END))AS ZY1,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI else 0 END))AS ZY2,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI else 0 END))AS ZY3,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI else 0 END))AS ZY4,"
				+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI else 0 END))AS ZY5,  "
				+ "d.dbid,TO_CHAR(d.CSSYTIME,'yyyy-mm-dd') CSSYTIME,Z.qsdbdl,D.DBID,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,D.DFZFLX,Z.GDFS,Z.STATIONTYPE"
				+ " FROM ZHANDIAN Z, DIANBIAO D,SBGL S  WHERE Z.ID = D.ZDID   AND D.DBYT = 'dbyt01' AND D.DBQYZT='1' AND Z.QYZT='1' "
				+ "  AND S.DIANBIAOID(+)=D.DBID AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?)"
				+ " GROUP BY D.ID,D.DANJIA, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,d.dbid,d.CSSYTIME,z.qsdbdl,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,D.DFZFLX,Z.GDFS,Z.STATIONTYPE";//Z.dianfei ��Ϊ d.danjia����
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
			if("dfzflx02".equals(dfzflx)){//��ͬ
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

			row.add("δ�鵽�Ա����û���" + content[0].toString() + content[2].toString()
					+ "�Ա����û���" + zbdyhh);
		} else {
			zbdyhhBol = true;
		}
		return row;
	}

	/**
	 * �Ա����û��Ų�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "�Ա����û���Ϊ��");
		}
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ϵͳ��ȫʡ�������Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ϵͳ�ж�ĵ���Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ϵͳ��ֱ������Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ϵͳ�н�������Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ϵͳ��������Ϊ�գ�");
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
		if ("".equals(property) || "null".equals(property) || null == property) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��վ������Ϊ�գ�");
		} 
		return row;
	}
	
	/** �������վ��δ����վ��ID�����
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	synchronized Vector<String> checkOutAndConnect(Object[] content) {
		Vector<String> row = new Vector<String>();
		ElecBillZbdDao dao = new ElecBillZbdDaoImp();
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
	 * �ϴε����� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "�ϴε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[4].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "�ϴε�������ʽ����ȷ");
			} else {
				lastdegree = Format.str_d(content[4].toString().trim());
			}

		}
		return row;
	}

	/**
	 * ���ε����� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "���ε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "���ε�������ʽ����ȷ");
			} else {
				thisdegree = Format.str_d(content[5].toString().trim());
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
	synchronized Vector<String> check05(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "���γ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "���γ���ʱ��   ��ʽ����ȷ");
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
						row.add(content[0].toString() + content[2].toString() + "���"
								+ dianbiaoid + "���γ���ʱ��   ����¼��ʱ��");
						
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
								row.add(content[0].toString() + content[2].toString() + "���"
										+ dianbiaoid + "���γ���ʱ��   ����¼��ʱ��");
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
				Date thistimea = Format.String2Time(content[7].toString().trim());
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

	/**
	 * ʵ���õ��� ��Ϊ�� ��ʽ
	 * 
	 * @param content
	 * @param row
	 * @return
	 * @update WangYiXiao 2014-5-7  ʵ�ʵ�ѽ���ʽ ����ȷ     ��Ϊ    �Ա����û���" + zbdyhh + "ʵ���õ�����ʽ����ȷ
	 */
	synchronized Vector<String> check06(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ʵ���õ���Ϊ��");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "ʵ���õ��� ��ʽ����ȷ");
			} else {
				blhdl =content[8].toString().trim();
			}
		}
		return row;
	}

//	/**
//	 * ��ʼ���� ��ʽ
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
//						+ "�Ա����û���" + zbdyhh + "��ʼ�·� ��ʽ����ȷ");
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
//	 * �������� ��Ϊ�� ��ʽ
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
//			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
//					+ zbdyhh + "�����·�Ϊ��");
//		} else {
//			if (!Format.isMonth(content[10].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "�Ա����û���" + zbdyhh + "�����·� ��ʽ����ȷ");
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
//	 * ��ʼ�·ݺͽ����·ݵĴ�С������
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
//						+ "�Ա����û���" + zbdyhh + "��ʼ�·ݴ��ڽ����·�");
//			}
//		}
//		return row;
//	}

	/**
	 * �ϴγ���ʱ��ͱ��γ���ʱ��Ĵ�С������
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
						+ "�Ա����û���" + zbdyhh + "�ϴγ���ʱ����ڱ��γ���ʱ��");
			}
		}
		return row;
	}

	/**
	 * ����ʱ�� ��ʽ
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
						+ "�Ա����û���" + zbdyhh + "����ʱ�� ��ʽ����ȷ");
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
	 * ʵ�ʵ�ѽ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "ʵ�ʵ�ѽ�� Ϊ��");
		} else {
			if (!Format.isNumber(content[12].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "ʵ�ʵ�ѽ�� ��ʽ����ȷ");
			}else if(Format.str_d(content[12].toString().trim())>0 && Format.str_d(content[12].toString().trim())<5){
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "ʵ�ʵ�ѽ�� ����С��5Ԫ");
			} else {
				actualdianfei = Format.str_d(content[12].toString().trim());
			}
		}
		return row;
	}

//	/**
//	 * �����·� ��Ϊ�ա���ʽ
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
//			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
//					+ zbdyhh + "�����·� Ϊ��");
//		} else {
//			if (!Format.isMonth(content[15].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString()
//						+ "�Ա����û���" + zbdyhh + "�����·� ��ʽ����ȷ");
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "Ʊ�����Ͳ���Ϊ��");
		}
		return row;
	}

//	/**
//	 * Ʊ��ʱ�� ��ʽ
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
//						+ "�Ա����û���" + zbdyhh + "Ʊ��ʱ��ʱ��   ��ʽ����ȷ");
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
	 * ��Ʊʱ��ĸ�ʽ
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
							+ "�Ա����û���" + zbdyhh + "��Ʊʱ���ʽ����ȷ");
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
//	 * ����ʱ��ĸ�ʽ
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
//							+ "�Ա����û���" + zbdyhh + "����ʱ���ʽ����ȷ");
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
	 * �ϴγ���ʱ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "�ϴγ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(content[6].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + zbdyhh + "�ϴγ���ʱ��   ��ʽ����ȷ");
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
	 * Ʊ�ݽ��
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
			row.add(content[0].toString() + content[2].toString() + "�Ա����û���"
					+ zbdyhh + "Ʊ�ݽ��   Ϊ��");
		}else {
			if (!Format.isNumber(content[14].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Ʊ�ݽ�� ��ʽ����ȷ");
			} else {
				pjje = content[14].toString().trim();
			}
		}
		return row;
	}

	
	/**
	 * ����û����ʱ��ļ�飬
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> compNowEleAndMoney(Object[] content) {
		Vector<String> row = new Vector<String>();

		// ���ݴ���û�����⣬���Excel �ĵ���

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
		if("��Ʊ".equals(pjlx)){
			pjlx = "pjlx03";
		}
		if("�վ�".equals(pjlx)){
			pjlx = "pjlx05";
		}
		if("��ֵ˰��Ʊ".equals(pjlx)){
			pjlx = "pjlx06";
		}

		return row;
	}

	/**
	 * �ж����ݿ����Ƿ�����������(�ظ�)�� ���ݿ������ظ����ݷ���true�����򷵻�false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> boolenSameInfo(Object[] content) {
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
				.valueOf(lastdegree), lastdatetime, dianbiaoid, bzmonth);
		if (temp) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString()
					+ "�����¼���ظ������飡����");
		}
		return row;

	}

	/**
	 * ��ѯ ���ķ�̯���� ������Ƿ��̯���м�飬 ���û������ ��ʼ����ѣ������� ��̯����͸�ֵ
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
			// ����
			blhdl1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// Ӫҵ
			blhdl2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// �칫
			blhdl3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// ��Ϣ��
			blhdl4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			// ����Ͷ��
			blhdl5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(blhdl).doubleValue());
			//������
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
			// ����
			actualpay1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// Ӫҵ
			actualpay2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// �칫
			actualpay3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// ��Ϣ��
			actualpay4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			// ����Ͷ��
			actualpay5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(actualpay).doubleValue() + wucha);
			//������
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
	 * ���û���ϴγ���ʱ��
	 */
	synchronized void noLastMonth(Object[] content) {

		Vector<String> row = new Vector<String>();

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// ��ѯ�����γ���ʱ�� �����·�
		String sql2 = "SELECT T.*,ROWNUM FROM(SELECT TO_CHAR(AM.THISDATETIME+1,'YYYY-MM-DD'),AM.BLHDL YDL,DF.ACTUALPAY ACTUALPAY,DF.UNITPRICE UNITPRICE FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES AM,ELECTRICFEES DF "
				+ "WHERE Z.ID=D.ZDID AND D.DBID=AM.AMMETERID_FK AND DF.CITYAUDIT = '1' AND DF.CITYZRAUDITSTATUS = '1' AND DF.AMMETERDEGREEID_FK = AM.AMMETERDEGREEID "
				+ " AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE"
				+ " P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?) ORDER BY AM.THISDATETIME desc nulls last )t where rownum=1";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql2);
			ps.setString(1, zbdyhh);
			ps.setString(2, accountid);
			System.out.println("ͨ���Ա����û�����������������,��ѯ�����γ���ʱ�� �����·�:" + sql2);
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
				row.add("δ�鵽�Ա����û���" + zbdyhh
						+ "���ϴγ���ʱ�䣺�õ��û�н������ã��뵽������ѵ���'��ʼʹ��ʱ��'����");
			}
//			else {
//				startmonth = lastdatetime.substring(0, 7);
//			}

//		}
//		thismonth=startmonth;
	}

	/**
	 * ��ʼ��� ��Ӧ�ĵ��
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
        String dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//����õ��� = (���ε�����-�ϴε�����)*����
		info.setDbydl(dbydl);
		info.setBeilv(beilv);
        String[] shiandxian = tool.getShiAndXian(dbid);
		dl2 = tool.checkDayDegree(dianbiaoid, thistime_s, last_s, blhdl);//�ϴ��պĵ�����ֵ	
		dl3 = tool.checkBcdl(blhdl, thistime_s, last_s, null, dianbiaoid,"","");//��ĵ�����ֵ
		String[] cbbl = tool.getQsdbdlCbbl(dbydl, thistime_s, last_s, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dianbiaoid,blhdl,qsdbdl,stationtype);//2014-10-28��ʡ�����,�ϲ�����,��׼ֵ
		dl4 = tool.checkBcdl2(cbbl[0]);//ʡ���곬���ֵ
		String[] expecthigh = tool.checkExceptAndHigh(dianbiaoid, actualpay, blhdl, thistime_s, last_s, String.valueOf(Format.str_d(cbbl[0])/100));//�쳣���߶�
		String[] site = tool.checckSite(dianbiaoid);//�Ƿ�1.2�����
		String[] adjust2 = tool.adjustCheck2(lastunitprice, dianfei);//���۵���
    	String[] chayue = tool.chaYue(thistime_s, bzmonth);//���γ���ʱ���Ӧ�·�-�����·�>=2
    	String[] thiselecfee = tool.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//���ε�ѽ�������������ƽ����ѽ��
    	String[] outprice = new String[]{"1",""};//Ĭ�� �����ж�Ϊͨ������û�н��и��жϣ�
    	if("zdsx04".equals(expecthigh[2])){//����Ϊ ���� �� �ж�
    		outprice = tool.OutElecUnitPrice(dianfei, shiandxian[0], shiandxian[1]);
    	}
		
    	dlmess = dlmess + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1];
    	//�˴���if()else{} �ж���ָ��������Զ���˲�ͨ���� ��ѵ��Զ����Ҳ��ͨ��������������ͨ����Ե�ѽ����� ��
    	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])){
    		dlauto="1";//�����Զ����ͨ��
    		String[] df6 = tool.checkElectric2(pjje);//Ʊ�ݽ��Ϊ���ж�
    			dfmess = dfmess  + df6[1] + outprice[1];
    		if("1".equals(df6[0]) && "1".equals(outprice[0])){
    			dfauto = "1";//����Զ����ͨ��
    		}else{
    			dfauto = "0";//����Զ���˲�ͨ��
    		}
    	}else{
    		dlauto="0";//�����Զ���˲�ͨ��
    		dfauto="0";//����Զ���˲�ͨ��
    	}
    	

    	String qxzr = "1";//�����������״̬ 0����Ҫ���,1:����Ҫ��ˣ���˱�־    0��δ��ˣ�1�����ͨ����2��˲�ͨ��
    	String city = "1";//�м����״̬����˱�־
    	String cityzr = "1";//�м��������״̬����˱�־		        
    	
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
    	dfmess =dfmess + adjust2[1] + chayue[1] + thiselecfee[1] + expecthigh[1] + site[1];//������Ϣ���ӵ���ѱ���
		
		if(thisdegree-lastdegree!=Double.parseDouble(blhdl)){
			dfmess = dfmess+"   �ϴε���������󳭱�����һ�£�" ;
			dfauto="0";
			dlmess = dlmess+"   �ϴε���������󳭱�����һ�£�" ;
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
		// ��������

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String amuuid = uuid2 + Long.toString(uuid1).substring(3);
		ZhurenPanduanBean zpb = new ZhurenPanduanBean();
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String countyaudittime = "1".equals(qxzr) ? s : null;//�����������ʱ��
		String cityaudittime = "1".equals(city) ? s : null;//�м����ʱ��
		String cityzraudittime = "1".equals(cityzr) ? s : null;//�м��������ʱ��
		

		zpb.setCountyauditstatus(qxzr);//�����������״̬
		zpb.setCountyaudittime(countyaudittime);//�����������ʱ��
		
		zpb.setCityaudittime(cityaudittime);//�м����ʱ��------------------------------------------
		
		zpb.setCityzrauditstatus(cityzr);//�м��������״̬
		zpb.setCityzraudittime(cityzraudittime);//�м��������ʱ��
		
		
		String cShengdb=cbbl[0];//��ʡ�����
		String cShidb=dl3[3];
		String edhdl1 = dl3[5];
		String qsdbdl1 = qsdbdl;//ȫʡ�������
		zpb.setCsdb(cShengdb);
		String temp = "0";//0���뻹��¼��
    	String hbzq = cbbl[1];//�ϲ�����
    	String bzz = cbbl[2];//��׼ֵ
		
		String a = dao.addAm(dianbiaoid, info, share, amuuid, temp, dfmess,
				dfauto, dlmess, dlauto, cShidb, city,zpb,hbzq,bzz,scb);
		if ("����ɹ���".equals(a)) {
			String rtmess = dao.addDegreeFees(this.getDLID(amuuid), dianbiaoid,
					info, share, amuuid, temp, dfmess, dfauto, dlmess, dlauto,
					beilv, city,zpb,dl3[4],edhdl1,qsdbdl1);
			if (!"����ɹ���".equals(rtmess)) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + dianbiaoid + "Error��");
			}
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
//					+ "�Ա����û���" + dianbiaoid + "��ĵ���Ϊ0��Ϊ��");
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
			dfmess = dfmess+"   �ϴε���������󳭱�����һ�£�" ;
			dfauto="0";
			dlmess = dlmess+"   �ϴε���������󳭱�����һ�£�" ;
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
		// ��������

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String amuuid = uuid2 + Long.toString(uuid1).substring(3);

		String temp = "0";
		
		
		
		
		ZhurenPanduanBean zpb = new ZhurenPanduanBean();
		 �洢�����ж� 
		// ʵ����������
		ElectricTool et = new ElectricTool();

		boolean b_ycgedf = false;// �쳣�߶���
		boolean b_sdb = false;// ����ʡ����
		boolean b_edhdl = false;// С�ڶ�ĵ���
		boolean b_zhandian = false;// 1.2�����

		// �ж��쳣���߶���
		
		//�����ڽ���ת����
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

		String countyauditstatus = qxsh;// �����������״̬��־λ
		String countyauditname = "";// �������������
		String countyaudittime = "";// �����������ʱ��

		// �ж϶�ĵ�����ʡ����
		String ed[] = et.checkBcdl(blhdl, thistime, lastdatetime, edhdl, dbid, "", "2");// ��ĵ���
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

		// �ж�1.2�����
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

		String cityzrauditstatus = szrBzw;// ���������״̬��־λ
		String cityzrauditname = "0";// �����������
		String cityzraudittime = "";// ���������ʱ��

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
		//���㳬ʡ���б�
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
		//���㳬ʡ���б�
		
		zpb.setCsdb(cShengdb);
			
		String a = dao.addAm(dianbiaoid, info, share, amuuid, temp, dfmess,
				dfauto, dlmess, dlauto, cShidb, flag,zpb);
		if ("����ɹ���".equals(a)) {
			String rtmess = dao.addDegreeFees(this.getDLID(amuuid), dianbiaoid,
					info, share, amuuid, temp, dfmess, dfauto, dlmess, dlauto,
					beilv, flag,zpb,ed[4],edhdl1,qsdbdl1);
			if (!"����ɹ���".equals(rtmess)) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString()
						+ "�Ա����û���" + dianbiaoid + "Error��");
			}
		}

		return row;
	}*/

	/**
	 * ��ȡ���
	 * 
	 * @param amuuid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String amuuid) {
		String sql = "select ammeterdegreeid from AMMETERDEGREES where uuid='"
				+ amuuid + "'";
		System.out.println("--���amuuid����id--" + sql);
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
