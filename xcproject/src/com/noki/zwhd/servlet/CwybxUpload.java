package com.noki.zwhd.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.noki.mobi.common.Account;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.CwManage;
import com.noki.zwhd.manage.SystemManage;
import com.noki.zwhd.manage.WyManage;
import com.noki.zwhd.model.CwybxBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class CwybxUpload extends HttpServlet {
	private static Log log = LogFactory.getLog(CwybxUpload.class.getName());
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private String msg;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cg = 0, bcg = 0, zg = 0;
		Vector<Vector<String>> wrongContent = new Vector<Vector<String>>();
		CountForm form = new CountForm();
		HttpSession session = request.getSession();
		Path ppath = new Path();
		response.setContentType(CONTENT_TYPE);
		String loginName = (String) request.getSession().getAttribute(
				"loginName");
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getRealPath("/web/wyftdfsh/uploadExcels/cwybx");
		System.out.println("------------->"+path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		String sendPath = request.getContextPath();
		String sendUrl = sendPath + "/web/msgdr.jsp";
		CwManage cwManage = new CwManage();
		String fileName_cw_date = "";
		try {
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 如果是普通表单字段
					String name = item.getFieldName();
					String value = item.getString();
					fileName_cw_date = value;
				} else {
					// 文件名
					String fileName = item.getName();
					ppath.servletInitialize(getServletConfig().getServletContext());
					String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
		            String filedir = dir1 + fileName + ".xls";
					// 文件后缀名
		            String fileSuffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					// 保存文件到本地文件夹
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
					log.info("开始解析电费核对模版....");
					// 读取文件
					File excel_file = new File(path + "/" + fileName);
					FileInputStream input = new FileInputStream(excel_file);
					XSSFWorkbook book = new XSSFWorkbook(input);
					int sheetNum = book.getNumberOfSheets();
					boolean hasWydfftExcel = false;
					XSSFSheet sheet = book.getSheetAt(0);
					String sheetName = sheet.getSheetName();
					hasWydfftExcel = true;
					int rowNum = sheet.getLastRowNum();
					List<CwybxBean> cwybxList = new ArrayList<CwybxBean>();
					List<Vector<String>> vectors = new ArrayList<Vector<String>>();
					for (int b = 1; b <= rowNum; b++) {
						XSSFRow row = sheet.getRow(b);
						XSSFCell cell_rq = row.getCell(0);
						String rq = getValue(cell_rq);

						System.out.println(rq);

						XSSFCell cell_bxlx = row.getCell(1);
						String bxlx = getValue(cell_bxlx);

						System.out.println(bxlx);

						XSSFCell cell_djph = row.getCell(2);
						String djph = getValue(cell_djph);

						System.out.println(djph);

						XSSFCell cell_zy = row.getCell(3);
						String zy = getValue(cell_zy);

						System.out.println(zy);

						XSSFCell cell_djzt = row.getCell(4);
						String djzt = getValue(cell_djzt);

						System.out.println(djzt);

						XSSFCell cell_bm = row.getCell(5);
						String bm = getValue(cell_bm);

						System.out.println(bm);

						XSSFCell cell_sfje = row.getCell(6);
						String sfje = getValue(cell_sfje);

						System.out.println(sfje);

						XSSFCell cell_fphsje = row.getCell(7);
						String fphsje = getValue(cell_fphsje);

						System.out.println(fphsje);

						XSSFCell cell_fpbhsje = row.getCell(8);
						String fpbhsje = getValue(cell_fpbhsje);

						System.out.println(fpbhsje);

						XSSFCell cell_jsdh = row.getCell(9);
						String jsdh = getValue(cell_jsdh);

						System.out.println(jsdh);

						XSSFCell cell_zdmc = row.getCell(10);
						String zdmc = getValue(cell_zdmc);

						System.out.println(zdmc);

						XSSFCell cell_zdbh = row.getCell(11);
						String zdbh = getValue(cell_zdbh);

						System.out.println(zdbh);

						CwybxBean cwybxBean = new CwybxBean();
						cwybxBean.setBM(bm);
						cwybxBean.setBXLX(bxlx);
						cwybxBean.setDJPH(djph);
						cwybxBean.setDJZT(djzt);
						cwybxBean.setFPBHSJE(fpbhsje);
						cwybxBean.setFPHSJE(fphsje);
						cwybxBean.setJSDH(jsdh);
						cwybxBean.setRQ(rq);
						cwybxBean.setSFJE(sfje);
						cwybxBean.setZDBH(zdbh);
						cwybxBean.setZDMC(zdmc);
						cwybxBean.setZY(zy);
						cwybxBean.setYEARMONTH(fileName_cw_date);
						cwybxList.add(cwybxBean);

						Vector<String> vector = new Vector<String>();
						vector.add(rq);
						vector.add(bxlx);
						vector.add(djph);
						vector.add(zy);
						vector.add(djzt);
						vector.add(bm);
						vector.add(sfje);
						vector.add(fphsje);
						vector.add(fpbhsje);
						vector.add(jsdh);
						vector.add(zdmc);
						vector.add(zdbh);
						vectors.add(vector);
					}

					for (int i = 0; i < cwybxList.size(); i++) {
						CwybxBean cwybxBean = cwybxList.get(i);
						String yearmonth = cwybxBean.getYEARMONTH();
						String zdbh = cwybxBean.getZDBH();
						String djph = cwybxBean.getDJPH();
						String jsdh = cwybxBean.getJSDH();
						String fphsje = cwybxBean.getFPHSJE();
						String fpbhsje = cwybxBean.getFPBHSJE();
						
						SystemManage systemManage = new SystemManage();
						
						ZhandianBean zhandian = systemManage.searchJzNameByJzCode(zdbh);
						
						if(zdbh==null||zdbh.equals("")){
							continue;
						}
						
						if(zhandian==null){
							Vector<String> row = vectors.get(i);
							row.add("系统中没有录入该站点！");
							wrongContent.add(row);
							bcg++;
							continue;
						}else{
							cwybxBean.setZDMC(zhandian.getJZNAME());
						}
						
						/**
						 * 重复校验
						 * **/
						boolean isWydfftCf = cwManage.isCwybxCf(yearmonth,zdbh, jsdh,djph, fphsje,
								fpbhsje);
						if (!isWydfftCf) {
							Vector<String> row = vectors.get(i);
							row.add("重复导入！");
							wrongContent.add(row);
							bcg++;
							continue;
						}
						
						boolean isSaveDb = cwManage.saveCwybx(cwybxBean);
						if (!isSaveDb) {
							Vector<String> row = vectors.get(i);
							row.add("入库失败！");
							wrongContent.add(row);
							bcg++;
							continue;
						} else {
							cg++;
						}
					}

					zg = rowNum;
					form.setCg(cg);
					form.setBcg(bcg);
					form.setZg(zg);
					System.out.println("总共多少行：" + rowNum);
					
					cwManage.saveCwybxHdjl(loginName, fileName_cw_date);
				}
			}

			if (!wrongContent.isEmpty()) {
				msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg()
						+ " 条！有 " + form.getBcg() + "  条数据未导入！";
				WriteExcle wr = new WriteExcle();
				String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
				wr.Write(wrongContent, loginName + "电表导入不成功的数据.xls",
						"电表导入不成功的数据", "电表导入不成功数据", 15, dir2);
			} else {
				msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
			}
			session.setAttribute("url", request.getContextPath() + "/web/wyftdfsh/tt_cwybx_serach.jsp");
	        session.setAttribute("msg", msg);
	        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "电表导入不成功的数据.xls");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(sendUrl);
	}

	public static String getValue(Cell cell) {
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
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
