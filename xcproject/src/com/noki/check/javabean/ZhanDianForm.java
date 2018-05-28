package com.noki.check.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
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
    private String jzcode;
    private String bieming;
    private String address;
    private String bgsign;
    private String jnglmk;
    private String jztype;
    private String jzproperty;
    private String yflx;
    private String shi;
    private String xian;
    private String sheng;
    private String id;
    private String cjr;
    private String cjtime;
    private String manualauditstatus_station;
    private String manualauditname_station;
    private String manualauditdatetime_station;
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

	public String getManualauditstatus_station() {
		return manualauditstatus_station;
	}

	public void setManualauditstatus_station(String manualauditstatus_station) {
		this.manualauditstatus_station = manualauditstatus_station;
	}

	public String getManualauditname_station() {
		return manualauditname_station;
	}

	public void setManualauditname_station(String manualauditname_station) {
		this.manualauditname_station = manualauditname_station;
	}

	public String getManualauditdatetime_station() {
		return manualauditdatetime_station;
	}

	public void setManualauditdatetime_station(String manualauditdatetime_station) {
		this.manualauditdatetime_station = manualauditdatetime_station;
	}
	
	/**
	   * 账户明细与修改界面调用此方法
	   * @param accountId String
	   * @return AccountFormBeangetAmmeterDegreeAllInfo
	   */
	  public synchronized ZhanDianForm getZhanDianInfo(String degreeid) {
		  ZhanDianForm bean = new ZhanDianForm();
	    StringBuffer sql = new StringBuffer();
	    sql.append("select * from zhandian where ID="+degreeid);
	    
	    DataBase db = new DataBase();
	    try {
	      System.out.println("getZhanDianInfo:"+sql);
	      db.connectDb();
	      ResultSet rs = null;
	      rs = db.queryAll(sql.toString());
	      while (rs.next()) {
	    	bean.setId(rs.getString("id") != null ? rs.getString("id") : "");
	    	bean.setAddress(rs.getString("address") != null ? rs.getString("address") : "");
	    	bean.setArea(rs.getString("area") != null ? rs.getString("area") : "");
	    	bean.setBgsign(rs.getString("bgsign") != null ? rs.getString("bgsign") : "");
	    	bean.setBieming(rs.getString("bieming") != null ? rs.getString("bieming") : "");
	    	bean.setFzr(rs.getString("fzr") != null ? rs.getString("fzr") : "");
	    	bean.setGdfs(rs.getString("gdfs") != null ? rs.getString("gdfs") : "");
	    	bean.setJnglmk(rs.getString("jnglmk") != null ? rs.getString("jnglmk") : "");
	    	bean.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");
	    	bean.setJzproperty(rs.getString("property") != null ? rs.getString("property") : "");
	    	bean.setJztype(rs.getString("jztype") != null ? rs.getString("jztype") : "");
	    	bean.setManualauditdatetime_station(rs.getString("manualauditdatetime_station") != null ? rs.getString("manualauditdatetime_station") : "");
	    	bean.setManualauditname_station(rs.getString("manualauditname_station") != null ? rs.getString("manualauditname_station") : "");
	    	bean.setManualauditstatus_station(rs.getString("manualauditstatus_station") != null ? rs.getString("manualauditstatus_station") : "");
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	bean.setShi(rs.getString("shi") != null ? rs.getString("shi") : "");
	    	bean.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");
	    	bean.setYflx(rs.getString("yflx") != null ? rs.getString("yflx") : "");
	    	bean.setMemo(rs.getString("memo") != null ? rs.getString("memo") : "");
	    	bean.setSheng(rs.getString("sheng") != null ? rs.getString("sheng") : "");
	    	bean.setCjr(rs.getString("cjr") != null ? rs.getString("cjr") : "");
	    	bean.setCjtime(rs.getString("cjtime") != null ? rs.getString("cjtime") : "");
	    	bean.setJzcode(rs.getString("jzcode") != null ? rs.getString("jzcode") : "");
	    	
	        //bean.setMemo(rs.getString(14) != null ? rs.getString(14) : "");
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

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getJzcode() {
		return jzcode;
	}

	public void setJzcode(String jzcode) {
		this.jzcode = jzcode;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjtime() {
		return cjtime;
	}

	public void setCjtime(String cjtime) {
		this.cjtime = cjtime;
	}
	
}
