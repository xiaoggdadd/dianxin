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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.DfManageYd;
import com.noki.zwhd.manage.DxDfManage;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;
import com.noki.zwhd.servlet.WydfftUpload;

public class DxdfftUpload extends HttpServlet {
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
		DxDfManage df=new DxDfManage();
		response.setContentType(CONTENT_TYPE);
		String loginName = (String) request.getSession().getAttribute("loginName");
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
					System.out.println("0123456"+fileName);
	                ppath.servletInitialize(getServletConfig().getServletContext());
	                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
	                String filedir = dir1 + fileName + ".xls";
					// 文件后缀名
					String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					// String[] fileName_wy = fileName.split("物业数据-电费分摊-");
					// if (fileName_wy.length > 0) {
					// 保存文件到本地文件夹
					// fileName_wy_date = fileName_wy[1].replaceAll("."
					// + fileSuffix, "");
					OutputStream out = new FileOutputStream(new File(path,fileName));
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
					System.out.println(excel_file);
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
					System.out.println(rowNum);
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

						XSSFCell cell_qrzt = row.getCell(2);
						String qrzt = getValue(cell_qrzt);

						System.out.println(qrzt);

						XSSFCell cell_dw = row.getCell(3);
						String dw = getValue(cell_dw);

						System.out.println(dw);

						XSSFCell cell_qx = row.getCell(4);
						String qx = getValue(cell_qx);

						System.out.println(qx);

						XSSFCell cell_zdmc = row.getCell(5);
						String zdmc = getValue(cell_zdmc);

						System.out.println(zdmc);

						XSSFCell cell_zdbm = row.getCell(6);
						String zdbm = getValue(cell_zdbm);

						System.out.println(zdbm);

						XSSFCell cell_gdfs = row.getCell(7);
						String gdfs = getValue(cell_gdfs);

						System.out.println(gdfs);

						XSSFCell cell_jsfs = row.getCell(8);
						String jsfs = getValue(cell_jsfs);

						System.out.println(jsfs);

						XSSFCell cell_dh = row.getCell(9);
						String dh = getValue(cell_dh);

						System.out.println(dh);

						XSSFCell cell_qm = row.getCell(10);
						String qm = getValue(cell_qm);

						System.out.println(qm);

						XSSFCell cell_zm = row.getCell(11);
						String zm = getValue(cell_zm);

						System.out.println(zm);

						XSSFCell cell_dliang = row.getCell(12);
						String dliang = getValue(cell_dliang);

						System.out.println(dliang);

						XSSFCell cell_danjia = row.getCell(13);
						String danjia = getValue(cell_danjia);

						System.out.println(danjia);

						XSSFCell cell_jfje = row.getCell(14);
						String jfje = getValue(cell_jfje);

						System.out.println(jfje);

						XSSFCell cell_jfrq = row.getCell(15);
						String jfrq = getValue(cell_jfrq);

						System.out.println(jfrq);

						XSSFCell cell_pjlx = row.getCell(16);
						String pjlx = getValue(cell_pjlx);

						System.out.println(pjlx);

						XSSFCell cell_gdfmc = row.getCell(17);
						String gdfmc = getValue(cell_gdfmc);

						System.out.println(gdfmc);

						XSSFCell cell_kh = row.getCell(18);
						String kh = getValue(cell_kh);

						System.out.println(kh);
						
						XSSFCell cell_dliu = row.getCell(19);
						String dliu = getValue(cell_dliu);

						System.out.println(dliu);
						
						XSSFCell cell_ftbl = row.getCell(20);
						String ftbl = getValue(cell_ftbl);

						System.out.println(ftbl);
						
						XSSFCell cell_fsyz = row.getCell(21);
						String fsyz = getValue(cell_fsyz);

						System.out.println(fsyz);
						
						XSSFCell cell_ftje = row.getCell(22);
						String ftje = getValue(cell_ftje);

						System.out.println(ftje);
						
//						XSSFCell cell_sfyz = row.getCell(23);
//						String sfyz = getValue(cell_sfyz);
//
//						System.out.println(sfyz);
						
//						XSSFCell cell_ftje = row.getCell(24);
//						String ftje = getValue(cell_ftje);
//
//						System.out.println(ftje);
//						
//						XSSFCell cell_pc = row.getCell(25);
//						String pc = getValue(cell_pc);
//
//						System.out.println(pc);
						
						Vector<String> vector = new Vector<String>();
						vector.add(xh);
						vector.add(pc);
						vector.add(qrzt);
						vector.add(dw);
						vector.add(qx);
						vector.add(zdmc);
						vector.add(zdbm);
						vector.add(gdfs);
						vector.add(jsfs);
						vector.add(dh);
						vector.add(qm);
						vector.add(zm);
						vector.add(dliang);
						vector.add(danjia);
						vector.add(jfje);
						vector.add(jfrq);
						vector.add(pjlx);
						vector.add(gdfmc);
						vector.add(kh);
						vector.add(dliu);
						vector.add(ftbl);
						vector.add(fsyz);
						vector.add(ftje);
						vectors.add(vector);

						WydfftBean zhandian = new WydfftBean();
						zhandian.setRownum(Integer.parseInt(xh));
						zhandian.setYEARMONTH(pc);
						zhandian.setYYSZT(qrzt);
						zhandian.setDSFGS(dw);
						zhandian.setQX(qx);
						zhandian.setZDMC(zdmc);
						zhandian.setZDBM(zdbm);
						zhandian.setGDFS(gdfs);
						zhandian.setJSFS(jsfs);
						zhandian.setDH(dh);
						zhandian.setQM(qm);
						zhandian.setZM(zm);
						zhandian.setDLIANG(dliang);
						zhandian.setDFDJ(danjia);
						zhandian.setJFJE(jfje);
						zhandian.setJFRQ(jfrq);
						zhandian.setJFPJLX(pjlx);
						zhandian.setGDFMC(gdfmc);
						zhandian.setKH(kh);
						zhandian.setDLIU(dliu);
						zhandian.setFTBL(ftbl);
						zhandian.setFSYZ(fsyz);
						zhandian.setFTJE(ftje);
						wydfftList.add(zhandian);
					}
					for (int i = 0; i < wydfftList.size(); i++) {
						WydfftBean wydfftBean = wydfftList.get(i);
						String dh = wydfftBean.getDH();
						String yearmonth = wydfftBean.getYEARMONTH();
						String zdbm = wydfftBean.getZDBM();						
						String kh = "电信";
						
						WydfftBean dbBean=df.searchWydfft(yearmonth, zdbm, dh, kh);
						if(dbBean==null){
							Vector<String> row = vectors.get(i);
							row.add("系统中没有该条物业信息作为基础或未被区县确认！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						String jfqsrq = wydfftBean.getJfqsrq();
						String jfzzrq = wydfftBean.getJfzzrq();
						String qm = wydfftBean.getQM();
						String zm = wydfftBean.getZM();
						String dliang = wydfftBean.getDLIANG();
					//	String dliu = wydfftBean.getDLIU();
						if(qm!=null&&!qm.equals("")&&!qm.equals("null")&&!qm.equals("NULL")&&zm!=null&&!zm.equals("")&&!zm.equals("null")&&!zm.equals("NULL")){
							double _qm = 0.00;
							try{
								_qm = Double.parseDouble(qm);
							}catch(Exception e){
								Vector<String> row = vectors.get(i);
								row.add("起码必须为数字！");
								wrongContent.add(row);
								//bcg++;
								//continue;
							}
									
							double _zm = 0.00;
							try{
								_zm = Double.parseDouble(zm);
							}catch(Exception e){
								Vector<String> row = vectors.get(i);
								row.add("止码必须为数字！");
								wrongContent.add(row);
								//bcg++;
								//continue;
							}
							if(_qm<_zm){
								if(_qm>0){
									
							Vector<String> row = vectors.get(i);
							row.add("起码应大于0！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						}else{
							
							Vector<String> row = vectors.get(i);
							row.add("起码应小于止码！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						}else{
							//System.out.println("123321");
							Vector<String> row = vectors.get(i);
							row.add("起止码不能为空！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
						Date _jfqsrq = null;
						try{
							 _jfqsrq = formate.parse(jfqsrq);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费起始日期必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						Date _jfzzrq = null;
						try{
							System.out.println("123321");
							_jfzzrq = formate.parse(jfzzrq);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费终止日期必须为yyyy-MM-dd的日期格式！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						
//						if(_jfqsrq.getTime()<=_jfzzrq.getTime()){
//							
//							
//						}else{
//							Vector<String> row = vectors.get(i);
//							row.add("缴费起始日期应小于等于缴费终止日期！");
//							wrongContent.add(row);
//							bcg++;
//							//continue;
//						}
						String jfje = wydfftBean.getJFJE();
						double _jfje = 0.00;
						try{
							_jfje = Double.parseDouble(jfje);
						}catch(Exception e){
							Vector<String> row = vectors.get(i);
							row.add("缴费金额必须为数字！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						
						SystemManage systemManage = new SystemManage();
						ZhandianBean zhandian =  systemManage.searchZhandianByJcode(zdbm);
						if(zhandian!=null){
							
						}else{
							Vector<String> row = vectors.get(i);
							row.add("系统中没有该站点编码！");
							wrongContent.add(row);
							//bcg++;
							//continue;
						}
						String gdfs = dbBean.getGDFS();
						DecimalFormat ddf = new DecimalFormat("#0.0000"); 
						double _dliang = Double.parseDouble(dliang);
						if(gdfs!=null&&gdfs.equals("直供电")){
							dbBean.setBCDJ(zhandian.getDj());
						}else if(gdfs!=null&&gdfs.equals("转供电")){
							dbBean.setBCDJ(ddf.format(_jfje/_dliang));	
						}
						int danjia = Integer.parseInt(ddf.format(_jfje/_dliang));
						if(gdfs.equals("直供电")&&danjia>1){
							Vector<String> row = vectors.get(i);
							row.add("直供电的单价必须小于1！");
							wrongContent.add(row);
						}
						
							dbBean.setDLIU(wydfftBean.getDLIU());
							dbBean.setFTBL(wydfftBean.getFTBL());
							dbBean.setFTJE(wydfftBean.getFTJE());
							dbBean.setFSYZ(wydfftBean.getFSYZ());
							dbBean.setJfqsrq(wydfftBean.getJfqsrq());
							dbBean.setJfzzrq(wydfftBean.getJfzzrq());
							dbBean.setQM(wydfftBean.getQM());
							dbBean.setZM(wydfftBean.getZM());
							dbBean.setDLIANG(wydfftBean.getDLIANG());	
							dbBean.setYYSXGZT("已修改");
						
						System.out.println("1111111");
						boolean isSaveDb = df.updateWydfftdx(dbBean);
						//System.out.println(isSaveDb);
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
	             wr.Write(wrongContent, loginName + "电信电费分摊导入不成功的数据.xls", "电信电费分摊导入不成功的数据",
	                      "电表导入不成功数据", 15, dir2);
	         } else {
	             msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
	         }
			
			session.setAttribute("url", request.getContextPath() + "/web/bgfenxi/dxdfftfx_serach.jsp");
	        session.setAttribute("msg", msg);
	        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "电信电费分摊导入不成功的数据.xls");
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
}
