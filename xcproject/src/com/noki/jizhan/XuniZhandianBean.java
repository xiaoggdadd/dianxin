package com.noki.jizhan;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import com.noki.util.Query;
import com.noki.page.NPageBean;
import com.noki.util.CTime;

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
public class XuniZhandianBean {
    public XuniZhandianBean() {
    }

    public synchronized ArrayList getXnzdDianBiao(int zdid) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
        s2.append("select d.id,d.dbid,z.jzname,");
        s2.append("(select t.name from indexs t where t.code=d.sszy ) as sszy");
        s2.append(",(select t.name from indexs t where t.code=d.dbyt ) as dbyt");
        s2.append(",d.csds,d.cssytime,d.beilv,d.dbxh,d.memo");
        s2.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
        s2.append("||(case when z.xian is not null then (select agname from d_area_grade where agcode=z.xian) else '' end) as szdq");
        s2.append(" from dianbiao d,zhandian z where d.zdid=z.id ");
        s2.append(" and d.zdid=" + zdid);
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        } finally {
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

    public synchronized ArrayList getXnzdShebei(int zdid) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
        s2.append(
                "select dianbiaoid,sheiebanid,mingcheng,guige,bili,sccj,zcbh,bccd");
        s2.append(",(select p.itemdescription from permission_configuration p where p.itemname=s.shuoshuzhuanye) as sszy");
        s2.append(" from sbgl s where s.dianbiaoid");
        s2.append(" in(select dbid from dianbiao where zdid=" + zdid + ")");
        System.out.println(s2.toString());
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        } finally {
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


}
