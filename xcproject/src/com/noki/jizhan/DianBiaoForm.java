package com.noki.jizhan;

import java.sql.*;

import com.noki.database.*;

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
public class DianBiaoForm {
    private String dbid;
    private String sszy;
    private String dbyt;
    private String dllx;
    private String ydsb;
    private String csds;
    private String cssytime;
    private String beilv;
    private String dbxh;
    private String memo;
    private int id;
    private String netpoint_name;
    private String netpoint_id;
    private String yhdl;
    private String dfzflx;
    private String dbname;
    private String linelosstype;
    private String linelossvalue;
    private String dbqyzt;
    private String zbdyhh;//自报电用户号
    private String ydbid;//原电表id
    private String jzname;
    private String dianfei;
    private String danjia;//电表单价
    private String bsdl;//变损电量
    private String zgdl;//暂估单价
    private String zzsl;// 增值税率
    private String countbzw;//是否多块表标志，记录该站点可以同时含有多块启用的结算电表（0---1块表；1---多块表）
    
    public String getCountbzw() {
		return countbzw;
	}

	public void setCountbzw(String countbzw) {
		this.countbzw = countbzw;
	}

	public String getZgdl() {
		return zgdl;
	}

	public void setZgdl(String zgdl) {
		this.zgdl = zgdl;
	}

	public String getZzsl() {
		return zzsl;
	}

	public void setZzsl(String zzsl) {
		this.zzsl = zzsl;
	}

	public String getBsdl() {
		return bsdl;
	}

	public void setBsdl(String bsdl) {
		this.bsdl = bsdl;
	}

	public String getDanjia() {
		return danjia;
	}

	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}

	public String getJzname() {
		return jzname;
	}

	public void setJzname(String jzname) {
		this.jzname = jzname;
	}

	public String getDianfei() {
		return dianfei;
	}

	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
	}

	public String getLinelosstype() {
		return linelosstype;
	}

	public void setLinelosstype(String linelosstype) {
		this.linelosstype = linelosstype;
	}

	
	public String getZbdyhh() {
		return zbdyhh;
	}

	public void setZbdyhh(String zbdyhh) {
		this.zbdyhh = zbdyhh;
	}

	public String getYdbid() {
		return ydbid;
	}

	public void setYdbid(String ydbid) {
		this.ydbid = ydbid;
	}

	public String getDbqyzt() {
		return dbqyzt;
	}

	public void setDbqyzt(String dbqyzt) {
		this.dbqyzt = dbqyzt;
	}

	public String getLinelossvalue() {
		return linelossvalue;
	}

	public void setLinelossvalue(String linelossvalue) {
		this.linelossvalue = linelossvalue;
	}

	
    public DianBiaoForm() {
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public void setSszy(String sszy) {
        this.sszy = sszy;
    }

    public void setDbyt(String dbyt) {
        this.dbyt = dbyt;
    }

    public void setDllx(String dllx) {
        this.dllx = dllx;
    }

    public void setYdsb(String ydsb) {
        this.ydsb = ydsb;
    }

    public void setCsds(String csds) {
        this.csds = csds;
    }

    public void setCssytime(String cssytime) {
        this.cssytime = cssytime;
    }

    public void setBeilv(String beilv) {
        this.beilv = beilv;
    }

    public void setDbxh(String dbxh) {
        this.dbxh = dbxh;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNetpoint_name(String netpoint_name) {
        this.netpoint_name = netpoint_name;
    }

    public void setNetpoint_id(String netpoint_id) {
        this.netpoint_id = netpoint_id;
    }

    public String getDbid() {
        return dbid;
    }

    public String getSszy() {
        return sszy;
    }

    public String getDbyt() {
        return dbyt;
    }

    public String getDllx() {
        return dllx;
    }

    public String getYdsb() {
        return ydsb;
    }

    public String getCsds() {
        return csds;
    }

    public String getCssytime() {
        return cssytime;
    }

    public String getBeilv() {
        return beilv;
    }

    public String getDbxh() {
        return dbxh;
    }

    public String getMemo() {
        return memo;
    }

    public int getId() {
        return id;
    }

    public String getNetpoint_name() {
        return netpoint_name;
    }

    public String getNetpoint_id() {
        return netpoint_id;
    }

    public synchronized DianBiaoForm getInfoData(String id) {

        String sql = "SELECT D.DBID, D.DLLX, D.YDSB, D.SSZY, D.DBYT, D.CSDS,to_char(D.CSSYTIME,'yyyy-mm-dd'), D.BEILV, D.DBXH, D.MEMO," 
                   + " D.NETPOINT_NAME, D.NETPOINT_ID, D.DBNAME, D.YHDL,(select name from indexs where code=D.dfzflx and type='dfzflx') as DFZFLX," 
                   + " D.LINELOSSTYPE, D.LINELOSSVALUE, D.DBQYZT, D.DBZBDYHH, D.YDBID, D.DANJIA, D.CHANGEVALUE, D.ZGDJ, D.ZZSL, Z.COUNTBZW" 
                   + " FROM DIANBIAO D,ZHANDIAN Z WHERE Z.ID = D.ZDID AND D.id=" + id;
        System.out.println("修改电表信息页面带出的信息："+sql);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql);
            if (rs.next()) {
                this.setDbid(rs.getString(1));
                this.setDllx(rs.getString(2));
                this.setYdsb(rs.getString(3));
                this.setSszy(rs.getString(4));
                this.setDbyt(rs.getString(5));
                this.setCsds(rs.getString(6)!=null?rs.getString(6):"");
                this.setCssytime(rs.getString(7)!=null?rs.getString(7):"");
                this.setBeilv(rs.getString(8)!=null?rs.getString(8):"");
                this.setDbxh(rs.getString(9)!=null?rs.getString(9):"");
                this.setMemo(rs.getString(10)!=null?rs.getString(10):"");
                this.setNetpoint_name(rs.getString(11)!=null?rs.getString(11):"");
                this.setNetpoint_id(rs.getString(12)!=null?rs.getString(12):"");
                this.setDbname(rs.getString(13)!=null?rs.getString(13):"");
                this.setYhdl(rs.getString(14)!=null?rs.getString(14):"");
                this.setDfzflx(rs.getString(15)!=null?rs.getString(15):"");
                this.setLinelosstype(rs.getString(16)!=null?rs.getString(16):"0");
                this.setLinelossvalue(rs.getString(17)!=null?rs.getString(17):"");
                this.setDbqyzt(rs.getString(18)!=null?rs.getString(18):"");
                this.setZbdyhh(rs.getString(19)!=null?rs.getString(19):"");
                this.setYdbid(rs.getString(20)!=null?rs.getString(20):"");
                this.setDanjia(rs.getString(21)!=null?rs.getString(21):"");
                
                this.setBsdl(rs.getString(22)!=null?rs.getString(22):"");
                this.setZgdl(rs.getString(23)!=null?rs.getString(23):"");
                this.setZzsl(String.valueOf(rs.getDouble(24)) != null ? String.valueOf(rs.getDouble(24)):"");
                this.setCountbzw(rs.getString(25)!=null?rs.getString(25):"");
                
                this.setId(Integer.parseInt(id));
                

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

	public void setYhdl(String yhdl) {
		this.yhdl = yhdl;
	}

	public String getYhdl() {
		return yhdl;
	}

	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}

	public String getDfzflx() {
		return dfzflx;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbname() {
		return dbname;
	}
	
	private double dbqyzs;//电表启用总数
	private double dbgbzs;//电表关闭总数
	
	 public double getDbqyzs() {
		return dbqyzs;
	}

	public void setDbqyzs(double dbqyzs) {
		this.dbqyzs = dbqyzs;
	}

	public double getDbgbzs() {
		return dbgbzs;
	}

	public void setDbgbzs(double dbgbzs) {
		this.dbgbzs = dbgbzs;
	}

	public synchronized DianBiaoForm getQyztCount(String id, String zdid) {

	        String sql = "select count( DECODE(db.dbqyzt,1,1)) as qydb,count( DECODE(db.dbqyzt,0,1)) as gbdb " 
	        		   + "from zhandian zd,dianbiao db "
	        		   + "where zd.id=db.zdid and db.dbyt='dbyt01' and zd.id=" + zdid +" and db.dbid<>'"+id+"'";
	                
	        System.out.println("当前站点下的非当前电表的启用电表数和关闭电表数："+sql);
	        DataBase db = new DataBase();
	        ResultSet rs = null;

	        try {
	            db.connectDb();
	            rs = db.queryAll(sql);
	            if (rs.next()) {
	            	this.setDbqyzs(rs.getDouble("qydb"));
	            	this.setDbgbzs(rs.getDouble("gbdb"));
	            }
	            db.close();
	        }catch (DbException de) {
	            de.printStackTrace();
	        } catch (SQLException de) {
	            de.printStackTrace();
	        }finally {
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
