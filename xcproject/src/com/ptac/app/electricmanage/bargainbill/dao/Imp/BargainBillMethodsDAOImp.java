package com.ptac.app.electricmanage.bargainbill.dao.Imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillCountBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillMessageBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSaveBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSelectConditionsBean;
import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.tool.BargainBillsInputUtil;

public class BargainBillMethodsDAOImp implements BargainBillMethodsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public synchronized String addBargainBill(BargainBillSaveBean formBean,
			String start, String end) {

		// birthday = birthday.length()>0?birthday:null;
		String msg = "";
		String kptime = formBean.getKptime();
		String notetime = formBean.getNotetime();
		String memo = formBean.getMemo();
		String notetypeid = formBean.getNotetypeid();

		String danjia = formBean.getDanjia();

		if (formBean.getThisdegree() == null) {
		}
		if (formBean.getDanjia() == null) {
			danjia = "";
		}
		if (formBean.getThisdatetime() == null) {
		}
		if (formBean.getFloatpay() == null) {
		}
		if (formBean.getInputoperator() == null) {

		}
		if (formBean.getPaydatetime() == null) {
		}
		if (formBean.getPayoperator() == null) {
		}
		if (formBean.getKptime() == null) {
			kptime = " ";
		} else {
			kptime = formBean.getKptime();
		}
		if (formBean.getNotetime() == null) {
			notetime = " ";
		} else {
			notetime = formBean.getNotetime();
		}
		if (formBean.getMemo() == null) {
			memo = " ";
		} else {
			memo = formBean.getMemo();
		}
		if (formBean.getNotetypeid() == null) {
			notetypeid = " ";
		} else {
			notetypeid = formBean.getNotetypeid();
		}
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);

		String df = formBean.getMoney();
		double dfyu = Double.parseDouble(df) % time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		// ������Ӫ�ߵ��(����)�ֵ�ÿ����
		double networkdfys = Double.parseDouble(formBean.getNetworkdf()) % time;
		int networkdf = (int) ((Double.parseDouble(formBean.getNetworkdf()) - networkdfys) / time);
		// ��Ϣ��֧���ߵ�ѷֵ�ÿ����
		double informatizationdfys = Double.parseDouble(formBean
				.getInformatizationdf())
				% time;
		int informatizationdf = (int) ((Double.parseDouble(formBean
				.getInformatizationdf()) - informatizationdfys) / time);
		// �����ۺ��ߵ�ѣ��칫���ֵ�ÿ����
		double administrativedfys = Double.parseDouble(formBean
				.getAdministrativedf())
				% time;
		int administrativedf = (int) ((Double.parseDouble(formBean
				.getAdministrativedf()) - administrativedfys) / time);
		// �г�Ӫ���ߵ��(Ӫҵ)�ֵ�ÿ����
		double marketdfys = Double.parseDouble(formBean.getMarketdf()) % time;
		int marketdf = (int) ((Double.parseDouble(formBean.getMarketdf()) - marketdfys) / time);
		// ����Ͷ���ߵ�ѷֵ�ÿ����
		double builddfys = Double.parseDouble(formBean.getBuilddf()) % time;
		int builddf = (int) ((Double.parseDouble(formBean.getBuilddf()) - builddfys) / time);
		// �������߷ֵ�ÿ����
		double dddfys = Double.parseDouble(formBean.getDddf()) % time;
		int dddf = (int) ((Double.parseDouble(formBean.getDddf()) - dddfys) / time);
		List nwdffentan = new ArrayList();
		List imdffentan = new ArrayList();
		List asdffentan = new ArrayList();
		List mkdffentan = new ArrayList();
		List bldffentan = new ArrayList();
		List dddffentan = new ArrayList();
		List dflist = new ArrayList();
		List year_month = new ArrayList();
		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			String yue = String.valueOf(starty);
			if (yue.length() == 1)
				yue = "0" + yue;
			String year_month_tmp = startn + "-" + yue;
			starty++;
			if (starty > 12) {
				starty = 1;
				startn++;
			}
			year_month.add(year_month_tmp);
			if (i == time - 1) {
				dflist.add(dfPermonth + dfyu);
				nwdffentan.add(networkdf + networkdfys);
				imdffentan.add(informatizationdf + informatizationdfys);
				asdffentan.add(administrativedf + administrativedfys);
				mkdffentan.add(marketdf + marketdfys);
				bldffentan.add(builddf + builddfys);
				dddffentan.add(dddf + dddfys);
			} else {
				dflist.add(dfPermonth);
				nwdffentan.add(networkdf);
				imdffentan.add(informatizationdf);
				asdffentan.add(administrativedf);
				mkdffentan.add(marketdf);
				bldffentan.add(builddf);
				dddffentan.add(dddf);
			}
		}
		
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
//		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String s = s1.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		if("1".equals(formBean.getCountyauditstatus())){//���������ж�
			formBean.setCountyaudittime(s);
		}else{
			formBean.setCountyaudittime(null);
		}
		
		if("1".equals(formBean.getCityzrauditstatus())){//������
			formBean.setCityzraudittime(s);
		}else{
			formBean.setCityzraudittime(null);
		}
		if("1".equals(formBean.getCITYAUDIT())){//�м����
			formBean.setCityaudittime(s);
		}else{
			formBean.setCityaudittime(null);
		}
		
		for (int i = 0; i < time; i++) {
			StringBuffer sql = new StringBuffer();
			sql
					.append("INSERT INTO PREPAYMENT(ID,STATIONID,PJJE,HTBH,PREPAYMENT_AMMETERID,FEETYPEID,MONEY,STARTDEGREE," +
							"INPUTDATETIME,INPUTOPERATOR,AUDITDATE,AUDITOPERATOR,FINANCIALDATE,FINANCIALOPERATOR,MEMO,NOTETYPEID," +
							"NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,UUID,ACCOUNTMONTH,STARTMONTH,ENDMONTH,financeaudit,entrypensonnel," +
							"entrytime,NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF,DDDF,DANJIA,YFFBZW,"
							+ "DIANLIANG,"
							+ "AUTOAUDITSTATUS,"
							+ "AUTOAUDITDESCRIPTION,"
							+ "MANUALAUDITNAME,"
							+ "MANUALAUDITSTATUS,"
							+ "MANUALAUDITDATETIME,"
							+ "COUNTYAUDITSTATUS,"
							+ "CITYZRAUDITSTATUS,"
							+ "COUNTYAUDITNAME,"
							+ "COUNTYAUDITTIME,"
							+ "CITYZRAUDITNAME,"
							+ "CITYZRAUDITTIME,"
							+ "CSDB,"
							+ "DEDHDL," +"CITYAUDIT,"+"jszq,STARTDATE,ENDDATE,flag,countyflag,cityflag,EDHDL,QSDBDL,CITYAUDITTIME," +
									"DBYDL,BEILV,HBZQ,BZZ,SCB,ZLFH,JLFH,DFZFLXCODE,GDFSCODE,STATIONTYPECODE,PROPERTYCODE "+
									" )");
			sql.append(" VALUES('" + i + "','" + formBean.getStationid()
					+ "','" + formBean.getPjje() + "','"
					+ formBean.getHtbianhao() + "','"
					+ formBean.getPrepayment_ammeterid() + "',NULL,'"
					+ dflist.get(i) + "',");
			sql.append("NULL,NULL,NULL,NULL,NULL,NULL,NULL,'"
					+ memo
					+ "','"
					+ notetypeid
					+ "',TO_DATE('"
					+ notetime
					+ "','yyyy-mm-dd'),TO_DATE('"
					+ kptime
					+ "','yyyy-mm-dd'),NULL,NULL,'"
					+ uuid
					+ "',TO_DATE('"
					+ formBean.getAccountmonth()
					+ "','yyyy-mm'),TO_DATE('"
					+ year_month.get(i)
					+ "','yyyy-mm'),TO_DATE('"
					+ year_month.get(i)
					+ "','yyyy-mm'),'1','"
					+ formBean.getEntrypensonnel()
					+ "',"
					+ s
					+ ",'"
					+ nwdffentan.get(i)
					+ "','"
					+ imdffentan.get(i)
					+ "','"
					+ asdffentan.get(i)
					+ "','"
					+ mkdffentan.get(i)
					+ "','"
					+ bldffentan.get(i)
					+ "','"
					+ dddffentan.get(i)
					+ "','"
					+ danjia
					+ "','1','"
					+ formBean.getDianliang()
					+ "','"
					+ formBean.getAutoauditstatus()
					+ "','"
					+ formBean.getAutoauditdescripthion()
					+ "','"
					+ formBean.getManualauditname()
					+ "','"
					+ formBean.getManualauditstatus()
					+ "',"
					+ formBean.getManualauditdatetime()
					+ ",'"
					+ formBean.getCountyauditstatus()
					+ "','"
					+ formBean.getCityzrauditstatus()
					+ "','"
					+ formBean.getCountyauditname()
					+ "',"
					+ formBean.getCountyaudittime()
					+ ",'"
					+ formBean.getCityzrauditname()
					+ "',"
					+ formBean.getCityzraudittime()
					+ ",'"
					+ formBean.getCsdb()
					+ "','"
					+ formBean.getDedhdl()
					+ "','"
					+formBean.getCITYAUDIT()
					+ "','"
					+formBean.getJszq()
					+ "',TO_DATE('"+formBean.getStartdate()
					+ "','yyyy-mm-dd'),TO_DATE('"+formBean.getEnddate()
					+ "','yyyy-mm-dd'),'"+formBean.getFlag()
					+ "','"+formBean.getCountyflag()
					+ "','"+formBean.getCityflag()
					+ "','"+formBean.getEdhdl()
					+ "','"+formBean.getQsdbdl()
					+ "',"+formBean.getCityaudittime()
					+","+formBean.getDbydl()
					+","+formBean.getBeilv()
					+","+formBean.getHbzq()
					+","+formBean.getBzz()
					+","+formBean.getScb()
					+","+formBean.getZlfh()
					+","+formBean.getJlfh()
					+",'"+formBean.getDfzflxcode()
					+"','"+formBean.getGdfscode()
					+"','"+formBean.getStationtypecode()
					+"','"+formBean.getPropertycode()
					+ "')");
			System.out.println("���ӵ�ѵ���"+sql.toString());
			sqlBatch[i] = sql.toString();
		}
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);

			boolean flag_save = false;
			for (int i = 0; i < sqlBatch.length; i++) {
				ps = conn.prepareStatement(sqlBatch[i]);
				int j = ps.executeUpdate();

				if (j > 0) {
					flag_save = true;
				} else {
					flag_save = false;
					break;
				}
			}

			if (flag_save == true) {
				msg = "��Ӻ�ͬ�ɹ�!";
				conn.commit();
			} else {
				msg = "��Ӻ�ͬʧ��!";
				conn.rollback();
			}

			conn.setAutoCommit(true);
		} catch (Exception de) {
			try {
				conn.rollback();
			} catch (SQLException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			db.free(null, ps, conn);
		}

		return msg;

	}

	@Override
	public synchronized String deleteBargainBills(String id) {
		String msg = "ɾ����ѵ���Ϣʧ�ܣ�";

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = db.getConnection();
			String sql = "delete from prepayment where uuid=(select uuid from yufufeiview where id=?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			System.out.println("�º�ͬ��ɾ����ͬ��");

			int i = ps.executeUpdate();
			if (i > 0) {
				msg = "ɾ����ͬ����Ϣ�ɹ���";
			} else {
				msg = "ɾ����ѵ���Ϣʧ�ܣ�";
			}
			db.commit();
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			db.free(null, ps, conn);
		}

		return msg;
	}

	@Override
	public synchronized List<BargainBillMessageBean> findBargainBills(
			BargainBillSelectConditionsBean bbscb, String loginID) {
		List<BargainBillMessageBean> ls = new ArrayList<BargainBillMessageBean>();

		String whereStr = getWhereString(bbscb);
		System.out.println("whereStr"+whereStr);

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = db.getConnection();
			String fzzdstr = getFuzeZdid(loginID);
			String sql = "select p.pjje,p.htbh,zd.zdcode,zd.jzname,to_char(p.accountmonth,'yyyy-mm') accountmonth,p.prepayment_ammeterid,d.dbname,p.money,to_char(p.startmonth,'yyyy-mm') startmonth,to_char(p.endmonth,'yyyy-mm') endmonth,p.id,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype"
					+ " from yufufeiview p,dianbiao d,zhandian zd"
					+ " where d.dbid=p.prepayment_ammeterid and zd.id=d.zdid and (p.manualauditstatus = '0' or p.manualauditstatus = '-2' or p.manualauditstatus is null) and d.dfzflx='dfzflx02'"
					+ " and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = ? )) "
					+ fzzdstr + ")";
			ps = conn.prepareStatement(sql);

			ps.setString(1, loginID);

			System.out.println("��ѯ��ͬ�����£�:"+sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				/* ��ȡ���� */
				String pjje = rs.getString("pjje");
				String htbh = rs.getString("htbh");
				String zdcode = rs.getString("zdcode");
				String jzname = rs.getString("jzname");
				String accountmonth = rs.getString("accountmonth");
				String prepayment_ammeterid = rs
						.getString("prepayment_ammeterid");
				String dbname = rs.getString("dbname");
				String money = rs.getString("money");
				String startmonth = rs.getString("startmonth");
				String endmonth = rs.getString("endmonth");
				String id = rs.getString("id");
				String stationtype = rs.getString("stationtype");

				BargainBillMessageBean bean = new BargainBillMessageBean(pjje,
						htbh, zdcode, jzname, accountmonth,
						prepayment_ammeterid, dbname, money, startmonth,
						endmonth, id, stationtype);

				ls.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.free(rs, ps, conn);
		}

		return ls;
	}
	
//��ͬ���޸ı���
	@SuppressWarnings("unchecked")
	@Override
	public synchronized String updateBargainBills(BargainBillSaveBean formBean,
			String id, String start, String end) {

		String msg = "�޸ĺ�ͬ����Ϣʧ�ܣ�";
		DataBase db = new DataBase();

		String kptime = formBean.getKptime();
		String noteno = formBean.getNoteno();
		String notetime = formBean.getNotetime();
		String memo = formBean.getMemo();
		String danjia = formBean.getDanjia();
		String notetypeid = formBean.getNotetypeid();
		if (kptime == null) {
			kptime = " ";
		} else {
			kptime = formBean.getKptime();
		}
		if (formBean.getDanjia() == null) {
			danjia = "";
		}
		if (notetime == null) {
			notetime = " ";
		} else {
			notetime = formBean.getNotetime();
		}
		if (memo == null) {
			memo = " ";
		} else {
			memo = formBean.getMemo();
		}
		if (notetypeid == null) {
			notetypeid = " ";
		} else {
			notetypeid = formBean.getNotetypeid();
		}
		if (noteno == null) {
			noteno = " ";
		} else {
			noteno = formBean.getNoteno();
		}

		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);

		String df = formBean.getMoney();
		double dfyu = Double.parseDouble(df) % time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;

		// ������Ӫ�ߵ��(����)�ֵ�ÿ����
		double networkdfys = Double.parseDouble(formBean.getNetworkdf()) % time;
		int networkdf = (int) ((Double.parseDouble(formBean.getNetworkdf()) - networkdfys) / time);
		// ��Ϣ��֧���ߵ�ѷֵ�ÿ����
		double informatizationdfys = Double.parseDouble(formBean
				.getInformatizationdf())
				% time;
		int informatizationdf = (int) ((Double.parseDouble(formBean
				.getInformatizationdf()) - informatizationdfys) / time);
		// �����ۺ��ߵ�ѣ��칫���ֵ�ÿ����
		double administrativedfys = Double.parseDouble(formBean
				.getAdministrativedf())
				% time;
		int administrativedf = (int) ((Double.parseDouble(formBean
				.getAdministrativedf()) - administrativedfys) / time);
		// �г�Ӫ���ߵ��(Ӫҵ)�ֵ�ÿ����
		double marketdfys = Double.parseDouble(formBean.getMarketdf()) % time;
		int marketdf = (int) ((Double.parseDouble(formBean.getMarketdf()) - marketdfys) / time);
		// ����Ͷ���ߵ�ѷֵ�ÿ����
		double builddfys = Double.parseDouble(formBean.getBuilddf()) % time;
		int builddf = (int) ((Double.parseDouble(formBean.getBuilddf()) - builddfys) / time);
		// �����������ߵ�ѷֵ�ÿ����
		double dddfys = Double.parseDouble(formBean.getDddf()) % time;
		int dddf = (int) ((Double.parseDouble(formBean.getDddf()) - dddfys) / time);
		List nwdffentan = new ArrayList();
		List imdffentan = new ArrayList();
		List asdffentan = new ArrayList();
		List mkdffentan = new ArrayList();
		List bldffentan = new ArrayList();
		List dddffentan = new ArrayList();
		List dflist = new ArrayList();
		List year_month = new ArrayList();
		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			String yue = String.valueOf(starty);
			if (yue.length() == 1)
				yue = "0" + yue;
			String year_month_tmp = startn + "-" + yue;
			starty++;
			if (starty > 12) {
				starty = 1;
				startn++;
			}
			year_month.add(year_month_tmp);
			if (i == time - 1) {
				dflist.add(dfPermonth + dfyu);
				nwdffentan.add(networkdf + networkdfys);
				imdffentan.add(informatizationdf + informatizationdfys);
				asdffentan.add(administrativedf + administrativedfys);
				mkdffentan.add(marketdf + marketdfys);
				bldffentan.add(builddf + builddfys);
				dddffentan.add(dddf + dddfys);
			} else {
				dflist.add(dfPermonth);
				nwdffentan.add(networkdf);
				imdffentan.add(informatizationdf);
				asdffentan.add(administrativedf);
				mkdffentan.add(marketdf);
				bldffentan.add(builddf);
				dddffentan.add(dddf);
			}
		}
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		//SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		//String s = s1.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		String delete = "delete from prepayment p where  p.uuid=(select uuid from prepayment where id='"
				+ id + "')";
		System.out.println("ɾ�����к�ͬ����");
		
		
		if("1".equals(formBean.getCountyauditstatus())){//���������ж�
			formBean.setCountyaudittime(s);
		}else{
			formBean.setCountyaudittime(null);
		}
		
		if("1".equals(formBean.getCityzrauditstatus())){//������
			formBean.setCityzraudittime(s);
		}else{
			formBean.setCityzraudittime(null);
		}
		if("1".equals(formBean.getCITYAUDIT())){//�м����
			formBean.setCityaudittime(s);
		}else{
			formBean.setCityaudittime(null);
		}

		for (int i = 0; i < time; i++) {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PREPAYMENT(ID,STATIONID,PJJE,HTBH,PREPAYMENT_AMMETERID,"
					+ "FEETYPEID,MONEY,STARTDEGREE,INPUTDATETIME,INPUTOPERATOR," 
					+ "AUDITDATE,AUDITOPERATOR,FINANCIALDATE,FINANCIALOPERATOR,MEMO," 
					+ "NOTETYPEID,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME," 
					+ "UUID,ACCOUNTMONTH,STARTMONTH,ENDMONTH,financeaudit," 
					+ "ENTRYPENSONNEL,ENTRYTIME,NETWORKDF,INFORMATIZATIONDF," 
					+ "ADMINISTRATIVEDF,MARKETDF,BUILDDF,DDDF,DANJIA,YFFBZW,"
					+ "DIANLIANG,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,MANUALAUDITNAME,MANUALAUDITSTATUS,"
					+ "MANUALAUDITDATETIME,COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,COUNTYAUDITNAME,COUNTYAUDITTIME,"
					+ "CITYZRAUDITNAME,CITYZRAUDITTIME,CSDB,"
					+ "DEDHDL,CITYAUDIT,jszq,STARTDATE,ENDDATE,flag,countyflag,cityflag,CITYAUDITTIME," 
					+ "EDHDL,QSDBDL,DBYDL,BEILV,HBZQ,BZZ,SCB,ZLFH,JLFH,PROPERTYCODE,STATIONTYPECODE,DFZFLXCODE,GDFSCODE "+
									")");
			sql.append(" VALUES('" + i + "','" + formBean.getStationid() + "','" + formBean.getPjje() + "','" + formBean.getHtbianhao() + "','" + formBean.getPrepayment_ammeterid() + "',NULL,'" + dflist.get(i) + "',");
			sql.append("NULL,NULL,NULL,NULL,NULL,NULL,NULL,'"
					+ memo + "','" + notetypeid + "',TO_DATE('" + notetime + "','yyyy-mm-dd'),TO_DATE('" + kptime + "','yyyy-mm-dd'),NULL,NULL,'"
					+ uuid + "',TO_DATE('" + formBean.getAccountmonth() + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),'1','"
					+ formBean.getEntrypensonnel() + "'," + s + ",'" + nwdffentan.get(i) + "','" + imdffentan.get(i) + "','"
					+ asdffentan.get(i) + "','" + mkdffentan.get(i) + "','" + bldffentan.get(i) + "','" + dddffentan.get(i)
					+ "','" + danjia + "','1','" + formBean.getDianliang() + "','" + formBean.getAutoauditstatus() + "','"
					+ formBean.getAutoauditdescripthion() + "','" + formBean.getManualauditname() + "','" + formBean.getManualauditstatus()
					+ "'," + formBean.getManualauditdatetime() + ",'" + formBean.getCountyauditstatus()
					+ "','" + formBean.getCityzrauditstatus() + "','" + formBean.getCountyauditname()
					+ "'," + formBean.getCountyaudittime() + ",'" + formBean.getCityzrauditname()
					+ "'," + formBean.getCityzraudittime() + ",'" + formBean.getCsdb()
					+ "','" + formBean.getDedhdl() + "','" +formBean.getCITYAUDIT()
					+ "','" +formBean.getJszq() + "',TO_DATE('"+formBean.getStartdate()
					+ "','yyyy-mm-dd'),TO_DATE('"+formBean.getEnddate() + "','yyyy-mm-dd'),'"+formBean.getFlag()
					+ "','"+formBean.getCountyflag() + "','"+formBean.getCityflag() + "',"+formBean.getCityaudittime()
					+ ",'"+formBean.getEdhdl() + "','"+formBean.getQsdbdl() +"',"+formBean.getDbydl()
					+","+formBean.getBeilv() +","+formBean.getHbzq() +","+formBean.getBzz() +","+formBean.getScb()
					+","+formBean.getZlfh() +","+formBean.getJlfh() +",'"+formBean.getPropertycode()
					+"','"+formBean.getStationtypecode() +"','"+formBean.getDfzflxcode() +"','"+formBean.getGdfscode()
					+ "')");

			System.out.println("�޸ĺ�ͬ��"+sql.toString());
			sqlBatch[i] = sql.toString();
		}

		msg = "�޸ĺ�ͬ����Ϣʧ�ܣ�";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(delete);
			ps.executeUpdate();
			
			boolean flag_save = false;
			for (int i = 0; i < sqlBatch.length; i++) {
				ps= conn.prepareStatement(sqlBatch[i]);
				int j = ps.executeUpdate();

				if (j > 0) {
					flag_save = true;
				} else {
					flag_save = false;
					break;
				}
			}

			if (flag_save == true) {
				msg = "�޸ĺ�ͬ�ɹ�!";
				conn.commit();
			} else {
				msg = "�޸ĺ�ͬʧ��!";
				conn.rollback();
			}

			conn.setAutoCommit(true);
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			db.free(null, ps, conn);
		}

		return msg;

	}
//��ͬ����	
	@SuppressWarnings("unchecked")
	public Vector input(Vector content, CountForm cform, Account account,
			HttpServletRequest request, HttpServletResponse response) {
		Vector wrongContent = new Vector();
		HttpSession session = request.getSession();
		// �������ݱ���
		Vector<String> wcell = new Vector<String>();
		// ת���� ������
		Object[] rows = content.toArray();
		int inputdata = rows.length;
		int inputerror = 0;
		int inputcount = 0;
		int inputlast = rows.length;
		session.setAttribute("inputdata", rows.length);
		for (int temp = 1; temp < rows.length; temp++) {
			// ÿ�����е����ݵı���
			Object[] cells = ((Vector) rows[temp]).toArray();
			//��ͬ�����ж�
			wcell = new BargainBillsInputUtil().inputCheck(cells, account);
			
			if (!wcell.isEmpty()) {
				wrongContent.add(wcell);
				inputerror++;
			}else{
				inputcount++;
			}
			inputlast = inputdata - inputerror -inputcount;
			if(session.getAttribute("inputerror")!=null||session.getAttribute("inputcount")!=null
					||session.getAttribute("inputlast")!=null){
				session.removeAttribute("inputerror");
				session.removeAttribute("inputcount");
				session.removeAttribute("inputlast");
			}
			session.setAttribute("inputerror", inputerror);
			session.setAttribute("inputcount", inputcount);
			session.setAttribute("inputlast", inputlast);
			
		}
		session.removeAttribute("inputerror");
		session.removeAttribute("inputcount");
		session.removeAttribute("inputlast");
		session.removeAttribute("inputdata");
		

		cform.setCg((content.size() - 1) - wrongContent.size());
		cform.setBcg(wrongContent.size());
		cform.setZg((content.size() - 1));

		return wrongContent;
	}
	
	//��ͬ���뱣��
	@SuppressWarnings("unchecked")
	public synchronized String saveXls(BargainBillSaveBean formBean,
			String bzw,String start,String end){
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		String uuid = uuid2+Long.toString(uuid1).substring(3);
		
		String df = formBean.getMoney();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		
	      
	    double networkdfys=Double.parseDouble(formBean.getNetworkdf())%time;
	    int networkdf = (int) ((Double.parseDouble(formBean.getNetworkdf())-networkdfys)/time);
	    //��Ϣ��֧���ߵ�ѷֵ�ÿ����
	    double informatizationdfys=Double.parseDouble(formBean.getInformatizationdf())%time;
	    int informatizationdf = (int) ((Double.parseDouble(formBean.getInformatizationdf())-informatizationdfys)/time);	    
	    //�����ۺ��ߵ�ѣ��칫���ֵ�ÿ����
	    double administrativedfys=Double.parseDouble(formBean.getAdministrativedf())%time;
	    int administrativedf = (int) ((Double.parseDouble(formBean.getAdministrativedf())-administrativedfys)/time);
	    //�г�Ӫ���ߵ��(Ӫҵ)�ֵ�ÿ����
	    double marketdfys=Double.parseDouble(formBean.getMarketdf())%time;
	    int marketdf = (int) ((Double.parseDouble(formBean.getMarketdf())-marketdfys)/time);
	    //����Ͷ���ߵ�ѷֵ�ÿ����
	    double builddfys=Double.parseDouble(formBean.getBuilddf())%time;
	    int builddf = (int) ((Double.parseDouble(formBean.getBuilddf())-builddfys)/time); 
	    //�������ߵ�ѷֵ�ÿ����
	    double dddfys=Double.parseDouble(formBean.getDddf())%time;
	    int dddf = (int) ((Double.parseDouble(formBean.getDddf())-dddfys)/time); 
	   	 List nwdffentan = new ArrayList();
		 List imdffentan = new ArrayList();
		 List asdffentan = new ArrayList();
		 List mkdffentan = new ArrayList();
		 List bldffentan = new ArrayList();
		 List dddffentan=new ArrayList();
		List dflist = new ArrayList();
		 List year_month = new ArrayList();
		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			String yue = String.valueOf(starty);
	     	if(yue.length()==1)yue = "0"+yue;
	     	String year_month_tmp = startn+"-"+yue;
	     	starty ++;
	     	if(starty>12){
	     		starty = 1;
	     		startn ++;
	     	}
	     	year_month.add(year_month_tmp);
			if (i == time - 1){
				dflist.add(dfPermonth + dfyu);
				nwdffentan.add(networkdf+networkdfys);
				imdffentan.add(informatizationdf+informatizationdfys);
				asdffentan.add(administrativedf+administrativedfys);
				mkdffentan.add(marketdf+marketdfys);
				bldffentan.add(builddf+builddfys);
				dddffentan.add(dddf+dddfys);
			}else{
				dflist.add(dfPermonth);
				nwdffentan.add(networkdf);
				imdffentan.add(informatizationdf);
				asdffentan.add(administrativedf);
				mkdffentan.add(marketdf);
				bldffentan.add(builddf);
				dddffentan.add(dddf);
			}
		}
		
		
		
		// birthday = birthday.length()>0?birthday:null;
		String msg = "�����˻�ʧ�ܣ������Ի������Ա��ϵ��";
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
//		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		String s = s1.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		if("1".equals(formBean.getCountyauditstatus())){//���������ж�
			formBean.setCountyaudittime(s);
		}else{
			formBean.setCountyaudittime(null);
		}
		
		if("1".equals(formBean.getCityzrauditstatus())){//������
			formBean.setCityzraudittime(s);
		}else{
			formBean.setCityzraudittime(null);
		}
		if("1".equals(formBean.getCITYAUDIT())){//�м����
			formBean.setCityaudittime(s);
		}else{
			formBean.setCityaudittime(null);
		}
		
		
		for(int i=0;i<time;i++){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PREPAYMENT(ID,PREPAYMENT_AMMETERID,STATIONID,PJJE," +
					"DIANLIANG,STARTMONTH,ENDMONTH,ACCOUNTMONTH,NOTETIME,KPTIME," +
					"NOTETYPEID,NOTENO,FINANCEAUDIT,ENTRYPENSONNEL,ENTRYTIME," +
					"UUID,MONEY,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,YFFBZW,MEMO,"
							+ "AUTOAUDITSTATUS,"
							+ "AUTOAUDITDESCRIPTION,"
							+ "MANUALAUDITNAME,"
							+ "MANUALAUDITSTATUS,"
							+ "MANUALAUDITDATETIME,"
							+ "COUNTYAUDITSTATUS,"
							+ "CITYZRAUDITSTATUS,"
							+ "COUNTYAUDITNAME,"
							+ "COUNTYAUDITTIME,"
							+ "CITYZRAUDITNAME,"
							+ "CITYZRAUDITTIME,"
							+ "CSDB,"
							+ "DEDHDL," +"CITYAUDIT,"+"jszq,STARTDATE,ENDDATE,flag,countyflag,cityflag,danjia,EDHDL,QSDBDL,CITYAUDITTIME," +
									"DBYDL,BEILV,HBZQ,BZZ,SCB,ZLFH,JLFH,PROPERTYCODE,GDFSCODE,DFZFLXCODE,STATIONTYPECODE,HTBH "+
									" )");
			
			sql.append(" VALUES ('"+i+"','"+formBean.getPrepayment_ammeterid()+"','"+formBean.getStationid()+"','"+formBean.getPjje()+"','"+formBean.getDianliang()+ "',TO_DATE('"+ year_month.get(i) + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('"
					+ formBean.getAccountmonth() + "','yyyy-mm'),TO_DATE('" + formBean.getNotetime()+ "','yyyy-mm-dd')");
			sql.append(",TO_DATE('" + formBean.getKptime() + "','yyyy-mm-dd'),'"+ formBean.getNotetypeid() + "','"+ formBean.getNoteno() +"','1','"+formBean.getEntrypensonnel()+"',"+s+",'"+uuid+"','"+dflist.get(i)+"','"+nwdffentan.get(i)+"','"+asdffentan.get(i)+"','"+mkdffentan.get(i)+"','"+
					imdffentan.get(i)+"','"+bldffentan.get(i)+"','"+dddffentan.get(i)+"','"+bzw+"','"+formBean.getMemo() + "','"
			+ formBean.getAutoauditstatus()
			+ "','"
			+ formBean.getAutoauditdescripthion()
			+ "','"
			+ formBean.getManualauditname()
			+ "','"
			+ formBean.getManualauditstatus()
			+ "',"
			+ formBean.getManualauditdatetime()
			+ ",'"
			+ formBean.getCountyauditstatus()
			+ "','"
			+ formBean.getCityzrauditstatus()
			+ "','"
			+ formBean.getCountyauditname()
			+ "',"
			+ formBean.getCountyaudittime()
			+ ",'"
			+ formBean.getCityzrauditname()
			+ "',"
			+ formBean.getCityzraudittime()
			+ ",'"
			+ formBean.getCsdb()
			+ "','"
			+ formBean.getDedhdl()
			+ "','"
			+formBean.getCITYAUDIT()
			+ "','"
			+formBean.getJszq()
			+ "',TO_DATE('"+formBean.getStartdate()
			+ "','yyyy-mm-dd'),TO_DATE('"+formBean.getEnddate()
			+ "','yyyy-mm-dd'),'"+formBean.getFlag()
			+ "','"+formBean.getCountyflag()
			+ "','"+formBean.getCityflag()
			+ "','"+formBean.getDanjia()
			+ "','"+formBean.getEdhdl()
			+ "','"+formBean.getQsdbdl()
			+ "',"+formBean.getCityaudittime()
			+","+formBean.getDbydl()
			+","+formBean.getBeilv()
			+","+formBean.getHbzq()
			+","+formBean.getBzz()
			+","+formBean.getScb()
			+","+formBean.getZlfh()
			+","+formBean.getJlfh()
			+",'"+formBean.getPropertycode()
			+"','"+formBean.getGdfscode()
			+"','"+formBean.getDfzflxcode()
			+"','"+formBean.getStationtypecode()
			+"','"+formBean.getHtbianhao()
			+ "')");
			sqlBatch[i] = sql.toString();
			System.out.println("���뱣��:"+sql.toString());
		
		}
		DataBase db = new DataBase();
		try {
			
			db.updateBatch(sqlBatch);
			msg = "1";
			db.commit();
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
			msg="0";
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	/**
	 * ˽�з������������ΪSQL��WHERE������ �����ں�ͬ���Ĳ�ѯʹ��
	 * 
	 * @author rock
	 * @param bbscb
	 * @return
	 */
	private synchronized String getWhereString(
			BargainBillSelectConditionsBean bbscb) {
		StringBuffer whereStr = new StringBuffer();

		// �ж���
		if (!"".equals(bbscb.getShi()) && !"0".equals(bbscb.getShi())
				&& !(bbscb.getShi() == null)) {
			whereStr.append(" and zd.shi='");
			whereStr.append(bbscb.getShi());
			whereStr.append("' ");
		}
		// �ж���
		if (!"".equals(bbscb.getXian()) && !"0".equals(bbscb.getXian())
				&& !(bbscb.getXian() == null)) {
			whereStr.append(" and zd.xian='");
			whereStr.append(bbscb.getXian());
			whereStr.append("' ");

		}
		// �ж�С��
		if (!"".equals(bbscb.getXiaoqu()) && !"0".equals(bbscb.getXiaoqu())
				&& !(bbscb.getXiaoqu() == null)) {
			whereStr.append(" and zd.xiaoqu='");
			whereStr.append(bbscb.getXiaoqu());
			whereStr.append("' ");

		}
		// �ж�վ������
		if (!"".equals(bbscb.getZdmc()) && !"0".equals(bbscb.getZdmc())
				&& !(bbscb.getZdmc() == null)) {
			whereStr.append(" and zd.jzname like'%");
			whereStr.append(bbscb.getZdmc());
			whereStr.append("%' ");

		}
		// �ж���ʼʱ��
		if (!"".equals(bbscb.getQssj()) && !"0".equals(bbscb.getQssj())
				&& !(bbscb.getQssj() == null)) {
			whereStr.append(" and to_char(p.startmonth,'yyyy-mm')>='");
			whereStr.append(bbscb.getQssj());
			whereStr.append("' ");

		}
		// �жϽ���ʱ��
		if (!"".equals(bbscb.getJssj()) && !"0".equals(bbscb.getJssj())
				&& !(bbscb.getJssj() == null)) {
			whereStr.append(" and to_char(p.endmonth,'yyyy-mm')<='");
			whereStr.append(bbscb.getJssj());
			whereStr.append("' ");

		}
		// �жϱ����·�
		if (!"".equals(bbscb.getBzyf()) && !"0".equals(bbscb.getBzyf())
				&& !(bbscb.getBzyf() == null)) {
			whereStr.append(" and to_char(p.accountmonth,'yyyy-mm')='");
			whereStr.append(bbscb.getBzyf());
			whereStr.append("' ");

		}
		// �ж�վ������
		if (!"".equals(bbscb.getZdlx()) && !"0".equals(bbscb.getZdlx())
				&& !(bbscb.getZdlx() == null)) {
			whereStr.append(" and zd.STATIONTYPE='");
			whereStr.append(bbscb.getZdlx());
			whereStr.append("' ");

		}
		// �ж�վ������״̬
		if (!"".equals(bbscb.getQyzt()) && !"-1".equals(bbscb.getQyzt())
				&& !(bbscb.getQyzt() == null)) {
			whereStr.append(" and zd.QYZT='");
			whereStr.append(bbscb.getQyzt());
			whereStr.append("' ");

		}

		return whereStr.toString();
	}

	/**
	 * ���ڲ�ѯ����վ��
	 * 
	 * @author rock
	 * @param loginid
	 * @return
	 */
	private synchronized String getFuzeZdid(String loginid) {/* ��ѯ����վ�� */
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
//	public synchronized BargainBillCountBean getCount(String ammeterid) {
//		BargainBillCountBean bbc = null;
//		    String sql = "select zd.edhdl,zd.qsdbdl,zd.dianfei " +
//		    		"from dianbiao d,zhandian zd " +
//		    		" where d.zdid=zd.id and zd.qyzt='1' and d.dbid=? group by zd.edhdl,zd.qsdbdl,zd.dianfei";
//		    
//		    DataBase db = new DataBase();
//		    Connection conn = null;
//		    PreparedStatement ps = null;
//		    ResultSet rs = null;
//		    try {
//		    	System.out.println("�����б�Ԥ���ѹ����ͬ��--���Ԥ���Ѵ�������ȫʡ�����������ĵ�����Ϣ:");
//		      conn = db.getConnection();
//		      ps = conn.prepareStatement(sql);
//		      
//		      ps.setString(1, ammeterid);
//		      
//		      rs = ps.executeQuery();
//		      while (rs.next()) {
//		    	  String ed = rs.getString(1)!=null?rs.getString(1):"";
//		    	  String qs = rs.getString(2)!=null?rs.getString(2):"";
//		    	  String dianfei = rs.getString(3)!=null?rs.getString(3):"0";
//		    	  
//		    	   bbc = new BargainBillCountBean(qs,ed,dianfei);
//		    	   break;
//		      }
//		      
//		    }
//		    catch (Exception de) {
//		      de.printStackTrace();
//		    }
//		    finally {
//		    	db.free(rs, ps, conn);
//		    }
//		    return bbc;
//		  }
}
