package com.noki.jtreport.sheng.download.javabean;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * ���л�վ�õ�����Ϣ���ܱ�.xls����һ��
 * @author ����
 *
 */
public class DSJZYDLXXHZ_ReportDao extends ReportDao {
	@Override
	//����  ������ ����ķ��� doQuery����
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		Integer startRow = 0;
		Integer endRow = 3;
		WritableWorkbook firstBook = books.get(0);//��ȡ�׸�������Ϊ��������Ļ��ܴ�
		WritableSheet sheet = firstBook.getSheet(0);
		WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); //�����ʽ
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
		wcf.setAlignment(jxl.format.Alignment.RIGHT);//���뷽ʽ
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
		int flag = 0;
		ArrayList list=ReportCopyOperate.shixs();//�Ե����ŵ�˳��
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();//��ѯ�������е�����
		String shi="";
		String shi1="";
		for(int e=0;e<books.size();e++){//����ĸ���
			shi1 = books.get(0).getSheet(0).getCell(0,3).getContents();//��ȡ��һ��������
			String shiName3 = books.get(e).getSheet(0).getCell(0,3).getContents().toString();//��ȡ����������  �浽list��
			
			System.out.println("++++��ѯ������shiName3"+shiName3);
			list2.add(shiName3);
		}
		//��ȡexcel�������ݵ�����    ����
		//String shiname1=""; //���ֻ��һ�����б��������
		for(int i=0;i<list.size();i++){			
			shi=(String) list.get(i);
			System.out.println("**********************************��������ѭ��i:"+i+"shi="+shi);
			boolean a = false;
			for(int n=0;n<books.size();n++){
				//System.out.println("=================��ѯ���ı���ѭ��n:"+n);				
		     /*  if(i==0&&books.size()==1){   //���ֻ��һ�����б��������
		    	    shiname1= books.get(0).getSheet(0).getCell(0,3).getContents();
				}*/
				String shiName2 = books.get(n).getSheet(0).getCell(0,3).getContents();//��ȡ��ѯ�����ı��������	
				/*if(books.size()==1){ //���ֻ��һ�����б��������
					shiName2=shiname1;
				}*/
				System.out.println("========shiName2:"+shiName2+"  ===��һ�����ݣ�"+books.get(n).getSheet(0).getCell(1,3).getContents()+"n="+n);
				for(int l=0;l<list2.size();l++){
					if(shi.equals(list2.get(l))){//��������� �Ͳ�ѯ����������  �Ƿ���ͬ
						a = true;
					}
				}	
				startRow = endRow;
				if(shi.equals(shi1)){ //����ĵ�һ������   ��  ��ѯ���ĵ�һ������ �Ƿ���ͬ
					endRow += 23;//�����23������
					flag=flag+1;//
					
					System.out.println("======++++++++++++++++++++++++++++++++=============��һ������������Ƿ����"+"shi:"+shi+"  shi1"+shi1+"endRow="+endRow);
					list1.add(shi);
					break;//��һ����������
				}else{
					if(shiName2.equals(shi)){//��ѯ�����ı���������� ����������Ƿ�һ��						
						ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 18, 25, 0, startRow);//���Ʊ���Ԫ�������
						sheet.mergeCells(0, endRow, 0, endRow+22);//�ϲ���Ԫ��
						System.out.println("��ѯ�����ı���������� ����������Ƿ�һ��	"+"shiName2:"+shiName2+"  shi"+shi+"endRow="+endRow);
						
						list1.add(shi);
						endRow += 23;
					}else if(!a){//��������� �Ͳ�ѯ����������  ����ͬ
						boolean b = true;
						for(int j=0;j<list1.size();j++){
							//System.out.println("�Ѿ����ƹ��ĵ�������ѭ��j:"+j+"endRow="+endRow);
							if(shi==list1.get(j)||shiName2==list1.get(j)){
								//System.out.println("����shi:"+shi+" �Ѿ����ƹ���"+list1.get(j)+" ��ѯ��shiName2"+shiName2+"endRow="+endRow);
								b = false;
							}
						}
						System.out.println("!!!!!!!���Ϊ�ճ���b:"+b+list.get(i)+"endRow="+endRow);
						if(b){//���û�б���  �ճ���
							//if(!shi11.equals(shi1))  �����һ������û�б��� Ӧ�ü�23��  ����Ϊ����ʱû�б���Ӧ�ü�23��
							sheet.insertRow(startRow);
							Label label = new Label(0,startRow,list.get(i).toString(),wcf);
							System.out.println("û�б����������"+list.get(i)+"endRow="+endRow);
							sheet.addCell(label);
							sheet.mergeCells(0, endRow, 0, endRow+22);
							list1.add(shi);
							endRow += 23;
							System.out.println("������ɺ�"+"endRow="+endRow);
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		
		
		
		
		/*for(WritableWorkbook book:books){
			startRow = endRow;
			endRow += 23;
			if(flag++ == 0)continue;//��һ����������
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 18, 25, 0, startRow);
			sheet.mergeCells(0, startRow, 0, endRow-1);
		}*/
		
		//----------------------ͳ��----------------------
		ReportCopyOperate.copyCells(sheet,sheet, 0, 3, 18, 25, 0, endRow);//����һ���ϼ�
		Label heji = new Label(0,endRow,"�ϼ�");//�ı�����
		sheet.addCell(heji);
		sheet.mergeCells(0, endRow, 0, endRow+22);
		
//		Pattern pattern = Pattern.compile("^[+\\-]?\\d+(.\\d+)?$");
		
		for(int i = 2;i<18;i++){//���б���      18��
			float lx1=0,lx2=0,lx3=0,lx4=0,lx5=0,lx6=0,lx7=0,lx8=0,lx9=0,lx10=0,lx11=0,lx12=0,lx13=0,lx14=0,lx15=0,lx16=0,lx17=0,lx18=0,lx19=0,lx20=0,lx21=0,lx22=0,lx23=0;
			for(int r = 3;r<endRow+23;r++){//���б���
				String leixing = sheet.getCell(1, r).getContents().trim();//��ȡ���еڶ��е����ݣ��������ĸ�����
				String content = "";
				if(r<endRow){//ͳ��֮ǰ������
					content = sheet.getCell(i, r).getContents();//��ȡ�õ�Ԫ������
					if(leixing.equals("����1"))lx1 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����2"))lx2 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����3"))lx3 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����4"))lx4 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����5"))lx5 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����6"))lx6 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����7"))lx7 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����8"))lx8 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����9"))lx9 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����10"))lx10 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����11"))lx11 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����12"))lx12 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����13"))lx13 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����14"))lx14 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����15"))lx15 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����16"))lx16 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����17"))lx17 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����18"))lx18 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����19"))lx19 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����20"))lx20 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����21"))lx21 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����22"))lx22 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("����վ"))lx23 += content.trim().equals("")?0:Double.valueOf(content);
				}
				else{//׼��ͳ��
					if(leixing.equals("����1"))content += Math.round(lx1*100)/100f;
					else if(leixing.equals("����2"))content += Math.round(lx2*100)/100f;
					else if(leixing.equals("����3"))content += Math.round(lx3*100)/100f;
					else if(leixing.equals("����4"))content += Math.round(lx4*100)/100f;
					else if(leixing.equals("����5"))content += Math.round(lx5*100)/100f;
					else if(leixing.equals("����6"))content += Math.round(lx6*100)/100f;
					else if(leixing.equals("����7"))content += Math.round(lx7*100)/100f;
					else if(leixing.equals("����8"))content += Math.round(lx8*100)/100f;
					else if(leixing.equals("����9"))content += Math.round(lx9*100)/100f;
					else if(leixing.equals("����10"))content += Math.round(lx10*100)/100f;
					else if(leixing.equals("����11"))content += Math.round(lx11*100)/100f;
					else if(leixing.equals("����12"))content += Math.round(lx12*100)/100f;
					else if(leixing.equals("����13"))content += Math.round(lx13*100)/100f;
					else if(leixing.equals("����14"))content += Math.round(lx14*100)/100f;
					else if(leixing.equals("����15"))content += Math.round(lx15*100)/100f;
					else if(leixing.equals("����16"))content += Math.round(lx16*100)/100f;
					else if(leixing.equals("����17"))content += Math.round(lx17*100)/100f;
					else if(leixing.equals("����18"))content += Math.round(lx18*100)/100f;
					else if(leixing.equals("����19"))content += Math.round(lx19*100)/100f;
					else if(leixing.equals("����20"))content += Math.round(lx20*100)/100f;
					else if(leixing.equals("����21"))content += Math.round(lx21*100)/100f;
					else if(leixing.equals("����22"))content += Math.round(lx22*100)/100f;
					else if(leixing.equals("����վ"))content += Math.round(lx23*100)/100f;
					
//					Matcher matcher = pattern.matcher(content);
//					
//					if(matcher.find()){
//						jxl.write.Number currentCell = new jxl.write.Number(r,i,Double.valueOf(content));
//						sheet.addCell(currentCell);
//					}
//					else{
					boolean dd=content.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
					if(content!=null&&content!=""&&dd==true){
						double con=Double.parseDouble(content);
						 jxl.write.Number number = new jxl.write.Number (i, r, con,wcf);
						 sheet.addCell(number);
					}else{
						sheet.addCell(new Label(i, r, content,wcf));
					}
					
					
					
//					Label currentCell = new Label(i,r,content,wcf);
//					sheet.addCell(currentCell);
//					currentCell.setCellFormat(wcf);
//					}
					
//					if(!content.equals("")){
//						
//						DisplayFormat DisplayFormat = NumberFormats.FORMAT10;
//						WritableCellFormat wcfF = new WritableCellFormat(DisplayFormat);
//						
//						Double dContent = Double.valueOf(content);
//						
//						jxl.write.Number currentCell = new jxl.write.Number(r,i,dContent,wcfF);
//						
//						sheet.addCell(currentCell);
//					}
				}
			}
		}
	}
}













