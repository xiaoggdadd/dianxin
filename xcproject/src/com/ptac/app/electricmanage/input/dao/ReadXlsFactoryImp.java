package com.ptac.app.electricmanage.input.dao;

import java.util.Vector;

import com.noki.electricfees.servlet.ReadXLS;

public class ReadXlsFactoryImp implements ReadXlsFactory {
	ReadXlsFactoryImp(String filename){
		int index = filename.lastIndexOf(".");
	    if(index==-1){
	    }
	    String type = filename.substring(index+1).trim().toLowerCase();
	    if("xls".equals(type)){
	    }
	}
	@Override
	public Vector getColumns(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getContent(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getContentdj(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getContenthtd(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
