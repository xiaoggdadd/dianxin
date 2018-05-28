package com.ptac.app.basisquery.contractbill.bean;


/**
 * @author lijing
 * @see 合同单查询所需的字段
 */
public class ContractBill {
	
		//站点表 ZHANDIAN
		private String id;//站点ID
		private String shi;//市
		private String xian;//区县	
		private String xiaoqu;//乡镇	
		private String jzname;//站点名称
		private String qsdbdl;//全省定标电量
		private String edhdl;//额定耗电量
		private String stationtype;//站点类型	
		private String zdsx;//站点属性
	
		//电表表 DIANBIAO
		private String dbid;//电表ID
		private String dbname;//电表名称	
		private String dfzflx;//电费支付类型
		private String danjia;//单价

		//预付费表
		private String htbianhao;//合同编号
		private String money;//金额
		private String dianliang;//用电量
		private String startmonth;//起始月份		
		private String endmonth;//结束月份
		private String noteno;//票据编号	
		private String notetypeid;//票据类型	
		private String accountmonth;//报账月份
		private String kjyf;//财务月份(会计月份)
		private String memo;//备注	
		private String beilv;//倍率
		private String lastdegree;//起始电表数	
		private String thisdegree;//结束电表数
		private String lastdatetime;//上次抄表时间	
		private String thisdatetime;//本次抄表时间
		private String entrytime;//录入时间
		private String entrypensonnel;//录入人员	
		private String autoauditstatus;//自动审核状态
		private String manualauditstatus;//人工审核状态
		private String manualauditdatetime;//人工审核时间	
		private String manualauditname;//人工审核员	
		private String countyauditstatus;//区县主任审核状态
		private String countyauditname;//区县主任审核人
		private String countyaudittime;//区县主任审核时间
		private String cityaudit;//市级审核状态	
		private String cityauditpensonnel;//市级审核员	
		private String cityaudittime;//市级审核时间
		private String cityzrauditstatus;//市主任审核状态
		private String cityzrauditname;//市主任审核人
		private String cityzraudittime;//市主任审核时间
		private String financialstatus;//财务审核状态
		private String financialdatetime;//财务审核时间	
		private String financialoperator;//财务审核员(财务操作员)	
		private	String liuchenghao;//流程号
		
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
