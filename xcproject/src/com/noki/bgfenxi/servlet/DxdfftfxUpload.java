package com.noki.bgfenxi.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import com.noki.bgfenxi.Model.DxdffxBean;
import com.noki.bgfenxi.manage.Dxdffxmanage;
import com.noki.jizhan.daoru.CountForm;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.noki.zwhd.manage.WyManage;

public class DxdfftfxUpload extends HttpServlet {
	private static Log log = LogFactory.getLog(DxdfftfxUpload.class.getName());
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
		//DxDfManage df=new DxDfManage();
		Dxdffxmanage df = new Dxdffxmanage();
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
			// �����ϴ�����ļ�
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// �������ͨ���ֶ�
					String name = item.getFieldName();
					String value = item.getString();
					fileName_wy_date = value;
				} else {
					// �ļ���
					String fileName = item.getName();
					System.out.println("0123456"+fileName);
	                ppath.servletInitialize(getServletConfig().getServletContext());
	                String dir1 = ppath.getPhysicalPath("/indata/", 0); // ������
	                String filedir = dir1 + fileName + ".xls";
					// �ļ���׺��
					String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					// String[] fileName_wy = fileName.split("��ҵ����-��ѷ�̯-");
					// if (fileName_wy.length > 0) {
					// �����ļ��������ļ���
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
					// msg = "ģ�����ƴ���,�밴���ص�ģ������޸�";
					// }
					log.info("��ʼ������ѵ�ģ��....");
					// ��ȡ�ļ�
					File excel_file = new File(path + "/" + fileName);
					System.out.println(excel_file);
					FileInputStream input = new FileInputStream(excel_file);
					XSSFWorkbook book = new XSSFWorkbook(input);
					// int sheetNum = book.getNumberOfSheets();
					// boolean hasWydfftExcel = false;

					// for (int a = 0; a < sheetNum; a++) {
					XSSFSheet sheet = book.getSheetAt(0);
					// String sheetName = sheet.getSheetName();
					// if (sheetName.equals("��ҵ����-��ѷ�̯")) {
					// hasWydfftExcel = true;
					
					int rowNum = sheet.getLastRowNum();
					System.out.println(rowNum);
					List<DxdffxBean> wydfftList = new ArrayList<DxdffxBean>();
					List<Vector<String>> vectors = new ArrayList<Vector<String>>();
					for (int b = 3; b < rowNum; b++) {
						XSSFRow row = sheet.getRow(b);
						
						XSSFCell cell_dw = row.getCell(0);
						String dw = getValue(cell_dw);
						//System.out.println(dw);

						XSSFCell cell_qx = row.getCell(1);
						String qx = getValue(cell_qx);

						//System.out.println(qx);

						XSSFCell cell_zdmc = row.getCell(2);
						String zdmc = getValue(cell_zdmc);

						//System.out.println(zdmc);

						XSSFCell cell_zdbm = row.getCell(3);
						String zdbm = getValue(cell_zdbm);

						//System.out.println(zdbm);
						
						XSSFCell cell_zzlx = row.getCell(4);
						String zzlx = getValue(cell_zzlx);

						XSSFCell cell_gdfs = row.getCell(5);
						String gdfs = getValue(cell_gdfs);

						//System.out.println(gdfs);

						XSSFCell cell_jsfs = row.getCell(6);
						String jsfs = getValue(cell_jsfs);

						//System.out.println(jsfs);

						XSSFCell cell_dh = row.getCell(7);
						String dh = getValue(cell_dh);

						//System.out.println(dh);

						XSSFCell cell_jfqsrq = row.getCell(8);
						String jfqsrq = getValue(cell_jfqsrq);

						//System.out.println(jfqsrq);

						XSSFCell cell_jfzzrq = row.getCell(9);
						String jfzzrq = getValue(cell_jfzzrq);

						//System.out.println(jfzzrq);
						
						XSSFCell cell_ts = row.getCell(10);
						String ts = getValue(cell_ts);
						//System.out.println(ts);
						
						XSSFCell cell_qm = row.getCell(11);
						String qm = getValue(cell_qm);
						
						XSSFCell cell_zm = row.getCell(12);
						String zm = getValue(cell_zm);

						XSSFCell cell_dliang = row.getCell(13);
						String dliang = getValue(cell_dliang);

						//System.out.println(dliang);

						XSSFCell cell_danjia = row.getCell(14);
						String danjia = getValue(cell_danjia);

						//System.out.println(danjia);

						XSSFCell cell_jfje = row.getCell(15);
						String jfje = getValue(cell_jfje);

						//System.out.println(jfje);

						XSSFCell cell_jfrq = row.getCell(16);
						String jfrq = getValue(cell_jfrq);

						//System.out.println(jfrq);

						XSSFCell cell_jfpjlx = row.getCell(17);
						String pjlx = getValue(cell_jfpjlx);

						//System.out.println(pjlx);

						XSSFCell cell_gdfmc = row.getCell(18);
						String gdfmc = getValue(cell_gdfmc);

						//System.out.println(gdfmc);

						XSSFCell cell_kh = row.getCell(19);
						String kh = getValue(cell_kh);

						//System.out.println(kh);
						
						XSSFCell cell_dliu = row.getCell(20);
						String dliu = getValue(cell_dliu);

						XSSFCell cell_ftbl = row.getCell(21);
						String ftbl = getValue(cell_ftbl);

						//System.out.println(ftbl);

						XSSFCell cell_fsyz = row.getCell(22);
						String fsyz = getValue(cell_fsyz);

						//System.out.println(fsyz);

						XSSFCell cell_ftje = row.getCell(23);
						String ftje = getValue(cell_ftje);

						//System.out.println(ftje);
						
						XSSFCell cell_cy = row.getCell(24);
						String cy = getValue(cell_cy);
						
//						XSSFCell cell_dliu = row.getCell(19);
//						String dliu = getValue(cell_dliu);
//
//						System.out.println(dliu);
//						
//						XSSFCell cell_ftbl = row.getCell(20);
//						String ftbl = getValue(cell_ftbl);
//
//						System.out.println(ftbl);
//						
//						XSSFCell cell_fsyz = row.getCell(21);
//						String fsyz = getValue(cell_fsyz);
//
//						System.out.println(fsyz);
//						
//						XSSFCell cell_ftje = row.getCell(22);
//						String ftje = getValue(cell_ftje);
//
//						System.out.println(ftje);
						
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
//						
//						Vector<String> vector = new Vector<String>();
//						vector.add(dw);
//						vector.add(qx);
//						vector.add(zdmc);
//						vector.add(zdbm);
//						vector.add(gdfs);
//						vector.add(jsfs);
//						vector.add(dh);
//						vector.add(lastcbsj);
//						vector.add(thiscbsj);
//						vector.add(dliang);
//						vector.add(danjia);
//						vector.add(jfje);
//						vector.add(jfrq);
//						vector.add(pjlx);
//						vector.add(gdfmc);
//						vector.add(kh);
//						vector.add(ftbl);
//						vector.add(fsyz);
//						vector.add(ftje);
//						vector.add(ftbl);
//						vector.add(fsyz);
//						vector.add(ftje);
//						vectors.add(vector);
						java.text.DecimalFormat def = new DecimalFormat("#.00");
						java.text.DecimalFormat deff = new DecimalFormat("#.0000");
						NumberFormat num = NumberFormat.getPercentInstance(); 
						num.setMaximumFractionDigits(2);
						String bili = num.format(Double.parseDouble(ftbl));
						System.out.print(bili);
						String dl="",chayi="";
						if(dliang!=null&&!dliang.equals("")){							
							try{
								 dl = def.format(Double.parseDouble(dliang.trim()));
							}catch(Exception e){
								dl=dliang;
							}
						}
						if(cy!=null&&!cy.equals("")){
							try{
								chayi=def.format(Double.parseDouble(cy));
							}catch(Exception e){
								chayi=cy;
							}
						}
						String qima="",zhima="",ftjeje="";
						try{
						 qima = def.format(Double.parseDouble(qm));
						}catch(Exception e){
							qima=qm;
						}
					    try{
						 zhima = def.format(Double.parseDouble(zm));
					    }catch(Exception e){
					    	zhima=zm;
					    }
						String jfjeje = def.format(Double.parseDouble(jfje));
						String dj = deff.format(Double.parseDouble(danjia));
						try{
						 ftjeje = def.format(Double.parseDouble(ftje));
						}catch(Exception e){
							ftjeje=ftje;
						}
						
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
						Date qs = sdf.parse(jfqsrq);
						Date zz = sdf.parse(jfzzrq);
						long aa = qs.getTime();
						long bb = zz.getTime();
						long c = bb-aa;
						//String tianshu = (String) (c/(1000*60*60*24));
						
						DxdffxBean dx = new DxdffxBean();
						dx.setDw(dw);
						dx.setQx(qx);
						dx.setZDMC(zdmc);
						dx.setZDBM(zdbm);
						dx.setGDFS(gdfs);
						dx.setJSFS(jsfs);
						dx.setDH(dh);
						dx.setJfqsrq(jfqsrq);
						dx.setJfzzrq(jfzzrq);
						dx.setDLIANG(dl);
						dx.setBCDJ(dj);
						dx.setJFJE(jfjeje);
						dx.setJFRQ(jfrq);
						dx.setJFPJLX(pjlx);
						dx.setGDFMC(gdfmc);
						dx.setKH(kh);
						dx.setFSYZ(fsyz);
						dx.setFTBL(bili);
						dx.setFTJE(ftjeje);
						dx.setZzlx(zzlx);
						//dx.setTs(tianshu);
						dx.setQM(qima);
						dx.setZM(zhima);
						dx.setDLIU(dliu);
						dx.setCY(chayi);
						wydfftList.add(dx);
					}
					
					for (int i = 0; i < wydfftList.size(); i++) {
						DxdffxBean wydfftBean = wydfftList.get(i);	
						DxdffxBean dbBean = new DxdffxBean();
						dbBean.setDw(wydfftBean.getDw());
						dbBean.setQx(wydfftBean.getQx());
						dbBean.setZDMC(wydfftBean.getZDMC());
						dbBean.setZDBM(wydfftBean.getZDBM());
						dbBean.setGDFS(wydfftBean.getGDFS());
						dbBean.setJSFS(wydfftBean.getJSFS());
						dbBean.setDH(wydfftBean.getDH());
						dbBean.setJfqsrq(wydfftBean.getJfqsrq());
						dbBean.setJfzzrq(wydfftBean.getJfzzrq());
						dbBean.setBCDJ(wydfftBean.getBCDJ());
						dbBean.setDLIANG(wydfftBean.getDLIANG());
						dbBean.setJFJE(wydfftBean.getJFJE());
						dbBean.setJFRQ(wydfftBean.getJFRQ());
						dbBean.setJFPJLX(wydfftBean.getJFPJLX());
						dbBean.setGDFMC(wydfftBean.getGDFMC());
						dbBean.setKH(wydfftBean.getKH());
						dbBean.setFTBL(wydfftBean.getFTBL());
						dbBean.setFSYZ(wydfftBean.getFSYZ());
						dbBean.setFTJE(wydfftBean.getFTJE());
						dbBean.setZzlx(wydfftBean.getZzlx());
						dbBean.setDLIU(wydfftBean.getDLIU());
						dbBean.setCY(wydfftBean.getCY());
						dbBean.setQM(wydfftBean.getQM());
						dbBean.setZM(wydfftBean.getZM());
						dbBean.setTs(wydfftBean.getTs());
						//System.out.println(wydfftBean.getTs());
//						
//							dbBean.setDLIU(wydfftBean.getDLIU());
//							dbBean.setFTBL(wydfftBean.getFTBL());
//							dbBean.setFTJE(wydfftBean.getFTJE());
//							dbBean.setFSYZ(wydfftBean.getFSYZ());
//							dbBean.setJfqsrq(wydfftBean.getJfqsrq());
//							dbBean.setJfzzrq(wydfftBean.getJfzzrq());
//							dbBean.setQM(wydfftBean.getQM());
//							dbBean.setZM(wydfftBean.getZM());
//							dbBean.setDLIANG(wydfftBean.getDLIANG());
//		
//							dbBean.setYYSXGZT("���޸�");
						
						boolean isSaveDb = df.adddxfx(dbBean);
						//System.out.println(isSaveDb);
						if (!isSaveDb) {
//							Vector<String> row = vectors.get(i);
//							row.add("���ʧ�ܣ�");
//							wrongContent.add(row);
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
					System.out.println("�ܹ������У�" + rowNum);
					wyManage.saveWydfftHdjl(loginName, fileName_wy_date);				
			}
			
			if (!wrongContent.isEmpty()) {
	             msg = "�� " + form.getZg() + "  �����ɹ����� " + form.getCg() +
	                   " ������ " + form.getBcg() + "  ������δ���룡";
	             WriteExcle wr = new WriteExcle();
	             String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // ������
	             wr.Write(wrongContent, loginName + "���ŵ�ѷ�̯���벻�ɹ�������.xls", "���ŵ�ѷ�̯���벻�ɹ�������",
	                      "����벻�ɹ�����", 15, dir2);
	         } else {
	             msg = "ȫ������ɹ��������� " + form.getCg() + " ����";
	         }			
			session.setAttribute("url", request.getContextPath() + "/web/wyftdfsh/tt_dxdfftqr_serach.jsp");
	        session.setAttribute("msg", msg);
	        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "���ŵ�ѷ�̯���벻�ɹ�������.xls");
			}	
	response.sendRedirect(url);
		}catch (Exception e) {
			e.printStackTrace();}
		
		}
	
	public static String getValue(Cell cell) {
//		if (cell == null) {
//			return "";
//		} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(cell.getBooleanCellValue());
//		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
//			return String.valueOf(cell.getNumericCellValue());
//		}else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")){
//			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			 Date date = cell.getDateCellValue(); 
//			  String a = sdf.format(date);  
//			  return a;
//		}
//		else {
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			return String.valueOf(cell.getStringCellValue());
//		}
		String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// ��������  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// �������ڸ�ʽ��ʱ���ʽ  
                SimpleDateFormat sdf = null;  
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// ����  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            }
//            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//            		result = String.valueOf(cell.getNumericCellValue());
//        	} 
            else if (cell.getCellStyle().getDataFormat() == 58) {  
                // �����Զ������ڸ�ʽ��m��d��(ͨ���жϵ�Ԫ��ĸ�ʽid�����id��ֵ��58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
                result = sdf.format(date);  
            } else {  
            	 double value = cell.getNumericCellValue();  
                 CellStyle style = cell.getCellStyle();  
                 DecimalFormat format = new DecimalFormat();  
                 String temp = style.getDataFormatString();  
                 // ��Ԫ�����óɳ���  
                 if (temp.equals("General")) {  
                     format.applyPattern("#");  
                 }  
                 result = format.format(value);
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String����  
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
