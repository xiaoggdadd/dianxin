package com.noki.jizhan;

import java.sql.*;
import java.util.Random;

import com.noki.database.*;


public class SiteModifyBean {
	
	
	private String zldy;//վ�㸺����
	private String fuzeren;//վ�㸺����
	private String danjia;//����
	private String jrwtype;//����������
	private String  edhdl;//��ĵ���
	private String fzr;
    private String memo;
    private String area;//վ�����
    private String gdfs;//���緽ʽ
    private String jzname;//վ������
    private String bieming;//����
    private String address;//��ַ
    private String bgsign;//�Ƿ���
    private String jnglmk;//�Ƿ����
    private String jztype;//���ű�������
    private String jzproperty;//վ������
    private String yflx;//�÷�����
    private String qyzt;//վ������״̬
    private String shi;//��
    private String xian;//��
    private String id;
    private String zdcode;//վ��code
    private String dianfei;
    private String xiaoqu;//С��
    private String typename;
    private String kzid;
    private String ckkd;//���ڿ��
    private String ysymj;//��ʹ�����
    private String jggs;//�������
    private String ysygs;//��ʹ�ø���
    private String jfgd;//�����߶�
    private String sdwd;//�趨�¶�
    private String sffs;//�ͷ緽ʽ
    private String lyfs;//��Դ��ʽ
    private int PUE;
    private String zzjgbm;
    private String xuni;
    private String czzd;
    private String gczt;
    private String gcxm;
    private String zdcq;//վ���Ȩ
    private String zdcb;
    private String zlfh;//ֱ������
    private String jnjsms;
    private String czzdid;
    private String gzqk;//��վ���
    private String nhzb;//�ܺ�ռ��
    private String zpsl;//��Ƶ����
    private String zgry;//�ڸ���Ա
    private String ktsl;//�յ�����
    private String pcsl;//PC����
    private String rll;//����������/�ˣ�
    private String nhjcdy;
    private String ERPbh;
    private String dhID;
    private String zhwgID;
    private String dzywID;
    private String longitude;
    private String latitude;
    private String caiji;
    private String ljzs;//�߼�վ��
    private String jzxlx;//��վ����
    private String jflx;//�ַ�����
    private String txj;//IDC�����Ǽ�
    private String ugs;//U����
    private String ysyugs;//��ʹ��U����
    private String jnjslx;//IDC���ܼ���
    private String ydlx;//�õ�����
    private String kongtiao;//���޿յ�
    private String gsf;//������
    private String stationtype;//վ������
    private String jlfh;//��������
    private String sydate;//��������
    private String gxxx;//������Ϣ
    private String jskssj;//���迪ʼʱ��
    private String jsjssj;//�������ʱ��
    private String xmh;//��Ŀ��
    private String ygd;//Զ����
    private String ysd;//Զ�ܵ�
    
    //վ�㸽����Ϣ
   // dytype,g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd
    private String dytype;//��������
    private String g2zd;//2G�豸
    private String g3zd;//3G�豸
    private String kdsb;//����豸
    private String yysb;//�����豸
    private String zpslzd;//վ����Ƶ����
    private String zssl;//��������
    private String kdsbsl;//����豸����
    private String yysbsl;//�����豸����
    private String kt1;//�յ�1
    private String kt2;//�յ�2
    private String zgd;//ֱ����
    private String lyjhjgs;//¥���������
    
    
     
    private String dfzflx;//���֧������
    private String fkfs;//���ʽ
    private String fkzq;//��������
    private String watchcost;//�ױ�Ʒ�
    private String zbdyhh;//�Ա����û���
    private String beilv;//����
    private String linelosstype;//��������
    private String linelossvalue;//����ֵ
    private String dbyt;//�����;
    private String gldb;//������
    
    //���������Ϣ
    private String signtypenum;//������ͱ��
    private String name;//�����������
    private String region;//��������
    private String sitetype;//վ������
    private String g2;//2G 
    private String g3;//3G
    private String oltg;//OLTG
    private String ktnum;//�յ�����
    
    //��������Ϣ
    private String ggid;//����id
    private String xxtype;//��Ϣ����
    private String ggtime;//����ʱ��
    private String dqtime;//ϵͳ��ǰʱ��
    private String bt;//����
    private String nr;//����
    private String lrr;//¼����
    
    private String sc;//����
    private String bg;//�칫
    private String yy;//Ӫҵ
    private String xxhzc;//��Ϣ��֧��
    private String jstz;//����Ͷ��
    private String dddf;//������
    
    private int countsc;//����
    private int countbg;//�칫
    private int countyy;//Ӫҵ
    private int countxxhzc;//��Ϣ��֧��
    private int countjstz;//����Ͷ��
    private int countdddf;//����Ͷ��
    private String csds;//��ʼ����
    private String cssytime;//��ʼʹ��ʱ��
    private String dbid;//���id
    private String ydbid;//ԭ���id
    
    private String changjia;
    private String jzlx;
    private String shebei;
    private String bbu;
    private String rru;
    private String ydshebei;
    private String gxgwsl;
    private String twgx;
    private String bm;
    private String g2xqs;//2GС����
    private String g3sqsl;//3G��������;
    private String ydgxsbsl;
    private String dxgxsbsl;
    
    private String dzdl;//ʡ���������
    private String dzyf;//�����·�
    private String dzbm;//������� 
    private String qsdbdl;//ȫʡ�������
    private String kts;//�յ���
    private String ktzgl;//�յ��ܹ���
    
	private String ysjts;//��ˮ��̨��
	private String wjts;//΢��̨��
	private String yybgkt;//Ӫҵ�칫�յ�
	private String jfsckt;//���������յ�
	
	private String ktyps;//�յ�һƥ��
	private String kteps;//�յ���ƥ��
	private String ktps;//�յ�ƥ��
	
	private String bsdl;//���б�ѹ������
	private String zybyqlx;//�������
	private String jzcode;
	private String wlzdbm;
	private String ltqx;
	private String ydqx;
	private String zzlx;
	private String nhxtid;
	private String fzrphone;
	
	
    
    public String getJzcode() {
		return jzcode;
	}




	public void setJzcode(String jzcode) {
		this.jzcode = jzcode;
	}




	public String getWlzdbm() {
		return wlzdbm;
	}




	public void setWlzdbm(String wlzdbm) {
		this.wlzdbm = wlzdbm;
	}




	public String getLtqx() {
		return ltqx;
	}




	public void setLtqx(String ltqx) {
		this.ltqx = ltqx;
	}




	public String getYdqx() {
		return ydqx;
	}




	public void setYdqx(String ydqx) {
		this.ydqx = ydqx;
	}




	public String getZzlx() {
		return zzlx;
	}




	public void setZzlx(String zzlx) {
		this.zzlx = zzlx;
	}




	public String getNhxtid() {
		return nhxtid;
	}




	public void setNhxtid(String nhxtid) {
		this.nhxtid = nhxtid;
	}




	public String getFzrphone() {
		return fzrphone;
	}




	public void setFzrphone(String fzrphone) {
		this.fzrphone = fzrphone;
	}




	public String getBsdl() {
		return bsdl;
	}




	public void setBsdl(String bsdl) {
		this.bsdl = bsdl;
	}




	public String getZybyqlx() {
		return zybyqlx;
	}




	public String getZldy() {
		return zldy;
	}




	public void setZldy(String zldy) {
		this.zldy = zldy;
	}




	public void setZybyqlx(String zybyqlx) {
		this.zybyqlx = zybyqlx;
	}




	public String getKtyps() {
		return ktyps;
	}




	public void setKtyps(String ktyps) {
		this.ktyps = ktyps;
	}




	public String getKteps() {
		return kteps;
	}




	public void setKteps(String kteps) {
		this.kteps = kteps;
	}




	public String getKtps() {
		return ktps;
	}




	public void setKtps(String ktps) {
		this.ktps = ktps;
	}




	public String getYsjts() {
		return ysjts;
	}




	public void setYsjts(String ysjts) {
		this.ysjts = ysjts;
	}




	public String getWjts() {
		return wjts;
	}




	public void setWjts(String wjts) {
		this.wjts = wjts;
	}




	public String getYybgkt() {
		return yybgkt;
	}




	public void setYybgkt(String yybgkt) {
		this.yybgkt = yybgkt;
	}




	public String getJfsckt() {
		return jfsckt;
	}




	public void setJfsckt(String jfsckt) {
		this.jfsckt = jfsckt;
	}




	public String getQsdbdl() {
		return qsdbdl;
	}




	public void setQsdbdl(String qsdbdl) {
		this.qsdbdl = qsdbdl;
	}




	public String getYdgxsbsl() {
		return ydgxsbsl;
	}




	public void setYdgxsbsl(String ydgxsbsl) {
		this.ydgxsbsl = ydgxsbsl;
	}




	public String getDxgxsbsl() {
		return dxgxsbsl;
	}




	public void setDxgxsbsl(String dxgxsbsl) {
		this.dxgxsbsl = dxgxsbsl;
	}




	public String getG2xqs() {
		return g2xqs;
	}




	public void setG2xqs(String g2xqs) {
		this.g2xqs = g2xqs;
	}




	public String getG3sqsl() {
		return g3sqsl;
	}




	public void setG3sqsl(String g3sqsl) {
		this.g3sqsl = g3sqsl;
	}




	public String getDzyf() {
		return dzyf;
	}




	public void setDzyf(String dzyf) {
		this.dzyf = dzyf;
	}




	public String getDzbm() {
		return dzbm;
	}




	public void setDzbm(String dzbm) {
		this.dzbm = dzbm;
	}




	public String getDzdl() {
		return dzdl;
	}




	public void setDzdl(String dzdl) {
		this.dzdl = dzdl;
	}




	public String getKts() {
		return kts;
	}




	public void setKts(String kts) {
		this.kts = kts;
	}




	public String getKtzgl() {
		return ktzgl;
	}




	public void setKtzgl(String ktzgl) {
		this.ktzgl = ktzgl;
	}




	public SiteModifyBean() {
    }

    
    
    
    public int getCountdddf() {
		return countdddf;
	}




	public void setCountdddf(int countdddf) {
		this.countdddf = countdddf;
	}




	public String getDddf() {
		return dddf;
	}




	public void setDddf(String dddf) {
		this.dddf = dddf;
	}




	public String getJskssj() {
		return jskssj;
	}




	public void setJskssj(String jskssj) {
		this.jskssj = jskssj;
	}




	public String getJsjssj() {
		return jsjssj;
	}




	public void setJsjssj(String jsjssj) {
		this.jsjssj = jsjssj;
	}




	public String getXmh() {
		return xmh;
	}




	public void setXmh(String xmh) {
		this.xmh = xmh;
	}




	public String getYgd() {
		return ygd;
	}




	public void setYgd(String ygd) {
		this.ygd = ygd;
	}




	public String getYsd() {
		return ysd;
	}




	public void setYsd(String ysd) {
		this.ysd = ysd;
	}




	public String getGxxx() {
		return gxxx;
	}




	public void setGxxx(String gxxx) {
		this.gxxx = gxxx;
	}




	public String getYdbid() {
		return ydbid;
	}




	public void setYdbid(String ydbid) {
		this.ydbid = ydbid;
	}




	public String getLyjhjgs() {
		return lyjhjgs;
	}




	public void setLyjhjgs(String lyjhjgs) {
		this.lyjhjgs = lyjhjgs;
	}




	public String getQyzt() {
		return qyzt;
	}

	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}

	public String getDbid() {
		return dbid;
	}


	public void setDbid(String dbid) {
		this.dbid = dbid;
	}


	public int getCountsc() {
		return countsc;
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

	public void setCountsc(int countsc) {
		this.countsc = countsc;
	}

	public int getCountbg() {
		return countbg;
	}

	public void setCountbg(int countbg) {
		this.countbg = countbg;
	}

	public int getCountyy() {
		return countyy;
	}

	public void setCountyy(int countyy) {
		this.countyy = countyy;
	}

	public int getCountxxhzc() {
		return countxxhzc;
	}

	public void setCountxxhzc(int countxxhzc) {
		this.countxxhzc = countxxhzc;
	}

	public int getCountjstz() {
		return countjstz;
	}

	public void setCountjstz(int countjstz) {
		this.countjstz = countjstz;
	}

	//
    public String getSc() {
		return sc;
	}



	public void setSc(String sc) {
		this.sc = sc;
	}



	public String getBg() {
		return bg;
	}



	public void setBg(String bg) {
		this.bg = bg;
	}



	public String getYy() {
		return yy;
	}



	public void setYy(String yy) {
		this.yy = yy;
	}



	public String getXxhzc() {
		return xxhzc;
	}



	public void setXxhzc(String xxhzc) {
		this.xxhzc = xxhzc;
	}



	public String getJstz() {
		return jstz;
	}



	public void setJstz(String jstz) {
		this.jstz = jstz;
	}



	public String getSydate() {
		return sydate;
	}



	public void setSydate(String sydate) {
		this.sydate = sydate;
	}



	public String getGgid() {
		return ggid;
	}



	public void setGgid(String ggid) {
		this.ggid = ggid;
	}



	public String getXxtype() {
		return xxtype;
	}



	public void setXxtype(String xxtype) {
		this.xxtype = xxtype;
	}



	public String getGgtime() {
		return ggtime;
	}



	public void setGgtime(String ggtime) {
		this.ggtime = ggtime;
	}



	public String getDqtime() {
		return dqtime;
	}



	public void setDqtime(String dqtime) {
		this.dqtime = dqtime;
	}



	public String getBt() {
		return bt;
	}



	public void setBt(String bt) {
		this.bt = bt;
	}



	public String getNr() {
		return nr;
	}



	public void setNr(String nr) {
		this.nr = nr;
	}



	public String getLrr() {
		return lrr;
	}



	public void setLrr(String lrr) {
		this.lrr = lrr;
	}



	public String getJlfh() {
		return jlfh;
	}



	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}



	public String getDytype() {
		return dytype;
	}



	public void setDytype(String dytype) {
		this.dytype = dytype;
	}



	public String getG2zd() {
		return g2zd;
	}



	public void setG2zd(String g2zd) {
		this.g2zd = g2zd;
	}



	public String getG3zd() {
		return g3zd;
	}



	public void setG3zd(String g3zd) {
		this.g3zd = g3zd;
	}



	public String getKdsb() {
		return kdsb;
	}



	public void setKdsb(String kdsb) {
		this.kdsb = kdsb;
	}



	public String getYysb() {
		return yysb;
	}



	public void setYysb(String yysb) {
		this.yysb = yysb;
	}



	public String getZpslzd() {
		return zpslzd;
	}



	public void setZpslzd(String zpslzd) {
		this.zpslzd = zpslzd;
	}



	public String getZssl() {
		return zssl;
	}



	public void setZssl(String zssl) {
		this.zssl = zssl;
	}



	public String getKdsbsl() {
		return kdsbsl;
	}



	public void setKdsbsl(String kdsbsl) {
		this.kdsbsl = kdsbsl;
	}



	public String getYysbsl() {
		return yysbsl;
	}



	public void setYysbsl(String yysbsl) {
		this.yysbsl = yysbsl;
	}



	public String getKt1() {
		return kt1;
	}



	public void setKt1(String kt1) {
		this.kt1 = kt1;
	}



	public String getKt2() {
		return kt2;
	}



	public void setKt2(String kt2) {
		this.kt2 = kt2;
	}



	public String getZgd() {
		return zgd;
	}



	public void setZgd(String zgd) {
		this.zgd = zgd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public String getSitetype() {
		return sitetype;
	}



	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}



	public String getG2() {
		return g2;
	}



	public void setG2(String g2) {
		this.g2 = g2;
	}



	public String getG3() {
		return g3;
	}



	public void setG3(String g3) {
		this.g3 = g3;
	}



	public String getOltg() {
		return oltg;
	}



	public void setOltg(String oltg) {
		this.oltg = oltg;
	}



	public String getKtnum() {
		return ktnum;
	}



	public void setKtnum(String ktnum) {
		this.ktnum = ktnum;
	}



	public String getSigntypenum() {
		return signtypenum;
	}


	public void setSigntypenum(String signtypenum) {
		this.signtypenum = signtypenum;
	}


	public String getFuzeren() {
		return fuzeren;
	}

	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}

	public String getDanjia() {
		return danjia;
	}

	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}

	public String getDfzflx() {
		return dfzflx;
	}

	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}

	public String getFkfs() {
		return fkfs;
	}

	public void setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}

	public String getFkzq() {
		return fkzq;
	}

	public void setFkzq(String fkzq) {
		this.fkzq = fkzq;
	}

	public String getWatchcost() {
		return watchcost;
	}

	public void setWatchcost(String watchcost) {
		this.watchcost = watchcost;
	}

	public String getZbdyhh() {
		return zbdyhh;
	}

	public void setZbdyhh(String zbdyhh) {
		this.zbdyhh = zbdyhh;
	}

	public String getBeilv() {
		return beilv;
	}

	public void setBeilv(String beilv) {
		this.beilv = beilv;
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

	public String getDbyt() {
		return dbyt;
	}

	public void setDbyt(String dbyt) {
		this.dbyt = dbyt;
	}

	public String getGldb() {
		return gldb;
	}

	public void setGldb(String gldb) {
		this.gldb = gldb;
	}
	
    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setGdfs(String gdfs) {
        this.gdfs = gdfs;
    }

    public void setJzname(String jzname) {
        this.jzname = jzname;
    }

    public void setBieming(String bieming) {
        this.bieming = bieming;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBgsign(String bgsign) {
        this.bgsign = bgsign;
    }

    public void setJnglmk(String jnglmk) {
        this.jnglmk = jnglmk;
    }

    public void setJztype(String jztype) {
        this.jztype = jztype;
    }

    public void setJzproperty(String jzproperty) {
        this.jzproperty = jzproperty;
    }

    public void setYflx(String yflx) {
        this.yflx = yflx;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZdcode(String zdcode) {

        this.zdcode = zdcode;
    }

    public void setDianfei(String dianfei) {
        this.dianfei = dianfei;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setKzid(String kzid) {
        this.kzid = kzid;
    }

    public void setCkkd(String ckkd) {
        this.ckkd = ckkd;
    }

    public void setYsymj(String ysymj) {
        this.ysymj = ysymj;
    }

    public void setJggs(String jggs) {
        this.jggs = jggs;
    }

    public void setYsygs(String ysygs) {
        this.ysygs = ysygs;
    }

    public void setJfgd(String jfgd) {
        this.jfgd = jfgd;
    }

    public void setSdwd(String sdwd) {
        this.sdwd = sdwd;
    }

    public void setSffs(String sffs) {
        this.sffs = sffs;
    }

    public void setLyfs(String lyfs) {
        this.lyfs = lyfs;
    }

    public void setZzjgbm(String zzjgbm) {
        this.zzjgbm = zzjgbm;
    }

    public void setXuni(String xuni) {
        this.xuni = xuni;
    }

    public void setCzzd(String czzd) {
        this.czzd = czzd;
    }

    public void setGczt(String gczt) {
        this.gczt = gczt;
    }

    public void setGcxm(String gcxm) {
        this.gcxm = gcxm;
    }

    public void setZdcq(String zdcq) {
        this.zdcq = zdcq;
    }

    public void setZdcb(String zdcb) {
        this.zdcb = zdcb;
    }

    public void setZlfh(String zlfh) {
        this.zlfh = zlfh;
    }

    public void setJnjsms(String jnjsms) {
        this.jnjsms = jnjsms;
    }

    public void setCzzdid(String czzdid) {
        this.czzdid = czzdid;
    }

    public void setGzqk(String gzqk) {
        this.gzqk = gzqk;
    }

    public void setNhzb(String nhzb) {
        this.nhzb = nhzb;
    }

    public void setZpsl(String zpsl) {
        this.zpsl = zpsl;
    }

    public void setZgry(String zgry) {
        this.zgry = zgry;
    }

    public void setKtsl(String ktsl) {
        this.ktsl = ktsl;
    }

    public void setPcsl(String pcsl) {
        this.pcsl = pcsl;
    }

    public void setRll(String rll) {
        this.rll = rll;
    }

    public void setNhjcdy(String nhjcdy) {
        this.nhjcdy = nhjcdy;
    }

    public void setDhID(String dhID) {
        this.dhID = dhID;
    }

    public void setZhwgID(String zhwgID) {
        this.zhwgID = zhwgID;
    }

    public void setDzywID(String dzywID) {
        this.dzywID = dzywID;
    }

    public void setLongitude(String longitude) {

        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {

        this.latitude = latitude;
    }

    public void setCaiji(String caiji) {
        this.caiji = caiji;
    }

    public void setEdhdl(String edhdl) {
        this.edhdl = edhdl;
    }

    public void setLjzs(String ljzs) {

        this.ljzs = ljzs;
    }

    public void setERPbh(String ERPbh) {
        this.ERPbh = ERPbh;
    }

    public void setPUE(int PUE) {
        this.PUE = PUE;
    }

    public String getFzr() {
        return fzr;
    }

    public String getMemo() {
        return memo;
    }

    public String getArea() {
        return area;
    }

    public String getGdfs() {
        return gdfs;
    }

    public String getJzname() {
        return jzname;
    }

    public String getBieming() {
        return bieming;
    }

    public String getAddress() {
        return address;
    }

    public String getBgsign() {
        return bgsign;
    }

    public String getJnglmk() {
        return jnglmk;
    }

    public String getJztype() {
        return jztype;
    }

    public String getJzproperty() {
        return jzproperty;
    }

    public String getYflx() {
        return yflx;
    }

    public String getShi() {
        return shi;
    }

    public String getXian() {
        return xian;
    }

    public String getId() {
        return id;
    }

    public String getZdcode() {

        return zdcode;
    }

    public String getDianfei() {
        return dianfei;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public String getTypename() {
        return typename;
    }

    public String getKzid() {
        return kzid;
    }

    public String getCkkd() {
        return ckkd;
    }

    public String getYsymj() {
        return ysymj;
    }

    public String getJggs() {
        return jggs;
    }

    public String getYsygs() {
        return ysygs;
    }

    public String getJfgd() {
        return jfgd;
    }

    public String getSdwd() {
        return sdwd;
    }

    public String getSffs() {
        return sffs;
    }

    public String getLyfs() {
        return lyfs;
    }

    public String getZzjgbm() {
        return zzjgbm;
    }

    public String getXuni() {
        return xuni;
    }

    public String getCzzd() {
        return czzd;
    }

    public String getGczt() {
        return gczt;
    }

    public String getGcxm() {
        return gcxm;
    }

    public String getZdcq() {
        return zdcq;
    }

    public String getZdcb() {
        return zdcb;
    }

    public String getZlfh() {
        return zlfh;
    }

    public String getJnjsms() {
        return jnjsms;
    }

    public String getCzzdid() {
        return czzdid;
    }

    public String getGzqk() {
        return gzqk;
    }

    public String getNhzb() {
        return nhzb;
    }

    public String getZpsl() {
        return zpsl;
    }

    public String getZgry() {
        return zgry;
    }

    public String getKtsl() {
        return ktsl;
    }

    public String getPcsl() {
        return pcsl;
    }

    public String getRll() {
        return rll;
    }

    public String getNhjcdy() {
        return nhjcdy;
    }

    public String getDhID() {
        return dhID;
    }

    public String getZhwgID() {
        return zhwgID;
    }

    public String getDzywID() {
        return dzywID;
    }

    public String getLongitude() {

        return longitude;
    }

    public String getLatitude() {

        return latitude;
    }

    public String getCaiji() {
        return caiji;
    }

    public String getEdhdl() {
        return edhdl;
    }

    public String getLjzs() {

        return ljzs;
    }

    public String getERPbh() {
        return ERPbh;
    }

    public int getPUE() {
        return PUE;
    }
    public void setJzxlx(String jzxlx) {
		this.jzxlx = jzxlx;
	}

	public String getJzxlx() {
		return jzxlx;
	}

	public void setJflx(String jflx) {
		this.jflx = jflx;
	}

	public String getJflx() {
		return jflx;
	}

	public void setTxj(String txj) {
		this.txj = txj;
	}

	public String getTxj() {
		return txj;
	}

	public void setUgs(String ugs) {
		this.ugs = ugs;
	}

	public String getUgs() {
		return ugs;
	}

	public void setYsyugs(String ysyugs) {
		this.ysyugs = ysyugs;
	}

	public String getYsyugs() {
		return ysyugs;
	}

	public void setJnjslx(String jnjslx) {
		this.jnjslx = jnjslx;
	}

	public String getJnjslx() {
		return jnjslx;
	}

	public void setYdlx(String ydlx) {
		this.ydlx = ydlx;
	}

	public String getYdlx() {
		return ydlx;
	}

	public void setJrwtype(String jrwtype) {
		this.jrwtype = jrwtype;
	}

	public String getJrwtype() {
		return jrwtype;
	}

	public void setKongtiao(String kongtiao) {
		this.kongtiao = kongtiao;
	}

	public String getKongtiao() {
		return kongtiao;
	}

	public void setGsf(String gsf) {
		this.gsf = gsf;
	}

	public String getGsf() {
		return gsf;
	}

	public void setStationtype(String stationtype) {
		this.stationtype = stationtype;
	}

	public String getStationtype() {
		return stationtype;
	}
	
	
public String getChangjia() {
		return changjia;
	}




	public void setChangjia(String changjia) {
		this.changjia = changjia;
	}




	public String getJzlx() {
		return jzlx;
	}




	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}




	public String getShebei() {
		return shebei;
	}




	public void setShebei(String shebei) {
		this.shebei = shebei;
	}




	public String getBbu() {
		return bbu;
	}




	public void setBbu(String bbu) {
		this.bbu = bbu;
	}




	public String getRru() {
		return rru;
	}




	public void setRru(String rru) {
		this.rru = rru;
	}




	public String getYdshebei() {
		return ydshebei;
	}




	public void setYdshebei(String ydshebei) {
		this.ydshebei = ydshebei;
	}




	public String getGxgwsl() {
		return gxgwsl;
	}




	public void setGxgwsl(String gxgwsl) {
		this.gxgwsl = gxgwsl;
	}
	
	



	public String getTwgx() {
		return twgx;
	}




	public void setTwgx(String twgx) {
		this.twgx = twgx;
	}
	
	




	public String getBm() {
		return bm;
	}




	public void setBm(String bm) {
		this.bm = bm;
	}




	//��վ���޸Ĳ�ѯ
    public synchronized SiteModifyBean getJizhan(String id,String dbid) {

        StringBuffer sql = new StringBuffer();
        sql.append("select z.SHI,z.XIAN,z.JZTYPE,z.PROPERTY,z.YFLX,z.JZNAME,z.BIEMING,z.ADDRESS,z.BGSIGN,z.JNGLMK,z.GDFS,z.AREA,z.FZR,z.SHENG,z.CJR,TO_CHAR(z.CJTIME,'yyyy-mm-dd') CJTIME,'' as  DANJIA,z.ZDCODE,z.XIAOQU");
        sql.append(",(select name from indexs where type='ZDLX' and code=z.jztype)");
        sql.append(",z.ZZJGBM,z.GCZT,z.GCXM,z.ZDCQ,z.ZDCB,z.ZLFH,z.CZZDID");
        sql.append(",z.ERPBH,z.DHID,z.ZHWGID,z.DZYWID,z.LONGITUDE,z.LATITUDE,z.XLX,z.JFLX,z.jrwtype,z.EDHDL,z.jlfh,z.gsf,z.entrypensonnel,to_char(z.entrytime,'yyyy-mm-dd') entrytime,z.stationtype,(select id from BENCHMARKING b where b.id=z.signtypenum)");
        sql.append(",DYTYPE,G2,G3,KDSB,YYSB,ZPSL,ZSSL,KDSBSL,YYSBSL,KT1,KT2,ZGD,TO_CHAR(SYDATE,'yyyy-mm-dd') SYDATE,z.QYZT,lyjhjgs,gxxx,to_char(jskssj,'yyyy-mm-dd') jskssj,to_char(jsjssj,'yyyy-mm-dd') jsjssj,xmh,ygd,ysd,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,twgx,bm,dzyf,dzbm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,QSDBDL,kts,ktzgl,ysjts,wjts,yybgkt,jfsckt,ktyps,kteps,ktps");
        sql.append(",z.ZYBYQLX,'' as CHANGEVALUE,z.zldy,z.JZCODE,z.id,z.zdcq,z.wlzdbm,z.ltqx,z.ydqx,z.zzlx,z.LONGITUDE,z.LATITUDE,z.nhxtid,z.fzrphone,z.changjia,z.bm");

        
        sql.append(" from zhandian z  where  z.id=" +id);
        System.out.println("վ�㣺"+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            if (rs.next()) {
            	this.setZdcb(rs.getString("zdcq"));
            	this.setWlzdbm(rs.getString("wlzdbm"));
            	this.setLtqx(rs.getString("ltqx"));
            	this.setYdqx(rs.getString("ydqx"));
            	this.setZzlx(rs.getString("zzlx"));
            	this.setLongitude(rs.getString("LONGITUDE"));
            	this.setLatitude(rs.getString("LATITUDE"));
            	this.setNhxtid(rs.getString("nhxtid"));
            	this.setFzrphone(rs.getString("fzrphone"));
            	this.setChangjia(rs.getString("changjia"));
            	this.setBm(rs.getString("bm"));
            	this.setJzcode(rs.getString("JZCODE"));
            	this.setZldy(rs.getString("zldy"));
            	this.setId(rs.getString("id"));
                this.setShi(rs.getString(1));
                this.setXian(rs.getString(2));
                this.setJztype(rs.getString(3));
                this.setJzproperty(rs.getString(4));
                this.setYflx(rs.getString(5));
                this.setJzname(rs.getString(6));
                this.setBieming(rs.getString(7)!=null?rs.getString(7):"");
                this.setAddress(rs.getString(8)!=null?rs.getString(8):"");
                this.setBgsign(rs.getString(9));
                this.setJnglmk(rs.getString(10));
                this.setGdfs(rs.getString(11));
                this.setArea(rs.getString(12)!=null?rs.getString(12):"");
              
                this.setFuzeren(rs.getString(13)!=null?rs.getString(13):"");
                //this.setDanjia(rs.getString(17)!=null?rs.getString(17):"");
                this.setZdcode(rs.getString(18));
                this.setXiaoqu(rs.getString(19));
                this.setTypename(rs.getString(20));
         
                this.setZzjgbm(rs.getString(21)!=null?rs.getString(21):"");
                this.setGczt(rs.getString(22)!=null?rs.getString(22):"0");
                this.setGcxm(rs.getString(23)!=null?rs.getString(23):"0");
                this.setZdcq(rs.getString(24)!=null?rs.getString(24):"0");
                this.setZdcb(rs.getString(25)!=null?rs.getString(25):"0");
                this.setZlfh(rs.getString(26)!=null?rs.getString(26):"");
                this.setERPbh(rs.getString(28)!=null?rs.getString(28):"0");
                this.setDhID(rs.getString(29)!=null?rs.getString(29):"");
                this.setZhwgID(rs.getString(30)!=null?rs.getString(30):"");
                this.setDzywID(rs.getString(31)!=null?rs.getString(31):"");
                this.setLongitude(rs.getString(32)!=null?rs.getString(32):"");
                this.setLatitude(rs.getString(33)!=null?rs.getString(33):"");
                this.setJzxlx(rs.getString(34)!=null?rs.getString(34):"");
                //С����
                this.setJflx(rs.getString(35)!=null?rs.getString(35):"0");
                this.setJrwtype(rs.getString(36)!=null?rs.getString(36):"0");
                this.setEdhdl(rs.getString(37)!=null?rs.getString(37):"");
                this.setJlfh(rs.getString(38)!=null?rs.getString(38):"");
                this.setGsf(rs.getString(39)!=null?rs.getString(39):"-1");
                this.setStationtype(rs.getString(42)!=null?rs.getString(42):"0");
                this.setSigntypenum(rs.getString(43)!=null?rs.getString(43):"0");
                //վ�㸽����Ϣ
                this.setDytype(rs.getString(44)!=null?rs.getString(44):"0");//��������
                this.setG2zd(rs.getString(45)!=null?rs.getString(45):"0");//վ��2G�豸
                this.setG3zd(rs.getString(46)!=null?rs.getString(46):"0");//վ��3G�豸
                this.setKdsb(rs.getString(47)!=null?rs.getString(47):"0");//����豸
                this.setYysb(rs.getString(48)!=null?rs.getString(48):"0");//�����豸
                this.setZpslzd(rs.getString(49)!=null?rs.getString(49):"");//վ����Ƶ����
                this.setZssl(rs.getString(50)!=null?rs.getString(50):"");//��������
                this.setKdsbsl(rs.getString(51)!=null?rs.getString(51):"");//����豸����
                this.setYysbsl(rs.getString(52)!=null?rs.getString(52):"");//�����豸����
                this.setKt1(rs.getString(53)!=null?rs.getString(53):"0");//�յ�1
                this.setKt2(rs.getString(54)!=null?rs.getString(54):"0");//�յ�2
                this.setZgd(rs.getString(55)!=null?rs.getString(55):"0");//ֱ����
                this.setSydate(rs.getString(56)!=null?rs.getString(56):"");//Ͷ��ʹ��ʱ��
                this.setQyzt(rs.getString(57)!=null?rs.getString(57):"");//վ������״̬
                this.setLyjhjgs(rs.getString(58)!=null?rs.getString(58):"0");//¥���������
                this.setGxxx(rs.getString(59)!=null?rs.getString(59):"0");//������Ϣ
                this.setJskssj(rs.getString(60)!=null?rs.getString(60):"");//���迪ʼʱ��
                this.setJsjssj(rs.getString(61)!=null?rs.getString(61):"");//�������ʱ��
                this.setXmh(rs.getString(62)!=null?rs.getString(62):"");//��Ŀ��
                this.setYgd(rs.getString(63)!=null?rs.getString(63):"0");//Զ����
                this.setYsd(rs.getString(64)!=null?rs.getString(64):"0");//Զ�ܵ�
                this.setChangjia(rs.getString(65)!=null?rs.getString(65) : "");//����
                this.setJzlx(rs.getString(66)!=null?rs.getString(66) : "");//��վ����
                this.setShebei(rs.getString(67)!=null?rs.getString(67) : "");//�豸����
                this.setBbu(rs.getString(68)!=null ?rs.getString(68): "0");//bbu����
                this.setRru(rs.getString(69)!=null ? rs.getString(69): "0");//rru����
                this.setYdshebei(rs.getString(70)!=null ?rs.getString(70): "0");//�ƶ��豸����
                this.setGxgwsl(rs.getString(71) !=null ? rs.getString(71) : "0");//��������豸����
                this.setTwgx(rs.getString(72) !=null ? rs.getString(72) : "");//��������
                this.setBm(rs.getString(73) !=null? rs.getString(73) : "");//����
                this.setDzyf(rs.getString(74) !=null? rs.getString(74) : "");//�����·�
                this.setDzbm(rs.getString(75) !=null? rs.getString(75) : "");//�������
                this.setG2xqs(rs.getString(76) !=null? rs.getString(76) : "0");//2GС����
                this.setG3sqsl(rs.getString(77) !=null? rs.getString(77) : "0");
                this.setYdgxsbsl(rs.getString(78) !=null? rs.getString(78) : "0");
                this.setDxgxsbsl(rs.getString(79) !=null? rs.getString(79) : "0");
                this.setQsdbdl(rs.getString(80) !=null? rs.getString(80) : "");
                this.setKts(rs.getString(81) !=null? rs.getString(81) : "0");
                this.setKtzgl(rs.getString(82) !=null? rs.getString(82) : "0");
                this.setYsjts(rs.getString(83) !=null? rs.getString(83) : "0");
                this.setWjts(rs.getString(84) !=null? rs.getString(84) : "0");
                this.setYybgkt(rs.getString(85) !=null? rs.getString(85) : "0");
                this.setJfsckt(rs.getString(86) !=null? rs.getString(86) : "0");
                this.setKtyps(rs.getString(87)!=null?rs.getString(87):"0");
                this.setKteps(rs.getString(88)!=null?rs.getString(88):"0");
                this.setKtps(rs.getString(89)!=null?rs.getString(89):"0");
                
                this.setZybyqlx(rs.getString(90)!=null?rs.getString(90):"");
                this.setBsdl(rs.getString(91)!=null?rs.getString(91):"0");
                System.out.println(rs.getString(60)+"----------------------");
            }
            sql.setLength(0);
			
            sql.append("select zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs,gzqk,nhzb,zpsl,zgry,ktsl,pcsl,rll,ljzs,txj,ugs,ysyugs,jnjslx,ydlx from zhandiankz where zdid="+id);
            System.out.println("վ����չ��Ϣ��"+sql);
          //  rs = db.queryAll(sql.toString());
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }

        return this;
    }

	
    //��������޸Ĳ�ѯ
    public synchronized SiteModifyBean getSign(String id){
    	StringBuffer sql = new StringBuffer();
    	sql.append("SELECT NAME,STATIONTYPEID,REGION,G2,G3,OLT,AIR_CONDITION FROM BENCHMARKING WHERE ID="+id);
    	DataBase db = new DataBase();
        ResultSet rs = null;
    	try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			
				while(rs.next()){
					this.setName(rs.getString(1)!=null?rs.getString(1):"0");//�����������
					this.setSitetype(rs.getString(2)!=null?rs.getString(2):"0");//վ������
					this.setRegion(rs.getString(3)!=null?rs.getString(3):"0");//��������
					this.setG2(rs.getString(4)!=null?rs.getString(4):"");//2G
					this.setG3(rs.getString(5)!=null?rs.getString(5):"");//3G
					this.setOltg(rs.getString(6)!=null?rs.getString(6):"");//OLTG
					this.setKtnum(rs.getString(7)!=null?rs.getString(7):"");//�յ�����
					
					
					
				}
            }
    	  catch (DbException de) {
              de.printStackTrace();
          } catch (SQLException de) {
              de.printStackTrace();
          }
          finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException se) {
                      se.printStackTrace();
                  }
              }
              try {
                  db.close();
              } catch (DbException de) {
                  de.printStackTrace();
              }

          }

    	return this;
}
  
    
    
  //��ѯ������id
    public synchronized SiteModifyBean dbid(String zdid,String accountname){
    	DataBase db = new DataBase();
    	ResultSet rs = null;
    	StringBuffer sql = new StringBuffer();
    	Random rnd = new Random();//�����
   	    String sjdbid = (String) (rnd.nextInt(999999999)+"");
   	    String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
   	    
    	sql.append("select max(db.dbid) from zhandian zd,dianbiao db where zd.id=db.zdid and db.dbyt='dbyt01' and zd.id='"+zdid+"'");
    	
    	String sql1="INSERT INTO DIANBIAO(zdid,dbid,dfzflx,dbyt,bzw,dbqyzt)values('"+zdid+"','"+sjdbid+"','dfzflx01','dbyt01','1','1') ";
    	String sql2="update dianbiao set dbid=(select id from dianbiao where dbid='"+sjdbid+"') where id=(select id from dianbiao where dbid='"+sjdbid+"')";
    	String sql3="insert into sbgl(dianbiaoid,SHEIEBANID,SHUOSHUZHUANYE,DBILI,FTBZW,xjid,xjbili,ENTRYPENSONNEL,ENTRYTIME)values((select id from dianbiao where dbid='"+sjdbid+"'),"
		                             +" to_char((select id from dianbiao where dbid = '"+sjdbid+"')) || '01"+"','zylx01','100','1',to_char((select id from dianbiao where dbid = '"+sjdbid+"')) || '0101','100','"+accountname+"',"+s+")";//����";
    	
    	System.out.println("dbid:"+sql.toString());
       	try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			
				if(rs.next()){
					this.setDbid(rs.getString(1)!=null?rs.getString(1):"");//id
					System.out.println("���1111id��"+this.getDbid());
				}
				if("".equals(this.getDbid())||this.getDbid()==null||this.getDbid()==""){ //���û�е�� �������Ӧ����Ϣ
					System.out.println("������"+sql1);
					System.out.println("�����̯"+sql3);
					System.out.println("�޸ĵ��id��"+sql2);
					System.out.println("��ѯ���id��"+sql.toString());
					db.update(sql1);
					db.update(sql3);
					rs = db.queryAll(sql2);	
					rs=db.queryAll(sql.toString());
					if(rs.next()){
						this.setDbid(rs.getString(1)!=null?rs.getString(1):"");//���id
						System.out.println("���2222id��"+this.getDbid());
					}
					
					
				}
				
				
				
				
            }
    	  catch (DbException de) {
              de.printStackTrace();
          } catch (SQLException de) {
              de.printStackTrace();
          }
          finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException se) {
                      se.printStackTrace();
                  }
              }
              try {
                  db.close();
              } catch (DbException de) {
                  de.printStackTrace();
              }

          }
    	

    	
    	return this;
    }
  //��ѯ������id
    public synchronized String getzdid(int ii,String login,String shengid,String whereStr){
    	DataBase db = new DataBase();
    	ResultSet rs = null;
    	StringBuffer sql = new StringBuffer();
    	Random rnd = new Random();//�����
   	    String sjdbid = (String) (rnd.nextInt(999999999)+"");
   	    String zdid="";
    	
    	sql.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select z.id from zhandian z  where  z.sheng = '"+shengid+"' "+whereStr+" " +
    			"and ((z.xiaoqu in (select t.agcode    from per_area t where t.accountid = '"+login+"'))) ORDER BY z.JZNAME) A )WHERE RN = "+ii+"" );
    	System.out.println("zdid:"+sql.toString());
try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			
				if(rs.next()){
					zdid=rs.getString(1)!=null?rs.getString(1):"";//id
					System.out.println("վ��1111id��"+zdid);
				}else{
					sql = new StringBuffer();
					rs=null;
					ii=ii-1;
					sql.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select z.id from zhandian z  where  z.sheng = '"+shengid+"'  "+whereStr+" " +
			    			"and ((z.xiaoqu in (select t.agcode    from per_area t where t.accountid = '"+login+"'))) ORDER BY z.JZNAME) A )WHERE RN = "+ii+"" );
			    	System.out.println("zdid:"+sql.toString());
			    	rs = db.queryAll(sql.toString());
			    	if(rs.next()){
						zdid=rs.getString(1)!=null?rs.getString(1):"";//id
						System.out.println("վ��1111id��"+zdid);
					}
				}
            }
    	  catch (DbException de) {
              de.printStackTrace();
          } catch (SQLException de) {
              de.printStackTrace();
          }
          finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException se) {
                      se.printStackTrace();
                  }
              }
              try {
                  db.close();
              } catch (DbException de) {
                  de.printStackTrace();
              }

          }
    	

    	
    	return zdid;
    }
   //������������ѯ
    public synchronized SiteModifyBean getGgao(String id){
    	StringBuffer sql = new StringBuffer();
    	sql.append("SELECT id,xxtype,ggtime,dqtime,bt,nr,lrr FROM gonggao g WHERE ID="+id);
    	DataBase db = new DataBase();
        ResultSet rs = null;
    	try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			System.out.println("��������ѯ"+sql.toString());
				while(rs.next()){
					this.setGgid(rs.getString(1)!=null?rs.getString(1):"");//id
					this.setXxtype(rs.getString(2)!=null?rs.getString(2):"0");//��Ϣ����
					this.setGgtime(rs.getString(3)!=null?rs.getString(3):"");//����ʱ��
					this.setDqtime(rs.getString(4)!=null?rs.getString(4):"");//��ǰʱ��
					this.setBt(rs.getString(5)!=null?rs.getString(5):"");//����
					this.setNr(rs.getString(6)!=null?rs.getString(6):"");//����
					this.setLrr(rs.getString(7)!=null?rs.getString(7):"");//¼����
					
					
					
				}
            }
    	  catch (DbException de) {
              de.printStackTrace();
          } catch (SQLException de) {
              de.printStackTrace();
          }
          finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException se) {
                      se.printStackTrace();
                  }
              }
              try {
                  db.close();
              } catch (DbException de) {
                  de.printStackTrace();
              }

          }

    	return this;
}
    //ȫʡ���������ѯ
    public synchronized SiteModifyBean getDzdl(String yf,String bm){
    
    	String s=yf.substring(5, 7);
    	System.out.println("sssssssssss"+s);
    	if(s.equals("01")){s="month1";}
	    if(s.equals("02")){s="month2";}
	    if(s.equals("03")){s="month3";}
	    if(s.equals("04")){s="month4";}
	    if(s.equals("05")){s="month5";}
	    if(s.equals("06")){s="month6";}
	    if(s.equals("07")){s="month7";}
	    if(s.equals("08")){s="month8";}
	    if(s.equals("09")){s="month9";}
	    if(s.equals("10")){s="month10";}
	    if(s.equals("11")){s="month11";}
	    if(s.equals("12")){s="month12";}

    	
    	StringBuffer sql = new StringBuffer();
    	sql.append("select "+s+" from zdnhbz n where n.lxbh='"+bm+"'");
    	DataBase db = new DataBase();
        ResultSet rs = null;
    	try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			System.out.println("ʡ�����������ѯ"+sql.toString());
				while(rs.next()){
					this.setDzdl(rs.getString(1)!=null?rs.getString(1):"");
					
				}
            }
    	  catch (DbException de) {
              de.printStackTrace();
          } catch (SQLException de) {
              de.printStackTrace();
          }
          finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException se) {
                      se.printStackTrace();
                  }
              }
              try {
                  db.close();
              } catch (DbException de) {
                  de.printStackTrace();
              }

          }

    	return this;
}
}
