package com.ptac.app.checksign.cityelectricfeecheck.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author WangYiXiao 2014-1-23
 * @see �м�������ʵ���� �м������ˣ���ѯ����������ѵ�����ͣ����
 */
public class EnhanceCityFeeCheckDaoImpl implements EnhanceCityFeeCheckDao {

	/**
	 * @param whereStr
	 *            (String) ��������
	 * @param loginId
	 *            (String) Ȩ��
	 * @see �м������˲�ѯ������
	 * @author WangYiXiao 2014-1-24
	 * @update WangYiXiao 2014-4-8  �м���˲�ѯsql�����̺�Ϊ��Ĭ������ȥ��
	 * @update zhouxue 2014-05-16 ��ѯsql��Ĭ������ վ������⣬�ǲɼ���վ�����ͨ��
	 * @update WangYiXiao 2014-8-11ȥ��tbtzsq=1��sql2����
	 */
	public List<CityElectricFeeCheck> queryCityFeeCheck(String whereStr,
			String loginId,String lrbz1,String lrbz2) {
		System.out.println("ElectricFeesBean��˵���б�����:");
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection conn = null;
		DataBase db = new DataBase();
		StringBuffer sql1 = new StringBuffer();// �м���˲�ѯ1 sql���½ᣬԤ����2��
		StringBuffer sql2 = new StringBuffer();// �м���˲�ѯ2����ͬ���忨��Ԥ����1��
		List<CityElectricFeeCheck> list = new ArrayList<CityElectricFeeCheck>();
		sql1
				.append("SELECT ZD.ZDCODE, ZD.JZNAME,"
						+ "D.DBID,RTNAME(ZD.PROPERTY),RTNAME(D.DFZFLX),"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
						+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "RTNAME(EF.NOTETYPEID),(SELECT IS2.NAME FROM INDEXS IS2 WHERE D.LINELOSSTYPE=IS2.CODE AND IS2.TYPE='xslx') AS LINELOSSTYPE,D.LINELOSSVALUE,D.BEILV,"
						+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,EF.EDHDL,AM.lastdatetime,AM.thisdatetime,EF.UNITPRICE,'1' AS DFBZW,AM.BLHDL,AM.DEDHDL, ZD.ZLFH,ZD.JLFH,"
						+ " EF.JSZQ,EF.QSDBDL,EF.PJJE,ZD.ID, "
						+ " D.DBNAME,rtname(ZD.GDFS),to_char(AM.LASTDATETIME,'yyyy-mm-dd'),to_char(AM.THISDATETIME,'yyyy-mm-dd'),AM.FLOATDEGREE,AM.MEMO,EF.UNITPRICE,EF.FLOATPAY,EF.MEMO,rndiqu(zd.xian),AM.ACTUALDEGREE,AM.CSDB,EF.MANPASSMEMO,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),AM.TBTZSQ, AM.AMMETERDEGREEID,"
						+ "AM.THISDEGREE,AM.LASTDEGREE,AM.LASTELECFEES,AM.LASTELECDEGREE,AM.LASTUNITPRICE,AM.LASTFLOATDEGREE,AM.LASTACTUALDEGREE,EF.CHANGEVALUE,EF.ACTUALLINELOSSVALUE,AM.BLHDL/EF.JSZQ BZRJ"
						+ " FROM CITYCHECK_DDF EF,ZHANDIAN ZD,DIANBIAO D,CITYCHECK_DDV AM  WHERE  D.DBID=AM.AMMETERID_FK AND ZD.ID=D.ZDID " +
						  "AND AM.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
						+ " AND (EF.MANUALAUDITSTATUS = '-1' OR EF.MANUALAUDITSTATUS = '1')"
						+ "  AND EF.COUNTYAUDITSTATUS='1' "
						+ whereStr+lrbz1
						+ " AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		sql2
				.append("SELECT ZD.ZDCODE, ZD.JZNAME,"
						+ "D.DBID,RTNAME(ZD.PROPERTY),RTNAME(D.DFZFLX),"
						+ "(SELECT NAME  FROM INDEXS WHERE CODE = ZD.STATIONTYPE  AND TYPE = 'stationtype') AS STATIONTYPE,"
						+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) "
						+ "ELSE  ''  END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"
						+ "RTNAME(EF.NOTETYPEID),(SELECT IS2.NAME FROM INDEXS IS2 WHERE D.LINELOSSTYPE=IS2.CODE AND IS2.TYPE='xslx') AS LINELOSSTYPE,"
						+ "D.LINELOSSVALUE,D.BEILV, EF.UUID, EF.MONEY, EF.CITYAUDIT,EF.ID,EF.EDHDL,'2' as dfbzw , zd.zlfh,zd.jlfh,"
						+ " EF.JSZQ,EF.QSDBDL,EF.PJJE,ZD.ID, "
						+ " D.DBNAME,rtname(ZD.GDFS),to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),EF.DANJIA,EF.MANUALAUDITSTATUS,rndiqu(zd.xian), "
						+ " EF.DEDHDL,EF.DIANLIANG,EF.AUTOAUDITSTATUS,EF.CSDB,EF.MANPASSMEMO,to_char(EF.ACCOUNTMONTH,'yyyy-mm'), EF.AMMETERDEGREEID_FK,EF.FINANCEAUDIT,EF.LASTYUE,EF.DIANLIANG/EF.JSZQ BZRJ"
						+ " FROM CITYCHECK_YFF EF, ZHANDIAN ZD, DIANBIAO D WHERE ZD.ID = D.ZDID AND EF.PREPAYMENT_AMMETERID=D.DBID " +
						  " AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1'"
						+ " AND (EF.MANUALAUDITSTATUS = '-1' OR EF.MANUALAUDITSTATUS = '1') "
						+ whereStr+lrbz2
						+ " AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx03' OR D.DFZFLX='dfzflx04') AND EF.COUNTYAUDITSTATUS='1'"
						+ " AND EF.CITYAUDIT <= '1' AND EF.FINANCEAUDIT <= '1'  AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						+ loginId + "')))");
		try {
			conn = db.getConnection();// ��ȡ����
			ps1 = conn.prepareStatement(sql1.toString());// Ԥ����
			ps2 = conn.prepareStatement(sql2.toString());// Ԥ����
			System.out.println("�м���˲�ѯ1"+sql1.toString());
			rs1 = ps1.executeQuery();// ִ��sql1
			System.out.println("�м���˲�ѯ2"+sql2.toString());
			rs2 = ps2.executeQuery();// ִ��sql2
			// while�ֶ�(�½ᣬԤ����2)
			String zdid = null;
			String zdcode = null;
			String jzname = null;
			String dbid = null;
			String property = null;
			String dfzflx = null;
			String stationtype = null;
			String szdq = null;
			String notetypeid = null;
			String linelosstype = null;
			String linelossvalue = null;
			String beilv = null;
			String electricfee_id = null;
			String dfuuid = null;
			String actualpay = null;
			String autoauditstatus = null;
			String manualauditstatus = null;
			String cityaudit = null;
			String edhdl = null;
			String lastdatetime = null;//û����
			String thisdatetime = null;//û����
			String unitprice = null;//û����
			String dfbzw = null;
			String blhdl = null;
			String dedhdl = null;
			String zlfh = null;
			String jlfh = null;
			String jszq = null;
			String qsdbdl = null;
			String pjje = null;
			
			String dbname = "";// �������
			String gdfs = "";// ���緽ʽ
			String dbydl = "";// ����õ���
			String sccbsj = "";// �ϴγ���ʱ��
			String bccbsj = "";// ���γ���ʱ��
//			String bzydl = "";// �����õ���
			String ydltz = "";// �õ�������
			String dlmemo = "";// ����������ע
			String danjia = "";// ����
			String dftz = "";// ��ѵ���
			String dfmemo = "";// ��ѵ�����ע
			String csdbdfe = "";// ��ʡ�����Ѷ�
			String csdbdfbz = "";// ��ʡ�����ѱ�ֵ
			String csdbdfjdz = "";//��ʡ�������ֵ
			String cshidbdfbz = "";// ���ж����ѱ�ֵ
			String cshidbdfjdz = "";//���ж����Ѿ���ֵ
			String quxian="";
			String qsdbdlcbbl = null;//ȫʡ��������������
			String manpassmemo = null;//�˹����ͨ��ԭ��
			String accountmonth = null;//�����·�
			String tbtzsq = null;//�ر��������
			String dlid = null;//����ID
			String thisdegree = "";//���εĵ�����
			String lastdegree = "";//�ϴε�����
			String lastelecfee = "";//���ڵ��
			String lastelecdegree = "";//���ڵ���
			String lastunitprice = "";//���ڵ���
			

			// while2�ֶ�(��ͬ���忨��Ԥ����1)
			String zdid2 = null;
			String zdcode2 = null;
			String jzname2 = null;
			String dbid2 = null;
			String property2 = null;
			String dfzflx2 = null;
			String stationtype2 = null;
			String szdq2 = null;
			String notetypeid2 = null;
			String linelosstype2 = null;
			String linelossvalue2 = null;
			String beilv2 = null;
			String uuid2 = null;
			String actualpay2 = null;
			String cityaudit2 = null;
			String yff_id2 = null;
			String edhdl2 = null;
			String dfbzw2 = null;
			String zlfh2 = null;
			String jlfh2 = null;
			String jszq2 = null;
			String qsdbdl2 = null;
			String pjje2 = null;
			String dedhdl2 = "";
			String blhdl2 = "";
			String qsdbdlcbbl2 = null;//ȫʡ��������������
			
			String dbname2 = "";// �������
			String gdfs2 = "";// ���緽ʽ
			String dbydl2 = "";// ����õ���
			String sccbsj2 = "";// �ϴγ���ʱ��
			String bccbsj2 = "";// ���γ���ʱ��
//			String bzydl2 = "";// �����õ���
			String ydltz2 = "";// �õ�������
			String dlmemo2 = "";// ����������ע
			String danjia2 = "";// ����
			String dftz2 = "";// ��ѵ���
			String dfmemo2 = "";// ��ѵ�����ע
			String csdbdfe2 = "";// ��ʡ�����Ѷ�
			String csdbdfbz2 = "";// ��ʡ�����ѱ�ֵ
			String csdbdfjdz2 = "";//��ʡ�����Ѿ���ֵ
			String cshidbdfbz2 = "";// ���ж����ѱ�ֵ
			String cshidbdfjdz2 = "";//���ж����Ѿ���ֵ
			String rgshzt2 = "";
			String quxian2 ="";
			
			String autoauditstatus2 = "";//�Զ����״̬
			String manpassmemo2 = null;//�˹����ͨ��ԭ��
			String accountmonth2 = null;//�����·�
			
			String dlid2 = null;//����ID
			
			String lastyue = "";//�������

			while (rs1.next()) {
				String gdtx = "";//��������
				zdcode = rs1.getString(1);// վ��code
				jzname = rs1.getString(2);// վ������
				dbid = rs1.getString(3);// ���id
				property = rs1.getString(4);// վ������
				dfzflx = rs1.getString(5);// ���֧������
				stationtype = rs1.getString(6);// վ������
				szdq = rs1.getString(7);// ���ڵ���
				notetypeid = rs1.getString(8);// Ʊ������
				linelosstype = rs1.getString(9);// ��������
				linelossvalue = rs1.getString(10);// ����ֵ
				beilv = rs1.getString(11);// ����
				electricfee_id = rs1.getString(12);// ���id
				dfuuid = rs1.getString(13);// ���uuid
				actualpay = rs1.getString(14);// ʵ�ʵ�ѽ��double
				autoauditstatus = rs1.getString(15);// �Զ����״̬
				manualauditstatus = rs1.getString(16);// �˹����״̬
				cityaudit = rs1.getString(17);// �����״̬
				edhdl = rs1.getString(18);// ��ĵ���
				lastdatetime = rs1.getString(19);// �ϴγ���ʱ��
				thisdatetime = rs1.getString(20);// ���γ���ʱ��
				unitprice = rs1.getString(21);// ����
				dfbzw = rs1.getString(22);// ��ѱ�־λ
				blhdl = rs1.getString(23);// ʵ���õ���double
				dedhdl = rs1.getString(24);// �����������
				zlfh = rs1.getString(25);// ֱ������double
				jlfh = rs1.getString(26);// ��������double
				jszq = rs1.getString(27);// ��������
				qsdbdl = rs1.getString(28);// ȫʡ�������double
				pjje = rs1.getString(29);// Ʊ�ݽ��double
				zdid = rs1.getString(30);// վ��id

				dbname = rs1.getString(31) != null ? rs1.getString(31) : "";// �������
				gdfs = rs1.getString(32) != null ? rs1.getString(32) : "";// ���緽ʽ
				sccbsj = rs1.getString(33) != null ? rs1.getString(33) : "";// �ϴγ���ʱ��
				bccbsj = rs1.getString(34) != null ? rs1.getString(34) : "";// ���γ���ʱ��
				ydltz = rs1.getString(35) != null ? rs1.getString(35) : "";// �õ�������
				dlmemo = rs1.getString(36) != null ? rs1.getString(36) : "";// ����������ע
				danjia = rs1.getString(37) != null ? rs1.getString(37) : "0";// ����
				dftz = rs1.getString(38) != null ? rs1.getString(38) : "";// ��ѵ���
				dfmemo = rs1.getString(39) != null ? rs1.getString(39) : "";// ��ѵ�����ע
				quxian = rs1.getString(40) != null ? rs1.getString(40) : "";//����
				String actualdegree = rs1.getString(41) != null ? rs1.getString(41) : "";
				qsdbdlcbbl = rs1.getString(42) != null ? rs1.getString(42) : "";
				manpassmemo = rs1.getString(43);//�˹����ͨ��ԭ��
				accountmonth = rs1.getString(44);//�����·�
				tbtzsq = rs1.getString(45);//�ر��������
				dlid = rs1.getString(46);//����ID
				thisdegree = rs1.getString(47);//���ε�����
				lastdegree = rs1.getString(48);//�ϴε�����
				lastelecfee = rs1.getString(49);//���ڵ��
				lastelecdegree = rs1.getString(50);//���ڵ���
				lastunitprice = rs1.getString(51);//���ڵ���
				
				// �ж��Ƿ�Ϊ��
				if (bccbsj == null || bccbsj == "" || bccbsj == "o"
						|| "null".equals(bccbsj)) {
					bccbsj = "";
				}
				if (sccbsj == null || sccbsj == "" || sccbsj == "o"
						|| "null".equals(sccbsj)) {
					sccbsj = "";
				}
				if (blhdl == null || blhdl == "" || blhdl == "o"
						|| "null".equals(blhdl)) {
					blhdl = "0";
				}
				if (" ".equals(qsdbdl) || "null".equals(qsdbdl)
						|| qsdbdl == null || "".equals(qsdbdl) || Format.str_d(qsdbdl)==0) {
					qsdbdl = "";
				}
				if ("".equals(zlfh) || "null".equals(zlfh) || zlfh == null) {
					zlfh = "";// ԭ��Ŀ�������������
				}
				if ("".equals(jlfh) || "null".equals(jlfh) || jlfh == null) {
					jlfh = "";// ԭ��Ŀ�������������
				}
				if ("".equals(jszq) || "null".equals(jszq) || jszq == null) {
					jszq = "";// ԭ��Ŀ�������������
				}
				if ("".equals(dedhdl) || "null".equals(dedhdl)
						|| dedhdl == null) {
					dedhdl = "";// ԭ���ľ������������
				}
				if (unitprice == null || "".equals(unitprice)
						|| "o".equals(unitprice) || "null".equals(unitprice)) {
					unitprice = "0";// ԭ���ľ������������
				}
				if ("".equals(property) || "null".equals(property)
						|| property == null) {
					property = "";
				}
				if ("".equals(stationtype) || "null".equals(stationtype)
						|| stationtype == null) {
					stationtype = "";
				}
				if ("".equals(dfzflx) || "null".equals(dfzflx)
						|| dfzflx == null) {
					dfzflx = "";
				}
				if ("".equals(edhdl) || "null".equals(edhdl) || edhdl == null) {
					edhdl = "0";
				}
				if ("null".equals(linelosstype) || linelosstype == null) {
					linelosstype = "";
				}
				if ("null".equals(linelossvalue) || linelossvalue == null) {
					linelossvalue = "";
				}
				if (beilv == null || beilv == "" || beilv == "o") {
					beilv = "1";
				}
				if ("null".equals(actualpay) || "".equals(actualpay)
						|| actualpay == null) {
					actualpay = "0";
				}
				if ("null".equals(notetypeid) || null == notetypeid) {
					notetypeid = "";
				}
				if ("null".equals(pjje) || "".equals(pjje) || null == pjje) {
					pjje = "";// ԭ���Ŀ���������ô�����
				}
				cityaudit = cityaudit != null ? cityaudit : "";

				//���������ж�
				
				if("0".equals(cityaudit)){//�м�δͨ��
					gdtx = "�����";
				}else if("1".equals(cityaudit)){//�м�ͨ��
					if("-1".equals(manualauditstatus)){//����ͨ��
						gdtx = "�ϼ��˵�";
					}
				}else if("-2".equals(cityaudit)){//�м���ͨ��
					gdtx = "�ѳ���";
				}
				
				// ����
				double eddf = 0;
				String zq = jszq;// �������ڼ��㣬ԭ����java����ģ�������sqlֱ�Ӳ������
				Long l = Long.parseLong(zq != "" ? zq : "0");
//				eddf = Format.d2(Double.parseDouble(edhdl)
//						* Double.parseDouble(unitprice) * l); // ���ѹ�ʽ����ĵ���*���ε���*���ڣ�ҳ����û����ֶ���
//				String qsdbdlcbbl1 = "";
//				double qsdbdlcbbl = 0;
//				if (!"".equals(qsdbdl) && !"".equals(jszq)) {
//					long quot = Long.parseLong(jszq);// ����
//					double sjydl = Double.parseDouble(blhdl);// ʵ���õ���
//					double dbdl1 = Double.parseDouble(qsdbdl) * quot;// ȫʡ�������*����
//					qsdbdlcbbl = ((sjydl - dbdl1) / dbdl1) * 100;// ȫʡ��������������:��ʵ���õ���-��ȫʡ�������*���ڣ���/��ȫʡ�������*���ڣ�
//					// Ԥ���ѵĲ����й�ʽ�����о���ʾʡ����û�оͲ���ʾ
//					qsdbdlcbbl1 = Format.str2(String.valueOf(qsdbdlcbbl));
//				}
				qsdbdlcbbl = "".equals(qsdbdlcbbl)?"":Format.str2(qsdbdlcbbl);
				// �������õ���
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree));
				if(Format.str_d(beilv)==0){
					beilv = "1";
				}
				Double dbdl = 0.0;
				
					dbdl = actdegree * Format.str_d(beilv);
					BigDecimal bg = new BigDecimal(dbdl);
					dbdl = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				
				dbydl = dbdl.toString();// ����õ���
				
				/* ���ֶε�һЩ���� */
				if (!"".equals(qsdbdl) && !"".equals(jszq)) {

					double zq2 = 0;// ����
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}

				

					/* ���㳬ʡ�����Ѷ� */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(qsdbdl)
										* Double.parseDouble(danjia) * zq2);
						BigDecimal bg2 = new BigDecimal(sdbdf);
						sdbdf = bg2.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						sdbdf = 0.0;
					}
					csdbdfe = sdbdf.toString();// ��ʡ�����Ѷ�
					csdbdfjdz = String.valueOf(Format.d2(Math.abs(sdbdf.doubleValue())));//��ʡ�����Ѿ���ֵ

					/* ���㳬ʡ�����ѱ�ֵ */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl)
										* Double.parseDouble(danjia) * zq2)
								* 100;
						BigDecimal bg1 = new BigDecimal(csdbbz.doubleValue());
						csdbbz = bg1.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
							e.printStackTrace();
						csdbbz = 0.0;
					}
					csdbdfbz = Format.str2(csdbbz.toString());// ��ʡ�����ѱ�ֵ
				}
				if (!"0".equals(edhdl) && !"".equals(jszq)) {

					double zq2 = 0;// ����
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}
					/* ���㳬�ж����Ѷ� */
					Double shidbdf = 0.0;
					try {
						shidbdf = Double.parseDouble(actualpay)
								- (Double.parseDouble(edhdl)
										* Double.parseDouble(danjia) * zq2);
						BigDecimal bg3 = new BigDecimal(shidbdf);
						shidbdf = bg3.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						shidbdf = 0.0;
					}
					cshidbdfjdz = String.valueOf(Format.d2(Math.abs(shidbdf.doubleValue())));//���ж����Ѿ���ֵ

					/* ���㳬�ж����ѱ�ֵ */
					Double cshidbbz = 0.0;
					try {
						cshidbbz = shidbdf
								/ (Double.parseDouble(edhdl)
										* Double.parseDouble(danjia) * zq2)
								* 100;
						BigDecimal bg4 = new BigDecimal(cshidbbz);
						cshidbbz = bg4.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {

						cshidbbz = 0.0;
					}
					cshidbdfbz = Format.str2(cshidbbz.toString());// ���ж����ѱ�ֵ

				}
				// ��ֵ
				CityElectricFeeCheck feesbeana = new CityElectricFeeCheck();
				feesbeana.setZdcode(zdcode);
				feesbeana.setJzname(jzname);
				feesbeana.setDbid(dbid);
				feesbeana.setProperty(property);
				feesbeana.setDfzflx(dfzflx);
				feesbeana.setStationtype(stationtype);
				feesbeana.setSzdq(szdq);
				feesbeana.setNotetypeid(notetypeid);
				feesbeana.setLinelosstype(linelosstype);
				feesbeana.setLinelossvalue(linelossvalue);
				feesbeana.setEy_id(electricfee_id);
				feesbeana.setUuid(dfuuid);
				feesbeana.setActualpay(Format.str2(actualpay));
				feesbeana.setAutoauditstatus(autoauditstatus);
				feesbeana.setManualauditstatus(manualauditstatus);
				feesbeana.setCityaudit(cityaudit);
				feesbeana.setEdhdl(Format.str2(edhdl));
				feesbeana.setLastdatetime(lastdatetime);
				feesbeana.setThisdatetime(thisdatetime);
				feesbeana.setUnitprice(Format.str4(unitprice));
				feesbeana.setDfbzw(dfbzw);
				feesbeana.setBlhdl(Format.str2(blhdl));
				if ("��".equals(dedhdl) || "-��".equals(dedhdl)) {// ���Ϊ��ֱ�Ӹ�ֵ(��ֹ��ʽ������)�������ʽ���ٸ�ֵ
					feesbeana.setDedhdl(dedhdl);
				} else {
					feesbeana.setDedhdl( !"".equals(dedhdl) ? Format.str2(dedhdl) : "");
				}
				feesbeana.setZlfh( !"".equals(zlfh) ? Format.str2(zlfh) : "");
				feesbeana.setJlfh( !"".equals(jlfh) ? Format.str2(jlfh) : "");
				feesbeana.setJszq(jszq);
				feesbeana.setQsdbdl( !"".equals(qsdbdl) ? Format.str2(qsdbdl) : "");// Ϊ���򲻸�ʽ��,��Ϊ��ʱ��ʾ""������"0"
				feesbeana.setPjje( !"".equals(pjje) ? Format.str2(pjje) : "");
				feesbeana.setEddf(Format.str2(String.valueOf(eddf)));
				feesbeana.setQsdbdlcbbl(qsdbdlcbbl);
				feesbeana.setZdid(zdid);

				feesbeana.setDbname(dbname);// �������
				feesbeana.setGdfs(gdfs);// ���緽ʽ
				feesbeana.setDbydl(dbydl);// ����õ���
				feesbeana.setSccbsj(sccbsj);// �ϴγ���ʱ��
				feesbeana.setBccbsj(bccbsj);// ���γ���ʱ��
//				feesbeana.setBzydl(bzydl);// �����õ���
				feesbeana.setYdltz("".equals(ydltz)?"":Format.str2(ydltz));// �õ�������
				feesbeana.setDlmemo(dlmemo);// ����������ע
				feesbeana.setDanjia(Format.str4(danjia));// ����
				feesbeana.setDftz("".equals(dftz)?"":Format.str2(dftz));// ��ѵ���
				feesbeana.setDfmemo(dfmemo);// ��ѵ�����ע
				feesbeana.setCsdbdfe(csdbdfe);// ��ʡ�����Ѷ�
				feesbeana.setCsdbdfbz(csdbdfbz + "%");// ��ʡ�����ѱ�ֵ
				feesbeana.setQuxian(quxian);
				feesbeana.setManpassmemo(manpassmemo);//�˹����ͨ��ԭ��
				feesbeana.setAccountmonth(accountmonth);//�����·�
				feesbeana.setLrbz("��ѵ�¼��");//¼���־
				feesbeana.setLastelecfees(lastelecfee);//���ڵ��
				feesbeana.setLastelecdegree(lastelecdegree);//���ڵ���
				feesbeana.setLastunitprice(lastunitprice);//���ڵ���
				
				if("1".equals(tbtzsq)){
					tbtzsq = "�����룬��˸�ֵ";
				}else if("0".equals(tbtzsq)){
					tbtzsq = "������";
				}

				feesbeana.setTbtzsq(tbtzsq);
				feesbeana.setDlid(dlid);
				
				//��������
				feesbeana.setGdtx(gdtx);
				
				feesbeana.setLastyue("");
				

				//��������*���ʡ����ڵ���õ��������ڵ�������*������
				String bv = "";
				if(Format.str_d(beilv)==0){//����Ϊ�� �� Ϊ 0
					bv="1";
				}else{
					bv = beilv;
				}
				String lastactualdegree = rs1.getString("LASTACTUALDEGREE")!=null?rs1.getString("LASTACTUALDEGREE"):"0.00"; //���ڵ���õ���
				String lastfloatdegree = rs1.getString("LASTFLOATDEGREE")!=null?rs1.getString("LASTFLOATDEGREE"):"0.00"; //���ڵ�������
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(feesbeana.getYdltz())*Format.str_d(bv)));//��������*����
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//���ڵ�������*������
				feesbeana.setLastfloatdegreeandbl(lastfloatdegreeandbl);//���ڵ�������*����
				feesbeana.setLastactualdegree(lastactualdegree);//���ڵ���õ���
				feesbeana.setFloatdegreeandbl(floatdegreeandbl);//��������*����
				
				String cshengbdld;
				String cshibdld;
				//��ʡ������ȣ����б������
				if(Format.str_d(feesbeana.getQsdbdl())==0){
					cshengbdld = "";
				}else{
					cshengbdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getQsdbdl())* Format.str_d(feesbeana.getJszq())));
				}
				if(Format.str_d(feesbeana.getEdhdl())==0){
					cshibdld = "";
				}else{
					cshibdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getEdhdl())* Format.str_d(feesbeana.getJszq())));
				}
				feesbeana.setCshengbdld(cshengbdld);
				feesbeana.setCshibdld(cshibdld);
				feesbeana.setCsdbdfjdz(csdbdfjdz);
				feesbeana.setCshidbdfjdz(cshidbdfjdz);
				feesbeana.setCshidbdfbl(cshidbdfbz+ "%");
				
				
				
				/*String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +feesbeana.getZdid()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
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
				feesbeana.setGlbrjl(Format.str2(""));
				String changevalue = rs1.getString("CHANGEVALUE")!=null?rs1.getString("CHANGEVALUE"):"0"; //����ֵ
				String actuallinelossvalue = rs1.getString("ACTUALLINELOSSVALUE")!=null?rs1.getString("ACTUALLINELOSSVALUE"):"0"; //ʵ������ֵ
				String bzrj = rs1.getString("BZRJ")!=null?rs1.getString("BZRJ"):"0"; //�����վ�����
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//�߱������
				feesbeana.setLineandchangeandbl(changeandlineandbl);//�߱������
				feesbeana.setBzrj(Format.str2(bzrj));//�����վ�����
				feesbeana.setBeilv(beilv);
				list.add(feesbeana);// ��bean��ӵ�list��

			}
			while (rs2.next()) {
				String gdtx2 = "";//��������
				String financeaudit = "";//�������״̬
				zdcode2 = rs2.getString(1);// վ��code
				jzname2 = rs2.getString(2);// վ������
				dbid2 = rs2.getString(3);// ���id
				property2 = rs2.getString(4);// վ������
				dfzflx2 = rs2.getString(5);// ���֧������
				stationtype2 = rs2.getString(6);// վ������
				szdq2 = rs2.getString(7);// ���ڵ���
				notetypeid2 = rs2.getString(8);// Ʊ������
				linelosstype2 = rs2.getString(9);// ��������
				linelossvalue2 = rs2.getString(10);// ����ֵ
				beilv2 = rs2.getString(11);// ����
				uuid2 = rs2.getString(12);// Ԥ����uuid
				actualpay2 = rs2.getString(13);// ʵ�ʵ�ѽ��double
				cityaudit2 = rs2.getString(14);// �����״̬
				yff_id2 = rs2.getString(15);// Ԥ����id
				edhdl2 = rs2.getString(16);// ��ĵ���
				dfbzw2 = rs2.getString(17);// ��ѱ�־λ
				zlfh2 = rs2.getString(18);// ֱ������double
				jlfh2 = rs2.getString(19);// ��������double
				jszq2 = rs2.getString(20);// ��������
				qsdbdl2 = rs2.getString(21);// ȫʡ�������double
				pjje2 = rs2.getString(22);// Ʊ�ݽ��double
				zdid2 = rs2.getString(23);// վ��id

				dbname2 = rs2.getString(24) != null ? rs2.getString(24) : "";// �������
				gdfs2 = rs2.getString(25) != null ? rs2.getString(25) : "";// ���緽ʽ
				sccbsj2 = rs2.getString(26) != null ? rs2.getString(26) : "";// �ϴγ���ʱ��
				bccbsj2 = rs2.getString(27) != null ? rs2.getString(27) : "";// ���γ���ʱ��
				danjia2 = rs2.getString(28) != null ? rs2.getString(28) : "0";// ����
				rgshzt2 = rs2.getString(29) != null ? rs2.getString(29) : "";// �˹����״̬
				quxian2 = rs2.getString(30) != null ? rs2.getString(30) : "";//����
				
				dedhdl2 = rs2.getString(31);//�����������
				blhdl2 = rs2.getString(32);//ʵ���õ���
				autoauditstatus2 = rs2.getString(33);//�Զ����״̬
				qsdbdlcbbl2 = rs2.getString(34);
				manpassmemo2 = rs2.getString(35);//�˹����ͨ��ԭ��
				accountmonth2 = rs2.getString(36);//�����·�
				dlid2 = rs2.getString(37);
				financeaudit = rs2.getString(38) != null ? rs2.getString(38) : "";//�������״̬
				lastyue = rs2.getString(39) != null ? rs2.getString(39) : "";//�������
				
				if("-1".equals(financeaudit) || "2".equals(financeaudit)){//�������״̬Ϊ-1��2
					rgshzt2 = financeaudit;
				}

				// �ж��Ƿ�Ϊ��
				if ("".equals(dedhdl2) || "null".equals(dedhdl2) || dedhdl2 == null) {
					dedhdl2 = "";//ԭ���ľ������������
				}
				 if (blhdl2 == null || blhdl2 == "" || blhdl2 == "o" || "null".equals(blhdl2)){
			        	blhdl2 = "0";
			        	}
				if (bccbsj2 == null || bccbsj2 == "" || bccbsj2 == "o"
						|| "null".equals(bccbsj2)) {
					bccbsj2 = "";
				}
				if (sccbsj2 == null || sccbsj2 == "" || sccbsj2 == "o"
						|| "null".equals(sccbsj2)) {
					sccbsj2 = "";
				}
				if (" ".equals(qsdbdl2) || "null".equals(qsdbdl2)
						|| qsdbdl2 == null || Format.str_d(qsdbdl2)==0) {
					qsdbdl2 = "";
				}
				if ("".equals(zlfh2) || "null".equals(zlfh2) || zlfh2 == null) {
					zlfh2 = "";
				}
				if ("".equals(jlfh2) || "null".equals(jlfh2) || jlfh2 == null) {
					jlfh2 = "";
				}
				if ("".equals(jszq2) || "null".equals(jszq2) || jszq2 == null) {
					jszq2 = "";
				}
				if ("".equals(property2) || "null".equals(property2)
						|| property2 == null) {
					property2 = "";
				}
				if ("".equals(stationtype2) || "null".equals(stationtype2)
						|| stationtype2 == null) {
					stationtype2 = "";
				}
				if ("".equals(dfzflx2) || "null".equals(dfzflx2)
						|| dfzflx2 == null) {
					dfzflx2 = "";
				}
				if ("".equals(edhdl2) || "null".equals(edhdl2)
						|| edhdl2 == null) {
					edhdl2 = "0";
				}
				if ("null".equals(linelosstype2) || linelosstype2 == null) {
					linelosstype2 = "";
				}
				if ("null".equals(linelossvalue2) || linelossvalue2 == null) {
					linelossvalue2 = "";
				}
				if (beilv2 == null || beilv2 == "" || beilv2 == "o") {
					beilv2 = "0";
				}
				if ("null".equals(actualpay2) || "".equals(actualpay2)
						|| actualpay2 == null) {
					actualpay2 = "0";
				}
				if ("null".equals(notetypeid2) || null == notetypeid2) {
					notetypeid2 = "";
				}
				if ("null".equals(pjje2) || "".equals(pjje2) || null == pjje2) {
					pjje2 = "";
				}
				cityaudit2 = cityaudit2 != null ? cityaudit2 : "";
				
				qsdbdlcbbl2 = "".equals(qsdbdlcbbl2)?"":Format.str2(qsdbdlcbbl2);
				
				//���������ж�
				if("0".equals(cityaudit2)){//�м�δͨ��
					gdtx2 = "�����";
				}else if("1".equals(cityaudit2)){//�м�ͨ��
					if("-1".equals(rgshzt2)){//����ͨ��
						gdtx2 = "�ϼ��˵�";
					}
				}else if("-2".equals(cityaudit2)){//�м���ͨ��
					gdtx2 = "�ѳ���";
				}
				// �������õ���
				if(Format.str_d(beilv2)==0){
					beilv2 ="1";
				}
				Double dbdl = 0.0;
				
					dbdl = Format.str_d(blhdl2) * Format.str_d(beilv2);
					BigDecimal bg1 = new BigDecimal(dbdl);
					dbdl = bg1.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
				dbydl2 = dbdl.toString();// ����õ���
				
				if (!"".equals(qsdbdl2) && !"".equals(jszq2)) {

					/* ���ֶε�һЩ���� */
					double zq2 = 0;// ����
					
						zq2 = Format.str_d(jszq2);
					

				

					/* ���㳬ʡ�����Ѷ� */
					Double sdbdf = 0.0;
					try {
						sdbdf = Double.parseDouble(actualpay2)
								- (Double.parseDouble(qsdbdl2)
										* Double.parseDouble(danjia2) * zq2);
						
						BigDecimal bg = new BigDecimal(sdbdf);
						sdbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
					csdbdfe2 = Format.str2(sdbdf.toString());// ��ʡ�����Ѷ�
					csdbdfjdz2 = String.valueOf(Format.d2(Math.abs(sdbdf.doubleValue())));//��ʡ�����Ѿ���ֵ
					/* ���㳬ʡ�����ѱ�ֵ */
					Double csdbbz = 0.0;
					try {
						csdbbz = sdbdf
								/ (Double.parseDouble(qsdbdl2)
										* Double.parseDouble(danjia2) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(csdbbz);
						csdbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						csdbbz = 0.0;
					}
					csdbdfbz2 = Format.str2(csdbbz.toString());// ��ʡ�����ѱ�ֵ
				}
				if (!"0".equals(edhdl2) && !"".equals(jszq2)) {

					double zq2 = 0;// ����
					try {
						zq2 = Double.parseDouble(jszq);
					} catch (Exception e) {

						zq2 = 0;
					}
					/* ���㳬�ж����Ѷ� */
					Double shidbdf = 0.0;
					try {
						shidbdf = Double.parseDouble(actualpay2)
								- (Double.parseDouble(edhdl2)
										* Double.parseDouble(danjia2) * zq2);
						BigDecimal bg = new BigDecimal(shidbdf);
						shidbdf = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						e.printStackTrace();
						shidbdf = 0.0;
					}
					cshidbdfjdz2 = String.valueOf(Format.d2(Math.abs(shidbdf.doubleValue())));//���ж����Ѿ���ֵ

					/* ���㳬�ж����ѱ�ֵ */
					Double cshidbbz = 0.0;
					try {
						cshidbbz = shidbdf
								/ (Double.parseDouble(edhdl2)
										* Double.parseDouble(danjia2) * zq2)
								* 100;
						BigDecimal bg = new BigDecimal(cshidbbz);
						cshidbbz = bg.setScale(2, BigDecimal.ROUND_HALF_UP)
								.doubleValue();
					} catch (Exception e) {
						cshidbbz = 0.0;
					}
					cshidbdfbz2 = Format.str2(cshidbbz.toString());// ���ж����ѱ�ֵ

				}
				
				// ��ֵ
				CityElectricFeeCheck feesbeana = new CityElectricFeeCheck();
				feesbeana.setZdcode(zdcode2);
				feesbeana.setJzname(jzname2);
				feesbeana.setDbid(dbid2);
				feesbeana.setProperty(property2);
				feesbeana.setDfzflx(dfzflx2);
				feesbeana.setStationtype(stationtype2);
				feesbeana.setSzdq(szdq2);
				feesbeana.setNotetypeid(notetypeid2);
				feesbeana.setLinelosstype(linelosstype2);
				feesbeana.setLinelossvalue(linelossvalue2);
				feesbeana.setUuid(uuid2);
				feesbeana.setActualpay(Format.str2(actualpay2));
				feesbeana.setCityaudit(cityaudit2);
				feesbeana.setEy_id(yff_id2);
				feesbeana.setEdhdl(Format.str2(edhdl2));
				feesbeana.setDfbzw(dfbzw2);
				feesbeana.setZlfh(!"".equals(zlfh2) ? Format.str2(zlfh2) : "");// Ϊ���򲻸�ʽ��,��Ϊ��ʱ��ʾ""������"0"
				feesbeana.setJlfh(!"".equals(jlfh2) ? Format.str2(jlfh2) : "");// Ϊ���򲻸�ʽ��
				feesbeana.setJszq(jszq2);
				feesbeana.setQsdbdl( !"".equals(qsdbdl2) ? Format.str2(qsdbdl2) : "");// Ϊ���򲻸�ʽ��
				feesbeana.setPjje( !"".equals(pjje2) ? Format.str2(pjje2) : "");// Ϊ���򲻸�ʽ��
				feesbeana.setQsdbdlcbbl(qsdbdlcbbl2);// Ϊ���򲻸�ʽ��
				feesbeana.setZdid(zdid2);

				feesbeana.setDbname(dbname2);// �������
				feesbeana.setGdfs(gdfs2);// ���緽ʽ
				feesbeana.setDbydl(dbydl2);// ����õ���
				feesbeana.setSccbsj(sccbsj2);// �ϴγ���ʱ��
				feesbeana.setBccbsj(bccbsj2);// ���γ���ʱ��
//				feesbeana.setBzydl(bzydl2);// �����õ���
				feesbeana.setYdltz("".equals(ydltz2)?"":Format.str2(ydltz2));// �õ�������
				feesbeana.setDlmemo(dlmemo2);// ����������ע
				feesbeana.setDanjia(Format.str4(danjia2));// ����
				feesbeana.setDftz("".equals(dftz2)?"":Format.str2(dftz2));// ��ѵ���
				feesbeana.setDfmemo(dfmemo2);// ��ѵ�����ע
				feesbeana.setCsdbdfe(csdbdfe2);// ��ʡ�����Ѷ�
				feesbeana.setCsdbdfbz(csdbdfbz2 + "%");// ��ʡ�����ѱ�ֵ
				feesbeana.setManualauditstatus(rgshzt2);
				feesbeana.setQuxian(quxian2);
				if("��".equals(dedhdl2) || "-��".equals(dedhdl2)){//���Ϊ��ֱ�Ӹ�ֵ(��ֹ��ʽ������)�������ʽ���ٸ�ֵ
		        	feesbeana.setDedhdl(dedhdl2);
		        }else{
		        	feesbeana.setDedhdl(!"".equals(dedhdl2)?Format.str2(dedhdl2):"");
		        }
		        feesbeana.setBlhdl(Format.str2(blhdl2));
		        feesbeana.setAutoauditstatus(autoauditstatus2);
		        feesbeana.setManpassmemo(manpassmemo2);//�˹����ͨ��ԭ��
		        feesbeana.setAccountmonth(accountmonth2);//�����·�
		        feesbeana.setLrbz("Ԥ����¼��");//¼���־
		        feesbeana.setGdtx(gdtx2);
		        
		        feesbeana.setTbtzsq("");
		        feesbeana.setDlid(dlid2);
		        feesbeana.setLastyue(lastyue);
		        
		        feesbeana.setLastfloatdegreeandbl("");//���ڵ�������*����
				feesbeana.setLastactualdegree("");//���ڵ���õ���
				feesbeana.setFloatdegreeandbl("");//��������*����
				
				String cshengbdld;
				String cshibdld;
				//��ʡ������ȣ����б������
				if(Format.str_d(feesbeana.getQsdbdl())==0){
					cshengbdld = "";
				}else{
					cshengbdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getQsdbdl())* Format.str_d(feesbeana.getJszq())));
				}
				if(Format.str_d(feesbeana.getEdhdl())==0){
					cshibdld = "";
				}else{
					cshibdld = Format.str2(String.valueOf(Format.str_d(feesbeana.getBlhdl()) - Format.str_d(feesbeana.getEdhdl())* Format.str_d(feesbeana.getJszq())));
				}
				feesbeana.setCshengbdld(cshengbdld);
				feesbeana.setCshibdld(cshibdld);
				feesbeana.setCsdbdfjdz(csdbdfjdz2);
				feesbeana.setCshidbdfjdz(cshidbdfjdz2);
				feesbeana.setCshidbdfbl(cshidbdfbz2+ "%");
				feesbeana.setGlbrjl("");
				feesbeana.setLineandchangeandbl("");//�߱������
				String bzrj = rs2.getString("BZRJ")!=null?rs2.getString("BZRJ"):"0"; //�����վ�����
				feesbeana.setBzrj(Format.str2(bzrj));//�����վ�����
				feesbeana.setBeilv(beilv2);
				list.add(feesbeana);
			}
		} catch (DbException dbe) {// ��׽�쳣
			dbe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// �ͷ���Դ
			db.free(rs1, ps1, null);
			db.free(rs2, ps2, conn);
		}
		return list;// ���ؽ����
	}

	/**
	 * @param list
	 *            List(CityElectricFeeCheck)
	 * @return (double[]) total[0] �����ϼ� total[1] ��Ѻϼ�
	 * @see ��������ϼƺ͵�Ѻϼ�
	 * @author WangYiXiao 2014-1-24
	 */
	public String[] total(List<CityElectricFeeCheck> list) {
		String[] total = new String[2];
		double totalelectric = 0;// �����ϼ�
		double totalmoney = 0;// ��Ѻϼ�
		int i;
		int size = list.size();// ��������
		String blhdl = null;
		for (i = 0; i < size; i++) {// �������
			CityElectricFeeCheck bean = list.get(i);// ���һ������
			blhdl = bean.getBlhdl();// ʵ���õ���
			if (blhdl == null || blhdl == "" || blhdl == "o"
					|| "null".equals(blhdl)) {// ʵ���õ���Ϊ����0
				blhdl = "0";
			}
			totalelectric = totalelectric + Double.parseDouble(blhdl);
			totalmoney = totalmoney + Double.parseDouble(bean.getActualpay());
		}
		// ����total
		total[0] = Format.str2(String.valueOf(totalelectric));
		total[1] = Format.str2(String.valueOf(totalmoney));
		return total;
	}

	/**
	 * @param personnal
	 *            (String) �����
	 * @param cityaudit
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
	 * @author WangYiXaio 2014-1-25
	 * @update WangYiXiao 2014-4-2 �ѱ����·� ��Ϊ ��ǰ�·�
	 * @return String �����Ϣmsg
	 */
	public synchronized String CheckCityFees(String personnal,
			String cityaudit, String chooseIdStr1, String chooseIdStr2,
			String bz) {
		String msg = "��˵����Ϣʧ�ܣ�";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// ϵͳʱ��
		String yuefen = "to_date(to_char(sysdate,'yyyy-mm'),'yyyy-mm')";//��ǰ�·�
		DataBase db = new DataBase();
		Connection conn = null;
		String str = "";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		if (bz.equals("1")) {
			str = "";
		} else if (bz.equals("0")) {
			str = "LIUCHENGHAO='',";// ���̺�
		}
		StringBuffer sql1 = new StringBuffer();// �½ᣬԤ����2
		StringBuffer sql2 = new StringBuffer();// ��ͬ�忨Ԥ����1
		try {
			conn = db.getConnection();
			// conn.setAutoCommit(false);
			sql1.append(" UPDATE ELECTRICFEES SET CITYAUDIT='" + cityaudit
					+ "'," + "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+" ACCOUNTMONTH=" + yuefen
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYAUDIT='" + cityaudit + "',"
					+ "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+ "ACCOUNTMONTH=" + yuefen
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// ������uuid��Ϊ����ִ��sql
				ps1 = conn.prepareStatement(sql1.toString());
				System.out.println("�м���˵�ѵ�"+sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) { // ���Ԥ����uuid��Ϊ����ִ��sql
				ps2 = conn.prepareStatement(sql2.toString());
				System.out.println("�м����Ԥ����"+sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "��˵����Ϣͨ����";
			} else if (bz.equals("0")) {
				msg = "��˵����Ϣȡ��ͨ����";
			} else {
				msg = "��˵����Ϣδͨ����";
			}
			// conn.commit();
			// conn.setAutoCommit(true);
		} catch (Exception e) {// �쳣����
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {// �ͷ���Դ
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}


	/**
	 * @author lijing
	 * @see �����м�������ͨ������ͨ����ȡ��ͨ������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Override
	public String CheckCityFees1(String personnal, String cityaudit,
			String chooseIdStr1, String chooseIdStr2, String bz,
			String cause) {
		String msg = "��˵����Ϣʧ�ܣ�";
		String time = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";// ϵͳʱ��
		String yuefen = "to_date(to_char(sysdate,'yyyy-mm'),'yyyy-mm')";//��ǰ�·�
		DataBase db = new DataBase();
		Connection conn = null;
		String str = "";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		if (bz.equals("1")) {
			str = "";
		} else if (bz.equals("0")) {
			str = "LIUCHENGHAO='',";// ���̺�
		}
		StringBuffer sql1 = new StringBuffer();// �½ᣬԤ����2
		StringBuffer sql2 = new StringBuffer();// ��ͬ�忨Ԥ����1
		try {
			conn = db.getConnection();
			
			sql1.append(" UPDATE ELECTRICFEES SET CITYAUDIT='" + cityaudit
					+ "'," + "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+" ACCOUNTMONTH=" + yuefen+","
					+" NOPASSCITY='" + cause+"'"
					+ " WHERE DFUUID IN (" + chooseIdStr1 + ")");
			sql2.append(" UPDATE PREPAYMENT SET CITYAUDIT='" + cityaudit + "',"
					+ "CITYAUDITTIME=" + time + "," + str
					+ "CITYAUDITPENSONNEL='" + personnal + "',"
					+ "ACCOUNTMONTH=" + yuefen+","
					+" NOPASSCITY='" + cause + "'"
					+ " WHERE UUID IN (" + chooseIdStr2 + ")");
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// ������uuid��Ϊ����ִ��sql
				ps1 = conn.prepareStatement(sql1.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != "" && chooseIdStr2 != null) { // ���Ԥ����uuid��Ϊ����ִ��sql
				ps2 = conn.prepareStatement(sql2.toString());
				ps2.executeUpdate();
			}
			if (bz.equals("1")) {
				msg = "�����м���������Ϣͨ����";
			} else if (bz.equals("0")) {
				msg = "�����м���������Ϣȡ��ͨ����";
			} else {
				msg = "�����м���������Ϣδͨ����";
			}
		} catch (Exception e) {// �쳣����
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {// �ͷ���Դ
			db.free(null, ps1, null);
			db.free(null, ps2, conn);
		}
		return msg;
	}

}
