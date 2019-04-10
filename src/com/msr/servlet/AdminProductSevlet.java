package com.msr.servlet;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.msr.bean.Product;
import com.msr.dao.ProductDao;
import com.msr.dao.impl.ProductDaoImpl;
import com.msr.utils.UUIDUtils;

import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


/**
 * Servlet implementation class AdminProductSevlet
 */
@WebServlet("/admin/AdminProductSevlet")
public class AdminProductSevlet extends BaseServlet {
	ProductDao pd = null;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5*1024 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	
	@Override
	public void init() throws ServletException {
		pd = new ProductDaoImpl();
		// 获取文件将被存储的位置
	 	filePath = getServletContext().getRealPath("/")+"images/goods/";
	}
	
	
	public String findAllProduct(HttpServletRequest req , HttpServletResponse resp) {
		List<Product> pList = pd.findAll();
		req.setAttribute("pList", pList);
		return "cate.jsp";
	}
	
	public String oneProduct(HttpServletRequest req , HttpServletResponse resp) {
		String pid = req.getParameter("pid");
		Product product = pd.getone(pid);
		product.setPid(pid);
		req.setAttribute("product", product);
		return "cate-edit.jsp";	
	}
	
	public String modifyProduct(HttpServletRequest req , HttpServletResponse resp) {
		String pid = req.getParameter("pid");
		String pname = req.getParameter("pname");
		String shop_price = req.getParameter("shop_price");
		String pimage = req.getParameter("pimage");
		String pdate = req.getParameter("pdate");
		String pdesc = req.getParameter("pdesc");
		String cid = req.getParameter("cid");
		
		Product product = new Product(pid, pname, Double.parseDouble(shop_price), pimage, pdate, pdesc, shop_price, cid);
		pd.ModifyProduct(product);
		req.setAttribute("product", product);
		return "cate-edit.jsp";
	}
	
	public String addProduct(HttpServletRequest req , HttpServletResponse resp) {
		String pid = UUIDUtils.getId();
		String pname = req.getParameter("pname");
		String shop_price = req.getParameter("shop_price");
		String pdate = req.getParameter("pdate");
		String pdesc = req.getParameter("pdesc");
		String cid = req.getParameter("cid");
		System.out.println("pname"+pname);
		System.out.println("shop_price"+shop_price);
		System.out.println("pdate"+pdate);
		System.out.println("pdesc"+pdesc);
		System.out.println("cid"+cid);
		String pimage = "images/goods/";
			// 检查我们有一个文件上传请求
		isMultipart = ServletFileUpload.isMultipartContent(req);
		System.out.println(pimage);
		System.out.println(filePath);
			DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件大小的最大值将被存储在内存中
		factory.setSizeThreshold(maxMemSize);
		// 保存大于maxMemSize的数据的位置.
		factory.setRepository(new File("/Users/liuwenjie/Desktop/temp"));
		// 创建一个新的文件上传处理程序
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 允许上传的文件大小的最大值
		upload.setSizeMax(maxFileSize);
		try {
			// 解析请求，获取文件项
			List fileItems = upload.parseRequest(req);
			// 处理上传的文件项
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// 获取上传文件的参数
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					System.out.println(fileName);
					pimage += fileName;
					System.out.println(pimage);
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// 写入文件
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("/")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("/") + 1));
					}
					fi.write(file);
				}else {
					if("pname".equals(fi.getFieldName())) {
						pname = fi.getString("UTF-8");
					}else if("shop_price".equals(fi.getFieldName())){
						shop_price = fi.getString("UTF-8");
					}else if("pdate".equals(fi.getFieldName())){
						pdate = fi.getString("UTF-8");
					}else if("pdesc".equals(fi.getFieldName())){
						pdesc = fi.getString("UTF-8");
					}else if("cid".equals(fi.getFieldName())){
						cid = fi.getString("UTF-8");
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("pnane:"+pname);
		Product product = new Product(pid, pname, Double.parseDouble(shop_price), pimage, pdate, pdesc, shop_price, cid);
		pd.addProduct(product);
		return "AdminProductSevlet?method=findAllProduct";

	}
	
	public String deleteProduct(HttpServletRequest req , HttpServletResponse resp) {
		System.out.println("=====adminproduct===delete=====");
		String pid = req.getParameter("pid");
		pd.deleteProduct(pid);	
		return "AdminProductSevlet?method=findAllProduct";
	}
	
}
