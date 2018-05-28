package com.noki.jizhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;

public class StationRelationshipTreeBean {
	
	public StationRelationshipTreeBean() {
		db = new DataBase();
	}
	
	private DataBase db;
	private String context;
	
	private String shi;
	private String xian;
	private String jztype;
	private String gdfs;
	private String yflx;
	private String zdcq;
	private String bgsign;
	private String jnsign;
	private String cjdsign;
    private String startmonth;
    private String endmonth;
    private String property;
    private String gsf;
    private String dbyt;
    private String g2;
    private String g3;
    private String kt1;
    private String kt2;
    private String kdsb;
    private String yysb;
    
	
	public List<StationRelationshipTree> getTree(){
		List<StationRelationshipTree> treeList = new ArrayList<StationRelationshipTree>();
		ResultSet set = this.getQueryResult();
		try {
			Integer currentId = 0;
			
			while(set.next()){
				Boolean foundCityNodeName = Boolean.FALSE;		//默认找不到显示的名称
				
				Integer cityParentId = 0;
				Integer countryParentId = 0;
				Integer stationTypeParentId = 0;
				Integer stationNameParentId = 0;
				Integer typeId=0;
				
				//----------------------城市的处理------------start---------------------------------------------------
				String cityGrade = set.getString(1);		//城市名称
				String shi=set.getString(2);
				for(StationRelationshipTree tree:treeList){
					if(tree.getContent().equals(cityGrade)){
						foundCityNodeName = Boolean.TRUE;
						countryParentId = tree.getId();
						break;
					}
				}
				if(!foundCityNodeName){							//如果在树中找不到当前的城市名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(cityParentId);
					ct.setContent(cityGrade);
					ct.setUrl(null);
					treeList.add(ct);
					countryParentId = currentId;
				}
				//----------------------城市的处理------------end-----------------------------------------------------
				
				
				//----------------------区县的处理------------start---------------------------------------------------
				String countryGrade = set.getString(3);		//区县名称
				String xian=set.getString(4);
				Boolean foundCountryNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getContent().equals(countryGrade)){
						foundCountryNodeName = Boolean.TRUE;						
						stationTypeParentId = tree.getId();
						break;
					}
				}
				if(!foundCountryNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(countryParentId);
					ct.setContent(countryGrade);
					ct.setUrl(null);
					treeList.add(ct);
					stationTypeParentId = currentId;
				}
				//----------------------区县的处理------------end-----------------------------------------------------
				
				
				//----------------------集团报表类型的处理------------start-----------------------------------------------
				String stationType = set.getString(5);		//集团报表类型
				String stationtype = set.getString(6);		
				Boolean foundStationTypeNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getPid()==stationTypeParentId && tree.getContent().equals(stationType)){
						foundStationTypeNodeName = Boolean.TRUE;						
						stationNameParentId = tree.getId();
						break;
					}
				}
				if(!foundStationTypeNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(stationTypeParentId);
					ct.setContent(stationType);
					ct.setUrl(getContext()+"/web/jizhan/stationRelationshipMapView_iframe_station.jsp?stationType="+stationtype+"&cityGrade="+shi+"&countryGrade="+xian+"&bgsign="+bgsign+"&cjdsign="+cjdsign+"&gdfs="+gdfs+"&jnsign="+jnsign+"&yflx="+yflx+"&zdcq="+zdcq);
					treeList.add(ct);
					stationNameParentId = currentId;
				}
				//----------------------集团报表类型的处理------------end-----------------------------------------------------
				
				
				//----------------------站点名称的处理------------start---------------------------------------------------
			
				/*String stationName = set.getString(4);		//站点名称
				Integer id = set.getInt(5);					//站点的唯一标识id 
				currentId ++;
				StationRelationshipTree ct = new StationRelationshipTree();
				ct.setId(currentId);
				ct.setPid(stationNameParentId);
				ct.setContent(stationName);
				ct.setUrl(getContext()+"/web/jizhan/stationRelationshipMapView_iframe_nodeInfo.jsp?stationId="+id);//这个地方可以写打开叶子节点后所触发的事件
				treeList.add(ct);*/
				//----------------------站点名称的处理------------end-----------------------------------------------------
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		
		return treeList;
	}
	//站点负责人树形结构
	public List<StationRelationshipTree> getTreefuzeren(){
		List<StationRelationshipTree> treeList = new ArrayList<StationRelationshipTree>();
		ResultSet set = this.getQueryResultfuzeren();
		try {
			Integer currentId = 0;
			
			while(set.next()){
				Boolean foundCityNodeName = Boolean.FALSE;		//默认找不到显示的名称
				
				Integer cityParentId = 0;
				Integer countryParentId = 0;
				Integer stationTypeParentId = 0;
				Integer stationNameParentId = 0;
				Integer typeId=0;
				
				//----------------------城市的处理------------start---------------------------------------------------
				String cityGrade = set.getString(1);		//城市名称
				String shi=set.getString(3);
				for(StationRelationshipTree tree:treeList){
					if(tree.getContent().equals(cityGrade)){
						foundCityNodeName = Boolean.TRUE;
						countryParentId = tree.getId();
						break;
					}
				}
				if(!foundCityNodeName){							//如果在树中找不到当前的城市名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(cityParentId);
					ct.setContent(cityGrade);
					ct.setUrl(null);
					treeList.add(ct);
					countryParentId = currentId;
				}
				//----------------------城市的处理------------end-----------------------------------------------------
				
				
				//----------------------区县的处理------------start---------------------------------------------------
				String countryGrade = set.getString(2);		//区县名称
				String xian = set.getString(4);
				if(countryGrade!=null){
					Boolean foundCountryNodeName = Boolean.FALSE;
					for(StationRelationshipTree tree:treeList){
						if(tree.getContent().equals(countryGrade)){
							foundCountryNodeName = Boolean.TRUE;						
							stationTypeParentId = tree.getId();
							break;
						}
					}
					if(!foundCountryNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
						currentId ++;
						StationRelationshipTree ct = new StationRelationshipTree();
						ct.setId(currentId);
						ct.setPid(countryParentId);
						ct.setContent(countryGrade);
						ct.setUrl(getContext()+"/web/jizhan/fuzeren_iframe_fuzeren.jsp?countryGrade="+shi+"&cityGrade="+xian+"&property="+property+"&gsf="+gsf+"&bgsign="+bgsign+"&dbyt="+dbyt+"&gdfs="+gdfs+"&zdcq="+zdcq+"&g2="+g2+"&g3="+g3+"&kt1="+kt1+"&kt2="+kt2+"&kdsb="+kdsb+"&yysb="+yysb);
						treeList.add(ct);
						stationTypeParentId = currentId;
					}
				}
				//----------------------区县的处理------------end-----------------------------------------------------
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		
		return treeList;
	}
	
	private ResultSet getQueryResult(){
		
		ResultSet set = null;
		
		StringBuffer sql = new StringBuffer("select (select d1.agname from d_area_grade d1 where d1.agcode = z.shi),z.shi, (select d2.agname from d_area_grade d2 where d2.agcode = z.xian),z.xian, i.name,  i.code,z.jzname, z.id from zhandian z, d_area_grade d,indexs i where d.agcode = z.xian and i.code=z.jztype");
		if(this.bgsign != null && !this.bgsign.equals("-1"))sql.append(" and z.bgsign = '"+this.bgsign+"'");
		if(this.cjdsign != null && !this.cjdsign.equals("-1"))sql.append(" and z.caiji = '"+this.cjdsign+"'");
		if(this.gdfs != null && !this.gdfs.equals("-1"))sql.append(" and z.gdfs = '"+this.gdfs+"'");
		if(this.jnsign != null && !this.jnsign.equals("-1"))sql.append(" and z.jnglmk = '"+this.jnsign+"'");
		if(this.jztype != null && !this.jztype.equals("-1"))sql.append(" and z.jztype = '"+this.jztype+"'");
		if(this.shi != null && !this.shi.equals("0"))sql.append(" and z.shi = '"+this.shi+"'");
		if(this.xian != null && !this.xian.equals("-1"))sql.append(" and z.xian = '"+this.xian+"'");
		if(this.yflx != null && !this.yflx.equals("-1"))sql.append(" and z.yflx = '"+this.yflx+"'");
		if(this.zdcq != null && !this.zdcq.equals("-1"))sql.append(" and z.zdcq = '"+this.zdcq+"'");
		
		System.out.println(sql);
		try {
			set = db.queryAll(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
		return set;
	}
	//站点负责人树形结构数据库查询语句
  private ResultSet getQueryResultfuzeren(){
		ResultSet set = null;
		
		StringBuffer sql = new StringBuffer("select distinct (select d1.agname from d_area_grade d1 where d1.agcode = z.shi),(select d2.agname from d_area_grade d2 where d2.agcode = z.xian),z.shi,z.xian,z.fzr from zhandian z,dianbiao d  where z.id=d.zdid ");
		if(this.property != null && !this.property.equals("-1"))sql.append(" and property = '"+this.property+"'");
		if(this.gsf != null && !this.gsf.equals("-1"))sql.append(" and gsf = '"+this.gsf+"'");
		if(this.bgsign != null && !this.bgsign.equals("-1"))sql.append(" and z.bgsign = '"+this.bgsign+"'");
		if(this.dbyt != null && !this.dbyt.equals("-1"))sql.append(" and d.dbyt = '"+this.dbyt+"'");
		if(this.gdfs != null && !this.gdfs.equals("-1"))sql.append(" and z.gdfs = '"+this.gdfs+"'");
		if(this.zdcq != null && !this.zdcq.equals("-1"))sql.append(" and z.zdcq = '"+this.zdcq+"'");
		if(this.g2 != null && !this.g2.equals("-1"))sql.append(" and z.g2 = '"+this.g2+"'");
		if(this.g3 != null && !this.g3.equals("-1"))sql.append(" and z.g3 = '"+this.g3+"'");
		if(this.kt1 != null && !this.kt1.equals("-1"))sql.append(" and z.kt1 = '"+this.kt1+"'");
		if(this.kt2 != null && !this.kt2.equals("-1"))sql.append(" and z.kt2 = '"+this.kt2+"'");
		if(this.kdsb != null && !this.kdsb.equals("-1"))sql.append(" and z.kdsb = '"+this.kdsb+"'");
		if(this.yysb != null && !this.yysb.equals("-1"))sql.append(" and z.yysb = '"+this.yysb+"'");

		System.out.println(sql);
		try {
			set = db.queryAll(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
		return set;
	}
	
	//费用分析图-----------------------------------------------------------------------------------------------------------------------
	public List<StationRelationshipTree> getAnalysisTree(){
		List<StationRelationshipTree> treeList = new ArrayList<StationRelationshipTree>();
		startmonth=this.getStartmonth();
		endmonth=this.getEndmonth();
		System.out.println("-"+startmonth+"-"+endmonth);
			ResultSet set = this.getQuery();
		
		
		try {
			Integer currentId = 0;
			
			while(set.next()){
				Boolean foundCityNodeName = Boolean.FALSE;		//默认找不到显示的名称
				
				Integer cityParentId = 0;
				Integer countryParentId = 0;
				Integer stationTypeParentId = 0;
				Integer stationNameParentId = 0;
				Integer fentanId=0;
				Integer typeId=0;
				String shengbb="",shibb="",xianbb="";
				
				//----------------------城市的处理------------start---------------------------------------------------
				String cityGrade = set.getString(1);		//城市名称
				
				for(StationRelationshipTree tree:treeList){
					if(tree.getContent().equals(cityGrade)){
						foundCityNodeName = Boolean.TRUE;
						countryParentId = tree.getId();
						break;
					}
				}
				if(!foundCityNodeName){							//如果在树中找不到当前的城市名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(cityParentId);
					ct.setContent(cityGrade);
					ct.setUrl(null);
					treeList.add(ct);
					countryParentId = currentId;
				}
				//----------------------城市的处理------------end-----------------------------------------------------
				
				
				//----------------------区县的处理------------start---------------------------------------------------
				//System.out.println("--"+cityGrade);
				if(cityGrade.equals("省本部")||cityGrade.equals("省传输局")){
					 shengbb=cityGrade;
					String stationName = set.getString(4);		//站点属性
					//System.out.println("-111-"+stationName);
					Boolean stationNameNodeName = Boolean.FALSE;
					for(StationRelationshipTree tree:treeList){
						if(tree.getPid()==countryParentId && tree.getContent().equals(stationName)){
							stationNameNodeName = Boolean.TRUE;						
							fentanId = tree.getId();
							break;
						}
					}
					if(!stationNameNodeName){							//
						
						currentId ++;
						StationRelationshipTree ct = new StationRelationshipTree();
						ct.setId(currentId);
						ct.setPid(countryParentId);
						ct.setContent(stationName);
						fentanId=currentId;
						ct.setUrl(null);//这个地方可以写打开叶子节点后所触发的事件
						treeList.add(ct);
						
					}
					String fentanName = set.getString(5);		//分摊
					Boolean fentanNodeName = Boolean.FALSE;
					for(StationRelationshipTree tree:treeList){
						if(tree.getPid()==fentanId && tree.getContent().equals(fentanName)){
							fentanNodeName = Boolean.TRUE;						
							fentanId = tree.getId();
							break;
						}
					}
					if(!fentanNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
						currentId ++;
						StationRelationshipTree ct = new StationRelationshipTree();
						ct.setId(currentId);
						ct.setPid(fentanId);
						ct.setContent(fentanName);
						
						ct.setUrl(getContext()+"/web/jizhan/AnalysisofExpenseMapView_info.jsp?shengbb="+shengbb+"&stationName="+stationName+"&fentanName="+fentanName+"&startmonth="+startmonth+"&endmonth="+endmonth);//这个地方可以写打开叶子节点后所触发的事件
						treeList.add(ct);
						
					}
					continue;
				}
				String countryGrade = set.getString(2);		//区县名称
				Boolean foundCountryNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getPid()==countryParentId &&tree.getContent().equals(countryGrade)){
						foundCountryNodeName = Boolean.TRUE;						
						stationTypeParentId = tree.getId();
						break;
					}
				}
				if(!foundCountryNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(countryParentId);
					ct.setContent(countryGrade);
					
					ct.setUrl(null);
					treeList.add(ct);
					stationTypeParentId = currentId;
				}
				//----------------------区县的处理------------end-----------------------------------------------------
				
				
				//----------------------乡镇处理------------start-----------------------------------------------
                   if(countryGrade.equals("市本部")||countryGrade.equals("市传输局")){
					 shibb=countryGrade;
					String stationName = set.getString(4);		//站点属性
					//System.out.println("-11-"+stationName);
					Boolean stationNameNodeName = Boolean.FALSE;
					for(StationRelationshipTree tree:treeList){
						if(tree.getPid()==stationTypeParentId && tree.getContent().equals(stationName)){
							stationNameNodeName = Boolean.TRUE;						
							fentanId = tree.getId();
							break;
						}
					}
					if(!stationNameNodeName){							//
						currentId ++;
						StationRelationshipTree ct = new StationRelationshipTree();
						ct.setId(currentId);
						ct.setPid(stationTypeParentId);
						ct.setContent(stationName);
						fentanId=currentId;
						ct.setUrl(null);//这个地方可以写打开叶子节点后所触发的事件
						treeList.add(ct);
						
					}
					String fentanName = set.getString(5);		//分摊
					Boolean fentanNodeName = Boolean.FALSE;
					for(StationRelationshipTree tree:treeList){
						if(tree.getPid()==fentanId && tree.getContent().equals(fentanName)){
							fentanNodeName = Boolean.TRUE;						
							fentanId = tree.getId();
							break;
						}
					}
					if(!fentanNodeName){							//如果在树中找不到当前的区县名称，则需要添加当前城市节点到树中
						currentId ++;
						StationRelationshipTree ct = new StationRelationshipTree();
						ct.setId(currentId);
						ct.setPid(fentanId);
						ct.setContent(fentanName);
						
						ct.setUrl(getContext()+"/web/jizhan/AnalysisofExpenseMapView_info.jsp?shibb="+shibb+"&cityGrade="+cityGrade+"&stationName="+stationName+"&fentanName="+fentanName+"&startmonth="+startmonth+"&endmonth="+endmonth);//这个地方可以写打开叶子节点后所触发的事件
						treeList.add(ct);
						
					}
					continue;
				}
				String stationType = set.getString(3);		//小区名称
				Boolean foundStationTypeNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getPid()==stationTypeParentId && tree.getContent().equals(stationType)){
						foundStationTypeNodeName = Boolean.TRUE;						
						stationNameParentId = tree.getId();
						break;
					}
				}
				if(!foundStationTypeNodeName){							//
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(stationTypeParentId);
					ct.setContent(stationType);
					ct.setUrl(null);
					treeList.add(ct);
					stationNameParentId = currentId;
				}
				//----------------------小区的处理------------end-----------------------------------------------------
				
				//----------------------属性的处理------------start-----------------------------------------------------
				 if(stationType.equals("县本部")||stationType.equals("县传输局")){
						 xianbb=stationType;
						String stationName = set.getString(4);		//站点属性
					//	System.out.println("-11-"+stationName);
						Boolean stationNameNodeName = Boolean.FALSE;
						for(StationRelationshipTree tree:treeList){
							if(tree.getPid()==stationNameParentId && tree.getContent().equals(stationName)){
								stationNameNodeName = Boolean.TRUE;						
								fentanId = tree.getId();
								break;
							}
						}
						if(!stationNameNodeName){							//
							currentId ++;
							StationRelationshipTree ct = new StationRelationshipTree();
							ct.setId(currentId);
							ct.setPid(stationNameParentId);
							ct.setContent(stationName);
							fentanId=currentId;
							System.out.println("currentId"+currentId+"countryParentId"+stationNameParentId+"stationName"+stationName);
							ct.setUrl(null);//这个地方可以写打开叶子节点后所触发的事件
							treeList.add(ct);
							
						}
						String fentanName = set.getString(5);		//分摊
						Boolean fentanNodeName = Boolean.FALSE;
						for(StationRelationshipTree tree:treeList){
							if(tree.getPid()==fentanId && tree.getContent().equals(fentanName)){
								fentanNodeName = Boolean.TRUE;						
								fentanId = tree.getId();
								break;
							}
						}
						if(!fentanNodeName){							//
							currentId ++;
							StationRelationshipTree ct = new StationRelationshipTree();
							ct.setId(currentId);
							ct.setPid(fentanId);
							ct.setContent(fentanName);
							
							ct.setUrl(getContext()+"/web/jizhan/AnalysisofExpenseMapView_info.jsp?xianbb="+xianbb+"&stationType="+stationType+"&stationName="+stationName+"&fentanName="+fentanName+"&startmonth="+startmonth+"&endmonth="+endmonth);//这个地方可以写打开叶子节点后所触发的事件
							treeList.add(ct);
							
						}
						continue;
					}
				
				 
				String stationName = set.getString(4);		//站点属性
				Boolean stationNameNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getPid()==stationNameParentId && tree.getContent().equals(stationName)){
						stationNameNodeName = Boolean.TRUE;						
						fentanId = tree.getId();
						break;
					}
				}
				if(!stationNameNodeName){							//
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(stationNameParentId);
					ct.setContent(stationName);
					fentanId=currentId;
					ct.setUrl(null);//这个地方可以写打开叶子节点后所触发的事件
					treeList.add(ct);
					
				}
				//----------------------属性的处理------------end-----------------------------------------------------
				
				//----------------------分摊的处理------------start-----------------------------------------------------
				String fentanName = set.getString(5);		//分摊
				Boolean fentanNodeName = Boolean.FALSE;
				for(StationRelationshipTree tree:treeList){
					if(tree.getPid()==fentanId && tree.getContent().equals(fentanName)){
						fentanNodeName = Boolean.TRUE;						
						fentanId = tree.getId();
						break;
					}
				}
				if(!fentanNodeName){							//
					currentId ++;
					StationRelationshipTree ct = new StationRelationshipTree();
					ct.setId(currentId);
					ct.setPid(fentanId);
					ct.setContent(fentanName);
					ct.setUrl(getContext()+"/web/jizhan/AnalysisofExpenseMapView_info.jsp?stationType="+stationType+"&stationName="+stationName+"&fentanName="+fentanName+"&startmonth="+startmonth+"&endmonth="+endmonth);//这个地方可以写打开叶子节点后所触发的事件
					treeList.add(ct);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		
		return treeList;
	}
	
	private ResultSet getQuery(){
		
		ResultSet set = null;
		StringBuffer sql=new StringBuffer("select case gsf when 'gsf01' then '省本部'  when 'gsf05' then '省传输局' else aa.shi end shi," +
				"case gsf when 'gsf02' then '市本部'   when 'gsf06' then  '市传输局' else aa.xian end xian," +
				"case gsf when 'gsf03' then '县本部'   when 'gsf07' then  '县传输局'  else aa.xiaoqu end xiaoqu,aa.name,aa.sszy " +
				"from (select distinct i.name,gsf,(select d1.agname from d_area_grade d1 where d1.agcode = z.shi) as shi," +
				"(select d2.agname from d_area_grade d2 where d2.agcode = z.xian) as xian," +
				"(select d3.agname  from d_area_grade d3    where d3.agcode = z.xiaoqu) as xiaoqu," +
				"(select ii.name from indexs ii where ii.code=s.shuoshuzhuanye and  ii.type='SSZY')as sszy  " +
				" from zhandian z, d_area_grade d, indexs i,dianbiao dd,sbgl s  where d.agcode = z.xian  " +
				"and dd.zdid=z.id and dd.dbid=s.dianbiaoid and dd.dbyt='dbyt01' and i.code = z.stationtype " );
		if(this.jztype != null && !this.jztype.equals("-1"))sql.append(" and z.stationtype = '"+this.jztype+"'");
		if(this.shi != null && !this.shi.equals("-1"))sql.append(" and z.shi = '"+this.shi+"'");
		if(this.xian != null && !this.xian.equals("-1"))sql.append(" and z.xian = '"+this.xian+"'");
		sql.append(" )aa  order by shi");
		System.out.println(sql);
		try {
			set = db.queryAll(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
		return set;
	}
	//详细信息
	public synchronized ArrayList getCollect(String str) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();

		ResultSet rs = null;
		try {
			db.connectDb();
			sql="select  z.zdcode,z.jzname,sum(dl.blhdl)as dl,sum(df.actualpay)as df,s.dbili " +
					"from zhandian z,dianbiao d,ammeterdegrees dl,electricfees df,indexs i,d_area_grade g,sbgl s  " +
					"where z.id=d.zdid and s.dianbiaoid=d.dbid and d.dbid=dl.ammeterid_fk and dl.ammeterdegreeid=df.ammeterdegreeid_fk  " +
					"and z.property=i.code  "+str+" " +
					"group by z.zdcode,z.jzname,s.dbili";
			System.out.println(sql.toString() + "***********************");
			rs = db.queryAll(sql.toString());

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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getXian() {
		return xian;
	}
	public void setXian(String xian) {
		this.xian = xian;
	}
	public String getJztype() {
		return jztype;
	}
	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getYflx() {
		return yflx;
	}
	public void setYflx(String yflx) {
		this.yflx = yflx;
	}
	public String getZdcq() {
		return zdcq;
	}
	public void setZdcq(String zdcq) {
		this.zdcq = zdcq;
	}
	public String getBgsign() {
		return bgsign;
	}
	public void setBgsign(String bgsign) {
		this.bgsign = bgsign;
	}
	public String getJnsign() {
		return jnsign;
	}
	public void setJnsign(String jnsign) {
		this.jnsign = jnsign;
	}
	public String getCjdsign() {
		return cjdsign;
	}
	public void setCjdsign(String cjdsign) {
		this.cjdsign = cjdsign;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}

	public String getStartmonth() {
		return startmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}
	public DataBase getDb() {
		return db;
	}
	public void setDb(DataBase db) {
		this.db = db;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getGsf() {
		return gsf;
	}
	public void setGsf(String gsf) {
		this.gsf = gsf;
	}
	public String getDbyt() {
		return dbyt;
	}
	public void setDbyt(String dbyt) {
		this.dbyt = dbyt;
	}
	public String getG2() {
		return g2;
	}
	public void setG2(String g2) {
		this.g2 = g2;
	}
	public String getG3() {
		return g3;
	}
	public void setG3(String g3) {
		this.g3 = g3;
	}
	public String getKt1() {
		return kt1;
	}
	public void setKt1(String kt1) {
		this.kt1 = kt1;
	}
	public String getKt2() {
		return kt2;
	}
	public void setKt2(String kt2) {
		this.kt2 = kt2;
	}
	public String getKdsb() {
		return kdsb;
	}
	public void setKdsb(String kdsb) {
		this.kdsb = kdsb;
	}
	public String getYysb() {
		return yysb;
	}
	public void setYysb(String yysb) {
		this.yysb = yysb;
	}
	
}
