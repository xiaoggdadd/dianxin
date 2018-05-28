package com.noki.jizhan.daoru;

import java.util.Vector;

/**
 * <p>Title: 定义一个接口  其中 两个必须实现的方法 获取列 和获取 表格内容 </p>
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
public interface ReadFile_ZD {

    public Vector getColumns(String fileName);

    public Vector getContent(String fileName);

}
