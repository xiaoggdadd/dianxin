package com.ptac.app.electricmanage.electricitybill.tool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanageUtil.Format;
/**
 * @see ��������ѹ�����
 * @author WangYiXiao 2014-1-20
 */
public class ElectricityTool {
    
	/**
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
	public synchronized boolean checkRepeat(String thisdegree,String thisdatetime,String lastdegree,String lastdatetime,String ammeteridFk,String accountmonth){
		DataBase db = new DataBase();
		boolean flag = false;//�Ƿ��ظ���־λ
		String sql = "SELECT DD.AMMETERDEGREEID FROM DIANDUVIEW DD,DIANFEIVIEW DF WHERE THISDEGREE='" + thisdegree
		+ "' AND  DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  AND THISDATETIME='" + thisdatetime
		+ "' AND LASTDEGREE='" + lastdegree
		+ "'AND LASTDATETIME='" + lastdatetime
		+ "' AND AMMETERID_FK='" + ammeteridFk
		+ "'AND ACCOUNTMONTH='" + accountmonth+"'";
		ResultSet rs = null;
		try {
			try {
				db.connectDb();
				System.out.println("�ظ����"+sql.toString());
				rs = db.queryAll(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	     	flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{//�ͷ���Դ
				try {
					rs.close();
					try {
						db.close();
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
	 *	�鿴�õ��������Ƿ����0���գ�������0.0 ����Ϊ�յ�ʱ��עmemo(����) ����Ϊ�գ�
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
		sql.append("SELECT CASE WHEN (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')) != '0' THEN AD.blhdl / (TO_DATE(AD.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AD.LASTDATETIME, 'YYYY-MM-DD')+1) ELSE   1 END DAYDEGREE" +
	    		" FROM DIANDUVIEW AD " +
	    		"WHERE AD.THISDATETIME ='"+lastadtime+"' AND AD.AMMETERID_FK='"+ammeterid+"'");
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
						autoauditdescription = "���ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ���20%";
					}	
		    }
			
		} catch (SQLException de) {
			de.printStackTrace();
		}finally{
			try {
				rs.close();
				try {
					db.close();
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
	 * @return 			   (String[])	 �Զ����״̬���Զ��������
	 * @see ���ε������¸�������վ���ĵ�������ֵ��20% ��hdl=edhdl*������
	 * Bchdl <hdl*(1-20/100)����
	 * Bchdl >hdl*(1+20/100)
	 * �Զ����״̬��autoauditstatusΪ0 ,
     * �Զ����������autoauditdescription = 
     * "���ε������¸�������վ���ĵ�������ֵ��20%";
     * @author WangYiXiao 2014-1-21
	 */
	public synchronized String[] checkBcdl(String blhdl,String thisdatetime,String lastdatetime,String edhdl,String dbid){ 
		String[] autoaudit = new String[4];
		String flag = "1";//������ʶ
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
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
					}		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					try {
						db.close();
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		Double hdl = Double.parseDouble(edhdl) * ts;//���ض���
		Double bchdl = Double.parseDouble(blhdl);//���κĵ���
		Double edcbbl = Format.d2((bchdl-hdl)/hdl);
		if((bchdl-hdl)/hdl >=0.1 && (bchdl-hdl)/ hdl <=0.9){
			autoauditstatus = "0";
			autoauditdescription = "���ε������¸�������վ���ĵ�������ֵ��10%";
			flag = "0";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
		autoaudit[3] = edcbbl+"";
		return autoaudit;
	}
	
	/**
	 * @param blhdl (String) ���ʺĵ���
	 * @param qsdbdl (String)ȫʡ�������
	 * @param thisdatetime   ���γ���ʱ��
	 * @param lastdatetime   �ϴγ���ʱ��
	 * @return (String[])	 �Զ����״̬���Զ��������
	 * @see ���ε������¸�������ȫʡ�����10%
	 */
	public synchronized String[]checkBcdl2(String blhdl,String qsdbdl,String thisdatetime,String lastdatetime,String dbid){
		String[] autoaudit = new String[3];
		String flag = "1";//������ʶ
		String autoauditstatus = "1";//�Զ����״̬
		String autoauditdescription = "";// �Զ��������
		Double bchdl = Double.parseDouble(blhdl);//���κĵ���
		long ts = getQuot(lastdatetime,thisdatetime)+1;//ʱ���
		String edsql = "SELECT Z.QSDBDL FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBID='"+ dbid + "'";
		ResultSet rs = null;
		DataBase db = new DataBase();
		System.out.println("ʡ����"+edsql);
			try {
				try {
					db.connectDb();
					rs = db.queryAll(edsql.toString());
				} catch (DbException e) {
					e.printStackTrace();
				}
				if (rs.next()) {
					qsdbdl = (null == rs.getString(1) ? "0" : rs
							.getString(1));
					if("".equals(qsdbdl)){
						qsdbdl="0";
					}
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					try {
						db.close();
					} catch (DbException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
			
		Double qsdbdll = Double.parseDouble(qsdbdl) * ts;
		if((bchdl-qsdbdll)/qsdbdll >=0.1 && (bchdl-qsdbdll)/ qsdbdll <=0.9){
			autoauditstatus = "0";
			autoauditdescription = "���ε�������վ��ʡ�����������ֵ��10%";
			flag = "0";
		}
		autoaudit[0] = autoauditstatus;
		autoaudit[1] = autoauditdescription;
		autoaudit[2] = flag;
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
					
				}else{
					if(null ==memo || "".equals(memo) || "null".equals(memo)){
						autoauditstatus = "0";
						autoauditdescription = "�õ���õ���û��˵����";
					}
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
	public static void main(String[] args) {
		ElectricityTool t = new ElectricityTool();
		System.out.println(t.getQuot("2013-10-19","2014-02-14" ));
		//t.checkBcdl("2031.4", "2014-02-14", "2013-10-19", "75.00", dbid);
	}
}
