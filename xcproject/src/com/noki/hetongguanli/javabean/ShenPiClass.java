package com.noki.hetongguanli.javabean;

public class ShenPiClass {
		private int id;				 //合同ID
		private String zhuangtai;    //合同状态，默认为0（未审核）
		private String beizhu;		 //合同备注
		private String starttime;	 //合同开始时间
		private String endtime;      //合同结束时间
		private String partya;  	 //合同甲方
		private String partyb;		 //合同乙方
		private String contractname; //合同名称
		private String projectamonnt;//合同金额  
		private String contractdetail;//合同详情
		//无参构造
		public ShenPiClass() {
			super();
		}
		//有参构造
		public ShenPiClass(int id, String zhuangtai, String beizhu,
				String starttime, String endtime, String partya, String partyb,
				String contractname, String projectamonnt,String contractdetail) {
			super();
			this.id = id;
			this.zhuangtai = zhuangtai;
			this.beizhu = beizhu;
			this.starttime = starttime;
			this.endtime = endtime;
			this.partya = partya;
			this.partyb = partyb;
			this.contractname = contractname;
			this.projectamonnt = projectamonnt;
			this.contractdetail = contractdetail;
		}
		//Set/Get方法
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getZhuangtai() {
			return zhuangtai;
		}
		public void setZhuangtai(String zhuangtai) {
			this.zhuangtai = zhuangtai;
		}
		public String getBeizhu() {
			return beizhu;
		}
		public void setBeizhu(String beizhu) {
			this.beizhu = beizhu;
		}
		public String getStarttime() {
			return starttime;
		}
		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}
		public String getEndtime() {
			return endtime;
		}
		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}
		public String getPartya() {
			return partya;
		}
		public void setPartya(String partya) {
			this.partya = partya;
		}
		public String getPartyb() {
			return partyb;
		}
		public void setPartyb(String partyb) {
			this.partyb = partyb;
		}
		public String getContractname() {
			return contractname;
		}
		public void setContractname(String contractname) {
			this.contractname = contractname;
		}
		public String getProjectamonnt() {
			return projectamonnt;
		}
		public void setProjectamonnt(String projectamonnt) {
			this.projectamonnt = projectamonnt;
		}
		public String getContractdetail() {
			return contractdetail;
		}
		public void setContractdetail(String contractdetail) {
			this.contractdetail = contractdetail;
		}
		
}
