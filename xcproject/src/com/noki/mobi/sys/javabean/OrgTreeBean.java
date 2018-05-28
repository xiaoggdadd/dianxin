package com.noki.mobi.sys.javabean;

import java.sql.*;

import com.jenkov.prizetags.tree.impl.*;
import com.jenkov.prizetags.tree.itf.*;
import com.noki.database.DataBase;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class OrgTreeBean {
    public OrgTreeBean() {
    }
    private String table1;

    ITree tree = new Tree();
    private DataBase db = null;
    private String bmid;

    /** Creates a new instance of CreateTree */
    public OrgTreeBean(String table1,String bmid) {
        this.table1 = table1;
        this.setBmid(bmid);
        try {
            ITreeNode root = null;
            this.db = new DataBase();
            StringBuffer sql = new StringBuffer();
            if(bmid.equals("0")){
                sql.append("select classid,classname from structure where parentid=0 and rootid=1");
            }else{
                sql.append("select classid,classname from structure where classid="+bmid);
            }
            ResultSet rs = db.queryAlls(sql.toString());
            if (rs != null) {
                if (rs.next()) {

                    String id = rs.getInt("CLASSID") + "";
                    String name = rs.getString("CLASSNAME");

                    root = new TreeNode(id, name, table1);
                    expandNode(db, root);
                }
            }
            if (root != null) {
                tree.setRoot(root);
                tree.expand(root.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void expandNode(DataBase db, ITreeNode parent) {
        ResultSet rs = null;
        try {

            rs = db.queryAlls(
                    "SELECT CLASSID,CLASSNAME FROM STRUCTURE WHERE rootid=1 and PARENTID=" +
                    parent.getId()+" ORDER BY ORDERID");

            if (rs != null) {

                while (rs.next()) {

                    String id = rs.getInt("CLASSID") + "";
                    String name = rs.getString("CLASSNAME");

                    ITreeNode node = new TreeNode(id, name, table1);
                    node.setObject(id);
                    parent.addChild(node);

                    expandNode(db, node);

                }
                rs.close();
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }

    private void dbClose() {
        try {
            System.out.println(">>organization end");
            this.db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized ITree getTree() {
        this.dbClose();
        return tree;

    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getBmid() {
        return bmid;
    }

}
