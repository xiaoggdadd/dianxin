package com.ptac.app.inter.bean;

public class KdgcElectrictypeBean {

	private String shi;
	// ����,
	private int id;
	// վ��ID,
	private int zlfh;
	// ZLFH ֱ������,
	private String xlx1;
	// Z.XLX ��վ����1,
	private String beilv;
	// D.BEILV ����,
	private String danjia;
	// D.DANJIA ����,
	private String accountmonth;
	// ACCOUNTMONTH �����·�,
	private String thisdegree;
	// V.THISDEGREE ���ζ���,
	private String lastdegree;
	// V.LASTDEGREE �ϴζ���,
	private String thisdatetime;
	// V.THISDATETIME ���γ���ʱ��,
	private String lastdatetime;
	// V.LASTDATETIME �ϴγ���ʱ��,
	private String floatdegree;
	// V.FLOATDEGREE ��������,
	private int blhdl;
	// ���˵���,
	private int actualpay;
	// F.ACTUALPAY ���˵��,
	private String floatpay;
	// F.FLOATPAY ��ѵ���,
	private String zmqtdl;
	// ---����������ĵ���
	private String ktdl;
	// ---�յ��ĵ���
	private String networkhdl;
	// v.networkhdl �칫�õ�ĵ������ȣ�,
	private String yytshdl;
	// ---Ӫҵ������õ�ĵ���
	private String qtdl;
	// ----����ĵ���
	private String dffsmonth;
	// F.ACCOUNTMONTH ��ѷ����·�,
	private String entrytime;
	// F.ENTRYTIME ¼������,
	private String lrdw;
	// z.shi||z.xian||z.xiaoqu ¼�뵥λ,
	private String entrypensonnel;
	// F.ENTRYPENSONNEL ¼����Ա,
	private String jlfh;
	// Z.JLFH ��������,
	private String dbid;

	// D.DBID ���ID
	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZlfh() {
		return zlfh;
	}

	public void setZlfh(int zlfh) {
		this.zlfh = zlfh;
	}

	public String getXlx1() {
		return xlx1;
	}

	public void setXlx1(String xlx1) {
		this.xlx1 = xlx1;
	}

	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}

	public String getDanjia() {
		return danjia;
	}

	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}

	public String getAccountmonth() {
		return accountmonth;
	}

	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}

	public String getThisdegree() {
		return thisdegree;
	}

	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}

	public String getLastdegree() {
		return lastdegree;
	}

	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}

	public String getThisdatetime() {
		return thisdatetime;
	}

	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}

	public String getLastdatetime() {
		return lastdatetime;
	}

	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}

	public String getFloatdegree() {
		return floatdegree;
	}

	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}

	public int getBlhdl() {
		return blhdl;
	}

	public void setBlhdl(int blhdl) {
		this.blhdl = blhdl;
	}

	public int getActualpay() {
		return actualpay;
	}

	public void setActualpay(int actualpay) {
		this.actualpay = actualpay;
	}

	public String getFloatpay() {
		return floatpay;
	}

	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}

	public String getZmqtdl() {
		return zmqtdl;
	}

	public void setZmqtdl(String zmqtdl) {
		this.zmqtdl = zmqtdl;
	}

	public String getKtdl() {
		return ktdl;
	}

	public void setKtdl(String ktdl) {
		this.ktdl = ktdl;
	}

	public String getNetworkhdl() {
		return networkhdl;
	}

	public void setNetworkhdl(String networkhdl) {
		this.networkhdl = networkhdl;
	}

	public String getYytshdl() {
		return yytshdl;
	}

	public void setYytshdl(String yytshdl) {
		this.yytshdl = yytshdl;
	}

	public String getQtdl() {
		return qtdl;
	}

	public void setQtdl(String qtdl) {
		this.qtdl = qtdl;
	}

	public String getDffsmonth() {
		return dffsmonth;
	}

	public void setDffsmonth(String dffsmonth) {
		this.dffsmonth = dffsmonth;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getLrdw() {
		return lrdw;
	}

	public void setLrdw(String lrdw) {
		this.lrdw = lrdw;
	}

	public String getEntrypensonnel() {
		return entrypensonnel;
	}

	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}

	public String getJlfh() {
		return jlfh;
	}

	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbi(String dbid) {
		this.dbid = dbid;
	}

	@Override
	public String toString() {
		xlx1 =this.xlx1!=null?this.xlx1:"";
		if("null".equals(this.xlx1)){
			xlx1 = "";
		}
		return this.shi + "||" + this.id + "||" + this.zlfh + "||" + this.xlx1
				+ "||" + this.beilv + "||" + this.danjia + "||"
				+ this.accountmonth + "||" + this.thisdegree + "||"
				+ this.lastdegree +"||"+ this.thisdatetime + "||"
				+ this.lastdatetime + "||" + this.floatdegree + "||"
				+ this.blhdl + "||" + this.actualpay + "||" + this.floatpay
				+ "||" + this.zmqtdl + "||" + this.ktdl + "||" + this.networkhdl + "||" + this.yytshdl
				+ "||" + this.qtdl + "||" + this.accountmonth + "||" + this.entrytime
				+ "||" + this.lrdw + "||" + this.entrypensonnel + "||"
				+ this.jlfh + "||" + this.dbid;
	}

}
