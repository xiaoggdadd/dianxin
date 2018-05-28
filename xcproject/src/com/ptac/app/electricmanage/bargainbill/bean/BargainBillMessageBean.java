package com.ptac.app.electricmanage.bargainbill.bean;

/**
 * ��ͬ����ѯ�����javaBean
 * 
 * @author rock
 * 
 */
public class BargainBillMessageBean {

	public String getPjje() {
		return pjje;
	}

	public void setPjje(String pjje) {
		this.pjje = pjje;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
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

	public String getAccountmonth() {
		return accountmonth;
	}

	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}

	public String getPrepayment_ammeterid() {
		return prepayment_ammeterid;
	}

	public void setPrepayment_ammeterid(String prepaymentAmmeterid) {
		prepayment_ammeterid = prepaymentAmmeterid;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStationtype() {
		return stationtype;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	/**
	 * �������췽�������ڿ��ٴ洢
	 * @author rock
	 * @param pjje
	 * @param htbh
	 * @param zdcode
	 * @param jzname
	 * @param accountmonth
	 * @param propayment_ammeterid
	 * @param dbname
	 * @param money
	 * @param startmonth
	 * @param endmonth
	 * @param id
	 * @param stationtype
	 */
	public BargainBillMessageBean(String pjje, String htbh, String zdcode,
			String jzname, String accountmonth, String prepayment_ammeterid,
			String dbname, String money, String startmonth, String endmonth,
			String id, String stationtype) {
		
		this.pjje = pjje;
		this.htbh = htbh;
		this.zdcode = zdcode;
		this.jzname = jzname;
		this.accountmonth = accountmonth;
		this.prepayment_ammeterid = prepayment_ammeterid;
		this.dbname = dbname;
		this.money = money;
		this.startmonth = startmonth;
		this.endmonth = endmonth;
		this.id = id;
		this.stationtype = stationtype;

	}
	
	/**
	 * ��һ���ղεĹ��췽��
	 * @author rock
	 */
	public BargainBillMessageBean(){
		
	}

	private String pjje;// Ʊ�ݽ��
	private String htbh;// ��ͬ���
	private String zdcode;// վ����
	private String jzname;// վ������
	private String accountmonth;// �����·�
	private String prepayment_ammeterid;// �����
	private String dbname;// �������
	private String money;// ���
	private String startmonth;// ��ʼʱ��
	private String endmonth;// ����ʱ��
	private String id;// Ԥ����ID
	private String stationtype;// վ������
}
