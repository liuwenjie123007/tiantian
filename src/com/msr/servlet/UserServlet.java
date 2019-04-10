package com.msr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.msr.bean.User;
import com.msr.dao.UserDao;
import com.msr.dao.impl.UserDaoImpl;
import com.msr.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	UserDao userdao = null;

	@Override
	public void init() throws ServletException {
		userdao = new UserDaoImpl();
	}

	public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");

		// 3、验证用户名和密码
		HttpSession session = req.getSession();
		if (userdao.login(username, pwd)) {
			// 4、保存数据
			session.setAttribute("username", username);
			// 5、重定向到首页
			// resp.sendRedirect("index.jsp");
			return "index.jsp";
		} else {
			// 4、保存错误信息
			session.setAttribute("loginfalse", "用户名或密码错误！！");
			// 5、重定向到登录页面
			// resp.sendRedirect("login.jsp");
			return "index.jsp";
		}
	}

	public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String telephone = request.getParameter("telephone");
		String birthday = request.getParameter("birthday");
		String name = request.getParameter("name");
		String uid = UUIDUtils.getId();
		String code = UUIDUtils.getUUID64();
		User newUser = new User(uid, username, password, name, email, telephone, birthday, sex, 1, code);

		userdao.addUser(newUser);

		request.getSession().setAttribute("username", username);
		// response.sendRedirect("index.jsp");
		return "index.jsp";
	}

	public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().removeAttribute("username");
		// resp.sendRedirect("index.jsp");
		return "index.jsp";
	}
	
	public String getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
		String username = (String)req.getSession().getAttribute("username");
		User user = userdao.getUserByUserName(username);
		req.getSession().setAttribute("user", user);

		return "user_center_info.jsp";
	}
}
