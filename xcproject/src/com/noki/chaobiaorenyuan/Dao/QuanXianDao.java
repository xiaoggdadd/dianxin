package com.noki.chaobiaorenyuan.Dao;

public class QuanXianDao {
	
	/**
	 * 根据角色权限分配可查看的抄表人，给出相应的sql语句
	 * 包括省级领导，市级领导，县区级领导分别对应不同的sql语句
	 */
	//获取ACCOUNT表有多少列，可以分为几页
	public String countQX(String sheng,String shi,String xian,String xiaoqu,String roleId){
		System.out.println(sheng);	//省
		System.out.println(shi);	//市
		System.out.println(xian);	//县
		System.out.println(xiaoqu);	//小区
		System.out.println(roleId);	//角色ID
		
		String sql = "";			//应执行的sql语句
		
		if(roleId.equals("445")){	//抄表人员
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
			
		}else if(roleId.equals("380")){	//区县管理
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"'";
			
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//市级领导
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'";
			
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//省级领导
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"'";
			
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//超级管理员
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1";
			
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//其他区县角色
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
			
		}else if(roleId.equals("366")){	//业务岗
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
		}
		return sql;
	}
	//获取ACCOUNT表有多少列，可以分为几页 （附带模糊查询）
	public String countNameQX(String sheng,String shi,String xian,String xiaoqu,String roleId,String name){
		System.out.println(sheng);	//省
		System.out.println(shi);	//市
		System.out.println(xian);	//县
		System.out.println(xiaoqu);	//小区
		System.out.println(roleId);	//角色ID
		System.out.println(name);	//模糊查询需要给定的参数
		String sql = "";			//应执行的sql语句
		
		if(roleId.equals("445")){	//抄表人员
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("380")){	//区县管理
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//市级领导
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//省级领导
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//超级管理员
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1 AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//其他区县角色
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("366")){	//业务岗
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
		}
		return sql;
	}
	//根据角色进行分页查询,选择相应的sql语句
	public String FenYeQX(int curpage,int x,int y,String name,String sheng,String shi,String xian,String xiaoqu,String roleId){
		System.out.println(x);		//第n页结束数据条数
		System.out.println(y);		//第n页开始数据条数
		System.out.println(curpage);//第几页
		System.out.println(sheng);	//省
		System.out.println(shi);	//市
		System.out.println(xian);	//县
		System.out.println(xiaoqu);	//小区
		System.out.println(roleId);	//角色ID
		System.out.println(name);	//模糊查询需要给定的参数
		
		String sql = "";			//应执行的sql语句
		
		if(roleId.equals("445")){	//抄表人员
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("380")){	//区县管理
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//市级领导
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"' AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//省级领导
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+" AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//超级管理员
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where ROWNUM <= "+x+" AND roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//其他区县角色
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '"+roleId+"' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("366")){	//业务岗
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '"+roleId+"' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}
		return sql;
	}
}
