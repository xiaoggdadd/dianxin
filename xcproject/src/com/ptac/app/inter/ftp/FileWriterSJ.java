package com.ptac.app.inter.ftp;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.AuditBean;
import com.ptac.app.inter.bean.SjInterFace;
import com.ptac.app.inter.dao.CheckValiate;
import com.ptac.app.inter.service.SjzcInterfaceDao;
import com.ptac.app.inter.service.SjzcInterfaceDaoImp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

/**
 * 审计接口数据
 * 
 * @author Administrator
 * 
 */
public class FileWriterSJ {
	Logger logger = Logger.getLogger(FileWriterSJ.class);
	private static Properties pro = null;
	private String filePath = System.getProperty("user.dir") + "\\SJZCTemp\\";
	private DateFormat format2 = new SimpleDateFormat("yyyyMM");
	private StringBuffer fileNameEle = new StringBuffer("MY0100");
	private String fileRealPath1 = "";

	public boolean writeToDataEle(List<AuditBean> list, String month) {

		this.fileNameEle.append(month + "000000.AVL");
		File file = new File(this.filePath + this.fileNameEle);
		boolean flag = false;
		this.fileRealPath1 = this.filePath + this.fileNameEle;
		if (!(file.exists()))
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Iterator localIterator = list.iterator(); localIterator
					.hasNext();) {
				Object obj = localIterator.next();
				bw.write(obj.toString());
				bw.write("\r\n");
			}
			bw.flush();
			bw.close();
			flag = true;
		} catch (Exception e) {
			logger.error("数据写入文件失败" + e.getMessage());
		}
		return flag;
	}

	public boolean putToFtp(String fileRealPath, String fileName) {
		boolean flag = false;
		String text = "This is the interface to write into FTP ALL (SJ)";
		String serverip = pro.getProperty("sjftpip");
		int serverport = Integer.parseInt(pro.getProperty("sjftpport"));
		String username = pro.getProperty("sjftpuser");
		String password = pro.getProperty("sjftppasswd");
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
		return flag;
	}

	/**
	 * 审计数据生成
	 * 
	 * @param month
	 * @param btime
	 * @param etime
	 * @return
	 */
	public boolean SjInterface(String month, String btime, String etime) {
		CheckValiate.getInstance().updateSjR(month);
		SjzcInterfaceDao imp = new SjzcInterfaceDaoImp();
		FileWriterSJ util = new FileWriterSJ();
		FileManager fileManager = new FileManager();
		boolean flage = false;// 上传Ftp标志
		boolean flag2 = false;// 本地文件生成标志
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader()
					.getResourceAsStream("interface.properties");
			pro.load(is);
			is.close();
		} catch (Exception e) {
			logger.error("资源数据信息读取失败：" + e.getMessage());
		}

		boolean flag = false;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString();

			if (pro.getProperty("execip").equals(ip)) {
				flag2 = util.writeToDataEle(imp
						.getSjzcData(month, btime, etime), month);
				if (flag2) {
					logger.info("完成审计数据本地待上传文件的生成处理。");
				} else {
					logger.error("审计数据本地待上传文件的生成失败！");
					CheckValiate.getInstance().updateSjU(month);
				}
			}
			if (flag2) {
				flage = util.putToFtp(util.fileRealPath1, util.fileNameEle
						.toString());
				if (flage) {
					SjInterFace face = new SjzcInterfaceDaoImp().getCount(month, btime, etime);
					CheckValiate.getInstance().updateSjS(month,face);
				} else {
					CheckValiate.getInstance().updateSjU(month);
					logger.error("审计数据上传Ftp出现问题，未上传");
				}
				fileManager.deleteFile(util.fileRealPath1);
			}
		} catch (UnknownHostException e) {
			CheckValiate.getInstance().updateSjU(month);
			logger.error("审计数据接口数据异常" + e.getMessage());
		}
		return flag;
	}

	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		// 使用时间 后期要改
		c1.add(Calendar.MONTH, -1);
		c1.add(Calendar.DATE, -2);
		c2.add(Calendar.MONTH, 0);
		c2.add(Calendar.DATE, -2);
		c3.add(Calendar.MONTH, -1);

		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format3 = new SimpleDateFormat("yyyyMM");
		String btime = format2.format(c1.getTime());
		String etime = format2.format(c2.getTime());
		String month = format3.format(c3.getTime());
		System.out.println(btime + "    " + etime + "   " + month);

		new FileWriterSJ().SjInterface(month, btime, etime);
	}
}