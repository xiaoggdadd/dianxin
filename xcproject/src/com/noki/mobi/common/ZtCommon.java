package com.noki.mobi.common;

import com.noki.common.CodeItem;
import com.noki.common.OrgAndRole;
import com.noki.common.RequestMessageCode;
import com.noki.common.ResponseMessageCode;
import com.noki.common.util.BZServiceImpl;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class ZtCommon {
    public ZtCommon() {
    }
//启用状态
    public synchronized ArrayList getSelctQyzt(String lxdm) {
        ArrayList list = new ArrayList();
        lxdm = lxdm.toLowerCase();
        // if(null==lxdm){lxdm="";}
        StringBuffer sql = new StringBuffer();
        sql.append("select code,name from indexs where type='" + lxdm +
                   "' or type='" + lxdm.toUpperCase() + "' order by code");
        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(">>>>>"+sql);
        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }
        catch (DbException de) {
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

        return list;
    }
    public synchronized ArrayList getSelctOptions(String lxdm) {
        ArrayList list = new ArrayList();
        lxdm = lxdm.toLowerCase();
        // if(null==lxdm){lxdm="";}
        StringBuffer sql = new StringBuffer();
        sql.append("select code,name from indexs where type='" + lxdm +
                   "' or type='" + lxdm.toUpperCase() + "' order by code");
        DataBase db = new DataBase();
        ResultSet rs = null;

        System.out.println(">>>>>"+sql);
        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    
    public synchronized List<OrgAndRole> getGangwei(String accountName) {
    	 BZServiceImpl bzServiceImpl = new BZServiceImpl();
    	// String jiekouUrl = "http://137.0.30.163:9898/mssproxy";//测试环境
    	 String jiekouUrl = "http://137.0.28.162:8090/mssproxy";//正式环境
    	 List<OrgAndRole> listoar = new ArrayList<OrgAndRole>();
    	 ArrayList list = new ArrayList();
        //获取组织编码
        RequestMessageCode requestMessage2 = new RequestMessageCode();
        List<CodeItem> items = new ArrayList<CodeItem>();
        CodeItem codeItem = new CodeItem();
        codeItem.setAccount(accountName);
        items.add(codeItem);
        requestMessage2.setItems(items);
        ResponseMessageCode responseMessage2 = new ResponseMessageCode();
        try
        {
            responseMessage2 = bzServiceImpl.GetOrgCode(jiekouUrl,requestMessage2);
            if(responseMessage2.getErrorMsg().equals("")){
            	listoar = responseMessage2.getItems().get(0).getOrgAndRoles();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            listoar = null;
        }
       

        return listoar;
    }
    //站点属性  所对应的站点类型
    public synchronized ArrayList getZdlx(String lxdm) {
        ArrayList list = new ArrayList();
       //  lxdm = lxdm.toLowerCase();
        StringBuffer sql = new StringBuffer();
        sql.append("select code,name from zdsx where fjcode='"+lxdm+"' ");

        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    //站点属性  所对应的站点类型
    public synchronized ArrayList getZdlx2(String lxdm) {
        ArrayList list = new ArrayList();
       //  lxdm = lxdm.toLowerCase();
        StringBuffer sql = new StringBuffer();
        sql.append("select code,name from zdsx where fjcode in ("+lxdm+") ");

        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    //获取标杆类型标号下拉框里的数据
    public synchronized ArrayList getSigntypenum() {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.append("select id,name from BENCHMARKING ");
        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    public synchronized ArrayList getSelctXslx(String xslx) {
        ArrayList list = new ArrayList();
        xslx = xslx.toLowerCase();
        StringBuffer sql = new StringBuffer();
        sql.append("select code,name from indexs where type='" + xslx +
                   "' or type='" + xslx.toUpperCase() + "' order by code");

        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }

    public synchronized String getFuzeQuYu(String loginName) {
        StringBuffer list = new StringBuffer();
        list.append("'0'");
        StringBuffer sql = new StringBuffer();
        sql.append("select agcode from per_area where accountname='" +
                   loginName + "'");

        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            while (rs.next()) {
                list.append(",'" + rs.getString(1) + "'");
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

        return list.toString();
    }

    public synchronized ArrayList getShi(String shengId) {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select agcode,agname from d_area_grade where agrade='2' and fagcode='" +
                shengId + "'");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
  
    public synchronized ArrayList getSheng() {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.append("select agcode,agname from d_area_grade where agrade=1");

       
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }

    public synchronized String getLastAgcode(String loginName) {

        StringBuffer sql = new StringBuffer();
        sql.append(
                "select xiaoqu,xian,shi,sheng from account where accountname='" +
                loginName + "'");

        
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            String xiaoqu = "", xian = "", shi = "", sheng = "";
            if (rs.next()) {
                xiaoqu = rs.getString(1);
                xian = rs.getString(2);
                shi = rs.getString(3);
                sheng = rs.getString(4);
                if (xiaoqu != null && !xiaoqu.equals("0") &&
                    !xiaoqu.equals("null")) {
                    return xiaoqu;
                } else if (xian != null && !xian.equals("0") &&
                           !xian.equals("null")) {
                    return xian;
                } else if (shi != null && !shi.equals("0") &&
                           !shi.equals("null")) {
                    return shi;
                } else {
                    return sheng;
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

        return "0";
    }

    public synchronized ArrayList getAgcode(String ssagcode, int codelength) {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        int cl = ssagcode.length();
        if (codelength >= cl) {
            sql.append(
                    "select agcode,agname from d_area_grade where length(agcode)=" +
                    codelength);
            sql.append(" and agcode like '" + ssagcode + "%'");
        } else {
            sql.append("select agcode,agname from d_area_grade where agcode='" +
                       ssagcode.substring(0, codelength) + "'");
        }

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }

    public synchronized String getAgcode_(String ssagcode,String pid, int codelength) {

        String cxs= "";
        if(ssagcode.length()>pid.length()){
            cxs=ssagcode;
        }else{
            cxs= pid;
        }
        StringBuffer list = new StringBuffer();
        list.append("<response>");
        StringBuffer sql = new StringBuffer();
        int cl = ssagcode.length();
        if (codelength >= cl) {
            sql.append(
                    "select agcode,agname from d_area_grade where length(agcode)=" +
                    codelength);
            sql.append(" and agcode like '" + cxs + "%'");
        } else {
            sql.append("select agcode,agname from d_area_grade where agcode='" +
                       cxs.substring(0, codelength) + "'");
        }
       
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            while (rs.next()) {
                list.append("<res>" + rs.getString(1) + "</res>");
                list.append("<res>" + rs.getString(2) + "</res>");
            }
            list.append("</response>");

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

        return list.toString();
    }
    //基站小类型 llx
    public synchronized ArrayList getjzxlx(String jzxlx) {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.append("select t.code,t.name from INDEXS t where type='StationType' ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
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

        return list;
    }
    //分摊专业 llx
    public synchronized String getsszy(String jzxlx) {
    	String sszy="";
       
        StringBuffer sql = new StringBuffer();
        sql.append("select t.code from INDEXS t where t.type='SSZY' and t.name='"+jzxlx+"' ");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(sql.toString());
           while(rs.next()){
        	   sszy=rs.getString(1);
           }
        }

        catch (Exception de) {
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

        return sszy;
    }



}
