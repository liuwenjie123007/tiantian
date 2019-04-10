package com.msr.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestPOI2 {

	public static void main(String[] args) {
		//(1)创建Workbook和Sheet
		//文件路径
		String filePath = "/Users/liuwenjie/Desktop/java_sourse/temp/test.xls";
		//创建Excel(Workbook)
		HSSFWorkbook workbook = new HSSFWorkbook();
		//(2)创建单元格
		//创建工作表（Sheet）
		HSSFSheet sheet = workbook.createSheet("Test");
		//创建行，从0开始
		HSSFRow row = sheet.createRow(0);
		//创建行的单元格，也是从0开始
		HSSFCell cell = row.createCell(0);
		//创建单元格内容
		cell.setCellValue("刘文杰");
		//设置单元格内容，重载
		row.createCell(1).setCellValue(false);
		row.createCell(2).setCellValue(new Date());
		row.createCell(3).setCellValue(12.345);
		
		//（3）创建文档摘要信息
		//创建文档信息
		workbook.createInformationProperties();
		//摘要信息
		DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
		//类别
		dsi.setCategory("类别：Excel文件");
		//管理者
		dsi.setManager("管理者：刘文杰");
		//公司
		dsi.setCompany("公司：msr");
		//摘要信息
		SummaryInformation si = workbook.getSummaryInformation();
		//主题
		si.setSubject("主题：一一");
		//标题
		si.setTitle("标题：测试文档");
		//作者
		si.setAuthor("作者：刘文杰");
		//备注
		si.setComments("备注：POI测试文档");
		
		
		//（4）创建批注
		HSSFPatriarch patr = sheet.createDrawingPatriarch();
		//创建批注位置
		HSSFClientAnchor anchor =patr.createAnchor(0, 0, 0, 0, 5, 1, 8, 3);
		//创建批注
		HSSFComment comment = patr.createComment(anchor);
		//设置批注内容
		comment.setString(new HSSFRichTextString("这是一个批注段略！"));
		//设置批注作者
		comment.setAuthor("刘文杰");
		//设置批注默认显示
		comment.setVisible(true);
		cell = sheet.createRow(0).createCell(1);
		cell.setCellValue("测试");
		//把批注赋值给单元格
		cell.setCellComment(comment);
		
		//(5)创建页眉和页脚
		//得到页眉
		HSSFHeader header = sheet.getHeader();
		header.setLeft("页眉左边");
		header.setRight("页眉右边");
		header.setCenter("页眉中间");
		//得到页脚
		HSSFFooter footer = sheet.getFooter();
		footer.setLeft("页脚左边");
		footer.setRight("页眉右边");
		footer.setCenter("页脚中间"+HSSFHeader.tab());
		
		
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			//保存Excel文件
			workbook.write(out);
			//关闭流
			out.close();
			System.out.println("OK！");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
