package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class ZddfInfoForm {
    private String zdid;
    private String id;
    private String ydfs;
    private String gdfs;
    private String fkzq;
    private String dfdj;
    private String zflx;
    private String fkfs;
    private String skdwmc;
    private String skdwzh;
    private String skdwyh;
    private String zbdyhh;
    private String scjftime;
    private String memo;
 //新加agreementid,signdate, origindate,stopdate,powerunit,unitlinkman,unitphone,watchcost
    private String agreementid;
    private String signdate;
    private String origindate;
    private String stopdate;
    private String powerunit;
    private String unitlinkman;
    private String unitphone;
    private String watchcost;
    
    
	
    
    
    public String getAgreementid() {
		return agreementid;
	}

	public void setAgreementid(String agreementid) {
		this.agreementid = agreementid;
	}

	public String getSigndate() {
		return signdate;
	}

	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}

	public String getOrigindate() {
		return origindate;
	}

	public void setOrigindate(String origindate) {
		this.origindate = origindate;
	}

	public String getStopdate() {
		return stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	public String getPowerunit() {
		return powerunit;
	}

	public void setPowerunit(String powerunit) {
		this.powerunit = powerunit;
	}

	public String getUnitlinkman() {
		return unitlinkman;
	}

	public void setUnitlinkman(String unitlinkman) {
		this.unitlinkman = unitlinkman;
	}

	public String getUnitphone() {
		return unitphone;
	}

	public void setUnitphone(String unitphone) {
		this.unitphone = unitphone;
	}

	public String getWatchcost() {
		return watchcost;
	}

	public void setWatchcost(String watchcost) {
		this.watchcost = watchcost;
	}
	//
	
	

	public ZddfInfoForm() {
    }

    public void setZdid(String zdid) {
        this.zdid = zdid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYdfs(String ydfs) {
        this.ydfs = ydfs;
    }

    public void setGdfs(String gdfs) {
        this.gdfs = gdfs;
    }

    public void setFkzq(String fkzq) {
        this.fkzq = fkzq;
    }

    public void setDfdj(String dfdj) {
        this.dfdj = dfdj;
    }

    public void setZflx(String zflx) {
        this.zflx = zflx;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }

    public void setSkdwmc(String skdwmc) {
        this.skdwmc = skdwmc;
    }

    public void setSkdwzh(String skdwzh) {
        this.skdwzh = skdwzh;
    }

    public void setSkdwyh(String skdwyh) {
        this.skdwyh = skdwyh;
    }

    public void setZbdyhh(String zbdyhh) {
        this.zbdyhh = zbdyhh;
    }

    public void setScjftime(String scjftime) {
        this.scjftime = scjftime;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getZdid() {
        return zdid;
    }

    public String getId() {
        return id;
    }

    public String getYdfs() {
        return ydfs;
    }

    public String getGdfs() {
        return gdfs;
    }

    public String getFkzq() {
        return fkzq;
    }

    public String getDfdj() {
        return dfdj;
    }

    public String getZflx() {
        return zflx;
    }

    public String getFkfs() {
        return fkfs;
    }

    public String getSkdwmc() {
        return skdwmc;
    }

    public String getSkdwzh() {
        return skdwzh;
    }

    public String getSkdwyh() {
        return skdwyh;
    }

    public String getZbdyhh() {
        return zbdyhh;
    }

    public String getScjftime() {
        return scjftime;
    }

    public String getMemo() {
        return memo;
    }
    
    //站点管理 修改站点电费表信息带出信息
    public synchronized ZddfInfoForm getJizhan(String id) {
    	//新加t.agreementid,t.signdate,t.origindate,t.stopdate,t.powerunit,t.unitlinkman,t.unitphone,t.watchcost
        StringBuffer sql = new StringBuffer();
        sql.append("select z.dianfei,z.gdfs,t.id infoid,t.fkzq,t.fkfs,t.skdwmc,t.skdwzh,t.skdwyh,t.zbdyhh,t.scjftime,t.memo,t.agreementid,t.signdate,t.origindate,t.stopdate,t.powerunit,t.unitlinkman,t.unitphone,t.watchcost");
        sql.append(
                " from zhandian z left join zddfinfo t on z.id=t.zdid where z.qyzt='1' and z.id=" +
                id);
        System.out.println("站点管理 修改站点电费表信息带出信息:"+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            if (rs.next()) {
                this.setDfdj(rs.getString(1)!=null?rs.getString(1):"");
                this.setGdfs(rs.getString(2)!=null?rs.getString(2):"0");
                this.setId(rs.getString(3)!=null?rs.getString(3):"0");
               // this.setYdfs(rs.getString(4)!=null?rs.getString(4):"0");
                this.setFkzq(rs.getString(4)!=null?rs.getString(4):"0");
                //this.setZflx(rs.getString(6)!=null?rs.getString(6):"0");
                this.setFkfs(rs.getString(5)!=null?rs.getString(5):"0");
                this.setSkdwmc(rs.getString(6)!=null?rs.getString(6):"");
                this.setSkdwzh(rs.getString(7)!=null?rs.getString(7):"");
                this.setSkdwyh(rs.getString(8)!=null?rs.getString(8):"");
                this.setZbdyhh(rs.getString(9)!=null?rs.getString(9):"");
                this.setScjftime(rs.getString(10)!=null?rs.getString(10):"");
                this.setMemo(rs.getString(11)!=null?rs.getString(11):"");
                this.setZdid(id);
              //新加t.agreementid,t.signdate,t.origindate,t.stopdate,t.powerunit,t.unitlinkman,t.unitphone,t.watchcost
                this.setAgreementid(rs.getString(12)!=null?rs.getString(12):"");
                this.setSigndate(rs.getString(13)!=null?rs.getString(13):"");
                this.setOrigindate(rs.getString(14)!=null?rs.getString(14):"");
                this.setStopdate(rs.getString(15)!=null?rs.getString(15):"");
                this.setPowerunit(rs.getString(16)!=null?rs.getString(16):"");
                this.setUnitlinkman(rs.getString(17)!=null?rs.getString(17):"");
                this.setUnitphone(rs.getString(18)!=null?rs.getString(18):"");
                this.setWatchcost(rs.getString(19)!=null?rs.getString(19):"");
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
