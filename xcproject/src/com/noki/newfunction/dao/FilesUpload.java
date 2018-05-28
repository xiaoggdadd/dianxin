package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.noki.newfunction.javabean.Zdinfo;

public class FilesUpload {
	private DataBase db;
	private Account account;
	
	
	public FilesUpload(Account account){
		db = new DataBase();
		this.account=account;
		
	}
	/**
	 * �ļ��Ƿ��ϴ���
	 * @return �ϴ�������false����Ϊtrue
	 */
	public Boolean fileIsUploaded(){
		String sql = "";

		System.out.println(sql+"--------------");
		try {
			ResultSet rs = db.queryAll(sql);
			if(rs.next())return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * ����ǰ�ļ��������ݿ�
	 * @param fileItem
	 * @throws Exception 
	 */
	public String insertToDB(String path,String name,List<Zdinfo> ls,String sjyq,String acname,int j,String entrytimeQS){
		String msg="�ɵ�ʧ�ܣ�";
		int i=0;
		String lch="";
		//System.out.println("filename"+filename);
		//int ii=filename.indexOf(".");
		//filename=filename.substring(0,ii);
		String s="SYSDATE";
		if(!path.equals("")){
			path=path+"\\"+name;
		}
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//�ϴ�����
		String fmt = "";
		fmt = "yyyyMMdd";
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		String ss=df.format(uploadDate);
	
		StringBuffer sql= new StringBuffer();
		int ts=0;//�жϲ��������
		if(ls!=null){
			for(Zdinfo lst:ls){
				ts++;
				String shiname1="";
				String shiname=lst.getShicode();
				if(shiname.equals("������")){shiname1="JN";}
				if(shiname.equals("�ൺ��")){shiname1="qd";}
				if(shiname.equals("�Ͳ���")){shiname1="zb";}
				if(shiname.equals("������")){shiname1="wh";}
				if(shiname.equals("��̨��")){shiname1="yt";}
				if(shiname.equals("������")){shiname1="rz";}
				if(shiname.equals("��ׯ��")){shiname1="zz";}
				if(shiname.equals("������")){shiname1="ly";}
				if(shiname.equals("̩����")){shiname1="ta";}
				if(shiname.equals("������")){shiname1="bz";}
				if(shiname.equals("������")){shiname1="hz";}
				if(shiname.equals("������")){shiname1="jn";}
				if(shiname.equals("�ĳ���")){shiname1="lc";}
				if(shiname.equals("Ϋ����")){shiname1="wf";}
				if(shiname.equals("��Ӫ��")){shiname1="dy";}
				if(shiname.equals("������")){shiname1="lw";}
				if(shiname.equals("������")){shiname1="dz";}

				String jztypename=lst.getJztype();
				if("����֧��".equals(jztypename)){jztypename="xz";}
				if("������".equals(jztypename)){jztypename="jr";}
				if("����".equals(jztypename)){jztypename="qt";}
				if("Ӫҵ����".equals(jztypename)){jztypename="yy";}
				if("��վ".equals(jztypename)){jztypename="jz";}
				if("���û���".equals(jztypename)){jztypename="jy";}
				String sj="";
				//System.out.println("shiname��"+shiname);
				//System.out.println("shiname1��"+shiname1);
				ResultSet rs=null;
				String sddf="";
				sddf=lst.getId()+lst.getZdid()+"ID"+name;
				//System.out.println("name:"+name);
			//System.out.println("sj:"+rs);
			// ���⣺��׼�·�+�����վ���Ĺ���
			String bt="";
			bt=lst.getCbsj()+"�����վ���Ĺ���";
			
			String dh="";
			//ZG+վ�����ԣ�����ĸ��+���ڣ�20130304��+����������ĸ��+վ��id    ����֧��   ������     ����    Ӫҵ����   ��վ    ���û���
			//һ�����ĵ��ĵ��ţ�zg+����+BM+���ֵ��+1��
			dh="ZG"+jztypename+ss+shiname1+lst.getZdid()+"";
			String pczgdh="";
			pczgdh="ZG"+ss+"BM"+j;
			//System.out.println("���ţ�"+dh);
			//System.out.println("���⣺"+bt);
			//System.out.println("�������ţ�"+pczgdh);
		
		sql.append( "insert into CBZDXX(WJID,ZGYQ,LCH,SJLRR,SJLRSJ,ZDID,CBSJ,SJNAME,DGPCH,BT,YPPCH,WCSJ,FILEPATH) values('"+lst.getId()+"','"+sjyq+"','"+j+"','"+acname+"',"+s+",'"+lst.getZdid()+"','"+lst.getCbsj()+"','"+name+"','"+dh+"','"+bt+"','"+pczgdh+"',to_date('"+entrytimeQS+"','yyyy-MM-DD'),'"+path+"')��");
		
	//System.out.println("����վ���ѯ�ϴ��ļ��������ݿ�sql��"+sql);
			
			}
		
		try {
			System.out.println("����վ���ѯ�ϴ��ļ��������ݿ�sql��"+sql);
			
			  System.out.println("����վ���ѯ�ϴ��ļ��������ݿ�sqlִ��������"+ts);
				db.connectDb();
				//i=db.update(sql.toString());
			     db.updateBatchDr(sql.toString().split("��"));//��������������ݲ���
			     i=1;
				if(i!=0){
					msg="�ɵ��ɹ���";
				}
				
			} catch (DbException e) {
				try{
					db.rollback();
				}catch (Exception dee) {
					dee.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try{
					db.close();
				}catch(Exception a){
					a.printStackTrace();
				}
			}
		
		}
		return msg;
	}
	
	public String insertToDB3(String path,String name,List<Zdinfo> ls,String sjyq,String acname,String wcsj,String id){
		String msg="�ɵ�ʧ�ܣ�";
		int i=0;
		String lch="";
		//System.out.println("filename"+filename);
		//int ii=filename.indexOf(".");
		//filename=filename.substring(0,ii);
		String s="SYSDATE";
		if(!path.equals("")){
			path=path+"\\"+name;
		}
		//path=path+"\\"+name; 
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//�ϴ�����
		if(ls!=null){
			for(Zdinfo lst:ls){
			//	String xname="";
			// 	xname=lst.getId()+id+"ID"+name;

		String sql = "insert into CBZDXF(BWJID,XFZGYQ,LCH,SHENGJLRR,SHENGJLRSJ,FILEPATH,WCSJ,SJPATH) values('"+lst.getId()+"','"+sjyq+"','"+lst.getLch()+"','"+acname+"',"+s+",'"+path+"',to_date('"+wcsj+"','YYYY-MM-DD'),'"+name+"')";
		  try {
			  System.out.println("����"+sql);
				db.connectDb();
				i=db.update(sql.toString());
				if(i!=0){
					msg="�ɵ��ɹ���";
				}
				
			} catch (DbException e) {
				try{
					db.rollback();
				}catch (Exception dee) {
					dee.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try{
					db.close();
				}catch(Exception a){
					a.printStackTrace();
				}
			}
		}
		}
		return msg;
	}

}
		//��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
		//String sqlForInsertBlob = "select t.filecontent from filetable t where t.filename='"+filename+"' and t.uploaddate=TO_DATE(('"+uploadDate+"'), 'yyyy-mm-dd')  for update";
		//InsertToBlob(sqlForInsertBlob,fileItem);
	
	/**
	 * �����ݿ��в���������ļ�
	 * @param ����ѯ��sql
	 * @throws Exception 
	 */
//	public void InsertToBlob(String sql,FileItem fileItem) throws Exception{
//		Connection conn = db.getConnection();
//		conn.setAutoCommit(false);
//		PreparedStatement  ps = conn.prepareStatement(sql);
//		System.out.println("--"+sql.toString());
//		ResultSet rs = ps.executeQuery();
//		if(rs.next()){
//			BLOB tmp = (BLOB) ((OracleResultSet)rs).getBlob(1);
//			InputStream is = fileItem.getInputStream();
//			OutputStream os = tmp.getBinaryOutputStream();
//			
//			byte[] buffer = new byte[1024];
//			int size = -1;
//			while((size=is.read(buffer))!=-1){
//				os.write(buffer,0,size);
//			}
//			is.close();
//			os.close();
//			conn.commit();
//			rs.close();
//			ps.close();
//		}
//	}
