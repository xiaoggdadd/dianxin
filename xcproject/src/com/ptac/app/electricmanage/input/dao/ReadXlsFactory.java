package com.ptac.app.electricmanage.input.dao;

import java.util.Vector;

public interface ReadXlsFactory {
	public Vector getColumns(String fileName);

	public Vector getContent(String fileName);

	public Vector getContenthtd(String fileName);

	public Vector getContentdj(String fileName);
}
