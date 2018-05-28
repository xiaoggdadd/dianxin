package com.ptac.app.electricmanage.enhanceelectricitybill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @see ��ѵ��޸�.dao��ʵ����
 * @author ZengJin
 */
public class ElecModifyDaoImp implements ElecModifyDao{
	DataBase db = new DataBase();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	/**
	 * @param degreeid (String) //���id
	 * @return (List)
	 * @see ��ѯ������ѻ�����Ϣ
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public List getElectricFeesInfo(String degreeid) {
		
    	List list = new ArrayList();	//���������
		ElectricityInfo elec = new ElectricityInfo();	//�������bean
		Share share = new Share();	//������̯��Ϣbean

	    StringBuffer sql = new StringBuffer();
	    sql.append("select TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd') LASTDATETIMEA,TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') THISDATETIMEA,ad.*,AD.AMMETERDEGREEID,EF.ELECTRICFEE_ID,Z.SHI SHICODE,AD.DDDF AS DDDFDL,AD.MEMO AS MEMOAM,AD.DBYDL,ef.pjje,ef.*,EF.DDDF AS DDDFDF,EF.MEMO AS MEMOEF,"
	    		+"Z.EDHDL EDHDLA,Z.QSDBDL QSDBDLA,Z.PROPERTY PROPERTYCODE,Z.SCB SCBA,EF.UNITPRICE AS UNITPRICEA ,EF.YZLIUCHENGHAO AS YZLIUCHENGHAO ,"
	    		+"Z.ZLFH ZLFHA,Z.JLFH JLFHA,D.BEILV BEILVA,D.CHANGEVALUE CHANGEVALUEA,D.LINELOSSTYPE LINELOSSTYPEA,D.LINELOSSVALUE LINELOSSVALUEA,"
	    		+"Z.STATIONTYPE STATIONTYPECODE,D.DFZFLX DFZFLXCODE,Z.GDFS GDFSCODE,Z.JZNAME,"
	    		+"EF.JFZB JFZB,EF.BFZB BFZB,EF.BGZB BGZB,EF.BPZB BPZB,EF.YDDF,TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTHA "
	    		+"from zhandian z,dianbiao d, ammeterdegrees ad,electricfees ef "
	    		+"where z.id=d.zdid and d.dbid= ad.AMMETERID_FK and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ELECTRICFEE_ID="+degreeid);
    	System.out.println("��ѵ�(�޸�)��ѯ������Ϣ:"+sql.toString());

	    try {
	    	db.connectDb();	 
	    	conn = db.getConnection();
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql.toString());//����sql
	    	while (rs.next()) {
	    		
				//------���ղ�ѯ��������(���ж��Ƿ�Ϊ��)------
	    		//���
	    		elec.setDbid(rs.getString("ammeterid_fk") != null ? rs.getString("ammeterid_fk") : "");
	    		//����
				elec.setAmmeterdegree_id(rs.getString("AMMETERDEGREEID") != null ? rs.getString("AMMETERDEGREEID") : "");
				elec.setFloatdegree(rs.getString("floatdegree") != null ? rs.getString("floatdegree") : "");
				elec.setActualdegree(rs.getString("actualdegree") != null ? rs.getString("actualdegree") : "");	    	
				elec.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");
				elec.setLastdatetime(rs.getString("lastdatetimea") != null ? rs.getString("lastdatetimea") : "");
				elec.setThisdegree(rs.getString("thisdegree") != null ? rs.getString("thisdegree") : "");
				elec.setThisdatetime(rs.getString("thisdatetimea") != null ? rs.getString("thisdatetimea") : "");
				elec.setMemo(rs.getString("memoam") != null ? rs.getString("memoam") : "");
				elec.setBlhdl(rs.getString("blhdl") != null ? rs.getString("blhdl") : "");
				elec.setDedhdl(rs.getString("dedhdl") != null ? rs.getString("dedhdl") : "");
				elec.setCsdb(rs.getString("CSDB") != null ? rs.getString("CSDB") : "");
				elec.setActualdegree(Format.str2(elec.getActualdegree()));	    	
				elec.setBlhdl(Format.str2(elec.getBlhdl()));
				elec.setPjdl(rs.getString("PJDL") != null ? rs.getString("PJDL") : "");
				elec.setDbydl(rs.getString("DBYDL") != null ? rs.getString("DBYDL") : "");
				
	    		//���
				elec.setShi(rs.getString("SHICODE") != null ? rs.getString("SHICODE") : "");
				elec.setElectricfee_id(rs.getString("ELECTRICFEE_ID") != null ? rs.getString("ELECTRICFEE_ID") : "");
				elec.setPjje(rs.getDouble("pjje"));
				elec.setAccountmonth(rs.getString("accountmontha") != null ? rs.getString("accountmontha") : "");
				elec.setActualpay(rs.getString("actualpay") != null ? Double.parseDouble(rs.getString("actualpay")) : 0);
				elec.setFloatpay(rs.getString("floatpay") != null ? rs.getString("floatpay") : "");
				elec.setInputoperator(rs.getString("inputoperator") != null ? rs.getString("inputoperator") : "");	    
				elec.setMemo1(rs.getString("memoef") != null ? rs.getString("memoef") : "");
				elec.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
				elec.setPayoperator(rs.getString("payoperator") != null ? rs.getString("payoperator") : "");
				elec.setUnitprice(rs.getString("unitpricea") != null ? rs.getString("unitpricea") : "");//��ѱ�
				elec.setLiuchenghao(rs.getString("yzliuchenghao") != null ? rs.getString("yzliuchenghao") : "");
				elec.setZlfh(rs.getString("zlfha") != null ? rs.getString("zlfha") : "");//
				elec.setJlfh(rs.getString("jlfha") != null ? rs.getString("jlfha") : "");//
				elec.setBeilv(rs.getString("beilva") != null ? rs.getString("beilva") : "");//
				elec.setPropertycode(rs.getString("PROPERTYCODE")!=null ? rs.getString("PROPERTYCODE"):"");//
				elec.setScb(rs.getString("SCBA")!=null ? rs.getString("SCBA"):"");//
				elec.setChangevalue(rs.getString("changevaluea") != null ? rs.getString("changevaluea") : "");//
				elec.setLinelosstype(rs.getString("linelosstypea") != null ? rs.getString("linelosstypea") : "");//
				elec.setLinelossvalue(rs.getString("linelossvaluea") != null ? rs.getString("linelossvaluea") : "");//
				elec.setStationtypecode(rs.getString("STATIONTYPECODE") != null ? rs.getString("STATIONTYPECODE") : "");
				elec.setDfzflxcode(rs.getString("DFZFLXCODE") != null ? rs.getString("DFZFLXCODE") : "");
				elec.setGdfscode(rs.getString("GDFSCODE") != null ? rs.getString("GDFSCODE") : "");
				elec.setJfzb(rs.getString("JFZB") != null ? rs.getString("JFZB") : "");
				elec.setBfzb(rs.getString("BFZB") != null ? rs.getString("BFZB") : "");
				elec.setBgzb(rs.getString("BGZB") != null ? rs.getString("BGZB") : "");
				elec.setBpzb(rs.getString("BPZB") != null ? rs.getString("BPZB") : "");
				elec.setYddf(rs.getString("YDDF") != null ? rs.getString("YDDF") : "");
				
				//������̯
				share.setNetworkhdl(rs.getString("NETWORKHDL")!= null ? Double.parseDouble(rs.getString("NETWORKHDL")) : 0);
				share.setAdministrativehdl(rs.getString("ADMINISTRATIVEHDL")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEHDL")) : 0);
				share.setMarkethdl(rs.getString("MARKETHDL")!= null ? Double.parseDouble(rs.getString("MARKETHDL")) : 0);
				share.setInformatizationhdl(rs.getString("INFORMATIZATIONHDL")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONHDL")) : 0);
				share.setBuildhdl(rs.getString("BUILDHDL")!= null ? Double.parseDouble(rs.getString("BUILDHDL")) : 0);
				share.setDddl(rs.getString("DDDFDL")!= null ? Double.parseDouble(rs.getString("DDDFDL")) : 0);//�������
				//��ѷ�̯
				share.setNetworkdf(rs.getString("NETWORKDF")!= null ? Double.parseDouble(rs.getString("NETWORKDF")) : 0);
				share.setAdministrativedf(rs.getString("ADMINISTRATIVEDF")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEDF")) : 0);
				share.setMarketdf(rs.getString("MARKETDF")!= null ? Double.parseDouble(rs.getString("MARKETDF")) : 0);
				share.setInformatizationdf(rs.getString("INFORMATIZATIONDF")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONDF")) : 0);
				share.setBuilddf(rs.getString("BUILDDF")!= null ? Double.parseDouble(rs.getString("BUILDDF")) : 0);
				share.setDddf(rs.getString("DDDFDF")!= null ? Double.parseDouble(rs.getString("DDDFDF")) : 0);//������
				elec.setEdhdl(rs.getString("edhdla") != null ? rs.getString("edhdla") : "");//
				elec.setQsdbdl(rs.getString("qsdbdla") != null ? rs.getString("qsdbdla") : "");//
				elec.setZdname(rs.getString("jzname"));
				//------С����ʽ��------
				elec.setUnitprice(Format.str4(elec.getUnitprice()));
				elec.setActualpay(Format.d2(elec.getActualpay()));
				elec.setPjje(Format.d2(elec.getPjje()));
			}
	    	//------���������ݵ�bean��������------
	    	list.add(elec);
	    	list.add(share);
	    } catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,stmt,conn);
		}
		return list;
	}
	
	
	/**
	 * @param dbid (String) //���id
	 * @return (ArrayList)
	 * @see ��ѯ������Ѹ�����Ϣ
	 * @author ZengJin 2014-2-15
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getOtherInfo(String dbid) {
		
		String sql = "select zd.zlfh,zd.jlfh, db.beilv,db.changevalue,db.linelosstype,db.linelossvalue,db.bgsign from zhandian zd,dianbiao db where db.zdid = zd.id and dbid='" + dbid + "'";
		System.out.println("��ѵ�(�޸�)��ѯ������Ϣ:");
		ArrayList list = new ArrayList();
		try {
			db.connectDb();	 
	    	conn = db.getConnection();
	    	stmt = conn.createStatement();
	    	rs = stmt.executeQuery(sql);	//����sql
			Query query = new Query();
			list = query.query(rs);	//���ս����
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,stmt,conn);
		}
		return list;
	}
	
	
	/**
	 * @param elec (ElectricityInfo) //�������bean
	 * @param share (Share) //��̯��Ϣbean
	 * @return (ArrayList)
	 * @see �������޸���Ϣ
	 * @author ZengJin 2014-2-15
	 */
		public String modifyElectricFees(ElectricityInfo elec,Share share,String dfms,String dfbz,String dlms,String dlbz,String flag,String edhdlbz,String qsdbdlbz,String qxzr,String cityzr, String jszq,String edhdl,String qsdbdl,String hbzq,String bzz) {
			
			String msg = "�޸ĵ����Ϣʧ�ܣ�";	//��ʾ��Ϣ,Ĭ��Ϊʧ��״̬
			
			String nowtime="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			//String nowtime= df.format(new Date());//��ϵͳʱ�丳��"¼��ʱ��"
			String countyaudittime = "1".equals(qxzr) ? nowtime : "NULL";//�����������ʱ��
			String cityaudittime = "1".equals(flag) ? nowtime : "NULL";//�м����ʱ��
			String cityzraudittime = "1".equals(cityzr) ? nowtime : "NULL";//�м��������ʱ��
			
			
			String sql1 = "UPDATE AMMETERDEGREES SET LASTDEGREE="+elec.getLastdegree()+", THISDEGREE="+elec.getThisdegree()+", LASTDATETIME=TO_DATE('"+elec.getLastdatetime()+"','yyyy-mm-dd'), THISDATETIME=TO_DATE('"+elec.getThisdatetime()+"','yyyy-mm-dd'), "
				 + "FLOATDEGREE="+elec.getFloatdegree()+", DBYDL="+elec.getDbydl()+", BLHDL="+elec.getBlhdl()+", MEMO='"+elec.getMemo()+"', ENTRYPENSONNEL='"+elec.getEntrypensonnel()+"', ENTRYTIME="+nowtime+"," 
				 +	"AUTOAUDITDESCRIPTION='"+dlms+"',AUTOAUDITSTATUS='"+dlbz+"',DEDHDL="+edhdlbz+",NETWORKHDL="+share.getNetworkhdl()+",INFORMATIZATIONHDL="+share.getInformatizationhdl()+",ADMINISTRATIVEHDL="+share.getAdministrativehdl()+"," 
				 +  "MARKETHDL="+share.getMarkethdl()+",BUILDHDL="+share.getBuildhdl()+",DDDF="+share.getDddl()+",INPUTOPERATOR='"+elec.getInputoperator()+"',FLAG='"+flag+"',CSDB="+qsdbdlbz+",COUNTYFLAG='"+qxzr+"',CITYFLAG='"+cityzr+"',MANUALAUDITSTATUS='0',"
				 + "TBTZSQ='"+elec.getTbtzsq()+"',PJDL="+elec.getPjdl()+",HBZQ="+hbzq+",BZZ="+bzz+",SCB="+elec.getScb()+",BEILV="+elec.getBeilv()
				 + " WHERE AMMETERDEGREEID=" + elec.getAmmeterdegree_id();
			System.out.println("�޸ĵ���sql��"+sql1);
			
			String sql2 = "UPDATE ELECTRICFEES SET UNITPRICE="+elec.getUnitprice()+", ACTUALPAY="+elec.getActualpay()+", FLOATPAY="+elec.getFloatpay()+", NOTETYPEID='"+elec.getNotetypeid()+"', "
				 + "ACCOUNTMONTH=TO_DATE('"+elec.getAccountmonth()+"','yyyy-mm'), PAYOPERATOR='"+elec.getPayoperator()+"', MEMO='"+elec.getMemo1()+"', PJJE="+elec.getPjje()+",AUTOAUDITDESCRIPTION='"+dfms+"',AUTOAUDITSTATUS='"+dfbz+"'," 
				 + "NETWORKDF="+share.getNetworkdf()+",INFORMATIZATIONDF="+share.getInformatizationdf()+",ADMINISTRATIVEDF="+share.getAdministrativedf()+",MARKETDF="+share.getMarketdf()+",BUILDDF="+share.getBuilddf()+",DDDF="+share.getDddf()+",CITYAUDIT='"+flag+"',COUNTYAUDITSTATUS='"+qxzr+"'," 
				 + "CITYZRAUDITSTATUS='"+cityzr+"',JSZQ="+jszq+",MANUALAUDITSTATUS='0',EDHDL="+edhdl+",QSDBDL="+qsdbdl+",YZLIUCHENGHAO='"+elec.getLiuchenghao()+"',COUNTYAUDITTIME="+countyaudittime+",CITYAUDITTIME="+cityaudittime+",CITYZRAUDITTIME="+cityzraudittime+","
				 + "YDDF="+elec.getYddf()+",ENTRYPENSONNEL='"+elec.getEntrypensonnel()+"', ENTRYTIME="+nowtime+",INPUTOPERATOR='"+elec.getInputoperator()+"',STATIONTYPECODE='"+elec.getStationtypecode()+"',PROPERTYCODE='"+elec.getPropertycode()+"',DFZFLXCODE='"+elec.getDfzflxcode()+"',GDFSCODE='"+elec.getGdfscode()+"'"
				 + " WHERE ELECTRICFEE_ID=" + elec.getElectricfee_id();
			System.out.println("�޸ĵ��sql��"+sql2);

			try {
				db.connectDb();	 
		    	conn = db.getConnection();
		    	ps = conn.prepareStatement(sql1);
//		    	ps.setString(1,elec.getLastdegree());
//		    	ps.setString(2,elec.getThisdegree());
//		    	ps.setString(3,elec.getLastdatetime());
//		    	ps.setString(4,elec.getThisdatetime());
//		    	ps.setString(5,elec.getFloatdegree());
//		    	ps.setString(6,elec.getDbydl());
//		    	ps.setString(7,elec.getBlhdl());
//		    	ps.setString(8,elec.getMemo());
//		    	ps.setString(9,elec.getEntrypensonnel());
//		    	ps.setString(10,elec.getEntrytime());
//		    	ps.setString(11,dlms);
//		    	ps.setString(12,dlbz);
//		    	ps.setString(13,edhdlbz);
//			    ps.setDouble(14,share.getNetworkhdl()); 
//			    ps.setDouble(15,share.getInformatizationhdl());  
//			    ps.setDouble(16,share.getAdministrativehdl()); 
//			    ps.setDouble(17,share.getMarkethdl()); 
//			    ps.setDouble(18,share.getBuildhdl()); 
//			    ps.setDouble(19,share.getDddl()); 
//			    ps.setString(20,elec.getInputoperator());
//			    ps.setString(21, flag);
//			    ps.setString(22, qsdbdlbz);
//			    ps.setString(23, qxzr);
//			    ps.setString(24, cityzr);
//			    ps.setString(25, "0");
//			    ps.setString(26,elec.getTbtzsq());
//			    ps.setString(27, elec.getPjdl());
//			    ps.setString(28, hbzq);
//			    ps.setString(29, bzz);
		    	
				int count1 = 0;
				count1 = ps.executeUpdate(); //���ض����ݿ��Ӱ������
				
		    	ps = conn.prepareStatement(sql2);
//		    	ps.setString(1,elec.getUnitprice());
//		    	ps.setDouble(2,elec.getActualpay());
//		    	ps.setString(3,elec.getFloatpay());
//		    	ps.setString(4,elec.getNotetypeid());
//		    	ps.setString(5,elec.getAccountmonth());
//		    	ps.setString(6,elec.getPayoperator());
//		    	ps.setString(7,elec.getMemo1());
//		    	ps.setDouble(8,elec.getPjje());
//		    	ps.setString(9,dfms);
//		    	ps.setString(10,dfbz);
//		    	ps.setDouble(11,share.getNetworkdf()); 
//			    ps.setDouble(12,share.getInformatizationdf());  
//			    ps.setDouble(13,share.getAdministrativedf()); 
//			    ps.setDouble(14,share.getMarketdf()); 
//			    ps.setDouble(15,share.getBuilddf()); 
//			    ps.setDouble(16,share.getDddf()); 
//			    ps.setString(17, flag);
//			    ps.setString(18, qxzr);
//			    ps.setString(19, cityzr);
//			    ps.setString(20, jszq);
//			    ps.setString(21, "0");
//			    ps.setString(22, edhdl);
//			    ps.setString(23, qsdbdl);
//			    ps.setString(24, elec.getLiuchenghao());
//			    ps.setString(25, countyaudittime);
//			    ps.setString(26, cityaudittime);
//			    ps.setString(27, cityzraudittime);
//			    ps.setString(28, elec.getYddf());
//			    ps.setString(29, elec.getEntrypensonnel());
//			    ps.setString(30, elec.getEntrytime());
//			    ps.setString(31, elec.getInputoperator());

				int count2 = 0;
				count2 = ps.executeUpdate(); //���ض����ݿ��Ӱ������
				
				if(count1==1&count2==1){
					msg = "��ѵ��޸ĳɹ�!!";	//��������޸���һ��,����ʾ�ɹ�
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					db.rollback();
				} catch (DbException e1) {
					e1.printStackTrace();
				}
			} finally {
				db.free(null,ps,conn);
			}
			return msg;
		}
		/**
		 * @author wangyixiao
		 * 
		 * @see ��ѵ�ҳ���ȡ����������һ�εĳ������
		 * 
		 * @param zdcode String ��������
		 * 
		 * @return bean ����һ��ElectricityInfo��bean
		 */
		@Override
		public ElectricityInfo elec1(String jsdbid) {
			DataBase db = null;
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			ElectricityInfo bean = new ElectricityInfo();
			StringBuffer sql = new StringBuffer();
			String dbid = "";
			String sql1 = "SELECT D.DBID FROM DIANBIAO D WHERE D.DBYT = 'dbyt03' AND D.ZDID = (SELECT ZD.ID FROM ZHANDIAN ZD, DIANBIAO DB WHERE DB.ZDID = ZD.ID AND DB.DBID = '"+jsdbid+"')";
			try {
				db = new DataBase();
				conn = db.getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(sql1);
				if (rs.next()){
					dbid = rs.getString("dbid");
				}
				sql.append("SELECT AM.THISDEGREE,TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AM.LASTDEGREE,TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,AM.MANUALAUDITNAME,AM.INPUTOPERATOR,AM.AMMETERID_FK FROM ZHANDIAN Z,  DIANBIAO D, AMMETERDEGREES AM, "
						+ "(SELECT MAX(T.THISDATETIME) THISDATETIME,T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK ='"
						+ dbid
						+ "'  GROUP BY AMMETERID_FK) DD"
						+ " WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID  AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'");
				System.out.println("��ȡ������ڵ�����:"+sql);
				
				
				rs = st.executeQuery(sql.toString());
				if (rs.next()) {
					bean.setGldlbz(true);
					bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs
							.getString("thisdatetime") : "");
					bean.setThisdegree(rs.getString("thisdegree") != null ? rs
							.getString("thisdegree") : "");
					bean.setLastdegree(rs.getString("lastdegree") != null ? rs
							.getString("lastdegree") : "");
					bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs
							.getString("lastdatetime") : "");
					bean.setManualauditname(rs.getString("manualauditname") != null ? rs
							.getString("manualauditname"):"");
					bean.setDlinputoperator(rs.getString("inputoperator") != null ? rs
							.getString("inputoperator"):"");
				}else{
					bean.setGldlbz(false);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				db.free(rs, st, conn);
			}
			return bean;
		}
		
		/**
		 * @author wangyixiao 2014-6-20
		 * 
		 * ���ڵ��������ڵ�ѣ����ڵ���
		 * 
		 * @param dbid String ��������
		 * 
		 * @return bean ����һ��AssistInfo��bean1
		 * 
		 * @update WangYiXiao 2014.4.9  ɾ���ϴ�¼��ʱ��
		 */
		@Override
		public AssistInfo lastInfo(String dbid) {
			StringBuffer sql = new StringBuffer();
			AssistInfo bean1 = new AssistInfo();
			sql.append("SELECT S.BLHDL YDL,E.ACTUALPAY ACTUALPAY,E.UNITPRICE UNITPRICE"
							+ " FROM(SELECT  MAX(T.THISDATETIME) LASTDATETIME,"
							+ "T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK "
							+ "AND D.CITYAUDIT = '1' AND D.CITYZRAUDITSTATUS = '1' AND AMMETERID_FK = '"
							+ dbid
							+ "' GROUP BY T.AMMETERID_FK) A,"
							+ "DIANDUVIEW S,DIANFEIVIEW E "
							+ "WHERE S.AMMETERID_FK=A.AMMETERID_FK AND S.THISDATETIME=A.LASTDATETIME "
							+ "AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK");

			System.out.println("������Ϣsql:"+sql);
			DataBase db = new DataBase();
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			try {
				conn = db.getConnection();
				st = conn.createStatement();
				rs = st.executeQuery(sql.toString());
				if (rs.next()) {
					bean1.setBlhdl(Format.str2(rs.getString("ydl") != null ? rs.getString("ydl") : "0.00"));
					bean1.setActualpay(Format.str_d(rs.getString("actualpay") != null ? Format.str2(rs.getString("actualpay")) : "0.00"));
					bean1.setUnitprice(rs.getString("unitprice")!=null?Format.str4(rs.getString("unitprice")):"");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.free(rs, st, conn);
			}
			return bean1;
		}
}
