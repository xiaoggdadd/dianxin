package com.noki.zwhd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noki.ammeterdegree.javabean.newAmmeterDegreeFormBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class LtdfftUpload extends HttpServlet {
	private static Log log = LogFactory.getLog(WydfftUpload.class.getName());
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg;
		int cg = 0, bcg = 0, zg = 0;
		Vector<Vector<String>> wrongContent = new Vector<Vector<String>>();
		CountForm form = new CountForm();
		Path ppath = new Path();
		HttpSession session = request.getSession();
		String cpath = request.getContextPath();
		String url = cpath + "/web/msgdr.jsp";
		WyManage wyManage = new WyManage();
		response.setContentType(CONTENT_TYPE);
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getRealPath("/web/wyftdfsh/uploadExcels/wydfft");
		System.out.println("------------->"+path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		String fileName_wy_date = "";
		try {
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 如果是普通表单字段
					String name = item.getFieldName();
					String value = item.getString();
					fileName_wy_date = value;
				} else {
					// 文件名
					String fileName = item.getName();
	                ppath.servletInitialize(getServletConfig().getServletContext());
	                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
	                String filedir = dir1 + fileName + ".xls";
					// 文件后缀名
					String fileSuffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					// String[] fileName_wy = fileName.split("物业数据-电费分摊-");
					// if (fileName_wy.length > 0) {
					// 保存文件到本地文件夹
					// fileName_wy_date = fileName_wy[1].replaceAll("."
					// + fileSuffix, "");
					OutputStream out = new FileOutputStream(new File(path,
							fileName));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					// } else {
					// msg = "模版名称错误,请按下载的模版进行修改";
					// }
					log.info("开始解析电费单模版....");
					// 读取文件
					File excel_file = new File(path + "/" + fileName);
					
					FileInputStream input = new FileInputStream(excel_file);
					XSSFWorkbook book = new XSSFWorkbook(input);
					// int sheetNum = book.getNumberOfSheets();
					// boolean hasWydfftExcel = false;

					// for (int a = 0; a < sheetNum; a++) {
					XSSFSheet sheet = book.getSheetAt(0);
					// String sheetName = sheet.getSheetName();
					// if (sheetName.equals("物业数据-电费分摊")) {
					// hasWydfftExcel = true;
					int rowNum = sheet.getLastRowNum();
					List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
					List<Vector<String>> vectors = new ArrayList<Vector<String>>();
					for (int b = 1; b <= rowNum; b++) {
						XSSFRow row = sheet.getRow(b);
						XSSFCell cell_xh = row.getCell(0);
						String xh = getValue(cell_xh);

						System.out.println(xh);

						XSSFCell cell_pc = row.getCell(1);
						String pc = getValue(cell_pc);

						System.out.println(pc);

						XSSFCell cell_yyszt = row.getCell(2);
						String yyszt = getValue(cell_yyszt);

						System.out.println(yyszt);

						XSSFCell cell_qy = row.getCell(3);
						String qy = getValue(cell_qy);

						System.out.println(qy);

						XSSFCell cell_ycqdw = row.getCell(4);
						String ycqdw = getValue(cell_ycqdw);

						System.out.println(ycqdw);

						XSSFCell cell_zdmc = row.getCell(5);
						String zdmc = getValue(cell_zdmc);

						System.out.println(zdmc);

						XSSFCell cell_zdbm = row.getCell(6);
						String zdbm = getValue(cell_zdbm);

						System.out.println(zdbm);

						XSSFCell cell_wldzbm = row.getCell(7);
						String wldzbm = getValue(cell_wldzbm);

						System.out.println(wldzbm);

						XSSFCell cell_dh = row.getCell(8);
						String dh = getValue(cell_dh);

						System.out.println(dh);

						XSSFCell cell_gdfmc = row.getCell(9);
						String gdfmc = getValue(cell_gdfmc);

						System.out.println(gdfmc);

						XSSFCell cell_pjlx = row.getCell(10);
						String pjlx = getValue(cell_pjlx);

						System.out.println(pjlx);

						XSSFCell cell_dbhh = row.getCell(11);
						String dbbm = getValue(cell_dbhh);

						System.out.println(dbbm);

						XSSFCell cell_zzlx = row.getCell(12);
						String zzlx = getValue(cell_zzlx);

						System.out.println(zzlx);


						XSSFCell cell_sccbsj = row.getCell(13);
						String sccbsj = parseExcel(cell_sccbsj);

						System.out.println(sccbsj);

						XSSFCell cell_bccbsj = row.getCell(14);
						String bccbsj = parseExcel(cell_bccbsj);

						System.out.println(bccbsj);
						
						XSSFCell cell_jfqsrq = row.getCell(15);
						String jfqsrq = parseExcel(cell_jfqsrq);

						System.out.println(jfqsrq);
						
						XSSFCell cell_jfzzrq = row.getCell(16);
						String jfzzrq = parseExcel(cell_jfzzrq);

						System.out.println(jfzzrq);

						XSSFCell cell_qm = row.getCell(17);
						String qm = getValue(cell_qm);

						System.out.println(qm);

						XSSFCell cell_zm = row.getCell(18);
						String zm = getValue(cell_zm);

						System.out.println(zm);
						
						XSSFCell cell_sh = row.getCell(19);
						String sh = getValue(cell_sh);

						System.out.println(sh);
						
						XSSFCell cell_jfje = row.getCell(20);
						String jfje = getValue(cell_jfje);

						System.out.println(jfje);
						
						XSSFCell cell_ftbl = row.getCell(21);
						String ftbl = getValue(cell_ftbl);

						System.out.println(ftbl);
						
						XSSFCell cell_ftje = row.getCell(22);
						String ftje = getValue(cell_ftje);

						System.out.println(ftje);
						
						XSSFCell cell_bz = row.getCell(23);
						String bz = getValue(cell_bz);

						System.out.println(bz);
						
						XSSFCell cell_nyglxtid = row.getCell(24);
						String nyglxtid = getValue(cell_nyglxtid);

						System.out.println(nyglxtid);
						
						XSSFCell cell_zzs = row.getCell(25);
						String zzs = getValue(cell_zzs);

						System.out.println(zzs);
						
						XSSFCell cell_szs = row.getCell(26);
						String szs = getValue(cell_szs);

						System.out.println(szs);
						
						Vector<String> vector = new Vector<String>();
						vector.add(xh);
						vector.add(pc);
						vector.add(yyszt);
						vector.add(qy);
						vector.add(ycqdw);
						vector.add(zdmc);
						vector.add(zdbm);
						vector.add(wldzbm);
						vector.add(dh);
						vector.add(gdfmc);
						vector.add(pjlx);
						vector.add(dbbm);
						vector.add(zzlx);
						vector.add(sccbsj);
						vector.add(bccbsj);
						vector.add(jfqsrq);
						vector.add(jfzzrq);
						vector.add(qm);
						vector.add(zm);
						vector.add(sh);
						vector.add(jfje);
						vector.add(ftbl);
						vector.add(ftje);
						vector.add(bz);
						vector.add(nyglxtid);
						vector.add(zzs);
						vector.add(szs);
						vectors.add(vector);

						WydfftBean wydfftBean = new WydfftBean();
						wydfftBean.setRownum(Integer.parseInt(xh));
						wydfftBean.setYEARMONTH(pc);
						wydfftBean.setYYSZT(yyszt);
						wydfftBean.setDSFGS(qy);
						wydfftBean.setZDMC(zdmc);
						wydfftBean.setZDBM(zdbm);
						wydfftBean.setCB_WLDZBMBM(wldzbm);
						wydfftBean.setDH(dh);
						wydfftBean.setGDFMC(gdfmc);
						wydfftBean.setJFPJLX(pjlx);
						wydfftBean.setDbbm(dbbm);
						wydfftBean.setCB_ZZLX(zzlx);
						wydfftBean.setSccbsj(sccbsj);
						wydfftBean.setBccbsj(bccbsj);
						wydfftBean.setJfqsrq(jfqsrq);
						wydfftBean.setJfzzrq(jfzzrq);
						wydfftBean.setQM(qm);
						wydfftBean.setZM(zm);
						wydfftBean.setSh(sh);
						wydfftBean.setJFJE(jfje);
						wydfftBean.setFTBL(ftbl);
						wydfftBean.setFTJE(ftje);
						wydfftBean.setBZ(bz);
						wydfftBean.setCB_NYGLXTID(nyglxtid);
						wydfftBean.setCB_ZZS(zzs);
						wydfftBean.setCB_SZS(szs);
						wydfftList.add(wydfftBean);
					}

					for (int i = 0; i < wydfftList.size(); i++) {
						WydfftBean wydfftBean = wydfftList.get(i);
						String dh = wydfftBean.getDH();
						String yearmonth = wydfftBean.getYEARMONTH();
						String zdbm = wydfftBean.getZDBM();
						String kh = "联通";
						
						WydfftBean dbBean = wyManage.searchWydfft(yearmonth, zdbm,dh, kh);
						if(dbBean==null){
							Vector<String> row = vectors.get(i);
							row.add("系统中没有该条物业信息作为基础或未被区县确认！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						
						//DecimalFormat df1 = new DecimalFormat("#0.0000");
						//DecimalFormat df2 = new DecimalFormat("#0.00");
						String sh = wydfftBean.getSh();
						String qm = wydfftBean.getQM();
						String zm = wydfftBean.getZM();
						if(qm!=null&&!qm.equals("")&&!qm.equals("null")&&!qm.equals("NULL")&&zm!=null&&!zm.equals("")&&!zm.equals("null")&&!zm.equals("NULL")){
							double _qm = 0.00;
							try{
								_qm = Double.parseDouble(qm);
							}catch(Exception e){
								Vector<String> row = vectors.get(i);
								row.add("起码必须为数字！");
								wrongContent.add(row);
								bcg++;
								continue;
							}
									
							double _zm = 0.00;
							try{
								_zm = Double.parseDouble(zm);
							}catch(Exception e){
								Vector<String> row = vectors.get(i);
								row.add("止码必须为数字！");
								wrongContent.add(row);
								bcg++;
								continue;
							}
							
							if(_qm<_zm){
								if(_qm>0){
									if(sh!=null&&!sh.equals("")&&!sh.equals("null")){
										double _sh = 0.00;
										try{
											_sh = Double.parseDouble(sh);
										}catch(Exception e){
											Vector<String> row = vectors.get(i);
											row.add("损耗必须为数字！");
											wrongContent.add(row);
											bcg++;
											continue;
										}
										
										double dl =  _zm-_qm+_sh;
										DecimalFormat df = new DecimalFormat("#0.00");
										dbBean.setDLIANG(df.format(dl));
									}else{
										Vector<String> row = vectors.get(i);
										row.add("损耗不能为空！");
										wrongContent.add(row);
										bcg++;
										continue;
									}
								}else{
									Vector<String> row = vectors.get(i);
									row.add("起码应大于0！");
									wrongContent.add(row);
									bcg++;
									continue;
								}
							}else{
								Vector<String> row = vectors.get(i);
								row.add("起码应小于止码！");
								wrongContent.add(row);
								bcg++;
								continue;
							}
						}else{
							Vector<String> row = vectors.get(i);
							row.add("起止码不能为空！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						String jfqsrq = wydfftBean.getJfqsrq();
						String jfzzrq = wydfftBean.getJfzzrq();
						
						SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
						Date _jfqsrq = null;
						try{
							 _jfqsrq = formate.parse(jfqsrq);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费起始日期必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						Date _jfzzrq = null;
						try{
							_jfzzrq = formate.parse(jfzzrq);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费终止日期必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						if(_jfqsrq.getTime()<=_jfzzrq.getTime()){
							
							
						}else{
							Vector<String> row = vectors.get(i);
							row.add("缴费起始日期应小于等于缴费终止日期！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						String sccbsj = wydfftBean.getSccbsj();
						String bccbsj = wydfftBean.getBccbsj();
						
						Date _sccbsj = null;
						try{
							 _sccbsj = formate.parse(sccbsj);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("上次抄表时间必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						Date _bccbsj = null;
						try{
							_bccbsj = formate.parse(bccbsj);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("本次抄表时间必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						if(_sccbsj.getTime()<=_bccbsj.getTime()){
							
							
						}else{
							Vector<String> row = vectors.get(i);
							row.add("上次抄表时间应小于等于本次抄表时间！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						String jfje = wydfftBean.getJFJE();
						double _jfje = 0.00;
						try{
							_jfje = Double.parseDouble(jfje);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费金额必须为数字！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						SystemManage systemManage = new SystemManage();
						ZhandianBean zhandian =  systemManage.searchZhandianByJcode(zdbm);
						if(zhandian!=null){
							
						}else{
							Vector<String> row = vectors.get(i);
							row.add("系统中没有该站点编码！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						String gdfs = dbBean.getGDFS();
						if(gdfs!=null&&gdfs.equals("直供电")){
							dbBean.setBCDJ(zhandian.getDj());
						}else if(gdfs!=null&&gdfs.equals("转供电")){
							String dliang = dbBean.getDLIANG();
							double _dliang = Double.parseDouble(dliang);
							DecimalFormat df = new DecimalFormat("#0.0000");  
							dbBean.setBCDJ(df.format(_jfje/_dliang));
						}
						
						dbBean.setCB_ZZLX(wydfftBean.getCB_ZZLX());
						dbBean.setBccbsj(wydfftBean.getBccbsj());
						dbBean.setSccbsj(wydfftBean.getSccbsj());
						dbBean.setJfqsrq(wydfftBean.getJfqsrq());
						dbBean.setJfzzrq(wydfftBean.getJfzzrq());
						dbBean.setQM(wydfftBean.getQM());
						dbBean.setZM(wydfftBean.getZM());
						dbBean.setSh(wydfftBean.getSh());
						dbBean.setCB_SZS(wydfftBean.getCB_SZS());
						dbBean.setCB_ZZS(wydfftBean.getCB_ZZS());
						dbBean.setCB_NYGLXTID(wydfftBean.getCB_NYGLXTID());
						dbBean.setZcjjzhbm(wydfftBean.getZcjjzhbm());
						dbBean.setYYSXGZT("已修改");
						
						boolean isSaveDb = wyManage.updateWydfft(dbBean);
						if (!isSaveDb) {
							Vector<String> row = vectors.get(i);
							row.add("入库失败！");
							wrongContent.add(row);
							bcg++;
							continue;
						}else{
							cg++;
						}
					}
					zg = rowNum;
					form.setCg(cg);
					form.setBcg(bcg);
					form.setZg(zg);
					System.out.println("总共多少行：" + rowNum);

					wyManage.saveWydfftHdjl(loginName, fileName_wy_date);
				}
			}
			
			if (!wrongContent.isEmpty()) {
	             msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg() +
	                   " 条！有 " + form.getBcg() + "  条数据未导入！";
	             WriteExcle wr = new WriteExcle();
	             String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
	             wr.Write(wrongContent, loginName + "联通电费单导入不成功的数据.xls", "联通电费单导入不成功的数据",
	                      "联通电费单导入不成功数据", 15, dir2);
	         } else {
	             msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
	         }
			
			session.setAttribute("url", request.getContextPath() + "/web/wyftdfsh/tt_ltdfft_serach.jsp");
	        session.setAttribute("msg", msg);
	        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "联通电费单导入不成功的数据.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(url);
	}
	
	public static String getValue(Cell cell) {
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue());
		}
	}
	
	private String parseExcel(Cell cell) {  
        String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;  
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                result = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                result = format.format(value);  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型  
            result = cell.getRichStringCellValue().toString();  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            result = "";  
        default:  
            result = "";  
            break;  
        }  
        return result;  
    }
}
