package com.noki.query.basequery.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import java.util.Date;
import com.noki.util.MD5;

public class CityHouseBean {

   

    public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
    	System.out.println("CityHouseBean-getPageData:"+whereStr);
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
    	try {
			 fzzdstr = getFuzeZdid(db,loginId);
        s2.append("select z.id,z.jzname");
        s2.append(",(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)");
        s2.append("||(case when z.xian is not null then (select agname from d_area_grade where agcode=z.xian) else '' end) as szdq,");
        s2.append("(select name from indexs where code = z.property and type = 'ZDSX') property,(select name from indexs where code = z.jztype and type = 'ZDLX')  jztype,(select name from indexs where code=z.yflx and type = 'FWLX') yflx,(select name from indexs where code=z.gdfs and type = 'GDFS') gdfs");
        s2.append(",z.jnglmk,z.area,z.dianfei,z.zdcode from zhandian z where 1=1 "+whereStr+ "and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
        s2.append(" order by z.sheng,z.shi,z.xian,z.jzname");


    } catch (DbException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from zhandian z,indexs t where z.jztype=t.code "+whereStr+ "and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
           
            rs = db.queryAll(strall.toString());
            System.out.println("strall.toString():"+strall.toString());
  	      if(rs.next()){
  	    	this.setAllPage((rs.getInt(1)+14)/15);
  	      }
  	      NPageBean nbean=new NPageBean();
  	      rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
  	      Query query=new Query();
  	      list = query.query(rs);
            
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

        return list;
    }
    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
  //负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			
		}	
    System.out.println("负责站点条件："+cxtj);
		return cxtj.toString();
	}
}
