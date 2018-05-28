package com.noki.page;

public class NPageBean {
	/**
	 * ls
	 * @param start
	 * @param subquery
	 * @return
	 */
	public synchronized String getQueryStr(int aa,int start,String subquery) {
		int end = start*aa+1;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		
		query.append(subquery);
		query.append(" ) A WHERE ROWNUM < "+end+")WHERE RN >= "+((start-1)*aa+1));
		System.out.println("11111111111111111111:"+query.toString()+"--------------------------");
		return query.toString();
	}
	public synchronized String getQueryStr(int start,String subquery) {
		int end = start*10+1;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		
		query.append(subquery);
		query.append(" ) A WHERE ROWNUM < "+end+")WHERE RN >= "+((start-1)*10+1));
		System.out.println("11111111111111111111:"+query.toString()+"--------------------------");
		return query.toString();
	}
	public synchronized String getQuery(int start,String subquery,String pm) {
		int end = start*15+1;
		System.out.println("**"+pm);
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		
		query.append(subquery);
		if(pm==""){
			query.append(" ) A WHERE ROWNUM < "+end+")WHERE RN >= "+((start-1)*15+1));
		}else{
			query.append(" ) A WHERE ROWNUM <= "+pm+")WHERE RN >= 1");
		}
		
		return query.toString();
	}
	public synchronized String getQueryStrdf(int start,String subquery) {
		int end = start*17+1;
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		
		query.append(subquery);
		query.append(" ) A WHERE ROWNUM < "+end+")WHERE RN >= "+((start-1)*17+1));
		System.out.println("11111111111111111111:"+query.toString()+"--------------------------");
		return query.toString();
	}
	public synchronized String getQuerydf(int start,String subquery,String pm) {
		int end = start*17+1;
		System.out.println("**"+pm);
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		
		query.append(subquery);
		if(pm==""){
			query.append(" ) A WHERE ROWNUM < "+end+")WHERE RN >= "+((start-1)*17+1));
		}else{
			query.append(" ) A WHERE ROWNUM <= "+pm+")WHERE RN >= 1");
		}
		
		return query.toString();
	}
	
}
