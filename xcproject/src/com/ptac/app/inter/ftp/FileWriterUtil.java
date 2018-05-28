package com.ptac.app.inter.ftp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.KdgcElectrictypeBean;
import com.ptac.app.inter.bean.KdgcZhandianBean;
import com.ptac.app.inter.bean.SjInterFace;
import com.ptac.app.inter.dao.CheckValiate;
import com.ptac.app.inter.service.KdgcInterfaceDaoImp;

/**
 * 
 * @author Administrator
 * 
 */
public class FileWriterUtil {
	private static Properties pro = null;
	Logger logger = Logger.getLogger(FileWriterUtil.class);
	private DateFormat format2 = new SimpleDateFormat("yyyyMMddhhmmss");

	private String filePath = System.getProperty("user.dir") + "\\KDGCTemp\\";
	private String fileNameZhanDian = "NYGLCM01_" + format2.format(new Date())
			+ "_001.data";
	private String fileNameEle = "NYGLHD01_" + format2.format(new Date())
			+ "_001.data";

	private String fileRealPath1 = "";
	private String fileRealPath2 = "";

	/**
	 * @param list
	 * @return
	 */
	public boolean writeToDataZhandian(List<KdgcZhandianBean> list) {
		String text = "This is the interface to write into writeToDataZhandian Data(KDGC)";
		File fp = new File(filePath);
		File file = new File(filePath + fileNameZhanDian);
		boolean flag = false;
		fileRealPath1 = filePath + fileNameZhanDian;
		if (!fp.exists()) {
			try {
				fp.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("KDGC url is not create ok!");
			}
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Object obj : list) {
				bw.write(obj.toString());
				bw.write("\r\n");
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		flag = true;
		logger.info(text);
		return flag;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public boolean writeToDataEle(List<KdgcElectrictypeBean> list) {
		String text = "This is the interface to write into writeToDataEle Data(KDGC)";

		File file = new File(filePath + fileNameEle);
		boolean flag = false;
		fileRealPath2 = filePath + fileNameEle;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Object obj : list) {
				bw.write(obj.toString());
				bw.write("\r\n");
			}

			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		flag = true;
		logger.info(text);
		return flag;
	}

	/**
	 * 
	 * @param fileRealPath
	 * @param fileName
	 * @return
	 */
	public boolean putToFtp(String fileRealPath, String fileName) {
		boolean flag = false;
		String text = "This is the interface to write into FTP ALL (KDGC)";
		String serverip = pro.getProperty("kdftpip");
		int serverport = Integer.parseInt(pro.getProperty("kdftpport"));
		String username = pro.getProperty("kdftpuser");
		String password = pro.getProperty("kdftppasswd");
		FtpUtil f = new FtpUtil(serverip, serverport, username, password);
		try {
			if (f.open()) {
				f.put(fileRealPath, fileName, "");
				f.close();
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		logger.info(text);
		return flag;
	}

	/**
	 * 
	 * @param inter_month
	 * @return
	 * @throws ParseException 
	 */
	public boolean KdgcInterface(String inter_month) throws ParseException {
		String month = new StringBuffer(inter_month).deleteCharAt(4).toString();
		CheckValiate.getInstance().updateKdR(month);
		KdgcInterfaceDaoImp imp = new KdgcInterfaceDaoImp();
		FileWriterUtil util = new FileWriterUtil();
		FileManager fileManager = new FileManager();
		
		InetAddress addr;
		boolean flag = false;

		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader()
					.getResourceAsStream("interface.properties");
			pro.load(is);
			is.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		try {
			addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();
			if (pro.getProperty("execip").equals(ip)) {
				boolean flag1 = util.writeToDataZhandian(imp
						.getZhandianInterface());
				boolean flag2 = util.writeToDataEle(imp
						.getElectrictypeInterface(inter_month));
				if (!flag1) {
					logger.error("科大接口站点部分本地待上传数据生成异常");
				}
				if (!flag2) {
					logger.error("科大接口电费部分本地待上传数据生成异常");
				}

				if (flag1 && flag2) {
					if (util
							.putToFtp(util.fileRealPath1, util.fileNameZhanDian)
							&& util.putToFtp(util.fileRealPath2,
									util.fileNameEle)) {
						SjInterFace face = new KdgcInterfaceDaoImp().getCountKd(inter_month);
						CheckValiate.getInstance().updateKdS(month,face);
						logger.info("科大接口数据上传Ftp服务器");
						if (fileManager.deleteFile(util.fileRealPath1)
								&& fileManager.deleteFile(util.fileRealPath2)) {
							logger.info("科大接口数据本地临时信息删除成功。");
						}

					} else {
						logger.error("科大接口数据生成异常未上传成功。");
						CheckValiate.getInstance().updateKdU(month);
					}
				}
			}
		} catch (UnknownHostException e) {
			CheckValiate.getInstance().updateKdU(month);
			logger.error("科大接口数据生成失败" + e.getMessage());
		}
		return flag;
	}

	public static void main(String[] args) {
		InetAddress addr;
		try {
			String a = InetAddress.getLocalHost().getHostAddress().toString();
			

			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -1);
			new FileWriterUtil().KdgcInterface("2015-01");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
