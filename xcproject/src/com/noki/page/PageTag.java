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
  private String flag; //0-��ʼ�� 1-ȫѡ��ȫѡ 2-��ҳ
  private String keyforpage; //���û�и�ѡ����"";����и�ѡ����Ϊ��ѡ���ֵ�����ݿ�����ֶ���
  private String keyforpagetype; //���û�и�ѡ������"";����и�ѡ�����ѡ��ֵ�����ݿ����ֶε�����
  private String formname; //ҳ�洰��ؼ�������
  private String controlname; //��ѡ�������
  private String canfenyesubmit; //1���з�ҳ�ύ�Ĺ��ܣ�0�����з�ҳ�ύ�Ĺ���
  private String listname; //�����ύid���б���
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
    String jumpPage = request.getParameter("jumpPage"); //��õ�ǰ��ҳ��

    if (jumpPage == null || jumpPage.equals("null")) { //���г�ʼ��
      // session.removeAttribute("pageCtl");
      String listname2 = this.listname;

      session.removeAttribute("selectedlist"); //ɾ��ȱʡ�б������ָ��listname,ϵͳ���ṩһ��selectedlist
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
     //��ҵ�����йصķ���
     Group p=new Group();
     PageBean pageCtl=p.query(accountid,groupname1,Integer.parseInt(jumpPage));//�������ݲ���,��jumpPageΪ0ʱ�����з�ҳ,һҳ��ʾȫ������
     session.setAttribute("keyforpage","groupid");//Ϊnull��""ʹ��ҳ�ύ����ʧЧ
     session.setAttribute("keyforpagetype","int");//diologid�ֶ������ݿ��е�����
     session.setAttribute("formname","form1");//�ύ�Ĵ��������
     session.setAttribute("controlname","itemSelect");//�ύ�Ŀؼ�������
     session.setAttribute("pageCtl",pageCtl);//PageBean���������
     session.setAttribute("canfenyesubmit","1");//1���з�ҳ�ύ���ܣ�0�����з�ҳ�ύ����
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
        out.write("\t��ǰҳȫѡ</a>&nbsp;\r\n");
        out.write("\t<a href=\"javascript:selectall2fun('");
        out.print(formname);
        out.write('\'');
        out.write(',');
        out.write('\'');
        out.print(controlname);
        out.write("',false);void 0\">\r\n");
        out.write("\t��ǰҳȡ��</a>-->\r\n");
        out.write("\t");

        if (canfenyesubmit != null && !canfenyesubmit.trim().equals("")
            && canfenyesubmit.trim().equals("1")) {

          out.write("\r\n");
          out.write("\t<a href=\"javascript:selectallye(true);void 0\">\r\n");
          out.write("\t           ����ҳȫѡ</a>&nbsp;\r\n");
          out.write(
              "\t<a href=\"javascript:selectallye(false);void 0\">����ҳȡ��</a>&nbsp;\r\n");
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
      out.write("\t\t  <!--ȫѡ��ȫȡ������ǰҳѡ����ѡ������з�ҳ�ύ���ܣ�Ӧ�ü����ҳ��-->\r\n");
      out.write("\t\t  ");

      //	ArrayList selected_list=(ArrayList)session.getAttribute("selectedlist");//��ȡ��ǰҳѡ����ǰһ����ѡ����б�
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
        out.write("��");
        out.print(page_for_pageCtl.curPage);
        out.write("ҳ&nbsp;��");
        out.print(page_for_pageCtl.maxPage);
        out.write('ҳ');

        if (page_for_pageCtl.curPage == 1) {
          out.print(" ��ҳ ��һҳ");
        }
        else {
          out.write("   \r\n");
          out.write("<A HREF=\"javascript:gotoPage(1)\">��ҳ</A>\r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.curPage - 1);
          out.write(")\">��һҳ</A>\r\n");
        }
        out.write('\r');
        out.write('\n');
        if (page_for_pageCtl.curPage == page_for_pageCtl.maxPage) {
          out.print("��һҳ βҳ ");
        }
        else {
          out.write("   \r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.curPage + 1);
          out.write(")\">��һҳ</A>&nbsp;\r\n");
          out.write("<A HREF=\"javascript:gotoPage(");
          out.print(page_for_pageCtl.maxPage);
          out.write(")\">βҳ</A>\r\n");
        }
        out.write("\r\n");
        out.write("ת����<SELECT name=\"jumpPage\" onchange=\"Jumping()\">\r\n");
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
        out.write("     </SELECT>ҳ\r\n");
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
          //�����ҳ�ύ

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
      else { //��֧�ַ�ҳ�ύ
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

    if (flag.equals("0")) { //��ʼ��
      doPageInit();
      return this.SKIP_BODY;

    }
    else if (flag.equals("1")) { //����ѡ����
      this.addSelect();
      return this.SKIP_BODY;

    }
    else if (flag.equals("2")) { //���ӷ�ҳpage
      this.addPage();
      return this.SKIP_BODY;

    }
    else {}
    return this.SKIP_BODY;
  }

}
