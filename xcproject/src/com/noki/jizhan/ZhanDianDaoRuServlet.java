package com.noki.jizhan;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.noki.jizhan.daoru.CountForm;
import com.noki.jizhan.daoru.InsertDb;
import com.noki.jizhan.daoru.InsertZD;
import com.noki.jizhan.daoru.ModifyZD;
import com.noki.jizhan.daoru.ReadFileFactory_ZD;
import com.noki.jizhan.daoru.ReadFile_ZD;
import com.noki.jizhan.daoru.ReadXLS_ZD;
import com.noki.jizhan.model.DianbiaoBean;
import com.noki.mobi.common.Account;
import com.noki.util.Path;
import com.noki.util.WriteExcle;


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
public class ZhanDianDaoRuServlet extends HttpServlet {
    public ZhanDianDaoRuServlet() {
    }

    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private String msg;
    private String url;
    private String filename = "";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        url = path + "/web/msgdr.jsp";
        //sendUrl = "/mobilebuss/web/msg.jsp";
        String loginName = (String) session.getAttribute("loginName");
        Account account=(Account)session.getAttribute("account");
        String accountname=account.getAccountName();
        String action = request.getParameter("action");
        msg = "导入失败！请重试或联系管理员";
        if (action.equals("upzhandian")) {
            try {
                filename = upload(request, response, loginName);
                Path ppath = new Path();
                ppath.servletInitialize(getServletConfig().getServletContext());
                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
                String filedir = dir1 + filename + ".xls";
                ReadXLS_ZD temp = new ReadXLS_ZD(); //ReadFileFactory_ZD().getReadFile(filedir);
                //Vector head=temp.getColumns(filename);
                Vector content = temp.getContent(filedir);
                InsertZD sellin = new InsertZD();
                CountForm form = new CountForm();
                Vector wrong = sellin.getWrong(content, form,accountname);
                sellin.closeDb();
                if (!wrong.isEmpty()) {
                    msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg() +
                          " 条！有 " + form.getBcg() + "  条数据未导入！";
                    WriteExcle wr = new WriteExcle();
                    String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
                    wr.Write(wrong, loginName + "站点导入不成功的数据.xls", "导入不成功的数据",
                             "导入不成功数据", 26, dir2);
                } else {
                    msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("url", path + "/web/jizhan/sitemanage.jsp");
            session.setAttribute("msg", msg);
            session.setAttribute("wfile",path + "/wrongdata/" + loginName +"站点导入不成功的数据.xls");

        } if (action.equals("newupzhandian")) {
            try {
                filename = upload(request, response, loginName);
                Path ppath = new Path();
                ppath.servletInitialize(getServletConfig().getServletContext());
                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
                String filedir = dir1 + filename + ".xls";
                ReadXLS_ZD temp = new ReadXLS_ZD(); //ReadFileFactory_ZD().getReadFile(filedir);
                //Vector head=temp.getColumns(filename);
                Vector content = temp.getContent(filedir);
                InsertZD sellin = new InsertZD();
                CountForm form = new CountForm();
                Vector wrong = sellin.getWrong(content, form,accountname);
                sellin.closeDb();
                if (!wrong.isEmpty()) {
                    msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg() +
                          " 条！有 " + form.getBcg() + "  条数据未导入！";
                    WriteExcle wr = new WriteExcle();
                    String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
                    wr.Write(wrong, loginName + "站点导入不成功的数据.xls", "导入不成功的数据",
                             "导入不成功数据", 26, dir2);
                } else {
                    msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("url", path + "/web/jizhan/Newsitemanage.jsp");
            session.setAttribute("msg", msg);
            session.setAttribute("wfile",path + "/wrongdata/" + loginName +"站点导入不成功的数据.xls");

        } else if (action.equals("downmodify")) {
            try {
                filename = upload(request, response, loginName);
                Path ppath = new Path();
                ppath.servletInitialize(getServletConfig().getServletContext());
                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
                String filedir = dir1 + filename + ".xls";
               // ReadFile_ZD temp = new ReadFileFactory_ZD().getReadFile(filedir);
                // ReadXLS_ZD temp = new ReadXLS_ZD();
                //POIReadXls temp = new POIReadXls();
                //Vector head=temp.getColumns(filename);
                ReadXLS_ZD temp = new ReadXLS_ZD();
                String shijians=temp.getShijian();
                System.out.println("偷吃人家大米饭"+shijians);
                int shijian=Integer.parseInt(shijians.substring(9, 11));
                //int shijian=19;
                
                if((shijian<=13&&shijian>=12)||(shijian<=23&&shijian>=18)||(shijian<=8&&shijian>=0)){
                	
                
                int row = temp.getContent100(filedir);
                Vector content=null;
                System.out.println("111111111111"+row);
              if(row<=100){
                content = temp.getContent4(filedir);
                ModifyZD sellin = new ModifyZD();
                CountForm form = new CountForm();
                Vector wrong = sellin.getWrong(content, form,accountname);
                sellin.closeDb();
                if (!wrong.isEmpty()) {
                    msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg() +
                          " 条！有 " + form.getBcg() + "  条数据未修改！";
                    WriteExcle wr = new WriteExcle();
                    String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
                    wr.Write(wrong, loginName + "站点修改不成功的数据.xls", "站点修改不成功的数据",
                             "站点修改不成功数据", 15, dir2);
                } else {
                    msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
                }
            }else{
            	 msg = "导入条数不能超过100条！";
            }
              
           }else{
        	   msg="导入时间为中午12点-1点和下午6点-次日8点这两个时间段！！！";
           }
              
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.setAttribute("url", path + "/web/jizhan/daorumodify.jsp");
        session.setAttribute("msg", msg);
        session.setAttribute("wfile",path + "/wrongdata/" + loginName + "站点修改不成功的数据.xls");

        }  else if (action.equals("updianbiao")) {
            try {
                filename = upload(request, response, loginName);
                Path ppath = new Path();
                ppath.servletInitialize(getServletConfig().getServletContext());
                String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
                String filedir = dir1 + filename + ".xls";
                
                Workbook book = Workbook.getWorkbook(new File(filedir));
                Sheet sheet = book.getSheet(0);
                int rowSize = sheet.getRows();
                
                List<DianbiaoBean> dianbiaoList = new ArrayList<DianbiaoBean>();
                List<Vector> content = new ArrayList<Vector>();
                for(int i=2;i<rowSize;i++){
                	Cell[] cells = sheet.getRow(i);
                	String qx = cells[0].getContents();
                	String xz = cells[1].getContents();
                	String zdmc = cells[2].getContents();
                	String zdid = cells[3].getContents();
                	String zdbm = cells[4].getContents();
                	String dbbm = cells[5].getContents();
                	String dbbl = cells[6].getContents();
                	String dbdj = cells[7].getContents();
                	String csds = cells[8].getContents();
                	
                	DianbiaoBean bean = new DianbiaoBean();
                	bean.setBEILV(dbbl);
                	bean.setDANJIA(dbdj);
                	bean.setZdid(zdid);
                	bean.setDBBM(dbbm);
                	bean.setCSDS(csds);
                	dianbiaoList.add(bean);
                	
                	Vector rows = new Vector();
                	rows.add(qx);
                	rows.add(xz);
                	rows.add(zdmc);
                	rows.add(zdid);
                	rows.add(zdbm);
                	rows.add(dbbm);
                	rows.add(dbbl);
                	rows.add(dbdj);
                	rows.add(csds);
                	content.add(rows);
                }
                System.out.println("总共多少行："+rowSize);
                
                ReadFile_ZD temp = new ReadFileFactory_ZD().getReadFile(filedir);
                // ReadXLS_ZD temp = new ReadXLS_ZD();
                //POIReadXls temp = new POIReadXls();
                //Vector head=temp.getColumns(filename);
                //Vector content = temp.getContent(filedir);
                InsertDb sellin = new InsertDb();
                CountForm form = new CountForm();
                Vector wrong = sellin.getWrong(dianbiaoList,content,form,accountname);
                sellin.closeDb();
                if (!wrong.isEmpty()) {
                    msg = "共 " + form.getZg() + "  条。成功导入 " + form.getCg() +
                          " 条！有 " + form.getBcg() + "  条数据未导入！";
                    WriteExcle wr = new WriteExcle();
                    String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
                    wr.Write(wrong, loginName + "电表导入不成功的数据.xls", "电表导入不成功的数据",
                             "电表导入不成功数据", 15, dir2);
                } else {
                    msg = "全部导入成功！共导入 " + form.getCg() + " 条！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("url", path + "/web/jizhan/dianbiaolist.jsp");
            session.setAttribute("msg", msg);
            session.setAttribute("wfile",path + "/wrongdata/" + loginName + "电表导入不成功的数据.xls");

        } 
        response.sendRedirect(url);
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    public synchronized String upload(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String loginName) throws
            Exception {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        filename = loginName + System.currentTimeMillis();
        // String store = "";
        ArrayList list = new ArrayList();
        DiskFileUpload fu = new DiskFileUpload();
        fu.setHeaderEncoding("utf-8");
        // 设置允许用户上传文件大小,单位:字节，这里设为2m
        fu.setSizeMax(5 * 1024 * 1024);
        // 设置最多只允许在内存中存储的数据,单位:字节
        fu.setSizeThreshold(4096);
        // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
        Path path = new Path();
        path.servletInitialize(getServletConfig().getServletContext());
        String dir1 = path.getPhysicalPath("/indata/", 0); // 传参数
        fu.setRepositoryPath(dir1);
        // 开始读取上传信息
        List fileItems = fu.parseRequest(request);
        // 依次处理每个上传的文件
        Iterator iter = fileItems.iterator();
        // 正则匹配，过滤路径取文件名
        String regExp = ".+\\\\(.+)$";
        // 过滤掉的文件类型
        // String[] errorType={".exe",".com",".cgi",".asp"};
        Pattern p = Pattern.compile(regExp);
        String zipname = null;
        int num = 0;
        File file = null;
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // 忽略其他不是文件域的所有表单信息
            if (!item.isFormField()) {
                String name = item.getName();
                long size = item.getSize();
                if ((name == null || name.equals("")) && size == 0) {
                    continue;
                }
                Matcher m = p.matcher(name);
                boolean result = true; //m.find();
                if (result) {
                    try {
                        // 保存上传的文件到指定的目录
                        String fileType = ".xls";
                        zipname = filename + fileType;
                        session.setAttribute("filename", dir1 + zipname);
                        file = new File(dir1 + zipname);
                        item.write(file);
                    } catch (Exception e) {
                        // out.println(e);
                        e.printStackTrace();
                    }

                } else {
                    throw new IOException("fail to upload");
                }
            }
        }
        return filename;
    }


}
