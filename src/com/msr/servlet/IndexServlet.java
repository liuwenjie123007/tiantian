package com.msr.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.bean.Category;
import com.msr.dao.CategoryDao;
import com.msr.dao.ProductDao;
import com.msr.dao.impl.CategoryDaoImpl;
import com.msr.dao.impl.ProductDaoImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	ProductDao pdi = null;
	
	@Override
	public void init() throws ServletException {
		pdi = new ProductDaoImpl();
	}
	public String getImage(HttpServletRequest req, HttpServletResponse resp) {
		//1.加载图片
				Map<String,List> map = pdi.getFI("1");
				Map<String,List> map1 = pdi.getFI("2");
				Map<String,List> map2 = pdi.getFI("3");
				Map<String,List> map3 = pdi.getFI("4");
				Map<String,List> map4 = pdi.getFI("5");
				Map<String,List> map5 = pdi.getFI("6");
			
				req.setAttribute("images", map);
				req.setAttribute("images1", map1);
				req.setAttribute("images2", map2);
				req.setAttribute("images3", map3);
				req.setAttribute("images4", map4);
				req.setAttribute("images5", map5);
				
		
		return "index.jsp";
		
	}
	
}
