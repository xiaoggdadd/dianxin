package com.noki.djguanli;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class danjiawhmanage {
	private int allPage;
	
	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public synchronized ArrayList getDJ(int start,String loginName,String loginId,String whereStr) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		try {
			  sql.append("SELECT Z.JZNAME,B.ZDNAME,CASE B.DBYT WHEN 'DBYT01' THEN '����' WHEN 'DBYT02' THEN '�ɼ�' ELSE '����' END DBYT, ");
			  sql.append("CASE B.DFZFLX WHEN 'DFZFLX01' THEN '�½�' WHEN 'DFZFLX02' THEN '��ͬ' WHEN 'DFZFLX03' THEN 'Ԥ֧' WHEN 'DFZFLX04' THEN '�忨' END DFZFLX,DANJIA");
			  sql.append(" FROM ZHANDIAN Z,DIANBIAO B WHERE Z.ID=B.ZDID "+whereStr);
			  DataBase db = new DataBase();
			  ResultSet rs = null;
			  s2.append("select count(*)  from (" + sql + ")");
			  
              System.out.println("����"+sql);
              System.out.println("��ҳ"+s2);
              ResultSet rs3 = null;
              rs3=db.queryAll(s2.toString());
              if(rs3.next()){
              	this.setAllPage((rs3.getInt(1)+9)/10);
              }
              NPageBean nbean = new NPageBean();
              rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
              Query query = new Query();
              list = query.query(rs);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
