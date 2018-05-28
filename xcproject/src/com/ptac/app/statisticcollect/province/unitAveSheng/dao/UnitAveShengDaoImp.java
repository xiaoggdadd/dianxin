package com.ptac.app.statisticcollect.province.unitAveSheng.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.electricdetail.bean.ElectricDetail;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.city.unitAveShi.bean.UnitAveShiBean;
import com.ptac.app.statisticcollect.province.unitAveSheng.bean.UnitAveShengBean;
/**
 * @see ��ѽ�����ϸdao��ʵ����
 * @author ZengJin
 */
public class UnitAveShengDaoImp implements UnitAveShengDao {
	/**
	 * @param whereStr (String)	//sql������
	 * @param loginId (String) //Ȩ�޹������� 
	 * @return List(UnitAveShiBean)
	 * @see ʡ������ƽ����ƽ������
	 * @author ZengJin 2014-3-31
	 */
	public List<UnitAveShengBean> queryElectric(String whereStr, String loginId, String shi, String bzyfstart, String bzyfend) {
		List<UnitAveShengBean> list = new ArrayList<UnitAveShengBean>();//���������
		StringBuffer sql = new StringBuffer();
		
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT (SELECT AGNAME AS SHI FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) AS SHI," 
					 +" SUM(E.UNITPRICE) AS BZDJZH,"
					 +" COUNT(E.ELECTRICFEE_ID) AS DFDTS,"
					 +" SUM(E.ACTUALPAY) AS ZDF,"
					 +" SUM(A.BLHDL) AS ZDL,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.UNITPRICE, NULL)) AS SQBZDJZH,"
					 +" COUNT(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.ELECTRICFEE_ID, NULL)) AS SQDFDTS,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.UNITPRICE, NULL)) AS MQBZDJZH,"
					 +" COUNT(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.ELECTRICFEE_ID, NULL)) AS MQDFDTS,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', E.ACTUALPAY, NULL)) AS SQZDF,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfstart +"', A.BLHDL, NULL)) AS SQZDL,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', E.ACTUALPAY, NULL)) AS MQZDF,"
					 +" SUM(DECODE(TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm'), '"+ bzyfend +"', A.BLHDL, NULL)) AS MQZDL"
					 +" FROM ZHANDIAN ZD, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E"
					 +" WHERE ZD.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK"
					 +" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') >= '"+ bzyfstart +"'"
					 +" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') <= '"+ bzyfend +"'"
					 + whereStr
					 +" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId +"'))"
					 +" GROUP BY ZD.SHI");
				
			System.out.println("ʡ������ƽ����ƽ������:" + sql);//��̨��ӡsql
			
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();

			rs = sta.executeQuery(sql.toString());//����sql
			while (rs.next()) {
				UnitAveShengBean bean = new UnitAveShengBean();//����ÿ��һ�����ݱ�����һ��bean
				//------���ղ�ѯ��������(���ж��Ƿ�Ϊ��)------
				String shiCh = rs.getString("SHI") != null ? rs.getString("SHI") : "";	//��
				double bzdjzh = Format.str_d(	rs.getString("BZDJZH")!=null?rs.getString("BZDJZH"):"");	//���˵����ܺ�
				double dfdts = Format.str_d(	rs.getString("DFDTS")!=null?rs.getString("DFDTS"):"");		//��ѵ�����
				double zdf = Format.str_d(		rs.getString("ZDF")!=null?rs.getString("ZDF"):"");			//�ܵ��
				double zdl = Format.str_d(		rs.getString("ZDL")!=null?rs.getString("ZDL"):"");			//�ܵ���
				double sqbzdjzh	= Format.str_d(	rs.getString("SQBZDJZH")!=null?rs.getString("SQBZDJZH"):"");//���ڱ��˵����ܺ�
				double sqdfdts = Format.str_d(	rs.getString("SQDFDTS")!=null?rs.getString("SQDFDTS"):"");	//���ڵ�ѵ�����
				double mqbzdjzh	= Format.str_d(	rs.getString("MQBZDJZH")!=null?rs.getString("MQBZDJZH"):"");//ĩ�ڱ��˵����ܺ�
				double mqdfdts = Format.str_d(	rs.getString("MQDFDTS")!=null?rs.getString("MQDFDTS"):"");	//ĩ�ڵ�ѵ�����
				double sqzdf = Format.str_d(	rs.getString("SQZDF")!=null?rs.getString("SQZDF"):"");		//�����ܵ��
				double sqzdl = Format.str_d(	rs.getString("SQZDL")!=null?rs.getString("SQZDL"):"");		//�����ܵ���
				double mqzdf = Format.str_d(	rs.getString("MQZDF")!=null?rs.getString("MQZDF"):"");		//ĩ���ܵ��
				double mqzdl = Format.str_d(	rs.getString("MQZDL")!=null?rs.getString("MQZDL"):"");		//ĩ���ܵ���
				
				//------�����ֶ�ֱ�ӷ���bean------
				bean.setShi(shiCh);
				bean.setBzyfstart(bzyfstart);
				bean.setBzyfend(bzyfend);
				
				//------���� ����ƽ�� = ���˵����ܺ�/��ѵ�����------
				double danjiapj = 0;
				if( dfdts != 0 && dfdts != 0.00 ){
					danjiapj = bzdjzh / dfdts;
				}
				bean.setDanjiapj(Format.str4(Double.toString(danjiapj)));
				
				//------���� ƽ������ = �ܵ��/�ܵ���------
				double pingjundj = 0;
				if( zdl !=0 && zdl != 0.00 ){
					pingjundj = zdf / zdl;
				}
				bean.setPingjundj(Format.str4(Double.toString(pingjundj)));
				
				//------���� ���ڵ���ƽ�� = ���ڱ��˵����ܺ�/���ڵ�ѵ�����------
				double sqdanjiapj = 0;
				if( sqdfdts != 0 && sqdfdts != 0.00 ){
					sqdanjiapj = sqbzdjzh / sqdfdts;
				}
				//------���� ĩ�ڵ���ƽ�� = ĩ�ڱ��˵����ܺ�/ĩ�ڵ�ѵ�����------
				double mqdanjiapj = 0;
				if( mqdfdts != 0 && mqdfdts != 0.00 ){
					mqdanjiapj = mqbzdjzh / mqdfdts;
				}
				//------���� ����ƽ������ = (ĩ�ڵ���ƽ��-���ڵ���ƽ��)/���ڵ���ƽ��------
				double danjiapjqs = 0;
				if( sqdanjiapj != 0 && sqdanjiapj != 0.00 ){
					danjiapjqs = ( mqdanjiapj - sqdanjiapj ) / sqdanjiapj;
				}
				bean.setDanjiapjqs(Format.str2(Double.toString(danjiapjqs)));
				
				//------���� ����ƽ������ = �����ܵ��/�����ܵ���------
				double sqpingjundj = 0;
				if( sqzdl !=0 && sqzdl != 0.00 ){
					sqpingjundj = sqzdf / sqzdl;
				}
				//------���� ĩ��ƽ������ = ĩ���ܵ��/ĩ���ܵ���------
				double mqpingjundj = 0;
				if( mqzdl !=0 && mqzdl != 0.00 ){
					mqpingjundj = mqzdf / mqzdl;
				}
				//------���� ƽ���������� = (ĩ��ƽ������-����ƽ������)/����ƽ������------
				double pingjundjqs = 0;
				if( sqpingjundj != 0 && sqpingjundj != 0.00 ){
					pingjundjqs = ( mqpingjundj - sqpingjundj ) / sqpingjundj;
				}
				bean.setPingjundjqs(Format.str2(Double.toString(pingjundjqs)));
				list.add(bean);//��ÿ��bean�Ž������
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	}
}
