package com.ptac.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noki.dataLoad.servlet.ExportExcelError;
import com.noki.jichuInfo.CbDownload.dianbiaoBean;
import com.noki.jizhan.DianBiaoBean;
import com.noki.jizhan.model.DianBiaoErrorBean;
import com.noki.mobi.common.CommonBean;
import com.ptac.app.accounting.AccountingDao;


public class ImportExcel {

	private ArrayList<String> dbbmList = new ArrayList<String>();
	private ArrayList<String> jzIdList = new ArrayList<String>();

	Logger logger = LoggerFactory.getLogger(ImportExcel.class);
   
	private Workbook wb = null;
	private Sheet sheet = null;
	@SuppressWarnings("rawtypes")
	private ArrayList checked = new ArrayList();
	private int rowIndex = 1; //默认有一级标题
	@SuppressWarnings("rawtypes")
	public ArrayList getChecked() {
		return checked;
	}

	@SuppressWarnings("rawtypes")
	public void setChecked(ArrayList checked) {
		this.checked = checked;
	}

	public ImportExcel() {
	}
	
	public ImportExcel( int rowIndex) {
		this.rowIndex = rowIndex;
	}

   public Object getValue(Cell cell){
	   Object val="";
	   
	   if(cell == null)
		   return val;
	   
	   switch (cell.getCellType()) {
	case Cell.CELL_TYPE_NUMERIC:
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			Date date = cell.getDateCellValue();
			val = DateFormatUtils.format(date, "yyyy-MM-dd");
		}else{
			val = cell.getNumericCellValue();
		}
		break;
	case Cell.CELL_TYPE_STRING:
		val = cell.getStringCellValue();
		break;
	case Cell.CELL_TYPE_BOOLEAN:
		val = cell.getBooleanCellValue();
		break;
	case Cell.CELL_TYPE_ERROR:
		val = cell.getErrorCellValue();
		break;
	default:
		break;
	}
	   return val;
   }
   
   @SuppressWarnings("rawtypes")
public ArrayList readExcel(File file)
			throws FileNotFoundException, Exception {
		return readExcel(file.getName(), new FileInputStream(file));
   }

	@SuppressWarnings("rawtypes")
	public ArrayList readExcel(String filename, InputStream is)
			throws Exception {
		if (filename == null || filename.isEmpty()) {
			throw new Exception("导入文档为空");
		} else if (filename.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (filename.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("读取的不是excel文件");
		}
		this.sheet = wb.getSheetAt(0);
		System.out.println("初始化成功 ，开始读取数据...");
		ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
		for (int i = rowIndex; i <= sheet.getLastRowNum(); i++) {
			ArrayList<Object> data = new ArrayList<Object>();
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				data.add(getValue(cell));
			}
			System.out.println("读取成功[" + i + "] " + data);
			logger.debug("读取成功[" + i + "] " + data);
			dataList.add(data);
		}
		return dataList;
	}

	public String check(ArrayList dataList ,String[] columns) {
		
		StringBuffer errorMsg = new StringBuffer();
		
		int rownumber = 0;
		int colnumber = 0;
		
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String,Object>>();

		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
			
			ArrayList<Object> data = (ArrayList<Object>)iterator.next();
			if(columns.length != data.size()){
				errorMsg.append("完整性校验失败");
				break;
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j = 0; j < data.size(); j++){
				// 对应关系
				map.put(columns[j].toLowerCase(), data.get(j));
			}
			//电表编码
			if(map.get("dbbm") == null){
				System.out.println("第" +(rownumber++)+ "行, " +(colnumber++)+ "列,电表编码为空值！");
				iterator.remove();
				continue;
			}
			//局站Id
			if (map.get("ljid") == null) {
				System.out.println("第" +(rownumber++)+ "行, " +(colnumber++)+ "列,局站ID为空值！");
				iterator.remove();
				continue;
			}
			//成本中心
			if (map.get("cbzxbm") == null || "".equals(map.get("cbzxbm"))) {// 成本中心
				System.out.println("第" +(rownumber++)+ "条," +(colnumber++)+ "列,成本中心为空值！");
				iterator.remove();
				continue;
			}
			//去除重复电表
			String dbbm = String.valueOf(map.get("dbbm"));
			if(DianBiaoBean.validateRepeat(dbbm)){
			  System.out.println("第" + (rownumber++) + "条,电表编号："+dbbm+" 数据中已存在 ");
			  iterator.remove();
			  continue;
			}
			
			//System.out.println("map-"+map);
			result.add(map);
			setChecked(result);
		}
		return errorMsg.toString();
   }
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	//新方法
	public ArrayList<DianBiaoErrorBean> check1(ArrayList dataList ,String[] columns) throws Exception {
		
		importexceldaoutil imp = new importexceldaoutil();

		ArrayList<DianBiaoErrorBean> dianbiaoerr = new ArrayList<DianBiaoErrorBean>();
		
		StringBuffer errorMsg = new StringBuffer();
		
		DianBiaoBean bean = new DianBiaoBean();
	    dbbmList = bean.getDbbmList();
	    jzIdList = bean.SelectLJID();
	    
		int rownumber = 1;
		int colnumber = 1;
		
		ArrayList<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
			ArrayList<Object> data = (ArrayList<Object>)iterator.next();
			if(columns.length != data.size()){
				errorMsg.append("完整性校验失败");
				
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j = 0; j < data.size(); j++){
				
				// 对应关系
				
				map.put(columns[j].toLowerCase(), data.get(j));
			}
			
			
			//电表编码
			if(map.get("dbbm") == null || "".equals(map.get("dbbm"))){
				
				String Error = "第" +(rownumber++)+ "行, " +(colnumber++)+ "列,电表编码为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//电表名称
			if(map.get("dbname") == null || "".equals(map.get("dbname"))){
				
				String Error = "第" +(rownumber++)+ "行, " +(colnumber++)+ "列,电表名称为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//局站Id为空
			if (map.get("ljid") == null || "".equals(map.get("ljid"))) {
				String Error = "第" +(rownumber++)+ "行, " +(colnumber++)+ "列,局站ID为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//成本中心为空
			if (map.get("cbzxbm") == null || "".equals(map.get("cbzxbm"))) {
				String Error = "第" +(rownumber++)+ "条," +(colnumber++)+ "列,成本中心为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//责任人为空
			if (map.get("zrr") == null || "".equals(map.get("zrr"))) {
				String Error = "第" +(rownumber++)+ "条," +(colnumber++)+ "列,责任人为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//报账人为空
			if (map.get("bzr") == null || "".equals(map.get("bzr"))) {
				String Error = "第" +(rownumber++)+ "条," +(colnumber++)+ "列,报账人为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//单价为空
			if (map.get("danjia") == null || "".equals(map.get("danjia"))) {
				String Error = "第" +(rownumber++)+ "条," +(colnumber++)+ "列,单价为空值！";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//验证分公司是否为数字
			String fgs = String.valueOf(map.get("fgs"));
			if(isNumber(fgs)){
				  String Error = "第" + (rownumber++) + "条,分公司应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
				}
			//验证区县分公司是否为数字
			String qxfgs = String.valueOf(map.get("qxfgs"));
			if(isNumber(qxfgs)){
				  String Error = "第" + (rownumber++) + "条,区县分公司应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证局站ID是否为数字
			String LJID = String.valueOf(map.get("ljid"));
			if(isNumber(LJID)){
				  String Error = "第" + (rownumber++) + "条,局站ID应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证电表最大读数是否为数字
			String maxds = String.valueOf(map.get("maxds"));
			if(isNumber(maxds)){
				  String Error = "第" + (rownumber++) + "条,电表最大读数应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证电表初始读数是否为数字
			String CSDS = String.valueOf(map.get("csds"));
			if(isNumber(CSDS)){
				  String Error = "第" + (rownumber++) + "条,电表初始读数应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证倍率是否为数字
			String BEILV = String.valueOf(map.get("beilv"));
			if(isNumber(BEILV)){
				  String Error = "第" + (rownumber++) + "条,倍率应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证单价是否为数字
			String DANJIA = String.valueOf(map.get("danjia"));
			if(isNumber(DANJIA)){
				  String Error = "第" + (rownumber++) + "条,单价应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证税率是否为数字
			String ZZSL = String.valueOf(map.get("zzsl"));
			if(isNumber(ZZSL)){
				  String Error = "第" + (rownumber++) + "条,税率应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//验证包干金额是否为数字
			String bgje = String.valueOf(map.get("bgje"));
			if(isNumber(bgje)){
				  String Error = "第" + (rownumber++) + "条,包干金额应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			String total_electricity = String.valueOf(map.get("total_electricity"));
			if(isNumber(total_electricity)){
				  String Error = "第" + (rownumber++) + "条,核定电量应为数字";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			
			//=====================数据库校验↓
			//去除重复电表
			String dbbm = String.valueOf(map.get("dbbm"));
			if(isDBBM(dbbm)){
			  String Error = "第" + (rownumber++) + "条,电表编号："+dbbm+" 数据中已存在 ";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//校验局站ID是否存在
			String ljid = String.valueOf(map.get("ljid"));
			if(isNotLJID(ljid)){
			  String Error = "第" + (rownumber++) + "条,"+ljid+" 动环局站ID不存 ";
			  System.out.println(Error);
			  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
			  iterator.remove();
			  continue;
			}
			
			result.add(map);
			setChecked(result);
		}
		return dianbiaoerr;
   }
	//验证是否为数字或小数
	public boolean isNumber(String str) {
		if("".equals(str) || str == null  || " ".equals(str)){
			str = "0";
		}
		String str2 = str.replaceAll(" ", ""); 
	    Pattern pattern = Pattern.compile("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?");  
	    Matcher isNum = pattern.matcher(str2);  
	    if (!isNum.matches()) {  
	        return true;  
	    }else{
	    	return false;  
	    }  
	}
	//验证电表编码是否存在
	public boolean isDBBM(String str) {
		
		boolean bool = dbbmList.contains(str);
		
		return bool;		
	}
	//验证动环局站ID是否存在
	public boolean isNotLJID(String str) {
        boolean isTrue = true;
		if(StringUtils.isNotBlank(str)){
			int jzId = 0;
			try {
				jzId = Double.valueOf(str).intValue();
			} catch (Exception e) {
				System.out.println("from "+str+"(String) to int 失败");
				isTrue = true;
			}
			boolean bool = jzIdList.contains(String.valueOf(jzId));
			if(bool){
				isTrue =  false;
			}else{
				isTrue = true;		
			}
		}
    	return isTrue;
		
		
	}
	
	
	//==============================================================================
   //手工导入↓
	public ArrayList<Map<String, Object>> readExcel(String filename,
			InputStream is, String[] columns) throws Exception {
		if (filename == null || filename.isEmpty()) {
			throw new Exception("导入文档为空");
		} else if (filename.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (filename.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("读取的不是excel文件");
		}
		this.sheet = wb.getSheetAt(0);
		System.out.println("初始化成功 ，开始读取数据...");
		ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			Row row = sheet.getRow(i);
			for (int j = 0; j < columns.length; j++) {
				Cell cell = row.getCell(j);
				map.put(columns[j].toLowerCase(), getValue(cell));
			}
			System.out.println("读取成功[" + i + "] " + map);
			logger.debug("读取成功[" + i + "] " + map);
			dataList.add(map);
		}
		return dataList;
	}

	public ArrayList<Map<String, Object>> readExcel(File file, String[] columns)
			throws FileNotFoundException, Exception {
		return readExcel(file.getName(), new FileInputStream(file), columns);
	}

	/**
	 * 
	 * @param filename 导入文件名称
	 * @param columns 导入数据库列
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Map<String, Object>> readExcel(String filename,
			String[] columns) throws Exception {
		return readExcel(new File(filename), columns);
	}

   /**
    * 字典数据转换 
    * @param list 数据源
    * @param type 字典类型
    * @param name 字典label
    * @return
    */
   @SuppressWarnings("rawtypes")
public String adjust(ArrayList list, String type, String name){
	   String code = name;
	   int count = ((Integer)list.get(0)).intValue();
	   for(int i=count; i<list.size()-1; i+=count){
		  String nameDB = (String)list.get(i + list.indexOf("NAME"));
		  String codeDB = (String)list.get(i + list.indexOf("CODE"));
		  String typeDB = (String)list.get(i + list.indexOf("TYPE"));
		  if(typeDB.equalsIgnoreCase(type)&&(nameDB.equalsIgnoreCase(name)|| nameDB.contains(name))){
			  code = codeDB;
			  break;
		  }
	   }
	   return code;
   }
      
   
  @SuppressWarnings("rawtypes")
public void execute() throws Exception{
	  String[] columns = {
				//"分公司","区县分公司","DBBM","实体编号 ", "LJID","局站名称","全省局站编码","实体名称", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "DFZFLX", "jfzq", "JFFS", "JLDW", "YHBH", "BEILV", "DBQYZT", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "fplx", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE", "ELECTRIC_SUPPLY_WAY", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "ZNDB", "ISDBLX"
			  "fgs","qxfgs","DBBM","实体编号 ", "LJID","局站名称","全省局站编码","实体名称", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "fkfs", "fkzq", "JFFS", "JLDW", "YHBH", "BEILV", "dbzt", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "PJLX", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE_DICT", "gdfs", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "shifou", "isdblx_dict"
	  };
	   
	  String[] dictypes = {"dwjslx","dbyt", "ydlx","fkfs","fkzq","jffs","dbzt","PJLX","CONT_TYPE_DICT","gdfs","isdblx_dict","shifou","ISGLF","shzt"};
	  CommonBean commBean = new CommonBean();
	  ArrayList list = commBean.getSelctOptions(dictypes);
	  System.out.println("所用字典类型 "+list);
	  ArrayList<Map<String, Object>> dataList = readExcel("d:\\电表基础表-高密2-导入.xlsx", columns);
	  //1、遍历导入数据
	  int number=1;
	  DianBiaoBean dianBiaoBean = new DianBiaoBean();
	  for (Iterator<Map<String, Object>> iterator = dataList.iterator(); iterator.hasNext();) {
		  Map<String, Object> map = iterator.next();
		  //2、根据电表编码去除重复电表数据
		  String dbbm = (String)map.get("dbbm");
		  if(DianBiaoBean.validateRepeat(dbbm)){
			  System.out.println("第" + (number++) + "条,电表编号："+dbbm+" 数据中已存在 ");
			  iterator.remove();
			  continue;
		  }
		  if(map.get("cbzxbm") == null || "".equals(map.get("cbzxbm")) ){//成本中心
			  System.out.println("第" + (number++) + "条,电表编号："+dbbm+" 对应成本中心为空! ");
			  //logger.info("第" + (number++) + "条,电表编号："+dbbm+" 对应成本为空 ");
			  continue;
		  }
		  //3、转换字典数据
		  for (int i = 0; i < dictypes.length; i++) {
			  String key = dictypes[i].toLowerCase();
			  String label = (String)map.get(key);
			  if(label !=null && !label.isEmpty()){//字典数据
				  String value = adjust(list, key, label);
				  map.put(key, value);
			  }
		  }
		  //System.out.println("-map" + map);
		   
	  }
	  //4、批量导入数据库中
	  dianBiaoBean.updateBatch(dataList); System.out.println("成功导入"+dataList.size()+"条数据");
	  //5、修改电表名称
	  dianBiaoBean.updateBatchName();
	  //合并步骤4、5
	  //dianBiaoBean.importBatch(dataList);//有问题？？？？？
	  //6、批量插入核算单元
	  AccountingDao dao = new AccountingDao();
	  dao.addAccountingUnitSelCostCenter();
  } 

   @SuppressWarnings("unused")
public static void main(String[] args) throws Exception {
//	   ImportExcel ie = new ImportExcel();
//	   String[] columns = {
//				"分公司","区县分公司","DBBM","实体编号 ", "LJID","局站名称","全省局站编码","实体名称", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "DFZFLX", "jfzq", "JFFS", "JLDW", "YHBH", "BEILV", "DBQYZT", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "fplx", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE", "ELECTRIC_SUPPLY_WAY", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "ZNDB", "ISDBLX"
//	   };
	   //ie.execute();
	   //DianBiaoBean dianBiaoBean = new DianBiaoBean();
	   //dianBiaoBean.updateBatchName();
	   
	  int  jzId = Double.valueOf("58257.0").intValue();
	  System.out.println(jzId);
	   
	   
   }
   
   
}
