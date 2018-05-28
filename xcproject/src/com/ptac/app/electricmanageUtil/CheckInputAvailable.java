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
 * @author xuzehua ��ѵ����ݵ����ӣ���������ͼ��
 */
public class CheckInputAvailable {
	private double averagefees;
	private String averagefeestrueorfalse,startshi;
	String accountid,accountname;
	String dianbiaoid, dianfei, beilv, linelosstype, linelossvalue, edhdl,htendtime,
			dbid, qsdbdl, dfzflx , liuchenghao,shi,property,zlfh,jlfh,scb,stationtype,gdfs;// ��ȫ��ֵ
	String scdf,scdl,lastunitprice;//�ϴε�ѣ��ϴε������ϴε���
	String dbds="",xgbzw="",hbzq,bzz;
	String dlmess ="";
	String dfmess ="";
	String dlauto = "0";
	String dfauto = "0";
	double dlzylx01, dlzylx02, dlzylx03, dlzylx04, dlzylx05, dlzylx06;// ����
	double dfzylx01, dfzylx02, dfzylx03, dfzylx04, dfzylx05, dfzylx06;// ���
	// �����ɵ����ݣ������룩
	double lastdegree, thisdegree, tzdegree, tzdianfei,
			actualdianfei//���˵�������ж�
			, pjje;
	String lasttime, thistime, bzmonth, blhdl,sjydl,
			enterperson,dbydl;
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
	 *            ��������
	 * @param wrong
	 *            ��������
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		accountid = account.getAccountId();
		accountname = account.getAccountName();
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
        
		// / ʱ��ıȽ�

		if (row.isEmpty()) {
			row = this.check13(content);
		}
//		if (row.isEmpty()) {
//			row = this.check15(content);
//		}
		if(row.isEmpty()){
			row = this.checkhtendtime(content);
		}
		// / ��С���
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

		// ����
		if (row.isEmpty()) {
			row = this.check21(content);
		}

		// ��ʼ����ǰ�ļ��
		if (row.isEmpty()) {
			row = this.check22(content);
		}
		// ��ʼ��̯�ļ�飬û���⿪ʼ�� ��������ֵ

		if (row.isEmpty()) {
			row = this.check23(content);
		}
		if(row.isEmpty()){
        	row = this.check24(content);
        }
		if(row.isEmpty()){
        	row = this.check26(content);
        }
		// ǰ�ڹ�����ɿ�ʼ��ӵ��
        if(row.isEmpty()){
        	row = this.check25(content);
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
			System.out.println("������Ϣ��ѯ"+sql);
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
		if ("".equals(property) || "null".equals(property)) {
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
	 * @author WangYiXiao 2014-12-08
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
					+ dianbiaoid + "�ϴε�����Ϊ��");
		} else {
			if (!Format.isNumber(lastd.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�ϴε�������ʽ����ȷ");
			} else {
				if (Format.str_d(lastd.trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "�ϴε�����������ڵ���0");
				} else {
					lastdegree = Format.str_d(lastd.trim());
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
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���ε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ε�������ʽ����ȷ");
			} else {
				if (Format.str_d(content[5].toString().trim())<0) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "���ε�����������ڵ���0");
				} else {
					thisdegree = Format.str_d(content[5].toString().trim());
				}
			}

		}
		return row;
	}

	/**
	 * �õ������� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�õ�������Ϊ��");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�õ���������ʽ����ȷ");
			} else {
				tzdegree = Format.d2(Format.str_d(content[8].toString().trim()));
			}

		}
		return row;
	}

	/**
	 * ʵ���õ������� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���˵���Ϊ��");
		} else {
			if (!Format.isNumber(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���˵�����ʽ����ȷ");
			}
		}
		return row;
	}

//	/**
//	 * ��ʼ�·� ��Ϊ�� ��ʽ
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
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + "��ʼ�·� Ϊ��");
//		} else {
//			if (!Format.isMonth(content[10].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "��ʼ�·� ��ʽ����ȷ");
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
//	 * �ͽ����·� ��Ϊ�� ��ʽ
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
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + "�����·�Ϊ��");
//		} else {
//			if (!Format.isMonth(content[11].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "�����·� ��ʽ����ȷ");
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
//	 * ��ʵ�·ݺͽ����·ݵĴ�С������
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
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "��ʼ�·ݴ��ڽ����·�");
//			}
//		}
//		return row;
//	}

	/**
	 * ���ε��� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���ε��� Ϊ��");
		} else {
			if (!Format.isNumber(content[13].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ε��� ��ʽ����ȷ");
			} else {
				if (!Format.str4(content[13].toString().trim()).equals(
						Format.str4(dianfei.toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "���" + dianbiaoid + "���ε���  �����ʵ�ʵ��۲����ϣ� ϵͳ����Ϊ��"
							+ Format.str4(dianfei.toString().trim()));
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
	synchronized Vector<String> check08(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (content[14] == null || "".equals(content[14].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���õ��� Ϊ��");
		} else {
			if (!Format.isNumber(content[14].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���õ��� ��ʽ����ȷ");
			} else {
				tzdianfei = Format.d2(Format.str_d(content[14].toString().trim()));
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
		if (content[15] == null || "".equals(content[15].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���˵�ѽ�� Ϊ��");
		} else {
			if (!Format.isNumber(content[15].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���˵�ѽ�� ��ʽ����ȷ");
			} else {
				actualdianfei = Format.d2(Format.str_d(content[15].toString().trim()));
			}
		}
		return row;
	}

//	/**
//	 * �����·� ��Ϊ��
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
//			row.add(content[0].toString() + content[2].toString() + "���"
//					+ dianbiaoid + "�����·� Ϊ��");
//		} else {
//			if (!Format.isMonth(content[18].toString().trim())) {
//				for (int j = 0; j < content.length; j++) {
//					row.add(content[j].toString().trim());
//				}
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "�����·� ��ʽ����ȷ");
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
	 * �ϴγ���ʱ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�ϴγ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(lastt.trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�ϴγ���ʱ��   ��ʽ����ȷ");
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
	 * ���γ���ʱ�� ��Ϊ�� ��ʽ
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���γ���ʱ��   Ϊ��");
		} else {
			if (!Format.isTime(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���γ���ʱ��   ��ʽ����ȷ");
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
	 * �ϴγ���ʱ����ڱ��γ���ʱ��
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
	 * ����ʱ��ĸ�ʽ
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "����ʱ���ʽ����ȷ");
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
	 * Ʊ�ݽ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Ʊ�ݽ��   Ϊ��");
		}else{
			if (!Format.isNumber(content[17].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Ʊ�ݽ�� ��ʽ����ȷ");
			} else {
				pjje = Format.str_d(content[17].toString().trim());
			}
		}
		return row;
	}

//	/**
//	 * Ʊ��ʱ��
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
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "Ʊ��ʱ�� ��ʽ����ȷ");
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
	 * ��Ʊʱ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "��Ʊʱ�� ��ʽ����ȷ");
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
//	 * ����ʱ��
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
//				row.add(content[0].toString() + content[2].toString() + "���"
//						+ dianbiaoid + "����ʱ�� ��ʽ����ȷ");
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
	 * �Ƚ�ʵ���õ�����ʵ�ʽ��ѽ�� ������ѵļ��� ǰ�ᣬ
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> check21(Object[] content) {
		Vector<String> row = new Vector<String>();
		double dl = 0.00, df = 0.00;
		double sjdl = 0.00;//ʵ���õ��������ζ���-�ϴζ���+��������
		// ʵ���õ����� �õ�� ���� �ж�
		double thisdegrees = Format.d2(Format.str_d(content[5].toString()
				.trim()));
		double lastdegrees = Format.d2(Format.str_d(lastd
				.trim()));
		double beilv_temp = 0.0;// �鴦 ���ж�
		double linelossvalue_temp = 0.0;// �鴦 ���ж�
		double floatdegrees = Format.d2(Format.str_d(content[8].toString()
				.trim()));
		if (linelosstype == null || "".equals(linelosstype.toString().trim())) {
			linelosstype = "";
		}
		if ("02xsbl".equals(linelosstype)) {//�������
				linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
			
			if (Format.str_d(beilv.toString().trim()) == 0) {
				beilv_temp = 1.0;
			}else{
				beilv_temp = Double.parseDouble(beilv); 
			}	
		} else {
			//if("01xstz".equals(linelosstype)){//�������
				linelossvalue_temp = Format.str_d(linelossvalue.toString().trim());
				if (Format.str_d(beilv.toString().trim()) == 0) {
					beilv_temp = 1.0;
				}else{
					beilv_temp = Double.parseDouble(beilv);
				}
			//}
		}
		if (content[9] != null && !"".equals(content[9].toString().trim())) {
			if ("02xsbl".equals(linelosstype)) {//�������
				// (��thisdegree-lastdegree��*linelossvalue+floatdegree)*mpower
				// ������λС��
				//��(���ε�����-�ϴε�������*��1+���������+����������*����
				dl = Format.d2(((thisdegrees - lastdegrees)
						* (1 + linelossvalue_temp) + floatdegrees)
						* beilv_temp);//���ʺĵ���
				sjdl = Format.d2((thisdegrees - lastdegrees)+floatdegrees);//ʵ���õ���
						
				if (dl != Format.d2(Format.str_d(content[9].toString().trim()))) {
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "���" + dianbiaoid + "����������� ��ϵͳ�������Ϊ��" + dl + ",�ϴε������������ݿ⣬Excel����е��ϴε������������޸� ");
					return row;
				} else {
					blhdl = Double.toString(dl);
					sjydl = Double.toString(sjdl);//ʵ���õ���
				}
			} else {
				// Thisdegree-lastdegree+linelossvalue+floatdegree��*mpower
				sjdl = Format.d2((thisdegrees - lastdegrees)+floatdegrees);//ʵ���õ���
				dl = Format
						.d2((thisdegrees - lastdegrees + linelossvalue_temp + floatdegrees)
								* beilv_temp);//���ʺĵ���
				if (dl != Format.d2(Format.str_d(content[9].toString().trim()))) {
					if (dl != Format.d2(Format.str_d(content[9].toString()
							.trim()))) {
						for (int j = 0; j < content.length; j++) {
							row.add(content[j].toString().trim());
						}
						row.add(content[0].toString() + content[2].toString()
								+ "���" + dianbiaoid + "����������� ��ϵͳ�������Ϊ��" + dl + ",�ϴε������������ݿ⣬Excel����е��ϴε������������޸� ");
						return row;
					}
				} else {
					blhdl = Double.toString(dl);//���ʺĵ���
					sjydl = Double.toString(sjdl);//ʵ���õ���
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
							+ "���" + dianbiaoid + "��Ѽ������ ��ϵͳ������Ϊ��" + df);
					return row;
				} else if (df>0 && df<5){
					for (int j = 0; j < content.length; j++) {
						row.add(content[j].toString().trim());
					}
					row.add(content[0].toString() + content[2].toString()
							+ "���" + dianbiaoid + "��ѽ���С��5Ԫ");
					return row;
					
				}else {
					// ���ݴ���û�����⣬���Excel �ĵ���
				
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

	//�����վ����>6
	synchronized Vector<String> check22(Object[] content) {
		Vector<String> row = new Vector<String>();
		if("1".equals(averagefeestrueorfalse)){
			ElectricTool tool = new ElectricTool();
			jszq_temp = tool.getQuot(lasttime, thistime)+1;//���������
			double averagefees_temp = actualdianfei/jszq_temp;
			if (shiLimit() && averagefees_temp > averagefees) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�վ����>"+averagefees+" ���ڵ�ѹ����µ�ѵ���ǿ���ܵ���");
			}
		}
		
		return row;
	}
	/**�����Щ���п�����ǿ�����ж�
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
	 * �ж����ݿ����Ƿ�����������(�ظ�)�� ���ݿ������ظ����ݷ���true�����򷵻�false
	 * 
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check23(Object[] content) {
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
	synchronized Vector<String> check25(Object[] content) {
		Vector<String> row = new Vector<String>();
		String[] dl1, dl2, dl3, dl4,cbbl;
		String[] df1, df2;
		String city = "1";//�м����״̬����˱�־
		String qxzr = "1";//�����������״̬ 0����Ҫ���,1:����Ҫ��ˣ���˱�־    0��δ��ˣ�1�����ͨ����2��˲�ͨ��
		String cityzr = "1";//�м��������״̬����˱�־	
		
		Share share = new Share();
		ElectricityInfo info = new ElectricityInfo();
		ElectricTool tool = new ElectricTool();
		ElecBillDao dao = new ElecBillDaoImp();
		String[] shiandxian = tool.getShiAndXian(dbid);
        if(Format.str_d(beilv)==0){
       	 beilv = "1";
        }
        dbydl = String.valueOf((thisdegree-lastdegree)*Format.str_d(beilv));//����õ��� = (���ε�����-�ϴε�����)*����
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
		cbbl = tool.getQsdbdlCbbl(dbydl, thistime, lasttime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);//2014-10-22��ʡ�����,�ϲ�����,��׼ֵ
		dl4 = tool.checkBcdl2(cbbl[0]);
    	//String[] expecthigh = tool.checkExceptAndHigh(dbid, qsdbdl, Double.toString(actualdianfei), Double.toString(actualdegree), thistime, lasttime, null, null, "2",null,"2");//�쳣���߶�
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

		info.setLastdegree(String.valueOf(lastdegree));
		info.setThisdegree(String.valueOf(thisdegree));
		info.setActualdegree(String.valueOf(sjydl));
		info.setLastdatetime(lasttime);
		info.setThisdatetime(thistime);
		info.setBlhdl(blhdl);
		info.setInputoperator(enterperson);
		info.setEntrypensonnel(accountname);
		info.setPayoperator(payperson);
		info.setLiuchenghao(liuchenghao == null ? "":liuchenghao);//Ԥ֧���̺�

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
		String jszq = dl3[4];//��������
		
		info.setScdf(scdf);
		info.setScdl(scdl);
		info.setLastunitprice(lastunitprice);
		
		hbzq = cbbl[1];//�ϲ�����
		bzz = cbbl[2];//��׼ֵ
		// ��������
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
		
        if(!"�����ѵ��ɹ���".equals(rtmess)){
        	for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "Error��");
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
	synchronized Vector<String> check26(Object[] content) {
		Vector<String> row = new Vector<String>();
		pjlx = content[16].toString().trim();
		if (content[16] == null || "".equals(content[16].toString().trim())) {
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
					row.add(content[0].toString() + content[2].toString() + "���"
							+ dianbiaoid + "Ʊ�����Ͳ���ȷ��");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
}
