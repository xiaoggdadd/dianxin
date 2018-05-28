package com.ptac.app.datastatistics.monitoringshare.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.ptac.app.datastatistics.monitoringshare.bean.MonShareBean;

/**
 * @author �
 * @see �����̯ʵ�ֲ�
 */
public class MonShareDaoImp implements MonShareDao {

	@Override
	public ArrayList<MonShareBean> queryExport(String whereStr, String loginId) {
		
		ArrayList<MonShareBean> list = new ArrayList<MonShareBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta  = null;
		ResultSet rs = null;
	 
		sql.append("SELECT RNDIQU(ZD.SHI),RNDIQU(ZD.XIAN),RNDIQU(ZD.XIAOQU),ZD.JZNAME,RTNAME(ZD.PROPERTY),RTNAME(ZD.STATIONTYPE),"
				 + "ZD.ID,D.DBID,(SELECT QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.SHUOSHUZHUANYE) SHUOSHUZHUANYE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.QCBCODE) QCBCODE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.KJKMCODE) KJKMCODE,"
				 + "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE = S.ZYMXCODE) ZYMXCODE,S.DBILI,S.XJBILI"
				 + " FROM ZHANDIAN ZD, DIANBIAO D, SBGL S"
				 + " WHERE ZD.ID = D.ZDID AND D.DBID = S.DIANBIAOID"
				 + whereStr
				 + " AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND ZD.SHSIGN = '1' AND D.DBYT = 'dbyt01'");
		
		System.out.println("�����̯:"+sql);
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			
			while(rs.next()){
				MonShareBean bean = new MonShareBean();
				
				String shi = rs.getString(1)!=null?rs.getString(1):"";//��
				String xian = rs.getString(2)!=null?rs.getString(2):"";//����	
				String xiaoqu = rs.getString(3)!=null?rs.getString(3):"";//����	
				String zdname = rs.getString(4)!=null?rs.getString(4):"";//վ������
				String zdsx = rs.getString(5)!=null?rs.getString(5):"";//վ������
				String zdlx = rs.getString(6)!=null?rs.getString(6):"";//վ������	
				String id = rs.getString(7)!=null?rs.getString(7):"";//վ��ID
				String dbid = rs.getString(8)!=null?rs.getString(8):"";//���ID
				String sszy = rs.getString(9)!=null?rs.getString(9):"";//����רҵ
				String qcb = rs.getString(10)!=null?rs.getString(10):"";//ȫ�ɱ�
				String kjkm = rs.getString(11)!=null?rs.getString(11):"";//��ƿ�Ŀ
				String zymx = rs.getString(12)!=null?rs.getString(12):"";//רҵ��ϸ
				String yjbl = rs.getString(13)!=null?rs.getString(13):"";//һ������
				String ejbl = rs.getString(14)!=null?rs.getString(14):"";//��������
				
				bean.setShi(shi);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				bean.setZdname(zdname);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setId(id);
				bean.setDbid(dbid);
				bean.setSszy(sszy);
				bean.setQcb(qcb);
				bean.setKjkm(kjkm);
				bean.setZymx(zymx);
				bean.setYjbl(yjbl);
				bean.setEjbl(ejbl);
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, sta, conn);
		}
		return list;
	}

}
