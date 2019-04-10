package com.msr.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestPOI1 {

	public static void main(String[] args) {
		
		//(1)创建Workbook和Sheet
		//文件路径
		String filePath = "/Users/liuwenjie/Desktop/java_sourse/temp/test.xls";
		//创建Excel(Workbook)
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet = workbook.createSheet();
		//创建一个标题名为Test的工作表
		sheet = workbook.createSheet("Test");
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
