package com.noki.chaobiaorenyuan.Dao;

public class QuanXianDao {
	
	/**
	 * ���ݽ�ɫȨ�޷���ɲ鿴�ĳ����ˣ�������Ӧ��sql���
	 * ����ʡ���쵼���м��쵼���������쵼�ֱ��Ӧ��ͬ��sql���
	 */
	//��ȡACCOUNT���ж����У����Է�Ϊ��ҳ
	public String countQX(String sheng,String shi,String xian,String xiaoqu,String roleId){
		System.out.println(sheng);	//ʡ
		System.out.println(shi);	//��
		System.out.println(xian);	//��
		System.out.println(xiaoqu);	//С��
		System.out.println(roleId);	//��ɫID
		
		String sql = "";			//Ӧִ�е�sql���
		
		if(roleId.equals("445")){	//������Ա
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
			
		}else if(roleId.equals("380")){	//���ع���
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"'";
			
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//�м��쵼
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'";
			
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//ʡ���쵼
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"'";
			
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//��������Ա
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1";
			
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//�������ؽ�ɫ
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
			
		}else if(roleId.equals("366")){	//ҵ���
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"'";
		}
		return sql;
	}
	//��ȡACCOUNT���ж����У����Է�Ϊ��ҳ ������ģ����ѯ��
	public String countNameQX(String sheng,String shi,String xian,String xiaoqu,String roleId,String name){
		System.out.println(sheng);	//ʡ
		System.out.println(shi);	//��
		System.out.println(xian);	//��
		System.out.println(xiaoqu);	//С��
		System.out.println(roleId);	//��ɫID
		System.out.println(name);	//ģ����ѯ��Ҫ�����Ĳ���
		String sql = "";			//Ӧִ�е�sql���
		
		if(roleId.equals("445")){	//������Ա
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("380")){	//���ع���
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//�м��쵼
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//ʡ���쵼
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//��������Ա
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '445'  AND  DELSIGN= 1 AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//�������ؽ�ɫ
			
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
			
		}else if(roleId.equals("366")){	//ҵ���
			sql = "SELECT  COUNT(0)  FROM ACCOUNT  WHERE  ROLEID = '"+roleId+"'  AND  DELSIGN= 1  AND SHENG='"+sheng+"' AND  SHI='"+shi+"'  AND XIAN='"+xian+"' AND  XIAOQU='"+xiaoqu+"' AND  ACCOUNTNAME  like '%"+name+"%'";
		}
		return sql;
	}
	//���ݽ�ɫ���з�ҳ��ѯ,ѡ����Ӧ��sql���
	public String FenYeQX(int curpage,int x,int y,String name,String sheng,String shi,String xian,String xiaoqu,String roleId){
		System.out.println(x);		//��nҳ������������
		System.out.println(y);		//��nҳ��ʼ��������
		System.out.println(curpage);//�ڼ�ҳ
		System.out.println(sheng);	//ʡ
		System.out.println(shi);	//��
		System.out.println(xian);	//��
		System.out.println(xiaoqu);	//С��
		System.out.println(roleId);	//��ɫID
		System.out.println(name);	//ģ����ѯ��Ҫ�����Ĳ���
		
		String sql = "";			//Ӧִ�е�sql���
		
		if(roleId.equals("445")){	//������Ա
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("380")){	//���ع���
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("367") || roleId.equals("368")||roleId.equals("369")||roleId.equals("370")||roleId.equals("371")||roleId.equals("372")||roleId.equals("373")||roleId.equals("500")||roleId.equals("501")||roleId.equals("502")||roleId.equals("384")){//�м��쵼
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"' AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("374") || roleId.equals("375") || roleId.equals("376") || roleId.equals("378")){	//ʡ���쵼
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+" AND   ROWNUM <= "+x+" and roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("365") ||roleId.equals("1") ||roleId.equals("400")){	//��������Ա
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where ROWNUM <= "+x+" AND roleid = '445' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("379") || roleId.equals("381") ||roleId.equals("465")){//�������ؽ�ɫ
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '"+roleId+"' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}else if(roleId.equals("366")){	//ҵ���
			
			 sql = "SELECT  *  FROM (SELECT ROWNUM rm,accountid,accountname,name,password,roleid,rolename,delsign FROM ACCOUNT where  SHENG="+sheng+"  AND   SHI='"+shi+"'  AND XIAN='"+xian+"' AND XIAOQU='"+xiaoqu+"'  AND   ROWNUM <= "+x+" and roleid = '"+roleId+"' and delsign = 1 and name like '%"+name+"%') WHERE rm >= "+y+"";
			 
		}
		return sql;
	}
}
