package com.ptac.app.basisquery.contractbill.bean;


/**
 * @author lijing
 * @see ��ͬ����ѯ������ֶ�
 */
public class ContractBill {
	
		//վ��� ZHANDIAN
		private String id;//վ��ID
		private String shi;//��
		private String xian;//����	
		private String xiaoqu;//����	
		private String jzname;//վ������
		private String qsdbdl;//ȫʡ�������
		private String edhdl;//��ĵ���
		private String stationtype;//վ������	
		private String zdsx;//վ������
	
		//���� DIANBIAO
		private String dbid;//���ID
		private String dbname;//�������	
		private String dfzflx;//���֧������
		private String danjia;//����

		//Ԥ���ѱ�
		private String htbianhao;//��ͬ���
		private String money;//���
		private String dianliang;//�õ���
		private String startmonth;//��ʼ�·�		
		private String endmonth;//�����·�
		private String noteno;//Ʊ�ݱ��	
		private String notetypeid;//Ʊ������	
		private String accountmonth;//�����·�
		private String kjyf;//�����·�(����·�)
		private String memo;//��ע	
		private String beilv;//����
		private String lastdegree;//��ʼ�����	
		private String thisdegree;//���������
		private String lastdatetime;//�ϴγ���ʱ��	
		private String thisdatetime;//���γ���ʱ��
		private String entrytime;//¼��ʱ��
		private String entrypensonnel;//¼����Ա	
		private String autoauditstatus;//�Զ����״̬
		private String manualauditstatus;//�˹����״̬
		private String manualauditdatetime;//�˹����ʱ��	
		private String manualauditname;//�˹����Ա	
		private String countyauditstatus;//�����������״̬
		private String countyauditname;//�������������
		private String countyaudittime;//�����������ʱ��
		private String cityaudit;//�м����״̬	
		private String cityauditpensonnel;//�м����Ա	
		private String cityaudittime;//�м����ʱ��
		private String cityzrauditstatus;//���������״̬
		private String cityzrauditname;//�����������
		private String cityzraudittime;//���������ʱ��
		private String financialstatus;//�������״̬
		private String financialdatetime;//�������ʱ��	
		private String financialoperator;//�������Ա(�������Ա)	
		private	String liuchenghao;//���̺�
		
		public String getLiuchenghao() {
			return liuchenghao;
		}
		public void setLiuchenghao(String liuchenghao) {
			this.liuchenghao = liuchenghao;
		}

		public String getQsdbdl() {
			return qsdbdl;
		}
		public void setQsdbdl(String qsdbdl) {
			this.qsdbdl = qsdbdl;
		}
	
		public String getDanjia() {
			return danjia;
		}

		public void setDanjia(String danjia) {
			this.danjia = danjia;
		}
		public String getHtbianhao() {
			return htbianhao;
		}
		public void setHtbianhao(String htbianhao) {
			this.htbianhao = htbianhao;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
		public String getDianliang() {
			return dianliang;
		}
		public void setDianliang(String dianliang) {
			this.dianliang = dianliang;
		}
		public String getCountyauditstatus() {
			return countyauditstatus;
		}
		public void setCountyauditstatus(String countyauditstatus) {
			this.countyauditstatus = countyauditstatus;
		}
		public String getCountyauditname() {
			return countyauditname;
		}
		public void setCountyauditname(String countyauditname) {
			this.countyauditname = countyauditname;
		}
		public String getCountyaudittime() {
			return countyaudittime;
		}
		public void setCountyaudittime(String countyaudittime) {
			this.countyaudittime = countyaudittime;
		}
		public String getCityzrauditstatus() {
			return cityzrauditstatus;
		}
		public void setCityzrauditstatus(String cityzrauditstatus) {
			this.cityzrauditstatus = cityzrauditstatus;
		}
		public String getCityzrauditname() {
			return cityzrauditname;
		}
		public void setCityzrauditname(String cityzrauditname) {
			this.cityzrauditname = cityzrauditname;
		}
		public String getCityzraudittime() {
			return cityzraudittime;
		}
		public void setCityzraudittime(String cityzraudittime) {
			this.cityzraudittime = cityzraudittime;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEdhdl() {
			return edhdl;
		}
		public void setEdhdl(String edhdl) {
			this.edhdl = edhdl;
		}
		public String getStationtype() {
			return stationtype;
		}
		public void setStationtype(String stationtype) {
			this.stationtype = stationtype;
		}
		public String getShi() {
			return shi;
		}
		public void setShi(String shi) {
			this.shi = shi;
		}
		public String getXian() {
			return xian;
		}
		public void setXian(String xian) {
			this.xian = xian;
		}
		public String getXiaoqu() {
			return xiaoqu;
		}
		public void setXiaoqu(String xiaoqu) {
			this.xiaoqu = xiaoqu;
		}
		public String getJzname() {
			return jzname;
		}
		public void setJzname(String jzname) {
			this.jzname = jzname;
		}
		public String getDbid() {
			return dbid;
		}
		public void setDbid(String dbid) {
			this.dbid = dbid;
		}

		public String getBeilv() {
			return beilv;
		}
		public void setBeilv(String beilv) {
			this.beilv = beilv;
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

		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
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
		public String getAutoauditstatus() {
			return autoauditstatus;
		}
		public void setAutoauditstatus(String autoauditstatus) {
			this.autoauditstatus = autoauditstatus;
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

		public String getCityauditpensonnel() {
			return cityauditpensonnel;
		}
		public void setCityauditpensonnel(String cityauditpensonnel) {
			this.cityauditpensonnel = cityauditpensonnel;
		}
		public String getCityaudittime() {
			return cityaudittime;
		}
		public void setCityaudittime(String cityaudittime) {
			this.cityaudittime = cityaudittime;
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
		public String getNotetypeid() {
			return notetypeid;
		}
		public void setNotetypeid(String notetypeid) {
			this.notetypeid = notetypeid;
		}

		public String getFinancialoperator() {
			return financialoperator;
		}
		public void setFinancialoperator(String financialoperator) {
			this.financialoperator = financialoperator;
		}
		public String getAccountmonth() {
			return accountmonth;
		}
		public void setAccountmonth(String accountmonth) {
			this.accountmonth = accountmonth;
		}

		public String getNoteno() {
			return noteno;
		}
		public void setNoteno(String noteno) {
			this.noteno = noteno;
		}
		public String getManualauditstatus() {
			return manualauditstatus;
		}
		public void setManualauditstatus(String manualauditstatus) {
			this.manualauditstatus = manualauditstatus;
		}
		public String getManualauditdatetime() {
			return manualauditdatetime;
		}
		public void setManualauditdatetime(String manualauditdatetime) {
			this.manualauditdatetime = manualauditdatetime;
		}
		public String getManualauditname() {
			return manualauditname;
		}
		public void setManualauditname(String manualauditname) {
			this.manualauditname = manualauditname;
		}
		public String getFinancialdatetime() {
			return financialdatetime;
		}
		public void setFinancialdatetime(String financialdatetime) {
			this.financialdatetime = financialdatetime;
		}
		public String getCityaudit() {
			return cityaudit;
		}
		public void setCityaudit(String cityaudit) {
			this.cityaudit = cityaudit;
		}
		public String getKjyf() {
			return kjyf;
		}
		public void setKjyf(String kjyf) {
			this.kjyf = kjyf;
		}
		public void setFinancialstatus(String financialstatus) {
			this.financialstatus = financialstatus;
		}
		public String getFinancialstatus() {
			return financialstatus;
		}
		public String getZdsx() {
			return zdsx;
		}
		public void setZdsx(String zdsx) {
			this.zdsx = zdsx;
		}

		
}
