package com.msr.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.msr.bean.Category;
import com.msr.bean.OrderItem;
import com.msr.bean.Orders;
import com.msr.bean.Product;
import com.msr.bean.User;
import com.msr.dao.CategoryDao;
import com.msr.dao.OrderItemDao;
import com.msr.dao.OrdersDao;
import com.msr.dao.ProductDao;
import com.msr.dao.UserDao;
import com.msr.dao.impl.CategoryDaoImpl;
import com.msr.dao.impl.OrderItemDaoImpl;
import com.msr.dao.impl.OrdersDaoImpl;
import com.msr.dao.impl.ProductDaoImpl;
import com.msr.dao.impl.UserDaoImpl;

public class ExcelUtil {
	
	private String filePath = "/Users/liuwenjie/Desktop/java_sourse/temp/";
	OrdersDao od = new OrdersDaoImpl();
	ProductDao pd = new ProductDaoImpl();
	OrderItemDao oid = new OrderItemDaoImpl();
	UserDao ud = new UserDaoImpl();
	CategoryDao cd = new CategoryDaoImpl();
	FileOutputStream out = null;
	
	
	
	public ExcelUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelUtil(String filePath) {
		super();
		this.filePath = filePath;
	}

	/**
	 * 打印所有表
	 * @return
	 */
	
	public HSSFWorkbook printAll() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook = printOrders(workbook);
		workbook = printproduct(workbook);
		workbook = printOrderItem(workbook);
		workbook = printUser(workbook);
		workbook = printCategory(workbook);
		
		return workbook;
	}
	
	public HSSFWorkbook printUser(HSSFWorkbook workbook) {
		HSSFSheet user_sheet = workbook.createSheet("user");
		List<User> uList = ud.findAll();
		String[] str = {"用户编号","用户名","密码","姓名","电子邮箱","电话号码","生日","性别","账号状态","激活码"};
		createTitle(user_sheet, str);
		int rowcount = 1;
		for(User u : uList) {
			HSSFRow row = user_sheet.createRow(rowcount);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(u.getUid());
			cell = row.createCell(1);
			cell.setCellValue(u.getUsername());
			cell = row.createCell(2);
			cell.setCellValue(u.getPassword());
			cell = row.createCell(3);
			cell.setCellValue(u.getName());
			cell = row.createCell(4);
			cell.setCellValue(u.getEmail());
			cell = row.createCell(5);
			cell.setCellValue(u.getTelephone());
			cell = row.createCell(6);
			cell.setCellValue(u.getBirthday());
			cell = row.createCell(7);
			cell.setCellValue(u.getSex());
			cell = row.createCell(8);
			cell.setCellValue(u.getState());
			cell = row.createCell(9);
			cell.setCellValue(u.getCode());
			rowcount++;
		}
		
		return workbook;
	}
	
	public HSSFWorkbook printCategory(HSSFWorkbook workbook) {
		List<Category> cList = cd.findAll();
		HSSFSheet category_sheet = workbook.createSheet("category");
		String[] str = {"分类编号","分类名"};
		createTitle(category_sheet, str);
		int rowcount = 1;
		for(Category c : cList) {
			HSSFRow row = category_sheet.createRow(rowcount);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(c.getCid());
			cell = row.createCell(1);
			cell.setCellValue(c.getCname());
			rowcount++;
		}
		
		return workbook;
	}
	
	public HSSFWorkbook printOrders(HSSFWorkbook workbook) {
		List<Orders> oList = od.findAll();
		workbook = new HSSFWorkbook();
		HSSFSheet order_sheet = workbook.createSheet("order");
	
		//创建标题行
		HSSFRow row = order_sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("订单编号");
		cell = row.createCell(1);
		cell.setCellValue("下单时间");
		cell = row.createCell(2);
		cell.setCellValue("订单总金额");
		cell = row.createCell(3);
		cell.setCellValue("订单状态");
		cell = row.createCell(4);
		cell.setCellValue("收货地址");
		cell = row.createCell(5);
		cell.setCellValue("收货人");
		cell = row.createCell(6);
		cell.setCellValue("联系电话");
		cell = row.createCell(7);
		cell.setCellValue("用户编号");
		int rowcount = 1;
		for(Orders orders : oList) {
			row = order_sheet.createRow(rowcount);
			String oid = orders.getOid();
			String orderTime = orders.getOrderTime();
			double total = orders.getTotal();
			int state = orders.getState();
			String address = orders.getAddress();
			String name = orders.getName();
			String telephone = orders.getOrderTime();
			User user = orders.getUser();
			String userid = user.getUid();
			
			cell = row.createCell(0);
			cell.setCellValue(oid);
			cell = row.createCell(1);
			cell.setCellValue(orderTime);
			cell = row.createCell(2);
			cell.setCellValue(total);
			cell = row.createCell(3);
			cell.setCellValue(state);
			cell = row.createCell(4);
			cell.setCellValue(address);
			cell = row.createCell(5);
			cell.setCellValue(name);
			cell = row.createCell(6);
			cell.setCellValue(telephone);
			cell = row.createCell(7);
			cell.setCellValue(userid);
			rowcount++;	
		}
		return workbook;
	}
	
	
	public void createTitle(HSSFSheet sheet,String[] str) {
		//创建标题行
		HSSFRow row = sheet.createRow(0);
		for(int i=0 ; i<str.length ; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
	}
	public HSSFWorkbook printOrderItem(HSSFWorkbook workbook) {
		HSSFSheet orderItem_sheet = workbook.createSheet("orderItem");
		List<OrderItem> oiList = oid.findAll();
		String[] str = {"订单项编号","商品数量","小计","商品编号","订单编号"};
		createTitle(orderItem_sheet, str);
		
		int rowcount = 1;
		for(OrderItem oi : oiList) {
			HSSFRow row = orderItem_sheet.createRow(rowcount);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(oi.getItemid());
			cell = row.createCell(1);
			cell.setCellValue(oi.getQuantity());
			cell = row.createCell(2);
			cell.setCellValue(oi.getTotal());
			cell = row.createCell(3);
			cell.setCellValue(oi.getProduct().getPid());
			cell = row.createCell(4);
			cell.setCellValue(oi.getOders().getOid());
			rowcount++;
		}
		
		return workbook;
	}
	
	
	public HSSFWorkbook printproduct(HSSFWorkbook workbook) {
		HSSFSheet product_sheet = workbook.createSheet("product");
		List<Product> pList = pd.findAll();
		String[] str = {"产品编号","产品名","产品价格","图片路径","上市时间","产品描述","产品状态","分类编号"};
		createTitle(product_sheet, str);
		int rowcount = 1;
		for(Product p : pList) {
			HSSFRow row = product_sheet.createRow(rowcount);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(p.getPid());
			cell = row.createCell(1);
			cell.setCellValue(p.getPname());
			cell = row.createCell(2);
			cell.setCellValue(p.getShop_price());
			cell = row.createCell(3);
			cell.setCellValue(p.getPimage());
			cell = row.createCell(4);
			cell.setCellValue(p.getPdate());
			cell = row.createCell(5);
			cell.setCellValue(p.getPdesc());
			cell = row.createCell(6);
			cell.setCellValue(p.getPflag());
			cell = row.createCell(7);
			cell.setCellValue(p.getCid());
			rowcount++;
		}	
		return workbook;
	}
	
	
	
	/**
	 * 打印输入的Excel表格
	 * @param workbook Excel表格
	 */
	public void output(HSSFWorkbook workbook) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_mm_dd_HH_mm");
			Date date = new Date();
			filePath = filePath + sf.format(date) + ".xls";
			out = new FileOutputStream(new File(filePath));
			workbook.write(out);
			out.close();
			System.out.println("OK!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
