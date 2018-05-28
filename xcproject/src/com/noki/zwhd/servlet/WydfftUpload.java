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

import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class WydfftUpload extends HttpServlet {
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
					log.info("开始解析电费核对模版....");
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
						XSSFCell cell_sfgs = row.getCell(0);
						String sfgs = getValue(cell_sfgs);

						System.out.println(sfgs);

						XSSFCell cell_dsfgs = row.getCell(1);
						String dsfgs = getValue(cell_dsfgs);

						System.out.println(dsfgs);

						XSSFCell cell_zdmc = row.getCell(2);
						String zdmc = getValue(cell_zdmc);

						System.out.println(zdmc);

						XSSFCell cell_zdbm = row.getCell(3);
						String zdbm = getValue(cell_zdbm);

						System.out.println(zdbm);

						XSSFCell cell_dh = row.getCell(4);
						String dh = getValue(cell_dh);

						System.out.println(dh);

						XSSFCell cell_zq = row.getCell(5);
						String zq = getValue(cell_zq);

						System.out.println(zq);

						XSSFCell cell_jfje = row.getCell(6);
						String jfje = getValue(cell_jfje);

						System.out.println(jfje);

						XSSFCell cell_jfrq = row.getCell(7);
						String jfrq = parseExcel(cell_jfrq);

						System.out.println(jfrq);

						XSSFCell cell_xzbs = row.getCell(8);
						String xzbs = getValue(cell_xzbs);

						System.out.println(xzbs);

						XSSFCell cell_jfpjlx = row.getCell(9);
						String jfpjlx = getValue(cell_jfpjlx);

						System.out.println(jfpjlx);

						XSSFCell cell_gdfmc = row.getCell(10);
						String gdfmc = getValue(cell_gdfmc);

						System.out.println(gdfmc);

						XSSFCell cell_kh = row.getCell(11);
						String kh = getValue(cell_kh);

						System.out.println(kh);

						XSSFCell cell_ftbl = row.getCell(12);
						String ftbl = getValue(cell_ftbl);

						System.out.println(ftbl);

						XSSFCell cell_fsyz = row.getCell(13);
						String fsyz = getValue(cell_fsyz);

						System.out.println(fsyz);

						XSSFCell cell_ftje = row.getCell(14);
						String ftje = getValue(cell_ftje);

						System.out.println(ftje);

						XSSFCell cell_kplx = row.getCell(15);
						String kplx = getValue(cell_kplx);

						System.out.println(kplx);

						XSSFCell cell_tzhje = row.getCell(16);
						String tzhje = getValue(cell_tzhje);

						System.out.println(tzhje);

						XSSFCell cell_tzyy = row.getCell(17);
						String tzyy = getValue(cell_tzyy);

						System.out.println(tzyy);

						XSSFCell cell_tzr = row.getCell(18);
						String tzr = getValue(cell_tzr);

						System.out.println(tzr);
						
						Vector<String> vector = new Vector<String>();
						vector.add(sfgs);
						vector.add(dsfgs);
						vector.add(zdmc);
						vector.add(zdbm);
						vector.add(dh);
						vector.add(zq);
						vector.add(jfje);
						vector.add(jfrq);
						vector.add(xzbs);
						vector.add(jfpjlx);
						vector.add(gdfmc);
						vector.add(kh);
						vector.add(ftbl);
						vector.add(fsyz);
						vector.add(ftje);
						vector.add(kplx);
						vectors.add(vector);

						WydfftBean wydfftBean = new WydfftBean();

						wydfftBean.setDH(dh);
						wydfftBean.setDSFGS(dsfgs);
						wydfftBean.setFSYZ(fsyz);
						wydfftBean.setFTBL(ftbl);
						wydfftBean.setFTJE(ftje);
						wydfftBean.setGDFMC(gdfmc);
						wydfftBean.setJFJE(jfje);
						wydfftBean.setJFPJLX(jfpjlx);
						wydfftBean.setJFRQ(jfrq);
						wydfftBean.setKH(kh);
						wydfftBean.setSFGS(sfgs);
						wydfftBean.setTZHJE(tzhje);
						wydfftBean.setTZR(tzr);
						wydfftBean.setTZYY(tzyy);
						wydfftBean.setXZBS(xzbs);
						wydfftBean.setZDBM(zdbm);
						wydfftBean.setZDMC(zdmc);
						wydfftBean.setZQ(zq);
						wydfftBean.setYEARMONTH(fileName_wy_date);
						wydfftBean.setKPLX(kplx);

						wydfftList.add(wydfftBean);
					}

					for (int i = 0; i < wydfftList.size(); i++) {
						WydfftBean wydfftBean = wydfftList.get(i);
						String zdbm = wydfftBean.getZDBM();
						String dh = wydfftBean.getDH();
						String kh = wydfftBean.getKH();
						String jfje = wydfftBean.getJFJE();
						String jfrq = wydfftBean.getJFRQ();
						String sfgs = wydfftBean.getSFGS();
						String yearmonth = wydfftBean.getYEARMONTH();
						String fsyz = wydfftBean.getFSYZ();
						String ftbl = wydfftBean.getFTBL();
						String ftje = wydfftBean.getFTJE();
						String zq = wydfftBean.getZQ();
						
						SystemManage systemManage = new SystemManage();
						
						ZhandianBean zhandian = systemManage.searchJzNameByJzCode(zdbm);
						
						if(zhandian==null){
							Vector<String> row = vectors.get(i);
							row.add("系统中没有录入该站点！");
							wrongContent.add(row);
							bcg++;
							continue;
						}else{
							String[] zqs = zq.split("-");
							SimpleDateFormat md = new SimpleDateFormat("yyyy.mm.dd");
							SimpleDateFormat ymd = new SimpleDateFormat("yyyy-mm-dd");
							String myzq = ymd.format(md.parse("2016."+zqs[0].trim())).toString()+";"+ymd.format(md.parse("2016."+zqs[1].trim())).toString();
							wydfftBean.setZQ(myzq);
							wydfftBean.setZDMC(zhandian.getJZNAME());
							wydfftBean.setSFGS(zhandian.getSHENG());
							wydfftBean.setDSFGS(zhandian.getSHI());
						}
						
						if(!(0<Double.parseDouble(ftbl)&&Double.parseDouble(ftbl)<=100)){
							Vector<String> row = vectors.get(i);
							row.add("分摊比例超出范围！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						DecimalFormat df = new DecimalFormat("#.00");  
						String _ftje = df.format(Double.parseDouble(jfje)*(1+Double.parseDouble(fsyz))*(1/Double.parseDouble(ftbl)));
						if(ftje.equals(_ftje)){
							Vector<String> row = vectors.get(i);
							row.add("分摊金额不对！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						/**
						 * 重复校验
						 * **/ 
						boolean isWydfftCf = wyManage.isWydfftCf(yearmonth,zdbm, dh, kh,
								jfje, jfrq);
						if (!isWydfftCf) {
							Vector<String> row = vectors.get(i);
							row.add("重复导入！");
							wrongContent.add(row);
							bcg++;
							continue;
						}

						boolean isSaveDb = wyManage.saveWydfft(wydfftBean);
						if (isSaveDb) {
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
	             wr.Write(wrongContent, loginName + "电表导入不成功的数据.xls", "电表导入不成功的数据",
	                      "电表导入不成功数据", 15, dir2);
	         } else {
	             msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
	         }
			
			session.setAttribute("url", request.getContextPath() + "/web/wyftdfsh/tt_wydfft_serach.jsp");
	        session.setAttribute("msg", msg);
	        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "电表导入不成功的数据.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(url);
//		String sendPath = request.getContextPath();
//		String sendUrl = sendPath + "/web/msgdr.jsp";
//
//		Account account = (Account) session.getAttribute("account");
//		session.setAttribute("msg", msg);
//		session.setAttribute("url", sendPath
//				+ "/web/wyftdfsh/tt_wydfft_input.jsp");
//		// session.setAttribute("wfile",
//		// sendPath + "/wrongdata/" + account.getAccountName()
//		// + "自报电用户号电费单导入不成功的数据.xls");
//
//		response.sendRedirect(sendUrl);
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
