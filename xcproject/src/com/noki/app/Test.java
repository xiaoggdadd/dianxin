package com.noki.app;

import java.io.File;

public class Test {
	public static String getCodeValue(String path){
		String recognizeText = "";
		try {
			recognizeText = new OCRHelper().recognizeText(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return recognizeText;
	}
	
}
