package com.ptac.app.electricmanage.electricitybill.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

/**
 * @author lijing
 * @see ��ѵ����ҳ������Ϣ�����ֶ�
 */
public class ElectricityInfo {
	
	private String dbid;//���ID

	//������
 
	private String ammeterdegree_id;//����id
	private String lastdegree;//�ϴε�����
	private String thisdegree;//���ε�����
	private String actualdegree;//�õ���
	private String lastdatetime;//�ϴγ���ʱ��+1	
	private String lasttime;//���γ���ʱ�䣨���ı��γ���ʱ�� ���� 1��
	private String thisdatetime;//���γ���ʱ��
	private String floatdegree;//�õ�������	
	private String startmonth;//��ʼ����
	private String blhdl;//ʵ���õ���(���ʺĵ���)
	private String memo;//(����)��ע	
	private String endmonth;//��������
    private String entrypensonnel;
    private String entrytime;
    private String dedhdl;//��ĵ��������ֵ
    private String csdb;//��ʡ�������ֵ
    private String scdf;//�ϴε��
    private String scdl;//�ϴε���
    private String lastunitprice;//�ϴε���
    private String manualauditname;//�˹�����ˣ�����ˣ�
    private String dlinputoperator;//�����������Ա
    private String pjdl;//Ʊ�ݵ���
    private String tbtzsq;//�ر��������
    private String dbydl;//����õ���2014-10-24 WangYiXiao
	
	//��ѱ�	    		
	private String electricfee_id;//���id;
    private String kptime;
	private String unitprice;//���ε���
	private String floatpay;//���õ���
	private String memo1;//(���)��ע
	private String accountmonth;//�����·�
	private double actualpay;//ʵ�ʵ�ѽ��
	private String notetypeid;//Ʊ������	
	private String noteno;//Ʊ�ݱ��
	private String notetime;//Ʊ��ʱ��
	private String inputoperator;//�������Ա
	private String paydatetime;//����ʱ��
	private String payoperator;//���Ѳ���Ա
	private double pjje;//Ʊ�ݽ��
	private int flag;
	private String csds;
	private String cssytime;
	private String liuchenghao;//Ԥ֧�����̺�
	private String yddf;//�õ���
	private String jfzb;//���ռ��
	private String bfzb;//����ռ��
	private String bpzb;//��ƽռ��
	private String bgzb;//����ռ��
	//2014-7-17
	private String stationtypecode;//վ������code
	private String propertycode;//վ������code
	private String dfzflxcode;//���֧������code
	private String gdfscode;//���緽ʽcode
	private String zlfh;//ֱ������
	private String jlfh;//��������
	private String beilv;//����
	private String changevalue;//����ֵ
	private String linelosstype;//��������
	private String linelossvalue;//����ֵ
	private String actuallinelossvalue;//ʵ������ֵ
	private String scb;//������2-14-10-22

	//վ���
	private String edhdl;//��ĵ���
	private String qsdbdl;//ȫʡ�������
	private String shi;//���� �����Ƿ��Ǽ����ж�
	
	private boolean gldlbz;//���������ʶ
	private String zdname;//վ������
	
	
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public ElectricityInfo(){
		
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbid() {
		return dbid;
	}
	public String getCsds() {
		return csds;
	}
	public void setCsds(String csds) {
		this.csds = csds;
	}
	public String getCssytime() {
		return cssytime;
	}
	public void setCssytime(String cssytime) {
		this.cssytime = cssytime;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	public String getLastdatetime() {
		return lastdatetime;
	}
	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}
	public String getThisdatetime() {
		return thisdatetime;
	}
	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getMemo1() {
		return memo1;
	}
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public double getActualpay() {
		return actualpay;
	}
	public void setActualpay(double actualpay) {
		this.actualpay = actualpay;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getInputoperator() {
		return inputoperator;
	}
	public void setInputoperator(String inputoperator) {
		this.inputoperator = inputoperator;
	}
	public String getPayoperator() {
		return payoperator;
	}
	public void setPayoperator(String payoperator) {
		this.payoperator = payoperator;
	}
	public double getPjje() {
		return pjje;
	}
	public void setPjje(double pjje) {
		this.pjje = pjje;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
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
	
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	
	public String getDedhdl() {
		return dedhdl;
	}
	public void setDedhdl(String dedhdl) {
		this.dedhdl = dedhdl;
	}
	
	public String getCsdb() {
		return csdb;
	}
	public void setCsdb(String csdb) {
		this.csdb = csdb;
	}
	public synchronized ElectricityInfo getChuShiDuShuDegree(String ammeterid) {
		ElectricityInfo bean=new ElectricityInfo();
		  StringBuffer sql = new StringBuffer();
		  sql.append("select db.csds,to_char(db.cssytime,'yyyy-mm-dd') cssytime from dianbiao db where db.dbid='"+ammeterid+"' and db.dbyt = 'dbyt01'");
		  DataBase db = new DataBase();
		  try{
			  System.out.println("��ȡ��ʼ������:");
			  db.connectDb();
		      ResultSet rs = null;
		      
		      rs = db.queryAll(sql.toString());
		      if(rs.next()){
		    	  bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		    	  bean.setCssytime(rs.getString("cssytime") != null ? rs.getString("cssytime") : "");
		      }
	    }
	    catch (DbException de) {
	      de.printStackTrace();
	    }
	    catch (SQLException se) {
	      se.printStackTrace();
	    }
	    finally {
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }
	    }
	    return bean;
	  }
	public void setKptime(String kptime) {
		this.kptime = kptime;
	}
	public String getKptime() {
		return kptime;
	}
	public String getLiuchenghao() {
		return liuchenghao;
	}
	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}
	public void setAmmeterdegree_id(String ammeterdegree_id) {
		this.ammeterdegree_id = ammeterdegree_id;
	}
	public String getAmmeterdegree_id() {
		return ammeterdegree_id;
	}
	public void setElectricfee_id(String electricfee_id) {
		this.electricfee_id = electricfee_id;
	}
	public String getElectricfee_id() {
		return electricfee_id;
	}
	public String getScdf() {
		return scdf;
	}
	public void setScdf(String scdf) {
		this.scdf = scdf;
	}
	public String getScdl() {
		return scdl;
	}
	public void setScdl(String scdl) {
		this.scdl = scdl;
	}
	public String getLastunitprice() {
		return lastunitprice;
	}
	public void setLastunitprice(String lastunitprice) {
		this.lastunitprice = lastunitprice;
	}
	public String getManualauditname() {
		return manualauditname;
	}
	public void setManualauditname(String manualauditname) {
		this.manualauditname = manualauditname;
	}
	public String getDlinputoperator() {
		return dlinputoperator;
	}
	public void setDlinputoperator(String dlinputoperator) {
		this.dlinputoperator = dlinputoperator;
	}
	public String getPjdl() {
		return pjdl;
	}
	public void setPjdl(String pjdl) {
		this.pjdl = pjdl;
	}
	public String getYddf() {
		return yddf;
	}
	public void setYddf(String yddf) {
		this.yddf = yddf;
	}
	public String getTbtzsq() {
		return tbtzsq;
	}
	public void setTbtzsq(String tbtzsq) {
		this.tbtzsq = tbtzsq;
	}
	public String getJfzb() {
		return jfzb;
	}
	public void setJfzb(String jfzb) {
		this.jfzb = jfzb;
	}
	public String getBfzb() {
		return bfzb;
	}
	public void setBfzb(String bfzb) {
		this.bfzb = bfzb;
	}
	public String getBpzb() {
		return bpzb;
	}
	public void setBpzb(String bpzb) {
		this.bpzb = bpzb;
	}
	public String getBgzb() {
		return bgzb;
	}
	public void setBgzb(String bgzb) {
		this.bgzb = bgzb;
	}
	public boolean isGldlbz() {
		return gldlbz;
	}
	public void setGldlbz(boolean gldlbz) {
		this.gldlbz = gldlbz;
	}
	public String getStationtypecode() {
		return stationtypecode;
	}
	public void setStationtypecode(String stationtypecode) {
		this.stationtypecode = stationtypecode;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public String getDfzflxcode() {
		return dfzflxcode;
	}
	public void setDfzflxcode(String dfzflxcode) {
		this.dfzflxcode = dfzflxcode;
	}
	public String getGdfscode() {
		return gdfscode;
	}
	public void setGdfscode(String gdfscode) {
		this.gdfscode = gdfscode;
	}
	public String getZlfh() {
		return zlfh;
	}
	public void setZlfh(String zlfh) {
		this.zlfh = zlfh;
	}
	public String getJlfh() {
		return jlfh;
	}
	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getChangevalue() {
		return changevalue;
	}
	public void setChangevalue(String changevalue) {
		this.changevalue = changevalue;
	}
	public String getLinelosstype() {
		return linelosstype;
	}
	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}
	public String getLinelossvalue() {
		return linelossvalue;
	}
	public void setLinelossvalue(String linelossvalue) {
		this.linelossvalue = linelossvalue;
	}
	public String getActuallinelossvalue() {
		return actuallinelossvalue;
	}
	public void setActuallinelossvalue(String actuallinelossvalue) {
		this.actuallinelossvalue = actuallinelossvalue;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getDbydl() {
		return dbydl;
	}
	public void setDbydl(String dbydl) {
		this.dbydl = dbydl;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}

	
	
}
