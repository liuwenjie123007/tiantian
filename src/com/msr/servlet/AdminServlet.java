package com.msr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msr.bean.User;
import com.msr.dao.UserDao;
import com.msr.dao.impl.UserDaoImpl;
import com.msr.utils.UUIDUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin/AdminServlet")
public class AdminServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao ud = null;
	
	@Override
	public void init() throws ServletException {
		ud = new UserDaoImpl();
	}
	
	
	public String showAll(HttpServletRequest req, HttpServletResponse reps) {
		System.out.println("===========AdminServlet===============");
		
		List<User> uList = ud.findAll();
		req.setAttribute("uList", uList);
		
		
		return "member-list.jsp";
	}
	
	public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

		ud.addUser(newUser);
		request.setAttribute("newUser", newUser);
		return "member-edit.jsp";
	}
	public String modifyUser_show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("===========AdminServlet===============");
		String username = request.getParameter("username");
		User newUser = ud.getUserByUserName(username);
		request.setAttribute("newUser", newUser);
		
		return "member-edit.jsp";
	}
	public String modifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username1 = request.getParameter("username1");
		User oldUser = ud.getUserByUserName(username1);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");
		String telephone = request.getParameter("telephone");
		String birthday = request.getParameter("birthday");
		String name = request.getParameter("name");
		String uid = oldUser.getUid();
		String code = oldUser.getCode();
		User newUser = new User(uid, username, password, name, email, telephone, birthday, sex, 1, code);
		
		ud.modifyUser(newUser);
		
		request.setAttribute("newUser", newUser);
		return "member-edit.jsp";
	}
	
	public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===========AdminServlet delete===============");
		String uid = request.getParameter("uid");
		ud.deleteByUid(uid);
		return "AdminServlet?method=showAll";
	}
	
	public String search(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		System.out.println(username);
		System.out.println(start);
		System.out.println(end);
		
		if((username==null || username.trim().equals("")) && (start==null||start.trim().equals("")) && (end == null || end.trim().equals(""))) {
			return "AdminServlet?method=showAll";
		}
		
		if((start==null||start.trim().equals("")) && (end == null || end.trim().equals(""))) {
			User user = ud.getUserByUserName(username);
			List<User> uList = new ArrayList<User>();
			uList.add(user);
			req.setAttribute("uList", uList);
			return "member-list.jsp";
		}
		if((username==null || username.trim().equals(""))&& (start==null||start.trim().equals(""))) {
			List<User> uList = ud.findByBirthday(null, end);
			req.setAttribute("uList", uList);
			return "member-list.jsp";
		}else if((username==null || username.trim().equals(""))&&(end == null || end.trim().equals(""))) {
			List<User> uList = ud.findByBirthday(start, null);
			req.setAttribute("uList", uList);
			return "member-list.jsp";
		}else if((username==null || username.trim().equals(""))) {
			List<User> uList = ud.findByBirthday(start, end);
			req.setAttribute("uList", uList);
			return "member-list.jsp";
		}
		
		
		return "member-list.jsp";
	}
}
