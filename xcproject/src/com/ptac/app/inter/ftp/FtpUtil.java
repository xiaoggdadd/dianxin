package com.ptac.app.inter.ftp;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class FtpUtil {
	Logger logger = Logger.getLogger(FtpUtil.class);
	private FTPClient ftpClient = null;
	private String server;
	private int port;
	private String userName;
	private String userPassword;

	/**
	 * @param server
	 * @param port
	 * @param userName
	 * @param userPassword
	 */
	public FtpUtil(String server, int port, String userName, String userPassword) {
		this.server = server;
		this.port = port;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean open() {
		if (ftpClient != null && ftpClient.isConnected()) {
			return true;
		}
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(this.server, this.port);
			ftpClient.login(this.userName, this.userPassword);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				this.close();
				logger.info("FTP server refused connection");
				System.exit(1);
			}
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			return true;
		} catch (Exception ex) {
			this.close();
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 */
	public void close() {
		try {
			if (ftpClient != null && ftpClient.isConnected())
				ftpClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Close Server Success :" + this.server + ";port:"
				+ this.port);
	}

	/**
	 * @param ftpPath
	 * @return
	 */
	public boolean mkDir(String ftpPath) {
		if (!ftpClient.isConnected()) {
			return false;
		}
		try {
			char[] chars = ftpPath.toCharArray();
			StringBuffer sbStr = new StringBuffer(256);
			for (int i = 0; i < chars.length; i++) {
				if ('\\' == chars[i]) {
					sbStr.append('/');
				} else {
					sbStr.append(chars[i]);
				}
			}
			ftpPath = sbStr.toString();
			Log.info("ftpPath:" + ftpPath);
			if (ftpPath.indexOf('/') == -1) {
				ftpClient
						.makeDirectory(new String(ftpPath.getBytes(), "utf-8"));
				ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),
						"utf-8"));
			} else {
				String[] paths = ftpPath.split("/");
				for (int i = 0; i < paths.length; i++) {
					ftpClient.makeDirectory(new String(paths[i].getBytes(),
							"utf-8"));
					ftpClient.changeWorkingDirectory(new String(paths[i]
							.getBytes(), "utf-8"));
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param localPathAndFileName
	 * @param ftpFileName
	 * @param ftpDirectory
	 * @throws Exception
	 */
	public boolean put(String localDirectoryAndFileName, String ftpFileName,
			String ftpDirectory) {
		if (!ftpClient.isConnected()) {
			return false;
		}
		boolean flag = false;
		if (ftpClient != null) {
			File srcFile = new File(localDirectoryAndFileName);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(srcFile);
				this.mkDir(ftpDirectory);
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				flag = ftpClient.storeFile(new String(ftpFileName.getBytes(),
						"utf-8"), fis);
			} catch (Exception e) {
				this.close();
				e.printStackTrace();
				return false;
			} finally {
				IOUtils.closeQuietly(fis);
			}
		}
		logger.info("success put file " + localDirectoryAndFileName + " to "
				+ ftpDirectory + ftpFileName);
		return flag;
	}
}
