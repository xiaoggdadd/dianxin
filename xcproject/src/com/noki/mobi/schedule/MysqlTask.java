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
 * <p>Title:�����Զ�MYSQl��ȡ�ƻ� </p>
 *
 * <p>Description: ���������Զ���ȡ�ƻ�  Timer  </p>
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
    private String preSday = "";// ����
    //private String pre2Sday = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");
    public void run() {
        System.out.println("mySQl_timer run== "+CTime.formatWholeDate());
        nowtime = CTime.formatDate();
        nowDay = nowtime.substring(0, 8);
        nowHour = nowtime.substring(8, 10);
        if (Integer.parseInt(nowHour) <= 1) { //��㵽һ��֮��ִ������
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

            System.out.println(st.toString()+"MYsqlTaskִ���ˣ�������������������������������");
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
                    msg = "������վ���ƺ;�վ���Ϊ " + mysqlname + "��" + preSday + " ������ʧ�ܣ�";//Ĭ�ϴ�ӡ����Ϣ���ɹ����滻��
                    sub.setLength(0);

                    sub.append(
                            "select all_code,gain_time from t_electric where concat(netpoint_name,'-',netpoint_id)='" +
                            mysqlname + "'");
                    sub.append(
                            " and UNIX_TIMESTAMP(gain_time)<UNIX_TIMESTAMP('" +
                            preSday + " 00:00:00') and ceck_flag='0' order by serial_no desc");

                    System.out.println(sub.toString()+"mysqlTask��ȡ�ܵ����Ͳɼ�ʱ�䣡��");
                    ResultSet rsp = mysqldb.queryAll(sub.toString());
                    if (rsp.next()) {
                        ftime = rsp.getString("gain_time").substring(0, 10);
                        fdata = rsp.getDouble("all_code");
                        System.out.println(ftime+"%%%"+fdata+"����ǰ���ܵ����Ͳɼ�ʱ�䣡����");
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
                        System.out.println(btime+"%%%"+bdata+"������ܵ����Ͳɼ�ʱ�䣡����");
                    } else {
                        btime = preSday;
                        bdata = 0;
                    }
                    chae = bdata - fdata;
                    DecimalFormat df = new DecimalFormat(".##");
                    sub.setLength(0);
                    sub.append("insert into AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,ACTUALDEGREE,ZDCB)");
                    
                 //   ��ӵ��� ����Դ����ѡȡ  ���ID����ȷ��   
                    sub.append(" select dbid,'" + fdata + "','" + bdata +
                               "','" + ftime + "','" + btime + "','" +
                               df.format(chae) + "','1'");
                    sub.append(" from dianbiao where (netpoint_name||'-'||netpoint_id)='"+mysqlname+"'");
                    
                    //�����䱾�������� �ֶ����ݿ���û�� (netpoint_name||'-'||netpoint_id)����ѡ��
                    
                    //����MYSQL�ӿ���� ����ȷ�����ID   ֻ�о�վ   ID��Name
                    
             /*       ��������	����˵��	�Ƿ����	�������ͣ����ȣ�
//                    serial_no	������ˮ��(������)	��	number
//                    area_name	��������	                                ��        Varchar2(40)
//                    org_name	�������ƣ�һ������	��	Varchar2(40)
//                    towns	          �������ƣ���������	��	Varchar2(40)
//                    netponit_name	��վ����              	��	Varchar2(40)
//                    area_id	���б�ţ�����ϵͳ���б�ţ�	��	Varchar2(40)
//                    netponit_id	��վ��ţ�����ϵͳ��վ��ţ�	��	Varchar2(40)
//                    happen_date	�ĵ緢������
//                    ��ʽYYYY-MM-DD 	                              ��	date
//                    all_code	���õ������ȣ�	�� 	number
//                    kt_code	�յ��õ������ȣ�	��	number
//                    dy_code	��Դ�ܺģ��ȣ�	��	number
//                    sb_code	�豸�ܺģ��ȣ�	��	number
//                    qt_code	�����ܺģ��ȣ�	��	number
//                    gain _time	����ʱ�䣬��ʽYYYY-MM-DD HH24:MI:SS	��   	date
//                    isert_time	�����ύʱ��,��ʽYYYY-MM-DD HH24:MI:SS	��   	Date
//                    ceck_flag	��ȡ��־���Ƿ���ͬ�����ܺĹ���ϵͳ��	��	Varchar2(8)
//                    ceck_time	��ȡʱ��	��	date
*/
                    
                    
                    k = db.update(sub.toString());
                    if (k == 1) {
                        msg = "������վ���ƺ;�վ���Ϊ " + mysqlname + "��" + preSday +
                              " �����ݳɹ���";
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
            //log.write(msg, account.getAccountName(), req.getRemoteAddr(), "�˻�ά��");

        }
    }

}
