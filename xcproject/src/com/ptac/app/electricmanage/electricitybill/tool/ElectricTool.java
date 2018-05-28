package com.ptac.app.electricmanage.electricitybill.tool;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanageUtil.Format;
/**
 * @see ��������ѹ�����
 * @author WangYiXiao 2014-1-20
 * @update WangYiXiao 2014-3-21 �޸�chayue()������ adjustFeeAndElec1();����adjustFeeAndElec2()
 */
public class ElectricTool {
    
	/**��ѵ��ж��ظ�
	 * @param thisdegree  	(String)	���ε�����
	 * @param thisdatetime 	(String) 	���γ���ʱ�� 
	 * @param lastdegree 	(String) 	�ϴε�����
	 * @param lastdatetime 	(String)	�ϴγ���ʱ��
	 * @param ammeteridFk 	(String)	���id
	 * @param accountmonth 	(String)	�����·�
	 * @return	(boolean)				true �ظ�	false ���ظ�
	 * @see �ж����ݿ����Ƿ�����������(�ظ�)�����ݿ������ظ����ݷ���true�����򷵻�false
	 * @author WangYiXiao 2014-1-20
	 */
	public synchronized boolean checkRepeat(String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//�Ƿ��ظ���־λ
		String sql = "SELECT DD.AMMETERDEGREEID FROM DIANDUVIEW DD,DIANFEIVIEW DF WHERE DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK AND LASTDEGREE='" + lastdegree
		+ "'AND TO_CHAR(LASTDATETIME,'yyyy-mm-dd')='" + lastdatetime
		+ "' AND AMMETERID_FK='" + ammeteridFk
		+ "'AND TO_CHAR(ACCOUNTMONTH,'yyyy-mm')='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("�ظ����"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//�ͷ���Դ
				try {
					if(rs!=null){
					rs.close();
					}
					try {
						if(db!=null){
						db.close();
						}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	
	/** Ԥ�����ж��ظ�
	 * @param thisdegree  	(String)	���ε�����
	 * @param thisdatetime 	(String) 	���γ���ʱ�� 
	 * @param lastdegree 	(String) 	�ϴε�����
	 * @param lastdatetime 	(String)	�ϴγ���ʱ��
	 * @param ammeteridFk 	(String)	���id
	 * @param accountmonth 	(String)	�����·�
	 * @return	(boolean)				true �ظ�	false ���ظ�
	 * @see �ж����ݿ����Ƿ�����������(�ظ�)�����ݿ������ظ����ݷ���true�����򷵻�false
	 * @author lijing
	 */
	public synchronized boolean checkRepeat1(String thisdegree,String thisdatetime,String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//�Ƿ��ظ���־λ
		String sql = "SELECT y.id FROM yufufeiview y WHERE STOPDEGREE='" + thisdegree
		+ "' AND TO_CHAR(ENDDATE,'yyyy-mm-dd')='" + thisdatetime
		+ "' AND STARTDEGREE='" + lastdegree
		+ "'AND TO_CHAR(STARTDATE,'yyyy-mm-dd')='" + lastdatetime
		+ "' AND PREPAYMENT_AMMETERID='" + ammeteridFk
		+ "'AND TO_CHAR(ACCOUNTMONTH,'yyyy-mm')='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("�ظ����"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//�ͷ���Դ
				try {
					if(rs!=null){
						rs.close();
						}
					try {
						if(db!=null){
							db.close();
							}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	/**  ��ͬ���ж��ظ�
	 * @author WangYiXiao 2015-1-14
	 */
	public synchronized boolean checkRepeat2(String ammeteridFk,String accountmonth,String thismonth,String endmonth){
		DataBase db = new DataBase();
		boolean flag = false;//�Ƿ��ظ���־λ
		String sql = "select * from yufufeiview t  where  t.prepayment_ammeterid='"
			+ ammeteridFk
			+ "' and to_char(t.startmonth,'yyyy-mm') ='"
			+ thismonth
			+ "' and to_char(t.endmonth,'yyyy-mm') ='"
			+ endmonth
			+ "' and to_char(t.accountmonth,'yyyy-mm')='"
			+ accountmonth
			+ "'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("�ظ����"+sql);
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//�ͷ���Դ
				try {
					if(rs!=null){
					rs.close();
					}
					try {
						if(db!=null){
						db.close();
						}
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	
	//�õ���������check
	
	/**
	 * @param  elecinfo	(ElectricityInfo)
	 * @return (String[]) �Զ����״̬���Զ��������
	 * @see																										
	 * 	C1 ���õ���������check
	 *	�鿴�õ��������Ƿ����0���գ�������0.0,0.00 ����Ϊ�յ�ʱ��עmemo(����) ����Ϊ�գ�
	 *	���ǵ��õ���������Ϊ�յ�ʱ�� memo��������Ϣ��
	 *	�����Զ����״̬��autoauditstatusΪ0 ,
	 * 	�Զ����������autoauditdescription = "�õ����û��˵����";
	 * 	autoauditstatus = autoaudit[0]�Զ����״̬
	 * 	autoauditdescription  = autoaudit[1] �Զ��������
	 * @author WangYiXiao 2014-1-20
	 */
	public synchronized String[] checkFloatDegree(ElectricityInfo elecinfo){
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		String floatdegree = elecinfo.getFloatdegree();//�õ�������
		String memo = elecinfo.getMemo();//(����)��ע
		if(null == floatdegree || "0".equals(floatdegree) || "0.0".equals(floatdegree) || "0.00".equals(floatdegree) || "".equals(floatdegree)){
		//�õ�������Ϊ�� ,��ע �ղ�������ν
		}else{
			if(null ==memo || "".equals(memo) || " ".equals(memo)){
				autoauditstatus = "0";
				autoauditdescription = "�õ����û��˵����";
			}
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	/**
	 * @param  ammeterid    (String) 	���id	
	 * @param  thisdatetime	(String) 	���γ���ʱ��
	 * @param  lastdatetime	(String) 	�ϴγ���ʱ��
	 * @param  blhdl		(String) 	���ʺĵ���
	 * @return              (String[])	 �Զ����״̬���Զ��������
	 * @see ���ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ���20%
	 * blhdl/ʱ���+1 ��ñ��ε��վ��ĵ���
	 * ���� ���ϴε��վ��ĵ����ı�ֵ��
	 * (����-�ϴ�)/�ϴ�*100%>=20%
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkDayDegree(String ammeterid,String thisdatetime,String lastdatetime,String blhdl){
		ResultSet rs = null;
		String[] autoaudit = new String[2];
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		String lastadtime = "";
		double daydegree = 0;//�ϴε��պĵ���
		try {
			dt = sdf.parse(lastdatetime);
			Calendar rightNow = Calendar.getInstance();
	         rightNow.setTime(dt);
	         rightNow.add(Calendar.DAY_OF_YEAR,-1);//ҳ�������ڼ���1����ʾ
	         Date dt1=rightNow.getTime();
	         lastadtime= sdf.format(dt1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		sql.append("SELECT CASE WHEN (AD.THISDATETIME - AD.LASTDATETIME) != 0 THEN AD.blhdl / (AD.THISDATETIME - AD.LASTDATETIME+1) ELSE   1 END DAYDEGREE" +
	    		" FROM DIANDUVIEW AD,DIANFEIVIEW DF " +
	    		"WHERE AD.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK AND DF.CITYAUDIT = '1' AND DF.CITYZRAUDITSTATUS = '1' AND TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') ='"+lastadtime+"' AND AD.AMMETERID_FK='"+ammeterid+"'");
		DataBase db = new DataBase();
		try {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			while (rs.next()){
		    	  daydegree=rs.getDouble(1); 
		    	  double daydegereerate = 0;//��ֵ
					double thisdaydegree = 0;
					long diff,day;
					diff = getQuot(lastdatetime,thisdatetime);
					day = diff+1;// ����������
					thisdaydegree = Double.parseDouble(blhdl) / day;// �����պĵ���
					daydegereerate = ((thisdaydegree - daydegree) / daydegree) * 1;// ������ϴ��պĵ�����ֵ
					if(daydegereerate >= 0.2){//>=20%
						autoauditstatus = "0";
						autoauditdescription = "���ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ���20%;";
					}	
		    }
			
		} catch (SQLException de) {
			de.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					}
				try {
					if(db!=null){
						db.close();
						}
				} catch (DbException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	
	/**
	 * @param blhdl        (String)     ���ʺĵ���
	 * @param thisdatetime (String)     ���γ���ʱ��
	 * @param lastdatetime (String)     �ϴγ���ʱ��
	 * @param edhdl        (String)     ��ĵ���
	 * @param ecbbl 	   (String)     ҳ�泬��ĵ�������
	 * @param addorimport  (String)     ��ӵ�ѵ����ǵ����ѵ� 1����ӣ�2������
	 * @return 			   (String[])	autoaudit[0]: �Զ����״̬��autoaudit[1]:�Զ����������autoaudit[2]�����м����״̬�жϣ�����û�ã���autoaudit[0]���ɣ���autoaudit[3]����ĵ������������
	 * @return  autoaudit[4]�����ڣ�autoaudit[5]����ĵ���
	 * @see ���ε������¸�������վ���ĵ�������ֵ��20% ��hdl=edhdl*������
	 * Bchdl <hdl*(1-10/100)����
	 * Bchdl >hdl*(1+10/100)
	 * �Զ����״̬��autoauditstatusΪ0 ,
     * �Զ����������autoauditdescription = 
     * "���ε������¸�������վ���ĵ�������ֵ��20%";
     * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkBcdl(String blhdl,String thisdatetime,String lastdatetime,String edhdl,String dbid,String ecbbl,String addorimport){ 
		String[] autoaudit = new String[6];
		String flag = "1";//������ʶ
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		String edcbbl1 = "";
		double edcbbl = 0.00;
		
			long ts = getQuot(lastdatetime, thisdatetime) + 1;
			String edsql = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID='"+ dbid + "'";
			DataBase db = new DataBase();
			ResultSet rs = null;
				try {
					
					try {
						db.connectDb();
						rs = db.queryAll(edsql.toString());
					} catch (DbException e) {
						e.printStackTrace();
					}
					
					if (rs.next()) {
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						
						if("".equals(edhdl)){
							edhdl="0";
						}
						if("0".equals(edhdl)){//��ĵ���Ϊ���򲻼���
							autoauditstatus = "0";
							autoauditdescription = "վ���ĵ���Ϊ��;";
							flag = "0";
							autoaudit[0] = autoauditstatus;
							autoaudit[1] = autoauditdescription;
							autoaudit[2] = flag;
							autoaudit[3] = "";
							autoaudit[4] = Long.toString(ts);
							autoaudit[5] = "";
							return autoaudit;
						}
						double hdl = Double.parseDouble(edhdl) * ts;//���ض���
						double bchdl = Double.parseDouble(blhdl);//���κĵ���
						edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
						edcbbl1 = Double.toString(edcbbl);
						if(bchdl < hdl*(1-0.1) || bchdl > hdl*(1+0.1)){
							autoauditstatus = "0";
							autoauditdescription = "���ε������¸�������վ���ĵ�������ֵ��10%;";
							flag = "0";
						}
						}		
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						if(rs!=null){
							rs.close();
							}
						try {
							if(db!=null){
								db.close();
								}
						} catch (DbException e) {
							e.printStackTrace();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
		autoaudit[3] = edcbbl1;
		autoaudit[4] = Long.toString(ts);
		autoaudit[5] = edhdl;
		return autoaudit;
	}
	
	/**
	 * @param scbbl          ʡ��������������   
	 * @param addorimport  (String)     ��ӵ�ѵ����ǵ����ѵ� 1����ӣ�2������
	 * @return (String[])	 autoaudit[0]:�Զ����״̬;autoaudit[1]:�Զ��������;
	 * @see ���ε������¸�������ȫʡ�����10%
	 * |�������|>10%
	 */
	public synchronized String[]checkBcdl2(String scbbl){
		String[] autoaudit = new String[2];
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		if(Math.abs(Format.str_d(scbbl))>10){
			autoauditstatus = "0";
			autoauditdescription = "���ε�������վ��ʡ�����������ֵ��10%;";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	/**
	 * @param elecinfo (ElectricityInfo)
	 * @return (String[])	 �Զ����״̬���Զ��������
	 * @see ����Զ����1
	 * �������ͨ��
	 *	�鿴��ѵ����Ƿ���ڿ� ����Ϊ�յ�ʱ��עmemo(���) ����Ϊ�գ�
	 *	���ǵ���ѵ�����Ϊ�յ�ʱ�� memo��������Ϣ��
	 *	�����Զ����״̬��autoauditstatusΪ0 ,
	 * 	�Զ���������� autoauditdescription = "�õ���õ���û��˵����";
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkElectric1(ElectricityInfo elecinfo){ 
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		String floatpay = elecinfo.getFloatpay();//��ѵ���
		String memo = elecinfo.getMemo1();//(���)��ע
			if("".equals(floatpay) || null == floatpay || "0".equals(floatpay) || "0.0".equals(floatpay) || "0.00".equals(floatpay)){
				//��ѵ���Ϊ�� ,��ע �ղ�������ν
			}else if(null ==memo || "".equals(memo) || "null".equals(memo)){
					autoauditstatus = "0";
					autoauditdescription = "�õ���õ���û��˵����";
			}
				

		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	 
	/**
	 * @param pjje (String) Ʊ�ݽ��
	 * @return  (String[])	�Զ����״̬���Զ��������
	 * @see ����Զ����2
	 * C2 Ʊ�ݽ���Ƿ�Ϊ�գ�
	 * ���Ʊ�ݽ��Ϊ�գ��Զ���˲�ͨ��
	 * autoauditstatus = "0";
	 * autoauditdescription = "Ʊ�ݽ��Ϊ�գ�";
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkElectric2(String pjje){ 
		String[] autoaudit = new String[2]; 
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		if(null == pjje || "0.00".equals(pjje) || "".equals(pjje) || "0".equals(pjje) || "0.0".equals(pjje)){
			autoauditstatus = "0";
			autoauditdescription = "Ʊ�ݽ��Ϊ�գ�";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		return autoaudit;
	}
	
	
	/**
	 * @author WangYiXiao 2014-2-19
	 * @param dbid ���id
	 * @param bccbtime ���γ���ʱ��
	 * @param sccbtime �ϴγ���ʱ��
	 * @param actualpay ʵ�ʵ�ѽ��
	 * @param actualelectric ʵ���õ���
	 * @param edhdl ��ĵ���
	 * @param sqdbdl ȫʡ�������
	 * @param scbbl ȫʡ����������������ҳ�棩
	 * @param ecbbl ��ĵ����������
	 * @param addorimport ����or���� 1�����ӣ�2������
	 * @Param jszq�������ڣ�����õģ�
	 * @param zqBZW: 1�ǵ������õ����ڣ�2��ͨ���ϴγ���ʱ��ͱ��γ���ʱ����������
	 * @see �Ƿ��쳣���߶���
	 * @return "1":�ǣ�"0":��
	 */
	public synchronized String[] checkExceptAndHighOld(String dbid,String qsdbdl,String actualpay,String actualelectric,String bccbtime,String sccbtime,String scbbl,String ecbbl,String addorimport,String jszq,String zqBZW){
		String[] audit = new String[3];
		String flag = "0";//�Ƿ����쳣���߶1:�ǣ�0����
		String description = "";//������Ϣ
		String property ="";//վ������
		double zhiliu = 0;//ֱ��
		double jiaoliu = 0;//����
		double dianfei = 0;//���
		double chaobiaofei = 0;//������
		double chaobiaobili1 = 0;//�������1��ʡ���꣩
		double chaobiaobili2 = 0;//�������2����ĵ�����
		long zhouqi = getQuot(sccbtime,bccbtime)+1;//����
		
		//��ѯ��������
		String edhdl = null;
		String zlfh1 = null;
		String jlfh1 = null;
		String df1 = null;
		String cbdf1 = null;
		double zlfh = 0;
		double jlfh = 0;
		double df = 0;
		double cbdf = 0;
		double cbbl = 0;
		String sql = "SELECT ZLFH,JLFH,PROPERTY FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?"; 
		String sql1 = "SELECT ZLFH,JLFH,DF,CBDF,CBBL FROM FILTER WHERE ZDSXCODE = ?";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		DataBase db = new DataBase();
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				zhiliu = rs.getDouble(1);
				jiaoliu = rs.getDouble(2);
				property = rs.getString(3);
			}
			if("zdsx07".equals(property)){//IDC����,�����idc���������쳣���߶�㣬�������
				flag = "1";
				description = "�߶�쳣������ΪIDC����;";
			}else {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, property);
			rs = ps.executeQuery();
			while(rs.next()){
				zlfh1 = rs.getString(1);
				jlfh1 = rs.getString(2);
				df1 = rs.getString(3);
				cbdf1 = rs.getString(4);
				zlfh = rs.getDouble(1);
				jlfh = rs.getDouble(2);
				df = rs.getDouble(3);
				cbdf = rs.getDouble(4);
				cbbl = rs.getDouble(5);
			}
			dianfei = Double.parseDouble(actualpay)/zhouqi;
			chaobiaofei = dianfei - df;
			
			//if("1".equals(addorimport)){//����
				//if("".equals(scbbl) || null == scbbl){
					//scbbl = "0";
				//}
				//chaobiaobili1 = Math.abs(Double.parseDouble(scbbl));
				//chaobiaobili2 = Math.abs(Double.parseDouble(ecbbl));
				
			//}else if("2".equals(addorimport)){//����
			double bchdl = Double.parseDouble(actualelectric);//���κĵ���
			String edsql = "SELECT Z.QSDBDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("��ѯʡ����");
			String edsql1 = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("��ѯ��ĵ���");
				
						ps = conn.prepareStatement(edsql);
						ps.setString(1, dbid);
						rs = ps.executeQuery();
					if (rs.next()) {
						qsdbdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(qsdbdl)){
							qsdbdl="0";
						}
						double qsdbdll = Double.parseDouble(qsdbdl) * zhouqi;
						chaobiaobili1 = Math.abs((bchdl-qsdbdll)/qsdbdll); 
					}
					ps = conn.prepareStatement(edsql1);
					ps.setString(1, dbid);
					rs = ps.executeQuery();
					if(rs.next()){
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(edhdl)){
							edhdl="0";
						}
						double hdl = Double.parseDouble(edhdl) * zhouqi;//���ض���
						chaobiaobili2 = Math.abs((bchdl - hdl)/hdl);//ȡ����ֵ
					}
			//}		
			if("".equals(edhdl) || null == edhdl || "0".equals(edhdl) || "0.0".equals(edhdl) || "0.00".equals(edhdl)){
				flag = "1";
				description = "�쳣���߶�:��ĵ���Ϊ��;";
			}else if("".equals(qsdbdl) || null == qsdbdl || "0".equals(qsdbdl) || "0.0".equals(qsdbdl) || "0.00".equals(qsdbdl)){
				flag = "1";
				description = "�쳣���߶�:ȫʡ�������Ϊ��;";
			}else{
				if("zdsx05".equals(property)){//������
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx02".equals(property)){//��վ
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%";
					 }
				}else if("zdsx01".equals(property)){//���û���
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "�쳣���߶�:����"+jlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx03".equals(property)){//Ӫҵ����
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "�쳣���߶�:����"+jlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx06".equals(property)){//����֧��
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "�쳣���߶�:����"+jlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%;";
					 }
				}else if("zdsx04".equals(property)){//����
					 if(zhiliu>zlfh){
						 flag = "1";
						 description = "�쳣���߶�:ֱ��"+zlfh1+"(A)����;";
					 }else if( jiaoliu>jlfh ){
						 flag = "1";
						 description = "�쳣���߶�:����"+jlfh1+"(A)����;";
					 }else if( dianfei>df){
						 flag = "1";
						 description = "�쳣���߶�:��Ѵ���"+df1+"(Ԫ/��);";
					 }else if(chaobiaofei>cbdf){
						 flag = "1";
						 description = "�쳣���߶�:�����Ѵ���"+cbdf1+"(Ԫ/��);";
					 }else if(chaobiaobili1>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:��ȫʡ���������������"+(int)(cbbl*100)+"%;"; 
					 }else if(chaobiaobili2>cbbl){
						 flag = "1";
						 description = "�쳣���߶�:����ĵ�����������"+(int)(cbbl*100)+"%;";
					 }
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		audit[0] = flag;
		audit[1] = description;
		audit[2] = property;
		return audit;
	}
	
	
	
	
	public synchronized String[] checkExceptAndHigh(String dbid,String actualpay,String actualelectric,String bccbtime,String sccbtime,String scbbl){
		String[] audit = new String[3];
		String flag = "0";//�Ƿ����쳣���߶1:�ǣ�0����
		String description = "";//������Ϣ
		String property ="";//վ������
		double zhiliu = 0;//ֱ��
		double jiaoliu = 0;//����
		double dianfei = 0;//���
		double chaobiaofei = 0;//������
		double chaobiaobili1 = Format.str_d(scbbl);//�������1��ʡ���꣩
		double chaobiaobili2 = 0;//�������2����ĵ�����
		long zhouqi = getQuot(sccbtime,bccbtime)+1;//����
		
		//��ѯ��������
		String edhdl = null;
		double zlfh = 0;
		double jlfh = 0;
		double df = 0;
		double cbdf = 0;
		double cbbl = 0;
		String sql = "SELECT ZLFH,JLFH,PROPERTY FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?"; 
		String sql1 = "SELECT ZLFH,JLFH,DF,CBDF,CBBL FROM FILTER WHERE ZDSXCODE = ?";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		DataBase db = new DataBase();
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				zhiliu = rs.getDouble(1);
				jiaoliu = rs.getDouble(2);
				property = rs.getString(3);
			}
			if("zdsx07".equals(property)){//IDC����,�����idc���������쳣���߶�㣬�������
				flag = "1";
				description = "�߶�쳣;";
			}else {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, property);
			rs = ps.executeQuery();
			while(rs.next()){
				zlfh = rs.getDouble(1);
				jlfh = rs.getDouble(2);
				df = rs.getDouble(3);
				cbdf = rs.getDouble(4);
				cbbl = rs.getDouble(5);
			}
			dianfei = Double.parseDouble(actualpay)/zhouqi;
			chaobiaofei = dianfei - df;
			
			//if("1".equals(addorimport)){//����
				//if("".equals(scbbl) || null == scbbl){
					//scbbl = "0";
				//}
				//chaobiaobili1 = Math.abs(Double.parseDouble(scbbl));
				//chaobiaobili2 = Math.abs(Double.parseDouble(ecbbl));
				
			//}else if("2".equals(addorimport)){//����
			double bchdl = Double.parseDouble(actualelectric);//���κĵ���
			String edsql1 = "SELECT Z.EDHDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID= ?";
			System.out.println("��ѯ��ĵ���");

					ps = conn.prepareStatement(edsql1);
					ps.setString(1, dbid);
					rs = ps.executeQuery();
					if(rs.next()){
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						if("".equals(edhdl)){
							edhdl="0";
						}
						double hdl = Double.parseDouble(edhdl) * zhouqi;//���ض���
						chaobiaobili2 = Math.abs((bchdl - hdl)/hdl);//ȡ����ֵ
					}
			//}		
			if("".equals(edhdl) || null == edhdl || "0".equals(edhdl) || "0.0".equals(edhdl) || "0.00".equals(edhdl)){
				flag = "1";
				description = "�쳣���߶�;";
			}else{
				if("zdsx05".equals(property)){//������
					 if(zhiliu>zlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl ){
						flag = "1";
						description = "�쳣���߶�;";
					}
				}else if("zdsx02".equals(property)){//��վ
					if(zhiliu>zlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl || chaobiaobili2>cbbl){
						flag = "1";
						description = "�쳣���߶�;";
					}
				}else if("zdsx01".equals(property)){//���û���
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl){
						flag = "1";
						description = "�쳣���߶�";
					}
				}else if("zdsx03".equals(property)){//Ӫҵ����
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl){
						flag = "1";
						description = "�쳣���߶�;";
					}
				}else if("zdsx06".equals(property)){//����֧��
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl || chaobiaobili2>cbbl ){
						flag = "1";
						description = "�쳣���߶�;";
					}
				}else if("zdsx04".equals(property)){//����
					if(zhiliu>zlfh || jiaoliu>jlfh || dianfei>df || chaobiaofei>cbdf || chaobiaobili1>cbbl  || chaobiaobili2>cbbl ){
						flag = "1";
						description = "�쳣���߶�;";
					}
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		audit[0] = flag;
		audit[1] = description;
		audit[2] = property;
		return audit;
	}
	
	
	/**
	 * @param zdid
	 * @see �Ƿ���1.2�����
	 * @author WangYIXIao 2014-2-19
	 * @return 
	 */
	public synchronized String[] checckSite(String dbid){
		String [] site = new String[2];
		String flag = "0";//�Ƿ���Ҫ�м����м�������˵�1.2����� 0����1��
		String description = "";//����
		PreparedStatement ps = null;
		String sql = "SELECT ZD.ID FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.SHBZW = '1' AND ZD.ID = DB.ZDID AND DB.DBID = ?";
		ResultSet rs = null;
		Connection conn = null;
		DataBase db = new DataBase();
		try {
			 conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,dbid);
			rs = ps.executeQuery();
			 if(rs.next()){
				 flag = "1";
				 description = "1.2�����";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		site[0] = flag;
		site[1] = description;
		return site;
	}
	
	/**
	 * @see ��ѵ����������������
	 * ��ѵ���>60,������������ֵ>500
	 * @param elecfeesadjust (String) ��ѵ���
	 * @param elecadjust (String) ��������
	 * @return (boolean[]) checkstatus[0]:�Ƿ��ѵ���>60,1:�ǣ�0����checkstatus[1]:��ѵ���������Ϣ;checkstatus[2]:�Ƿ��ѵ���>60���ҵ�����������ֵ����500��1���ǣ�0����checkstatus[3]:��ѣ���������������Ϣ
	 * @author WangYiXiao 2014-3-4
	 */
	public synchronized String[] adjustCheck1(String elecfeesadjust,String elecadjust){
		String[] checkstatus = new String[4];
		String checkfees = "0";//��ѱ�ʶ
		String checkfeesdescription = "";//�������
		String checkelec = "0";//������ʶ
		String check = "0";//��ѣ�������ʶ
		String feesandelecdescription = "";//��ѣ���������
		//���
		elecfeesadjust = "".equals(elecfeesadjust.trim()) ? "0" : elecfeesadjust.trim();//ȥ�ո�
		double fees = Double.parseDouble(elecfeesadjust);
		checkfees = fees>60?"1":"0";//��ѵ���״̬
		checkfeesdescription = "1".equals(checkfees)?"��ѵ�������60;":"";
		//����
		elecadjust = "".equals(elecadjust.trim()) ? "0" : elecadjust.trim();//ȥ�ո�
		double elec = Math.abs(Double.parseDouble(elecadjust));
		checkelec = elec>500?"1":"0";
		//���������
		if("1".equals(checkfees) && "1".equals(checkelec)){
			check = "1";
			feesandelecdescription = "��ѵ�������60���ҵ�����������ֵ����500��";
		}
		//��ֵ
		checkstatus[0] = checkfees;
		checkstatus[1] = checkfeesdescription;
		checkstatus[2] = check;
		checkstatus[3] = feesandelecdescription;
		//����״̬
		return checkstatus;
	}
	
	/**
	 * @see ���۵����Ƿ����5%
	 * (����-�ϴ�)/�ϴ�>0.05
	 * @param lastunitprice (String) �ϴε���
	 * @param thisunitprice (String) ���ε���
	 * @return (String[]) 1:���۵�������5%��0:���۵���δ����5%
	 * @author WangYiXiao 2014-3-4
	 */
	public synchronized String[] adjustCheck2(String lastunitprice,String thisunitprice){
		String[] check = new String[2];
		String checkstatus = "0";
		String checkdescription = "";
		//�ϴλ򱾴ε���Ϊ���򲻽����ж�
		if(null == lastunitprice || "".equals(lastunitprice.trim()) || null == thisunitprice){
			check[0] = checkstatus;
			check[1] = checkdescription;
			return check;
		}
		double lastpe = Double.parseDouble(lastunitprice.trim());
		double thispe = Double.parseDouble(thisunitprice.trim());
		checkstatus = (thispe - lastpe) / lastpe > 0.05 ? "1":"0";
		checkdescription = "1".equals(checkstatus)?"���۵�������5%;":"";
		check[0] = checkstatus;
		check[1] = checkdescription;
		return check;
	}
	
	/**
	 * @see Ʊ��ʱ���Ӧ�·�-�����·�>=2
	 * @param thisdatetime (String) ���γ���ʱ��
	 * @param accountmonth (String) �����·�
	 * @return (String[]) check[0]���Ƿ�Ʊ��ʱ���Ӧ�·�-�����·�>=2��1���ǣ�0����check[1]��������Ϣ
	 * @author WangYiXiao 2014-3-6
	 * @update WangYiXiao 2014-3-21 Ʊ��ʱ�� ��Ϊ ���γ���ʱ��
	 */
	public synchronized String[] chaYue(String thisdatetime,String accountmonth){
		String[] check = new String[2];
		String checkflag = "0";
		String checkdescription = "";
		//Ʊ�� ʱ�� ��Ϊ ���γ���ʱ��
		int byear = Integer.parseInt(thisdatetime.substring(0, 4));//��
		int byue = Integer.parseInt(thisdatetime.substring(5, 7));//��
		//�����·�
		int ayear = Integer.parseInt(accountmonth.substring(0, 4));//��
		int ayue = Integer.parseInt(accountmonth.substring(5, 7));//��
		//�Ƚ�
		int yue = (byear-ayear)*12 + (byue-ayue); //��
		if(yue >= 2){
			checkflag = "1";
			checkdescription = "���γ���ʱ���Ӧ�·�-�����·�>=2;";
		}
		check[0] = checkflag;
		check[1] = checkdescription;
		return check;
	}
	
	/**
	 * @see �Ƿ�������������10���ҵ���������
	 * @param fee (String) ��ѵ���
	 * @param elec (String) ��������
	 * @return (String[]) auto[0]:��־λ��1���ǣ�0����auto[1]��������Ϣ
	 */
	public synchronized String[] adjustFeeAndElec1(String fee,String elec){
		String[] auto = new String[2];
		String flag = "0";//��־
		String description = "";//����
		if( Double.parseDouble(fee) > 10 && Double.parseDouble(elec) < 0 ){
			flag = "1";
			description = "�������������10���ҵ���������;";
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see ���������������������ҵ�ѣ�����������������������*����-��ѵ�����/��ѵ���>1.1 
	 * @param elec (String) ��������
	 * @param fee  (String)��ѵ���
	 * @param unitprice (String) ����
	 * @return (String[]) auto[0]:��־λ��1���ǣ�0����auto[1]��������Ϣ
	 */
	public synchronized String[] adjustFeeAndElec2(String elec,String fee,String unitprice){
		String[] auto = new String[2];
		String flag = "0";//��־
		String description = "";//����
		double ele = Double.parseDouble(elec);
		double fe = Double.parseDouble(fee);
		double up = Double.parseDouble(unitprice);
		//System.out.println("ele:"+ele);
		//System.out.println("fe:"+fe);
		//System.out.println("up:"+up);
			if( (ele > 0 && fe > 0 && (ele * up - fe)/fe > 1.1) || (ele < 0 && fe < 0 && (ele * up - fe)/fe > 1.1)){
				flag = "1";
				description = "����������*����-��ѵ�����/��ѵ���>1.1;";
			}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see �����5,6,7,8,9,10�·ݵ�������*���ʴ���800��Ҫ�ļ����  �������500 
	 * @param elec (String) ��������
	 * @return (String[]) auto[0]:��־λ��1���ǣ�0����auto[1]��������Ϣ
	 */
	public synchronized String[] adjustElec(String elec,String beilv){
		Date date = new Date();
		int yue = date.getMonth()+1;//��ǰ�·�
		String[] auto = new String[2];
		String flag = "0";//��־
		String description = "";//����
		if(beilv==null||beilv.equals("")||beilv.equals("0")||beilv.equals("null")){
			beilv="1";
		}
		double ele = Double.parseDouble(elec)*Double.parseDouble(beilv);
		//System.out.println("ele:"+ele +"  beilv:" +beilv);
			if(yue == 5 || yue == 6 || yue == 7 || yue == 8 || yue == 9 || yue == 10 ){// yue>=5 && yue<=10
				if(ele > 800){
				flag = "1";
				description = "������������800;";
				}
			}else if(yue == 11 || yue == 12 || yue == 1 || yue == 2 || yue == 3 || yue == 4){
				if(ele > 500){
					flag = "1";
					description = "������������500;";
					}
			}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see ���ε�ѽ�������������ƽ����ѽ��
	 * @param bcelefees (String) ���ε�ѽ��
	 * @param (String) shi
	 * @param {String} xian
	 * @return (String[]) auto[0]:��־λ;auto[1]:
	 * @author WangYiXiao 2014-3-26
	 */
	public synchronized String[] checkThisFees(String bcelefees,String shi,String xian){
		String[] auto = new String[2];
		String lastaveragefee = "";//��������ƽ�����
		String flag = "0";//��־
		String description = "";//����
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT PAYAVG FROM (SELECT PAYAVG FROM T_PDCOUNT TECT WHERE SHI = ? AND XIAN = ? ORDER BY TECT.ACCOUNTMONTH DESC NULLS LAST ) WHERE ROWNUM = 1";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, shi);
			ps.setString(2, xian);
			System.out.println("��������ƽ�����");
			rs = ps.executeQuery();
			if(rs.next()){
				lastaveragefee = rs.getString("PAYAVG") == null ? "" : rs.getString("PAYAVG");
				if(!"".equals(lastaveragefee)){
					if(Double.parseDouble(bcelefees)>Double.parseDouble(lastaveragefee)){
						flag = "1";
						description = "���ε�ѽ�������������ƽ����ѽ��;";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;
	}
	
	/**
	 * @see ���絥�۴��ڱ������±���ƽ��ֵ1.1������
	 * @param unitprice (String) ���絥��
	 * @param shi (String)
	 * @param xian (String)
	 * @return (String[]) auto[0]:��־λ;auto[1]:
	 * @author WangYiXiao 2014-3-27
	 */
	public synchronized String[] OutElecUnitPrice(String unitprice,String shi,String xian){
		String[] auto = new String[2];
		String flag = "0";//��־
		String description = "���絥��С�ڵ��ڱ������±���ƽ��ֵ1.1��;";//����
		double averageprice = 0;
		String sql = "SELECT UNITPRICEAVG FROM (SELECT UNITPRICEAVG FROM T_INPAYCOUNT TECT WHERE SHI = ?  ORDER BY TECT.ACCOUNTMONTH DESC NULLS LAST) WHERE ROWNUM = 1";//�������±���ƽ��ֵ
		DataBase db = new DataBase();
		Connection connc = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connc = db.getConnection();
			ps = connc.prepareStatement(sql);
			ps.setString(1, shi);
			//ps.setString(2, xian);
			System.out.println("�������±���ƽ��ֵ");
			rs = ps.executeQuery();
			if(rs.next()){
				averageprice = rs.getDouble("UNITPRICEAVG");
				if(Double.parseDouble(unitprice) > averageprice * 1.1){
					flag = "1";
					description = "";
				}
			}else{
				flag = "0";
			    description = "�������±���ƽ������Ϊ��;";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, connc);
		}
		auto[0] = flag;
		auto[1] = description;
		return auto;	
	}
	
	/**
	 * @see ��ȡĳ����������У�����
	 * @param dbid
	 * @return (String[]) shi;xian
	 * @author WangYiXiao 2014-3-27
	 */
	public synchronized String[] getShiAndXian(String dbid){
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] audit = new String[2];
		String shi = "";
		String xian = "";
		String sql1= "SELECT ZD.SHI SHI,ZD.XIAN XIAN FROM ZHANDIAN ZD, DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = ?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, dbid);
			rs = ps.executeQuery();
			while(rs.next()){
				shi = rs.getString("SHI");
				xian = rs.getString("XIAN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		audit[0] = shi;
		audit[1] = xian;
		return audit;

	}
/** 
 * @author WangYiXiao 2014-10-25
 * @update WangYiXiao 2015-1-21 ��������ԭ���������ʽ
 * ����������:
 *4.վ������Ϊ����֧��վ�����ͳ�ģ��ֵ�
 *2.վ������Ϊ���û���վ�����ͳ����Ļ���
 *3.վ������ΪӪҵ����
 *  ��������ԭ���������ʽ:((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
 * @param dbydl ����õ��� (���ζ���-�ϴζ���)*����
 * @param thistime ���γ���ʱ��
 * @param lasttime �ϴγ���ʱ��
 * @param shi ��
 * @param property վ������
 * @param zlfh ֱ������
 * @param jlfh �������� 
 * @param edhdl ��ĵ���
 * @param scb ������
 * @param dbid ���id
 * @param blhdl ���ʺĵ���
 * @param qsdbdl ȫʡ�������
 * @param zdlx վ������
 * @return cbbl[0]�������  cbbl[1]�ϲ����� cbbl[2]��׼ֵ
 */
public synchronized String[] getQsdbdlCbbl(String dbydl,String thistime,String lasttime,
		String shi,String property,String zlfh,String jlfh,String edhdl,String scb,String dbid,String blhdl,String qsdbdl,String zdlx){
	String[] cbbl = new String[3];//�������,�ϲ�����,��׼ֵ
	BigDecimal hbzq = new BigDecimal("0");//�ϲ�����
	BigDecimal bzz = null;//��׼ֵ
	BigDecimal bl = null;//�������
	int ktsl = 0;//�յ���
	Connection connc = null;
	Statement st = null;
	ResultSet rs = null;
	String sql = "SELECT T.YMONTH,T.EMONTH,T.SMONTH,T.SIMONTH,T.WMONTH,T.LMONTH,T.QMONTH,T.BMONTH,T.JMONTH,T.SHIMONTH,T.SYMONTH,T.SEMONTH "
			+ "FROM YFXS T WHERE T.SHICODE = '"+shi+"'";
	DataBase db = new DataBase();
	//--�·�ϵ��map
	Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
	//--�յ�ϵ��
	BigDecimal ktxs = null;
	//--����ϵ��
	BigDecimal fwxs = new BigDecimal("0");//ֻ�л�վ�з���ϵ��,��������0
	try {
		connc = db.getConnection();
		st = connc.createStatement();
		rs = st.executeQuery(sql);
		if(rs.next()){
			map.put("1", rs.getBigDecimal("YMONTH"));
			map.put("2", rs.getBigDecimal("EMONTH"));
			map.put("3", rs.getBigDecimal("SMONTH"));
			map.put("4", rs.getBigDecimal("SIMONTH"));
			map.put("5", rs.getBigDecimal("WMONTH"));
			map.put("6", rs.getBigDecimal("LMONTH"));
			map.put("7", rs.getBigDecimal("QMONTH"));
			map.put("8", rs.getBigDecimal("BMONTH"));
			map.put("9", rs.getBigDecimal("JMONTH"));
			map.put("10", rs.getBigDecimal("SHIMONTH"));
			map.put("11", rs.getBigDecimal("SYMONTH"));
			map.put("12", rs.getBigDecimal("SEMONTH"));
		}
		BigDecimal zlfhbd = new BigDecimal(zlfh);//ֱ������
		BigDecimal bigd = new BigDecimal("100");
		String str = "";
		if("zdsx02".equals(property)){//��վ
			str = " T.JZKTXS ";
			
			String sql2 = "SELECT FS.FXXS FXXS FROM FWXS FS WHERE FS.YFLXCODE=(SELECT ZD.YFLX FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID = '"+dbid+"')";
			rs = st.executeQuery(sql2);
			if(rs.next()){
				fwxs = rs.getBigDecimal("FXXS");
			}
		}else if("zdsx05".equals(property)){//������
			str = "T.JRWKTXS";
		}else if("zdsx06".equals(property)){//����֧��
			str = "T.XZZJKTXS";
		}else if("zdsx01".equals(property)){//���û���
			str = "T.JYJFKTXS";
		}else if("zdsx04".equals(property)){//����
			str = "T.QTKTXS";
		}else if("zdsx07".equals(property)){//IDC����
			str = "T.IDCJFKTXS";
		}else if("zdsx03".equals(property)){//Ӫҵ����
			str = "T.YYWDKTXS";
		}else {//�̻�
			str = "T.QTKTXS";
		}
		String sql1 = "SELECT "+str+" FROM KTXS T WHERE "+(zlfhbd.compareTo(bigd)==-1?
				("T.KSZLFH<="+zlfh+" AND T.JSZLSH>"+zlfh):
					("T.KSZLFH<="+zlfh+" AND T.JSZLSH = 0 "));
		System.out.println(sql1);
		rs = st.executeQuery(sql1);
		if(rs.next()){
			ktxs = rs.getBigDecimal(1);
		}
		if("zdsx05".equals(property) || "zdsx03".equals(property)){//������/Ӫҵ����
			//---������ر�(��ĵ���)���������ֱ�����߽���Ϊ0 ��Ϊ��  �յ�ϵ��Ϊ0
			if(Format.str_d(edhdl)==0||Format.str_d(scb)==0||Format.str_d(zlfh)==0||Format.str_d(jlfh)==0){
				ktxs = new BigDecimal("0");
			}else{
				BigDecimal a = null;
				BigDecimal b = null;
				BigDecimal c = null;
				BigDecimal zldl = new BigDecimal(zlfh).multiply(new BigDecimal("1.52"));//ֱ������
				BigDecimal jldl = new BigDecimal(jlfh).multiply(new BigDecimal("5.28"));//��������
				a = zldl.min(jldl);
				b = new BigDecimal(edhdl).min(new BigDecimal(scb));
				c = a.min(b);//��Сֵ
				ktxs = ktxs.divide(c,2,BigDecimal.ROUND_HALF_UP);
			}
		}
		String ktssql = "SELECT ZD.KTS FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID AND DB.DBID='"+dbid+"'";//վ���kts
		rs = st.executeQuery(ktssql);
		if(rs.next()){
		ktsl = rs.getInt("KTS");	
		}
		ktxs = ktsl>0?ktxs:new BigDecimal("0");//����յ���С�ڵ���0��յ�ϵ��Ϊ0
		BigDecimal jbh = new BigDecimal("1").add(fwxs);//�ֲ��� = ����ϵ�� + ����ϵ��;
		long[][] yue = new DateUtil().getYue(lasttime, thistime);
		int  leng = yue.length;
		for(int i = 0 ; i < leng ; i++){
			hbzq = hbzq.add(new BigDecimal(yue[i][1]).multiply(jbh.add(map.get(String.valueOf(yue[i][0])).multiply(ktxs)))); //�ϲ����� = �ϲ����� + ÿ������*(����ϵ��=�ֲ���+�·�ϵ��*�յ�ϵ�� )
		}
		bzz = hbzq.multiply(new BigDecimal(scb));//��׼ֵ=�ϲ�����*������
		// *4.վ������Ϊ����֧��վ�����ͳ�ģ��ֵ�
		// *2.վ������Ϊ���û���վ�����ͳ����Ļ���
		// *3.վ������ΪӪҵ����
//		if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
//				|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
//				|| "zdsx03".equals(property) ){//��������ԭ���������ʽ
//			
//		}
		//2015-2-2
		if(judgeFsc(property,zdlx)){//����
			bl = (new BigDecimal(dbydl).subtract(bzz)).divide(bzz, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//������õ���-��׼ֵ��/��׼ֵ*100%
		}else{//��������ԭ���������ʽ
			long zhouqi = getQuot(lasttime,thistime)+1;//����
			BigDecimal qsdl = new BigDecimal(qsdbdl).multiply(new BigDecimal(zhouqi));
			bl = (new BigDecimal(blhdl).subtract(qsdl)).divide(qsdl, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
		}
		
		cbbl[0] = Format.str2(bl.toString());
		cbbl[1] = Format.str2(hbzq.toString());
		cbbl[2] = Format.str2(bzz.toString());
	} catch (DbException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		db.free(rs, st, connc);
	}
	return cbbl;
}
/**�ж��Ƿ����������վ��
 * @author WangYiXiao 2015-1-28
 * @param property (String) վ������
 * @param zdname (String) վ������
 * @return
 */
public synchronized boolean judgeOut(String property,String zdname){
	zdname = null==zdname?"":zdname; 
	//վ������Ϊ zdsx04 ���� վ�������к��� ���� ��Ϊ �������վ��
	return ("zdsx04".equals(property) && (zdname.indexOf("����") != -1)) ? true : false;
}

/** �ж��Ƿ�Ϊ������
 * @param property
 * @param zdlx
 * @return
 * @author WangYiXiao 2015-1-28
 * @update WangYiXiao 2015-2-2 �ж��Ƿ�Ϊ���������Ϊ �ж��Ƿ�Ϊ����
 */
public synchronized boolean judgeFsc(String property,String zdlx){
	//������:
	// *4.վ������Ϊ����֧��վ�����ͳ�ģ��ֵ�
	// *2.վ������Ϊ���û���վ�����ͳ����Ļ���
	// *3.վ������ΪӪҵ����
//	if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
//			|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
//			|| "zdsx03".equals(property) ){
//		flag = true;
//	}
	// ��  ��Ϊ ��
/*	
  	�����ࣺ
  	վ������Ϊ��վ����������IDC�����е�վ������						
	վ������Ϊ���û����µģ����Ļ��������û������Ρ�ͨ�Ŵ�¥						
	վ������Ϊ�����µģ���Ƶ��ء�����	(�豸ע˵��)					
	վ������Ϊ����֧���µ�ģ��֡�����֧�֡�����֧�����Ρ�����(�豸ע˵��)						
	�������ࣺ
	ʣ�µ���������	
*/
	return ("zdsx02".equals(property) || "zdsx05".equals(property) || "zdsx07".equals(property)
			|| ("zdsx01".equals(property) && ("StationType20".equals(zdlx) || "StationType49".equals(zdlx) || "StationType08".equals(zdlx)))
			|| ("zdsx04".equals(property) && ("StationType13".equals(zdlx) || "StationType18".equals(zdlx)))
			|| ("zdsx06".equals(property) && ("StationType12".equals(zdlx) || "StationType25".equals(zdlx) || "StationType48".equals(zdlx) || "StationType18".equals(zdlx)))
			) ? true : false;
	
}
/**�ж��Ƿ�Ϊֱ����
 * @param gdfscode
 * @return
 * @author WangYiXiao 2015-1-28
 */
public synchronized boolean judgeZgd(String gdfscode){
	//���緽ʽΪֱ����1��ֱ����2 ��Ϊֱ����
	return ("gdfs06".equals(gdfscode) || "gdfs07".equals(gdfscode)) ? true : false;
}


/**����վ�����Լ������жϲ�ѯ���ֱ�׼����
 * @param property
 * @param zdname
 * @param zdlx
 * @param gdfscode
 * @return
 * @author WangYiXiao 2015-1-28
 */
public synchronized String selectUnitprice(String property,String zdname,String zdlx, String gdfscode){
	String leixing = "";
	if(judgeOut(property,zdname)){//���������
		if(judgeFsc(property,zdlx)){//������
			if(judgeZgd(gdfscode)){//ֱ����
				leixing = "WSZHI";
			}else{//��ֱ����(ת����)
				leixing = "WSZHUAN";
			}
		}else{//��������
			if(judgeZgd(gdfscode)){//ֱ����
				leixing = "WFZHI";
			}else{//��ֱ����(ת����)
				leixing = "WFZHUAN";
			}
		}
	}else{//֧����
		if(judgeFsc(property,zdlx)){//������
			if(judgeZgd(gdfscode)){//ֱ����
				leixing = "ZSZHI";
			}else{//��ֱ����(ת����)
				leixing = "ZSZHUAN";
			}
		}else{//��������
			if(judgeZgd(gdfscode)){//ֱ����
				leixing = "ZFZHI";
			}else{//��ֱ����(ת����)
				leixing = "ZFZHUAN";
			}
		}	
	}
	return leixing;
}
/**�жϱ����Ƿ���ĳЩ���ֵ�������
 * @param beilv
 * @param bei
 * return ��:false ��:true
 * @author WangYiXiao 2015-2-12
 */
public synchronized boolean beilvBei(String beilv,String[] bei){
	boolean flag = true;
	for( int i=0;i<bei.length;i++){
		flag = (Format.str_d(beilv) % Format.str_d(bei[i]) != 0 ? true:false) && flag;
	}
	return flag;
}

/**��ǿ���̣���ѳ��������(���˵�ѡ���׼����*��׼����) / ��׼����*��׼����*100% ��������
 * @param bzz (String)  ��׼���� 
 * @param bzdj (String) ��׼����
 * @param bzdf (String) ���˵��
 * @return (String) ��ѳ������
 * @author WangYiXiao 2015-2-13
 */
public synchronized String getOverFeesbl(String bzz,String bzdj,String bzdf){
	BigDecimal cbbl;
	BigDecimal dlanddj = new BigDecimal(bzz).multiply(new BigDecimal(bzdj));//��׼����*��׼����
	cbbl = (new BigDecimal(bzdf).subtract(dlanddj)).divide(dlanddj, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
	return Format.str2(cbbl.toString());
}

	 /**
	 * @param time1 (String) С��ʱ��
	 * @param time2 (String) ���ʱ��
	 * @return (long) ��������
	 * @see ��������������������
	 * @author WangYiXiao 2014-1-21
	 */
	public synchronized long getQuot(String time1, String time2){
		    long days = 0;
		    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		    try {
		     Date date1 = ft.parse( time1 );
		     Date date2 = ft.parse( time2);
		     days = date2.getTime() - date1.getTime();
		     days = days/1000/60/60/24;//һ��ĺ�����
		    } catch (Exception e) {
		     e.printStackTrace();
		    }
		    return days;
	}
}
