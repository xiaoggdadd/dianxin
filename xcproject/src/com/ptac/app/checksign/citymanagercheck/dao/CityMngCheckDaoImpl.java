package com.ptac.app.checksign.citymanagercheck.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author lijing
 * @see �м��������
 */
public class CityMngCheckDaoImpl implements CityMngCheckDao {

	/**
	 * @author lijing
	 * @see �м�������˲�ѯ��Ϣ
	 * @param whereStr
	 *            (String) ����������
	 * @param loginId
	 *            (String) ��ȡ��ǰ��¼�˵�ID
	 * @return list ��ѯ�����Ľ����list
	 * update zhouxue 2014-05-16 ��ѯsql����Ĭ�Ϲ������� վ������⣬�ǲɼ���վ���˹����ͨ��
	 * update zhouxue 2014-06-10  ��ѯ��ѵ�sql���ȥ��Ĭ�Ϲ�������  �����֧������ΪԤ֧���½�
	 */
	public List<CityMngCheckBean> queryCityMngCheck(String whereStr,
			String loginId,String lrbz1,String lrbz2) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CityMngCheckBean> list = new ArrayList<CityMngCheckBean>();

		StringBuffer sql = new StringBuffer();// �м���˲�ѯ ���½ᣬԤ����2��
		StringBuffer sql1 = new StringBuffer();// �м���˲�ѯ����ͬ���忨��Ԥ����1��
		sql
				.append("SELECT Z.ID,Z.JZNAME,to_char(E.ACCOUNTMONTH,'yyyy-mm'),A.BLHDL,E.ACTUALPAY,E.UNITPRICE,E.QSDBDL,E.EDHDL,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "RTNAME(Z.PROPERTY),D.DBID,D.DBNAME,A.LASTDEGREE,A.THISDEGREE,to_char(A.LASTDATETIME,'yyyy-mm-dd'),to_char(A.THISDATETIME,'yyyy-mm-dd'),"
						+ "A.FLOATDEGREE,A.MEMO,E.FLOATPAY,E.MEMO,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "E.ELECTRICFEE_ID,E.DFUUID,E.CITYZRAUDITSTATUS,RTNAME(D.DFZFLX),A.DEDHDL,A.CSDB,'1' AS DFBZW,"
						+ " rndiqu(z.xian),rtname(z.gdfs),E.JSZQ,E.MANUALAUDITSTATUS,E.CITYAUDIT,D.BEILV,RTNAME(E.NOTETYPEID),A.ACTUALDEGREE,E.AUTOAUDITSTATUS,E.MANPASSMEMO,"
						+ "A.LASTELECFEES,A.LASTELECDEGREE,A.LASTUNITPRICE,E.CHANGEVALUE,E.ACTUALLINELOSSVALUE,A.BLHDL/E.JSZQ BZRJ"
						+ ",A.LASTFLOATDEGREE,A.LASTACTUALDEGREE"
						+ " FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E"
						+ " WHERE Z.ID=D.ZDID AND D.DBID=A.AMMETERID_FK AND A.AMMETERDEGREEID=E.AMMETERDEGREEID_FK "
						+ " AND (E.MANUALAUDITSTATUS = '-1' OR E.MANUALAUDITSTATUS = '1') "
						+ " AND E.CITYAUDIT = '1' "
						+ " AND E.LIUCHENGHAO IS NULL "
						+ " AND A.CITYFLAG = '0' AND Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' "
						+ whereStr+lrbz1
						+ " AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		System.out.println("�м�������˲�ѯ�½�,Ԥ����2sql:"+sql.toString());
		sql1
				.append("SELECT Z.ID,Z.JZNAME,to_char(E.ACCOUNTMONTH,'yyyy-mm'),E.DIANLIANG,E.MONEY,E.DANJIA,E.QSDBDL,E.EDHDL,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "RTNAME(Z.PROPERTY),D.DBID,D.DBNAME,E.CITYZRAUDITSTATUS,RTNAME(D.DFZFLX),E.ID,E.UUID,E.DEDHDL,E.CSDB,'2' as dfbzw,"
						+ " rndiqu(z.xian),rtname(z.gdfs),E.JSZQ,E.MANUALAUDITSTATUS,E.CITYAUDIT,D.BEILV,RTNAME(E.NOTETYPEID),to_char(E.STARTDATE,'yyyy-mm-dd'),"
						+ " to_char(E.ENDDATE,'yyyy-mm-dd'),E.AUTOAUDITSTATUS,E.MANPASSMEMO,E.LASTYUE,E.DIANLIANG/E.JSZQ BZRJ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,yufufeiview E"
						+ " WHERE Z.ID = D.ZDID AND E.PREPAYMENT_AMMETERID = D.DBID"
						+ " AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx03' OR D.DFZFLX='dfzflx04')"
						+ " AND (E.FINANCEAUDIT = '-1' OR E.FINANCEAUDIT = '1') "
						+ " AND E.CITYAUDIT = '1' "
						+ " AND E.LIUCHENGHAO IS NULL "
						+ " AND E.CITYFLAG = '0' AND Z.XUNI='0' AND Z.CAIJI='0' AND Z.SHSIGN='1' "
						+ whereStr+lrbz2
						+ " AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		System.out.println("�м�������˲�ѯ��ͬ���忨��Ԥ����1sql:"+sql1.toString());
	
		DataBase db = new DataBase();
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps1 = conn.prepareStatement(sql1.toString());
			rs = ps.executeQuery();
			rs1 = ps1.executeQuery();

			while (rs.next()) {
				CityMngCheckBean bean = new CityMngCheckBean();
				String zdid = rs.getString(1) != null ? rs.getString(1) : "";// վ��ID
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";// վ������
				String accountmonth = rs.getString(3) != null ? rs.getString(3)
						: "";// �����·�
				String blhdl = rs.getString(4) != null ? rs.getString(4) : "";// ʵ���õ���double
				String actualpay = rs.getString(5) != null ? rs.getString(5)
						: "";// ʵ�ʵ��
				String unitprice = rs.getString(6) != null ? rs.getString(6)
						: "0.0000";// ����
				String qsdbdl = rs.getString(7) != null ? rs.getString(7) : "";// ʡ����
				String edhdl = rs.getString(8) != null ? rs.getString(8) : "";// �ж���
				String stationtype = rs.getString(9) != null ? rs.getString(9)
						: "";// վ������
				String property = rs.getString(10) != null ? rs.getString(10)
						: "";// վ������
				String dbid = rs.getString(11) != null ? rs.getString(11) : "";// ���id
				String dbname = rs.getString(12) != null ? rs.getString(12)
						: "";// �������
				String lastdegree = rs.getString(13) != null ? rs.getString(13)
						: "";// ��ʼ�����
				String thisdegree = rs.getString(14) != null ? rs.getString(14)
						: "";// ���������
				String lastdatetime = rs.getString(15) != null ? rs
						.getString(15) : "";// �ϴγ���ʱ��
				String thisdatetime = rs.getString(16) != null ? rs
						.getString(16) : "";// ���γ���ʱ��
				String floatdegree = rs.getString(17) != null ? rs
						.getString(17) : "";// ��������
				String memo = rs.getString(18) != null ? rs.getString(18) : "";// ����������ע
				String floatpay = rs.getString(19) != null ? rs.getString(19)
						: "";// ��ѵ���
				String memo1 = rs.getString(20) != null ? rs.getString(20) : "";// ��ѵ�����ע
				String szdq = rs.getString(21) != null ? rs.getString(21) : "";// ���ڵ���
				String electricfee_id = rs.getString(22) != null ? rs
						.getString(22) : "";// ���id
				String uuid = rs.getString(23) != null ? rs.getString(23) : "";// uuid
				String cityzrauditstatus = rs.getString(24) != null ? rs
						.getString(24) : "";// �м��������״̬
				String dfzflx = rs.getString(25) != null ? rs.getString(25)
						: "";// ���֧������
				String dedhdl = rs.getString(26) != null ? rs.getString(26)
						: "";// ���ж���
				String csdb = rs.getString(27) != null ? rs.getString(27) : "";// ��ʡ����
				String dfbzw = rs.getString(28) != null ? rs.getString(28) : "";// ��ѱ�־λ

				String quxian = rs.getString(29) != null ? rs.getString(29)
						: "";// ����
				String gdfs = rs.getString(30) != null ? rs.getString(30) : "";// ���緽ʽ
				String jszq = rs.getString(31) != null ? rs.getString(31) : "";// ��������
				String rgshzt = rs.getString(32) != null ? rs.getString(32)
						: "";// �˹����״̬
				String sjshzt = rs.getString(33) != null ? rs.getString(33)
						: "";// �м����״̬
				String beilv = rs.getString(34) != null ? rs.getString(34) : "1";// ����
				String pjlx = rs.getString(35) != null ? rs.getString(35) : "";// Ʊ������
				String actualdegree = rs.getString(36) != null ? rs.getString(36) : "";// ����ʱ��
				String autoauditstatus = rs.getString(37) != null ? rs.getString(37) : "";//�Զ����״̬(0δͨ��  1ͨ����
				String manpassmemo = rs.getString(38) != null ? rs.getString(38) : "";//�˹����ͨ��ԭ��
				
				bean.setLastelecfees(rs.getString(39) != null ? rs.getString(39) : "");//�ϴε��
				bean.setLastelecdegree(rs.getString(40) != null ? rs.getString(40) : "");//�ϴε���
				bean.setLastunitprice(rs.getString(41) != null ? rs.getString(41) : "");//�ϴε���
				
				
				String dbydl = "";// ����õ���
				String csdbdfe = "";// ��ʡ���Ѷ�
				String csdbdfbz = "";// ��ʡ���ѱ�ֵ

				/* ���ֶε�һЩ���� */
				double zq2 = 0;// ����
				try {
					zq2 = Double.parseDouble(jszq);
				} catch (Exception e) {

				}

				// �������õ���
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree));
				Double dbdl = 0.0;
				try {

					dbdl = actdegree * Double.parseDouble(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				} catch (Exception e) {

				}
				dbydl = dbdl.toString();// ����õ���

				if(!"".equals(qsdbdl) && !"".equals(zq2)){
				
					/* ���㳬ʡ�����Ѷ� */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(qsdbdl)
										* Double.parseDouble(unitprice) * zq2);
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
					}
					csdbdfe = sdbdf.toString();// ��ʡ�����Ѷ�
	
					/* ���㳬ʡ�����ѱ�ֵ */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl)
										* Double.parseDouble(unitprice) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
						csdbbz = 0.0;
					}
					csdbdfbz = csdbbz.toString();// ��ʡ�����ѱ�ֵ
				}
				//��������*���ʡ����ڵ���õ��������ڵ�������*������
				String bv = "";
				if(Format.str_d(beilv)==0){//����Ϊ�� �� Ϊ 0
					bv="1";
				}else{
					bv = beilv;
				}
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0"; //���ڵ���õ���
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0"; //���ڵ�������
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//��������*����
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//���ڵ�������*������
				bean.setLastfloatdegreeandbl(lastfloatdegreeandbl);//���ڵ�������*����
				bean.setLastactualdegree(lastactualdegree);//���ڵ���õ���
				bean.setFloatdegreeandbl(floatdegreeandbl);//��������*����
				
				bean.setZdid(zdid);
				bean.setZdname(zdname);
				bean.setAccountmonth(accountmonth);
				bean.setBlhdl(Format.str2(blhdl));
				bean.setActualpay(Format.str2(actualpay));
				bean.setUnitprice(unitprice);
				bean.setCsbl(Format.str2(csdb));
				bean.setCsbbl(Format.str2(dedhdl));
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(edhdl);
				bean.setStationtype(stationtype);
				bean.setProperty(property);
				bean.setDbid(dbid);
				bean.setDbname(dbname);
				bean.setLastdegree(lastdegree);
				bean.setThisdegree(thisdegree);
				bean.setLastdatetime(lastdatetime);
				bean.setThisdatetime(thisdatetime);
				bean.setFloatdegree(floatdegree);
				bean.setMemo(memo);
				bean.setFloatpay(floatpay);
				bean.setMemo1(memo1);
				bean.setSzdq(szdq);
				bean.setElectricfeeid(electricfee_id);
				bean.setUuid(uuid);
				bean.setCityzrauditstatus(cityzrauditstatus);
				bean.setDfzflx(dfzflx);
				bean.setDfbzw(dfbzw);

				/* �������ֶ� */

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjlx(pjlx);
				bean.setDbydl(dbydl);
				bean.setCsdbdfe(csdbdfe);
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLrzt("��ѵ�¼��");
				
				if("0".equals(autoauditstatus)){
					bean.setManpassmemo(manpassmemo);
				}else{
					bean.setManpassmemo("");
				}

				if("0".equals(cityzrauditstatus)){
					bean.setGdtx("�����");
				}else if("1".equals(cityzrauditstatus)){
					if("-1".equals(rgshzt)){
						bean.setGdtx("�ϼ��˵�");
					}else if("2".equals(rgshzt) || "0".equals(rgshzt) || "1".equals(rgshzt) || "-2".equals(rgshzt)){
						bean.setGdtx("");
					}
				}
				
				bean.setLastyue("");
				/*String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +bean.getZdid()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("�˹���˵�ѹ����վ�������ѯ"+sqla.toString());
				ResultSet rsa = staa.executeQuery(sqla);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//������վ�����
				}
				dba.free(rsa, staa, conna);*/
				bean.setGlbrjl(Format.str2(""));
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //����ֵ
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //ʵ������ֵ
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //�����վ�����
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//�߱������
				bean.setLineandchangeandbl(changeandlineandbl);//�߱������
				bean.setBzrj(Format.str2(bzrj));//�����վ�����
				
				list.add(bean);
			}

			while (rs1.next()) {

				String zdid1 = rs1.getString(1) != null ? rs1.getString(1) : "";// վ��ID
				String zdname1 = rs1.getString(2) != null ? rs1.getString(2)
						: "";// վ������
				String accountmonth1 = rs1.getString(3) != null ? rs1
						.getString(3) : "";// �����·�
				String blhdl1 = rs1.getString(4) != null ? rs1.getString(4)
						: "";// ʵ���õ���double
				String actualpay1 = rs1.getString(5) != null ? rs1.getString(5)
						: "";// ʵ�ʵ��
				String unitprice1 = rs1.getString(6) != null ? rs1.getString(6)
						: "0.0000";// ����
				String qsdbdl1 = rs1.getString(7) != null ? rs1.getString(7)
						: "";// ʡ����
				String edhdl1 = rs1.getString(8) != null ? rs1.getString(8)
						: "";// �ж���
				String stationtype1 = rs1.getString(9) != null ? rs1
						.getString(9) : "";// վ������
				String property1 = rs1.getString(10) != null ? rs1
						.getString(10) : "";// վ������
				String dbid1 = rs1.getString(11) != null ? rs1.getString(11)
						: "";// ���id
				String dbname1 = rs1.getString(12) != null ? rs1.getString(12)
						: "";// �������
				String cityzrauditstatus1 = rs1.getString(13) != null ? rs1
						.getString(13) : "";// �м��������״̬
				String dfzflx1 = rs1.getString(14) != null ? rs1.getString(14)
						: "";// ���֧������
				String id1 = rs1.getString(15) != null ? rs1.getString(15) : "";
				String uuid1 = rs1.getString(16) != null ? rs1.getString(16)
						: "";
				String dedhdl1 = rs1.getString(17) != null ? rs1.getString(17)
						: "";// ���ж���
				String csdb1 = rs1.getString(18) != null ? rs1.getString(18)
						: "";// ��ʡ����
				String dfbzw1 = rs1.getString(19) != null ? rs1.getString(19)
						: "";// ��ѱ�־λ

				String quxian = rs1.getString(20) != null ? rs1.getString(20)
						: "";// ����
				String gdfs = rs1.getString(21) != null ? rs1.getString(21) : "";// ���緽ʽ
				String jszq = rs1.getString(22) != null ? rs1.getString(22) : "";// ��������
				String rgshzt = rs1.getString(23) != null ? rs1.getString(23)
						: "";// �˹����״̬
				String sjshzt = rs1.getString(24) != null ? rs1.getString(24)
						: "";// �м����״̬
				String beilv = rs1.getString(25) != null ? rs1.getString(25) : "";// ����
				String pjlx = rs1.getString(26) != null ? rs1.getString(26) : "";// Ʊ������
				String startTime = rs1.getString(27) != null ? rs1.getString(27) : "";// ��ʼʱ��
				String endTime = rs1.getString(28) != null ? rs1.getString(28) : "";// ����ʱ��
				
				String autoauditstatus1 = rs1.getString(29) != null ? rs1.getString(29) : "";//�Զ����״̬(0δͨ��  1ͨ����
				String manpassmemo1 = rs1.getString(30) != null ? rs1.getString(30) : "";//�˹����ͨ��ԭ��
				
				String lastyue = rs1.getString(31) != null ? rs1.getString(31) : "";//�������

				String dbydl = "";// ����õ���
				String csdbdfe = "";// ��ʡ���Ѷ�
				String csdbdfbz = "";// ��ʡ���ѱ�ֵ

				/* ���ֶε�һЩ���� */
				double zq2 = 0;// ����
				try {
					zq2 = Double.parseDouble(jszq);
				} catch (Exception e) {

				}

				// �������õ���
				Double dbdl = 0.0;
				try {

					dbdl = Format.str_d(blhdl1) * Double.parseDouble(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				} catch (Exception e) {

				}
				dbydl = dbdl.toString();// ����õ���
				
				if(!"".equals(qsdbdl1) && !"".equals(zq2)){
	
					/* ���㳬ʡ�����Ѷ� */
					Double sdbdf = 0.0;
					
					try {
						sdbdf = Double.parseDouble(actualpay1)
								- (Double.parseDouble(qsdbdl1)
										* Double.parseDouble(unitprice1) * zq2);
						
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					csdbdfe = sdbdf.toString();// ��ʡ�����Ѷ�
					
	
					/* ���㳬ʡ�����ѱ�ֵ */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl1)
										* Double.parseDouble(unitprice1) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
	
						csdbbz = 0.0;
					}
					csdbdfbz = csdbbz.toString();// ��ʡ�����ѱ�ֵ
				}
				
				CityMngCheckBean bean = new CityMngCheckBean();
				bean.setZdid(zdid1);
				bean.setZdname(zdname1);
				bean.setAccountmonth(accountmonth1);
				bean.setBlhdl(Format.str2(blhdl1));
				bean.setActualpay(Format.str2(actualpay1));
				bean.setUnitprice(unitprice1);
				bean.setQsdbdl(qsdbdl1);
				bean.setEdhdl(edhdl1);
				bean.setStationtype(stationtype1);
				bean.setProperty(property1);
				bean.setDbid(dbid1);
				bean.setDbname(dbname1);
				bean.setCityzrauditstatus(cityzrauditstatus1);
				bean.setDfzflx(dfzflx1);
				bean.setElectricfeeid(id1);
				bean.setUuid(uuid1);
				bean.setCsbbl(Format.str2(dedhdl1));
				bean.setCsbl(Format.str2(csdb1));
				bean.setDfbzw(dfbzw1);
				
				bean.setFloatdegree("");
				bean.setMemo("");
				bean.setFloatpay("");
				bean.setMemo1("");

				/* �������ֶ� */

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjlx(pjlx);
				bean.setDbydl(dbydl);
				bean.setCsdbdfe(csdbdfe);
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLastdatetime(startTime);
				bean.setThisdatetime(endTime);
				bean.setLrzt("Ԥ����¼��");
				if("0".equals(autoauditstatus1)){
					bean.setManpassmemo(manpassmemo1);
				}else{
					bean.setManpassmemo("");
				}
				
				bean.setLastelecfees("");//�ϴε��
				bean.setLastelecdegree("");//�ϴε���
				bean.setLastunitprice("");//�ϴε���

				if("0".equals(cityzrauditstatus1)){
					bean.setGdtx("�����");
				}else if("1".equals(cityzrauditstatus1)){
					if("-1".equals(rgshzt)){
						bean.setGdtx("�ϼ��˵�");
					}else if("2".equals(rgshzt) || "0".equals(rgshzt) || "1".equals(rgshzt) || "-2".equals(rgshzt)){
						bean.setGdtx("");
					}
				}
				
				bean.setLastyue(lastyue);
				
				bean.setLastfloatdegreeandbl("");//���ڵ�������*����
				bean.setLastactualdegree("");//���ڵ���õ���
				bean.setFloatdegreeandbl("");//��������*����
				bean.setLineandchangeandbl("");//�߱������
				String bzrj = rs1.getString("BZRJ")!=null?rs1.getString("BZRJ"):"0"; //�����վ�����
				bean.setBzrj(Format.str2(bzrj));//�����վ�����
				bean.setGlbrjl("");
				list.add(bean);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, ps, null);
			db.free(rs1, ps1, conn);
		}

		return list;
	}

	/**
	 * @author lijing
	 * @see �м����������ϸ��Ϣ
	 * @param dbid
	 *            (String) ��ȡ��ǰ�ĵ��ID
	 * @param dfzflx
	 *            (String) ���ݵ��֧�������ж����½�,Ԥ����2���Ǻ�ͬ,�忨,Ԥ����1
	 * @param dfbzw
	 *            (String) ���ݵ�ѱ�־λ�ж����½�,Ԥ����2���Ǻ�ͬ,�忨,Ԥ����1
	 * @param accountmonth
	 *            ��String�� �������������·�С�ڵ������ڵı����·�
	 * @return list ��ϸ��Ϣlist
	 */
	public List<CityMngCheckBean> getCityMngCheckInfo(String dbid,
			String dfzflx, String dfbzw, String accountmonth) {

		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CityMngCheckBean> list = new ArrayList<CityMngCheckBean>();

		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		sql
				.append("SELECT Z.ID,Z.JZNAME,D.DBNAME,E.ACCOUNTMONTH,A.BLHDL,E.ACTUALPAY,"
						+ "A.LASTDEGREE,A.THISDEGREE,A.LASTDATETIME,A.THISDATETIME,"
						+ "A.FLOATDEGREE,A.MEMO,E.FLOATPAY,E.MEMO,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E"
						+ " WHERE Z.ID=D.ZDID AND D.DBID=A.AMMETERID_FK AND A.AMMETERDEGREEID=E.AMMETERDEGREEID_FK "
						+ " AND D.DBID = '"
						+ dbid
						+ "' AND E.ACCOUNTMONTH<='"
						+ accountmonth + "'order by ACCOUNTMONTH DESC");

		sql1
				.append("SELECT Z.ID,Z.JZNAME,D.DBNAME,E.ACCOUNTMONTH,E.DIANLIANG,E.MONEY,"
						+ "E.STARTDEGREE,E.STOPDEGREE,E.STARTDATE,E.ENDDATE,"
						+ "(CASE WHEN Z.XIAN IS NOT NULL THEN RNDIQU(Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN RNDIQU(Z.XIAOQU) ELSE '' END) AS SZDQ"
						+ " FROM ZHANDIAN Z,DIANBIAO D,yufufeiview E"
						+ " WHERE Z.ID = D.ZDID AND E.PREPAYMENT_AMMETERID = D.DBID AND Z.QYZT = '1' "
						+ " AND D.DBID = '"
						+ dbid
						+ "' AND E.ACCOUNTMONTH<='"
						+ accountmonth + "'order by ACCOUNTMONTH DESC");

		DataBase db = new DataBase();
		if (("�½�".equals(dfzflx) || "Ԥ֧".equals(dfzflx)) && dfbzw.equals("1")) {
			System.out.println("�м����������ϸ��ѯ�½�,Ԥ����2:");
			try {
				db.connectDb();
				conn = db.getConnection();
				ps = conn.prepareStatement(sql.toString());
				rs = ps.executeQuery();

				while (rs.next()) {

					String zdid = rs.getString(1) != null ? rs.getString(1)
							: "";// վ��ID
					String zdname = rs.getString(2) != null ? rs.getString(2)
							: "";// վ������
					String dbname = rs.getString(3) != null ? rs.getString(3)
							: "";// �������
					String accountmonth1 = rs.getString(4) != null ? rs
							.getString(4) : "";// �����·�
					String blhdl = rs.getString(5) != null ? rs.getString(5)
							: "";// ʵ���õ���double
					String actualpay = rs.getString(6) != null ? rs
							.getString(6) : "";// ʵ�ʵ��
					String lastdegree = rs.getString(7) != null ? rs
							.getString(7) : "";// ��ʼ�����
					String thisdegree = rs.getString(8) != null ? rs
							.getString(8) : "";// ���������
					String lastdatetime = rs.getString(9) != null ? rs
							.getString(9) : "";// �ϴγ���ʱ��
					String thisdatetime = rs.getString(10) != null ? rs
							.getString(10) : "";// ���γ���ʱ��
					String floatdegree = rs.getString(11) != null ? rs
							.getString(11) : "";// ��������
					String memo = rs.getString(12) != null ? rs.getString(12)
							: "";// ����������ע
					String floatpay = rs.getString(13) != null ? rs
							.getString(13) : "";// ��ѵ���
					String memo1 = rs.getString(14) != null ? rs.getString(14)
							: "";// ��ѵ�����ע
					String szdq = rs.getString(15) != null ? rs.getString(15)
							: "";// ���ڵ���

					CityMngCheckBean bean = new CityMngCheckBean();
					bean.setZdid(zdid);
					bean.setZdname(zdname);
					bean.setDbname(dbname);
					bean.setAccountmonth(accountmonth1);
					bean.setBlhdl(blhdl);
					bean.setActualpay(Format.str2(actualpay));
					bean.setLastdegree(lastdegree);
					bean.setThisdegree(thisdegree);
					bean.setLastdatetime(lastdatetime);
					bean.setThisdatetime(thisdatetime);
					bean.setFloatdegree(floatdegree);
					bean.setMemo(memo);
					bean.setFloatpay(floatpay);
					bean.setMemo1(memo1);
					bean.setSzdq(szdq);

					list.add(bean);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs, ps, conn);
			}
		}

		if (("��ͬ".equals(dfzflx) || "�忨".equals(dfzflx) || "Ԥ֧".equals(dfzflx))
				&& dfbzw.equals("2")) {
			System.out.println("�м����������ϸ��ѯ��ͬ,�忨,Ԥ����1:");
			try {
				db.connectDb();
				conn = db.getConnection();
				ps1 = conn.prepareStatement(sql1.toString());
				rs1 = ps1.executeQuery();

				while (rs.next()) {

					CityMngCheckBean bean = new CityMngCheckBean();
					bean.setZdid(rs1.getString(1));
					bean.setZdname(rs1.getString(2));
					bean.setDbname(rs1.getString(3));
					bean.setAccountmonth(rs1.getString(4));
					bean.setBlhdl(rs1.getString(5));
					bean.setActualpay(Format.str2(rs1.getString(6)));
					bean.setLastdegree(rs1.getString(7));
					bean.setThisdegree(rs1.getString(8));
					bean.setLastdatetime(rs1.getString(9));
					bean.setThisdatetime(rs1.getString(10));
					bean.setFloatdegree("");
					bean.setMemo("");
					bean.setFloatpay("");
					bean.setMemo1("");
					bean.setSzdq(rs1.getString(15));

					list.add(bean);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs1, ps1, conn);
			}
		}

		return list;
	}

	/**
	 * @author lijing
	 * @param personnal
	 *            (String) �����
	 * @param cityzrauditstatus
	 *            (String) ��˱�־
	 * @param chooseIdStr1
	 *            (String) ���uuid
	 * @param chooseIdStr2
	 *            ��String�� Ԥ����uuid
	 * @param bz
	 *            (String) msg��־
	 * @see �м�������
	 * @param accountId
	 *            (String) �˺�id
	 * @return String �����Ϣmsg
	 */
	public String CheckCityFees(String personnal, String cityzrauditstatus,
			String chooseIdStr1, String chooseIdStr2, String bz) {
		String msg = "�м�������˵����Ϣʧ�ܣ�";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// ϵͳʱ��
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		StringBuffer sql1 = new StringBuffer();// �½ᣬԤ����2
		StringBuffer sql2 = new StringBuffer();// ��ͬ�忨Ԥ����1
		try {
			conn = db.getConnection();

			sql1.append(" UPDATE ELECTRICFEES SET CITYZRAUDITSTATUS='"
					+ cityzrauditstatus + "'," + "CITYZRAUDITTIME=" + time
					+ "," + "CITYZRAUDITNAME='" + personnal + "'"
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYZRAUDITSTATUS='"
					+ cityzrauditstatus + "'," + "CITYZRAUDITTIME=" + time
					+ "," + "CITYZRAUDITNAME='" + personnal + "'"
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {
				ps1 = conn.prepareStatement(sql1.toString());
				System.out.println("�м�������˵�ѵ�:"+sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) {
				ps2 = conn.prepareStatement(sql2.toString());
				System.out.println("�м��������Ԥ����:"+sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "�м����������Ϣͨ����";
			} else if (bz.equals("0")) {
				msg = "�м����������Ϣȡ��ͨ����";
			} else {
				msg = "�м����������Ϣ��ͨ����";
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}

}
