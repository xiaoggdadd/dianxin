package com.noki.jizhan;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ZhanDianForm {
    private String fzr;
    private String memo;
    private String area;
    private String gdfs;
    private String jzname;
    private String bieming;
    private String address;
    private String bgsign;
    private String jnglmk;
    private String jztype;
    private String jzproperty;
    private String yflx;
    private String shi;
    private String xian;
    private String id;
    private String zdcode;
    private String dianfei;
    private String xiaoqu;
    private String typename;
    private String kzid;
    private String ckkd;
    private String ysymj;
    private String jggs;
    private String ysygs;
    private String jfgd;
    private String sdwd;
    private String sffs;
    private String lyfs;
    private int PUE;
    private String zzjgbm;
    private String xuni;
    private String czzd;
    private String gczt;
    private String gcxm;
    private String zdcq;
    private String zdcb;
    private String zlfh;
    private String jnjsms;
    private String czzdid;
    private String gzqk;
    private String nhzb;
    private String zpsl;
    private String zgry;
    private String ktsl;
    private String pcsl;
    private String rll;
    private String nhjcdy;
    private String ERPbh;
    private String dhID;
    private String zhwgID;
    private String dzywID;
    private String longitude;
    private String latitude;
    private String caiji;
    private String edhdl;
    private String ljzs;
    private String jzxlx;
    private String jflx;
    private String txj;
    private String ugs;
    private String ysyugs;
    private String jnjslx;
    private String ydlx;
    private String jrwtype;
    private String kongtiao;
    private String gsf;
    private String stationtype;
    private String signtypenum;//标杆类型编号
    private String zhan;//站点总数
    private String dianbiao;//电表数
    private String jiaofei;//电表缴费数
    private String zidong;//自动审核数
    private String rengong;//人工审核数
    private String shiji;//市级审核数
    private String caiwu;//财务审核数
    private String liuchenghao;//有流程号的数量
    private String code;
    private String counn3;
    private String dfzflx;
    private String fkzq;
    private String dfel;//交费次数
    private String dfpay;//最后交费时间
    //========== 新增专业
    private int zynum;//专业站点数
     private String zywc;//专业完成
     private String zywf;//专业未分
     private String zyyc;//专业异常
     private double zybl;//专业比例
     //=========新增详细
     private int xxnum;//详细站点数
     private String xxwc;//详细完成
     private String xxwf;//详细未分
     private String xxyc;//详细异常
     private double xxbl;//详细比例
     private String shicode;
     //==================SDF.ENTRYPENSONNEL,ACC.MOBILE,ACC.EMAIL,ACC.POSITION,ACC.BUMEN
     private String entrypensonnel;
     private String MOBILE;
     private String EMAIL;
     private String POSITION;
     private String BUMEN;
    private String sex;
    private String accname;
    private String rolename;
     //ACCOUNTNAME, ACC.ROLENAME,
     
     
     
     
    
    public String getSex() {
		return sex;
	}

	public String getAccname() {
		return accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEntrypensonnel() {
		return entrypensonnel;
	}

	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}

	public String getBUMEN() {
		return BUMEN;
	}

	public void setBUMEN(String bUMEN) {
		BUMEN = bUMEN;
	}

	public String getShicode() {
		return shicode;
	}

	public void setShicode(String shicode) {
		this.shicode = shicode;
	}

	public int getZynum() {
		return zynum;
	}

	public void setZynum(int zynum) {
		this.zynum = zynum;
	}

	public String getZywc() {
		return zywc;
	}

	public void setZywc(String zywc) {
		this.zywc = zywc;
	}

	public String getZywf() {
		return zywf;
	}

	public void setZywf(String zywf) {
		this.zywf = zywf;
	}

	public String getZyyc() {
		return zyyc;
	}

	public void setZyyc(String zyyc) {
		this.zyyc = zyyc;
	}

	public double getZybl() {
		return zybl;
	}

	public void setZybl(double zybl) {
		this.zybl = zybl;
	}

	public int getXxnum() {
		return xxnum;
	}

	public void setXxnum(int xxnum) {
		this.xxnum = xxnum;
	}

	public String getXxwc() {
		return xxwc;
	}

	public void setXxwc(String xxwc) {
		this.xxwc = xxwc;
	}

	public String getXxwf() {
		return xxwf;
	}

	public void setXxwf(String xxwf) {
		this.xxwf = xxwf;
	}

	public String getXxyc() {
		return xxyc;
	}

	public void setXxyc(String xxyc) {
		this.xxyc = xxyc;
	}

	public double getXxbl() {
		return xxbl;
	}

	public void setXxbl(double xxbl) {
		this.xxbl = xxbl;
	}

	public String getFkzq() {
		return fkzq;
	}

	public void setFkzq(String fkzq) {
		this.fkzq = fkzq;
	}

	public String getDfel() {
		return dfel;
	}

	public void setDfel(String dfel) {
		this.dfel = dfel;
	}

	public String getDfpay() {
		return dfpay;
	}

	public void setDfpay(String dfpay) {
		this.dfpay = dfpay;
	}

	public String getDfzflx() {
		return dfzflx;
	}

	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}

	public String getCounn3() {
		return counn3;
	}

	public void setCounn3(String counn3) {
		this.counn3 = counn3;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShiji() {
		return shiji;
	}

	public void setShiji(String shiji) {
		this.shiji = shiji;
	}

	public String getZhan() {
		return zhan;
	}

	public void setZhan(String zhan) {
		this.zhan = zhan;
	}

	public String getDianbiao() {
		return dianbiao;
	}

	public void setDianbiao(String dianbiao) {
		this.dianbiao = dianbiao;
	}

	public String getJiaofei() {
		return jiaofei;
	}

	public void setJiaofei(String jiaofei) {
		this.jiaofei = jiaofei;
	}

	public String getZidong() {
		return zidong;
	}

	public void setZidong(String zidong) {
		this.zidong = zidong;
	}

	public String getRengong() {
		return rengong;
	}

	public void setRengong(String rengong) {
		this.rengong = rengong;
	}

	public String getCaiwu() {
		return caiwu;
	}

	public void setCaiwu(String caiwu) {
		this.caiwu = caiwu;
	}

	public String getLiuchenghao() {
		return liuchenghao;
	}

	public void setLiuchenghao(String liuchenghao) {
		this.liuchenghao = liuchenghao;
	}

	public String getSigntypenum() {
		return signtypenum;
	}

	public void setSigntypenum(String signtypenum) {
		this.signtypenum = signtypenum;
	}

	public ZhanDianForm() {
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

    public synchronized ZhanDianForm getJizhan(String id) {

        StringBuffer sql = new StringBuffer();
        sql.append("select SHI,XIAN,JZTYPE,PROPERTY,YFLX,JZNAME,BIEMING,ADDRESS,BGSIGN,JNGLMK,GDFS,AREA,MEMO,FZR,SHENG,CJR,CJTIME,DIANFEI,ZDCODE,XIAOQU");
        sql.append(",(select name from indexs where type='ZDLX' and code=z.jztype)");
        sql.append(",PUE,ZZJGBM,XUNI,CZZD,GCZT,GCXM,ZDCQ,ZDCB,ZLFH,JNJSMS,CZZDID");
        sql.append(",NHJCDY,ERPBH,DHID,ZHWGID,DZYWID,LONGITUDE,LATITUDE,CAIJI,XLX,JFLX,EDHDL,jrwtype,kongtiao,gsf,stationtype,(select id from BENCHMARKING b  where b.id=z.signtypenum)");
        sql.append(" from zhandian z where z.id=" +
                id);
        System.out.println(sql);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            if (rs.next()) {
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
                this.setMemo(rs.getString(13)!=null?rs.getString(13):"");
                this.setFzr(rs.getString(14)!=null?rs.getString(14):"");
                this.setDianfei(rs.getString(18)!=null?rs.getString(18):"");
                this.setZdcode(rs.getString(19));
                this.setXiaoqu(rs.getString(20));
                this.setTypename(rs.getString(21));
                this.setPUE(rs.getInt(22));
                this.setZzjgbm(rs.getString(23)!=null?rs.getString(23):"");
                this.setXuni(rs.getString(24));
                this.setCzzd(rs.getString(25)!=null?rs.getString(25):"");
                this.setGczt(rs.getString(26)!=null?rs.getString(26):"0");
                this.setGcxm(rs.getString(27)!=null?rs.getString(27):"0");
                this.setZdcq(rs.getString(28)!=null?rs.getString(28):"0");
                this.setZdcb(rs.getString(29)!=null?rs.getString(29):"0");
                this.setZlfh(rs.getString(30)!=null?rs.getString(30):"");
                this.setJnjsms(rs.getString(31)!=null?rs.getString(31):"");
                this.setCzzdid(rs.getString(32));
                this.setNhjcdy(rs.getString(33));
                this.setERPbh(rs.getString(34)!=null?rs.getString(34):"0");
                this.setDhID(rs.getString(35)!=null?rs.getString(35):"");
                this.setZhwgID(rs.getString(36)!=null?rs.getString(36):"");
                this.setDzywID(rs.getString(37)!=null?rs.getString(37):"");
                this.setLongitude(rs.getString(38)!=null?rs.getString(38):"");
                this.setLatitude(rs.getString(39)!=null?rs.getString(39):"");
                this.setCaiji(rs.getString(40));
                this.setJzxlx(rs.getString(41)!=null?rs.getString(41):"0");
                this.setJflx(rs.getString(42)!=null?rs.getString(42):"0");
                this.setEdhdl(rs.getString(43)!=null?rs.getString(43):"");
                this.setJrwtype(rs.getString(44)!=null?rs.getString(44):"0");
                this.setKongtiao(rs.getString(45)!=null?rs.getString(45):"");
                this.setGsf(rs.getString(46)!=null?rs.getString(46):"-1");
                this.setStationtype(rs.getString(47)!=null?rs.getString(47):"0");
                this.setSigntypenum(rs.getString(48)!=null?rs.getString(48):"0");
                System.out.println(rs.getString(42)+"----------------------");
            }
            sql.setLength(0);
            sql.append("select zdid,ckkd,ysymj,jggs,ysygs,jfgd,sdwd,sffs,lyfs,gzqk,nhzb,zpsl,zgry,ktsl,pcsl,rll,ljzs,txj,ugs,ysyugs,jnjslx,ydlx from zhandiankz where zdid="+id);
            rs = db.queryAll(sql.toString());
            if(rs.next()){
                this.setKzid(rs.getString(1)!=null?rs.getString(1):"0");
                this.setCkkd(rs.getString(2)!=null?rs.getString(2):"");
                this.setYsymj(rs.getString(3)!=null?rs.getString(3):"");
                this.setJggs(rs.getString(4)!=null?rs.getString(4):"");
                this.setYsygs(rs.getString(5)!=null?rs.getString(5):"");
                this.setJfgd(rs.getString(6)!=null?rs.getString(6):"");
                this.setSdwd(rs.getString(7)!=null?rs.getString(7):"");
                this.setSffs(rs.getString(8)!=null?rs.getString(8):"0");
                this.setLyfs(rs.getString(9)!=null?rs.getString(9):"0");
                this.setGzqk(rs.getString(10)!=null?rs.getString(10):"");
                this.setNhzb(rs.getString(11)!=null?rs.getString(11):"");
                this.setZpsl(rs.getString(12)!=null?rs.getString(12):"");
                this.setZgry(rs.getString(13)!=null?rs.getString(13):"");
                this.setKtsl(rs.getString(14)!=null?rs.getString(14):"");
                this.setPcsl(rs.getString(15)!=null?rs.getString(15):"");
                this.setRll(rs.getString(16)!=null?rs.getString(16):"");
                 this.setLjzs(rs.getString(17)!=null?rs.getString(17):"");
                 this.setTxj(rs.getString(18)!=null?rs.getString(18):"");
                 this.setUgs(rs.getString(19)!=null?rs.getString(19):"");
                 this.setYsyugs(rs.getString(20)!=null?rs.getString(20):"");
                 this.setJnjslx(rs.getString(21)!=null?rs.getString(21):"");
                 this.setYdlx(rs.getString(22)!=null?rs.getString(22):"");
            }else{
                this.setKzid("0");
                this.setCkkd("");
                this.setYsymj("");
                this.setJggs("");
                this.setYsygs("");
                this.setJfgd("");
                this.setSdwd("");
                this.setSffs("0");
                this.setLyfs("0");
                this.setGzqk("");
                this.setNhzb("");
                this.setZpsl("");
                this.setZgry("");
                this.setKtsl("");
                this.setPcsl("");
                this.setRll("");
                this.setLjzs("");
                this.setTxj("");
                this.setUgs("");
                this.setYsyugs("");
                this.setJnjslx("");
                this.setYdlx("");

            }
            db.close();
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

}
