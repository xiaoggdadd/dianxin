package com.noki.htnygl.dlsjbd.servlce;

import java.util.ArrayList;

import com.noki.htnygl.dlsjbd.javabean.dlsjbdBean;
import com.noki.htnygl.dlsjbd.javabean.zdDlsjbdBean;

/**
 * @author xuzehua
 * */

public interface dlsjbdService {
	/**
	 * 按照查询条件 （自然月） 查询，通过dao返回的数据进行差额，比例的计算，对某一地市下的所有区县进行显示(城市必选)
	 * 
	 *  查询完 对数据进行 柱状图处理，可以借鉴 tool包下的 ，例子文件
	 * */
    public ArrayList<dlsjbdBean> getDlsjbd(String shi,String quxian,String startmonth,String endmonth);
    
    /**
     * 在以上的 以上dao 返回数据的基础上， 对某一个区县 下的站点进行显示：zdDlsjbdBean   (选择了某个区县后触发)
     * 
     * 
     * */
    public ArrayList<zdDlsjbdBean> getZdDlsjbd(String shi,String quxian, String startmonth,String endmonth);
}
