package com.noki.ammeterdegree.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.sysconfig.javabean.AutoAuditBean;

public class BargainBean {
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
	private String jszq;
	private String csdb;
	private String dedhdl;
	
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
	private String dddf;//������ ��������

	private String beilv;//����
	private String shicode;//��
	private String property;//վ������
	private String scb;//������
	private String zlfh;//ֱ������
	private String jlfh;//��������
	private String stationtypecode;//վ������code
	private String gdfscode;//���緽ʽcode
	private String dfzflxcode;//���֧������code
	private String propertycode;//վ������code
	private String qsdbdl;//ȫʡ�������
	private String edhdl;//��ĵ���
	
	
	public String getQsdbdl() {
		return qsdbdl;
	}
	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}
	public String getEdhdl() {
		return edhdl;
	}
	public void setEdhdl(String edhdl) {
		this.edhdl = edhdl;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public String getStationtypecode() {
		return stationtypecode;
	}
	public void setStationtypecode(String stationtypecode) {
		this.stationtypecode = stationtypecode;
	}
	public String getGdfscode() {
		return gdfscode;
	}
	public void setGdfscode(String gdfscode) {
		this.gdfscode = gdfscode;
	}
	public String getDfzflxcode() {
		return dfzflxcode;
	}
	public void setDfzflxcode(String dfzflxcode) {
		this.dfzflxcode = dfzflxcode;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getShicode() {
		return shicode;
	}
	public void setShicode(String shicode) {
		this.shicode = shicode;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
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
	public String getJszq() {
		return jszq;
	}
	public void setJszq(String jszq) {
		this.jszq = jszq;
	}
	public String getCsdb() {
		return csdb;
	}
	public void setCsdb(String csdb) {
		this.csdb = csdb;
	}
	public String getDedhdl() {
		return dedhdl;
	}
	public void setDedhdl(String dedhdl) {
		this.dedhdl = dedhdl;
	}
	public String getDddf() {
		return dddf;
	}
	public void setDddf(String dddf) {
		this.dddf = dddf;
	}
	public String getHtbianhao() {
		return htbianhao;
	}
	public void setHtbianhao(String htbianhao) {
		this.htbianhao = htbianhao;
	}
	public double getPjje() {
		return pjje;
	}
	public void setPjje(double pjje) {
		this.pjje = pjje;
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
	public String getNoteno() {
		return noteno;
	}
	public void setNoteno(String noteno) {
		this.noteno = noteno;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	 public synchronized BargainBean getBargainDan(String id) {
		 BargainBean bean = new BargainBean();
		    StringBuffer sql = new StringBuffer();
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    sql.append("select p.id,p.htbh,p.pjje,zd.id as zdid,p.PREPAYMENT_AMMETERID," +
		    		"p.MONEY,d.dfzflx,p.NOTETYPEID,p.memo,p.NOTENO,TO_CHAR(p.NOTETIME,'yyyy-mm-dd') NOTETIME,TO_CHAR(p.KPTIME,'yyyy-mm-dd') KPTIME," +
		    		"TO_CHAR(p.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(p.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(p.ENDMONTH,'yyyy-mm') ENDMONTH,zd.zdcode,d.dbname,zd.jzname," +
		    		"p.networkdf,p.administrativedf,p.marketdf,p.informatizationdf,p.builddf," +
		    		"d.danjia,p.dddf,p.jszq,p.csdb,p.dedhdl," +
		    		"d.beilv,zd.property,zd.zlfh,zd.jlfh,zd.scb,zd.shi shicode,zd.stationtype,zd.gdfs,zd.qsdbdl,zd.edhdl "+
		    		"from dianbiao d,yufufeiview p,zhandian zd  " +
		    		"where zd.id=d.zdid and d.dbid = p.prepayment_ammeterid and p.id = "+id+"");
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("getElectricFeesInfo:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
			    bean.setPrepayment_ammeterid(rs.getString("prepayment_Ammeterid") != null ? rs.getString("prepayment_Ammeterid") : "");		    	
			    bean.setId(rs.getInt(1));	
			    System.out.println(rs.getInt(1)+"woshi");
			    //bean.setFeetypeid(rs.getString("feetypeid") != null ? rs.getString("feetypeid") : "");	
			    bean.setMoney(rs.getString("money") != null ? rs.getString("money") : "");
			    bean.setHtbianhao(rs.getString("htbh")!= null ? rs.getString("htbh") : "");
		    	bean.setStartmonth(rs.getString("startmonth") != null ? rs.getString("startmonth") : "");		    	
		    	bean.setAccountmonth(rs.getString("accountmonth") != null ? rs.getString("accountmonth") : "");		    	
		    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");		    	
		    	bean.setNotetypeid(rs.getString("notetypeid") != null ? rs.getString("notetypeid") : "");
		    	bean.setNotetime(rs.getString("notetime") != null ? rs.getString("notetime") : "");	
		    	bean.setNoteno(rs.getString("noteno") != null ? rs.getString("noteno") : "");	
		    	bean.setKptime(rs.getString("kptime") != null ? rs.getString("kptime") : "");		
		        bean.setEndmonth(rs.getString("endmonth") !=null ? rs.getString("endmonth") : "");
		        bean.setZdcode(rs.getString("zdcode") != null ? rs.getString("zdcode") : "");
		        bean.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
		        bean.setDbname(rs.getString("dbname") != null ? rs.getString("dbname") : "");
		        bean.setDfzflx(rs.getString("dfzflx")!=null? rs.getString("dfzflx") : "");
		        bean.setStationid(rs.getString("zdid")!=null? rs.getString("zdid") : "");
		        bean.setPjje(rs.getDouble("pjje"));
		        bean.setShicode(rs.getString("shicode")!=null?rs.getString("shicode"):"");
		        bean.setProperty(rs.getString("property")!=null?rs.getString("property"):"");
		        bean.setScb(rs.getString("scb")!=null?rs.getString("scb"):"");
		        bean.setZlfh(rs.getString("zlfh")!=null?rs.getString("zlfh"):"");
		        bean.setJlfh(rs.getString("jlfh")!=null?rs.getString("jlfh"):"");
		        bean.setStationtypecode(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
		        bean.setGdfscode(rs.getString("gdfs")!=null?rs.getString("gdfs"):"");
		        bean.setEdhdl(rs.getString("edhdl")!=null?rs.getString("edhdl"):"");
		        bean.setQsdbdl(rs.getString("qsdbdl")!=null?rs.getString("qsdbdl"):"");
		        
		        //��ȡ��̯����Ľ�������Ӫ�ߵ��(����)����Ϣ��֧���ߵ�ѡ������ۺ��ߵ�ѣ��칫�����г�Ӫ���ߵ��(Ӫҵ)������Ͷ���ߵ�ѡ�������(����)��
		        bean.setNetworkdf(rs.getString("networkdf")!=null? rs.getString("networkdf") : "0.00");
		        bean.setInformatizationdf(rs.getString("informatizationdf")!=null? rs.getString("informatizationdf") : "0.00");
		        bean.setAdministrativedf(rs.getString("administrativedf")!=null? rs.getString("administrativedf") : "0.00");
		        bean.setMarketdf(rs.getString("marketdf")!=null? rs.getString("marketdf") : "0.00");
		        bean.setBuilddf(rs.getString("builddf")!=null? rs.getString("builddf") : "0.00");
		        bean.setDddf(rs.getString("dddf")!=null?rs.getString("dddf"):"0.00");
		        //��ѵ���
		        bean.setDanjia(rs.getString("danjia")!=null? rs.getString("danjia") : "0.0000");
		        bean.setJszq(rs.getString("jszq")!=null? rs.getString("jszq") : "0");
		        bean.setCsdb(rs.getString("csdb")!=null? rs.getString("csdb") : "0");
		        bean.setDedhdl(rs.getString("dedhdl")!=null? rs.getString("dedhdl") : "0");
		        
		      }
		      rs.close();
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
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public synchronized String modifyBargain(BargainBean formBean,String id,String start,String end) {
		String msg = "�޸ĺ�ͬ����Ϣʧ�ܣ�";
		DataBase db = new DataBase();	
		try {
			    db.connectDb();         
            	String kptime=formBean.getKptime();
        		String noteno=formBean.getNoteno();
        		String notetime=formBean.getNotetime();
        		String memo=formBean.getMemo();
        		String danjia=formBean.getDanjia();
        		String notetypeid=formBean.getNotetypeid();
        		double pjje=formBean.getPjje();
        		String htbh=formBean.getHtbianhao();
        		if(kptime==null){
        			kptime=" ";			
        		}else{
        			kptime=formBean.getKptime();
        		}
        		if(formBean.getDanjia()==null){
        			danjia="";
        		}
        		if(notetime==null){
        			notetime=" ";
        		}else{
        			notetime=formBean.getNotetime();
        		}
        		if(memo==null){
        			memo=" ";			
        		}else{
        			memo=formBean.getMemo();
        		}
        		if(notetypeid==null){
        			notetypeid=" ";
        		}else{
        			notetypeid=formBean.getNotetypeid();
        		}
        		if(noteno==null){
        			noteno=" ";
        		}else{
        			noteno=formBean.getNoteno();
        		}
        		
        		int startn = Integer.parseInt(start.substring(0, 4));
        		int starty = Integer.parseInt(start.substring(5, 7));
        		int endn = Integer.parseInt(end.substring(0, 4));
        		int endy = Integer.parseInt(end.substring(5, 7));
        		int time = (endn - startn) * 12 + endy - starty + 1;
        		
//        		long uuid = System.currentTimeMillis();
        		long uuid1 = System.currentTimeMillis();
        		String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
        		String uuid = uuid2+Long.toString(uuid1).substring(3);        		
        		
        		String df = formBean.getMoney();
        		double dfyu = Double.parseDouble(df)%time;
        		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;

        	    //������Ӫ�ߵ��(����)�ֵ�ÿ����
        	    double networkdfys=Double.parseDouble(formBean.getNetworkdf())%time;
        	    int networkdf = (int) ((Double.parseDouble(formBean.getNetworkdf())-networkdfys)/time);
        	    //��Ϣ��֧���ߵ�ѷֵ�ÿ����
        	    double informatizationdfys=Double.parseDouble(formBean.getInformatizationdf())%time;
        	    int informatizationdf = (int) ((Double.parseDouble(formBean.getInformatizationdf())-informatizationdfys)/time);	    
        	    //�����ۺ��ߵ�ѣ��칫���ֵ�ÿ����
        	    double administrativedfys=Double.parseDouble(formBean.getAdministrativedf())%time;
        	    int administrativedf = (int) ((Double.parseDouble(formBean.getAdministrativedf())-administrativedfys)/time);
        	    //�г�Ӫ���ߵ��(Ӫҵ)�ֵ�ÿ����
        	    double marketdfys=Double.parseDouble(formBean.getMarketdf())%time;
        	    int marketdf = (int) ((Double.parseDouble(formBean.getMarketdf())-marketdfys)/time);
        	    //����Ͷ���ߵ�ѷֵ�ÿ����
        	    double builddfys=Double.parseDouble(formBean.getBuilddf())%time;
        	    int builddf = (int) ((Double.parseDouble(formBean.getBuilddf())-builddfys)/time); 
        	    //�����������ߵ�ѷֵ�ÿ����
        	    double dddfys=Double.parseDouble(formBean.getDddf())%time;
        	    int dddf = (int) ((Double.parseDouble(formBean.getDddf())-dddfys)/time); 
        	   	 List nwdffentan = new ArrayList();
        		 List imdffentan = new ArrayList();
        		 List asdffentan = new ArrayList();
        		 List mkdffentan = new ArrayList();
        		 List bldffentan = new ArrayList();
        		 List dddffentan=new ArrayList();
        		List dflist = new ArrayList();
        		 List year_month = new ArrayList();
        		String[] sqlBatch = new String[time];
        		for (int i = 0; i < time; i++) {
        			String yue = String.valueOf(starty);
        	     	if(yue.length()==1)yue = "0"+yue;
        	     	String year_month_tmp = startn+"-"+yue;
        	     	starty ++;
        	     	if(starty>12){
        	     		starty = 1;
        	     		startn ++;
        	     	}
        	     	year_month.add(year_month_tmp);
        			if (i == time - 1){
        				dflist.add(dfPermonth + dfyu);
        				nwdffentan.add(networkdf+networkdfys);
        				imdffentan.add(informatizationdf+informatizationdfys);
        				asdffentan.add(administrativedf+administrativedfys);
        				mkdffentan.add(marketdf+marketdfys);
        				bldffentan.add(builddf+builddfys);
        				dddffentan.add(dddf+dddfys);
        			}else{
        				dflist.add(dfPermonth);
        				nwdffentan.add(networkdf);
        				imdffentan.add(informatizationdf);
        				asdffentan.add(administrativedf);
        				mkdffentan.add(marketdf);
        				bldffentan.add(builddf);
        				dddffentan.add(dddf);
        			}
        		}
        		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
                String delete="delete from prepayment p where  p.uuid=(select uuid from prepayment where id='"+id+"')";
                System.out.println("--"+delete.toString());
        		for (int i = 0; i < time; i++) {	
        			StringBuffer sql = new StringBuffer();
        			sql.append("INSERT INTO PREPAYMENT(ID,STATIONID,PJJE,HTBH,PREPAYMENT_AMMETERID,FEETYPEID,MONEY,STARTDEGREE,STARTDATE,ENDDATE,INPUTDATETIME,INPUTOPERATOR,AUDITDATE,AUDITOPERATOR,FINANCIALDATE,FINANCIALOPERATOR,MEMO,NOTETYPEID,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,UUID,ACCOUNTMONTH,STARTMONTH,ENDMONTH,financeaudit,cityaudit,ENTRYPENSONNEL,ENTRYTIME,NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF,DDDF,DANJIA,YFFBZW)");
        			sql.append(" VALUES('"+i+"','"+formBean.getStationid()+"','"+formBean.getPjje()+"','"+formBean.getHtbianhao()+"','"+ formBean.getPrepayment_ammeterid()+"',"+"NULL"+",'"+dflist.get(i)+"'," );
        			sql.append("NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+",'"+memo+"','"+notetypeid+"',TO_DATE('" +notetime+"','yyyy-mm-dd'),TO_DATE('"+kptime+"','yyyy-mm-dd'),"+"NULL"+","+"NULL"+",'"+uuid+"',TO_DATE('"+formBean.getAccountmonth()+"','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('"
        					+year_month.get(i)+"','yyyy-mm'),'1','0','"+formBean.getEntrypensonnel()+"',"+s+",'"+nwdffentan.get(i)+"','"+imdffentan.get(i)+"','"+asdffentan.get(i)+"','"+mkdffentan.get(i)+"','"+bldffentan.get(i)+"','"+dddffentan.get(i)+"','"+danjia+"','1')");
    			
    			 System.out.println("--"+sql.toString());
    			 sqlBatch[i]=sql.toString();
        		}
           
			msg = "�޸ĺ�ͬ����Ϣʧ�ܣ�";	
			db.setAutoCommit(false);
			db.update(delete);
			db.updateBatch(sqlBatch);
			msg = "�޸ĺ�ͬ����Ϣ�ɹ���";
			db.commit();
			db.setAutoCommit(true);
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	public synchronized String delBargain(String id) {
		String msg = "ɾ����ѵ���Ϣʧ�ܣ�";
		DataBase db = new DataBase();

		try {
			db.connectDb();
            String delete1="delete from prepayment where uuid=(select uuid from yufufeiview where id=" +id+")";
			
//            String sql1 = "DELETE electricfees WHERE ELECTRICFEE_ID="
//					+ degreeid; // ɾ�������Ϣ��

			System.out.println(delete1.toString());
			// String delNames = getName(accountId, db);
			msg = "ɾ����ͬ����Ϣʧ�ܣ�";
			db.update(delete1);
			
			msg = "ɾ����ͬ����Ϣ�ɹ���";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}
	public String getEntrypensonnel() {
		return entrypensonnel;
	}
	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}
	public String getEntrytime() {
		return entrytime;
	}
	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public String getDanjia() {
		return danjia;
	}
	
}
