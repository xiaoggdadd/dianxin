package com.noki.mobi.schedule;

import java.util.TimerTask;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import java.text.SimpleDateFormat;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.text.DecimalFormat;
import com.noki.util.CTime;
/**
 * <p>Title:电量自动MYSQl读取计划 </p>
 *
 * <p>Description: 建立电量自动读取计划  Timer  </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: ptac</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MysqlTask extends TimerTask {
    public MysqlTask() {  
    }

    private String nowtime = "";
    private String nowDay = "";
    private String nowHour = "";
    private String preSday = "";// 日期
    //private String pre2Sday = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    public void run() {
        System.out.println("mySQl_timer run== "+CTime.formatWholeDate());
        nowtime = CTime.formatDate();
        nowDay = nowtime.substring(0, 8);
        nowHour = nowtime.substring(8, 10);
        if (Integer.parseInt(nowHour) <= 1) { //零点到一点之间执行任务
            DataBase db = null;
            MysqlDB mysqldb = null;
            DbLog log = new DbLog();
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DATE, -1);
            Date perday = calendar.getTime();
            //calendar.add(calendar.DATE, -1);
            //Date pre2day = calendar.getTime();
            preSday = sdfk.format(perday);
            // pre2Sday = sdfk.format(pre2day);

            StringBuffer st = new StringBuffer();
            st.append( "select distinct(concat(netpoint_name,'-',netpoint_id)) from t_electric where ");
            st.append(" UNIX_TIMESTAMP(gain_time)>UNIX_TIMESTAMP('" + preSday +
                      " 00:00:00')");
            st.append(" and UNIX_TIMESTAMP(gain_time)<=UNIX_TIMESTAMP('" + preSday +
                      " 23:59:59') and ceck_flag='0'");

            System.out.println(st.toString()+"MYsqlTask执行了！！！！！！！！！！！！！！！！");
            String msg = "";
            int k = 0;
            db = new DataBase();
            mysqldb = new MysqlDB();
            try {
                db.connectDb();
                mysqldb.connectDb();
                ResultSet rs = mysqldb.queryAll(st.toString());
                StringBuffer sub = new StringBuffer();
                String ftime = "";
                String btime = "";
                double fdata = 0;
                double bdata = 0;
                double chae = 0;
                String dbid = "",mysqlname="";
                while (rs.next()) {
                    mysqlname = rs.getString(1);
                    System.out.println(mysqlname+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    msg = "解析局站名称和局站编号为 " + mysqlname + "，" + preSday + " 的数据失败！";//默认打印的消息！成功会替换的
                    sub.setLength(0);

                    sub.append(
                            "select all_code,gain_time from t_electric where concat(netpoint_name,'-',netpoint_id)='" +
                            mysqlname + "'");
                    sub.append(
                            " and UNIX_TIMESTAMP(gain_time)<UNIX_TIMESTAMP('" +
                            preSday + " 00:00:00') and ceck_flag='0' order by serial_no desc");

                    System.out.println(sub.toString()+"mysqlTask读取总电量和采集时间！！");
                    ResultSet rsp = mysqldb.queryAll(sub.toString());
                    if (rsp.next()) {
                        ftime = rsp.getString("gain_time").substring(0, 10);
                        fdata = rsp.getDouble("all_code");
                        System.out.println(ftime+"%%%"+fdata+"昨天前的总电量和采集时间！！！");
                    } else {
                        ftime = preSday;
                        fdata = 0;
                    }
                    sub.setLength(0);

                    sub.append(
                            "select all_code,gain_time from t_electric where concat(netpoint_name,'-',netpoint_id)='" +
                            mysqlname + "'");
                    sub.append(
                            " and  UNIX_TIMESTAMP(gain_time)>UNIX_TIMESTAMP('" +
                            preSday + " 00:00:00') and ceck_flag='0' order by serial_no desc");

                    System.out.println(sub.toString());
                    rsp = mysqldb.queryAll(sub.toString());
                    if (rsp.next()) {
                        btime = rsp.getString("gain_time").substring(0, 10);
                        bdata = rsp.getDouble("all_code");
                        System.out.println(btime+"%%%"+bdata+"昨天的总电量和采集时间！！！");
                    } else {
                        btime = preSday;
                        bdata = 0;
                    }
                    chae = bdata - fdata;
                    DecimalFormat df = new DecimalFormat(".##");
                    sub.setLength(0);
                    sub.append("insert into AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,ACTUALDEGREE,ZDCB)");
                    
                 //   添加电量 数据源从哪选取  电表ID怎样确定   
                    sub.append(" select dbid,'" + fdata + "','" + bdata +
                               "','" + ftime + "','" + btime + "','" +
                               df.format(chae) + "','1'");
                    sub.append(" from dianbiao where (netpoint_name||'-'||netpoint_id)='"+mysqlname+"'");
                    
                    //这个语句本身有问题 字段数据库中没有 (netpoint_name||'-'||netpoint_id)怎样选择
                    
                    //滨州MYSQL接口设计 不能确定电表ID   只有局站   ID和Name
                    
             /*       参数名称	参数说明	是否必填	参数类型（长度）
//                    serial_no	工单流水号(自增长)	是	number
//                    area_name	地市名称	                                是        Varchar2(40)
//                    org_name	区域名称（一级区域）	是	Varchar2(40)
//                    towns	          乡镇名称（二级区域）	是	Varchar2(40)
//                    netponit_name	局站名称              	是	Varchar2(40)
//                    area_id	地市编号（动环系统地市编号）	是	Varchar2(40)
//                    netponit_id	局站编号（动环系统局站编号）	是	Varchar2(40)
//                    happen_date	耗电发生日期
//                    格式YYYY-MM-DD 	                              是	date
//                    all_code	总用电量（度）	是 	number
//                    kt_code	空调用电量（度）	否	number
//                    dy_code	电源能耗（度）	否	number
//                    sb_code	设备能耗（度）	否	number
//                    qt_code	其它能耗（度）	否	number
//                    gain _time	采样时间，格式YYYY-MM-DD HH24:MI:SS	否   	date
//                    isert_time	数据提交时间,格式YYYY-MM-DD HH24:MI:SS	是   	Date
//                    ceck_flag	读取标志（是否已同步到能耗管理系统）	否	Varchar2(8)
//                    ceck_time	读取时间	否	date
*/
                    
                    
                    k = db.update(sub.toString());
                    if (k == 1) {
                        msg = "解析局站名称和局站编号为 " + mysqlname + "，" + preSday +
                              " 的数据成功！";
                        StringBuffer retstr = new StringBuffer();
                        retstr.append(
                                "update t_electric set ceck_flag='1',ceck_time=sysdate() where ");
                        retstr.append(
                                " concat(netpoint_name,'-',netpoint_id)='" +
                                mysqlname + "'");
                        mysqldb.update(retstr.toString());
                    }
                    System.out.println(msg);
                }
            } catch (DbException de) {
                de.printStackTrace();
            } catch (SQLException de) {
                de.printStackTrace();
            } finally {
                try {
                    mysqldb.close();
                    db.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }

            }
            //log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");

        }
    }

}
