package com.ptac.app.provinceapply.gbsq.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.newfunction.dao.KtxsDao;
import com.noki.newfunction.javabean.Ktxs;
import com.noki.zdqxkz.dao.ShiQuery;
import com.noki.zdqxkz.javabean.Zdqxkz;
import com.ptac.app.provinceapply.gbsq.bean.GbsqBaseBean;
import com.ptac.app.provinceapply.gbsq.bean.GbsqBean;
import com.ptac.app.provinceapply.gbsq.dao.GbsqDao;
import com.ptac.app.provinceapply.gbsq.dao.GbsqDaoImp;

/**
 * @author lijing
 * @see ʡ����ı����
 */
public class GbsqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun".equals(command)){//��ѯ
			query(request,response);
		}else if("tongguo".equals(command)|| "butongguo".equals(command) || "quxiao".equals(command)){
			check(request, response);
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����
		String shengzt = request.getParameter("shengzt");//���״̬
		String zdname = request.getParameter("zdname");//վ������
		String zdid = request.getParameter("zdid");//վ��ID
		String property = request.getParameter("zdsx1");//վ������
		String zdlx = request.getParameter("zdlx1");//վ������
		String loginId = request.getParameter("loginId");//�û�id
		
		StringBuffer whereStr = new StringBuffer();//��ѯ����
		if (zdid != null && !zdid.equals("")){
			whereStr.append(" AND Z.ID = '" + zdid + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" AND Z.JZNAME LIKE '%" + zdname + "%'");
		}
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" AND Z.SHI='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			 whereStr.append(" AND Z.XIAN='" + xian + "'");
		}
		if("0".equals(shengzt)){
			whereStr.append(" AND (Q.BFTG NOT LIKE '%9%' OR Q.BFTG IS NULL) AND (Q.BFBTG NOT LIKE '%9%' OR Q.BFBTG IS NULL)");	
		}else if("1".equals(shengzt)){
			whereStr.append(" AND Q.BFTG LIKE '%9%'");	
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!property.equals("��ѡ��")){
			whereStr.append(" AND Q.NEWPROPERTY in(" + property + ")");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")&&!zdlx.equals("��ѡ��")){
			whereStr.append(" AND Q.NEWSTATIONTYPE in(" + zdlx + ")");
		}
		
		GbsqDao dao = new GbsqDaoImp();
		List<GbsqBean> list = dao.query(whereStr.toString(),loginId,shengzt);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/web/zdqxkz/gbsqshenhe.jsp").forward(request, response);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();//��·��
		HttpSession session = request.getSession();
		String loginid = request.getParameter("loginId");
		String personnal = request.getParameter("personnal");
		String[] qxkzid=request.getParameterValues("test[]");
		String command = request.getParameter("command");
		GbsqDao dao = new GbsqDaoImp();
		List<GbsqBaseBean> list = new ArrayList<GbsqBaseBean>();
			list = dao.queryBase(qxkzid);
		if("tongguo".equals(command)){
			
			KtxsDao daoa = new KtxsDao();
			   String msg1="ʡ���ͨ��ʧ�ܣ�";
				List<Ktxs> fylist = new ArrayList<Ktxs>();
				fylist = daoa.getfwxs();
				List<Ktxs> fylist1 = new ArrayList<Ktxs>();
				fylist1 = daoa.getktxs();
				String syf="";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//�������ڸ�ʽ
				String sff=df.format(new Date());
				String yf=sff.toString().substring(5,7);
				if(yf.equals("01")){
					syf="YMONTH";
				}else if(yf.equals("02")){
					syf="EMONTH";
				}else if(yf.equals("03")){
					syf="SMONTH";
				}else if(yf.equals("04")){
					syf="SIMONTH";
				}else if(yf.equals("05")){
					syf="WMONTH";
				}else if(yf.equals("06")){
					syf="LMONTH";
				}else if(yf.equals("07")){
					syf="QMONTH";
				}else if(yf.equals("08")){
					syf="BMONTH";
				}else if(yf.equals("09")){
					syf="JMONTH";
				}else if(yf.equals("10")){
					syf="SHIMONTH";
				}else if(yf.equals("11")){
					syf="SYMONTH";
				}else if(yf.equals("12")){
					syf="SEMONTH";
				}
				String fxxs="",bzw="",b1="",b2="",b3="",b4="",b5="",b6="";
				for(Ktxs fw:fylist){
					fxxs=fw.getFxxs();
					bzw=fw.getFwsjbzw();
					if(bzw.equals("1")){
						b1=fxxs;
					}else if(bzw.equals("2")){
						b2=fxxs;
					}else if(bzw.equals("3")){
						b3=fxxs;
					}
				}
				String bb1="",bb2="",bb3="",bb4="",bb5="",bb6="",bb7="";
				String bb8="",bb9="",bb10="",bb11="",bb12="",bb13="",bb14="";
				String bb15="",bb16="",bb17="",bb18="",bb19="",bb20="",bb21="",bb22="";
				//����������
				String dd3="";
				String dd5="",dd6="",dd7="",dd8="",dd9="",dd10="",dd11="",dd12="",dd13="",dd14="",dd15="",dd16="",dd17="";
				String dd18="",dd19="",dd20="",dd21="",dd22="";
				//����֧��
				String xx3="";
				String xx5="",xx6="",xx7="",xx8="",xx9="",xx10="",xx11="",xx12="",xx13="",xx14="",xx15="",xx16="",xx17="";
				String xx18="",xx19="",xx20="",xx21="",xx22="";
				//���û���
				String jj3="";
				String jj5="",jj6="",jj7="",jj8="",jj9="",jj10="",jj11="",jj12="",jj13="",jj14="",jj15="",jj16="",jj17="";
				String jj18="",jj19="",jj20="",jj21="",jj22="";
				//Ӫҵ����
				String yy3="";
				String yy5="",yy6="",yy7="",yy8="",yy9="",yy10="",yy11="",yy12="",yy13="",yy14="",yy15="",yy16="",yy17="";
				String yy18="",yy19="",yy20="",yy21="",yy22="";
				//����
				String qq3="";
				String qq5="",qq6="",qq7="",qq8="",qq9="",qq10="",qq11="",qq12="",qq13="",qq14="",qq15="",qq16="",qq17="";
				String qq18="",qq19="",qq20="",qq21="",qq22="";
				//IDC����
				String ii5="",ii6="",ii7="",ii8="",ii9="",ii10="",ii11="",ii12="",ii13="",ii14="",ii15="",ii16="",ii17="";
				String ii18="",ii19="",ii20="",ii21="",ii22="";
				String ii3="";
				for(Ktxs kt:fylist1){
					bb1=kt.getKszlfh();
					bb2=kt.getJszlfh();
					bb4=kt.getSjbzw();
					bb3=kt.getJzktxs();//��վ�յ�ϵ��
					dd3=kt.getJrwktxs();//�������Ŀյ�ϵ��
					xx3=kt.getXzzjktxs();//����֧�ֵĿյ�ϵ��
					jj3=kt.getJyjfktxs();//���û����յ�ϵ��
					yy3=kt.getYywdktxs();//Ӫҵ����յ�ϵ��
					ii3=kt.getIdcjfktxs();//IDC�����յ�ϵ��
					qq3=kt.getQtktxs();//�����յ�ϵ��
					if(bb4.equals("1")){
						//��վΪbb
						bb5=bb1;
						bb6=bb2;
						bb7=bb3;
						//������Ϊdd
						dd5=bb1;
						dd6=bb2;
						dd7=dd3;
						//����֧��
						xx5=bb1;
						xx6=bb2;
						xx7=xx3;
						//���û���
						jj5=bb1;
						jj6=bb2;
						jj7=jj3;
						//Ӫҵ����
						yy5=bb1;
						yy6=bb2;
						yy7=yy3;
						//IDC����
						ii5=bb1;
						ii6=bb2;
						ii7=ii3;
						//����
						qq5=bb1;
						qq6=bb2;
						qq7=qq3;
						
						
					}
                 if(bb4.equals("2")){
                	//��վΪbb
						bb8=bb1;
						bb9=bb2;
						bb10=bb3;
						//������dd
						dd8=bb1;
						dd9=bb2;
						dd10=dd3;
						//����֧��
						xx8=bb1;
						xx9=bb2;
						xx10=xx3;
						//���û���
						jj8=bb1;
						jj9=bb2;
						jj10=jj3;
						//Ӫҵ����
						yy8=bb1;
						yy9=bb2;
						yy10=yy3;
						//IDC����
						ii8=bb1;
						ii9=bb2;
						ii10=ii3;
						//����
						qq8=bb1;
						qq9=bb2;
						qq10=qq3;
						
					}
                  if(bb4.equals("3")){
                	//��վΪbb
                	  bb11=bb1;
						  bb12=bb2;
						  bb13=bb3;
						//������dd
						  dd11=bb1;
						  dd12=bb2;
						  dd13=dd3;
						//����֧��
						xx11=bb1;
						xx12=bb2;
						xx13=xx3;
						//====================
					//���û���
						jj11=bb1;
						jj12=bb2;
						jj13=jj3;
						//Ӫҵ����
						yy11=bb1;
						yy12=bb2;
						yy13=yy3;
						//IDC����
						ii11=bb1;
						ii12=bb2;
						ii13=ii3;
						//����
						qq11=bb1;
						qq12=bb2;
						qq13=qq3;
}
                 if(bb4.equals("4")){
                	//��վΪbb
                	 bb14=bb1;
						bb15=bb2;
						bb16=bb3;
						//������dd
						dd14=bb1;
						dd15=bb2;
						dd16=dd3;
					//����֧��
						xx14=bb1;
						xx15=bb2;
						xx16=xx3;
						//===========
						//���û���
						jj14=bb1;
						jj15=bb2;
						jj16=jj3;
						//Ӫҵ����
						yy14=bb1;
						yy15=bb2;
						yy16=yy3;
						//IDC����
						ii14=bb1;
						ii15=bb2;
						ii16=ii3;
						//����
						qq14=bb1;
						qq15=bb2;
						qq16=qq3;
}
                 if(bb4.equals("5")){
                	 //��վbb
                	 bb17=bb1;
						bb18=bb2;
						bb19=bb3;
						//������dd
						 dd17=bb1;
						dd18=bb2;
						dd19=dd3;
					//����֧��
						xx17=bb1;
						xx18=bb2;
						xx19=xx3;
						//==========
						//���û���
						jj17=bb1;
						jj18=bb2;
						jj19=jj3;
						//Ӫҵ����
						yy17=bb1;
						yy18=bb2;
						yy19=yy3;
						//IDC����
						ii17=bb1;
						ii18=bb2;
						ii19=ii3;
						//����
						qq17=bb1;
						qq18=bb2;
						qq19=qq3;
}
                 if(bb4.equals("6")){
                	 //��վbb
                	 bb20=bb1;
						bb22=bb3;
						//==������dd
						 dd20=bb1;
						dd22=dd3;
					//����֧��
						xx20=bb1;
						xx22=xx3;
						//=========
						//���û���
						jj20=bb1;
						jj22=jj3;
						//Ӫҵ����
						yy20=bb1;
						yy22=yy3;
						//IDC����
						ii20=bb1;
						ii22=ii3;
						//����
						qq20=bb1;
						qq22=qq3;
}
				}
				
			  String  msg =  dao.checkPass(list,syf,b1,b2,b3,
					   bb5,bb6,bb7,bb8,bb9,bb10,bb11,bb12,bb13,bb14,bb15,bb16,bb17,bb18,bb19,bb20,bb22,
					   dd5,dd6,dd7,dd8,dd9,dd10,dd11,dd12,dd13,dd14,dd15,dd16,dd17,dd18,dd19,dd20,dd22,
					   xx5,xx6,xx7,xx8,xx9,xx10,xx11,xx12,xx13,xx14,xx15,xx16,xx17,xx18,xx19,xx20,xx22,
					   yy5,yy6,yy7,yy8,yy9,yy10,yy11,yy12,yy13,yy14,yy15,yy16,yy17,yy18,yy19,yy20,yy22,
					   qq5,qq6,qq7,qq8,qq9,qq10,qq11,qq12,qq13,qq14,qq15,qq16,qq17,qq18,qq19,qq20,qq22,
					   ii5,ii6,ii7,ii8,ii9,ii10,ii11,ii12,ii13,ii14,ii15,ii16,ii17,ii18,ii19,ii20,ii22,
					   jj5,jj6,jj7,jj8,jj9,jj10,jj11,jj12,jj13,jj14,jj15,jj16,jj17,jj18,jj19,jj20,jj22,personnal);
			

			String  url = path + "/web/zdqxkz/gbsqshenhe.jsp" ;
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if("butongguo".equals(command)){
			System.out.println("��ͨ��");
			String cause = request.getParameter("cause")==null?"":request.getParameter("cause");
			String s = new String(cause.getBytes("ISO8859-1"), "UTF-8");
			String msg = dao.checkNoPass(list, personnal, s);
			String  url = path + "/web/zdqxkz/gbsqshenhe.jsp" ;
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if("quxiao".equals(command)){
			System.out.println("ȡ��");
			String msg = dao.checkQuXiao(list, personnal);
			String  url = path + "/web/zdqxkz/gbsqshenhe.jsp" ;
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		
		
	}
}
