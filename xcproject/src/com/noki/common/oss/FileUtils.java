package com.noki.common.oss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * csv文件工具类
 * @author guol
 *
 */
public class FileUtils {

	public static ArrayList<?> readcsv(String pathname) throws IOException {
		return readcsv(new File(pathname));
	}

	public static ArrayList<?> readcsv(File file) throws IOException {
		return readcsv(new FileInputStream(file));
	}

	public static ArrayList<?> readcsv(InputStream ins) throws IOException {
		ArrayList<String> dataList = new ArrayList<String>();
		String line = "";
		String data = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
		while ((line = reader.readLine()) != null) {
			data = line;
			// System.out.println(data);
			dataList.add(data);
		}
		System.out.println("csv表格中所有行数：" + dataList.size());
		return dataList;
	}
}
