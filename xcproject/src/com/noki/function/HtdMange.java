package com.noki.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.ammeterdegree.javabean.BargainBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;

public class HtdMange {
	private String stationid;//վ��id
	private String prepayment_ammeterid;//���id
	private String feetypeid;//����id
	private String money;//���
	private int ammeterdegreeid_fk;//����id
	private String memo;
	private String notetypeid;//Ʊ������
	private String bargain;//Ʊ�ݱ��
	private String notetime;//Ʊ��ʱ��
	private String noteno;
	private String kptime;//��Ʊʱ��
	private String accountmonth;//�����·�
	private String startmonth;
	private String endmonth;
	private int id;
	private String zdcode;
	private String jzname;
	private String dbname;
	private String dfzflx;
	private String entrypensonnel;
	private String entrytime;
	private double pjje;
	private String htbianhao;
	
	private String thisdegree;
	private String thisdatetime;
	private String linelosstype;
	private String floatpay;
	private String inputoperator;
	private String paydatetime;
	private String payoperator;
	private String danjia;
	
	private String networkdf;//������Ӫ�ߵ��(����)
	private String informatizationdf;//��Ϣ��֧���ߵ��
	private String administrativedf;//�����ۺ��ߵ�ѣ��칫��
	private String marketdf;//�г�Ӫ���ߵ��(Ӫҵ)
	private String builddf;//����Ͷ���ߵ��
	private String dddf;//������
	private String fpje;
	private String fplx;
	private String uuid;
	
	
	 public String getDddf() {
		return dddf;
	}
	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFpje() {
		return fpje;
	}
	public void setFpje(String fpje) {
		this.fpje = fpje;
	}
	public String getFplx() {
		return fplx;
	}
	public void setFplx(String fplx) {
		this.fplx = fplx;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
	public String getPrepayment_ammeterid() {
		return prepayment_ammeterid;
	}
	public void setPrepayment_ammeterid(String prepaymentAmmeterid) {
		prepayment_ammeterid = prepaymentAmmeterid;
	}
	public String getFeetypeid() {
		return feetypeid;
	}
	public void setFeetypeid(String feetypeid) {
		this.feetypeid = feetypeid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public int getAmmeterdegreeid_fk() {
		return ammeterdegreeid_fk;
	}
	public void setAmmeterdegreeid_fk(int ammeterdegreeidFk) {
		ammeterdegreeid_fk = ammeterdegreeidFk;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getBargain() {
		return bargain;
	}
	public void setBargain(String bargain) {
		this.bargain = bargain;
	}
	public String getNotetime() {
		return notetime;
	}



	public void setNotetime(String notetime) {
		this.notetime = notetime;
	}



	public String getNoteno() {
		return noteno;
	}



	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}



	public String getKptime() {
		return kptime;
	}



	public void setKptime(String kptime) {
		this.kptime = kptime;
	}



	public String getAccountmonth() {
		return accountmonth;
	}



	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}



	public String getStartmonth() {
		return startmonth;
	}



	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}



	public String getEndmonth() {
		return endmonth;
	}



	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getZdcode() {
		return zdcode;
	}



	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}



	public String getJzname() {
		return jzname;
	}



	public void setJzname(String jzname) {
		this.jzname = jzname;
	}



	public String getDbname() {
		return dbname;
	}



	public void setDbname(String dbname) {
		this.dbname = dbname;
	}



	public String getDfzflx() {
		return dfzflx;
	}



	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}



	public String getEntrypensonnel() {
		return entrypensonnel;
	}



	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}



	public String getEntrytime() {
		return entrytime;
	}



	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}



	public double getPjje() {
		return pjje;
	}



	public void setPjje(double pjje) {
		this.pjje = pjje;
	}



	public String getHtbianhao() {
		return htbianhao;
	}



	public void setHtbianhao(String htbianhao) {
		this.htbianhao = htbianhao;
	}



	public String getThisdegree() {
		return thisdegree;
	}



	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}



	public String getThisdatetime() {
		return thisdatetime;
	}



	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}



	public String getLinelosstype() {
		return linelosstype;
	}



	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}



	public String getFloatpay() {
		return floatpay;
	}



	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}



	public String getInputoperator() {
		return inputoperator;
	}



	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
	}



	public String getPaydatetime() {
		return paydatetime;
	}



	public void setPaydatetime(String paydatetime) {
		this.paydatetime = paydatetime;
	}



	public String getPayoperator() {
		return payoperator;
	}



	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}



	public String getDanjia() {
		return danjia;
	}



	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}



	public String getNetworkdf() {
		return networkdf;
	}



	public void setNetworkdf(String networkdf) {
		this.networkdf = networkdf;
	}



	public String getInformatizationdf() {
		return informatizationdf;
	}



	public void setInformatizationdf(String informatizationdf) {
		this.informatizationdf = informatizationdf;
	}



	public String getAdministrativedf() {
		return administrativedf;
	}



	public void setAdministrativedf(String administrativedf) {
		this.administrativedf = administrativedf;
	}



	public String getMarketdf() {
		return marketdf;
	}



	public void setMarketdf(String marketdf) {
		this.marketdf = marketdf;
	}



	public String getBuilddf() {
		return builddf;
	}



	public void setBuilddf(String builddf) {
		this.builddf = builddf;
	}



	public  synchronized List<HtdMange> getBargainDana(String id) {
		List<HtdMange> list=new ArrayList<HtdMange>();
		    StringBuffer sql = new StringBuffer();
		    ResultSet rs = null;
		    String sql1="";
		    sql1="SELECT P.ID,P.HTBH,P.PJJE,ZD.ID AS ZDID,P.PREPAYMENT_AMMETERID,P.MONEY, (SELECT I.NAME FROM INDEXS I WHERE I.CODE = D.DFZFLX AND I.TYPE = 'dfzflx') AS DFZFLX,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=P.NOTETYPEID AND I.TYPE='PJLX') AS NOTETYPEID,P.MEMO,P.NOTENO,TO_CHAR(P.NOTETIME,'yyyy-mm-dd') NOTETIME,TO_CHAR(P.KPTIME,'yyyy-mm-dd') KPTIME,"+
                "  TO_CHAR(P.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(P.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(P.ENDMONTH,'yyyy-mm') ENDMONTH,ZD.ZDCODE,D.DBNAME,ZD.JZNAME,P.NETWORKDF,P.ADMINISTRATIVEDF,P.MARKETDF,P.INFORMATIZATIONDF,"+
                 "  P.BUILDDF,ZD.DIANFEI,P.FPJE,'��Ʊ' AS FPLX,p.dddf"+
                 "  FROM DIANBIAO D,YUFUFEIVIEW P,ZHANDIAN ZD WHERE ZD.ID = P.STATIONID AND ZD.ID = D.ZDID AND D.DBID = P.PREPAYMENT_AMMETERID AND P.UUID="+id+"";
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select p.id,p.htbh,p.pjje,zd.id as zdid,p.PREPAYMENT_AMMETERID,p.MONEY,d.dfzflx,p.NOTETYPEID,p.memo,p.NOTENO,p.NOTETIME,p.KPTIME,p.ACCOUNTMONTH,p.STARTMONTH,p.ENDMONTH,zd.zdcode,d.dbname,zd.jzname,p.networkdf,p.administrativedf,p.marketdf,p.informatizationdf,p.builddf,zd.dianfei from dianbiao d,yufufeiview p,zhandian zd  " +
		    		//"where zd.id =p.stationid and zd.id=d.zdid and d.dbid = p.prepayment_ammeterid and p.id = "+id+"");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getElectricFeesInfo:"+sql1);
		      db.connectDb();
		     
		      rs = db.queryAll(sql1.toString());
		      while (rs.next()) {
		    	  HtdMange bean = new HtdMange();
			    bean.setId(rs.getInt(1));	
			    bean.setHtbianhao(rs.getString(2));
			    bean.setPjje(rs.getDouble(3));
			    bean.setStationid(rs.getString(4));
			    bean.setPrepayment_ammeterid(rs.getString(5));	
			    bean.setMoney(rs.getString(6));
			    bean.setDfzflx(rs.getString(7));
			    bean.setNotetypeid(rs.getString(8));
			    bean.setMemo(rs.getString(9));	
			    bean.setNoteno(rs.getString(10));
			    bean.setNotetime(rs.getString(11));	
			    bean.setKptime(rs.getString(12));  
			    bean.setAccountmonth(rs.getString(13));
			    bean.setStartmonth(rs.getString(14));	
			    bean.setEndmonth(rs.getString(15));
			    bean.setZdcode(rs.getString(16));
		        bean.setDbname(rs.getString(17));
		        bean.setJzname(rs.getString(18));
		        //��ȡ��̯����Ľ�������Ӫ�ߵ��(����)����Ϣ��֧���ߵ�ѡ������ۺ��ߵ�ѣ��칫�����г�Ӫ���ߵ��(Ӫҵ)������Ͷ���ߵ��,�����ѣ�
		        bean.setNetworkdf(rs.getString(19));
		        bean.setAdministrativedf(rs.getString(20));
		        bean.setMarketdf(rs.getString(21));
		        bean.setInformatizationdf(rs.getString(22));
		       	bean.setBuilddf(rs.getString(23));
		        //��ѵ���
		        bean.setDanjia(rs.getString(24));	    	
		    	bean.setFpje(rs.getString(25));
		    	bean.setFplx(rs.getString(26));
		    	bean.setDddf(rs.getString(27));
		    	list.add(bean);
		      }
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				if(db!=null){
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
				}
			}
		    return list;
		  }

	public synchronized String modifyBargain(HtdMange formBean,String id) {
		
		String msg = "��ͬ����¼��Ʊʧ�ܣ�";
		DataBase db = new DataBase();	
		String mm="";
		try {
			    db.connectDb();         
        		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
               // String delete="delete from prepayment p where  p.uuid=(select uuid from prepayment where id='"+id+"')";
                //System.out.println("--"+delete.toString());entrypensonnel
        		
        		mm="UPDATE PREPAYMENT  SET ENTRYPENSONNEL='"+formBean.getEntrypensonnel()+"',ENTRYTIME="+s+",FPJE='"+formBean.getFpje()+"',FPLX='pjlx02',FPSH='0'  WHERE UUID='"+formBean.getUuid()+"' ";
//        			StringBuffer sql = new StringBuffer();
//        			sql.append("INSERT INTO PREPAYMENT(ENTRYPERSONNEL,ENTRYTIME,FPJE,FPLX,ID)");
//        			sql.append(" VALUES('"+formBean.getEntrypensonnel()+"',"+s+",'"+formBean.getFpje()+"','"+formBean.getFplx()+"','"+formBean.getId()+"' )");
    			 System.out.println("--"+mm.toString());
			msg = "��ͬ����¼��Ʊʧ�ܣ�";
			
			//db.update(delete);
			db.setAutoCommit(false);
			db.update(mm.toString());
			db.commit();
			
			db.close();
			//db.update(sql.toString());
			msg = "��ͬ����¼��Ʊ�ɹ���";
			
		} catch (DbException de) {
			try {
				db.rollback();
				db.close();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {
				if(db!=null){
					db.close();
				}
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

}
