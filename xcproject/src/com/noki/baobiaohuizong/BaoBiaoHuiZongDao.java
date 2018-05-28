package com.noki.baobiaohuizong;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.noki.baobiaohuizong.bean.Bdsyswcjdbb;
import com.noki.baobiaohuizong.bean.Bqydfdlfx;
import com.noki.baobiaohuizong.bean.Bqyjzpuemxb;
import com.noki.baobiaohuizong.bean.Bqyyczdtj;
import com.noki.baobiaohuizong.bean.Bqyyswcjd;
import com.noki.baobiaohuizong.bean.Bsdpjpuebb;
import com.noki.baobiaohuizong.bean.DQName;
import com.noki.baobiaohuizong.bean.Dfcypldbg;
import com.noki.baobiaohuizong.bean.Idcjljqtlxdfbb;
import com.noki.baobiaohuizong.bean.RYName;
import com.noki.baobiaohuizong.bean.Ydxzzdslcx;
import com.noki.baobiaohuizong.bean.Zdshl;
import com.noki.baobiaohuizong.bean.Zhdjbb;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.YspzClass;

public class BaoBiaoHuiZongDao  extends DataBase{
	
	//查询视图数据总行数
	public int count(String sql) {
		
		//定义总行数
		
		int zh = 0;			
		
		//定义每页条数
		
		int ts = 10;
		
		//定义共有几页
		
		int ys = 0;			

		System.out.println(sql);
		sql = "SELECT COUNT(0) FROM("+sql+")";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if(rs.next()){
				zh = rs.getInt(1);
			}
		} catch (Exception de) {
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
		//计算是否余列
		int x = zh % ts;
		if(x != 0){
			ys = zh / ts + 1;
		}else{
			ys = zh / ts;
		}
		//返回共有几页
		return ys; 
	}
	//根据第几页判断SQL语句
	public String fysql(int curpage,String sql){
		//计算应跳转的内容
		int x = curpage * 10;		
		int y = curpage * 10 - 9;
		String fenyesql = "SELECT * FROM ( SELECT A.*, ROWNUM R  FROM ("+sql+") A  WHERE ROWNUM <= "+x+" )WHERE R >= "+y+"  ";
		return fenyesql;
	}
	//根据地区编码查询地区中文名称
	public String DQname(String agcode){  
		String agname = "";
		String sql="SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = '"+agcode+"'";
        List<DQName> list = super.Query(sql, null, DQName.class); 
        for(int i = 0;i<list.size();i++){
        	agname = list.get(i).getAgname();
        }
		return agname;  
	} 
	
	//根据人员编码查询人员的中文名称
	public String RYname(String bzr){  
		String bzrName = "";
		String sql="SELECT NAME FROM ACCOUNT WHERE CTHRNUMBER = '"+bzr+"'";
        List<RYName> list = super.Query(sql, null, RYName.class); 
        for(int i = 0;i<list.size();i++){
        	bzrName = list.get(i).getName();
        }
		return bzrName;  
	} 
	
	public int pagesYspzClass(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT * FROM YSPZ WHERE 1=1 AND STATE = 0";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
	public List<YspzClass> queryYspzClass(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT * FROM YSPZ WHERE 1=1 AND STATE = 0";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql =fysql(curpage,sql);
        List<YspzClass> list =super.Query(sql, null, YspzClass.class);  
        return list;  
    }
	
	public int pagesBqydfdlfx(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,ALLMONEY,DIANLIANG,DLPLS,PJFZZDL,BZR,MEMO FROM VIEW_BQYDFDLFX WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	} 
    public List<Bqydfdlfx> queryBqydfdlfx(int curpage,String shi,String xian,String xiaoqu){  
    	
        String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,ALLMONEY,DIANLIANG,DLPLS,PJFZZDL,BZR,MEMO FROM VIEW_BQYDFDLFX WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Bqydfdlfx> list = super.Query(sql, null, Bqydfdlfx.class);  
        return list;  
    }
	public int pagesBqyjzpuemxb(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,YUEDU,EDHDL,PJFZZDL,PUEZHI,BZR,MEMO FROM VIEW_BQYJZPUEMXB WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Bqyjzpuemxb> queryBqyjzpuemxb(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,COSTTIME,YUEDU,EDHDL,PJFZZDL,PUEZHI,BZR,MEMO FROM VIEW_BQYJZPUEMXB WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Bqyjzpuemxb> list = super.Query(sql, null, Bqyjzpuemxb.class);  
        return list;  
    }
	public int pagesBqyyczdtj(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,YCPLD,PJFZZDL,BZR,MEMO FROM VIEW_BQYYCZDTJ WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Bqyyczdtj> queryBqyyczdtj(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,YCPLD,PJFZZDL,BZR,MEMO FROM VIEW_BQYYCZDTJ WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Bqyyczdtj> list = super.Query(sql, null, Bqyyczdtj.class);  
        return list;  
    }
		public int pagesBqyyswcjd(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,BZR,MEMO FROM VIEW_BQYYSWCJD WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
        public List<Bqyyswcjd> queryBqyyswcjd(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT STATION_NO,JZNAME,SHI,XIAN,XIAOQU,ISSUPERVISOR,HOUSETYPE_NAME,RJYDL,ALLMONEY,SJDIANLIANG,EDHDL,BZR,MEMO FROM VIEW_BQYYSWCJD WHERE 1=1";
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Bqyyswcjd> list = super.Query(sql, null, Bqyyswcjd.class);  
        return list;  
    }
	public int pagesZhdjbb(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,ZHDANJIA  FROM VIEW_ZHDJBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
        public List<Zhdjbb> queryZhdjbb(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,ZHDANJIA  FROM VIEW_ZHDJBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Zhdjbb> list = super.Query(sql, null, Zhdjbb.class);  
        return list;  
    }
	public int pagesDfcypldbg(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,ALLMONEY,DEDF,CYPLD FROM VIEW_DFCYPLDBG WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Dfcypldbg> queryDfcypldbg(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,ALLMONEY,DEDF,CYPLD FROM VIEW_DFCYPLDBG WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Dfcypldbg> list = super.Query(sql, null, Dfcypldbg.class);  
        return list;  
    }
	public int pagesIdcjljqtlxdfbb(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,DIANLIANG,ALLMONEY,PRICE,JZTYPE FROM VIEW_IDCJLJQTLXDFBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Idcjljqtlxdfbb> queryIdcjljqtlxdfbb(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT COSTTIME,SHI,XIAN,XIAOQU,JZNAME,DBNAME,DIANLIANG,ALLMONEY,PRICE,JZTYPE FROM VIEW_IDCJLJQTLXDFBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Idcjljqtlxdfbb> list = super.Query(sql, null, Idcjljqtlxdfbb.class);  
        return list;  
    }
	public int pagesBdsyswcjdbb(String shi){  
		int ys = 0;
		String sql="SELECT COSTTIME,SHI,ALLMONEY FROM VIEW_BDSYSWCJDBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Bdsyswcjdbb> queryBdsyswcjdbb(int curpage,String shi){  
		String sql="SELECT COSTTIME,SHI,ALLMONEY FROM VIEW_BDSYSWCJDBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		sql = fysql(curpage,sql);
        List<Bdsyswcjdbb> list = super.Query(sql, null, Bdsyswcjdbb.class);  
        return list;  
    }
	public int pagesBsdpjpuebb(String shi){  
		int ys = 0;
		String sql="SELECT COSTTIME,SHI,JZNAME,DIANLIANG,BGDL,PUEZHI FROM VIEW_BSDPJPUEBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		ys = count(sql);
		return ys;  
	}
    public List<Bsdpjpuebb> queryBsdpjpuebb(int curpage,String shi){  
		String sql="SELECT COSTTIME,SHI,JZNAME,DIANLIANG,BGDL,PUEZHI FROM VIEW_BSDPJPUEBB WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		sql = fysql(curpage,sql);
        List<Bsdpjpuebb> list = super.Query(sql, null, Bsdpjpuebb.class);  
        return list;  
    }
	public int pagesZdshl(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT CREATEDATE,SHI,XIAN,XIAOQU,JZNAME,ZDSHS,ZDSHL FROM VIEW_ZDSHL WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Zdshl> queryZdshl(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT CREATEDATE,SHI,XIAN,XIAOQU,JZNAME,ZDSHS,ZDSHL FROM VIEW_ZDSHL WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Zdshl> list = super.Query(sql, null, Zdshl.class);  
        return list;  
    }
	public int pagesYdxzzdslcx(String shi,String xian,String xiaoqu){  
		int ys = 0;
		String sql="SELECT CJTIME, SHI, XIAN,XIAOQU,JZNAME, JFDJ, STATIONTYPE, ZDCQ, LONGITUDE, LATITUDE FROM VIEW_YDXZZDSLCX WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		ys = count(sql); 
		return ys;  
	}
    public List<Ydxzzdslcx> queryYdxzzdslcx(int curpage,String shi,String xian,String xiaoqu){  
		String sql="SELECT CJTIME, SHI, XIAN,XIAOQU, JZNAME, JFDJ, STATIONTYPE, ZDCQ, LONGITUDE, LATITUDE FROM VIEW_YDXZZDSLCX WHERE 1=1";
		
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND XIAOQU='"+xiaoqu+"'";
		}
		sql = fysql(curpage,sql);
        List<Ydxzzdslcx> list = super.Query(sql, null, Ydxzzdslcx.class);  
        return list;  
    }
}
