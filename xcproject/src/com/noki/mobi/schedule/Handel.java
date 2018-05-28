package com.noki.mobi.schedule;

import java.util.*;


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
public class Handel {
    private String btime;
    private String etime;
    public Handel(String btime, String etime) {
        this.btime = btime;
        this.etime = etime;
    }

    public String beginHandel() {

        HandelBean bean = new HandelBean();
        String msg = bean.handeljx(btime,etime);
        return msg;
    }


    public void setBtime(String btime) {
        this.btime = btime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getBtime() {
        return btime;
    }

    public String getEtime() {
        return etime;
    }
}
