package com.msr.dao;

import java.util.List;

import com.msr.bean.User;

public interface UserDao {
	boolean login(String username, String pwd);

	void addUser(User newUser);

	String[] getUserInfo(String username);
	
	User getUserByUserName(String username);
	
	List<User> findAll();
	
	void modifyUser(User user);
	
	void deleteByUid(String uid);
	
	List<User> findByBirthday(String start,String end);
}
