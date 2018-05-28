package com.ptac.app.version;

import java.io.InputStream;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;

public class Version {
	private Logger logger = Logger.getLogger(Version.class);
	private String version;
	private static Properties pro = null;
	public String getVersion(){
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader()
					.getResourceAsStream("interface.properties");
			pro.load(is);
			is.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		version = pro.getProperty("version");
		logger.info("系统版本号为:V"+version);
		return version;
	}
}
