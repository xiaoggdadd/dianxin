package com.noki.YuFuFei;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class YuFuFeiManager {
	public boolean SaveYff(YuFuFeiBean bean) throws DbException{
		StringBuffer sql = new StringBuffer();
		StringBuffer sq = new StringBuffer();
		DataBase db = new DataBase();
		int a=0,c=0;
		int b=1;
	//	DataBase db1 = new DataBase();
		sql.append("INSERT INTO AMMETERDEGREES (AMMETERDEGREEID,AMMETERID_FK,STARTMONTHOLD,ENDMONTHOLD,LASTDEGREE,THISDEGREE,BLHDL)");
		sql.append("VALUES (AMMETERDEGREES_SEQ.nextval,(select dbid from dianbiao where dbbm='"+bean.getDbbm()+"'),'"+bean.getJfqsrq()+"',");
		sql.append("'"+bean.getJfzzrq()+"','"+bean.getQm()+"','"+bean.getZm()+"','"+bean.getDl()+"')");
		db.connectDb();
		System.out.println("电量添加"+sql);
		a=db.update(sql.toString());
		if(a==1){
			b=b++;
		}
		sq.append("INSERT INTO ELECTRICFEES (ELECTRICFEE_ID,AMMETERDEGREEID_FK,NOTETYPEID,ACTUALPAY,ACTUALPAY_D,UNITPRICE,BG) ");
		sq.append("VALUES (ELECTRICFEES_SEQ.nextval,(select AMMETERDEGREEID from AMMETERDEGREES where AMMETERID_FK=(select dbid from dianbiao where dbbm='"+bean.getDbbm()+"') and blhdl='"+bean.getDl()+"'),");
		sq.append("'"+bean.getPjlx()+"','"+bean.getYg()+"','"+bean.getFtje()+"','"+bean.getDj()+"','"+0+"')");
		db.connectDb();
		System.out.println("电费添加"+sq);
		c=db.update(sq.toString());
		db.commit();
		if(c==1){
			b=b++;
		}
		System.out.println(b);
		if(b==1){
		return true;
		}else{
			return false;
		}
	}
	public boolean deleteYff(String dlid,String dfid) throws DbException{
		StringBuffer sql = new StringBuffer();
		StringBuffer sq = new StringBuffer();
		DataBase db = new DataBase();
		int a=0,c=0;
		int b=1;
		sql.append("delete from AMMETERDEGREES where AMMETERDEGREEID='"+dlid+"'");
		System.out.println("电量删除"+sql);
		db.connectDb();
		a=db.update(sql.toString());
		if(a==1){
			b=b++;
		}
		sq.append("delete from electricfees where ELECTRICFEE_ID='"+dfid+"'");
		System.out.println("电费删除"+sq);
		c=db.update(sq.toString());
		if(c==1){
			b=b++;
		}
		db.commit();
		System.out.println(b);
		if(b==1){
			return true;
		}else{
			return false;
		}
	}
	public boolean UpdateYff(YuFuFeiBean bean) throws DbException{
		StringBuffer sql = new StringBuffer();
		StringBuffer sq = new StringBuffer();
		DataBase db = new DataBase();
		int a=0,c=0;
		int b=1;
	//	DataBase db1 = new DataBase();
		sql.append("UPDATE AMMETERDEGREES SET STARTMONTHOLD='"+bean.getJfqsrq()+"',ENDMONTHOLD='"+bean.getJfzzrq()+"',LASTDEGREE='"+bean.getQm()+"',");
		sql.append("THISDEGREE='"+bean.getZm()+"',BLHDL='"+bean.getDl()+"' where AMMETERDEGREEID='"+bean.getDlid()+"'");
		db.connectDb();
		System.out.println("电量修改"+sql);
		a=db.update(sql.toString());
		if(a==1){
			b=b++;
		}
		sq.append("UPDATE ELECTRICFEES SET NOTETYPEID='"+bean.getPjlx()+"',ACTUALPAY='"+bean.getYg()+"',ACTUALPAY_D='"+bean.getFtje()+"',");
		sq.append("UNITPRICE='"+bean.getDj()+"' where ELECTRICFEE_ID='"+bean.getDfid()+"'");
		db.connectDb();
		System.out.println("电费修改"+sq);
		c=db.update(sq.toString());
		db.commit();
		if(c==1){
			b=b++;
		}
		if(b==1){
		return true;
		}else{
			return false;
		}
	}
}
