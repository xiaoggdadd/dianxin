package com.noki.mobi.cx;

import com.noki.database.DataBase;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class CXCommon {
    public CXCommon() {
    }
    public String getDianBiaoId(String shi, String qx, String xq,DataBase db) {
        StringBuffer dbid = new StringBuffer("0");
        StringBuffer sql = new StringBuffer();
        if (shi.equals("0")) {
            sql.append("");
        } else if (qx.equals("0")) {

        } else if(xq.equals("0")){

        }
        return dbid.toString();
    }

}
