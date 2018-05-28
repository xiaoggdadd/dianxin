package com.noki.mobi.cx;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;



/**
 * <p>Title: 供电方式</p>
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
public class GongDianFangShiTongJi {
	public  GongDianFangShiTongJi(){
		
	}
    public synchronized ArrayList getPageData(String whereStr) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select C.gdfs,C.zds,(C.zds / D.zs) * 100 as zdbl,C.zds1,(C.zds1 / D.zs) * 100 as zdbl1 from (select A.zds,B.zds1 ,A.gdfs  from ((select count(zd.id) as zds,i.name as gdfs from zhandian zd,indexs i where 1 = 1 "+whereStr+" and zd.gdfs=i.code group by  i.name) A  left join (select count(zd.id) as zds1,i.name as gdfs1 from zhandian zd,indexs i where 1 = 1 "+whereStr+" and zd.gdfs=i.code and zd.zdcb='1'group by  i.name) B on  A.gdfs=B.gdfs1))C,(select count(id) as zs from zhandian zd where 1 = 1 "+whereStr+")D");
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            System.out.println(s2.toString());///////////
            rs = db.queryAll(s2.toString());
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

}
