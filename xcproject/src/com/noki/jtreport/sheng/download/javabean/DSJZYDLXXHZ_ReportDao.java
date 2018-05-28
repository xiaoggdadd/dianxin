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
 * 地市基站用电量信息汇总表.xls（表一）
 * @author 王海
 *
 */
public class DSJZYDLXXHZ_ReportDao extends ReportDao {
	@Override
	//子类  重新了 父类的方法 doQuery（）
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		Integer startRow = 0;
		Integer endRow = 3;
		WritableWorkbook firstBook = books.get(0);//获取首个报表，作为其他报表的汇总处
		WritableSheet sheet = firstBook.getSheet(0);
		WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); //字体格式
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
		wcf.setAlignment(jxl.format.Alignment.RIGHT);//对齐方式
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
		int flag = 0;
		ArrayList list=ReportCopyOperate.shixs();//对地市排的顺序
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();//查询出的所有的市名
		String shi="";
		String shi1="";
		for(int e=0;e<books.size();e++){//报表的个数
			shi1 = books.get(0).getSheet(0).getCell(0,3).getContents();//获取第一个市名称
			String shiName3 = books.get(e).getSheet(0).getCell(0,3).getContents().toString();//获取所有市名称  存到list里
			
			System.out.println("++++查询出来的shiName3"+shiName3);
			list2.add(shiName3);
		}
		//获取excel中有数据的行数    排序
		//String shiname1=""; //解决只有一个地市报表的问题
		for(int i=0;i<list.size();i++){			
			shi=(String) list.get(i);
			System.out.println("**********************************排序市名循环i:"+i+"shi="+shi);
			boolean a = false;
			for(int n=0;n<books.size();n++){
				//System.out.println("=================查询出的报表循环n:"+n);				
		     /*  if(i==0&&books.size()==1){   //解决只有一个地市报表的问题
		    	    shiname1= books.get(0).getSheet(0).getCell(0,3).getContents();
				}*/
				String shiName2 = books.get(n).getSheet(0).getCell(0,3).getContents();//获取查询出来的报表的市名	
				/*if(books.size()==1){ //解决只有一个地市报表的问题
					shiName2=shiname1;
				}*/
				System.out.println("========shiName2:"+shiName2+"  ===第一行数据："+books.get(n).getSheet(0).getCell(1,3).getContents()+"n="+n);
				for(int l=0;l<list2.size();l++){
					if(shi.equals(list2.get(l))){//排序的市名 和查询出来的市名  是否相同
						a = true;
					}
				}	
				startRow = endRow;
				if(shi.equals(shi1)){ //排序的第一个市名   和  查询到的第一个市名 是否相同
					endRow += 23;//报表就23行数据
					flag=flag+1;//
					
					System.out.println("======++++++++++++++++++++++++++++++++=============第一个市与排序的是否相等"+"shi:"+shi+"  shi1"+shi1+"endRow="+endRow);
					list1.add(shi);
					break;//第一个报表跳过
				}else{
					if(shiName2.equals(shi)){//查询出来的报表的市名和 排序的市名是否一样						
						ReportCopyOperate.copyCells(books.get(n).getSheet(0),sheet, 0, 3, 18, 25, 0, startRow);//复制报表单元格的数据
						sheet.mergeCells(0, endRow, 0, endRow+22);//合并单元格
						System.out.println("查询出来的报表的市名和 排序的市名是否一样	"+"shiName2:"+shiName2+"  shi"+shi+"endRow="+endRow);
						
						list1.add(shi);
						endRow += 23;
					}else if(!a){//排序的市名 和查询出来的市名  不相同
						boolean b = true;
						for(int j=0;j<list1.size();j++){
							//System.out.println("已经复制过的地市名称循环j:"+j+"endRow="+endRow);
							if(shi==list1.get(j)||shiName2==list1.get(j)){
								//System.out.println("排序shi:"+shi+" 已经复制过的"+list1.get(j)+" 查询的shiName2"+shiName2+"endRow="+endRow);
								b = false;
							}
						}
						System.out.println("!!!!!!!如个为空出来b:"+b+list.get(i)+"endRow="+endRow);
						if(b){//如果没有报表  空出来
							//if(!shi11.equals(shi1))  如果第一个地市没有报表 应该加23行  市名为济南时没有报表应该加23行
							sheet.insertRow(startRow);
							Label label = new Label(0,startRow,list.get(i).toString(),wcf);
							System.out.println("没有报表的市名："+list.get(i)+"endRow="+endRow);
							sheet.addCell(label);
							sheet.mergeCells(0, endRow, 0, endRow+22);
							list1.add(shi);
							endRow += 23;
							System.out.println("插入完成后："+"endRow="+endRow);
						}	
					}			
				}
				
				//ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
			}	
		}
		
		
		
		
		/*for(WritableWorkbook book:books){
			startRow = endRow;
			endRow += 23;
			if(flag++ == 0)continue;//第一个报表跳过
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 18, 25, 0, startRow);
			sheet.mergeCells(0, startRow, 0, endRow-1);
		}*/
		
		//----------------------统计----------------------
		ReportCopyOperate.copyCells(sheet,sheet, 0, 3, 18, 25, 0, endRow);//复制一个合计
		Label heji = new Label(0,endRow,"合计");//文本类型
		sheet.addCell(heji);
		sheet.mergeCells(0, endRow, 0, endRow+22);
		
//		Pattern pattern = Pattern.compile("^[+\\-]?\\d+(.\\d+)?$");
		
		for(int i = 2;i<18;i++){//逐列遍历      18列
			float lx1=0,lx2=0,lx3=0,lx4=0,lx5=0,lx6=0,lx7=0,lx8=0,lx9=0,lx10=0,lx11=0,lx12=0,lx13=0,lx14=0,lx15=0,lx16=0,lx17=0,lx18=0,lx19=0,lx20=0,lx21=0,lx22=0,lx23=0;
			for(int r = 3;r<endRow+23;r++){//逐行遍历
				String leixing = sheet.getCell(1, r).getContents().trim();//获取该行第二列的内容，即属于哪个类型
				String content = "";
				if(r<endRow){//统计之前的数据
					content = sheet.getCell(i, r).getContents();//获取该单元格内容
					if(leixing.equals("类型1"))lx1 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型2"))lx2 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型3"))lx3 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型4"))lx4 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型5"))lx5 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型6"))lx6 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型7"))lx7 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型8"))lx8 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型9"))lx9 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型10"))lx10 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型11"))lx11 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型12"))lx12 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型13"))lx13 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型14"))lx14 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型15"))lx15 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型16"))lx16 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型17"))lx17 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型18"))lx18 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型19"))lx19 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型20"))lx20 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型21"))lx21 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("类型22"))lx22 += content.trim().equals("")?0:Double.valueOf(content);
					else if(leixing.equals("室外站"))lx23 += content.trim().equals("")?0:Double.valueOf(content);
				}
				else{//准备统计
					if(leixing.equals("类型1"))content += Math.round(lx1*100)/100f;
					else if(leixing.equals("类型2"))content += Math.round(lx2*100)/100f;
					else if(leixing.equals("类型3"))content += Math.round(lx3*100)/100f;
					else if(leixing.equals("类型4"))content += Math.round(lx4*100)/100f;
					else if(leixing.equals("类型5"))content += Math.round(lx5*100)/100f;
					else if(leixing.equals("类型6"))content += Math.round(lx6*100)/100f;
					else if(leixing.equals("类型7"))content += Math.round(lx7*100)/100f;
					else if(leixing.equals("类型8"))content += Math.round(lx8*100)/100f;
					else if(leixing.equals("类型9"))content += Math.round(lx9*100)/100f;
					else if(leixing.equals("类型10"))content += Math.round(lx10*100)/100f;
					else if(leixing.equals("类型11"))content += Math.round(lx11*100)/100f;
					else if(leixing.equals("类型12"))content += Math.round(lx12*100)/100f;
					else if(leixing.equals("类型13"))content += Math.round(lx13*100)/100f;
					else if(leixing.equals("类型14"))content += Math.round(lx14*100)/100f;
					else if(leixing.equals("类型15"))content += Math.round(lx15*100)/100f;
					else if(leixing.equals("类型16"))content += Math.round(lx16*100)/100f;
					else if(leixing.equals("类型17"))content += Math.round(lx17*100)/100f;
					else if(leixing.equals("类型18"))content += Math.round(lx18*100)/100f;
					else if(leixing.equals("类型19"))content += Math.round(lx19*100)/100f;
					else if(leixing.equals("类型20"))content += Math.round(lx20*100)/100f;
					else if(leixing.equals("类型21"))content += Math.round(lx21*100)/100f;
					else if(leixing.equals("类型22"))content += Math.round(lx22*100)/100f;
					else if(leixing.equals("室外站"))content += Math.round(lx23*100)/100f;
					
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













