package com.msr.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FirstFilter
 */
@WebFilter(filterName = "/FirstFilter", urlPatterns = "/*")
public class FirstFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public FirstFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/*HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String path = req.getRequestURI();
		String username = (String)req.getSession().getAttribute("username");
		
		System.out.println(path);

		List<String> passList = new ArrayList<String>();
		passList.add("index.jsp");
		passList.add("login.jsp");
		passList.add("register.jsp");
		passList.add("IndexServlet");
		passList.add("ProductServlet");
		passList.add("cart.jsp");
		passList.add("CartServlet");
		passList.add("UserServlet");
		passList.add("js");
		passList.add("image");
		passList.add("css");
		
		if(username !=null) {
			chain.doFilter(request, response);
			return;
		}

		for (String pass : passList) {
			if (path.indexOf(pass) != -1) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		resp.sendRedirect("login.jsp");*/
		
		chain.doFilter(request, response);
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		System.out.println("=======FirstFilter已经启动==========");
		// TODO Auto-generated method stub
	}

}
