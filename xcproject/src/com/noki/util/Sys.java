package com.noki.util;

import java.io.IOException;
import java.util.Properties;

public class Sys {

	static public Properties loadPropertiesResource(String aResourcePath) throws IOException {
		try {
			ClassLoader classLoader = Sys.class.getClassLoader();
			Properties properties = new Properties();
			properties.load(classLoader.getResourceAsStream(aResourcePath));
			return properties;
		} catch (Throwable t) {
			throw new IOException("路径" + aResourcePath + "载入配置文件失败");
		}
	}
}
