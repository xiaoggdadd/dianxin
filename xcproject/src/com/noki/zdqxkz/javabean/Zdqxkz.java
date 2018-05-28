package com.noki.zdqxkz.javabean;

public class Zdqxkz {
	
	//表qskz 里面的字段
	private String id;//		自增id	
	private String zdid;//	          站点id	
	private String newbgsign;//	是否标杆（新）	
	private String newdianfei;//单价（新）	
	private String newedhdl;//	额定耗电量（新）	
	private String newjlfh;//	交流负荷（新）	
	private String newzlfh;//	直流负荷（新）	
	private String newproperty;//站点属性（新）	
	private String newstationtype;//站点类型（新）	
	private String newyflx;//	用房类型（新）	
	private String newgxxx;//	共享信息（新）	
	private String newgdfs;//	供电方式（新）	
	private String newzgd;//	直供电（新）	
	private String newarea;//	站点面积（新）	
	private String newqyzt;//	站点启用状态（新）	
	private String newg2;//		2G（新）	
	private String newg3;//		3G（新）	
	private String newzpsl;//	载频（新）	
	private String newzssl;//	载扇（新）	
	private String newchangjia;//厂家（新）	
	private String newjzlx;//	基站类型2（新）	
	private String newshebei;//	设备编码（新）	
	private String newbbu;//	BBU数量（新）	
	private String newrru;//	RRU数量（新）	
	private String newydshebei;//移动设备数量（新）	
	private String newgxgwsl;//	共享固网设备数量（新）	
	private String newqsdbdl;//全省定标电量（新）
	private String newdfzflx;//电费支付类型(新)
	
	private String xgsm;//		修改说明	
	private String xgyj;//		修改依据	
	private String fjmc;//		附件名称	
	private String fjnr;//		附件	
	private String qxtjr;//		区县提交人	
	private String qxtjbz;//	区县提交标志	
	private String qxtjtime;//	区县提交时间	
	private String shishr;//	市级审核人	
	private String shishbz;//	市级审核标志	
	private String shishsj;//	市级审核时间	
	private String shengshr;//	省级审核人	
	private String shengshbz;//	省级审核标志	
	private String shengshsj;//	省级审核时间	
	private String qxpdbz;//	字段权限判断标志	
	private String oldbgsign;//	是否标杆（老）	
	private String olddianfei;//单价（老）	
	private String oldedhdl;//	额定耗电量（老）	
	private String oldjlfh;//	交流负荷（老）	
	private String oldzlfh;//	直流负荷（老）	
	private String oldproperty;//站点属性（老）	
	private String oldstationtype;//站点类型（老）	
	private String oldyflx;//	 用房类型（老）	
	private String oldgxxx;//	共享信息（老）	
	private String oldgdfs;//	供电方式（老）	
	private String oldzgd;//	直供电（老）	
	private String oldarea;//	站点面积（老）	
	private String oldqyzt;//	站点启用状态（老）	
	private String oldg2;//		2G（老）	
	private String oldg3;//		3G（老）	
	private String oldzpsl;//	载频（老）	
	private String oldzssl;//	载扇（老）	
	private String oldchangjia;//厂家（老）	
	private String oldjzlx;//	基站类型2（老）	
	private String oldshebei;//	设备编码（老）	
	private String oldbbu;//	BBU数量（老）	
	private String oldrru;//	RRU数量（老）	
	private String oldydshebei;//移动设备数量（老）	
	private String oldgxgwsl;//	共享固网设备数量（老）	
	private String oldqsdbdl;//全省定标电量（老）
	private String oldqsdb;//生产标 全省定标电量(不含空调)
	private String olddfzflx;//电费支付类型(老)
	
	private String shi;//市
	private String xian;//区县
	private String xiaoqu;//乡镇
	private String zdname;//站点名称
	private String biaoti;//标题
	private String filepath;//文件存放路径
	private String qskzid;//qskz自增id
	private String qxly;//省级不通过理由
	
	private String sjly;//市级不通过理由
	private String dbdj;//电表单价
	private String newdbdj;//电表最新单价
	private String dbid;//电表ID
	
	
	private String scb;//生产标
	private String dbds;//电表读数
	private String dsbzw;//电表读数修改标志位
	private String olddbds;//电表修改读数(老)
	private String oldxgbzw;//电表读数修改标识位(老)
	
	private String zdbzw;//站点核实标志位 1为是 0为否 在站点表里添加 字段为bzw
	private String bghs;// 站点标杆等级标志 A-1，B-2，C-3，D-4 在qskz表里添加 表里字段为BGDJ 
	private String noreason;//标杆降级不通过原因
	
	private String flgg;//省级审核分类更改字段
	private String bftg;//省级审核分类部分通过字段
	private String bfbtg;//省级审核分类部分不通过字段
	
	
	
	public String getBghs() {
		return bghs;
	}
	public void setBghs(String bghs) {
		this.bghs = bghs;
	}
	public String getZdbzw() {
		return zdbzw;
	}
	public void setZdbzw(String zdbzw) {
		this.zdbzw = zdbzw;
	}
	public String getDsbzw() {
		return dsbzw;
	}
	public void setDsbzw(String dsbzw) {
		this.dsbzw = dsbzw;
	}
	public String getDbds() {
		return dbds;
	}
	public void setDbds(String dbds) {
		this.dbds = dbds;
	}
	public String getScb() {
		return scb;
	}
	public void setScb(String scb) {
		this.scb = scb;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getNewdbdj() {
		return newdbdj;
	}
	public void setNewdbdj(String newdbdj) {
		this.newdbdj = newdbdj;
	}
	public String getDbdj() {
		return dbdj;
	}
	public void setDbdj(String dbdj) {
		this.dbdj = dbdj;
	}
	public String getOldqsdb() {
		return oldqsdb;
	}
	public void setOldqsdb(String oldqsdb) {
		this.oldqsdb = oldqsdb;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getQxly() {
		return qxly;
	}
	public void setQxly(String qxly) {
		this.qxly = qxly;
	}
	public String getQskzid() {
		return qskzid;
	}
	public void setQskzid(String qskzid) {
		this.qskzid = qskzid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getBiaoti() {
		return biaoti;
	}
	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
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
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getNewbgsign() {
		return newbgsign;
	}
	public void setNewbgsign(String newbgsign) {
		this.newbgsign = newbgsign;
	}
	public String getNewdianfei() {
		return newdianfei;
	}
	public void setNewdianfei(String newdianfei) {
		this.newdianfei = newdianfei;
	}
	public String getNewedhdl() {
		return newedhdl;
	}
	public void setNewedhdl(String newedhdl) {
		this.newedhdl = newedhdl;
	}
	public String getNewjlfh() {
		return newjlfh;
	}
	public void setNewjlfh(String newjlfh) {
		this.newjlfh = newjlfh;
	}
	public String getNewzlfh() {
		return newzlfh;
	}
	public void setNewzlfh(String newzlfh) {
		this.newzlfh = newzlfh;
	}
	public String getNewproperty() {
		return newproperty;
	}
	public void setNewproperty(String newproperty) {
		this.newproperty = newproperty;
	}
	public String getNewstationtype() {
		return newstationtype;
	}
	public void setNewstationtype(String newstationtype) {
		this.newstationtype = newstationtype;
	}
	public String getNewyflx() {
		return newyflx;
	}
	public void setNewyflx(String newyflx) {
		this.newyflx = newyflx;
	}
	public String getNewgxxx() {
		return newgxxx;
	}
	public void setNewgxxx(String newgxxx) {
		this.newgxxx = newgxxx;
	}
	public String getNewgdfs() {
		return newgdfs;
	}
	public void setNewgdfs(String newgdfs) {
		this.newgdfs = newgdfs;
	}
	public String getNewzgd() {
		return newzgd;
	}
	public void setNewzgd(String newzgd) {
		this.newzgd = newzgd;
	}
	public String getNewarea() {
		return newarea;
	}
	public void setNewarea(String newarea) {
		this.newarea = newarea;
	}
	public String getNewqyzt() {
		return newqyzt;
	}
	public void setNewqyzt(String newqyzt) {
		this.newqyzt = newqyzt;
	}
	public String getNewg2() {
		return newg2;
	}
	public void setNewg2(String newg2) {
		this.newg2 = newg2;
	}
	public String getNewg3() {
		return newg3;
	}
	public void setNewg3(String newg3) {
		this.newg3 = newg3;
	}
	public String getNewzpsl() {
		return newzpsl;
	}
	public void setNewzpsl(String newzpsl) {
		this.newzpsl = newzpsl;
	}
	public String getNewzssl() {
		return newzssl;
	}
	public void setNewzssl(String newzssl) {
		this.newzssl = newzssl;
	}
	public String getNewchangjia() {
		return newchangjia;
	}
	public void setNewchangjia(String newchangjia) {
		this.newchangjia = newchangjia;
	}
	public String getNewjzlx() {
		return newjzlx;
	}
	public void setNewjzlx(String newjzlx) {
		this.newjzlx = newjzlx;
	}
	public String getNewshebei() {
		return newshebei;
	}
	public void setNewshebei(String newshebei) {
		this.newshebei = newshebei;
	}
	public String getNewbbu() {
		return newbbu;
	}
	public void setNewbbu(String newbbu) {
		this.newbbu = newbbu;
	}
	public String getNewrru() {
		return newrru;
	}
	public void setNewrru(String newrru) {
		this.newrru = newrru;
	}
	public String getNewydshebei() {
		return newydshebei;
	}
	public void setNewydshebei(String newydshebei) {
		this.newydshebei = newydshebei;
	}
	public String getNewgxgwsl() {
		return newgxgwsl;
	}
	public void setNewgxgwsl(String newgxgwsl) {
		this.newgxgwsl = newgxgwsl;
	}
	public String getXgsm() {
		return xgsm;
	}
	public void setXgsm(String xgsm) {
		this.xgsm = xgsm;
	}
	public String getXgyj() {
		return xgyj;
	}
	public void setXgyj(String xgyj) {
		this.xgyj = xgyj;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getFjnr() {
		return fjnr;
	}
	public void setFjnr(String fjnr) {
		this.fjnr = fjnr;
	}
	public String getQxtjr() {
		return qxtjr;
	}
	public void setQxtjr(String qxtjr) {
		this.qxtjr = qxtjr;
	}
	public String getQxtjbz() {
		return qxtjbz;
	}
	public void setQxtjbz(String qxtjbz) {
		this.qxtjbz = qxtjbz;
	}
	public String getQxtjtime() {
		return qxtjtime;
	}
	public void setQxtjtime(String qxtjtime) {
		this.qxtjtime = qxtjtime;
	}
	public String getShishr() {
		return shishr;
	}
	public void setShishr(String shishr) {
		this.shishr = shishr;
	}
	public String getShishbz() {
		return shishbz;
	}
	public void setShishbz(String shishbz) {
		this.shishbz = shishbz;
	}
	public String getShishsj() {
		return shishsj;
	}
	public void setShishsj(String shishsj) {
		this.shishsj = shishsj;
	}
	public String getShengshr() {
		return shengshr;
	}
	public void setShengshr(String shengshr) {
		this.shengshr = shengshr;
	}
	public String getShengshbz() {
		return shengshbz;
	}
	public void setShengshbz(String shengshbz) {
		this.shengshbz = shengshbz;
	}
	public String getShengshsj() {
		return shengshsj;
	}
	public void setShengshsj(String shengshsj) {
		this.shengshsj = shengshsj;
	}
	public String getQxpdbz() {
		return qxpdbz;
	}
	public void setQxpdbz(String qxpdbz) {
		this.qxpdbz = qxpdbz;
	}
	public String getOldbgsign() {
		return oldbgsign;
	}
	public void setOldbgsign(String oldbgsign) {
		this.oldbgsign = oldbgsign;
	}
	public String getOlddianfei() {
		return olddianfei;
	}
	public void setOlddianfei(String olddianfei) {
		this.olddianfei = olddianfei;
	}
	public String getOldedhdl() {
		return oldedhdl;
	}
	public void setOldedhdl(String oldedhdl) {
		this.oldedhdl = oldedhdl;
	}
	public String getOldjlfh() {
		return oldjlfh;
	}
	public void setOldjlfh(String oldjlfh) {
		this.oldjlfh = oldjlfh;
	}
	public String getOldzlfh() {
		return oldzlfh;
	}
	public void setOldzlfh(String oldzlfh) {
		this.oldzlfh = oldzlfh;
	}
	public String getOldproperty() {
		return oldproperty;
	}
	public void setOldproperty(String oldproperty) {
		this.oldproperty = oldproperty;
	}
	public String getOldstationtype() {
		return oldstationtype;
	}
	public void setOldstationtype(String oldstationtype) {
		this.oldstationtype = oldstationtype;
	}
	public String getOldyflx() {
		return oldyflx;
	}
	public void setOldyflx(String oldyflx) {
		this.oldyflx = oldyflx;
	}
	public String getOldgxxx() {
		return oldgxxx;
	}
	public void setOldgxxx(String oldgxxx) {
		this.oldgxxx = oldgxxx;
	}
	public String getOldgdfs() {
		return oldgdfs;
	}
	public void setOldgdfs(String oldgdfs) {
		this.oldgdfs = oldgdfs;
	}
	public String getOldzgd() {
		return oldzgd;
	}
	public void setOldzgd(String oldzgd) {
		this.oldzgd = oldzgd;
	}
	public String getOldarea() {
		return oldarea;
	}
	public void setOldarea(String oldarea) {
		this.oldarea = oldarea;
	}
	public String getOldqyzt() {
		return oldqyzt;
	}
	public void setOldqyzt(String oldqyzt) {
		this.oldqyzt = oldqyzt;
	}
	public String getOldg2() {
		return oldg2;
	}
	public void setOldg2(String oldg2) {
		this.oldg2 = oldg2;
	}
	public String getOldg3() {
		return oldg3;
	}
	public void setOldg3(String oldg3) {
		this.oldg3 = oldg3;
	}
	public String getOldzpsl() {
		return oldzpsl;
	}
	public void setOldzpsl(String oldzpsl) {
		this.oldzpsl = oldzpsl;
	}
	public String getOldzssl() {
		return oldzssl;
	}
	public void setOldzssl(String oldzssl) {
		this.oldzssl = oldzssl;
	}
	public String getOldchangjia() {
		return oldchangjia;
	}
	public void setOldchangjia(String oldchangjia) {
		this.oldchangjia = oldchangjia;
	}
	public String getOldjzlx() {
		return oldjzlx;
	}
	public void setOldjzlx(String oldjzlx) {
		this.oldjzlx = oldjzlx;
	}
	public String getOldshebei() {
		return oldshebei;
	}
	public void setOldshebei(String oldshebei) {
		this.oldshebei = oldshebei;
	}
	public String getOldbbu() {
		return oldbbu;
	}
	public void setOldbbu(String oldbbu) {
		this.oldbbu = oldbbu;
	}
	public String getOldrru() {
		return oldrru;
	}
	public void setOldrru(String oldrru) {
		this.oldrru = oldrru;
	}
	public String getOldydshebei() {
		return oldydshebei;
	}
	public void setOldydshebei(String oldydshebei) {
		this.oldydshebei = oldydshebei;
	}
	public String getOldgxgwsl() {
		return oldgxgwsl;
	}
	public void setOldgxgwsl(String oldgxgwsl) {
		this.oldgxgwsl = oldgxgwsl;
	}
	public String getNewqsdbdl() {
		return newqsdbdl;
	}
	public void setNewqsdbdl(String newqsdbdl) {
		this.newqsdbdl = newqsdbdl;
	}
	public String getOldqsdbdl() {
		return oldqsdbdl;
	}
	public void setOldqsdbdl(String oldqsdbdl) {
		this.oldqsdbdl = oldqsdbdl;
	}
	public String getNoreason() {
		return noreason;
	}
	public void setNoreason(String noreason) {
		this.noreason = noreason;
	}
	public String getNewdfzflx() {
		return newdfzflx;
	}
	public void setNewdfzflx(String newdfzflx) {
		this.newdfzflx = newdfzflx;
	}
	public String getOlddfzflx() {
		return olddfzflx;
	}
	public void setOlddfzflx(String olddfzflx) {
		this.olddfzflx = olddfzflx;
	}
	public String getOlddbds() {
		return olddbds;
	}
	public void setOlddbds(String olddbds) {
		this.olddbds = olddbds;
	}
	public String getOldxgbzw() {
		return oldxgbzw;
	}
	public void setOldxgbzw(String oldxgbzw) {
		this.oldxgbzw = oldxgbzw;
	}
	public String getFlgg() {
		return flgg;
	}
	public void setFlgg(String flgg) {
		this.flgg = flgg;
	}
	public String getBftg() {
		return bftg;
	}
	public void setBftg(String bftg) {
		this.bftg = bftg;
	}
	public String getBfbtg() {
		return bfbtg;
	}
	public void setBfbtg(String bfbtg) {
		this.bfbtg = bfbtg;
	}
	
	

}
