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
 * ��ͬ������
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
	boolean zbdyhhBol = false;// �Ƿ����Ա����û���

	/**
	 * ��ͬ�� ����� ��� excel��������ȷ�Լ��� ��ѵ������ظ��Լ��
	 */
	public BargainBillsInputUtil() {

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
		accountName = account.getAccountName();
		Vector<String> row = new Vector<String>();

		// ��������
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
		// ǰ�ڹ�����ɿ�ʼ��ӵ��

		if (row.isEmpty()) {
			row = this.addEle(content);
		}
		return row;

	}

	/**
	 * վ��ID��Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "վ��ID"
					+ zdid + "վ��IDΪ��");
		}
		return row;
	}

	/**
	 * ��ѯ���޴�վ��
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
				row.add("δ�鵽" + b[0].toString() + b[2].toString() + "վ��ID"
						+ b[4].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			row.add("δ�鵽" + b[0].toString() + b[2].toString() + "վ��ID"
					+ b[4].toString().trim());
		} finally {
			db.free(rs, ps, conn);
		}

		return row;
	}

	/**
	 * ���ID��Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "վ��ID"
					+ zdid + "���IDΪ��");
		}
		return row;
	}

	/**
	 * ���ID��ѯ
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
				row.add("δ�鵽" + b[0].toString() + b[2].toString() + "վ��ID"
						+ b[4].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			row.add("δ�鵽" + b[0].toString() + b[2].toString() + "վ��ID"
					+ b[4].toString().trim());
		} finally {
			db.free(rs, ps, conn);
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
		if (Format.str_d(qsdbdla)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ��ȫʡ�������Ϊ�գ�");
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
		if (Format.str_d(edhdla)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ�ж�ĵ���Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ��ֱ������Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ�н�������Ϊ�գ�");
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ��������Ϊ�գ�");
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
		if ("".equals(property) || "null".equals(property)||null==property) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "ϵͳ��վ������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * ��� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "���Ϊ��");
		} else {
			if (!Format.isNumber(content[6].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "����ʽ����ȷ");
			} else {
				money = content[6].toString().trim();
			}

		}
		return row;
	}

	/**
	 * ��ʼ���� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "��ʼ����Ϊ��");
		} else {

			if (!Format.isMonth(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "��ʼ�·� ��ʽ����ȷ");
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
	 * �������� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "�����·�Ϊ��");
		} else {
			if (!Format.isMonth(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "�����·� ��ʽ����ȷ");
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
	 * ��ʼ�·ݺͽ����·ݵĴ�С������
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
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "��ʼ�·ݴ��ڽ����·�");
			}
		}
		return row;
	}

	/**
	 * �����·� ��Ϊ�ա���ʽ
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "�����·� Ϊ��");
		} else {
			if (!Format.isMonth(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "�����·� ��ʽ����ȷ");
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
	 * �õ��� ��ʽ
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
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "�õ��� ��ʽ����ȷ");
			} else {
				ydl = content[10].toString().trim();
			}
		}
		return row;
	}

	/**
	 * Ʊ��ʱ�� ��ʽ
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
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "Ʊ��ʱ��ʱ��   ��ʽ����ȷ");
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
	 * ��Ʊʱ��ĸ�ʽ
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
							+ "��ͬ��" + zdid + "��Ʊʱ���ʽ����ȷ");
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
	 * Ʊ�����Ͳ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "Ʊ�����Ͳ���Ϊ��");
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
					row.add(content[0].toString() + content[2].toString() + "��ͬ��"
							+ zdid + "Ʊ�����Ͳ���ȷ��");
				}
			} catch (Exception e) {
				e.printStackTrace();
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "Ʊ�����Ͳ���ȷ��");
			}finally{
				ds.free(rs, ps, conn);
			}
            
		}
		return row;
	}

	/**
	 * Ʊ�ݽ�� ��ʽ
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
				row.add(content[0].toString() + content[2].toString() + "��ͬ��"
						+ zdid + "Ʊ�ݽ���ʽ����ȷ");
			} else {
				pjje = content[15].toString().trim();
			}

		}
		return row;
	}
	

	/**
	 * ��ͬ���
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
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "��ͬ���Ϊ��");
		} else {
			htbh=content[17].toString().trim();

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
		if ("��Ʊ".equals(pjlx)) {
			pjlx = "pjlx03";
		}
		if ("�վ�".equals(pjlx)) {
			pjlx = "pjlx05";
		}
		
		if ("��ֵ˰��Ʊ".equals(pjlx)) {
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
		temp = tool.checkRepeat2(dbid, accountmonth, thismonth, endmonth);
		if (temp) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "��ͬ���ظ������飡����");
		}
		return row;

	}

	/**
	 * ��ѯ ���ķ�̯���� ������Ƿ��̯���м�飬 ���û������ ��ʼ����ѣ������� ��̯����͸�ֵ
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

		// ��̯���(��ע����0.0000001��Ϊ���DecimalFormat������������С��λ����0.005����������)
		if (!"".equals(money) || money != null || money != "0"
				|| !"o".equals(money)) {
			// ����
			actualpay1 = df.format((new Double(dbili1).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// Ӫҵ
			actualpay2 = df.format((new Double(dbili2).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// �칫
			actualpay3 = df.format((new Double(dbili3).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// ��Ϣ��
			actualpay4 = df.format((new Double(dbili4).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// ����Ͷ��
			actualpay5 = df.format((new Double(dbili5).doubleValue() / 100)
					* new Double(money).doubleValue() + wucha);
			// ������
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
	 * ��ʼ��� ��Ӧ�ĵ��
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

		// ��̯���
		// ����
		bean.setNetworkdf(actualpay1);
		// Ӫҵ
		bean.setMarketdf(actualpay2);
		// �칫
		bean.setAdministrativedf(actualpay3);
		// ��Ϣ��
		bean.setInformatizationdf(actualpay4);
		// ����Ͷ��
		bean.setBuilddf(actualpay5);
		// ������
		bean.setDddf(actualpay6);

		/* �洢�����ж� */
		// ʵ����������
		ElectricTool et = new ElectricTool();

		// ��Ϊ�����жϣ�����Ĭ��ͨ��

		String manualauditname = "";// �˹������
		String manualauditstatus = "0";// �˹����״̬
		String manualauditdatetime = null;// �˹����ʱ��

		boolean ycgedf = false;// �쳣�߶���
		boolean sdb = false;// ����ʡ����
		boolean edhdl = false;// С�ڶ�ĵ���
		boolean zhandian = false;// 1.2�����

		// �ж��쳣���߶���
		
		//�����ڽ���ת����
		GetZQ gz = new GetZQ();
		String qssj = gz.getBeginTime(thismonth);
		String jssj = gz.getEndTime(endmonth);
		
		bean.setStartdate(qssj);
		bean.setEnddate(jssj);
		
	//	BargainBillCountBean info = dao.getCount(dbid);
		
		/*�洢����*/
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
         String dbydl = ydl;//����õ��� = (���ε�����-�ϴε�����)*����
         bean.setDbydl(dbydl);
         bean.setBeilv(beilv);
         bean.setScb(scb);
		String[] cbbl = et.getQsdbdlCbbl(dbydl, jssj, qssj, shicode, property, zlfh, jlfh, edhdla, scb, dbid,ydl,qsdbdla,stationtype);//2014-10-28��ʡ�����,�ϲ�����,��׼ֵ
		// �ж϶�ĵ�����ʡ����
		String ed[] = et.checkBcdl(ydl, jssj, qssj,edhdla, dbid, "", "2");// ��ĵ���
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

		String countyauditstatus = qxsh;// �����������״̬��־λ
		String countyauditname = "";// �������������
		String countyaudittime = "";// �����������ʱ��

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

		// �ж�1.2�����
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

		String cityzrauditstatus = szrBzw;// ���������״̬��־λ
		String cityzrauditname = "";// �����������
		String cityzraudittime = "";// ���������ʱ��

		String cityAudit = "0";
		if (ycgedf == false || edhdl == false || sdb == false
				|| zhandian == false) {
			cityAudit = "0";
		} else {
			cityAudit = "1";
		}
		
		StringBuffer text = new StringBuffer();//��ͨ��ԭ������
		text.append(eah[1]);//��Ӳ�ͨ����Ϣ
		text.append(" "+ed[1]);
		text.append(" "+qs[1]);
		text.append(" "+onePointTwo[1]);
		
		/*�Զ����״̬������*/
		String autoauditstatus = cityAudit;// �Զ����״̬
		String autoauditdescripthion = text.toString();// �Զ��������

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
		//���㳬ʡ���б�
		
		
		bean.setCsdb(cSheng);
		bean.setDedhdl(cCity);
		

		Vector<String> row = new Vector<String>();
		
		String bzw = "0";
		String a = dao.saveXls(bean, bzw, thismonth, endmonth);
		if (!"1".equals(a)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "��ͬ��"
					+ zdid + "Error��");
		}

		return row;
	}

}
