package com.noki.biaogan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
import com.noki.biaogan.BiaoganManage;
import com.noki.biaogan.CBUserManage;
import com.noki.biaogan.model.AmmertdegreeBean;
import com.noki.biaogan.model.CbUserBean;
import com.noki.chaobiaorenyuan.servlet.ImgCompress;

@SuppressWarnings("serial")
public class CBUserSendMessageServlet extends HttpServlet {
	/**
	 * 两个经纬度之间的距离的计算分方式的参数
	 */
	// private static double EARTH_RADIUS = 6378.137;
	private static double EARTH_RADIUS = 6371.393;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	private static Log log = LogFactory.getLog(CBUserSendMessageServlet.class
			.getName());
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private double blhdl;


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String resturnMessage = "";
		session.setAttribute("value", "");
		response.setContentType(CONTENT_TYPE);
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getRealPath("/TTMobil_Web/temp_image");
		System.out.println("------------->" + path);
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		String dbid = "";
		String dlValue = "0";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String cbyDatetime = sdf.format(new Date());
		String lastDlValue = "0";
		String lastCbyDatetime = "";
		String jfxx = "";
		String dbch = "";
		String zdjing = "";
		String zdwei = "";
		String dlongitude = "";
		String dlatitude = "";
		String longitude = "";
		String latitude = "";
		double twopathlength = 0;
		String beilv = "";
		String ELECTRICCURRENTTYPE = "";
		String DANJIAOLD = "1";
		@SuppressWarnings("unused")
		String zduserid = "";
		@SuppressWarnings("unused")
		String zduseriddb = "";
		@SuppressWarnings("unused")
		int panduandian = 1;
		boolean weizhipanduan = false;
		String userdbid = null;
		String createdate = null;
		String userid = null;
		 boolean setflag = false;
		List<String> fileNames = new ArrayList<String>();

		try {
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					log.info(name + "====" + value);
					if (name.equals("jzid")) {
					} else if (name.equals("dbid")) {
						dbid = value;
					} else if (name.equals("jfxx")) {
						jfxx = value;
					} else if (name.equals("dlValue")) {
						dlValue = value;
					} else if (name.equals("lastDlValue")) {
						lastDlValue = value;
					} else if (name.equals("lastCBTime")) {
						lastCbyDatetime = value;
					} else if (name.equals("dbch")) {
						dbch = value;
					} else if (name.equals("longitude")) {
						longitude = value;
					} else if (name.equals("latitude")) {
						latitude = value;
					} else if (name.equals("zduseriddb")) {
						zduseriddb = value;
					} else if (name.equals("zduserid")) {
						zduserid = value;
					} else if (name.equals("userdbid")) {
						userdbid = value;
					} else if (name.equals("zdwei")) {
						zdwei = value;
					} else if (name.equals("zdjing")) {
						zdjing = value;
					} else if (name.equals("createdate")) {
						createdate = value;
					}else if (name .equals("userid")){
						userid = value;
					}
				}
			}

			for (FileItem item : list) {
				if (!item.isFormField()) {

					String fileName = item.getName();
					String fileSuffix = fileName.substring(
							fileName.lastIndexOf(".") + 1, fileName.length());
					System.out.println("--------------->" + fileName + ""
							+ fileSuffix);
					if (fileName != null && !fileName.equals("")) {
						if (fileSuffix.equals("jpg")
								|| fileSuffix.equals("jpeg")) {
							CBUserManage cbuser = new CBUserManage();
							String _file_path = cbuser.searchZdByUserurl();// request.getRealPath("/");
							if (_file_path != null && _file_path.equals("")) {
								resturnMessage = "没有设置图片存放位置";
								String requestPath = request.getContextPath();
								session.setAttribute("value", resturnMessage);
								response.sendRedirect(requestPath
										+ "/appweb/xccb.jsp");
							}
							String file_path = _file_path + "\\" + dbid + "\\";
							String file_name = UUID.randomUUID().toString()
									+ "." + fileSuffix;
							System.out.println("图片取出来的地址" + file_path);
							File file = new File(file_path);
							if (!file.exists() && !file.isDirectory()) {
								file.mkdir();
							} else {

							}

							OutputStream out = new FileOutputStream(new File(
									file_path, file_name));
							InputStream in = item.getInputStream();
							int length = 0;
							byte[] buf = new byte[1024];
							while ((length = in.read(buf)) != -1) {
								out.write(buf, 0, length);
							}
							in.close();
							out.close();

							log.info(file_path + "\\" + file_name);

							// fileNames.add(file_path + "\\" + file_name);
							fileNames.add(file_name);
							ImgCompress imgCom = new ImgCompress(file_path
									+ "\\" + file_name);
							imgCom.resizeFix(file_path + "\\" + file_name, 400,
									400);
							log.info("结束：" + new Date().toLocaleString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 后台判断传值

		BiaoganManage biaoganManage = new BiaoganManage();
		List<AmmertdegreeBean> lastDbdl = biaoganManage
				.searchLastDbdl(userdbid);

		log.info("[电表表的集合]" + lastDbdl);

		for (int i = 0; i < lastDbdl.size(); i++) {
			beilv = lastDbdl.get(i).getBEILV();
			DANJIAOLD = lastDbdl.get(i).getDANJIAOLD();
			dlongitude = lastDbdl.get(i).getLongitude();
			dlatitude = lastDbdl.get(i).getDlatitude();
			ELECTRICCURRENTTYPE = lastDbdl.get(i).getELECTRICCURRENTTYPE();

		}
		System.out.print("查出来的倍率" + beilv);
		System.out.print("查出来的DANJIAOLD" + DANJIAOLD);
		System.out.print("查出来的dlongitude" + dlongitude);
		//新的判断
		if(dlongitude == null||"".equals(dlongitude)){
			if(zdjing == null || "".equals(zdjing)){
				if(longitude == null||"".equals(longitude)||longitude=="undefined"||"undefined".equals(longitude)){
					
				}else{
						CBUserManage cbuser = new CBUserManage();
						AmmertdegreeBean ammertset1 = new AmmertdegreeBean();
						ammertset1.setDBID(dbid);
						ammertset1.setDlongitude(longitude);
						ammertset1.setDlatitude(latitude);
						setflag = cbuser.saveSetValue(ammertset1);
					}
			}else if(longitude == null||"".equals(longitude)||longitude=="undefined"||"undefined".equals(longitude)){
				
			}else{
				twopathlength = GetDistance(Double.parseDouble(latitude),
						 Double.parseDouble(longitude),
						 Double.parseDouble(zdwei), Double.parseDouble(zdjing));
				if(twopathlength<500){
					CBUserManage cbuser = new CBUserManage();
					AmmertdegreeBean ammertset1 = new AmmertdegreeBean();
					ammertset1.setDBID(dbid);
					ammertset1.setDlongitude(zdjing);
					ammertset1.setDlatitude(zdwei);
					setflag = cbuser.saveSetValue(ammertset1);
				}
			}
		}else if (longitude == null||"".equals(longitude)||longitude=="undefined"||"undefined".equals(longitude)){
			
		}else{
			twopathlength = GetDistance(Double.parseDouble(latitude),
					 Double.parseDouble(longitude),
					 Double.parseDouble(dlatitude), Double.parseDouble(dlongitude));
			if(twopathlength<500){
				CBUserManage cbuser = new CBUserManage();
				AmmertdegreeBean ammertset1 = new AmmertdegreeBean();
				ammertset1.setDBID(dbid);
				ammertset1.setDlongitude(dlongitude);
				ammertset1.setDlatitude(dlatitude);
				setflag = cbuser.saveSetValue(ammertset1);
			}
		}
		@SuppressWarnings("unused")
		CbUserBean user = (CbUserBean) session.getAttribute("loginUser");
		String thisdate = cbyDatetime.trim();
		String lastdate = lastCbyDatetime.trim();
		int tianshu = differentDays(stringToDate(lastdate),
				stringToDate(thisdate));

		System.out.println(dlValue);
		System.out.println(lastDlValue);
		System.out.println("倍率是" + beilv);
		double a = ComputeUtil.sub(Double.parseDouble(dlValue),
				Double.parseDouble(lastDlValue));
		if (beilv != null) {
			@SuppressWarnings("unused")
			double blhdl = ComputeUtil.mul(Double.parseDouble("1"), a);
		} else {
			@SuppressWarnings("unused")
			double blhdl = ComputeUtil.mul(Double.parseDouble(beilv), a);
		}
		AmmertdegreeBean ammertdegree = new AmmertdegreeBean();
		ammertdegree.setDIANBIAOID(dbid);
		ammertdegree.setDANJIAOLD(DANJIAOLD);
		ammertdegree.setELECTRICCURRENTTYPE(ELECTRICCURRENTTYPE);
		ammertdegree.setSTARTTIME(lastdate);
		ammertdegree.setENDTIME(thisdate);
		ammertdegree.setSQDS(lastDlValue);
		ammertdegree.setBQDS(dlValue);
		ammertdegree.setBEILV(beilv);
		Date day = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM");
		String createtime = date.format(day);
		ammertdegree.setCREATEDATE(createtime);
		ammertdegree.setDAYNUM(tianshu);
		ammertdegree.setDIANLIANG(Double.toString(blhdl));

		if (fileNames.size() == 1) {
			ammertdegree.setDBIMAGEPATH1(fileNames.get(0));
			System.out.println("保存一张");
		}

		if (fileNames.size() == 2) {
			ammertdegree.setDBIMAGEPATH1(fileNames.get(0));
			ammertdegree.setDBIMAGEPATH2(fileNames.get(1));
			System.out.println("保存二张");
		}

		if (fileNames.size() == 3) {
			ammertdegree.setDBIMAGEPATH1(fileNames.get(0));
			ammertdegree.setDBIMAGEPATH2(fileNames.get(1));
			ammertdegree.setDBIMAGEPATH3(fileNames.get(2));
			System.out.println("保存三张");
		}

		ammertdegree.setROOMINFO(jfxx);
		//
		// ammertdegree.setISLD(isLd);
		ammertdegree.setJfxx(jfxx);
		ammertdegree.setDbch(dbch);

		CBUserManage cbuser = new CBUserManage();
		// 判断月份是否为同一月份
		// 未放开暂时没有限制月份
		boolean yuefen = cbuser.XiangChaYiYue_new(createdate, thisdate);
		if(yuefen){
		if(1==1){
			boolean flag = cbuser.saveCbValue(ammertdegree,userid);
			if (flag){
				resturnMessage = "保存成功";
			}else{
				resturnMessage = "保存失败";
			}
		}else{
			resturnMessage = "保存失败,请确定当前位置";
		}
		}else{
			resturnMessage = "保存失败,当月已报账";
		}
		
		String requestPath = request.getContextPath();
		session.setAttribute("value", resturnMessage);
		response.sendRedirect(requestPath + "/appweb/xccb.jsp");
	}

	// date转换方法
	public static Date stringToDate(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(source);
		} catch (Exception e) {
		}
		return date;
	}

	// date判断方法
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return (timeDistance + (day2 - day1)) + 1;
		} else // 不同年
		{
			return (day2 - day1) + 1;
		}
	}

	/*
	 * double的乘法运算
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/*
	 * double的减法
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 经纬度计算的方法体
	 * 
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}
}
