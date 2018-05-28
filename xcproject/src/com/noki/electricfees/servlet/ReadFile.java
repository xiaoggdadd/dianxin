package com.noki.electricfees.servlet;

import java.util.Vector;


/**
 * 
 * @author GT
 *
 */
public interface ReadFile {
  public Vector getColumns(String fileName);
  public Vector getContentzd(String fileName);
  public Vector getContent(String fileName);
  public Vector getContenthtd(String fileName);
  public Vector getContentdj(String fileName);
}
