package com.noki.mobi.up;

import java.util.Vector;

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
public interface ReadFile {
  public Vector getColumns(String fileName);

  public Vector getContent(String fileName);
}
