package com.ptac.app.priceover.citycheck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;

/**
 * @author lijing
 * @see ���۳��������ʵ�ֲ�
 */
public class CityCheckDaoImp implements CityCheckDao {

	@Override
	public List<CityCheckBean> queryExport(String string, String loginId,
			String beginbl,String endbl) {
		
		List<CityCheckBean> list = new ArrayList<CityCheckBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(SHI),JZNAME,PLD,BZPRICE,SJPRICE,ACTUALPAY,BLHDL,RTNAME(PROPERTY),"
				 + "(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " rtname(GDFS),ZDID,RNDIQU(XIAN),RNDIQU(XIAOQU),CITYAUDIT,ID,TO_CHAR(ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,PROVINCEAUDIT"
				 + " FROM PRICEPROCEDURE WHERE 1=1  AND COUNTYCHECK = '1' "
				 + string
				 + (beginbl==""?"":(" AND PLD >="+beginbl))+ (endbl==""?"":(" AND PLD <="+endbl)));
		
		System.out.println("�������Ϣ��ѯ��"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CityCheckBean bean = new CityCheckBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//��
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//վ������
				String pld = rs.getString(3) != null ? rs.getString(3) : "";//ƫ���
				String bzdj = rs.getString(4) != null ? rs.getString(4) : "";//��׼����
				String sjdj = rs.getString(5) != null ? rs.getString(5) : "";//ʵ�ʵ���
				String bzdf = rs.getString(6) != null ? rs.getString(6) : "";//���˵��
				String bzdl = rs.getString(7) != null ? rs.getString(7) : "";//���˵���
				String zdsx = rs.getString(8) != null ? rs.getString(8) : "";//վ������
				String zdlx = rs.getString(9) != null ? rs.getString(9) : "";//վ������
				String gdfs = rs.getString(10) != null ? rs.getString(10) : "";//���緽ʽ
				String zdid = rs.getString(11) != null ? rs.getString(11) : "";//վ��ID
				String xian = rs.getString(12) != null ? rs.getString(12) : "";//����
				String xiaoqu = rs.getString(13) != null ? rs.getString(13) : "";//����
				String state = rs.getString(14) != null ? rs.getString(14) : "";//�����״̬
				String id = rs.getString(15) != null ? rs.getString(15) : "";
				String accountmonth = rs.getString(16) != null ? rs.getString(16) : "";//�����·�
				String shbzw = rs.getString(17) != null ? rs.getString(17) : "";//ʡ��˱�־λ
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				if("0".equals(state)){
					bean.setState("δ���");
				}else if("1".equals(state)){
					bean.setState("ͨ��");
				}else if("2".equals(state)){
					bean.setState("�˵�");
				}
				bean.setId(id);
				bean.setAccountmonth(accountmonth);
				bean.setShbzw(shbzw);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public CityCheckBean getInfo(String id, String bzyf) {
		
		CityCheckBean bean = new CityCheckBean();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT JZNAME,ZDID,RTNAME(PROPERTY),(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " RNDIQU(SHI),RNDIQU(XIAN),RNDIQU(XIAOQU),to_char(ACCOUNTMONTH,'yyyy-mm'),ACTUALPAY,BLHDL,SJPRICE,BZPRICE,PLD,rtname(GDFS),rtname(YZYDLX),YZHDJCDJ,"
				 + " HZDJ,BZPRICE,JFDLZB,PDLZB,ZGDJCDJ,GFDLZB,GDLZB,BYQRL,BL,rtname(YDSX),QSDBDL,XBSL,XBSDL,round(GLFSFXS,2),round(MSSFXS,2),ID"
				 + " FROM PRICEPROCEDURE WHERE ID = '"+id+"'" +(bzyf==""?"":("  AND to_char(ACCOUNTMONTH,'yyyy-mm') = '"+bzyf+"'")));
		
		System.out.println("�������ϸ��Ϣ��ѯ��"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			if(rs.next()){
				
				String zdname = rs.getString(1) != null ? rs.getString(1) : "";//վ������
				String zdid = rs.getString(2) != null ? rs.getString(2) : "";//վ��ID
				String zdsx = rs.getString(3) != null ? rs.getString(3) : "";//վ������
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//վ������
				String city = rs.getString(5) != null ? rs.getString(5) : "";//��
				String xian = rs.getString(6) != null ? rs.getString(6) : "";//����
				String xiaoqu = rs.getString(7) != null ? rs.getString(7) : "";//����
				String accountmonth = rs.getString(8) != null ? rs.getString(8) : "";//�����·�
				String bzdf = rs.getString(9) != null ? rs.getString(9) : "";//���˵��
				String bzdl = rs.getString(10) != null ? rs.getString(10) : "";//���˵���
				String sjdj = rs.getString(11) != null ? rs.getString(11) : "";//ʵ�ʵ���
				String bzdj = rs.getString(12) != null ? rs.getString(12) : "";//��׼����
				String pld = rs.getString(13) != null ? rs.getString(13) : "";//ƫ���
				String gdfs = rs.getString(14) != null ? rs.getString(14) : "";//���緽ʽ
				String yzydlx = rs.getString(15) != null ? rs.getString(15) : "";//ҵ���õ�����
				String yzhdjcdj = rs.getString(16) != null ? rs.getString(16) : "";//ҵ������������
				String hzdj = rs.getString(17) != null ? rs.getString(17) : "";//��׼����
				String sbzdj = rs.getString(18) != null ? rs.getString(18) : "";//ʡ��׼����
				String jfdlzb = rs.getString(19) != null ? rs.getString(19) : "";//������(ռ��)
				String pdlzb = rs.getString(20) != null ? rs.getString(20) : "";//ƽ����(ռ��)
				String zgdjcdj = rs.getString(21) != null ? rs.getString(21) : "";//ֱ�����������
				String gfdlzb = rs.getString(22) != null ? rs.getString(22) : "";//�߷����(ռ��)
				String gdlzb = rs.getString(23) != null ? rs.getString(23) : "";//�ȵ���(ռ��)
				String byqrl = rs.getString(24) != null ? rs.getString(24) : "";//��ѹ������
				String beilv = rs.getString(25) != null ? rs.getString(25) : "";//����
				String ydsx = rs.getString(26) != null ? rs.getString(26) : "";//�õ�����(����/������)
				String sdb = rs.getString(27) != null ? rs.getString(27) : "";//ʡ����
				String xbsl = rs.getString(28) != null ? rs.getString(28) : "";//�߱�����
				String xbsdl = rs.getString(29) != null ? rs.getString(29) : "";//�߱������(�߱�����*ʡ����)
				String glfsfxs = rs.getString(30) != null ? rs.getString(30) : "";//������ϸ�ϵ��(%)
				String msfxs = rs.getString(31) != null ? rs.getString(31) : "";//æʱ�ϸ�ϵ��(%)
				String id1 = rs.getString(32) != null ? rs.getString(32) : "";
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setAccountmonth(accountmonth);
				bean.setYzhdjcdj(yzhdjcdj);
				bean.setYzydlx(yzydlx);
				bean.setHzdj(hzdj);
				bean.setSbzdj(sbzdj);
				bean.setJfdlzb(jfdlzb);
				bean.setPdlzb(pdlzb);
				bean.setZgdjcdj(zgdjcdj);
				bean.setGfdlzb(gfdlzb);
				bean.setGdlzb(gdlzb);
				bean.setByqrl(byqrl);
				bean.setBeilv(beilv);
				bean.setYdsx(ydsx);
				bean.setSdb(sdb);
				bean.setXbsl(xbsl);
				bean.setXbsdl(xbsdl);
				bean.setGlfsfxs(glfsfxs);
				bean.setMsfxs(msfxs);
				bean.setId(id1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return bean;
	}

	@Override
	public int tdUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set CITYAUDIT='2',CITYAUDITPERSON='"+loginName+"',CITYAUDITTIME=sysdate where ID in ("+id+")";
		System.out.println("���˵���"+sql.toString());
		DataBase db = new DataBase();
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
	}

	@Override
	public int tgUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set CITYAUDIT='1',CITYAUDITPERSON='"+loginName+"',CITYAUDITTIME=sysdate where ID in ("+id+")";
		System.out.println("�����ͨ����"+sql.toString());
		DataBase db = new DataBase();
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
	}

}
