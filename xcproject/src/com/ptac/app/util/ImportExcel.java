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
	private int rowIndex = 1; //Ĭ����һ������
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
			throw new Exception("�����ĵ�Ϊ��");
		} else if (filename.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (filename.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("��ȡ�Ĳ���excel�ļ�");
		}
		this.sheet = wb.getSheetAt(0);
		System.out.println("��ʼ���ɹ� ����ʼ��ȡ����...");
		ArrayList<ArrayList<Object>> dataList = new ArrayList<ArrayList<Object>>();
		for (int i = rowIndex; i <= sheet.getLastRowNum(); i++) {
			ArrayList<Object> data = new ArrayList<Object>();
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				data.add(getValue(cell));
			}
			System.out.println("��ȡ�ɹ�[" + i + "] " + data);
			logger.debug("��ȡ�ɹ�[" + i + "] " + data);
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
				errorMsg.append("������У��ʧ��");
				break;
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j = 0; j < data.size(); j++){
				// ��Ӧ��ϵ
				map.put(columns[j].toLowerCase(), data.get(j));
			}
			//������
			if(map.get("dbbm") == null){
				System.out.println("��" +(rownumber++)+ "��, " +(colnumber++)+ "��,������Ϊ��ֵ��");
				iterator.remove();
				continue;
			}
			//��վId
			if (map.get("ljid") == null) {
				System.out.println("��" +(rownumber++)+ "��, " +(colnumber++)+ "��,��վIDΪ��ֵ��");
				iterator.remove();
				continue;
			}
			//�ɱ�����
			if (map.get("cbzxbm") == null || "".equals(map.get("cbzxbm"))) {// �ɱ�����
				System.out.println("��" +(rownumber++)+ "��," +(colnumber++)+ "��,�ɱ�����Ϊ��ֵ��");
				iterator.remove();
				continue;
			}
			//ȥ���ظ����
			String dbbm = String.valueOf(map.get("dbbm"));
			if(DianBiaoBean.validateRepeat(dbbm)){
			  System.out.println("��" + (rownumber++) + "��,����ţ�"+dbbm+" �������Ѵ��� ");
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
	//�·���
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
				errorMsg.append("������У��ʧ��");
				
			}
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			for(int j = 0; j < data.size(); j++){
				
				// ��Ӧ��ϵ
				
				map.put(columns[j].toLowerCase(), data.get(j));
			}
			
			
			//������
			if(map.get("dbbm") == null || "".equals(map.get("dbbm"))){
				
				String Error = "��" +(rownumber++)+ "��, " +(colnumber++)+ "��,������Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//�������
			if(map.get("dbname") == null || "".equals(map.get("dbname"))){
				
				String Error = "��" +(rownumber++)+ "��, " +(colnumber++)+ "��,�������Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//��վIdΪ��
			if (map.get("ljid") == null || "".equals(map.get("ljid"))) {
				String Error = "��" +(rownumber++)+ "��, " +(colnumber++)+ "��,��վIDΪ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//�ɱ�����Ϊ��
			if (map.get("cbzxbm") == null || "".equals(map.get("cbzxbm"))) {
				String Error = "��" +(rownumber++)+ "��," +(colnumber++)+ "��,�ɱ�����Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//������Ϊ��
			if (map.get("zrr") == null || "".equals(map.get("zrr"))) {
				String Error = "��" +(rownumber++)+ "��," +(colnumber++)+ "��,������Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//������Ϊ��
			if (map.get("bzr") == null || "".equals(map.get("bzr"))) {
				String Error = "��" +(rownumber++)+ "��," +(colnumber++)+ "��,������Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//����Ϊ��
			if (map.get("danjia") == null || "".equals(map.get("danjia"))) {
				String Error = "��" +(rownumber++)+ "��," +(colnumber++)+ "��,����Ϊ��ֵ��";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//��֤�ֹ�˾�Ƿ�Ϊ����
			String fgs = String.valueOf(map.get("fgs"));
			if(isNumber(fgs)){
				  String Error = "��" + (rownumber++) + "��,�ֹ�˾ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
				}
			//��֤���طֹ�˾�Ƿ�Ϊ����
			String qxfgs = String.valueOf(map.get("qxfgs"));
			if(isNumber(qxfgs)){
				  String Error = "��" + (rownumber++) + "��,���طֹ�˾ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤��վID�Ƿ�Ϊ����
			String LJID = String.valueOf(map.get("ljid"));
			if(isNumber(LJID)){
				  String Error = "��" + (rownumber++) + "��,��վIDӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤����������Ƿ�Ϊ����
			String maxds = String.valueOf(map.get("maxds"));
			if(isNumber(maxds)){
				  String Error = "��" + (rownumber++) + "��,���������ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤����ʼ�����Ƿ�Ϊ����
			String CSDS = String.valueOf(map.get("csds"));
			if(isNumber(CSDS)){
				  String Error = "��" + (rownumber++) + "��,����ʼ����ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤�����Ƿ�Ϊ����
			String BEILV = String.valueOf(map.get("beilv"));
			if(isNumber(BEILV)){
				  String Error = "��" + (rownumber++) + "��,����ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤�����Ƿ�Ϊ����
			String DANJIA = String.valueOf(map.get("danjia"));
			if(isNumber(DANJIA)){
				  String Error = "��" + (rownumber++) + "��,����ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤˰���Ƿ�Ϊ����
			String ZZSL = String.valueOf(map.get("zzsl"));
			if(isNumber(ZZSL)){
				  String Error = "��" + (rownumber++) + "��,˰��ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			//��֤���ɽ���Ƿ�Ϊ����
			String bgje = String.valueOf(map.get("bgje"));
			if(isNumber(bgje)){
				  String Error = "��" + (rownumber++) + "��,���ɽ��ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			String total_electricity = String.valueOf(map.get("total_electricity"));
			if(isNumber(total_electricity)){
				  String Error = "��" + (rownumber++) + "��,�˶�����ӦΪ����";
				  System.out.println(Error);
				  dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				  iterator.remove();
				  continue;
			}
			
			//=====================���ݿ�У���
			//ȥ���ظ����
			String dbbm = String.valueOf(map.get("dbbm"));
			if(isDBBM(dbbm)){
			  String Error = "��" + (rownumber++) + "��,����ţ�"+dbbm+" �������Ѵ��� ";
				System.out.println(Error);
				dianbiaoerr.add((DianBiaoErrorBean)imp.convertMap2Bean(Error,map,DianBiaoErrorBean.class)) ;
				iterator.remove();
				continue;
			}
			//У���վID�Ƿ����
			String ljid = String.valueOf(map.get("ljid"));
			if(isNotLJID(ljid)){
			  String Error = "��" + (rownumber++) + "��,"+ljid+" ������վID���� ";
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
	//��֤�Ƿ�Ϊ���ֻ�С��
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
	//��֤�������Ƿ����
	public boolean isDBBM(String str) {
		
		boolean bool = dbbmList.contains(str);
		
		return bool;		
	}
	//��֤������վID�Ƿ����
	public boolean isNotLJID(String str) {
        boolean isTrue = true;
		if(StringUtils.isNotBlank(str)){
			int jzId = 0;
			try {
				jzId = Double.valueOf(str).intValue();
			} catch (Exception e) {
				System.out.println("from "+str+"(String) to int ʧ��");
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
   //�ֹ������
	public ArrayList<Map<String, Object>> readExcel(String filename,
			InputStream is, String[] columns) throws Exception {
		if (filename == null || filename.isEmpty()) {
			throw new Exception("�����ĵ�Ϊ��");
		} else if (filename.toLowerCase().endsWith("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (filename.toLowerCase().endsWith("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("��ȡ�Ĳ���excel�ļ�");
		}
		this.sheet = wb.getSheetAt(0);
		System.out.println("��ʼ���ɹ� ����ʼ��ȡ����...");
		ArrayList<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			Row row = sheet.getRow(i);
			for (int j = 0; j < columns.length; j++) {
				Cell cell = row.getCell(j);
				map.put(columns[j].toLowerCase(), getValue(cell));
			}
			System.out.println("��ȡ�ɹ�[" + i + "] " + map);
			logger.debug("��ȡ�ɹ�[" + i + "] " + map);
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
	 * @param filename �����ļ�����
	 * @param columns �������ݿ���
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Map<String, Object>> readExcel(String filename,
			String[] columns) throws Exception {
		return readExcel(new File(filename), columns);
	}

   /**
    * �ֵ�����ת�� 
    * @param list ����Դ
    * @param type �ֵ�����
    * @param name �ֵ�label
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
				//"�ֹ�˾","���طֹ�˾","DBBM","ʵ���� ", "LJID","��վ����","ȫʡ��վ����","ʵ������", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "DFZFLX", "jfzq", "JFFS", "JLDW", "YHBH", "BEILV", "DBQYZT", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "fplx", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE", "ELECTRIC_SUPPLY_WAY", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "ZNDB", "ISDBLX"
			  "fgs","qxfgs","DBBM","ʵ���� ", "LJID","��վ����","ȫʡ��վ����","ʵ������", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "fkfs", "fkzq", "JFFS", "JLDW", "YHBH", "BEILV", "dbzt", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "PJLX", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE_DICT", "gdfs", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "shifou", "isdblx_dict"
	  };
	   
	  String[] dictypes = {"dwjslx","dbyt", "ydlx","fkfs","fkzq","jffs","dbzt","PJLX","CONT_TYPE_DICT","gdfs","isdblx_dict","shifou","ISGLF","shzt"};
	  CommonBean commBean = new CommonBean();
	  ArrayList list = commBean.getSelctOptions(dictypes);
	  System.out.println("�����ֵ����� "+list);
	  ArrayList<Map<String, Object>> dataList = readExcel("d:\\��������-����2-����.xlsx", columns);
	  //1��������������
	  int number=1;
	  DianBiaoBean dianBiaoBean = new DianBiaoBean();
	  for (Iterator<Map<String, Object>> iterator = dataList.iterator(); iterator.hasNext();) {
		  Map<String, Object> map = iterator.next();
		  //2�����ݵ�����ȥ���ظ��������
		  String dbbm = (String)map.get("dbbm");
		  if(DianBiaoBean.validateRepeat(dbbm)){
			  System.out.println("��" + (number++) + "��,����ţ�"+dbbm+" �������Ѵ��� ");
			  iterator.remove();
			  continue;
		  }
		  if(map.get("cbzxbm") == null || "".equals(map.get("cbzxbm")) ){//�ɱ�����
			  System.out.println("��" + (number++) + "��,����ţ�"+dbbm+" ��Ӧ�ɱ�����Ϊ��! ");
			  //logger.info("��" + (number++) + "��,����ţ�"+dbbm+" ��Ӧ�ɱ�Ϊ�� ");
			  continue;
		  }
		  //3��ת���ֵ�����
		  for (int i = 0; i < dictypes.length; i++) {
			  String key = dictypes[i].toLowerCase();
			  String label = (String)map.get(key);
			  if(label !=null && !label.isEmpty()){//�ֵ�����
				  String value = adjust(list, key, label);
				  map.put(key, value);
			  }
		  }
		  //System.out.println("-map" + map);
		   
	  }
	  //4�������������ݿ���
	  dianBiaoBean.updateBatch(dataList); System.out.println("�ɹ�����"+dataList.size()+"������");
	  //5���޸ĵ������
	  dianBiaoBean.updateBatchName();
	  //�ϲ�����4��5
	  //dianBiaoBean.importBatch(dataList);//�����⣿��������
	  //6������������㵥Ԫ
	  AccountingDao dao = new AccountingDao();
	  dao.addAccountingUnitSelCostCenter();
  } 

   @SuppressWarnings("unused")
public static void main(String[] args) throws Exception {
//	   ImportExcel ie = new ImportExcel();
//	   String[] columns = {
//				"�ֹ�˾","���طֹ�˾","DBBM","ʵ���� ", "LJID","��վ����","ȫʡ��վ����","ʵ������", "maxds", "CSDS", "dwjslx","CBZX","CBZXBM", "DBYT", "ISGLF", "GLBM", "ZRR", "BZR", "YDSX", "YDLX", "DFZFLX", "jfzq", "JFFS", "JLDW", "YHBH", "BEILV", "DBQYZT", "bgje", "DANJIA", "gysmc", "gysbm", "skfmc", "yhzh", "ssyh", "khyh", "zhsss", "zhssshi", "memo", "CSLRR", "SHZT", "fplx", "ZZSL", "IS_CONT", "CONT_ID", "CONT_TYPE", "ELECTRIC_SUPPLY_WAY", "PRODUCTION_PROP", "TOTAL_ELECTRICITY", "ZNDB", "ISDBLX"
//	   };
	   //ie.execute();
	   //DianBiaoBean dianBiaoBean = new DianBiaoBean();
	   //dianBiaoBean.updateBatchName();
	   
	  int  jzId = Double.valueOf("58257.0").intValue();
	  System.out.println(jzId);
	   
	   
   }
   
   
}
