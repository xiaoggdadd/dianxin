package com.noki.page;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import javax.servlet.http.*;

/**
 * @author Administrator
 *
 */
public class PageTag
    extends BodyTagSupport {
  private String flag; //0-初始化 1-全选不全选 2-分页
  private String keyforpage; //如果没有复选框，填"";如果有复选框，则为复选框的值在数据库里的字段名
  private String keyforpagetype; //如果没有复选框，则填"";如果有复选框，则填复选框值在数据库里字段的类型
  private String formname; //页面窗体控件的名字
  private String controlname; //复选框的名字
  private String canfenyesubmit; //1具有分页提交的功能；0不具有分页提交的功能
  private String listname; //保存提交id的列表名
  private String image; //

  private HttpSession session;
  private HttpServletRequest request;
  private HttpServletResponse response;

  /**
   * @return the canfenyesubmit
   */
  public String getCanfenyesubmit() {
    return canfenyesubmit;
  }

  /**
   * @return the image
   */
  public String getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * @return the listname
   */
  public String getListname() {
    return listname;
  }

  /**
   * @param listname the listname to set
   */
  public void setListname(String listname) {
    this.listname = listname;
  }

  /**
   * @param canfenyesubmit the canfenyesubmit to set
   */
  public void setCanfenyesubmit(String canfenyesubmit) {
    this.canfenyesubmit = canfenyesubmit;
  }

  /**
   * @return the controlname
   */
  public String getControlname() {
    return controlname;
  }

  /**
   * @param controlname the controlname to set
   */
  public void setControlname(String controlname) {
    this.controlname = controlname;
  }

  /**
   * @return the flag
   */
  public String getFlag() {
    return flag;
  }

  /**
   * @param flag the flag to set
   */
  public void setFlag(String flag) {
    this.flag = flag;
  }

  /**
   * @return the formname
   */
  public String getFormname() {
    return formname;
  }

  /**
   * @param formname the formname to set
   */
  public void setFormname(String formname) {
    this.formname = formname;
  }

  /**
   * @return the keyforpage
   */
  public String getKeyforpage() {
    return keyforpage;
  }

  /**
   * @param keyforpage the keyforpage to set
   */
  public void setKeyforpage(String keyforpage) {
    this.keyforpage = keyforpage;
  }

  /**
   * @return the keyforpagetype
   */
  public String getKeyforpagetype() {
    return keyforpagetype;
  }

  /**
   * @param keyforpagetype the keyforpagetype to set
   */
  public void setKeyforpagetype(String keyforpagetype) {
    this.keyforpagetype = keyforpagetype;
  }

  public void setPageContext(PageContext pc) {
    this.pageContext = pc;
    session = pc.getSession();
    request = (HttpServletRequest) pc.getRequest();
    response = (HttpServletResponse) pc.getResponse();
  }

  private void doPageInit() {
    String jumpPage = request.getParameter("jumpPage"); //获得当前的页码

    if (jumpPage == null || jumpPage.equals("null")) { //进行初始化
      // session.removeAttribute("pageCtl");
      String listname2 = this.listname;

      session.removeAttribute("selectedlist"); //删除缺省列表，如果不指定listname,系统会提供一个selectedlist
      session.removeAttribute(listname2);

      if (listname2 == null || listname2.trim().equals("")) {
        ArrayList list = new ArrayList();
        session.setAttribute("selectedlist", list);
      }
      else {
        ArrayList list = new ArrayList();
        session.setAttribute(listname2, list);
      }

    }
    session.setAttribute("listname", listname);
    session.setAttribute("controlname", this.controlname);
    session.setAttribute("keyforpage", this.keyforpage);
    session.setAttribute("keyforpagetype", this.keyforpagetype);
    session.setAttribute("canfenyesubmit", canfenyesubmit);
    session.setAttribute("formname", this.formname);

  }

  private void addSelect() {
    /*
     //和业务务有关的方法
     Group p=new Group();
     PageBean pageCtl=p.query(accountid,groupname1,Integer.parseInt(jumpPage));//根据内容查找,当jumpPage为0时不进行分页,一页显示全部内容
     session.setAttribute("keyforpage","groupid");//为null或""使分页提交功能失效
     session.setAttribute("keyforpagetype","int");//diologid字段在数据库中的类型
     session.setAttribute("formname","form1");//提交的窗体的名字
     session.setAttribute("controlname","itemSelect");//提交的控件的名字
     session.setAttribute("pageCtl",pageCtl);//PageBean对象的名字
     session.setAttribute("canfenyesubmit","1");//1具有分页提交功能，0不具有分页提交功能
     session.setAttribute("listname","selectedlist");
     */
    try {
      JspWriter out = this.pageContext.getOut();

      out.write("\r\n");
      out.write("\t\t  ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");

      String formname = (String) session.getAttribute("formname");
      String controlname = (String) session.getAttribute("controlname");
      String canfenyesubmit = (String) session.getAttribute("canfenyesubmit");

      if (controlname != null && !controlname.trim().equals("") && formname != null &&
          !formname.trim().equals("")) {

        out.write("\r\n");
        out.write("\t<!--<a href=\"javascript:selectall2fun('");
        out.print(formname);
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.print(controlname);
        out.write("',true);void 0\">\r\n");
        out.write("\t当前页全选</a>&nbsp;\r\n");
        out.write("\t<a href=\"javascript:selectall2fun('");
        out.print(formname);
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.print(controlname);
        out.write("',false);void 0\">\r\n");
        out.write("\t当前页取消</a>-->\r\n");
        out.write("\t");

        if (canfenyesubmit != null && !canfenyesubmit.trim().equals("")
            && canfenyesubmit.trim().equals("1")) {

          out.write("\r\n");
          out.write("\t<a href=\"javascript:selectallye(true);void 0\">\r\n");
          out.write("\t           所有页全选</a>&nbsp;\r\n");
          out.write(
              "\t<a href=\"javascript:selectallye(false);void 0\">所有页取消</a>&nbsp;\r\n");
          out.write("\r\n");
          out.write("\t");

          String flagflag = request.getParameter("flagflag");
          PageBean pagebean = (PageBean) session.getAttribute("pageCtl");

          if (flagflag != null && flagflag.equals("0")) {
            pagebean.selectAll(request, response);

          }
          else if (flagflag != null && flagflag.equals("1")) {
            pagebean.removeAll(request, response);
          }
          else {
          }

          out.write("\r\n");
          out.write("\t<input type=\"hidden\" name=\"flagflag\">\r\n");
          out.write("\t\r\n");
          out.write("   <script>\r\n");
          out.write("   \r\n");
          out.write("      function selectallye(flag)\r\n");
          out.write("\t  {\r\n");
          out.write("\t  \t if(flag)\r\n");
          out.write("\t\t {\r\n");
          out.write("\t\t    document['");
          out.print(formname);
          out.write("'].flagflag.value=\"0\";\r\n");
          out.write("\t\t    document['");
          out.print(formname);
          out.write("'].submit();\r\n");
          out.write("\t\t }\r\n");
          out.write("\t\t else{\r\n");
          out.write("\t\t    document['");
          out.print(formname);
          out.write("'].flagflag.value=\"1\";\r\n");
          out.write("\t\t    document['");
          out.print(formname);
          out.write("'].submit();\r\n");
          out.write("\t\t }\r\n");
          out.write("\t  }\r\n");
          out.write("\t</script>\r\n");
          out.write("\t");
        }
        out.write("\r\n");
        out.write("\t<script> \r\n");
        out.write("\tfunction selectall2fun(formname,name, isselect) \r\n");
        out.write("  \t{\r\n");
        out.write("\t\t var length=0;\r\n");
        out.write("\t\t //alert(index+\" \"+length);\r\n");
        out.write("\t\tif(isselect)\r\n");
        out.write("\t\t{\r\n");
        out.write("\t\t  length=document[formname][name].length;\r\n");
        out.write("\t\r\n");
        out.write("\t\t  if(length!=null) \r\n");
        out.write("\t\t  {\r\n");
        out.write("\t\t\t\tfor(var j=0;j<length; j++)\r\n");
        out.write("\t\t\t\t{\r\n");
        out.write("\t\t\t\t  document[formname][name][j].checked=true;\r\n");
        out.write("\t\t\t\t}\r\n");
        out.write("\t\t  }\r\n");
        out.write("\t\t  else document[formname][name].checked=true;\r\n");
        out.write("\t\t }\r\n");
        out.write("\t\r\n");
        out.write("\t\t else\r\n");
        out.write("\t\t {\r\n");
        out.write("\t\t\t\t\tlength=document[formname][name].length;\r\n");
        out.write("\t\r\n");
        out.write("\t\t\t\tif(length!=null)\r\n");
        out.write("\t\t\t\t{\r\n");
        out.write("\t\t\t\t\tfor(var j=0;j<length; j++)\r\n");
        out.write("\t\t\t\t\t\t{\r\n");
        out.write("\t\t\t\t\t\tdocument[formname][name][j].checked=false;\r\n");
        out.write("\t\t\t\t\t\t}\r\n");
        out.write("\t\t\t\t}\r\n");
        out.write("\t\t\t\t  else document[formname][name].checked=false;\r\n");
        out.write("\t\t }\r\n");
        out.write("\r\n");
        out.write("  }\t\r\n");
        out.write("   </script>\r\n");
        out.write("   ");
      }
      out.write('\r');
      out.write('\n');
      out.write(" \r\n");
      out.write("\t\t  <!--全选，全取消，当前页选，不选。如果有分页提交功能，应该加这个页面-->\r\n");
      out.write("\t\t  ");

      //	ArrayList selected_list=(ArrayList)session.getAttribute("selectedlist");//获取当前页选择动作前一次所选择的列表
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void addPage() {
    try {
      PageBean page_for_pageCtl = (PageBean) session.getAttribute("pageCtl");
      JspWriter out = this.pageContext.getOut();
      if (page_for_pageCtl.maxPage > 1) {
        String page_for_keyforpage = (String) session.getAttribute("keyforpage");

        String page_for_formname = (String) session.getAttribute("formname");
        String page_for_controlname =
            (String) session.getAttribute("controlname");
        String page_for_canfenyesubmit =
            (String) session.getAttribute("canfenyesubmit");

        out.write("\r\n");
        out.write("<script language=\"JavaScript\">\r\n");
        out.write("\t\t  \r\n");
        out.write("<!--\r\n");
        out.write("function Jumping(){\r\n");
        out.write("  document[\"");
        out.print(page_for_formname);
        out.write("\"].submit();\r\n");
        out.write("  return ;\r\n");
        out.write("}\r\n");
        out.write("\r\n");
        out.write("function gotoPage(pagenum){\r\n");
        out.write("  document[\"");
        out.print(page_for_formname);
        out.write("\"].jumpPage.value = pagenum;\r\n");
        out.write("  document[\"");
        out.print(page_for_formname);
        out.write("\"].submit();\r\n");
        out.write("  return ;\r\n");
        out.write("}\r\n");
        out.write("\r\n");
        out.write("-->\r\n");
        out.write("</script>\r\n");
        out.write("\r\n");
        out.write("第");
        out.print(page_for_pageCtl.curPage);
        out.write("页&nbsp;共");
        out.print(page_for_pageCtl.maxPage);
        out.write('页');

        if (page_for_pageCtl.curPage == 1) {
          out.print(" 首页 上一页");
        }
        else {
          out.write("   \r\n");
          out.write("<A HREF=\"javascript:gotoPage(1)\">首页</A>\r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.curPage - 1);
          out.write(")\">上一页</A>\r\n");
        }
        out.write('\r');
        out.write('\n');
        if (page_for_pageCtl.curPage == page_for_pageCtl.maxPage) {
          out.print("下一页 尾页 ");
        }
        else {
          out.write("   \r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.curPage + 1);
          out.write(")\">下一页</A>&nbsp;\r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.maxPage);
          out.write(")\">尾页</A>\r\n");
        }
        out.write("\r\n");
        out.write("转到第<SELECT name=\"jumpPage\" onchange=\"Jumping()\">\r\n");
        out.write("     ");
        for (int i = 1; i <= page_for_pageCtl.maxPage; i++) {
          if (i == page_for_pageCtl.curPage) {

            out.write("\r\n");
            out.write("     <OPTION selected value=");
            out.print(i);
            out.write('>');
            out.print(i);
            out.write("</OPTION>\r\n");
            out.write("     ");
          }
          else {
            out.write("\r\n");
            out.write("     <OPTION value=");
            out.print(i);
            out.write('>');
            out.print(i);
            out.write("</OPTION>\r\n");
            out.write("     ");
          }
        }
        out.write("   \r\n");
        out.write("     </SELECT>页\r\n");
        out.write("\r\n");
        out.write("\t ");

        String listname = (String) session.getAttribute("listname");

        if (page_for_keyforpage != null &&
            !page_for_keyforpage.trim().equals("")
            && page_for_canfenyesubmit != null &&
            page_for_canfenyesubmit.trim().equals("1"))

        {
          ArrayList page_find_alist = page_for_pageCtl.getResult();
          int page_find_k = ( (Integer) page_find_alist.get(0)).intValue();
          //处理分页提交

          for (int page_find_i = page_find_k;
               page_find_i < page_find_alist.size() - 1;
               page_find_i += page_find_k) {

            String id = (String) page_find_alist.get(page_find_i +
                page_find_alist.indexOf(page_for_keyforpage.toUpperCase()));

            out.write("<input type=\"hidden\" name=\"page_for_ids\" value=\"");
            out.print(id);
            out.write('"');
            out.write('/');
            out.write('>');

          }
          String[] page_ids = request.getParameterValues("page_for_ids");
          String[] actual_ids = request.getParameterValues(page_for_controlname);

          ArrayList selectedlist = null;

          if (listname == null || listname.trim().equals("")) {
            listname = "selectedlist";
            session.setAttribute("listname", listname);
          }

          selectedlist = (ArrayList) session.getAttribute(listname);
          if (selectedlist == null) {
            selectedlist = new ArrayList();
          }
          ArrayList actual_ids_list = new ArrayList();
          //System.out.println(page_ids.length+" "+actual_ids.length);
          for (int i = 0; actual_ids != null && i < actual_ids.length; i++) {
            String id = actual_ids[i];
            if (!selectedlist.contains(id)) {
              selectedlist.add(id);
            }
            actual_ids_list.add(id);
          }

          for (int i = 0; page_ids != null && i < page_ids.length; i++) {
            String id = page_ids[i];
            if (selectedlist.contains(id) && !actual_ids_list.contains(id)) {
              selectedlist.remove(selectedlist.indexOf(id));
            }
          }

          session.setAttribute(listname, selectedlist);
          out.write("\r\n");
          out.write("\r\n");
          out.write("  ");
        }

        //System.out.println()
      }
      else { //不支持分页提交
        session.removeAttribute(listname);
        ArrayList selectedlist = (ArrayList) session.getAttribute(listname);

      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  public int doStartTag() throws JspException {

    if (flag == null || (!flag.equals("0")
                         && !flag.equals("1") && !flag.equals("2"))) {
      return this.SKIP_BODY;
    }

    if (flag.equals("0")) { //初始化
      doPageInit();
      return this.SKIP_BODY;

    }
    else if (flag.equals("1")) { //增加选择项
      this.addSelect();
      return this.SKIP_BODY;

    }
    else if (flag.equals("2")) { //增加翻页page
      this.addPage();
      return this.SKIP_BODY;

    }
    else {}
    return this.SKIP_BODY;
  }

}
