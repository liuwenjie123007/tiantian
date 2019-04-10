package com.msr.servlet;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.msr.bean.Category;
import com.msr.dao.CategoryDao;
import com.msr.dao.impl.CategoryDaoImpl;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
@WebListener
public class MyListener implements ServletContextListener {
	public MyListener() {
		System.out.println("MyListener构造函数被调用");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("MyListener被销毁");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent ap) {
		System.out.println("MyListener初始化函数被启用");
		CategoryDao cd = new CategoryDaoImpl();
		List<Category> cList = cd.findAll();
		ap.getServletContext().setAttribute("category", cList);
	}

}
