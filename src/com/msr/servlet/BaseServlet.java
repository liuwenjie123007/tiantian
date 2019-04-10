package com.msr.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	public BaseServlet() {
		System.out.println("BaseServlet构造方法");
	}

	// 处理业务逻辑

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==========BaseServlet==========");
		// 1、设置中文乱码
		request.setCharacterEncoding("UTF-8");
		// 2、获取method参数
		String method = request.getParameter("method");
		// 判断method是否有值
		if (method == null || method.equals("") || method.trim().equals("")) {
			method = "execute";
		} else {
			try {
				// 3、获取对象的class:this 代表正在访问的子类servlet对象
				Method md = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
				// 4、执行方法
				String path = (String) md.invoke(this, request, response);
				if (path == null) {
					System.out.println("跳转路径为空，请检查！！");
				} else {
					// 页面跳转（转发）
					request.getRequestDispatcher(path).forward(request, response);
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected String execute(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
