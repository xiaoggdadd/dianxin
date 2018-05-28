package com.ptac.app.basisquery.liucheng.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.basisquery.liucheng.bean.LiuCheng;

public class LiuChengDaoImpl implements LiuChengDao {

	Connection conn = null;
	Statement sta = null;
	@Override
	public LiuCheng getLiuChengInfo(String whereStr) {
		
		LiuCheng bean = new LiuCheng();
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT E.MANUALAUDITSTATUS,E.MANUALAUDITDATETIME,E.MANUALAUDITNAME,E.COUNTYAUDITSTATUS,E.COUNTYAUDITTIME," 
	    		 + "E.COUNTYAUDITNAME,E.CITYAUDIT,E.CITYAUDITTIME,E.CITYAUDITPENSONNEL,E.CITYZRAUDITSTATUS,E.CITYZRAUDITTIME,"
	    		 + "E.CITYZRAUDITNAME,E.FINANCIALDATETIME,E.FINANCIALOPERATOR,E.AUTOAUDITSTATUS,E.ENTRYPENSONNEL,E.ENTRYTIME,E.DAYINREN,E.DAYINTIME"
	    		 + " FROM ELECTRICFEES E "+whereStr);
	    		
	     //" WHERE A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK "+ whereStr);		    
		 //+ whereStr+"  AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");  , AMMETERDEGREES A
	    
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {
	      System.out.println("���̲�ѯ:"+sql);
	      db.connectDb();	 
	      conn = db.getConnection();
	      sta = conn.createStatement();
	      rs = sta.executeQuery(sql.toString());
	      while(rs.next()){
	    	  
	    	  String rgzt = rs.getString("MANUALAUDITSTATUS")!=null?rs.getString("MANUALAUDITSTATUS"):"";//�˹����״̬
	    	  String rgtime = rs.getString("MANUALAUDITDATETIME")!=null?rs.getString("MANUALAUDITDATETIME"):"";//�˹����ʱ��
	    	  String rgpeople = rs.getString("MANUALAUDITNAME")!=null?rs.getString("MANUALAUDITNAME"):"";//�˹������
	    	  String qxzt = rs.getString("COUNTYAUDITSTATUS")!=null?rs.getString("COUNTYAUDITSTATUS"):"";//�������״̬
	    	  String qxtime = rs.getString("COUNTYAUDITTIME")!=null?rs.getString("COUNTYAUDITTIME"):"";//�������ʱ��
	    	  String qxpeople = rs.getString("COUNTYAUDITNAME")!=null?rs.getString("COUNTYAUDITNAME"):"";//���������
	    	  String sjzt = rs.getString("CITYAUDIT")!=null?rs.getString("CITYAUDIT"):"";//�м����״̬
	    	  String sjtime = rs.getString("CITYAUDITTIME")!=null?rs.getString("CITYAUDITTIME"):"";//�м����ʱ��
	    	  String sjpeople = rs.getString("CITYAUDITPENSONNEL")!=null?rs.getString("CITYAUDITPENSONNEL"):"";//�м������
	    	  String szrzt = rs.getString("CITYZRAUDITSTATUS")!=null?rs.getString("CITYZRAUDITSTATUS"):"";//���������״̬
	    	  String szrtime = rs.getString("CITYZRAUDITTIME")!=null?rs.getString("CITYZRAUDITTIME"):"";//���������ʱ��
	    	  String szrpeople = rs.getString("CITYZRAUDITNAME")!=null?rs.getString("CITYZRAUDITNAME"):"";//�����������
	    	  String cwtime = rs.getString("FINANCIALDATETIME")!=null?rs.getString("FINANCIALDATETIME"):"";//�������ʱ��
	    	  String cwpeople = rs.getString("FINANCIALOPERATOR")!=null?rs.getString("FINANCIALOPERATOR"):"";//���������
	    	  String zdzt  = rs.getString("AUTOAUDITSTATUS")!=null?rs.getString("AUTOAUDITSTATUS"):"";//�Զ����״̬
	    	  String lrry  = rs.getString("ENTRYPENSONNEL")!=null?rs.getString("ENTRYPENSONNEL"):"";//¼����
	    	  String lrsj  = rs.getString("ENTRYTIME")!=null?rs.getString("ENTRYTIME"):"";//¼��ʱ��
	    	  String dyr  = rs.getString("DAYINREN")!=null?rs.getString("DAYINREN"):"";//��ӡ��
	    	  String dysj  = rs.getString("DAYINTIME")!=null?rs.getString("DAYINTIME"):"";//��ӡʱ��
	    	  
	    	  bean.setRgtime(rgtime);
	    	  bean.setRgpeople(rgpeople);
	    	  bean.setQxtime(qxtime);
	    	  bean.setQxpeople(qxpeople);
	    	  bean.setSjtime(sjtime);
	    	  bean.setSjpeople(sjpeople);
	    	  bean.setSzrtime(szrtime);
	    	  bean.setSzrpeople(szrpeople);
	    	  bean.setCwtime(cwtime);
	    	  bean.setCwpeople(cwpeople);
	    	  bean.setLrry(lrry);
	    	  bean.setLrsj(lrsj);
	    	  bean.setDyr(dyr);
	    	  bean.setDysj(dysj);
	    	  
	    	  	//------�Զ����״̬------
	    	  	if("0".equals(zdzt)){
		  		    bean.setZdzt("δ���");
		  		}else if("1".equals(zdzt)){
		  			bean.setZdzt("ͨ��");
		  		}
	    	  
		    	//------�˹����״̬  �������״̬------
		  		if("0".equals(rgzt)){
		  		    bean.setRgzt("δ���");
		  		    bean.setCwzt("δ���");
		  		}else if("1".equals(rgzt)){
		  			bean.setRgzt("ͨ��");
		  			bean.setCwzt("δ���");
		  		}else if("2".equals(rgzt)){
		  			bean.setRgzt("ͨ��");
		  			bean.setCwzt("ͨ��");
		  		}else if("-1".equals(rgzt)){
		  			bean.setRgzt("ͨ��");
		  			bean.setCwzt("��ͨ��");
		  		}else if("-2".equals(rgzt)){
		  			bean.setRgzt("��ͨ��");
		  			bean.setCwzt("δ���");
		  		}
       		
	  		    //------�����������״̬------
	       		if("0".equals(qxzt)){
	       			bean.setQxzt("δ���");
	       		}else if("1".equals(qxzt)){
	       			bean.setQxzt("ͨ��");
	       		}else if("2".equals(qxzt)){
	       			bean.setQxzt("��ͨ��");
	       		}
	       		
	       		//------�м����״̬------
	       		if("0".equals(sjzt)){
	       			bean.setSjzt("δ���");
	       		}else if("1".equals(sjzt)){
	       			bean.setSjzt("ͨ��");
	       		}else if("-2".equals(sjzt)){
	       			bean.setSjzt("��ͨ��");
	       		}
	       		
	       		//------���������״̬------
	       		if("0".equals(szrzt)){
	       			bean.setSzrzt("δ���");
	       		}else if("1".equals(szrzt)){
	       			bean.setSzrzt("ͨ��");
	       		}else if("2".equals(szrzt)){
	       			bean.setSzrzt("��ͨ��");
	       		}
		    	  
	      }
	    } catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs,sta,conn);
	    }
	    return bean;
	  }

}
