package com.noki.mobi.up;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ReadFileFactory {
  public ReadFileFactory() {
  }
  public static ReadFile getReadFile(String filename) throws Exception{
    ReadFile reader = null;
    int index = filename.lastIndexOf(".");
    if(index==-1){
      return null;
    }
    String type = filename.substring(index+1).trim().toLowerCase();
    if("xls".equals(type)){
      reader = new ReadXLS();
    }/*else if("csv".equals(type)){
      reader = new ReadCSV();
    }*/
    return reader;

  }

}
