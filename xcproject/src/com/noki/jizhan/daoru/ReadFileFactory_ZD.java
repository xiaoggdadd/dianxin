package com.noki.jizhan.daoru;


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
public class ReadFileFactory_ZD {
    public ReadFileFactory_ZD() {
    }
    public static ReadFile_ZD getReadFile(String filename) throws Exception {
       ReadFile_ZD reader = null;
       int index = filename.lastIndexOf(".");
       if (index == -1) {
           return null;
       }
       String type = filename.substring(index + 1).trim().toLowerCase();
       if ("xls".equals(type)) {
           reader = new ReadXLS_ZD();
       }
       /*else if("csv".equals(type)){
       reader = new ReadCSV();
           }*/
      return reader;

   }

}
