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
	 * 文件是否上传过
	 * @return 上传过返回false否则为true
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
	 * 将当前文件插入数据库
	 * @param fileItem
	 * @throws Exception 
	 */
	public String insertToDB(String path,String name,List<Zdinfo> ls,String sjyq,String acname,int j,String entrytimeQS){
		String msg="派单失败！";
		int i=0;
		String lch="";
		//System.out.println("filename"+filename);
		//int ii=filename.indexOf(".");
		//filename=filename.substring(0,ii);
		String s="SYSDATE";
		if(!path.equals("")){
			path=path+"\\"+name;
		}
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//上传日期
		String fmt = "";
		fmt = "yyyyMMdd";
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		String ss=df.format(uploadDate);
	
		StringBuffer sql= new StringBuffer();
		int ts=0;//判断插入多少条
		if(ls!=null){
			for(Zdinfo lst:ls){
				ts++;
				String shiname1="";
				String shiname=lst.getShicode();
				if(shiname.equals("济南市")){shiname1="JN";}
				if(shiname.equals("青岛市")){shiname1="qd";}
				if(shiname.equals("淄博市")){shiname1="zb";}
				if(shiname.equals("威海市")){shiname1="wh";}
				if(shiname.equals("烟台市")){shiname1="yt";}
				if(shiname.equals("日照市")){shiname1="rz";}
				if(shiname.equals("枣庄市")){shiname1="zz";}
				if(shiname.equals("临沂市")){shiname1="ly";}
				if(shiname.equals("泰安市")){shiname1="ta";}
				if(shiname.equals("滨州市")){shiname1="bz";}
				if(shiname.equals("菏泽市")){shiname1="hz";}
				if(shiname.equals("济宁市")){shiname1="jn";}
				if(shiname.equals("聊城市")){shiname1="lc";}
				if(shiname.equals("潍坊市")){shiname1="wf";}
				if(shiname.equals("东营市")){shiname1="dy";}
				if(shiname.equals("莱芜市")){shiname1="lw";}
				if(shiname.equals("德州市")){shiname1="dz";}

				String jztypename=lst.getJztype();
				if("乡镇支局".equals(jztypename)){jztypename="xz";}
				if("接入网".equals(jztypename)){jztypename="jr";}
				if("其他".equals(jztypename)){jztypename="qt";}
				if("营业网点".equals(jztypename)){jztypename="yy";}
				if("基站".equals(jztypename)){jztypename="jz";}
				if("局用机房".equals(jztypename)){jztypename="jy";}
				String sj="";
				//System.out.println("shiname："+shiname);
				//System.out.println("shiname1："+shiname1);
				ResultSet rs=null;
				String sddf="";
				sddf=lst.getId()+lst.getZdid()+"ID"+name;
				//System.out.println("name:"+name);
			//System.out.println("sj:"+rs);
			// 标题：标准月份+超标基站整改工单
			String bt="";
			bt=lst.getCbsj()+"超标基站整改工单";
			
			String dh="";
			//ZG+站点属性（俩字母）+日期（20130304）+地区（俩字母）+站点id    乡镇支局   接入网     其他    营业网点   基站    局用机房
			//一批整改单的单号：zg+日期+BM+最大值（+1）
			dh="ZG"+jztypename+ss+shiname1+lst.getZdid()+"";
			String pczgdh="";
			pczgdh="ZG"+ss+"BM"+j;
			//System.out.println("单号："+dh);
			//System.out.println("标题："+bt);
			//System.out.println("整批单号："+pczgdh);
		
		sql.append( "insert into CBZDXX(WJID,ZGYQ,LCH,SJLRR,SJLRSJ,ZDID,CBSJ,SJNAME,DGPCH,BT,YPPCH,WCSJ,FILEPATH) values('"+lst.getId()+"','"+sjyq+"','"+j+"','"+acname+"',"+s+",'"+lst.getZdid()+"','"+lst.getCbsj()+"','"+name+"','"+dh+"','"+bt+"','"+pczgdh+"',to_date('"+entrytimeQS+"','yyyy-MM-DD'),'"+path+"')の");
		
	//System.out.println("超标站点查询上传文件插入数据库sql："+sql);
			
			}
		
		try {
			System.out.println("超标站点查询上传文件插入数据库sql："+sql);
			
			  System.out.println("超标站点查询上传文件插入数据库sql执行条数："+ts);
				db.connectDb();
				//i=db.update(sql.toString());
			     db.updateBatchDr(sql.toString().split("の"));//批量处理大量数据插入
			     i=1;
				if(i!=0){
					msg="派单成功！";
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
		String msg="派单失败！";
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
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//上传日期
		if(ls!=null){
			for(Zdinfo lst:ls){
			//	String xname="";
			// 	xname=lst.getId()+id+"ID"+name;

		String sql = "insert into CBZDXF(BWJID,XFZGYQ,LCH,SHENGJLRR,SHENGJLRSJ,FILEPATH,WCSJ,SJPATH) values('"+lst.getId()+"','"+sjyq+"','"+lst.getLch()+"','"+acname+"',"+s+",'"+path+"',to_date('"+wcsj+"','YYYY-MM-DD'),'"+name+"')";
		  try {
			  System.out.println("跟心"+sql);
				db.connectDb();
				i=db.update(sql.toString());
				if(i!=0){
					msg="派单成功！";
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
		//需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
		//String sqlForInsertBlob = "select t.filecontent from filetable t where t.filename='"+filename+"' and t.uploaddate=TO_DATE(('"+uploadDate+"'), 'yyyy-mm-dd')  for update";
		//InsertToBlob(sqlForInsertBlob,fileItem);
	
	/**
	 * 向数据库中插入二进制文件
	 * @param 待查询的sql
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
