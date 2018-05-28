package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.function.CityQueryBean;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.newfunction.javabean.zgsj;
import com.noki.util.CTime;

public class zgsjdao {
	 
	// �ݹ������м����2 ��ѯ
	public synchronized List<zgsj>  getzgsj2(String whereStr,
			String loginId) {		
		List<zgsj>  list = new ArrayList<zgsj>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
		//	fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT S.ID,S.ZDID,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.SHI)AS SHI," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAN) AS XIAN," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAOQU)AS XQ," +
					"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
					"S.ZDNAME,S.ZGQSSJ,S.ZGJSSJ,Z.QSDBDL,S.DANJIA,S.MONEY,S.QXSHZT,S.CWSHZT,S.SJESHZT,S.ZGYF " +
					" FROM ZHANDIAN Z,ZGSJ S WHERE Z.ID=S.ZDID AND S.QXSHZT='1'AND S.SJSHZT='1' and s.cwshzt<>'1' "+whereStr+" " +
					 "  AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))) ";
            System.out.println("����վ��������б��ѯ��"+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				zgsj formbean=new zgsj();
				formbean.setId(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setZdid(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setShi(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setQuxian(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setXiangzhen(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setZdlx(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setZdname(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setZfqssj(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setZfjssj(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setSdb(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setDanjia(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setMoney(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setQxshzt(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setCwshzt(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setSjeshzt(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setZgyf(rs.getString(16)!=null?rs.getString(16):"");
				list.add(formbean);
				}
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	
	//�ݹ����� �м�2��� ͨ��
	 public synchronized int sjeshtg(String id,String loginName){ 
		 int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			//sql.append("update CBZDXX c set c.shijxf='0',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
			sql.append(" UPDATE ZGSJ S SET S.SJESHZT = '1', SJESHR = '"+loginName+"', S.SJESHSJ =sysdate  WHERE S.ID IN ("+id+")");
			
			DataBase db = new DataBase();
			System.out.println("�ݹ������м�2���ͨ����"+sql.toString());
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
		//�ݹ����� �м�2��� ��ͨ��
	 public synchronized int sjeshbtg(String id,String loginName){ 
		 int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			//sql.append("update CBZDXX c set c.shijxf='0',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
			sql.append(" UPDATE ZGSJ S SET S.SJESHZT = '2', SJESHR = '"+loginName+"', S.SJESHSJ =sysdate  WHERE S.ID IN ("+id+")");
			
			DataBase db = new DataBase();
			System.out.println("�ݹ������м�2��˲�ͨ����"+sql.toString());
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
		//�ݹ����� �м�2���   ȡ�����
	 public synchronized int sjeshqxsh(String id,String loginName){ 
		 int msg=0;
	    	CTime ctime = new CTime();
			String inserttime = ctime.formatWholeDate(new Date());
			StringBuffer sql = new StringBuffer();
			//sql.append("update CBZDXX c set c.shijxf='0',c.shijxfr='"+loginName+"',c.shijxfsj=sysdate where  c.sjxf='1' and c.wjid in ("+id+")");
			sql.append(" UPDATE ZGSJ S SET S.SJESHZT = '0', SJESHR = '"+loginName+"', S.SJESHSJ =sysdate  WHERE S.ID IN ("+id+")");
			
			DataBase db = new DataBase();
			System.out.println("�ݹ������м�2���ȡ����ˣ�"+sql.toString());
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
	 
	// �ݹ�������˲�ѯ
		public synchronized List<zgsj> getCaiWu(String wherestr, String loginId) {
			List<zgsj> ls = new ArrayList();
			String sql = "";
			DataBase db = new DataBase();
			ResultSet rs = null;
			try{
				sql = "SELECT  s.id,s.zdid,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.SHI)AS SHI," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAN) AS XIAN," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAOQU)AS XQ," +
					"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
					"S.ZDNAME,S.ZGQSSJ,S.ZGJSSJ,Z.QSDBDL,S.DANJIA,S.ZGYF,S.ZGDL,S.DLTZ,S.ZZZGDL,S.MONEY,S.CWSHZT,S.SJSHZT  FROM ZHANDIAN Z,ZGSJ S " +
					"WHERE Z.ID=S.ZDID "+wherestr+" AND s.qxshzt='1' AND s.sjshzt='1' AND s.sjeshzt='1' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
				System.out.println("�ݹ���������б��ѯ��" + sql.toString());
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					zgsj z = new zgsj();
					z.setId(rs.getString(1) != null ? rs.getString(1) : "");
					z.setZdid(rs.getString(2) != null ? rs.getString(2) : "");
					z.setShi(rs.getString(3) != null ? rs.getString(3) : "");
					z.setQuxian(rs.getString(4) != null ? rs.getString(4) : "");
					z.setXiangzhen(rs.getString(5) != null ? rs.getString(5): "");
					z.setZdlx(rs.getString(6)!=null?rs.getString(6):"");
					z.setZdname(rs.getString(7)!=null?rs.getString(7):"");
					Date d1= rs.getDate(8)!=null?rs.getDate(8):null;
					z.setZfqssj(d1!=null?d1.toString():"");
					Date d2 = rs.getDate(9)!=null?rs.getDate(9):null;
					z.setZfjssj(d2!=null?d1.toString():"");
					z.setSdb(rs.getString(10)!=null?rs.getString(10):"");
					z.setDanjia(rs.getString(11)!=null?rs.getString(11):"");
					z.setZgyf(rs.getString(12)!=null?rs.getString(12):"");
					z.setZgdl(rs.getString(13)!=null?rs.getString(13):"");
					z.setDltz(rs.getString(14)!=null?rs.getString(14):"");
					z.setZzzgdl(rs.getString(15)!=null?rs.getString(15):"");
					z.setMoney(rs.getString(16)!=null?rs.getString(16):"");
					z.setCwshzt(rs.getString(17)!=null?rs.getString(17):"");
					z.setSjshzt(rs.getString(18)!=null?rs.getString(18):"");
					ls.add(z);
				}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
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
			return ls;
		}
		//�ݹ��˹���˲�ѯ
		public synchronized List<zgsj> getRengon(String wherestr, String loginId) {
			List<zgsj> ls = new ArrayList();
			String sql = "";
			DataBase db = new DataBase();
			ResultSet rs = null;
			try{
				sql = "SELECT  s.id,s.zdid,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.SHI)AS SHI," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAN) AS XIAN," +
					"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAOQU)AS XQ," +
					"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
					"S.ZDNAME,S.ZGQSSJ,S.ZGJSSJ,Z.QSDBDL,S.DANJIA,S.ZGYF,S.MONEY,S.QXSHZT,S.SJSHZT  FROM ZHANDIAN Z,ZGSJ S " +
					"WHERE Z.ID=S.ZDID "+wherestr+" AND S.cwshzt<>'1'  AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
				System.out.println("�ݹ��˹�����б��ѯ��" + sql.toString());
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					zgsj z = new zgsj();
					z.setId(rs.getString(1) != null ? rs.getString(1) : "");
					z.setZdid(rs.getString(2) != null ? rs.getString(2) : "");
					z.setShi(rs.getString(3) != null ? rs.getString(3) : "");
					z.setQuxian(rs.getString(4) != null ? rs.getString(4) : "");
					z.setXiangzhen(rs.getString(5) != null ? rs.getString(5): "");
					z.setZdlx(rs.getString(6)!=null?rs.getString(6):"");
					z.setZdname(rs.getString(7)!=null?rs.getString(7):"");
					Date d1= rs.getDate(8)!=null?rs.getDate(8):null;
					z.setZfqssj(d1!=null?d1.toString():"");
					Date d2 = rs.getDate(9)!=null?rs.getDate(9):null;
					z.setZfjssj(d2!=null?d2.toString():"");
					z.setSdb(rs.getString(10)!=null?rs.getString(10):"");
					z.setDanjia(rs.getString(11)!=null?rs.getString(11):"");
					z.setZgyf(rs.getString(12)!=null?rs.getString(12):"");
					z.setMoney(rs.getString(13)!=null?rs.getString(13):"");
					z.setQxshzt(rs.getString("qxshzt")!=null?rs.getString("qxshzt"):"");
					z.setSjshzt(rs.getString(15)!=null?rs.getString(15):"");
					ls.add(z);
				}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
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
			return ls;
		}
		//�����ݹ����ͨ��
		public synchronized String zgcwshtg(String chooseIdStr1,String loginName){
		    String msg = "���������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();

			boolean bz = false;

		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());	
		      String sql1="update zgsj s set s.cwshzt='1',s.cwshr='"+loginName+"',s.cwshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      System.out.println("���ǲ����");
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ��������ͨ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;	    	  
		      }
		      if(bz){
		    	  msg="������˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		//�����ݹ���˲�ͨ��
		public synchronized String zgcwshno(String chooseIdStr1,String loginName){
		    String msg = "���������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();
			
			boolean bz = false;

		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());
		      String sql1="update zgsj s set s.cwshzt='2',cwshr='"+loginName+"',s.cwshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ�������˲�ͨ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;	    	  
		      }
		      if(bz){
		    	  msg="������˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		//�����ݹ����ȡ��
		public synchronized String zgcwshqx(String chooseIdStr1,String loginName){
		    String msg = "���������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();
			boolean bz = false;

		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());
		      
		      String sql1="update zgsj s set s.cwshzt='0',s.cwshr='"+loginName+"',s.cwshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ��������ȡ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;	    	  
		      }
		      if(bz){
		    	  msg="������˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		//�˹��ݹ����ͨ��
		public synchronized String zgrgshtg(String chooseIdStr1,String loginName){
		    String msg = "�˹������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();

			boolean bz = false;
		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());	
		      String sql1="update zgsj s set s.qxshzt='1',s.qxshr='"+loginName+"',s.qxshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      System.out.println("--------------------------------------------    +++"+chooseIdStr1);
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ��˹����ͨ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;
		    	  System.out.println(bz);
		      }
		      if(bz){
		    	  msg="�˹���˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		//�˹��ݹ���˲�ͨ��
		public synchronized String zgrgshno(String chooseIdStr1,String loginName){
		    String msg = "�˹������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();
			
			boolean bz = false;

		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());
		      String sql1="update zgsj s set s.qxshzt='2',s.qxshr='"+loginName+"',s.qxshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ��˹���˲�ͨ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;	    	  
		      }
		      if(bz){
		    	  msg="�˹���˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		//�˹��ݹ����ȡ��
		
		public synchronized String zgrgshqx(String chooseIdStr1,String loginName){
		    String msg = "�˹������Ϣʧ�ܣ�";
		    DataBase db = new DataBase();
			boolean bz = false;

		    try {
		      db.connectDb();
		      String inserttime = CTime.formatWholeDate(new Date());
		      String sql1="update zgsj s set s.qxshzt='0',s.qxshr='"+loginName+"',s.qxshsj=to_date('"+inserttime+"','yyyy-MM-dd HH24:mi:ss') where s.id in("+chooseIdStr1+")";
		      msg = "�����Ϣʧ�ܣ�";
		      
		      if(chooseIdStr1!=""&&chooseIdStr1!=null){
		    	  System.out.println("�ݹ��˹����ȡ��:"+sql1.toString());
		    	  bz=db.update(sql1)>0;	    	  
		      }
		      if(bz){
		    	  msg="�˹���˳ɹ���";
		      }
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }

		    return msg;
		  }
		
		//�ݹ���ϸ��ѯ
		public synchronized List<zgsj>  getZgmx(String whereStr,
				String loginId) {
			List<zgsj>  list = new ArrayList<zgsj>();
			CTime ct = new CTime();
			String kjnd = ct.formatShortDate().substring(0, 4);
			String sql = "";
			String sql1="";
			String fzzdstr = "";
			DataBase db = new DataBase();
	        ResultSet rs = null;
			try {
			//	fzzdstr = getFuzeZdid(db, loginId);
				/*sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,c.shijsh,x.csms,x.yyfx,x.cszrr,c.bzpld,x.bt,x.dgpch,x.yppch,c.fksj,c.tdyy,c.shengjsh " +
						"from cbzd c,zhandian z,cbzdxx x where z.id=c.zdid and c.id=x.wjid and c.QXTJSH='1'  "+whereStr+" " +
						 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						 + loginId + "'))) ";*/
				sql1="SELECT s.id,"+
				" (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.SHI)AS SHI,"+
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAN) AS XIAN,"+
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAOQU)AS XQ,"+
				"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE ) AS STATIONTYPE,"+
				"S.ZDNAME,S.ZGQSSJ,S.ZGJSSJ,Z.QSDBDL,S.DANJIA,S.ZGYF,S.MONEY,S.QXSHZT,S.QXSHR,S.QXSHSJ,S.SJSHZT,S.SJSHR,S.SJSHSJ,S.CWSHZT,S.CWSHR,S.CWSHSJ,s.sjeshzt,s.sjeshr,sjeshsj"+
				" FROM ZHANDIAN Z,ZGSJ S WHERE Z.ID=S.ZDID "+whereStr+""+
				"AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					 + loginId + "')))";
				System.out.println("������б��ѯ��"+sql1.toString());
				db.connectDb();
				rs = db.queryAll(sql1.toString());
				//Query query = new Query();
				//list = query.query(rs);
				while(rs.next()){
					zgsj zj =new zgsj();
					zj.setId(rs.getString(1)!=null?rs.getString(1):"");
					zj.setShi(rs.getString(2)!=null?rs.getString(2):"");
					zj.setQuxian(rs.getString(3)!=null?rs.getString(3):"");
					zj.setXiangzhen(rs.getString(4)!=null?rs.getString(4):"");
					zj.setZdname(rs.getString(6)!=null?rs.getString(6):"");
					zj.setZdlx(rs.getString(5)!=null?rs.getString(5):"");
					zj.setZfqssj(rs.getString(7)!=null?rs.getString(7):"");
					zj.setZfjssj(rs.getString(8)!=null?rs.getString(8):"");
					zj.setSdb(rs.getString(9)!=null?rs.getString(9):"");
					zj.setDanjia(rs.getString(10)!=null?rs.getString(10):"");
					zj.setZgyf(rs.getString(11)!=null?rs.getString(11):"");
					zj.setMoney(rs.getString(12)!=null?rs.getString(12):"");
					zj.setQxshzt(rs.getString(13)!=null?rs.getString(13):"");
					zj.setQxshr(rs.getString(14)!=null?rs.getString(14):"");
					zj.setQxshsj(rs.getString(15)!=null?rs.getString(15):"");
					zj.setSjshzt(rs.getString(16)!=null?rs.getString(16):"");
					zj.setSjshr(rs.getString(17)!=null?rs.getString(17):"");
					zj.setSjshsj(rs.getString(18)!=null?rs.getString(18):"");
					zj.setCwshzt(rs.getString(19)!=null?rs.getString(19):"");
					zj.setCwshr(rs.getString(20)!=null?rs.getString(20):"");
					zj.setCwshsj(rs.getString(21)!=null?rs.getString(21):"");
					zj.setSjeshzt(rs.getString(22)!=null?rs.getString(22):"");
					zj.setSjeshr(rs.getString(23)!=null?rs.getString(23):"");
					zj.setSjeshsj(rs.getString(24)!=null?rs.getString(24):"");
					list.add(zj);
					}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
			//System.out.println(list.toString() + "------------------");
			return list;
		}

		//�ݹ��м���˲�ѯ
		public synchronized List<zgsj>  getZgshishenhe(String whereStr,
				String loginId) {
			List<zgsj>  list = new ArrayList<zgsj>();
			CTime ct = new CTime();
			String kjnd = ct.formatShortDate().substring(0, 4);
			//String sql = "";
			String sql1="";
			String fzzdstr = "";
			DataBase db = new DataBase();
	        ResultSet rs = null;
			try {
			//	fzzdstr = getFuzeZdid(db, loginId);
				/*sql = "select c.id,c.zdid,c.zdname,c.shi,c.xian,c.xiaoqu,c.cbsj,c.shijsh,x.csms,x.yyfx,x.cszrr,c.bzpld,x.bt,x.dgpch,x.yppch,c.fksj,c.tdyy,c.shengjsh " +
						"from cbzd c,zhandian z,cbzdxx x where z.id=c.zdid and c.id=x.wjid and c.QXTJSH='1'  "+whereStr+" " +
						 "AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
						 + loginId + "'))) ";*/
				sql1="SELECT s.id,"+
				" (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.SHI)AS SHI,"+
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAN) AS XIAN,"+
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=Z.XIAOQU)AS XQ,"+
				"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE) AS STATIONTYPE,"+
				"S.ZDNAME,to_char(S.ZGQSSJ,'yyyy-mm-dd'),to_char(S.ZGJSSJ,'yyyy-mm-dd'),Z.QSDBDL,S.DANJIA,to_char(S.ZGYF,'yyyy-mm'),S.MONEY,S.QXSHZT,S.SJSHZT,s.sjeshzt"+
				" FROM ZHANDIAN Z,ZGSJ S WHERE Z.ID=S.ZDID AND S.QXSHZT='1'  and s.cwshzt<>'1' "+whereStr+""+
				" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					 + loginId + "')))";
	            System.out.println("����վ��������б��ѯ��"+sql1.toString());
				db.connectDb();
				rs = db.queryAll(sql1.toString());
				//Query query = new Query();
				//list = query.query(rs);
				while(rs.next()){
					zgsj zj =new zgsj();
					zj.setId(rs.getString(1)!=null?rs.getString(1):"");
					zj.setShi(rs.getString(2)!=null?rs.getString(2):"");
					zj.setQuxian(rs.getString(3)!=null?rs.getString(3):"");
					zj.setXiangzhen(rs.getString(4)!=null?rs.getString(4):"");
					zj.setZdname(rs.getString(6)!=null?rs.getString(6):"");
					zj.setZdlx(rs.getString(5)!=null?rs.getString(5):"");
					zj.setZfqssj(rs.getString(7)!=null?rs.getString(7):"");
					zj.setZfjssj(rs.getString(8)!=null?rs.getString(8):"");
					zj.setSdb(rs.getString(9)!=null?rs.getString(9):"");
					zj.setDanjia(rs.getString(10)!=null?rs.getString(10):"");
					zj.setZgyf(rs.getString(11)!=null?rs.getString(11):"");
					zj.setMoney(rs.getString(12)!=null?rs.getString(12):"");
					zj.setQxshzt(rs.getString(13)!=null?rs.getString(13):"");
					zj.setSjshzt(rs.getString(14)!=null?rs.getString(14):"");
					zj.setSjeshzt(rs.getString(15)!=null?rs.getString(15):"");
					list.add(zj);
					}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
			//System.out.println(list.toString() + "------------------");
			return list;
		}
		//�ݹ������м����ͨ��
		
		public synchronized String zgsjshenhetg(String chooseIdStr1,String loginName) {
			String msg = "�м������Ϣʧ�ܣ�";
			DataBase db = new DataBase();
			boolean bz = false;
			 try {
			      db.connectDb();

			      String sql1="update zgsj s set s.sjshzt='1',s.sjshr='"+loginName+"',s.sjshsj=sysdate  where s.id in("+chooseIdStr1+")";
			      msg = "�����Ϣʧ�ܣ�";
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("�ݹ��м����ͨ��:"+sql1.toString());
			    	  bz = db.update(sql1)>0;
			    	 
			      }

			      if(bz){
				     msg = "�м����ͨ����";
				  }
			    } catch (DbException de) {
			      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      de.printStackTrace();
			    }
			    finally {
			      try {

			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
			    }

			    return msg;
			  }
		//�ݹ������м���˶ͨ��
		public synchronized String zgsjshenheno(String chooseIdStr1,String loginName) {
			String msg = "�м������Ϣʧ�ܣ�";
			DataBase db = new DataBase();
			boolean bz = false;
			 try {
			      db.connectDb();

			      String sql1="update zgsj s set s.sjshzt='2',s.sjshr='"+loginName+"',s.sjshsj=sysdate where s.id in("+chooseIdStr1+")";
			      msg = "�����Ϣʧ�ܣ�";
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("�ݹ��м���˲�ͨ��:"+sql1.toString());
			    	  bz = db.update(sql1)>0;
			      }

			      if(bz){
				     msg = "�м����ͨ����";
				  }
			    } catch (DbException de) {
			      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      de.printStackTrace();
			    }
			    finally {
			      try {

			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
			    }

			    return msg;
			  }

		//�ݹ������м�ȡ�����
		public synchronized String zgsjqxsh(String chooseIdStr1,String loginName) {
			String msg = "�м������Ϣʧ�ܣ�";
			DataBase db = new DataBase();
			boolean bz = false;
			 try {
			      db.connectDb();

			      String sql1="update zgsj s set s.sjshzt='0',s.sjshr='"+loginName+"',s.sjshsj=sysdate where s.id in("+chooseIdStr1+")";
			      msg = "�����Ϣʧ�ܣ�";
			      if(chooseIdStr1!=""&&chooseIdStr1!=null){
			    	  System.out.println("�ݹ��м����ȡ�����:"+sql1.toString());
			    	  bz = db.update(sql1)>0;
			      }

			      if(bz){
				     msg = "�м�ȡ�����δͨ������";
				  }
			    } catch (DbException de) {
			      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      de.printStackTrace();
			    }
			    finally {
			      try {

			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
			    }

			    return msg;
			  }
		
//-----------------------------------------------------------------------		
		public String insertDate(ArrayList<zgsj> list,String uuid,String lrren){		
			String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
			DataBase db = new DataBase();
			ResultSet rs = null;
			//String month = bean.getZfqssj();
			
			String ret="1";
			StringBuffer sql=new StringBuffer();
			System.out.println(list.size()+"");
			for(zgsj bean:list){
				sql.append("insert into zgsj(SHI,XIAN,XIANGZHEN,ZDID,DBID,BZW,ZDNAME,ZGQSSJ,ZGJSSJ,ZGYF,DANJIA,MONEY,LRR,LRSJ,QXSHZT,SJSHZT,SJESHZT,CWSHZT,UUID) values('"+bean.getShi()+"','"+bean.getQuxian()+"','"+bean.getXiangzhen()+"','"
					+bean.getZdid()+"','"+bean.getDbid()+"','"+bean.getBzw()+"','"+bean.getZdname()+"',to_date('"+bean.getZfqssj()+"','YYYY-MM-DD'),to_date('"+bean.getZfjssj()+"','YYYY-MM-DD'),'"+bean.getZgyf()+"','"+bean.getDanjia()+"','"+
					bean.getMoney()+"','"+lrren+"',sysdate,'0','0','0','0','"+uuid+"')��");
				
			}
			//System.out.println("�����أ�"+sql);
			try {
				db.connectDb();
				 db.updateBatchDr(sql.toString().split("��"));
				ret="2";
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
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
			return ret;
		}
		public String insertDate2(ArrayList<zgsj> list,String uuid,String lrren){		
			String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
			DataBase db = new DataBase();
			ResultSet rs = null;
			//String month = bean.getZfqssj();
			
			String ret="1";
			StringBuffer sql=new StringBuffer();
			System.out.println(list.size()+"");
			for(zgsj bean:list){
				sql.append("insert into zgsj(SHI,XIAN,XIANGZHEN,ZDID,DBID,BZW,ZDNAME,ZGQSSJ,ZGJSSJ,ZGYF,DANJIA,MONEY,LRR,LRSJ,QXSHZT,SJSHZT,SJESHZT,CWSHZT,UUID,QXSHR,QXSHSJ,SJSHR,SJSHSJ,SJESHR,SJESHSJ) values('"+bean.getShi()+"','"+bean.getQuxian()+"','"+bean.getXiangzhen()+"','"
					+bean.getZdid()+"','"+bean.getDbid()+"','"+bean.getBzw()+"','"+bean.getZdname()+"',to_date('"+bean.getZfqssj()+"','YYYY-MM-DD'),to_date('"+bean.getZfjssj()+"','YYYY-MM-DD'),'"+bean.getZgyf()+"','"+bean.getDanjia()+"','"+
					bean.getMoney()+"','"+lrren+"',sysdate,'1','1','1','0','"+uuid+"','"+bean.getQxshr()+"',to_date('"+bean.getQxshsj()+"','yyyy-mm-dd hh24:mi:ss'),'"+bean.getSjshr()+"',to_date('"+bean.getSjshsj()+"','yyyy-mm-dd hh24:mi:ss'),'"+bean.getSjeshr()+"',to_date('"+bean.getSjeshsj()+"','yyyy-mm-dd hh24:mi:ss'))��");
				
			}
			System.out.println("����ͨ�����²��룡"+sql);
			try {
				db.connectDb();
				 db.updateBatchDr(sql.toString().split("��"));
				ret="2";
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
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
			return ret;
		}
		
		public synchronized  ArrayList<zgsj>  getZgxx(String whereStr) {
			ArrayList<zgsj>  list = new ArrayList<zgsj>();
			DataBase db = new DataBase();
	        ResultSet rs = null;
	        String sql ="SELECT DISTINCT (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)," +
	        		"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN)," +
	        		"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU),ZD.JZNAME,ZD.ID,ZD.DIANFEI,DB.DBID,DB.CSSYTIME,AMM.LASTDATETIME,AMM.THISDATETIME, AMM.ACTUALPAY,ASJ.Zgqssj,ASJ.Zgjssj,ASJ.money,ZD.EDHDL,ZD.Qsdbdl," +
	        		"(SELECT T.NAME  FROM INDEXS T WHERE T.CODE = ZD.STATIONTYPE AND T.TYPE = 'stationtype'),ASJ.qxshr, ASJ.qxshsj, ASJ.sjshr, ASJ.sjshsj, ASJ.sjeshr, ASJ.sjeshsj " +
	        		"FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME, DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME FROM DIANDUVIEW DD,DIANFEIVIEW DF,(SELECT MAX(AMM.THISDATETIME) AS DATETIME, AMM.AMMETERID_FK " +
	        		"FROM DIANDUVIEW AMM, DIANFEIVIEW DF WHERE 1 = 1 and DF.MANUALAUDITSTATUS = '2'AND AMM.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK  GROUP BY AMM.AMMETERID_FK) B " +
	        		"WHERE DD.AMMETERID_FK = B.AMMETERID_FK AND DD.THISDATETIME = B.DATETIME AND DD.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK and DF.MANUALAUDITSTATUS = '2') AMM," +
	        		"( select zg.zgqssj,zg.zgjssj,zg.money,zg.dbid,zg.qxshzt,zg.sjshzt,zg.sjeshzt,zg.cwshzt,zg.qxshr, zg.qxshsj, zg.sjshr, zg.sjshsj, zg.sjeshr, zg.sjeshsj from zhandian zd,zgsj zg,(select max(zg.zgjssj) as jsdate,zg.dbid" +
	        		" from zhandian zd,zgsj zg where zd.id=zg.zdid and zg.cwshzt='1' group by zg.dbid) SJ" +
	        		" where zd.id=zg.zdid and zg.dbid=sj.dbid and zg.zgjssj=sj.jsdate and zg.cwshzt='1')ASJ,DIANBIAO DB,ZHANDIAN ZD" +
	        		" WHERE DB.DBID = AMM.AMMETERID_FK(+)  AND ZD.ID = DB.ZDID and ASJ.dbid(+)=db.dbid AND DB.DBYT = 'dbyt01' and db.DBQYZT = '1' AND (DB.DFZFLX = 'dfzflx01' OR DB.DFZFLX = 'dfzflx03') AND ZD.QYZT = '1' AND ZD.SHSIGN = '1'"+whereStr;
			try {
	            System.out.println("�����ݹ���Ϣ��ѯ��"+sql.toString());
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					zgsj zj =new zgsj();
					zj.setShi(rs.getString(1) != null ? rs.getString(1) : "");
					zj.setQuxian(rs.getString(2) != null ? rs.getString(2) : "");
					zj.setXiangzhen(rs.getString(3) != null ? rs.getString(3) : "");
					zj.setZdname(rs.getString(4) != null? rs.getString(4) : "");
					zj.setZdid(rs.getString(5) != null? rs.getString(5) : "");
					zj.setDianfei(rs.getString(6) != null? rs.getString(6) : "");
					zj.setDbid(rs.getString(7) != null? rs.getString(7) : "");
					zj.setCssytime(rs.getString(8) != null? rs.getString(8) : "");
					zj.setLasttime(rs.getString(9) != null? rs.getString(9) : "");
					zj.setThistime(rs.getString(10) != null? rs.getString(10) : "");
					zj.setActualpay(rs.getString(11) != null? rs.getString(11) : "");
					zj.setZfqssj(rs.getString(12) != null? rs.getString(12) : "");
					zj.setZfjssj(rs.getString(13) != null? rs.getString(13) : "");
					zj.setMoney(rs.getString(14) != null? rs.getString(14) : "");
					zj.setEdhdl(rs.getString(15) != null? rs.getString(15) : "");
					zj.setSdb(rs.getString(16) != null? rs.getString(16) : "");
					zj.setZdlx(rs.getString(17) != null? rs.getString(17) : "");
					zj.setQxshr(rs.getString(18) != null? rs.getString(18) : "");
					zj.setQxshsj(rs.getString(19) != null? rs.getString(19) : "");
					zj.setSjshr(rs.getString(20) != null? rs.getString(20) : "");
					zj.setSjshsj(rs.getString(21) != null? rs.getString(21) : "");
					zj.setSjeshr(rs.getString(22) != null? rs.getString(22) : "");
					zj.setSjeshsj(rs.getString(23) != null? rs.getString(23) : "");
					list.add(zj);
				}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
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
			return list;
		}
		public synchronized  ArrayList<zgsj>  getZgxx1(String whereStr) {
			ArrayList<zgsj>  list = new ArrayList<zgsj>();
			DataBase db = new DataBase();
	        ResultSet rs = null;
	        String sql ="SELECT DISTINCT (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)," +
	        		"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN)," +
	        		"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU),ZD.JZNAME,ZD.ID,ZD.DIANFEI,DB.DBID,DB.CSSYTIME,AMM.LASTDATETIME,AMM.THISDATETIME, AMM.ACTUALPAY,ASJ.Zgqssj,ASJ.Zgjssj,ASJ.money,ZD.EDHDL,ZD.Qsdbdl," +
	        		"(SELECT T.NAME  FROM INDEXS T WHERE T.CODE = ZD.STATIONTYPE AND T.TYPE = 'stationtype'),zg.qxshr, zg.qxshsj, zg.sjshr, zg.sjshsj, zg.sjeshr, zg.sjeshsj " +
	        		"FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME, DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME FROM DIANDUVIEW DD,DIANFEIVIEW DF,(SELECT MAX(AMM.THISDATETIME) AS DATETIME, AMM.AMMETERID_FK " +
	        		"FROM DIANDUVIEW AMM, DIANFEIVIEW DF WHERE 1 = 1 and DF.MANUALAUDITSTATUS = '2'AND AMM.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK  GROUP BY AMM.AMMETERID_FK) B " +
	        		"WHERE DD.AMMETERID_FK = B.AMMETERID_FK AND DD.THISDATETIME = B.DATETIME AND DD.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK and DF.MANUALAUDITSTATUS = '2') AMM," +
	        		"( select zg.zgqssj,zg.zgjssj,zg.money,zg.dbid,zg.qxshzt,zg.sjshzt,zg.sjeshzt,zg.cwshzt from zhandian zd,zgsj zg,(select max(zg.zgjssj) as jsdate,zg.dbid" +
	        		" from zhandian zd,zgsj zg where zd.id=zg.zdid and zg.cwshzt='1' group by zg.dbid) SJ" +
	        		" where zd.id=zg.zdid and zg.dbid=sj.dbid and zg.zgjssj=sj.jsdate and zg.cwshzt='1')ASJ,DIANBIAO DB,ZHANDIAN ZD,ZGSJ ZG" +
	        		" WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = ZG.ZDID AND DB.DBID = ZG.DBID(+) AND ZD.ID = DB.ZDID and ASJ.dbid(+)=db.dbid AND DB.DBYT = 'dbyt01' and db.DBQYZT = '1' AND (DB.DFZFLX = 'dfzflx01' OR DB.DFZFLX = 'dfzflx03') AND ZD.QYZT = '1' AND ZD.SHSIGN = '1'"+whereStr;
			try {
	            System.out.println("�����ݹ�����ͨ����Ϣ��ѯ��"+sql.toString());
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					zgsj zj =new zgsj();
					zj.setShi(rs.getString(1) != null ? rs.getString(1) : "");
					zj.setQuxian(rs.getString(2) != null ? rs.getString(2) : "");
					zj.setXiangzhen(rs.getString(3) != null ? rs.getString(3) : "");
					zj.setZdname(rs.getString(4) != null? rs.getString(4) : "");
					zj.setZdid(rs.getString(5) != null? rs.getString(5) : "");
					zj.setDianfei(rs.getString(6) != null? rs.getString(6) : "");
					zj.setDbid(rs.getString(7) != null? rs.getString(7) : "");
					zj.setCssytime(rs.getString(8) != null? rs.getString(8) : "");
					zj.setLasttime(rs.getString(9) != null? rs.getString(9) : "");
					zj.setThistime(rs.getString(10) != null? rs.getString(10) : "");
					zj.setActualpay(rs.getString(11) != null? rs.getString(11) : "");
					zj.setZfqssj(rs.getString(12) != null? rs.getString(12) : "");
					zj.setZfjssj(rs.getString(13) != null? rs.getString(13) : "");
					zj.setMoney(rs.getString(14) != null? rs.getString(14) : "");
					zj.setEdhdl(rs.getString(15) != null? rs.getString(15) : "");
					zj.setSdb(rs.getString(16) != null? rs.getString(16) : "");
					zj.setZdlx(rs.getString(17) != null? rs.getString(17) : "");
					zj.setQxshr(rs.getString(18) != null? rs.getString(18) : "");
					zj.setQxshsj(rs.getString(19) != null? rs.getString(19) : "");
					zj.setSjshr(rs.getString(20) != null? rs.getString(20) : "");
					zj.setSjshsj(rs.getString(21) != null? rs.getString(21) : "");
					zj.setSjeshr(rs.getString(22) != null? rs.getString(22) : "");
					zj.setSjeshsj(rs.getString(23) != null? rs.getString(23) : "");
					list.add(zj);
				}
			}catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
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
			return list;
		}
		public List<zgsj> getRepeat(String loginId,String shi,String xian,String zgyf){
			List<zgsj> list=new ArrayList<zgsj>();
			DataBase db = new DataBase();
			ResultSet rs = null;
			String sql="SELECT DISTINCT Z.ZGYF FROM ZGSJ Z, ZHANDIAN ZZ " +
					"WHERE Z.ZDID = ZZ.ID AND ZZ.SHI='"+shi+"' AND ZZ.XIAN='"+xian+"' AND Z.ZGYF='"+zgyf+"'AND Z.QXSHZT='1' AND ((zz.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
			try {
				db.connectDb();
				System.out.println("�ݹ��ظ��жϣ�"+sql);
				rs = db.queryAll(sql);
				while(rs.next()){
					zgsj bean=new zgsj();
				    bean.setZgyf(rs.getString(1).trim());
				list.add(bean);	
				}
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
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
			System.out.println("zzzzzz"+list.size());
			return list;
		}
	
		public synchronized void Delete(String shi,String xian,String zgyf){//δ����ظ�ɾ��
			String sql="delete from zgsj z where z.shi='"+shi+"' and z.xian='"+xian+"' and z.zgyf='"+zgyf+"'  AND Z.QXSHZT='0'";
			DataBase db = new DataBase();
			System.out.println("�����ݹ��ظ�ɾ����"+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {
					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
		 }
		public synchronized void Delete1(String shi,String xian,String zgyf,String zdid,String dbid){//����δ����ظ�ɾ��
			String sql="delete from zgsj z where z.shi='"+shi+"' and z.xian='"+xian+"' and z.zgyf='"+zgyf+"' and z.zdid='"+zdid+"' and z.dbid='"+dbid+"'";
			DataBase db = new DataBase();
			System.out.println("���ز����ݹ��ظ�ɾ����"+sql.toString());
			try {	
				db.connectDb();
				db.update(sql.toString());
				db.commit();
				
			} catch (DbException e) {
				e.printStackTrace();
			}finally {
				try {
					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
		 }
}
