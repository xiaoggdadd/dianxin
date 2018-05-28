package com.noki.common.oss;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <![CDATA[*]]>
 * @author guol
 *
 */
public class CDataAdapter extends XmlAdapter<String, String> {

	private static final String CDATA_END = "]]>";
	private static final String CDATA_BEGIN = "<![CDATA[";

	@Override
	public String marshal(String s) throws Exception {
		return CDATA_BEGIN + s + CDATA_END;
	}

	@Override
	public String unmarshal(String s) throws Exception {
		if (s.startsWith(CDATA_BEGIN) && s.endsWith(CDATA_END)) {
			s = s.substring(CDATA_BEGIN.length(),
					s.length() - CDATA_END.length());
		}
		return s;
	}

}
